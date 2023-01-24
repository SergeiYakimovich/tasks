package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.gridnine.testing.FlightBuilder.createFlights;
import static com.gridnine.testing.FlightsFiltration.DepBeforeArr;
import static com.gridnine.testing.FlightsFiltration.depAfterDateTime;
import static com.gridnine.testing.FlightsFiltration.makeFiltration;
import static com.gridnine.testing.FlightsFiltration.stayLessThen;

public class Main {
    public static void main() {
        List<Flight> filteredFlights; // список полетов после фильтрации
        List<Predicate<Flight>> rools; // список правил фильтрации

        List<Flight> flights = createFlights();
        showFlights(flights);

        Predicate<Flight> rool1 = depAfterDateTime(LocalDateTime.now()); // создаем правило фильтрации
        rools = List.of(rool1); // кладем правило в список правил
        filteredFlights = makeFiltration(flights, rools); // фильтруем полеты
        showFlights(filteredFlights); // смотрим результат

        Predicate<Flight> rool2 = DepBeforeArr();
        rools = List.of(rool2);
        filteredFlights = makeFiltration(flights, rools);
        showFlights(filteredFlights);

        Predicate<Flight> rool3 = stayLessThen(2);
        rools = List.of(rool3);
        filteredFlights = makeFiltration(flights, rools);
        showFlights(filteredFlights);

        // отфильтруем по всем 3-м правилам
        rools = List.of(rool1, rool2, rool3);
        filteredFlights = makeFiltration(flights, rools);
        showFlights(filteredFlights);
    }

    /**
     * Вывод на экран списка полетов
     * @param flights список полетов
     */
    public static void showFlights(List<Flight> flights) {
        if(flights.size() == 0) {
            System.out.println("\nNo flights");
            return;
        }
        System.out.println("\nFligths :");
        int[] count = {0};
        flights.stream()
                .forEach(x -> System.out.println(++count[0] + " - " + x.toString()));
    }

}
