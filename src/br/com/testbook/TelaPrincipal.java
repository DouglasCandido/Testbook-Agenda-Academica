package br.com.testbook;

import br.com.testbook.HorarioEscolar.*;
import br.com.testbook.RendimentoEscolar.*;
import br.com.testbook.Eventos.*;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TelaPrincipal extends JFrame {

    private JMenuBar barra;

    private JMenu menuHorarioEscolar;
    private JMenu menuRendimentoEscolar;
    private JMenu menuEventos;
    private JMenu menuInformacoes;

    private JMenuItem itemAdicionarDisciplina;
    private JMenuItem itemAdicionarAula;
    private JMenuItem itemHorarioDasAulas;
    private JMenuItem itemBoletim;
    private JMenuItem itemConfigurarBoletim;
    private JMenuItem itemCalendarioDeEventos;
    private JMenuItem itemAnotacoes;
    private JMenuItem itemSobre;

    private JLabel labelLogoTipo;

    public TelaPrincipal() {

        this.setTitle("Testbook");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        iniciarComponentes();
        decorarTela();
      
        this.setVisible(true);

    }

    private void iniciarComponentes() {

        barra = new JMenuBar();
        
        menuHorarioEscolar = new JMenu("Horário escolar");
        menuHorarioEscolar.setIcon(getImagem("Relogio1.png"));
        itemAdicionarDisciplina = new JMenuItem("Adicionar disciplina");
        itemAdicionarDisciplina.setIcon(getImagem("Add1.png"));
        itemAdicionarAula = new JMenuItem("Adicionar aula");
        itemAdicionarAula.setIcon(getImagem("Add1.png"));
        itemHorarioDasAulas = new JMenuItem("Horário escolar");
        itemHorarioDasAulas.setIcon(getImagem("Calendario3.png")); 
        
        menuHorarioEscolar.add(itemAdicionarDisciplina);
        menuHorarioEscolar.add(itemAdicionarAula);
        menuHorarioEscolar.add(itemHorarioDasAulas);

        menuRendimentoEscolar = new JMenu("Rendimento escolar");
        menuRendimentoEscolar.setIcon(getImagem("Umapessoa1.png"));
        itemBoletim = new JMenuItem("Boletim");
        itemBoletim.setIcon((getImagem("Lapispainel1.png")));
        itemConfigurarBoletim = new JMenuItem("Configurar boletim");
        itemConfigurarBoletim.setIcon(getImagem("Configurar1.png"));
        
        menuRendimentoEscolar.add(itemBoletim);
        menuRendimentoEscolar.add(itemConfigurarBoletim);
        
        menuEventos = new JMenu("Eventos");
        menuEventos.setIcon(getImagem("Calendario1.png"));
        itemAnotacoes = new JMenuItem("Anotações");
        itemAnotacoes.setIcon(getImagem("Agenda1.png"));
        itemCalendarioDeEventos = new JMenuItem("Calendário de eventos");
        itemCalendarioDeEventos.setIcon(getImagem("Calendario2.png"));
        
        menuEventos.add(itemCalendarioDeEventos);
        menuEventos.add(itemAnotacoes);

        menuInformacoes = new JMenu("Informações");
        menuInformacoes.setIcon(getImagem("Ajuda1.png"));
        itemSobre = new JMenuItem("Sobre o Testbook");
        itemSobre.setIcon(getImagem("Ajuda1.png"));
        
        menuInformacoes.add(itemSobre);
        
        ActionAdicionarDisciplina a1 = new ActionAdicionarDisciplina();
        itemAdicionarDisciplina.addActionListener(a1);

        ActionAdicionarAula a2 = new ActionAdicionarAula();
        itemAdicionarAula.addActionListener(a2);
        
        ActionBoletim a3 = new ActionBoletim();
        itemBoletim.addActionListener(a3);
        
        ActionConfigurarBoletim a4 = new ActionConfigurarBoletim();
        itemConfigurarBoletim.addActionListener(a4);
        
        ActionAnotacoes a5 = new ActionAnotacoes();
        itemAnotacoes.addActionListener(a5);
        
        ActionCalendarioDeEventos a6 = new ActionCalendarioDeEventos();
        itemCalendarioDeEventos.addActionListener(a6);
        
        ActionHorarioDasAulas a7 = new ActionHorarioDasAulas();
        itemHorarioDasAulas.addActionListener(a7);
        
        ActionInformacoes a8 = new ActionInformacoes();
        itemSobre.addActionListener(a8);

        barra.add(menuHorarioEscolar);
        barra.add(menuRendimentoEscolar);
        barra.add(menuEventos);
        barra.add(menuInformacoes);

        this.setJMenuBar(barra);

    }

    private void decorarTela() {

        Image imagemDeFundo = getImagem("Logotipo1.png").getImage();

        labelLogoTipo = new JLabel(new ImageIcon(imagemDeFundo));

        labelLogoTipo.setBounds(400, 400, 400, 400);

        this.add(labelLogoTipo);
        
    }

    private ImageIcon getImagem(String nome) {

        String caminhoDaImagem = "br/com/testbook/Imagens/" + nome;
        ClassLoader classLoader = this.getClass().getClassLoader();
        ImageIcon imagem = new ImageIcon(classLoader.getResource(caminhoDaImagem));
        return imagem;

    }

    private class ActionAdicionarDisciplina implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new TelaAdicionarDisciplina().setVisible(true);
            
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");

        }

    }

    private class ActionAdicionarAula implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new TelaAdicionarAula().setVisible(true);
            
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");

        }

    }
    
    private class ActionBoletim implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            new TelaBoletim().setVisible(true);
            
        }
        
    }
    
    private class ActionConfigurarBoletim implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          
            new TelaConfigurarBoletim().setVisible(true);
            
        }
           
    }
    
    private class ActionAnotacoes implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
     
                new TelaAnotacoes().setVisible(true);
                
            }
  
        }
    
    private class ActionCalendarioDeEventos implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           
            new TelaCalendarioDeEventos().setVisible(true);
            
            JOptionPane.showMessageDialog(null, "Clique no icone do calendario para marcar a data do evento.");
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            
        }
            
    }
    
    private class ActionHorarioDasAulas implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
           
            new TelaHorario().setVisible(true);
            
        }
        
    }
    
    private class ActionInformacoes implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            new TelaInformacoes().setVisible(true);
            
        }
        
    }

}
