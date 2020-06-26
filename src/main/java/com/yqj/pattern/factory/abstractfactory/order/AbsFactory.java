package com.yqj.pattern.factory.abstractfactory.order;

import com.yqj.pattern.factory.abstractfactory.pizza.Pizza;

public interface AbsFactory {
    Pizza createPizza(String orderType);
}
