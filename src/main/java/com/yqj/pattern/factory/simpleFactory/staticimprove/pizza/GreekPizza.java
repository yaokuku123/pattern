package com.yqj.pattern.factory.simpleFactory.staticimprove.pizza;

public class GreekPizza extends Pizza {

    @Override
    public void prepare() {
        setPizzaName("奶酪pizza");
        System.out.println(pizzaName + "准备制作");
    }
}
