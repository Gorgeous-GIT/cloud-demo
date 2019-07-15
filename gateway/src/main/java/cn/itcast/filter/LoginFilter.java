package cn.itcast.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取Request
        HttpServletRequest request = ctx.getRequest();
        // 获取请求的参数access-token
        String token=  request.getParameter("access-token");
        //判断是否存在
        if(StringUtils.isBlank(token)){
            //不存在，未登陆，则拦截
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
        }
        return null;
    }
}
