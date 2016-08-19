/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
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
    private javax.swing.JComboBox<String> comboboxdireitacima;
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
    private JPanel jpaneldireitabaixo;
    private Components.PersonalTextField tdescricaoeditar;
    private Components.PersonalTextField tversaoeditar;
    private Components.PersonalTextField tanoeditar;
    private Components.PersonalTextField tempresaeditar;
    private javax.swing.JCheckBox tatualizadoeditar;
    private Components.PersonalToggleButton btoogleditar;

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
        tdescricaoeditar = new Components.PersonalTextField();
        tversaoeditar = new Components.PersonalTextField();
        tanoeditar = new Components.PersonalTextField();
        tempresaeditar = new Components.PersonalTextField();
        tatualizadoeditar = new javax.swing.JCheckBox();
        btoogleditar = new Components.PersonalToggleButton();
        bteditar = new javax.swing.JButton();
        btapagar = new javax.swing.JButton();
    }

    public WMaterial(javax.swing.JDialog dialogo, Keys.Material mat, Color cor, Langs.Locale lingua, String url) {
        super(dialogo);
        this.dialogopai = dialogo;
        panelcor = cor;
        painelgeral = new JPanel();
        this.lingua = lingua;
        this.url = url;
        this.mat = mat;
        prefs = Preferences.userNodeForPackage(getClass());
        lfeat = new java.util.ArrayList<>();
        lsoft = new java.util.ArrayList<>();
        ldis = new java.util.ArrayList<>();
        tdescricaoeditar = new Components.PersonalTextField();
        tversaoeditar = new Components.PersonalTextField();
        tanoeditar = new Components.PersonalTextField();
        tempresaeditar = new Components.PersonalTextField();
        tatualizadoeditar = new javax.swing.JCheckBox();
        btoogleditar = new Components.PersonalToggleButton();
        bteditar = new javax.swing.JButton();
        btapagar = new javax.swing.JButton();
    }

    public void create() {

        this.setPreferredSize(new Dimension(700, 500));
        this.setResizable(false);
        this.setMinimumSize(new Dimension(700, 500));
        this.setMaximumSize(new Dimension(700, 500));
        this.setBackground(new Color(254, 254, 254));
        painelgeral.setPreferredSize(new Dimension(7000, 500));
        painelgeral.setBounds(0, 0, 700, 500);
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

        // combobox inicial
        javax.swing.JComboBox<String> comboboxopcoes;
        switch (mat.getTypeOfMaterial().getMaterialTypeID()) {
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
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{mat.getTypeOfMaterial().getTypeOfMaterialName() + " " + mat.getDescription()});
            jpanelesquerda.removeAll();
            painelgeral.repaint();
            jpanelesquerda.add(makeTable(comboboxopcoes, model, true, comboboxopcoes.getSelectedIndex()));
            prefs.put("material", mat.getCodeOfMaterial());
            prefs.putInt("combobox", comboboxopcoes.getSelectedIndex());
            this.updateComboBox(comboboxopcoes);
            drawcomponentsRight(comboboxopcoes, jpaneldireitabaixo);
        });

        sppinerquantidade = new javax.swing.JSpinner();
        sppinerquantidade.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)));
        sppinerquantidade.setBackground(new Color(213, 213, 213));
        javax.swing.SpinnerNumberModel sn = new javax.swing.SpinnerNumberModel(0, 0, 200, 1);
        sppinerquantidade.setModel(sn);
        checkatualizado = new javax.swing.JCheckBox();
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{mat.getTypeOfMaterial().getTypeOfMaterialName() + " " + mat.getDescription()});

        //paineis
        jpanelesquerda.removeAll();
        jpanelesquerda.setBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true));
        jpanelesquerda.add(this.makeTable(comboboxopcoes, model, true, comboboxopcoes.getSelectedIndex()));

        JPanel jpaneltituloesquerda = new javax.swing.JPanel();
        jpaneltituloesquerda.setPreferredSize(new java.awt.Dimension(315, 40));
        jpaneltituloesquerda.add(comboboxopcoes);

        JPanel jpaneltitulodireita = new javax.swing.JPanel(null);
        jpaneltitulodireita.setPreferredSize(new java.awt.Dimension(284, 40));
        javax.swing.JLabel labeltitulodireita = new javax.swing.JLabel(lingua.translate("Editar"));
        labeltitulodireita.setPreferredSize(new Dimension(285, 40));
        labeltitulodireita.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labeltitulodireita.setBounds(0, 0, 284, 40);
        labeltitulodireita.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labeltitulodireita.setBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, false));
        jpaneltitulodireita.add(labeltitulodireita);
        // adicionar carateristicas e software
        JPanel jpaneldireitacima = new javax.swing.JPanel(null);
        jpaneldireitacima.setPreferredSize(new java.awt.Dimension(284, 137));
        jpaneldireitacima.setBackground(new Color(250, 255, 225));

        javax.swing.JButton btmais = new javax.swing.JButton();
        ImageIcon icoadd = null;
        try {
            BufferedImage imo = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/plus.png"));
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
                            db.close();
                            jpanelesquerda.removeAll();
                            jpanelesquerda.add(makeTable(comboboxopcoes, model2, true, comboboxopcoes.getSelectedIndex()));
                        }
                    }
                    break;
                case 2:
                    if (disciplinas != null) {
                        Keys.Subject subs = disciplinas.get(comboboxdireitacima.getSelectedIndex() - 1);
                        if (DataBase.DataBase.testConnection(url)) {
                            DataBase.DataBase db = new DataBase.DataBase(url);
                            db.associateClassroomWithSubject(mat, subs);
                            db.close();
                            jpanelesquerda.removeAll();
                            jpanelesquerda.add(makeTable(comboboxopcoes, model2, true, comboboxopcoes.getSelectedIndex()));
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
                            db.close();
                            jpanelesquerda.removeAll();
                            jpanelesquerda.add(makeTable(comboboxopcoes, model2, true, comboboxopcoes.getSelectedIndex()));
                        }
                    }
                    break;
            }

        });
        jpaneldireitacima.add(btmais);
        comboboxdireitacima = new javax.swing.JComboBox<>();
        comboboxdireitacima.setEditable(true);
        comboboxdireitacima.setEnabled(true);
        comboboxdireitacima.setAutoscrolls(true);
        comboboxdireitacima.getEditor().getEditorComponent().setForeground(new Color(205, 205, 205));
        javax.swing.JTextField jtexto = (javax.swing.JTextField) comboboxdireitacima.getEditor().getEditorComponent();
        jtexto.setSelectionColor(Color.DARK_GRAY);
        this.updateComboBox(comboboxopcoes);

        comboboxdireitacima.setPreferredSize(new Dimension(250, 28));
        comboboxdireitacima.setBounds(35, 60, 228, 28);
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
                if (comboboxdireitacima.getSelectedIndex() != 0) {
                    String va = fil.getText();
                    aux = comboboxdireitacima.getSelectedIndex();
                    DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) comboboxdireitacima.getModel();
                    for (int i = 1; i <= modelo.getSize(); i++) {
                        if (modelo.getElementAt(i) != null) {
                            if ((modelo.getElementAt(i).toString().toLowerCase().matches("(.*)" + va.toLowerCase() + "(.*)")) && (!va.equals(""))) {
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
                    fil.setText(va);
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
        list.setCellRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
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
        ImageIcon ico = null;

        try {
            BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/outro.png"));
            ico = new ImageIcon(im);
        } catch (IOException ex) {
            Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ico
                != null) {
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
                        ldescricao.setPreferredSize(new Dimension(100, 32));
                        ldescricao.setFocusable(true);
                        ldescricao.requestFocus();
                        ldescricao.setBounds(20, 0, 100, 32);
                        panel.add(ldescricao);

                        Components.PersonalTextField tdescricao = new Components.PersonalTextField();
                        tdescricao.setPreferredSize(new Dimension(240, 30));
                        tdescricao.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tdescricao.setBounds(120, 0, 240, 30);
                        panel.add(tdescricao);
                        javax.swing.JLabel lmedida = new javax.swing.JLabel(lingua.translate("Medida") + ": ");
                        lmedida.setPreferredSize(new Dimension(100, 30));
                        lmedida.setBounds(20, 40, 100, 30);
                        panel.add(lmedida);

                        Components.PersonalTextField tmedida = new Components.PersonalTextField();
                        tmedida.setPreferredSize(new Dimension(240, 30));
                        tmedida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tmedida.setBounds(120, 40, 240, 30);
                        panel.add(tmedida);

                        String[] btstexto = {lingua.translate("Adicionar"), lingua.translate("Voltar")};
                        tdescricao.addPlaceHolder("Descrição da caraterística", null);
                        tdescricao.setSelectionColor(Color.DARK_GRAY);
                        tmedida.addPlaceHolder("Medida de valor", null);
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
                        ldescricao.setPreferredSize(new Dimension(100, 32));
                        ldescricao.setFocusable(true);
                        ldescricao.requestFocus();
                        ldescricao.setBounds(30, 0, 100, 30);
                        panel.add(ldescricao);

                        Components.PersonalTextField tdescricao = new Components.PersonalTextField();
                        tdescricao.setPreferredSize(new Dimension(240, 32));
                        tdescricao.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tdescricao.setBounds(100, 0, 240, 30);
                        panel.add(tdescricao);

                        javax.swing.JLabel lmedida = new javax.swing.JLabel(lingua.translate("Versão") + ": ");
                        lmedida.setPreferredSize(new Dimension(100, 32));
                        lmedida.setBounds(30, 40, 100, 30);
                        panel.add(lmedida);

                        Components.PersonalTextField tmedida = new Components.PersonalTextField();
                        tmedida.setPreferredSize(new Dimension(240, 32));
                        tmedida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tmedida.setBounds(100, 40, 240, 30);
                        panel.add(tmedida);

                        javax.swing.JLabel lempresa = new javax.swing.JLabel(lingua.translate("Empresa") + ": ");
                        lempresa.setPreferredSize(new Dimension(100, 32));
                        lempresa.setBounds(30, 80, 100, 30);
                        panel.add(lempresa);

                        Components.PersonalTextField tempresa = new Components.PersonalTextField();
                        tempresa.setPreferredSize(new Dimension(240, 32));
                        tempresa.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                        tempresa.setBounds(100, 80, 240, 30);
                        panel.add(tempresa);

                        javax.swing.JLabel lano = new javax.swing.JLabel(lingua.translate("Ano") + ": ");
                        lano.setPreferredSize(new Dimension(120, 32));
                        lano.setBounds(30, 120, 100, 30);
                        panel.add(lano);

                        javax.swing.JSpinner sano = new javax.swing.JSpinner();
                        int decim = new TimeDate.Date().getYear();
                        javax.swing.JSpinner.NumberEditor editor = (javax.swing.JSpinner.NumberEditor) sano.getEditor();
                        editor.getFormat().setGroupingUsed(false);
                        editor.getModel().setMaximum(decim);
                        editor.getModel().setMinimum(1900);
                        editor.getModel().setValue(decim);
                        editor.getModel().setStepSize(1);
                        sano.setPreferredSize(new Dimension(100, 30));
                        sano.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)));
                        sano.setBounds(100, 120, 100, 30);
                        panel.add(sano);
                        sano.setToolTipText(lingua.translate("Ano de lançamento"));
                        String[] btstexto = {lingua.translate("Adicionar"), lingua.translate("Voltar")};
                        tdescricao.addPlaceHolder(lingua.translate("Nome do programa"), null);
                        tdescricao.setSelectionColor(Color.DARK_GRAY);
                        tmedida.addPlaceHolder(lingua.translate("Versão de software"), null);
                        tempresa.addPlaceHolder(lingua.translate("Empresa proprietária"), null);
                        Components.MessagePane pop = new Components.MessagePane(this, Components.MessagePane.ACAO, panelcor, lingua.translate("Adicionar outra caraterística"), 400, 340, panel, "", btstexto);
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

        JPanel jpanelbaixo = new javax.swing.JPanel();

        jpanelbaixo.setBackground(Color.ORANGE);

        //jpanel2 = jpanelesquerda
        //jpanel4 = jpaneldireitacima azul
        //jpanel7 = jpaneldireitabaixo rosa
        //jpanel5 = jpaneltituloesquerda
        //jpanel6 = jpaneltitulodireita verde
        //jpanel8 = jpanelbaixo laranja
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
                                if (disciplinas.get(comboboxdireitacima.getSelectedIndex() - 1).compareTo(sub) == 0) {
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
                    } else if (comboboxdireitacima.getSelectedIndex() <= 0) {
                        btmais.setEnabled(false);
                    }
                    break;
            }
            System.out.println(comboboxdireitacima.getSelectedIndex());
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
            DataBase.DataBase db = new DataBase.DataBase(url);
            tabela.setSelectionBackground(Color.DARK_GRAY);
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            String auxiliar;
            switch (mat.getTypeOfMaterial().getMaterialTypeID()) {
                case 1:
                    switch (valor) {
                        case 1:
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
                                auxiliar = dis.getName();
                                modelo.addRow(new String[]{auxiliar});
                                size += tabela.getRowHeight();
                            }
                            break;
                        default:
                            lfeat = db.getFeaturesByMaterial(mat);
                            for (Keys.Feature fea : lfeat) {
                                auxiliar = fea.getDescription();
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
                                auxiliar = fea.getDescription();
                                if (fea.getNumber() != 0) {
                                    auxiliar += auxiliar + " " + fea.getNumber() + " (" + fea.getUnityMeasure() + ")";
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
                        auxiliar = fea.getDescription();
                        if (fea.getNumber() != 0) {
                            auxiliar += auxiliar + " " + fea.getNumber() + " (" + fea.getUnityMeasure() + ")";
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
            headerRenderer.setPreferredSize(new Dimension(100, 30));
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
                    tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
                        if (tabela.getSelectedRow() > -1) {
                            Keys.Software soft = lsoft.get(tabela.getSelectedRow());
                            tdescricaoeditar.setText(soft.getName());
                            tdescricaoeditar.stopPlaceHolder();
                            tversaoeditar.setText(soft.getVersion());
                            tversaoeditar.stopPlaceHolder();
                            tanoeditar.setText(soft.getYear());
                            tanoeditar.stopPlaceHolder();
                            tempresaeditar.setText(soft.getInterprise());
                            tempresaeditar.stopPlaceHolder();
                            btoogleditar.setEnabled(true);
                            if (DataBase.DataBase.testConnection(url)) {
                                DataBase.DataBase dab = new DataBase.DataBase(url);
                                boolean v = dab.getStateOfSoftwareUpdated(soft, mat);
                                if (v) {
                                    tatualizadoeditar.setSelected(false);
                                } else {
                                    tatualizadoeditar.setSelected(true);
                                }
                                dab.close();
                            }
                        } else {
                            tdescricaoeditar.setEnabled(false);
                            tdescricaoeditar.showPLaceHolder();
                            tversaoeditar.setEnabled(false);
                            tversaoeditar.showPLaceHolder();
                            tanoeditar.setEnabled(false);
                            tanoeditar.showPLaceHolder();
                            tempresaeditar.setEnabled(false);
                            tempresaeditar.showPLaceHolder();
                            tatualizadoeditar.setEnabled(false);
                            tatualizadoeditar.setSelected(false);
                            btoogleditar.setSelected(true);
                            btoogleditar.setEnabled(false);
                        }
                    });
                case 2:

                default:

            }

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
                        tdescricaoeditar.startPlaceHolder();
                        tversaoeditar.startPlaceHolder();
                        tanoeditar.startPlaceHolder();
                        tempresaeditar.startPlaceHolder();
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
            switch (val) {
                case 1:
                    software = db.getSoftwareList();
                    valores = new String[software.size() + 1];
                    int j = 1;
                    valores[0] = lingua.translate("Selecionar software") + " ...";
                    while (j < software.size() + 1) {
                        valores[j] = software.get(j - 1).getName();
                        j++;
                    }
                    comboboxdireitacima.setModel(new javax.swing.DefaultComboBoxModel<>(valores));
                    break;
                case 2:
                    disciplinas = db.getAllSubjects();
                    valores = new String[disciplinas.size() + 1];
                    int l = 1;
                    valores[0] = lingua.translate("Escolher disciplina") + " ...";
                    while (l < disciplinas.size() + 1) {
                        valores[l] = disciplinas.get(l - 1).getName();
                        l++;
                    }
                    comboboxdireitacima.setModel(new javax.swing.DefaultComboBoxModel<>(valores));
                    break;
                default:
                    features = db.getFeaturesByTypeOfMaterial(mat.getTypeOfMaterial());
                    valores = new String[features.size() + 1];
                    int i = 1;
                    valores[0] = lingua.translate("Escolher caraterística") + " ...";
                    while (i < features.size() + 1) {
                        valores[i] = features.get(i - 1).getDescription();
                        i++;
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
                System.out.println(panel.getPreferredSize().getWidth() - checkatualizado.getPreferredSize().getWidth());
                checkatualizado.setBounds((int) (panel.getPreferredSize().getWidth() - checkatualizado.getPreferredSize().getWidth()) - 18, 0, (int) checkatualizado.getPreferredSize().getWidth(), 28);
                panel.add(checkatualizado);
                break;
            case 2:
                break;
            default:
                javax.swing.JLabel label = new javax.swing.JLabel(lingua.translate("Quantidade") + ": ");
                label.setPreferredSize(new Dimension(135, 26));
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setBounds(10, 0, 135, 26);
                sppinerquantidade.setPreferredSize(new Dimension(110, 30));
                sppinerquantidade.setBounds(149, 0, 104, 32);
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
                        bteditar.setText(lingua.translate("editar"));
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
                                String v = tversaoeditar.getText();
                                String a = tanoeditar.getText();
                                String em = tempresaeditar.getText();
                                boolean at = tatualizadoeditar.isSelected();
                                Keys.Software novo = new Keys.Software(n, v, a, em);
                                Keys.Software velho = lsoft.get(tabela.getSelectedRow());
                                System.out.println(velho.getName());
                                System.out.println(novo.getYear());
                                System.out.println(novo.getInterprise());
                                System.out.println(novo.getVersion());
                                System.out.println(novo.getName());
                                /*db.updateSofware(velho, novo, mat);
                                db.updateStateOfSoftware(novo, mat, at);
                                db.close();
                                btoogleditar.setSelected(true);
                                tdescricaoeditar.setEnabled(false);
                                tdescricaoeditar.setForeground(Components.PersonalTextField.DEFAULT_PLACEHOLDER_COLOR);
                                tversaoeditar.setEnabled(false);
                                tversaoeditar.setForeground(Components.PersonalTextField.DEFAULT_PLACEHOLDER_COLOR);
                                tanoeditar.setEnabled(false);
                                tanoeditar.setForeground(Components.PersonalTextField.DEFAULT_PLACEHOLDER_COLOR);
                                tempresaeditar.setEnabled(false);
                                tempresaeditar.setForeground(Components.PersonalTextField.DEFAULT_PLACEHOLDER_COLOR);
                                tatualizadoeditar.setEnabled(false);
                                bteditar.setEnabled(false);*/
                                
                            }
                        }
                    });
                    bteditar.addActionListener(abteditar);

                    btapagar.addActionListener((ActionEvent e) -> {

                    });

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
                            tdescricaoeditar.setEnabled(true);
                            tdescricaoeditar.setForeground(Color.BLACK);
                            tversaoeditar.setEnabled(true);
                            tversaoeditar.setForeground(Color.BLACK);
                            tanoeditar.setEnabled(true);
                            tanoeditar.setForeground(Color.BLACK);
                            tempresaeditar.setEnabled(true);
                            tempresaeditar.setForeground(Color.BLACK);
                            tatualizadoeditar.setEnabled(true);
                            bteditar.setEnabled(true);
                            btapagar.setEnabled(true);
                        } else if (btoogleditar.isSelected()) {
                            tdescricaoeditar.setEnabled(false);
                            tdescricaoeditar.setForeground(Components.PersonalTextField.DEFAULT_PLACEHOLDER_COLOR);
                            tversaoeditar.setEnabled(false);
                            tversaoeditar.setForeground(Components.PersonalTextField.DEFAULT_PLACEHOLDER_COLOR);
                            tanoeditar.setEnabled(false);
                            tanoeditar.setForeground(Components.PersonalTextField.DEFAULT_PLACEHOLDER_COLOR);
                            tempresaeditar.setEnabled(false);
                            tempresaeditar.setForeground(Components.PersonalTextField.DEFAULT_PLACEHOLDER_COLOR);
                            tatualizadoeditar.setEnabled(false);
                            bteditar.setEnabled(false);
                            btapagar.setEnabled(false);
                        }
                    });
                    btoogleditar.addActionListener(abtoogle);
                    panel.add(btoogleditar);
                    // fim btoogle

                    tdescricaoeditar.addPlaceHolder(lingua.translate("Nome do programa"), null);
                    tdescricaoeditar.setPreferredSize(new Dimension(200, 26));
                    tdescricaoeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tdescricaoeditar.setBounds(25, 55, 238, 26);
                    panel.add(tdescricaoeditar);

                    tversaoeditar.addPlaceHolder(lingua.translate("Versão de software"), null);
                    tversaoeditar.setPreferredSize(new Dimension(200, 26));
                    tversaoeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tversaoeditar.setBounds(25, 85, 238, 26);
                    panel.add(tversaoeditar);

                    tempresaeditar.addPlaceHolder(lingua.translate("Empresa poprietária"), null);
                    tempresaeditar.setPreferredSize(new Dimension(200, 26));
                    tempresaeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tempresaeditar.setBounds(25, 115, 238, 26);
                    panel.add(tempresaeditar);

                    tanoeditar.addPlaceHolder(lingua.translate("Ano de lançamento"), null);
                    tanoeditar.setPreferredSize(new Dimension(200, 26));
                    tanoeditar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                    tanoeditar.setBounds(25, 145, 238, 26);
                    panel.add(tanoeditar);
                    if (tabela.getSelectedRow() > -1) {
                        tdescricaoeditar.setEnabled(true);
                        tversaoeditar.setEnabled(true);
                        tanoeditar.setEnabled(true);
                        tempresaeditar.setEnabled(true);
                        tatualizadoeditar.setEnabled(true);
                    } else {
                        tdescricaoeditar.setEnabled(false);
                        tversaoeditar.setEnabled(false);
                        tanoeditar.setEnabled(false);
                        tempresaeditar.setEnabled(false);
                        tatualizadoeditar.setEnabled(false);
                    }
                    tatualizadoeditar.setText(lingua.translate("Atualizado") + ":");
                    tatualizadoeditar.setHorizontalTextPosition(SwingConstants.LEFT);
                    tatualizadoeditar.setHorizontalAlignment(SwingConstants.RIGHT);
                    tatualizadoeditar.setPreferredSize(new Dimension(200, 26));
                    tatualizadoeditar.setBounds(25, 175, 235, 26);
                    panel.add(tatualizadoeditar);

                }
                break;
            case 2:
                //bt.setBounds(0,(int)panel.getPreferredSize().getHeight() -45,90,40);
                //panel.add(bt);
                break;
            default:

                break;

        }
    }

}
