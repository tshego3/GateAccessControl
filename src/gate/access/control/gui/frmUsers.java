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
public class frmUsers extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmUsers
     */
    public frmUsers() {
        initComponents();
         mLoadUserName();
        mLoadGUIControls();
    }
    
    CaesarsChiper clsCC= new CaesarsChiper(); 
    
Boolean boolRecordExists=false; 
Boolean boolEdit=false; 
Boolean boolCreate=false; 
String strUserName;
String strPassword;
String strRole;
int intUserID; 
    
private void mClearVariables()
{

strUserName="";
strPassword="";
strRole="";
intUserID=0;
}    
    
private void mGetValuesFromGUI()
{
strUserName=txtUserName.getText();
strPassword=txtPassword.getText();
strRole=txtRole.getText();
}

private void mSetValuesToUpperCase()
{
strUserName=strUserName.toUpperCase();
strPassword=strPassword.toUpperCase();
strRole=strRole.toUpperCase();
}

private void mSetValuesInGUI()
{
txtUserName.setText(strUserName);
txtPassword.setText(strPassword);
txtRole.setText(strRole);
}

private void mClearTextFields()
{
txtUserName.setText("");
txtPassword.setText("");
txtRole.setText("");
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
String strQuery = " Select * from users where UserName='" + strUserName + "' and UserNamePassword='" + strPassword +"'";
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

private void mCreateUser()
{
java.sql.Connection conMySQLConnectionString= null; 
String URL= "jdbc:mysql://localhost:3306/gateaccesscontrol"; 
String User="root"; 
String Password="Password"; 
try
{
conMySQLConnectionString=DriverManager.getConnection(URL,User,Password); 
Statement myStatement =conMySQLConnectionString.createStatement();
String sqlinsert = " insert into Users " + " (UserName, UserNamePassword, Role) "+" values('"+ strUserName +"', '"+ strPassword +"', '"+ strRole +"') ";
myStatement.executeUpdate(sqlinsert); 
myStatement.close(); 
JOptionPane.showMessageDialog(null,"Complete");
}
catch (Exception e)
        {
JOptionPane.showMessageDialog(null, e); 
        }
}

private void mLoadUserName()
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
String strQuery = "Select UserName from Users"; 
stStatement.execute(strQuery); 
rs=stStatement.getResultSet();

while(rs.next())
{
cboUserName.addItem(rs.getString(1));
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

private void mUpdateUser()
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
String strQuery = " UPDATE Users SET UserName='" + strUserName +"', UserNamePassword='" + strPassword +"', Role='" + strRole +"' WHERE ID = "+ intUserID;  
 
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
cboUserName.setModel(model);
}

private void mDeleteUser()
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
String strQuery = " Delete from Users where UserName ='" + strUserName +"' and UserNamePassword='" + strPassword +"' and Role='" + strRole +"' and ID = "+ intUserID; 
 
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

private void mReadUserDetails()
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
String strQuery = " Select ID,UserName,UserNamePassword,Role from Users where UserName ='"+ cboUserName.getSelectedItem().toString()+"'";
stStatement.execute(strQuery); 
rs=stStatement.getResultSet();

while(rs.next())
{
intUserID=rs.getInt(1); 
strUserName=rs.getString(2); 
strPassword=rs.getString(3); 
strRole =rs.getString(4);  

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
txtUserName.setEnabled(false);
txtPassword.setEnabled(false);
txtRole.setEnabled(false);
cboUserName.setEnabled(true); 
btnCreate.setEnabled(true); 
btnLoad.setEnabled(true); 
btnEdit.setEnabled(true); 
btnSave.setEnabled(false); 
btnDelete.setEnabled(true);
}

private void mEditGUIControls()
{
txtUserName.setEnabled(true);
txtPassword.setEnabled(true);
txtRole.setEnabled(true);
cboUserName.setEnabled(false); 
btnCreate.setEnabled(false); 
btnLoad.setEnabled(false); 
btnEdit.setEnabled(false); 
btnSave.setEnabled(true); 
btnDelete.setEnabled(true);
}

private void mSaveGUIControls()
{
txtUserName.setEnabled(false);
txtPassword.setEnabled(false);
txtRole.setEnabled(false);
cboUserName.setEnabled(true); 
btnCreate.setEnabled(true); 
btnLoad.setEnabled(true); 
btnEdit.setEnabled(true); 
btnSave.setEnabled(false); 
btnDelete.setEnabled(false);
}

private void mCreateGUIControls()
{
txtUserName.setEnabled(true);
txtPassword.setEnabled(true);
txtRole.setEnabled(true);
cboUserName.setEnabled(false); 
btnCreate.setEnabled(false); 
btnLoad.setEnabled(false); 
btnEdit.setEnabled(false); 
btnSave.setEnabled(true); 
btnDelete.setEnabled(true);
}

private void mDeleteGUIControls()
{
txtUserName.setEnabled(false);
txtPassword.setEnabled(false);
txtRole.setEnabled(false);
cboUserName.setEnabled(true); 
btnCreate.setEnabled(true); 
btnLoad.setEnabled(true); 
btnEdit.setEnabled(true); 
btnSave.setEnabled(false); 
btnDelete.setEnabled(false);
}

private void mEncryptPassword()
{
    strPassword=clsCC.mEncrypt(strPassword, 5);
}

private void mDecryptPassword()
{
    strPassword=clsCC.mDecrypt(strPassword, 5);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUserName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        cboUserName = new javax.swing.JComboBox<>();
        lblRole = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtRole = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        lblUserName.setText("User Name:");

        lblPassword.setText("Password:");

        lblRole.setText("Role:");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboUserName, 0, 173, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserName)
                            .addComponent(lblPassword)
                            .addComponent(lblRole))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserName)
                            .addComponent(txtPassword)
                            .addComponent(txtRole)))
                    .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserName)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRole)
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        
        mCreateGUIControls(); 
txtUserName.requestFocusInWindow(); 
btnDelete.setText("Cancel"); 
boolCreate=true; 
        
        
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
       
mReadUserDetails();
mDecryptPassword();
mSetValuesInGUI();
mLoadGUIControls();
        
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
      
      mReadUserDetails();
      mDecryptPassword();
      mSetValuesInGUI();
      mEditGUIControls();  
      txtUserName.requestFocusInWindow(); 
btnDelete.setText("Cancel"); 
boolEdit=true;
        
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       
if(boolCreate==true)
{
if(txtUserName.getText().equals(""))
{
JOptionPane.showMessageDialog(null,"The field cannot be left empty"); 
txtUserName.requestFocusInWindow();
}
else if(txtPassword.getText().equals(""))
{
JOptionPane.showMessageDialog(null,"The field cannot be left empty"); 
txtPassword.requestFocusInWindow();
}
else if(txtRole.getText().equals(""))
{
JOptionPane.showMessageDialog(null,"The field cannot be left empty"); 
txtRole.requestFocusInWindow();
}
else
{
mGetValuesFromGUI(); 
mSetValuesToUpperCase();
mEncryptPassword();
mcheckIfltemsExistInTabIe(); 
if(boolRecordExists==true)
{
boolRecordExists=false;
JOptionPane.showMessageDialog(null, "User already Exists");
}
else if(boolRecordExists==false)
{
boolCreate=false; 
mCreateUser(); 
mClearTextFields(); 
mClearVariables(); 
mClearComboBox(); 
mLoadUserName(); 
mLoadGUIControls();
}
}
}

else if(boolEdit=true)
{
boolEdit=false;
mGetValuesFromGUI(); 
mSetValuesToUpperCase(); 
mEncryptPassword();
mUpdateUser(); 
mClearTextFields(); 
mClearVariables(); 
mClearComboBox(); 
mLoadUserName(); 
mLoadGUIControls(); 
}
btnDelete.setText("Delete");     
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
      if("Delete".equals(btnDelete.getText())) 
{
mReadUserDetails(); 
mDeleteUser(); 
mClearComboBox(); 
mClearVariables(); 
mLoadUserName(); 
}
else if("Cancel".equals(btnDelete.getText()))
{
mClearTextFields(); 
mClearVariables(); 
mClearComboBox(); 
mLoadUserName(); 
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
    private javax.swing.JComboBox<String> cboUserName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
