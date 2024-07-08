package multithread;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

public class FootSteps {
    private static final String lock = "lock";

    public static void main(String[] args) {
        new Thread(new Foot(FootName.RIGHT1)).start();
        new Thread(new Foot(FootName.RIGHT2)).start();
        new Thread(new Foot(FootName.RIGHT3)).start();
        new Thread(new Foot(FootName.LEFT1)).start();
        new Thread(new Foot(FootName.LEFT2)).start();
        new Thread(new Foot(FootName.LEFT3)).start();
        new Thread(new Foot(FootName.LEFT4)).start();
        new Thread(new Foot(FootName.RIGHT4)).start();
    }

    @Data
    @RequiredArgsConstructor
    public static class Foot implements Runnable {
        private final FootName name;
        private static FootName currentLeg = FootName.LEFT1;

        @SneakyThrows
        @Override
        public synchronized void run() {
            while (true) {
                synchronized (lock) {
                    if(name.equals(currentLeg)) {
                        System.out.println(name + " foot is moving");
                        Thread.sleep(1000);
                        currentLeg = FootName.getNext(currentLeg);
                    }
                }
            }
        }
    }

    public enum FootName {
        LEFT1, RIGHT1, LEFT2, RIGHT2, LEFT3, RIGHT3, LEFT4, RIGHT4;

        public static FootName getNext(FootName current) {
            return current.ordinal() == values().length - 1 ? values()[0] : values()[current.ordinal() + 1];
        }
    }
}
