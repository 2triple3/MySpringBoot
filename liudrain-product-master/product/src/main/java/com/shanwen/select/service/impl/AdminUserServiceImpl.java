package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.AdminUser;
import com.shanwen.select.entity.FreeArticle;
import com.shanwen.select.entity.Order;
import com.shanwen.select.mapper.AdminUserMapper;
import com.shanwen.select.mapper.FreeArticleMapper;
import com.shanwen.select.mapper.OrderMapper;
import com.shanwen.select.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {
    @Autowired
    AdminUserMapper adminUserMapper;
    @Override
    public AdminUser login(Map map) {
        return adminUserMapper.login(map);
    }

    @Override
    public void updatePwd(AdminUser user) {
         adminUserMapper.updatePwd(user);
    }

    @Override
    public void updateByMyId(Integer a) {
        adminUserMapper.updateByMyId(a);
    }


}
