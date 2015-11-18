package br.com.testbook.Eventos;

import br.com.testbook.HorarioEscolar.Disciplina;
import br.com.testbook.ControleComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TelaAnotacoes extends JFrame {
    
    private JPanel painel;
    private JPanel painelBotoes;
    
    private JLabel lbAnotacao;
    private JLabel lbDisciplina;
    
    private JTextField txtAnotacao;
    
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JButton btnRemover;
    
    public static JComboBox<Disciplina> disciplinasCadastradas;
    
    private JTable tabela;
    
    private TabelaAnotacao modelo;
    
    private JScrollPane scrollPaneTabela;

    public TelaAnotacoes() {
    
        super("Adicionar Anotacao");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes (){
        
        painel = new JPanel(new GridLayout(2, 14));
        painelBotoes = new JPanel(new FlowLayout());
        
        tabela = new JTable();
        
        modelo = new TabelaAnotacao(new ArrayList<Anotacao>());
        
        tabela.setModel(modelo);
        tabela.addMouseListener(new ActionTabela());
        
        scrollPaneTabela = new JScrollPane(tabela);        
        
        lbAnotacao = new JLabel("Anotação:");
        txtAnotacao = new JTextField(70);
        
        lbDisciplina = new JLabel("Disciplina");
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setIcon(getImagem("Limpar1.png"));
        btnLimpar.setToolTipText("Limpa os campos de texto.");
        btnLimpar.addActionListener(new BotaoLimpar());
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setIcon(getImagem("Salvar1.png"));
        btnSalvar.setToolTipText("Adicionar anotação.");
        btnSalvar.addActionListener(new BotaoSalvar());
        
        btnRemover = new JButton("Remover");
        btnRemover.setIcon(getImagem("Remover1.png"));
        btnRemover.setToolTipText("Remover anotação.");
        btnRemover.addActionListener(new BotaoRemover());
        
        disciplinasCadastradas = new JComboBox<Disciplina>(); 
        ControleComboBox.addItemComboBoxDisciplinas(disciplinasCadastradas);
        
        painel.add(lbDisciplina);  
        painel.add(disciplinasCadastradas);
        
        painel.add(lbAnotacao);
        painel.add(txtAnotacao);
        
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnRemover);
        
        this.add(painel, BorderLayout.NORTH);
        this.add(scrollPaneTabela, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);
        this.addWindowListener(new FecharTela());
        
        lerArquivo();
        
    }
    
    private ImageIcon getImagem(String nome) {

        String caminhoDaImagem = "br/com/testbook/Imagens/" + nome;
        ClassLoader classLoader = this.getClass().getClassLoader();
        ImageIcon imagem = new ImageIcon(classLoader.getResource(caminhoDaImagem));
        return imagem;

    }
    
    private class BotaoLimpar implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            txtAnotacao.setText("");
     
        }
        
    }
    
    private class BotaoSalvar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            Disciplina b = (Disciplina) disciplinasCadastradas.getSelectedItem();
            String d = b.getNome();
            
            String a = txtAnotacao.getText();
            
            Anotacao anotacao = new Anotacao();
           
            if(tabela.getSelectedRow()<0){
                
                anotacao.setNomeDisciplina(d);
                anotacao.setAnotacao(a);
                 
                if (anotacao.validar()) {
                    
                    modelo.addRow(anotacao);
                    
                }
                
            } else {
                
                int linha  = tabela.getSelectedRow();
                modelo.updateRow(linha, d, a);
                
            }
            
            
            txtAnotacao.setText("");
            
        }
        
    }
    
     private class BotaoRemover implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            
            int linha =  tabela.getSelectedRow();
            
            if(linha>=0){
                
                modelo.removeRow(linha);
                
            }
            
        }                                
    }
     
     private class ActionTabela implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if (tabela.getSelectedRow() >= 0) {
                
                TabelaAnotacao a = (TabelaAnotacao) tabela.getModel();
                Anotacao aa = a.get(tabela.getSelectedRow());  
                
                disciplinasCadastradas.setSelectedItem(aa.getNomeDisciplina());
                
                txtAnotacao.setText(aa.getAnotacao());
               
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
     
     private class FecharTela implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            
            salvarArquivo();

        }

        @Override
        public void windowClosed(WindowEvent e) {
            
        }

        @Override
        public void windowIconified(WindowEvent e) {
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            
        }

        @Override
        public void windowActivated(WindowEvent e) {
            
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            
        }

        
       
     }
    
    private void salvarArquivo() {
        
        File f = new File("Anotacoes.txt");
            
        try {
            
            FileOutputStream out = new FileOutputStream(f);
            
            for(Anotacao adicionada : modelo.getAll()){
                
               out.write(adicionada.toFileString().getBytes());
               
            }
            
        out.close();
            
        } catch (IOException ioe) {
            
            ioe.printStackTrace();
            
        }
            
    }
    
    private void lerArquivo() {
        
        File arquivo = new File("Anotacoes.txt");
        
        if(arquivo.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);
                
                String auxiliar = bufferedReader.readLine();
                
                while(auxiliar != null){
                    
                    Anotacao a = new Anotacao();
                    String[] vetor = auxiliar.split(";");
                    
                    a.setNomeDisciplina(vetor[0]);                 
                    a.setAnotacao(vetor[1]);
                    modelo.addRow(a);
                    
                    auxiliar = bufferedReader.readLine();

                }
                
                bufferedReader.close();
                                   
            }catch(Exception e){
                
                e.printStackTrace();
                
            } 
            
        }
        
    }
    
}
