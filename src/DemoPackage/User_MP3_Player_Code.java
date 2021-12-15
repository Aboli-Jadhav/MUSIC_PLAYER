/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoPackage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author DELL
 */
public class User_MP3_Player_Code 
{
    FileInputStream FIS; 
    BufferedInputStream BIS;
    
    public Player player;
    
    public long pauseLocation;
    public long songTotalLength;
    
    public String fileLocation;
    
    public void Stop()
    {
        if(player != null)
        {
            player.close();
            pauseLocation=0;
            songTotalLength=0;
        }
    }
    
    public void Play(String path)
    {
        try {
            FIS=new FileInputStream(path);
            BIS=new BufferedInputStream(FIS);
            
            player=new Player(BIS);
            songTotalLength=FIS.available();
            fileLocation=path + "";
            
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(MP3_Player_Code.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (JavaLayerException ex) 
        {
            Logger.getLogger(MP3_Player_Code.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MP3_Player_Code.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
            public void run()
            {
                try 
                {
                    player.play();
                    
                    if(player.isComplete() &&MP3Player_GUI.count==1)
                    {
                        Play(fileLocation);
                    }
                    if(player.isComplete())
                    {
                        MP3Player_GUI.lbl_songnm.setText("");
                    }
                } 
                catch (JavaLayerException ex) 
                {
                    
                }
            }
        }.start();
    }
    
    public void Pause()
    {
        if(player != null)
        {
            try 
            {
                pauseLocation=FIS.available();
                player.close();

            } 
            catch (IOException ex) 
            {
                
            }
        }
    }
    
    //Resume Method
    public void Resume()
    {
        try {
            FIS=new FileInputStream(fileLocation);
            BIS=new BufferedInputStream(FIS);
            
            player=new Player(BIS);
            
            FIS.skip(songTotalLength - pauseLocation);
            
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(MP3_Player_Code.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (JavaLayerException ex) 
        {
            Logger.getLogger(MP3_Player_Code.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MP3_Player_Code.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
            public void run()
            {
                try 
                {
                    player.play();
                } 
                catch (JavaLayerException ex) 
                {
                }
            }
        }.start();
    }
}
