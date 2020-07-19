package com.zhiyi.vestation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-08
 */
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员用户名
     */
    private String managerName;

    /**
     * 管理员主键
     */
    @TableId(value = "manager_id", type = IdType.AUTO)
    private Integer managerId;

    /**
     * 管理员登录密码
     */
    private String managerPassword;

    /**
     * 管理员头像路径
     */
    private String managerImage;


    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerImage() {
        return managerImage;
    }

    public void setManagerImage(String managerImage) {
        this.managerImage = managerImage;
    }

    @Override
    public String toString() {
        return "Manager{" +
        "managerName=" + managerName +
        ", managerId=" + managerId +
        ", managerPassword=" + managerPassword +
        ", managerImage=" + managerImage +
        "}";
    }
}
