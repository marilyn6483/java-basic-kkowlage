package chapter2;

import java.io.IOException;
import java.net.ServerSocket;

public class SimpleServer {
    // 模拟服务端拒绝连接场景
    // ServerSocket的第二个参数2，表示连接队列的长度
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8000, 2);
        Thread.sleep(360000);
    }
}
