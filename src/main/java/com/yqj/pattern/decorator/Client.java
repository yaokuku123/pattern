package com.yqj.pattern.decorator;

abstract class Drink {
    private String desc;
    private float price = 0.0f;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract float cost();
}

class Coffee extends Drink {

    @Override
    public float cost() {
        return this.getPrice();
    }
}

class ShortBlack extends Coffee {
    public ShortBlack() {
        setDesc("ShortBlack");
        setPrice(5.0f);
    }
}

class LongBlack extends Coffee {
    public LongBlack() {
        setDesc("Decaf");
        setPrice(3.0f);
    }
}

class Decorator extends Drink {

    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + "&&" + obj.getDesc();
    }
}

class Chocolate extends Decorator {

    public Chocolate(Drink obj) {
        super(obj);
        setDesc("Chocolate");
        setPrice(2.0f);
    }
}

class Milk extends Decorator {

    public Milk(Drink obj) {
        super(obj);
        setDesc("Milk");
        setPrice(1.0f);
    }
}

public class Client {
    public static void main(String[] args) {
        Drink order = new LongBlack();
        System.out.println(order.getDesc() + " " + order.cost());
        order = new Milk(order);
        System.out.println(order.getDesc() + " " + order.cost());
        order = new Chocolate(order);
        System.out.println(order.getDesc() + " " + order.cost());
        order = new Chocolate(order);
        System.out.println(order.getDesc() + " " + order.cost());
    }
}
