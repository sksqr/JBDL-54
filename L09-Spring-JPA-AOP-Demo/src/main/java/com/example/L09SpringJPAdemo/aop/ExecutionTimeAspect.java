package com.example.L09SpringJPAdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    private static Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeAspect.class);


    @Around("@annotation(com.example.L09SpringJPAdemo.aop.LogExecutionTime)")
    public Object executionLogTime( ProceedingJoinPoint point ) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        long end = System.currentTimeMillis();
        long executionTime = end-start;
        LOGGER.info("Execution time : {} ms ",executionTime );
        return result;
    }


//    @Around("execution(* com.example.L09SpringJPAdemo.service.AOPService.*(..))")
//    public Object executionLogTime( ProceedingJoinPoint point ) throws Throwable {
//
//        long start = System.currentTimeMillis();
//        Object result = point.proceed();
//        long end = System.currentTimeMillis();
//        long executionTime = end-start;
//        LOGGER.info("Execution time : {} ms ",executionTime );
//        return result;
//    }

    @Before("execution(* com.example.L09SpringJPAdemo.service.AOPService.getData(..))")
    public void beforeMethod( ){
        LOGGER.info("Starting method execution " );
    }
}
