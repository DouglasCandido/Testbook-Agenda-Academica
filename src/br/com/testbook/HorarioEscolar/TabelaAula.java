package br.com.testbook.HorarioEscolar;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaAula extends AbstractTableModel {
    
    private ArrayList<Aula> lista = null;
    private String[] colunas = {"Dia da semana", "Disciplina","Hora de início", "Hora do fim", "Anotação"};
    
    public TabelaAula(ArrayList<Aula> l){
        
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
            
            case 0: return a.getDiaAula();
            case 1: return a.getNomeDisciplina();
            case 2: return a.getHoraInicio();
            case 3: return a.getHoraFim();
            case 4: return a.getAnotacao();            
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
            String dia, 
            String d){
        
        this.lista.get(linha).setHoraInicio(horaInicio);
        this.lista.get(linha).setHoraFim(horaFim);
        this.lista.get(linha).setAnotacao(anotacao);
        this.lista.get(linha).setDiaAula(dia);
        this.lista.get(linha).setNomeDisciplina(d);
        this.fireTableDataChanged();
        
    }
    
    public ArrayList<Aula> getAll(){
        
        return this.lista;
        
    }
    
}
