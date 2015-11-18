package br.com.testbook.Eventos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaCalendarioDeEventos extends AbstractTableModel{
    
    private ArrayList<Calendario> lista;
    
    private final String headers[] = 
                        {"Disciplina", "Tipo de evento", "Título do evento", "Anotação do evento",  
                            "Data do evento"};
    
    
    public TabelaCalendarioDeEventos(ArrayList<Calendario> l){
        
        if(l==null){
            
            lista = new ArrayList<>();
            
        }else{
            
            lista = l;
            
        }
        
    }

    @Override
    public int getRowCount() {
        
        return lista.size();
        
    }

    @Override
    public int getColumnCount() {
        
        return headers.length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Calendario c =  lista.get(rowIndex);
        
        switch(columnIndex){
            
            case 0: return c.getNomeDisciplina();
            case 1: return c.getTipoEvento();
            case 2: return c.getTituloEvento();
            case 3: return c.getAnotacaoEvento();
            case 4: return c.getDataEvento();
                
            default: return "N/A";
                
        }
        
    }
    
    @Override
    public String getColumnName(int index){
        
        return headers[index];
        
    }
    
    public Calendario get(int index){
        
        return lista.get(index);
        
    }
    
    public void addRow(Calendario c){
        
        this.lista.add(c);
        
        this.fireTableDataChanged();
        
    }
    
    public void removeRow(int linha){
        
        this.lista.remove(linha);
        
        this.fireTableRowsDeleted(linha, linha);
        
    }
    
    public void updateRow(int linha, String nomeDisciplina, String tipoEvento, 
            String tituloEvento, String anotacaoEvento, String dataEvento){
        
        this.lista.get(linha).setNomeDisciplina(nomeDisciplina);
        this.lista.get(linha).setTipoEvento(tipoEvento);
        this.lista.get(linha).setTituloEvento(tituloEvento);
        this.lista.get(linha).setAnotacaoEvento(anotacaoEvento);
        this.lista.get(linha).setDataEvento(dataEvento);
        
        this.fireTableDataChanged();
        
    }
    
    public ArrayList<Calendario> getAll(){
        
        return this.lista;
        
    }
    
}