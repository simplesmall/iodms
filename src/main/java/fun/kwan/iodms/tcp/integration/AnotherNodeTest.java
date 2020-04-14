package tcp.integration;

import tcp.entity.AnotherNode;
import tcp.entity.CreateOrder;

import java.io.IOException;

/**
 * @author 简小
 * @create 2020-04-03 9:55
 */
public class AnotherNodeTest {

    /*
    * 测试了 1 控制开关开闭状态的操作  0105
    *       2 查询DI的操作           0101
    *       3 查询AI的操作           0104
    *
    * Sample: 00 00 00 00 00 06 0105 0010 ff00
    *         00 00 00 00 00 06 0105 0010 ff00     // 原样返回输入指令
    *
    *         00 00 00 00 00 06 0101 0000 0008
    *         00 00 00 00 00 06 010101 ac           // 返回的最后两位表示八位AI的状态：
    *                                               // 解释：ac = 1011 1101 则1~8状态为  开 关 开 开  开 开 关 开
    *
    *         00 00 00 00 00 06 0104 0000 0002      // 根据切出来的最后一位确定查询的AI状态口数 0000 ~ 0008
    *         00 00 00 00 00 06 010410  00ac 03b3   // 下面的0104xx 后面位数由上面末位决定，
    *                                               // 显示数据为转换为【（十进制/1024）*5 / 5】* 设备区间范围
    *
    * */
    public static void main(String[] args) throws IOException {
        CreateOrder createOrder = new CreateOrder(1, "0000", "06", "0105", "0010", "0000");
        AnotherNode anotherNode = new AnotherNode("192.168.1.254", 502, createOrder);
        String retStr = anotherNode.CreateNode();
        System.out.println("retStr is:"+retStr);

        System.out.println("=========");

        CreateOrder ff00 = new CreateOrder(2, "0000", "06", "0101", "0000", "0008");
        AnotherNode node = new AnotherNode("192.168.1.254", 502,ff00);
        String nodeRet = node.CreateNode();
        System.out.println(nodeRet);

        System.out.println("=========");

        CreateOrder theCre = new CreateOrder(3, "0000", "06", "0104", "0000", "0003");


        System.out.println(theCre.getType()+"\t"+theCre.getAfterLength()+"\t"+theCre.getOpeCode()+"\t"+theCre.getStatusCode());


        AnotherNode theNode = new AnotherNode("192.168.1.254", 502,theCre);
        String thrNodeRet = theNode.CreateNode();
        System.out.println(thrNodeRet);
    }
}
