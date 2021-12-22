package com.nnk.springboot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class ControllerLogAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("within(com.nnk.springboot.controllers..*)" +
            "&& within(@org.springframework.stereotype.Controller *)")
    public void controller() {
    }

    @Before("controller()")
    public void logBefore(JoinPoint joinPoint) {

        logger.info("********************************* Start aspect logging *******************************************************");
        logger.info("Entering in Method :  {}",  joinPoint.getSignature().getName());
        logger.info("Class Name :  {}" , joinPoint.getSignature().getDeclaringTypeName());
        logger.info("Arguments :  {}" , Arrays.toString(joinPoint.getArgs()));
        logger.info("********************************** End aspect logging *******************************************************");

    }
}
