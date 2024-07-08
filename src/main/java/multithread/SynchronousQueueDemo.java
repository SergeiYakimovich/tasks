package multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    final static SynchronousQueue<String> queue = new SynchronousQueue<>();
//    final static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {

        Thread producer = new Thread(new Producer(), "PRODUCER");
        producer.start(); // starting publisher thread

        Thread consumer = new Thread(new Consumer(),"CONSUMER");
        consumer.start(); // starting consumer thread

    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    String event = queue.take(); // thread will block here
                    System.out.printf("[%s] consumed event : %s %n", Thread.currentThread()
                            .getName(), event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String event = "Event-" + i;
                try {
                    queue.put(event); // thread will block here
                    System.out.printf("[%s] published event : %s %n", Thread.currentThread()
                            .getName(), event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
