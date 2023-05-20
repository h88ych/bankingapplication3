
package bankingapplication3;
//connect sql database
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BankConnection {
    
    public static Connection connect(){ //not create obj
        String URL = "jdbc:mysql://localhost:3306/mydb3";
        String username = "root"; //in sql
        String password = "mysql@sit";
        Connection con = null; //return cant see in try cactch 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            con = DriverManager.getConnection(URL, username, password);  
   
                    } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
