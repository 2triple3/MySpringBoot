package com.shanwen.select.config;

public class WxConfig {
    //小程序appid  XXX 小程序相关配置。可以自己申请一个小程序做开发测试用或者直接用测试的
    public static final String appid = "wx750f68c82c78a951";
    //善问 ：
    //小程序key即appsecret
    public static final String appkey = "363c33d337b905853ca3328c066935c5";
    // key
    //微信支付的商户id
    public static final String mch_id = "XXX";
    //微信支付的商户密钥
    public static final String key = "XXX";
    //支付成功后的服务器回调url
    public static final String notify_url = "XXX";
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";


}
