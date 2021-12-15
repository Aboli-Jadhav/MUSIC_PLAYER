/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoPackage;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class demoOwnedSongs 
{
    public  static String[] getSongs() throws ClassNotFoundException, SQLException
    {
        String Songs[];

        String usernm=LoginUser.getData();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abmusicplayerdb", "root", "");
        Statement stmt=con.createStatement();
        
        String qry="Select count(SongNames) from "+usernm.toLowerCase()+" ";
        ResultSet rs=stmt.executeQuery(qry);
        rs.next();
        int i=rs.getInt(1);
        Songs=new String[i];
        
        i=0;
        String qry2="Select SongNames from "+usernm.toLowerCase()+" ";
        rs=stmt.executeQuery(qry2);
        while(rs.next())
        {
            Songs[i++]=rs.getString("SongNames");
        }
        return Songs;
        
    }
    
}
