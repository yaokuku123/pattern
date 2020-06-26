package com.yqj.pattern.factory.factorymethod.order;

import com.yqj.pattern.factory.factorymethod.pizza.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BJOrderPizza extends OrderPizza{

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if("cheese".equals(orderType)){
            pizza = new BJCheesePizza();
        } else if("greek".equals(orderType)){
            pizza = new BJGreekPizza();
        }
        return pizza;
    }
}

class LDOrderPizza extends OrderPizza{

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

public abstract class OrderPizza {

    public abstract Pizza createPizza(String orderType);

    public OrderPizza(){
        Pizza pizza = null;
        do{
            String orderType = getOrderType();
            pizza = createPizza(orderType);
            if(pizza!=null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
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
        new BJOrderPizza();
    }
}
