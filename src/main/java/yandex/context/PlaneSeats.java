package yandex.context;

import java.util.Map;
import java.util.Scanner;

public class PlaneSeats {
    private static final char freeSeat = '.';
    private static final char occupiedSeat = '#';
    private static final char bookedSeat = 'X';
    private static final Map<Integer, Character> seatsMap = Map.of(0, 'A', 1, 'B', 2, 'C', 3, '_', 4, 'D', 5, 'E', 6, 'F');

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(Paths.get("input.txt"));
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        char[][] plane = new char[rows][7];
        for (int i = 0; i < rows; i++) {
            String nextStr = scanner.nextLine();
            plane[i] = nextStr.toCharArray();
        }

        int groups = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < groups; i++) {
            int numberOfPeople = scanner.nextInt();
            String side = scanner.next();
            String requirement = scanner.next();

            seatPeople(plane, numberOfPeople, side, requirement);
        }

        scanner.close();
    }

    private static void seatPeople(char[][] plane, int numberOfPeople, String side, String requirement) {
        for (int i = 0; i < plane.length; i++) {
            if (tryBookSeatsOnRow(plane[i], numberOfPeople, side, requirement)) {
                showBookedSeats(i, plane[i]);
                showPlane(plane);
                fillBookedSeats(plane[i], occupiedSeat);
                return;
            } else {
                fillBookedSeats(plane[i], freeSeat);
            }
        }
        System.out.println("Cannot fulfill passengers requirements");
    }

    private static void showBookedSeats(int rowNumber, char[] row) {
        System.out.print("Passengers can take seats:");
        for (int j = 0; j < row.length; j++) {
            if (row[j] == bookedSeat) {
                System.out.print(" " + (rowNumber+1) + seatsMap.get(j));
            }
        }
        System.out.println();
    }

    private static boolean tryBookSeatsOnRow(char[] row, int numberOfPeople, String side, String requirement) {
        if (side.equals("left")) {
            return requirement.equals("window") ?
                    bookSeats(row, numberOfPeople, 0, 1) :
                    bookSeats(row, numberOfPeople, 2, -1);
        } else {
            return requirement.equals("window") ?
                    bookSeats(row, numberOfPeople, 6, -1) :
                    bookSeats(row, numberOfPeople, 4, 1);
        }
    }

    private static boolean bookSeats(char[] row, int numberOfPeople, int firstSeat, int direction) {
        for(int i = 0; i < numberOfPeople; i++) {
            if(row[firstSeat] == freeSeat) {
                row[firstSeat] = bookedSeat;
                firstSeat += direction;
            } else {
                return false;
            }
        }
        return true;
    }

    private static void fillBookedSeats(char[] row, char simbol) {
        for(int i = 0; i < row.length; i++) {
            if(row[i] == bookedSeat) {
                row[i] = simbol;
            }
        }
    }

    private static void showPlane(char[][] plane) {
        for (char[] rows : plane) {
            for (char seat : rows) {
                System.out.print(seat);
            }
            System.out.println();
        }
    }

}
