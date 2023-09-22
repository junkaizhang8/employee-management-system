package edu.main;

public class PTEWriter extends EmployeeWriter {


    public PTEWriter() {}

    
    public PTEWriter(PTE employeeInfo) {
        setEmployeeInfo(employeeInfo);
    }


    public String getFormattedEmpInfo() {
        PTE theEmp = (PTE) getEmployeeInfo();
        String className = String.valueOf(this.getClass()).split(" ")[1];
        int empNum = theEmp.getEmpNum();
        String firstName = theEmp.getFirstName();
        String lastName = theEmp.getLastName();
        double deductRate = theEmp.getDeductRate();
        double hourlyWage = theEmp.getHourlyWage();
        int hoursPerWeek = theEmp.getHoursPerWeek();
        int weeksPerYear = theEmp.getWeeksPerYear();
        return String.format("%s|%d|%s|%s|%f|%f|%d|%d", className, empNum, firstName, lastName,
                             deductRate, hourlyWage, hoursPerWeek, weeksPerYear);
    }


    public void readFormattedEmployeeInfo(String[] format) {
        int empNum = Integer.parseInt(format[1]);
        String firstName = format[2];
        String lastName = format[3];
        double deductRate = Double.parseDouble(format[4]);
        double hourlyWage = Double.parseDouble(format[5]);
        int hoursPerWeek = Integer.parseInt(format[6]);
        int weeksPerYear = Integer.parseInt(format[7]);
        setEmployeeInfo(new PTE(empNum, firstName, lastName, deductRate, hourlyWage, hoursPerWeek, weeksPerYear));
    }
}
