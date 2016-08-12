/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import FileIOAux.ImageAux;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author toze
 */
public class MessagePane {

    public static final int AVISO = 1;
    public static final int INFORMACAO = 0;
    public static final int ACAO = 2;
    javax.swing.JPanel panel;
    int largura;
    int altura;
    java.awt.Color corsistema;
    javax.swing.JButton[] botoes;
    javax.swing.JComboBox[] comboboxes;
    int resposta;
    String titulo;
    int tipo;
    java.awt.Color corborda;
    javax.swing.JLabel[] objetos;
    int mensagem;
    java.awt.Component componentpai;
    String textomensagem;
    String[] textobotoes;

    public MessagePane( java.awt.Component comp, int tipo, java.awt.Color cor, String titulo, int largura, int altura, String texto, String[] bt) {
        panel = new javax.swing.JPanel(null);
        this.largura = largura;
        this.altura = altura;
        this.corsistema = cor;
        this.titulo = titulo;
        this.tipo = tipo;
        mensagem = 0;
        this.componentpai = comp;
        this.textomensagem = texto;
        this.textobotoes = bt;
    }
    
    public MessagePane( java.awt.Component comp, int tipo, java.awt.Color cor, String titulo, int largura, int altura, javax.swing.JLabel[] objetos, String texto, String[] bt) {
        panel = new javax.swing.JPanel(null);
        this.largura = largura;
        this.altura = altura;
        this.corsistema = cor;
        this.titulo = titulo;
        this.tipo = tipo;
        this.objetos = objetos;
        mensagem = 1;
        this.componentpai = comp;
        this.textomensagem = texto;
        this.textobotoes = bt;
    }

    public MessagePane( java.awt.Component comp, int tipo, java.awt.Color cor, String titulo, int largura, int altura, javax.swing.JButton[] bt, String texto, String[] bto) {
        panel = new javax.swing.JPanel(null);
        this.largura = largura;
        this.altura = altura;
        this.botoes = bt;
        this.corsistema = cor;
        this.titulo = titulo;
        this.tipo = tipo;
        mensagem = 2;
        this.componentpai = comp;
        this.textomensagem = texto;
        this.textobotoes = bto;
    }

    public MessagePane( java.awt.Component comp, int tipo, java.awt.Color cor, String titulo, int largura, int altura, javax.swing.JButton[] bt, javax.swing.JComboBox[] comboboxes, String texto, String[] bto) {
        panel = new javax.swing.JPanel(null);
        this.largura = largura;
        this.altura = altura;
        this.botoes = bt;
        this.comboboxes = comboboxes;
        this.corsistema = cor;
        this.titulo = titulo;
        this.tipo = tipo;
        mensagem = 3;
        this.componentpai = comp;
        this.textomensagem = texto;
        this.textobotoes = bto;
    }
    
    
    public int showMessage(){
        switch(mensagem) {
            case 1: 
                showObjectMessage();
                break;
            default:
                showSimpleMessage();
                break;
        }
        return resposta;
    }

    private void showSimpleMessage() {
        resposta = -1;
        switch (tipo) {
            case 1:
                corborda = new Color(254, 0, 0);
                break;
            case 2:
                corborda = new Color(0, 0, 254);
                break;
            default:
                corborda = new Color(254, 254, 152);
                break;
        }
        javax.swing.JDialog dialogo = new javax.swing.JDialog();
        dialogo.setPreferredSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setResizable(false);
        dialogo.setMinimumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setMaximumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setBackground(new java.awt.Color(224, 224, 224));
        dialogo.setTitle(titulo);
        panel.setPreferredSize(new java.awt.Dimension(this.largura, altura));
        panel.setBackground(new java.awt.Color(254, 254, 254));
        panel.setBounds(0, 0, this.largura, this.altura);
        javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(corsistema, 2), javax.swing.BorderFactory.createLineBorder(corborda, 2));
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1), 1), border11);
        panel.setBorder(border22);
        javax.swing.JLabel label = new javax.swing.JLabel(textomensagem);

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        Font font = new Font("Cantarell", java.awt.Font.PLAIN, 12);
        label.setFont(font);
        int l = (int) (font.getStringBounds(textomensagem, frc).getWidth());
        int a = (int) (font.getStringBounds(textomensagem, frc).getHeight());
        label.setPreferredSize(new java.awt.Dimension(l + 50, a + 60));
        label.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        label.setBounds((largura / 2 - (l + 50) / 2), altura / 2 - (a + 60), l + 50, a + 50);
        panel.add(label);
        botoes = new javax.swing.JButton[textobotoes.length];
        if (textobotoes.length == 2) {
            botoes[1] = new javax.swing.JButton();
            botoes[1].setFocusable(false);
            botoes[1].setPreferredSize(new Dimension(90, 40));
            botoes[1].setBounds(largura / 2 - 95, altura - 90, 90, 40);
            botoes[1].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/delete.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                botoes[1].setIcon(iconbtsearch);
            }
            botoes[1].setToolTipText(textobotoes[1]);
            botoes[1].setMnemonic(textobotoes[1].charAt(2));
            botoes[1].setBackground(new Color(51, 102, 153));
            botoes[1].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            botoes[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 0;
                }
            });
            panel.add(botoes[1]);
        }
        if ((textobotoes.length == 2) || (textobotoes.length == 1)) {
            botoes[0] = new javax.swing.JButton();
            botoes[0].setFocusable(false);
            botoes[0].setMnemonic(textobotoes[0].charAt(2));
            botoes[0].setPreferredSize(new Dimension(90, 40));
            if (textobotoes.length == 2) {
                botoes[0].setBounds(largura / 2 + 5, altura - 90, 90, 40);
            } else {
                botoes[0].setBounds(largura / 2 - 40, altura - 90, 90, 40);
            }
            botoes[0].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                botoes[0].setIcon(iconbtsearch);
            }
            botoes[0].setToolTipText(textobotoes[0]);
            botoes[0].setBackground(new Color(51, 102, 153));
            botoes[0].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            botoes[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 1;
                }
            });
            panel.add(botoes[0]);
        }
        dialogo.add(panel);
        dialogo.setLocationRelativeTo(componentpai);
        dialogo.setModal(true);
        dialogo.setVisible(true);
    }
    
    
    private void showObjectMessage() {
        resposta = -1;
        switch (tipo) {
            case 1:
                corborda = new Color(254, 0, 0);
                break;
            case 2:
                corborda = new Color(0, 0, 254);
                break;
            default:
                corborda = new Color(254, 254, 152);
                break;
        }
        javax.swing.JDialog dialogo = new javax.swing.JDialog();
        dialogo.setPreferredSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setResizable(false);
        dialogo.setMinimumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setMaximumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setBackground(new java.awt.Color(224, 224, 224));
        dialogo.setTitle(titulo);
        panel.setPreferredSize(new java.awt.Dimension(this.largura, altura));
        panel.setBackground(new java.awt.Color(254, 254, 254));
        panel.setBounds(0, 0, this.largura, this.altura);
        javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(corsistema, 2), javax.swing.BorderFactory.createLineBorder(corborda, 2));
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1), 1), border11);
        panel.setBorder(border22);

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        Font font = new Font("Cantarell", java.awt.Font.PLAIN, 12);
        int k = 0;
        int alturaauxiliar = 0;
        while (k < objetos.length){
            int l = (int) (font.getStringBounds(objetos[k].getText(), frc).getWidth());
            int a = (int) (font.getStringBounds(objetos[k].getText(), frc).getHeight());
            objetos[k].setBounds((largura / 2 - (l + 50) / 2), alturaauxiliar + 20, l + 50, a + 10);
            alturaauxiliar += a + 10;
            panel.add(objetos[k]);
            k++;
        }
        botoes = new javax.swing.JButton[textobotoes.length];
        if (textobotoes.length == 2) {
            botoes[1] = new javax.swing.JButton();
            botoes[1].setFocusable(false);
            botoes[1].setPreferredSize(new Dimension(90, 40));
            botoes[1].setBounds(largura / 2 - 95, altura - 90, 90, 40);
            botoes[1].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/delete.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                botoes[1].setIcon(iconbtsearch);
            }
            botoes[1].setToolTipText(textobotoes[1]);
            botoes[1].setMnemonic(textobotoes[1].charAt(2));
            botoes[1].setBackground(new Color(51, 102, 153));
            botoes[1].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            botoes[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 0;
                }
            });
            panel.add(botoes[1]);
        }
        if ((textobotoes.length == 2) || (textobotoes.length == 1)) {
            botoes[0] = new javax.swing.JButton();
            botoes[0].setFocusable(false);
            botoes[0].setMnemonic(textobotoes[0].charAt(2));
            botoes[0].setPreferredSize(new Dimension(90, 40));
            if (textobotoes.length == 2) {
                botoes[0].setBounds(largura / 2 + 5, altura - 90, 90, 40);
            } else {
                botoes[0].setBounds(largura / 2 - 45, altura - 90, 90, 40);
            }
            botoes[0].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                botoes[0].setIcon(iconbtsearch);
            }
            botoes[0].setToolTipText(textobotoes[0]);
            botoes[0].setBackground(new Color(51, 102, 153));
            botoes[0].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            botoes[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 1;
                }
            });
            panel.add(botoes[0]);
        }
        dialogo.add(panel);
        dialogo.setLocationRelativeTo(componentpai);
        dialogo.setModal(true);
        dialogo.setVisible(true);
    }

}
