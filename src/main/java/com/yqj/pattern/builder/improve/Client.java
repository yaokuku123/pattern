package com.yqj.pattern.builder.improve;

//产品
class House{
    private String basic;
    private String wall;
    private String roofed;

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getRoofed() {
        return roofed;
    }

    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }

    @Override
    public String toString() {
        return "House{" +
                "basic='" + basic + '\'' +
                ", wall='" + wall + '\'' +
                ", roofed='" + roofed + '\'' +
                '}';
    }
}

//抽象建造者
abstract class HouseBuilder{
    protected House house = new House();
    //建造流程，定义有关的抽象方法
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();
    //建造好房子，返回产品
    public House buildHouse(){
        return house;
    }
}

//具体建造者1
class CommonHouseBuilder extends HouseBuilder{
    //实现具体的构建方法
    @Override
    public void buildBasic() {
        house.setBasic("普通房子打地基");
    }

    @Override
    public void buildWalls() {
        house.setWall("普通房子刷墙");
    }

    @Override
    public void roofed() {
        house.setRoofed("普通房子封顶");
    }
}

//具体建造者2
class HighBuilding extends HouseBuilder{

    @Override
    public void buildBasic() {
        house.setBasic("别墅打地基");
    }

    @Override
    public void buildWalls() {
        house.setWall("别墅刷墙");
    }

    @Override
    public void roofed() {
        house.setRoofed("别墅封顶");
    }
}



//指挥者
class HouseDirector{
    HouseBuilder houseBuilder;

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //制定产品的构建流程
    public House build(){
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        //返回产品
        return houseBuilder.buildHouse();
    }
}

//用户
public class Client{
    public static void main(String[] args) {
        HouseDirector director = new HouseDirector(new CommonHouseBuilder());
        System.out.println(director.build());
        //更改指挥者的构建产品
        director.setHouseBuilder(new HighBuilding());
        System.out.println(director.build());
    }
}