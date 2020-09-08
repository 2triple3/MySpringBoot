package com.shanwen.select.web;

import com.shanwen.select.entity.Attribute;
import com.shanwen.select.entity.AttributeClass;
import com.shanwen.select.entity.Category;
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
import java.util.Date;
import java.util.List;

/**
 * 电气参数明细
 */
@Controller
@RequestMapping("/attribute")
public class AttributeController {
    @Autowired
    IAttributeService attributeService;
    @Autowired
    IAttributeClassService attributeClassService;
    @Autowired
    ICategoryService categoryService;

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Integer attributeId) {
        attributeService.updateByMyId(attributeId);
        return "redirect:/attributeclass/list";
    }

    /**
     * 增加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView(Model model, Attribute attribute, Integer categoryId) {
        AttributeClass attributeClass = new AttributeClass();
        List<AttributeClass> attributeClasslist = attributeClassService.selectAll(categoryId);
        model.addAttribute("attributeClasslist", attributeClasslist);
//        Category category=new Category();
//        category.setStatus(1);
//        List<Category> categoryList =categoryService.selectAll(category);
//        model.addAttribute("categoryList",categoryList);
        return "attributeclass/detailadd";
    }

    /*@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, Attribute attribute, Integer attributeClassId) {
        AttributeClass attributeClass = new AttributeClass();
        attributeClass.setAttributeClassId(attributeClassId);
        attribute.setAttributeClass(attributeClass);
        attributeService.insertAll(attribute);
        return "redirect:/attributeclass/list";
    }
*/
    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editView(Model model, Attribute attribute, Integer categoryId, Integer attributeId) {
        AttributeClass attributeClass = new AttributeClass();
        List<AttributeClass> attributeClasslist = attributeClassService.selectAll(categoryId);
        model.addAttribute("attributeClasslist", attributeClasslist);
        attribute = attributeService.selectMyNeed(attributeId);
        model.addAttribute("attribute", attribute);
        return "attributeclass/detailedit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model, Attribute attribute, Integer attributeClassId, AttributeClass attributeClass) {
        attributeClass.setAttributeClassId(attributeClassId);
        attribute.setAttributeClass(attributeClass);
        attributeService.updateAll(attribute);
        return "redirect:/attributeclass/list";
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
