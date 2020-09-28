package com.example.springboot.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        System.out.print("处理拦截之前");
        request.setAttribute("startTime", new Date().getTime());
        System.out.print(((HandlerMethod) obj).getBean().getClass().getName());
        System.out.print(((HandlerMethod) obj).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView) {
        System.out.print("开始处理拦截");
        Long start = (Long) request.getAttribute("startTime");
        System.out.print("耗时：" + (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {
        System.out.println("处理拦截之后");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("【拦截器】耗时 " + (new Date().getTime() - start));
        System.out.println("异常信息 " + e);
    }
}
