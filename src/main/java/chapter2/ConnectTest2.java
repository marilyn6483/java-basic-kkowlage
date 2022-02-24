package chapter2;


import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class ConnectTest2 {
    public static void main(String[] args) throws IOException {
//        String proxyHost = "myproxy.abc.com";
//        int proxyPort = 1080;
//        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost, proxyPort));
//        Socket socket = new Socket(proxy);
//        socket.connect(new InetSocketAddress("www.javathinker.net", 80));
//        System.out.println(socket);
        System.out.println(InetAddress.getLocalHost().getHostName());
        Enumeration<NetworkInterface> enu = NetworkInterface.getNetworkInterfaces();
        while (enu.hasMoreElements()) {
            System.out.println(enu.nextElement());
        }
    }
}
