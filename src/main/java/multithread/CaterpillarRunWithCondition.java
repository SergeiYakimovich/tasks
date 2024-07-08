package multithread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CaterpillarRunWithCondition {
    public static void main(String[] args) throws InterruptedException {
        Caterpillar caterpillar = new Caterpillar(3);
        caterpillar.start();

    }

    static class Caterpillar {
        Lock reentrantLock = new ReentrantLock();
        int size;
        List<Leg> legs;
        int currentLeg;

        List<Condition> conditions;

        Caterpillar(int size) {
            this.size = size;
            legs = new ArrayList<>(size * 2);
            conditions = new ArrayList<>(size * 2);
            for (int i = 1; i <= size * 2; i++) {
                legs.add(new Leg(i));
                conditions.add(reentrantLock.newCondition());
            }
        }

        public void start() {
            for (Leg leg : legs) {
                new Thread(leg).start();
            }
            currentLeg = 1;
            reentrantLock.lock();
            conditions.get(currentLeg - 1).signal();
            reentrantLock.unlock();
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
                    reentrantLock.lock();
                    conditions.get(legNumber - 1).await();
                    System.out.println(legName + " foot is moving");
                    Thread.sleep(1000);
                    currentLeg = getNextLeg(currentLeg);
                    conditions.get(currentLeg - 1).signal();
                    reentrantLock.unlock();
                }
            }
        }

    }
}
