package com.zhiyi.vestation.pojo;

import lombok.Data;

/**
 * 状态码和信息的类
 * @author LM_Code
 * @create 2020-07-03-10:29
 */
@Data
public class Status {
    public int code; //状态码
    public String data; //信息说明

    public Status(int code, String data) {
        this.code = code;
        this.data = data;
    }
}
