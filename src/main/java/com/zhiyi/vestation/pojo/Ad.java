package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */

@Data
@Document(indexName = "ad")
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @Id
    @Field(type = FieldType.Integer)
    @TableId(value = "ad_id", type = IdType.AUTO)
    private Integer adId;

    /**
     * 广告图片地址
     */
    @Field(type = FieldType.Keyword)
    private String imageUrl;

    /**
     * 广告链接地址
     */
    @Field(type = FieldType.Keyword)
    private String linkUrl;

    /**
     * 是否存在,0不存在，1存在
     */
    @TableLogic
    private Boolean exist;

}
