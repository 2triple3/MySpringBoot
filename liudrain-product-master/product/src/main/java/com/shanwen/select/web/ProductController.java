package com.shanwen.select.web;

import com.alibaba.druid.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.core.metadata.IPage;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanwen.select.entity.*;
import com.shanwen.select.service.*;


import com.shanwen.select.utils.Page1;
import com.shanwen.select.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 产品
 */

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IImageService imageService;
    @Autowired
    IAttributeService attributeService;
    @Autowired
    IProductAttributeService productAttributeService;
    @Autowired
    IProductDecisionClassService productDecisionClassService;
    @Autowired
    FeatureService featureService;
    @Autowired
    ProductFeatureService productFeatureService;

    /**
     * list列表
     */
    //@ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String productList(Model model, Product product, Integer pageNum, Integer pageSize) {
        /***修改后的有分页*/
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();


        Map map = new HashMap();
        if (product.getBrand() != null) {
            map.put("brand", product.getBrand());
        }
        if (product.getBrand() != null) {
            map.put("model", product.getModel());
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum == null ? 1 : pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(productService.selectProductByMap(map));

        model.addAttribute("productList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("brand", product.getBrand());
        model.addAttribute("model", product.getModel());
        // return productList;
        return "product/list";
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String AddView(Model model) {
        List<Category> categoryList = categoryService.selectForProductAdd();
        model.addAttribute("categoryList", categoryList);

        return "product/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(Model model, Product product, Integer categoryId, Category category) {
        category.setCategoryId(categoryId);
        product.setCategory(category);
        product.setCreateDate(new Date());
        product.setStatus(0);
        productService.insertAll(product);

        return "redirect:list";
    }


    /**
     * 上架下架
     */

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String del(Product product) {
        productService.updateById(product);
        return "redirect:list";
    }


    /**
     * 查询产品下的照片
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String image(Model model, Product product) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", product.getProductId());
        queryWrapper.orderByDesc("sort");
        product = productService.getProductById(product.getProductId());
        List<Image> imageList = imageService.list(queryWrapper);

        model.addAttribute("imageList", imageList);
        model.addAttribute("product", product);

        return "productImage/list";
    }

    /**
     * 图片新增
     *
     * @param product
     * @return
     */
    @RequestMapping(value = "/imageAdd", method = RequestMethod.GET)
    public String imageAddGet(Model model, Product product) {
        product = productService.getProductById(product.getProductId());
        model.addAttribute("product", product);
        return "productImage/add";
    }

    /**
     * 图片新增POST
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/imageAdd", method = RequestMethod.POST)
    public String imageAddPost(Model model, Integer productId, Integer sort, Integer type, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (sort == null) {
            sort = 1;
        }
        String url = imageService.imageUrl(file);
        if (url != null) {
            Image image = new Image();
            image.setImageUrl(url);
            image.setProductId(productId);
            image.setSort(sort);
            image.setType(type);
            imageService.save(image);
        }
        return "redirect:image?productId=" + productId;
    }


    /**
     * 图片删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/imageDel", method = RequestMethod.GET)
    public String imageDel(Model model, Integer imageId) {
        Image image = imageService.getById(imageId);
        imageService.removeById(imageId);
        return "redirect:image?productId=" + image.getProductId();
    }

    /**
     * 图片更改
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/imageChange", method = RequestMethod.GET)
    public String imageChange(Model model, Integer imageId, Integer productId, Integer type) {
        Image image = new Image();
        image.setImageId(imageId);
        image.setType(type);
        imageService.updateById(image);
        return "redirect:image?productId=" + productId;
    }

    /**
     * 图片更改
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/imageChangeList", method = RequestMethod.GET)
    public String imageChangeList(Model model, Integer imageId, Integer productId) {

        Image image = new Image();
        image.setListImg(0);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", productId);
        imageService.update(image, queryWrapper);
        image.setImageId(imageId);
        image.setListImg(1);
        imageService.updateById(image);
        return "redirect:image?productId=" + productId;
    }


    /**
     * 产品修改
     *
     * @param model
     * @param productId
     * @return
     */

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editGet(Model model, Integer productId) {
        Product product = productService.getProductById(productId);
        Category category = new Category();
        category.setLevel(3);
        List<Category> categoryList = categoryService.selectAll(category);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product", product);
        return "product/edit";
    }

    /**
     * 产品修改
     *
     * @param model
     * @param product
     * @return
     */

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(Model model, Product product, Integer categoryId) {

        product.setCategory(new Category(categoryId));
        product.setCreateDate(new Date());
        product.setStatus(1);
        productService.updateAll(product);
        return "redirect:list";
    }

    /**
     * 电气参数编辑查看
     */
    @RequestMapping(value = "/productAttribute", method = RequestMethod.GET)
    public String AttributeView(Model model, Integer productId) {

        Product product = productService.getProductById(productId);

        List<Attribute> attributeList = productAttributeService.getAttributeByProduct(product.getProductId(), product.getCategory().getCategoryId());
        model.addAttribute("attributeList", attributeList);
        model.addAttribute("product", product);
        return "productattribute/edit";
    }

    /**
     * 电气参数编辑提交
     */
    @RequestMapping(value = "/productAttribute", method = RequestMethod.POST)
    public String AttributeEdit(HttpServletRequest servletRequest, Integer productId) {
        Product product = productService.getProductById(productId);
        List<Attribute> attributeList = productAttributeService.getAttributeByProduct(product.getProductId(), product.getCategory().getCategoryId());
        List<ProductAttribute> list = new ArrayList<>();
        for (int i = 0; i < attributeList.size(); i++) {
            String value = servletRequest.getParameter(String.valueOf(attributeList.get(i).getAttributeId()));
            ProductAttribute productAttribute = new ProductAttribute();
            productAttribute.setProductId(productId);
            productAttribute.setAttributeId(attributeList.get(i).getAttributeId());
            productAttribute.setStatus(1);
            productAttribute.setValue(value);
            if (!StringUtils.isEmpty(value)) {
                list.add(productAttribute);
            }
        }
        productAttributeService.deleteProductAttribute(productId);
        productAttributeService.saveProductAttribute(list);
        return "redirect:list";
    }


    /**
     * 决策参数编辑查看
     */
    @RequestMapping(value = "/productDecision", method = RequestMethod.GET)
    public String DecisionView(Model model, Integer productId) {


        Product product = productService.getProductById(productId);

        List<DecisionClass> decisionClassList = productDecisionClassService.getDecisionClassForWeb(productId, product.getCategory().getCategoryId());

        model.addAttribute("decisionClassList", decisionClassList);
        model.addAttribute("product", product);
        return "productdecision/edit";
    }

    /**
     * 决策参数编辑提交
     */
    @RequestMapping(value = "/productDecision", method = RequestMethod.POST)
    public String productDecision(HttpServletRequest servletRequest, Integer productId) {
        Product product = productService.getProductById(productId);
        List<DecisionClass> decisionClassList = productDecisionClassService.getDecisionClassForWeb(productId, product.getCategory().getCategoryId());


        List<ProductDecisionClass> list1 = new ArrayList<>();
        List<ProductDecision> list2 = new ArrayList<>();

        for (int i = 0; i < decisionClassList.size(); i++) {
            for (int j = 0; j < decisionClassList.get(i).getDecisionList().size(); j++) {
                String value = servletRequest.getParameter("d" + String.valueOf(decisionClassList.get(i).getDecisionList().get(j).getDecisionId()));
                ProductDecision productDecision = new ProductDecision();
                productDecision.setResult(value.trim());
                productDecision.setStatus(1);
                productDecision.setDecision(decisionClassList.get(i).getDecisionList().get(j));
                productDecision.setCreateDate(new Date());
                productDecision.setProduct(product);
                list2.add(productDecision);
            }
            String score = servletRequest.getParameter("dc" + String.valueOf(decisionClassList.get(i).getDecisionClassId()));
            ProductDecisionClass productDecisionClass = new ProductDecisionClass();
            if (!StringUtils.isEmpty(score.trim())) {
                productDecisionClass.setScore(Float.valueOf(score.trim()));
            }
            productDecisionClass.setProduct(product);
            productDecisionClass.setDecisionClass(decisionClassList.get(i));
            productDecisionClass.setCreateDate(new Date());
            list1.add(productDecisionClass);
        }
        productDecisionClassService.deleteDecisionClass(productId);
        productDecisionClassService.saveDecisionClass(list1);

        productDecisionClassService.deleteDecision(productId);
        productDecisionClassService.saveDecision(list2);

        return "redirect:list";
    }


    /**
     * 特点查询优点缺点
     */
    @RequestMapping(value = "/feature", method = RequestMethod.GET)
    public String getFeature(Model model, Integer productId) {
        Product product = productService.getProductById(productId);
        //优点
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("category_id", product.getCategory().getCategoryId());
        queryWrapper.eq("flag", 1);

        //缺点
        List<Feature> youdianList = featureService.list(queryWrapper);
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("category_id", product.getCategory().getCategoryId());
        queryWrapper2.eq("flag", 2);
        List<Feature> quedianList = featureService.list(queryWrapper2);

        QueryWrapper queryWrapper3 = new QueryWrapper();
        queryWrapper3.eq("product_id", productId);

        List<ProductFeature> productFeatures = productFeatureService.list(queryWrapper3);

        model.addAttribute("youdianList", youdianList);
        model.addAttribute("quedianList", quedianList);
        model.addAttribute("product", product);
        model.addAttribute("productFeatures", productFeatures);
        return "product/featureedit";
    }

    @RequestMapping(value = "/feature", method = RequestMethod.POST)
    public String getFeaturePost(HttpServletRequest servletRequest, Model model, Integer productId) {
        Product product = productService.getProductById(productId);

        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("category_id", product.getCategory().getCategoryId());
        List<Feature> features = featureService.list(queryWrapper);

        //删除之前的
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("product_id", productId);

        productFeatureService.remove(queryWrapper2);

        List<ProductFeature> productFeatures = new ArrayList<>();
        for (int i = 0; i <= features.size(); i++) {
            String value = servletRequest.getParameter("feature1" + i);
            if (null != value && null != value.trim()) {
                ProductFeature productFeature = new ProductFeature();
                productFeature.setFeatureId(Integer.valueOf(value));
                productFeature.setProductId(productId);
                productFeatures.add(productFeature);
            }
        }

        for (int i = 0; i <= features.size(); i++) {
            String value = servletRequest.getParameter("feature2" + i);
            if (null != value && null != value.trim()) {
                ProductFeature productFeature = new ProductFeature();
                productFeature.setFeatureId(Integer.valueOf(value));
                productFeature.setProductId(productId);
                productFeatures.add(productFeature);
            }
        }
        productFeatureService.saveOrUpdateBatch(productFeatures);

        return "redirect:list";
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
