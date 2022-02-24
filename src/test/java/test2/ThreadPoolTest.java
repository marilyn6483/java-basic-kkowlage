package test2;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static Runnable get(final int i) {

        return new Runnable() {
            public void run() {

                System.out.println(i);
//                System.out.println(Thread.currentThread().getName() + " is running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    static class MyThread implements Runnable {

        public void run() {

            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        SynchronousQueue<Runnable> workQueue = new SynchronousQueue();
        LinkedBlockingQueue queue = new LinkedBlockingQueue(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,
                6, 60L, TimeUnit.SECONDS, queue);



        System.out.println(executor);

//        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ; i < 10; i++) {
//            service.execute(get(i));
            executor.execute(get(i));
            System.out.println("queue size -> " + queue.size());
            System.out.println("pool size -> " + executor.getPoolSize());
            System.out.println("core pool size -> " + executor.getCorePoolSize());
            System.out.println("maximum pool size -> " + executor.getMaximumPoolSize());
        }

        executor.shutdown();

        if (executor.isTerminated()) {
            System.out.println("executors terminated!");
        }
    }
}
