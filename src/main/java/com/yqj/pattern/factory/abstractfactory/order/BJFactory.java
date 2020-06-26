package com.yqj.pattern.factory.abstractfactory.order;

import com.yqj.pattern.factory.abstractfactory.pizza.BJCheesePizza;
import com.yqj.pattern.factory.abstractfactory.pizza.BJGreekPizza;
import com.yqj.pattern.factory.abstractfactory.pizza.Pizza;

public class BJFactory implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if("cheese".equals(orderType)){
            pizza = new BJCheesePizza();
        }else if("greek".equals(orderType)){
            pizza = new BJGreekPizza();
        }
        return pizza;
    }
}
