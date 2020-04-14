package tcp;


import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 简小
 * @create 2020-03-27 18:04
 */
public class TCPClient {
    /*把16进制字符串转换成字节数组*/
    public static byte[] hexStringToByte(String hex) {
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

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /* 把字节数组转换成16进制字符串*/
    public static final String bytesToHexString(byte[] bArray) {
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

    // 16进制表示的字符串转换为字节数组
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();

        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节，把这样表示的16进制字符创，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return b;
    }

    public static String enRich(String oldStr, String newStr) {
        if (newStr.length() < oldStr.length()) {

        }
        return oldStr;
    }


    /**
     * number   要转换的数
     * from     原数的进制
     * to       要转换成的进制
     */
    private static String change(String number, int from, int to) {
        return new BigInteger(number, from).toString(to);
    }


    public static void main(String[] args) throws Exception {


        Socket s = new Socket(InetAddress.getByName("192.168.1.254"), 502);
        s.setKeepAlive(true);
        OutputStream outputStream = s.getOutputStream();

        // 下发的指令
//        String str = "00100000000601050012ff00";
        //读取DI状态
//        String str = ("00 00 00 00 00 06 01 01 00 00 00 08 ").replace(" ","");
        //  读取AI  位数+1
        String str = (" 00 00 00 00 00 06 01 04 00 00 00 08").replace(" ","");
        // 尝试读取激光测距
//        String str = (" 01 03 00 00 00 02 c4 0b ").replace(" ","");

        //  将指令转换为bytes发送
        byte[] bytes = hexStringToByte(str);
        System.out.println("hexStringToByte   " + bytes);
        System.out.println("bytesToHexString   " + bytesToHexString(bytes));
        //  往设备上输入指令
        outputStream.write(bytes);

        //  将输入的16进制指令集转换二进制
        String Hex2Bin = change(str, 16, 2);
        System.out.println("Hex to Binary :  " + Hex2Bin);
        //  再将二进制转换为十六进制看与自己发送的指令是否一致
        String Bin2Hex = change(Hex2Bin, 2, 16);
        System.out.println("Binary to Hex : " + Bin2Hex);

        //  读取服务端发送内容
        InputStream inputStream = s.getInputStream();
//        byte[] bytes1 = new byte[str.length()+1];
        byte[] bytes1 = new byte[25];

        int len;
        len=inputStream.read(bytes1);
        String ret = new String(bytes1, 0, len);


        String b2str = bytesToHexString(bytes1);
        System.out.println("Return String b2str: "+b2str);


        // 连续反转控制操作测试

        /*int count = 0;
        String str2=null;
        while (count < 100) {
            // 反转测试
            str = count % 2 == 0 ? "00100000000601050011ff00" : "001000000006010500110000";
            byte[] out = hexStringToByteArray(str);
            outputStream.write(out);
            System.out.println("No " + count + " times output...");
            Thread.sleep(50);

            str2 = count % 2 == 0 ? "001000000006010500120000" : "00100000000601050012ff0000";
            byte[] out2 = hexStringToByteArray(str2);
            outputStream.write(out2);
            System.out.println("No  222  " + count + " times output...");
            //  操作间隔两秒
            Thread.sleep(50);
            count++;
        }*/

        s.close();
    }
}
