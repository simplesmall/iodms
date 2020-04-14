package fun.kwan.iodms.JNA;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author 简小
 * @create 2020-03-22 22:26
 */

public interface Dllinterface extends Library {
    Dllinterface INSTANCE = (Dllinterface) Native.load("D:\\JavaWork\\iodms\\src\\ZLDevManage", Dllinterface.class);

    // 初始化 一旦有设 备作为客户端连接上这个端口，就可以被搜索到，实现外网设备的管理。 如果不需要外网客户端设备的管理，可以设置该参数为 0。
    boolean ZLDM_Init(int port);

    // 退出前调用该函数。该函数用于析构函数库内部变量，如果不调用该函数而 退出，可能出现错误提示对话框。
    void ZLDM_Exit();

    // 获 取 ZLDevManage.dll 函 数 库 的版本号
    String ZLDM_GetVer();

    // 搜索网络中所有联网的卓岚联网设备，并保存在内存中，以后随时可以用 ZLDM_GetDevID()获得设备列表。
    int ZLDM_StartSearchDev();

    // 调用 ZLDM_StartSearchDev()后使用这个函数获取第 i 个需要的的设备的 id。
    // 这种机制使得用户无需为设备列表构建负责的链表结构进行保存。甚至也 无需使用数组保存设备 id 列表。
    String ZLDM_GetDevID(int Devindex);

    // 获取某个设备的参数，参数的返回值是 string 类型的。
    // 2、 PARAM_DEV_LOCAL_IP
    String ZLDM_GetDevParamString(String id, int ParamType);

    //  获取某个设备的参数，参数的返回值是 int 类型的。 ParamType
    // 1、 PARAM_DEV_EXIST_IN_SUBNET  3、PARAM_DEV_LOCAL_PORT   4、PARAM_DEV_OUTER_IP   5、PARAM_ALL_SUBNET_ID
    // 6、 PARAM_SIMULATE_PORT        7、PARAM_P2P_STATUS_CODE  8、PARAM_P2P_STATUS_CHS 9、 PARAM_P2P_STATUS_EN
    int ZLDM_GetDevParamInt(String id, int ParamType);

    // 设置某个设备的参数，参数是 int 类型的。
    int ZLDM_SetDevParamInt(String id, String NewParam, int ParamType);

    // 设置某个设备的参数，参数是 int 类型的。
    int ZLDM_SetDevParamString(String id, String NewParam, int ParamType);

    // 所有参数设置完毕后，必须调用 ZLDM_SetDevParamExcute 执行写入。
    int ZLDM_SetDevParamExcute(String id);
}

