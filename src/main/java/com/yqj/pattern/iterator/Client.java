package com.yqj.pattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Department {
    private String name;
    private String desc;

    public Department(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


class ComputerCollegeIterator implements Iterator<Department> {

    private Department[] departments;
    int position = 0;

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }


    @Override
    public boolean hasNext() {
        if (position >= departments.length || departments[position] == null) {
            return false;
        }
        return true;
    }

    @Override
    public Department next() {
        return departments[position++];

    }
}

class InfoCollegeIterator implements Iterator<Department> {

    private List<Department> departments;
    private int index = -1;

    public InfoCollegeIterator(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if (index >= departments.size() - 1) {
            return false;
        } else {
            index++;
            return true;
        }
    }

    @Override
    public Department next() {
        return departments.get(index);
    }
}

interface College {
    String getName();
    //增加系的方法
    void addDepartment(String name, String desc);
    //返回迭代器遍历
    Iterator createIterator();
}

class ComputerCollege implements College {
    private Department[] departments;
    //保存当前数组的对象个数
    private int numOfDepartment = 0;

    public ComputerCollege() {
        departments = new Department[5];
        addDepartment("Java","Java desc");
        addDepartment("C++","C++ desc");
        addDepartment("Python","Python desc");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Department department = new Department(name, desc);
        departments[numOfDepartment++] = department;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(departments);
    }
}

class InfoCollege implements College{

    List<Department> departments;

    public InfoCollege() {
        departments = new ArrayList<>();
        addDepartment("信息安全","信息安全");
        addDepartment("网络安全","网络安全");
        addDepartment("服务器安全","服务器安全");
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        departments.add(new Department(name,desc));
    }

    @Override
    public Iterator createIterator() {
        return new InfoCollegeIterator(departments);
    }
}

//遍历类
class OutputImpl{
    private List<College> colleges;

    public OutputImpl(List<College> colleges) {
        this.colleges = colleges;
    }

    public void printCollege(){
        Iterator<College> iterator = colleges.iterator();
        while (iterator.hasNext()){
            College college = iterator.next();
            System.out.println("========="+college.getName()+"========");
            printDepartment(college.createIterator());
        }
    }

    public void printDepartment(Iterator<Department> iterator){
        while (iterator.hasNext()){
            Department department = iterator.next();
            System.out.println(department.getName());
        }
    }
}

public class Client {
    public static void main(String[] args) {
        List<College> colleges = new ArrayList<>();
        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();
        colleges.add(computerCollege);
        colleges.add(infoCollege);
        OutputImpl output = new OutputImpl(colleges);
        output.printCollege();
    }
}
