/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gate.access.control.gui;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author TXG
 */
public class frmVisitors extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmVisitors
     */
    public frmVisitors() {
        initComponents();
        mLoadName();
        mLoadGUIControls();
        
    }
    
Boolean boolRecordExists=false; 
Boolean boolEdit=false; 
Boolean boolCreate=false; 
String strName; 
String strDate; 
String strTimeIn; 
String strFromCompany;
String strVehicleRegNo; 
String strTelNo;
String strReasonForVisit;
String strPersonToSee;
int intVisitorID; 

private void mClearVariables()
{

strDate=""; 
strTimeIn=""; 
strName=""; 
strFromCompany="";
strVehicleRegNo=""; 
strTelNo="";
strReasonForVisit="";
strPersonToSee="";
intVisitorID=0;
}

private void mGetValuesFromGUI()
{
strDate=txtDate.getText(); 
strTimeIn=txtTimeIn.getText(); 
strName=txtName.getText(); 
strFromCompany=txtFromCompany.getText();
strVehicleRegNo=txtVehicleRegNo.getText(); 
strTelNo=txtTelNo.getText();
strReasonForVisit=txtReasonForVisit.getText();
strPersonToSee=txtPersonToSee.getText();
}

private void mSetValuesToUpperCase()
{
strDate=strDate.toUpperCase();
strTimeIn=strTimeIn.toUpperCase();
strName=strName.toUpperCase(); 
strFromCompany=strFromCompany.toUpperCase();
strVehicleRegNo=strVehicleRegNo.toUpperCase(); 
strTelNo=strTelNo.toUpperCase();
strReasonForVisit=strReasonForVisit.toUpperCase();
strPersonToSee=strPersonToSee.toUpperCase();
}

private void mSetValuesInGUI()
{
txtDate.setText(strDate); 
txtTimeIn.setText(strTimeIn); 
txtName.setText(strName); 
txtFromCompany.setText(strFromCompany);
txtVehicleRegNo.setText(strVehicleRegNo); 
txtTelNo.setText(strTelNo);
txtReasonForVisit.setText(strReasonForVisit);
txtPersonToSee.setText(strPersonToSee); 
}

private void mClearTextFields()
{
txtDate.setText(""); 
txtTimeIn.setText(""); 
txtName.setText(""); 
txtFromCompany.setText("");
txtVehicleRegNo.setText(""); 
txtTelNo.setText("");
txtReasonForVisit.setText("");
txtPersonToSee.setText("");
}

private void mcheckIfltemsExistInTabIe()
{
String strDBConnectionString="jdbc:mysql://localhost:3306/gateaccesscontrol"; 
String strDBUser="root"; 
String strDBPassword="Password"; 
java.sql.Connection conMySQLConnectionString;
Statement stStatement=null; 
ResultSet rs= null; 
try 
{
conMySQLConnectionString=DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword); 
stStatement = conMySQLConnectionString.createStatement(); 
String strQuery = "Select * from visitors where Name_='" + strName + "' and From_Company='" + strFromCompany + "' and Vehicle_Reg_No='" + strVehicleRegNo + "' and Tel_No='" + strTelNo + "' and Reason_For_Visit='" + strReasonForVisit + "' and Person_To_See='" + strPersonToSee + "' and Date_='" + strDate + "' and Time_In='" + strTimeIn + "'";
stStatement.execute(strQuery); 
rs=stStatement.getResultSet(); 
boolRecordExists=rs.next();
}
catch (Exception e) 
{
JOptionPane.showMessageDialog(null,e);
}
finally
{
try
{
stStatement.close();
}
catch (Exception e)
{
JOptionPane.showMessageDialog(null,"Connection String not Closed"+""+e);
}
}
}

private void mCreateVisitor()
{
java.sql.Connection conMySQLConnectionString= null; 
String URL= "jdbc:mysql://localhost:3306/gateaccesscontrol"; 
String User="root"; 
String Password="Password"; 
try
{
conMySQLConnectionString=DriverManager.getConnection(URL,User,Password); 
Statement myStatement =conMySQLConnectionString.createStatement();
String sqlinsert = "insert into Visitors  " + " (Name_, From_Company, Vehicle_Reg_No, Tel_No, Reason_For_Visit, Person_To_See, Date_, Time_In) "+" values ('"+ strName +"', '"+ strFromCompany +"', '"+ strVehicleRegNo +"','"+ strTelNo +"', '"+ strReasonForVisit +"', '"+ strPersonToSee +"', '"+ strDate +"', '"+ strTimeIn +"')";
myStatement.executeUpdate(sqlinsert); 
myStatement.close(); 
JOptionPane.showMessageDialog(null,"Complete");
}
catch (Exception e)
        {
JOptionPane.showMessageDialog(null, e); 
        }
}

private void mLoadName()
{
String strDBConnectionString="jdbc:mysql://localhost:3306/gateaccesscontrol"; 
String strDBUser="root"; 
String strDBPassword="Password";
java.sql.Connection conMySQLConnectionString;
Statement stStatement=null; 
ResultSet rs= null;  

try
{
conMySQLConnectionString=DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
stStatement = conMySQLConnectionString.createStatement(); 
String strQuery = " Select Name_ from Visitors"; 
stStatement.execute(strQuery); 
rs=stStatement.getResultSet();

while(rs.next())
{
cboName.addItem(rs.getString(1));
}
}
catch(Exception e) 
{
JOptionPane.showMessageDialog(null, e); 
}
finally 
{
try 
{
stStatement.close(); 
}
catch (Exception e) 
{  
JOptionPane.showMessageDialog(null,"Connection String not Closed"+""+e);
}
}
}


private void mUpdateVisitor()
{
String strDBConnectionString="jdbc:mysql://localhost:3306/gateaccesscontrol"; 
String strDBUser="root"; 
String strDBPassword="Password";
java.sql.Connection conMySQLConnectionString;
Statement stStatement=null; 
ResultSet rs= null;  

try
{
conMySQLConnectionString=DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
stStatement = conMySQLConnectionString.createStatement(); 
String strQuery = " UPDATE visitors SET Name_ ='"+strName +"',From_Company='"+strFromCompany+"',Vehicle_Reg_No='"+strVehicleRegNo+"',Tel_No='"+strTelNo+"',Reason_For_Visit='"+strReasonForVisit+"',Person_To_See='"+strPersonToSee+"',Date_='"+strDate+"',Time_In='"+strTimeIn+"' where ID ="+intVisitorID; 
 
stStatement.execute(strQuery); 
rs=stStatement.getResultSet();
}
catch(Exception e) 
{
JOptionPane.showMessageDialog(null, e); 
}
finally 
{
try 
{
stStatement.close(); 
}
catch (Exception e) 
{  
JOptionPane.showMessageDialog(null,"Connection String not Closed"+""+e);
}
}
}


private void mClearComboBox()
{
String[] arrArray = new String[0]; 
javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray); 
cboName.setModel(model);
}

private void mDeleteVisitor()
{
String strDBConnectionString="jdbc:mysql://localhost:3306/gateaccesscontrol"; 
String strDBUser="root"; 
String strDBPassword="Password";
java.sql.Connection conMySQLConnectionString;
Statement stStatement=null; 
ResultSet rs= null;  

try
{
conMySQLConnectionString=DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
stStatement = conMySQLConnectionString.createStatement(); 
String strQuery = " Delete from visitors where Name_ ='"+strName +"' and From_Company='"+strFromCompany+"' and Vehicle_Reg_No='"+strVehicleRegNo+"' and Tel_No='"+strTelNo+"' and Reason_For_Visit='"+strReasonForVisit+"' and Person_To_See='"+strPersonToSee+"' and Date_='"+strDate+"' and Time_In='"+strTimeIn+"' and ID ="+intVisitorID; 
  
stStatement.execute(strQuery); 
rs=stStatement.getResultSet();
}
catch(Exception e) 
{
JOptionPane.showMessageDialog(null, e); 
}
finally 
{
try 
{
stStatement.close(); 
}
catch (Exception e) 
{  
JOptionPane.showMessageDialog(null,"Connection String not Closed"+""+e);
}
}
}


private void mReadVisitorDetails()
{
String strDBConnectionString="jdbc:mysql://localhost:3306/gateaccesscontrol"; 
String strDBUser="root"; 
String strDBPassword="Password";
java.sql.Connection conMySQLConnectionString;
Statement stStatement=null; 
ResultSet rs= null;  

try
{
conMySQLConnectionString=DriverManager.getConnection(strDBConnectionString,strDBUser,strDBPassword);
stStatement = conMySQLConnectionString.createStatement(); 
String strQuery = " Select ID, Date_, Time_In, Name_, From_Company, Vehicle_Reg_No, Tel_No, Reason_For_Visit, Person_To_See from Visitors where Name_='"+ cboName.getSelectedItem().toString()+"'";
stStatement.execute(strQuery); 
rs=stStatement.getResultSet();

while(rs.next())
{
intVisitorID=rs.getInt(1); 
strDate=rs.getString(2); 
strTimeIn=rs.getString(3); 
strName =rs.getString(4); 
strFromCompany=rs.getString(5); 
strVehicleRegNo=rs.getString(6); 
strTelNo =rs.getString(7); 
strReasonForVisit=rs.getString(8); 
strPersonToSee=rs.getString(9); 

}
}
catch(Exception e) 
{
JOptionPane.showMessageDialog(null, e); 
}
finally 
{
try 
{
stStatement.close(); 
}
catch (Exception e) 
{  
JOptionPane.showMessageDialog(null,"Connection String not Closed"+""+e);
}
}
}

private void mLoadGUIControls()
{
txtDate.setEnabled(false);
txtTimeIn.setEnabled(false);
txtName.setEnabled(false);
txtFromCompany.setEnabled(false);
txtVehicleRegNo.setEnabled(false);
txtTelNo.setEnabled(false);
txtReasonForVisit.setEnabled(false);
txtPersonToSee.setEnabled(false);
cboName.setEnabled(true); 
btnCreate.setEnabled(true); 
btnLoad.setEnabled(true); 
btnEdit.setEnabled(true); 
btnSave.setEnabled(false); 
btnDelete.setEnabled(true);
}

private void mEditGUIControls()
{
txtDate.setEnabled(true);
txtTimeIn.setEnabled(true);
txtName.setEnabled(true);
txtFromCompany.setEnabled(true);
txtVehicleRegNo.setEnabled(true);
txtTelNo.setEnabled(true);
txtReasonForVisit.setEnabled(true);
txtPersonToSee.setEnabled(true);
cboName.setEnabled(false); 
btnCreate.setEnabled(false); 
btnLoad.setEnabled(false); 
btnEdit.setEnabled(false); 
btnSave.setEnabled(true); 
btnDelete.setEnabled(true);
}

private void mSaveGUIControls()
{
txtDate.setEnabled(false);
txtTimeIn.setEnabled(false);
txtName.setEnabled(false);
txtFromCompany.setEnabled(false);
txtVehicleRegNo.setEnabled(false);
txtTelNo.setEnabled(false);
txtReasonForVisit.setEnabled(false);
txtPersonToSee.setEnabled(false);
cboName.setEnabled(true); 
btnCreate.setEnabled(true); 
btnLoad.setEnabled(true); 
btnEdit.setEnabled(true); 
btnSave.setEnabled(false); 
btnDelete.setEnabled(false);
}

private void mCreateGUIControls()
{
txtDate.setEnabled(true);
txtTimeIn.setEnabled(true);
txtName.setEnabled(true);
txtFromCompany.setEnabled(true);
txtVehicleRegNo.setEnabled(true);
txtTelNo.setEnabled(true);
txtReasonForVisit.setEnabled(true);
txtPersonToSee.setEnabled(true);
cboName.setEnabled(false); 
btnCreate.setEnabled(false); 
btnLoad.setEnabled(false); 
btnEdit.setEnabled(false); 
btnSave.setEnabled(true); 
btnDelete.setEnabled(true);
}

private void mDeleteGUIControls()
{
txtDate.setEnabled(false);
txtTimeIn.setEnabled(false);
txtName.setEnabled(false);
txtFromCompany.setEnabled(false);
txtVehicleRegNo.setEnabled(false);
txtTelNo.setEnabled(false);
txtReasonForVisit.setEnabled(false);
txtPersonToSee.setEnabled(false);
cboName.setEnabled(true); 
btnCreate.setEnabled(true); 
btnLoad.setEnabled(true); 
btnEdit.setEnabled(true); 
btnSave.setEnabled(false); 
btnDelete.setEnabled(false);
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDate = new javax.swing.JLabel();
        lblTimeIn = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblFromCompany = new javax.swing.JLabel();
        lblVehicleRegNo = new javax.swing.JLabel();
        lblTellNo = new javax.swing.JLabel();
        lblReasonForVisit = new javax.swing.JLabel();
        lblPersonToSee = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        txtTimeIn = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtFromCompany = new javax.swing.JTextField();
        txtVehicleRegNo = new javax.swing.JTextField();
        txtTelNo = new javax.swing.JTextField();
        txtReasonForVisit = new javax.swing.JTextField();
        txtPersonToSee = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cboName = new javax.swing.JComboBox<>();

        lblDate.setText("Date:");

        lblTimeIn.setText("Time In:");

        lblName.setText("Name:");

        lblFromCompany.setText("From Company:");

        lblVehicleRegNo.setText("Vehicle Reg. No:");

        lblTellNo.setText("Tel. No:");

        lblReasonForVisit.setText("Reason For Visit:");

        lblPersonToSee.setText("Person To See:");

        txtFromCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFromCompanyActionPerformed(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate)
                            .addComponent(lblTimeIn)
                            .addComponent(lblName)
                            .addComponent(lblFromCompany)
                            .addComponent(lblVehicleRegNo)
                            .addComponent(lblTellNo)
                            .addComponent(lblReasonForVisit)
                            .addComponent(lblPersonToSee))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPersonToSee)
                            .addComponent(txtReasonForVisit)
                            .addComponent(txtTelNo)
                            .addComponent(txtTimeIn)
                            .addComponent(txtDate)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFromCompany)
                            .addComponent(txtVehicleRegNo)))
                    .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDate)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblTimeIn))
                    .addComponent(txtTimeIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFromCompany)
                    .addComponent(txtFromCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVehicleRegNo)
                    .addComponent(txtVehicleRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTellNo)
                    .addComponent(txtTelNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReasonForVisit)
                    .addComponent(txtReasonForVisit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersonToSee)
                    .addComponent(txtPersonToSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFromCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFromCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFromCompanyActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
     
mCreateGUIControls(); 
txtName.requestFocusInWindow(); 
btnDelete.setText("Cancel"); 
boolCreate=true; 
        
        
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
      
mReadVisitorDetails(); 
mSetValuesInGUI();
mLoadGUIControls();
        
        
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
      mReadVisitorDetails();
      mSetValuesInGUI();
      mEditGUIControls();  
      txtName.requestFocusInWindow(); 
btnDelete.setText("Cancel"); 
boolEdit=true;
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       
if(boolCreate==true)
{
if(txtName.getText().equals(""))
{
JOptionPane.showMessageDialog(null,"The field cannot be left empty"); 
txtName.requestFocusInWindow();
}
else if(txtTelNo.getText().equals(""))
{
JOptionPane.showMessageDialog(null,"The field cannot be left empty"); 
txtTelNo.requestFocusInWindow();
}
else
{
mGetValuesFromGUI(); 
mSetValuesToUpperCase(); 
mcheckIfltemsExistInTabIe(); 
if(boolRecordExists==true)
{
boolRecordExists=false;
JOptionPane.showMessageDialog(null, "Visitor already Exists");
}
else if(boolRecordExists==false)
{
boolCreate=false; 
mCreateVisitor(); 
mClearTextFields(); 
mClearVariables(); 
mClearComboBox(); 
mLoadName(); 
mLoadGUIControls();
}
}
}

else if(boolEdit=true)
{
boolEdit=false;
mGetValuesFromGUI(); 
mSetValuesToUpperCase(); 
mUpdateVisitor(); 
mClearTextFields(); 
mClearVariables(); 
mClearComboBox(); 
mLoadName(); 
mLoadGUIControls(); 
}
btnDelete.setText("Delete");  
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       
if("Delete".equals(btnDelete.getText())) 
{
mReadVisitorDetails(); 
mDeleteVisitor(); 
mClearComboBox(); 
mClearVariables(); 
mLoadName(); 
}
else if("Cancel".equals(btnDelete.getText()))
{
mClearTextFields(); 
mClearVariables(); 
mClearComboBox(); 
mLoadName(); 
mLoadGUIControls(); 
}
btnDelete.setText("Delete");
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cboName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblFromCompany;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPersonToSee;
    private javax.swing.JLabel lblReasonForVisit;
    private javax.swing.JLabel lblTellNo;
    private javax.swing.JLabel lblTimeIn;
    private javax.swing.JLabel lblVehicleRegNo;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtFromCompany;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPersonToSee;
    private javax.swing.JTextField txtReasonForVisit;
    private javax.swing.JTextField txtTelNo;
    private javax.swing.JTextField txtTimeIn;
    private javax.swing.JTextField txtVehicleRegNo;
    // End of variables declaration//GEN-END:variables


}
