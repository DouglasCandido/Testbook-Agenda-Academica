package br.com.testbook.HorarioEscolar;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PainelHorario extends JPanel{
    
    private JPanel painel;
    
    private JLabel lbNome;
    private JLabel lbHComeco;
    private JLabel lbHFim;
    private JLabel lbAnotacao;
    
    private JTextField txtNome;
    private JTextField txtHComeco;
    private JTextField txtHFim;
    private JTextField txtAnotacao;
    
    private JTable tabela;
    
    private TabelaHorario modelo;
    
    private JScrollPane scrollPaneTabela;
    
    private String diaAula;

    public PainelHorario(String diaAula) {
        
        this.diaAula = diaAula;
        
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes(){
        
        painel = new JPanel();
        
        lbNome = new JLabel("Disciplina:");
        painel.add(lbNome);
        
        txtNome = new JTextField(20);
        txtNome.setEditable(false);
        painel.add(txtNome);
        
        lbHComeco = new JLabel("Hora de início:");
        painel.add(lbHComeco);
        
        txtHComeco = new JTextField(20);
        txtHComeco.setEditable(false);
        painel.add(txtHComeco);
        
        lbHFim = new JLabel("Hora do término:");
        painel.add(lbHFim);
        
        txtHFim = new JTextField(20);
        txtHFim.setEditable(false);
        painel.add(txtHFim);
        
        lbAnotacao = new JLabel("Anotação:");
        painel.add(lbAnotacao);
        
        txtAnotacao = new JTextField(20);
        txtAnotacao.setEditable(false);
        painel.add(txtAnotacao);
        
        tabela = new JTable();
        
        modelo = new TabelaHorario(new ArrayList<Aula>());
        
        tabela.setModel(modelo);
        tabela.addMouseListener(new ActionTabela());
        
        scrollPaneTabela = new JScrollPane(tabela);
        
        lerArquivo();
        
        this.add(painel, BorderLayout.NORTH);
        this.add(scrollPaneTabela, BorderLayout.SOUTH);

        
    }
    
    private void lerArquivo() {
        
        File arquivo = new File("Aulas.txt");
        
        if(arquivo.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);
                
                String auxiliar = bufferedReader.readLine();
                
                while(auxiliar != null){
                    
                    String[] vetor = auxiliar.split(";");
                    
                    if(vetor[0].matches(diaAula)){
                        
                        Aula aula = new Aula();

                        aula.setDiaAula(vetor[0]);
                        aula.setNomeDisciplina(vetor[1]);
                        aula.setHoraInicio(vetor[2]);
                        aula.setHoraFim(vetor[3]);
                        aula.setAnotacao(vetor[4]);

                        modelo.addRow(aula);
                        
                    }
                    
                    auxiliar = bufferedReader.readLine();
                    
                }
                
                bufferedReader.close();
                                   
            }catch(Exception e){
                
                e.printStackTrace();
                
            }  
            
        }
        
    }

    private class ActionTabela implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if (tabela.getSelectedRow() >= 0) {
                
                TabelaHorario ta = (TabelaHorario) tabela.getModel();
                
                Aula a = ta.get(tabela.getSelectedRow());
                
                
                txtNome.setText(a.getNomeDisciplina());
                txtHComeco.setText(a.getHoraInicio());
                txtHFim.setText(a.getHoraFim());
                txtAnotacao.setText(a.getAnotacao());
               
            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
    
}
