package com.yqj.pattern.composite;

import java.util.ArrayList;
import java.util.List;

abstract class OrganizationComponent {
    private String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    public abstract void print();
}

class University extends OrganizationComponent {
    private List<OrganizationComponent> colleges = new ArrayList<>();

    public University(String name) {
        super(name);
    }


    @Override
    public void add(OrganizationComponent organizationComponent) {
        colleges.add(organizationComponent);
    }

    @Override
    public void remove(OrganizationComponent organizationComponent) {
        colleges.remove(organizationComponent);
    }

    @Override
    public void print() {
        System.out.println("++++++++++"+this.getName()+"++++++++++");
        for (OrganizationComponent college : colleges) {
            college.print();
        }
    }
}

class College extends OrganizationComponent {
    private List<OrganizationComponent> departments = new ArrayList<>();

    public College(String name) {
        super(name);
    }

    @Override
    public void add(OrganizationComponent organizationComponent) {
        departments.add(organizationComponent);
    }

    @Override
    public void remove(OrganizationComponent organizationComponent) {
        departments.remove(organizationComponent);
    }

    @Override
    public void print() {
        System.out.println("++++++++++"+this.getName()+"++++++++++");
        for (OrganizationComponent department : departments) {
            department.print();
        }
    }
}

class Department extends OrganizationComponent{

    public Department(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println(this.getName());
    }
}

public class Client {
    public static void main(String[] args) {
        //从大到小创建对象
        //创建学校
        University university = new University("首师大");
        //创建学院
        College computerCollege = new College("计算机学院");
        College chemcialCollege = new College("化学学院");
        //创建系
        Department department1 = new Department("软件工程");
        Department department2 = new Department("计算机科学与技术");
        Department department3 = new Department("通讯工程");
        Department department4 = new Department("化学师范");
        Department department5 = new Department("应用化学");
        //将系加入各学院
        computerCollege.add(department1);
        computerCollege.add(department2);
        computerCollege.add(department3);
        chemcialCollege.add(department4);
        chemcialCollege.add(department5);
        //将学院加入学校
        university.add(computerCollege);
        university.add(chemcialCollege);
        //移除一个系
        chemcialCollege.remove(department4);
        //打印
        university.print();

    }
}
