package com.zhiyi.vestation;

import com.zhiyi.vestation.mapper.GoodsMapper;
import com.zhiyi.vestation.pojo.*;
import com.zhiyi.vestation.service.*;
import com.zhiyi.vestation.utils.ImgUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.velocity.runtime.directive.Foreach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;

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
   /* @Test
    void contextLoads() {
    }

    */
   /**
     * 图片上传测试
     */
/*    @Test
    @Ignore
    void testUploadImg(){
        File file = new File("F:\\我的文件\\照片\\二寸红.jpg");
        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            System.out.println(ImgUtil.uploadImg(fileBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 图片删除测试
     *//*
    @Test
    void testDelImg(){
        System.out.println(ImgUtil.delete("440eed2b-2f6e-49d6-9e84-2aeb315170bb"));
    }*/

    /*@Test
    @Ignore
    public void print(){
        System.out.println("============================================");
        String[] split = "3".split(",");
        
        System.out.println(split.length);
        System.out.println(split[0]);
        System.out.println(split.toString());
    }*/

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

}
