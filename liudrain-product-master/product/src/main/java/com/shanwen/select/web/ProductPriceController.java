package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.Product;
import com.shanwen.select.entity.ProductPrice;
import com.shanwen.select.service.IProductPriceService;
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
 * 产品价格
 */
@Controller
@RequestMapping("/productprice")
public class ProductPriceController {

    @Autowired
    IProductPriceService productPriceService;
    @Autowired
    IProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String productList(Integer productId, Model model) {
//        QueryWrapper<ProductPrice> queryWrapper=new QueryWrapper<>();
//        productPrice.setStatus(1);
//        queryWrapper.setEntity(productPrice);
//        List<ProductPrice> list=productPriceService.list();

        Product product = new Product();
        product.setProductId(productId);
        ProductPrice productPrice = new ProductPrice();
        productPrice.setProduct(product);

        List<ProductPrice> list = productPriceService.selectAll(productPrice);
        product = productService.getProductById(product.getProductId());
        model.addAttribute("productPriceList", list);
        model.addAttribute("product", product);

        return "productprice/list";

    }

    /**
     * 增加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String AddView(Model model, Integer productId) {
        //获取产品的名称
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "productprice/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String Add(ProductPrice productPrice, Integer productId) {
        Product product = new Product();
        product.setProductId(productId);

        productPrice.setProduct(product);
        productPrice.setStatus(1);
        productPrice.setUpdateDate(new Date());
        //productPriceService.save(productPrice);
        productPriceService.saveAll(productPrice);
        return "redirect:list?productId=" + productId;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditView(Model model, Integer productPriceId) {

        ProductPrice productPrice = productPriceService.selectPriceById(productPriceId);
        model.addAttribute("productPrice", productPrice);
        return "productprice/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String Edit(Model model, ProductPrice productPrice, Integer productId) {
        productPrice.setUpdateDate(new Date());

        productPriceService.updateAll(productPrice);
        return "redirect:list?productId=" + productId;
    }

    /**
     * 删除id·
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteView(Integer productPriceId) {

        ProductPrice productPrice = productPriceService.selectPriceById(productPriceId);
        productPriceService.deleteById(productPriceId);
        return "redirect:list?productId=" + productPrice.getProduct().getProductId();
    }


    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
