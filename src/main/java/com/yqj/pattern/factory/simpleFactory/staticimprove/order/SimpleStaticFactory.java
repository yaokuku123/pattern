package com.yqj.pattern.factory.simpleFactory.staticimprove.order;

import com.yqj.pattern.factory.simpleFactory.staticimprove.pizza.CheesePizza;
import com.yqj.pattern.factory.simpleFactory.staticimprove.pizza.GreekPizza;
import com.yqj.pattern.factory.simpleFactory.staticimprove.pizza.Pizza;

public class SimpleStaticFactory {
    public static Pizza getFactory(String orderType){
        Pizza pizza = null;
        if("cheese".equals(orderType)){
            pizza = new CheesePizza();
        } else if ("greek".equals(orderType)){
            pizza = new GreekPizza();
        }
        return pizza;
    }
}
