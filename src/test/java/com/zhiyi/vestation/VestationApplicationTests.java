package com.zhiyi.vestation;

import com.zhiyi.vestation.utils.ImgUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest
class VestationApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 图片上传测试
     */
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

    /**
     * 图片删除测试
     */
    @Test
    void testDelImg(){
        System.out.println(ImgUtil.delete("93032346-4e13-40f9-91d3-d1854345bece"));
    }

}
