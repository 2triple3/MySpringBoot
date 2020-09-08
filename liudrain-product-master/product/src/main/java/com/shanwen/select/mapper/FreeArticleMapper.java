package com.shanwen.select.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanwen.select.entity.FreeArticle;

import java.util.List;

/**
 * <p>
 * 免费文章 Mapper 接口
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface FreeArticleMapper extends BaseMapper<FreeArticle> {
    /*查询用于小程序首页展示*/
    List<FreeArticle> selectArticle();


    void updateByMyId(FreeArticle freeArticleId);

}
