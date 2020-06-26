package com.yqj.pattern.bridge;

interface Brand{
    void open();
    void call();
    void close();
}

class Vivo implements Brand{

    @Override
    public void open() {
        System.out.println("Vivo开机");
    }

    @Override
    public void call() {
        System.out.println("Vivo打电话");
    }

    @Override
    public void close() {
        System.out.println("Vivo关机");
    }
}

class XiaoMi implements Brand{

    @Override
    public void open() {
        System.out.println("小米开机");
    }

    @Override
    public void call() {
        System.out.println("小米打电话");
    }

    @Override
    public void close() {
        System.out.println("小米关机");
    }
}

abstract class Phone{
    Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open(){
        brand.open();
    }

    protected void call(){
        brand.call();
    }

    protected void close(){
        brand.close();
    }
}

class FoldedPhone extends Phone{

    public FoldedPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        super.open();
        System.out.println("折叠样式手机");
    }

    public void call(){
        super.call();
        System.out.println("折叠样式手机");
    }

    public void close(){
        super.close();
        System.out.println("折叠样式手机");
    }
}

class UpRightPhone extends Phone{

    public UpRightPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        super.open();
        System.out.println("直立样式手机");
    }

    public void call(){
        super.call();
        System.out.println("直立样式手机");
    }

    public void close(){
        super.close();
        System.out.println("直立样式手机");
    }
}

public class Client{
    public static void main(String[] args) {
        Phone phone = new FoldedPhone(new XiaoMi());
        phone.open();
        phone.call();
        phone.close();
        System.out.println("+++++++++++++");
        UpRightPhone phone2 = new UpRightPhone(new Vivo());
        phone2.open();
        phone2.call();
        phone2.close();
    }
}
