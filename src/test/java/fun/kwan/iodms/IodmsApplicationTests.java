package fun.kwan.iodms;

import fun.kwan.iodms.JNA.Dllinterface;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class IodmsApplicationTests {

    @Test
    void contextLoads() {
        Dllinterface instance = Dllinterface.INSTANCE;
        int i = instance.ZLDM_StartSearchDev();
        System.out.println(i);
    }

    @Test
    public void TestJNA() {
        Dllinterface instance = Dllinterface.INSTANCE;
        boolean B = instance.ZLDM_Init(502);
        System.out.println(B);
        // 获取 ZLDevManage.dll 函 数 库 的版本号
        String ver = instance.ZLDM_GetVer();
        System.out.println(ver);

        // 搜索网络中所有联网的设备
        // 返回搜索到的设备的总数。之后可以用 ZLDM_GetDevID()逐个获取其中的 ID。
        int device = instance.ZLDM_StartSearchDev();
        System.out.println("搜索到的设备的总数: " + device);
        // 调用 ZLDM_StartSearchDev()后使用这个函数获取第 i 个需要的的设备的 id
        System.out.println("一号设备的ID：" + instance.ZLDM_GetDevID(0));
        List<String> devList = new ArrayList<String>();

        for (int i = 0; i < device; i++) {
            devList.add(instance.ZLDM_GetDevID(i));
            System.out.println("Device id is:" + devList.get(i));
        }

        for (int i = 0; i < device; i++) {
            String params = instance.ZLDM_GetDevParamString(devList.get(i), 2);
            int intparams = instance.ZLDM_GetDevParamInt(devList.get(i), 3);
            System.out.println(i + "号设备的参数为： " + params);
            System.out.println(i + "号设备的int参数为： " + intparams);
        }

        int ret = instance.ZLDM_SetDevParamInt(devList.get(0), "505", 3);
        System.out.println("ZLDM_SetDevParamInt result 设置新端口:"+ret);

        int exeResult = instance.ZLDM_SetDevParamExcute(devList.get(0));
        System.out.println("ZLDM_SetDevParamExcute result is 执行修改:"+exeResult);

        System.out.println("New port is: " + instance.ZLDM_GetDevParamInt(devList.get(0), 3));

        int ipexec = instance.ZLDM_SetDevParamString(devList.get(0), "192.168.1.253", 2);
        System.out.println("ZLDM_SetDevParamInt result 设置新IP:" + ipexec);

        int EXEIP = instance.ZLDM_SetDevParamExcute(devList.get(0));
        System.out.println("ZLDM_SetDevParamExcute result is 执行修改:" + EXEIP);

        System.out.println("New IP is: " + instance.ZLDM_GetDevParamString(devList.get(0), 2));

        int workMode = instance.ZLDM_GetDevParamInt(devList.get(0), 264);
        System.out.println("workMode is: "+workMode);
    }


}
