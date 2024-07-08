package multithread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class CaterpillarRunWithSemaphore {
    public static void main(String[] args) throws InterruptedException {
        Caterpillar caterpillar = new Caterpillar(3);
        caterpillar.start();

    }

    static class Caterpillar {
        int size;
        List<Leg> legs;
        int currentLeg;

        List<Semaphore> semaphores;

        Caterpillar(int size) {
            this.size = size;
            legs = new ArrayList<>(size * 2);
            semaphores = new ArrayList<>(size * 2);
            for (int i = 1; i <= size * 2; i++) {
                legs.add(new Leg(i));
                semaphores.add(new Semaphore(0));
            }
        }

        public void start() {
            for (Leg leg : legs) {
                new Thread(leg).start();
            }
            currentLeg = 1;
            semaphores.get(currentLeg - 1).release();
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
                    semaphores.get(legNumber - 1).acquire();
                    System.out.println(legName + " foot is moving");
                    Thread.sleep(1000);
                    currentLeg = getNextLeg(currentLeg);
                    semaphores.get(currentLeg - 1).release();
                }
            }
        }

    }

}
