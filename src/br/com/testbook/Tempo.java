package br.com.testbook;

//Esta classe define as propriedades do tempo
public class Tempo {
    
    private int horas;
    private int minutos;

    public int getHoras() {
        
        return horas;
        
    }

    public int getMinutos() {
        
        return minutos;
        
    }

    public void setHoras(int horas) {
        
        if(horas > 23 || horas < 0){
            
            this.horas = 0;
            
        }else{
            
            this.horas = horas;
            
        }
        
    }

    public void setMinutos(int minutos) {
        
        if(minutos > 59 || minutos < 0){
            
            this.minutos = 0;
            
        }else{
            
            this.minutos = minutos;
            
        }
        
    }
    
}