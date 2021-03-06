/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import Clavis.KeyQuest;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author toze
 */
public class WMaterial extends javax.swing.JDialog {

    public static final long serialVersionUID = 1L;
    public static final Color DEFAULT_PANEL_COLOR = Color.WHITE;
    private javax.swing.JDialog dialogopai;
    private Color panelcor;
    private final JPanel painelgeral;
    private final Langs.Locale lingua;
    private String url;
    private final Keys.Material mat;
    private final Preferences prefs;
    private org.jdesktop.swingx.JXTable tabela;
    private final javax.swing.JComboBox<String> comboboxdireitacima;
    private java.util.List<Keys.Feature> features;
    private java.util.List<Keys.Software> software;
    private java.util.List<Keys.Subject> disciplinas;
    private Components.PersonalButton btdireitacimaoutro;
    private String[] valores;
    private java.util.List<Keys.Feature> lfeat;
    private java.util.List<Keys.Software> lsoft;
    private java.util.List<Keys.Subject> ldis;
    private javax.swing.JSpinner sppinerquantidade;
    private javax.swing.JCheckBox checkatualizado;
    private javax.swing.JButton bteditar;
    private javax.swing.JButton btapagar;
    private ActionListener abteditar;
    private ActionListener abtapagar;
    private ActionListener abtoogle;
    private ListSelectionListener listlistener;
    private JPanel jpaneldireitabaixo;
    private final javax.swing.JLabel tdescricaoeditar;
    private final javax.swing.JLabel tversaoeditar;
    private final javax.swing.JLabel tanoeditar;
    private final javax.swing.JSpinner tquantidadeeditar;
    private final javax.swing.JLabel tempresaeditar;
    private final javax.swing.JCheckBox tatualizadoeditar;
    private final Components.PersonalToggleButton btoogleditar;
    private final javax.swing.JLabel labeldisciplina;
    private final javax.swing.JLabel labelcodigodisciplina;
    private javax.swing.JButton btmais;

    public WMaterial() {
        super();
        panelcor = DEFAULT_PANEL_COLOR;
        this.dialogopai = null;
        painelgeral = new JPanel();
        lingua = new Langs.Locale();
        mat = new Keys.Material();
        prefs = Preferences.userNodeForPackage(getClass());
        lfeat = new java.util.ArrayList<>();
        lsoft = new java.util.ArrayList<>();
        ldis = new java.util.ArrayList<>();
        jpaneldireitabaixo = new JPanel();
        tdescricaoeditar = new javax.swing.JLabel();
        tversaoeditar = new javax.swing.JLabel();
        tanoeditar = new javax.swing.JLabel();
        tempresaeditar = new javax.swing.JLabel();
        tatualizadoeditar = new javax.swing.JCheckBox();
        btoogleditar = new Components.PersonalToggleButton();
        bteditar = new javax.swing.JButton();
        btapagar = new javax.swing.JButton();
        labeldisciplina = new javax.swing.JLabel();
        labelcodigodisciplina = new javax.swing.JLabel();
        tquantidadeeditar = new javax.swing.JSpinner();
        comboboxdireitacima = new javax.swing.JComboBox<>();
    }

    public WMaterial(javax.swing.JDialog dialogo, Keys.Material mat, Langs.Locale lingua, String url) {
        super(dialogo);
        this.dialogopai = dialogo;
        panelcor = KeyQuest.getSystemColor();
        painelgeral = new JPanel();
        this.lingua = lingua;
        this.url = url;
        this.mat = mat;
        prefs = Preferences.userNodeForPackage(getClass());
        lfeat = new java.util.ArrayList<>();
        lsoft = new java.util.ArrayList<>();
        ldis = new java.util.ArrayList<>();
        tdescricaoeditar = new javax.swing.JLabel();
        tversaoeditar = new javax.swing.JLabel();
        tanoeditar = new javax.swing.JLabel();
        tempresaeditar = new javax.swing.JLabel();
        tatualizadoeditar = new javax.swing.JCheckBox();
        btoogleditar = new Components.PersonalToggleButton();
        bteditar = new javax.swing.JButton();
        btapagar = new javax.swing.JButton();
        labeldisciplina = new javax.swing.JLabel();
        labelcodigodisciplina = new javax.swing.JLabel();
        tquantidadeeditar = new javax.swing.JSpinner();
        comboboxdireitacima = new javax.swing.JComboBox<>();
    }

    public void create() {
        this.setTitle(lingua.translate("Mais definições do recurso") + ": " + lingua.translate(mat.getTypeOfMaterial().getTypeOfMaterialName()) + " " + lingua.translate(mat.getDescription()));
        this.setPreferredSize(new Dimension(700, 500));
        this.setResizable(false);
        this.setMinimumSize(new Dimension(700, 500));
        this.setMaximumSize(new Dimension(700, 500));
        this.setBackground(new Color(254, 254, 254));
        painelgeral.setPreferredSize(new Dimension(7000, 500));
        painelgeral.setBounds(0, 0, 700, 500);
        KeyQuest.addtoPropertyListener(painelgeral, true);
        javax.swing.border.Border border = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(panelcor, 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        painelgeral.setBorder(border);
        this.add(painelgeral);
        btoogleditar.setEnabled(false);
        btoogleditar.setSelected(true);
        JPanel jpanelesquerda = new javax.swing.JPanel();
        jpanelesquerda.setBackground(painelgeral.getBackground());
        java.awt.GridLayout gl = new java.awt.GridLayout();
        jpanelesquerda.setLayout(gl);
        jpanelesquerda.setPreferredSize(new java.awt.Dimension(355, 300));
        tquantidadeeditar.addMouseListener(Components.PopUpMenu.simpleCopyPaste(lingua, tquantidadeeditar, true));

        // combobox inicial
        javax.swing.JComboBox<String> comboboxopcoes;
        int valorauxiliar = 0;
        if (mat.getMaterialTypeID() != 1) {
            if (DataBase.DataBase.testConnection(url)) {
                DataBase.DataBase db = new DataBase.DataBase(url);
                if (db.isTypeOfMaterialHavingSoftware(mat)) {
                    valorauxiliar = 2;
                }
                db.close();
            }
        } else {
            valorauxiliar = 1;
        }
        switch (valorauxiliar) {
            case 1:
                comboboxopcoes = new javax.swing.JComboBox<>(new String[]{lingua.translate("Caraterísticas"), lingua.translate("Software em computadores"), lingua.translate("Disciplinas relacionadas")});
                break;
            case 2:
                comboboxopcoes = new javax.swing.JComboBox<>(new String[]{lingua.translate("Caraterísticas"), lingua.translate("Software em computadores")});
                break;
            default:
                comboboxopcoes = new javax.swing.JComboBox<>(new String[]{lingua.translate("Caraterísticas")});
                break;
        }
        ((javax.swing.JLabel) comboboxopcoes.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        BasicComboPopup popupVista = (BasicComboPopup) comboboxopcoes.getAccessibleContext().getAccessibleChild(0);
        popupVista.getList().setSelectionBackground(Color.DARK_GRAY);
        popupVista.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        if (prefs.get("material", mat.getCodeOfMaterial()).equals(mat.getCodeOfMaterial())) {
            comboboxopcoes.setSelectedIndex(prefs.getInt("combobox", 0));
        } else {
            comboboxopcoes.setSelectedIndex(0);
        }
        comboboxopcoes.setPreferredSize(new Dimension(310, 28));
        comboboxopcoes.setFocusable(false);
        comboboxopcoes.setBackground(new Color(213, 213, 213));
        comboboxopcoes.setBounds(0, 0, 320, 40);
        comboboxopcoes.addActionListener((ActionEvent e) -> {
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate(mat.getTypeOfMaterial().getTypeOfMaterialName()) + " " + lingua.translate(mat.getDescription())});
            jpanelesquerda.removeAll();
            painelgeral.repaint();
            jpanelesquerda.add(makeTable(comboboxopcoes, model, true, comboboxopcoes.getSelectedIndex()));
            prefs.put("material", mat.getCodeOfMaterial());
            prefs.putInt("combobox", comboboxopcoes.getSelectedIndex());
            this.updateComboBox(comboboxopcoes);
            drawcomponentsRight(comboboxopcoes, jpaneldireitabaixo);
            drawcomponentsRight(comboboxopcoes, jpaneldireitabaixo);
            if (comboboxopcoes.getSelectedIndex() == 2) {
                comboboxdireitacima.setBounds(20, 60, 243, 28);
            } else {
                comboboxdireitacima.setBounds(35, 60, 228, 28);
            }
            switch (comboboxopcoes.getSelectedIndex()) {
                case 1:
                    btdireitacimaoutro.setToolTipText(lingua.translate("Adicionar software"));
                    btmais.setToolTipText(lingua.translate("Associar software ao recurso"));
                    break;
                case 2:
                    btmais.setToolTipText(lingua.translate("Associar disciplina ao recurso"));
                    break;
                default:
                    btdireitacimaoutro.setToolTipText(lingua.translate("Adicionar caraterística"));
                    btmais.setToolTipText(lingua.translate("Associar caraterística ao recurso"));
                    break;
            }
        });
        sppinerquantidade = new javax.swing.JSpinner();
        javax.swing.JSpinner.NumberEditor editor = (javax.swing.JSpinner.NumberEditor) sppinerquantidade.getEditor();
        editor.getFormat().setGroupingUsed(false);
        editor.getModel().setMaximum(10000000);
        editor.getModel().setMinimum(0);
        editor.getModel().setValue(0);
        editor.getModel().setStepSize(1);
        checkatualizado = new javax.swing.JCheckBox();
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{mat.getTypeOfMaterial().getTypeOfMaterialName() + " " + mat.getDescription()});

        //paineis
        jpanelesquerda.removeAll();

        jpanelesquerda.add(this.makeTable(comboboxopcoes, model, true, comboboxopcoes.getSelectedIndex()));

        JPanel jpaneltituloesquerda = new javax.swing.JPanel();
        jpaneltituloesquerda.setPreferredSize(new java.awt.Dimension(315, 40));
        jpaneltituloesquerda.add(comboboxopcoes);

        JPanel jpaneltitulodireita = new javax.swing.JPanel(null);
        jpaneltitulodireita.setPreferredSize(new java.awt.Dimension(284, 40));
        javax.swing.JLabel labeltitulodireita = new javax.swing.JLabel(lingua.translate("Painéis de edição"));
        labeltitulodireita.setPreferredSize(new Dimension(280, 30));
        labeltitulodireita.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labeltitulodireita.setBounds(0, 0, 282, 30);
        labeltitulodireita.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labeltitulodireita.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labeltitulodireita.setOpaque(true);
        labeltitulodireita.setBackground(new Color(250, 250, 250));
        jpaneltitulodireita.add(labeltitulodireita);
        // adicionar carateristicas e software
        JPanel jpaneldireitacima = new javax.swing.JPanel(null);
        jpaneldireitacima.setPreferredSize(new java.awt.Dimension(284, 137));
        jpaneldireitacima.setBackground(new Color(250, 255, 225));

        btmais = new javax.swing.JButton();

        ImageIcon icoadd = null;
        try {
            BufferedImage imo = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/plus24x24.png"));
            icoadd = new ImageIcon(imo);
        } catch (IOException ex) {
            Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (icoadd != null) {
            btmais.setIcon(icoadd);
        } else {
            btmais.setText(lingua.translate("Adicionar"));
        }
        btmais.setPreferredSize(new Dimension(90, 40));
        btmais.setBackground(new Color(51, 102, 153));
        btmais.setFocusPainted(false);
        btmais.setEnabled(false);
        btmais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btmais.setBounds(185, 7, 90, 40);
        javax.swing.table.DefaultTableModel model2 = new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{mat.getTypeOfMaterial().getTypeOfMaterialName() + " " + mat.getDescription()});
        btmais.addActionListener((ActionEvent e) -> {
            int val = comboboxopcoes.getSelectedIndex();
            switch (val) {
                case 1:
                    if (software != null) {
                        Keys.Software soft = software.get(comboboxdireitacima.getSelectedIndex() - 1);
                        if (DataBase.DataBase.testConnection(url)) {
                            DataBase.DataBase db = new DataBase.DataBase(url);
                            db.associateSoftwareWithMaterial(soft, mat, checkatualizado.isSelected());
                            if (db.isSoftwareAssociatedWithMaterial(soft, mat)) {
                                btmais.setEnabled(false);
                                checkatualizado.setSelected(false);
                                javax.swing.JTextField jt = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
                                jt.setForeground(new Color(205, 205, 205));
                                comboboxdireitacima.setSelectedIndex(0);
                                requestFocusInWindow();
                                jpanelesquerda.removeAll();
                                jpanelesquerda.add(makeTable(comboboxopcoes, model2, true, comboboxopcoes.getSelectedIndex()));
                            }
                            db.close();
                        }
                    }
                    break;
                case 2:
                    if (disciplinas != null) {
                        Keys.Subject subs = disciplinas.get(comboboxdireitacima.getSelectedIndex() - 1);
                        if (DataBase.DataBase.testConnection(url)) {
                            DataBase.DataBase db = new DataBase.DataBase(url);
                            db.associateClassroomWithSubject(mat, subs);
                            if (db.isSubjectAssociatedWithClassroom(subs, mat)) {
                                btmais.setEnabled(false);
                                javax.swing.JTextField jt = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
                                jt.setForeground(new Color(205, 205, 205));
                                comboboxdireitacima.setSelectedIndex(0);
                                requestFocusInWindow();
                                jpanelesquerda.removeAll();
                                jpanelesquerda.add(makeTable(comboboxopcoes, model2, true, comboboxopcoes.getSelectedIndex()));
                            }
                            db.close();
                        }
                    }
                    break;
                default:
                    if (features != null) {
                        Keys.Feature feat = features.get(comboboxdireitacima.getSelectedIndex() - 1);
                        if (DataBase.DataBase.testConnection(url)) {
                            DataBase.DataBase db = new DataBase.DataBase(url);
                            feat.setNumber((int) sppinerquantidade.getValue());
                            db.associateFeatureWithMaterial(feat, mat);
                            if (db.isFeatureAssociatedWithMaterial(feat, mat)) {
                                btmais.setEnabled(false);
                                sppinerquantidade.setValue(0);
                                javax.swing.JTextField jt = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
                                jt.setForeground(new Color(205, 205, 205));
                                comboboxdireitacima.setSelectedIndex(0);
                                requestFocusInWindow();
                                jpanelesquerda.removeAll();
                                jpanelesquerda.add(makeTable(comboboxopcoes, model2, true, comboboxopcoes.getSelectedIndex()));
                                db.close();
                            }
                        }
                    }
                    break;
            }

        });
        jpaneldireitacima.add(btmais);
        comboboxdireitacima.setEditable(true);
        comboboxdireitacima.setEnabled(true);
        comboboxdireitacima.setAutoscrolls(true);
        comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(205, 205, 205));
        javax.swing.JTextField jtexto = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
        jtexto.setSelectionColor(Color.DARK_GRAY);
        this.updateComboBox(comboboxopcoes);

        comboboxdireitacima.setPreferredSize(new Dimension(250, 28));
        if (comboboxopcoes.getSelectedIndex() == 2) {
            comboboxdireitacima.setBounds(20, 60, 243, 28);
        } else {
            comboboxdireitacima.setBounds(35, 60, 228, 28);
        }
        BasicComboPopup popupcarateristicas = (BasicComboPopup) comboboxdireitacima.getAccessibleContext().getAccessibleChild(0);
        popupcarateristicas.getList().setSelectionBackground(Color.DARK_GRAY);
        popupcarateristicas.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        comboboxdireitacima.setFocusable(true);
        comboboxdireitacima.setBackground(new Color(213, 213, 213));

        comboboxdireitacima.getEditor().getEditorComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (comboboxdireitacima.getSelectedIndex() == 0) {
                    javax.swing.JTextField fil = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
                    fil.setText("");

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (comboboxdireitacima.getSelectedIndex() == 0) {
                    javax.swing.JTextField fil = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
                    fil.setCaretColor(Color.BLACK);
                    fil.setForeground(Color.BLACK);
                    comboboxdireitacima.setSelectedIndex(-1);
                }
            }
        });

        comboboxdireitacima.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            int aux;
            Object child = comboboxdireitacima.getAccessibleContext().getAccessibleChild(0);
            BasicComboPopup popup = (BasicComboPopup) child;
            javax.swing.JList<?> list = popup.getList();
            javax.swing.JTextField fil = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
            boolean bauxiliar = false;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    requestFocusInWindow();
                    updateComboBox(comboboxopcoes);
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (comboboxdireitacima.getSelectedIndex() == 0) {
                        fil.setCaretPosition(0);
                        fil.setText("");
                    }
                } else if ((comboboxdireitacima.getSelectedIndex() == 0) && (bauxiliar) && (e.getKeyCode() != KeyEvent.VK_ENTER)) {
                    fil.setCaretPosition(0);
                    fil.setForeground(Color.BLACK);
                    fil.setCaretColor(Color.BLACK);
                    comboboxdireitacima.setSelectedIndex(-1);
                    fil.setText("");
                    bauxiliar = false;
                } else if (!comboboxdireitacima.isPopupVisible()) {
                    comboboxdireitacima.setPopupVisible(true);
                } else if ((comboboxdireitacima.getSelectedIndex() == 1) && (e.getKeyCode() == KeyEvent.VK_UP)) {
                    comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(205, 205, 205));
                    fil.setCaretColor(Color.WHITE);

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int val = e.getKeyCode();
                if ((val == KeyEvent.VK_DOWN) || (val == KeyEvent.VK_UP)) {
                    if (comboboxdireitacima.getSelectedIndex() == 0) {
                        comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(205, 205, 205));
                        fil.setCaretColor(Color.WHITE);
                        bauxiliar = true;
                    } else {
                        comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(1, 1, 1));
                        fil.setCaretColor(Color.BLACK);
                    }
                    if ((val == KeyEvent.VK_DOWN) && (comboboxdireitacima.getSelectedIndex() == -1)) {
                        comboboxdireitacima.setSelectedIndex(1);
                    } else if (((e.getKeyCode() == KeyEvent.VK_UP) && (comboboxdireitacima.getSelectedIndex() == -1))) {
                        comboboxdireitacima.setSelectedIndex(1);
                    }
                } else if ((comboboxdireitacima.getSelectedIndex() == 0) && (val == KeyEvent.VK_ENTER)) {
                    requestFocusInWindow();
                } else if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (fil.getText().equals(""))) {
                    comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(205, 205, 205));
                    fil.setCaretColor(Color.WHITE);
                    bauxiliar = true;
                    comboboxdireitacima.setSelectedIndex(0);
                }

                if ((comboboxdireitacima.getSelectedIndex() != 0) && (e.getKeyCode() != KeyEvent.VK_LEFT) && (e.getKeyCode() != KeyEvent.VK_RIGHT)) {
                    String va = fil.getText();
                    aux = comboboxdireitacima.getSelectedIndex();
                    DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) comboboxdireitacima.getModel();
                    for (int i = 1; i <= modelo.getSize(); i++) {
                        if (modelo.getElementAt(i) != null) {
                            String mov = va;
                            String mov2 = modelo.getElementAt(i).toString();
                            mov = mov.replaceAll("[áàãäâ]", "a");
                            mov = mov.replaceAll("[íìîĩï]", "i");
                            mov = mov.replaceAll("[éèêẽë]", "e");
                            mov = mov.replaceAll("[úùũüû]", "u");
                            mov = mov.replaceAll("[óòôõö]", "o");
                            mov = mov.replaceAll("[ñ]", "n");
                            mov2 = mov2.replaceAll("[áàãäâ]", "a");
                            mov2 = mov2.replaceAll("[íìîĩï]", "i");
                            mov2 = mov2.replaceAll("[éèêẽë]", "e");
                            mov2 = mov2.replaceAll("[úùũüû]", "u");
                            mov2 = mov2.replaceAll("[óòôõö]", "o");
                            mov2 = mov2.replaceAll("[ñ]", "n");
                            if ((mov2.toLowerCase().matches("(.*)" + mov.toLowerCase().replaceAll("[\\[\\]\\(\\)\\/\\{\\}\"\\#$%&=\\\\]+", "") + "(.*)")) && (!mov.equals(""))) {
                                aux = i;
                                break;
                            }
                        }
                    }
                    comboboxdireitacima.setSelectedIndex(aux);
                    if (val == KeyEvent.VK_ENTER) {
                        if (comboboxdireitacima.getSelectedIndex() > 0) {
                            va = comboboxdireitacima.getSelectedItem().toString();
                        }
                    }
                    if (fil.getCaretPosition() == fil.getText().length()) {
                        fil.setText(va);
                    }
                }
            }
        }
        );

        comboboxdireitacima.getEditor()
                .getEditorComponent().addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e
                    ) {
                        if (comboboxdireitacima.getSelectedIndex() < 1) {
                            comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(1, 1, 1));
                            javax.swing.JTextField field = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
                            field.setText("");
                        }
                        tabela.clearSelection();
                        btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                    }

                    @Override
                    public void focusLost(FocusEvent e
                    ) {
                        if (comboboxdireitacima.getSelectedIndex() < 1) {
                            comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(205, 205, 205));
                            updateComboBox(comboboxopcoes);
                        }
                    }

                }
                );
        Object child = comboboxdireitacima.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup) child;
        javax.swing.JList<?> list = popup.getList();
        list.setCellRenderer(new ListCellRenderer<Object>() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = new javax.swing.JLabel();
                label.setOpaque(true);
                label.setBackground(new Color(254, 254, 254));
                label.setPreferredSize(new Dimension(90, 20));
                label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                label.setHorizontalAlignment(javax.swing.JLabel.LEFT);
                if (index != 0) {
                    label.setText(value.toString());
                } else {
                    label.setForeground(new Color(205, 205, 205));
                    label.setText("");
                }

                if (isSelected) {
                    label.setBackground(Color.DARK_GRAY);
                    label.setForeground(Color.WHITE);
                }
                return label;
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (comboboxdireitacima.getSelectedIndex() == 0) {
                    comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(205, 205, 205));
                    requestFocusInWindow();
                } else {
                    comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(1, 1, 1));
                }
            }
        });
        jpaneldireitacima.add(comboboxdireitacima);

        btdireitacimaoutro = new Components.PersonalButton();

        btdireitacimaoutro.setPreferredSize(new Dimension(30, 30));
        btdireitacimaoutro.setBounds(8, 58, 28, 28);
        btdireitacimaoutro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        switch (comboboxopcoes.getSelectedIndex()) {
            case 1:
                btdireitacimaoutro.setToolTipText(lingua.translate("Adicionar software"));
                btmais.setToolTipText(lingua.translate("Associar software ao recurso"));
                break;
            case 2:
                btmais.setToolTipText(lingua.translate("Associar disciplina ao recurso"));
                break;
            default:
                btdireitacimaoutro.setToolTipText(lingua.translate("Adicionar caraterística"));
                btmais.setToolTipText(lingua.translate("Associar caraterística ao recurso"));
                break;
        }
        ImageIcon ico = null;

        try {
            BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/outro.png"));
            ico = new ImageIcon(im);
        } catch (IOException ex) {
            Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ico != null) {
            btdireitacimaoutro.setIcon(ico);
        } else {
            btdireitacimaoutro.setText("+");
        }

        btdireitacimaoutro.addActionListener(
                (ActionEvent e) -> {
                    JPanel panel = new JPanel(null);
                    if (comboboxopcoes.getSelectedIndex() == 0) {
                        panel.setPreferredSize(new Dimension(380, 100));
                        javax.swing.JLabel ldescricao = new javax.swing.JLabel(lingua.translate("Descrição") + ": ");
                        ldescricao.setPreferredSize(new Dimension(120, 32));
                        ldescricao.setFocusable(true);
                        ldescricao.requestFocus();
                        ldescricao.setBounds(20, 0, 120, 32);
                        panel.add(ldescricao);

                        Components.PersonalTextField tdescricao = new Components.PersonalTextField();
                        tdescricao.setPreferredSize(new Dimension(240, 30));
                        tdescricao.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tdescricao.setBounds(120, 0, 240, 30);
                        panel.add(tdescricao);
                        javax.swing.JLabel lmedida = new javax.swing.JLabel(lingua.translate("Medida") + ": ");
                        lmedida.setPreferredSize(new Dimension(120, 30));
                        lmedida.setBounds(20, 40, 120, 30);
                        tdescricao.addMouseListener(Components.PopUpMenu.simpleCopyPaste(lingua, tdescricao, true));
                        panel.add(lmedida);

                        Components.PersonalTextField tmedida = new Components.PersonalTextField();
                        tmedida.setPreferredSize(new Dimension(240, 30));
                        tmedida.addMouseListener(Components.PopUpMenu.simpleCopyPaste(lingua, tmedida, true));
                        tmedida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tmedida.setBounds(120, 40, 240, 30);
                        panel.add(tmedida);

                        String[] btstexto = {lingua.translate("Adicionar"), lingua.translate("Voltar")};
                        tdescricao.addPlaceHolder(lingua.translate("Descrição da caraterística"), null);
                        tdescricao.setToolTipText(lingua.translate("Descricao da caraterística"));
                        tdescricao.setSelectionColor(Color.DARK_GRAY);
                        tmedida.addPlaceHolder(lingua.translate("Medida de valor"), null);
                        tmedida.setToolTipText(lingua.translate("Medida de valor"));
                        Components.MessagePane pop = new Components.MessagePane(this, Components.MessagePane.ACAO, panelcor, lingua.translate("Adicionar outra caraterística"), 400, 250, panel, "", btstexto);
                        ImageIcon icosair = null;
                        try {
                            BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/return.png"));
                            icosair = new ImageIcon(im);
                        } catch (IOException ex) {
                            Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pop.getLeftButton().setIcon(icosair);
                        pop.getLeftButton().setFocusable(true);
                        pop.getLeftButton().requestFocus();
                        tmedida.setLostCenterComponent(pop.getLeftButton());
                        tmedida.setSelectionColor(Color.DARK_GRAY);
                        tdescricao.setLostCenterComponent(pop.getLeftButton());
                        int val = pop.showMessage();
                        if (val == 1) {
                            String desc = tdescricao.getText();
                            String medida = tmedida.getText();
                            if (!desc.equals("")) {
                                if (DataBase.DataBase.testConnection(url)) {
                                    DataBase.DataBase db = new DataBase.DataBase(url);
                                    Keys.Feature feature = new Keys.Feature(desc, medida, 0, mat.getTypeOfMaterial());
                                    db.insertFeature(feature);
                                    db.close();
                                    updateComboBox(comboboxopcoes);
                                    for (int i = 0; i < comboboxdireitacima.getItemCount(); i++) {
                                        String aux = comboboxdireitacima.getItemAt(i);
                                        if (aux.equals(desc)) {
                                            comboboxdireitacima.setSelectedIndex(i);
                                            javax.swing.JTextField fl = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
                                            fl.setForeground(Color.BLACK);
                                            break;
                                        }
                                    }

                                }
                            }
                        }
                    } else if (comboboxopcoes.getSelectedIndex() == 1) {
                        panel.setPreferredSize(new Dimension(380, 150));
                        javax.swing.JLabel ldescricao = new javax.swing.JLabel(lingua.translate("Software") + ": ");
                        ldescricao.setPreferredSize(new Dimension(120, 32));
                        ldescricao.setFocusable(true);
                        ldescricao.requestFocus();
                        ldescricao.setBounds(30, 0, 120, 30);
                        panel.add(ldescricao);

                        Components.PersonalTextField tdescricao = new Components.PersonalTextField();
                        tdescricao.setPreferredSize(new Dimension(240, 32));
                        tdescricao.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tdescricao.setBounds(120, 0, 240, 30);
                        tdescricao.addMouseListener(Components.PopUpMenu.simpleCopyPaste(lingua, tdescricao, true));
                        panel.add(tdescricao);

                        javax.swing.JLabel lmedida = new javax.swing.JLabel(lingua.translate("Versão") + ": ");
                        lmedida.setPreferredSize(new Dimension(120, 32));
                        lmedida.setBounds(30, 40, 120, 30);
                        panel.add(lmedida);

                        Components.PersonalTextField tmedida = new Components.PersonalTextField();
                        tmedida.setPreferredSize(new Dimension(240, 32));
                        tmedida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tmedida.setBounds(120, 40, 240, 30);
                        tmedida.addMouseListener(Components.PopUpMenu.simpleCopyPaste(lingua, tmedida, true));
                        panel.add(tmedida);

                        javax.swing.JLabel lempresa = new javax.swing.JLabel(lingua.translate("Empresa") + ": ");
                        lempresa.setPreferredSize(new Dimension(120, 32));
                        lempresa.setBounds(30, 80, 120, 30);
                        panel.add(lempresa);

                        Components.PersonalTextField tempresa = new Components.PersonalTextField();
                        tempresa.setPreferredSize(new Dimension(240, 32));
                        tempresa.setSelectionColor(Color.DARK_GRAY);
                        tempresa.addMouseListener(Components.PopUpMenu.simpleCopyPaste(lingua, tempresa, true));
                        tempresa.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tempresa.setBounds(120, 80, 240, 30);
                        panel.add(tempresa);

                        javax.swing.JLabel lano = new javax.swing.JLabel(lingua.translate("Ano") + ": ");
                        lano.setPreferredSize(new Dimension(140, 32));
                        lano.setBounds(30, 120, 120, 30);
                        panel.add(lano);

                        javax.swing.JSpinner sano = new javax.swing.JSpinner();
                        int decim = new TimeDate.Date().getYear();
                        javax.swing.JSpinner.NumberEditor editor2 = (javax.swing.JSpinner.NumberEditor) sano.getEditor();
                        editor2.getFormat().setGroupingUsed(false);
                        editor2.getModel().setMaximum(decim);
                        editor2.getModel().setMinimum(1900);
                        editor2.getModel().setValue(decim);
                        editor2.getModel().setStepSize(1);
                        editor2.getTextField().setSelectionColor(Color.DARK_GRAY);
                        sano.setPreferredSize(new Dimension(120, 30));
                        sano.setBounds(120, 120, 100, 30);
                        panel.add(sano);
                        sano.setToolTipText(lingua.translate("Ano de lançamento"));
                        String[] btstexto = {lingua.translate("Adicionar"), lingua.translate("Voltar")};
                        tdescricao.addPlaceHolder(lingua.translate("Nome do programa"), null);
                        tdescricao.setToolTipText(lingua.translate("Nome do programa"));
                        tdescricao.setSelectionColor(Color.DARK_GRAY);
                        tmedida.addPlaceHolder(lingua.translate("Versão de software"), null);
                        tmedida.setToolTipText(lingua.translate("Versão de software"));
                        tempresa.addPlaceHolder(lingua.translate("Empresa proprietária"), null);
                        tempresa.setToolTipText(lingua.translate("Empresa proprietária"));
                        Components.MessagePane pop = new Components.MessagePane(this, Components.MessagePane.ACAO, panelcor, lingua.translate("Adicionar outro programa"), 400, 340, panel, "", btstexto);
                        ImageIcon icosair = null;
                        try {
                            BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/return.png"));
                            icosair = new ImageIcon(im);
                        } catch (IOException ex) {
                            Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        pop.getLeftButton().setIcon(icosair);
                        pop.getLeftButton().setFocusable(true);
                        pop.getLeftButton().requestFocus();
                        tmedida.setLostCenterComponent(pop.getLeftButton());
                        tmedida.setSelectionColor(Color.DARK_GRAY);
                        tdescricao.setLostCenterComponent(pop.getLeftButton());
                        int val = pop.showMessage();
                        if (val == 1) {
                            String desc = tdescricao.getText();
                            String versao = tmedida.getText();
                            String empresa = tempresa.getText();
                            int anoin = (int) sano.getValue();
                            String ano = String.valueOf(anoin);
                            if (!desc.equals("")) {
                                if (DataBase.DataBase.testConnection(url)) {
                                    DataBase.DataBase db = new DataBase.DataBase(url);
                                    Keys.Software soft = new Keys.Software(desc, versao, ano, empresa);
                                    db.insertSoftware(soft);
                                    db.close();
                                    updateComboBox(comboboxopcoes);
                                    for (int i = 0; i < comboboxdireitacima.getItemCount(); i++) {
                                        String aux = comboboxdireitacima.getItemAt(i);
                                        if (aux.equals(desc)) {
                                            comboboxdireitacima.setSelectedIndex(i);
                                            javax.swing.JTextField fl = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
                                            fl.setForeground(Color.BLACK);
                                            break;
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
        );
        javax.swing.JPanel paneladicionar = new javax.swing.JPanel(null);

        paneladicionar.setPreferredSize(new Dimension(265, 57));
        paneladicionar.setBounds(10, 94, 265, 32);
        paneladicionar.setBackground(jpaneldireitacima.getBackground());
        jpaneldireitacima.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, true, true, false), BorderFactory.createLineBorder(Color.BLACK, 1)));
        jpaneldireitacima.add(paneladicionar);

        drawcomponentsToAdd(comboboxopcoes, paneladicionar);

        if (comboboxopcoes.getSelectedIndex()
                != 2) {
            jpaneldireitacima.add(btdireitacimaoutro);
        }

        comboboxopcoes.addActionListener(
                (ActionEvent e) -> {
                    drawcomponentsToAdd(comboboxopcoes, paneladicionar);
                    if (comboboxopcoes.getSelectedIndex() != 2) {
                        if (!jpaneldireitacima.getComponent(1).equals(btdireitacimaoutro)) {
                            jpaneldireitacima.add(btdireitacimaoutro);
                        }
                    } else {
                        jpaneldireitacima.remove(btdireitacimaoutro);
                    }
                }
        );

        //jpaneldireitabaixo
        jpaneldireitabaixo = new javax.swing.JPanel(null);
        jpaneldireitabaixo.setPreferredSize(new java.awt.Dimension(315, 147));
        jpaneldireitabaixo.setBackground(new Color(248, 248, 253));
        jpaneldireitabaixo.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, true, true, false), BorderFactory.createLineBorder(Color.BLACK, 1)));
        this.drawcomponentsRight(comboboxopcoes, jpaneldireitabaixo);

        JPanel jpanelbaixo = new javax.swing.JPanel(null);
        javax.swing.JButton btsair = new javax.swing.JButton();

        try {
            if (Clavis.KeyQuest.class.getResource("Images/exit32x32.png") != null) {
                BufferedImage image = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/exit26x24.png"));
                ImageIcon iml = new ImageIcon(image);
                btsair.setIcon(iml);
            } else {
                btsair.setText(lingua.translate("Sair"));
            }
        } catch (IOException ex) {
            Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }

        btsair.setPreferredSize(new Dimension(90, 40));
        btsair.setBackground(new Color(1, 1, 1));
        btsair.setToolTipText(lingua.translate("Voltar"));
        btsair.setFocusPainted(false);
        btsair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsair.setBounds(10, 10, 90, 40);
        btsair.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            this.dispose();

        });
        jpanelbaixo.add(btsair);

        jpanelbaixo.setPreferredSize(new java.awt.Dimension(631, 70));

        javax.swing.GroupLayout playout = new javax.swing.GroupLayout(painelgeral);

        painelgeral.setLayout(playout);

        playout.setHorizontalGroup(
                playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(playout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jpanelbaixo, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                .addComponent(jpaneltituloesquerda, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                .addComponent(jpanelesquerda, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jpaneldireitabaixo, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(jpaneldireitacima, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(jpaneltitulodireita, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addContainerGap())
        );
        playout.setVerticalGroup(
                playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(playout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jpaneltitulodireita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jpaneltituloesquerda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(playout.createSequentialGroup()
                                        .addComponent(jpanelesquerda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jpanelbaixo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(playout.createSequentialGroup()
                                        .addComponent(jpaneldireitacima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jpaneldireitabaixo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                        .addContainerGap(74, Short.MAX_VALUE))
        );

        this.addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e
            ) {
                prefs.put("material", mat.getCodeOfMaterial());
                prefs.putInt("combobox", comboboxopcoes.getSelectedIndex());
            }

            @Override
            public void windowClosed(WindowEvent e
            ) {
                prefs.put("material", mat.getCodeOfMaterial());
                prefs.putInt("combobox", comboboxopcoes.getSelectedIndex());
            }
        }
        );
        comboboxdireitacima.addActionListener((ActionEvent e) -> {
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            btmais.setEnabled(false);
            int tamanho = modelo.getRowCount();
            boolean altera = false;
            int val = comboboxopcoes.getSelectedIndex();
            switch (val) {
                case 1:
                    if ((comboboxopcoes.getSelectedIndex() == 1) && (comboboxdireitacima.getSelectedIndex() > 0)) {
                        if (!lsoft.isEmpty()) {
                            for (Keys.Software soft : lsoft) {
                                if (software.get(comboboxdireitacima.getSelectedIndex() - 1).compareTo(soft) == 0) {
                                    altera = true;
                                }
                            }
                        }
                        if (!altera) {
                            btmais.setEnabled(true);
                        } else {
                            btmais.setEnabled(false);
                        }
                    } else if (comboboxdireitacima.getSelectedIndex() <= 0) {
                        btmais.setEnabled(false);
                    }
                    break;
                case 2:
                    if ((comboboxopcoes.getSelectedIndex() == 2) && (comboboxdireitacima.getSelectedIndex() > 0)) {
                        if (!ldis.isEmpty()) {
                            for (Keys.Subject sub : ldis) {
                                if ((disciplinas.get(comboboxdireitacima.getSelectedIndex() - 1).getCode().equals(sub.getCode())) && (disciplinas.get(comboboxdireitacima.getSelectedIndex() - 1).getName().equals(sub.getName()))) {
                                    altera = true;
                                }
                            }
                        }
                        if (!altera) {
                            btmais.setEnabled(true);
                        } else {
                            btmais.setEnabled(false);
                        }
                    } else if (comboboxdireitacima.getSelectedIndex() <= 0) {
                        btmais.setEnabled(false);
                    }
                    break;
                default:
                    if ((comboboxopcoes.getSelectedIndex() == 0) && (comboboxdireitacima.getSelectedIndex() > 0)) {
                        if (!lfeat.isEmpty()) {
                            for (Keys.Feature fea : lfeat) {
                                if (features.get(comboboxdireitacima.getSelectedIndex() - 1).compareTo(fea) == 0) {
                                    altera = true;
                                }
                            }
                        }
                        if (!altera) {
                            btmais.setEnabled(true);
                        } else {
                            btmais.setEnabled(false);
                        }
                        Keys.Feature fot = features.get(comboboxdireitacima.getSelectedIndex()-1);
                        if ((fot.getUnityMeasure().equals(""))||(fot.getUnityMeasure().equals("sem"))) {
                            sppinerquantidade.setValue(0);
                            sppinerquantidade.setEnabled(false);
                        } else {
                            sppinerquantidade.setEnabled(true);
                        }
                    } else if (comboboxdireitacima.getSelectedIndex() <= 0) {
                        btmais.setEnabled(false);
                        sppinerquantidade.setValue(0);
                        sppinerquantidade.setEnabled(false);
                    }
                    break;
            }
        });

    }

    public void appear() {
        this.setVisible(true);
        this.setLocationRelativeTo(getParentWindow());
    }

    /**
     * @return the panelcor
     */
    public Color getBorderColor() {
        return panelcor;
    }

    /**
     * @param panelcor the panelcor to set
     */
    public void setBorderColor(Color panelcor) {
        this.panelcor = panelcor;
    }

    /**
     * @return the dialogopai
     */
    public javax.swing.JDialog getParentWindow() {
        return dialogopai;
    }

    /**
     * @param dialogopai the dialogopai to set
     */
    public void setParentWindow(javax.swing.JDialog dialogopai) {
        this.dialogopai = dialogopai;
    }

    private javax.swing.JScrollPane makeTable(javax.swing.JComboBox<String> comboboxopcoes, DefaultTableModel model, boolean ativa, int valor) {
        javax.swing.JScrollPane panelscroll = new javax.swing.JScrollPane();
        if (DataBase.DataBase.testConnection(url)) {
            tabela = new org.jdesktop.swingx.JXTable();
            Border border = BorderFactory.createEmptyBorder(5, 5, 0, 0);
            UIManager.put("Table.focusCellHighlightBorder", border);
            tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            panelscroll.setPreferredSize(new Dimension(334, 300));
            panelscroll.setBounds(0, 0, 334, 300);
            panelscroll.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            panelscroll.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            panelscroll.setViewportView(tabela);
            panelscroll.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            javax.swing.JLabel lo = new javax.swing.JLabel();
            lo.setBackground(new Color(100, 100, 100));
            lo.setOpaque(true);
            panelscroll.setCorner(javax.swing.JScrollPane.UPPER_TRAILING_CORNER, lo);
            tabela.setRowHeight(30);
            tabela.setModel(model);
            int size = 0;
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            int tam = modelo.getRowCount();
            for (int i = 0; i < tam; i++) {
                modelo.removeRow(0);
            }
            DataBase.DataBase db = new DataBase.DataBase(url);
            tabela.setSelectionBackground(Color.DARK_GRAY);
            String auxiliar;
            int valorauxiliar = 0;
            if (mat.getMaterialTypeID() != 1) {
                if (DataBase.DataBase.testConnection(url)) {
                    if (db.isTypeOfMaterialHavingSoftware(mat)) {
                        valorauxiliar = 2;
                    }
                }
            } else {
                valorauxiliar = 1;
            }
            switch (valorauxiliar) {
                case 1:
                    switch (valor) {
                        case 1:
                            lsoft = new java.util.ArrayList<>();
                            lsoft = db.getSoftwareListByMaterial(mat);
                            for (Keys.Software soft : lsoft) {
                                auxiliar = soft.getName() + " (" + lingua.translate("Versão") + ": " + soft.getVersion() + ")";
                                modelo.addRow(new String[]{auxiliar});
                                size += tabela.getRowHeight();
                            }
                            break;
                        case 2:
                            ldis = db.getSubjectsByMaterial(mat);
                            for (Keys.Subject dis : ldis) {
                                auxiliar = lingua.translate(dis.getName());
                                modelo.addRow(new String[]{auxiliar});
                                size += tabela.getRowHeight();
                            }
                            break;
                        default:
                            lfeat = db.getFeaturesByMaterial(mat);
                            for (Keys.Feature fea : lfeat) {
                                auxiliar = lingua.translate(fea.getDescription());
                                if (fea.getNumber() != 0) {
                                    auxiliar = auxiliar + " (" + fea.getNumber() + " " + fea.getUnityMeasure() + ")";
                                }
                                modelo.addRow(new String[]{auxiliar});
                                size += tabela.getRowHeight();
                            }
                            break;
                    }
                    break;
                case 2:
                    switch (valor) {
                        case 1:
                            lsoft = db.getSoftwareListByMaterial(mat);
                            for (Keys.Software soft : lsoft) {
                                auxiliar = soft.getName() + " (" + lingua.translate("Versão") + ": " + soft.getVersion() + ")";
                                modelo.addRow(new String[]{auxiliar});
                                size += tabela.getRowHeight();
                            }
                            break;
                        default:
                            lfeat = db.getFeaturesByMaterial(mat);
                            for (Keys.Feature fea : lfeat) {
                                auxiliar = lingua.translate(fea.getDescription());
                                if (fea.getNumber() != 0) {
                                    auxiliar = auxiliar + " (" + fea.getNumber() + " " + fea.getUnityMeasure() + ")";
                                }
                                modelo.addRow(new String[]{auxiliar});
                                size += tabela.getRowHeight();
                            }
                            break;
                    }
                    break;
                default:
                    lfeat = db.getFeaturesByMaterial(mat);
                    for (Keys.Feature fea : lfeat) {
                        auxiliar = lingua.translate(fea.getDescription());
                        if (fea.getNumber() != 0) {
                            auxiliar = auxiliar + " (" + fea.getNumber() + " " + fea.getUnityMeasure() + ")";
                        }
                        modelo.addRow(new String[]{auxiliar});
                        size += tabela.getRowHeight();
                    }
                    break;
            }
            db.close();
            tabela.setPreferredSize(new Dimension(334, size));
            tabela.getColumnModel().getColumn(0).setCellRenderer(renderer);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
                private static final long serialVersionUID = 1L;

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                            row, column);
                    setFont(new Font("Cantarell", Font.PLAIN, 14));
                    this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(1, 1, 1)));
                    return this;
                }
            };

            headerRenderer.setBackground(new Color(100, 100, 100));
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setPreferredSize(new Dimension(100, 40));
            headerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            tabela.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
            tabela.setEnabled(ativa);
            tabela.setEditable(false);
            btoogleditar.setSelected(true);
            btoogleditar.setEnabled(false);
            bteditar.setEnabled(false);
            btapagar.setEnabled(false);
            switch (comboboxopcoes.getSelectedIndex()) {
                case 1:
                    if (listlistener != null) {
                        tabela.getSelectionModel().removeListSelectionListener(listlistener);
                    }
                    listlistener = (ListSelectionEvent e) -> {
                        if (tabela.getSelectedRow() > -1) {
                            Keys.Software soft = new Keys.Software(lsoft.get(tabela.getSelectedRow()));
                            tdescricaoeditar.setText(soft.getName());
                            String ano = soft.getYear();
                            if (ano.equals("sem")) {
                                ano = "";
                            }
                            String empresa = soft.getInterprise();
                            if (empresa.equals("sem")) {
                                empresa = "";
                            }
                            tdescricaoeditar.setBackground(new Color(254, 254, 254));
                            tdescricaoeditar.setForeground(new Color(1, 1, 1));
                            tanoeditar.setBackground(new Color(254, 254, 254));
                            tanoeditar.setForeground(new Color(1, 1, 1));
                            tanoeditar.setText(ano);
                            tversaoeditar.setBackground(new Color(254, 254, 254));
                            tversaoeditar.setForeground(new Color(1, 1, 1));
                            tversaoeditar.setText(soft.getVersion());
                            tempresaeditar.setBackground(new Color(254, 254, 254));
                            tempresaeditar.setForeground(new Color(1, 1, 1));
                            tempresaeditar.setText(empresa);
                            btoogleditar.setEnabled(true);
                            if (DataBase.DataBase.testConnection(url)) {
                                DataBase.DataBase dab = new DataBase.DataBase(url);
                                boolean v = dab.getStateOfSoftwareUpdated(soft, mat);
                                if (v) {
                                    tatualizadoeditar.setSelected(true);
                                } else {
                                    tatualizadoeditar.setSelected(false);
                                }
                                dab.close();
                            }
                            bteditar.setEnabled(false);
                            btapagar.setEnabled(false);
                            tatualizadoeditar.setEnabled(false);
                            btoogleditar.setSelected(true);

                            btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                        } else {
                            tdescricaoeditar.setText(lingua.translate("Nome do programa"));
                            tdescricaoeditar.setBackground(new Color(245, 245, 245));
                            tdescricaoeditar.setForeground(new Color(142, 143, 145));
                            tversaoeditar.setText(lingua.translate("Versão de software"));
                            tversaoeditar.setBackground(new Color(245, 245, 245));
                            tversaoeditar.setForeground(new Color(142, 143, 145));
                            tempresaeditar.setText(lingua.translate("Empresa proprietária"));
                            tempresaeditar.setBackground(new Color(245, 245, 245));
                            tempresaeditar.setForeground(new Color(142, 143, 145));
                            tanoeditar.setText(lingua.translate("Ano de lançamento"));
                            tanoeditar.setBackground(new Color(245, 245, 245));
                            tanoeditar.setForeground(new Color(142, 143, 145));
                            tatualizadoeditar.setEnabled(false);
                            tatualizadoeditar.setSelected(false);
                            btoogleditar.setSelected(true);
                            btoogleditar.setEnabled(false);
                            bteditar.setEnabled(false);
                            btapagar.setEnabled(false);
                        }
                    };
                    break;
                case 2:
                    if (listlistener != null) {
                        tabela.getSelectionModel().removeListSelectionListener(listlistener);
                    }
                    listlistener = (ListSelectionEvent e) -> {
                        if (tabela.getSelectedRow() > -1) {
                            Keys.Subject sub = new Keys.Subject(ldis.get(tabela.getSelectedRow()));
                            labeldisciplina.setText(sub.getName());
                            labeldisciplina.setBackground(new Color(254, 254, 254));
                            labeldisciplina.setForeground(new Color(1, 1, 1));
                            labelcodigodisciplina.setText(sub.getCode());
                            labelcodigodisciplina.setBackground(new Color(254, 254, 254));
                            labelcodigodisciplina.setForeground(new Color(1, 1, 1));
                            btoogleditar.setEnabled(true);
                            btoogleditar.setSelected(true);
                        } else {
                            labeldisciplina.setText(lingua.translate("Nome da disciplina"));
                            labeldisciplina.setBackground(new Color(245, 245, 245));
                            labeldisciplina.setForeground(new Color(142, 143, 145));
                            labelcodigodisciplina.setText(lingua.translate("Código da disciplina"));
                            labelcodigodisciplina.setBackground(new Color(245, 245, 245));
                            labelcodigodisciplina.setForeground(new Color(142, 143, 145));
                            btoogleditar.setEnabled(false);
                            btoogleditar.setSelected(true);
                            btapagar.setEnabled(false);
                        }
                    };
                    break;
                default:
                    if (listlistener != null) {
                        tabela.getSelectionModel().removeListSelectionListener(listlistener);
                    }
                    listlistener = (ListSelectionEvent e) -> {
                        if (tabela.getSelectedRow() > -1) {
                            Keys.Feature fea = new Keys.Feature(lfeat.get(tabela.getSelectedRow()));
                            if (fea.getUnityMeasure().equals("")) {
                                tdescricaoeditar.setBackground(new Color(254, 254, 254));
                                tdescricaoeditar.setForeground(new Color(1, 1, 1));
                                tdescricaoeditar.setText(fea.getDescription());
                                tversaoeditar.setVisible(false);
                                bteditar.setVisible(false);
                                tquantidadeeditar.setVisible(false);
                            } else {
                                tversaoeditar.setVisible(true);
                                tquantidadeeditar.setVisible(true);
                                bteditar.setVisible(true);
                                tdescricaoeditar.setBackground(new Color(254, 254, 254));
                                tdescricaoeditar.setForeground(new Color(1, 1, 1));
                                tdescricaoeditar.setText(fea.getDescription());
                                tversaoeditar.setBackground(new Color(254, 254, 254));
                                tversaoeditar.setForeground(new Color(1, 1, 1));
                                tversaoeditar.setText(fea.getUnityMeasure());
                                tquantidadeeditar.setValue(fea.getNumber());
                            }
                            btoogleditar.setSelected(true);
                            btoogleditar.setEnabled(true);
                            bteditar.setEnabled(false);
                            btapagar.setEnabled(false);
                            tquantidadeeditar.setEnabled(false);
                            btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                        } else {
                            tversaoeditar.setVisible(true);
                            tquantidadeeditar.setVisible(true);
                            bteditar.setVisible(true);
                            tdescricaoeditar.setText(lingua.translate("Nome da caraterística"));
                            tversaoeditar.setText(lingua.translate("Medida de valor"));
                            tdescricaoeditar.setBackground(new Color(245, 245, 245));
                            tdescricaoeditar.setForeground(new Color(142, 143, 145));
                            tversaoeditar.setBackground(new Color(245, 245, 245));
                            tversaoeditar.setForeground(new Color(142, 143, 145));
                            tquantidadeeditar.setValue(0);
                            tquantidadeeditar.setEnabled(false);
                            btoogleditar.setSelected(true);
                            btoogleditar.setEnabled(false);
                            btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                            bteditar.setEnabled(false);
                            btapagar.setEnabled(false);
                        }
                    };
                    break;
            }
            tabela.getSelectionModel().addListSelectionListener(listlistener);
            tabela.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        tabela.clearSelection();
                        if (comboboxopcoes.getSelectedIndex() == 1) {
                            tdescricaoeditar.setText(lingua.translate("Nome do programa"));
                            tversaoeditar.setText(lingua.translate("Versão de software"));
                            tempresaeditar.setText(lingua.translate("Empresa proprietária"));
                            tanoeditar.setText(lingua.translate("Ano de lançamento"));
                        } else {
                            tdescricaoeditar.setText(lingua.translate("Nome da caraterística"));
                            tversaoeditar.setText(lingua.translate("Medida de valor"));
                        }
                        btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                    }
                }
            });
        }
        return panelscroll;
    }

    private void updateComboBox(javax.swing.JComboBox<String> comboboxopcoes) {
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            int val = comboboxopcoes.getSelectedIndex();
            javax.swing.JTextField jt = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
            jt.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            int muda;
            switch (val) {
                case 1:
                    software = db.getSoftwareList();
                    valores = new String[software.size() + 1];
                    int j = 1;
                    valores[0] = lingua.translate("Associar software") + " ...";
                    while (j < software.size() + 1) {
                        valores[j] = software.get(j - 1).getName();
                        j++;
                    }
                    muda = 2;
                    for (int ko = 0; ko < valores.length; ko++) {
                        for (int ki = ko + 1; ki < valores.length; ki++) {
                            if (valores[ko].toLowerCase().equals(valores[ki].toLowerCase())) {
                                valores[ki] = valores[ki] + " (" + muda + ")";
                            }
                            muda++;
                        }
                        muda = 2;
                    }
                    comboboxdireitacima.setModel(new javax.swing.DefaultComboBoxModel<>(valores));
                    break;
                case 2:
                    disciplinas = db.getAllSubjects();
                    valores = new String[disciplinas.size() + 1];
                    int l = 1;
                    valores[0] = lingua.translate("Associar disciplina") + " ...";
                    while (l < disciplinas.size() + 1) {
                        valores[l] = disciplinas.get(l - 1).getName();
                        l++;
                    }
                    muda = 2;
                    for (int ko = 0; ko < valores.length; ko++) {
                        for (int ki = ko + 1; ki < valores.length; ki++) {
                            if (valores[ko].toLowerCase().equals(valores[ki].toLowerCase())) {
                                valores[ki] = valores[ki] + " (" + muda + ")";
                            }
                            muda++;
                        }
                        muda = 2;
                    }
                    comboboxdireitacima.setModel(new javax.swing.DefaultComboBoxModel<>(valores));
                    break;
                default:
                    features = db.getFeaturesByTypeOfMaterial(mat.getTypeOfMaterial());
                    valores = new String[features.size() + 1];
                    int i = 1;
                    valores[0] = lingua.translate("Associar caraterística") + " ...";
                    while (i < features.size() + 1) {
                        valores[i] = features.get(i - 1).getDescription();
                        i++;
                    }
                    muda = 2;
                    for (int ko = 0; ko < valores.length; ko++) {
                        for (int ki = ko + 1; ki < valores.length; ki++) {
                            if (valores[ko].toLowerCase().equals(valores[ki].toLowerCase())) {
                                valores[ki] = valores[ki] + " (" + muda + ")";
                            }
                            muda++;
                        }
                        muda = 2;
                    }
                    comboboxdireitacima.setModel(new javax.swing.DefaultComboBoxModel<>(valores));
                    break;
            }
            db.close();
            jt.setForeground(new Color(205, 205, 205));
            requestFocusInWindow();
            Object child = comboboxdireitacima.getAccessibleContext().getAccessibleChild(0);
            BasicComboPopup popup = (BasicComboPopup) child;
            javax.swing.JList<?> list = popup.getList();
            list.ensureIndexIsVisible(list.getSelectedIndex());
            comboboxdireitacima.setSelectedIndex(0);
        }

    }

    private void drawcomponentsToAdd(javax.swing.JComboBox<String> comboboxopcoes, javax.swing.JPanel panel) {

        if (panel.getComponentCount() > 0) {
            panel.removeAll();
        }
        switch (comboboxopcoes.getSelectedIndex()) {
            case 1:
                checkatualizado.setText(lingua.translate("Atualizado") + ":");
                checkatualizado.setHorizontalTextPosition(SwingConstants.LEFT);
                checkatualizado.setHorizontalAlignment(SwingConstants.RIGHT);
                checkatualizado.setBounds((int) (panel.getPreferredSize().getWidth() - checkatualizado.getPreferredSize().getWidth()) - 18, 0, (int) checkatualizado.getPreferredSize().getWidth(), 28);
                panel.add(checkatualizado);

                break;
            case 2:
                break;
            default:
                javax.swing.JLabel label = new javax.swing.JLabel(lingua.translate("Quantidade") + ": ");
                label.setPreferredSize(new Dimension(135, 26));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setBounds(0, 0, 105, 26);
                sppinerquantidade.setPreferredSize(new Dimension(110, 28));
                sppinerquantidade.setBounds(111, 0, 142, 28);
                sppinerquantidade.setFocusable(false);
                panel.add(label);
                panel.add(sppinerquantidade);
                break;

        }
    }

    private void drawcomponentsRight(javax.swing.JComboBox<String> comboboxopcoes, javax.swing.JPanel panel) {

        if (panel.getComponentCount() > 0) {
            panel.removeAll();
        }
        ImageIcon icoadd = null;

        btapagar = new javax.swing.JButton();
        btapagar.setPreferredSize(new Dimension(90, 40));
        btapagar.setBackground(new Color(51, 102, 153));
        btapagar.setFocusPainted(false);
        btapagar.setEnabled(false);
        btapagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        try {
            if (Clavis.KeyQuest.class.getResource("Images/apagar.png") != null) {
                BufferedImage imo = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/apagar.png"));
                icoadd = new ImageIcon(imo);
            } else {
                icoadd = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (icoadd != null) {
            btapagar.setIcon(icoadd);
        } else {
            btapagar.setText(lingua.translate("Apagar"));
        }
        btapagar.setBounds((int) (panel.getPreferredSize().getWidth() - 130), 7, 90, 40);
        btapagar.setToolTipText(lingua.translate("Eliminar associação"));
        panel.add(btapagar);
        switch (comboboxopcoes.getSelectedIndex()) {
            case 1:
                if (lsoft != null) {
                    //bt editar
                    bteditar = new javax.swing.JButton();
                    bteditar.setPreferredSize(new Dimension(90, 40));
                    bteditar.setBackground(new Color(51, 102, 153));
                    bteditar.setFocusPainted(false);
                    bteditar.setEnabled(false);
                    bteditar.setToolTipText(lingua.translate("Gravar novos valores"));
                    bteditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    try {
                        if (Clavis.KeyQuest.class.getResource("Images/edit.png") != null) {
                            BufferedImage imo = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/edit.png"));
                            icoadd = new ImageIcon(imo);
                        } else {
                            icoadd = null;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (icoadd != null) {
                        bteditar.setIcon(icoadd);
                    } else {
                        bteditar.setText(lingua.translate("Editar"));
                    }
                    bteditar.setBounds((int) (panel.getPreferredSize().getWidth() - 225), 7, 90, 40);
                    panel.add(bteditar);
                    if (abteditar != null) {
                        bteditar.removeActionListener(abteditar);
                    }
                    abteditar = ((ActionEvent e) -> {
                        if (DataBase.DataBase.testConnection(url)) {
                            if ((tabela.getSelectedRow() > -1) && (!btoogleditar.isSelected())) {
                                DataBase.DataBase db = new DataBase.DataBase(url);
                                Keys.Software velho = new Keys.Software(lsoft.get(tabela.getSelectedRow()));
                                int resultado = db.updateStateOfSoftware(velho, mat, tatualizadoeditar.isSelected());
                                if (resultado != 1) {
                                    Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, panelcor, lingua.translate("Erro"), 400, 200, lingua.translate("Erro ao realizar o update" + "."), new String[]{lingua.translate("Voltar")});
                                    mensagem.showMessage();
                                } else {
                                    btoogleditar.setSelected(true);
                                    tatualizadoeditar.setSelected(false);
                                    tatualizadoeditar.setEnabled(false);
                                    bteditar.setEnabled(false);
                                    btapagar.setEnabled(false);
                                }
                                tdescricaoeditar.setText(velho.getName());
                                tversaoeditar.setText(velho.getVersion());
                                tanoeditar.setText(velho.getYear());
                                tempresaeditar.setText(velho.getInterprise());
                                tatualizadoeditar.setSelected(db.getStateOfSoftwareUpdated(velho, mat));
                                db.close();
                            }
                        }
                    });
                    bteditar.addActionListener(abteditar);
                    if (abtapagar != null) {
                        btapagar.removeActionListener(abtapagar);
                    }
                    abtapagar = (ActionEvent e) -> {
                        if (DataBase.DataBase.testConnection(url)) {
                            if ((tabela.getSelectedRow() > -1) && (!btoogleditar.isSelected())) {
                                DataBase.DataBase db = new DataBase.DataBase(url);
                                int val = tabela.getSelectedRow();
                                Keys.Software velho = lsoft.get(val);
                                db.deleteAssociationBetweenSoftwareAndMaterial(velho, mat);
                                if (!db.isSoftwareAssociatedWithMaterial(velho, mat)) {
                                    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                                    modelo.removeRow(val);
                                    tabela.clearSelection();
                                    Rectangle r;
                                    int altura = 0;
                                    tabela.setPreferredSize(new Dimension((int)tabela.getPreferredSize().getWidth(), 0));
                                    for (int x = 0; x < modelo.getRowCount(); x++) {
                                        altura += tabela.getRowHeight();
                                        tabela.setPreferredSize(new Dimension((int) tabela.getPreferredSize().getWidth(), altura));
                                        
                                    }
                                    lsoft.remove(val);
                                    btoogleditar.setSelected(false);
                                    tdescricaoeditar.setText(lingua.translate("Nome do programa"));
                                    tdescricaoeditar.setBackground(new Color(245, 245, 245));
                                    tdescricaoeditar.setForeground(new Color(142, 143, 145));
                                    tversaoeditar.setText(lingua.translate("Versão de software"));
                                    tversaoeditar.setBackground(new Color(245, 245, 245));
                                    tversaoeditar.setForeground(new Color(142, 143, 145));
                                    tempresaeditar.setText(lingua.translate("Empresa proprietária"));
                                    tempresaeditar.setBackground(new Color(245, 245, 245));
                                    tempresaeditar.setForeground(new Color(142, 143, 145));
                                    tanoeditar.setText(lingua.translate("Ano de lançamento"));
                                    tanoeditar.setBackground(new Color(245, 245, 245));
                                    tanoeditar.setForeground(new Color(142, 143, 145));
                                    tatualizadoeditar.setSelected(false);
                                    tatualizadoeditar.setEnabled(false);
                                    bteditar.setEnabled(false);
                                    btapagar.setEnabled(false);
                                    if (comboboxdireitacima.getSelectedIndex() > 0) {
                                        if (software.get(comboboxdireitacima.getSelectedIndex() - 1).compareTo(velho) == 0) {
                                            btmais.setEnabled(true);
                                        }
                                    }
                                }
                                db.close();
                            }
                        }
                    };
                    btapagar.addActionListener(abtapagar);

                    //btoogle
                    try {
                        if (Clavis.KeyQuest.class.getResource("Images/lock.png") != null) {
                            BufferedImage imo = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lock.png"));
                            icoadd = new ImageIcon(imo);
                        } else {
                            icoadd = null;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (icoadd != null) {
                        btoogleditar.setIcon(icoadd);
                    } else {
                        btoogleditar.setText(lingua.translate("Bloqueado"));
                    }
                    btoogleditar.setIcon(icoadd);
                    btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                    btoogleditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btoogleditar.setPreferredSize(new Dimension(32, 30));
                    btoogleditar.setBounds(20, 10, 34, 30);
                    btoogleditar.removeActionListener(abteditar);

                    if (abtoogle != null) {
                        btoogleditar.removeActionListener(abtoogle);
                    }
                    abtoogle = ((ActionEvent e) -> {
                        if ((tabela.getSelectedRow() > -1) && (!btoogleditar.isSelected())) {
                            tatualizadoeditar.setEnabled(true);
                            bteditar.setEnabled(true);
                            btapagar.setEnabled(true);
                            btoogleditar.setToolTipText(lingua.translate("Bloquear"));
                        } else if (btoogleditar.isSelected()) {
                            btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                            Keys.Software soft = lsoft.get(tabela.getSelectedRow());
                            tdescricaoeditar.setText(soft.getName());
                            tversaoeditar.setText(soft.getVersion());
                            String ano = soft.getYear();
                            if (ano.equals("sem")) {
                                ano = "";
                            }
                            String empresa = soft.getInterprise();
                            if (empresa.equals("sem")) {
                                empresa = "";
                            }
                            tanoeditar.setText(ano);
                            tempresaeditar.setText(empresa);
                            tatualizadoeditar.setEnabled(false);
                            if (DataBase.DataBase.testConnection(url)) {
                                DataBase.DataBase db = new DataBase.DataBase(url);
                                tatualizadoeditar.setSelected(db.getStateOfSoftwareUpdated(soft, mat));
                                db.close();
                            }
                            bteditar.setEnabled(false);
                            btapagar.setEnabled(false);
                        }
                    });
                    btoogleditar.addActionListener(abtoogle);
                    panel.add(btoogleditar);
                    // fim btoogle

                    tdescricaoeditar.setText(lingua.translate("Nome do programa"));
                    tdescricaoeditar.setForeground(new Color(142, 143, 145));
                    tdescricaoeditar.setBackground(new Color(245, 245, 245));
                    tdescricaoeditar.setOpaque(true);
                    tdescricaoeditar.setToolTipText(lingua.translate("Nome do programa"));
                    tdescricaoeditar.setPreferredSize(new Dimension(200, 28));
                    tdescricaoeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tdescricaoeditar.setBounds(25, 55, 238, 28);
                    panel.add(tdescricaoeditar);
                    tversaoeditar.setText(lingua.translate("Versão de software"));
                    tversaoeditar.setBackground(new Color(245, 245, 245));
                    tversaoeditar.setOpaque(true);
                    tversaoeditar.setForeground(new Color(142, 143, 145));
                    tversaoeditar.setToolTipText(lingua.translate("Versão de software"));
                    tversaoeditar.setPreferredSize(new Dimension(200, 28));
                    tversaoeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tversaoeditar.setBounds(25, 85, 238, 28);
                    panel.add(tversaoeditar);
                    tempresaeditar.setText(lingua.translate("Empresa proprietária"));
                    tempresaeditar.setForeground(new Color(142, 143, 145));
                    tempresaeditar.setBackground(new Color(245, 245, 245));
                    tempresaeditar.setOpaque(true);
                    tempresaeditar.setToolTipText(lingua.translate("Empresa proprietária"));
                    tempresaeditar.setPreferredSize(new Dimension(200, 28));
                    tempresaeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tempresaeditar.setBounds(25, 115, 238, 28);
                    panel.add(tempresaeditar);
                    tanoeditar.setText(lingua.translate("Ano de lançamento"));
                    tanoeditar.setOpaque(true);
                    tanoeditar.setForeground(new Color(142, 143, 145));
                    tanoeditar.setBackground(new Color(245, 245, 245));
                    tanoeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tanoeditar.setToolTipText(lingua.translate("Ano de lançamento"));
                    tanoeditar.setPreferredSize(new Dimension(200, 28));
                    tanoeditar.setBounds(25, 145, 238, 28);
                    panel.add(tanoeditar);

                    //
                    if (tabela.getSelectedRow() > -1) {
                        tatualizadoeditar.setEnabled(true);
                    } else {
                        tatualizadoeditar.setEnabled(false);
                    }
                    tatualizadoeditar.setText(lingua.translate("Atualizado") + ":");
                    tatualizadoeditar.setHorizontalTextPosition(SwingConstants.LEFT);
                    tatualizadoeditar.setHorizontalAlignment(SwingConstants.RIGHT);
                    tatualizadoeditar.setPreferredSize(new Dimension(200, 28));
                    tatualizadoeditar.setBounds(25, 175, 235, 28);
                    panel.add(tatualizadoeditar);

                }
                break;
            case 2:
                if (ldis != null) {
                    if (abtapagar != null) {
                        btapagar.removeActionListener(abtapagar);
                    }
                    abtapagar = (ActionEvent e) -> {
                        if (DataBase.DataBase.testConnection(url)) {
                            if ((tabela.getSelectedRow() > -1) && (!btoogleditar.isSelected())) {
                                DataBase.DataBase db = new DataBase.DataBase(url);
                                int val = tabela.getSelectedRow();
                                Keys.Subject velho = ldis.get(val);
                                db.deleteAssociationBetweenSubjectAndClassroom(velho, mat);
                                if (!db.isSubjectAssociatedWithClassroom(velho, mat)) {
                                    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                                    modelo.removeRow(val);
                                    tabela.clearSelection();
                                    Rectangle r;
                                    int altura = 0;
                                    tabela.setPreferredSize(new Dimension((int)tabela.getPreferredSize().getWidth(), 0));
                                    for (int x = 0; x < modelo.getRowCount(); x++) {
                                        altura += tabela.getRowHeight();
                                        tabela.setPreferredSize(new Dimension((int) tabela.getPreferredSize().getWidth(), altura));
                                        
                                    }
                                    ldis.remove(val);
                                    btoogleditar.setSelected(true);
                                    labeldisciplina.setText(lingua.translate("Nome da disciplina"));
                                    labeldisciplina.setBackground(new Color(245, 245, 245));
                                    labeldisciplina.setForeground(new Color(142, 143, 145));
                                    labelcodigodisciplina.setText(lingua.translate("Código da disciplina"));
                                    labelcodigodisciplina.setBackground(new Color(245, 245, 245));
                                    labelcodigodisciplina.setForeground(new Color(142, 143, 145));
                                    btapagar.setEnabled(false);
                                    if (comboboxdireitacima.getSelectedIndex() > 0) {
                                        if (disciplinas.get(comboboxdireitacima.getSelectedIndex() - 1).compareTo(velho) == 0) {
                                            btmais.setEnabled(true);
                                        }
                                    }
                                }
                                db.close();
                            }
                        }
                    };
                    btapagar.addActionListener(abtapagar);

                    //btoogle
                    try {
                        if (Clavis.KeyQuest.class.getResource("Images/lock.png") != null) {
                            BufferedImage imo = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lock.png"));
                            icoadd = new ImageIcon(imo);
                        } else {
                            icoadd = null;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (icoadd != null) {
                        btoogleditar.setIcon(icoadd);
                    } else {
                        btoogleditar.setText(lingua.translate("Bloqueado"));
                    }
                    btoogleditar.setIcon(icoadd);
                    btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                    btoogleditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btoogleditar.setPreferredSize(new Dimension(32, 30));
                    btoogleditar.setBounds(20, 10, 34, 30);
                    btoogleditar.removeActionListener(abteditar);

                    if (abtoogle != null) {
                        btoogleditar.removeActionListener(abtoogle);
                    }
                    abtoogle = ((ActionEvent e) -> {
                        if ((tabela.getSelectedRow() > -1) && (!btoogleditar.isSelected())) {
                            btapagar.setEnabled(true);
                            btoogleditar.setToolTipText(lingua.translate("Bloquear"));
                        } else if (btoogleditar.isSelected()) {
                            btapagar.setEnabled(false);
                            btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                        }
                    });
                    btoogleditar.addActionListener(abtoogle);
                    panel.add(btoogleditar);
                    // fim btoogle

                    labeldisciplina.setToolTipText(lingua.translate("Nome da disciplina"));
                    labeldisciplina.setText(lingua.translate("Nome da disciplina"));
                    labeldisciplina.setPreferredSize(new Dimension(200, 28));
                    labeldisciplina.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    labeldisciplina.setBounds(25, 85, 238, 28);
                    labeldisciplina.setBackground(new Color(245, 245, 245));
                    labeldisciplina.setForeground(new Color(142, 143, 145));
                    labeldisciplina.setOpaque(true);
                    panel.add(labeldisciplina);

                    labelcodigodisciplina.setToolTipText(lingua.translate("Código da disciplina"));
                    labelcodigodisciplina.setText(lingua.translate("Código da disciplina"));
                    labelcodigodisciplina.setPreferredSize(new Dimension(200, 28));
                    labelcodigodisciplina.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    labelcodigodisciplina.setBounds(25, 115, 238, 28);
                    labelcodigodisciplina.setBackground(new Color(245, 245, 245));
                    labelcodigodisciplina.setForeground(new Color(142, 143, 145));
                    labelcodigodisciplina.setOpaque(true);
                    panel.add(labelcodigodisciplina);
                }
                break;
            default:
                if (lfeat != null) {
                    //bt editar
                    bteditar = new javax.swing.JButton();
                    bteditar.setPreferredSize(new Dimension(90, 40));
                    bteditar.setBackground(new Color(51, 102, 153));
                    bteditar.setFocusPainted(false);
                    bteditar.setEnabled(false);
                    bteditar.setToolTipText(lingua.translate("Gravar novos valores"));
                    bteditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    try {
                        if (Clavis.KeyQuest.class.getResource("Images/edit.png") != null) {
                            BufferedImage imo = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/edit.png"));
                            icoadd = new ImageIcon(imo);
                        } else {
                            icoadd = null;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (icoadd != null) {
                        bteditar.setIcon(icoadd);
                    } else {
                        bteditar.setText(lingua.translate("Editar"));
                    }
                    bteditar.setBounds((int) (panel.getPreferredSize().getWidth() - 225), 7, 90, 40);
                    panel.add(bteditar);
                    if (abteditar != null) {
                        bteditar.removeActionListener(abteditar);
                    }
                    abteditar = ((ActionEvent e) -> {
                        if (DataBase.DataBase.testConnection(url)) {
                            if ((tabela.getSelectedRow() > -1) && (!btoogleditar.isSelected())) {
                                DataBase.DataBase db = new DataBase.DataBase(url);
                                String n = tdescricaoeditar.getText();
                                if (!n.equals("")) {
                                    String m = tversaoeditar.getText();
                                    Keys.Feature velho = new Keys.Feature(lfeat.get(tabela.getSelectedRow()));
                                    Keys.Feature novo = new Keys.Feature(velho);
                                    novo.setUnityMeasure(m);
                                    int v;
                                    try {
                                        v = Integer.parseInt(tquantidadeeditar.getValue().toString());
                                    } catch (NumberFormatException exc) {
                                        v = 0;
                                    }
                                    novo.setNumber(v);
                                    int resultado = db.updateFeatureWithAssociation(velho, novo, mat);
                                    if (resultado != 1) {
                                        Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, panelcor, lingua.translate("Erro"), 400, 200, lingua.translate("Erro ao realizar o update") + ".", new String[]{lingua.translate("Voltar")});
                                        mensagem.showMessage();
                                        tdescricaoeditar.setText(novo.getDescription());
                                        tversaoeditar.setText(novo.getUnityMeasure());
                                        tquantidadeeditar.setValue(novo.getNumber());
                                    } else {
                                        btoogleditar.setSelected(true);
                                        tquantidadeeditar.setEnabled(false);
                                        bteditar.setEnabled(false);
                                        btapagar.setEnabled(false);
                                        if (db.getFeatureId(novo) > 0) {
                                            lfeat.set(tabela.getSelectedRow(), novo);
                                            String auxiliar = lingua.translate(novo.getDescription());
                                            if (novo.getNumber() != 0) {
                                                auxiliar = auxiliar + " (" + novo.getNumber() + " " + novo.getUnityMeasure() + ")";
                                            }
                                            tabela.getModel().setValueAt(auxiliar, tabela.getSelectedRow(), 0);
                                        }
                                    }
                                    db.close();
                                }
                            }
                        }
                    });
                    bteditar.addActionListener(abteditar);

                    if (abtapagar != null) {
                        btapagar.removeActionListener(abtapagar);
                    }
                    abtapagar = (ActionEvent e) -> {
                        if (DataBase.DataBase.testConnection(url)) {
                            if ((tabela.getSelectedRow() > -1) && (!btoogleditar.isSelected())) {
                                DataBase.DataBase db = new DataBase.DataBase(url);
                                int val = tabela.getSelectedRow();
                                Keys.Feature velho = lfeat.get(val);
                                db.deleteAssociationFeatureWithMaterial(velho, mat);
                                if (!db.isFeatureAssociatedWithMaterial(velho, mat)) {
                                    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                                    modelo.removeRow(val);
                                    tabela.clearSelection();
                                    Rectangle r;
                                    int altura = 0;
                                    tabela.setPreferredSize(new Dimension((int)tabela.getPreferredSize().getWidth(), 0));
                                    for (int x = 0; x < modelo.getRowCount(); x++) {
                                        altura += tabela.getRowHeight();
                                        tabela.setPreferredSize(new Dimension((int) tabela.getPreferredSize().getWidth(), altura));
                                        
                                    }
                                    lfeat.remove(val);
                                    btoogleditar.setSelected(true);
                                    btoogleditar.setEnabled(false);
                                    tdescricaoeditar.setText(lingua.translate("Nome da caraterística"));
                                    tversaoeditar.setText(lingua.translate("Medida de valor"));
                                    tquantidadeeditar.setValue(0);
                                    tquantidadeeditar.setEnabled(false);
                                    bteditar.setEnabled(false);
                                    btapagar.setEnabled(false);
                                    if (comboboxdireitacima.getSelectedIndex() > 0) {
                                        if (features.get(comboboxdireitacima.getSelectedIndex() - 1).compareTo(velho) == 0) {
                                            btmais.setEnabled(true);
                                        }
                                    }
                                }
                                db.close();
                            }
                        }
                    };
                    btapagar.addActionListener(abtapagar);

                    //btoogle
                    try {
                        if (Clavis.KeyQuest.class.getResource("Images/lock.png") != null) {
                            BufferedImage imo = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lock.png"));
                            icoadd = new ImageIcon(imo);
                        } else {
                            icoadd = null;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (icoadd != null) {
                        btoogleditar.setIcon(icoadd);
                    } else {
                        btoogleditar.setText(lingua.translate("on"));
                    }
                    btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                    btoogleditar.setIcon(icoadd);
                    btoogleditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btoogleditar.setPreferredSize(new Dimension(32, 30));
                    btoogleditar.setBounds(20, 10, 34, 30);
                    btoogleditar.removeActionListener(abteditar);

                    if (abtoogle != null) {
                        btoogleditar.removeActionListener(abtoogle);
                    }
                    abtoogle = ((ActionEvent e) -> {
                        if ((tabela.getSelectedRow() > -1) && (!btoogleditar.isSelected())) {
                            bteditar.setEnabled(true);
                            btapagar.setEnabled(true);
                            tquantidadeeditar.setEnabled(true);
                            btoogleditar.setToolTipText(lingua.translate("Bloquear"));
                        } else if (btoogleditar.isSelected()) {
                            btoogleditar.setToolTipText(lingua.translate("Desbloquear"));
                            Keys.Feature feat = lfeat.get(tabela.getSelectedRow());
                            tdescricaoeditar.setText(feat.getDescription());
                            tversaoeditar.setText(feat.getUnityMeasure());
                            tquantidadeeditar.setValue(feat.getNumber());
                            tquantidadeeditar.setEnabled(false);
                            bteditar.setEnabled(false);
                            btapagar.setEnabled(false);
                        }
                    });
                    btoogleditar.addActionListener(abtoogle);
                    panel.add(btoogleditar);
                    // fim btoogle

                    tdescricaoeditar.setText(lingua.translate("Nome da caraterística"));
                    tdescricaoeditar.setBackground(new Color(245, 245, 245));
                    tdescricaoeditar.setForeground(new Color(142, 143, 145));
                    tdescricaoeditar.setOpaque(true);
                    tdescricaoeditar.setToolTipText(lingua.translate("Nome da caraterística"));
                    tdescricaoeditar.setPreferredSize(new Dimension(200, 28));
                    tdescricaoeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tdescricaoeditar.setBounds(25, 85, 238, 28);
                    panel.add(tdescricaoeditar);
                    DataBase.DataBase db = new DataBase.DataBase(url);
                    tversaoeditar.setBackground(new Color(245, 245, 245));
                    tversaoeditar.setForeground(new Color(142, 143, 145));
                    tversaoeditar.setOpaque(true);
                    tversaoeditar.setText(lingua.translate("Medida de valor"));
                    tversaoeditar.setToolTipText(lingua.translate("Medida de valor"));
                    tversaoeditar.setPreferredSize(new Dimension(200, 28));
                    tversaoeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 2, 0.5f, 6, true, true, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tversaoeditar.setBounds(25, 115, 238, 28);
                    panel.add(tversaoeditar);

                    // spinner quantidade
                    javax.swing.JSpinner.NumberEditor editor = (javax.swing.JSpinner.NumberEditor) tquantidadeeditar.getEditor();
                    editor.getFormat().setGroupingUsed(false);
                    editor.getModel().setMaximum(10000000);
                    editor.getModel().setMinimum(0);
                    editor.getModel().setValue(0);
                    editor.getModel().setStepSize(1);
                    editor.getTextField().setSelectionColor(Color.DARK_GRAY);
                    tquantidadeeditar.setToolTipText(lingua.translate("Quantidade da associação"));
                    tquantidadeeditar.setPreferredSize(new Dimension(100, 28));
                    tquantidadeeditar.setBounds(115, 145, 148, 28);
                    tquantidadeeditar.setEnabled(false);
                    panel.add(tquantidadeeditar);

                }
                break;

        }
    }

}
