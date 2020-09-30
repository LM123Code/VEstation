package com.zhiyi.vestation.utils;

/**
 * @author DaHang
 * @date 2020/9/28 16:07
 */
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.PostConstruct;
import java.util.UUID;

@Slf4j
@Component
public class SenInfoCheckUtil {

    private static String access_token;

//    @Scheduled(cron = "* * /2 * * ? *")
    @PostConstruct
    public void getAccessToken(){
        log.info("拿一次token {}",this.access_token);
        //GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        String params = "grant_type=client_credential&appid=wx16428bb434005c77&secret=9b11d1ddc1ae84b7bc3edfdfdb9a714a";
        String res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token",params);
        //解析相应内容（转换成json对象）
        net.sf.json.JSONObject json = JSONObject.fromObject(res);
        if (json.get("access_token") != null){
            //这里证明拿到了
            this.access_token = json.get("access_token").toString();
            return;
        }
        //这是没拿到
        if (json.get("errcode").toString().equals("0")){
            //获取会话密钥（session_key）
            this.access_token = json.get("access_token").toString();
            log.info("=====>>>>>使用cron获取了accss_token  {}",this.access_token);
            this.access_token = "-1";
        }else {
            System.out.println("出错了" + json.get("errcode").toString());
            System.out.println("出错了" + json.get("errmsg").toString());
            this.access_token = "-1";
        }
    }


//    @Scheduled(cron="0/5 * *  * * ? ")
    @Scheduled(cron = "0 0 */2 * * ?")
    public void start() {
        getAccessToken();
    }

    public boolean checkMsg(String msg){
        //https://api.weixin.qq.com/wxa/servicemarket?access_token=ACCESS_TOKEN
        String accessToken = this.access_token;
        if (accessToken.equals("-1")){
            return false;
        }
        String url = "https://api.weixin.qq.com/wxa/servicemarket?access_token=" + accessToken;
        //        "Action": "TextApproval",
        //  "Text": "hello world!"
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service","wxee446d7507c68b11");
        jsonObject.put("api","msgSecCheck");
        jsonObject.put("client_msg_id", UUID.randomUUID().toString());
        JSONObject data =  new JSONObject();
        data.put("Action","TextApproval");
        data.put("Text",msg);
        jsonObject.put("data",data);
        String res = HttpRequest.post(jsonObject,url);
        //解析相应内容（转换成json对象）
        //这里是拿数据
        JSONObject json = JSONObject.fromObject(res);
        String jsondata = json.get("data").toString();
        JSONObject dataObj = JSONObject.fromObject(jsondata);
        //数据中的返回值
        String response = dataObj.get("Response").toString();
        JSONObject responseObj = JSONObject.fromObject(response);
        //数据里面的token
        String evilTokens = responseObj.get("EvilTokens").toString();
        if (evilTokens.equals("[]")){
            return true;
        }else {
            return false;
        }
//        JSONObject evilTokensObj = JSONObject.fromObject(evilTokens);
//        //安不安全
//        String evilFlag = evilTokensObj.get("EvilFlag").toString();
//        if (evilFlag.equals("0")){
//            System.out.println("莫得问题");
//            return true;
//        }else {
//            System.out.println("貌似有问题");
//            return false;
//        }
    }

    public boolean checkImg(String imgUrl){
        //https://api.weixin.qq.com/wxa/servicemarket?access_token=ACCESS_TOKEN
        String accessToken = this.access_token;
        if (accessToken.equals("-1")){
            return false;
        }
        String url = "https://api.weixin.qq.com/wxa/servicemarket?access_token=" + accessToken;
        //这个是调用的外部信息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service","wxee446d7507c68b11");
        jsonObject.put("api","msgSecCheck");
        jsonObject.put("client_msg_id", UUID.randomUUID().toString());
        //调用的所必须传输的shuju
        JSONObject data =  new JSONObject();
        data.put("Action","ImageModeration");
        data.put("Scenes","[\"PORN\",\"POLITICS\",\"TERRORISM\"]");
        data.put("ImageUrl",imgUrl);
        data.put("Config","");
        data.put("Extra","");
        data.put("ImageBase64","");
        jsonObject.put("data",data);
        String res = HttpRequest.post(jsonObject,url);
        //这里是拿数据
        JSONObject json = JSONObject.fromObject(res);
        String jsondata = json.get("data").toString();
        JSONObject dataObj = JSONObject.fromObject(jsondata);
        //数据中的返回值
        String response = dataObj.get("Response").toString();
        JSONObject responseObj = JSONObject.fromObject(response);
//        String suggestion = responseObj.get("Suggestion").toString();
//        String pornResult = responseObj.get("PornResult").toString();
//        String politicsResult = responseObj.get("PoliticsResult").toString();
//        String terrorismResult = responseObj.get("TerrorismResult").toString();
        String ispass = responseObj.get("Suggestion").toString();
        if (ispass.equals("PASS")){
            return true;
        }else {
            return false;
        }
        //解析相应内容（转换成json对象）
        //这里是拿数据
//        return true;
    }


}
