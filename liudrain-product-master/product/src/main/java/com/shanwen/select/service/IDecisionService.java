package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.Decision;
import com.sun.javafx.image.IntPixelGetter;

import java.util.List;

/**
 * <p>
 * 产品决策参数 服务类
 * </p>
 *
 * @author liudong
 * @since 2020-01-13
 */
public interface IDecisionService extends IService<Decision> {

    List<Decision> selectAll(Decision decision);

    void updateByMyId(Integer decisionId);

    //新增
    void insertAll(List<Decision> decision);

    Decision selectMyNeed(Integer decisionId);

    void updateAll(Decision decision);


    void deleteByDecisionClass(Integer decisionClassId);
}
