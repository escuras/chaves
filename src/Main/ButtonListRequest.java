/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Cursor;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author toze
 */
public class ButtonListRequest {

    private List<javax.swing.JButton> bLista;
    private Set mater;
    private Dimension dim;
    private Langs.Locale lingua;
    private javax.swing.JTabbedPane tpanel;
    private javax.swing.JPanel pane;
    private Color btcor1;
    private Color btcor2;
    private Color panelcor;
    private java.util.Iterator<?> iterador;
    private int tipopesquisa;
    private String url;
    private javax.swing.JLabel labelativa;
    private javax.swing.JDialog dialogoanterior;

    public ButtonListRequest(String url, javax.swing.JDialog dialogo, RequestList req, String csv, Langs.Locale lingua, javax.swing.JTabbedPane tpanel, int tipopesquisa, Color panelcolor) {
        this.mater = new HashSet<>();
        this.lingua = lingua;
        this.url = url;
        this.dialogoanterior = dialogo;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 18));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        this.panelcor = panelcolor;
        pane = new javax.swing.JPanel();
        pane.setPreferredSize(tpanel.getPreferredSize());
        DataBase.DataBase db = new DataBase.DataBase(csv);
        dim = new Dimension(80, 80);
        this.btcor1 = new Color(54, 54, 154);
        this.btcor2 = new Color(145, 145, 254);
        this.tpanel = tpanel;
        this.tipopesquisa = tipopesquisa;
        int val = req.getTypeOfMaterial().getMaterialTypeID();
        switch (tipopesquisa) {
            case 0:
                if (val == 1) {
                    mater = new TreeSet<>(db.getClassrooms(0));
                } else {
                    mater = new TreeSet<>(db.getMaterialsByType(val));
                }
                break;
            case 1:
                if (val == 1) {
                    mater = new TreeSet<>(db.getClassrooms(1));
                } else {
                    mater = new TreeSet<>(db.getMaterialsByType(val));
                }
                break;
            default:
                if (val == 1) {
                    mater = new TreeSet<>(db.getClassrooms(2));
                } else {
                    mater = new TreeSet<>(db.getMaterialsByType(val));
                }
                break;
        }
        iterador = mater.iterator();
    }

    public String[] getListOfMaterialType() {
        String[] nomes = new String[this.mater.size()];
        int i = 0;
        if (this.iterador.next() instanceof Clavis.Material) {
            for (Object n : this.mater) {
                Clavis.Material m = (Clavis.Material) n;
                nomes[i] = lingua.translate(m.getDescription());
                i++;
            }
        } else {
            for (Object n : this.mater) {
                Clavis.Classroom m = (Clavis.Classroom) n;
                nomes[i] = lingua.translate(m.getDescription());
                i++;
            }
        }
        return nomes;
    }

    public List<javax.swing.JButton> getButtons() {
        this.bLista = new ArrayList<>();
        int segue = 0;
        if (!this.mater.isEmpty()) {
            for (Object n : this.mater) {
                javax.swing.JButton button = new javax.swing.JButton();
                button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setPreferredSize(dim);
                button.setMaximumSize(dim);
                button.setFocusPainted(false);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setBounds(0, 0, 80, 80);
                button.setSize(new Dimension(80, 80));
                if (!(n instanceof Clavis.Classroom)) {
                    Clavis.Material m = (Clavis.Material) n;
                    if (m.isLoaned()) {
                        button.setBackground(btcor1);
                    } else {
                        button.setBackground(btcor2);
                    }
                    button.setText(this.lingua.translate(m.getDescription()));
                    button.addActionListener((ActionEvent e) -> {
                        ActionButton at = new ActionButton(dialogoanterior, m);
                        at.open();
                    });
                    javax.swing.ImageIcon ic;
                    if (m.getMaterialImage().equals("sem")) {
                        BufferedImage ima;
                        try {
                            String auximage = "Images/" + m.getTypeOfMaterialImage() + ".png";
                            if (this.getClass().getResource(auximage) != null) {
                                ima = ImageIO.read(getClass().getResourceAsStream(auximage));
                                ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                                ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                                ic = new javax.swing.ImageIcon(ima);
                                button.setIcon(ic);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ButtonListRequest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        BufferedImage ima = FileIOAux.ImageAux.transformFromBase64IntoImage(m.getMaterialImage());
                        if (ima != null) {
                            ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    }
                } else {
                    Clavis.Classroom m = (Clavis.Classroom) n;
                    if (m.isLoaned()) {
                        button.setBackground(btcor1);
                    } else {
                        button.setBackground(btcor2);
                    }
                    button.setText(this.lingua.translate(m.getDescription()));
                    button.addActionListener((ActionEvent e) -> {
                        ActionButton at = new ActionButton(dialogoanterior, m);
                        at.open();
                    });
                    javax.swing.ImageIcon ic;
                    if (m.getMaterialImage().equals("sem")) {
                        BufferedImage ima;
                        try {
                            String auximage = "Images/" + m.getTypeOfMaterialImage() + ".png";
                            if (this.getClass().getResource(auximage) != null) {
                                ima = ImageIO.read(getClass().getResourceAsStream(auximage));
                                ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                                ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                                ic = new javax.swing.ImageIcon(ima);
                                button.setIcon(ic);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ButtonListRequest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        BufferedImage ima = FileIOAux.ImageAux.transformFromBase64IntoImage(m.getMaterialImage());
                        if (ima != null) {
                            ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    }
                }
                bLista.add(button);
                segue++;
            }
        }
        return bLista;
    }

    public javax.swing.JScrollPane getScrollPane() {
        javax.swing.JScrollPane aux = new javax.swing.JScrollPane();
        this.bLista = this.getButtons();
        if (!this.bLista.isEmpty()) {
            pane.setLayout(new Components.ModifiedFlowLayout());
            pane.setBorder(new EmptyBorder(40, 80, 40, 80));
            pane.setBackground(new Color(245, 245, 220));
            aux.setPreferredSize(new Dimension(0, 300));
            pane.setPreferredSize(new Dimension(0, 300));
            int g = 0;
            int v = 0;
            bLista.stream().forEach((bt) -> {
                pane.add(bt, BorderLayout.PAGE_END);

            });
            int altura;
            int valinicial = 50;
            int i = 0;
            while (i < bLista.size()) {
                if ((i % 5 + 1) == 1) {
                    valinicial += bLista.get(i).getHeight() + 10;
                }
                i++;
            }
            if (valinicial > 300) {
                altura = valinicial;
                pane.setPreferredSize(new Dimension(0, altura));
            }

        }
        aux.setViewportView(pane);
        return aux;
    }

    class ActionButton extends javax.swing.JDialog {

        private final Clavis.Material mat;
        private final Clavis.Classroom cla;
        private boolean editar;
        private javax.swing.JPanel painel;
        private javax.swing.JTextField[] texto;
        private javax.swing.JDialog dialogopai;

        public ActionButton(javax.swing.JDialog dialogo, Clavis.Material m) {
            super(dialogo);
            this.mat = m;
            this.cla = null;
            editar = false;
            dialogopai = dialogo;
        }

        public ActionButton(javax.swing.JDialog dialogo, Clavis.Classroom m) {
            super(dialogo);
            this.cla = m;
            this.mat = null;
            editar = false;
            dialogopai = dialogo;
        }

        public void create() {
            if (cla != null) {
                painel = new javax.swing.JPanel();
                painel.setBackground(new java.awt.Color(254, 254, 254));
                painel.setPreferredSize(new java.awt.Dimension(660, 528));
                this.setTitle(lingua.translate("Detalhes") + " " + lingua.translate("de") + " " + lingua.translate(cla.getTypeOfMaterialName()) + ": " + lingua.translate("sala") + " " + lingua.translate(cla.getDescription()));
                this.setMinimumSize(new java.awt.Dimension(700, 500));
                this.setResizable(false);
                javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(panelcor, 6), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1), border11);
                painel.setBorder(border22);
                javax.swing.GroupLayout janelaLayout = new javax.swing.GroupLayout(this.getContentPane());
                this.getContentPane().setLayout(janelaLayout);
                janelaLayout.setHorizontalGroup(
                        janelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                janelaLayout.setVerticalGroup(
                        janelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                // titulos 
                javax.swing.JLabel label1 = new javax.swing.JLabel(lingua.translate("Infomação"));
                label1.setPreferredSize(new Dimension(181, 32));
                label1.setForeground(Color.BLACK);
                label1.setFont(new Font("Cantarell", Font.PLAIN, 18));
                label1.setHorizontalAlignment(javax.swing.JLabel.CENTER);

                // painel de informcao geral
                javax.swing.JPanel painel1 = new javax.swing.JPanel();
                painel1.setBackground(Color.WHITE);
                painel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                javax.swing.JLabel imageview = new javax.swing.JLabel();
                imageview.setPreferredSize(new Dimension(50, 40));
                imageview.setBounds(20, 5, 50, 40);
                imageview.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                imageview.setOpaque(true);
                imageview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                if (!cla.getMaterialImage().equals("sem")) {
                    BufferedImage bimage = FileIOAux.ImageAux.makeRoundedCorner(FileIOAux.ImageAux.resize(FileIOAux.ImageAux.transformFromBase64IntoImage(cla.getMaterialImage()), 52, 40), 0);
                    imageview.setIcon(new javax.swing.ImageIcon(bimage));
                } else {
                    String path = new File("").getAbsolutePath() + System.getProperty("file.separator") + "Resources" + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator") + cla.getTypeOfMaterialImage() + ".png";
                    imageview.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.makeRoundedCorner(FileIOAux.ImageAux.resize(FileIOAux.ImageAux.getImageFromFile(new File(path)), 52, 40), 0)));
                }
                javax.swing.JPanel painelimagem = new javax.swing.JPanel(null);
                painelimagem.setPreferredSize(new Dimension(360, 40));
                painelimagem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
                painelimagem.setBackground(Color.WHITE);
                painelimagem.setBounds(0, 0, 360, 40);
                painelimagem.add(imageview);
                painel1.add(painelimagem);

                // painel de informacao top
                javax.swing.JPanel painel1Cima = new javax.swing.JPanel();
                GridLayout glayout1 = new GridLayout(6, 2);
                painel1Cima.setLayout(glayout1);
                painel1Cima.setBackground(Color.WHITE);
                painel1Cima.setPreferredSize(new Dimension(370, 180));
                painel1Cima.setBounds(0, 40, 360, 180);
                painel1Cima.setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 20));

                // adicionando componentes ao painel de informacao top
                // primeira linha
                javax.swing.JLabel auxiliar = new javax.swing.JLabel(lingua.translate("Descrição") + ": ");
                auxiliar.setPreferredSize(new Dimension(179, 32));
                auxiliar.setFont(new Font("Cantarell", Font.BOLD, 14));
                auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
                painel1Cima.add(auxiliar);

                String sauxiliar = lingua.translate("sala") + " " + lingua.translate(cla.getDescription());
                texto = new org.jdesktop.swingx.JXTextField[6];
                texto[0] = new org.jdesktop.swingx.JXTextField();
                texto[0].setText(sauxiliar);
                texto[0].setBackground(new Color(249, 249, 249));
                texto[0].setFocusable(false);
                texto[0].setBorder(BorderFactory.createCompoundBorder(texto[0].getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
                painel1Cima.add(texto[0]);

                /*AffineTransform affinetransform = new AffineTransform();     
            FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
            Font font = new Font("Tahoma", Font.PLAIN, 12);
            int textwidth = (int)(font.getStringBounds(sauxiliar, frc).getWidth());
            if (textwidth > 179) {
                sauxiliar = sauxiliar + " ... ";
                //texto.setText(sauxiliar);
            }*/
                // segunda linha
                auxiliar = new javax.swing.JLabel(lingua.translate("Estado") + ": ");
                auxiliar.setPreferredSize(new Dimension(179, 32));
                auxiliar.setFont(new Font("Cantarell", Font.BOLD, 14));
                auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
                painel1Cima.add(auxiliar);
                texto[1] = new org.jdesktop.swingx.JXTextField();
                if (cla.isLoaned()) {
                    sauxiliar = lingua.translate("ocupado");
                    texto[1].setForeground(Color.RED);
                } else {
                    sauxiliar = lingua.translate("livre");
                    texto[1].setForeground(new Color(0, 102, 0));
                }
                texto[1].setText(sauxiliar);
                texto[1].setBackground(new Color(249, 249, 249));
                texto[1].setFocusable(false);
                texto[1].setBorder(BorderFactory.createCompoundBorder(texto[1].getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
                painel1Cima.add(texto[1]);

                // terceira linha
                auxiliar = new javax.swing.JLabel(lingua.translate("Lugares") + ": ");
                auxiliar.setPreferredSize(new Dimension(181, 32));
                auxiliar.setFont(new Font("Cantarell", Font.BOLD, 14));
                auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
                painel1Cima.add(auxiliar);
                sauxiliar = "" + cla.getPlaces();
                texto[2] = new org.jdesktop.swingx.JXTextField();
                texto[2].setText(sauxiliar);
                texto[2].setBackground(new Color(249, 249, 249));
                texto[2].setFocusable(false);
                texto[2].setBorder(BorderFactory.createCompoundBorder(texto[2].getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
                painel1Cima.add(texto[2]);

                // quarta linha
                auxiliar = new javax.swing.JLabel(lingua.translate("Computadores") + ": ");
                auxiliar.setPreferredSize(new Dimension(181, 32));
                auxiliar.setFont(new Font("Cantarell", Font.BOLD, 14));
                auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
                painel1Cima.add(auxiliar);
                sauxiliar = "" + cla.getComputers();
                texto[3] = new org.jdesktop.swingx.JXTextField();
                texto[3].setText(sauxiliar);
                texto[3].setBackground(new Color(249, 249, 249));
                texto[3].setFocusable(false);
                texto[3].setBorder(BorderFactory.createCompoundBorder(texto[3].getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
                painel1Cima.add(texto[3]);

                // quinta linha
                auxiliar = new javax.swing.JLabel(lingua.translate("Projetor") + ": ");
                auxiliar.setPreferredSize(new Dimension(181, 32));
                auxiliar.setFont(new Font("Cantarell", Font.BOLD, 14));
                auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
                painel1Cima.add(auxiliar);
                texto[4] = new org.jdesktop.swingx.JXTextField();
                if (cla.hasProjector()) {
                    sauxiliar = lingua.translate("Sim").toLowerCase();
                } else {
                    sauxiliar = lingua.translate("Nao").toLowerCase();
                }
                texto[4].setText(sauxiliar);
                texto[4].setBackground(new Color(249, 249, 249));
                texto[4].setFocusable(false);
                texto[4].setBorder(BorderFactory.createCompoundBorder(texto[4].getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
                painel1Cima.add(texto[4]);

                // sexta linha
                auxiliar = new javax.swing.JLabel(lingua.translate("Quadro interativo") + ": ");
                auxiliar.setPreferredSize(new Dimension(181, 32));
                auxiliar.setFont(new Font("Cantarell", Font.BOLD, 14));
                auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
                painel1Cima.add(auxiliar);
                texto[5] = new org.jdesktop.swingx.JXTextField();
                if (cla.hasInteractiveTable()) {
                    sauxiliar = lingua.translate("Sim").toLowerCase();
                } else {
                    sauxiliar = lingua.translate("Nao").toLowerCase();
                }
                texto[5].setBackground(new Color(249, 249, 249));
                texto[5].setFocusable(false);
                texto[5].setBorder(BorderFactory.createCompoundBorder(texto[5].getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
                texto[5].setText(sauxiliar);
                painel1Cima.add(texto[5]);

                javax.swing.JPanel painel1Baixo = new javax.swing.JPanel();
                painel1Baixo.setPreferredSize(new Dimension(370, 40));
                painel1Baixo.setBackground(Color.WHITE);
                painel1Baixo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
                glayout1 = new GridLayout(0, 2);
                glayout1.setHgap(20);
                painel1Baixo.setLayout(glayout1);

                javax.swing.JButton bt1painel1Baixo = new javax.swing.JButton();
                java.awt.image.BufferedImage imagebtok = null;
                try {
                    imagebtok = ImageIO.read(getClass().getResourceAsStream("Images/edit.png"));
                } catch (IOException ex) {
                }
                if (imagebtok != null) {
                    javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok);
                    bt1painel1Baixo.setIcon(iconbtok);
                }
                bt1painel1Baixo.setToolTipText(lingua.translate("Editar campos"));
                bt1painel1Baixo.setFocusPainted(false);
                bt1painel1Baixo.setBackground(Color.BLACK);
                javax.swing.border.Border baux[] = new javax.swing.border.Border[4];
                for (int i = 0; i < 4; i++) {
                    baux[i] = texto[i + 2].getBorder();
                }
                bt1painel1Baixo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                bt1painel1Baixo.addActionListener((ActionEvent e) -> {
                    if (!editar) {
                        java.awt.image.BufferedImage imagebtok1 = null;
                        try {
                            imagebtok1 = ImageIO.read(getClass().getResourceAsStream("Images/save.png"));
                        } catch (IOException ex) {
                        }
                        if (imagebtok1 != null) {
                            javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok1);
                            bt1painel1Baixo.setIcon(iconbtok);
                        }
                        for (int i = 2; i < texto.length; i++) {
                            texto[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), baux[i - 2]));
                        }
                        bt1painel1Baixo.setToolTipText(lingua.translate("Gravar"));
                        for (int i = 2; i < texto.length; i++) {
                            texto[i].setFocusable(true);
                            texto[i].setBackground(Color.WHITE);
                            texto[i].addKeyListener(new java.awt.event.KeyAdapter() {
                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                                        for (JTextField texto1 : texto) {
                                            texto1.setFocusable(false);
                                            texto1.setBackground(new Color(249, 249, 249));
                                        }
                                        editar = false;
                                        java.awt.image.BufferedImage imagebtok2 = null;
                                        try {
                                            imagebtok2 = ImageIO.read(getClass().getResourceAsStream("Images/edit.png"));
                                        } catch (IOException ex) {
                                        }
                                        if (imagebtok2 != null) {
                                            javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok2);
                                            bt1painel1Baixo.setIcon(iconbtok);
                                        }
                                        bt1painel1Baixo.setToolTipText(lingua.translate("Editar campos"));
                                    }
                                }
                            });
                        }
                        editar = true;
                    } else {
                        javax.swing.Timer timer = null;
                        if (baux[0] != null) {
                            texto[2].setBorder(baux[0]);
                        }
                        for (JTextField texto1 : texto) {
                            texto1.setFocusable(false);
                            texto1.setBackground(new Color(249, 249, 249));
                        }
                        for (int i = 2; i < texto.length; i++) {
                            texto[i].setBorder(baux[i - 2]);
                        }
                        editar = false;
                        java.awt.image.BufferedImage imagebtok3 = null;
                        try {
                            imagebtok3 = ImageIO.read(getClass().getResourceAsStream("Images/edit.png"));
                        } catch (IOException ex) {
                        }
                        if (imagebtok3 != null) {
                            javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok3);
                            bt1painel1Baixo.setIcon(iconbtok);
                        }
                        bt1painel1Baixo.setToolTipText(lingua.translate("Editar campos"));
                        boolean auxiliar1 = false;
                        if (!texto[2].getText().matches("^\\d+$")) {
                            auxiliar1 = true;
                            baux[0] = texto[2].getBorder();
                            timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                texto[2].setBorder(baux[0]);
                                texto[2].setText("" + cla.getPlaces());
                                bt1painel1Baixo.setEnabled(true);
                            });
                            texto[2].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[0]));
                            timer.setRepeats(false);
                            bt1painel1Baixo.setEnabled(false);
                            timer.start();
                        }
                        if (!texto[3].getText().matches("^\\d+$")) {
                            auxiliar1 = true;
                            baux[1] = texto[3].getBorder();
                            timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                texto[3].setBorder(baux[1]);
                                texto[3].setText("" + cla.getComputers());
                                bt1painel1Baixo.setEnabled(true);
                            });
                            texto[3].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[1]));
                            timer.setRepeats(false);
                            bt1painel1Baixo.setEnabled(false);
                            timer.start();
                        }
                        if ((!texto[4].getText().toLowerCase().equals(lingua.translate("Sim").toLowerCase())) && (!texto[4].getText().toLowerCase().equals(lingua.translate("Nao").toLowerCase())) && (!texto[4].getText().toLowerCase().equals("nao"))) {
                            auxiliar1 = true;
                            baux[2] = texto[4].getBorder();
                            timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                texto[4].setBorder(baux[2]);
                                if (cla.hasProjector()) {
                                    texto[4].setText(lingua.translate("Sim").toLowerCase());
                                } else {
                                    texto[4].setText(lingua.translate("Nao").toLowerCase());
                                }
                                bt1painel1Baixo.setEnabled(true);
                            });
                            texto[4].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[2]));
                            timer.setRepeats(false);
                            bt1painel1Baixo.setEnabled(false);
                            timer.start();
                        }
                        if (!(texto[5].getText().toLowerCase().equals(lingua.translate("Sim").toLowerCase())) && (!texto[5].getText().toLowerCase().equals(lingua.translate("Nao").toLowerCase())) && (!texto[5].getText().toLowerCase().equals("nao"))) {
                            auxiliar1 = true;
                            baux[3] = texto[5].getBorder();
                            timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                texto[5].setBorder(baux[3]);
                                if (cla.hasInteractiveTable()) {
                                    texto[5].setText(lingua.translate("Sim").toLowerCase());
                                } else {
                                    texto[5].setText(lingua.translate("Nao").toLowerCase());
                                }
                                bt1painel1Baixo.setEnabled(true);
                            });
                            texto[5].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[3]));
                            timer.setRepeats(false);
                            bt1painel1Baixo.setEnabled(false);
                            timer.start();
                        }
                        if (!auxiliar1) {

                            if (texto[2].getText().length() > 1) {
                                int i = 0;
                                while (i < texto[2].getText().length()) {
                                    if (texto[2].getText().charAt(i) == '0') {
                                        texto[2].setText(texto[2].getText().replaceFirst("0", ""));
                                    }
                                    if (texto[2].getText().charAt(i) != '0') {
                                        break;
                                    }
                                }
                            }
                            if (texto[3].getText().length() > 1) {
                                int i = 0;
                                while (i < texto[3].getText().length()) {
                                    if (texto[3].getText().charAt(i) == '0') {
                                        texto[3].setText(texto[3].getText().replaceFirst("0", ""));
                                    }
                                    if (texto[3].getText().charAt(i) != '0') {
                                        break;
                                    }
                                }
                            }
                            cla.setPlaces(Integer.valueOf(texto[2].getText()));
                            cla.setComputers(Integer.valueOf(texto[3].getText()));
                            if ((texto[4].getText().toLowerCase().equals("nao"))) {
                                texto[4].setText("não");
                            } else {
                                texto[4].setText(texto[4].getText().toLowerCase());
                            }
                            if (texto[4].getText().equals(lingua.translate("Sim").toLowerCase())) {
                                cla.setProjector(true);
                            } else {
                                cla.setProjector(false);
                            }
                            if ((texto[5].getText().toLowerCase().equals("nao"))) {
                                texto[5].setText("não");
                            } else {
                                texto[5].setText(texto[5].getText().toLowerCase());
                            }
                            if (texto[5].getText().equals(lingua.translate("Sim").toLowerCase())) {
                                cla.setInteractiveTable(true);
                            } else {
                                cla.setInteractiveTable(false);
                            }
                            DataBase.DataBase db = new DataBase.DataBase(url);
                            db.updateClassroom(cla);
                        }
                    }
                });

                javax.swing.JButton bt2painel1Baixo = new javax.swing.JButton();
                imagebtok = null;
                try {
                    imagebtok = ImageIO.read(getClass().getResourceAsStream("Images/plus24x24.png"));
                } catch (IOException ex) {
                }
                if (imagebtok != null) {
                    javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok);
                    bt2painel1Baixo.setIcon(iconbtok);
                }
                bt2painel1Baixo.setFocusPainted(false);
                bt2painel1Baixo.setBackground(Color.BLACK);
                bt2painel1Baixo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                bt2painel1Baixo.addActionListener((ActionEvent e) -> {
                    Main.Windows.WRequest janela = new Main.Windows.WRequest(this);
                    janela.create();
                    janela.appear();
                });
                painel1Baixo.add(bt2painel1Baixo);
                painel1Baixo.add(bt1painel1Baixo);
                painel1.add(painel1Cima);
                painel1.add(painel1Baixo);

                // painel esquerda
                javax.swing.JPanel painel2 = new javax.swing.JPanel();
                BoxLayout blayout2 = new BoxLayout(painel2, BoxLayout.Y_AXIS);

                painel2.setLayout(null);
                painel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                this.createPanelRight(painel2);

                javax.swing.JPanel painel11 = new javax.swing.JPanel();

                painel11.setBackground(new Color(254, 254, 254));
                painel11.setPreferredSize(new Dimension(360, 40));
                painel11.setLayout(null);
                javax.swing.JButton btsair = new javax.swing.JButton();
                btsair.setPreferredSize(new Dimension(160, 40));
                java.awt.image.BufferedImage imagebtsair = null;
                try {
                    imagebtsair = ImageIO.read(getClass().getResourceAsStream("Images/exit26x24.png"));
                } catch (IOException ex) {
                }
                if (imagebtsair != null) {
                    javax.swing.ImageIcon iconbtsair = new javax.swing.ImageIcon(imagebtsair);
                    btsair.setIcon(iconbtsair);
                }
                btsair.setBounds(5, 0, 160, 40);
                btsair.setToolTipText(lingua.translate("Sair"));
                btsair.setBackground(Color.BLACK);
                btsair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btsair.setFocusPainted(false);
                btsair.addActionListener((ActionEvent e) -> {
                    this.setVisible(false);
                    this.dispose();
                });
                painel11.add(btsair);
                javax.swing.JPanel painel22 = new javax.swing.JPanel();
                //BoxLayout blayout22 = new BoxLayout(painel22, BoxLayout.Y_AXIS);
                //painel22.setLayout(blayout22);
                painel22.setBackground(new Color(254, 254, 254));
                painel22.setLayout(null);
                javax.swing.JButton btreq = new javax.swing.JButton();
                btreq.setPreferredSize(new Dimension(160, 40));
                btreq.setToolTipText(lingua.translate("Realizar requisição"));
                java.awt.image.BufferedImage imagebtreq = null;
                try {
                    imagebtreq = ImageIO.read(getClass().getResourceAsStream("Images/request.png"));
                } catch (IOException ex) {
                }
                if (imagebtreq != null) {
                    javax.swing.ImageIcon iconbtreq = new javax.swing.ImageIcon(imagebtreq);
                    btreq.setIcon(iconbtreq);
                }
                btreq.setBackground(Color.BLACK);
                btreq.setFocusPainted(false);
                btreq.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btreq.setBounds(55, 0, 160, 40);
                painel22.add(btreq);

                javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
                painel.setLayout(painelLayout);
                painelLayout.setHorizontalGroup(
                        painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(painel1, 0, 380, Short.MAX_VALUE)
                                        .addComponent(painel11, 0, 180, Short.MAX_VALUE)
                                        .addComponent(label1, 0, 380, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(painel22, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                        .addComponent(painel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelativa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(64, Short.MAX_VALUE))
                );
                painelLayout.setVerticalGroup(
                        painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelativa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(painel2, 0, 290, Short.MAX_VALUE)
                                        .addComponent(painel1, 0, 290, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(painel11, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                        .addComponent(painel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(59, Short.MAX_VALUE))
                );
            }

        }

        public void open() {
            if ((mat == null) && (cla != null)) {
                this.create();
                this.setVisible(true);
                this.setLocationRelativeTo(dialogopai);
            } else if ((cla == null) && (mat != null)) {
                JOptionPane.showMessageDialog(null, "Ola mundo " + mat.getDescription());
            }
        }

        private void createPanelRight(javax.swing.JPanel painel2) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            Clavis.Request req;
            if (!cla.isLoaned()) {
                req = db.getNextRequest(cla);
            } else {
                req = db.getCurrentRequest(cla);
            }
            javax.swing.JLabel lbtl;
            if (req.getId() != -1) {
                if (!(cla.isLoaned()) && (!req.getPerson().getName().equals("sem"))) {
                    labelativa = new javax.swing.JLabel(lingua.translate("Próxima requisição"));
                    labelativa.setPreferredSize(new Dimension(181, 32));
                    labelativa.setFont(new Font("Cantarell", Font.PLAIN, 18));
                    labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    painel2.setBackground(new Color(246, 255, 248));
                } else {
                    labelativa.setText(lingua.translate("Requisitado por"));
                    labelativa.setPreferredSize(new Dimension(181, 32));
                    labelativa.setFont(new Font("Cantarell", Font.PLAIN, 18));
                    labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    painel2.setBackground(new Color(255, 246, 248));
                }

                javax.swing.JLabel lbtl1 = new javax.swing.JLabel(lingua.translate("Requisitante"));
                lbtl1.setFont(new Font("Cantarell", Font.BOLD, 14));
                lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lbtl1.setPreferredSize(new Dimension(219, 20));
                lbtl1.setBounds(0, 20, 219, 20);
                painel2.add(lbtl1);

                String nome = req.getPerson().getName();
                AffineTransform affinetransform = new AffineTransform();
                FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
                Font fonte = new Font("Cantarell", Font.PLAIN, 12);
                int larguratexto = (int) (fonte.getStringBounds(nome, frc).getWidth());
                if (larguratexto > 180) {
                    String[] auxiliar = nome.split(" ");
                    nome = nome.replace(auxiliar[0], "");
                    nome = nome.replace(auxiliar[auxiliar.length - 1], "");
                    int i = 1;
                    while (larguratexto > 180) {
                        if (auxiliar[i].length() > 2) {
                            nome = nome.replace(auxiliar[i], auxiliar[i].charAt(0) + ".");
                        } else {
                            nome = nome.replace(auxiliar[i], "");
                        }
                        larguratexto = (int) (fonte.getStringBounds(auxiliar[0] + nome + auxiliar[auxiliar.length - 1], frc).getWidth());
                        i++;
                    }
                    nome = auxiliar[0] + nome + auxiliar[auxiliar.length - 1];
                }
                javax.swing.JLabel lb1 = new javax.swing.JLabel(nome);
                lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
                lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lb1.setPreferredSize(new Dimension(219, 20));
                lb1.setBounds(0, 40, 219, 20);
                painel2.add(lb1);

                if (!cla.isLoaned()) {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Dia"));
                } else {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Até ao dia"));
                }
                lbtl1.setFont(new Font("Cantarell", Font.BOLD, 14));
                lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lbtl1.setPreferredSize(new Dimension(219, 20));
                lbtl1.setBounds(0, 70, 219, 20);
                painel2.add(lbtl1);

                lb1 = new javax.swing.JLabel(req.getBeginDate().toString());
                lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
                lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lb1.setPreferredSize(new Dimension(219, 20));
                lb1.setBounds(0, 90, 219, 20);
                painel2.add(lb1);

                if (!cla.isLoaned()) {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Hora"));
                } else {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Hora de entrega"));
                }
                lbtl1.setFont(new Font("Cantarell", Font.BOLD, 14));
                lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lbtl1.setPreferredSize(new Dimension(219, 20));
                lbtl1.setBounds(0, 120, 219, 20);
                painel2.add(lbtl1);

                if (!cla.isLoaned()) {
                    lb1 = new javax.swing.JLabel(lingua.translate("de") + ": " + req.getTimeBegin().toString(0) + " " + lingua.translate("até") + " " + req.getTimeEnd().toString(0));
                } else {
                    lb1 = new javax.swing.JLabel(req.getTimeEnd().toString(0));
                }
                lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
                lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lb1.setPreferredSize(new Dimension(219, 20));
                lb1.setBounds(0, 140, 219, 20);
                painel2.add(lb1);

                lbtl1 = new javax.swing.JLabel(lingua.translate("Atividade"));
                lbtl1.setFont(new Font("Cantarell", Font.BOLD, 14));
                lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lbtl1.setPreferredSize(new Dimension(219, 20));
                lbtl1.setBounds(0, 170, 219, 20);
                painel2.add(lbtl1);

                if (!req.getActivity().equals("sem")) {
                    lb1 = new javax.swing.JLabel(lingua.translate(req.getActivity()));
                } else {
                    lb1 = new javax.swing.JLabel(lingua.translate("Não existe descrição"));
                }
                lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
                lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lb1.setPreferredSize(new Dimension(219, 20));
                lb1.setBounds(0, 190, 219, 20);
                painel2.add(lb1);

                if (req.getSubject().getId() != 0) {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Disciplina"));
                    lbtl1.setFont(new Font("Cantarell", Font.BOLD, 14));
                    lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    lbtl1.setPreferredSize(new Dimension(219, 20));
                    lbtl1.setBounds(0, 220, 219, 20);
                    painel2.add(lbtl1);

                    lb1 = new javax.swing.JLabel(lingua.translate(req.getSubject().getName()));
                    lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
                    lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    lb1.setPreferredSize(new Dimension(219, 20));
                    lb1.setBounds(0, 240, 219, 20);
                    painel2.add(lb1);
                }
            } else {
                lbtl = new javax.swing.JLabel(lingua.translate("Não existem requisições"));
                painel2.setBackground(new Color(246, 246, 246));
                lbtl.setFont(new Font("Cantarell", Font.BOLD | Font.CENTER_BASELINE, 16));
                lbtl.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lbtl.setPreferredSize(new Dimension(219, 20));
                lbtl.setBounds(0, 100, 219, 20);
                painel2.add(lbtl);
                lbtl = new javax.swing.JLabel(lingua.translate("para este recurso") + "!");
                lbtl.setFont(new Font("Cantarell", Font.BOLD | Font.CENTER_BASELINE, 16));
                lbtl.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lbtl.setPreferredSize(new Dimension(219, 20));
                lbtl.setBounds(0, 120, 219, 20);
                painel2.add(lbtl);

            }
        }

    }

}
