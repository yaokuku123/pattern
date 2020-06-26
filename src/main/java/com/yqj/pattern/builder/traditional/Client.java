package com.yqj.pattern.builder.traditional;

abstract class AbstractHouse {
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();
    public void build(){
        buildBasic();
        buildWalls();
        roofed();
    }
}

class CommonHouse extends AbstractHouse{

    @Override
    public void buildBasic() {
        System.out.println("普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通房子刷墙");
    }

    @Override
    public void roofed() {
        System.out.println("普通房子封顶");
    }
}

class HighBuilding extends AbstractHouse{

    @Override
    public void buildBasic() {
        System.out.println("别墅打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("别墅刷墙");
    }

    @Override
    public void roofed() {
        System.out.println("别墅封顶");
    }
}

public class Client{
    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        commonHouse.build();

        HighBuilding highBuilding = new HighBuilding();
        highBuilding.build();
    }
}