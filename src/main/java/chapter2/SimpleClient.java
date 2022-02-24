package chapter2;

import java.io.IOException;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket s1 = new Socket("localhost", 8000);
        System.out.println("第一次连接成功");
        Socket s2 = new Socket("localhost", 8000);
        System.out.println("第二次连接成功");
        Socket s3 = new Socket("localhost", 8000);
        System.out.println("第三次连接成功");

    }
}
