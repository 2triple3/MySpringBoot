package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.AdminUser;
import com.shanwen.select.entity.FreeArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
//@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
    AdminUser login(Map map);

    void updatePwd(AdminUser user);

    void updateByMyId(Integer a);
}
