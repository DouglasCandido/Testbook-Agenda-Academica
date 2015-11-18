package br.com.testbook.RendimentoEscolar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public abstract class LeitorDeValoresParaTela {
    
    public static String getPeso1(){
        
        File f = new File("ValoresDoBoletim.txt");
        
        if(f.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);

                String values = bufferedReader.readLine();
                String[] v = values.split(";");
                    
                return v[0];
                    
            }catch(Exception e){
                
                e.printStackTrace();
                
            }
            
        }
        
        return "";
    }
    
    public static String getPeso2(){
        
        File f = new File("ValoresDoBoletim.txt");
        
        if(f.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);

                String values = bufferedReader.readLine();
                String[] v = values.split(";");
                    
                return v[1];
                    
            }catch(Exception e){
                
                e.printStackTrace();
                
            }
            
        }
        
        return "";
    }
    
    public static String getPeso3(){
        
        File f = new File("ValoresDoBoletim.txt");
        
        if(f.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);

                String values = bufferedReader.readLine();
                String[] v = values.split(";");
                    
                return v[2];
                    
            }catch(Exception e){
                
                e.printStackTrace();
                
            }
            
        }
        
        return "";
    }
    
    public static String getPeso4(){
        
        File f = new File("ValoresDoBoletim.txt");
        
        if(f.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);

                String values = bufferedReader.readLine();
                String [] v = values.split(";");
                    
                return v[3];
                    
            }catch(Exception e){
                
                e.printStackTrace();
                
            }
            
        }
        
        return "";
    }
    
    public static String getMediaNecessaria(){
        
        File f = new File("ValoresDoBoletim.txt");
        
        if(f.exists()){
            
            try{
                FileReader leitorDeArquivo = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);

                String values = bufferedReader.readLine();
                String [] v = values.split(";");
                    
                return v[4];
                    
            }catch(Exception e){
                
                e.printStackTrace();
                
            }
            
        }
        
        return "";
    }
    
}
