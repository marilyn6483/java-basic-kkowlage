package chapter2;

import java.io.IOException;
import java.net.*;

public class ConnectTest {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 80;
        ConnectTest connectTest = new ConnectTest();
        connectTest.connect(host, port);

    }

    public void connect(String host, int port) {
        SocketAddress socketAddress = new InetSocketAddress(host, port);
        Socket socket = null;
        String result;
        try {
            long beginTime = System.currentTimeMillis();
            socket = new Socket();
            socket.bind(new InetSocketAddress(InetAddress.getByName("222.34.5.7"), 5678));
            socket.connect(socketAddress, 3000);
            long endTime = System.currentTimeMillis();
            result = endTime - beginTime + "/ms";
        } catch (BindException e) {
//            e.printStackTrace();
            result = "Bind Exception";
        } catch (ConnectException e) {
            // 没有服务进行监听指定端口
            // 服务端拒绝连接
            result = "Connect Exception";
        } catch (UnknownHostException e) {
            result = "Unknown Host Exception";
        } catch (SocketTimeoutException e) {
            result = "Socket Timeout Exception";
        } catch (IOException e) {
            result = "IOException";
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(socketAddress + ": " + result);
    }
}
