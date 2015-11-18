package br.com.testbook;

import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Testbook {
    
    public static void main(String args[]){
        
        try{
            
            UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
            
        }catch(UnsupportedLookAndFeelException e){
            
            e.printStackTrace();
            
        }catch(ParseException e){
            
            e.printStackTrace();
                    
        } 
       
        new TelaPrincipal();
        
    }
    
}
