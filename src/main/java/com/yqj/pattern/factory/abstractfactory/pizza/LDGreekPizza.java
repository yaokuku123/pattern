package com.yqj.pattern.factory.abstractfactory.pizza;

public class LDGreekPizza extends Pizza {
    @Override
    public void prepare() {
        setPizzaName("伦敦希腊pizza");
        System.out.println(pizzaName + "准备制作");
    }
}
