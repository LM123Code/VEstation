package com.zhiyi.vestation.pojo;



import com.alibaba.fastjson.JSONObject;
import lombok.Data;


@Data
public class ResultStatus<T> {
    private String code;
    private String msg;
    private T data;
    JSONObject resultStatus = new JSONObject();
    public ResultStatus(String code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }
    public ResultStatus(String code,String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }
    public String toString() {
        resultStatus.put("code",this.code);
        resultStatus.put("msg",this.msg);
        resultStatus.put("data",this.data);
        return this.resultStatus.toJSONString();
    }


}
