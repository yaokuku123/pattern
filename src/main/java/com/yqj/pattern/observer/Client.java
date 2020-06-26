package com.yqj.pattern.observer;

import java.util.ArrayList;
import java.util.List;

interface Subject{
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}

class WeatherData implements Subject{

    private float temperature;
    private float pressure;
    private float humidity;
    private List<Observer> observers = new ArrayList<>();

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setData(float temperature,float pressure,float humidity){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if(observers.contains(observer)){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        System.out.println("======update========");
        for (Observer observer : observers) {
            observer.update(temperature,pressure,humidity);
        }
    }
}

interface Observer{
    void update(float temperature,float pressure,float humidity);
}

class Baidu implements Observer{
    private float temperature;
    private float pressure;
    private float humidity;

    @Override
    public void update(float temperature,float pressure,float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    public void display(){
        System.out.println("Baidu temperature:"+temperature);
        System.out.println("Baidu pressure:"+pressure);
        System.out.println("Baidu humidity"+humidity);
    }
}

class Sina implements Observer{
    private float temperature;
    private float pressure;
    private float humidity;

    @Override
    public void update(float temperature,float pressure,float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    public void display(){
        System.out.println("Sina temperature:"+temperature);
        System.out.println("Sina pressure:"+pressure);
        System.out.println("Sina humidity"+humidity);
    }
}

public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        Observer baidu = new Baidu();
        Observer sina = new Sina();

        weatherData.registerObserver(baidu);
        weatherData.registerObserver(sina);

        weatherData.setData(35,150,20);
        weatherData.setData(33,111,25);
    }
}
