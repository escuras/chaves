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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
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

    public ButtonListRequest(RequestList req, String csv, Langs.Locale lingua, javax.swing.JTabbedPane tpanel, int tipopesquisa, Color panelcolor) {
        this.mater = new HashSet<>();
        this.lingua = lingua;
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
        System.out.println(this.mater.size());
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
                    button.addActionListener(new ActionButton(m));
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
                    button.addActionListener(new ActionButton(m));
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
            int laux = 0;
            int altura = (int) tpanel.getHeight();
            int largura = (int) tpanel.getWidth();
            System.out.println("altura: " + altura);
            System.out.println("largura: " + largura);
            int aaux = 0;
            int valinicial = 50;
            int i = 0;
            while (i < bLista.size()) {
                if ((i % 5 + 1) == 1) {
                    System.out.println(bLista.get(i).getHeight());
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

    class ActionButton implements ActionListener {

        private final Clavis.Material mat;
        private final Clavis.Classroom cla;
        private javax.swing.JDialog janela;
        private javax.swing.JPanel painel;

        public ActionButton(Clavis.Material m) {
            this.mat = m;
            this.cla = null;
        }

        public ActionButton(Clavis.Classroom m) {
            this.cla = m;
            this.mat = null;
            janela = new javax.swing.JDialog();
            painel = new javax.swing.JPanel();
            painel.setBackground(new java.awt.Color(254, 254, 254));
            painel.setBorder(null);
            painel.setPreferredSize(new java.awt.Dimension(660, 528));
            janela.setTitle(lingua.translate("Detalhes")+" "+lingua.translate("de")+" "+lingua.translate(cla.getTypeOfMaterialName())+": "+lingua.translate(cla.getDescription()));
            janela.setMinimumSize(new java.awt.Dimension(700, 500));
            janela.setResizable(false);
            janela.setAlwaysOnTop(true);
            javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(panelcor, 6), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
            javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1), border11);
            painel.setBorder(border22);
            javax.swing.GroupLayout janelaLayout = new javax.swing.GroupLayout(janela.getContentPane());
            janela.getContentPane().setLayout(janelaLayout);
            janelaLayout.setHorizontalGroup(
                    janelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            janelaLayout.setVerticalGroup(
                    janelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            //painel.setLayout(null);
            javax.swing.JLabel lab = new javax.swing.JLabel("ola mundo");
            lab.setPreferredSize(new Dimension(100,30));
            lab.setBounds(0, 0, 100, 30);
            painel.add(lab);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((mat == null) && (cla != null)) {
                janela.setVisible(true);
                janela.setLocationRelativeTo(tpanel);
            } else if ((cla == null) && (mat != null)) {
                JOptionPane.showMessageDialog(null, "Ola mundo " + mat.getDescription());
            }
        }

    }

}
