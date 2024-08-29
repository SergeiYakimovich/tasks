package yandex.context3;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Quoting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();

        Map<Long, String> logs = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            long time = scanner.nextLong();
            String token = scanner.nextLine().trim();
            logs.put(time, token);
        }

        for (Map.Entry<Long, String> log : logs.entrySet()) {
            long time = log.getKey();
            if (countLogs(logs, time) < n) {
                System.out.println(time + " " + log.getValue());
            }
        }

        scanner.close();
    }

    private static long countLogs(Map<Long, String> logs, long time) {
        return logs.keySet().stream()
                .filter(t -> isValid(t, time))
                .count();
    }

    private static boolean isValid(long time1, long time2) {
        Instant instant1 = Instant.ofEpochMilli(time1);
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneId.of("UTC"));
        Instant instant2 = Instant.ofEpochMilli(time2);
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.of("UTC"));

        return localDateTime1.isBefore(localDateTime2) && localDateTime1.isAfter(localDateTime2.minusSeconds(1));
    }
}
