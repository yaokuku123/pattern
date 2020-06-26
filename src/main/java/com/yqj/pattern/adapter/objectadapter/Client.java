package com.yqj.pattern.adapter.objectadapter;

//被适配的类
class Voltage220V{
    public int output220V(){
        int src = 220;
        System.out.println("输出电压="+src+"V");
        return src;
    }
}
//适配器接口
interface Voltage5V{
    int output5V();
}
//对象适配器
class VoltageAdapter implements Voltage5V{

    Voltage220V voltage220V;
    //聚合被适配对象
    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    //实现适配，将220V电压转换为5V电压
    @Override
    public int output5V() {
        int src = voltage220V.output220V();
        //转换电压
        int dst = src / 44;
        return dst;
    }
}
//手机
class Phone{
    public void charging(Voltage5V voltage5V){
        if(voltage5V.output5V() == 5){
            System.out.println("可以充电 电压5V");
        }else {
            System.out.println("不可充电");
        }
    }
}
//用户
public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));
    }
}


