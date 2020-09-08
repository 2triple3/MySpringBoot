package com.shanwen.select.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanwen.select.entity.FreeArticle;
import com.shanwen.select.entity.Price;
import com.shanwen.select.service.IOrderService;
import com.shanwen.select.service.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单数据
 */
@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    IOrderService orderService;

    @Autowired
    IPriceService priceService;

    /**
     * 订单数据列表
     */

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String orderList(Model model) {

        Map map = new HashMap<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        calendar.setTime(date);

        String startDate = sdf.format(calendar.getTime());
        map.put("startDate", startDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String endDate = sdf.format(calendar.getTime());
        map.put("endDate", endDate);

        Map dailyResult = orderService.getOrderData(map);
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        endDate = sdf.format(calendar.getTime());
        map.put("endDate", endDate);

        Map weekResult = orderService.getOrderData(map);
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        endDate = sdf.format(calendar.getTime());
        map.put("endDate", endDate);
        Map monthResult = orderService.getOrderData(map);
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        endDate = sdf.format(calendar.getTime());
        map.put("endDate", endDate);
        Map yearResult = orderService.getOrderData(map);


        model.addAttribute("dailyResult", dailyResult);
        model.addAttribute("weekResult", weekResult);
        model.addAttribute("monthResult", monthResult);
        model.addAttribute("yearResult", yearResult);

        return "order/list";
    }

    /**
     * 当前价格
     */

    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public String getPrice(Model model) {


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_Date");
        List<Price> priceList = priceService.list(queryWrapper);
        model.addAttribute("priceList", priceList);
        return "order/price";
    }

    /**
     * 当前价格
     */

    @RequestMapping(value = "/editPrice", method = RequestMethod.GET)
    public String editPrice(Model model, Price price) {


        priceService.updateById(price);
        return "redirect:price";
    }

}
