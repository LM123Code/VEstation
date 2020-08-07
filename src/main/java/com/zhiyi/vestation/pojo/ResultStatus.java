package com.zhiyi.vestation.pojo;



import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultStatus {
    private String code;
    private String msg;
    private Object data;

    public String toString() {
        JSONObject resultStatus = new JSONObject();
        resultStatus.put("code",this.code);
        resultStatus.put("msg",this.msg);
        resultStatus.put("data",this.data);
        return resultStatus.toJSONString();
    }

    public ResultStatus setCode(String code){
        this.code = code;
        return this;
    }

    public ResultStatus setMsg(String msg){
        this.msg = msg;
        return this;
    }
    public ResultStatus setData(Object data){
        this.data = data;
        return this;
    }



}
