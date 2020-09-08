package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.*;
import com.shanwen.select.service.DecisionClassCateService;
import com.shanwen.select.service.ICategoryService;
import com.shanwen.select.service.IDecisionClassService;
import com.shanwen.select.service.IDecisionService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 决策参数基本信息维护
 */
@Controller
@RequestMapping("/decisionclass")
public class DecisionClassController {
    @Autowired
    IDecisionClassService decisionClassService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IDecisionService decisionService;
    @Autowired
    DecisionClassCateService decisionClassCateService;


    /**
     * 列表
     *
     * @param model
     * @param
     * @return
     */
    @RequestMapping("/list")

    public String decisionList(Model model, DecisionClass decisionClass, Integer categoryId) {
        List<DecisionClass> decisionClassList = decisionClassService.selectAll(categoryId);
        model.addAttribute("decisionlist", decisionClassList);
        Category category = categoryService.getById(categoryId);
        model.addAttribute("category", category);
        return "decisionclass/list";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String AddView(Model model, Category category) {
        category = categoryService.getById(category.getCategoryId());
        model.addAttribute("category", category);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", category.getCategoryId());
        List<DecisionClassCate> decisionClassCates = decisionClassCateService.list(queryWrapper);
        model.addAttribute("category", category);
        model.addAttribute("decisionClassCates", decisionClassCates);
        return "decisionclass/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(Model model, DecisionClass decisionClass, Integer categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        decisionClass.setCategory(category);
        decisionClass.setCreateDate(new Date());
        decisionClass.setStatus(1);
        decisionClassService.insertAll(decisionClass);
        return "redirect:list?categoryId=" + categoryId;
    }


    /**
     * 一级指标维护
     */
    @RequestMapping(value = "/cate", method = RequestMethod.GET)
    public String cateView(Model model, Category category) {
        category = categoryService.getById(category.getCategoryId());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", category.getCategoryId());
        List<DecisionClassCate> decisionClassCates = decisionClassCateService.list(queryWrapper);
        model.addAttribute("category", category);
        model.addAttribute("decisionClassCates", decisionClassCates);
        return "decisionclass/decisionclasscate";
    }

    @RequestMapping(value = "/cateadd", method = RequestMethod.GET)
    public String cateadd(Model model, DecisionClassCate decisionClassCate) {
        decisionClassCateService.save(decisionClassCate);
        return "redirect:cate?categoryId=" + decisionClassCate.getCategoryId();
    }

    @RequestMapping(value = "/catedelete", method = RequestMethod.GET)
    public String catedelete(Model model, DecisionClassCate decisionClassCate) {
        decisionClassCateService.removeById(decisionClassCate.getDecisionClassCateId());
        return "redirect:cate?categoryId=" + decisionClassCate.getCategoryId();
    }

    @RequestMapping(value = "/cateedit", method = RequestMethod.GET)
    public String cateedit(Model model, DecisionClassCate decisionClassCate) {

        decisionClassCateService.updateById(decisionClassCate);
        return "redirect:cate?categoryId=" + decisionClassCate.getCategoryId();
    }


   /* @RequestMapping(value = "/cate", method = RequestMethod.POST)
    public String cateAdd(HttpServletRequest servletRequest, Integer trLen, DecisionClass decisionClass, Integer categoryId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);
        decisionClassCateService.remove(queryWrapper);

        List<DecisionClassCate> list = new ArrayList<>();
        for (int i = 0; i <= trLen + 1; i++) {
            String value = servletRequest.getParameter("dccname" + i);
            if (null != value && null != value.trim()) {
                DecisionClassCate decisionClassCate = new DecisionClassCate();
                decisionClassCate.setCategoryId(categoryId);
                decisionClassCate.setDccName(value);
                list.add(decisionClassCate);
            }
        }
        decisionClassCateService.saveBatch(list);
        return "redirect:list?categoryId=" + categoryId;
    }*/


    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditView(Model model, DecisionClass decisionClass, Category category, Integer decisionClassId) {

        category = categoryService.getById(category.getCategoryId());
        model.addAttribute("category", category);
        decisionClass = decisionClassService.selectMyNeed(decisionClassId);
        model.addAttribute("decisionClass", decisionClass);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", category.getCategoryId());
        List<DecisionClassCate> decisionClassCates = decisionClassCateService.list(queryWrapper);
        model.addAttribute("decisionClassCates", decisionClassCates);

        return "decisionclass/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String Edit(Model model, DecisionClass decisionClass, Integer categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        decisionClass.setCategory(category);
        decisionClass.setCreateDate(new Date());
        decisionClass.setStatus(1);
        decisionClassService.updateAll(decisionClass);
        return "redirect:list?categoryId=" + categoryId;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Model model, Integer categoryId, Integer decisionClassId) {
        //伪删除
        decisionClassService.updateByMyId(decisionClassId);
        return "redirect:list?categoryId=" + categoryId;
    }


    /**
     * 明细页面列表detaillist
     */
    @RequestMapping(value = "/detaillist", method = RequestMethod.GET)
    public String detaiListGet(Integer decisionClassId, Model model, Decision decision, Integer categoryId) {

        DecisionClass decisionClass = decisionClassService.selectMyNeed(decisionClassId);
        decision.setDecisionClass(decisionClass);
        List<Decision> decisionList = decisionService.selectAll(decision);
        model.addAttribute("list", decisionList);
        model.addAttribute("decisionClass", decisionClass);
        Category category = categoryService.getById(categoryId);
        model.addAttribute("category", category);
        return "decisionclass/detailedit";
    }


    /**
     * 明细页面列表detaillist
     */
    @RequestMapping(value = "/detaillist", method = RequestMethod.POST)
    public String detaiListPost(HttpServletRequest servletRequest, Integer decisionClassId, Model model, Integer trLen, DecisionClass decisionClass, Integer categoryId) {

        decisionService.deleteByDecisionClass(decisionClassId);

        List<Decision> list = new ArrayList<>();
        for (int i = 0; i <= trLen; i++) {

            String decisionId = servletRequest.getParameter("decisionId" + (i));

            String name = servletRequest.getParameter("dname" + (i));
            String filtrate = servletRequest.getParameter("filtrate" + (i));
            if (name != null) {
                Decision decision = new Decision();

                decision.setDecisionClass(decisionClass);
                if (filtrate != null) {
                    decision.setFiltrate(1);
                }
                decision.setDname(name);
                decision.setStatus(1);
                decision.setDsort(i + 1);
                decision.setCreateDate(new Date());
                if (decisionId != null) {
                    decision.setDecisionId(Integer.valueOf(decisionId));
                    decisionService.updateAll(decision);
                } else {
                    list.add(decision);
                }
            }
        }
        if (list.size() > 0) {
            decisionService.saveOrUpdateBatch(list);
        }

        return "redirect:list?categoryId=" + categoryId;

    }


    /**
     * 处理时间
     *
     * @param request
     * @param binder
     */
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
