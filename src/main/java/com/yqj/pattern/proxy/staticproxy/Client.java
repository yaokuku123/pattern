package com.yqj.pattern.proxy.staticproxy;

//接口
interface ITeacherDao{
    public void teach();
}

//被代理对象
class TeacherDao implements ITeacherDao{

    @Override
    public void teach() {
        System.out.println("老师上课");
    }
}

//代理对象，静态代理
class TeacherDaoProxy implements ITeacherDao{

    //代理的目标对象，通过接口聚合
    private ITeacherDao teacherDao;

    public TeacherDaoProxy(ITeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    //扩展功能
    @Override
    public void teach() {
        System.out.println("准备教材");
        teacherDao.teach();
        System.out.println("下课");
    }
}

public class Client {
    public static void main(String[] args) {
        //创建代理对象，同时将被代理对象传递给代理对象
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(new TeacherDao());
        //通过代理对象，调用被代理对象的增强方法
        teacherDaoProxy.teach();
    }
}
