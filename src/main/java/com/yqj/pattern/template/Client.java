package com.yqj.pattern.template;

//抽象类，表示豆浆
abstract class SoyaMilk{
    //模板方法make，可以做成final，不让子类覆盖
    public final void make(){
        select();
        if(customerWantAdd()){
            add();
        }
        soak();
        beat();
    }

    protected void select(){
        System.out.println("选择上好原料");
    }

    protected abstract void add();

    protected void soak(){
        System.out.println("浸泡");
    }

    protected void beat(){
        System.out.println("烘培");
    }

    protected Boolean customerWantAdd(){
        return true;
    }
}

class BlackBeanSoyaMilk extends SoyaMilk{

    @Override
    protected void add() {
        System.out.println("加入黑豆");
    }
}

class PennutSoyaMilk extends SoyaMilk{

    @Override
    protected void add() {
        System.out.println("加入花生");
    }
}

//纯豆浆，不添加任何配料
class PureSoyaMilk extends SoyaMilk{

    //重写，空方法
    @Override
    protected void add() {
        //不会执行
        System.out.println("hehe");
    }

    //重写勾子方法的状态
    @Override
    protected Boolean customerWantAdd() {
        return false;
    }
}

public class Client {
    public static void main(String[] args) {
        SoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }
}
