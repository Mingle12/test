package com.Mingle.filter;

import com.Mingle.person.Admin;
import com.Mingle.server.AdminServer;
import com.Mingle.server.impl.AdminServerImpl;
import com.Mingle.util.BeanTool;
import com.Mingle.util.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 过滤器
 * **/

@Configuration
@Slf4j
public class LoginCheckFilter implements WebMvcConfigurer, HandlerInterceptor {

    @Resource
    private LoginCheckFilter loginCheckFilter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("执行拦截！");
        registry.addInterceptor(loginCheckFilter).addPathPatterns("/**");
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if(request.getMethod().equals("OPTIONS")||"/admin/Login".equals(request.getRequestURI())||
                    "/admin/Logout".equals(request.getRequestURI())){
                response.setStatus(HttpServletResponse.SC_OK);
                log.info("无需拦截直接放行{}",request.getRequestURI());
                return true;
            }

            response.setCharacterEncoding("utf-8");
            String token = request.getHeader("token"); //前端vue将token添加在请求头中
            log.info("获取到的token：{}",token);
            if(token != null){
                Admin admin = JWTToken.verifyToken(token);
                if(admin==null){
                    System.out.println("未通过拦截器");
                    return false;
                }
                AdminServerImpl adminServer = (AdminServerImpl) BeanTool.getBean(AdminServer.class);
                boolean b = adminServer.checkAdminInfo(admin);
                if (!b){
                    return false;
                }
            }else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
