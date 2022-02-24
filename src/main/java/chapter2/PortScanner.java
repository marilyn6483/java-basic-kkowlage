package chapter2;

import java.io.IOException;
import java.net.Socket;

public class PortScanner {
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) host = args[0];
//        new PortScanner().scan(host);
        for (int i = 1; i < 1024; i++) {
            int port = i;
            Runnable runnable = () -> {
                Socket socket = null;
                try {
                    socket = new Socket("localhost", port);
                    System.out.println(Thread.currentThread().getName() + " ->  There is a server on port " + port);
                } catch (IOException e) {
                e.printStackTrace();
//                    System.out.println("Cannot connect to port " + port);
                } finally {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            };
            new Thread(runnable).start();
        }
    }

    public void scan(String host) {
        Socket socket = null;
        for (int port = 1; port < 1024; port++) {
            try {
                socket = new Socket(host, port);
                System.out.println("There is a server on port " + port);
            } catch (IOException e) {
//                e.printStackTrace();
                System.out.println("Cannot connect to port " + port);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
