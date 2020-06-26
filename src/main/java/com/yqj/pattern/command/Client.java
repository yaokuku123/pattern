package com.yqj.pattern.command;

//具体执行者
class LightReceiver{
    public void on(){
        System.out.println("开灯");
    }

    public void off(){
        System.out.println("关灯");
    }
}

//命令接口
interface Command{
    //执行命令
    public void execute();
    //撤销操作
    public void undo();
}

//具体命令，开灯
class LightOnCommand implements Command{
    //聚合具体的执行者
    private LightReceiver lightReceiver;
    //传入执行者
    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        //调用执行者的方法
        lightReceiver.on();
    }

    @Override
    public void undo() {
        //调用执行者的方法
        lightReceiver.off();
    }
}

//具体命令，关灯
class LightOffCommand implements Command{

    private LightReceiver lightReceiver;

    public LightOffCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        lightReceiver.off();
    }

    @Override
    public void undo() {
        lightReceiver.on();
    }
}

//具体命令，空实现，方便初始化按钮。省掉对空的判断
class NoCommand implements Command{

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}

class RemoteController{
    //按钮命令数值
    private Command[] onCommands;
    private Command[] offCommands;
    //撤销命令，记录上次的操作
    private Command undoCommand;

    public RemoteController() {
        //初始化按钮
        onCommands = new Command[5];
        offCommands = new Command[5];
        undoCommand = new NoCommand();
        for (int i=0 ; i<5 ; i++){
           onCommands[i] = new NoCommand();
           offCommands[i] = new NoCommand();
        }
    }

    //给按钮设置具体的命令
    public void setCommand(int index,Command onCommand,Command offCommand){
        onCommands[index] = onCommand;
        offCommands[index] = offCommand;
    }

    //按下开按钮
    public void onButtonWasPushed(int index){
        //找到按下的按钮，并调用该按钮的方法
        onCommands[index].execute();
        //记录操作，用于撤销
        undoCommand = onCommands[index];
    }

    //按下关按钮
    public void offButtonWasPushed(int index){
        offCommands[index].execute();
        undoCommand = offCommands[index];
    }

    //按撤销按钮
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }
}

public class Client {
    public static void main(String[] args) {
        //创建电灯对象(执行者)
        LightReceiver lightReceiver = new LightReceiver();
        //创建开，关灯的命令对象
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        //创建遥控器
        RemoteController remoteController = new RemoteController();
        //设置按钮对应的命令
        remoteController.setCommand(0,lightOnCommand,lightOffCommand);
        //按下按钮
        remoteController.onButtonWasPushed(0);
        remoteController.offButtonWasPushed(0);
        remoteController.undoButtonWasPushed();
    }
}
