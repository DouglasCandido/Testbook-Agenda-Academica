package br.com.testbook.HorarioEscolar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class TelaAdicionarDisciplina extends JFrame {
   
    private JPanel painel; 
    private JPanel painelBotoes;
    
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtProfessor;
    
    private JLabel lbNome;
    private JLabel lbEmail;
    private JLabel lbProfessor;
    
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JButton btnRemover;
    
    private JTable tabela;
    
    private TabelaDisciplina modelo;
    
    private JScrollPane scrollPaneTabela; 
    
    public TelaAdicionarDisciplina () {
        
        super("Adicionar uma nova disciplina");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes(){
       
        painel = new JPanel();
        painelBotoes = new JPanel();

        tabela = new JTable();
        modelo = new TabelaDisciplina(new ArrayList<Disciplina>());
        tabela.setModel(modelo);
        tabela.addMouseListener(new ActionTabela());
        scrollPaneTabela = new JScrollPane(tabela);
        
        lbNome = new JLabel("Nome da disciplina:");
        txtNome = new JTextField(20);
        
        lbEmail = new JLabel("Email do professor:");
        txtEmail = new JTextField(20);
        
        lbProfessor = new JLabel("Nome do professor:");
        txtProfessor = new JTextField(20);
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setIcon(getImagem("Limpar1.png"));
        btnLimpar.setToolTipText("Limpa os campos de texto.");
        btnLimpar.addActionListener(new BotaoLimpar());
        
        btnSalvar = new JButton("Salvar", getImagem("Salvar1.png"));
        btnSalvar.setToolTipText("Armazena os dados de uma nova disciplina.");
        btnSalvar.addActionListener(new BotaoSalvar());
        
        btnRemover = new JButton("Remover", getImagem("Remover1.png"));
        btnRemover.setToolTipText("Remove os dados de uma disciplina existente.");
        btnRemover.addActionListener(new BotaoRemover());
         
        painel.add(lbNome);
        painel.add(txtNome);
        
        painel.add(lbEmail);
        painel.add(txtEmail);
        
        painel.add(lbProfessor);
        painel.add(txtProfessor); 
        
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnRemover);
    
        this.add(painel, BorderLayout.NORTH);
        this.add(scrollPaneTabela, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);
        this.addWindowListener(new FecharTela());
        
        lerArquivo();
        
    }   
    
    //Método que retorna uma imagem
    private ImageIcon getImagem(String nome){  
              
        String caminhoDaImagem = "br/com/testbook/Imagens/" + nome;  
        ClassLoader classLoader = this.getClass().getClassLoader();  
        ImageIcon imagem = new ImageIcon(classLoader.getResource(caminhoDaImagem));  
        return imagem;  

    }  
    
    private class BotaoLimpar implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            txtNome.setText("");
            txtEmail.setText("");
            txtProfessor.setText("");
            
        }
        
    }
    
    private class BotaoSalvar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            Disciplina d = new Disciplina();
            
            String[] vetor = new String[3];
           
            vetor[0] = txtNome.getText();
            vetor[1] = txtEmail.getText();
            vetor[2] = txtProfessor.getText();
           
            d.setNome(vetor[0]);
            d.setEmailProfessor(vetor[1]);
            d.setProfessor(vetor[2]);

                 
            if (d.validar()) {
                
                modelo.addRow(d);

            } else {
                
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                
            }
            
            txtNome.setText("");
            txtEmail.setText("");
            txtProfessor.setText("");
                      
        }
        
    }
    
    private void SalvarArquivo() {
            
            File f = new File("Disciplinas.txt");
            
        try {
            
            FileOutputStream out = new FileOutputStream(f);
            
            for(Disciplina adicionada : modelo.getAll()){
                
               out.write(adicionada.toFileString().getBytes());
               
            }
            
            out.close();
            
        } catch (IOException ioe) {
            
            ioe.printStackTrace();
            
        }
        
    }
        
    private void lerArquivo(){
        
        File arquivo = new File("Disciplinas.txt");
        
        if(arquivo.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(leitorDeArquivo);
                
                String auxiliar = br.readLine();
                
                while(auxiliar != null){
                    
                    Disciplina disciplina = new Disciplina();
                    
                    String[] vetor = auxiliar.split(";");
                    
                    disciplina.setNome(vetor[0]);
                    disciplina.setProfessor(vetor[1]);
                    disciplina.setEmailProfessor(vetor[2]);
                    
                    modelo.addRow(disciplina);
                    
                    auxiliar = br.readLine();
                    
                }
                
                br.close();
                                   
            }catch(Exception e){
                
                e.printStackTrace();
                
            }
            
        }
        
    }
        
    private class ActionTabela implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if (tabela.getSelectedRow() >= 0) {
                
                TabelaDisciplina td = (TabelaDisciplina) tabela.getModel();
                Disciplina d = td.get(tabela.getSelectedRow());
                
                txtNome.setText(d.getNome());
                txtEmail.setText(d.getEmailProfessor());
                txtProfessor.setText(d.getProfessor());
                
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

    private class BotaoRemover implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            
            int linha =  tabela.getSelectedRow();
            
            if(linha>=0){
                
                modelo.removeRow(linha);
                
            }
            
        }
        
    }
              
    private class FecharTela implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            
               SalvarArquivo();

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
       
}
