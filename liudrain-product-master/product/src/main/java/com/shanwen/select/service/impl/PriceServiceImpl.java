package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Price;
import com.shanwen.select.mapper.PriceMapper;
import com.shanwen.select.service.IPriceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 当前在线的售卖价格 服务实现类
 * </p>
 *
 * @author liudong
 * @since 2019-12-28
 */
@Service
public class PriceServiceImpl extends ServiceImpl<PriceMapper, Price> implements IPriceService {

}
