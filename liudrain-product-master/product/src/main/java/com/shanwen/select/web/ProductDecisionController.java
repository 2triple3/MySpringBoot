package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.Decision;
import com.shanwen.select.entity.ProductDecision;
import com.shanwen.select.service.IProductDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 产品决策参数
 */
@Controller
@RequestMapping("/productdecision")
public class ProductDecisionController {
    @Autowired
    IProductDecisionService productDecisionService;

    /**
     * 列表
     */
    //@ResponseBody  //测试前台显示json数据
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String productDecisionList(ProductDecision decision, Model model) {

        //采用构造器查询
        QueryWrapper<ProductDecision> queryWrqpper = new QueryWrapper<>();
        decision.setStatus(1);
        queryWrqpper.setEntity(decision);
        List<ProductDecision> list = productDecisionService.list(queryWrqpper);
        model.addAttribute("decisionlist", list);
        return "productdecision/list";
    }


    /**
     * 增加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String AddView() {

        return "productdecision/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(ProductDecision decision) {
        productDecisionService.save(decision);
        return "redirect:list";
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditView() {

        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String Edit() {

        return null;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteView(Integer decisionId) {
        productDecisionService.updateByMyId(decisionId);
    }
}
