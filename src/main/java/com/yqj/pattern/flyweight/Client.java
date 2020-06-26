package com.yqj.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;
//外部状态，可以动态使用
class User{
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}

//抽象类
abstract class WebSite{
    public abstract void use(User user);
}

//具体实现
class ConcreteWebSite extends WebSite{
    //type 内部状态，共享部分
    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }
    //user 外部状态
    @Override
    public void use(User user) {
        System.out.println(user.getUsername() + "用户构建了"+ type + "形式的网站");
    }
}

//工厂类，根据需要返回一个网站
class WebSiteFactory{

    private  Map<String,WebSite> pool = new HashMap<>();

    public WebSite getWebSiteCategory(String type){
        if(!pool.containsKey(type)){
            pool.put(type,new ConcreteWebSite(type));
        }
        return pool.get(type);
    }

    public int getSize(){
        return pool.size();
    }
}

public class Client {
    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        WebSite webSite1 = webSiteFactory.getWebSiteCategory("新闻");
        webSite1.use(new User("alice"));
        WebSite webSite2 = webSiteFactory.getWebSiteCategory("博客");
        webSite2.use(new User("tom"));
        WebSite webSite3 = webSiteFactory.getWebSiteCategory("博客");
        webSite3.use(new User("bob"));
        System.out.println(webSiteFactory.getSize());
    }
}
