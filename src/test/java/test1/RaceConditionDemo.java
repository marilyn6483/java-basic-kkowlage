package test1;

import java.util.Random;

public class RaceConditionDemo {
    // 内部静态类，自定义的worker线程
    static class WorkerThread extends Thread {

        private final int requestCount;

        public WorkerThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        private void processRequest(String requestID) {
            try {
                Thread.sleep(new Random().nextInt(100) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.printf("%s got RequestID: % n",
//                    Thread.currentThread().getName(), requestID);
            System.out.println(Thread.currentThread().getName() + " got RequestID: " + requestID);
        }

        @Override
        public void run() {
            int i = requestCount;
            String requestID;
            RequestIDGenerator requestIDGen = RequestIDGenerator.getInstance();
            System.out.println(Thread.currentThread().getName() + " got generator -> " + requestIDGen);
            while (i-- > 0) {
                requestID = requestIDGen.nextID();
                processRequest(requestID);
            }
        }

    }

    public static void main(String[] args) throws Exception {

//        int numOfThreads = args.length > 0 ?
//        Short.valueOf(args[0]): Runtime.getRuntime().availableProcessors();
        int numOfThreads = 4;
//        Thread[] workerThreads = new WorkerThread[numOfThreads];
//        for (int i = 0; i < numOfThreads; i++) {
//            workerThreads[i] = new WorkerThread(i, 3);
//        }
//
//        for (Thread workerThread: workerThreads) {
//            workerThread.start();
//        }
        new WorkerThread(1, 3).start();

    }
}
