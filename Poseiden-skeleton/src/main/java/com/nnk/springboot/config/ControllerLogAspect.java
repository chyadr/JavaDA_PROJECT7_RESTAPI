package com.nnk.springboot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerLogAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("within(com.nnk.springboot.controllers..*)" +
            "&& within(@org.springframework.stereotype.Controller *)")
    public void controller() {
    }

    @After("controller()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info(" {}" , joinPoint.toString());
    }
}
