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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author toze
 */
public class ButtonListRequest {

    List<javax.swing.JButton> bLista;
    Set mater;
    Dimension dim;
    Langs.Locale lingua;
    javax.swing.JTabbedPane tpanel;
    javax.swing.JPanel pane;
    Color btcor;
    Color panelcor;

    public ButtonListRequest(RequestList req, String csv, Langs.Locale lingua, javax.swing.JTabbedPane tpanel) {
        this.mater = new HashSet<>();
        this.lingua = lingua;
        pane = new javax.swing.JPanel();
        pane.setPreferredSize(tpanel.getPreferredSize());
        DataBase.DataBase db = new DataBase.DataBase(csv);
        dim = new Dimension(80, 80);
        this.btcor = new Color(254,254,254);
        this.tpanel = tpanel;
        int val = req.getTypeOfMaterial().getMaterialTypeID();
        if (val == 1) {
            mater = new TreeSet<Clavis.Classroom>(db.getClassrooms());
        } else {
            mater = new TreeSet<Clavis.Material>(db.getMaterialsByType(val));
        }
    }

    public String[] getListOfMaterialType() {
        String[] nomes = new String[this.mater.size()];
        int i = 0;
        if (this.mater instanceof Clavis.Material) {
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
        int val = 5;
        if (!this.mater.isEmpty()) {
            if (this.mater instanceof Clavis.Material) {
                for (Object n : this.mater) {
                    Clavis.Material m = (Clavis.Material) n;
                    javax.swing.JButton button = new javax.swing.JButton();
                    button.setText(this.lingua.translate(m.getDescription()));
                    button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    button.setHorizontalAlignment(SwingConstants.CENTER);
                    button.setPreferredSize(dim);
                    button.setMaximumSize(dim);
                    button.setHorizontalTextPosition(SwingConstants.CENTER);
                    button.setVerticalTextPosition(SwingConstants.BOTTOM);
                    button.setHorizontalAlignment(SwingConstants.CENTER);
                    button.setBackground(btcor);
                    button.setFocusPainted(false);
                    button.setBounds(0, 0, 80, 80);
                    button.setSize(new Dimension(100, 100));
                    button.addActionListener(new ActionButton(m));
                    javax.swing.ImageIcon ic;
                    if (m.getMaterialImage().equals("sem")) {
                        java.io.File file = new java.io.File("src/Main/Images/sala.png");
                        if (file.isFile()) {
                            BufferedImage ima = FileIOAux.ImageAux.getImageFromFile(file);
                            ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    } else {
                        BufferedImage ima = FileIOAux.ImageAux.transformFromBase64IntoImage(m.getMaterialImage());
                        if ( ima != null) {
                            ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    }
                    bLista.add(button);
                }
            } else {
                for (Object n : this.mater) {
                    Clavis.Classroom m = (Clavis.Classroom) n;
                    javax.swing.JButton button = new javax.swing.JButton();
                    button.setText(this.lingua.translate(m.getDescription()));
                    button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    button.setHorizontalAlignment(SwingConstants.CENTER);
                    button.setPreferredSize(dim);
                    button.setMaximumSize(dim);
                    button.setFocusPainted(false);
                    button.setHorizontalTextPosition(SwingConstants.CENTER);
                    button.setVerticalTextPosition(SwingConstants.BOTTOM);
                    button.setHorizontalAlignment(SwingConstants.CENTER);
                    button.setBackground(btcor);
                    button.setBounds(0, 0, 80, 80);
                    button.setSize(new Dimension(100, 100));
                    button.addActionListener(new ActionButton(m));
                    javax.swing.ImageIcon ic;
                    if (m.getMaterialImage().equals("sem")) {
                        java.io.File file = new java.io.File("src/Main/Images/sala.png");
                        if (file.isFile()) {
                            BufferedImage ima = FileIOAux.ImageAux.getImageFromFile(file);
                            ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    } else {
                        BufferedImage ima = FileIOAux.ImageAux.transformFromBase64IntoImage(m.getMaterialImage());
                        if ( ima != null) {
                            ima = FileIOAux.ImageAux.resize(ima, 40, 40);
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    }
                    bLista.add(button);
                    val+=55;
                }
            }
        }
        return bLista;
    }
    
   

    public javax.swing.JScrollPane getScrollPane() {
        javax.swing.JScrollPane aux = new javax.swing.JScrollPane();
        this.bLista = this.getButtons();
        if (!this.bLista.isEmpty()) {
            //GridBagLayout ff = new GridBagLayout();
            //GridBagConstraints conts = new GridBagConstraints();
            int i = 0;
            //pane.setBounds(new Rectangle(400, 100));
            pane.setLayout(new Components.ModifiedFlowLayout());
            pane.setBorder(new EmptyBorder(20, 0, 20, 0));
            pane.setMinimumSize(new Dimension(200, 200));
            pane.setMaximumSize(new Dimension(200, 2000));
            pane.setBackground(Color.DARK_GRAY);
            //pane.setLayout(ff);
            int g = 0;
            int v = 0;
            for (javax.swing.JButton bt : bLista) {
                pane.add(bt, BorderLayout.PAGE_END);
            }
            pane.addComponentListener(new ComponentListener() {
                int j = 0;

                @Override
                public void componentResized(ComponentEvent e) {
                    int i = 0;
                    int laux = 0;
                    int altura = (int) pane.getHeight();
                    int largura = (int) pane.getWidth();
                    int aaux = 0;
                    j++;
                    int val = 83;
                    while (i < bLista.size()) {
                        if ((laux + val >= largura) || (largura <= laux)) {
                            aaux += bLista.get(i).getHeight() + 30;
                            laux = 0;
                        }
                        laux += bLista.get(i).getWidth() + 20;
                        i++;
                    }
                    if (laux != 0) {
                        aaux = aaux - bLista.get(0).getHeight() + 30;
                    }
                    if (aaux > altura) {
                        pane.setPreferredSize(new Dimension(0, aaux));
                    } else if (aaux < altura) {
                        while (altura > aaux) {
                            altura -= bLista.get(0).getHeight() + 20;
                        }
                        pane.setPreferredSize(new Dimension(0, altura));
                    }

                }

                @Override
                public void componentMoved(ComponentEvent e) {

                }

                @Override
                public void componentShown(ComponentEvent e) {
                }

                @Override
                public void componentHidden(ComponentEvent e) {
                }

            });

        }

        aux.setViewportView(pane);
        return aux;
    }

    class ActionButton implements ActionListener {

        private Clavis.Material mat;
        private Clavis.Classroom cla;

        public ActionButton(Clavis.Material m) {
            this.mat = m;
            this.cla = null;
        }

        public ActionButton(Clavis.Classroom m) {
            this.cla = m;
            this.mat = null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((mat == null) && (cla != null)) {
                JOptionPane.showMessageDialog(null, "Ola mundo " + cla.getDescription());
            } else if ((cla == null) && (mat != null)) {
                JOptionPane.showMessageDialog(null, "Ola mundo " + mat.getDescription());
            }
        }

    }

}
