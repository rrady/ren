package io.ren.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by aneagu on 07/01/2020.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    private long startPoint;
    private long endPoint;

    @Before("@annotation(io.ren.api.aop.EnteringExitingLog)")
    public void enteringMethod(JoinPoint joinPoint) {
        LOGGER.info("Entering method " + joinPoint.getSignature().getName() + " in " + joinPoint.getSourceLocation().getWithinType().getName());
        startPoint = System.currentTimeMillis();
    }


    @After("@annotation(io.ren.api.aop.EnteringExitingLog)")
    public void exitingMethod(JoinPoint joinPoint) {
        endPoint = System.currentTimeMillis();
        LOGGER.info("Exiting method " + joinPoint.getSignature().getName() + " in " + joinPoint.getSourceLocation().getWithinType().getName()
                + " with duration " + (endPoint -startPoint) + " milliseconds.");
    }


}
