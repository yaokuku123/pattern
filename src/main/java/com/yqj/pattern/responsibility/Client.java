package com.yqj.pattern.responsibility;

class PurchaseRequest{
    private Integer id;
    private Float price;

    public PurchaseRequest(Integer id, Float price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Float getPrice() {
        return price;
    }
}

//抽象类，定义处理请求的审批人
abstract class Approver{
    protected Approver approver;
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void processRequest(PurchaseRequest request);
}

class DepartmentApprover extends Approver{

    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() <= 5000){
            System.out.println("请求编号："+request.getId()
                    +" 金额："+request.getPrice()+"被"+this.name+"处理");
        }else {
            approver.processRequest(request);
        }
    }
}

class CollegeApprover extends Approver{

    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() <= 10000 && request.getPrice() > 5000){
            System.out.println("请求编号："+request.getId()
                    +" 金额："+request.getPrice()+"被"+this.name+"处理");
        }else {
            approver.processRequest(request);
        }
    }
}

class ViceSchoolMasterApprover extends Approver{

    public ViceSchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() <= 30000 && request.getPrice() > 10000){
            System.out.println("请求编号："+request.getId()
                    +" 金额："+request.getPrice()+"被"+this.name+"处理");
        }else {
            approver.processRequest(request);
        }
    }
}

class SchoolMasterApprover extends Approver{

    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() > 30000){
            System.out.println("请求编号："+request.getId()
                    +" 金额："+request.getPrice()+"被"+this.name+"处理");
        }else {
            approver.processRequest(request);
        }
    }
}


public class Client {
    public static void main(String[] args) {
        //创建请求
        PurchaseRequest request = new PurchaseRequest(233, 6000.0f);
        //创建审批人
        Approver departmentApprover = new DepartmentApprover("系主任");
        Approver collegeApprover = new CollegeApprover("院长");
        Approver viceSchoolMasterApprover = new ViceSchoolMasterApprover("副校长");
        Approver schoolMasterApprover = new SchoolMasterApprover("校长");
        //设置各级审批人下一个审批人(构成环形)
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolMasterApprover);
        viceSchoolMasterApprover.setApprover(schoolMasterApprover);
        schoolMasterApprover.setApprover(departmentApprover);

        departmentApprover.processRequest(request);
        viceSchoolMasterApprover.processRequest(request);

    }
}
