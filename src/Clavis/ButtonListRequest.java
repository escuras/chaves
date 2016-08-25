/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

/**
 *
 * @author toze
 */
public class ButtonListRequest {

    private List<javax.swing.JButton> bLista;
    private Set<Keys.Material> mater;
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

    public ButtonListRequest(String url, javax.swing.JDialog dialogo, RequestList req, String csv, Langs.Locale lingua, javax.swing.JTabbedPane tpanel, int tipopesquisa) {
        this.mater = new HashSet<>();
        this.lingua = lingua;
        this.url = url;
        this.dialogoanterior = dialogo;

        this.panelcor = KeyQuest.getSystemColor();
        pane = new javax.swing.JPanel();
        pane.setPreferredSize(tpanel.getPreferredSize());

        dim = new Dimension(80, 80);
        this.btcor1 = new Color(54, 54, 154);
        this.btcor2 = new Color(145, 145, 254);
        this.tpanel = tpanel;
        this.tipopesquisa = tipopesquisa;
        if (DataBase.DataBase.testConnection(url)) {
            int val = req.getTypeOfMaterial().getMaterialTypeID();
            DataBase.DataBase db = new DataBase.DataBase(csv);
            switch (tipopesquisa) {
                case 0:
                    if (val == 1) {
                        mater = new TreeSet<>(db.getClassrooms(0));
                    } else {
                        mater = new TreeSet<>(db.getMaterialsByType(val, 0));
                    }
                    break;
                case 1:
                    if (val == 1) {
                        mater = new TreeSet<>(db.getClassrooms(1));
                    } else {
                        mater = new TreeSet<>(db.getMaterialsByType(val, 1));
                    }
                    break;
                default:
                    if (val == 1) {
                        mater = new TreeSet<>(db.getClassrooms(2));
                    } else {
                        mater = new TreeSet<>(db.getMaterialsByType(val, 2));
                    }
                    break;
            }
            db.close();
        }
        iterador = mater.iterator();
    }

    public String[] getListOfMaterialType() {
        String[] nomes = new String[this.mater.size()];
        int i = 0;
        if (this.iterador.next() instanceof Keys.Material) {
            for (Object n : this.mater) {
                Keys.Material m = (Keys.Material) n;
                nomes[i] = lingua.translate(m.getDescription());
                i++;
            }
        } else {
            for (Object n : this.mater) {
                Keys.Classroom m = (Keys.Classroom) n;
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
                button.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
                if (!(n instanceof Keys.Classroom)) {
                    Keys.Material m = (Keys.Material) n;
                    if (m.isLoaned()) {
                        button.setBackground(btcor1);
                    } else {
                        button.setBackground(btcor2);
                    }
                    button.setText(this.lingua.translate(m.getDescription()));
                    button.setToolTipText(lingua.translate(m.getTypeOfMaterialName())+": "+m.getDescription());
                    button.addActionListener((ActionEvent e) -> {
                        ActionButton at = new ActionButton(dialogoanterior, m, lingua, url, button);
                        dialogoanterior.setVisible(false);
                        at.open();
                    });
                    javax.swing.ImageIcon ic;
                    if (m.getMaterialImage().equals("sem")) {
                        BufferedImage ima;
                        try {
                            File file = new File(new File("").getAbsolutePath()
                                    + System.getProperty("file.separator")
                                    + "Resources" + System.getProperty("file.separator")
                                    + "Images" + System.getProperty("file.separator")
                                    + m.getTypeOfMaterialImage() + ".png");
                            if (file.isFile()) {
                                ima = ImageIO.read(file);
                                ima = FileIOAux.ImageAux.resize(ima, dim.width -(dim.width/2), dim.height - (dim.height/2));
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
                            ima = FileIOAux.ImageAux.resize(ima, dim.width -(dim.width/2), dim.height - (dim.height/2));
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    }
                } else {
                    Keys.Classroom m = (Keys.Classroom) n;
                    if (m.isLoaned()) {
                        button.setBackground(btcor1);
                    } else {
                        button.setBackground(btcor2);
                    }
                    button.setText(this.lingua.translate(m.getDescription()));
                    button.setToolTipText(lingua.translate(m.getTypeOfMaterialName())+": "+m.getDescription());
                    button.addActionListener((ActionEvent e) -> {
                        ActionButton at = new ActionButton(dialogoanterior, m, lingua, url, button);
                        dialogoanterior.setVisible(false);
                        at.open();
                    });
                    javax.swing.ImageIcon ic;
                    if (m.getMaterialImage().equals("sem")) {
                        BufferedImage ima;
                        try {
                             File file = new File(new File("").getAbsolutePath()
                                    + System.getProperty("file.separator")
                                    + "Resources" + System.getProperty("file.separator")
                                    + "Images" + System.getProperty("file.separator")
                                    + m.getTypeOfMaterialImage() + ".png");
                            if (file.isFile()) {
                                ima = ImageIO.read(file);
                                ima = FileIOAux.ImageAux.resize(ima, dim.width -(dim.width/2), dim.height - (dim.height/2));
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
                            ima = FileIOAux.ImageAux.resize(ima, dim.width -(dim.width/2), dim.height - (dim.height/2));
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
        int valorborder = 50; 
        if (!this.bLista.isEmpty()) {
            pane.setLayout(new Components.ModifiedFlowLayout());
            pane.setBorder(new EmptyBorder(20, valorborder, 20, valorborder));
            pane.setBackground(new Color(245, 245, 220));
            bLista.stream().forEach((bt) -> {
                pane.add(bt, BorderLayout.PAGE_END);
            });
            int largura = (int) (pane.getPreferredSize().getWidth() - (valorborder*2));
            int valorbotoes = largura / (int)(dim.getWidth());
            int valinicial = 20 + (int)dim.getHeight() + 30;
            int i = 1;
            while (i < bLista.size()) {
                if ((i % valorbotoes) == 0) {
                    valinicial += bLista.get(i).getHeight() + 5;
                }
                i++;
            }
            pane.setPreferredSize(new Dimension(0, valinicial));
        }
        aux.setViewportView(pane);
        return aux;
    }

}
