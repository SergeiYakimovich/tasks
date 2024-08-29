package utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MyDateTimeUtils {
    public static void showDuration(Duration duration) {
        System.out.print("Days:" + duration.toDays());
        System.out.print(" Hours:" + duration.toHoursPart());
        System.out.print(" Minutes:" + duration.toMinutesPart());
        System.out.print(" Seconds:" + duration.toSecondsPart());
        System.out.println(" Nanos:" + duration.toNanosPart());
    }

    public static void myTimer(Runnable method, TimeUnit timeUnit) {
        long time = System.nanoTime();
        method.run();
        time = System.nanoTime() - time;
        time = TimeUnit.NANOSECONDS.convert(time, timeUnit);
        System.out.printf("Time= %,9.3f ms\n", time/1_000_000.0);
    }
}
