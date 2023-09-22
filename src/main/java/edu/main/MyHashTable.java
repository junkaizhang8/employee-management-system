/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.main;

/**
 *
 * @author junkaizhang
 */
import java.util.*;
        
public class MyHashTable {
    
    
    public ArrayList<EmployeeWriter>[] buckets;
    public int numInHashTable;
	
	
    public MyHashTable(int howManyBuckets) {
        buckets = new ArrayList[howManyBuckets];
		
        for (int i = 0; i < howManyBuckets; i++) {
            buckets[i] = new ArrayList<>();
            }
		
        numInHashTable = 0;
	}
	
	
    public int getNumInHashTable() {
        return numInHashTable;
    }
	
	
    public int calcBucket(int keyValue) {
        return (keyValue % buckets.length);
    }
	
	
    public void addEmployee(EmployeeWriter theEmployee) {
        int bucketToGoInto = theEmployee.getEmployeeInfo().getEmpNum() % buckets.length;

        buckets[bucketToGoInto].add(theEmployee);

        Collections.sort(buckets[bucketToGoInto], new Comparator<EmployeeWriter>(){
            public int compare(EmployeeWriter emp1, EmployeeWriter emp2){
                return emp1.getEmployeeInfo().getEmpNum() - emp2.getEmployeeInfo().getEmpNum();
            }
        });
        
        numInHashTable += 1;
    }


    public EmployeeWriter removeEmployee(int employeeNum) {
        int bucketToGoInto = employeeNum % buckets.length;

        for (int i = 0; i < buckets[bucketToGoInto].size(); i++) {
            EmployeeWriter currentEmployee = buckets[bucketToGoInto].get(i);

            if (currentEmployee.getEmployeeInfo().getEmpNum() == employeeNum) {
                buckets[bucketToGoInto].remove(i);
                numInHashTable -= 1;
                
                return currentEmployee;
            }
        }

        System.out.println("Employee does not exist!");
        return null;
    }


    public int searchByEmployeeNumber(int employeeNum) {
        int bucketToGoInto = employeeNum % buckets.length;

        for (int i = 0; i < buckets[bucketToGoInto].size(); i++) {
            EmployeeWriter currentEmployee = buckets[bucketToGoInto].get(i);

            if (currentEmployee.getEmployeeInfo().getEmpNum() == employeeNum) {
                return bucketToGoInto;
            }
        }

        return -1;
    }


    public void getEmployeeWriter(int employeeNum) {
        double netAnnualIncome = 0;
        int bucketToGoInto = employeeNum % buckets.length;

        for (int i = 0; i < buckets[bucketToGoInto].size(); i++) {
            EmployeeWriter currentEmployee = buckets[bucketToGoInto].get(i);

            if (currentEmployee.getEmployeeInfo().getEmpNum() == employeeNum) {
                netAnnualIncome = currentEmployee.getEmployeeInfo().calcNetAnnualIncome();
                System.out.println(currentEmployee.getEmployeeInfo().getEmpNum() + ", " + currentEmployee.getEmployeeInfo().getFirstName() + ", " + currentEmployee.getEmployeeInfo().getLastName() + ", " + netAnnualIncome);
                return;
            }
        }

        System.out.println("Employee does not exist!");
    }


    public void displayContents() {

        for (int i = 0; i < buckets.length; i++) {
            ArrayList<EmployeeWriter> currentBucket = buckets[i];
            System.out.println("Bucket " + i + ":");

            for (int j = 0; j < buckets[i].size(); j++) {
                EmployeeWriter currentEmployee = currentBucket.get(j);
                System.out.println(currentEmployee.getEmployeeInfo().getEmpNum() + ", " + currentEmployee.getEmployeeInfo().getFirstName() + ", " + currentEmployee.getEmployeeInfo().getLastName());
            }

            System.out.println("-------------------------------");
        }
    }

}

