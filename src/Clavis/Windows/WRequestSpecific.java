/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import Clavis.ButtonListRequest;
import java.awt.Color;
import Keys.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author toze
 */
public class WRequestSpecific extends javax.swing.JDialog {

    private Color corborda;
    private Color corfundo;
    private Material material;
    private javax.swing.JDialog dialogo;
    private javax.swing.JFrame frame;
    private Langs.Locale lingua;
    private String url;
    private Components.PersonalCombo jComboBoxNomeUtilizador;
    private java.util.List<Keys.Person> pessoas;
    private Keys.Person pessoaescolhida;
    private TimeDate.Date dinicio;
    private TimeDate.Date dfim;
    private TimeDate.Time tinicio;
    private TimeDate.Time tfim;
    private int intervalo;
    private java.util.List<Keys.Request> rlist;
    private boolean reqefetuada;
    private java.util.List<Keys.ClassStudents> reqturmas;
    private java.util.List<Keys.Subject> reqdisciplinas;
    private String reqatividade;
    private KeyListener lkey;
    private MouseListener lmou;

    /**
     * Creates new form WRequestSpecified
     */
    public WRequestSpecific(Color corborda, Color corfundo, Material mat, String url, Langs.Locale lingua, javax.swing.JDialog dialogo) {
        this.corborda = corborda;
        this.corfundo = corfundo;
        this.material = mat;
        this.dialogo = dialogo;
        this.frame = null;
        this.lingua = lingua;
        this.url = url;
        jComboBoxNomeUtilizador = new Components.PersonalCombo(rootPane);
        pessoas = new java.util.ArrayList<>();
        pessoaescolhida = null;
        dinicio = null;
        dfim = null;
        tinicio = null;
        tfim = null;
        intervalo = 1;
        reqefetuada = false;
        rlist = new java.util.ArrayList<>();
        reqturmas = new java.util.ArrayList<>();
        reqdisciplinas = new java.util.ArrayList<>();
        reqatividade = "";
    }

    public WRequestSpecific(Color corborda, Color corfundo, Material mat, String url, Langs.Locale lingua, javax.swing.JFrame frame) {
        this.corborda = corborda;
        this.corfundo = corfundo;
        this.material = mat;
        this.frame = frame;
        this.dialogo = null;
        this.lingua = lingua;
        this.url = url;
        jComboBoxNomeUtilizador = new Components.PersonalCombo(rootPane);
        pessoas = new java.util.ArrayList<>();
        pessoaescolhida = null;
        dinicio = null;
        dfim = null;
        tinicio = null;
        tfim = null;
        intervalo = 1;
        reqefetuada = false;
        rlist = new java.util.ArrayList<>();
        reqturmas = new java.util.ArrayList<>();
        reqdisciplinas = new java.util.ArrayList<>();
        reqatividade = "";
    }

    public void create() {
        initComponents();
        makeComboBoxUser();
        makeTextFieldEmail();
        makeTextFieldCode();
        makeValidDate();
        this.setTitle(lingua.translate("Requisição específica"));
        this.setModal(true);
        dinicio = this.getDate(jSpinnerDataLevantamento);
        dfim = this.getDate(jSpinnerDataEntrega);
        tinicio = this.getTime(jSpinnerHoraLevantamento);
        tfim = this.getTime(jSpinnerHoraEntrega);
        makeRequestsList();
        makeModelTimeComboBox();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                close();
            }
        });
    }

    public void close() {
        reqefetuada = false;
        this.setVisible(false);
        if (dialogo != null) {
            java.awt.Point p = this.getLocation();
            dialogo.setLocation(p);
            dialogo.setVisible(true);
        } else if (frame != null) {
            java.awt.Point p = this.getLocation();
            frame.setLocation(p);
            frame.setVisible(true);
        }
        this.dispose();
    }

    public int isIntervalDateValid() {
        dinicio = this.getDate(jSpinnerDataLevantamento);
        dfim = this.getDate(jSpinnerDataEntrega);
        tinicio = this.getTime(jSpinnerHoraLevantamento);
        tfim = this.getTime(jSpinnerHoraEntrega);
        TimeDate.Date dat = new TimeDate.Date();
        if ((dinicio == null) || (dfim == null) || (tinicio == null) || (tfim == null)) {
            return -19;
        } else if (dinicio.isBigger(dat) > 0) {
            return -18;
        } else if (dinicio.isBigger(dfim) < 0) {
            return -17;
        } else if (this.checkHolidays()) {
            return -15;
        } else if (dinicio.isBigger(dfim) == 0) {
            if (tinicio.compareTime(tfim) < 0) {
                return -16;
            }
        }
        int val = dinicio.isBigger(dfim);
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            java.util.Set<Keys.Request> req = db.getRequestsByMaterialByDateInterval(material, dat, dat.dateAfter(dinicio.isBigger(dfim) + 1));
            for (Keys.Request r : req) {
                if (r.getDeliveryDate() == null) {
                    TimeDate.Date dat1 = r.getBeginDate();
                    TimeDate.Date dat2 = r.getEndDate();
                    TimeDate.Time tim1 = r.getTimeBegin();
                    TimeDate.Time tim2 = r.getTimeEnd();
                    if (dinicio.isBigger(dfim) != 0) {
                        if ((dat1.isBigger(dinicio) > 0) && (dat2.isBigger(dinicio) < 0)) {
                            return -14;
                        } else if ((dat1.isBigger(dfim) > 0) && (dat2.isBigger(dfim) < 0)) {
                            return -13;
                        } else if ((dat1.isBigger(dinicio) < 0) && (dat1.isBigger(dfim) > 0)) {
                            return -12;
                        } else if ((dat2.isBigger(dinicio) < 0) && (dat2.isBigger(dfim) > 0)) {
                            return -11;
                        } else if ((dat2.isBigger(dinicio) == 0) && (dat2.isBigger(dfim) == 0)) {
                            return -10;
                        } else if (dat2.isBigger(dinicio) == 0) {
                            if (tim2.compareTime(tinicio) < 0) {
                                return -9;
                            }
                        } else if (dat1.isBigger(dfim) == 0) {
                            if (tim1.compareTime(tfim) > 0) {
                                return -8;
                            }
                        }
                    } else if ((dinicio.isBigger(dat1) == 0) && (dat1.isBigger(dat2) != 0)) {
                        if (tim1.compareTime(tfim) > 0) {
                            return -7;
                        }
                    } else if ((dfim.isBigger(dat2) == 0) && (dat1.isBigger(dat2) != 0)) {
                        if (tim2.compareTime(tinicio) < 0) {
                            return -6;
                        }
                    } else if ((tim1.compareTime(tinicio) > 0) && (tim2.compareTime(tinicio) < 0)) {
                        return -5;
                    } else if ((tim1.compareTime(tfim) > 0) && (tim2.compareTime(tfim) < 0)) {
                        return -4;
                    } else if ((tim1.compareTime(tinicio) < 0) && (tim1.compareTime(tfim) > 0)) {
                        return -3;
                    } else if ((tim2.compareTime(tinicio) < 0) && (tim2.compareTime(tfim) > 0)) {
                        return -2;
                    } else if ((tim1.compareTime(tinicio) == 0) && (tim2.compareTime(tfim) == 0)) {
                        return -1;
                    }
                }
            }
        } else {
            return 0;
        }
        return 1;
    }

    private void makeModelTimeComboBox() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement(lingua.translate("1 dia"));
        model.addElement(lingua.translate("2 dias"));
        model.addElement(lingua.translate("5 dias"));
        model.addElement(lingua.translate("15 dias"));
        jComboBoxTempo.setModel(model);
        jComboBoxTempo.addActionListener((ActionEvent e) -> {
            switch (jComboBoxTempo.getSelectedIndex()) {
                case 0:
                    intervalo = 1;
                    break;
                case 1:
                    intervalo = 2;
                    break;
                case 2:
                    intervalo = 5;
                    break;
                case 3:
                    intervalo = 15;
                    break;
                default:
                    intervalo = 1;
                    break;
            }
            this.makeRequestsList();
        });

    }

    public void appear() {
        this.setModal(true);
        java.awt.Point p = new java.awt.Point();
        if (dialogo != null) {
            p = dialogo.getLocation();
            double x = p.getX() + dialogo.getWidth() / 2;
            x -= (this.getWidth() / 2);
            double y = p.getY() + dialogo.getHeight() / 2;;
            y -= (this.getHeight() / 2);
            p.setLocation(x, y);
        } else if (frame != null) {
            p = frame.getLocation();
            double x = p.getX() + frame.getWidth() / 2;
            x -= (this.getWidth() / 2);
            double y = p.getY() + frame.getHeight() / 2;;
            y -= (this.getHeight() / 2);
            p.setLocation(x, y);
        } else {
            java.awt.Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            double x = dim.getWidth() / 2;
            double y = dim.getHeight() / 2;
            x -= (this.getWidth() / 2);
            y -= (this.getHeight() / 2);
            p.setLocation(x, y);
        }
        this.setLocation(p);
        this.setVisible(true);
    }

    private void makeRequestsList() {
        jListHorario.removeMouseListener(lmou);
        jListHorario.removeKeyListener(lkey);
        if (jListHorario.getModel().getSize() > 0) {
            DefaultListModel m = (DefaultListModel) jListHorario.getModel();
            for (int i = 0; i < m.getSize(); i++) {
                m.remove(0);
            }
        }
        int reqid = -1;
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            TimeDate.Date dat = new TimeDate.Date();
            rlist = Clavis.RequestList.simplifyRequests(db.getRequestsByMaterialByDateInterval(material, dat, dat.dateAfter(intervalo)));
            if (reqefetuada) {
                reqid = db.getRequestID(pessoaescolhida.getId(), material.getId(), dinicio, dfim, tinicio, tfim);
            }
            db.close();
            DefaultListModel<String> modelo = new DefaultListModel<>();
            String andancas;
            String cor;
            int i = 0;
            for (Keys.Request r : rlist) {
                if (reqid == r.getId()) {
                    cor = "#ccffb3";
                } else if (i % 2 == 0) {
                    cor = "#efeed0";
                } else {
                    cor = "#d7d4a7";
                }
                andancas = "<html><div style='padding-top:4px;text-align:center;width:203px;height:30px; background-color:" + cor + ";'> <b>" + lingua.translate("Início") + ": </b>" + r.getBeginDate().toString() + " (" + r.getTimeBegin().toString(0) + ")<br/>"
                        + "<b>" + lingua.translate("Fim") + ": </b>" + r.getEndDate().toString() + " (" + r.getTimeEnd().toString(0) + ")"
                        + "</div></html>";
                modelo.addElement(andancas);
                i++;
            }
            jListHorario.setModel(modelo);
        } else {
            DefaultListModel<String> modelo = new DefaultListModel<>();
            modelo.addElement(lingua.translate("A lista está vazia neste período") + ".");
            jListHorario.setModel(modelo);
        }
        jScrollPaneHorario.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        DefaultListModel m = (DefaultListModel) jListHorario.getModel();
        Rectangle r;
        int altura = 10;
        jListHorario.setPreferredSize(new Dimension((int) jListHorario.getPreferredSize().getWidth(), 0));
        for (int x = 0; x < m.getSize(); x++) {
            r = jListHorario.getCellBounds(x, x);
            altura += (int) (r.getHeight());
            if (altura >= jListHorario.getPreferredSize().getHeight()) {
                jListHorario.setPreferredSize(new Dimension((int) jListHorario.getPreferredSize().getWidth(), (int) (altura)));
            }
        }
        lmou = (new MouseAdapter() {
            String[] st = {lingua.translate("Ver detalhes")};
            Components.MessagePane mensagem;
            Components.PopUpMenu pop;

            @Override
            public void mouseReleased(MouseEvent e) {
                if (rlist.size() > 0) {
                    ActionListener[] act = new ActionListener[]{
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Keys.Request req = null;
                                for (int h = 0; h < rlist.size(); h++) {
                                    if (jListHorario.getSelectedIndex() == h) {
                                        req = rlist.get(h);
                                    }
                                }
                                if (req != null) {
                                    mensagem = new Components.MessagePane(jListHorario.getRootPane(), Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Detalhes"), 500, 400, ButtonListRequest.makePanelDetailsRequest(req, lingua), "", new String[]{lingua.translate("Voltar")});
                                    mensagem.showMessage();
                                }
                            }
                        }
                    };
                    pop = new Components.PopUpMenu(st, act);
                    pop.create();
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        if (rlist.size() > 0) {
                            pop.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    java.awt.Point p = e.getPoint();
                    jListHorario.setSelectedIndex(jListHorario.locationToIndex(p));
                }
            }
        });
        jListHorario.addMouseListener(lmou);
        lkey = (new KeyAdapter() {
            Components.MessagePane mensagem;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jListHorario.clearSelection();
                } else if ((jListHorario.getSelectedIndex() >= 0) && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                    if (rlist.size() > 0) {
                        Keys.Request req = null;
                        for (int h = 0; h < rlist.size(); h++) {
                            if (jListHorario.getSelectedIndex() == h) {
                                req = rlist.get(h);
                            }
                        }
                        if (req != null) {
                            mensagem = new Components.MessagePane(jListHorario.getRootPane(), Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Detalhes"), 500, 400, ButtonListRequest.makePanelDetailsRequest(req, lingua), "", new String[]{lingua.translate("Voltar")});
                            mensagem.showMessage();
                        }
                    }
                }
            }
        });
        jListHorario.addKeyListener(lkey);

    }

    private void makeComboBoxUser() {
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            java.util.List<Keys.Person> p = db.getPersons();
            pessoas = p;
            int var;
            for (int i = 0; i < p.size() - 1; i++) {
                var = 2;
                for (int j = i + 1; j < p.size(); j++) {
                    p.get(j).setName(WRequest.treatLongStrings(p.get(j).getName(), 80, jComboBoxNomeUtilizador.getComboBox().getEditor().getEditorComponent().getFont()));
                    if (p.get(i).getName().equals(p.get(j).getName())) {
                        p.get(j).setName(p.get(j).getName() + " (" + var + ")");
                        var++;
                    }
                }
            }
            DefaultComboBoxModel<Keys.Person> modelop = (DefaultComboBoxModel) jComboBoxNomeUtilizador.getModel();
            p.stream().forEach((pes) -> {
                modelop.addElement(pes);
            });
        }

        jComboBoxNomeUtilizador.getComboBox().addItemListener((ItemEvent e) -> {
            if (jComboBoxNomeUtilizador.getSelectedIndex() > 0) {
                Keys.Person p = pessoas.get(jComboBoxNomeUtilizador.getSelectedIndex() - 1);
                if ((p.getEmail().equals("")) || (p.getEmail().equals("sem"))) {
                    personalTextFieldEmailUtilizador.showWithCondition(lingua.translate("Não existe registo de email") + "!", jComboBoxNomeUtilizador.getSelectedIndex() > 0);
                } else {
                    personalTextFieldEmailUtilizador.setText(p.getEmail());
                }
                if ((p.getIdentification().equals("")) || (p.getIdentification().equals("sem"))) {
                    personalTextFieldCodigoUtilizador.showWithCondition(lingua.translate("Não existe Identificação") + "!", jComboBoxNomeUtilizador.getSelectedIndex() > 0);
                } else {
                    personalTextFieldCodigoUtilizador.setText(p.getIdentification());
                }
                pessoaescolhida = p;
            } else if ((jComboBoxNomeUtilizador.getSelectedIndex() == 0) && (!e.getItem().toString().equals(""))) {
                personalTextFieldEmailUtilizador.restartPlaceHolder();
                personalTextFieldEmailUtilizador.showPLaceHolder();
                personalTextFieldCodigoUtilizador.restartPlaceHolder();
                personalTextFieldCodigoUtilizador.showPLaceHolder();
                pessoaescolhida = null;
            }
            changeButtonState();
        });

        jComboBoxNomeUtilizador.setSelectedIndex(0);
    }

    private void changeButtonState() {
        if ((pessoaescolhida != null) && (isIntervalDateValid() == 1)) {
            jButtonConfirma.setEnabled(true);
            jButtonAlgoMais.setEnabled(true);
            TimeDate.Time tempoatual = new TimeDate.Time();
            if (this.isMaterialInLateState()) {
                jButtonConfirma.setBackground(new Components.Color(255, 104, 100));
            } else {
                jButtonConfirma.setBackground(new Components.Color(51, 102, 203));
            }
        } else {
            jButtonConfirma.setBackground(new Components.Color(51, 102, 203));
            jButtonAlgoMais.setEnabled(false);
            jButtonConfirma.setEnabled(false);
        }
    }

    private boolean isMaterialInLateState() {
        if (material.isLoaned()) {
            if (DataBase.DataBase.testConnection(url)) {
                DataBase.DataBase db = new DataBase.DataBase(url);
                Keys.Request re = db.getCurrentRequest(material);
                db.close();
                TimeDate.Date datfim = re.getEndDate();
                TimeDate.Time tempofim = re.getTimeEnd();
                TimeDate.Date datatual = new TimeDate.Date();
                TimeDate.Time tempoatual = new TimeDate.Time();
                if (datfim.isBigger(datatual) > 0) {
                    return true;
                } else if ((datfim.isBigger(datatual) == 0) && (tempofim.compareTime(tempoatual) > 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void makeTextFieldEmail() {
        personalTextFieldEmailUtilizador.addActionListener((ActionEvent e) -> {
            boolean encontrou = false;
            for (Keys.Person pp : pessoas) {
                if ((!pp.getEmail().equals("")) && (!pp.getEmail().equals("sem"))) {
                    if (pp.getEmail().equals(personalTextFieldEmailUtilizador.getText())) {
                        jComboBoxNomeUtilizador.setSelectedItem(pp);
                        if ((pp.getIdentification().equals("")) || (pp.getIdentification().equals("sem"))) {
                            personalTextFieldCodigoUtilizador.showWithCondition(lingua.translate("Não existe Identificação") + "!", jComboBoxNomeUtilizador.getSelectedIndex() > 0);
                        } else {
                            personalTextFieldCodigoUtilizador.setText(pp.getIdentification());
                        }
                        encontrou = true;
                    }
                }
            }
            if (!encontrou) {
                if (personalTextFieldEmailUtilizador.getText().equals("")) {
                    this.requestFocusInWindow();
                }
                pessoaescolhida = null;
                jComboBoxNomeUtilizador.setSelectedIndex(0);
                jComboBoxNomeUtilizador.setSelectedIndex(0);
                personalTextFieldCodigoUtilizador.restartPlaceHolder();
                personalTextFieldCodigoUtilizador.showPLaceHolder();

            }
        });
        personalTextFieldEmailUtilizador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (personalTextFieldCodigoUtilizador.getText().equals("")) {
                        personalTextFieldEmailUtilizador.restartPlaceHolder();
                        personalTextFieldEmailUtilizador.showPLaceHolder();
                        personalTextFieldCodigoUtilizador.restartPlaceHolder();
                        personalTextFieldCodigoUtilizador.showPLaceHolder();
                        jComboBoxNomeUtilizador.setSelectedIndex(0);
                    }
                }
            }
        });
    }

    private void makeTextFieldCode() {
        personalTextFieldCodigoUtilizador.addActionListener((ActionEvent e) -> {
            boolean encontrou = false;
            for (Keys.Person pp : pessoas) {
                if (pp.getIdentification().equals(personalTextFieldCodigoUtilizador.getText())) {
                    jComboBoxNomeUtilizador.setSelectedItem(pp);
                    personalTextFieldEmailUtilizador.stopPlaceHolder();
                    pessoaescolhida = pp;
                    if ((pp.getEmail().equals("")) || (pp.getEmail().equals("sem"))) {
                        personalTextFieldEmailUtilizador.showWithCondition(lingua.translate("Não existe registo de email") + "!", jComboBoxNomeUtilizador.getSelectedIndex() > 0);
                    } else {
                        personalTextFieldEmailUtilizador.setText(pp.getEmail());
                    }
                    encontrou = true;
                }
            }
            if (!encontrou) {
                if (personalTextFieldCodigoUtilizador.getText().equals("")) {
                    this.requestFocusInWindow();
                }
                pessoaescolhida = null;
                jComboBoxNomeUtilizador.setSelectedIndex(0);
                jComboBoxNomeUtilizador.setSelectedIndex(0);
                personalTextFieldEmailUtilizador.restartPlaceHolder();
                personalTextFieldEmailUtilizador.showPLaceHolder();
            }
        });
        personalTextFieldCodigoUtilizador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (personalTextFieldCodigoUtilizador.getText().equals("")) {
                        personalTextFieldEmailUtilizador.restartPlaceHolder();
                        personalTextFieldEmailUtilizador.showPLaceHolder();
                        personalTextFieldCodigoUtilizador.restartPlaceHolder();
                        personalTextFieldCodigoUtilizador.showPLaceHolder();
                        jComboBoxNomeUtilizador.setSelectedIndex(0);
                    }
                }
            }

        });
    }

    public void updatePersons(java.util.List<Keys.Person> lista, boolean editar) {
        pessoas = lista;
        int pos = jComboBoxNomeUtilizador.getSelectedIndex();
        jComboBoxNomeUtilizador.removeAllItems();
        lista.stream().forEach((p) -> {
            jComboBoxNomeUtilizador.addItem(p);
        });
        if (editar) {
            jComboBoxNomeUtilizador.setSelectedIndex(pos);
            jComboBoxNomeUtilizador.setSelectedIndex(pos);
            if (pos > 0) {
                Keys.Person pessoa = pessoas.get(pos - 1);
                if ((pessoa.getEmail().equals("sem")) || (pessoa.getEmail().equals(""))) {
                    personalTextFieldEmailUtilizador.setText("");
                    personalTextFieldEmailUtilizador.showPLaceHolder();
                } else {
                    personalTextFieldEmailUtilizador.setText(pessoa.getEmail());
                }
                if ((pessoa.getIdentification().equals("sem")) || (pessoa.getIdentification().equals(""))) {
                    personalTextFieldCodigoUtilizador.setText("");
                    personalTextFieldCodigoUtilizador.showPLaceHolder();
                } else {
                    personalTextFieldCodigoUtilizador.setText(pessoa.getIdentification());
                }
            }
        } else {
            jComboBoxNomeUtilizador.setSelectedIndex(0);
            jComboBoxNomeUtilizador.setSelectedIndex(0);
            personalTextFieldEmailUtilizador.setText("");
            personalTextFieldEmailUtilizador.showPLaceHolder();
            personalTextFieldCodigoUtilizador.setText("");
            personalTextFieldCodigoUtilizador.showPLaceHolder();
        }
    }

    private void makeValidDate() {
        jSpinnerHoraLevantamento.addChangeListener((ChangeEvent e) -> {
            TimeDate.Date date1 = this.getDate(jSpinnerDataLevantamento);
            TimeDate.Date date2 = this.getDate(jSpinnerDataEntrega);
            TimeDate.Date datahoje = new TimeDate.Date();
            if (date1.isBigger(date2) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = this.getTime(jSpinnerHoraEntrega);
                if (date1.isBigger(datahoje) == 0) {
                    TimeDate.Time timatual = new TimeDate.Time();
                    if (timatual.compareTime(tim) < 0) {
                        jSpinnerHoraLevantamento.setValue(new Date());
                    }
                }
                if (tim.compareTime(tim2) < 0) {
                    jSpinnerHoraEntrega.setValue(jSpinnerHoraLevantamento.getValue());
                }
            } else if (datahoje.isBigger(date1) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time timatual = new TimeDate.Time();
                if (timatual.compareTime(tim) < 0) {
                    jSpinnerHoraLevantamento.setValue(new Date());
                }
            }
            changeButtonState();
        });
        jSpinnerHoraEntrega.addChangeListener((ChangeEvent e) -> {
            TimeDate.Date date1 = this.getDate(jSpinnerDataLevantamento);
            TimeDate.Date date2 = this.getDate(jSpinnerDataEntrega);
            TimeDate.Date datahoje = new TimeDate.Date();
            if (date1.isBigger(date2) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = this.getTime(jSpinnerHoraEntrega);
                if (date1.isBigger(datahoje) == 0) {
                    TimeDate.Time timatual = new TimeDate.Time();
                    if (timatual.compareTime(tim2) < 0) {
                        jSpinnerHoraEntrega.setValue(new Date());
                    }
                }
                if (tim2.compareTime(tim) > 0) {
                    jSpinnerHoraLevantamento.setValue(jSpinnerHoraEntrega.getValue());
                }
            } else if (datahoje.isBigger(date1) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time timatual = new TimeDate.Time();
                if (timatual.compareTime(tim) < 0) {
                    jSpinnerHoraLevantamento.setValue(new Date());
                }
            }
            changeButtonState();
        });
        jSpinnerDataLevantamento.addChangeListener((ChangeEvent e) -> {
            TimeDate.Date date1 = this.getDate(jSpinnerDataLevantamento);
            TimeDate.Date date2 = this.getDate(jSpinnerDataEntrega);
            TimeDate.Date datahoje = new TimeDate.Date();
            if (date1.isBigger(datahoje) > 0) {
                jSpinnerDataLevantamento.setValue(new Date());
            } else if (date1.isBigger(date2) < 0) {
                jSpinnerDataEntrega.setValue(jSpinnerDataLevantamento.getValue());
            } else if (date1.isBigger(date2) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = this.getTime(jSpinnerHoraEntrega);
                if (date1.isBigger(datahoje) == 0) {
                    TimeDate.Time timatual = new TimeDate.Time();
                    if (timatual.compareTime(tim) < 0) {
                        jSpinnerHoraLevantamento.setValue(new Date());
                    }
                }
                if (tim.compareTime(tim2) < 0) {
                    jSpinnerHoraEntrega.setValue(jSpinnerHoraLevantamento.getValue());
                }
            } else if (datahoje.isBigger(date1) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time timatual = new TimeDate.Time();
                if (timatual.compareTime(tim) < 0) {
                    jSpinnerHoraLevantamento.setValue(new Date());
                    jSpinnerHoraLevantamento.repaint();
                }
            }
            changeButtonState();
        });
        jSpinnerDataEntrega.addChangeListener((ChangeEvent e) -> {
            TimeDate.Date date1 = this.getDate(jSpinnerDataLevantamento);
            TimeDate.Date date2 = this.getDate(jSpinnerDataEntrega);
            TimeDate.Date datahoje = new TimeDate.Date();
            if (date2.isBigger(date1) > 0) {
                jSpinnerDataLevantamento.setValue((Date) jSpinnerDataEntrega.getValue());
            } else if (date1.isBigger(date2) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = this.getTime(jSpinnerHoraEntrega);
                if (date1.isBigger(datahoje) == 0) {
                    TimeDate.Time timatual = new TimeDate.Time();
                    if (timatual.compareTime(tim) < 0) {
                        jSpinnerHoraLevantamento.setValue(new Date());
                        jSpinnerHoraLevantamento.repaint();
                    }
                }
                if (tim.compareTime(tim2) < 0) {
                    jSpinnerHoraEntrega.setValue(jSpinnerHoraLevantamento.getValue());
                    jSpinnerHoraEntrega.repaint();
                }
            } else if (datahoje.isBigger(date1) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time timatual = new TimeDate.Time();
                if (timatual.compareTime(tim) < 0) {
                    jSpinnerHoraLevantamento.setValue(new Date());
                    jSpinnerHoraLevantamento.repaint();
                }
            }
            changeButtonState();
        });

    }

    private TimeDate.Time getTime(javax.swing.JSpinner spin) {
        java.util.Date tempo = (java.util.Date) spin.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tempo);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);
        int segundos = cal.get(Calendar.SECOND);
        return new TimeDate.Time(horas, minutos, segundos);
    }

    private TimeDate.Date getDate(javax.swing.JSpinner spin) {
        java.util.Date tempo = (java.util.Date) spin.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tempo);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        return new TimeDate.Date(dia, mes, ano);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInicial = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSpinnerHoraLevantamento = new javax.swing.JSpinner();
        jSpinnerDataLevantamento = new javax.swing.JSpinner();
        jXLabelLevantamento = new org.jdesktop.swingx.JXLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSpinnerHoraEntrega = new javax.swing.JSpinner();
        jSpinnerDataEntrega = new javax.swing.JSpinner();
        jLabelEntrega = new org.jdesktop.swingx.JXLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPaneHorario = new javax.swing.JScrollPane();
        jListHorario = new org.jdesktop.swingx.JXList();
        jLabelLista = new org.jdesktop.swingx.JXLabel();
        jButtonConfirma = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jComboBoxUtilizador = jComboBoxNomeUtilizador.getComboBox();
        personalTextFieldEmailUtilizador = new Components.PersonalTextField();
        personalTextFieldCodigoUtilizador = new Components.PersonalTextField();
        jLabelUtilizador = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jLabelRecurso = new javax.swing.JLabel();
        jButtonExit = new javax.swing.JButton();
        jComboBoxTempo = new javax.swing.JComboBox<>();
        jButtonAlgoMais = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setResizable(false);

        jPanelInicial.setBackground(new java.awt.Color(254, 254, 254));
        jPanelInicial.setMaximumSize(new java.awt.Dimension(700, 500));
        jPanelInicial.setMinimumSize(new java.awt.Dimension(700, 500));
        jPanelInicial.setPreferredSize(new java.awt.Dimension(680, 480));

        jPanel1.setMaximumSize(new java.awt.Dimension(269, 389));
        jPanel1.setMinimumSize(new java.awt.Dimension(269, 389));

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setBorder(null);

        jSpinnerHoraLevantamento.setMaximumSize(new java.awt.Dimension(148, 30));
        jSpinnerHoraLevantamento.setMinimumSize(new java.awt.Dimension(148, 30));
        jSpinnerHoraLevantamento.setPreferredSize(new java.awt.Dimension(148, 30));

        jSpinnerDataLevantamento.setPreferredSize(new java.awt.Dimension(148, 30));

        jXLabelLevantamento.setBackground(new java.awt.Color(238, 240, 238));
        jXLabelLevantamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jXLabelLevantamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelLevantamento.setMaximumSize(new java.awt.Dimension(269, 30));
        jXLabelLevantamento.setMinimumSize(new java.awt.Dimension(269, 30));
        jXLabelLevantamento.setOpaque(true);
        jXLabelLevantamento.setPreferredSize(new java.awt.Dimension(269, 30));

        jLabel1.setText("Data:");

        jLabel2.setText("Hora:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jXLabelLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerDataLevantamento, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jSpinnerHoraLevantamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabelLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerDataLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerHoraLevantamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) jSpinnerHoraLevantamento.getEditor();
        spinnerEditor.getTextField().setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSpinnerHoraLevantamento.setModel(new SpinnerDateModel());
        jSpinnerHoraLevantamento.setEditor(new JSpinner.DateEditor(jSpinnerHoraLevantamento, "HH:mm"));
        javax.swing.JFormattedTextField ff = (javax.swing.JFormattedTextField)((javax.swing.JSpinner.DateEditor)jSpinnerHoraLevantamento.getEditor()).getComponent(0);
        ff.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSpinnerDataLevantamento.setModel(new SpinnerDateModel());
        jSpinnerDataLevantamento.setEditor(new JSpinner.DateEditor(jSpinnerDataLevantamento, "dd/MM/yyyy"));
        javax.swing.JFormattedTextField ff2 = (javax.swing.JFormattedTextField)((javax.swing.JSpinner.DateEditor)jSpinnerDataLevantamento.getEditor()).getComponent(0);
        ff2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jXLabelLevantamento.setText(lingua.translate("Início da requisição"));

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setCornerSize(6);
        dropShadowBorder1.setShadowSize(3);
        dropShadowBorder1.setShowLeftShadow(true);
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder1, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jSpinnerHoraEntrega.setMaximumSize(new java.awt.Dimension(148, 30));
        jSpinnerHoraEntrega.setMinimumSize(new java.awt.Dimension(148, 30));
        jSpinnerHoraEntrega.setPreferredSize(new java.awt.Dimension(148, 30));

        jSpinnerDataEntrega.setPreferredSize(new java.awt.Dimension(148, 30));

        jLabelEntrega.setBackground(new java.awt.Color(240, 238, 238));
        jLabelEntrega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelEntrega.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEntrega.setMaximumSize(new java.awt.Dimension(260, 30));
        jLabelEntrega.setMinimumSize(new java.awt.Dimension(269, 30));
        jLabelEntrega.setOpaque(true);
        jLabelEntrega.setPreferredSize(new java.awt.Dimension(269, 30));

        jLabel3.setText("Data:");

        jLabel4.setText("Hora:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerDataEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinnerHoraEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinnerHoraEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSpinnerHoraEntrega.setModel(new SpinnerDateModel());
        jSpinnerHoraEntrega.setEditor(new JSpinner.DateEditor(jSpinnerHoraEntrega, "HH:mm"));
        javax.swing.JFormattedTextField ffjSpinnerHoraEntrega = (javax.swing.JFormattedTextField)((javax.swing.JSpinner.DateEditor)jSpinnerHoraEntrega.getEditor()).getComponent(0);
        ffjSpinnerHoraEntrega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSpinnerDataEntrega.setModel(new SpinnerDateModel());
        jSpinnerDataEntrega.setEditor(new JSpinner.DateEditor(jSpinnerDataEntrega, "dd/MM/yyyy"));
        javax.swing.JFormattedTextField ffSpinner4 = (javax.swing.JFormattedTextField)((javax.swing.JSpinner.DateEditor)jSpinnerDataEntrega.getEditor()).getComponent(0);
        ffSpinner4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabelEntrega.setText(lingua.translate("Início da requisição"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadow = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadow.setCornerSize(6);
        dropShadow.setShadowSize(3);
        dropShadow.setShowLeftShadow(true);
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadow, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jListHorario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListHorario.setPreferredSize(new java.awt.Dimension(282, 380));
        jScrollPaneHorario.setViewportView(jListHorario);
        jListHorario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jListHorario.setCellRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 2L;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = (javax.swing.JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(150, 45));
                if (isSelected) {
                    label.setBackground(jListHorario.getBackground());
                    label.setForeground(Color.WHITE);
                    label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0), BorderFactory.createLineBorder(Color.black, 1)));
                }
                return label;
            }
        });
        jListHorario.setPreferredSize(new Dimension(220, 130));
        jListHorario.setBackground(new Color(250, 250, 250));

        jScrollPaneHorario.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, true, true, true), BorderFactory.createLineBorder(Color.BLACK)));
        jScrollPaneHorario.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jLabelLista.setBackground(new java.awt.Color(250, 250, 250));
        jLabelLista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelLista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLista.setMaximumSize(new java.awt.Dimension(280, 30));
        jLabelLista.setMinimumSize(new java.awt.Dimension(280, 30));
        jLabelLista.setOpaque(true);
        jLabelLista.setPreferredSize(new java.awt.Dimension(280, 30));

        jButtonConfirma.setBackground(new java.awt.Color(51, 102, 203));
        jButtonConfirma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonConfirma.setEnabled(false);
        jButtonConfirma.setFocusPainted(false);
        jButtonConfirma.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonConfirma.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonConfirma.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmaActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(249, 248, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setCornerSize(6);
        dropShadowBorder2.setShadowSize(3);
        dropShadowBorder2.setShowLeftShadow(true);
        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder2, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        personalTextFieldEmailUtilizador.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        personalTextFieldEmailUtilizador.setMaximumSize(new java.awt.Dimension(225, 30));
        personalTextFieldEmailUtilizador.setMinimumSize(new java.awt.Dimension(225, 30));
        personalTextFieldEmailUtilizador.setPreferredSize(new java.awt.Dimension(225, 30));

        personalTextFieldCodigoUtilizador.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        personalTextFieldCodigoUtilizador.setMaximumSize(new java.awt.Dimension(225, 30));
        personalTextFieldCodigoUtilizador.setMinimumSize(new java.awt.Dimension(225, 30));
        personalTextFieldCodigoUtilizador.setPreferredSize(new java.awt.Dimension(225, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(personalTextFieldCodigoUtilizador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personalTextFieldEmailUtilizador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personalTextFieldEmailUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personalTextFieldCodigoUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jComboBoxNomeUtilizador.setBackgroundColor(new Color(213,213,213));
        jComboBoxNomeUtilizador.setHelpText(lingua.translate("Nome de utilizador")+" ...");
        jComboBoxNomeUtilizador.create();
        javax.swing.JTextField txmutilizador = (javax.swing.JTextField) jComboBoxNomeUtilizador.getComboBox().getEditor().getEditorComponent();
        txmutilizador.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 8));
        txmutilizador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jComboBoxNomeUtilizador.setHorizontalTextPosition((int) javax.swing.JLabel.LEFT);
        personalTextFieldEmailUtilizador.addPlaceHolder(lingua.translate("Correio eletrónico")+" ...", jLabelRecurso);
        Border f = BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK));
        personalTextFieldEmailUtilizador.setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        personalTextFieldCodigoUtilizador.setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        personalTextFieldCodigoUtilizador.addPlaceHolder(lingua.translate("Código de identificação")+" ...", jLabelRecurso);
        jLabelUtilizador.setText(lingua.translate("Utilizador")+": ");
        jLabel6.setText(lingua.translate("Identificação")+":");
        jLabelEmail.setText(lingua.translate("E-mail")+":");

        jLabelRecurso.setBackground(new java.awt.Color(250, 250, 250));
        jLabelRecurso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRecurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelRecurso.setMaximumSize(new java.awt.Dimension(352, 30));
        jLabelRecurso.setMinimumSize(new java.awt.Dimension(352, 30));
        jLabelRecurso.setOpaque(true);
        jLabelRecurso.setPreferredSize(new java.awt.Dimension(352, 30));

        jButtonExit.setBackground(new java.awt.Color(1, 1, 1));
        jButtonExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExit.setFocusPainted(false);
        jButtonExit.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonExit.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonExit.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jComboBoxTempo.setFocusable(false);
        jComboBoxTempo.setMaximumSize(new java.awt.Dimension(293, 30));
        jComboBoxTempo.setMinimumSize(new java.awt.Dimension(293, 30));
        jComboBoxTempo.setPreferredSize(new java.awt.Dimension(293, 30));

        jButtonAlgoMais.setBackground(new java.awt.Color(51, 102, 203));
        jButtonAlgoMais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAlgoMais.setEnabled(false);
        jButtonAlgoMais.setFocusPainted(false);
        jButtonAlgoMais.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonAlgoMais.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonAlgoMais.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonAlgoMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlgoMaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInicialLayout = new javax.swing.GroupLayout(jPanelInicial);
        jPanelInicial.setLayout(jPanelInicialLayout);
        jPanelInicialLayout.setHorizontalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicialLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonAlgoMais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxTempo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneHorario))
                .addGap(12, 12, 12))
        );
        jPanelInicialLayout.setVerticalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLista, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInicialLayout.createSequentialGroup()
                                .addGap(0, 124, Short.MAX_VALUE)
                                .addComponent(jButtonConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAlgoMais, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jComboBoxTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneHorario)))
                .addGap(21, 21, 21))
        );

        jLabelLista.setText(lingua.translate("Lista de requisições atribuidas"));
        try {
            if (Clavis.KeyQuest.class.getResource("Images/save.png") != null) {
                BufferedImage im3 = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/save.png"));
                ImageIcon ic3 = new ImageIcon(im3);
                jButtonConfirma.setIcon(ic3);
            } else {
                jButtonConfirma.setText(lingua.translate("Requisitar"));
            }
        } catch(IOException e){}

        jButtonConfirma.setToolTipText(lingua.translate("Efetuar requisição"));
        if (material != null) {
            jLabelRecurso.setText(lingua.translate("Requisição")+": "+lingua.translate(material.getTypeOfMaterialName())+" "+material.getDescription());
        }
        else {
            jLabelRecurso.setText(lingua.translate("Primeiro, deve selecionar um recurso")+".");
        }
        try {
            if (Clavis.KeyQuest.class.getResource("Images/exit26x24.png") != null) {
                BufferedImage imexit = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/exit26x24.png"));
                ImageIcon icexit = new ImageIcon(imexit);
                jButtonExit.setIcon(icexit);
            } else {
                jButtonExit.setText(lingua.translate("Voltar"));
            }
        } catch (IOException e) {}
        jButtonExit.setToolTipText(lingua.translate("Voltar"));
        Components.PersonalCombo.setHorizontalTextPosition(javax.swing.JTextField.CENTER, jComboBoxTempo);
        ((javax.swing.JLabel)jComboBoxTempo.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);

        BasicComboPopup popupVista = (BasicComboPopup) jComboBoxTempo.getAccessibleContext().getAccessibleChild(0);
        popupVista.getList().setSelectionBackground(Color.DARK_GRAY);
        popupVista.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        try {
            if (Clavis.KeyQuest.class.getResource("Images/algomais.png") != null) {
                BufferedImage imalgomais = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/algomais.png"));
                ImageIcon icalgomais = new ImageIcon(imalgomais);
                jButtonAlgoMais.setIcon(icalgomais);
            } else {
                jButtonAlgoMais.setText(lingua.translate("Algo mais"));
            }
        } catch(IOException e){}

        jButtonAlgoMais.setToolTipText(lingua.translate("Acrescentar mais informação"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        jPanelInicial.setBackground(corfundo);
        jPanelInicial.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(corborda, 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        this.close();
    }//GEN-LAST:event_jButtonExitActionPerformed

    private boolean checkHolidays() {
        if (dinicio != null) {
            boolean vai_e_vem = false;
            TimeDate.HolidaysList feriados = Clavis.KeyQuest.getHolidays();
            for (TimeDate.Holiday hol : feriados.getHolidays()) {
                if ((hol.getDay() == dinicio.getDay()) && (hol.getMonth() == dinicio.getMonth())) {
                    vai_e_vem = true;
                }
                if ((hol.getDay() == dfim.getDay()) && (hol.getMonth() == dfim.getMonth())) {
                    vai_e_vem = true;
                }
                if (vai_e_vem) {
                    return true;
                }
            }
        }
        return false;
    }

    private void makeRequest(boolean mostra) {
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            int res = db.insertRequest(material, pessoaescolhida, reqatividade, reqturmas, reqdisciplinas, dinicio, dfim, tinicio, tfim);
            if (res == 1) {
                if (mostra) {
                    Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.INFORMACAO, corborda, lingua.translate("Nota"), 400, 200, lingua.translate("O registo da requisição foi efetuado") + ".", new String[]{lingua.translate("Voltar")});
                    mensagem.showMessage();
                }
                reqefetuada = true;
                this.changeButtonState();
                this.makeRequestsList();
                Clavis.KeyQuest.refreshTables();
                jComboBoxNomeUtilizador.setSelectedIndex(0);
                pessoaescolhida = null;
            } else {
                Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Nota"), 400, 200, lingua.translate("O registo não foi efetuado") + ".", new String[]{lingua.translate("Voltar")});
                mensagem.showMessage();
            }
        }
    }
    private void jButtonConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmaActionPerformed
        int situa = this.isIntervalDateValid();
        if ((pessoaescolhida != null) && (situa == 1)) {
            if (this.isMaterialInLateState()) {
                String aviso = "<html><div style='text-align:center'>" + lingua.translate("O recurso está emprestado com atraso de entrega") + ".<br/>"
                        + lingua.translate("Mesmo assim, pretende fazer a requisição") + "?</div></html>";
                Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.INFORMACAO, corborda, lingua.translate("Nota"), 400, 200, aviso, new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
                int val = mensagem.showMessage();
                if (val == 1) {
                    makeRequest(false);
                }
            } else {
                makeRequest(true);
            }
        }
    }//GEN-LAST:event_jButtonConfirmaActionPerformed

    private void jButtonAlgoMaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlgoMaisActionPerformed
        WOthersPanel panel = new WOthersPanel(this, url, lingua, reqatividade, reqturmas, reqdisciplinas);
        Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.ACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Adicionar outra informação"), 600, 400, panel.createPanelMoreInfo(500, 350), "", new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
        if (mensagem.showMessage() == 1) {
            reqatividade = panel.getActivity();
            reqturmas = panel.getStudentsClasses();
            reqdisciplinas = panel.getSubjects();
            if (!reqatividade.equals("")) {
                jButtonAlgoMais.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            } else {
                reqatividade = "";
                reqturmas = new java.util.ArrayList<>();
                reqdisciplinas = new java.util.ArrayList<>();
            }
        } else {
            reqatividade = "";
            reqturmas = new java.util.ArrayList<>();
            reqdisciplinas = new java.util.ArrayList<>();
        }
    }//GEN-LAST:event_jButtonAlgoMaisActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlgoMais;
    private javax.swing.JButton jButtonConfirma;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JComboBox<String> jComboBoxTempo;
    /*
    private javax.swing.JComboBox<String> jComboBoxUtilizador;
    */
    private javax.swing.JComboBox<Object> jComboBoxUtilizador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelEmail;
    private org.jdesktop.swingx.JXLabel jLabelEntrega;
    private org.jdesktop.swingx.JXLabel jLabelLista;
    private javax.swing.JLabel jLabelRecurso;
    private javax.swing.JLabel jLabelUtilizador;
    /*
    private org.jdesktop.swingx.JXList<String> jListHorario;
    */
    private org.jdesktop.swingx.JXList jListHorario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelInicial;
    private javax.swing.JScrollPane jScrollPaneHorario;
    private javax.swing.JSpinner jSpinnerDataEntrega;
    private javax.swing.JSpinner jSpinnerDataLevantamento;
    private javax.swing.JSpinner jSpinnerHoraEntrega;
    private javax.swing.JSpinner jSpinnerHoraLevantamento;
    private org.jdesktop.swingx.JXLabel jXLabelLevantamento;
    private Components.PersonalTextField personalTextFieldCodigoUtilizador;
    private Components.PersonalTextField personalTextFieldEmailUtilizador;
    // End of variables declaration//GEN-END:variables
}
