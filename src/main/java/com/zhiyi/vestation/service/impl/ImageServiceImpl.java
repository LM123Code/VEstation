package com.zhiyi.vestation.service.impl;

import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.service.ImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
@Service
public class ImageServiceImpl implements ImageService {

    /**
     * 上传图片
     * @param image  图片文件
     * @return 返回status查看状态
     * @throws IOException
     */
    @Override
    public Map<String, Object> uploadImage(byte[] image) {
//        return ImgUtil.uploadImg(image);
        return new HashMap<>();
    }

    /**
     * 删除图片
     * @param key 图片名称
     * @return 返回status状态
     */
    @Override
    public ResultStatus delImage(String key) {
//        return ImgUtil.delete(key);
        return new ResultStatus();
    }
}
