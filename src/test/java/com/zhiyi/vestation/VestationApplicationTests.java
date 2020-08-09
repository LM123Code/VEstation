package com.zhiyi.vestation;

import com.zhiyi.vestation.utils.ImgUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest
class VestationApplicationTests {

   /* @Test
    void contextLoads() {
    }

    *//**
     * 图片上传测试
     *//*
    @Test
    void testUploadImg(){
        File file = new File("F:\\我的文件\\照片\\二寸红.jpg");
        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            System.out.println(ImgUtil.uploadImg(fileBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    *//**
     * 图片删除测试
     *//*
    @Test
    void testDelImg(){
        System.out.println(ImgUtil.delete("440eed2b-2f6e-49d6-9e84-2aeb315170bb"));
    }*/

    @Test
    public void print(){
        System.out.println("============================================");
        String[] split = "3".split(",");
        
        System.out.println(split.length);
        System.out.println(split[0]);
        System.out.println(split.toString());
    }

}
