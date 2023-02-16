package HexletQA;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * класс для работы с датой-временем
 */
public class DateTime {
    /**
     * создание таймеров прямого и обратного отсчета
     */
    public static void makeTimer() {
        JLabel label1 = new JLabel();
        java.util.Timer timer1 = new java.util.Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            int i = 20;
            public void run() {
                label1.setText("Таймер 1 - осталось : " + i + " сек.");
                i--;
                if (i < 0) {
                    timer1.cancel();
                    label1.setText("Таймер 1 - время вышло");
                }
            }
        }, 0, 1000);

        JLabel label2 = new JLabel();
        java.util.Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            int i = 0;
            public void run() {
                label2.setText("Таймер 2 - прошло : " + i + " сек.");
                i++;
            }
        }, 0, 1000);

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setBounds(500, 300, 300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label1, BorderLayout.NORTH);
        frame.add(label2, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void getDateTime() {

        Date date = new Date();
        System.out.println(date.toString()); // => Wed Nov 30 16:56:56 MSK 2022

        Time time = new Time(date.getTime());
        System.out.println(time.toString()); // => 16:56:56
    }

}
