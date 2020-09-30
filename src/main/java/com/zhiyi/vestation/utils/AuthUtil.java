package com.zhiyi.vestation.utils;

import com.baidu.aip.ocr.AipOcr;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;
import com.zhiyi.vestation.pojo.VxUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author LM_Code
 * @create 2020-07-02-16:04
 *
 * 图片上传的工具类
 */
@Component
public class AuthUtil {


    //设置APPID/AK/SK
    public static final String APP_ID = "22756032";
    public static final String API_KEY = "imUDY6Lg9TZDI0SyoiOsdRFt";
    public static final String SECRET_KEY = "AmXKEdth3wpUdf5NcCLSGgrHCsdG8BiN";

    @Value("${image-url}")
    private String imageUrl;

    public  boolean authIdentify(String realName ,String unitName , String unitMajor , String unitUrl){
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("probability", "false");


        String file = "C:\\Users\\27548\\Desktop\\" + UUID.randomUUID();
        try {
            URL url = new URL(unitUrl);
            BufferedImage img = ImageIO.read(url);
            ImageIO.write(img, "jpg", new File(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 参数为本地图片二进制数组
        JSONObject jsonObject = client.basicAccurateGeneral(file,options);
        JSONArray jsonArray = (JSONArray) jsonObject.get("words_result");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0 ; i < jsonArray.length(); i++){
            String o =jsonArray.get(i).toString();
            JSONObject worldLine = new JSONObject(o);
            String words = worldLine.get("words").toString();
            stringBuffer.append(" " + words);
        }
        int indexOf = stringBuffer.indexOf(realName);
        int indexOf1 = stringBuffer.indexOf(unitName);
        int indexOf2 = stringBuffer.indexOf(unitMajor);
        System.out.println(jsonObject.toString(2));
        if (indexOf==-1 || indexOf1==-1 || indexOf2==-1){
            System.out.println("不合格");
            return false;
        }else {
            System.out.println("合格");
            return true;
        }
    }

}
