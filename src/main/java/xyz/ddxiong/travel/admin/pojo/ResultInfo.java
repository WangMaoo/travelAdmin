package xyz.ddxiong.travel.admin.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义一个类存放ajax响应结果信息
 */
public class ResultInfo {
    private boolean flag; // 成功或失败
    private Object successData; // 成功的数据
    private String errorMsg; // 失败的提示信息

    /**
     * 将响应结果对象转成json字符串
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String resultJson = om.writeValueAsString(obj);
        System.out.println(resultJson);
        return resultJson;
    }


    @Override
    public String toString() {
        return "ResultInfo{" +
                "flag=" + flag +
                ", successData=" + successData +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getSuccessData() {
        return successData;
    }

    public void setSuccessData(Object successData) {
        this.successData = successData;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
