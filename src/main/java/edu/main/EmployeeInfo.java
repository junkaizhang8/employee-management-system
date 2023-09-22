/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.main;

/**
 *
 * @author junkaizhang
 */
public abstract class EmployeeInfo {
	

    private int empNum;
    private String firstName;
    private String lastName;
    private double deductRate;


    public EmployeeInfo(int empNum, String firstName, String lastName, double deductRate) {
        this.empNum = empNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deductRate = deductRate;
    }


    public abstract double calcNetAnnualIncome();


    public abstract String getEmpType();
    
    
    public int getEmpNum() {
        return empNum;
    }
    
    
    public void setEmpNum(int empNum) {
        this.empNum = empNum;
    }
    
    
    public String getFirstName() {
        return firstName;
    }
    
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    
    public String getLastName() {
        return lastName;
    }
    
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
    public double getDeductRate() {
        return deductRate;
    }
    
    
    public void setDeductRate(double deductRate) {
        this.deductRate = deductRate;
    }

}
