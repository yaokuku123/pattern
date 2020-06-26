package com.yqj.pattern.factory.abstractfactory.order;


import com.yqj.pattern.factory.abstractfactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {
    AbsFactory factory;

    public OrderPizza(AbsFactory factory){
        setFactory(factory);
    }

    public void setFactory(AbsFactory factory){
        this.factory = factory;
        Pizza pizza = null;
        do{
            String orderType = getOrderType();
            pizza = this.factory.createPizza(orderType);
            if(pizza!=null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else {
                System.out.println("订购失败");
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
        new OrderPizza(new BJFactory());
    }
}
