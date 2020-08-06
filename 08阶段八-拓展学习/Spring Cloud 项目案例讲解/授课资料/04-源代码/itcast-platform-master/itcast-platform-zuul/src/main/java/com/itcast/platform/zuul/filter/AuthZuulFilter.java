package com.itcast.platform.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

public class AuthZuulFilter extends ZuulFilter {
    // 日志输出器
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthZuulFilter.class);

    @Value("${server.port}")
    private String serverPort;

    //四种类型：pre,routing,error,post
    //pre：主要用在路由映射的阶段是寻找路由映射表的
    //routing:具体的路由转发过滤器是在routing路由器，具体的请求转发的时候会调用
    //error:一旦前面的过滤器出错了，会调用error过滤器。
    //post:当routing，error运行完后才会调用该过滤器，是在最后阶段的
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    //控制过滤器生效不生效，可以在里面写一串逻辑来控制
    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getThrowable() == null;
    }

    //执行过滤逻辑
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //从请求头中获取token信息
        String userToken = request.getHeader("userToken");
        LOGGER.debug("网关端口：" + serverPort);
        if (StringUtils.isEmpty(userToken)) {
            //设置为false就不会继续执行服务代码
            ctx.setSendZuulResponse(false);
            //设置状态码
            ctx.setResponseStatusCode(401);
            //设置相应信息
            ctx.setResponseBody("userToken is null");
            return null;
        }
        return null;
    }


}
