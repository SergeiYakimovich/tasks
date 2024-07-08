package multithread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CaterpillarRun {
    public static void main(String[] args) throws InterruptedException {
        Caterpillar caterpillar = new Caterpillar(3);
        caterpillar.start();

//        Thread.sleep(2000);
//        caterpillar.pause();
//        System.out.println("Caterpillar is stopped");
//        caterpillar.go();
//        System.out.println("Caterpillar is running");
//        Thread.sleep(1000);
//        caterpillar.pause();
    }

    static class Caterpillar {
        Lock lock1 = new ReentrantLock();
        final String lock = "lock";
        int size;
        List<Leg> legs;
        volatile int currentLeg;
        boolean isRunning;

        Caterpillar(int size) {
            this.size = size;
            legs = new ArrayList<>(size * 2);
            for (int i = 1; i <= size * 2; i++) {
                legs.add(new Leg(i));
            }
        }

        public void start() {
            currentLeg = 1;
            isRunning = true;
            for (Leg leg : legs) {
                new Thread(leg).start();
            }
        }

        public void pause() {
            isRunning = false;
        }

        public void go() {
            isRunning = true;
        }

        int getNextLeg(int current) {
            return current < size * 2 ? current+1 : 1;
        }

        class Leg implements Runnable {
            int legNumber;
            String legName;

            public Leg(int legNumber) {
                this.legNumber = legNumber;
                this.legName = legNumber % 2 != 0 ? "Left-"+ ((legNumber-1)/2 + 1) : "Right-" + ((legNumber-1)/2+1);
            }

            @SneakyThrows
            @Override
            public void run() {
                while (true) {
//                    synchronized (lock) {
//                    lock1.lock();
                        if(legNumber == currentLeg && isRunning) {
                            System.out.println(legName + " foot is moving");
                            Thread.sleep(1000);
                            currentLeg = getNextLeg(currentLeg);
                        }
//                    lock1.unlock();
//                    }
                }
            }
        }

    }
}
