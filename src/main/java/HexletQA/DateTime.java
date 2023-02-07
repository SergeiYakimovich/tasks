package HexletQA;

import java.sql.Time;
import java.util.Date;

/**
 * класс для работы с датами
 */
public class DateTime {

    public static void getDateTime() {

        Date date = new Date();
        System.out.println(date.toString()); // => Wed Nov 30 16:56:56 MSK 2022

        Time time = new Time(date.getTime());
        System.out.println(time.toString()); // => 16:56:56
    }

}
