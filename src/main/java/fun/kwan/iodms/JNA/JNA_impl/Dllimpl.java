package fun.kwan.iodms.JNA.JNA_impl;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author 简小
 * @create 2020-03-27 11:57
 */
public class Dllimpl {

    public interface Clibrary extends Library {
        Clibrary INSTANCE = (Clibrary) Native.load("D:\\JetbrainProjects\\IDEA\\iodms\\src\\ZLDevManage", Clibrary.class);

        boolean ZLDM_Init(int port);

        String ZLDM_GetVer();

        int ZLDM_StartSearchDev();

        String ZLDM_GetDevID(int id);

        String ZLDM_GetDevParamString(String id, int type);

        int ZLDM_GetDevParamInt(String id, int type);

        int ZLDM_SetDevParamInt(String id, int newParam, int type);

        int ZLDM_SetDevParamExcute(String id);

        int ZLDM_SetDevParamString(String id, String newParam, int type);

    }
}
