package com.example.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(LoggingAspect.class);

    // Define the pointcut once
    @Pointcut("execution(* com.example.springaop.service.*.*(..))")
    public void serviceMethods() {
    }

    // Executes before the method
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Starting method: {}",
                joinPoint.getSignature().getName());
    }

    // Executes after the method, whether successful or unsuccessful
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Finished method: {}",
                joinPoint.getSignature().getName());
    }

    // Executes when the method returns successfully
    @AfterReturning(
            pointcut = "serviceMethods()",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned: {}",
                joinPoint.getSignature().getName(), result);
    }

    // Executes when the method throws an exception
    @AfterThrowing(
            pointcut = "serviceMethods()",
            throwing = "ex"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Method {} failed: {}",
                joinPoint.getSignature().getName(),
                ex.getMessage());
    }

    // Wraps method execution and measures execution time
    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        logger.info("Executing method: {}",
                joinPoint.getSignature().getName());

        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();

            logger.info("Execution completed: {}",
                    joinPoint.getSignature().getName());

            logger.info("Execution time: {} ms",
                    endTime - startTime);
        }
    }
}