/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoPackage;

import java.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.*;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;

/**
 *
 * @author DELL
 */
public class User_pathGetter 
{
    
    public         DefaultListModel listmodel;
    public  String usernm=LoginUser.getData();
    public String dbsongslist[];
    
    public void abcd() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abmusicplayerdb", "root", "");
        Statement stmt=con.createStatement();
        String qry="Select count(SongNames) from "+usernm.toLowerCase()+" ";
        ResultSet rs=stmt.executeQuery(qry);
        rs.next();
        int i=rs.getInt(1);
        dbsongslist=new String[i];
        i=0;
        
        String qry3="Select SongNames from "+usernm.toLowerCase()+" ";
        rs=stmt.executeQuery(qry3);
        while(rs.next())
        {
            dbsongslist[i++]=rs.getString("SongNames");
        }
             
        
    }
    
    public  void demo() throws ClassNotFoundException, SQLException     {
       
        abcd();
        final File f=new File("C:\\Users\\DELL\\Music");
        List<String>  result=new ArrayList<>();
        search(".*\\.mp3",f,result);
         ArrayList<String> sng=new ArrayList<String>();
        for(String s:result)
        {
            sng.add(s);

        }
         String songs[]=new String[sng.size()];

        int sz=sng.size();
        for(int i=0;i<sz;i++)
        {
            songs[i]=sng.get(i);
        }
        listmodel=new DefaultListModel();
        for(int i=0;i<songs.length;i++)
        {
            
            listmodel.addElement(songs[i]);
            
        }
        for(int a=0;a<dbsongslist.length;a++)
        {
            if(listmodel.contains(dbsongslist[a]))
            {
                listmodel.removeElement(dbsongslist[a]);
            }
            else
            {
                System.out.println(dbsongslist[a]);
            }
        }
        
        
        
    }
    
    
    public static void search(final String pattern,final File folder,List<String> res)
    {
        for(final File f:folder.listFiles())
        {
            if(f.isDirectory())
                search(pattern,f,res);
            if(f.isFile())
            {
                if(f.getName().matches(pattern))
                {
                    res.add(f.getAbsolutePath());
                }
            }
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        User_pathGetter pgg=new User_pathGetter();
        pgg.demo();
    }
   
}
