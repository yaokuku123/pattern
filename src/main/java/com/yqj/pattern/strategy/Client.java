package com.yqj.pattern.strategy;

abstract class Duck{
    //属性，策略接口
    protected FlyBehavior flyBehavior;

    public Duck(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public abstract void display();

    public void quack(){
        System.out.println("鸭子嘎嘎叫");
    }

    public void swim(){
        System.out.println("鸭子会游泳");
    }

    public void fly(){
        if (flyBehavior!=null){
            flyBehavior.fly();
        }
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}

class PekingDuck extends Duck{


    public PekingDuck() {
        super(new BadFlyBehavior());
    }

    @Override
    public void display() {
        System.out.println("北京鸭");
    }
}

class ToyDuck extends Duck{

    public ToyDuck() {
        super(new UnFlyBehavior());
    }

    @Override
    public void display() {
        System.out.println("玩具鸭");
    }
}

class WildDuck extends Duck{

    public WildDuck() {
        super(new GoodFlyBehavior());
    }

    @Override
    public void display() {
        System.out.println("野鸭");
    }
}

interface FlyBehavior{
    void fly();
}

class BadFlyBehavior implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("飞翔技术一般");
    }
}

class GoodFlyBehavior implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("飞翔技术高超");
    }
}

class UnFlyBehavior implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("不会飞翔");
    }
}

public class Client {
    public static void main(String[] args) {
        Duck wildDuck = new WildDuck();
        wildDuck.fly();
        System.out.println("=====");
        Duck pekingDuck = new PekingDuck();
        pekingDuck.fly();
        pekingDuck.setFlyBehavior(new UnFlyBehavior());
        pekingDuck.fly();
        System.out.println("=====");
        Duck toyDuck = new ToyDuck();
        toyDuck.fly();
    }
}
