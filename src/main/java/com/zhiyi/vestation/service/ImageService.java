package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public interface ImageService {

    /**
     * 上传图片
     * @param image  图片二进制数据
     * @return 返回status查看状态
     * @throws IOException
     */
    Map<String, Integer> uploadImage(List<byte[]> images);

    /**
     * 删除图片
     * @param key 图片名称
     * @return 返回status状态
     */
    ResultStatus delImage(String key);
}
