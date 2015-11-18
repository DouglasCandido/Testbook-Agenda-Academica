package br.com.testbook.Eventos;

import br.com.testbook.ControleComboBox;
import br.com.testbook.HorarioEscolar.Disciplina;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TelaCalendarioDeEventos extends JFrame {
    
    private JPanel painel;
    private JPanel painelBotoes;
    
    private JDateChooser calendarioDeEventos;
      
    public static JComboBox<Disciplina> disciplinasCadastradas;
    private JComboBox<String> tiposDeEventos;
    
    private JLabel lbDisciplinasCadastradas;
    private JLabel lbTiposDeEventos;
    private JLabel lbTituloEvento;
    private JLabel lbAnotacaoEvento;
    
    private JTextField txtTituloEvento;
    private JTextField txtAnotacaoEvento;
    
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JButton btnRemover;
    
    private JTable tabela;
    
    private TabelaCalendarioDeEventos modelo;
    
    private JScrollPane scrollPaneTabela;
    
    public TelaCalendarioDeEventos(){
        
        super("Calendário de eventos");
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
        
        modelo = new TabelaCalendarioDeEventos(new ArrayList<Calendario>());
        
        tabela.setModel(modelo);
        tabela.addMouseListener(new ActionTabela());
        
        scrollPaneTabela = new JScrollPane(tabela);
        
        lbDisciplinasCadastradas = new JLabel("Disciplina:");
        
        disciplinasCadastradas = new JComboBox<>();
        ControleComboBox.addItemComboBoxDisciplinas(disciplinasCadastradas);
        
        lbTiposDeEventos = new JLabel("Tipos de eventos:");
        
        tiposDeEventos = new JComboBox<>();
        tiposDeEventos.addItem("Prova");
        tiposDeEventos.addItem("Trabalho");
        tiposDeEventos.addItem("Aula de campo");
        tiposDeEventos.addItem("Devolução de livro");
        tiposDeEventos.addItem("Outro");
         
        lbTituloEvento = new JLabel("Título:");
        lbAnotacaoEvento = new JLabel("Anotação:");
        
        txtTituloEvento = new JTextField(20);
        txtAnotacaoEvento = new JTextField(20);
            
        calendarioDeEventos = new JDateChooser();
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setToolTipText("Limpa os campos de texto.");
        btnLimpar.setIcon(getImagem("Limpar1.png"));
        btnLimpar.addActionListener(new BotaoLimpar());
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setToolTipText("Armazena os dados de uma nova aula.");
        btnSalvar.setIcon(getImagem("Salvar1.png"));
        btnSalvar.addActionListener(new BotaoSalvar());
        
        btnRemover = new JButton("Remover");
        btnRemover.setToolTipText("Remove os dados de uma aula existente.");
        btnRemover.setIcon(getImagem("Remover1.png"));
        btnRemover.addActionListener(new BotaoRemover());
                        
        painel.add(lbDisciplinasCadastradas);
        painel.add(disciplinasCadastradas);
        
        painel.add(lbTiposDeEventos);
        painel.add(tiposDeEventos);
        
        painel.add(lbTituloEvento);
        painel.add(txtTituloEvento);
        
        painel.add(lbAnotacaoEvento);
        painel.add(txtAnotacaoEvento);
            
        painel.add(calendarioDeEventos);
        
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
           
            txtTituloEvento.setText("");
            txtAnotacaoEvento.setText("");
        
        }
    
    }
    
    private class BotaoSalvar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            Calendario calendario = new Calendario();
            
            Disciplina d = (Disciplina) disciplinasCadastradas.getSelectedItem();
            
            String strD = d.getNome();
            String tipoE = (String) tiposDeEventos.getSelectedItem();
            String tituloE = txtTituloEvento.getText();
            String anotacaoE = txtAnotacaoEvento.getText();
            
            Date a = calendarioDeEventos.getDate();
            SimpleDateFormat b = new SimpleDateFormat("dd/MM/yyyy");
            
            String dataE = b.format(a);
                   
            if(tabela.getSelectedRow()<0){
                
                calendario.setNomeDisciplina(strD);
                calendario.setTipoEvento(tipoE);
                calendario.setTituloEvento(tituloE);
                calendario.setAnotacaoEvento(anotacaoE);
                calendario.setDataEvento(dataE);
                 
                if (calendario.validar()) {
                    
                    modelo.addRow(calendario);
                    
                }
                
            } else {
                
                int linha  = tabela.getSelectedRow();
                
                modelo.updateRow(linha, strD, tipoE, tituloE, anotacaoE, dataE);
                
            }
            
            txtTituloEvento.setText("");
            txtAnotacaoEvento.setText("");
            
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
                
                TabelaCalendarioDeEventos tc = (TabelaCalendarioDeEventos) tabela.getModel();
                
                Calendario c = tc.get(tabela.getSelectedRow());
                
                disciplinasCadastradas.setSelectedItem(c.getNomeDisciplina());
                tiposDeEventos.setSelectedItem(c.getTipoEvento());
                txtTituloEvento.setText(c.getTituloEvento());
                txtAnotacaoEvento.setText(c.getAnotacaoEvento());
                calendarioDeEventos.setDateFormatString(c.getDataEvento());

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
        
        File f = new File("Calendario.txt");
            
        try {
            
            FileOutputStream out = new FileOutputStream(f);
            
            for(Calendario calendario : modelo.getAll()){
                
                out.write(calendario.toFileString().getBytes());
               
            }
            
        out.close();
            
        } catch (IOException ioe) {
            
            ioe.printStackTrace();
            
        }
            
    }
    
    private void lerArquivo() {
        
        File arquivo = new File("Calendario.txt");
        
        if(arquivo.exists()){
            
            try{
                
                FileReader leitorDeArquivo = new FileReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(leitorDeArquivo);
                
                String auxiliar = bufferedReader.readLine();
                
                while(auxiliar != null){
                    
                    Calendario calendario = new Calendario();
                    
                    String[] vetor = auxiliar.split(";");
                    
                    calendario.setNomeDisciplina(vetor[0]);
                    calendario.setTipoEvento(vetor[1]);
                    calendario.setTituloEvento(vetor[2]);
                    calendario.setAnotacaoEvento(vetor[3]);
                    calendario.setDataEvento(vetor[4]);

                    modelo.addRow(calendario);
                    
                    auxiliar = bufferedReader.readLine();
                    
                }
                
                bufferedReader.close();
                                   
            }catch(Exception e){
                
                e.printStackTrace();
                
            }  
            
        }
        
    }

}
