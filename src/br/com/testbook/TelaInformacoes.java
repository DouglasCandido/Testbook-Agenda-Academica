package br.com.testbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaInformacoes extends JFrame {
    
    private JPanel painel;
    
    private JLabel lbSobre;
    private JLabel lbLogotipo;
    
    public TelaInformacoes(){
        
        super("Informações sobre o software");
        
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes(){
        
        painel = new JPanel();
        
        lbSobre = new JLabel("<html>"
                + "<br/> <br/> <br/>"
                + "<p> Testbook - Agenda Acadêmica 1.0 <br/>"
                + "<p> Desenvolvido em 2014 pelos alunos Anderson Kleber, Carlos Mariz e Douglas Cândido do curso técnico integrado em informática do 2° ano. <br/> <br/> <br/>"
                + "<p> Agradecimentos: A todos que nos ajudaram de alguma forma no desenvolvimento do software e"
                + "&nbsp em especial ao professor Max Miller pelo suporte durante o desenvolvimento do projeto. <br/> <br/> <br/>"
                + "Instituto Federal de Educação, Ciência e Tecnologia do Rio Grande do Norte - Campus Caicó 2015 <br/> <br/> <br/>"
                + "&copy COPYRIGHT ALL RIGHTS RESERVED"
                + "</html>");
        
        lbSobre.setFont(new Font("Arial", Font.BOLD, 13)); 
        
        lbLogotipo = new JLabel(getImagem("Logotipo2.png"));
        
        painel.add(lbSobre);
        painel.add(lbLogotipo, BorderLayout.SOUTH);
        
        this.add(painel);
        
    }
    
    private ImageIcon getImagem(String nome) {

        String caminhoDaImagem = "br/com/testbook/Imagens/" + nome;
        ClassLoader classLoader = this.getClass().getClassLoader();
        ImageIcon imagem = new ImageIcon(classLoader.getResource(caminhoDaImagem));
        return imagem;

    }
    
}
