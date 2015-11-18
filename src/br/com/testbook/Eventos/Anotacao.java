package br.com.testbook.Eventos;

public class Anotacao {
    
    private String anotacao;
    private String nomeDisciplina;

   
    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

  
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

   
    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
    
    public boolean validar(){
       return !anotacao.isEmpty() ;
    }
    
    public String toFileString(){
        
        return nomeDisciplina + ";" + anotacao + ";" + "\n";
        
    }
    
    @Override
    public String toString(){
        
        return nomeDisciplina;
        
    }
    
}
