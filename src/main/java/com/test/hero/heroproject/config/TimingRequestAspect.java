package com.test.hero.heroproject.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimingRequestAspect {

    private final Logger log = LoggerFactory.getLogger(TimingRequestAspect.class);

    @Around("@annotation(TimingRequest)")
    public Object measureTimingOnRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Delay timing of the request : {}ms", duration);
        return result;
    }

}
