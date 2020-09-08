package com.shanwen.select.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.FreeArticle;
import com.shanwen.select.entity.Product;

import java.util.List;

/**
 * <p>
 * 免费文章 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IFreeArticleService extends IService<FreeArticle> {
    void updateByMyId(FreeArticle freeArticle);

    int insert(FreeArticle freeArticle);

    List<FreeArticle> selectArticle();

    //小程序查询
    IPage<FreeArticle> getQueryList(Integer pageNo, Integer size);

    //管理端查询
    IPage<FreeArticle> selectPage(Page page, FreeArticle freeArticle);

}
