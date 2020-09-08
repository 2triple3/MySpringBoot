package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.Content;
import com.shanwen.select.entity.FreeArticle;
import com.shanwen.select.entity.WeixinUserInfo;
import com.shanwen.select.service.ContentService;
import com.shanwen.select.service.IFreeArticleService;
import com.shanwen.select.service.WeixinUserInfoSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 付费文章的查看控制器
 */

@Controller
@RequestMapping("/chargeArticle")
public class ChargeArticleController {

    @Autowired
    IFreeArticleService freeArticleService;

    @Autowired
    WeixinUserInfoSerivice weixinUserInfoSerivice;


    @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
    public String chargeArticle(Model model, Integer articleId, String userId) {
        //采用构造器查询
        WeixinUserInfo userInfo = weixinUserInfoSerivice.getById(userId);
        if (null != userInfo) {
            FreeArticle freeArticle = freeArticleService.getById(articleId);
            model.addAttribute("freeArticle", freeArticle);
            return "article/detail";
        } else {
            return "article/error";
        }
    }

}
