package com.yqj.pattern.factory.simpleFactory.triditional.order;

import com.yqj.pattern.factory.simpleFactory.improve.pizza.CheesePizza;
import com.yqj.pattern.factory.simpleFactory.improve.pizza.GreekPizza;
import com.yqj.pattern.factory.simpleFactory.improve.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {
    Pizza pizza = null;
    String orderType = null;

    public OrderPizza(){
        do {
            orderType = getOrderType();
            if("cheese".equals(orderType)){
                pizza = new CheesePizza();
            } else if ("greek".equals(orderType)){
                pizza = new GreekPizza();
            } else {
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    private String getOrderType(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza type: ");
            String orderType = bufferedReader.readLine();
            return orderType;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        new OrderPizza();
    }
}
