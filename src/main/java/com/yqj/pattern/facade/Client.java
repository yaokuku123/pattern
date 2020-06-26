package com.yqj.pattern.facade;

class DVDPlayer {
    private static DVDPlayer dvdPlayer = new DVDPlayer();

    private DVDPlayer() {

    }

    public static DVDPlayer getInstance() {
        return dvdPlayer;
    }

    public void on() {
        System.out.println("DVDPlayer on");
    }

    public void off() {
        System.out.println("DVDPlayer off");
    }

    public void play() {
        System.out.println("DVDPlayer play");
    }

    public void pause() {
        System.out.println("DVDPlayer pause");
    }
}

class Screen {
    private static Screen screen = new Screen();

    private Screen() {

    }

    public static Screen getInstance(){
        return screen;
    }

    public void up(){
        System.out.println("Screen up");
    }

    public void down(){
        System.out.println("Screen down");
    }
}

class Popcorn{
    private static Popcorn popcorn = new Popcorn();
    private Popcorn(){

    }
    public static Popcorn getInstance(){
        return popcorn;
    }

    public void on(){
        System.out.println("Popcorn on");
    }

    public void off(){
        System.out.println("Popcorn off");
    }

    public void pop(){
        System.out.println("Popcorn pop");
    }
}

class HomeTheaterFacade{
    private DVDPlayer dvdPlayer = DVDPlayer.getInstance();
    private Screen screen = Screen.getInstance();
    private Popcorn popcorn = Popcorn.getInstance();

    public void ready(){
        popcorn.on();
        popcorn.pop();
        screen.down();
        dvdPlayer.on();
    }

    public void play(){
        dvdPlayer.play();
    }

    public void pause(){
        dvdPlayer.pause();
    }

    public void end(){
        popcorn.off();
        screen.up();
        dvdPlayer.off();
    }
}

public class Client {
    public static void main(String[] args) {
        HomeTheaterFacade facade = new HomeTheaterFacade();
        facade.ready();
        System.out.println("=====");
        facade.play();
        System.out.println("=====");
        facade.pause();
        System.out.println("=====");
        facade.end();
    }
}
