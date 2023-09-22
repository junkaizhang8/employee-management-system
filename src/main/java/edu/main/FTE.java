/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.main;

/**
 *
 * @author junkaizhang
 */
public class FTE extends EmployeeInfo {
	
	
    private double yearlySalary;
    private static String empType = "Full Time";


    public FTE(int empNum, String firstName, String lastName, double deductRate, double yearlySalary) {
        super(empNum, firstName, lastName, deductRate);
        this.yearlySalary = yearlySalary;
    }


    public double calcNetAnnualIncome() {
        double netAnnualIncome = yearlySalary * (1 - getDeductRate());
        return Math.round(netAnnualIncome * 100.0) / 100.0;
    }


    public String getEmpType() {
        return empType;
    }
    
    
    public double getYearlySalary() {
        return yearlySalary;
    }
    
    
    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }
}
