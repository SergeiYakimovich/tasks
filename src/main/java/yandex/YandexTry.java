package yandex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class YandexTry { // тестирование системы ввода-вывода данных

    class App1 {
        public static void main(String[] args) throws Exception {
            Path path = Paths.get("input.txt");
            String contents = Files.readString(path);
            String[] mas = contents.trim().split(" ");
            Integer a = Integer.parseInt(mas[0]);
            Integer b = Integer.parseInt(mas[1]);
            System.out.println(a + b);
        }
    }

    class App2 {
        public static void main(String[] args)  {
            Path path = Paths.get("input.txt");
            String contents = null;
            try {
                contents = Files.readString(path);
            } catch (IOException ex) {
                return;
            }

            String[] mas = contents.trim().split(" ");
            Integer a = 0;
            Integer b = 0;
            try {
                a = Integer.parseInt(mas[0]);
                b = Integer.parseInt(mas[1]);
            } catch (Exception ex) {
                return;
            }

            Path pathOut = Paths.get("output.txt");
            String textOut = String.valueOf(a + b);
            try {
                Files.writeString(pathOut, textOut);
            } catch (IOException ex) {
                return;
            }
        }
    }

    class App3 {
        public static void main(String[] args) throws Exception {
            String[] mas = new String[2];
            Scanner sc = new Scanner(System.in);
            mas[0] = sc.next();
            mas[1] = sc.next();
            Integer a = Integer.parseInt(mas[0]);
            Integer b = Integer.parseInt(mas[1]);
            System.out.println(a + b);
        }
    }

    class App4 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String J = "";
            String S = "";
            try {
                J = sc.next();
                S = sc.next();
            } catch (Exception ex) {
                System.out.println(0);
                return;
            }
            int result = 0;
            for (int i = 0; i < S.length(); i++) {
                char ch = S.charAt(i);
                if (J.indexOf(ch) >= 0) {
                    result++;
                }
            }
            System.out.println(result);
        }
    }

    class App5 { // задача Андрей и резервуары

        public static void main(String[] args) throws Exception {
            Path path = Paths.get("input.txt");
            String textIn = Files.readString(path);

            String[] masIn = textIn.trim().split("\n");
            String[] reserv = masIn[1].trim().split(" ");
            Integer n = Integer.parseInt(masIn[0]);
            Integer[] mas = new Integer[n];
            for(int i=0; i < n; i++) {
                mas[i] = Integer.parseInt(reserv[i]);
            }

            int prev = mas[0];
            for(int i=1; i < n; i++) {
                if(mas[i] < prev) {
                    System.out.println(-1);
                    return;
                } else {
                    prev = mas[i];
                }
            }

            Path pathOut = Paths.get("output.txt");
            String textOut = String.valueOf(mas[mas.length - 1] - mas[0]);
            Files.writeString(pathOut, textOut);
        }

    }


}
