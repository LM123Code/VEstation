package com.zhiyi.vestation;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Base64Util;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.service.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class VestationApplicationTests {

    @Autowired
    JobService jobService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ForumService forumService;
    @Autowired
    RoomService roomService;
    @Autowired
    VxUserService vxUserService;
    @Autowired
    SystemMsgService systemMsgService;

    //设置APPID/AK/SK
    public static final String APP_ID = "22756032";
    public static final String API_KEY = "imUDY6Lg9TZDI0SyoiOsdRFt";
    public static final String SECRET_KEY = "AmXKEdth3wpUdf5NcCLSGgrHCsdG8BiN";


    @Test
    void testSelect(){
        List<Job> list = jobService.selectJobListAboutKeyWorlds("运营");
        Assert.notEmpty(list);
        List<Goods> goods = goodsService.selectGoodsListAboutKeyWorlds("ffe");
        Assert.notEmpty(goods);
        List<Forum> forums = forumService.selectForumListAboutKeyWorlds("主卧");
        Assert.notEmpty(forums);
        List<Room> rooms = roomService.selectRoomListAboutKeyWorlds("陕西");
        Assert.notEmpty(rooms);
    }

    @Test
    void testFindAllUser(){
        List<VxUser> allUser = vxUserService.getAllUser();
        Assert.notEmpty(allUser);
    }

    @Test
    void SendMsgAndGetSomeOneMsg() throws ParseException {
        boolean b = systemMsgService.addSystemMsg("大家好这是一个测试的信息");
        Assert.isTrue(b);
        b = systemMsgService.addSystemMsg("hi man");
        Assert.isTrue(b);
        //systemMsgService.setSystemMsgLookToTrue("123456","22");
        List<SystemMsg> systemMsgByOpenId = systemMsgService.getSystemMsgByOpenId("123456");
        for (SystemMsg systemMsg: systemMsgByOpenId) {
            System.out.println(systemMsg);
        }
    }

    @Test
    void testSelect1(){
        ResultStatus allGoodsInPageByPriceDecrease = goodsService.getAllGoodsInPageByPriceDecrease(1, "");
        ResultStatus allGoodsInPageByPriceIncrease = goodsService.getAllGoodsInPageByPriceIncrease(1, "");
        ResultStatus allGoodsInPageByTimeIncrease = goodsService.getAllGoodsInPageByTimeIncrease(1, "");

        ResultStatus allGoodsInPageByViewIncrease = goodsService.getAllGoodsInPageByViewIncrease(1, "婚纱");
        List<Goods> goods = goodsService.selectGoodsListAboutKeyWorlds("婚纱");
        System.out.println(1);
    }
    @Test
    void fun12() throws UnsupportedEncodingException {

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

        String file = "C:\\Users\\27548\\Desktop\\2.jpg" ;
        try {
            URL url = new URL("https://qiniu.mutou135468.top/0a4ff718-c017-11ea-a3a5-00163e0a349f.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIO.write(img, "jpg", new File(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        downloadImage();

        // 参数为本地图片二进制数组
        JSONObject jsonObject = client.basicAccurateGeneral("C:\\Users\\27548\\Desktop\\0a4ff718-c017-11ea-a3a5-00163e0a349f.jpg", options);
        JSONArray jsonArray = (JSONArray) jsonObject.get("words_result");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0 ; i < jsonArray.length(); i++){
            String o =jsonArray.get(i).toString();
            JSONObject worldLine = new JSONObject(o);
            String words = worldLine.get("words").toString();
            stringBuffer.append(" " + words);
        }
        int indexOf = stringBuffer.indexOf("殷家乐");
        int indexOf1 = stringBuffer.indexOf("老干妈");
        int indexOf2 = stringBuffer.indexOf("测开");
        if (indexOf==-1 || indexOf1==-1 || indexOf2==-1){
            System.out.println("不合格");
        }else {
            System.out.println("合格");
        }
        System.out.println(jsonObject.toString(2));
    }

    public static void downloadImage(){

    }
    public static byte[] ImageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码

        return Base64Util.decode(data.toByteArray().toString());
    }


}
