package br.com.testbook.Eventos;

public class Calendario {
    
    private String nomeDisciplina;
    private String tipoEvento;
    private String tituloEvento;
    private String anotacaoEvento;
    private String dataEvento;

    public String getNomeDisciplina() {
        
        return nomeDisciplina;
        
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        
        this.nomeDisciplina = nomeDisciplina;
        
    }

    public String getTipoEvento() {
        
        return tipoEvento;
        
    }

    public void setTipoEvento(String tipoEvento) {
        
        this.tipoEvento = tipoEvento;
        
    }

    public String getTituloEvento() {
        
        return tituloEvento;
        
    }

    public void setTituloEvento(String tituloEvento) {
        
        this.tituloEvento = tituloEvento;
        
    }

    public String getAnotacaoEvento() {
        
        return anotacaoEvento;
        
    }

    public void setAnotacaoEvento(String anotacaoEvento) {
        
        this.anotacaoEvento = anotacaoEvento;
        
    }

    public String getDataEvento() {
        
        return dataEvento;
        
    }

    public void setDataEvento(String dataEvento) {
        
        this.dataEvento = dataEvento;
        
    }
    
    public boolean validar(){
        
        return !nomeDisciplina.isEmpty() && !tipoEvento.isEmpty() && !tituloEvento.isEmpty()
                && !anotacaoEvento.isEmpty() && !dataEvento.isEmpty();
       
    }
        
    public String toFileString(){
        
        return nomeDisciplina + ";" + tipoEvento + ";" + tituloEvento + ";" + anotacaoEvento + ";"
                + dataEvento + "\n";
        
    }
    
}
