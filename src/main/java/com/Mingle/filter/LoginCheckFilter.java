package com.Mingle.filter;

import com.Mingle.common.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 * **/

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取本次请求的uri
        String requestURI = request.getRequestURI();
        log.info("本次请求的路径为：{}",requestURI);

        String[] urls = new String[]{
                "/admin/Login",
                "/admin/Logout",
                "/swagger-ui.html/*",

        };

        boolean check = check(urls, requestURI);

        //不需要处理直接放行
        if (check){
            log.info("本次请求不需要处理{}",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //判断登录状态，如果已登录，则直接放行
       if (request.getSession().getAttribute("admin")!=null){
           log.info("用户已登录，id为：{}",request.getSession().getAttribute("admin"));
           filterChain.doFilter(request,response);
           return;
       }

       log.info("用户未登录");
       //未登录则返回登录结果，通过输出流方式向客户端页面相应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
       return;
    }

    /*
    路径匹配，检查本次请求是否需要放行
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }

}
