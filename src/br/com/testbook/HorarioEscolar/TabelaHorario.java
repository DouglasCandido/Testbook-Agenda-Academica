package br.com.testbook.HorarioEscolar;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaHorario extends AbstractTableModel {
    
    private ArrayList<Aula> lista = null;
    private String[] colunas = {"Disciplina","Hora de início", "Hora do fim", "Anotação"};
    
    public TabelaHorario(ArrayList<Aula> l){
        
        if(l == null){
            
            lista = new ArrayList<Aula>();
            
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
        
        Aula a = lista.get(row);
        
        switch(col){
            
            case 0: return a.getNomeDisciplina();
            case 1: return a.getHoraInicio();
            case 2: return a.getHoraFim();
            case 3: return a.getAnotacao();            
            default: return null;
                
        }
        
    }    
       
    public Aula get(int col){
        
        return lista.get(col);
        
    }
    
    public void addRow(Aula a){
        
        this.lista.add(a);
        this.fireTableDataChanged();
        
    }
    
    public void removeRow(int linha){
        
        this.lista.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        
    }
    
    public void updateRow(int linha, 
            String horaInicio, 
            String horaFim, 
            String anotacao,
            String d){
        
        this.lista.get(linha).setHoraInicio(horaInicio);
        this.lista.get(linha).setHoraFim(horaFim);
        this.lista.get(linha).setAnotacao(anotacao);
        this.lista.get(linha).setNomeDisciplina(d);
        this.fireTableDataChanged();
        
    }
    
    public ArrayList<Aula> getAll(){
        
        return this.lista;
        
    }
    
}