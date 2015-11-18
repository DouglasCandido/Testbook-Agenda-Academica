package br.com.testbook.Eventos;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TabelaAnotacao extends AbstractTableModel {
    private ArrayList<Anotacao> lista = null;
    private String[] colunas = {"Disciplina", "Anotação"};

    
    public TabelaAnotacao(ArrayList<Anotacao> l){
        
        if(l == null){
            
            lista = new ArrayList<Anotacao>();
            
        }else{
            
            lista = l;
            
        }
        
    }
    
    @Override
    public String getColumnName(int col){
        
        return colunas[col];
        
    }
    
    
    @Override
    public int getRowCount() {
        return lista.size();
        
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
        
    }

    
    @Override
    public Object getValueAt(int row, int col) {
        
        Anotacao a = lista.get(row);
        
        switch(col){
            
            case 0: return a.getNomeDisciplina(); 
            case 1: return a.getAnotacao();            
            default: return null;
                
        }
        
    }
    
    public Anotacao get(int col){
        
        return lista.get(col);
        
    }
    
    public void addRow(Anotacao a){
        
        this.lista.add(a);
        this.fireTableDataChanged();
        
    }
    
    public void removeRow(int linha){
        
        this.lista.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        
    }
    
    public void updateRow(int linha, String disciplina, String anotacao ){
        
        this.lista.get(linha).setNomeDisciplina(disciplina);
        this.lista.get(linha).setAnotacao(anotacao);
        this.fireTableDataChanged();
        
    }
    
    public ArrayList<Anotacao> getAll(){
        
        return this.lista;
        
    }
     
}


