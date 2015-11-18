package br.com.testbook.RendimentoEscolar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Boletim {
    
    private String nomeDisciplina;
    
    private double media1;
    private double media2;
    private double media3;
    private double media4; 
    private static double peso1;
    private static double peso2;
    private static double peso3;
    private static double peso4;
    private double mediaFinalAritmetica;
    private double mediaFinalPonderada;
    private static double mediaNecessaria;

    public String getSituacaoAritmetica() {
        
        if(calcularMediaFinalAritmetica() >= mediaNecessaria){
            return "Aprovado";
        }else{
            return "Reprovado";
        }
        
    }

    public String getSituacaoPonderada() {
        
        if(calcularMediaFinalPonderada()>= mediaNecessaria){
            return "Aprovado";
        }else{
            return "Reprovado";
        }
        
    }
    
    public static double getMediaNecessaria() {
        
        return mediaNecessaria;
        
    }

    public static void setMediaNecessaria(double mediaNecessaria) {
        
        Boletim.mediaNecessaria = mediaNecessaria;
        
    }
    
    public void setMediaFinalAritmetica(double mediaFinalAritmetica) {
        
        this.mediaFinalAritmetica = mediaFinalAritmetica;
        
    }
    
        public void setMediaFinalPonderada(double mediaFinalPonderada) {
        
        this.mediaFinalPonderada = mediaFinalPonderada;
        
    }

    public Boletim() {
        
        lerValoresConfigurados();
        
    }
    
    public double calcularMediaFinalAritmetica(){
        
        mediaFinalAritmetica = ((media1 + media2 + media3 + media4) / 4); 
        
        return mediaFinalAritmetica;
        
    }
    
    public double calcularMediaFinalPonderada(){
        
        mediaFinalPonderada = (media1 * peso1 + 
                media2 * peso2 + 
                media3 * peso3 + 
                media4 * peso4) /
                (peso1 + peso2 + peso3 + peso4 );
        
        return mediaFinalPonderada;
        
    }

    public String getNomeDisciplina() {
        
        return nomeDisciplina;
        
    }

    public double getMedia1() {
        
        return media1;
        
    }

    public double getMedia2() {
        
        return media2;
        
    }

    public double getMedia3() {
        
        return media3;
        
    }

    public double getMedia4() {
        
        return media4;
        
    }

    public double getPeso1() {
        
        return peso1;
        
    }

    public double getPeso2() {
        
        return peso2;
        
    }

    public double getPeso3() {
        
        return peso3;
        
    }

    public double getPeso4() {
        
        return peso4;
        
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        
        this.nomeDisciplina = nomeDisciplina;
        
    }

    public void setMedia1(double media1) {
        
        this.media1 = media1;
        
    }

    public void setMedia2(double media2) {
        
        this.media2 = media2;
        
    }

    public void setMedia3(double media3) {
        
        this.media3 = media3;
        
    }

    public void setMedia4(double media4) {
        
        this.media4 = media4;
        
    }

    public void setPeso1(double peso1) {
        
        this.peso1 = peso1;
        
    }

    public void setPeso2(double peso2) {
        
        this.peso2 = peso2;
        
    }

    public void setPeso3(double peso3) {
        
        this.peso3 = peso3;
        
    }

    public void setPeso4(double peso4) {
        
        this.peso4 = peso4;
        
    }
    
  public boolean validar(double[] vetor){
      
      String strMedia1 = String.valueOf(media1);
      String strMedia2 = String.valueOf(media2);
      String strMedia3 = String.valueOf(media3);
      String strMedia4 = String.valueOf(media4);    
      
      return !strMedia1.isEmpty() && !strMedia2.isEmpty() && !strMedia3.isEmpty() && !strMedia4.isEmpty();
       
    }
  
  public double MediaPresicaPonderada() {
      media4 = (((mediaNecessaria) * (peso1 + peso2 + peso3 + peso4)) - 
              ((media1*peso1)+
              (media2*peso2)+
              (media3*peso3)))/peso4;
      
      return media4;
  } 
  
  public double MediaPrecisaAritmetica(){
      media4 = (4 * mediaNecessaria) - (media1 + media2 + media3);
      
      return media4;
  }

    private void lerValoresConfigurados() {
        
        File f = new File("ValoresDeBoletim.txt");
        
        if(!f.exists()){
            
            this.peso1 = 2;
            this.peso2 = 2;
            this.peso3 = 3;
            this.peso4 = 3;
            this.mediaNecessaria = 60;
            
        }else{
        
            try{
                
                FileReader leitorDeArquivo = new FileReader(f);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);
                
                String values = bufferedReader.readLine();
                String[] v = values.split(";");
                
                this.peso1 = Double.parseDouble(v[0]);
                this.peso2 = Double.parseDouble(v[1]);
                this.peso3 = Double.parseDouble(v[2]);
                this.peso4 = Double.parseDouble(v[3]);
                this.mediaNecessaria = Double.parseDouble(v[4]);
                
                bufferedReader.close();
                
            }catch(Exception e){
                
                 e.printStackTrace();
                 
            }
            
        }
        
    }
        
    public static void salvarValoresConfigurados(double[] n){
        
        File f = new File("ValoresDoBoletim.txt");
        
        String[] v = new String[5];
        
        v[0] = "" + n[0];
        v[1] = "" + n[1];
        v[2] = "" + n[2];
        v[3] = "" + n[3];
        v[4] = "" + n[4];
        
        try{
            
            FileOutputStream out = new FileOutputStream(f);
            
            for(int i = 0 ; i < v.length ; i++){
                
                out.write(v[i].getBytes());
                
                if(i != v.length - 1){
                    
                    out.write(";".getBytes());
                    
                }
            }
            
        } catch (IOException ioe) {
            
            ioe.printStackTrace();
            
        }
    }
    
    public static void restaurarArquivo(){
        
        File f = new File("ValoresDoBoletim.txt");
        
        try{
            
            FileOutputStream out = new FileOutputStream(f);
            
            out.write("2.0;".getBytes());
            out.write("2.0;".getBytes());
            out.write("3.0;".getBytes());
            out.write("3.0;".getBytes());
            out.write("60.0".getBytes());
            
        } catch (IOException ioe) {
            
            ioe.printStackTrace();
            
        }
        
    }
    
    public String toFileString(){
        
        return nomeDisciplina + ";" + media1 + ";" + media2 + ";" +  media3 + ";"
                + media4 + ";" + mediaFinalAritmetica + ";" + mediaFinalPonderada 
                + ";" + "\n";
        
    }
    
}
