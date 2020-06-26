package com.yqj.pattern.factory.simpleFactory.improve.order;

import com.yqj.pattern.factory.simpleFactory.improve.pizza.CheesePizza;
import com.yqj.pattern.factory.simpleFactory.improve.pizza.GreekPizza;
import com.yqj.pattern.factory.simpleFactory.improve.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {
    Pizza pizza = null;
    SimpleFactory factory = null;

    public OrderPizza(SimpleFactory factory){
        setFactory(factory);
    }

    public void setFactory(SimpleFactory factory){
        this.factory = factory;
        String orderType;
        do{
            orderType = getOrderType();
            pizza = this.factory.getFactory(orderType);
            if(pizza != null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else {
                System.out.println("订购披萨失败");
                break;
            }
        }while (true);
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
        new OrderPizza(new SimpleFactory());
    }
}
