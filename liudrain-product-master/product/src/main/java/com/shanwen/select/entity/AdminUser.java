package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 客户端的人员信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("AdminUser")
public class AdminUser {
    /**
     * 主键
     */
    @TableId(value="admin_Id", type = IdType.AUTO)
    private Integer adminId;
    /**status
     * 客户端登录人姓名
     */
    @TableField("adminName")
    private String adminName;
    /**
     * 登录密码
     */
    @TableField("password")
    private String password;
    /**
     * 伪删字段,0代表伪删，1代表正常使用
     */
    @TableField("status")
    private Integer status;
    /**
     * 是否超级管理员，0代表普通员工，1代表超级管理员
     */
    @TableField("isSuper")
    private Integer isSuper;

}
