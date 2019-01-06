package com.tutorial.crm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations

    @Pointcut("execution(* com.tutorial.crm.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.tutorial.crm.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.tutorial.crm.dao.*.*(..))")
    private void forDAOPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("<AOP>====> in @Before : calling method : " + method);
        //display the data returned
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            myLogger.info("---> argument : " + arg);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()",
                    returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("<AOP>====> in @AfterReturning : calling method : " + method);
        //display the data returned
        myLogger.info("---> result : " + result);
    }

}
