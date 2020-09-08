package com.shanwen.select.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.AdminUser;
import com.shanwen.select.entity.FreeArticle;

import java.util.Map;

public interface AdminUserService extends IService<AdminUser> {
    //登录查询账号
    AdminUser login(Map map);
    //修改密码
    void updatePwd(AdminUser user);
    //伪删除
    void updateByMyId(Integer a);
}
