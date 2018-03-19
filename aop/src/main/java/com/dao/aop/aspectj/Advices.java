package com.dao.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 通知类，横切逻辑
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.aop.aspectj.Advices.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-19 09:44:00
 */
@Aspect
@Component
public class Advices {

    /**
     * 开始通知
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 杭州微财科技有限公司
     * @param joinPoint
     * @return void
     */
    @Before("execution(* com.dao.aop.proxy.Animals.*(..))")
    public void before(JoinPoint joinPoint){
        System.out.println("======开始通知======");
        //获取切点参数
        Object[] args = joinPoint.getArgs();
        System.out.println("参数：");
        for(Object arg:args) {
            System.out.print(arg+"|");
        }
        System.out.println("\n方法名称:"+joinPoint.getSignature().getName());
        System.out.println("参数数目:"+joinPoint.getSignature().getModifiers());
        System.out.println("切面被代理的类型:"+joinPoint.getSignature().getDeclaringType());
        System.out.println("切面被代理的类型名称:"+joinPoint.getSignature().getDeclaringTypeName());
    }
    /**
     * 结束通知
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 杭州微财科技有限公司
     * @param joinPoint
     * @return void
     */
    @After("execution(* com.dao.aop.proxy.Animals.*(..))")
    public void after(JoinPoint joinPoint){
        System.out.println("======最终通知======");
    }


}
