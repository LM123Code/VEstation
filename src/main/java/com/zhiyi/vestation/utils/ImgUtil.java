package com.zhiyi.vestation.utils;

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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import javax.xml.transform.Result;
import java.util.UUID;


/**
 * @author LM_Code
 * @create 2020-07-02-16:04
 *
 * 图片上传的工具类
 */
public class ImgUtil {

    // 设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "L-5Kq9EPN507_TujPf_5v-VOTAT7V7m3p_5VW1df";
    private static final String SECRET_KEY = "-8vNTYcj8BR2wL6lsyr8a5Y81ttfgK3qJJ2CmtkY";
    // 要上传的空间
    private static final String bucketname = "zhichangbao";
    // 密钥配置
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 测试域名，只有30天有效期
    private static final String QINIU_IMAGE_CDNS = "qcu6llba8.bkt.clouddn.com";
    private static final Configuration cfg = new Configuration(Zone.zone2());//设置空间上传域名

    /**
     * 获取上传凭证
     * @return
     */
    private static String getUpToken() {
        return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
    }


    /**
     * base64方式上传
     * @param base64 图片的字节数组
     * @param key 图片名称
     * @return
     * @throws Exception
     */
    public static int put64image(byte[] base64, String key) throws Exception {
        String file64 = Base64.encodeToString(base64, 0);
        Integer len = base64.length;

        //华北空间使用 upload-z1.qiniu.com，华南空间使用 upload-z2.qiniu.com，北美空间使用 upload-na0.qiniu.com
        String url = "http://upload-z2.qiniu.com/putb64/" + len + "/key/" + UrlSafeBase64.encodeToString(key);

        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
//        System.out.println(response.code());
        //返回图片名称
        return response.code();
    }

    /**
     * 上传图片
     * @param base64
     * @return   code=200代表上传成功，key为图片名称
     */
    public static ResultStatus uploadImg(byte[] base64){
//        生成随机图片名称key
        String key = UUID.randomUUID().toString();
        ResultStatus resultStatus = new ResultStatus();
        try {
            int code = put64image(base64, key);
            if (code != 200) {
                return resultStatus.setCode("1").setMsg("上传失败");
            }
            return resultStatus.setCode("200").setMsg("ok").setData(key);
        } catch (Exception e) {
            e.printStackTrace();
            return resultStatus;
        }
    }

    /**
     * 删除图片
     * @param key 图片的文件名
     * @Explain 删除空间中的图片
     */
    public static ResultStatus delete(String key) {
        BucketManager bucketManager = new BucketManager(auth, cfg);
        ResultStatus resultStatus = new ResultStatus();
        try {
            Response delete = bucketManager.delete(bucketname, key);
            if (delete.statusCode != 200)
                return resultStatus.setMsg("删除失败").setCode("1");
            if (key == null || key.equals("")) {
                return resultStatus.setCode("0").setMsg("没有图片名");
            }
            return resultStatus.setMsg("ok").setCode("200");
        } catch (Exception e) {
            e.printStackTrace();
            return resultStatus;
        }
    }
}
