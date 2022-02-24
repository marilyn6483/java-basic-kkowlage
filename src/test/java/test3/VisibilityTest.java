package test3;

import java.util.Random;

public class VisibilityTest {

    static class TimeConsumingTask implements Runnable {
        private boolean toCancel = false;

        public boolean doExecute() {
            boolean isDone = false;
//            System.out.println("do Executing …………");
            try {
                Thread.sleep(new Random().nextInt(100) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isDone = true;
            return isDone;
        }

        public void toCancel() {
            toCancel = true;
            System.out.println(this + "  canceled");
        }

        public void run() {

            while (!toCancel) {

//                if (doExecute()) {
//                    break;
//                }

//                if (toCancel) {
//                    System.out.println("Task was canceled");
//                } else {
//                    System.out.println("Task was not canceled");
//                }
            }

        }
    }
    public static void main(String[] args) {

        TimeConsumingTask task = new TimeConsumingTask();
        Thread thread = new Thread(task);
        thread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.toCancel();


    }
}
