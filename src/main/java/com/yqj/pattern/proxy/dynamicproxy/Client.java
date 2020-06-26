package com.yqj.pattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//接口
interface ITeacherDao{
    public void teach();
    public Boolean teachStudent(String name);
}

//被代理对象
class TeacherDao implements ITeacherDao{

    @Override
    public void teach() {
        System.out.println("老师讲课");
    }

    @Override
    public Boolean teachStudent(String name) {
        System.out.println("我教"+name);
        return true;
    }
}

//生成代理对象的工厂
class ProxyFactory{

    //维护一个目标对象 Object
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){

        /*
        参数说明：
            当前目标对象的类加载器；
            当前目标对象的接口类型；
            执行对目标对象的方法时，会触发事情处理器方法，把当前执行的目标对象方法作为参数传入
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("准备上课材料");
                //反射调用目标对象的方法
                Object result = method.invoke(target, args);
                System.out.println("下课");
                return result;
            }
        });
    }
}

public class Client {
    public static void main(String[] args) {
        //创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory(new TeacherDao());
        //给目标对象创建代理对象，并将Object类型转换为ITeacherDao类型
        ITeacherDao proxyInstance = (ITeacherDao) proxyFactory.getProxyInstance();
        //通过代理对象调用目标对象的方法
        proxyInstance.teach();
        //调用用传参和返回值的方法
        Boolean result = proxyInstance.teachStudent("yorick");
        System.out.println(result);
    }
}
