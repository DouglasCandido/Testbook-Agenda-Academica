package br.com.testbook.RendimentoEscolar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaConfigurarBoletim extends JFrame{
    
    private JPanel painel;
    
    private JLabel lbPeso1;
    private JLabel lbPeso2;
    private JLabel lbPeso3;
    private JLabel lbPeso4;
    private JLabel lbMediaNecessaria;
    
    private JTextField txtPeso1;
    private JTextField txtPeso2;
    private JTextField txtPeso3;
    private JTextField txtPeso4;
    private JTextField txtMediaNecessaria;
    
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JButton btnRestaurar;
    
    public TelaConfigurarBoletim() {
        
        super("Configuração do boletim");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        
        iniciarComponentes();
        
    }

    private void iniciarComponentes() {
        
        painel = new JPanel();
          
        lbPeso1 = new JLabel("Peso 1:");
        painel.add(lbPeso1);
        txtPeso1 = new JTextField(3);
        painel.add(txtPeso1);
        txtPeso1.setText(LeitorDeValoresParaTela.getPeso1());
        
        lbPeso2 = new JLabel("Peso 2:");
        painel.add(lbPeso2);
        txtPeso2 = new JTextField(3);
        painel.add(txtPeso2);
        txtPeso2.setText(LeitorDeValoresParaTela.getPeso2());
        
        lbPeso3 = new JLabel("Peso 3:");
        painel.add(lbPeso3);
        txtPeso3 = new JTextField(3);
        painel.add(txtPeso3);
        txtPeso3.setText(LeitorDeValoresParaTela.getPeso3());
        
        lbPeso4 = new JLabel("Peso 4:");
        painel.add(lbPeso4);
        txtPeso4 = new JTextField(3);
        painel.add(txtPeso4);
        txtPeso4.setText(LeitorDeValoresParaTela.getPeso4());
        
        lbMediaNecessaria = new JLabel("Media Necessária:");;
        painel.add(lbMediaNecessaria);
        txtMediaNecessaria = new JTextField(3);
        painel.add(txtMediaNecessaria);
        txtMediaNecessaria.setText(LeitorDeValoresParaTela.getMediaNecessaria());
        
        btnLimpar = new JButton("Limpar");
        btnLimpar.setIcon(getImagem("Limpar1.png"));
        btnLimpar.setToolTipText("Limpa os campos de texto.");
        btnLimpar.addActionListener(new BotaoLimpar());
        painel.add(btnLimpar);
            
        btnSalvar = new JButton("Reconfigurar");
        btnSalvar.setIcon(getImagem("Reconfigurar1.png"));
        btnSalvar.setToolTipText("Reconfigura o boletim de acordo com os valores digitados.");
        btnSalvar.addActionListener(new BotaoSalvar());
        painel.add(btnSalvar);
        
        btnRestaurar = new JButton("Restaurar valores padrões");
        btnRestaurar.setIcon(getImagem("Voltar1.png"));
        btnRestaurar.setToolTipText("Restaura os valores padrões do boletim.");
        btnRestaurar.addActionListener(new BotaoRestaurar());
        painel.add(btnRestaurar);
       
        this.add(painel);
        this.pack();
        this.setLocationRelativeTo(null);
        
    }
    
    private ImageIcon getImagem(String nome) {

        String caminhoDaImagem = "br/com/testbook/Imagens/" + nome;
        ClassLoader classLoader = this.getClass().getClassLoader();
        ImageIcon imagem = new ImageIcon(classLoader.getResource(caminhoDaImagem));
        return imagem;

    }

    private class BotaoRestaurar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Boletim.restaurarArquivo();
            JOptionPane.showMessageDialog(null, "Restaurado com sucesso!");
            
        }
        
    } 
    
    private class BotaoLimpar implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            txtPeso1.setText("");
            txtPeso2.setText("");
            txtPeso3.setText("");
            txtPeso4.setText("");
            txtMediaNecessaria.setText("");
            
        }
        
    }

    private class BotaoSalvar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            double[] n = new double[5];
            n[0] = Double.parseDouble(txtPeso1.getText());
            n[1] = Double.parseDouble(txtPeso2.getText());
            n[2] = Double.parseDouble(txtPeso3.getText());
            n[3] = Double.parseDouble(txtPeso4.getText());
            n[4] = Double.parseDouble(txtMediaNecessaria.getText());

            Boletim.salvarValoresConfigurados(n);
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        }
        
    }
    
}
