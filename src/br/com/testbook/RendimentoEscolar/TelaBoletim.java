package br.com.testbook.RendimentoEscolar;

import br.com.testbook.HorarioEscolar.Disciplina;
import br.com.testbook.ControleComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;


public class TelaBoletim extends JFrame {
    
    private JPanel painel; 
    private JPanel painelBotoes;
    
    public static JComboBox<Disciplina> disciplinasCadastradas;
    
    private JLabel lbMedia1;
    private JLabel lbMedia2;
    private JLabel lbMedia3;
    private JLabel lbMedia4;
    
    private JTextField txtMedia1;
    private JTextField txtMedia2;
    private JTextField txtMedia3;
    private JTextField txtMedia4;
    
    private JButton btnCalcularMediaFinalAritmetica;
    private JButton btnCalcularMediaFinalPonderada;
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JButton btnRemover;
    private JButton btnDeQuantoPrecisa;
    
    private JTable tabela;
    
    private TabelaBoletim modelo;
    
    private JScrollPane scrollPaneTabela;
    
    public TelaBoletim(){
        
        super("Boletim");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes(){
        
        painel = new JPanel();
        painelBotoes = new JPanel(new FlowLayout());
       
        modelo = new TabelaBoletim(new ArrayList<>()); 
        
        tabela = new JTable(modelo); 
        
        scrollPaneTabela = new JScrollPane(tabela);
       
        disciplinasCadastradas = new JComboBox<>();
        ControleComboBox.addItemComboBoxDisciplinas(disciplinasCadastradas);
        
        lbMedia1 = new JLabel("Média 1° bimestre:");
        lbMedia2 = new JLabel("Média 2° bimestre:");
        lbMedia3 = new JLabel("Média 3° bimestre:");
        lbMedia4 = new JLabel("Média 4° bimestre:");       
        
        txtMedia1 = new JTextField(3);
        txtMedia2 = new JTextField(3);
        txtMedia3 = new JTextField(3);
        txtMedia4 = new JTextField(3);
        
        btnCalcularMediaFinalAritmetica = new JButton("Calcular média final aritmética");
        btnCalcularMediaFinalAritmetica.setToolTipText("Calcula a média final de acordo com o número de bimestres.");
        btnCalcularMediaFinalAritmetica.addActionListener(new BotaoCalcularMediaFinalAritmetica());
        
        btnCalcularMediaFinalPonderada = new JButton("Calcular média final ponderada");
        btnCalcularMediaFinalPonderada.setToolTipText("Calcula a média final de acordo com os pesos dos bimestres.");
        btnCalcularMediaFinalPonderada.addActionListener(new BotaoCalcularMediaFinalPonderada());
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setIcon(getImagem("Limpar1.png"));
        btnLimpar.setToolTipText("Limpa os campos de texto.");
        btnLimpar.addActionListener(new BotaoLimpar());
        
        btnRemover = new JButton("Remover");
        btnRemover.setIcon(getImagem("Remover1.png"));
        btnRemover.setToolTipText("Remove uma linha de dados do boletim, removendo todas as médias bimestrais informadas e a média final da disciplina.");
        btnRemover.addActionListener(new BotaoRemover());

        btnSalvar = new JButton("Gerar boletim");
        btnSalvar.setIcon(getImagem("Ok1.png"));
        btnSalvar.setToolTipText("Gera uma nova linha de dados no boletim, adicionando todas as médias bimestrais informadas e a média final da disciplina.");
        btnSalvar.addActionListener(new BotaoSalvar());
        
        btnDeQuantoPrecisa = new JButton("De quanto você precisa?");
        //btnDeQuantoPrecisa.setIcon(getImagem(""));
        btnDeQuantoPrecisa.setToolTipText("Calcula a pontuação necessária para ser aprovado na disciplina.");
        btnDeQuantoPrecisa.addActionListener(new BotaoDeQuantoPrecisa());
        
        
        painel.add(disciplinasCadastradas);
        
        painel.add(lbMedia1);
        painel.add(txtMedia1);
        
        painel.add(lbMedia2);
        painel.add(txtMedia2);
        
        painel.add(lbMedia3);
        painel.add(txtMedia3);
        
        painel.add(lbMedia4);
        painel.add(txtMedia4);
        
        painelBotoes.add(btnCalcularMediaFinalAritmetica);
        painelBotoes.add(btnCalcularMediaFinalPonderada); 
        painelBotoes.add(btnDeQuantoPrecisa);
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
    
    private void salvarArquivo() {
        
        File f = new File("Boletim.txt");
            
        try {
            
            FileOutputStream out = new FileOutputStream(f);
            
            for(Boletim boletim : modelo.getAll()){
                
               out.write(boletim.toFileString().getBytes());
               
            }
            
        out.close();
            
        } catch (IOException ioe) {
            
            ioe.printStackTrace();
            
        }
            
    }
    
        private void lerArquivo() {
        
        File arquivo = new File("Boletim.txt");
        
        if(arquivo.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);
                
                String auxiliar = bufferedReader.readLine();
                
                while(auxiliar != null){
                    
                    Boletim b = new Boletim();
                    
                    String[] vetor = auxiliar.split(";");
                    
                    double[] m = new double[6];
                    
                    m[0] = Double.parseDouble(vetor[1]);
                    m[1] = Double.parseDouble(vetor[2]);
                    m[2] = Double.parseDouble(vetor[3]);
                    m[3] = Double.parseDouble(vetor[4]);
                    m[4] = Double.parseDouble(vetor[5]);
                    m[5] = Double.parseDouble(vetor[6]);
                    
                    modelo.addRow(b);
                    
                    auxiliar = bufferedReader.readLine();
                    
                    b.setNomeDisciplina(vetor[0]);
                    b.setMedia1(m[0]);
                    b.setMedia2(m[1]);
                    b.setMedia3(m[2]);
                    b.setMedia4(m[3]);
                    b.setMediaFinalAritmetica(m[4]);
                    b.setMediaFinalPonderada(m[5]);

                }
                
                bufferedReader.close();
                                   
            }catch(Exception e){
                
                e.printStackTrace();
                
            } 
            
        }
        
    }

    private class BotaoDeQuantoPrecisa implements ActionListener {

        public boolean validar(String[] vetor){
      
        String strMedia1 = vetor[0];
        String strMedia2 = vetor[1];
        String strMedia3 = vetor[2];
      
      return !strMedia1.isEmpty() && !strMedia2.isEmpty() && !strMedia3.isEmpty();
       
    }
      
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Boletim b = new Boletim();
            
            String[] medias = new String[4];
            
            medias[0] = txtMedia1.getText();
            medias[1] = txtMedia2.getText();
            medias[2] = txtMedia3.getText();
            
            if(validar(medias)) {
                
                b.setMedia1(Double.parseDouble(medias[0]));
                b.setMedia2(Double.parseDouble(medias[1]));
                b.setMedia3(Double.parseDouble(medias[2]));

                double precisoponderada = b.MediaPresicaPonderada();
                double preciso = b.MediaPrecisaAritmetica();
                JOptionPane.showMessageDialog(null, "Você precisa tirar" + preciso + "No 4º bimestre," + "se sua media for aritmética");
                JOptionPane.showMessageDialog(null, "Você precisa tirar" + precisoponderada + "No 4º bimestre," + "se sua media for ponderada");        
                
            } else {
                
               JOptionPane.showMessageDialog(null, "Você precisa escrever pelo menos 3 medias");
               
            }
                
            }
        
        }   
    
        
        private class ActionTabela implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            if (tabela.getSelectedRow() >= 0) {
                
                TabelaBoletim tb = (TabelaBoletim) tabela.getModel();
                Boletim b = tb.get(tabela.getSelectedRow());
                
                disciplinasCadastradas.setSelectedItem(b.getNomeDisciplina());
                txtMedia1.setText(String.valueOf(b.getMedia1()));
                txtMedia1.setText(String.valueOf(b.getMedia2()));
                txtMedia1.setText(String.valueOf(b.getMedia3()));
                txtMedia1.setText(String.valueOf(b.getMedia4()));
                
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
    
    private class BotaoCalcularMediaFinalAritmetica implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Boletim b = new Boletim();
            
            String[] medias = new String[4];
            
            medias[0] = txtMedia1.getText();
            medias[1] = txtMedia2.getText();
            medias[2] = txtMedia3.getText();
            medias[3] = txtMedia4.getText();
            
            b.setMedia1(Double.parseDouble(medias[0]));
            b.setMedia2(Double.parseDouble(medias[1]));
            b.setMedia3(Double.parseDouble(medias[2]));
            b.setMedia4(Double.parseDouble(medias[3]));
            
            double calculo = b.calcularMediaFinalAritmetica();
            
            if(calculo >= Boletim.getMediaNecessaria()){
                
                JOptionPane.showMessageDialog(null, b.getSituacaoAritmetica() + ", sua média final é: " + b.calcularMediaFinalAritmetica());
                
            }else if(calculo < Boletim.getMediaNecessaria()){
                
                JOptionPane.showMessageDialog(null, b.getSituacaoAritmetica() + ", sua média final é: " + b.calcularMediaFinalAritmetica());

            }
           
        }
               
    }
    
    
    private class BotaoCalcularMediaFinalPonderada implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            
            Boletim b = new Boletim();
            
            String[] medias = new String[4];
            
            medias[0] = txtMedia1.getText();
            medias[1] = txtMedia2.getText();
            medias[2] = txtMedia3.getText();
            medias[3] = txtMedia4.getText();
            
            b.setMedia1(Double.parseDouble(medias[0]));
            b.setMedia2(Double.parseDouble(medias[1]));
            b.setMedia3(Double.parseDouble(medias[2]));
            b.setMedia4(Double.parseDouble(medias[3]));
            
            double calculo = b.calcularMediaFinalPonderada();
            
            if(calculo >= Boletim.getMediaNecessaria()){
                
                JOptionPane.showMessageDialog(null, b.getSituacaoPonderada()+ ", sua média final é: " + b.calcularMediaFinalPonderada());
                
            }else if(calculo < Boletim.getMediaNecessaria()){
                
                JOptionPane.showMessageDialog(null, b.getSituacaoPonderada()+ ", sua média final é: " + b.calcularMediaFinalPonderada());
                
            }
            
        }
        
    }
    
    
    private class BotaoLimpar implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            txtMedia1.setText("");
            txtMedia2.setText("");
            txtMedia3.setText("");
            txtMedia4.setText("");
        }
        
    }
     
    private class BotaoSalvar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Boletim b = new Boletim();

            Disciplina d = (Disciplina) disciplinasCadastradas.getSelectedItem();         
            String strD = d.getNome();
            
            double mA;
            double mP;
            
            String sA; 
            String sP; 
            
            mA = b.calcularMediaFinalAritmetica();
            mP = b.calcularMediaFinalPonderada();
            
            sA = b.getSituacaoAritmetica();
            sP = b.getSituacaoPonderada();
            
            String[] n = new String[4];  
            n[0] = txtMedia1.getText();
            n[1] = txtMedia2.getText();
            n[2] = txtMedia3.getText();
            n[3] = txtMedia4.getText();
                   
            double[] m = new double[4];                 
            m[0] = Double.parseDouble(n[0]);
            m[1] = Double.parseDouble(n[1]);
            m[2] = Double.parseDouble(n[2]);
            m[3] = Double.parseDouble(n[3]);
                     
            if(tabela.getSelectedRow()<0){
 
                b.setNomeDisciplina(strD);
                b.setMedia1(m[0]);
                b.setMedia2(m[1]);
                b.setMedia3(m[2]);
                b.setMedia4(m[3]);
                b.setMediaFinalAritmetica(mA);
                b.setMediaFinalPonderada(mP);
                
                if (b.validar(m)) {
                    
                    modelo.addRow(b);
                    
                }
                
            } else {
                
                int linha  = tabela.getSelectedRow();
                
                modelo.updateRow(linha, strD, m[0], m[1], m[2], m[3], 
                        b.calcularMediaFinalAritmetica(), b.calcularMediaFinalPonderada()
                        , b.getSituacaoAritmetica(), b.getSituacaoPonderada());
                    
           }
            
            txtMedia1.setText("");
            txtMedia2.setText("");
            txtMedia3.setText("");
            txtMedia4.setText("");
            
        }

    }
    
    private class BotaoRemover implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         
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
    
}
