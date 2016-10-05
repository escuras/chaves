/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author toze
 */
public class ButtonListRequest {

    public static final Color OCCUPIED_COLOR = new Color(155, 140, 190);
    public static final Color FREE_COLOR = new Color(100, 145, 255);
    private List<PersonalButtonRequest> bLista;
    private List<Keys.Material> mater;
    private Dimension dim;
    private Langs.Locale lingua;
    private javax.swing.JPanel pane;
    private Color btcor1;
    private Color btcor2;
    private java.util.Iterator<Keys.Material> iterador;
    private String url;
    private javax.swing.JFrame frameanterior;
    private int selecionado;
    private javax.swing.JLabel lnome;
    private javax.swing.JLabel lcodigo;
    private int botoeslinha;
    private java.util.List<Keys.Request> rlista;
    private javax.swing.JList<String> jListRequisicoes;
    private javax.swing.JScrollPane jScrollPane2;
    private int ndias;
    private boolean lista;
    private KeyListener lkey;
    private MouseListener lmou;
    Border emptyBorder;
    Border emptyBorder2;

    public ButtonListRequest(String url, javax.swing.JFrame frame, Keys.TypeOfMaterial tipo, Langs.Locale lingua, javax.swing.JTabbedPane tpanel, int tipopesquisa, String pesquisa) {
        this.mater = new ArrayList<>();
        this.lingua = lingua;
        this.url = url;
        this.frameanterior = frame;
        pane = new javax.swing.JPanel();
        pane.setPreferredSize(tpanel.getPreferredSize());
        dim = new Dimension(82, 82);
        this.btcor1 = ButtonListRequest.OCCUPIED_COLOR;
        this.btcor2 = ButtonListRequest.FREE_COLOR;
        this.rlista = new java.util.ArrayList<>();
        selecionado = -1;
        lista = false;
        emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        emptyBorder2 = BorderFactory.createEmptyBorder(0, 9, 0, 9);
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

    public void addComponentsToBehavior(javax.swing.JLabel nome, javax.swing.JLabel codigo) {
        lnome = nome;
        lcodigo = codigo;
    }

    public List<PersonalButtonRequest> getButtons() {
        this.bLista = new ArrayList<>();
        Font f = new Font("Contarell", Font.PLAIN, 12);
        if (!this.mater.isEmpty()) {
            this.mater.stream().map((n) -> {
                PersonalButtonRequest button = new PersonalButtonRequest();
                button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setPreferredSize(dim);
                button.setMaximumSize(dim);
                button.setFocusPainted(false);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalAlignment(SwingConstants.CENTER);
                //button.setBorder(emptyBorder);
                button.setFocusPainted(false);
                button.setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
                if (!(n instanceof Keys.Classroom)) {
                    Keys.Material m = n;
                    button.setValue(m.getId());
                    String auxiliar = m.getDescription();
                    button.setFont(f);
                    Clavis.Windows.WRequest.treatLongStrings(auxiliar, 10, f);
                    button.setDescription(auxiliar);
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
                    String auxiliar = m.getDescription();
                    Clavis.Windows.WRequest.treatLongStrings(auxiliar, 60, button.getFont());
                    button.setDescription(auxiliar);
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
                return button;
            }).forEach((button) -> {
                bLista.add(button);
            });
            for (PersonalButtonRequest r : bLista) {
                r.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getClickCount() == 2) {
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
                            int vax = selecionado;
                            for (PersonalButtonRequest ro : bLista) {
                                ro.setBorder(emptyBorder);
                                if (ro.getValue() == r.getValue()) {
                                    ro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), emptyBorder2));
                                    selecionado = va;
                                }
                                va++;
                            }
                            if (selecionado == vax) {
                                ActionButton at;
                                if (mater.get(selecionado) instanceof Keys.Classroom) {
                                    at = new ActionButton(frameanterior, (Keys.Classroom) mater.get(selecionado), lingua, url, r);
                                } else {
                                    at = new ActionButton(frameanterior, mater.get(selecionado), lingua, url, r);
                                }
                                frameanterior.setVisible(false);
                                at.open();
                            }
                            if (vax != selecionado) {
                                if ((lnome != null) && (lcodigo != null)) {
                                    if (selecionado < 0) {
                                        lnome.setText("");
                                        lcodigo.setText("");
                                    } else {
                                        Keys.Material sel = getSelectedMaterial();
                                        if (sel.getMaterialTypeID() == 1) {
                                            lnome.setText(lingua.translate(sel.getTypeOfMaterialName()) + " " + sel.getDescription());
                                        } else {
                                            lnome.setText(sel.getDescription());
                                        }
                                        lcodigo.setText(sel.getCodeOfMaterial());
                                    }
                                    if (lista) {
                                        makeList();
                                    }
                                }
                            }
                        }
                    }
                });
                r.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ENTER:
                                ActionButton at;
                                if (mater.get(selecionado) instanceof Keys.Classroom) {
                                    at = new ActionButton(frameanterior, (Keys.Classroom) mater.get(selecionado), lingua, url, r);
                                } else {
                                    at = new ActionButton(frameanterior, mater.get(selecionado), lingua, url, r);
                                }
                                frameanterior.setVisible(false);
                                at.open();
                                break;
                            case KeyEvent.VK_ESCAPE:
                                selecionado = -1;
                                bLista.stream().forEach((ro) -> {
                                    ro.setBorder(emptyBorder);
                                });
                                clearTable();
                                lnome.setText("");
                                lcodigo.setText("");
                                break;
                            case KeyEvent.VK_LEFT:
                                if (selecionado > 0) {
                                    selecionado = selecionado - 1;
                                    int va = 0;
                                    for (PersonalButtonRequest ro : bLista) {
                                        ro.setBorder(emptyBorder);
                                        if (va == selecionado) {
                                            ro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), emptyBorder2));
                                        }
                                        va++;
                                    }
                                }
                                break;
                            case KeyEvent.VK_RIGHT:
                                if ((selecionado < mater.size() - 1) && (selecionado >= 0)) {
                                    selecionado = selecionado + 1;
                                    int va = 0;
                                    for (PersonalButtonRequest ro : bLista) {
                                        ro.setBorder(emptyBorder);
                                        if (va == selecionado) {
                                            ro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), emptyBorder2));
                                        }
                                        va++;
                                    }
                                }
                                break;
                            case KeyEvent.VK_UP:
                                if (selecionado - botoeslinha >= 0) {
                                    selecionado = selecionado - botoeslinha;
                                    int va = 0;
                                    for (PersonalButtonRequest ro : bLista) {
                                        ro.setBorder(emptyBorder);
                                        if (va == selecionado) {
                                            ro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), emptyBorder2));
                                        }
                                        va++;
                                    }
                                }
                                break;
                            case KeyEvent.VK_DOWN:
                                if (selecionado + botoeslinha < mater.size()) {
                                    selecionado = selecionado + botoeslinha;
                                    int va = 0;
                                    for (PersonalButtonRequest ro : bLista) {
                                        ro.setBorder(emptyBorder);
                                        if (va == selecionado) {
                                            ro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), emptyBorder2));
                                        }
                                        va++;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                        if ((lnome != null) && (lcodigo != null)) {
                            if (selecionado < 0) {
                                lnome.setText("");
                                lcodigo.setText("");
                            } else {
                                Keys.Material sel = getSelectedMaterial();
                                if (sel.getMaterialTypeID() == 1) {
                                    lnome.setText(lingua.translate(sel.getTypeOfMaterialName()) + " " + sel.getDescription());
                                } else {
                                    lnome.setText(sel.getDescription());
                                }
                                lcodigo.setText(sel.getCodeOfMaterial());
                            }
                            if (lista) {
                                makeList();
                            }
                        }
                    }
                });
            }
        }
        return bLista;
    }

    public void addList(javax.swing.JList<String> jListRequisicoes, javax.swing.JScrollPane jScrollPane2, int ndias) {
        this.jListRequisicoes = jListRequisicoes;
        DefaultListModel<String> modelo = new DefaultListModel<>();
        modelo.addElement("<html><div style='border-style: none; padding-top:4px;text-align:center;width:185px;height:30px; color:#000;'>" + lingua.translate("Selecione um recurso") + ".</div></html>");
        jListRequisicoes.setModel(modelo);
        this.jScrollPane2 = jScrollPane2;
        this.ndias = ndias;
        lista = true;
    }

    private void makeList() {
        this.jListRequisicoes.removeAll();
        this.jListRequisicoes.removeMouseListener(lmou);
        this.jListRequisicoes.removeKeyListener(lkey);
        if (selecionado >= 0) {
            if (DataBase.DataBase.testConnection(url)) {
                DataBase.DataBase db = new DataBase.DataBase(url);
                TimeDate.Date dat = new TimeDate.Date();
                rlista = Clavis.RequestList.simplifyRequests(db.getRequestsByMaterialByDateInterval(getSelectedMaterial(), dat, dat.dateAfter(ndias)));
                db.close();
                DefaultListModel<String> modelo = new DefaultListModel<>();
                String andancas;
                String cor;
                int i = 0;
                if (rlista.size() > 0) {
                    for (Keys.Request r : rlista) {
                        if (i % 2 == 0) {
                            cor = "#cccccc";
                        } else {
                            cor = "#999999";
                        }
                        andancas = "<html><div style='border-style: solid;border-width:1px;padding-top:4px;text-align:center;width:185px;height:30px; background-color:" + cor + ";'> <b>" + lingua.translate("Início") + ": </b>" + r.getBeginDate().toString() + " (" + r.getTimeBegin().toString(0) + ")<br/>"
                                + "<b>" + lingua.translate("Fim") + ": </b>" + r.getEndDate().toString() + " (" + r.getTimeEnd().toString(0) + ")"
                                + "</div></html>";
                        modelo.addElement(andancas);
                        i++;
                    }
                } else {
                    modelo.addElement("<html><div style='border-style: none;padding-top:4px;text-align:center;width:185px;height:30px; color:#000;'>" + lingua.translate("A lista está vazia") + ".</div></html>");
                    jListRequisicoes.setModel(modelo);
                }
                jListRequisicoes.setModel(modelo);
            } else {
                DefaultListModel<String> modelo = new DefaultListModel<>();
                modelo.addElement(lingua.translate("Erro de ligação") + ".");
                jListRequisicoes.setModel(modelo);
            }
            jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            DefaultListModel<String> m = (DefaultListModel<String>) jListRequisicoes.getModel();
            Rectangle r;
            int altura = 20;
            jListRequisicoes.setPreferredSize(new Dimension((int) jListRequisicoes.getPreferredSize().getWidth(), 0));
            for (int x = 0; x < m.getSize(); x++) {
                r = jListRequisicoes.getCellBounds(x, x);
                altura += (int) (r.getHeight());
                jListRequisicoes.setPreferredSize(new Dimension((int) jListRequisicoes.getPreferredSize().getWidth(), altura));
            }
            lmou = (new MouseAdapter() {
                String[] st = {lingua.translate("Ver detalhes")};
                Components.MessagePane mensagem;
                Components.PopUpMenu pop;

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (rlista.size() > 0) {
                        ActionListener[] act = new ActionListener[]{
                            new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Keys.Request req = null;
                                    for (int h = 0; h < rlista.size(); h++) {
                                        if (jListRequisicoes.getSelectedIndex() == h) {
                                            req = rlista.get(h);
                                        }
                                    }
                                    if (req != null) {
                                        mensagem = new Components.MessagePane(frameanterior, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Detalhes"), 500, 400, ButtonListRequest.makePanelDetailsRequest(req, lingua), "", new String[]{lingua.translate("Voltar")});
                                        mensagem.showMessage();
                                    }
                                }
                            }
                        };
                        pop = new Components.PopUpMenu(st, act);
                        pop.create();
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (rlista.size() > 0) {
                                pop.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }
                        if (e.getClickCount() == 2) {
                            Keys.Request req = null;
                            for (int h = 0; h < rlista.size(); h++) {
                                if (jListRequisicoes.getSelectedIndex() == h) {
                                    req = rlista.get(h);
                                }
                            }
                            if (req != null) {
                                mensagem = new Components.MessagePane(frameanterior, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Detalhes"), 500, 400, ButtonListRequest.makePanelDetailsRequest(req, lingua), "", new String[]{lingua.translate("Voltar")});
                                mensagem.showMessage();
                            }
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        java.awt.Point p = e.getPoint();
                        jListRequisicoes.setSelectedIndex(jListRequisicoes.locationToIndex(p));
                    }
                }
            });
            jListRequisicoes.addMouseListener(lmou);
            lkey = (new KeyAdapter() {
                Components.MessagePane mensagem;

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        jListRequisicoes.clearSelection();
                    } else if ((jListRequisicoes.getSelectedIndex() >= 0) && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                        if (rlista.size() > 0) {
                            Keys.Request req = null;
                            for (int h = 0; h < rlista.size(); h++) {
                                if (jListRequisicoes.getSelectedIndex() == h) {
                                    req = rlista.get(h);
                                }
                            }
                            if (req != null) {
                                mensagem = new Components.MessagePane(frameanterior, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Detalhes"), 500, 400, ButtonListRequest.makePanelDetailsRequest(req, lingua), "", new String[]{lingua.translate("Voltar")});
                                mensagem.showMessage();
                            }
                        }
                    }
                }
            });
            jListRequisicoes.addKeyListener(lkey);
        } else {
            clearTable();
        }
    }

    private void clearTable() {
       
        this.jListRequisicoes.removeMouseListener(lmou);
        this.jListRequisicoes.removeKeyListener(lkey);
        DefaultListModel<String> modelo = new DefaultListModel<>();
        modelo.addElement("<html><div style='padding-top:4px;text-align:center;width:185px;height:30px; color:#000;'>" + lingua.translate("Selecione um recurso") + ".</div></html>");
        jListRequisicoes.setModel(modelo);
    }

    public void clear() {
        selecionado = -1;
        clearTable();
        lnome.setText("");
        lcodigo.setText("");
        this.jListRequisicoes.removeMouseListener(lmou);
        this.jListRequisicoes.removeKeyListener(lkey);
        if (bLista != null) {
            for (int i = 0; i < bLista.size(); i++) {
                bLista.get(i).setBorder(emptyBorder);
            }
        }

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
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
        javax.swing.JScrollPane aux = new javax.swing.JScrollPane();
        aux.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        aux.getVerticalScrollBar().setUnitIncrement(10);
        this.bLista = this.getButtons();
        pane.setBackground(new Color(245, 245, 220));
        int valorborder = 50;
        pane.setLayout(new Components.ModifiedFlowLayout());
        pane.setLayout(fl);
        pane.setBorder(new EmptyBorder(20, valorborder, 20, valorborder));
        if (!this.bLista.isEmpty()) {
            bLista.stream().forEach((bt) -> {
                pane.add(bt, BorderLayout.PAGE_END);
            });
            int largura = (int) (pane.getPreferredSize().getWidth() - (valorborder * 2));
            int valorbotoes = largura / (int) (dim.getWidth());
            botoeslinha = valorbotoes;
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

    public static javax.swing.JPanel makePanelDetailsRequest(Keys.Request req, Langs.Locale lingua) {
        javax.swing.JPanel panel = new javax.swing.JPanel(null);
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBounds(10, 20, 400, 300);
        Font fonte = new java.awt.Font("Cantarell", Font.BOLD, 14);
        Font fonte2 = new java.awt.Font("Cantarell", Font.PLAIN, 14);
        javax.swing.JLabel label1 = new javax.swing.JLabel();
        label1.setFont(fonte);
        label1.setBounds(10, 10, 140, 30);
        label1.setText(lingua.translate("Utilizador") + ":");
        panel.add(label1);
        javax.swing.JLabel labelr1 = new javax.swing.JLabel();
        labelr1.setFont(fonte2);
        labelr1.setBounds(160, 10, 270, 30);
        String g = Clavis.Windows.WRequest.treatLongStrings(req.getPerson().getName(), 120, labelr1.getFont());
        labelr1.setText(g);
        panel.add(labelr1);
        javax.swing.JLabel label2 = new javax.swing.JLabel();
        label2.setFont(fonte);
        label2.setBounds(10, 40, 140, 30);
        label2.setText(lingua.translate("Atividade") + ":");
        panel.add(label2);
        javax.swing.JLabel labelr2 = new javax.swing.JLabel();
        labelr2.setFont(fonte2);
        labelr2.setBounds(160, 40, 270, 30);
        String[] satividade = req.getActivity().split(":::");
        if (satividade.length > 1) {
            Components.PopUpMenu pop = new Components.PopUpMenu(satividade, lingua);
            pop.create();
            labelr2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pop.show(labelr2, labelr2.getX(), labelr2.getY());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    pop.setVisible(false);
                }
            });
        }
        if (!satividade[0].equals("")) {
            labelr2.setText(satividade[0]);
        } else {
            labelr2.setText(lingua.translate("Não existe informação") + ".");
        }
        panel.add(labelr2);
        javax.swing.JLabel label3 = new javax.swing.JLabel();
        label3.setFont(fonte);
        label3.setBounds(10, 70, 140, 30);
        label3.setText(lingua.translate("Disciplina") + ":");
        panel.add(label3);
        javax.swing.JLabel labelr3 = new javax.swing.JLabel();
        labelr3.setFont(fonte2);
        labelr3.setBounds(160, 70, 270, 30);
        if (req.isMultiDisciplinar()) {
            java.util.Iterator<Keys.Subject> iter = req.getSubjects().iterator();
            String[] sdisciplinas = new String[req.getSubjects().size()];
            int i = 0;
            Keys.Subject s;
            while (iter.hasNext()) {
                s = iter.next();
                sdisciplinas[i] = s.getName() + " (" + s.getCode() + ")";
                i++;
            }
            Components.PopUpMenu pop = new Components.PopUpMenu(sdisciplinas);
            pop.create();
            labelr3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pop.show(labelr2, labelr2.getX(), labelr2.getY());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    pop.setVisible(false);
                }
            });
            labelr3.setText(lingua.translate("Múltiplas disciplinas"));
        } else {
            labelr3.setText(req.getSubject().getName());
        }
        int altura = (int) (labelr3.getBounds().getY() + labelr3.getBounds().getHeight());
        javax.swing.JLabel label4 = new javax.swing.JLabel();
        panel.add(labelr3);
        label4.setFont(fonte);
        label4.setBounds(10, altura, 140, 30);
        label4.setText(lingua.translate("Turma") + ":");
        panel.add(label4);
        javax.swing.JLabel labelr4 = new javax.swing.JLabel();
        labelr4.setFont(fonte2);
        labelr4.setBounds(160, altura, 270, 30);
        if (req.isMultiClass()) {
            java.util.Iterator<Keys.ClassStudents> iter = req.getStudentsClasses().iterator();
            String[] sturmas = new String[req.getStudentsClasses().size()];
            int i = 0;
            while (iter.hasNext()) {
                sturmas[i] = iter.next().getName();
                i++;
            }
            Components.PopUpMenu pop = new Components.PopUpMenu(sturmas);
            pop.create();
            labelr4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pop.show(labelr2, labelr2.getX(), labelr2.getY());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    pop.setVisible(false);
                }
            });
            labelr4.setText(lingua.translate("Múltiplas turmas"));
        } else if (!req.getStudentsClass().getName().equals("")) {
            labelr4.setText(req.getStudentsClass().getName());
        } else {
            labelr4.setText(lingua.translate("Não existe informação") + ".");
        }
        altura = (int) (labelr4.getBounds().getY() + labelr4.getBounds().getHeight());
        panel.add(labelr4);
        javax.swing.JLabel label5 = new javax.swing.JLabel();
        label5.setFont(fonte);
        label5.setBounds(10, altura, 140, 30);
        if (req.getBeginDate().isBigger(req.getEndDate()) == 0) {
            label5.setText(lingua.translate("Data") + ":");
        } else {
            label5.setText(lingua.translate("Data inicial") + ":");
        }
        panel.add(label5);
        javax.swing.JLabel labelr5 = new javax.swing.JLabel();
        labelr5.setFont(fonte2);
        labelr5.setBounds(160, altura, 270, 30);
        labelr5.setText(req.getBeginDate().toStringWithMonthWord(lingua));
        altura = (int) (labelr5.getBounds().getY() + labelr5.getBounds().getHeight());
        panel.add(labelr5);
        if (req.getBeginDate().isBigger(req.getEndDate()) > 0) {
            javax.swing.JLabel label6 = new javax.swing.JLabel();
            label6.setFont(fonte);
            label6.setBounds(10, altura, 140, 30);
            label6.setText(lingua.translate("Data final") + ":");
            panel.add(label6);
            javax.swing.JLabel labelr6 = new javax.swing.JLabel();
            labelr6.setFont(fonte2);
            labelr6.setBounds(160, altura, 270, 30);
            labelr6.setText(req.getEndDate().toStringWithMonthWord(lingua));
            altura = (int) (labelr6.getBounds().getY() + labelr6.getBounds().getHeight());
            panel.add(labelr6);
        }
        javax.swing.JLabel label7 = new javax.swing.JLabel();
        label7.setFont(fonte);
        label7.setBounds(10, altura, 140, 30);
        label7.setText(lingua.translate("Hora inicial") + ":");
        panel.add(label7);
        javax.swing.JLabel labelr7 = new javax.swing.JLabel();
        labelr7.setFont(fonte2);
        labelr7.setBounds(160, altura, 270, 30);
        labelr7.setText(req.getTimeBegin().toString(0));
        altura = (int) (labelr7.getBounds().getY() + labelr7.getBounds().getHeight());
        panel.add(labelr7);
        javax.swing.JLabel label8 = new javax.swing.JLabel();
        label8.setFont(fonte);
        label8.setBounds(10, altura, 140, 30);
        label8.setText(lingua.translate("Hora final") + ":");
        panel.add(label8);
        javax.swing.JLabel labelr8 = new javax.swing.JLabel();
        labelr8.setFont(fonte2);
        labelr8.setBounds(160, altura, 270, 30);
        labelr8.setText(req.getTimeEnd().toString(0));
        altura = (int) (labelr8.getBounds().getY() + labelr8.getBounds().getHeight());
        panel.add(labelr8);
        if (req.getBeginDate().isBigger(req.getEndDate()) == 0) {
            javax.swing.JLabel label9 = new javax.swing.JLabel();
            label9.setFont(fonte);
            label9.setBounds(10, altura, 140, 30);
            label9.setText(lingua.translate("Dia da semana") + ":");
            panel.add(label9);
            javax.swing.JLabel labelr9 = new javax.swing.JLabel();
            labelr9.setFont(fonte2);
            labelr9.setBounds(160, altura, 270, 30);
            labelr9.setText(lingua.translate(req.getWeekDay().perDayName()));
            panel.add(labelr9);
        }
        return panel;
    }

}
