package com.zhiyi.vestation.pojo;

import lombok.Data;

@Data
public class LoginPojo {
    String appid;
    String secret;
    String js_code;
    String userAvatarUrl;
    String nickName;
}
