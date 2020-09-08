package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.Image;
import com.shanwen.select.entity.Product;
import com.shanwen.select.entity.ProductReport;
import com.shanwen.select.service.IImageService;
import com.shanwen.select.service.IProductReportService;
import com.shanwen.select.service.IProductService;
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
import java.util.Date;
import java.util.List;

/**
 * 产品评价
 */
@Controller
@RequestMapping("/productreport")
public class ProductReportController {
    @Autowired
    IProductReportService productReportService;
    @Autowired
    IProductService productService;
    @Autowired
    IImageService imageService;

    /**
     * 列表
     *
     * @return
     */
    //@ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String productList(ProductReport productReport, Model model, Integer productId) {
//        QueryWrapper<ProductReport> queryWrapper=new QueryWrapper<>();
////        productReport.setStatus(1);
////        queryWrapper.setEntity(productReport);
////        List<ProductReport> list=productReportService.list();
        Product product = productService.getProductById(productId);
        productReport.setStatus(1);
        productReport.setProduct(product);
        List<ProductReport> list = productReportService.selectAll(productReport);
        model.addAttribute("productReportList", list);
        model.addAttribute("product", product);
        return "productreport/list";

    }

    /**
     * 增加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String AddView(Model model, Integer productId) {

        //获取产品的名称
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", product.getProductId());
        queryWrapper.orderByDesc("sort");
        List<Image> imageList = imageService.list(queryWrapper);
        model.addAttribute("imageList", imageList);
        return "productreport/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(ProductReport productReport, Integer productId) {
        Product product = new Product();
        product.setProductId(productId);
        productReport.setProduct(product);
        productReport.setCreateDate(new Date());
        productReport.setStatus(1);
        productReportService.saveAll(productReport);
        return "redirect:list?productId=" + productId;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditView(Model model, ProductReport productReport) {


        //产品名称列表
        ProductReport report = productReportService.getOneProductReport(productReport.getProductReportId());
        //获取要修改的一条数据
        Product product = productService.getProductById(report.getProduct().getProductId());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", product.getProductId());
        queryWrapper.orderByDesc("sort");
        List<Image> imageList = imageService.list(queryWrapper);
        model.addAttribute("imageList", imageList);
        model.addAttribute("product", product);

        model.addAttribute("productReport", report);
        return "productreport/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String Edit(Model model, ProductReport productReport, Integer productId) {
        productReport.setCreateDate(new Date());
        productReportService.updateAll(productReport);
        return "redirect:list?productId=" + productId;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteView(Integer productReportId) {

        ProductReport report = productReportService.getOneProductReport(productReportId);
        productReportService.removeById(productReportId);
        return "redirect:list?productId=" + report.getProduct().getProductId();
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
