package com.yqj.pattern.singleton.type03;

public class Singleton03 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
    }
}

class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
