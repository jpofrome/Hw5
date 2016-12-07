package com.example;

import org.springframework.stereotype.Component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Ares on 12/7/2016.
 */
@Component
@Aspect

public class LoggingAspect {

    //logging aspect
    //tell user when something is being done
    @Pointcut("execution(public * com.example.Controller.*(..))")
    public void publicMethod() {}

        @Before ("publicMethod()")
        public void addLog(final JoinPoint joinPoint) {
            System.out.println("*** Executing: " + joinPoint.getSignature());
            Object[] arguments = joinPoint.getArgs();
            for (Object argument: arguments) {
                System.out.println("*** " + argument.getClass().getSimpleName() + " = " + argument);
            }
        }


        //Timing aspect
        //tell user what time something is being done
        @Around("publicMethod() && @annotation(Timed)")
        public Object profile(final ProceedingJoinPoint joinPoint) throws Throwable {
            final long start = System.currentTimeMillis();
            try {
                final Object value = joinPoint.proceed();
                return value;
            } catch (Throwable t) {
                throw t;
            } finally {
                final long stop = System.currentTimeMillis();
                System.out.println("+++ Execution time of " + joinPoint.getSignature().getName() + " was : " + (stop-start));
            }
        }
}
