package com.sc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by BaiZe schoolmate on 2020/5/9 23:21.
 */

@Aspect
@Component
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    拦截web下的所有类所有的方法 ,作为切面
    @Pointcut("execution(* com.sc.controller.*.*(..))")
    public void  log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //得到类名+方法名字
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        //得到请求参数
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        //输出请求信息，以及处理类，处理方法信息
        logger.info("Request : {}",requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("--------doAfter--------");
    }
    //定义方法返回的结果 result
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        //获取返回内容
        logger.info("Result : {}"+result);
    }
    //定义内部类，封装请求信息
    private  class RequestLog{

        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

    }
}
