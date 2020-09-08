package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shanwen.select.entity.*;
import com.shanwen.select.service.*;
import com.shanwen.select.utils.StringUtil;
import com.shanwen.select.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品分类
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @Autowired
    IAttributeClassService attributeClassService;
    @Autowired
    IAttributeService attributeService;
    @Autowired
    IDecisionClassService decisionClassService;
    @Autowired
    IDecisionService decisionService;

    @Autowired
    FeatureService featureService;

    @Autowired
    FiltrateService filtrateService;
    @Autowired
    SortService sortService;

    /**
     * /**
     * 列表
     */
    //@ResponseBody  //测试前台显示json数据
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String CategoryList(Category Category, Model model) {

        //采用构造器查询
        QueryWrapper<Category> QueryWrapper = new QueryWrapper<>();
        Category.setStatus(1);
        QueryWrapper.setEntity(Category);
        List<Category> categoryList = categoryService.list(QueryWrapper);
        //  List<Category> totalList = TreeUtils.buidTree(categoryList);
        model.addAttribute("totalList", categoryList);
        return "category/list";
    }

    @RequestMapping(value = "/getCategory")
    @ResponseBody
    public List<Category> getJsonCategoryList(Category Category, Model model) {

        //采用构造器查询
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        List<Category> categoryList = categoryService.list(queryWrapper);
        return categoryList;
    }

    /**
     * 增加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String AddView(Model model, Category category) {
        category.setStatus(1);
        category.setCreateDate(new Date());
        categoryService.save(category);
        return "redirect:list";
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditView(Model model, Category category, Integer categoryId) {


        categoryService.updateName(category);
        return "redirect:list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteView(Model model, Integer categoryId) {


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);
        List<AttributeClass> attributeClasses = attributeClassService.list(queryWrapper);
        for (int i = 0; i < attributeClasses.size(); i++) {
            attributeService.deleteByAttributeClass(attributeClasses.get(i).getAttributeClassId());
            attributeClassService.removeById(attributeClasses.get(i).getAttributeClassId());
        }

        List<DecisionClass> decisionClasses = decisionClassService.list(queryWrapper);
        for (int i = 0; i < decisionClasses.size(); i++) {
            decisionService.deleteByDecisionClass(decisionClasses.get(i).getDecisionClassId());
            decisionClassService.removeById(decisionClasses.get(i).getDecisionClassId());
        }

        categoryService.updateByMyId(categoryId);
        return "redirect:list";

    }

    /**
     * 类别特点维护
     */
    @RequestMapping(value = "/feature", method = RequestMethod.GET)
    public String featureView(Model model, Integer categoryId) {

        Category category = categoryService.getById(categoryId);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);
        List<Feature> list = featureService.list(queryWrapper);
        model.addAttribute("list", list);
        model.addAttribute("category", category);
        return "category/editfeature";
    }

    /**
     * 获取提交的特点数据
     *
     * @param servletRequest
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/feature", method = RequestMethod.POST)
    public String featureViewPOST(HttpServletRequest servletRequest, Integer trLen, Integer categoryId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);
        featureService.remove(queryWrapper);

        List<Feature> featureList = new ArrayList<>();
        for (int i = 0; i <= trLen + 1; i++) {
            String value = servletRequest.getParameter("feature" + i);
            String flag = servletRequest.getParameter("flag" + i);
            if (null != value && null != value.trim()) {
                Feature feature = new Feature();
                feature.setContent(value.trim());
                feature.setCategoryId(categoryId);
                feature.setFlag(Integer.valueOf(flag));
                featureList.add(feature);
            }
        }
        featureService.saveBatch(featureList);
        return "category/list";
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

    @RequestMapping(value = "/filtratelist", method = RequestMethod.GET)
    public String filtrateList(Model model, Integer categoryId) {
        Category category = categoryService.getById(categoryId);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("sort");
        List<Filtrate> filtrateList = filtrateService.list(queryWrapper);
        model.addAttribute("category", category);
        model.addAttribute("filtrateList", filtrateList);
        return "category/filtratelist";
    }


    @RequestMapping(value = "/filtrate", method = RequestMethod.GET)
    public String filtrateViewGET(Model model, Integer categoryId, Integer paramType) {

        Category category = categoryService.getById(categoryId);
        List<Attribute> attributeList = attributeService.getAttributeForSort(categoryId);
        List<DecisionClass> decisionClassList = decisionClassService.selectAll(categoryId);
        model.addAttribute("paramType", paramType);
        model.addAttribute("decisionClassList", decisionClassList);
        model.addAttribute("attributeList", attributeList);
        model.addAttribute("category", category);
        return "category/filtrate";
    }

    @RequestMapping(value = "/filtrate", method = RequestMethod.POST)
    public String filtrateViewPOST(Model model, Filtrate filtrate) {

        filtrate.setDbName(StringUtil.convertHanzi2Pinyin(filtrate.getFiltrateName(), false));
        filtrateService.save(filtrate);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("filtrate_name", filtrate.getFiltrateName());
        filtrate = filtrateService.getOne(queryWrapper);
        Sort sort = new Sort();
        sort.setFiltrateId(filtrate.getFiltrateId());
        sort.setCategoryId(filtrate.getCategoryId());
        sort.setWay("desc");
        sort.setShowName(filtrate.getFiltrateName() + "从高到低");
        sortService.save(sort);

        Sort sort1 = new Sort();
        sort1.setFiltrateId(filtrate.getFiltrateId());
        sort1.setCategoryId(filtrate.getCategoryId());
        sort1.setWay("asc");
        sort1.setShowName(filtrate.getFiltrateName() + "从低到高");
        sortService.save(sort1);
        return "redirect:filtratelist?categoryId=" + filtrate.getCategoryId();
    }

    @RequestMapping(value = "/filtrateedit", method = RequestMethod.GET)
    public String filtrateeditGET(Model model, Filtrate filtrate) {


        filtrate = filtrateService.getById(filtrate.getFiltrateId());
        Category category = categoryService.getById(filtrate.getCategoryId());
        List<Attribute> attributeList = attributeService.getAttributeForSort(filtrate.getCategoryId());
        List<DecisionClass> decisionClassList = decisionClassService.selectAll(filtrate.getCategoryId());
        model.addAttribute("decisionClassList", decisionClassList);
        model.addAttribute("attributeList", attributeList);
        model.addAttribute("category", category);
        model.addAttribute("filtrate", filtrate);
        return "category/filtrateedit";
    }

    @RequestMapping(value = "/filtrateedit", method = RequestMethod.POST)
    public String filtrateeditPOST(Model model, Filtrate filtrate) {
        filtrate.setDbName(StringUtil.convertHanzi2Pinyin(filtrate.getFiltrateName(), false));
        filtrateService.updateById(filtrate);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("filtrate_name", filtrate.getFiltrateName());
        filtrate = filtrateService.getOne(queryWrapper);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("filtrate_id", filtrate.getFiltrateId());
        sortService.remove(queryWrapper1);

        Sort sort = new Sort();
        sort.setFiltrateId(filtrate.getFiltrateId());
        sort.setCategoryId(filtrate.getCategoryId());
        sort.setWay("desc");
        sort.setShowName(filtrate.getFiltrateName() + "从高到低");
        sortService.save(sort);

        Sort sort1 = new Sort();
        sort1.setFiltrateId(filtrate.getFiltrateId());
        sort1.setCategoryId(filtrate.getCategoryId());
        sort1.setWay("asc");
        sort1.setShowName(filtrate.getFiltrateName() + "从低到高");
        sortService.save(sort1);


        return "redirect:filtratelist?categoryId=" + filtrate.getCategoryId();
    }

    @RequestMapping(value = "/filtratedel", method = RequestMethod.GET)
    public String filtratedel(Model model, Filtrate filtrate) {
        filtrate = filtrateService.getById(filtrate.getFiltrateId());
        filtrateService.removeById(filtrate.getFiltrateId());

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("filtrate_id", filtrate.getFiltrateId());
        sortService.remove(queryWrapper1);

        return "redirect:filtratelist?categoryId=" + filtrate.getCategoryId();
    }


}
