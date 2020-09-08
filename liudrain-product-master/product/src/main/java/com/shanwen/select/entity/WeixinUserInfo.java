package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信用户表
 * </p>
 *
 * @author liudong
 * @since 2019-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WeixinUserInfo")
public class WeixinUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId("user_Id")
    private String userId;

    /**
     * 微信openid
     */
    @TableField("open_Id")
    private String openId;

    /**
     * 第一次访问时间
     */
    @TableField("create_Date")
    private Date createDate;

    /**
     * 注册时间 ,开通会员时间
     */
    @TableField("register_Date")
    private Date registerDate;

    /**
     * 会员到期时间
     */
    @TableField("expire_Date")
    private Date expireDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 会员级别
     */
    private Integer level;

    /**
     * 电话号码
     */
    private String phone;


}
