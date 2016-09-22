/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author toze
 */
public class WOthersPanel {

    private java.util.List<Keys.ClassStudents> reqturmas;
    private java.util.List<Keys.Subject> reqdisciplinas;
    private String reqatividade;
    private String url;
    private Langs.Locale lingua;
    private javax.swing.JFrame frame;
    private javax.swing.JDialog dialogo;

    public WOthersPanel(javax.swing.JFrame frame, String url, Langs.Locale lingua, String reqatividade, java.util.List<Keys.ClassStudents> reqturmas, java.util.List<Keys.Subject> reqdisciplinas) {
        this.reqturmas = reqturmas;
        this.reqatividade = reqatividade;
        this.reqdisciplinas = reqdisciplinas;
        this.url = url;
        this.lingua = lingua;
        this.frame = frame;
        this.dialogo = null;
    }

    public WOthersPanel(javax.swing.JDialog dialogo, String url, Langs.Locale lingua, String reqatividade, java.util.List<Keys.ClassStudents> reqturmas, java.util.List<Keys.Subject> reqdisciplinas) {
        this.reqturmas = reqturmas;
        this.reqatividade = reqatividade;
        this.reqdisciplinas = reqdisciplinas;
        this.url = url;
        this.lingua = lingua;
        this.frame = null;
        this.dialogo = dialogo;
    }

    public javax.swing.JPanel createPanelMoreInfo(int largura, int altura) {
        javax.swing.JPanel panel = new javax.swing.JPanel(null);
        panel.setPreferredSize(new Dimension(largura, altura));
        panel.setMinimumSize(new Dimension(largura, altura));
        panel.setSize(largura, altura);

        javax.swing.JLabel label1 = new javax.swing.JLabel();
        label1.setSize(largura / 3, 30);
        label1.setFocusable(true);
        label1.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        label1.setHorizontalTextPosition(javax.swing.JLabel.TRAILING);
        label1.setBounds(40, 20, 100, 30);
        label1.setText(lingua.translate("Atividade") + ": ");
        Components.PersonalButton btatividade = new Components.PersonalButton();
        try {
            if (Clavis.KeyQuest.class.getResource("Images/outro.png") != null) {
                BufferedImage bfbtatividade = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/outro.png"));
                ImageIcon icbtatividade = new ImageIcon(bfbtatividade);
                btatividade.setIcon(icbtatividade);
            } else {
                btatividade.setText("+");
            }
        } catch (IOException e) {

        }
        btatividade.setPreferredSize(new Dimension(30, 30));
        btatividade.setBounds(415, 20, 30, 30);
        btatividade.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Components.PersonalCombo cbatividade = new Components.PersonalCombo(label1);
        cbatividade.setPreferredSize(new Dimension(250, 34));
        cbatividade.getComboBox().setBounds(140, 20, 270, 34);
        cbatividade.setHorizontalTextPosition(javax.swing.JTextField.LEFT);
        javax.swing.JTextField tx1 = (javax.swing.JTextField) cbatividade.getComboBox().getEditor().getEditorComponent();
        tx1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        cbatividade.create();
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            java.util.Set<String> atividades = db.getActivities();
            db.close();
            atividades.stream().forEach((atividade) -> {
                cbatividade.getComboBox().addItem(lingua.translate(atividade));
            });
        }
        cbatividade.setSelectedIndex(0);

        // paineis inferiores
        javax.swing.JPanel panesquerda = new javax.swing.JPanel(null);
        panesquerda.setPreferredSize(new Dimension(250, 220));
        panesquerda.setBounds(0, 70, 250, 220);
        panel.add(panesquerda);
        javax.swing.JLabel labelturmas = new javax.swing.JLabel(lingua.translate("Turmas"));
        labelturmas.setPreferredSize(new Dimension(200, 30));
        labelturmas.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelturmas.setBounds(10, 0, 230, 28);
        labelturmas.setOpaque(true);
        labelturmas.setBorder(BorderFactory.createLineBorder(Color.black));
        labelturmas.setFocusable(true);
        labelturmas.setBackground(new Color(252, 252, 252));
        panesquerda.add(labelturmas);

        Components.PersonalButton bturmas = new Components.PersonalButton();
        try {
            if (Clavis.KeyQuest.class.getResource("Images/ok16x16.png") != null) {
                BufferedImage bfbturmas = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok16x16.png"));
                ImageIcon icbturmas = new ImageIcon(bfbturmas);
                bturmas.setIcon(icbturmas);
            } else {
                bturmas.setText("+");
            }
        } catch (IOException e) {

        }
        bturmas.setPreferredSize(new Dimension(30, 30));
        bturmas.setBounds(10, 36, 30, 30);
        bturmas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panesquerda.add(bturmas);
        Components.PersonalCombo cbturmas = new Components.PersonalCombo(labelturmas);
        cbturmas.create();
        cbturmas.setPreferredSize(new Dimension(200, 34));
        javax.swing.JTextField tx2 = (javax.swing.JTextField) cbturmas.getComboBox().getEditor().getEditorComponent();
        tx2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        cbturmas.getComboBox().setBounds(40, 36, 200, 34);
        cbturmas.setHelpText(lingua.translate("Turmas a participar ..."));
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            java.util.List<Keys.ClassStudents> turmas = db.getStudentsClasses();
            db.close();
            turmas.stream().forEach((turma) -> {
                cbturmas.getComboBox().addItem(turma);
            });
        }
        cbturmas.setHorizontalTextPosition(javax.swing.JTextField.LEFT);
        panesquerda.add(cbturmas.getComboBox());

        DefaultListModel<Keys.ClassStudents> ll = new DefaultListModel();
        javax.swing.JList<Keys.ClassStudents> lturmas = new javax.swing.JList<>(ll);
        javax.swing.CellRendererPane pon = (javax.swing.CellRendererPane) lturmas.getComponent(0);
        pon.setPreferredSize(new Dimension(230, 100));

        lturmas.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        lturmas.setCellRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 2L;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = (javax.swing.JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(150, 25));
                if (isSelected) {
                    label.setBackground(Color.GRAY);
                    label.setForeground(Color.WHITE);
                    label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
                return label;
            }
        });
        lturmas.setPreferredSize(new Dimension(220, 130));
        lturmas.setBackground(new Color(250, 250, 250));
        javax.swing.JScrollPane sturmas = new javax.swing.JScrollPane(lturmas);
        sturmas.setPreferredSize(new Dimension(230, 140));
        sturmas.setBounds(10, 78, 230, 140);
        sturmas.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)));
        sturmas.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panesquerda.add(sturmas);

        javax.swing.JPanel pandireita = new javax.swing.JPanel(null);
        pandireita.setPreferredSize(new Dimension(250, 220));
        pandireita.setBounds(260, 70, 250, 220);
        panel.add(pandireita);

        javax.swing.JLabel labeldisciplinas = new javax.swing.JLabel(lingua.translate("Disciplinas"));
        labeldisciplinas.setPreferredSize(new Dimension(200, 28));
        labeldisciplinas.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labeldisciplinas.setBounds(10, 0, 230, 28);
        labeldisciplinas.setOpaque(true);
        labeldisciplinas.setFocusable(true);
        labeldisciplinas.setBackground(new Color(252, 252, 252));
        labeldisciplinas.setBorder(labelturmas.getBorder());
        pandireita.add(labeldisciplinas);

        Components.PersonalButton bdisciplinas = new Components.PersonalButton();
        bdisciplinas.setIcon(bturmas.getIcon());
        bdisciplinas.setPreferredSize(new Dimension(30, 30));
        bdisciplinas.setBounds(10, 36, 30, 30);
        bdisciplinas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pandireita.add(bdisciplinas);

        Components.PersonalCombo cbdisciplinas = new Components.PersonalCombo(labeldisciplinas);
        cbdisciplinas.create();
        cbdisciplinas.setPreferredSize(new Dimension(210, 34));
        javax.swing.JTextField tx3 = (javax.swing.JTextField) cbdisciplinas.getComboBox().getEditor().getEditorComponent();
        tx3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        cbdisciplinas.getComboBox().setBounds(40, 36, 202, 34);
        cbdisciplinas.setHelpText(lingua.translate("Disciplinas envolvidas ..."));
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            java.util.List<Keys.Subject> disciplinas = db.getAllSubjects();
            db.close();
            disciplinas.stream().forEach((disciplina) -> {
                cbdisciplinas.getComboBox().addItem(disciplina);
            });
        }
        cbdisciplinas.setHorizontalTextPosition(javax.swing.JTextField.LEFT);
        pandireita.add(cbdisciplinas.getComboBox());

        DefaultListModel<Keys.Subject> ss = new DefaultListModel();
        javax.swing.JList<Keys.Subject> ldisciplinas = new javax.swing.JList<>(ss);
        ldisciplinas.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        ldisciplinas.setCellRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 2L;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = (javax.swing.JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(150, 25));
                if (isSelected) {
                    label.setBackground(Color.GRAY);
                    label.setForeground(Color.WHITE);
                    label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                }
                return label;
            }
        });
        ldisciplinas.setPreferredSize(new Dimension(220, 130));
        ldisciplinas.setBackground(new Color(250, 250, 250));

        javax.swing.JScrollPane sdisciplinas = new javax.swing.JScrollPane(ldisciplinas);
        sdisciplinas.setPreferredSize(new Dimension(230, 140));
        sdisciplinas.setBounds(10, 78, 230, 140);
        sdisciplinas.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, true, true, false), BorderFactory.createLineBorder(Color.BLACK)));
        sdisciplinas.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pandireita.add(sdisciplinas);

        panel.add(btatividade);
        panel.add(label1);
        panel.add(cbatividade.getComboBox());

        btatividade.addActionListener((ActionEvent e) -> {
            javax.swing.JPanel panel2 = new javax.swing.JPanel();
            panel2.setPreferredSize(new Dimension(400, 100));
            javax.swing.JLabel lb1 = new javax.swing.JLabel();
            lb1.setText(lingua.translate("Designação") + ":");
            lb1.setPreferredSize(new Dimension(100, 30));
            lb1.setFocusable(true);
            lb1.requestFocus();
            Components.PersonalTextField tx = new Components.PersonalTextField();
            tx.addPlaceHolder(lingua.translate("Nome da atividade"), lb1);
            tx.setPreferredSize(new Dimension(250, 30));
            tx.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            Border f = BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK));
            tx.setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 10, 0, 10)));
            panel2.add(lb1);
            panel2.add(tx);
            Components.MessagePane mensagem;
            if (frame != null) {
                mensagem = new Components.MessagePane(frame, Components.MessagePane.ACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Adicionar atividade"), 400, 220, panel2, "", new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
            } else {
                mensagem = new Components.MessagePane(dialogo, Components.MessagePane.ACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Adicionar atividade"), 400, 220, panel2, "", new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
            }
            if (mensagem.showMessage() == 1) {
                if (!tx.getText().equals("")) {
                    if (DataBase.DataBase.testConnection(url)) {
                        DataBase.DataBase db = new DataBase.DataBase(url);
                        db.insertActivity(tx.getText());
                        java.util.Set<String> atividades = db.getActivities();
                        db.close();
                        cbatividade.removeAllItems();
                        atividades.stream().forEach((atividade) -> {
                            cbatividade.getComboBox().addItem(lingua.translate(atividade));
                        });
                        cbatividade.setSelectedIndex(0);
                    }
                }
            }
        });

        bturmas.addActionListener((ActionEvent e) -> {
            if (cbturmas.getSelectedIndex() > 0) {
                Keys.ClassStudents s = (Keys.ClassStudents) cbturmas.getSelectedItem();
                if (!ll.contains(s)) {
                    getStudentsClasses().add(s);
                    ll.addElement(s);
                    if (((ll.getSize() * 25) + 10) > lturmas.getPreferredSize().getHeight()) {
                        lturmas.setPreferredSize(new Dimension((int) lturmas.getPreferredSize().getWidth(), (int) lturmas.getPreferredSize().getHeight() + 25));
                    }
                }
            }
        });

        bdisciplinas.addActionListener((ActionEvent e) -> {
            if (cbdisciplinas.getSelectedIndex() > 0) {
                Keys.Subject s = (Keys.Subject) cbdisciplinas.getSelectedItem();
                if (!ss.contains(s)) {
                    getSubjects().add(s);
                    ss.addElement(s);
                    if (((ss.getSize() * 25) + 10) > ldisciplinas.getPreferredSize().getHeight()) {
                        ldisciplinas.setPreferredSize(new Dimension((int) ldisciplinas.getPreferredSize().getWidth(), (int) ldisciplinas.getPreferredSize().getHeight() + 25));
                    }
                }
            }
        });

        lturmas.addKeyListener(new KeyAdapter() {
            Components.MessagePane mensagem;

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        if (lturmas.getSelectedIndex() > -1) {
                            Keys.ClassStudents turma = lturmas.getSelectedValue();
                            String envia = "<html><div style='text-align:left;font-size:11px;'>"
                                    + "<b>" + lingua.translate("Nome") + ": </b> " + lingua.translate(turma.getName())
                                    + "<br/><b>" + lingua.translate("Código") + ": </b> " + lingua.translate(turma.getCode())
                                    + "<br/><b>" + lingua.translate("Curso") + ": </b> " + lingua.translate(turma.getDegree())
                                    + "<br/> </div></html>";
                            mensagem = new Components.MessagePane(null, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Informação"), 300, 200, envia, new String[]{lingua.translate("Voltar")});
                            mensagem.showMessage();
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        lturmas.clearSelection();
                        break;
                    case KeyEvent.VK_DELETE:
                        getStudentsClasses().remove(lturmas.getSelectedIndex());
                        ll.remove(lturmas.getSelectedIndex());
                        break;
                    default:
                        break;
                }
            }
        });
        if ((getActivity() != null) && (!reqatividade.equals(""))) {
            cbatividade.setSelectedItem(getActivity());
            for (int i = 0; i < getStudentsClasses().size(); i++) {
                ll.addElement(getStudentsClasses().get(i));
            }
            for (int i = 0; i < getSubjects().size(); i++) {
                ss.addElement(getSubjects().get(i));
            }
            cbturmas.setSelectedIndex(0);
            cbdisciplinas.setSelectedIndex(0);
        } else {
            cbturmas.setEnabled(false);
            cbdisciplinas.setEnabled(false);
        }

        cbatividade.addActionListener((ActionEvent e) -> {
            if (cbatividade.getSelectedItem() != null) {
                if (cbatividade.getSelectedIndex() <= 0) {
                    cbturmas.setEnabled(false);
                    ss.removeAllElements();
                    cbdisciplinas.setEnabled(false);
                    ll.removeAllElements();
                } else {
                    cbturmas.setEnabled(true);
                    cbdisciplinas.setEnabled(true);

                }
                setActivity(cbatividade.getSelectedItem().toString());
            }

        });

        lturmas.addMouseListener(new MouseAdapter() {
            Components.PopUpMenu poplturmas;
            Components.MessagePane mensagem;

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (lturmas.getSelectedIndex() > -1) {
                        Keys.ClassStudents turma = lturmas.getSelectedValue();
                        String envia = "<html><div style='text-align:left;font-size:11px;'>"
                                + "<b>" + lingua.translate("Nome") + ": </b> " + lingua.translate(turma.getName())
                                + "<br/><b>" + lingua.translate("Código") + ": </b> " + lingua.translate(turma.getCode())
                                + "<br/><b>" + lingua.translate("Curso") + ": </b> " + lingua.translate(turma.getDegree())
                                + "<br/> </div></html>";
                        mensagem = new Components.MessagePane(null, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Informação"), 400, 200, envia, new String[]{lingua.translate("Voltar")});
                        mensagem.showMessage();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int sel = lturmas.locationToIndex(new java.awt.Point(e.getX(), e.getY()));
                    lturmas.setSelectedIndex(sel);
                    if (lturmas.getSelectedIndex() > -1) {
                        String[] titulos = {lingua.translate("Remover")};
                        ActionListener[] acts = new ActionListener[1];
                        acts[0] = (ActionEvent e1) -> {
                            getSubjects().remove(lturmas.getSelectedIndex());
                            ll.remove(lturmas.getSelectedIndex());
                        };
                        poplturmas = new Components.PopUpMenu(titulos, acts);
                        poplturmas.create();
                        poplturmas.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        ldisciplinas.addKeyListener(new KeyAdapter() {
            Components.MessagePane mensagem;

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        if (ldisciplinas.getSelectedIndex() > -1) {
                            Keys.Subject disciplina = ldisciplinas.getSelectedValue();
                            String envia = "<html><div style='text-align:left;font-size:11px;'>"
                                    + "<b>" + lingua.translate("Nome") + ": </b> " + lingua.translate(disciplina.getName())
                                    + "<br/><b>" + lingua.translate("Código") + ": </b> " + lingua.translate(disciplina.getCode())
                                    + "<br/> </div></html>";
                            mensagem = new Components.MessagePane(null, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Informação"), 300, 200, envia, new String[]{lingua.translate("Voltar")});
                            mensagem.showMessage();
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        ldisciplinas.clearSelection();
                        break;
                    case KeyEvent.VK_DELETE:
                        getSubjects().remove(ldisciplinas.getSelectedIndex());
                        ss.remove(ldisciplinas.getSelectedIndex());
                        break;
                    default:
                        break;
                }
            }
        });

        ldisciplinas.addMouseListener(new MouseAdapter() {
            Components.PopUpMenu popdisciplinas;
            Components.MessagePane mensagem;

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (ldisciplinas.getSelectedIndex() > -1) {
                        Keys.Subject disciplina = ldisciplinas.getSelectedValue();
                        String envia = "<html><div style='text-align:left;font-size:11px;'>"
                                + "<b>" + lingua.translate("Nome") + ": </b> " + lingua.translate(disciplina.getName())
                                + "<br/><b>" + lingua.translate("Código") + ": </b> " + lingua.translate(disciplina.getCode())
                                + "<br/> </div></html>";
                        mensagem = new Components.MessagePane(null, Components.MessagePane.INFORMACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Informação"), 400, 200, envia, new String[]{lingua.translate("Voltar")});
                        mensagem.showMessage();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int sel = ldisciplinas.locationToIndex(new java.awt.Point(e.getX(), e.getY()));
                    ldisciplinas.setSelectedIndex(sel);
                    if (ldisciplinas.getSelectedIndex() > -1) {
                        String[] titulos = {lingua.translate("Remover")};
                        ActionListener[] acts = new ActionListener[1];
                        acts[0] = (ActionEvent e1) -> {
                            getSubjects().remove(ldisciplinas.getSelectedIndex());
                            ss.remove(ldisciplinas.getSelectedIndex());
                        };
                        popdisciplinas = new Components.PopUpMenu(titulos, acts);
                        popdisciplinas.create();
                        popdisciplinas.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });
        return panel;
    }

    /**
     * @return the reqturmas
     */
    public java.util.List<Keys.ClassStudents> getStudentsClasses() {
        return reqturmas;
    }

    /**
     * @param reqturmas the reqturmas to set
     */
    public void setStudentsClasses(java.util.List<Keys.ClassStudents> reqturmas) {
        this.reqturmas = reqturmas;
    }

    /**
     * @return the reqdisciplinas
     */
    public java.util.List<Keys.Subject> getSubjects() {
        return reqdisciplinas;
    }

    /**
     * @param reqdisciplinas the reqdisciplinas to set
     */
    public void setSubjects(java.util.List<Keys.Subject> reqdisciplinas) {
        this.reqdisciplinas = reqdisciplinas;
    }

    /**
     * @return the reqatividade
     */
    public String getActivity() {
        return reqatividade;
    }

    /**
     * @param reqatividade the reqatividade to set
     */
    public void setActivity(String reqatividade) {
        this.reqatividade = reqatividade;
    }

}
