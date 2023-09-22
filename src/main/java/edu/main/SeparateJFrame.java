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
public class SeparateJFrame extends javax.swing.JFrame {
    
    
    private MyHashTable mainHT;
    private DefaultTableModel model;

    /**
     * Creates new form SeparateJFrame
     */
    public SeparateJFrame() {
        initComponents();
    }
    
    
    public void setMainHT(MyHashTable refValForHT) {
        mainHT = refValForHT;
    }
    
    
    public void displayTable() {
        int numInHT = mainHT.getNumInHashTable();
        
        model = new DefaultTableModel(new Object[] {"Status", 
                                                    "Emp Num", 
                                                    "First Name", 
                                                    "Last Name", 
                                                    "Net Yearly Salary"}, 
                                                    numInHT);
        
        empInfoTable.setModel(model);
        empInfoTable.setAutoCreateColumnsFromModel(true);
        
        int empCounter = -1;
        
        if (numInHT > 0) {
            
            for (int i = 0; i < mainHT.buckets.length; i++) {
                
                for (int j = 0; j < mainHT.buckets[i].size(); j++) {
                    
                    EmployeeInfo theEmp = mainHT.buckets[i].get(j).getEmployeeInfo();
                    
                    empCounter ++;
                    
                    model.setValueAt(theEmp.getEmpType(), empCounter, 0);
                    model.setValueAt(theEmp.getEmpNum(), empCounter, 1);
                    model.setValueAt(theEmp.getFirstName(), empCounter, 2);
                    model.setValueAt(theEmp.getLastName(), empCounter, 3);
                    model.setValueAt(theEmp.calcNetAnnualIncome(), empCounter, 4);
                }
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        empInfoTable = new javax.swing.JTable();

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
        ));
        jScrollPane1.setViewportView(empInfoTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SeparateJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeparateJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeparateJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeparateJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeparateJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable empInfoTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
