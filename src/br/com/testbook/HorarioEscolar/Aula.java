package br.com.testbook.HorarioEscolar;

//Esta classe define as propriedades de uma aula
public class Aula {
    
    private String diaAula;
    private String nomeDisciplina;
    private String horaInicio;
    private String horaFim;
    private String anotacao;
    
    public String getDiaAula() {
        
        return diaAula;
        
    }

    public void setDiaAula(String diaAula) {
        
        this.diaAula = diaAula;
        
    }
    
    public String getNomeDisciplina() {
        
        return nomeDisciplina;
        
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        
        this.nomeDisciplina = nomeDisciplina;
        
    }
    
    public String getAnotacao() {
        
        return anotacao;
        
    }

    public String getHoraInicio() {
        
        return horaInicio;
        
    }

    public String getHoraFim() {
        
        return horaFim;
        
    }

    public void setHoraInicio(String horaInicio) {
        
        this.horaInicio = horaInicio;
        
    }

    public void setHoraFim(String horaFim) {
        
        this.horaFim = horaFim;
        
    }

    public void setAnotacao(String anotacao) {
        
        this.anotacao = anotacao;
        
    }
    
    public boolean validar(){
        
       return !nomeDisciplina.isEmpty() && !diaAula.isEmpty() && !horaInicio.isEmpty() && !horaFim.isEmpty() && !anotacao.isEmpty();
       
    }
    
    public String toFileString(){
        
        return diaAula + ";" + nomeDisciplina + ";" + horaInicio + ";" + horaFim + ";" + anotacao + ";" + "\n";
        
    }
    
    @Override
    public String toString(){
        
        return nomeDisciplina;
        
    }
    
}