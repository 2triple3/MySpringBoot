package com.springboot.system.config;



import com.springboot.system.security.UserDetailsServiceImpl;
import jdk.nashorn.internal.ir.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;


import com.springboot.system.security.JwtAuthenticationFilter;
import com.springboot.system.security.JwtAuthenticationProvider;

/**
 * Spring Security Config
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        //获取用户账号密码及权限信息
//        return new UserDetailsServiceImpl();
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
         //使用自定义身份验证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
//        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("WebSecurityConfig---configure()");

        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
    		.authorizeRequests()
    		// 跨域预检请求
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 首页
            .antMatchers("/").permitAll()
            //登录页
            .antMatchers("/login").permitAll()
            //检查用户名是否存在
            .antMatchers("/api/checkUsernameExist/**").permitAll()

            //查找菜单树
            .antMatchers("//menu/findNavTree").permitAll()

            // web jars
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
            // 查看SQL监控（druid）
            .antMatchers("/druid/**").permitAll()
            .antMatchers("/swagger-resources").permitAll()  // swagger.antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/v2/api-docs").permitAll()
            // 验证码
            .antMatchers("/captcha.jpg**").permitAll()
           // 服务监控
            .antMatchers("/actuator/**").permitAll()
           // 其他所有请求需要身份认证
            .anyRequest().authenticated()

        ;

      http.authorizeRequests().and().formLogin().permitAll();

        // 退出登录处理器
       http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        // token验证过滤器
       http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

    }


}