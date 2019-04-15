package com.itheima.cglib;

import com.itheima.aspect.MyAspect;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import java.lang.reflect.Method;
//import org.springframework.cglib.proxy.Callback;

//import org.aopalliance.intercept.MethodInterceptor;
//import org.aopalliance.intercept.MethodInvocation;



import java.lang.reflect.Method;

//代理类
public class CglibProxy implements MethodInterceptor {
    //代理方法
    public Object createProxy(Object target)
    {
        //创建一个动态类对象
        Enhancer enhancer = new Enhancer();
        //确定需要增强的类，设置其父类
        enhancer.setSuperclass(target.getClass());
        //添加回调函数
        enhancer.setCallback(this);
        //返回创建的代理类
        return enhancer.create();
    }


    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        //创建切面类对象
        MyAspect myAspect = new MyAspect();
        //前增强
        myAspect.check_Permissions();
        //目标方向执行
        Object obj = methodProxy.invokeSuper(proxy,args);
        //后增强
        myAspect.log();
        return obj;
    }


}
