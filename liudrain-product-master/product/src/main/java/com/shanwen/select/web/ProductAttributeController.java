package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.Attribute;
import com.shanwen.select.entity.Product;
import com.shanwen.select.service.IAttributeService;
import com.shanwen.select.service.IProductReportService;
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
 * 属性管理
 */
@Controller
@RequestMapping("/attribute")
public class ProductAttributeController {
    @Autowired
    IAttributeService attributeService;

    /**
     * 列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String productList(Attribute attribute, Model model) {
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<>();
        attribute.setStatus(1);
        queryWrapper.setEntity(attribute);
        List<Attribute> list = attributeService.list();
        model.addAttribute("list", list);
        return "productattribute/list";
    }





    /**
     * 增加
     */
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String AddView() {
//
//        return "productattribute/add";
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String Add(Attribute attribute) {
//        attributeService.save(attribute);
//        return "redirect:list";
//    }

    /**
     * 修改
     */
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public String EditView() {
//
//        return "productattribute/edit";
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String Edit() {
//
//        return "redirect:list";
//    }

    /**
     * 删除
     */
//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public String deleteView(Model model, Integer attributeId) {
//        attributeService.updateByMyId(attributeId);
//        return "redirect:list";
//    }

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
