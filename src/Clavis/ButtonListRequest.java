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

    public static final Color OCCUPIED_COLOR = new Color(120, 120, 120);
    public static final Color FREE_COLOR = new Color(170, 170, 170);
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
        this.btcor1 = ButtonListRequest.OCCUPIED_COLOR;
        this.btcor2 = ButtonListRequest.FREE_COLOR;
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
        if (!this.mater.isEmpty()) {
            for (Object n : this.mater) {
                PersonalButtonRequest button = new PersonalButtonRequest();
                button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setPreferredSize(dim);
                button.setMaximumSize(dim);
                button.setFocusPainted(false);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
                if (!(n instanceof Keys.Classroom)) {
                    Keys.Material m = (Keys.Material) n;
                    button.setValue(m.getId());
                    button.setDescription(m.getDescription());
                    if (m.isLoaned()) {
                        button.setBackground(btcor1);
                    } else {
                        button.setBackground(btcor2);
                    }
                    button.setText(this.lingua.translate(m.getDescription()));
                    button.setToolTipText(lingua.translate(m.getTypeOfMaterialName()) + ": " + m.getDescription());
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
                                ima = FileIOAux.ImageAux.resize(ima, dim.width - (dim.width / 2), dim.height - (dim.height / 2));
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
                            ima = FileIOAux.ImageAux.resize(ima, dim.width - (dim.width / 2), dim.height - (dim.height / 2));
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    }
                } else {
                    Keys.Classroom m = (Keys.Classroom) n;
                    button.setValue(m.getId());
                    button.setDescription(m.getDescription());
                    if (m.isLoaned()) {
                        button.setBackground(btcor1);
                    } else {
                        button.setBackground(btcor2);
                    }
                    button.setText(this.lingua.translate(m.getDescription()));
                    button.setToolTipText(lingua.translate(m.getTypeOfMaterialName()) + ": " + m.getDescription());
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
                                ima = FileIOAux.ImageAux.resize(ima, dim.width - (dim.width / 2), dim.height - (dim.height / 2));
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
                            ima = FileIOAux.ImageAux.resize(ima, dim.width - (dim.width / 2), dim.height - (dim.height / 2));
                            ima = FileIOAux.ImageAux.makeRoundedCorner(ima, 45);
                            ic = new javax.swing.ImageIcon(ima);
                            button.setIcon(ic);
                        }
                    }
                }
                bLista.add(button);
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
            int largura = (int) (pane.getPreferredSize().getWidth() - (valorborder * 2));
            int valorbotoes = largura / (int) (dim.getWidth());
            int valinicial = 20 + (int) dim.getHeight() + 30;
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

class PersonalButtonRequest extends javax.swing.JButton implements Comparable<PersonalButtonRequest>, Cloneable {

    int valor;
    String designacao;

    public PersonalButtonRequest() {
        super();
        valor = -1;
        designacao = "";
    }

    public PersonalButtonRequest(int val, String designacao) {
        super();
        valor = val;
        this.designacao = designacao;
    }

    public void setValue(int val) {
        valor = val;
    }

    public int getValue() {
        return valor;
    }

    public void setDescription(String designacao) {
        this.designacao = designacao;
    }

    public String getDescription() {
        return designacao;
    }

    @Override
    public int compareTo(PersonalButtonRequest o) {
        
        if((this.getText().matches("\\d+"))&&(o.getText().matches("\\d+"))) {
            String texto = this.getText();
            String texto2 = o.getText();
            while (texto.charAt(0) == '0') {
                texto = texto.replaceFirst("0", "");
            }
            while (texto2.charAt(0) == '0') {
                texto2 = texto2.replaceFirst("0", "");
            }
            int val1 = Integer.parseInt(texto);
            int val2 = Integer.parseInt(texto2);
            return (val1 - val2);
        }
        int val;
        val = this.getDescription().compareTo(o.getDescription());
        if (val == 0) {
            val = this.getText().compareTo(o.getText());
            if (val == 0) {
                val = this.getValue() - o.getValue();
            }
        }
        return val;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
