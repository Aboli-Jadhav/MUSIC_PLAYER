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
public class pathGetter 
{
    
    public         DefaultListModel listmodel;

     
    public  void demo()
    {
       
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
    public static void main(String[] args) {
        pathGetter pgg=new pathGetter();
        pgg.demo();
    }
   
}
