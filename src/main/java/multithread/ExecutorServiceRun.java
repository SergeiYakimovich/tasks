package multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorServiceRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        forkJoinPool.invoke(new MyTask());

        List<Future<Integer>> result = new ArrayList<>();
        try (MyExecutorService executorService = new MyExecutorService(10)) {
            for (int i = 0; i < 100000; i++) {
//                result.add(executorService.submit(new MyCallable()));
            }
        }

        result.forEach(x -> {
            try {
                x.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("\nTime: " + (endTime - startTime));
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            int nextInt = new Random().nextInt();
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + nextInt);
            return nextInt;
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello World");
        }
    }

    static class MyExecutorService extends ThreadPoolExecutor implements AutoCloseable {
        public MyExecutorService(int nThreads) {
            super(nThreads, nThreads,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());
        }

        @Override
        public void close() {
            shutdown();
        }
    }

    static class MyTask extends RecursiveTask<Integer> {
        @Override
        protected Integer compute() {
            int nextInt = new Random().nextInt();
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + nextInt);
            return nextInt;
        }
    }

}
