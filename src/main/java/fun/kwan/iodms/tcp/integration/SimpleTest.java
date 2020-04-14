package tcp.integration;


import tcp.entity.CreateOrder;
import tcp.entity.Node;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 简小
 * @create 2020-04-01 17:29
 */
public class SimpleTest {
    public static String Another() throws IOException {
        Node node;

        CreateOrder order = new CreateOrder(3, "", "", "0104", "0000", "0005");
        String createOrder =order.GenerateStr();


        node = new Node("192.168.1.254",502);
        Socket socket = node.InitSocket("192.168.1.254", 502);   //建立 socket连接

        OutputStream out = node.GetOut(socket);                          //  创建输出流

        out.write(node.HexStringToBytes(createOrder));                   // 向服务器发送指令

        int hanlen=node.HandleLength(createOrder,order.getType());

        // 留着测试发送指令以及读取服务器位数
        System.out.println(createOrder);
        System.out.println(hanlen);

        byte[] bytes = new byte[node.HandleLength(createOrder,order.getType())];                // 处理读多少服务器返回的数据，根据输入处理指令类型确定读取返回数据长度

        socket.getInputStream().read(bytes);                            // 正式的读取服务器返回的数据


        String backString = node.BytesToHexString(bytes);               //  服务端返回的bytes 转换为string

        System.out.println(backString);

        return backString;
    }

    public static void main(String[] args) throws IOException {
        Node node;

        CreateOrder order = new CreateOrder(1, "", "", "0105", "0010", "ff00");
        String createOrder =order.GenerateStr();                                          //  创建输入指令


        node = new Node("192.168.1.254",502);
        Socket socket = node.InitSocket("192.168.1.254", 502);   //建立 socket连接

        OutputStream out = node.GetOut(socket);                          //  创建输出流


        out.write(node.HexStringToBytes(createOrder));                   // 向服务器发送指令

        int hanlen=node.HandleLength(createOrder,order.getType());
        System.out.println("=====> Type is : "+order.getType());

        // 留着测试发送指令以及读取服务器位数
        System.out.println("CreateOrder is ===> "+createOrder);
        System.out.println(hanlen);

        byte[] bytes = new byte[node.HandleLength(createOrder,order.getType())];                // 处理读多少服务器返回的数据，根据输入处理指令类型确定读取返回数据长度

        socket.getInputStream().read(bytes);                            // 正式的读取服务器返回的数据


        String backString = node.BytesToHexString(bytes);               //  服务端返回的bytes 转换为string


        System.out.println("backString ===> "+backString);

        // 后续处理 根据type判断命令含义并将服务器对应回传指令分析处理
        //  数模转换的处理单独作为一个模块
        String backStr = Another();
        System.out.println("Result show is :  "+backStr);

    }
}
