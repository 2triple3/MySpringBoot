package com.shanwen.select.app;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.shanwen.select.config.WxConfig;
import com.shanwen.select.entity.Msg;
import com.shanwen.select.entity.Order;
import com.shanwen.select.entity.Price;
import com.shanwen.select.entity.WeixinUserInfo;
import com.shanwen.select.service.IOrderService;
import com.shanwen.select.service.IPriceService;
import com.shanwen.select.service.WeixinUserInfoSerivice;
import com.shanwen.select.utils.PayUtil;
import com.shanwen.select.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@RequestMapping("/wxpay")
@RestController
public class WeXinPayController {

    @Autowired
    IPriceService priceService;
    @Autowired
    WeixinUserInfoSerivice userInfoService;

    @Autowired
    IOrderService orderService;

    @RequestMapping("/first")
    @ResponseBody
    public Msg wxPay(Integer priceId, HttpServletRequest request) {

        Price price = priceService.getById(priceId);
        String userId = request.getHeader("userId");
        WeixinUserInfo weixinUserInfo = userInfoService.getById(userId);
        Order order = new Order();
        order.setUserInfo(weixinUserInfo);
        order.setOrderNum(StringUtil.getOrderNo());
        order.setDateLength(price.getDuration());
        order.setCreateDate(new Date());
        order.setDateLength(price.getDuration());
        order.setPayPrice(price.getPriceNum());
        order.setStatus(0);
        try {
            //微信支付配置
            WxPayConfig wxPayConfig = new WxPayConfig();
            wxPayConfig.setMiniAppId(WxConfig.appid);      //小程序Id
            //支付商户资料
            wxPayConfig.setMchId(WxConfig.mch_id);
            wxPayConfig.setMchKey(WxConfig.key);
            wxPayConfig.setNotifyUrl(WxConfig.notify_url);

            //支付类, 所有方法都在这个类里
            BestPayServiceImpl bestPayService = new BestPayServiceImpl();
            bestPayService.setWxPayConfig(wxPayConfig);
            PayRequest payRequest = new PayRequest();
            payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MINI);
            payRequest.setOrderId(order.getOrderNum());
            payRequest.setOrderName(price.getName());
            payRequest.setOrderAmount(price.getPriceNum());
            payRequest.setOpenid(weixinUserInfo.getOpenId());
            PayResponse payResponse = bestPayService.pay(payRequest);
            System.out.println(payResponse);
            if (payResponse.getPaySign() != null) {
                orderService.createOrder(order);
                return Msg.success(payResponse);
            } else {
                return Msg.error("微信支付调用失败！");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/wxNotify")
    @ResponseBody
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);

        Map map = PayUtil.doXMLParse(notityXml);
        String sign = (String) map.get("sign");
        // 校验签名不能带sign
        map.remove("sign");
        String returnCode = (String) map.get("return_code");
        if ("SUCCESS".equals(returnCode)) {
            // 验证签名是否正确
            if (PayUtil.verify(PayUtil.createLinkString(map), sign, WxConfig.key, "utf-8")) {
                /** 此处添加自己的业务逻辑代码start **/

                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("order_num", (String) map.get("out_trade_no"));
                Order order = orderService.selectOrder((String) map.get("out_trade_no"));
                order.setStatus(1);
                order.setPayDate(new Date());
                WeixinUserInfo weixinUserInfo = order.getUserInfo();
                //设置开始时间
                weixinUserInfo.setRegisterDate(new Date());
                //设置过期时间
                weixinUserInfo.setExpireDate(rollDay(new Date(), order.getDateLength()));
                weixinUserInfo.setLevel(1);
                orderService.updateOrder(order);
                userInfoService.updateById(weixinUserInfo);

                /** 此处添加自己的业务逻辑代码end **/
                // 通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }

    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

}
