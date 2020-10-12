package com.sc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截掉所有controller的控制器
@ControllerAdvice
public class ExceptionHandlerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 当前方法为异常处理方法，处理级别为exception
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        ModelAndView mv = new ModelAndView();
        //记录异常请求地址，异常信息
        logger.error("Request URL : {}, Exception : {}",request.getRequestURL(),e);
        //如果当前异常，ResponseStatus不为空,则抛出该异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }

        //获取异常信息，url
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }

}
