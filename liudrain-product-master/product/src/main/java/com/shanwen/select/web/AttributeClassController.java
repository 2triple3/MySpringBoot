package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.*;
import com.shanwen.select.service.FiltrateService;
import com.shanwen.select.service.IAttributeClassService;
import com.shanwen.select.service.IAttributeService;
import com.shanwen.select.service.ICategoryService;
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
 * 属性管理
 */
@Controller
@RequestMapping("/attributeclass")
public class AttributeClassController {
    @Autowired
    IAttributeClassService attributeClassService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IAttributeService attributeService;


    /**
     * 列表
     *
     * @return
     */

    //@ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String productList(AttributeClass attributeClass, Integer categoryId, Model model) {
        Category category = categoryService.getById(categoryId);
        attributeClass.setStatus(1);
        List<AttributeClass> list = attributeClassService.selectAll(categoryId);
        model.addAttribute("list", list);
        model.addAttribute("category", category);
        return "attributeclass/list";
    }


    /**
     * 增加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String AddView(Model model, Category category) {
        category = categoryService.getById(category.getCategoryId());
        model.addAttribute("category", category);
        return "attributeclass/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(AttributeClass attributeClass, Integer categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        attributeClass.setCategory(category);
        attributeClass.setStatus(1);
        attributeClass.setCreateDate(new Date());
        attributeClassService.insertAll(attributeClass);
        //attributeClassService.save(attribute);
        return "redirect:list?categoryId=" + categoryId;
    }

    /**
     * 电气参数明细的修改或增加
     */
    @RequestMapping(value = "/editdetail", method = RequestMethod.GET)
    public String AddDetailView(Model model, Integer attributeClassId, Attribute attribute, Integer categoryId) {
        Category category = categoryService.getById(categoryId);


        AttributeClass attributeClass = attributeClassService.getById(attributeClassId);

        attribute.setAttributeClass(attributeClass);

        List<Attribute> list = attributeService.selectByMyId(attribute);

        model.addAttribute("list", list);
        model.addAttribute("attributeClass", attributeClass);
        model.addAttribute("category", category);
        return "attributeclass/detailedit";
    }

    @RequestMapping(value = "/editdetail", method = RequestMethod.POST)
    public String AddDetail(HttpServletRequest servletRequest, Model model, Integer trLen, AttributeClass attributeClass, Integer categoryId) {

        attributeService.deleteByAttributeClass(attributeClass.getAttributeClassId());
        List<Attribute> list = new ArrayList<>();
        for (int i = 0; i <= trLen + 1; i++) {
            String attributeId = servletRequest.getParameter("attributeId" + i);
            String name = servletRequest.getParameter("aname" + i);
            String select = servletRequest.getParameter("select" + i);
            String sort = servletRequest.getParameter("sort" + i);
            if (name != null) {
                Attribute attribute = new Attribute();
                attribute.setAttributeClass(attributeClass);
                attribute.setAname(name);
                attribute.setStatus(1);
                attribute.setCreateDate(new Date());
                if ("1".equals(select)) {
                    attribute.setFiltrate(1);
                }
                if ("1".equals(sort)) {
                    attribute.setSortOptions(1);
                }
                if (attributeId != null) {
                    attributeService.updateAll(attribute);
                } else {
                    list.add(attribute);
                }

            }
        }
        if (list.size() > 0) {
            attributeService.insertAll(list);
        }
        return "redirect:list?categoryId=" + categoryId;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditView(Model model, Integer attributeClassId, AttributeClass attributeClass, Category category) {
        attributeClass = attributeClassService.selectMyNeed(attributeClassId);
        category.setStatus(1);
        List<Category> categoryList = categoryService.selectAll(category);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("attributeClass", attributeClass);
        return "attributeclass/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String Edit(Model model, AttributeClass attributeClass, Integer categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        attributeClass.setCategory(category);
        attributeClass.setCreateDate(new Date());
        attributeClassService.updateAll(attributeClass);
        return "redirect:list?categoryId=" + categoryId;
    }


    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteView(Model model, Integer attributeClassId, Integer categoryId) {
        attributeClassService.updateByMyId(attributeClassId);
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
