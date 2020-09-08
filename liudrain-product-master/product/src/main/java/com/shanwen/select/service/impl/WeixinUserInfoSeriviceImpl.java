package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.WeixinUserInfo;
import com.shanwen.select.mapper.WeixinUserInfoDao;
import com.shanwen.select.service.WeixinUserInfoSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinUserInfoSeriviceImpl extends ServiceImpl<WeixinUserInfoDao, WeixinUserInfo> implements WeixinUserInfoSerivice {
    @Autowired
    WeixinUserInfoDao weixinUserInfo;

    @Override
    public WeixinUserInfo findByUser(WeixinUserInfo userInfo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (userInfo.getOpenId() != null) {
            queryWrapper.eq("open_Id", userInfo.getOpenId());
        }
        if (userInfo.getUserId() != null) {
            queryWrapper.eq("user_Id", userInfo.getUserId());
        }
        return weixinUserInfo.selectOne(queryWrapper);
    }
}
