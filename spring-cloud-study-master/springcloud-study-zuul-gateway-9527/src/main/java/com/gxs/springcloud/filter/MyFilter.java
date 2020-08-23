package com.gxs.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @Author：大漠知秋
 * @Description：测试使用第一个 pre Filter
 * @CreateDate：4:34 PM 2018/10/30
 */
@Component
@Slf4j
public class MyFilter extends ZuulFilter {
    /**
     * 过滤类型
     *String ERROR_TYPE = "error";
     *String POST_TYPE = "post";
     *String PRE_TYPE = "pre";
     *String ROUTE_TYPE = "route";
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 多个过滤器时，此过滤器执行次序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 代表该过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤请求后做的处理，比如检查请求带没带token
     * @return
     */
    @Override
    public Object run() {
        log.info("经过第一个 pre 过滤器");

        // 获取请求上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取到request
        HttpServletRequest request = context.getRequest();

        String user = request.getParameter("user");
        String uri = request.getRequestURI();
        // 若请求中包含/abc8080路径，且没有user请求参数，则无法通过过滤
        if(uri.contains("/abc8080") && StringUtils.isEmpty(user)) {
            log.warn("user用户为空");
            // 指定当前请求未通过zuul过滤，默认值为true
            context.setResponseBody("无法通过过滤");
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED_401);
            context.setSendZuulResponse(false);
            return null;
        }
        context.setSendZuulResponse(true);
        return null;
    }

}
