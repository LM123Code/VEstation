package com.zhiyi.vestation.controller;


import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    ImageService imageService;

    /**
     * 上传图片
     * @param multipartFiles  图片文件
     * @return 返回status查看状态
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("uploadImage")
    public ResultStatus uploadImage(List<MultipartFile> multipartFiles) throws IOException {
        if(Objects.isNull(multipartFiles)){
            return ResultStatus.builder().code("1").msg("所选图片为空").build();
        }
        List<byte[]> images = multipartFiles.stream().map(multipartFile -> {
            try {
                return multipartFile.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        Map<String, Integer> map = imageService.uploadImage(images);
        List<String> keys = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue() == 200){
                keys.add(entry.getKey());
            }
        }
        return ResultStatus.builder().code("200").msg("上传成功" + keys.size() + "张图片").data(keys).build();
    }

    /**
     * 删除图片
     * @param key 图片名称
     * @return 返回status状态
     */
    @ResponseBody
    @GetMapping("delImage")
    public ResultStatus delImage(String key){
        return imageService.delImage(key);
    }
}

