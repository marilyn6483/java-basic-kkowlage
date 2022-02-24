package concurrent;

public class Test2 {

    public static void main(String[] args) {
        //join
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
