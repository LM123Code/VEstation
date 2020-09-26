package com.zhiyi.vestation.controller;


import com.github.zouchanglin.storage.service.auth.QiNiuAuthService;
import com.github.zouchanglin.storage.service.upload.QiNiuUploadService;
import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@RestController
@RequestMapping("${api-url}/image")
public class ImageController {


    @Autowired
    private QiNiuAuthService qiNiuAuthService;

    @Autowired
    private QiNiuUploadService qiNiuUploadService;

    /**
     * 上传图片
     * @param file  图片文件
     * @return 返回status查看状态
     * @throws IOException
     */



    @GetMapping(value="/getToken")
    public ResultStatus getToken(MultipartFile file) throws IOException {
        String token = qiNiuAuthService.getToken();
        return ResultStatus.builder().code("200").msg("").data(token).build();
    }

    @PostMapping(value = "/uploadImage")
    public ResultStatus uploadImage(MultipartFile file) throws IOException {
//        if(Objects.isNull(file)){
//            return ResultStatus.builder().code("0").msg("图片文件为空").build();
//        }
//        byte[] image = file.getBytes();
//        Map<String, Object> map = imageService.uploadImage(image);
//        if ((int)map.get("code") != 200) {
//            return ResultStatus.builder().code("0").msg("上传失败").build();
//        }
        return ResultStatus.builder().code("200").msg("ok").data(null).build();
    }

    




}

