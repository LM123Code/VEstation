package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public class SystemMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "system_msg_id", type = IdType.AUTO)
    private Integer systemMsgId;

    /**
     * 系统消息接收人
     */
    private String toOpenid;

    /**
     * 系统消息内容
     */
    private String msgContent;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 查看表示，0未查看，1已查看
     */
    private Boolean look;

    /**
     * 是否存在,0不存在，1存在
     */
    @TableLogic
    private Boolean exist;


    public Integer getSystemMsgId() {
        return systemMsgId;
    }

    public void setSystemMsgId(Integer systemMsgId) {
        this.systemMsgId = systemMsgId;
    }

    public String getToOpenid() {
        return toOpenid;
    }

    public void setToOpenid(String toOpenid) {
        this.toOpenid = toOpenid;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getLook() {
        return look;
    }

    public void setLook(Boolean look) {
        this.look = look;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "SystemMsg{" +
        "systemMsgId=" + systemMsgId +
        ", toOpenid=" + toOpenid +
        ", msgContent=" + msgContent +
        ", createDate=" + createDate +
        ", look=" + look +
        ", exist=" + exist +
        "}";
    }
}
