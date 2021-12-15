/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class demoCheckUserName 
{
    public  boolean checkUsername(String unm) throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abmusicplayerdb","root","");
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser=false;
        String qry="SELECT * FROM userlogin where 'UserName'=?";
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        demoCheckUserName dd=new demoCheckUserName();
        boolean cc=dd.checkUsername("Abcd@1234");
        System.out.print(cc);
    }
}
