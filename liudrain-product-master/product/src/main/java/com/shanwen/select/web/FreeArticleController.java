package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanwen.select.entity.AdminUser;
import com.shanwen.select.entity.FreeArticle;
import com.shanwen.select.entity.Image;
import com.shanwen.select.service.IFreeArticleService;
import com.shanwen.select.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 免费文章
 */
@Controller
@RequestMapping("/article")
public class FreeArticleController {
    @Autowired
    IFreeArticleService freeArticleService;

    @Autowired
    IImageService imageService;


    @Value("${domain}")
    String domain;

    /**
     * list列表
     */
    //@ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String articleList(Model model, Page page, FreeArticle article) {


        IPage<FreeArticle> result = freeArticleService.selectPage(page, article);

        page.setTotal(result.getPages());
        model.addAttribute("page", page);
        model.addAttribute("articleList", result.getRecords());
        model.addAttribute("type", article.getType());

        return "article/list";
        //return list;
    }

    /**
     * 新增
     */

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String articleAddView(Model model, FreeArticle article, Integer type) {

        model.addAttribute("type", type);
        if (type == 1) {
            return "article/add";
        } else {
            article.setCreateDate(new Date());
            article.setStatus(0);
            freeArticleService.insert(article);

            if (article.getType() == 2) {
                String contentUrl = domain + "/chargeArticle/getArticle?articleId=" + article.getFreeArticleId();
                article.setContentUrl(contentUrl);
                freeArticleService.updateById(article);
            }

            return "redirect:list?type=" + article.getType();
        }

    }

    //@ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String articleAdd(FreeArticle freeArticle, @RequestParam(value = "file", required = false) MultipartFile file, Integer type) {
        freeArticle.setCreateDate(new Date());
        freeArticle.setStatus(1);
        String url = imageService.imageUrl(file);
        freeArticle.setImageUrl(url);
        freeArticleService.insert(freeArticle);
        if (freeArticle.getType() == 2) {
            String contentUrl = domain + "/chargeArticle/getArticle?articleId=" + freeArticle.getFreeArticleId();
            freeArticle.setContentUrl(contentUrl);
            freeArticleService.updateById(freeArticle);
        }


        return "redirect:list?type=" + type;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String articleEditView(Model model, FreeArticle freeArticle, Integer type, Integer freeArticleId) {
        freeArticle = freeArticleService.getById(freeArticleId);
        model.addAttribute("freeArticle", freeArticle);
        if (type == 1) {
            return "article/edit";
        } else {
            QueryWrapper<Image> queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("product_id", -freeArticleId);
            queryWrapper1.orderByDesc("sort");
            List<Image> imageList = imageService.list(queryWrapper1);
            model.addAttribute("imageList", imageList);
            return "article/articleedit";
        }

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String articleEdit(Model model, FreeArticle freeArticle, @RequestParam(value = "file", required = false) MultipartFile file, Integer type) {
        if (file.getSize() != 0) {
            String url = imageService.imageUrl(file);
            freeArticle.setImageUrl(url);
        }

        freeArticleService.updateById(freeArticle);
        return "redirect:list?type=" + type;
    }

    /**
     * 删除或者下架
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String articleDelete(FreeArticle freeArticle, Integer flag) {
        if (flag == 0) {
            freeArticle.setStatus(0);
            freeArticleService.updateByMyId(freeArticle);
        } else if (flag == 1) {
            freeArticle.setStatus(1);
            freeArticleService.updateByMyId(freeArticle);
        } else {
            freeArticleService.removeById(freeArticle.getFreeArticleId());

        }

        return "redirect:list?type=" + freeArticle.getType();
    }


   /* **
    @RequestMapping(value = "/imageList", method = RequestMethod.GET)
    public String imageList(FreeArticle freeArticle) {
        freeArticle.setStatus(0);
        freeArticleService.updateByMyId(freeArticle);
        return "redirect:list?type=" + freeArticle.getType();
    }*/


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
