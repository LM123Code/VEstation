package com.zhiyi.vestation.service;

import com.zhiyi.vestation.pojo.ResultStatus;
import com.zhiyi.vestation.pojo.Status;

import java.io.IOException;

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
    ResultStatus uploadImage(byte[] image);

    /**
     * 删除图片
     * @param key 图片名称
     * @return 返回status状态
     */
    ResultStatus delImage(String key);
}
