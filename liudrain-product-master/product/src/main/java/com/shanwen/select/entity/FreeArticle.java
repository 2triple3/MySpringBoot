package com.shanwen.select.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 免费文章
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FreeArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章主键，自增
     */
    @TableId(value = "free_article_id", type = IdType.AUTO)
    private Integer freeArticleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 文章属性
     */
    private String attribute;

    /**
     * 文章概要
     */
    private String summary;

    /**
     * 文章状态
     */
    private Integer status;

    /**
     * 内容链接
     */
    private String contentUrl;

    /**
     * 文章图片链接
     */
    private String imageUrl;

    /**
     * 文章属性，是否为付费
     */
    private Integer type;
    /**
     * 文章内容
     */

    private String content;
}
