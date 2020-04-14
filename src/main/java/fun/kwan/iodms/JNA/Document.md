

开发文档参考：这些定义都是获得、设置参数时的参数类型定义，列出表格如下，对表格说明 如下： 

 int：则使用 ZLDM_SetDevParamInt 和 ZLDM_GetDevParamInt z

string：则使用 ZLDM_SetDevParamString 和 ZLDM_GetDevParamString 

读取：支持 ZLDM_GetDevParamInt 或者 ZLDM_GetDevParamString 

设置：支持 ZLDM_SetDevParamInt 或者 ZLDM_SetDevParamString 

说明：对参数功能的说明 

 参数 2：ZLDM_SetDevParamInt 或者 ZLDM_SetDevParamString 的第二个参数 

 返回值：string 就是需要的字符串，int 的返回值如果为-1 表示错误，其 它值的含义见下表。注意设置值是根据在下拉框中的位置设计的，方便和下 拉框位置对应。比如停止位 1 返回值为 0，停止位为 2 范围值为 1。这是容 易搞混的地方，请引起注意。

<img src="D:%5CJavaWork%5Ciodms%5Csrc%5Cmain%5Cjava%5Cfun%5Ckwan%5Ciodms%5CJNA%5CDocument.assets%5C1586763110123.png" alt="1586763110123" style="zoom:150%;" />

![1586763029195](D:%5CJavaWork%5Ciodms%5Csrc%5Cmain%5Cjava%5Cfun%5Ckwan%5Ciodms%5CJNA%5CDocument.assets%5C1586763029195.png)

<img src="D:%5CJavaWork%5Ciodms%5Csrc%5Cmain%5Cjava%5Cfun%5Ckwan%5Ciodms%5CJNA%5CDocument.assets%5C1586763206370.png" alt="1586763206370" style="zoom:150%;" />

![1586763277041](D:%5CJavaWork%5Ciodms%5Csrc%5Cmain%5Cjava%5Cfun%5Ckwan%5Ciodms%5CJNA%5CDocument.assets%5C1586763277041.png)