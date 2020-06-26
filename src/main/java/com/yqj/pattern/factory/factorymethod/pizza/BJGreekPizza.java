package com.yqj.pattern.factory.factorymethod.pizza;

public class BJGreekPizza extends Pizza {

    @Override
    public void prepare() {
        setPizzaName("北京希腊pizza");
        System.out.println(pizzaName + "准备制作");
    }
}
