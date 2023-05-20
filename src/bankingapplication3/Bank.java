
package bankingapplication3;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {
    private String name;

    public Bank(String name) {
        this.name = name;
    }
    
    public void openAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "insert into account(accID , accName , accBalance)" + "values(?,?,?)"; //preparedstatement
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, account.getNumber());
            pre.setString(2,account.getName());
            pre.setDouble(3,account.getBalance());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listAccount(){
        Connection con = BankConnection.connect(); //con sql
        try {
            Statement stat = con.createStatement();
            String sql = "select * from account";
            
            ResultSet re = stat.executeQuery(sql);
            
            while(re.next()){
                
                System.out.println(re.getString(1)+" "+re.getString(2)+" "+re.getString(3));
            }
            System.out.println(); //space
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void closeAccount(int number){
        Connection con = BankConnection.connect();
        String sql = "delete from account where accID=?"; //blind prepareddstat
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, number); //position 1
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void depositMoney(Account account , double amount ){ //update balance
        account.deposit(amount);
        
        System.out.println(account.getBalance());
        
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setDouble(1, account.getBalance());
            pre.setInt(2, account.getNumber());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void withdrawMoney(Account account,double amount){
        account.withdraw(amount);
    }

    public Account getAccount(int number) { //number
        Connection con = BankConnection.connect();
        Account account = null;
     
        Statement stat;
        try {
            
            stat = con.createStatement();
            String sql = "select * from account where accID = '"+number+"'";
            ResultSet re = stat.executeQuery(sql); //query for resultset
            
            String accountName = "";
            double balance = 0;
            
            while(re.next()){         
                accountName = re.getString(2); //name column2
                balance = re.getDouble(3); //balance
                System.out.println(re.getString(2));
            }
            
            account = new Account(number , accountName , balance);
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return account;
    }
    
}

/*Connection con = BankConnection.connect();
        String sql = "select * from account where accID = ?";
        Account account = null;
        PreparedStatement pre;
        try {
            
            pre = con.prepareStatement(sql);
            pre.setInt(1, number);
            
            ResultSet re = pre.executeQuery(); //query for resultset
            
            String accountName = re.getString(2); //name column2
            double balance = re.getDouble(3); //balance
            account = new Account(number , accountName , balance); */
        