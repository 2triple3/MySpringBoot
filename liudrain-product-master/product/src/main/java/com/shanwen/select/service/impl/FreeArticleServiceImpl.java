package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.FreeArticle;
import com.shanwen.select.entity.Product;
import com.shanwen.select.mapper.FreeArticleMapper;
import com.shanwen.select.service.IFreeArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 免费文章 服务实现类
 *
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Service
public class FreeArticleServiceImpl extends ServiceImpl<FreeArticleMapper, FreeArticle> implements IFreeArticleService {
    @Autowired
    FreeArticleMapper freeArticleMapper;

    @Override
    public List<FreeArticle> selectArticle() {
        return freeArticleMapper.selectArticle();
    }


    @Override
    public IPage<FreeArticle> getQueryList(Integer pageNo, Integer size) {
        Page<FreeArticle> page = new Page<>(pageNo == null ? 1 : pageNo, size == null ? 2 : size);
        QueryWrapper<FreeArticle> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        wrapper.orderByDesc("create_Date");
        IPage<FreeArticle> result = freeArticleMapper.selectPage(page, wrapper);
        return result;
    }

    @Override
    public IPage<FreeArticle> selectPage(Page page, FreeArticle freeArticle) {
        QueryWrapper<FreeArticle> wrapper = new QueryWrapper<>();
        if (freeArticle.getType() != null) {
            wrapper.eq("type", freeArticle.getType());
        }
        wrapper.orderByDesc("free_article_id");
        IPage<FreeArticle> result = freeArticleMapper.selectPage(page, wrapper);
        return result;
    }


    @Override
    public void updateByMyId(FreeArticle freeArticle) {
        freeArticleMapper.updateByMyId(freeArticle);
    }

    @Override
    public int insert(FreeArticle freeArticle) {
        return freeArticleMapper.insert(freeArticle);
    }


}
