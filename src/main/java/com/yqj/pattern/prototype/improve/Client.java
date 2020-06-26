package com.yqj.pattern.prototype.improve;

public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        System.out.println(sheep+" hashcode:"+sheep.hashCode());
        System.out.println(sheep2+" hashcode:"+sheep2.hashCode());
        System.out.println(sheep3+" hashcode:"+sheep3.hashCode());

    }
}


