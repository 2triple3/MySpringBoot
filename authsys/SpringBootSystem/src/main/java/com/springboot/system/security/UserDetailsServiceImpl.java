package com.springboot.system.security;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.springboot.common.entity.SysUser;
import com.springboot.system.config.WebSecurityConfig;
import com.springboot.system.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 用户登录认证信息查询
 * @author fancm
 * @date Nov 20, 2018
 */

@Service("UserDetailsServiceImpl")
//@Resource
//@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("UserDetailsServiceImpl---loadUserByUsername()");
        SysUser user = sysUserService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = sysUserService.findPermissions(user.getUsername());
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(user.getUsername(), user.getPassword(), user.getSalt(), grantedAuthorities);
    }
}
