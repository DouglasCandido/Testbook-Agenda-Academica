package br.com.testbook.HorarioEscolar;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaDisciplina extends AbstractTableModel {
    
    private ArrayList<Disciplina> lista = null;
    private String[] colunas = {"Nome","Professor","Email do Professor"};
    
    public TabelaDisciplina(ArrayList<Disciplina> l){
        
        if(l == null){
            
            lista = new ArrayList<Disciplina>();
            
        }else{
            
            lista = l;
            
        }
        
    }
    
    @Override
    public String getColumnName(int col){
        
        return colunas[col];
        
    }
    
    @Override
    public int getRowCount(){
        
        return lista.size();
        
    }
    
    @Override
    public int getColumnCount(){
        
        return colunas.length;
        
    }
    
    @Override
    public Object getValueAt(int row, int col){
        
        Disciplina p = lista.get(row);
        
        switch(col){
            
            case 0: return p.getNome();
            case 1: return p.getProfessor();
            case 2: return p.getEmailProfessor();            
            default: return null;
                
        }
        
    }    
       
    public Disciplina get(int col){
        
        return lista.get(col);
        
    }
    
    public void addRow(Disciplina p){
        
        this.lista.add(p);
        this.fireTableDataChanged();
        
    }
    
    public void removeRow(int linha){
        
        this.lista.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        
    }
    
    public void updateRow(int linha, String nome, String emailprofessor, String professor){
        
        this.lista.get(linha).setNome(nome);
        this.lista.get(linha).setEmailProfessor(emailprofessor);
        this.lista.get(linha).setProfessor(professor);
        this.fireTableDataChanged();
        
    }
    
    public ArrayList<Disciplina> getAll(){
        
        return this.lista;
        
    }
    
}
