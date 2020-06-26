package com.yqj.pattern.factory.abstractfactory.order;


import com.yqj.pattern.factory.abstractfactory.pizza.LDCheesePizza;
import com.yqj.pattern.factory.abstractfactory.pizza.LDGreekPizza;
import com.yqj.pattern.factory.abstractfactory.pizza.Pizza;

public class LDFactory implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if("cheese".equals(orderType)){
            pizza = new LDCheesePizza();
        }else if("greek".equals(orderType)){
            pizza = new LDGreekPizza();
        }
        return pizza;
    }
}
