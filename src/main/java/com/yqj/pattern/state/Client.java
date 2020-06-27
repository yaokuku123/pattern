package com.yqj.pattern.state;

import java.util.Random;

class Activity{
    //当前状态
    private State state = null;
    //奖品数量
    private int count = 0;
    //钱数
    private int money = 0;

    //状态
    private State noRaffleState = new NoRaffleState(this);
    private State canRaffleState = new CanRaffleState(this);
    private State dispenseState = new DispenseState(this);
    private State dispenseOutState = new DispenseOutState(this);

    public Activity(int count, int money) {
        this.state = getNoRaffleState();
        this.count = count;
        this.money = money;
    }

    //扣钱
    public void deduceMoney(){
        state.deduceMoney();
    }

    //抽奖
    public void raffle(){
        if(state.raffle()){
            state.dispensePrize();
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public State getNoRaffleState() {
        return noRaffleState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public State getDispenseOutState() {
        return dispenseOutState;
    }

}


//抽象类，定义各个状态都需要实现的不同行为
abstract class State{
    public abstract void deduceMoney();
    public abstract boolean raffle();
    public abstract void dispensePrize();
}

class NoRaffleState extends State{

    private Activity activity;

    public NoRaffleState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        if(activity.getCount()>0){
            int currentMoney = activity.getMoney();
            if(currentMoney - 50 >=0){
                activity.setMoney(currentMoney - 50);
                activity.setState(activity.getCanRaffleState());
                System.out.println("成功扣除50块钱,余额:"+(activity.getMoney()));
            }else {
                System.out.println("余额不足,抽奖结束");
                System.exit(0);
            }
        }else {
            activity.setState(activity.getDispenseOutState());
        }
    }

    @Override
    public boolean raffle() {
        System.out.println("请先扣钱");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("请先扣钱");
    }
}

class CanRaffleState extends State{

    private Activity activity;

    public CanRaffleState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("已扣钱");
    }

    @Override
    public boolean raffle() {
        System.out.println("正在抽奖");
        Random random = new Random();
        int num = random.nextInt(10);
        if (num == 0){
            System.out.println("抽奖成功");
            activity.setCount(activity.getCount()-1);
            activity.setState(activity.getDispenseState());
            return true;
        }
        System.out.println("很遗憾，没有抽到奖品");
        activity.setState(activity.getNoRaffleState());
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("请先抽奖");
    }
}

class DispenseState extends State{

    private Activity activity;

    public DispenseState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("已扣钱");
    }

    @Override
    public boolean raffle() {
        System.out.println("已抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        if (activity.getCount() > 0){
            System.out.println("发送奖品,可以再次抽奖");
            activity.setState(activity.getNoRaffleState());
        }else {
            System.out.println("发送奖品，奖品已抽完");
            activity.setState(activity.getDispenseOutState());
        }
    }
}

class DispenseOutState extends State{

    private Activity activity;

    public DispenseOutState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("奖品已抽完，无法扣款");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品已抽完，无法抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品已抽完");
    }
}

public class Client {
    public static void main(String[] args) {
        Activity activity = new Activity(2,500);
        for (int i = 0; i < 30; i++) {
            System.out.println("-----第"+(i+1)+"次抽奖-----");
            //扣钱
            activity.deduceMoney();
            //参与抽奖
            activity.raffle();
        }
    }
}
