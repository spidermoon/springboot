package com.example.springboot.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Pointcut("execution(public * com.example.springboot.web..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录请求内容
        logger.info("URL：" + request.getRequestURI().toLowerCase());
        logger.info("HTTP_METHOD:" + request.getMethod());
        logger.info("IP：" + request.getRemoteAddr());
        logger.info("CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS:" + Arrays.toString(joinPoint.getArgs()));

    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        System.out.println("-----执行时间为：" + time);
        return result;
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        logger.info("RESPONSE" + ret);
    }
}
