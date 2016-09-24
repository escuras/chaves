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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

/**
 *
 * @author toze
 */
public class ButtonListRequest {

    public static final Color OCCUPIED_COLOR = new Color(255, 100, 100);
    public static final Color FREE_COLOR = new Color(100, 100, 255);
    private List<PersonalButtonRequest> bLista;
    private List<Keys.Material> mater;
    private Dimension dim;
    private Langs.Locale lingua;
    private javax.swing.JTabbedPane tpanel;
    private javax.swing.JPanel pane;
    private Color btcor1;
    private Color btcor2;
    private Color panelcor;
    private java.util.Iterator<Keys.Material> iterador;
    private int tipopesquisa;
    private String url;
    private javax.swing.JLabel labelativa;
    private javax.swing.JFrame frameanterior;
    private int selecionado;
    private int valor;

    public ButtonListRequest(String url, javax.swing.JFrame frame, Keys.TypeOfMaterial tipo, Langs.Locale lingua, javax.swing.JTabbedPane tpanel, int tipopesquisa, String pesquisa) {
        this.mater = new ArrayList<>();
        this.lingua = lingua;
        this.url = url;
        this.frameanterior = frame;
        this.panelcor = KeyQuest.getSystemColor();
        pane = new javax.swing.JPanel();
        pane.setPreferredSize(tpanel.getPreferredSize());
        dim = new Dimension(140, 140);
        this.btcor1 = ButtonListRequest.OCCUPIED_COLOR;
        this.btcor2 = ButtonListRequest.FREE_COLOR;
        this.tpanel = tpanel;
        this.tipopesquisa = tipopesquisa;
        if (DataBase.DataBase.testConnection(url)) {
            int val = tipo.getMaterialTypeID();
            DataBase.DataBase db = new DataBase.DataBase(url);
            switch (tipopesquisa) {
                case 0:
                    if (val == 1) {
                        mater = new ArrayList<>(db.getClassrooms(0));
                    } else {
                        mater = new ArrayList<>(db.getMaterialsByType(val, 0));
                    }
                    break;
                case 1:
                    if (val == 1) {
                        mater = new ArrayList<>(db.getClassrooms(1));
                    } else {
                        mater = new ArrayList<>(db.getMaterialsByType(val, 1));
                    }
                    break;
                case 3:
                    mater = new ArrayList<>(db.searchForMaterials(tipo, pesquisa));
                    System.out.println(mater.size());
                    break;
                default:
                    if (val == 1) {
                        mater = new ArrayList<>(db.getClassrooms(2));
                    } else {
                        mater = new ArrayList<>(db.getMaterialsByType(val, 2));
                    }
                    break;
            }
            db.close();
        }
        Collections.sort(mater);
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

    public List<PersonalButtonRequest> getButtons() {
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
            valor = 0;
            for (PersonalButtonRequest r : bLista) {
                r.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                             mater.get(selecionado);
                             ActionButton at;
                            if (mater.get(selecionado) instanceof Keys.Classroom) {
                                at = new ActionButton(frameanterior, (Keys.Classroom) mater.get(selecionado), lingua, url, r);
                            } else {
                                at = new ActionButton(frameanterior, mater.get(selecionado), lingua, url, r);
                            }
                            frameanterior.setVisible(false);
                            at.open();
                        } else {
                            int va = 0;
                            for (PersonalButtonRequest ro : bLista) {
                                ro.setBorder(null);
                                if (ro.getValue() == r.getValue()) {
                                    ro.setBorder(BorderFactory.createLineBorder(Color.RED,1));
                                    selecionado = va;
                                }
                                va++;
                            }
                            System.out.println(getSelectedMaterial());
                        }
                    }
                });
                r.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ENTER:
                                ActionButton at = new ActionButton(frameanterior,  mater.get(selecionado), lingua, url, r);
                                frameanterior.setVisible(false);
                                at.open();
                                break;
                            case KeyEvent.VK_ESCAPE:
                                selecionado = -1;
                                for (PersonalButtonRequest ro : bLista) {
                                    ro.setBorder(null);
                                }   break;
                            case KeyEvent.VK_LEFT:
                                if (selecionado > 0) {
                                    selecionado = selecionado - 1;
                                    int va = 0;
                                    for (PersonalButtonRequest ro : bLista) {
                                        ro.setBorder(null);
                                        if (va == selecionado) {
                                            ro.setBorder(BorderFactory.createLineBorder(Color.RED,1));
                                        }
                                        va++;
                                    }
                                }   break;
                            case KeyEvent.VK_RIGHT:
                                if ((selecionado < mater.size()-1)&&(selecionado >= 0)) {
                                    selecionado = selecionado + 1;
                                    int va = 0;
                                    for (PersonalButtonRequest ro : bLista) {
                                        ro.setBorder(null);
                                        if (va == selecionado) {
                                            ro.setBorder(BorderFactory.createLineBorder(Color.RED,1));
                                        }
                                        va++;
                                    }
                                }   break;
                            default:
                                break;
                        }
                    }
                });
                valor++;
            }
        }
        return bLista;
    }

    public Keys.Material getSelectedMaterial() {
        int j = 0;
        for (Keys.Material m : mater) {
            if (j == selecionado) {
                return m;
            }
            j++;
        }
        return null;
    }

    public javax.swing.JScrollPane getScrollPane() {
        javax.swing.JScrollPane aux = new javax.swing.JScrollPane();
        aux.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        aux.getVerticalScrollBar().setUnitIncrement(10);
        this.bLista = this.getButtons();
        pane.setBackground(new Color(245, 245, 220));
        int valorborder = 50;
        pane.setLayout(new Components.ModifiedFlowLayout());
        pane.setBorder(new EmptyBorder(20, valorborder, 20, valorborder));
        if (!this.bLista.isEmpty()) {

            bLista.stream().forEach((bt) -> {
                pane.add(bt, BorderLayout.PAGE_END);
            });
            int largura = (int) (pane.getPreferredSize().getWidth() - (valorborder * 2));
            int valorbotoes = largura / (int) (dim.getWidth());
            int valinicial = 50 + (int) dim.getHeight();
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

    public PersonalButtonRequest(PersonalButtonRequest r) {
        super();
        valor = r.getValue();
        this.designacao = r.getDescription();
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
        if ((this.getText().matches("\\d+")) && (o.getText().matches("\\d+"))) {
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
