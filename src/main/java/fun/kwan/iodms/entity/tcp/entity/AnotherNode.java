package fun.kwan.iodms.entity.tcp.entity;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 简小
 * @create 2020-04-03 9:33
 */
public class AnotherNode {
    private String IP;
    private int Port;

    private CreateOrder order;

    public AnotherNode(String IP, int port, CreateOrder order) {
        this.IP = IP;
        Port = port;
        this.order = order;
    }

    public String CreateNode() throws IOException {

        CreateOrder createOrder = new CreateOrder(this.order.getType(), this.order.getAddr(),
                this.order.getAfterLength(), this.order.getOpeCode(), this.order.getRegisterAddr(), this.order.getStatusCode());
        String order = createOrder.GenerateStr();

        Node node = new Node(this.IP, this.Port);

        Socket socket = node.InitSocket(this.IP, this.Port);   //建立 socket连接

        OutputStream out = node.GetOut(socket);                          //  创建输出流
                                              //  创建输入指令

        out.write(node.HexStringToBytes(order));                   // 向服务器发送指令

        int hanlen = node.HandleLength(order, createOrder.getType());

        // 留着测试发送指令以及读取服务器位数
        System.out.println("Trans HexString : "+order);
        System.out.println("Trans hexString length: "+hanlen);
        System.out.println("GetType is:"+createOrder.getType());

        byte[] bytes = new byte[node.HandleLength(order, createOrder.getType())];                // 处理读多少服务器返回的数据，根据输入处理指令类型确定读取返回数据长度

        socket.getInputStream().read(bytes);                            // 正式的读取服务器返回的数据

        socket.close();
        String backString = node.BytesToHexString(bytes);               //  服务端返回的bytes 转换为string

        System.out.println("backString is: "+backString);

        return backString;
    }

}
