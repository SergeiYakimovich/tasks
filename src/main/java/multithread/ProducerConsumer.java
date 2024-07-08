package multithread;

import lombok.RequiredArgsConstructor;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumer {

    public static void main(String[] args) {
        Vector<Integer> sharedQueue = new Vector<>();
        int size = 4;

        Thread prodThread = new Thread(new Producer(sharedQueue, size), "Producer");
        Thread consThread = new Thread(new Consumer(sharedQueue), "Consumer");
        prodThread.start();
        consThread.start();
    }

    @RequiredArgsConstructor
    static class Producer implements Runnable {
        private final Vector sharedQueue;
        private final int SIZE;

        @Override
        public void run() {
            for (int i = 0; i < 7; i++) {
                try {
                    System.out.println("Produced: " + i);
                    produce(i);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void produce(int i) throws InterruptedException {
            //wait if the queue is full
//            while (sharedQueue.size() == SIZE) {
            if (sharedQueue.size() == SIZE) {
                synchronized (sharedQueue) {
                    System.out.println("The queue is full " + Thread.currentThread().getName()
                            + " is waiting , size: " + sharedQueue.size());
                    sharedQueue.wait();
                }
            }

            //producing element and notify consumers
            synchronized (sharedQueue) {
                sharedQueue.add(i);
                sharedQueue.notifyAll();
            }
        }
    }

    @RequiredArgsConstructor
    static class Consumer implements Runnable {
        private final Vector sharedQueue;

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Consumed: " + consume());
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        private int consume() throws InterruptedException {
            //wait if the queue is empty
//            while (sharedQueue.isEmpty()) {
            if (sharedQueue.isEmpty()) {
                synchronized (sharedQueue) {
                    System.out.println("The queue is empty " + Thread.currentThread().getName()
                            + " is waiting , size: " + sharedQueue.size());

                    sharedQueue.wait();
                }
            }

            //Otherwise consume element and notify the waiting producer
            synchronized (sharedQueue) {
                sharedQueue.notifyAll();
                return (Integer) sharedQueue.remove(0);
            }
        }
    }

}
