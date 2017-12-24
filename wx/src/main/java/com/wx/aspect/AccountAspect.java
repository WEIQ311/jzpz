package com.wx.aspect;

import com.wx.util.WxUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * aop处理
 *
 * @author weiQiang
 */
@Aspect
@Component
public class AccountAspect {

    private final static Logger logger = LoggerFactory.getLogger(AccountAspect.class);

    @Pointcut(value = "execution(* com.wx.controller.*.*(..))")
    public void doController() {
    }

    @Before("doController()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url:{},方法:{},请求ip:{},类和方法:{}(),参数:{}", request.getRequestURL(), request.getMethod(), WxUtil.getIpAddress(request), joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), joinPoint.getArgs());
       /* if (StringUtils.isBlank(request.getParameter(AccountEnum.USER_ID.getMessage()))) {
            logger.error(AccountEnum.USER_ID_EMPTY.getMessage());
            throw new RuntimeException(AccountEnum.USER_ID_EMPTY.getMessage());
        }*/
    }

    @After("doController()")
    public void doAfter() {}

    @AfterReturning(returning = "object", pointcut = "doController()")
    public void doAfterReturning(Object object) {
        if (null != object) {
            logger.debug("response:{}", object.toString());
        } else {
            logger.debug("没有返回值");
        }
    }
}
