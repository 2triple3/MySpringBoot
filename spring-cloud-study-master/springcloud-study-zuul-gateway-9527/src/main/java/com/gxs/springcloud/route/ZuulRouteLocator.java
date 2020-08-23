package com.gxs.springcloud.route;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
public class ZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private ZuulProperties properties;
//    private StringRedisTemplate redisTemplate;
//    @Autowired
//    private ServerConfigService serverConfigService;

    public ZuulRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
//        this.properties = properties;
//        this.redisTemplate = redisTemplate;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    //覆盖这个方法，重实现它
    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        //重新定义一个路由映射map
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //把父类中的映射继承下来，它主要是从配置文件中取的映射。
        routesMap.putAll(super.locateRoutes());
        //这里的路由信息来自于配置文件
        Map<String,String> map =new HashMap<>();
        //for (Map.Entry<String, String> entry : serverConfigService.getGmNodes().entrySet()) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String serverId = entry.getKey();
            String serviceId = entry.getValue().toLowerCase();
            String path = "/node/**";
            ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            //服务提供者的id,即spring.application.name
            zuulRoute.setServiceId(serviceId);
            //这个id是匹配的前半部分，比如匹配模式是/node/** 那么这个id就是/node
            zuulRoute.setId("/node");
            //匹配的路径
            zuulRoute.setPath(path);
            //这里注意一下，这个key就是要匹配的path,可以查看父类的实现，它就是使用path做为key的。
            routesMap.put(path, zuulRoute);
        }
        return routesMap;
    }


}
