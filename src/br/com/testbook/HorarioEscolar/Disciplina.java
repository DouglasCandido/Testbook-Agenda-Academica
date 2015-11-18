package br.com.testbook.HorarioEscolar;

public class Disciplina {

    private String nome;
    private String professor;
    private String emailProfessor;
    
    public String getNome() {
        
        return nome;
        
    }

    public void setNome(String nome) {
        
        this.nome = nome;
        
    }

    public String getProfessor() {
        
        return professor;
        
    }

    public void setProfessor(String professor) {
        
        this.professor = professor;
        
    }

    public String getEmailProfessor() {
        
        return emailProfessor;
        
    }

    public void setEmailProfessor(String emailprofessor) {
        
        this.emailProfessor = emailprofessor;
        
    }
    
    public boolean validar(){
        
       return !nome.isEmpty() && !emailProfessor.isEmpty() && !professor.isEmpty();
       
    }
    
    public String toString(){
        
        return nome;
        
    }
    
    public String toFileString(){
        
        return nome + ";" + professor + ";" + emailProfessor + "\n";
        
    }
    
}
