/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoPackage;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author DELL
 */
public class displayList 
{
    public  DefaultListModel songmodel;
    public String songlist[];
    public static String Username;
    
    public  void dispList()
    {
        try {
            Username=LoginUser.getData();
            PreparedStatement ps;
            ResultSet rs;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abmusicplayerdb","root","");
            String qry="Select count(SongNames) from "+Username.toLowerCase()+" ";
            ps=con.prepareStatement(qry);
            rs=ps.executeQuery();
            rs.next();
            int i=rs.getInt(1);
            songlist=new String[i];
            i=0;
            
            
            String qry2="Select SongNames from "+Username.toLowerCase()+" ";
            Statement stmt=con.createStatement();
            ResultSet res=stmt.executeQuery(qry2);
            while(res.next())
            {
                songlist[i++]=res.getString(1);
                
            }

            songmodel=new DefaultListModel();
            for(int j=0;j<songlist.length;j++)
            {
                songmodel.addElement(songlist[j]);
                
            }

        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(displayList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(displayList.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public static void main(String[] args) {
        displayList d1=new displayList();
        d1.dispList();

    }
}
