/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.main;

import javax.swing.table.*;
/**
 *
 * @author junkaizhang
 */
public class EmployeeSearchJFrame extends javax.swing.JFrame {
    
    
    private MyHashTable mainHT;
    private DefaultTableModel model;
    private int currentEmpNum;
    private EmployeeWriter theEmp;
    private String specialCharacters;

    /**
     * Creates new form EmployeeSearchJFrame
     */
    public EmployeeSearchJFrame() {
        initComponents();
        editButton.setVisible(false);
        removeButton.setVisible(false);
        resetButton.setVisible(false);
        fullTimeButton.setVisible(false);
        partTimeButton.setVisible(false);
        firstNameLabel.setVisible(false);
        firstNameInput.setVisible(false);
        lastNameLabel.setVisible(false);
        lastNameInput.setVisible(false);
        deductRateLabel.setVisible(false);
        deductRateInput.setVisible(false);
        yearlySalaryLabel.setVisible(false);
        yearlySalaryInput.setVisible(false);
        hourlyWageLabel.setVisible(false);
        hourlyWageInput.setVisible(false);
        hoursPerWeekLabel.setVisible(false);
        hoursPerWeekInput.setVisible(false);
        weeksPerYearLabel.setVisible(false);
        weeksPerYearInput.setVisible(false);
        errorLabel.setVisible(false);
        eventLabel.setVisible(false);
        confirmButton.setVisible(false);
        
        currentEmpNum = -1;
        
        specialCharacters = "|!@#$%^&*()?/{}:;<>`~+=\\\"\'";
    }
    
    
    public void setMainHT(MyHashTable refValForHT) {
        mainHT = refValForHT;
    }
    
    
    private void clearLabels() {
        errorLabel.setVisible(false);
        eventLabel.setVisible(false);
    }
    
    
    private void insertTable(EmployeeWriter employee) {
        EmployeeInfo employeeInfo = employee.getEmployeeInfo();

        model.setValueAt(employeeInfo.getEmpType(), 0, 0);
        model.setValueAt(employeeInfo.getEmpNum(), 0, 1);
        model.setValueAt(employeeInfo.getFirstName(), 0, 2);
        model.setValueAt(employeeInfo.getLastName(), 0, 3);
        model.setValueAt(employeeInfo.calcNetAnnualIncome(), 0, 4);
    }
    
    
    private void displayTable() {
        clearLabels();
        
        try {
            int empNumLength = empNumInput.getText().length();
            int theEmpNum = Integer.parseInt(empNumInput.getText());
            
            if (empNumLength != 6 || theEmpNum < 0 || empNumInput.getText().charAt(0) == '0') {
                errorLabel.setText("Employee number should a 6 digit number!");
                errorLabel.setVisible(true);
                empNumInput.setText("");
            }
            
            else {
                int bucketToGoInto = theEmpNum % mainHT.buckets.length;

                for (int i = 0; i < mainHT.buckets[bucketToGoInto].size(); i++) {
                    EmployeeWriter currentEmployee = mainHT.buckets[bucketToGoInto].get(i);

                    if (currentEmployee.getEmployeeInfo().getEmpNum() == theEmpNum) {
                        int numInHT = mainHT.getNumInHashTable();
                        currentEmpNum = theEmpNum;

                        model = new DefaultTableModel(new Object[] {"Status", 
                                                                    "Emp Num", 
                                                                    "First Name", 
                                                                    "Last Name", 
                                                                    "Net Yearly Salary"}, 
                                                                    numInHT);

                        empInfoTable.setModel(model);
                        empInfoTable.setAutoCreateColumnsFromModel(true);

                        insertTable(currentEmployee);
                        theEmp = currentEmployee;
                        
                        editButton.setVisible(true);
                        removeButton.setVisible(true);
                        resetButton.setVisible(true);
                        
                        return;
                    }
                }
                errorLabel.setText("Employee number " + theEmpNum + " does not exist!");
                errorLabel.setVisible(true);
            }
        }
        
        catch (Exception e) {
            errorLabel.setText("Employee number should be a 6 digit number!");
            errorLabel.setVisible(true);
            empNumInput.setText("");
        }
    }
    
    
    private void editTheEmployee() {
        
        clearLabels();
        
        if (currentEmpNum != -1) {
            fullTimeButton.setVisible(true);
            partTimeButton.setVisible(true);
            firstNameLabel.setVisible(true);
            firstNameInput.setVisible(true);
            lastNameLabel.setVisible(true);
            lastNameInput.setVisible(true);
            deductRateLabel.setVisible(true);
            deductRateInput.setVisible(true);
            confirmButton.setVisible(true);

            EmployeeInfo employeeInfo = theEmp.getEmployeeInfo();
            
            firstNameInput.setText(employeeInfo.getFirstName());
            lastNameInput.setText(employeeInfo.getLastName());
            deductRateInput.setText(String.valueOf(employeeInfo.getDeductRate()));
            
            if (employeeInfo instanceof FTE) {
                FTE theFTE = (FTE) employeeInfo;
                
                fullTimeButton.doClick();
                yearlySalaryLabel.setVisible(true);
                yearlySalaryInput.setVisible(true);
                
                yearlySalaryInput.setText(String.valueOf(theFTE.getYearlySalary()));
                hourlyWageInput.setText("");
                hoursPerWeekInput.setText("");
                weeksPerYearInput.setText("");
            }
            
            else if (employeeInfo instanceof PTE) {
                PTE thePTE = (PTE) employeeInfo;
                
                partTimeButton.doClick();
                hourlyWageLabel.setVisible(true);
                hourlyWageInput.setVisible(true);
                hoursPerWeekLabel.setVisible(true);
                hoursPerWeekInput.setVisible(true);
                weeksPerYearLabel.setVisible(true);
                weeksPerYearInput.setVisible(true);
                
                hourlyWageInput.setText(String.valueOf(thePTE.getHourlyWage()));
                hoursPerWeekInput.setText(String.valueOf(thePTE.getHoursPerWeek()));
                weeksPerYearInput.setText(String.valueOf(thePTE.getWeeksPerYear()));
                yearlySalaryInput.setText("");
            }
        }
    }
    
    
    private boolean updateTheEmployee() {
        
        boolean returnVal = false;
        
        String theFirstName = firstNameInput.getText();
        
        if (theFirstName.equals("")) {
            errorLabel.setText("First name should not be empty!");
            errorLabel.setVisible(true);
            firstNameInput.setText("");

            return returnVal;
        }
                    
        else {
            char[] firstNameArray = theFirstName.toCharArray();

            for (char c : firstNameArray) {

                if (Character.isDigit(c)) {
                    errorLabel.setText("First name should not include " + c + "!");
                    errorLabel.setVisible(true);
                    firstNameInput.setText("");

                    return returnVal;
                }
            }

            for (int i = 0; i < theFirstName.length(); i++) {

                if (specialCharacters.contains(theFirstName.substring(i, i + 1))) {
                    errorLabel.setText("First name should not include " + theFirstName.charAt(i) + "!");
                    errorLabel.setVisible(true);
                    firstNameInput.setText("");

                    return returnVal;
                }
            }
        }

        String theLastName = lastNameInput.getText();

        if (theLastName.equals("")) {
            errorLabel.setText("Last name should not be empty!");
            errorLabel.setVisible(true);
            lastNameInput.setText("");

            return returnVal;
        }

        else {
            char[] lastNameArray = theLastName.toCharArray();

            for (char c : lastNameArray) {

                if (Character.isDigit(c)) {
                    errorLabel.setText("Last name should not include " + c + "!");
                    errorLabel.setVisible(true);
                    lastNameInput.setText("");

                    return returnVal;
                }
            }

            for (int i = 0; i < theLastName.length(); i++) {

                if (specialCharacters.contains(theLastName.substring(i, i + 1))) {
                    errorLabel.setText("Last name should not include " + theLastName.charAt(i) + "!");
                    errorLabel.setVisible(true);
                    lastNameInput.setText("");

                    return returnVal;
                }
            }
        }

        try {
            double theDeductRate = Double.parseDouble(deductRateInput.getText());
            
            if (theDeductRate < 0 || theDeductRate > 1) {
                errorLabel.setText("Deduct rate should be a number from 0 to 1!");
                errorLabel.setVisible(true);
                deductRateInput.setText("");
            }
            
            else {

                if (fullTimeButton.isSelected()) {

                    try {
                        double theYearlySalary = Double.parseDouble(yearlySalaryInput.getText());
                        
                        if (theYearlySalary < 0) {
                            errorLabel.setText("Yearly salary should be a positive number!");
                            errorLabel.setVisible(true);
                            yearlySalaryInput.setText("");
                        }
                        
                        else {
                            mainHT.removeEmployee(currentEmpNum);

                            FTEWriter theFTE = new FTEWriter(new FTE(currentEmpNum, theFirstName, theLastName, theDeductRate, theYearlySalary));

                            mainHT.addEmployee(theFTE);
                            insertTable(theFTE);
                            theEmp = theFTE;

                            empNumInput.setText("");
                            firstNameInput.setText("");
                            lastNameInput.setText("");
                            deductRateInput.setText("");
                            yearlySalaryInput.setText("");

                            errorLabel.setVisible(false);

                            eventLabel.setText("Employee edited!");
                            eventLabel.setVisible(true);
                            
                            returnVal = true;
                        }
                    }

                    catch (Exception e) {
                        errorLabel.setText("Yearly salary should be a positive number!");
                        errorLabel.setVisible(true);
                        yearlySalaryInput.setText("");
                    }
                }

                else if (partTimeButton.isSelected()) {

                    try {
                        double theHourlyWage = Double.parseDouble(hourlyWageInput.getText());
                        
                        if (theHourlyWage < 0) {
                            errorLabel.setText("Hourly wage should be a positive number!");
                            errorLabel.setVisible(true);
                            hourlyWageInput.setText("");
                        }
                        
                        else {
                            
                            try {
                                int theHoursPerWeek = Integer.parseInt(hoursPerWeekInput.getText());
                                
                                if (theHoursPerWeek < 0 || theHoursPerWeek > 168) {
                                    errorLabel.setText("Hours per week should be a number from 0 to 168!");
                                    errorLabel.setVisible(true);
                                    hoursPerWeekInput.setText("");
                                }
                                
                                else {
                                    
                                    try {
                                        int theWeeksPerYear = Integer.parseInt(weeksPerYearInput.getText());
                                        
                                        if (theWeeksPerYear < 0 || theWeeksPerYear > 52) {
                                            errorLabel.setText("Weeks per year should be a number from 0 to 52!");
                                            errorLabel.setVisible(true);
                                            weeksPerYearInput.setText("");
                                        }
                                        
                                        else {
                                            mainHT.removeEmployee(currentEmpNum);
                                            
                                            PTEWriter thePTE = new PTEWriter(new PTE(currentEmpNum, theFirstName, theLastName, theDeductRate, theHourlyWage, theHoursPerWeek, theWeeksPerYear));

                                            mainHT.addEmployee(thePTE);
                                            insertTable(thePTE);
                                            theEmp = thePTE;

                                            empNumInput.setText("");
                                            firstNameInput.setText("");
                                            lastNameInput.setText("");
                                            deductRateInput.setText("");
                                            hourlyWageInput.setText("");
                                            hoursPerWeekInput.setText("");
                                            weeksPerYearInput.setText("");

                                            errorLabel.setVisible(false);

                                            eventLabel.setText("Employee edited!");
                                            eventLabel.setVisible(true);
                                            
                                            returnVal = true;
                                        }
                                    }

                                    catch (Exception e) {
                                        errorLabel.setText("Weeks per year should be a number from 0 to 52!");
                                        errorLabel.setVisible(true);
                                        weeksPerYearInput.setText("");
                                    }
                                }
                            }

                            catch (Exception e) {
                                errorLabel.setText("Hours per week should be a number from 0 to 168!");
                                errorLabel.setVisible(true);
                                hoursPerWeekInput.setText("");
                            }
                        }
                    }

                    catch (Exception e) {
                        errorLabel.setText("Hourly wage should be a positive number!");
                        errorLabel.setVisible(true);
                        hourlyWageInput.setText("");
                    }
                }
            }
        }

        catch (Exception e) {
            errorLabel.setText("Deduct rate should be a number from 0 to 1!");
            errorLabel.setVisible(true);
            deductRateInput.setText("");
        }
        
        return returnVal;
    }
    
    
    private void removeTheEmployee() {
        
        clearLabels();
        
        if (currentEmpNum != -1) {
            EmployeeWriter removedEmp = mainHT.removeEmployee(currentEmpNum);
            model.removeRow(0);
            eventLabel.setText(removedEmp.getEmployeeInfo().getFirstName() + " " + removedEmp.getEmployeeInfo().getLastName() + " removed!");
            currentEmpNum = -1;
            
            eventLabel.setVisible(true);
            editButton.setVisible(false);
            removeButton.setVisible(false);
            resetButton.setVisible(false);
            fullTimeButton.setVisible(false);
            partTimeButton.setVisible(false);
            firstNameLabel.setVisible(false);
            firstNameInput.setVisible(false);
            lastNameLabel.setVisible(false);
            lastNameInput.setVisible(false);
            deductRateLabel.setVisible(false);
            deductRateInput.setVisible(false);
            yearlySalaryLabel.setVisible(false);
            yearlySalaryInput.setVisible(false);
            hourlyWageLabel.setVisible(false);
            hourlyWageInput.setVisible(false);
            hoursPerWeekLabel.setVisible(false);
            hoursPerWeekInput.setVisible(false);
            weeksPerYearLabel.setVisible(false);
            weeksPerYearInput.setVisible(false);
            confirmButton.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        empNumLabel = new javax.swing.JLabel();
        empNumInput = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        empInfoTable = new javax.swing.JTable();
        resetButton = new javax.swing.JButton();
        firstNameLabel = new javax.swing.JLabel();
        firstNameInput = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameInput = new javax.swing.JTextField();
        deductRateLabel = new javax.swing.JLabel();
        deductRateInput = new javax.swing.JTextField();
        fullTimeButton = new javax.swing.JRadioButton();
        partTimeButton = new javax.swing.JRadioButton();
        yearlySalaryLabel = new javax.swing.JLabel();
        hourlyWageLabel = new javax.swing.JLabel();
        hoursPerWeekLabel = new javax.swing.JLabel();
        weeksPerYearLabel = new javax.swing.JLabel();
        yearlySalaryInput = new javax.swing.JTextField();
        hourlyWageInput = new javax.swing.JTextField();
        hoursPerWeekInput = new javax.swing.JTextField();
        weeksPerYearInput = new javax.swing.JTextField();
        removeButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        eventLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();

        empNumLabel.setText("Enter Employee Num:");

        empNumInput.setMinimumSize(new java.awt.Dimension(100, 23));
        empNumInput.setPreferredSize(new java.awt.Dimension(100, 23));
        empNumInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empNumInputActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        empInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Status", "Emp Num", "First Name", "Last Name", "Net Annual Income"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(empInfoTable);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        firstNameLabel.setText("First Name:");

        firstNameInput.setMinimumSize(new java.awt.Dimension(100, 23));
        firstNameInput.setPreferredSize(new java.awt.Dimension(100, 23));

        lastNameLabel.setText("Last Name:");

        lastNameInput.setMinimumSize(new java.awt.Dimension(100, 23));
        lastNameInput.setPreferredSize(new java.awt.Dimension(100, 23));
        lastNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameInputActionPerformed(evt);
            }
        });

        deductRateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        deductRateLabel.setText("Deduct Rate:");

        deductRateInput.setMinimumSize(new java.awt.Dimension(100, 23));
        deductRateInput.setPreferredSize(new java.awt.Dimension(100, 23));

        buttonGroup1.add(fullTimeButton);
        fullTimeButton.setText("Full-Time");
        fullTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullTimeButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(partTimeButton);
        partTimeButton.setText("Part-Time");
        partTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partTimeButtonActionPerformed(evt);
            }
        });

        yearlySalaryLabel.setText("Yearly Salary:");

        hourlyWageLabel.setText("Hourly Wage:");

        hoursPerWeekLabel.setText("Hours Per Week:");

        weeksPerYearLabel.setText("Weeks Per Year:");

        yearlySalaryInput.setMinimumSize(new java.awt.Dimension(100, 23));
        yearlySalaryInput.setPreferredSize(new java.awt.Dimension(100, 23));

        hourlyWageInput.setMinimumSize(new java.awt.Dimension(100, 23));
        hourlyWageInput.setPreferredSize(new java.awt.Dimension(100, 23));
        hourlyWageInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourlyWageInputActionPerformed(evt);
            }
        });

        hoursPerWeekInput.setMinimumSize(new java.awt.Dimension(100, 23));
        hoursPerWeekInput.setPreferredSize(new java.awt.Dimension(100, 23));

        weeksPerYearInput.setMinimumSize(new java.awt.Dimension(100, 23));
        weeksPerYearInput.setPreferredSize(new java.awt.Dimension(100, 23));

        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        errorLabel.setBackground(new java.awt.Color(250, 184, 184));
        errorLabel.setForeground(new java.awt.Color(160, 33, 33));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel.setText("Error Label");
        errorLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(160, 33, 33)));
        errorLabel.setOpaque(true);

        eventLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eventLabel.setText("Event Label");

        confirmButton.setText("Confirm Change");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(empNumLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empNumInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetButton))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(yearlySalaryLabel)
                                .addGap(18, 18, 18)
                                .addComponent(yearlySalaryInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lastNameLabel)
                                    .addComponent(firstNameLabel)
                                    .addComponent(deductRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lastNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deductRateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partTimeButton)
                            .addComponent(fullTimeButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hourlyWageLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hoursPerWeekLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(weeksPerYearLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hoursPerWeekInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourlyWageInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weeksPerYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(confirmButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(eventLabel)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empNumLabel)
                    .addComponent(empNumInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton)
                    .addComponent(resetButton)
                    .addComponent(removeButton)
                    .addComponent(searchButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(eventLabel)
                        .addGap(26, 26, 26)
                        .addComponent(confirmButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fullTimeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(partTimeButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(errorLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(firstNameLabel)
                                    .addComponent(firstNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lastNameLabel)
                                    .addComponent(lastNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(deductRateLabel)
                                    .addComponent(deductRateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hourlyWageLabel)
                                    .addComponent(hourlyWageInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hoursPerWeekLabel)
                                    .addComponent(hoursPerWeekInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(weeksPerYearLabel)
                                    .addComponent(weeksPerYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yearlySalaryLabel)
                            .addComponent(yearlySalaryInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(85, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void empNumInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empNumInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empNumInputActionPerformed

    private void lastNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameInputActionPerformed

    private void hourlyWageInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourlyWageInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourlyWageInputActionPerformed

    private void fullTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullTimeButtonActionPerformed
        // TODO add your handling code here:
        yearlySalaryLabel.setVisible(true);
        yearlySalaryInput.setVisible(true);
        hourlyWageLabel.setVisible(false);
        hourlyWageInput.setVisible(false);
        hoursPerWeekLabel.setVisible(false);
        hoursPerWeekInput.setVisible(false);
        weeksPerYearLabel.setVisible(false);
        weeksPerYearInput.setVisible(false);
    }//GEN-LAST:event_fullTimeButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        displayTable();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        editTheEmployee();
    }//GEN-LAST:event_editButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        removeTheEmployee();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        editButton.setVisible(false);
        removeButton.setVisible(false);
        resetButton.setVisible(false);
        fullTimeButton.setVisible(false);
        partTimeButton.setVisible(false);
        firstNameLabel.setVisible(false);
        firstNameInput.setVisible(false);
        lastNameLabel.setVisible(false);
        lastNameInput.setVisible(false);
        deductRateLabel.setVisible(false);
        deductRateInput.setVisible(false);
        yearlySalaryLabel.setVisible(false);
        yearlySalaryInput.setVisible(false);
        hourlyWageLabel.setVisible(false);
        hourlyWageInput.setVisible(false);
        hoursPerWeekLabel.setVisible(false);
        hoursPerWeekInput.setVisible(false);
        weeksPerYearLabel.setVisible(false);
        weeksPerYearInput.setVisible(false);
        errorLabel.setVisible(false);
        eventLabel.setVisible(false);
        confirmButton.setVisible(false);
        
        empNumInput.setText("");
        
        model.removeRow(0);
        
        currentEmpNum = -1;
    }//GEN-LAST:event_resetButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        // TODO add your handling code here:
        if (updateTheEmployee()) {
            fullTimeButton.setVisible(false);
            partTimeButton.setVisible(false);
            firstNameLabel.setVisible(false);
            firstNameInput.setVisible(false);
            lastNameLabel.setVisible(false);
            lastNameInput.setVisible(false);
            deductRateLabel.setVisible(false);
            deductRateInput.setVisible(false);
            yearlySalaryLabel.setVisible(false);
            yearlySalaryInput.setVisible(false);
            hourlyWageLabel.setVisible(false);
            hourlyWageInput.setVisible(false);
            hoursPerWeekLabel.setVisible(false);
            hoursPerWeekInput.setVisible(false);
            weeksPerYearLabel.setVisible(false);
            weeksPerYearInput.setVisible(false);
            confirmButton.setVisible(false);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void partTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partTimeButtonActionPerformed
        // TODO add your handling code here:
        hourlyWageLabel.setVisible(true);
        hourlyWageInput.setVisible(true);
        hoursPerWeekLabel.setVisible(true);
        hoursPerWeekInput.setVisible(true);
        weeksPerYearLabel.setVisible(true);
        weeksPerYearInput.setVisible(true);
        yearlySalaryLabel.setVisible(false);
        yearlySalaryInput.setVisible(false);
    }//GEN-LAST:event_partTimeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeSearchJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeSearchJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeSearchJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeSearchJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeSearchJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextField deductRateInput;
    private javax.swing.JLabel deductRateLabel;
    private javax.swing.JButton editButton;
    private javax.swing.JTable empInfoTable;
    private javax.swing.JTextField empNumInput;
    private javax.swing.JLabel empNumLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel eventLabel;
    private javax.swing.JTextField firstNameInput;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JRadioButton fullTimeButton;
    private javax.swing.JTextField hourlyWageInput;
    private javax.swing.JLabel hourlyWageLabel;
    private javax.swing.JTextField hoursPerWeekInput;
    private javax.swing.JLabel hoursPerWeekLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastNameInput;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JRadioButton partTimeButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField weeksPerYearInput;
    private javax.swing.JLabel weeksPerYearLabel;
    private javax.swing.JTextField yearlySalaryInput;
    private javax.swing.JLabel yearlySalaryLabel;
    // End of variables declaration//GEN-END:variables
}
