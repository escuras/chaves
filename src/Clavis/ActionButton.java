/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;

public final class ActionButton extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;
    private final Keys.Material mat;
    private final Keys.Classroom cla;
    private boolean editar;
    private javax.swing.JPanel painel;
    private javax.swing.JTextField[] texto;
    private final javax.swing.JFrame framepai;
    private FileIOAux.ImageExtension bimage;
    private boolean alterado;
    private javax.swing.JLabel labelativa;
    private final Langs.Locale lingua;
    private final Color panelcor;
    private final String url;
    private int atraso;
    private TimeDate.Time tim;
    private TimeDate.Date dat;
    private javax.swing.JLabel labelauxiliar;
    private Timer timertempoatrasado;
    private PersonalButtonRequest btchamada;
    private static ActionButton app = null;

    public ActionButton() {
        super();
        this.mat = null;
        this.cla = null;
        this.lingua = Langs.Locale.getLocale_pt_PT();
        editar = false;
        this.panelcor = KeyQuest.getSystemColor();
        this.url = "";
        framepai = null;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelativa.setOpaque(true);
        labelativa.setBackground(new Color(250, 250, 250));
        atraso = -TableRequest.DEFAULT_LATE_INTERVAL * 60;
        this.btchamada = null;
    }

    public ActionButton(Keys.Material m, Langs.Locale lingua, String url, PersonalButtonRequest btchamada) {
        super();
        this.mat = m;
        this.cla = null;
        this.lingua = lingua;
        editar = false;
        this.panelcor = KeyQuest.getSystemColor();
        this.url = url;
        framepai = null;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelativa.setOpaque(true);
        labelativa.setBackground(new Color(250, 250, 250));
        atraso = -TableRequest.DEFAULT_LATE_INTERVAL * 60;
        this.btchamada = btchamada;
    }

    public ActionButton(Keys.Classroom m, Langs.Locale lingua, String url, PersonalButtonRequest btchamada) {
        super();
        this.mat = null;
        this.cla = m;
        this.lingua = lingua;
        editar = false;
        this.panelcor = KeyQuest.getSystemColor();
        this.url = url;
        framepai = null;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelativa.setOpaque(true);
        labelativa.setBackground(new Color(250, 250, 250));
        atraso = -TableRequest.DEFAULT_LATE_INTERVAL * 60;
        this.btchamada = btchamada;
    }

    public ActionButton(javax.swing.JFrame frame, Keys.Material m, Langs.Locale lingua, String url, PersonalButtonRequest btchamada) {
        super(frame);
        this.mat = m;
        this.cla = null;
        this.lingua = lingua;
        editar = false;
        this.panelcor = KeyQuest.getSystemColor();
        this.url = url;
        framepai = frame;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelativa.setOpaque(true);
        labelativa.setBackground(new Color(250, 250, 250));
        atraso = -TableRequest.DEFAULT_LATE_INTERVAL * 60;
        this.btchamada = btchamada;
    }

    public ActionButton(javax.swing.JFrame frame, Keys.Classroom m, Langs.Locale lingua, String url, PersonalButtonRequest btchamada) {
        super(frame);
        this.cla = m;
        this.mat = null;
        this.lingua = lingua;
        editar = false;
        this.panelcor = KeyQuest.getSystemColor();
        this.url = url;
        framepai = frame;
        bimage = null;
        this.alterado = false;
        labelativa = new javax.swing.JLabel(lingua.translate("Estado"));
        labelativa.setPreferredSize(new Dimension(181, 32));
        labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelativa.setOpaque(true);
        labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelativa.setBackground(new Color(250, 250, 250));
        atraso = -TableRequest.DEFAULT_LATE_INTERVAL * 60;
        this.btchamada = btchamada;
    }

    public void create() {
        painel = new javax.swing.JPanel();
        painel.setBackground(new java.awt.Color(254, 254, 254));
        painel.setPreferredSize(new java.awt.Dimension(660, 528));
        this.setMinimumSize(new java.awt.Dimension(700, 500));
        this.setSize(new java.awt.Dimension(700, 500));
        this.setResizable(false);

        javax.swing.border.Border border = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(panelcor, 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        painel.setBorder(border);
        KeyQuest.addtoPropertyListener(painel, true);
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
        label1.setPreferredSize(new Dimension(181, 30));
        label1.setOpaque(true);
        label1.setForeground(Color.BLACK);
        label1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label1.setBackground(new Color(250, 250, 250));
        label1.setFont(new Font("Cantarell", Font.PLAIN, 14));
        label1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        // painel de informcao geral
        javax.swing.JPanel painel1 = new javax.swing.JPanel();
        painel1.setBackground(Color.WHITE);
        painel1.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK, 1)));
        javax.swing.JPanel painelimagem = new javax.swing.JPanel(null);
        painelimagem.setPreferredSize(new Dimension(360, 60));
        painelimagem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        painelimagem.setBackground(painel1.getBackground());
        painelimagem.setBounds(0, 0, 360, 60);
        painel1.add(painelimagem);
        if (cla != null) {
            this.setTitle(lingua.translate("Detalhes") + " " + lingua.translate("de") + " " + lingua.translate(cla.getTypeOfMaterialName()) + ": " + lingua.translate("sala") + " " + lingua.translate(cla.getDescription()));
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
            painelimagem.add(imageview);

            texto = new org.jdesktop.swingx.JXTextField[7];

            javax.swing.JLabel auxiliar = new javax.swing.JLabel(lingua.translate("Descrição") + ": ");
            auxiliar.setPreferredSize(new Dimension(179, 32));
            auxiliar.setBounds(85, 20, 120, 30);
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            painelimagem.add(auxiliar);

            String sauxiliar = lingua.translate("sala") + " " + lingua.translate(cla.getDescription());
            Border f = BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK));
            texto[0] = new org.jdesktop.swingx.JXTextField();
            texto[0].setText(sauxiliar);
            texto[0].setSelectionColor(Color.DARK_GRAY);
            texto[0].setBackground(new Color(249, 249, 249));
            texto[0].setFocusable(false);
            texto[0].setBounds(181, 22, 165, 30);
            texto[0].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painelimagem.add(texto[0]);

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
            auxiliar = new javax.swing.JLabel(lingua.translate("Código") + ": ");
            auxiliar.setPreferredSize(new Dimension(179, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            painel1Cima.add(auxiliar);
            texto[1] = new org.jdesktop.swingx.JXTextField();
            texto[1].setText(cla.getCodeOfMaterial());
            texto[1].setSelectionColor(Color.DARK_GRAY);
            texto[1].setBackground(new Color(249, 249, 249));
            texto[1].setFocusable(false);
            texto[1].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painel1Cima.add(texto[1]);

            // segunda linha
            auxiliar = new javax.swing.JLabel(lingua.translate("Estado") + ": ");
            auxiliar.setPreferredSize(new Dimension(179, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            painel1Cima.add(auxiliar);
            texto[2] = new org.jdesktop.swingx.JXTextField();
            if (cla.isLoaned()) {
                sauxiliar = lingua.translate("ocupado");
                texto[2].setForeground(Color.RED);
            } else {
                sauxiliar = lingua.translate("livre");
                texto[2].setForeground(new Color(0, 102, 0));
            }
            texto[2].setText(sauxiliar);
            texto[2].setBackground(new Color(249, 249, 249));
            texto[2].setFocusable(false);
            texto[2].setSelectionColor(Color.DARK_GRAY);
            texto[2].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painel1Cima.add(texto[2]);

            // terceira linha
            auxiliar = new javax.swing.JLabel(lingua.translate("Lugares") + ": ");
            auxiliar.setPreferredSize(new Dimension(181, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            painel1Cima.add(auxiliar);
            sauxiliar = "" + cla.getPlaces();
            texto[3] = new org.jdesktop.swingx.JXTextField();
            texto[3].setText(sauxiliar);
            texto[3].setSelectionColor(Color.DARK_GRAY);
            texto[3].setBackground(new Color(249, 249, 249));
            texto[3].setFocusable(false);
            texto[3].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painel1Cima.add(texto[3]);

            // quarta linha
            auxiliar = new javax.swing.JLabel(lingua.translate("Computadores") + ": ");
            auxiliar.setPreferredSize(new Dimension(181, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            painel1Cima.add(auxiliar);
            sauxiliar = "" + cla.getComputers();
            texto[4] = new org.jdesktop.swingx.JXTextField();
            texto[4].setText(sauxiliar);
            texto[4].setSelectionColor(Color.DARK_GRAY);
            texto[4].setBackground(new Color(249, 249, 249));
            texto[4].setFocusable(false);
            texto[4].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painel1Cima.add(texto[4]);

            // quinta linha
            auxiliar = new javax.swing.JLabel(lingua.translate("Projetor") + ": ");
            auxiliar.setPreferredSize(new Dimension(181, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            painel1Cima.add(auxiliar);
            texto[5] = new org.jdesktop.swingx.JXTextField();
            if (cla.hasProjector()) {
                sauxiliar = lingua.translate("Sim").toLowerCase();
            } else {
                sauxiliar = lingua.translate("Nao").toLowerCase();
            }
            texto[5].setText(sauxiliar);
            texto[5].setBackground(new Color(249, 249, 249));
            texto[5].setFocusable(false);
            texto[5].setSelectionColor(Color.DARK_GRAY);
            texto[5].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painel1Cima.add(texto[5]);

            // sexta linha
            auxiliar = new javax.swing.JLabel(lingua.translate("Quadro interativo") + ": ");
            auxiliar.setPreferredSize(new Dimension(181, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            painel1Cima.add(auxiliar);
            texto[6] = new org.jdesktop.swingx.JXTextField();
            if (cla.hasInteractiveTable()) {
                sauxiliar = lingua.translate("Sim").toLowerCase();
            } else {
                sauxiliar = lingua.translate("Nao").toLowerCase();
            }
            texto[6].setBackground(new Color(249, 249, 249));
            texto[6].setFocusable(false);
            texto[6].setSelectionColor(Color.DARK_GRAY);
            texto[6].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            texto[6].setText(sauxiliar);
            painel1Cima.add(texto[6]);

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

            javax.swing.border.Border baux[] = new javax.swing.border.Border[8];
            for (int i = 0; i < 7; i++) {
                if (i != 2) {
                    baux[i] = texto[i].getBorder();
                }
            }
            baux[7] = imageview.getBorder();
            bteditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            java.awt.event.MouseListener[] copypaste = new java.awt.event.MouseListener[7];
            java.awt.event.MouseListener mouseimage = new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FileIOAux.ImageExtension image;
                    image = bimage;

                    if ((bimage = FileIOAux.ImageAux.getImageFromFileDialog(lingua.translate("Escolha da imagem para previsualização"), imageview, ActionButton.this, 54, 44)) != null) {
                        alterado = true;
                    } else {
                        bimage = image;
                    }
                    /*
                    if ((bimage = FileIOAux.ImageAux.getImageFromFileChooser(imageview, lingua, 54, 44)) != null) {
                        alterado = true;
                    } else {
                        bimage = image;
                    }*/
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
            };
            for (int i = 0; i < copypaste.length; i++) {
                if (i != 2) {
                    copypaste[i] = Components.PopUpMenu.simpleCopyPaste(lingua, texto[i], true);
                }
            }
            bteditar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (DataBase.DataBase.testConnection(url)) {
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
                            for (int i = 0; i < texto.length; i++) {
                                if (i != 2) {
                                    texto[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), baux[i]));
                                }
                            }
                            imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                            bteditar.setToolTipText(lingua.translate("Gravar"));
                            for (int i = 0; i < texto.length; i++) {
                                if (i == 0) {
                                    texto[i].setText(lingua.translate(cla.getDescription()));
                                }
                                if (i != 2) {
                                    texto[i].setFocusable(true);
                                    texto[i].setBackground(Color.WHITE);
                                    texto[i].addMouseListener(copypaste[i]);
                                    texto[i].addKeyListener(new java.awt.event.KeyAdapter() {
                                        @Override
                                        public void keyPressed(KeyEvent e) {
                                            if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                                                for (JTextField texto1 : texto) {
                                                    texto1.setFocusable(false);
                                                    texto1.setText(cla.getDescription());
                                                    texto1.setBackground(new Color(249, 249, 249));
                                                }
                                                for (int i = 0; i < texto.length; i++) {
                                                    if (i != 2) {
                                                        texto[i].setBorder(baux[i]);
                                                        switch (i) {
                                                            case 0:
                                                                String sauxiliar = lingua.translate("sala") + " " + lingua.translate(cla.getDescription());
                                                                texto[0].setText(sauxiliar);
                                                                break;
                                                            case 1:
                                                                texto[1].setText(cla.getCodeOfMaterial());
                                                                break;
                                                            case 3:
                                                                texto[3].setText("" + cla.getPlaces());
                                                                break;
                                                            case 4:
                                                                texto[4].setText("" + cla.getComputers());
                                                                break;
                                                            case 5:
                                                                if (cla.hasProjector()) {
                                                                    texto[5].setText(lingua.translate("Sim").toLowerCase());
                                                                } else {
                                                                    texto[5].setText(lingua.translate("Nao").toLowerCase());
                                                                }
                                                                break;
                                                            case 6:
                                                                if (cla.hasInteractiveTable()) {
                                                                    texto[6].setText(lingua.translate("Sim").toLowerCase());
                                                                } else {
                                                                    texto[6].setText(lingua.translate("Nao").toLowerCase());
                                                                }
                                                                break;
                                                        }
                                                    }
                                                }
                                                imageview.setBorder(baux[7]);
                                                imageview.removeMouseListener(mouseimage);
                                                imageview.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
                            }
                            imageview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                            imageview.addMouseListener(mouseimage);
                            editar = true;
                        } else {
                            for (int i = 0; i < texto.length; i++) {
                                if (i != 2) {
                                    texto[i].removeMouseListener(copypaste[i]);
                                }
                            }
                            javax.swing.Timer timer;
                            for (int i = 0; i < texto.length; i++) {
                                if (i != 2) {
                                    texto[i].setBorder(baux[i]);
                                    texto[i].setBackground(new Color(249, 249, 249));
                                    texto[i].setFocusable(false);
                                }
                            }
                            imageview.setBorder(baux[7]);
                            imageview.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                            editar = false;
                            java.awt.image.BufferedImage imagebtok3 = null;
                            try {
                                if (this.getClass().getResource("Images/edit.png") != null) {
                                    imagebtok3 = ImageIO.read(getClass().getResourceAsStream("Images/edit.png"));
                                }
                            } catch (IOException ex) {
                            }
                            if (imagebtok3 != null) {
                                javax.swing.ImageIcon iconbtok = new javax.swing.ImageIcon(imagebtok3);
                                bteditar.setIcon(iconbtok);
                            }
                            bteditar.setToolTipText(lingua.translate("Editar campos"));
                            boolean auxiliar1 = false;
                            int onde = -1;
                            if (texto[0].getText().equals("")) {
                                auxiliar1 = true;
                                baux[0] = texto[0].getBorder();
                                timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                    texto[0].setBorder(baux[0]);
                                    String sauxiliar = lingua.translate("sala") + " " + lingua.translate(cla.getDescription());
                                    texto[0].setText(sauxiliar);
                                    bteditar.setEnabled(true);
                                });
                                onde = 0;
                                texto[0].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[0]));
                                timer.setRepeats(false);
                                bteditar.setEnabled(false);
                                timer.start();
                            }
                            if (texto[1].getText().equals("")) {
                                auxiliar1 = true;
                                baux[1] = texto[1].getBorder();
                                timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                    texto[1].setBorder(baux[1]);
                                    texto[1].setText(cla.getCodeOfMaterial());
                                    bteditar.setEnabled(true);
                                });
                                onde = 1;
                                texto[1].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[1]));
                                timer.setRepeats(false);
                                bteditar.setEnabled(false);
                                timer.start();
                            }
                            if (!texto[3].getText().matches("^\\d+$")) {
                                auxiliar1 = true;
                                baux[3] = texto[3].getBorder();
                                timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                    texto[3].setBorder(baux[3]);
                                    texto[3].setText("" + cla.getPlaces());
                                    bteditar.setEnabled(true);
                                });
                                onde = 3;
                                texto[3].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[3]));
                                timer.setRepeats(false);
                                bteditar.setEnabled(false);
                                timer.start();
                            }
                            if (!texto[4].getText().matches("^\\d+$")) {
                                auxiliar1 = true;
                                onde = 4;
                                baux[4] = texto[4].getBorder();
                                timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                    texto[4].setBorder(baux[4]);
                                    texto[4].setText("" + cla.getComputers());
                                    bteditar.setEnabled(true);
                                });
                                texto[4].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[4]));
                                timer.setRepeats(false);
                                bteditar.setEnabled(false);
                                timer.start();
                            }
                            if ((!texto[5].getText().toLowerCase().equals(lingua.translate("Sim").toLowerCase())) && (!texto[5].getText().toLowerCase().equals(lingua.translate("Nao").toLowerCase())) && (!texto[5].getText().toLowerCase().equals("nao"))) {
                                auxiliar1 = true;
                                onde = 5;
                                baux[5] = texto[5].getBorder();
                                timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                    texto[5].setBorder(baux[5]);
                                    if (cla.hasProjector()) {
                                        texto[5].setText(lingua.translate("Sim").toLowerCase());
                                    } else {
                                        texto[5].setText(lingua.translate("Nao").toLowerCase());
                                    }
                                    bteditar.setEnabled(true);
                                });
                                texto[5].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[5]));
                                timer.setRepeats(false);
                                bteditar.setEnabled(false);
                                timer.start();
                            }
                            if (!(texto[6].getText().toLowerCase().equals(lingua.translate("Sim").toLowerCase())) && (!texto[6].getText().toLowerCase().equals(lingua.translate("Nao").toLowerCase())) && (!texto[6].getText().toLowerCase().equals("nao"))) {
                                auxiliar1 = true;
                                onde = 6;
                                baux[6] = texto[6].getBorder();
                                timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                    texto[6].setBorder(baux[6]);
                                    if (cla.hasInteractiveTable()) {
                                        texto[6].setText(lingua.translate("Sim").toLowerCase());
                                    } else {
                                        texto[6].setText(lingua.translate("Nao").toLowerCase());
                                    }
                                    bteditar.setEnabled(true);
                                });
                                texto[6].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[6]));
                                timer.setRepeats(false);
                                bteditar.setEnabled(false);
                                timer.start();
                            }
                            if (auxiliar1) {
                                for (int i = 0; i < 6; i++) {
                                    if (i != onde) {
                                        switch (i) {
                                            case 0:
                                                String sauxiliar = lingua.translate("sala") + " " + lingua.translate(cla.getDescription());
                                                texto[0].setText(sauxiliar);
                                                break;
                                            case 1:
                                                texto[1].setText(cla.getCodeOfMaterial());
                                                break;
                                            case 3:
                                                texto[3].setText("" + cla.getPlaces());
                                                break;
                                            case 4:
                                                texto[4].setText("" + cla.getComputers());
                                                break;
                                            case 5:
                                                if (cla.hasProjector()) {
                                                    texto[5].setText(lingua.translate("Sim").toLowerCase());
                                                } else {
                                                    texto[5].setText(lingua.translate("Nao").toLowerCase());
                                                }
                                                break;
                                            case 6:
                                                if (cla.hasInteractiveTable()) {
                                                    texto[6].setText(lingua.translate("Sim").toLowerCase());
                                                } else {
                                                    texto[6].setText(lingua.translate("Nao").toLowerCase());
                                                }
                                                break;
                                        }
                                    }
                                }
                            } else if (DataBase.DataBase.testConnection(url)) {
                                cla.setCodeOfMaterial(texto[1].getText());
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
                                if (texto[4].getText().length() > 1) {
                                    int i = 0;
                                    while (i < texto[4].getText().length()) {
                                        if (texto[4].getText().charAt(i) == '0') {
                                            texto[4].setText(texto[4].getText().replaceFirst("0", ""));
                                        }
                                        if (texto[4].getText().charAt(i) != '0') {
                                            break;
                                        }
                                    }
                                }
                                cla.setPlaces(Integer.valueOf(texto[3].getText()));
                                cla.setComputers(Integer.valueOf(texto[4].getText()));
                                if ((texto[5].getText().toLowerCase().equals("nao"))) {
                                    texto[5].setText("não");
                                } else {
                                    texto[5].setText(texto[5].getText().toLowerCase());
                                }
                                if (texto[5].getText().equals(lingua.translate("Sim").toLowerCase())) {
                                    cla.setProjector(true);
                                } else {
                                    cla.setProjector(false);
                                }
                                if ((texto[6].getText().toLowerCase().equals("nao"))) {
                                    texto[6].setText("não");
                                } else {
                                    texto[6].setText(texto[6].getText().toLowerCase());
                                }
                                if (texto[6].getText().equals(lingua.translate("Sim").toLowerCase())) {
                                    cla.setInteractiveTable(true);
                                } else {
                                    cla.setInteractiveTable(false);
                                }
                                String chamada = cla.getDescription();
                                cla.setDescription(texto[0].getText());
                                DataBase.DataBase db = new DataBase.DataBase(url);
                                if ((bimage != null) && (!bimage.getExtension().equals("")) && (alterado)) {
                                    cla.setMaterialImage(bimage.getImage(), bimage.getExtension());
                                }
                                int val = db.updateMaterial(cla, alterado);
                                int val2 = db.updateClassroom(cla);

                                if ((val < 0) || (val2 < 0)) {
                                    Components.MessagePane message = new Components.MessagePane(Clavis.ActionButton.getWindows()[0], Components.MessagePane.AVISO, Clavis.KeyQuest.systemColor, lingua.translate("Aviso"), 400, 200, lingua.translate("O update não foi concretizado (parcial ou totalmente)."), new String[]{lingua.translate("Voltar")});
                                    message.showMessage();
                                }
                                String sauxiliar = lingua.translate("sala") + " " + lingua.translate(cla.getDescription());
                                texto[0].setText(sauxiliar);
                                if (btchamada != null) {
                                    javax.swing.ImageIcon ic;
                                    if (alterado) {
                                        BufferedImage ima = FileIOAux.ImageAux.transformFromBase64IntoImage(cla.getMaterialImage());
                                        if (ima != null) {
                                            ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                                            ic = new javax.swing.ImageIcon(ima);
                                            btchamada.setIcon(ic);
                                        }
                                    }
                                    if (!chamada.equals(texto[0].getText())) {
                                        btchamada.setText(cla.getDescription());
                                        btchamada.setDescription(cla.getDescription());
                                        sortAfterRename();
                                    }
                                }
                                for (java.awt.event.MouseListener l : imageview.getMouseListeners()) {
                                    imageview.removeMouseListener(l);
                                }
                                RequestList lista = Clavis.KeyQuest.getListRequest().getRequestList();
                                int jaestoucansado = 0;
                                for (Keys.Request r : lista.getRequests()) {
                                    if (r.getMaterial().getId() == getMaterial().getId()) {
                                        r.setMaterial(getMaterial());
                                        Clavis.KeyQuest.getListRequest().getTable().setValueAt(r.getMaterial().getDescription(), jaestoucansado, 1);

                                    }
                                    jaestoucansado++;
                                }
                                Clavis.KeyQuest.getListRequest().refreshPanel();
                                db.close();
                            } else {
                                Components.MessagePane message = new Components.MessagePane(Clavis.ActionButton.getWindows()[0], Components.MessagePane.AVISO, Clavis.KeyQuest.systemColor, lingua.translate("Aviso"), 400, 200, lingua.translate("Erro de conexão à base de dados") + ".", new String[]{lingua.translate("Voltar")});
                                message.showMessage();
                            }
                            alterado = false;
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
                Clavis.Windows.WMaterial janela = new Clavis.Windows.WMaterial(this, cla, lingua, url);

                Window[] windows = Window.getWindows();
                boolean pode_se = true;
                if (windows != null) {
                    for (Window w : windows) {
                        if ((w.isShowing()) && (w instanceof Clavis.Windows.WMaterial)) {
                            pode_se = false;
                        }
                    }
                }
                if (pode_se) {
                    janela.create();
                    janela.appear();
                }
            });
            painel1Baixo.add(btMais);

            painel1.add(painel1Cima);
            painel1.add(painel1Baixo);

            // painel esquerda
            javax.swing.JPanel painel2 = new javax.swing.JPanel();
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
                Window[] windows = Window.getWindows();
                for (int i = 0; i < windows.length; i++) {
                    if (windows[i] instanceof Clavis.Windows.WMaterial) {
                        windows[i].setVisible(false);
                        windows[i].dispose();
                    } else if (windows[i] instanceof Clavis.Windows.WShedule) {
                        windows[i].setVisible(false);
                        windows[i].dispose();
                    }
                }
                if (framepai != null) {
                    double x = this.getX() + (this.getWidth() / 2) - (framepai.getWidth() / 2);
                    double y = this.getY() + (this.getHeight() / 2) - (framepai.getHeight() / 2);
                    framepai.setLocation((int) x, (int) y);
                    framepai.setVisible(true);
                }
                if ((timertempoatrasado != null) && (timertempoatrasado.isRunning())) {
                    timertempoatrasado.stop();
                }
                this.dispose();
            });
            painel11.add(btsair);

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
            btreq.setBounds(285, 0, 90, 40);
            btreq.addActionListener((ActionEvent e) -> {
                Clavis.Windows.WRequestSpecific wreq = new Clavis.Windows.WRequestSpecific(panelcor, painel.getBackground(), getMaterial(), url, lingua, this);
                wreq.create();
                this.setModal(false);
                this.setVisible(false);
                wreq.appear();
                this.setVisible(false);
            });
            painel11.add(btreq);

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
                Clavis.Windows.WShedule horario = new Clavis.Windows.WShedule(this, panelcor, cla, url, lingua);
                Window[] windows = Window.getWindows();
                boolean pode_se = true;
                if (windows != null) {
                    for (Window w : windows) {
                        if ((w.isShowing()) && (w instanceof Clavis.Windows.WShedule)) {
                            pode_se = false;
                        }
                    }
                }
                if (pode_se) {
                    horario.create();
                    horario.appear();
                }
            });
            painel22.add(bthorario);

            if (DataBase.DataBase.testConnection(url)) {
                DataBase.DataBase db = new DataBase.DataBase(url);
                Keys.Request req = db.getCurrentRequest(cla);
                db.close();
                if ((cla.isLoaned()) || (req.getId() > 0)) {
                    javax.swing.JButton btentrega = new javax.swing.JButton();
                    btentrega.setPreferredSize(new Dimension(90, 40));
                    btentrega.setBackground(new Color(165, 42, 42));
                    btentrega.setBounds(100, 0, 90, 40);
                    try {
                        if (Clavis.KeyQuest.class.getResource("Images/entrega.png") != null) {
                            BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/entrega.png"));
                            javax.swing.ImageIcon ico = new javax.swing.ImageIcon(im);
                            btentrega.setIcon(ico);
                        } else {
                            btentrega.setText(lingua.translate("Devolver"));
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ActionButton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btentrega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btentrega.setToolTipText(lingua.translate("Marcar devolução"));
                    painel22.add(btentrega);
                    btentrega.addActionListener((ActionEvent e) -> {
                        this.confirmDelivery(btentrega, painel22, painel2, req, cla);
                    });
                }
            }
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
                    Window[] windows = Window.getWindows();
                    for (int i = 0; i < windows.length; i++) {
                        if (windows[i] instanceof Clavis.Windows.WMaterial) {
                            windows[i].setVisible(false);
                            windows[i].dispose();
                        } else if (windows[i] instanceof Clavis.Windows.WShedule) {
                            windows[i].setVisible(false);
                            windows[i].dispose();
                        }
                    }
                    if (framepai != null) {
                        framepai.setLocation(getX(), getY());
                        framepai.setVisible(true);
                    }
                    if ((timertempoatrasado != null) && (timertempoatrasado.isRunning())) {
                        timertempoatrasado.stop();
                    }
                    dispose();
                }
            });
        } else {
            this.setTitle(lingua.translate("Detalhes") + " " + lingua.translate("de") + " " + lingua.translate(getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(getMaterial().getDescription()));
            javax.swing.JLabel imageview = new javax.swing.JLabel();
            imageview.setPreferredSize(new Dimension(60, 50));
            imageview.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            imageview.setVerticalAlignment(javax.swing.JLabel.CENTER);
            imageview.setBounds(14, 10, 60, 50);
            imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.BLACK, 1)));
            if (!mat.getMaterialImage().equals("sem")) {
                bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.transformFromBase64IntoImage(getMaterial().getMaterialImage()));
                imageview.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
            } else {
                String path = new File("").getAbsolutePath() + System.getProperty("file.separator") + "Resources" + System.getProperty("file.separator") + "Images" + System.getProperty("file.separator") + getMaterial().getTypeOfMaterialImage() + ".png";
                bimage = new FileIOAux.ImageExtension(FileIOAux.ImageAux.getImageFromFile(new File(path)));
                imageview.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(bimage.getImage(), 54, 44)));
            }
            painelimagem.add(imageview);
            painel1.add(painelimagem);

            // painel de informacao top
            javax.swing.JPanel painel1Cima = new javax.swing.JPanel();
            painel1Cima.setLayout(null);
            painel1Cima.setBackground(Color.WHITE);
            painel1Cima.setPreferredSize(new Dimension(370, 180));
            painel1Cima.setBounds(0, 40, 360, 180);
            painel1Cima.setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 20));

            // adicionando componentes ao painel de informacao top
            // primeira linha
            javax.swing.JLabel auxiliar = new javax.swing.JLabel(lingua.translate("Descrição") + ": ");
            auxiliar.setPreferredSize(new Dimension(179, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            auxiliar.setBounds(20, 10, 110, 28);
            painel1Cima.add(auxiliar);

            String sauxiliar = lingua.translate(getMaterial().getDescription());
            texto = new org.jdesktop.swingx.JXTextField[3];
            Border f = BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK));
            texto[0] = new org.jdesktop.swingx.JXTextField();
            texto[0].setText(sauxiliar);
            texto[0].setBackground(new Color(249, 249, 249));
            texto[0].setSelectionColor(Color.DARK_GRAY);
            texto[0].setBounds(140, 12, 210, 28);
            texto[0].setFocusable(false);

            texto[0].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painel1Cima.add(texto[0]);

            // segunda linha
            auxiliar = new javax.swing.JLabel(lingua.translate("Código") + ": ");
            auxiliar.setPreferredSize(new Dimension(179, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            auxiliar.setBounds(20, 40, 110, 28);
            painel1Cima.add(auxiliar);

            sauxiliar = lingua.translate(getMaterial().getCodeOfMaterial());
            texto[1] = new org.jdesktop.swingx.JXTextField();
            texto[1].setText(sauxiliar);
            texto[1].setSelectionColor(Color.DARK_GRAY);
            texto[1].setBackground(new Color(249, 249, 249));
            texto[1].setBounds(140, 42, 210, 28);
            texto[1].setFocusable(false);
            texto[1].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painel1Cima.add(texto[1]);

            // terceira linha
            auxiliar = new javax.swing.JLabel(lingua.translate("Estado") + ": ");
            auxiliar.setPreferredSize(new Dimension(179, 32));
            auxiliar.setFont(new Font("Cantarell", Font.PLAIN, 14));
            auxiliar.setBounds(20, 70, 110, 28);
            auxiliar.setHorizontalAlignment(javax.swing.JLabel.LEADING);
            painel1Cima.add(auxiliar);
            texto[2] = new org.jdesktop.swingx.JXTextField();
            if (getMaterial().isLoaned()) {
                sauxiliar = lingua.translate("ocupado");
                texto[2].setForeground(Color.RED);
            } else {
                sauxiliar = lingua.translate("livre");
                texto[2].setForeground(new Color(0, 102, 0));
            }
            texto[2].setText(sauxiliar);
            texto[2].setBackground(new Color(249, 249, 249));
            texto[2].setFocusable(false);
            texto[2].setSelectionColor(Color.DARK_GRAY);
            texto[2].setBounds(140, 72, 210, 28);
            texto[2].setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 5, 0, 0)));
            painel1Cima.add(texto[2]);

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

            javax.swing.border.Border baux[] = new javax.swing.border.Border[3];
            java.awt.event.MouseListener mouseimage = new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FileIOAux.ImageExtension image;
                    image = bimage;
                    UIManager.put("TextField.background", new Color(234, 234, 234));
                    UIManager.put("List.background", new Color(234, 234, 234));
                    if ((bimage = FileIOAux.ImageAux.getImageFromFileDialog(lingua.translate("Escolha da imagem para previsualização"), imageview, ActionButton.this, 54, 44)) != null) {
                        alterado = true;
                    } else {
                        bimage = image;
                    }
                    /*
                    if ((bimage = FileIOAux.ImageAux.getImageFromFileChooser(imageview, lingua, 54, 44)) != null) {
                        alterado = true;
                    } else {
                        bimage = image;
                    }*/

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
            };
            baux[0] = texto[0].getBorder();
            baux[1] = texto[1].getBorder();
            baux[2] = imageview.getBorder();
            bteditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            java.awt.event.MouseListener copypaste = Components.PopUpMenu.simpleCopyPaste(lingua, texto[0], true);
            java.awt.event.MouseListener copypaste2 = Components.PopUpMenu.simpleCopyPaste(lingua, texto[1], true);
            bteditar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (DataBase.DataBase.testConnection(url)) {
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
                            texto[0].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), baux[0]));
                            imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                            bteditar.setToolTipText(lingua.translate("Gravar"));
                            texto[0].setFocusable(true);
                            texto[0].setBackground(Color.WHITE);
                            texto[0].addMouseListener(copypaste);
                            texto[0].addKeyListener(new java.awt.event.KeyAdapter() {
                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                                        for (JTextField texto1 : texto) {
                                            texto1.setFocusable(false);
                                            texto1.setBackground(new Color(249, 249, 249));
                                        }
                                        texto[0].setText(getMaterial().getDescription());
                                        texto[0].setBorder(baux[0]);
                                        texto[1].setText(getMaterial().getCodeOfMaterial());
                                        texto[1].setBorder(baux[1]);
                                        imageview.setBorder(baux[2]);
                                        imageview.removeMouseListener(mouseimage);
                                        imageview.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                        editar = false;
                                        imageview.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
                            texto[1].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), baux[0]));
                            imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                            bteditar.setToolTipText(lingua.translate("Gravar"));
                            texto[1].setFocusable(true);
                            texto[1].setBackground(Color.WHITE);
                            texto[1].addMouseListener(copypaste2);
                            texto[1].addKeyListener(new java.awt.event.KeyAdapter() {
                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                                        for (JTextField texto1 : texto) {
                                            texto1.setFocusable(false);
                                            texto1.setBackground(new Color(249, 249, 249));
                                        }
                                        texto[0].setText(getMaterial().getDescription());
                                        texto[0].setBorder(baux[0]);
                                        texto[1].setText(getMaterial().getCodeOfMaterial());
                                        texto[1].setBorder(baux[1]);
                                        imageview.setBorder(baux[2]);
                                        imageview.removeMouseListener(mouseimage);
                                        imageview.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
                            imageview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            imageview.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
                            imageview.addMouseListener(mouseimage);
                            editar = true;
                        } else {
                            texto[0].removeMouseListener(copypaste);
                            javax.swing.Timer timer;
                            if (baux[0] != null) {
                                texto[0].setBorder(baux[0]);
                            }
                            if (baux[1] != null) {
                                texto[1].setBorder(baux[1]);
                            }
                            imageview.setBorder(baux[2]);
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
                            boolean bauxiliar = false;
                            if (texto[0].getText().equals("")) {
                                baux[0] = texto[0].getBorder();
                                timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                    texto[0].setBorder(baux[0]);
                                    texto[0].setText(getMaterial().getDescription());
                                    texto[1].setBorder(baux[1]);
                                    bteditar.setEnabled(true);

                                });
                                texto[1].setText(getMaterial().getCodeOfMaterial());
                                texto[0].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[0]));
                                texto[1].setFocusable(false);
                                texto[1].setBackground(new Color(249, 249, 249));
                                texto[0].setFocusable(false);
                                texto[0].setBackground(new Color(249, 249, 249));
                                timer.setRepeats(false);
                                bteditar.setEnabled(false);
                                bauxiliar = true;
                                timer.start();
                            }
                            if (texto[1].getText().equals("")) {
                                baux[1] = texto[1].getBorder();
                                timer = new javax.swing.Timer(2000, (ActionEvent et) -> {
                                    texto[1].setBorder(baux[1]);
                                    texto[1].setText(getMaterial().getCodeOfMaterial());
                                    texto[0].setBorder(baux[0]);
                                    bteditar.setEnabled(true);
                                });
                                texto[0].setText(getMaterial().getDescription());
                                texto[1].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), baux[1]));
                                texto[0].setFocusable(false);
                                texto[0].setBackground(new Color(249, 249, 249));
                                texto[1].setFocusable(false);
                                texto[1].setBackground(new Color(249, 249, 249));
                                timer.setRepeats(false);
                                bteditar.setEnabled(false);
                                bauxiliar = true;
                                timer.start();
                            }
                            if (!bauxiliar) {
                                if (DataBase.DataBase.testConnection(url)) {
                                    DataBase.DataBase db = new DataBase.DataBase(url);
                                    if ((bimage != null) && (!bimage.getExtension().equals("")) && (alterado)) {
                                        getMaterial().setMaterialImage(bimage.getImage(), bimage.getExtension());
                                    }
                                    String chamada = getMaterial().getDescription();
                                    getMaterial().setDescription(texto[0].getText());
                                    getMaterial().setCodeOfMaterial(texto[1].getText());
                                    int val = db.updateMaterial(getMaterial(), alterado);
                                    if (val < 0) {
                                        Components.MessagePane message = new Components.MessagePane(Clavis.ActionButton.getWindows()[0], Components.MessagePane.AVISO, Clavis.KeyQuest.systemColor, lingua.translate("Aviso"), 400, 200, lingua.translate("O update não foi concretizado."), new String[]{lingua.translate("Voltar")});
                                        message.showMessage();
                                    }

                                    for (java.awt.event.MouseListener l : imageview.getMouseListeners()) {
                                        imageview.removeMouseListener(l);
                                    }
                                    db.close();
                                    if (btchamada != null) {
                                        javax.swing.ImageIcon ic;
                                        if (alterado) {
                                            BufferedImage ima = FileIOAux.ImageAux.transformFromBase64IntoImage(getMaterial().getMaterialImage());
                                            if (ima != null) {
                                                ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                                                ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                                                ic = new javax.swing.ImageIcon(ima);
                                                btchamada.setIcon(ic);
                                            }
                                        }
                                        if (!chamada.equals(texto[0].getText())) {
                                            btchamada.setText(getMaterial().getDescription());
                                            btchamada.setDescription(getMaterial().getDescription());
                                            sortAfterRename();
                                        }
                                    }
                                    texto[0].setBorder(baux[0]);
                                    texto[0].setFocusable(false);
                                    texto[0].setBackground(new Color(249, 249, 249));
                                    texto[1].setBorder(baux[1]);
                                    texto[1].setFocusable(false);
                                    texto[1].setBackground(new Color(249, 249, 249));
                                    RequestList lista = Clavis.KeyQuest.getListRequest().getRequestList();
                                    int jaestoucansado = 0;
                                    for (Keys.Request r : lista.getRequests()) {
                                        if (r.getMaterial().getId() == getMaterial().getId()) {
                                            r.setMaterial(getMaterial());
                                            Clavis.KeyQuest.getListRequest().getTable().setValueAt(r.getMaterial().getDescription(), jaestoucansado, 1);

                                        }
                                        jaestoucansado++;
                                    }
                                    Clavis.KeyQuest.getListRequest().refreshPanel();
                                } else {
                                    Components.MessagePane message = new Components.MessagePane(Clavis.ActionButton.getWindows()[0], Components.MessagePane.AVISO, Clavis.KeyQuest.systemColor, lingua.translate("Aviso"), 400, 200, lingua.translate("Erro de conexão à base de dados") + ".", new String[]{lingua.translate("Voltar")});
                                    message.showMessage();
                                }
                            }
                            alterado = false;
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
                Clavis.Windows.WMaterial janela = new Clavis.Windows.WMaterial(this, getMaterial(), lingua, url);

                Window[] windows = Window.getWindows();
                boolean pode_se = true;
                if (windows != null) {
                    for (Window w : windows) {
                        if ((w.isShowing()) && (w instanceof Clavis.Windows.WMaterial)) {
                            pode_se = false;
                        }
                    }
                }
                if (pode_se) {
                    janela.create();
                    janela.appear();
                }
            });
            painel1Baixo.add(btMais);

            painel1.add(painel1Cima);
            painel1.add(painel1Baixo);

            // painel esquerda
            javax.swing.JPanel painel2 = new javax.swing.JPanel();

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
                Window[] windows = Window.getWindows();
                for (int i = 0; i < windows.length; i++) {
                    if (windows[i] instanceof Clavis.Windows.WMaterial) {
                        windows[i].setVisible(false);
                        windows[i].dispose();
                    } else if (windows[i] instanceof Clavis.Windows.WShedule) {
                        windows[i].setVisible(false);
                        windows[i].dispose();
                    }
                }
                if (framepai != null) {
                    double x = this.getX() + (this.getWidth() / 2) - (framepai.getWidth() / 2);
                    double y = this.getY() + (this.getHeight() / 2) - (framepai.getHeight() / 2);
                    framepai.setLocation((int) x, (int) y);
                    framepai.setVisible(true);
                }
                if ((timertempoatrasado != null) && (timertempoatrasado.isRunning())) {
                    timertempoatrasado.stop();
                }
                this.dispose();
            });
            painel11.add(btsair);

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
            btreq.addActionListener((ActionEvent e) -> {
                Clavis.Windows.WRequestSpecific wreq = new Clavis.Windows.WRequestSpecific(panelcor, painel.getBackground(), getMaterial(), url, lingua, this);
                wreq.create();
                this.setModal(false);
                this.setVisible(false);
                wreq.appear();

            });
            btreq.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btreq.setBounds(285, 0, 90, 40);
            painel11.add(btreq);

            javax.swing.JPanel painel22 = new javax.swing.JPanel();
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
                Clavis.Windows.WShedule horario = new Clavis.Windows.WShedule(this, panelcor, getMaterial(), url, lingua);
                Window[] windows = Window.getWindows();
                boolean pode_se = true;
                if (windows != null) {
                    for (Window w : windows) {
                        if ((w.isShowing()) && (w instanceof Clavis.Windows.WShedule)) {
                            pode_se = false;
                        }
                    }
                }
                if (pode_se) {
                    horario.create();
                    horario.appear();
                }
            });
            painel22.add(bthorario);

            if (DataBase.DataBase.testConnection(url)) {
                DataBase.DataBase db = new DataBase.DataBase(url);
                Keys.Request req = db.getCurrentRequest(getMaterial());
                db.close();
                if ((getMaterial().isLoaned()) || (req.getId() > 0)) {
                    javax.swing.JButton btentrega = new javax.swing.JButton();
                    btentrega.setPreferredSize(new Dimension(90, 40));
                    btentrega.setBounds(100, 0, 90, 40);
                    btentrega.setBackground(new Color(165, 42, 42));
                    try {
                        if (Clavis.KeyQuest.class.getResource("Images/entrega.png") != null) {
                            BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/entrega.png"));
                            javax.swing.ImageIcon ico = new javax.swing.ImageIcon(im);
                            btentrega.setIcon(ico);
                        } else {
                            btentrega.setText(lingua.translate("Devolver"));
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ActionButton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    btentrega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btentrega.setToolTipText(lingua.translate("Marcar devolução"));
                    painel22.add(btentrega);
                    btentrega.addActionListener((ActionEvent e) -> {
                        this.confirmDelivery(btentrega, painel22, painel2, req, getMaterial());
                    });
                }

            }

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
                    Window[] windows = Window.getWindows();
                    for (int i = 0; i < windows.length; i++) {
                        if (windows[i] instanceof Clavis.Windows.WMaterial) {
                            windows[i].setVisible(false);
                            windows[i].dispose();
                        } else if (windows[i] instanceof Clavis.Windows.WShedule) {
                            windows[i].setVisible(false);
                            windows[i].dispose();
                        }
                    }
                    if (framepai != null) {
                        double x = getX() + (getWidth() / 2) - (framepai.getWidth() / 2);
                        double y = getY() + (getHeight() / 2) - (framepai.getHeight() / 2);
                        framepai.setLocation((int) x, (int) y);
                        framepai.setVisible(true);
                    }
                    if ((timertempoatrasado != null) && (timertempoatrasado.isRunning())) {
                        timertempoatrasado.stop();
                    }
                    dispose();
                }
            });

        }
    }

    public void open() {
        this.create();
        if (framepai != null) {
            this.setLocation((framepai.getX() + (framepai.getWidth()) / 2) - (this.getWidth() / 2), (framepai.getY() + (framepai.getHeight()) / 2) - (this.getHeight() / 2));
        } else {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
            int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
            this.setLocation(x, y);
        }
        this.setModal(true);
        this.setVisible(true);
    }

    public void confirmDelivery(javax.swing.JButton bt, javax.swing.JPanel panel1, javax.swing.JPanel panel2, Keys.Request req, Keys.Material mat) {
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            java.util.Set<Keys.Request> reqs = db.getUnionRequests(req);
            db.changeRequestTerminateState(req);
            if (reqs.size() > 0) {
                for (Keys.Request re : reqs) {
                    db.changeRequestTerminateState(re);
                }
            }
            Clavis.KeyQuest.refreshDevolutionTable();
            int val = db.getNextRequest(mat).getId();
            db.close();
            req.setTerminated();
            bt.setVisible(false);
            panel1.remove(bt);
            if (val > 0) {
                labelativa.setText(lingua.translate("Próxima requisição"));
            } else {
                labelativa.setText(lingua.translate("Estado"));
            }
            texto[2].setText(lingua.translate("livre"));
            texto[2].setForeground(new Color(0, 102, 0));
            panel2.removeAll();
            mat.setLoaned(false);
            this.createPanelRight(panel2);
            if (btchamada != null) {
                btchamada.setBackground(Clavis.ButtonListRequest.FREE_COLOR);
                btchamada.setBorder(BorderFactory.createEmptyBorder(0, 9, 0, 9));
                if ((Clavis.KeyQuest.getMaterialsButtonsTable() != null) && (Clavis.KeyQuest.getMaterialsButtonsTable().getComponentAt(2) != null)) {
                    javax.swing.JScrollPane ps = ((javax.swing.JScrollPane) Clavis.KeyQuest.getMaterialsButtonsTable().getComponentAt(2));
                    javax.swing.JViewport jv = (javax.swing.JViewport) ps.getComponent(0);
                    javax.swing.JPanel pan = (javax.swing.JPanel) jv.getComponent(0);
                    for (int i = 0; i < pan.getComponentCount(); i++) {
                        PersonalButtonRequest pb = (PersonalButtonRequest) pan.getComponent(i);
                        if (pb.getValue() == btchamada.getValue()) {
                            pan.remove(i);
                        }
                    }
                }
                if ((Clavis.KeyQuest.getMaterialsButtonsTable() != null) && (Clavis.KeyQuest.getMaterialsButtonsTable().getComponentAt(1) != null)) {
                    javax.swing.JScrollPane ps1 = ((javax.swing.JScrollPane) Clavis.KeyQuest.getMaterialsButtonsTable().getComponentAt(1));
                    javax.swing.JViewport jv1 = (javax.swing.JViewport) ps1.getComponent(0);
                    javax.swing.JPanel pan1 = (javax.swing.JPanel) jv1.getComponent(0);
                    java.util.Set<PersonalButtonRequest> novo = new java.util.TreeSet<>();
                    try {
                        novo.add((PersonalButtonRequest) btchamada.clone());
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(ActionButton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i = 0; i < pan1.getComponentCount(); i++) {
                        novo.add(((PersonalButtonRequest) pan1.getComponent(i)));
                    }
                    pan1.removeAll();
                    for (PersonalButtonRequest n : novo) {
                        pan1.add(n);
                    }
                }
                java.util.Set<PersonalButtonRequest> novo = new java.util.TreeSet<>();
                javax.swing.JScrollPane ps0 = ((javax.swing.JScrollPane) Clavis.KeyQuest.getMaterialsButtonsTable().getComponentAt(0));
                javax.swing.JViewport jv0 = (javax.swing.JViewport) ps0.getComponent(0);
                javax.swing.JPanel pan0 = (javax.swing.JPanel) jv0.getComponent(0);
                for (int i = 0; i < pan0.getComponentCount(); i++) {
                    if (btchamada.getValue() == ((PersonalButtonRequest) pan0.getComponent(i)).getValue()) {
                        ((PersonalButtonRequest) pan0.getComponent(i)).setBackground(ButtonListRequest.FREE_COLOR);
                    }
                }
            }
        }
    }

    public void sortAfterRename() {
        if (Clavis.KeyQuest.getMaterialsButtonsTable() != null) {
            javax.swing.JScrollPane ps0 = ((javax.swing.JScrollPane) Clavis.KeyQuest.getMaterialsButtonsTable().getComponentAt(0));
            javax.swing.JViewport jv0 = (javax.swing.JViewport) ps0.getComponent(0);
            javax.swing.JPanel pan0 = (javax.swing.JPanel) jv0.getComponent(0);
            java.util.Set<PersonalButtonRequest> novo = new java.util.TreeSet<>();
            for (int i = 0; i < pan0.getComponentCount(); i++) {
                novo.add(((PersonalButtonRequest) pan0.getComponent(i)));
            }
            pan0.removeAll();
            for (PersonalButtonRequest n : novo) {
                pan0.add(n);
            }
            if (Clavis.KeyQuest.getMaterialsButtonsTable().getComponentCount() > 1) {
                javax.swing.JScrollPane ps1 = ((javax.swing.JScrollPane) Clavis.KeyQuest.getMaterialsButtonsTable().getComponentAt(1));
                javax.swing.JViewport jv1 = (javax.swing.JViewport) ps1.getComponent(0);
                javax.swing.JPanel pan1 = (javax.swing.JPanel) jv1.getComponent(0);
                novo = new java.util.TreeSet<>();
                for (int i = 0; i < pan1.getComponentCount(); i++) {
                    PersonalButtonRequest btr = ((PersonalButtonRequest) pan1.getComponent(i));
                    if (btr.getValue() == btchamada.getValue()) {
                        try {
                            novo.add((PersonalButtonRequest) btchamada.clone());
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(ActionButton.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        novo.add(btr);
                    }
                }
                pan1.removeAll();
                for (PersonalButtonRequest n : novo) {
                    pan1.add(n);
                }
                javax.swing.JScrollPane ps2 = ((javax.swing.JScrollPane) Clavis.KeyQuest.getMaterialsButtonsTable().getComponentAt(2));
                javax.swing.JViewport jv2 = (javax.swing.JViewport) ps2.getComponent(0);
                javax.swing.JPanel pan2 = (javax.swing.JPanel) jv2.getComponent(0);
                novo = new java.util.TreeSet<>();
                for (int i = 0; i < pan2.getComponentCount(); i++) {
                    PersonalButtonRequest btr = ((PersonalButtonRequest) pan2.getComponent(i));
                    if (btr.getValue() == btchamada.getValue()) {
                        try {
                            novo.add((PersonalButtonRequest) btchamada.clone());
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(ActionButton.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        novo.add(btr);
                    }
                }
                pan2.removeAll();
                for (PersonalButtonRequest n : novo) {
                    pan2.add(n);
                }
            }
        }
    }

    private void createPanelRight(javax.swing.JPanel painel2) {
        DataBase.DataBase db = new DataBase.DataBase(url);
        Keys.Request req;
        java.util.Set<Keys.Request> requnion;
        boolean emprestado;
        if (cla != null) {
            if (!cla.isLoaned()) {
                req = db.getNextRequest(cla);
                requnion = db.getUnionRequests(req);
                emprestado = false;
            } else {
                req = db.getCurrentRequest(cla);
                requnion = db.getUnionRequests(req);
                emprestado = true;
                if (req.getId() == -1) {
                    req = db.getNextRequest(cla);
                    requnion = db.getUnionRequests(req);
                    emprestado = false;
                }
            }
        } else if (!mat.isLoaned()) {
            req = db.getNextRequest(getMaterial());
            requnion = db.getUnionRequests(req);
            emprestado = false;
        } else {

            req = db.getCurrentRequest(getMaterial());
            requnion = db.getUnionRequests(req);
            emprestado = true;
            if (req.getId() == -1) {
                req = db.getNextRequest(getMaterial());
                requnion = db.getUnionRequests(req);
                emprestado = false;
            }
        }
        db.close();
        javax.swing.JLabel lbtl;
        if (req.getId() != -1) {
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
            if (cla != null) {
                if ((!cla.isLoaned()) || (!emprestado)) {
                    labelativa = new javax.swing.JLabel(lingua.translate("Próxima requisição"));
                    labelativa.setPreferredSize(new Dimension(181, 32));
                    labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                    labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    labelativa.setOpaque(true);
                    labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    labelativa.setBackground(new Color(250, 250, 250));
                    painel2.setBackground(new Color(246, 255, 248));
                    atrasado = false;
                    dia = false;
                } else if ((cla.isLoaned()) && (emprestado) && ((dat.isBigger(new TimeDate.Date()) == 0) && (tim.compareTime(new TimeDate.Time()) + atraso > 0))) {
                    labelativa = new javax.swing.JLabel(lingua.translate("Atrasado") + " " + lingua.translate("na entrega"));
                    labelativa.setPreferredSize(new Dimension(181, 32));
                    labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                    labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    labelativa.setOpaque(true);
                    labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    labelativa.setBackground(new Color(250, 250, 250));
                    painel2.setBackground(new Color(255, 246, 248));
                    atrasado = true;
                    dia = false;
                } else if ((cla.isLoaned()) && (emprestado) && ((dat.isBigger(new TimeDate.Date()) > 0))) {
                    labelativa = new javax.swing.JLabel(lingua.translate(lingua.translate("Atrasado") + ": " + dat.isBigger(new TimeDate.Date()) + "") + " " + lingua.translate("dia(s)"));
                    labelativa.setPreferredSize(new Dimension(181, 32));
                    labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                    labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    labelativa.setOpaque(true);
                    labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    labelativa.setBackground(new Color(250, 250, 250));
                    painel2.setBackground(new Color(255, 246, 248));
                    atrasado = true;
                    dia = true;
                } else {
                    labelativa = new javax.swing.JLabel(lingua.translate("Requisitado por"));
                    labelativa.setPreferredSize(new Dimension(181, 32));
                    labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                    labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                    painel2.setBackground(new Color(245, 245, 220));
                    labelativa.setOpaque(true);
                    labelativa.setBackground(new Color(250, 250, 250));
                    atrasado = false;
                    dia = false;
                }
            } else if ((!mat.isLoaned()) || (!emprestado)) {
                labelativa = new javax.swing.JLabel(lingua.translate("Próxima requisição"));
                labelativa.setPreferredSize(new Dimension(181, 32));
                labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                labelativa.setOpaque(true);
                labelativa.setBackground(new Color(250, 250, 250));
                painel2.setBackground(new Color(246, 255, 248));
                atrasado = false;
                dia = false;
            } else if ((getMaterial().isLoaned()) && (emprestado) && ((dat.isBigger(new TimeDate.Date()) == 0) && (tim.compareTime(new TimeDate.Time()) + atraso > 0))) {
                labelativa = new javax.swing.JLabel(lingua.translate("Atrasado") + " " + lingua.translate("na entrega"));
                labelativa.setPreferredSize(new Dimension(181, 32));
                labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                labelativa.setOpaque(true);
                labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labelativa.setBackground(new Color(250, 250, 250));
                painel2.setBackground(new Color(255, 246, 248));
                atrasado = true;
                dia = false;
            } else if ((getMaterial().isLoaned()) && (emprestado) && ((dat.isBigger(new TimeDate.Date()) > 0))) {
                labelativa = new javax.swing.JLabel(lingua.translate(lingua.translate("Atrasado") + ": " + dat.isBigger(new TimeDate.Date()) + "") + " " + lingua.translate("dia(s)"));
                labelativa.setPreferredSize(new Dimension(181, 32));
                labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                labelativa.setOpaque(true);
                labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labelativa.setBackground(new Color(250, 250, 250));
                painel2.setBackground(new Color(255, 246, 248));
                atrasado = true;
                dia = true;
            } else {
                labelativa = new javax.swing.JLabel(lingua.translate("Requisitado por"));
                labelativa.setPreferredSize(new Dimension(181, 32));
                labelativa.setFont(new Font("Cantarell", Font.PLAIN, 14));
                labelativa.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                painel2.setBackground(new Color(245, 245, 220));
                labelativa.setOpaque(true);
                labelativa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                labelativa.setBackground(new Color(250, 250, 250));
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
            if (cla != null) {
                if (!cla.isLoaned() || (!emprestado)) {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Dia"));
                } else {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Até ao dia"));
                }
            } else if (!mat.isLoaned() || (!emprestado)) {
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
            if (cla != null) {
                if (!cla.isLoaned() || (!emprestado)) {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Hora"));
                } else {
                    lbtl1 = new javax.swing.JLabel(lingua.translate("Hora de entrega"));
                }
            } else if (!mat.isLoaned() || (!emprestado)) {
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
            if (cla != null) {
                if (!cla.isLoaned() || (!emprestado)) {
                    if (requnion.isEmpty()) {
                        lb1 = new javax.swing.JLabel(lingua.translate("Desde as") + ": " + req.getTimeBegin().toString(0) + " " + lingua.translate("até às") + " " + req.getTimeEnd().toString(0) + ".");
                    } else {
                        tim = req.getTimeEnd();
                        for (Keys.Request re : requnion) {
                            if (re.getTimeEnd().compareTime(tim) < 0) {
                                tim = re.getTimeEnd();
                            }
                        }
                        lb1 = new javax.swing.JLabel(lingua.translate("Desde as") + " " + req.getTimeBegin().toString(0) + " " + lingua.translate("até às") + " " + tim.toString(0) + ".");
                    }
                    labelauxiliar = new javax.swing.JLabel(lb1.getText());
                } else if (requnion.isEmpty()) {
                    lb1 = new javax.swing.JLabel(req.getTimeEnd().toString(0));
                    labelauxiliar = new javax.swing.JLabel(lb1.getText());
                } else {
                    tim = req.getTimeEnd();
                    for (Keys.Request re : requnion) {
                        if (re.getTimeEnd().compareTime(tim) < 0) {
                            tim = re.getTimeEnd();
                        }
                        lb1 = new javax.swing.JLabel(tim.toString(0));
                    }
                    labelauxiliar = new javax.swing.JLabel(lb1.getText());
                }
            } else if (!mat.isLoaned() || (!emprestado)) {
                if (requnion.isEmpty()) {
                    lb1 = new javax.swing.JLabel(lingua.translate("Hora de início") + ": " + req.getTimeBegin().toString(0));
                } else {
                    tim = req.getTimeEnd();
                    for (Keys.Request re : requnion) {
                        if (re.getTimeEnd().compareTime(tim) < 0) {
                            tim = re.getTimeEnd();
                        }
                    }
                    lb1 = new javax.swing.JLabel(lingua.translate("Hora de entrega") + ": " + tim.toString(0));
                }
                labelauxiliar = new javax.swing.JLabel(lb1.getText());
            } else if (requnion.isEmpty()) {
                lb1 = new javax.swing.JLabel(req.getTimeEnd().toString(0));
                labelauxiliar = new javax.swing.JLabel(lb1.getText());
            } else {
                tim = req.getTimeEnd();
                for (Keys.Request re : requnion) {
                    if (re.getTimeEnd().compareTime(tim) < 0) {
                        tim = re.getTimeEnd();
                    }
                    lb1 = new javax.swing.JLabel(tim.toString(0));
                }
                labelauxiliar = new javax.swing.JLabel(lb1.getText());
            }
            if ((atrasado) && (!dia)) {
                labelauxiliar.setForeground(Color.RED);
                double t = (double)tim.compareTime(new TimeDate.Time()) / 60.0;
                double decimal;
                String hora;
                String minutos;
                if (t > 59) {
                    t = t / 60.0;
                    decimal = (int) t;
                    decimal = t - decimal;
                    decimal = decimal * 60.0;
                    t = (int) t;
                    hora = "" + (int) t;
                    minutos = "" + (int) Math.round(decimal);
                } else {
                    hora = "0";
                    minutos = "" + (int) Math.round(t);
                }
                if (hora.length() == 1) {
                    hora = "0" + hora;
                }
                if (minutos.length() == 1) {
                    minutos = "0" + minutos;
                }
                labelauxiliar.setText(tim.toString(0) + " (" + lingua.translate("mais") + " " + hora + ":" + minutos + ")");
                this.timertempoatrasado = new Timer(1000, (ActionEvent e) -> {
                    double t1 = (double)tim.compareTime(new TimeDate.Time()) / 60.0;
                    double decimal1;
                    String hora1;
                    String minutos1;
                    if (t1 > 59) {
                        t1 = t1 / 60.0;
                        decimal1 = (int) t1;
                        decimal1 = t1 - decimal1;
                        decimal1 = decimal1 * 60.0;
                        t1 = (int) t1;
                        hora1 = "" + (int) t1;
                        minutos1 = "" + (int) Math.round(decimal1);
                    } else {
                        hora1 = "0";
                        minutos1 = "" + (int) Math.round(t1);
                    }
                    if (hora1.length() == 1) {
                        hora1 = "0" + hora1;
                    }
                    if (minutos1.length() == 1) {
                        minutos1 = "0" + minutos1;
                    }
                    labelauxiliar.setText(tim.toString(0) + " (" + lingua.translate("mais") + " " + hora1 + ":" + minutos1 + ")");
                });
                timertempoatrasado.start();
            }
            labelauxiliar.setFont(new Font("Cantarell", Font.PLAIN, 12));
            labelauxiliar.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            labelauxiliar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 8, 0, 4), BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)));
            labelauxiliar.setPreferredSize(new Dimension(219, 20));
            labelauxiliar.setBounds(0, 140, 219, 20);
            painel2.add(labelauxiliar);

            lbtl1 = new javax.swing.JLabel(lingua.translate("Atividade"));
            lbtl1.setFont(new Font("Cantarell", Font.PLAIN, 14));
            lbtl1.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            lbtl1.setForeground(Color.BLUE);
            lbtl1.setPreferredSize(new Dimension(219, 20));
            lbtl1.setBounds(0, 170, 219, 20);
            painel2.add(lbtl1);
            if (cla != null) {
                if ((!req.getActivity().equals("sem")) && (!req.getActivity().equals(""))) {
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
            } else if ((!req.getActivity().equals("sem")) && (!req.getActivity().equals(""))) {
                lb1 = new javax.swing.JLabel(lingua.translate(req.getActivity()));
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

    /**
     * @return the atraso
     */
    public int getDelay() {
        return atraso;
    }

    /**
     * @param atraso the atraso to set
     */
    public void setDelay(int atraso) {
        this.atraso = -atraso;
    }

    public static synchronized ActionButton getActionButton() {
        //if (app == null) app = new ActionButton();
        return app;
    }

    /**
     * @return the mat
     */
    public Keys.Material getMaterial() {
        if (mat == null) {
            return cla;
        }
        return mat;
    }

}
