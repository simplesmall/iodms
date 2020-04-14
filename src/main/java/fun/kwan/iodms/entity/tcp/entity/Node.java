package fun.kwan.iodms.entity.tcp.entity;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author 简小
 * @create 2020-04-01 16:14
 * IP  socket IP
 * Port  socket port
 * Order 操作指令（命令）
 */
public class Node {
    private String IP;
    private int Port;
    private CreateOrder Order;


    public Node(String IP, int port) {
        this.IP = IP;
        this.Port = port;
    }

    Socket s = null;
    String str = null;


    // 初始化socket 建立socket连接
    public  Socket InitSocket(String IP, int Port) throws IOException {
        s = new Socket(InetAddress.getByName(IP), Port);
        s.setKeepAlive(true);
        return s;
    }

    //  获取socket连接的输出流（留备输出指令到服务器）
    public OutputStream GetOut(Socket s) throws IOException {
        return s.getOutputStream();
    }

    /*  解决返回数据字节长度问题
    * Order 表示命令行字符串
    * OrderType表示操作类型
    *  1： D0 控制
    *  2： DI 开关状态查询
    *  3： AI 查询
    * */
    public int HandleLength(String str,int OrderType) {
        if (OrderType == 1) {
            return str.length()/2;
        }else if (OrderType == 2){
            return str.length()/2-2;
        }else if (OrderType == 3){
            return  (int)(str.length()/2*0.75)+ CalculateRetAIBytes(str);
        }
        return -1;
    }

    // 根据输入指令类型转换查询AI状态的字节数
    public int CalculateRetAIBytes(String str){
        return Integer.parseInt(str.substring(str.length() - 2, str.length()))*2;
    }

    //  把16进制字符串转换成字节数组
    public  byte[] HexStringToBytes(String hex) {
        hex=hex.replace(" ","");
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private  byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    //  把字节数组转换成16进制字符串
    public  String BytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        CreateOrder createOrder = new CreateOrder(1, "", "", "", "", "");
        String retStr = createOrder.GenerateStr();
        System.out.println(retStr);

        CreateOrder blank = new CreateOrder();
        String blankOrder = blank.GenerateStr();
        System.out.println(blankOrder);

//        byte[] bytes = hexStringToByte(retStr);


//        System.out.println(bytes);
//        String roll = bytesToHexString(bytes);
//        System.out.println(roll);


    }


}
