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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author toze
 */
public class MessagePane {

    public static final int AVISO = 1;
    public static final int INFORMACAO = 0;
    public static final int ACAO = 2;
    public static final java.awt.Color BACKGROUND_COLOR = new java.awt.Color(255,255,255);  
    private javax.swing.JPanel panel;
    int largura;
    int altura;
    java.awt.Color corsistema;
    private javax.swing.JButton[] botoes;
    javax.swing.JComponent[] componentes;
    javax.swing.JComboBox<String>[] comboboxes;
    javax.swing.JPanel panelcolocado;
    javax.swing.JDialog dialogo;
    int resposta;
    String titulo;
    int tipo;
    java.awt.Color corborda;
    javax.swing.JLabel[] objetos;
    java.awt.Component componentpai;
    String textomensagem;
    String[] textobotoes;
    boolean visivel;
    private java.awt.Color corpanel;

    public MessagePane( java.awt.Component comp, int tipo, java.awt.Color cor, String titulo, int largura, int altura, String texto, String[] bt) {
        this.visivel = false;
        this.panel = new javax.swing.JPanel(null);
        this.largura = largura;
        this.altura = altura;
        this.corsistema = cor;
        this.titulo = titulo;
        this.tipo = tipo;
        this.componentpai = comp;
        this.textomensagem = texto;
        this.textobotoes = bt;
        this.showSimpleMessage();
        this.corpanel = BACKGROUND_COLOR;
    }
    
    public MessagePane( java.awt.Component comp, int tipo, java.awt.Color cor, String titulo, int largura, int altura, javax.swing.JLabel[] objetos, String texto, String[] bt) {
        this.visivel = false;
        this.panel = new javax.swing.JPanel(null);
        this.largura = largura;
        this.altura = altura;
        this.corsistema = cor;
        this.titulo = titulo;
        this.tipo = tipo;
        this.objetos = objetos;
        this.componentpai = comp;
        this.textomensagem = texto;
        this.textobotoes = bt;
        this.showLabelMessage();
        this.corpanel = BACKGROUND_COLOR;
    }
    
    public MessagePane( java.awt.Component comp, int tipo, java.awt.Color cor, String titulo, int largura, int altura, javax.swing.JPanel panelcolocado, String texto, String[] bto) {
        this.visivel = false;
        this.panel = new javax.swing.JPanel(null);
        this.largura = largura;
        this.altura = altura;
        this.corsistema = cor;
        this.titulo = titulo;
        this.tipo = tipo;
        this.panelcolocado = panelcolocado;
        this.componentpai = comp;
        this.textomensagem = texto;
        this.textobotoes = bto;
        this.showPanelMessage();
        this.corpanel = BACKGROUND_COLOR;
    }

    public MessagePane( java.awt.Component comp, int tipo, java.awt.Color cor, String titulo, int largura, int altura, javax.swing.JComponent[] componentes, String texto, String[] bto) {
        this.visivel = false;
        this.panel = new javax.swing.JPanel(null);
        this.largura = largura;
        this.altura = altura;
        this.componentes = componentes;
        this.corsistema = cor;
        this.titulo = titulo;
        this.tipo = tipo;
        this.componentpai = comp;
        this.textomensagem = texto;
        this.textobotoes = bto;
        this.showComponentMessage();
        this.corpanel = BACKGROUND_COLOR;
    }
    
    public boolean isVisible(){
        return visivel;
    }
    
    
    public int showMessage(){
        if (dialogo != null) {
            dialogo.setLocationRelativeTo(componentpai);
            dialogo.setModal(true);
            dialogo.setVisible(true);
            this.visivel = true;
        }
        this.visivel = false;
        return resposta;
    }
    
    public void closeWindow(){
        this.dialogo.setVisible(false);
        this.dialogo.dispose();
        visivel = false;
    }

    private void showSimpleMessage() {
        resposta = -1;
        switch (tipo) {
            case 1:
                corborda = new Color(254, 0, 0);
                break;
            case 2:
                corborda = new Color(204, 204, 254);
                break;
            default:
                corborda = new Color(254, 254, 152);
                break;
        }
        dialogo = new javax.swing.JDialog();
        dialogo.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        dialogo.setPreferredSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setResizable(false);
        dialogo.setMinimumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setMaximumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setBackground(new java.awt.Color(224, 224, 224));
        dialogo.setTitle(titulo);
        getPanel().setPreferredSize(new java.awt.Dimension(this.largura, altura));
        getPanel().setBackground(getPanelColor());
        getPanel().setBounds(0, 0, this.largura, this.altura);
        javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(corsistema, 2), javax.swing.BorderFactory.createLineBorder(corborda, 2));
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1), 1), border11);
        getPanel().setBorder(border22);
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
        getPanel().add(label);
        setButtons(new javax.swing.JButton[textobotoes.length]);
        if (textobotoes.length == 2) {
            getButtons()[1] = new javax.swing.JButton();
            getButtons()[1].setFocusable(false);
            getButtons()[1].setPreferredSize(new Dimension(90, 40));
            getButtons()[1].setBounds(largura / 2 - 95, altura - 90, 90, 40);
            getButtons()[1].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/delete.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                getButtons()[1].setIcon(iconbtsearch);
            }
            getButtons()[1].setToolTipText(textobotoes[1]);
            getButtons()[1].setMnemonic(textobotoes[1].charAt(2));
            getButtons()[1].setBackground(new Color(51, 102, 153));
            getButtons()[1].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            getButtons()[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 0;
                }
            });
            getPanel().add(getButtons()[1]);
        }
        if ((textobotoes.length == 2) || (textobotoes.length == 1)) {
            getButtons()[0] = new javax.swing.JButton();
            getButtons()[0].setFocusable(false);
            getButtons()[0].setMnemonic(textobotoes[0].charAt(2));
            getButtons()[0].setPreferredSize(new Dimension(90, 40));
            if (textobotoes.length == 2) {
                getButtons()[0].setBounds(largura / 2 + 5, altura - 90, 90, 40);
            } else {
                getButtons()[0].setBounds(largura / 2 - 40, altura - 90, 90, 40);
            }
            getButtons()[0].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                getButtons()[0].setIcon(iconbtsearch);
            }
            getButtons()[0].setToolTipText(textobotoes[0]);
            getButtons()[0].setBackground(new Color(51, 102, 153));
            getButtons()[0].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            getButtons()[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 1;
                }
            });
            getPanel().add(getButtons()[0]);
        }
        if (textobotoes.length == 1) {
            dialogo.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e) {
                    if ((e.getKeyCode() == KeyEvent.VK_ENTER)||(e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                        closeWindow();
                    }
                }
            });
        }
        dialogo.add(getPanel());
    }
    
    
    private void showLabelMessage() {
        resposta = -1;
        switch (tipo) {
            case 1:
                corborda = new Color(254, 0, 0);
                break;
            case 2:
                corborda = new Color(204, 204, 254);
                break;
            default:
                corborda = new Color(254, 254, 152);
                break;
        }
        dialogo = new javax.swing.JDialog();
        dialogo.setPreferredSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setResizable(false);
        dialogo.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        dialogo.setMinimumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setMaximumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setBackground(new java.awt.Color(224, 224, 224));
        dialogo.setTitle(titulo);
        getPanel().setPreferredSize(new java.awt.Dimension(this.largura, altura));
        getPanel().setBackground(getPanelColor());
        getPanel().setBounds(0, 0, this.largura, this.altura);
        javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(corsistema, 2), javax.swing.BorderFactory.createLineBorder(corborda, 2));
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1), 1), border11);
        getPanel().setBorder(border22);

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
            getPanel().add(objetos[k]);
            k++;
        }
        setButtons(new javax.swing.JButton[textobotoes.length]);
        if (textobotoes.length == 2) {
            getButtons()[1] = new javax.swing.JButton();
            getButtons()[1].setFocusable(false);
            getButtons()[1].setPreferredSize(new Dimension(90, 40));
            getButtons()[1].setBounds(largura / 2 - 95, altura - 90, 90, 40);
            getButtons()[1].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/delete.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                getButtons()[1].setIcon(iconbtsearch);
            }
            getButtons()[1].setToolTipText(textobotoes[1]);
            getButtons()[1].setMnemonic(textobotoes[1].charAt(2));
            getButtons()[1].setBackground(new Color(51, 102, 153));
            getButtons()[1].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            getButtons()[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 0;
                }
            });
            getPanel().add(getButtons()[1]);
        }
        if ((textobotoes.length == 2) || (textobotoes.length == 1)) {
            getButtons()[0] = new javax.swing.JButton();
            getButtons()[0].setFocusable(false);
            getButtons()[0].setMnemonic(textobotoes[0].charAt(2));
            getButtons()[0].setPreferredSize(new Dimension(90, 40));
            if (textobotoes.length == 2) {
                getButtons()[0].setBounds(largura / 2 + 5, altura - 90, 90, 40);
            } else {
                getButtons()[0].setBounds(largura / 2 - 45, altura - 90, 90, 40);
            }
            getButtons()[0].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                getButtons()[0].setIcon(iconbtsearch);
            }
            getButtons()[0].setToolTipText(textobotoes[0]);
            getButtons()[0].setBackground(new Color(51, 102, 153));
            getButtons()[0].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            getButtons()[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 1;
                }
            });
            getPanel().add(getButtons()[0]);
        }
        if (textobotoes.length == 1) {
            dialogo.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e) {
                    if ((e.getKeyCode() == KeyEvent.VK_ENTER)||(e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                        closeWindow();
                    }
                }
            });
        }
        dialogo.add(getPanel());
    }
    
    
    private void showComponentMessage() {
        resposta = -1;
        switch (tipo) {
            case 1:
                corborda = new Color(254, 0, 0);
                break;
            case 2:
                corborda = new Color(204, 204, 254);
                break;
            default:
                corborda = new Color(254, 254, 152);
                break;
        }
        dialogo = new javax.swing.JDialog();
        dialogo.setPreferredSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setResizable(false);
        dialogo.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        dialogo.setMinimumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setMaximumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setBackground(new java.awt.Color(224, 224, 224));
        dialogo.setTitle(titulo);
        getPanel().setPreferredSize(new java.awt.Dimension(this.largura, altura));
        getPanel().setBackground(getPanelColor());
        getPanel().setBounds(0, 0, this.largura, this.altura);
        javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(corsistema, 2), javax.swing.BorderFactory.createLineBorder(corborda, 2));
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1), 1), border11);
        getPanel().setBorder(border22);

        int k = 0;
        while (k < componentes.length){
            int l = componentes[k].getWidth();
            int a = componentes[k].getHeight();
            componentes[k].setBounds(0, 0, 200, 100);
            getPanel().add(componentes[k]);
            k++;
        }
        setButtons(new javax.swing.JButton[textobotoes.length]);
        if (textobotoes.length == 2) {
            getButtons()[1] = new javax.swing.JButton();
            getButtons()[1].setFocusable(false);
            getButtons()[1].setPreferredSize(new Dimension(90, 40));
            getButtons()[1].setBounds(largura / 2 - 95, altura - 90, 90, 40);
            getButtons()[1].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/delete.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                getButtons()[1].setIcon(iconbtsearch);
            }
            getButtons()[1].setToolTipText(textobotoes[1]);
            getButtons()[1].setMnemonic(textobotoes[1].charAt(2));
            getButtons()[1].setBackground(new Color(51, 102, 153));
            getButtons()[1].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            getButtons()[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 0;
                }
            });
            getPanel().add(getButtons()[1]);
        }
        if ((textobotoes.length == 2) || (textobotoes.length == 1)) {
            getButtons()[0] = new javax.swing.JButton();
            getButtons()[0].setFocusable(false);
            getButtons()[0].setMnemonic(textobotoes[0].charAt(2));
            getButtons()[0].setPreferredSize(new Dimension(90, 40));
            if (textobotoes.length == 2) {
                getButtons()[0].setBounds(largura / 2 + 5, altura - 90, 90, 40);
            } else {
                getButtons()[0].setBounds(largura / 2 - 45, altura - 90, 90, 40);
            }
            getButtons()[0].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                getButtons()[0].setIcon(iconbtsearch);
            }
            getButtons()[0].setToolTipText(textobotoes[0]);
            getButtons()[0].setBackground(new Color(51, 102, 153));
            getButtons()[0].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            getButtons()[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 1;
                }
            });
            getPanel().add(getButtons()[0]);
        }
        if (textobotoes.length == 1) {
            dialogo.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e) {
                    if ((e.getKeyCode() == KeyEvent.VK_ENTER)||(e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                        closeWindow();
                    }
                }
            });
        }
        dialogo.add(getPanel());
    }
    
    private void showPanelMessage() {
        resposta = -1;
        switch (tipo) {
            case 1:
                corborda = new Color(254, 0, 0);
                break;
            case 2:
                corborda = new Color(204, 204, 254);
                break;
            default:
                corborda = new Color(254, 254, 152);
                break;
        }
        dialogo = new javax.swing.JDialog();
        dialogo.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        dialogo.setPreferredSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setResizable(false);
        dialogo.setMinimumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setMaximumSize(new java.awt.Dimension(this.largura, altura));
        dialogo.setBackground(new java.awt.Color(224, 224, 224));
        dialogo.addMouseListener(new MouseAdapter(){
         
            @Override
            public void mousePressed(MouseEvent e) {
               dialogo.requestFocusInWindow();
            }

            
        });
        dialogo.setTitle(titulo);
        if (panelcolocado.getPreferredSize().getHeight() > this.altura -100) {
            panelcolocado.setPreferredSize(new Dimension((int)panelcolocado.getPreferredSize().getWidth(),this.altura -100));
        }
        if (panelcolocado.getPreferredSize().getWidth() > this.largura -20) {
            panelcolocado.setPreferredSize(new Dimension(this.largura - 20, (int)panelcolocado.getPreferredSize().getHeight()));
        }
        getPanel().setPreferredSize(new java.awt.Dimension(this.largura, altura));
        getPanel().setBackground(getPanelColor());
        getPanel().setBounds(0, 0, this.largura, this.altura);
      
        javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(corsistema, 2), javax.swing.BorderFactory.createLineBorder(corborda, 2));
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 1, 1), 1), border11);
        getPanel().setBorder(border22);
        
        int l = (int) panelcolocado.getPreferredSize().getWidth();
        int a = (int) panelcolocado.getPreferredSize().getHeight();
        panelcolocado.setBounds(largura / 2 - l /2, altura / 2 - a + 20 , l, a);
        
        getPanel().add(panelcolocado);
        if (getPanel().getComponent(0).getLocation().getY() < 0) {
            getPanel().getComponent(0).setLocation((int)panelcolocado.getBounds().getX(), 10);
        }
        setButtons(new javax.swing.JButton[textobotoes.length]);
        if (textobotoes.length == 2) {
            getButtons()[1] = new javax.swing.JButton();
            getButtons()[1].setFocusable(false);
            getButtons()[1].setPreferredSize(new Dimension(90, 40));
            getButtons()[1].setBounds(largura / 2 - 95, altura - 90, 90, 40);
            getButtons()[1].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/delete.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                getButtons()[1].setIcon(iconbtsearch);
            }
            getButtons()[1].setToolTipText(textobotoes[1]);
            getButtons()[1].setMnemonic(textobotoes[1].charAt(2));
            getButtons()[1].setBackground(new Color(51, 102, 153));
            getButtons()[1].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            getButtons()[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 0;
                }
            });
            getPanel().add(getButtons()[1]);
        }
        if ((textobotoes.length == 2) || (textobotoes.length == 1)) {
            getButtons()[0] = new javax.swing.JButton();
            getButtons()[0].setFocusable(false);
            getButtons()[0].setMnemonic(textobotoes[0].charAt(2));
            getButtons()[0].setPreferredSize(new Dimension(90, 40));
            if (textobotoes.length == 2) {
                getButtons()[0].setBounds(largura / 2 + 5, altura - 90, 90, 40);
            } else {
                getButtons()[0].setBounds(largura / 2 - 45, altura - 90, 90, 40);
            }
            getButtons()[0].setFocusPainted(false);
            java.awt.image.BufferedImage imagebtsearch = null;
            try {
                imagebtsearch = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
            } catch (IOException ex) {
                Logger.getLogger(ImageAux.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagebtsearch != null) {
                javax.swing.ImageIcon iconbtsearch = new javax.swing.ImageIcon(imagebtsearch);
                getButtons()[0].setIcon(iconbtsearch);
            }
            getButtons()[0].setToolTipText(textobotoes[0]);
            getButtons()[0].setBackground(new Color(51, 102, 153));
            getButtons()[0].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            getButtons()[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogo.setVisible(false);
                    resposta = 1;
                }
            });
            getPanel().add(getButtons()[0]);
        } 
        if (textobotoes.length == 1) {
            dialogo.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e) {
                    if ((e.getKeyCode() == KeyEvent.VK_ENTER)||(e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                        closeWindow();
                    }
                }
            });
        }
        dialogo.add(getPanel());
    }

    /**
     * @return the panel
     */
    public javax.swing.JPanel getPanel() {
        return panel;
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(javax.swing.JPanel panel) {
        this.panel = panel;
    }

    /**
     * @return the botoes
     */
    public javax.swing.JButton[] getButtons() {
        return botoes;
    }

    /**
     * @param botoes the botoes to set
     */
    public void setButtons(javax.swing.JButton[] botoes) {
        this.botoes = botoes;
    }
    
    public javax.swing.JButton getLeftButton() {
        return botoes[1];
    }
    
    public javax.swing.JButton getRightButton() {
        return botoes[0];
    }

    /**
     * @return the corpanel
     */
    public java.awt.Color getPanelColor() {
        return corpanel;
    }

    /**
     * @param corpanel the corpanel to set
     */
    public void setPanelColor(java.awt.Color corpanel) {
        this.corpanel = corpanel;
    }
    
}
