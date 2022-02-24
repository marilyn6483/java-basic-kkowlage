package chapter2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClient {
    String host = "www.javathinker.net";
    int port = 80;
    Socket socket;

    public void createSocket() throws Exception {
        socket = new Socket(host, port);
    }
    public void communicate() throws Exception {
        StringBuffer sb = new StringBuffer("GET /index.jsp HTTP/1.1/\r\n");
        sb.append("Host: " + host + "\r\n");
        sb.append("Accept: */*\r\n");
        sb.append("Accept-Language: zh-CN,zh;q=0.9\r\n");
        sb.append("Accept-Encoding: gzip, deflate\r\n");
        sb.append("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36\r\n");
        sb.append("Connection: keep-alive\r\n\r\n");
//        Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
//Accept-Encoding: gzip, deflate
//Accept-Language: zh-CN,zh;q=0.9
//Connection: keep-alive

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(sb.toString().getBytes());
        outputStream.flush();

        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buff)) != -1) {
            buffer.write(buff, 0, len);
        }
        System.out.println(new String(buffer.toByteArray()));
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.createSocket();
        httpClient.communicate();


    }
}
