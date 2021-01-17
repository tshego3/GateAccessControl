/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gate.access.control.gui;

import java.awt.BorderLayout; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.ResultSetMetaData; 
import java.sql.Statement; 
import java.util.Vector; 
import javax.swing.JFrame; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 


/**
 *
 * @author TXG
 */
public class frmViewVisitors extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmViewVisitors
     */
    public frmViewVisitors() {
        initComponents();
        mLoadViewVisitors();
    }
    
private void mLoadViewVisitors()
{
java.sql.Connection conMySQLConnectionString= null; 
String URL="jdbc:mysql://localhost:3306/gateaccesscontrol";
String User="root"; 
String Password="Password"; 
Statement stSQLQuery = null; 
ResultSet rsVisitors= null; 
String strSQLQuery;

try
{
conMySQLConnectionString=DriverManager.getConnection(URL,User,Password);
stSQLQuery=conMySQLConnectionString.createStatement(); 
strSQLQuery = "select Name_ from visitors;"; 
rsVisitors=stSQLQuery.executeQuery(strSQLQuery); 
ResultSetMetaData rsmt = rsVisitors.getMetaData(); 
int intColumnCount = rsmt.getColumnCount(); 
Vector vColumn = new Vector(intColumnCount); 
for(int i = intColumnCount; i <=intColumnCount; i++)
{
vColumn.add(rsmt.getColumnName(i)); 
}
Vector vData = new Vector(); 
Vector vRow = new Vector(); 
while(rsVisitors.next())
{
vRow = new Vector(intColumnCount); 
for (int i = 1; i <=intColumnCount; i++)
{
vRow.add(rsVisitors.getObject(i));
}
vData.add(vRow); 
}
JPanel pnlTable = new JPanel(); 
JTable tblVisitors = new JTable(vData,vColumn); 
JScrollPane jspUsersPane = new JScrollPane(tblVisitors); 
tblVisitors.setFillsViewportHeight(true); 
tblVisitors.setVisible(true); 
tblVisitors.validate(); 
pnlTable.setLayout(new BorderLayout()); 
pnlTable.add(jspUsersPane, BorderLayout.CENTER); 
this.setContentPane(pnlTable);
}
catch (Exception e) 
{
JOptionPane.showMessageDialog(null, "Error" + e);
}
finally 
{
try 
{
stSQLQuery.close(); 
rsVisitors.close(); 
conMySQLConnectionString.close(); 
}
catch (Exception e)
{
JOptionPane.showMessageDialog(null, "Error");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
