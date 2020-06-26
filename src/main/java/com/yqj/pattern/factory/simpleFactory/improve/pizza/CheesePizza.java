package com.yqj.pattern.factory.simpleFactory.improve.pizza;

public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        setPizzaName("希腊pizza");
        System.out.println(pizzaName+"准备制作");
    }
}
