package com.shanwen.select.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanwen.select.config.StrategyContext;
import com.shanwen.select.entity.*;
import com.shanwen.select.service.*;
import com.shanwen.select.utils.HttpsUtil;
import com.shanwen.select.utils.StringUtil;
import com.shanwen.select.utils.UserInfoUtil;
import com.shanwen.select.utils.UtilTool;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/wxapp")
@RestController
public class WeiXinUserController {
    @Autowired
    WeixinUserInfoSerivice userInfoService;
    @Autowired
    IFreeArticleService freeArticleService;
    @Autowired
    IProductService productService;
    @Autowired
    IProductAttributeClassService productAttributeClassService;
    @Autowired
    IProductDecisionClassService productDecisionClassService;
    @Autowired
    IProductReportService productReportService;
    @Autowired
    IPriceService priceService;
    @Autowired
    IParamsService paramsService;
    @Autowired
    StrategyContext strategyContext;
    @Autowired
    IAttributeService attributeService;
    @Autowired
    FeatureService featureService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    FiltrateService filtrateService;
    @Autowired
    IImageService imageService;
    @Autowired
    SortService sortService;
    @Autowired
    ContentService contentService;

    @ResponseBody
    @RequestMapping(value="/api")
    public String getUserInfoByUserid() {

        return "sssssss";
    }

    /**
     * 获取用户的openid
     *
     * @param code
     * @return
     */
    @RequestMapping("/getopenId")
    public Msg getopenIds(String code) {
        System.out.println("ccccccode"+code);
        byte[] keyByte = null;
        if (code != null || !(code.equals(""))) {
            String CODE = code;
            String WebAccessToken = "";
            String openId = "";
            String userId = null;
            String sessionkey = "";
            // 替换字符串，获得请求URL
            String token = UserInfoUtil.getWebAccess(CODE);// 通过自定义工具类组合出小程序需要的登录凭证

            // 通过https方式请求获得web_access_token并获得小程序的返回
            String response = HttpsUtil.httpsRequestToString(token, "GET", null);
            // 通过JsonObject解析小程序返回数据
            JSONObject jsonObject = JSON.parseObject(response);

            // 如果JasonObject或opeid为空则登录失败
            if (null != jsonObject && jsonObject.getString("openid") != null) {
                try {
                    // 从jsonObject中获取sessionKey的值
                    WebAccessToken = jsonObject.getString("session_key");
                    keyByte = Base64.decodeBase64(WebAccessToken);
                    // 获取openid
                    openId = jsonObject.getString("openid");

                    System.out.println(
                            "》》》获取access_token成功[session_key:" + WebAccessToken + "---------------openid:" + openId);
                    // 在这之后写自己想在登录中进行的各种操作
                    // 如果存在则返回userid

                    WeixinUserInfo one = new WeixinUserInfo();
                    one.setOpenId(openId);
                    one = userInfoService.findByUser(one);
                    String phone = null;
                    if (one == null) {
                        WeixinUserInfo entity = new WeixinUserInfo();
                        entity.setUserId(UtilTool.getUUId());
                        entity.setOpenId(openId);
                        entity.setCreateDate(new Date());
                        entity.setStatus(1);
                        entity.setLevel(0);
                        if (userInfoService.save(entity)) {
                            WeixinUserInfo save = userInfoService.findByUser(entity);
                            return Msg.success(save);
                        } else {
                            return Msg.error("网络错误！");
                        }
                    } else {
                        return Msg.success(one);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    WebAccessToken = null;// 获取code失败
                    System.out.println("获取session_key失败");
                    return Msg.error("无法获取session_key失败，登录失败");
                }
            } else {
                System.out.println("获取openid及session_key失败");
                return Msg.error("无法获取openid及session_key，登录失败");
            }
        }

        return Msg.error("code为空，登录失败");
    }

    /**
     * 主页文章展示
     *
     * @return
     */

    @RequestMapping("/getHomeArticle")
    public Msg getHomeArticle(Integer pageNo, Integer size) {

        IPage<FreeArticle> articleList = freeArticleService.getQueryList(pageNo, size == null ? 4 : size);
        return Msg.success(articleList);
    }


    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Msg getUserInfo(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        WeixinUserInfo weixinUserInfo = userInfoService.getById(userId);
        return Msg.success(weixinUserInfo);
    }

    /**
     * 支付成功后更新用户的状态
     *
     * @param request
     * @param level
     * @return
     */
    @RequestMapping("/getLevel")
    public Msg getPay(HttpServletRequest request, Integer level) {
        String userId = request.getHeader("userId");
        WeixinUserInfo weixinUserInfo = new WeixinUserInfo();
        weixinUserInfo.setUserId(userId);
        weixinUserInfo.setLevel(level);
        if (userInfoService.updateById(weixinUserInfo)) {
            return Msg.success("支付成功！");
        } else {
            return Msg.error("网络错误，稍后再试！");
        }
    }

    /**
     * 获取关注清单列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/getFocus")
    public Msg getFocus(HttpServletRequest request) {
        String userId = request.getHeader("userId");
//        userId="3a33884275cb41eda029f55bef9d73fb";
        List<Product> list = productService.selectFocusProduct(userId);
        //list.forEach();
        return Msg.success(list);
    }

    /**
     * 根据关键词模糊搜产品
     *
     * @return
     */
    @RequestMapping("/getQuery")
    public Msg getQuery(String key) {
        List<Product> list = productService.getQuery(key);
        return Msg.success(list);
    }

    /**
     * 产品查询list
     *
     * @return
     */
    @RequestMapping("/getProductList")
    public Msg getProductList(HttpServletRequest request, String key, Integer categoryId, QueryParams queryParams, Integer pageNum, Integer quedian) {
        String userId = request.getHeader("userId");
        if (categoryId == null) {
            QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>();
            queryWrapper.eq("status", 1);
            List<Category> categoryList = categoryService.list(queryWrapper);
            categoryId = categoryList.get(0).getCategoryId();
        }

        List<Category> categoryList = categoryService.selectForProductAdd();

        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        QueryWrapper<Filtrate> queryWrapper = new QueryWrapper<Filtrate>();
        queryWrapper.ne("param_type", 0);
        List<Filtrate> filtrateList = filtrateService.list(queryWrapper);


        for (int i = 0; i < filtrateList.size(); i++) {
            stringBuilder1.append(filtrateList.get(i).getDbName() + ",");

            if (filtrateList.get(i).getParamType() == 1) {
                String sql = "\n  left join (select a.product_id , value as " + filtrateList.get(i).getDbName() + " from product a left join product_Attribute  t" + i + " on a.product_id " +
                        "= t" + i + ".productId where attribute_id =" + filtrateList.get(i).getParamId() + " ) tt" + i + "  on a.product_id=tt" + i + " .product_id";
                stringBuilder2.append(sql);
            }
            if (filtrateList.get(i).getParamType() == 2) {

                String sql = "\n" +
                        "left join (select productId , score as " + filtrateList.get(i).getDbName() + " from  product_DecisionClass " +
                        "  where decisionClassId =23  ) t" + i + " on a.product_id= t" + i + ".productId";
                stringBuilder2.append(sql);
            }


        }


        Map<String, Object> map = new HashMap();

        map.put("params1", stringBuilder1.toString());
        map.put("params2", stringBuilder2.toString());

        map.put("userId", userId);
        //暂时默认用第一个品类
        map.put("weight", categoryList.get(0).getCategoryId());
        if (key != null && !key.equals("undefined") && key != "") {
            map.put("name", key);
        }
        Map<String, Object> search = new HashMap();
        search.put("feature", null);
        search.put("attribute", null);
        search.put("decision", null);
        if (StringUtil.isNotEmpty(queryParams.getParams())) {
            search = getParamsMap(queryParams.getParams(), categoryId);
        }

        map.put("priceLow", queryParams.getLow() == null ? 0 : queryParams.getLow());
        map.put("priceHigh", queryParams.getHeigh() == null ? 30000 : queryParams.getHeigh());

        if (queryParams.getLogicSort() != 0) {
            Sort sort = sortService.getById(queryParams.getLogicSort());
            Filtrate filtrate = filtrateService.getById(sort.getFiltrateId());
            map.put("logicSort", "order by " + filtrate.getDbName() + " " + sort.getWay());
        }


        if (queryParams.getBrandtext() != null && !queryParams.getBrandtext().startsWith("un") && !queryParams.getBrandtext().startsWith("选择")) {
            map.put("brand", StringUtil.getParamsString(queryParams.getBrandtext()));
        }


        map.put("feature", search.get("feature"));
        map.put("attribute", search.get("attribute"));
        map.put("decision", search.get("decision"));

        if (quedian == 1) {
            List<String> que = new ArrayList<>();
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("category_id", categoryId);
            queryWrapper2.eq("flag", 2);
            List<Feature> quedianList = featureService.list(queryWrapper2);
            quedianList.forEach(q -> {
                que.add(q.getContent());
            });
            map.put("feature", StringUtil.getParamsString(que));
        }

        PageHelper.startPage(pageNum == null ? 1 : pageNum, 5);
        PageInfo pageInfo = new PageInfo(productService.getQueryListByUser(map));


        if (pageInfo.getTotal() == 0) {
            return Msg.error(null);
        }
        if ((pageNum == null ? 1 : pageNum) > pageInfo.getPages()) {
            return Msg.success(null);
        }
        return Msg.success(pageInfo);
    }

    @RequestMapping("/getProduct")
    public Msg getProduct(HttpServletRequest request, Integer productId) {
        String userId = request.getHeader("userId");
        Map querymap = new HashMap();
        querymap.put("priceLow", 0);
        querymap.put("priceHigh", 30000);

        querymap.put("userId", userId);
        querymap.put("productId", productId);
        Product product = productService.getQueryListByUser(querymap).get(0);
        List<AttributeClass> attributeClasses = productAttributeClassService.getAttributeClass(productId);
        List<DecisionClass> decisionClasses = productDecisionClassService.getDecisionClass(productId);
        List<ProductReport> reportList = productReportService.getProductReport(productId);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type", 1);
        List<Image> imageList = imageService.list(queryWrapper);
        product.setImageList(imageList);
        //1是优点 2 是缺点
        Map map = new HashMap();
        map.put("productId", productId);
        map.put("flag", 1);
        List<Feature> youdian = featureService.getFeatureList(map);
        product.setAdvantages(youdian);
        map.put("flag", 2);
        List<Feature> quedian = featureService.getFeatureList(map);
        product.setDisadvantages(quedian);

        product.setAttributeClasses(attributeClasses);
        product.setDecisions(decisionClasses);
        product.setProductReports(reportList);
        return Msg.success(product);
    }


    /**
     * 新增关注和取消关注
     *
     * @param request
     * @param productId
     * @param type
     * @return
     */

    @RequestMapping("/focus")
    public Msg getFocus(HttpServletRequest request, Integer productId, Integer type) {
        String userId = request.getHeader("userId");
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("productId", productId);
        int result = productService.updateFocus(map, type);
        if (result == 1) {
            return Msg.success("success");
        } else {
            return Msg.error("fail");
        }
    }

    /**
     * 查询当前会员价格
     *
     * @return
     */
    @RequestMapping("/getPrice")
    public Msg getPrice() {
        List<Price> list = priceService.list();
        return Msg.success(list);
    }


    /**
     * * 获取查询的格式
     *
     * @param categoryId 类别
     * @param type       是否选中缺点
     * @return
     */

    @RequestMapping("/getSelect")
    public Msg getSelect(Integer categoryId, Integer type) {

        if (null == categoryId) {
            List<Category> categoryList = categoryService.selectForProductAdd();
            categoryId = categoryList.get(0).getCategoryId();
        }

        attributeService.getAttributeForSort(categoryId);
        Map map = new HashMap();
        List<Params> params = paramsService.selectParams();

        //优点
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.eq("flag", 1);

        //缺点
        List<Feature> youdianList = featureService.list(queryWrapper);
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("category_id", categoryId);
        queryWrapper2.eq("flag", 2);
        List<Feature> quedianList = featureService.list(queryWrapper2);
        List<ParamsValue> you = new ArrayList<>();
        youdianList.forEach(
                a -> {
                    ParamsValue paramsValue = new ParamsValue();
                    paramsValue.setPvName(a.getContent());
                    you.add(paramsValue);
                });
        Params you1 = new Params();
        you1.setPName("优点");
        you1.setParamsValues(you);

        List<ParamsValue> que = new ArrayList<>();
        quedianList.forEach(
                a -> {
                    ParamsValue paramsValue = new ParamsValue();
                    paramsValue.setPvName(a.getContent());
                    if (null != type && 1 == type) {
                        paramsValue.setChecked(true);
                    }
                    que.add(paramsValue);
                });
        Params que1 = new Params();
        que1.setPName("缺点");
        que1.setParamsValues(que);


        List<Map> brands = productService.getBrand();

        List<ParamsValue> brandList = new ArrayList<>();
        brands.forEach(brand -> {
            ParamsValue paramsValue = new ParamsValue();
            paramsValue.setPvName((String) brand.get("brand"));
            paramsValue.setFirstLetter((String) brand.get("firstLetter"));
            if (brand.get("isshow") != null) {
                paramsValue.setIsshow(true);
            }
            brandList.add(paramsValue);
        });

        params.add(0, que1);
        params.add(0, you1);
        List<Params> params2 = paramsService.selectParams2();
        params.addAll(params2);

        map.put("params", params);
        map.put("brandList", brandList);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.orderByDesc("sort");

        List<Sort> sorts = sortService.list();
        Sort sort = new Sort();
        sort.setShowName("默认排序");
        sort.setSortId(0);
        sorts.add(0, sort);
        map.put("sorts", sorts);

        return Msg.success(map);
    }

    /**
     * 获取关注产品的详情信息，用于比对
     *
     * @param request
     * @return
     */
    @RequestMapping("/getFocusProduct")
    public Msg getFocusProduct(HttpServletRequest request, String[] types) {
        String userId = request.getHeader("userId");
        List<Product> list = productService.selectFocusProduct(userId);
        //1,2,4,3,5,6,7
        if (types[0].equals("null")) {
            list.forEach(product -> {
                List<DecisionClass> decisionClasses = productDecisionClassService.getDecisionClass(product.getProductId());
                product.setDecisions(decisionClasses);
            });
        } else {
            // 使用策略模式
            int sum = 0;
            for (int i = 0; i < types.length; i++) {
                sum += Integer.valueOf(types[i]);
            }
            //计算出当前的那几个选项，进行选项的处理
            list = strategyContext.getResource(list, String.valueOf(sum));
        }

        return Msg.success(list);
    }

    /**
     * 获取筛选参数
     *
     * @param queryParams
     * @param categoryId
     * @return
     */

    public Map getParamsMap(String queryParams, Integer categoryId) {
        String[] params = queryParams.split(",");

        List<String> feature = new ArrayList<>();
        List<String> attribute = new ArrayList<>();
        List<String> decision = new ArrayList<>();
        //优缺点
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);

        List<Feature> features = featureService.list(queryWrapper);
        List<String> decisions = productDecisionClassService.getDecisionResult(categoryId);

        for (int i = 0; i < params.length; i++) {
            boolean f = false;
            for (int j = 0; j < features.size(); j++) {
                if (params[i].equals(features.get(j).getContent())) {
                    feature.add(params[i]);
                    f = true;
                    break;
                }
            }
            if (!f) {
                for (int k = 0; k < decisions.size(); k++) {
                    if (params[i].equals(decisions.get(k))) {
                        decision.add(params[i]);
                        f = true;
                        break;
                    }
                }
            }
            if (!f) {
                attribute.add(params[i]);
            }
        }
        Map map = new HashMap();

        map.put("feature", feature.size() == 0 ? null : StringUtil.getParamsString(feature));
        map.put("attribute", attribute.size() == 0 ? null : StringUtil.getParamsString(attribute));
        map.put("decision", decision.size() == 0 ? null : StringUtil.getParamsString(decision));

        return map;
    }

    @RequestMapping("/getContent")
    public Msg getContent(Integer type) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<Content>();
        queryWrapper.eq("type", type);
        Content content = contentService.getOne(queryWrapper);
        return Msg.success(content);
    }


}

