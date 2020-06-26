package com.yqj.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

abstract class People{
    private String name;

    public People(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void accept(Action action);
}

class Man extends People{

    public Man(String name) {
        super(name);
    }

    //双分派，首先将具体状态action作为参数传递到man中(第一次分派)
    //然后man类调用作为参数的action的具体方法getManResult()，同时将自己this作为参数传入(第二次分派)
    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}

class Woman extends People{

    public Woman(String name) {
        super(name);
    }

    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}

abstract class Action{
    //得到男的评价
    public abstract void getManResult(Man man);
    //得到女的评价
    public abstract void getWomanResult(Woman woman);
}

class Success extends Action{

    @Override
    public void getManResult(Man man) {
        System.out.println("男的赞同 "+ man.getName());
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女的赞同 " + woman.getName());
    }
}

class Fail extends Action{

    @Override
    public void getManResult(Man man) {
        System.out.println("男的反对" + man.getName());
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女的反对" + woman.getName());
    }
}

//数据结构，维护和管理很多人
class ObjectStructure{
    //维护集合
    private List<People> elements = new ArrayList<>();
    //添加一个人
    public void add(People p){
        elements.add(p);
    }
    //删除一个人
    public void remove(People p){
        elements.remove(p);
    }
    //显示测评情况
    public void display(Action action){
        for (People element : elements) {
            element.accept(action);
        }
    }
}

public class Client {
    public static void main(String[] args) {
        //创建两个评价标准
        Action success = new Success();
        Action fail = new Fail();
        //创建访问者对象
        Man bob = new Man("bob");
        Man tom = new Man("tom");
        Woman alice = new Woman("alice");
        //高层遍历访问者的对象
        ObjectStructure objectStructure = new ObjectStructure();
        //加入集合
        objectStructure.add(bob);
        objectStructure.add(tom);
        objectStructure.add(alice);
        //评价
        objectStructure.display(success);
        objectStructure.display(fail);

    }
}
