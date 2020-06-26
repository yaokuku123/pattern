package com.yqj.pattern.factory.simpleFactory.staticimprove.order;

import com.yqj.pattern.factory.simpleFactory.staticimprove.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {
    Pizza pizza = null;

    public OrderPizza(){
        setFactory();
    }

    public void setFactory(){
        String orderType;
        do{
            orderType = getOrderType();
            pizza = SimpleStaticFactory.getFactory(orderType);
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
        new OrderPizza();
    }
}
