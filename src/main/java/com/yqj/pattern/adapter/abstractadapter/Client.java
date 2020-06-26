package com.yqj.pattern.adapter.abstractadapter;

interface Life{
    void eat();
    void sing();
    void movie();
}

abstract class AbsAdapter implements Life{
    @Override
    public void eat() {

    }

    @Override
    public void sing() {

    }

    @Override
    public void movie() {

    }
}

public class Client {
    public static void main(String[] args) {
        AbsAdapter adapter = new AbsAdapter(){
            @Override
            public void eat() {
                System.out.println("吃顿好的");
            }
        };
        adapter.eat();
    }
}
