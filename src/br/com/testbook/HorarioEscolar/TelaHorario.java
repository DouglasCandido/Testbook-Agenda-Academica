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
import javax.swing.JTabbedPane;

public class TelaHorario extends JFrame{
    
    private JTabbedPane tabbedPane;
    
    private JPanel painelSegunda;
    private JPanel painelTerca;
    private JPanel painelQuarta;
    private JPanel painelQuinta;
    private JPanel painelSexta;
    private JPanel painelSabado;
    private JPanel painelDomingo;
    
    public TelaHorario(){
      
        super("Horário escolar");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        iniciarComponentes();
        
    }
    
    private void iniciarComponentes(){
        
        tabbedPane = new JTabbedPane();
        
        painelSegunda = new PainelHorario("Segunda");
        tabbedPane.addTab("Segunda-feira", painelSegunda);
        
        painelTerca = new PainelHorario("Terça");
        tabbedPane.addTab("Terça-feira", painelTerca);
        
        painelQuarta = new PainelHorario("Quarta");
        tabbedPane.addTab("Quarta-feira", painelQuarta);
        
        painelQuinta = new PainelHorario("Quinta");
        tabbedPane.addTab("Quinta-feira", painelQuinta);
        
        painelSexta = new PainelHorario("Sexta");
        tabbedPane.addTab("Sexta-feira", painelSexta);
        
        painelSabado = new PainelHorario("Sabado");
        tabbedPane.addTab("Sabado", painelSabado);
        
        painelDomingo = new PainelHorario("Domingo");
        tabbedPane.addTab("Domingo", painelDomingo);
        
        this.add(tabbedPane);
        
    }
    
}
