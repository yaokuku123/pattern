package com.yqj.pattern.factory.abstractfactory.pizza;

public class BJCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setPizzaName("北京奶酪pizza");
        System.out.println(pizzaName+"准备制作");
    }
}
