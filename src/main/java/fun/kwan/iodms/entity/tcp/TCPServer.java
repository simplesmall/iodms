package fun.kwan.iodms.entity.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 简小
 * @create 2020-03-27 18:00
 */
public class TCPServer {
    public static void main(String[] args) throws  Exception{
        ServerSocket serverSocket = new ServerSocket(82);

        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();

        byte[] bytes = new byte[1024];

        int len;

        len=inputStream.read(bytes);
        InetAddress address = accept.getInetAddress();

        System.out.println("Sender "+address);
        System.out.println(new String(bytes,0,len));

        // 写回客户端
        OutputStream outputStream = accept.getOutputStream();

        String ret = "00 10 00 00 00 06 00 10 01 01 ff 00";
        outputStream.write(ret.getBytes());
        accept.close();
    }
}
