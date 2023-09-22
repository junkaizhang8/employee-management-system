/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.main;

/**
 *
 * @author junkaizhang
 */
public class PTE extends EmployeeInfo {
	
	
    private double hourlyWage;
    private int hoursPerWeek;
    private int weeksPerYear;
    private static String empType = "Part Time";


    public PTE(int empNum, String firstName, String lastName, double deductRate, double hourlyWage, int hoursPerWeek, int weeksPerYear) {
        super(empNum, firstName, lastName, deductRate);
        this.hourlyWage = hourlyWage;
        this.hoursPerWeek = hoursPerWeek;
        this.weeksPerYear = weeksPerYear;
    }


    public double calcNetAnnualIncome() {
        double netAnnualIncome = hourlyWage * hoursPerWeek * weeksPerYear * (1 - getDeductRate());
        return Math.round(netAnnualIncome * 100.0) / 100.0;
    }


    public String getEmpType() {
        return empType;
    }
    
    
    public double getHourlyWage() {
        return hourlyWage;
    }
    
    
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    
    
    public int getHoursPerWeek() {
        return hoursPerWeek;
    }
    
    
    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
    
    
    public int getWeeksPerYear() {
        return weeksPerYear;
    }
    
    
    public void setWeeksPerYear(int weeksPeryear) {
        this.weeksPerYear = weeksPeryear;
    }
}
