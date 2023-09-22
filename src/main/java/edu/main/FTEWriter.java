package edu.main;

public class FTEWriter extends EmployeeWriter {


    public FTEWriter() {}
    

    public FTEWriter(FTE employeeInfo) {
        setEmployeeInfo(employeeInfo);
    }


    public String getFormattedEmpInfo() {
        FTE theEmp = (FTE) getEmployeeInfo();
        String className = String.valueOf(this.getClass()).split(" ")[1];
        int empNum = theEmp.getEmpNum();
        String firstName = theEmp.getFirstName();
        String lastName = theEmp.getLastName();
        double deductRate = theEmp.getDeductRate();
        double yearlySalary = theEmp.getYearlySalary();
        return String.format("%s|%d|%s|%s|%f|%f", className, empNum, firstName, lastName,
                             deductRate, yearlySalary);
    }


    public void readFormattedEmployeeInfo(String[] format) {
        int empNum = Integer.parseInt(format[1]);
        String firstName = format[2];
        String lastName = format[3];
        double deductRate = Double.parseDouble(format[4]);
        double yearlySalary = Double.parseDouble(format[5]);
        setEmployeeInfo(new FTE(empNum, firstName, lastName, deductRate, yearlySalary));
    }
}