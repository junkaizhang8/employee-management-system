/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.main;

/**
 *
 * @author junkaizhang
 */
public class AddNewEmployeeJFrame extends javax.swing.JFrame {
    
    
    private MyHashTable mainHT;
    private String specialCharacters;

    /**
     * Creates new form AddNewEmployeeJFrame
     */
    public AddNewEmployeeJFrame() {
        initComponents();
        yearlySalaryLabel.setVisible(false);
        yearlySalaryInput.setVisible(false);
        hourlyWageLabel.setVisible(false);
        hourlyWageInput.setVisible(false);
        hoursPerWeekLabel.setVisible(false);
        hoursPerWeekInput.setVisible(false);
        weeksPerYearLabel.setVisible(false);
        weeksPerYearInput.setVisible(false);
        errorLabel.setVisible(false);
        empAddedLabel.setVisible(false);
        
        specialCharacters = "|!@#$%^&*()?/{}:;<>`~+=\\\"\'";
    }
    
    
    public void setMainHT(MyHashTable refValForHT) {
        mainHT = refValForHT;
    }
    
    
    private void addTheNewEmployee(java.awt.event.ActionEvent evt) {
        
        try {
            int empNumLength = empNumInput.getText().length();
            int theEmpNum = Integer.parseInt(empNumInput.getText());
            
            if (empNumLength != 6 || theEmpNum < 0 || empNumInput.getText().charAt(0) == '0') {
                errorLabel.setText("Employee number should be a 6 digit number!");
                errorLabel.setVisible(true);
                empNumInput.setText("");
            }
            
            else {
                
                if (mainHT.searchByEmployeeNumber(theEmpNum) == theEmpNum % mainHT.buckets.length) {
                    errorLabel.setText("Employee number " + theEmpNum + " already in use!");
                    errorLabel.setVisible(true);
                    empNumInput.setText("");
                }
                
                else {
                    String theFirstName = firstNameInput.getText();
                    
                    if (theFirstName.equals("")) {
                        errorLabel.setText("First name should not be empty!");
                        errorLabel.setVisible(true);
                        firstNameInput.setText("");
                        
                        return;
                    }
                    
                    else {
                        char[] firstNameArray = theFirstName.toCharArray();

                        for (char c : firstNameArray) {

                            if (Character.isDigit(c)) {
                                errorLabel.setText("First name should not include " + c + "!");
                                errorLabel.setVisible(true);
                                firstNameInput.setText("");

                                return;
                            }
                        }

                        for (int i = 0; i < theFirstName.length(); i++) {

                            if (specialCharacters.contains(theFirstName.substring(i, i + 1))) {
                                errorLabel.setText("First name should not include " + theFirstName.charAt(i) + "!");
                                errorLabel.setVisible(true);
                                firstNameInput.setText("");

                                return;
                            }
                        }
                    }
                    
                    String theLastName = lastNameInput.getText();
                    
                    if (theLastName.equals("")) {
                        errorLabel.setText("Last name should not be empty!");
                        errorLabel.setVisible(true);
                        lastNameInput.setText("");
                        
                        return;
                    }
                    
                    else {
                        char[] lastNameArray = theLastName.toCharArray();

                        for (char c : lastNameArray) {

                            if (Character.isDigit(c)) {
                                errorLabel.setText("Last name should not include " + c + "!");
                                errorLabel.setVisible(true);
                                lastNameInput.setText("");

                                return;
                            }
                        }

                        for (int i = 0; i < theLastName.length(); i++) {

                            if (specialCharacters.contains(theLastName.substring(i, i + 1))) {
                                errorLabel.setText("Last name should not include " + theLastName.charAt(i) + "!");
                                errorLabel.setVisible(true);
                                lastNameInput.setText("");

                                return;
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
                                        FTEWriter theFTE = new FTEWriter(new FTE(theEmpNum, theFirstName, theLastName, theDeductRate, theYearlySalary));

                                        mainHT.addEmployee(theFTE);

                                        empNumInput.setText("");
                                        firstNameInput.setText("");
                                        lastNameInput.setText("");
                                        deductRateInput.setText("");
                                        yearlySalaryInput.setText("");

                                        errorLabel.setVisible(false);

                                        empAddedLabel.setText(theFirstName + " " + theLastName + " added!");
                                        empAddedLabel.setVisible(true);
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
                                                        PTEWriter thePTE = new PTEWriter(new PTE(theEmpNum, theFirstName, theLastName, theDeductRate, theHourlyWage, theHoursPerWeek, theWeeksPerYear));

                                                        mainHT.addEmployee(thePTE);

                                                        empNumInput.setText("");
                                                        firstNameInput.setText("");
                                                        lastNameInput.setText("");
                                                        deductRateInput.setText("");
                                                        hourlyWageInput.setText("");
                                                        hoursPerWeekInput.setText("");
                                                        weeksPerYearInput.setText("");

                                                        errorLabel.setVisible(false);

                                                        empAddedLabel.setText(theFirstName + " " + theLastName + " added!");
                                                        empAddedLabel.setVisible(true);
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
                            
                            else {
                                errorLabel.setText("Please specify the employee type!");
                                errorLabel.setVisible(true);
                            }
                        }
                    }
                
                    catch (Exception e) {
                        errorLabel.setText("Deduct rate should be a number from 0 to 1!");
                        errorLabel.setVisible(true);
                        deductRateInput.setText("");
                    }
                }
            }
        }
        
        catch (Exception e) {
            errorLabel.setText("Employee number should be a 6 digit number!");
            errorLabel.setVisible(true);
            empNumInput.setText("");
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
        empNumInput = new javax.swing.JTextField();
        firstNameInput = new javax.swing.JTextField();
        empNumLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        lastNameInput = new javax.swing.JTextField();
        deductRateLabel = new javax.swing.JLabel();
        deductRateInput = new javax.swing.JTextField();
        fullTimeButton = new javax.swing.JRadioButton();
        partTimeButton = new javax.swing.JRadioButton();
        yearlySalaryInput = new javax.swing.JTextField();
        yearlySalaryLabel = new javax.swing.JLabel();
        hourlyWageLabel = new javax.swing.JLabel();
        hoursPerWeekLabel = new javax.swing.JLabel();
        weeksPerYearLabel = new javax.swing.JLabel();
        hourlyWageInput = new javax.swing.JTextField();
        hoursPerWeekInput = new javax.swing.JTextField();
        weeksPerYearInput = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        empAddedLabel = new javax.swing.JLabel();

        setResizable(false);

        empNumInput.setMinimumSize(new java.awt.Dimension(100, 23));
        empNumInput.setPreferredSize(new java.awt.Dimension(100, 23));
        empNumInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empNumInputActionPerformed(evt);
            }
        });

        firstNameInput.setPreferredSize(new java.awt.Dimension(100, 23));

        empNumLabel.setText("Emp Num:");

        firstNameLabel.setText("First Name:");

        lastNameLabel.setText("Last Name:");

        lastNameInput.setPreferredSize(new java.awt.Dimension(100, 23));

        deductRateLabel.setText("Deduct Rate:");

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

        yearlySalaryInput.setPreferredSize(new java.awt.Dimension(100, 23));
        yearlySalaryInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearlySalaryInputActionPerformed(evt);
            }
        });

        yearlySalaryLabel.setText("Yearly Salary:");

        hourlyWageLabel.setText("Hourly Wage:");

        hoursPerWeekLabel.setText("Hours Per Week:");

        weeksPerYearLabel.setText("Weeks Per Year:");

        hourlyWageInput.setPreferredSize(new java.awt.Dimension(100, 23));
        hourlyWageInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourlyWageInputActionPerformed(evt);
            }
        });

        hoursPerWeekInput.setPreferredSize(new java.awt.Dimension(100, 23));
        hoursPerWeekInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoursPerWeekInputActionPerformed(evt);
            }
        });

        weeksPerYearInput.setPreferredSize(new java.awt.Dimension(100, 23));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        errorLabel.setBackground(new java.awt.Color(250, 184, 184));
        errorLabel.setForeground(new java.awt.Color(160, 33, 33));
        errorLabel.setText("Error Label");
        errorLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(160, 33, 33)));
        errorLabel.setOpaque(true);

        empAddedLabel.setText("Emp Added Label");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deductRateLabel)
                            .addComponent(empNumLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lastNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(firstNameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lastNameInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstNameInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deductRateInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(empNumInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(weeksPerYearLabel)
                            .addComponent(hoursPerWeekLabel)
                            .addComponent(hourlyWageLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(yearlySalaryLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(weeksPerYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(yearlySalaryInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(hourlyWageInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(hoursPerWeekInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fullTimeButton)
                                .addGap(38, 38, 38)
                                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(partTimeButton)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(addButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(empAddedLabel)))))
                        .addContainerGap(39, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fullTimeButton)
                    .addComponent(errorLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partTimeButton)
                    .addComponent(empAddedLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(empNumLabel)
                            .addComponent(empNumInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearlySalaryInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearlySalaryLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameLabel)
                            .addComponent(firstNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourlyWageLabel)
                            .addComponent(hourlyWageInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastNameLabel)
                            .addComponent(hoursPerWeekLabel)
                            .addComponent(hoursPerWeekInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deductRateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deductRateLabel)
                            .addComponent(weeksPerYearLabel)
                            .addComponent(weeksPerYearInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fullTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullTimeButtonActionPerformed
        // TODO add your handling code here:
        yearlySalaryLabel.setVisible(true);
        yearlySalaryInput.setVisible(true);
        hourlyWageLabel.setVisible(false);
        hourlyWageInput.setVisible(false);
        hourlyWageInput.setText("");
        hoursPerWeekLabel.setVisible(false);
        hoursPerWeekInput.setVisible(false);
        hoursPerWeekInput.setText("");
        weeksPerYearLabel.setVisible(false);
        weeksPerYearInput.setVisible(false);
        weeksPerYearInput.setText("");
    }//GEN-LAST:event_fullTimeButtonActionPerformed

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
        yearlySalaryInput.setText("");
        
    }//GEN-LAST:event_partTimeButtonActionPerformed

    private void yearlySalaryInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearlySalaryInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearlySalaryInputActionPerformed

    private void empNumInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empNumInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empNumInputActionPerformed

    private void hourlyWageInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourlyWageInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourlyWageInputActionPerformed

    private void hoursPerWeekInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoursPerWeekInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoursPerWeekInputActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        addTheNewEmployee(evt);
    }//GEN-LAST:event_addButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AddNewEmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewEmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewEmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewEmployeeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewEmployeeJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField deductRateInput;
    private javax.swing.JLabel deductRateLabel;
    private javax.swing.JLabel empAddedLabel;
    private javax.swing.JTextField empNumInput;
    private javax.swing.JLabel empNumLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JTextField firstNameInput;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JRadioButton fullTimeButton;
    private javax.swing.JTextField hourlyWageInput;
    private javax.swing.JLabel hourlyWageLabel;
    private javax.swing.JTextField hoursPerWeekInput;
    private javax.swing.JLabel hoursPerWeekLabel;
    private javax.swing.JTextField lastNameInput;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JRadioButton partTimeButton;
    private javax.swing.JTextField weeksPerYearInput;
    private javax.swing.JLabel weeksPerYearLabel;
    private javax.swing.JTextField yearlySalaryInput;
    private javax.swing.JLabel yearlySalaryLabel;
    // End of variables declaration//GEN-END:variables
}
