package com.yqj.pattern.factory.factorymethod.pizza;

public abstract class Pizza {
    protected String pizzaName;

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public abstract void prepare();

    public void bake() {
        System.out.println(pizzaName + "烘焙披萨");
    }

    public void cut() {
        System.out.println(pizzaName + "切披萨");
    }

    public void box() {
        System.out.println(pizzaName + "打包披萨");
    }


}
