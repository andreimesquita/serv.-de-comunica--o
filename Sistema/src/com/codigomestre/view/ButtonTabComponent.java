package com.codigomestre.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

public class ButtonTabComponent extends JPanel {
   private final JTabbedPane pane;

     //
    //CONSTRUTOR
   //
   //Início...
   public ButtonTabComponent() {
       //unset default FlowLayout' gaps
       super(new FlowLayout(FlowLayout.LEFT, 0, 0));
       if (pane == null) {
           throw new NullPointerException("TabbedPane is null");
       }
       this.pane = pane;
       setOpaque(false);

       //faz a JLabel ler o título do JTabbedPane
       JLabel label = new JLabel() {
           public String getText() {
               int i = pane.indexOfTabComponent(ButtonTabComponent.this);
               if (i != -1) {
                   return pane.getTitleAt(i);
               }
               return null;
           }
       };

       add(label);
       //adiciona mais espaço entre a label e o botão
       label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
       //tab button
       JButton button = new TabButton();
       add(button);
       //adiciona mais espaço para o topo do componente
       setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
   }//Fim do construtor.

     //
    //CLASSE TABBUTTON
   //
   //Define as características do botão fechar.
   //
   //Início
   private class TabButton extends JButton implements ActionListener {
       public TabButton() {
           int size = 17;
           setPreferredSize(new Dimension(size, size));
           setToolTipText("Fechar esta aba!");
           //Faz o botão ser igual para todas as Laf's
           setUI(new BasicButtonUI());
           //Torna-o transparente
           setContentAreaFilled(false);
           //Não necessidade de estar com focusable
           setFocusable(false);
           setBorder(BorderFactory.createEtchedBorder());
           setBorderPainted(false);
           //Fazendo um efeito de rolagem
           //usamos o mesmo listener para todos os botões
           addMouseListener(buttonMouseListener);
           setRolloverEnabled(true);
           //Fecha a guia apropriada, clicando no botão
           addActionListener(this);
       }

       public void actionPerformed(ActionEvent e) {
           JOptionPane.showMessageDialog(null, "Funcionalidade ainda não implementada.");
       }

       //pinta o X
       protected void paintComponent(Graphics g) {
           super.paintComponent(g);
           Graphics2D g2 = (Graphics2D) g.create();
           //mudança na imagem para botões pressionados
           if (getModel().isPressed()) {
               g2.translate(1, 1);
           }
           g2.setStroke(new BasicStroke(2));
           g2.setColor(Color.BLACK);
           if (getModel().isRollover()) {
               g2.setColor(Color.MAGENTA);
           }
           int delta = 6;
           g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
           g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
           g2.dispose();
       }
   }//Fim da classe TabButton.

     //
    //MOUSELISTENER
   //
   //Define os eventos de entrada e saida do mouse.
   //
   //Início...
   private final static MouseListener buttonMouseListener = new MouseAdapter() {
       public void mouseEntered(MouseEvent e) {
           Component component = e.getComponent();
           if (component instanceof AbstractButton) {
               AbstractButton button = (AbstractButton) component;
               button.setBorderPainted(true);
           }
       }

       public void mouseExited(MouseEvent e) {
           Component component = e.getComponent();
           if (component instanceof AbstractButton) {
               AbstractButton button = (AbstractButton) component;
               button.setBorderPainted(false);
           }
       }
   };//Fim dos Listeners.
}
