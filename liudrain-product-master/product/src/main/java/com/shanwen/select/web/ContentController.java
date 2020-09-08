package com.shanwen.select.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.Category;
import com.shanwen.select.entity.Content;
import com.shanwen.select.entity.Image;
import com.shanwen.select.entity.Product;
import com.shanwen.select.service.ContentService;
import com.shanwen.select.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentService contentService;

    @Autowired
    IImageService imageService;


    @RequestMapping(value = "/contentList", method = RequestMethod.GET)
    public String getContentList(Model model) {
        //采用构造器查询
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("type", 1, 2);
        List<Content> contentList = contentService.list(queryWrapper);
        if (contentList.size() == 0) {
            Content content = new Content();
            content.setType(1);
            contentList.add(content);
            Content content1 = new Content();
            content1.setType(2);
            contentList.add(content1);
            contentService.saveBatch(contentList);
        }
        contentList = contentService.list();
        model.addAttribute("contentList", contentList);
        return "content/list";
    }


    /**
     * 未用到
     *
     * @param model
     * @param a
     * @return
     */
    @RequestMapping(value = "/chargeArticle", method = RequestMethod.GET)
    public String chargeArticle(Model model, Integer a) {
        //采用构造器查询
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", 3);
        List<Content> contentList = contentService.list(queryWrapper);
        if (contentList.size() == 0) {
            Content content = new Content();
            content.setType(1);
            contentList.add(content);
            Content content1 = new Content();
            content1.setType(2);
            contentList.add(content1);
            contentService.saveBatch(contentList);
        }

        contentList = contentService.list();
        model.addAttribute("contentList", contentList);
        return "content/list";
    }


    @RequestMapping(value = "/getContent", method = RequestMethod.GET)
    public String getJsonCategoryList(Model model, Integer type) {
        //采用构造器查询
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        Content content = contentService.getOne(queryWrapper);
        if (content == null) {
            content = new Content();
        }
        QueryWrapper<Image> queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("product_id", 0);
        queryWrapper1.orderByDesc("sort");
        List<Image> imageList = imageService.list(queryWrapper1);
        model.addAttribute("imageList", imageList);
        model.addAttribute("content", content);
        model.addAttribute("type", type);
        return "content/edit";
    }

    @RequestMapping(value = "/getContent", method = RequestMethod.POST)
    public String postContent(Model model, Content content) {
        //采用构造器查询
        contentService.saveOrUpdate(content);
        return "redirect:contentList?type=" + content.getType();
    }

    /**
     * 查询出相关照片
     * *
     *
     * @return
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String image(Model model, Integer articleId) {

        QueryWrapper<Image> queryWrapper = new QueryWrapper<>();
        if (articleId != null) {
            queryWrapper.eq("product_id", -articleId);
            model.addAttribute("articleId", articleId);
        } else {
            queryWrapper.eq("product_id", 0);
        }
        List<Image> imageList = imageService.list(queryWrapper);
        queryWrapper.orderByDesc("sort");
        model.addAttribute("imageList", imageList);
        return "content/imgList";
    }

    /**
     * 图片新增
     *
     * @return
     */
    @RequestMapping(value = "/imageAdd", method = RequestMethod.GET)
    public String imageAddGet(Model model, Integer articleId) {

        model.addAttribute("articleId", articleId);

        return "content/imgadd";
    }

    /**
     * 图片新增POST
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/imageAdd", method = RequestMethod.POST)
    public String imageAddPost(Model model, Integer articleId, Integer sort, Integer type, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (sort == null) {
            sort = 1;
        }
        String url = imageService.imageUrl(file);
        if (url != null) {
            Image image = new Image();
            image.setImageUrl(url);
            if (articleId != null) {
                image.setProductId(-articleId);
            } else {
                image.setProductId(0);
            }
            image.setSort(sort);
            image.setType(type);
            imageService.save(image);
        }
        if (articleId != null) {
            return "redirect:image?articleId=" + articleId;
        } else {
            return "redirect:image";
        }
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


}
