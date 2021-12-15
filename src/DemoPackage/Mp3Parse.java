package DemoPackage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

public class Mp3Parse {

    public   void getMetadataOfSong(String songnm)
    {
        FileInputStream inputstream = null;
        try {
            //detecting the file type
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            inputstream = new FileInputStream(new File(songnm));
            ParseContext pcontext = new ParseContext();
            //Mp3 parser
            Mp3Parser Mp3Parser = new Mp3Parser();
            Mp3Parser.parse(inputstream, handler, metadata, pcontext);
            LyricsHandler lyrics = new LyricsHandler(inputstream, handler);
            while (lyrics.hasLyrics()) {
                System.out.println(lyrics.toString());
            }
            DefaultListModel data=new DefaultListModel();

            User_GetSongMetaData.lbl_content.setText(handler.toString());
            System.out.println("Metadata of the document:");
            String[] metadataNames = metadata.names();
            for (String name : metadataNames) { 
                data.addElement(name+" : "+metadata.get(name));
                
            }
            User_GetSongMetaData.metadata_list.setModel(data);
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mp3Parse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mp3Parse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Mp3Parse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TikaException ex) {
            Logger.getLogger(Mp3Parse.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputstream.close();
            } catch (IOException ex) {
                Logger.getLogger(Mp3Parse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        Mp3Parse mp=new Mp3Parse();
    }
}
