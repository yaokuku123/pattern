package com.yqj.pattern.singleton.type06;

public class Singleton06 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance == instance2);
        instance.doSomething();
    }
}

enum Singleton{
    INSTANCE;
    public void doSomething(){
        System.out.println("Hello World");
    }
}
