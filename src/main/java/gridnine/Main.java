package gridnine;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main() {
        List<Flight> filteredFlights; // список полетов после фильтрации
        List<Predicate<Flight>> rools; // список правил фильтрации

        List<Flight> flights = FlightBuilder.createFlights();
        showFlights(flights);

        Predicate<Flight> rool1 = FlightsFiltration.depAfterDateTime(LocalDateTime.now()); // создаем правило фильтрации
        rools = List.of(rool1); // кладем правило в список правил
        filteredFlights = FlightsFiltration.makeFiltration(flights, rools); // фильтруем полеты
        showFlights(filteredFlights); // смотрим результат

        Predicate<Flight> rool2 = FlightsFiltration.DepBeforeArr();
        rools = List.of(rool2);
        filteredFlights = FlightsFiltration.makeFiltration(flights, rools);
        showFlights(filteredFlights);

        Predicate<Flight> rool3 = FlightsFiltration.stayLessThen(2);
        rools = List.of(rool3);
        filteredFlights = FlightsFiltration.makeFiltration(flights, rools);
        showFlights(filteredFlights);

        // отфильтруем по всем 3-м правилам
        rools = List.of(rool1, rool2, rool3);
        filteredFlights = FlightsFiltration.makeFiltration(flights, rools);
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
