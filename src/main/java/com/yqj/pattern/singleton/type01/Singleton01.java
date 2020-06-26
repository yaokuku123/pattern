package com.yqj.pattern.singleton.type01;

public class Singleton01 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
    }
}

class Singleton{
    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance(){
        return instance;
    }
}

