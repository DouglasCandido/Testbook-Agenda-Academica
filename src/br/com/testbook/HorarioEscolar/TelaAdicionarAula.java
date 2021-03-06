package br.com.testbook.HorarioEscolar;

import br.com.testbook.ControleComboBox;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
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
import javax.swing.JOptionPane;

public class TelaAdicionarAula extends JFrame {
    
    private JPanel painel;
    private JPanel painelBotoes;
    
    private JLabel lbDiaDaAula;
    private JLabel lbDisciplina;
    private JLabel lbHoraInicio;
    private JLabel lbHoraFim;
    private JLabel lbAnotacao;
    
    private JTextField txtHoraInicio;
    private JTextField txtHoraFim;
    private JTextField txtAnotacao;
    
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JButton btnRemover;
    
    private JComboBox<String> diasDasAulas;
    public static JComboBox<Disciplina> disciplinasCadastradas;
    
    private JTable tabela;
    
    private TabelaAula modelo;
    
    private JScrollPane scrollPaneTabela;
    
    public TelaAdicionarAula() {
        
        super("Adicionar uma nova aula");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
       
        
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes (){
        
        painel = new JPanel();
        painelBotoes = new JPanel();
        
        tabela = new JTable();
        
        modelo = new TabelaAula(new ArrayList<Aula>());
        
        tabela.setModel(modelo);
        tabela.addMouseListener(new ActionTabela());
        
        scrollPaneTabela = new JScrollPane(tabela);
        
        lbDiaDaAula = new JLabel("Dia da aula");
        lbDisciplina = new JLabel("Disciplina:");
        
        lbHoraInicio = new JLabel("Hora de início:");
        lbHoraFim = new JLabel("Hora do fim:");
        
        txtHoraInicio = new JTextField(4); 
        txtHoraFim = new JTextField(4);  
        
        lbAnotacao = new JLabel("Anotação:");
        txtAnotacao = new JTextField(20);
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setIcon(getImagem("Limpar1.png"));
        btnLimpar.setToolTipText("Limpa os campos de texto.");
        btnLimpar.addActionListener(new BotaoLimpar());
        
        btnSalvar = new JButton("Salvar", getImagem("Salvar1.png"));
        btnSalvar.setToolTipText("Armazena os dados de uma nova aula.");
        btnSalvar.addActionListener(new BotaoSalvar());
        
        btnRemover = new JButton("Remover", getImagem("Remover1.png"));
        btnRemover.setToolTipText("Remove os dados de uma aula existente.");
        btnRemover.addActionListener(new BotaoRemover());
        
        diasDasAulas = new JComboBox<String>();
        diasDasAulas.addItem("Domingo");
        diasDasAulas.addItem("Segunda"); 
        diasDasAulas.addItem("Terça");
        diasDasAulas.addItem("Quarta");
        diasDasAulas.addItem("Quinta");
        diasDasAulas.addItem("Sexta");
        diasDasAulas.addItem("Sábado");
   
        disciplinasCadastradas = new JComboBox<Disciplina>(); 
        ControleComboBox.addItemComboBoxDisciplinas(disciplinasCadastradas);
        
        painel.add(lbDiaDaAula);
        painel.add(diasDasAulas);
        
        painel.add(lbDisciplina);
        painel.add(disciplinasCadastradas);
        
        painel.add(lbHoraInicio);
        painel.add(txtHoraInicio);
        
        painel.add(lbHoraFim);
        painel.add(txtHoraFim);
        
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
            
            txtHoraInicio.setText("");
            txtHoraFim.setText("");
            txtAnotacao.setText("");
            
        }
        
    }
      
    private class BotaoSalvar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            Aula aula = new Aula();
            
            String dia = (String) diasDasAulas.getSelectedItem();
            
            Disciplina b = (Disciplina) disciplinasCadastradas.getSelectedItem();
            String d = b.getNome();
            
            String i = txtHoraInicio.getText();
            String f = txtHoraFim.getText();
            String a = txtAnotacao.getText();
            
            aula.setDiaAula(dia);
            aula.setNomeDisciplina(d);
            aula.setHoraInicio(i);
            aula.setHoraFim(f);
            aula.setAnotacao(a);
             
            if (aula.validar()) {

                modelo.addRow(aula);
                
            } else {

                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                
            }
            
            txtHoraInicio.setText("");
            txtHoraFim.setText("");
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
                
                TabelaAula ta = (TabelaAula) tabela.getModel();
                
                Aula a = ta.get(tabela.getSelectedRow());
                
                diasDasAulas.setSelectedItem(a.getDiaAula());
                disciplinasCadastradas.setSelectedItem(a.getNomeDisciplina());
                txtHoraInicio.setText(a.getHoraInicio());
                txtHoraFim.setText(a.getHoraFim());
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
        
        File f = new File("Aulas.txt");
            
        try {
            
            FileOutputStream out = new FileOutputStream(f);
            
            for(Aula adicionada : modelo.getAll()){
                
               out.write(adicionada.toFileString().getBytes());
               
            }
            
        out.close();
            
        } catch (IOException ioe) {
            
            ioe.printStackTrace();
            
        }
            
    }
    
    private void lerArquivo() {
        
        File arquivo = new File("Aulas.txt");
        
        if(arquivo.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);
                
                String auxiliar = bufferedReader.readLine();
                
                while(auxiliar != null){
                    
                    Aula aula = new Aula();
                    
                    String[] vetor = auxiliar.split(";");
                    
                    aula.setDiaAula(vetor[0]);
                    aula.setNomeDisciplina(vetor[1]);
                    aula.setHoraInicio(vetor[2]);
                    aula.setHoraFim(vetor[3]);
                    aula.setAnotacao(vetor[4]);
                    
                    modelo.addRow(aula);
                    
                    auxiliar = bufferedReader.readLine();
                    
                }
                
                bufferedReader.close();
                                   
            }catch(Exception e){
                
                e.printStackTrace();
                
            }  
            
        }
        
    }
    
}
