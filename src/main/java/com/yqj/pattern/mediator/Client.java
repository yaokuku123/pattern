package com.yqj.pattern.mediator;

import java.util.HashMap;
import java.util.Map;

//中介者抽象类
abstract class Mediator {
    //将同事对象加入到集合
    public abstract void register(String colleagueName, Colleague colleague);

    //接受消息，消息由具体的同事对象发出
    public abstract void getMessage(int stateChange, String colleagueName);
}

//具体的中介者类
class ConcreteMediator extends Mediator {
    //集合，存放所有同事对象
    private Map<String, Colleague> colleagueMap;
    private Map<String, String> internalMap;

    public ConcreteMediator() {
        colleagueMap = new HashMap<>();
        internalMap = new HashMap<>();
    }

    @Override
    public void register(String colleagueName, Colleague colleague) {
        //将同事放入集合
        colleagueMap.put(colleagueName, colleague);
        //自定义映射字段
        if (colleague instanceof Alarm) {
            internalMap.put("alarm", colleagueName);
        } else if (colleague instanceof TV) {
            internalMap.put("TV", colleagueName);
        } else if (colleague instanceof Curtains) {
            internalMap.put("curtains", colleagueName);
        } else if (colleague instanceof CoffeeMachine) {
            internalMap.put("coffeeMachine", colleagueName);
        }
    }

    //中介者核心方法
    //1.根据得到的消息，完成对应任务
    //2.中介者在这人方法中协调哥哥具体的同事对象完成任务
    @Override
    public void getMessage(int stateChange, String colleagueName) {
        //处理闹钟发出的消息
        if (colleagueMap.get(colleagueName) instanceof Alarm) {
            if (stateChange == 0) {
                ((CoffeeMachine) (colleagueMap.get(internalMap.get("coffeeMachine")))).startCoffee();
                ((TV) (colleagueMap.get(internalMap.get("TV")))).startTv();
            } else if (stateChange == 1) {
                ((TV) (colleagueMap.get(internalMap.get("TV")))).stopTv();
            }
        } else if (colleagueMap.get(colleagueName) instanceof CoffeeMachine) {
            if (stateChange == 0) {
                ((Curtains) (colleagueMap.get(internalMap.get("curtains")))).upCurtains();
            }
        } else if (colleagueMap.get(colleagueName) instanceof TV) {
            //..
        } else if (colleagueMap.get(colleagueName) instanceof Curtains) {
            //..
        }

    }

}

//同事抽象类
abstract class Colleague {
    private String name;
    private Mediator mediator;

    public Colleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public abstract void sendMessage(int stateChange);

    public Mediator getMediator() {
        return mediator;
    }

    public String getName() {
        return name;
    }
}

class Alarm extends Colleague {

    public Alarm(String name, Mediator mediator) {
        super(name, mediator);
        //在创建同事对象时，将自己放入到ConcreteMediator 对象的集合中，让中介者来管理
        mediator.register(name, this);
    }

    public void sendAlarm(int stateChange) {
        sendMessage(stateChange);
    }

    //给中介者对象方式信息
    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.getName());
    }

}

class TV extends Colleague {

    public TV(String name, Mediator mediator) {
        super(name, mediator);
        mediator.register(name, this);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.getName());
    }

    public void startTv() {
        System.out.println("打开电视机");
    }

    public void stopTv() {
        System.out.println("关闭电视机");
    }
}

class Curtains extends Colleague {

    public Curtains(String name, Mediator mediator) {
        super(name, mediator);
        mediator.register(name, this);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.getName());
    }

    public void upCurtains() {
        System.out.println("打开窗帘");
    }
}

class CoffeeMachine extends Colleague {

    public CoffeeMachine(String name, Mediator mediator) {
        super(name, mediator);
        mediator.register(name, this);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.getName());
    }

    public void startCoffee() {
        System.out.println("制作咖啡");
    }

    public void finishCoffee() {
        System.out.println("制作完成咖啡");
        sendMessage(0);
    }
}

public class Client {
    public static void main(String[] args) {
        //创建一个中介者对象
        Mediator mediator = new ConcreteMediator();
        //创建Alarm对象并加入到中介者对象的HashMap中
        Alarm alarm = new Alarm("alarm", mediator);
        //创建CoffeeMachine对象并加入到中介者对象的HashMap中
        CoffeeMachine coffeeMachine = new CoffeeMachine("coffeeMachine", mediator);
        //创建Curtains对象并加入到中介者对象的HashMap中
        Curtains curtains = new Curtains("curtains", mediator);
        //创建TV对象并加入到中介者对象的HashMap中
        TV tv = new TV("TV", mediator);

        //让闹钟发出消息
        alarm.sendAlarm(0);
        System.out.println("====");
        alarm.sendAlarm(1);
        System.out.println("====");
        //让咖啡机发出消息
        coffeeMachine.finishCoffee();
    }
}
