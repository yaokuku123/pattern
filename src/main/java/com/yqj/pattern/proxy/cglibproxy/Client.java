package com.yqj.pattern.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class TeacherDao{
    public String teach(){
        System.out.println("老师上课");
        return "OK";
    }
}

//创建代理对象的工厂
class ProxyFactory implements MethodInterceptor{

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //返回代理对象
    public Object getProxyInstance(){
        //创建一个工具类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类对象(代理对象)
        return enhancer.create();
    }

    //重写intercept方法，会调用目标对象的方法
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("准备上课材料");
        Object result = method.invoke(target, args);
        System.out.println("下课");
        return result;
    }
}

public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new TeacherDao());
        //获取代理对象，由于是通过子类扩展，所以可以使用父类类型
        TeacherDao proxyInstance = (TeacherDao) proxyFactory.getProxyInstance();
        //执行代理对象的方法，触发intercept方法，实现对目标对象的调用
        String result = proxyInstance.teach();
        System.out.println(result);
    }
}
