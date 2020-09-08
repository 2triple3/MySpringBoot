package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.AdminUser;
import com.shanwen.select.service.AdminUserService;

import com.shanwen.select.service.IOrderService;
import com.shanwen.select.service.WeixinUserInfoSerivice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
@SessionAttributes("adminUser")
public class AdminController {
    @Autowired
    AdminUserService adminUserService;

    @Autowired
    IOrderService orderService;

    @Autowired
    WeixinUserInfoSerivice weixinUserInfoSerivice;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView(HttpServletRequest request) {
        return "user/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String adminName, String passWord, HttpServletRequest request, RedirectAttributes attr) {
        Map map = new HashMap();
        AdminUser adminUser = new AdminUser();

        map.put("adminName", adminName);
        map.put("passWord", passWord);

        // map.put("passWord", passWord);
        adminUser = adminUserService.login(map);
        if (adminUser == null || adminUser.getAdminId() == null) {
            attr.addFlashAttribute("error", "账号或密码错误！");
            return "redirect:login";
        }
        request.getSession().setAttribute("adminUser", adminUser);
        //model.addAttribute("title", "sbb");
        //return "redirect:/ocr/list";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        calendar.setTime(date);
        String startDate = sdf.format(calendar.getTime());
        map.put("startDate", startDate);

        calendar.add(Calendar.MONTH, -1);
        String endDate = sdf.format(calendar.getTime());
        map.put("endDate", endDate);

        Map monthResult = orderService.getOrderData(map);
        int count = weixinUserInfoSerivice.count();


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.gt("create_date", endDate);
        int count2 = weixinUserInfoSerivice.count(queryWrapper);

        model.addAttribute("count", count);
        model.addAttribute("count2", count2);
        model.addAttribute("monthResult", monthResult);
        return "user/welcome";
    }

    /**
     * 用户注销
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, SessionStatus sessionStatus) {
        HttpSession session = request.getSession();
        if (session != null) {
            AdminUser user = (AdminUser) session.getAttribute("adminName");//从当前session中获取用户信息
            session.removeAttribute("adminName");
            session.invalidate();//关闭session
            sessionStatus.setComplete();
            //System.out.println(session.getAttribute("user"));
        }
        //request.getSession().removeAttribute("user");
        //request.getSession().
        return "user/index";
    }

    /**
     * 用户修改密码
     */
    @RequestMapping(value = "/editpwd", method = RequestMethod.GET)
    public String editView(ModelMap map, AdminUser user) {

        return "user/editpwd";
    }

    @RequestMapping(value = "/editpwd", method = RequestMethod.POST)
    public String edit(ModelMap map, AdminUser user, String password) {
        String adminName = user.getAdminName();
        String passWord = user.getPassword();
        // user.setPassWord(passWord);
        user.setPassword(password);
        user.setAdminName(adminName);

        adminUserService.updatePwd(user);
        return "redirect:login";
    }

    /**
     * 超级管理员权限控制（增加用户、删除用户、重置密码，修改用户的状态）
     *
     * @param model
     * @param userpmiss
     * @return
     */
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public String personlist(Model model, @Param("adminUser") AdminUser userpmiss) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<AdminUser>();
        //条件：查询所有正常状态为1
        queryWrapper.eq("status", 1);
        List<AdminUser> userlist = adminUserService.list(queryWrapper);
        Integer flag = userpmiss.getIsSuper();
        if (flag == 0) {
            System.out.println("用户没有权限登录该模块");
            return "redirect:login";
        }

        model.addAttribute("userlist", userlist);

        return "user/userlist";
    }
    // TODO: 2019/12/29  以下功能不重要，等待开发玩产品完善 根据客户具体要求再开发

    /**
     * 超级管理员添加账户
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView() {

        return "user/addperson";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String adminName, String password, String status, String isSuper) {
        AdminUser user = new AdminUser();
        user.setAdminName(adminName);
        user.setPassword(password);


        // user.setPassWord(passWord);

        if (status.equals("正常")) {
            user.setStatus(1);
        } else if (status.equals("禁用")) {
            user.setStatus(0);
        }
        if (isSuper.equals("是")) {
            user.setIsSuper(1);
        } else if (isSuper.equals("否")) {
            user.setIsSuper(0);
        }

        adminUserService.save(user);
        return "redirect:permission";
    }

    /**
     * 超级管理员删除账户
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Model model, AdminUser user, Integer a) {
//        QueryWrapper<AdminUser> queryWrapper=new QueryWrapper<AdminUser>();
////        //条件：查询所有正常状态为1
////        queryWrapper.eq("admin_Id", a);
////        AdminUser adminUser1=new AdminUser();
////        adminUser1=(AdminUser)adminUserService.list(queryWrapper).get(0);
////        adminUser1.setStatus(0);
////
////        adminUserService.updateById(adminUser1);
        user.setStatus(0);
        adminUserService.updateByMyId(a);
        return "redirect:permission";
    }
    /**
     * 超级管理员修改普通账户为超级账户
     */

    /**
     * 超级管理员修改超级账户为普通账户
     */
}

