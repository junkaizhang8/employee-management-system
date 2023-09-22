package edu.main;

public abstract class EmployeeWriter {


    private EmployeeInfo employeeInfo;


    public abstract String getFormattedEmpInfo();


    public abstract void readFormattedEmployeeInfo(String[] format);


    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }


    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }
}
