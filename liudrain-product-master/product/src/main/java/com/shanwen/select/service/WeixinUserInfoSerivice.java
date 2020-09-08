package com.shanwen.select.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.WeixinUserInfo;


public interface WeixinUserInfoSerivice extends IService<WeixinUserInfo> {

    WeixinUserInfo findByUser(WeixinUserInfo userInfo);



}
