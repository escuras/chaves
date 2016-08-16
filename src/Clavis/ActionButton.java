/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ActionButton extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;
    private final Keys.Material mat;
    private final Keys.Classroom cla;
    private boolean editar;
    private javax.swing.JPanel painel;
    private javax.swing.JTextField[] texto;
    private final javax.swing.JDialog dialogopai;
    private FileIOAux.ImageExtension bimage;
    private boolean alterado;
    private javax.swing.JLabel labelativa;
    private final Langs.Locale lingua;
    private final Color panelcor;
    private String url;

    public ActionButton(Keys.Material m, Langs.Locale lingua, Color panelcor, String url) {
        super();
        this.mat = m;
        this.cla = null;
        this.lingua = lingua;
        editar = false;
        this.panelcor = panelcor;
        this.url = url;
        dialogopai = null;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 18));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
    }

    public ActionButton(Keys.Classroom m, Langs.Locale lingua, Color panelcor, String url) {
        super();
        this.mat = null;
        this.cla = m;
        this.lingua = lingua;
        editar = false;
        this.panelcor = panelcor;
        this.url = url;
        dialogopai = null;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 18));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
    }

    public ActionButton(javax.swing.JDialog dialogo, Keys.Material m, Langs.Locale lingua, Color panelcor, String url) {
        super(dialogo);
        this.mat = m;
        this.cla = null;
        this.lingua = lingua;
        editar = false;
        this.panelcor = panelcor;
        this.url = url;
        dialogopai = dialogo;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 18));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
    }

    public ActionButton(javax.swing.JDialog dialogo, Keys.Classroom m, Langs.Locale lingua, Color panelcor, String url) {
        super(dialogo);
        this.cla = m;
        this.mat = null;
        this.lingua = lingua;
        editar = false;
        this.panelcor = panelcor;
        this.url = url;
        dialogopai = dialogo;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelativa.setBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, false));

    }

    public void create() {
        if (cla != null) {
            painel = new javax.swing.JPanel();
            painel.setBackground(new java.awt.Color(254, 254, 254));
            painel.setPreferredSize(new java.awt.Dimension(660, 528));
            this.setTitle(lingua.translate("Detalhes") + " " + lingua.translate("de") + " " + lingua.translate(cla.getTypeOfMaterialName()) + ": " + lingua.translate("sala") + " " + lingua.translate(cla.getDescription()));
            this.setMinimumSize(new java.awt.Dimension(700, 500));
            this.setResizable(false);
            javax.swing.border.Border border = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(panelcor, 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
            painel.setBorder(border);
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
            javax.swing.JLabel label1 = new javax.swing.JLabel(lingua.translate("Informação"));
            label1.setPreferredSize(new Dimension(181, 32));
            label1.setForeground(Color.BLACK);
            label1.setFont(new Font("Cantarell", Font.PLAIN, 14));
            label1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            label1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, false));

            // painel de informcao geral
            javax.swing.JPanel painel1 = new javax.swing.JPanel();
            painel1.setBackground(Color.WHITE);
            painel1.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK, 1)));
            javax.swing.JLabel imageview = new javax.swing.JLabel();
            imageview.setPreferredSize(new Dimension(60, 50));
            imageview.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            imageview.setVerticalAlignment(javax.swing.JLabel.CENTER);
            imageview.setBounds(14, 10, 60, 50);

            imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.BLACK, 1)));
            if (!cla.getMaterialImage().equals("sem")) {
                bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.transformFromBase64IntoImage(cla.getMaterialImage()));
                imageview.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
            } else {
                String path = new File("").getAbsolutePath() + System.getProperty("file.separator") + "Resources" + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator") + cla.getTypeOfMaterialImage() + ".png";
                bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.getImageFromFile(new File(path)));
                imageview.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
            }
            javax.swing.JPanel painelimagem = new javax.swing.JPanel(null);
            painelimagem.setPreferredSize(new Dimension(360, 60));
            painelimagem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            painelimagem.setBackground(Color.WHITE);
            painelimagem.setBounds(0, 0, 360, 60);
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
            painel1Baixo.setLayout(null);

            javax.swing.JButton bteditar = new javax.swing.JButton();
            java.awt.image.BufferedImage imagebtok = null;
            try {
                imagebtok = ImageIO.read(getClass().getResourceAsStream("Images/edit.png"));
            } catch (IOException ex) {
            }
            if (imagebtok != null) {
                javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok);
                bteditar.setIcon(iconbtok);
            }
            bteditar.setToolTipText(lingua.translate("Editar campos"));
            bteditar.setPreferredSize(new Dimension(90, 40));
            bteditar.setBounds(260, 0, 90, 40);
            bteditar.setFocusPainted(false);
            bteditar.setBackground(new Color(51, 102, 153));
            javax.swing.border.Border baux[] = new javax.swing.border.Border[5];
            for (int i = 0; i < 4; i++) {
                baux[i] = texto[i + 2].getBorder();
            }
            baux[4] = imageview.getBorder();
            bteditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            bteditar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!editar) {
                        java.awt.image.BufferedImage imagebtok1 = null;
                        try {
                            imagebtok1 = ImageIO.read(getClass().getResourceAsStream("Images/save.png"));
                        } catch (IOException ex) {
                        }
                        if (imagebtok1 != null) {
                            javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok1);
                            bteditar.setIcon(iconbtok);
                        }
                        for (int i = 2; i < texto.length; i++) {
                            texto[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), baux[i - 2]));
                        }
                        imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                        bteditar.setToolTipText(lingua.translate("Gravar"));
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
                                            bteditar.setIcon(iconbtok);
                                        }
                                        bteditar.setToolTipText(lingua.translate("Editar campos"));
                                    }
                                }
                            });
                        }
                        imageview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                        imageview.addMouseListener(new java.awt.event.MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                FileIOAux.ImageExtension image;
                                image = bimage;
                                UIManager.put("TextField.background", new Color(234, 234, 234));
                                UIManager.put("List.background", new Color(234, 234, 234));
                                if ((bimage = FileIOAux.ImageAux.getImageFromFileChooser(imageview, lingua, 54, 44)) != null) {
                                    alterado = true;
                                } else {
                                    bimage = image;
                                }
                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                if (bimage != null) {
                                    imageview.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(FileIOAux.ImageAux.makeWhiter(bimage.getImage(), 80), 54, 44)));
                                }
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                if (bimage != null) {
                                    imageview.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
                                }
                            }
                        });
                        editar = true;
                    } else {
                        javax.swing.Timer timer;
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
                        imageview.setBorder(baux[4]);
                        imageview.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                        editar = false;
                        java.awt.image.BufferedImage imagebtok3 = null;
                        try {
                            imagebtok3 = ImageIO.read(getClass().getResourceAsStream("Images/edit.png"));
                        } catch (IOException ex) {
                        }
                        if (imagebtok3 != null) {
                            javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok3);
                            bteditar.setIcon(iconbtok);
                        }
                        bteditar.setToolTipText(lingua.translate("Editar campos"));
                        boolean auxiliar1 = false;
                        if (!texto[2].getText().matches("^\\d+$")) {
                            auxiliar1 = true;
                            baux[0] = texto[2].getBorder();
                            timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                texto[2].setBorder(baux[0]);
                                texto[2].setText("" + cla.getPlaces());
                                bteditar.setEnabled(true);
                            });
                            texto[2].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[0]));
                            timer.setRepeats(false);
                            bteditar.setEnabled(false);
                            timer.start();
                        }
                        if (!texto[3].getText().matches("^\\d+$")) {
                            auxiliar1 = true;
                            baux[1] = texto[3].getBorder();
                            timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                texto[3].setBorder(baux[1]);
                                texto[3].setText("" + cla.getComputers());
                                bteditar.setEnabled(true);
                            });
                            texto[3].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[1]));
                            timer.setRepeats(false);
                            bteditar.setEnabled(false);
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
                                bteditar.setEnabled(true);
                            });
                            texto[4].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[2]));
                            timer.setRepeats(false);
                            bteditar.setEnabled(false);
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
                                bteditar.setEnabled(true);
                            });
                            texto[5].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[3]));
                            timer.setRepeats(false);
                            bteditar.setEnabled(false);
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
                            if ((bimage != null) && (!bimage.getExtension().equals("")) && (alterado)) {
                                cla.setMaterialImage(bimage.getImage(), bimage.getExtension());
                                alterado = false;
                                db.updateMaterial(cla);
                            }
                            for (java.awt.event.MouseListener l : imageview.getMouseListeners()) {
                                imageview.removeMouseListener(l);
                            }
                            db.close();
                        }
                    }
                }
            });

            painel1Baixo.add(bteditar);

            // bt mais
            javax.swing.JButton btMais = new javax.swing.JButton();
            imagebtok = null;
            try {
                imagebtok = ImageIO.read(getClass().getResourceAsStream("Images/plus24x24.png"));
            } catch (IOException ex) {
            }
            if (imagebtok != null) {
                javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok);
                btMais.setIcon(iconbtok);
            }
            btMais.setPreferredSize(new Dimension(90, 40));
            btMais.setBounds(165, 0, 90, 40);
            btMais.setFocusPainted(false);
            btMais.setBackground(new Color(51, 102, 153));
            btMais.setToolTipText(lingua.translate("Mais dados"));
            btMais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btMais.addActionListener((ActionEvent e) -> {
                Clavis.Windows.WMaterial janela = new Clavis.Windows.WMaterial(this, cla, panelcor, lingua, url);
                janela.create();
                janela.appear();
            });
            painel1Baixo.add(btMais);

            painel1.add(painel1Cima);
            painel1.add(painel1Baixo);

            // painel esquerda
            javax.swing.JPanel painel2 = new javax.swing.JPanel();
            BoxLayout blayout2 = new BoxLayout(painel2, BoxLayout.Y_AXIS);

            painel2.setLayout(null);
            painel2.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, true, true, false), BorderFactory.createLineBorder(Color.BLACK, 1)));

            this.createPanelRight(painel2);

            javax.swing.JPanel painel11 = new javax.swing.JPanel();

            painel11.setBackground(new Color(254, 254, 254));
            painel11.setPreferredSize(new Dimension(360, 40));
            painel11.setLayout(null);

// bt sair
            javax.swing.JButton btsair = new javax.swing.JButton();
            btsair.setPreferredSize(new Dimension(90, 40));
            java.awt.image.BufferedImage imagebtsair = null;
            try {
                imagebtsair = ImageIO.read(getClass().getResourceAsStream("Images/exit26x24.png"));
            } catch (IOException ex) {
            }
            if (imagebtsair != null) {
                javax.swing.ImageIcon iconbtsair = new javax.swing.ImageIcon(imagebtsair);
                btsair.setIcon(iconbtsair);
            }
            btsair.setBounds(5, 0, 90, 40);
            btsair.setToolTipText(lingua.translate("Sair"));
            btsair.setBackground(new Color(5, 5, 5));
            btsair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btsair.setFocusPainted(false);
            btsair.addActionListener((ActionEvent e) -> {
                this.setVisible(false);
                if (dialogopai != null) {
                    dialogopai.setLocation(this.getX(), this.getY());
                    dialogopai.setVisible(true);
                }
                this.dispose();
            });
            painel11.add(btsair);

            javax.swing.JPanel painel22 = new javax.swing.JPanel();
            //BoxLayout blayout22 = new BoxLayout(painel22, BoxLayout.Y_AXIS);
            //painel22.setLayout(blayout22);
            painel22.setBackground(new Color(254, 254, 254));
            painel22.setLayout(null);

//bthorario
            javax.swing.JButton bthorario = new javax.swing.JButton();
            bthorario.setPreferredSize(new Dimension(90, 40));
            java.awt.image.BufferedImage imagebthorario = null;
            try {
                imagebthorario = ImageIO.read(getClass().getResourceAsStream("Images/calendario.png"));
            } catch (IOException ex) {
            }
            if (imagebthorario != null) {
                javax.swing.ImageIcon iconbthorario = new javax.swing.ImageIcon(imagebthorario);
                bthorario.setIcon(iconbthorario);
            }
            bthorario.setBounds(5, 0, 90, 40);
            bthorario.setToolTipText(lingua.translate("Horário"));
            bthorario.setBackground(new Color(51, 102, 153));
            bthorario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            bthorario.setFocusPainted(false);
            bthorario.addActionListener((ActionEvent e) -> {

            });
            painel22.add(bthorario);

// btreq
            javax.swing.JButton btreq = new javax.swing.JButton();
            btreq.setPreferredSize(new Dimension(90, 40));
            btreq.setToolTipText(lingua.translate("Requisitar"));
            java.awt.image.BufferedImage imagebtreq = null;
            try {
                imagebtreq = ImageIO.read(getClass().getResourceAsStream("Images/request.png"));
            } catch (IOException ex) {
            }
            if (imagebtreq != null) {
                javax.swing.ImageIcon iconbtreq = new javax.swing.ImageIcon(imagebtreq);
                btreq.setIcon(iconbtreq);
            }
            btreq.setBackground(new Color(57, 147, 2));
            btreq.setContentAreaFilled(true);
            btreq.setFocusPainted(false);
            btreq.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btreq.setBounds(100, 0, 90, 40);
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
                                    .addComponent(painel2, 0, 310, Short.MAX_VALUE)
                                    .addComponent(painel1, 0, 310, Short.MAX_VALUE))
                            .addGap(10, 10, 10)
                            .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(painel11, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                    .addComponent(painel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(59, Short.MAX_VALUE))
            );
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                    if (dialogopai != null) {
                        dialogopai.setLocation(getX(), getY());
                        dialogopai.setVisible(true);
                    }
                    dispose();
                }
            });
        }

    }

    public void open() {
        if ((mat == null) && (cla != null)) {
            this.create();
            if (dialogopai != null) {
                this.setLocation(dialogopai.getX(), dialogopai.getY());
            } else {
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
                this.setLocation(x, y);
            }
            this.setVisible(true);
        } else if ((cla == null) && (mat != null)) {
            JOptionPane.showMessageDialog(null, "Ola mundo " + mat.getDescription());
        }
    }

    private void createPanelRight(javax.swing.JPanel painel2) {
        DataBase.DataBase db = new DataBase.DataBase(url);
        Keys.Request req;
        java.util.Set<Keys.Request> requnion;
        if (!cla.isLoaned()) {
            req = db.getNextRequest(cla);
            requnion = db.getUnionRequests(req);
        } else {
            req = db.getCurrentRequest(cla);
            requnion = db.getUnionRequests(req);
        }
        db.close();
        javax.swing.JLabel lbtl;
        if (req.getId() != -1) {
            TimeDate.Date dat;
            TimeDate.Time tim;
            boolean atrasado;
            boolean dia;
            if (requnion.isEmpty()) {
                dat = req.getEndDate();
                tim = req.getTimeEnd();
            } else {
                tim = req.getTimeEnd();
                dat = req.getEndDate();
                for (Keys.Request re : requnion) {
                    if (re.getTimeEnd().compareTime(tim) < 0) {
                        tim = re.getTimeEnd();
                        dat = re.getEndDate();
                    }
                }
            }
            if ((!cla.isLoaned()) && (!req.getPerson().getName().equals("sem"))) {
                labelativa = new javax.swing.JLabel(lingua.translate("Próxima requisição"));
                labelativa.setPreferredSize(new Dimension(181, 32));
                labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                labelativa.setBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, false));

                painel2.setBackground(new Color(246, 255, 248));
                atrasado = false;
                dia = false;
            } else if ((cla.isLoaned()) && ((dat.isBigger(new TimeDate.Date()) == 0) && (tim.compareTime(new TimeDate.Time()) > 0))) {
                labelativa = new javax.swing.JLabel(lingua.translate("Atrasado") + " " + lingua.translate("na entrega"));
                labelativa.setPreferredSize(new Dimension(181, 32));
                labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                labelativa.setBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, false));
                painel2.setBackground(new Color(255, 246, 248));
                atrasado = true;
                dia = false;
            } else if ((cla.isLoaned()) && ((dat.isBigger(new TimeDate.Date()) > 0))) {
                labelativa = new javax.swing.JLabel(lingua.translate(lingua.translate("Atrasado") + ": " + dat.isBigger(new TimeDate.Date()) + "") + " " + lingua.translate("dias"));
                labelativa.setPreferredSize(new Dimension(181, 32));
                labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                labelativa.setBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, false));
                painel2.setBackground(new Color(255, 246, 248));
                atrasado = true;
                dia = true;
            } else {
                labelativa = new javax.swing.JLabel(lingua.translate("Requisitado por"));
                labelativa.setPreferredSize(new Dimension(181, 32));
                labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                labelativa.setBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, false));
                painel2.setBackground(new Color(235, 250, 237));
                atrasado = false;
                dia = false;
            }

            javax.swing.JLabel lbtl1 = new javax.swing.JLabel(lingua.translate("Requisitante"));
            lbtl1.setFont(new Font("Cantarell", Font.PLAIN, 14));
            lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lbtl1.setPreferredSize(new Dimension(219, 20));
            lbtl1.setForeground(Color.BLUE);
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
            lb1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 8, 0, 4), BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
            painel2.add(lb1);
            if (!cla.isLoaned()) {
                lbtl1 = new javax.swing.JLabel(lingua.translate("Dia"));
            } else {
                lbtl1 = new javax.swing.JLabel(lingua.translate("Até ao dia"));
            }
            lbtl1.setFont(new Font("Cantarell", Font.PLAIN, 14));
            lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lbtl1.setPreferredSize(new Dimension(219, 20));
            lbtl1.setForeground(Color.BLUE);
            lbtl1.setBounds(0, 70, 219, 20);
            painel2.add(lbtl1);

            lb1 = new javax.swing.JLabel();
            if ((atrasado) && (dia)) {
                lb1.setForeground(Color.RED);
            }
            lb1.setText(req.getBeginDate().toString());
            lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
            lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lb1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 8, 0, 4), BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
            lb1.setPreferredSize(new Dimension(219, 20));
            lb1.setBounds(0, 90, 219, 20);

            painel2.add(lb1);

            if (!cla.isLoaned()) {
                lbtl1 = new javax.swing.JLabel(lingua.translate("Hora"));
            } else {

                lbtl1 = new javax.swing.JLabel(lingua.translate("Hora de entrega"));
            }
            lbtl1.setFont(new Font("Cantarell", Font.PLAIN, 14));
            lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lbtl1.setPreferredSize(new Dimension(219, 20));
            lbtl1.setForeground(Color.BLUE);
            lbtl1.setBounds(0, 120, 219, 20);
            painel2.add(lbtl1);

            if (!cla.isLoaned()) {
                if (requnion.isEmpty()) {
                    lb1 = new javax.swing.JLabel(lingua.translate("Desde as") + ": " + req.getTimeBegin().toString(0) + " " + lingua.translate("até às") + " " + req.getTimeEnd().toString(0) + ".");
                } else {
                    tim = req.getTimeEnd();
                    for (Keys.Request re : requnion) {
                        if (re.getTimeEnd().compareTime(tim) < 0) {
                            tim = re.getTimeEnd();
                        }
                    }
                    lb1 = new javax.swing.JLabel(lingua.translate("Desde as") + ": " + req.getTimeBegin().toString(0) + " " + lingua.translate("até às") + " " + tim.toString(0) + ".");
                }
            } else if (requnion.isEmpty()) {
                lb1 = new javax.swing.JLabel(req.getTimeEnd().toString(0));
            } else {
                tim = req.getTimeEnd();
                for (Keys.Request re : requnion) {
                    if (re.getTimeEnd().compareTime(tim) < 0) {
                        tim = re.getTimeEnd();
                    }
                    lb1 = new javax.swing.JLabel(tim.toString(0));
                }
            }
            if ((atrasado) && (!dia)) {
                lb1.setForeground(Color.RED);
            }
            lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
            lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lb1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 8, 0, 4), BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
            lb1.setPreferredSize(new Dimension(219, 20));
            lb1.setBounds(0, 140, 219, 20);
            painel2.add(lb1);

            lbtl1 = new javax.swing.JLabel(lingua.translate("Atividade"));
            lbtl1.setFont(new Font("Cantarell", Font.PLAIN, 14));
            lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lbtl1.setForeground(Color.BLUE);
            lbtl1.setPreferredSize(new Dimension(219, 20));
            lbtl1.setBounds(0, 170, 219, 20);
            painel2.add(lbtl1);

            if (!req.getActivity().equals("sem")) {
                if (requnion.isEmpty()) {
                    lb1 = new javax.swing.JLabel(lingua.translate(req.getActivity()));
                } else {
                    lb1 = new javax.swing.JLabel(lingua.translate("Múltiplas atividades"));
                    String[] auxiliar = new String[requnion.size() + 2];
                    auxiliar[0] = "Múltiplas atividades:::";
                    auxiliar[1] = req.getActivity() + ";;;" + req.getTimeBegin().toString(0) + ";;;" + req.getTimeEnd().toString(0);
                    int i = 2;
                    for (Keys.Request re : requnion) {
                        auxiliar[i] = re.getActivity() + ";;;" + re.getTimeBegin().toString(0) + ";;;" + re.getTimeEnd().toString(0);
                        i++;
                    }
                    Components.PopUpMenu pop = new Components.PopUpMenu(auxiliar, lingua);
                    pop.create();
                    lb1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (!pop.isShowing()) {
                                pop.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            e.getComponent().setForeground(new Color(145, 145, 145));
                            pop.show(e.getComponent(), e.getX(), e.getY());
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            e.getComponent().setForeground(new Color(1, 1, 1));
                            pop.setVisible(false);
                        }
                    });
                }
            } else {
                lb1 = new javax.swing.JLabel(lingua.translate("Não existe descrição"));
            }
            lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
            lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lb1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 8, 0, 4), BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
            lb1.setPreferredSize(new Dimension(219, 20));
            lb1.setBounds(0, 190, 219, 20);
            painel2.add(lb1);

            if (req.getSubject().getId() != 0) {
                lbtl1 = new javax.swing.JLabel(lingua.translate("Disciplina"));
                lbtl1.setFont(new Font("Cantarell", Font.PLAIN, 14));
                lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lbtl1.setForeground(Color.BLUE);
                lbtl1.setPreferredSize(new Dimension(219, 20));
                lbtl1.setBounds(0, 220, 219, 20);
                painel2.add(lbtl1);

                lb1 = new javax.swing.JLabel(lingua.translate(req.getSubject().getName()));
                lb1.setFont(new Font("Cantarell", Font.PLAIN, 12));
                lb1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                lb1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 8, 0, 4), BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
                lb1.setPreferredSize(new Dimension(219, 20));
                lb1.setBounds(0, 240, 219, 20);
                painel2.add(lb1);
            }
        } else {
            lbtl = new javax.swing.JLabel(lingua.translate("Não existem requisições"));
            painel2.setBackground(new Color(246, 246, 246));
            lbtl.setFont(new Font("Cantarell", Font.PLAIN, 14));
            lbtl.setForeground(Color.BLUE);
            lbtl.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lbtl.setPreferredSize(new Dimension(219, 20));
            lbtl.setBounds(0, 100, 219, 20);
            painel2.add(lbtl);
            lbtl = new javax.swing.JLabel(" " + lingua.translate("para este recurso") + "!");
            lbtl.setForeground(Color.BLUE);
            lbtl.setFont(new Font("Cantarell", Font.PLAIN, 14));
            lbtl.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lbtl.setPreferredSize(new Dimension(219, 20));
            lbtl.setBounds(0, 120, 219, 20);
            painel2.add(lbtl);

        }
    }

}
