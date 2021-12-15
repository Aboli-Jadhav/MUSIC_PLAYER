/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoPackage;

/**
 *
 * @author DELL
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class demoCheckUserName1 
{
    public  boolean checkUsername(String unm) throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abmusicplayerdb","root","");
        PreparedStatement ps;
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        ResultSet rs;
        boolean checkUser=false;
        String qry="SELECT * FROM userlogin where UserName=?";
        ps=con.prepareStatement(qry);
        ps.setString(1, unm);
        rs=ps.executeQuery();
        if(rs.next())
        {
            checkUser=true;
            return checkUser;
        }
        else
        {
            checkUser=false;
            return checkUser;
        }
    }
    public static Connection setConnection()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
                 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abmusicplayerdb", "root", "");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    
    }
}
