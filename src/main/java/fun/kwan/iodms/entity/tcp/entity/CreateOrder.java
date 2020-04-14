package fun.kwan.iodms.entity.tcp.entity;

/**
 * @author 简小
 * @create 2020-04-01 16:15
 * Type 指明操作类型是什么：ENUM ReadAI ReadDI  WD0 RD0
 * Addr 设备的位置，默认是0000 或 0001
 * AfterLength: 标识后面有效位位多少 填充位+标识位 00 00 00 06 或 00 00 00 04
 * OpeCode: 操作指令 0104 0105 0101等
 * RegisterAddr: 寄存器地址 两个字节 0010
 * StatusCode: 操作目标状态码 ff00 0000  0008 0003等
 */
public class CreateOrder {
    private int Type;
    private String Addr;
    private String AfterLength;
    private String OpeCode;
    private String RegisterAddr;

    public CreateOrder() {
    }

    private String StatusCode;

    public CreateOrder(int type, String addr, String afterLength, String opeCode, String registerAddr, String statusCode) {
        this.Type = type;
        this.Addr = addr;
        this.AfterLength = afterLength;
        this.OpeCode = opeCode;
        this.RegisterAddr = registerAddr;
        this.StatusCode = statusCode;
    }

    public int getType() {
        return Type;
    }

    public String getAddr() {
        return Addr;
    }

    public String getAfterLength() {
        return AfterLength;
    }

    public String getOpeCode() {
        return OpeCode;
    }

    public String getRegisterAddr() {
        return RegisterAddr;
    }

    public String getStatusCode() {
        return StatusCode;
    }


    String BlackHolder = "000000";
    String str = null;

    /*// 根据输入的 操作类型 地址 后标识位数 操作代码 寄存器地址 目标状态码 组合成指令
    *  包括输入为空和未读取到数据指令时的操作处理
    *
    * */
    public String GenerateStr() {
        str = null;
        Addr =        this.Addr == ""          || this.Addr == null ? "0001" : this.Addr;
        AfterLength = this.AfterLength == ""   || this.AfterLength == null ? "06" : this.AfterLength;
        OpeCode =     this.OpeCode == ""       || this.OpeCode == null ? "0105" : this.OpeCode;
        RegisterAddr = this.RegisterAddr == "" || this.RegisterAddr == null ? "0010" : this.RegisterAddr;
        StatusCode =  this.StatusCode == ""    || this.StatusCode == null ? "ff00" : this.StatusCode;
        str = Addr + BlackHolder + AfterLength + OpeCode + RegisterAddr + StatusCode;
        return str;
    }

}
