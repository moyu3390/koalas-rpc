package thrift.xml.server.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


public class KoalasAop {

    @Pointcut("execution(public * thrift.xml.server.impl..*.*(..))")
    //@Pointcut("execution(public * thrift..*.Iface+.*(..))")
    public void myMethod(){}

    @Before("myMethod()")
    public void before1(JoinPoint joinPoint) throws NoSuchMethodException {
        System.out.println("method start1");
    }
}
