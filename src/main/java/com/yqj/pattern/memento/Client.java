package com.yqj.pattern.memento;

import java.util.ArrayList;
import java.util.List;

//原始对象
class GameRole{
    private int vit;
    private int def;

    public GameRole(int vit, int def) {
        this.vit = vit;
        this.def = def;
    }

    public Memento createMemento(){
        return new Memento(vit,def);
    }

    public void recoverMenmento(Memento memento){
        vit = memento.getVit();
        def = memento.getDef();
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    @Override
    public String toString() {
        return "GameRole{" +
                "vit=" + vit +
                ", def=" + def +
                '}';
    }
}

//备忘录对象
class Memento{
    private int vit;
    private int def;

    public Memento(int vit, int def) {
        this.vit = vit;
        this.def = def;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}

class Caretaker{
    //在集合中会有多个备忘录对象
    private List<Memento> mementos = new ArrayList<>();

    //将某个备忘录对象加入集合
    public void add(Memento memento){
        mementos.add(memento);
    }

    //获取第index个原始对象的备忘录对象(保存的状态)
    public Memento get(int index){
        return mementos.get(index);
    }

}

public class Client {
    public static void main(String[] args) {
        GameRole gameRole = new GameRole(100,100);
        Caretaker caretaker = new Caretaker();
        caretaker.add(gameRole.createMemento());
        gameRole.setVit(70);
        gameRole.setDef(80);
        caretaker.add(gameRole.createMemento());
        gameRole.setVit(60);
        gameRole.setDef(70);
        caretaker.add(gameRole.createMemento());

        System.out.println(gameRole);
        gameRole.recoverMenmento(caretaker.get(1));
        System.out.println(gameRole);
        gameRole.recoverMenmento(caretaker.get(0));
        System.out.println(gameRole);
    }
}
