package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.swing.*;

/**
 * Created by Ares on 12/7/2016.
 */

@Component
@Aspect

public class FaithfulAspect {

    @Pointcut("execution(public * com.example.FaithfulController.*ing(..))")
    public void withFriends() {}

    @Before("withFriends()")
    public void prayingWithFriends(final JoinPoint joinPoint){
        System.out.println("Lock hands and praise god ");
    }

    @After("withFriends()")
    public void closingWithFriends(final JoinPoint joinPoint){
        System.out.println("Bow our heads and nominate someone to pray ");
    }

    @Pointcut("execution(public * com.example.FaithfulController.*ing(..))")
    public void withoutFriends() {}

    @Before("withoutFriends()")
    public void prayingAlone(final JoinPoint joinPoint){
        System.out.println("Put hands together and thank God ");
    }

    @After("withoutFriends()")
    public void closingAlone(final JoinPoint joinPoint){
        System.out.println("Let God know how happy you are ");
    }


}
