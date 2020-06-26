package com.yqj.pattern.factory.simpleFactory.improve.order;

import com.yqj.pattern.factory.simpleFactory.improve.pizza.CheesePizza;
import com.yqj.pattern.factory.simpleFactory.improve.pizza.GreekPizza;
import com.yqj.pattern.factory.simpleFactory.improve.pizza.Pizza;

public class SimpleFactory {
    public Pizza getFactory(String orderType){
        Pizza pizza = null;
        if("cheese".equals(orderType)){
            pizza = new CheesePizza();
        } else if ("greek".equals(orderType)){
            pizza = new GreekPizza();
        }
        return pizza;
    }
}
