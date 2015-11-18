package br.com.testbook;

import br.com.testbook.HorarioEscolar.Disciplina;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JComboBox;

public  class ControleComboBox {
    
    public static void addItemComboBoxDisciplinas(JComboBox<Disciplina> c){
        
        File arquivo = new File("Disciplinas.txt");
        
        if(arquivo.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(leitorDeArquivo);
                
                String auxiliar = br.readLine();
                
                while(auxiliar != null){
                    
                    Disciplina d = new Disciplina();
                
                    String[] vetor = auxiliar.split(";");  
                    
                    d.setNome(vetor[0]);
                    d.setProfessor(vetor[1]);
                    d.setEmailProfessor((vetor[2]));
                    
                    c.addItem(d);          
                    
                    auxiliar = br.readLine();
                    
                }
                
                br.close();
                                   
            }catch(Exception e){
                
                e.printStackTrace();
                
            }
            
        }
        
    }
    
}
