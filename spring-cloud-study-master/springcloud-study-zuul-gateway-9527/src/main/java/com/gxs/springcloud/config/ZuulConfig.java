package com.gxs.springcloud.config;

import com.gxs.springcloud.route.ZuulRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;

    @Bean
    public ZuulRouteLocator getRouteLocator() {
        return new ZuulRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
//        return new ZuulRouteLocator(this.server.getServlet().getServletPrefix(), this.zuulProperties);
    }


//    @Bean
//    public SimpleRouteLocator zuulRouteLocator(ZuulProperties zuulProperties, IZuulRouteDataRepository dataRepository) {
//        Map<String, ZuulProperties.ZuulRoute> routeMap = zuulProperties.getRoutes();
//        List<ZuulRouteData> data = dataRepository.findAll();
//        ZuulProperties.ZuulRoute zuulRoute;
//        for (ZuulRouteData routeData : data) {
//            zuulRoute = new ZuulProperties.ZuulRoute();
//            String path = routeData.getPath();
//            zuulRoute.setId(path);
//            zuulRoute.setPath("/" + path + "/**");
//            zuulRoute.setUrl(routeData.getUrl());
//            zuulRoute.setRetryable(false);
//            zuulRoute.setStripPrefix(true);
//            zuulRoute.setSensitiveHeaders(new HashSet<>());
//            routeMap.put(path, zuulRoute);
//        }
//        zuulProperties.setRoutes(routeMap);
//        return new SimpleRouteLocator("", zuulProperties);
//    }

}
