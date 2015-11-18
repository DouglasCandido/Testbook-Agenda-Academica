package br.com.testbook.RendimentoEscolar;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaBoletim extends AbstractTableModel{
    
    private ArrayList<Boletim> lista;
    
    private final String headers[] = 
                        {"Disciplina", "N1", "N2", "N3", "N4", 
                            "Média final Aritmética", "Média final ponderada", 
                            "Situação aritmética", "Situação ponderada"};
    
    
    public TabelaBoletim(ArrayList<Boletim> l){
        
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
        
        Boletim b =  lista.get(rowIndex);
        
        switch(columnIndex){
            
            case 0: return b.getNomeDisciplina();
            case 1: return b.getMedia1();
            case 2: return b.getMedia2();
            case 3: return b.getMedia3();
            case 4: return b.getMedia4();
            case 5: return b.calcularMediaFinalAritmetica();
            case 6: return b.calcularMediaFinalPonderada();
            case 7: return b.getSituacaoAritmetica();
            case 8: return b.getSituacaoPonderada();
                
            default: return "N/A";
                
        }
        
    }
    
    @Override
    public String getColumnName(int index){
        
        return headers[index];
        
    }
    
    public Boletim get(int index){
        
        return lista.get(index);
        
    }
    
    public void addRow(Boletim b){
        
        this.lista.add(b);
        
        this.fireTableDataChanged();
        
    }
    
    public void removeRow(int linha){
        
        this.lista.remove(linha);
        
        this.fireTableRowsDeleted(linha, linha);
        
    }
    
    public void updateRow(int linha, 
                String disciplina, 
                double n1, double n2, double n3, double n4, 
                double notaFinalAritmetica, double notaFinalPonderada, 
                String situacaoAritmetica, String situacaoPonderada){
        
        this.lista.get(linha).setNomeDisciplina(disciplina);
        this.lista.get(linha).setMedia1(n1);
        this.lista.get(linha).setMedia2(n2);
        this.lista.get(linha).setMedia3(n3);
        this.lista.get(linha).setMedia4(n4);
        this.lista.get(linha).setMediaFinalAritmetica(notaFinalAritmetica);
        this.lista.get(linha).setMediaFinalPonderada(notaFinalPonderada);
        
        this.fireTableDataChanged();
        
    }
    
    public ArrayList<Boletim> getAll(){
        
        return this.lista;
        
    }
    
}

