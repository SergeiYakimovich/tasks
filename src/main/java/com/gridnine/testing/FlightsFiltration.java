package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightsFiltration {
    /**
     * Фильтрация списка полетов по правилу
     * @param flights список полетов
     * @param rools список правил фильтрации
     * @return отфильтрованный список полетов
     */
    public static List<Flight> makeFiltration(List<Flight> flights, List<Predicate<Flight>> rools) {
        return flights.stream()
                .filter(x -> isFlightGood(x, rools))
                .collect(Collectors.toList());
    }

    /**
     * Проверка полета на соответствие списку правил
     * @param flight полет
     * @param rools список правил
     * @return true, если полет удовлетворяет списку правил и false, если нет
     */
    public static boolean isFlightGood(Flight flight, List<Predicate<Flight>> rools) {
        for (Predicate<Flight> rool : rools) {
            if(!rool.test(flight)) return false;
        }
        return true;
    }

    /**
     * Фильтрация всех полетов по вылету до заданного момента времени
     * @param dep момент времени
     * @return правило фильтрации
     */
    public static Predicate<Flight> depAfterDateTime(LocalDateTime dep) {
        return x -> {
            for(Segment segment : x.getSegments()) {
                if(segment.getDepartureDate().isBefore(dep)) return false;
            }
            return true;
        };
    }

    /**
     * Фильтрация всех полетов по сегментам с датой прилёта раньше даты вылета
     * @return правило фильтрации
     */
    public static Predicate<Flight> DepBeforeArr() {
        return x -> {
            for(Segment segment : x.getSegments()) {
                if(segment.getArrivalDate().isBefore(segment.getDepartureDate())) return false;
            }
            return true;
        };
    }

    /**
     * Фильтрация всех полетов по общему времени, проведённому на земле
     * @param maxHours максимально допустимое число часов простоя
     * @return правило фильтрации
     */
    public static Predicate<Flight> stayLessThen(int maxHours) {
        return x -> {
            int stayHours = 0;
            for(int i = 1; i < x.getSegments().size(); i++) {
                LocalDateTime depTime = x.getSegments().get(i).getDepartureDate();
                LocalDateTime arrTime = x.getSegments().get(i - 1).getArrivalDate();
                stayHours += ChronoUnit.MINUTES.between(arrTime, depTime);
            }
            return stayHours / 60 <= maxHours;
        };
    }
}
