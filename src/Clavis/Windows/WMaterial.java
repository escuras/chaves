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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.scene.Cursor;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
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
    private Langs.Locale lingua;
    private String url;
    private Keys.Material mat;
    private Preferences prefs;
    private org.jdesktop.swingx.JXTable tabela;
    private javax.swing.JComboBox<String> comboboxdireitacima;
    private java.util.List<Keys.Feature> features;
    private java.util.List<Keys.Software> software;
    private java.util.List<Keys.Subject> disciplinas;
    private Components.PersonalButton btdireitacimamais;
    private String[] valores;

    public WMaterial() {
        super();
        panelcor = DEFAULT_PANEL_COLOR;
        this.dialogopai = null;
        painelgeral = new JPanel();
        lingua = new Langs.Locale();
        mat = new Keys.Material();
        prefs = Preferences.userNodeForPackage(getClass());
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

        JPanel jpanelesquerda = new javax.swing.JPanel();
        jpanelesquerda.setBackground(painelgeral.getBackground());
        java.awt.GridLayout gl = new java.awt.GridLayout();
        jpanelesquerda.setLayout(gl);

        // combobox inicial
        javax.swing.JComboBox<String> comboboxopcoes = new javax.swing.JComboBox<>(new String[]{lingua.translate("Caraterísticas"), lingua.translate("Software em computadores"), lingua.translate("Disciplinas relacionadas")});
        ((javax.swing.JLabel) comboboxopcoes.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        BasicComboPopup popupVista = (BasicComboPopup) comboboxopcoes.getAccessibleContext().getAccessibleChild(0);
        popupVista.getList().setSelectionBackground(Color.DARK_GRAY);
        popupVista.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        if (prefs.get("material", mat.getCodeOfMaterial()).equals(mat.getCodeOfMaterial())) {
            comboboxopcoes.setSelectedIndex(prefs.getInt("combobox", 0));
        } else {
            comboboxopcoes.setSelectedIndex(0);
        }
        comboboxopcoes.setPreferredSize(new Dimension(310, 26));
        comboboxopcoes.setFocusable(false);
        comboboxopcoes.setBackground(new Color(213, 213, 213));
        comboboxopcoes.setBounds(0, 0, 320, 40);
        comboboxopcoes.addActionListener((ActionEvent e) -> {
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{comboboxopcoes.getSelectedItem().toString()});
            jpanelesquerda.removeAll();
            painelgeral.repaint();
            jpanelesquerda.add(makeTable(comboboxopcoes, model, false, comboboxopcoes.getSelectedIndex()));
            prefs.put("material", mat.getCodeOfMaterial());
            prefs.putInt("combobox", comboboxopcoes.getSelectedIndex());
            this.updateComboBox(comboboxopcoes);
        });
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{comboboxopcoes.getSelectedItem().toString()});

        //paineis
        jpanelesquerda.removeAll();
        jpanelesquerda.add(this.makeTable(comboboxopcoes, model, false, comboboxopcoes.getSelectedIndex()));

        JPanel jpaneltituloesquerda = new javax.swing.JPanel();
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
        jpaneldireitacima.setPreferredSize(new java.awt.Dimension(284, 147));

        javax.swing.JLabel labeladicionar = new javax.swing.JLabel(lingua.translate("Adicionar") + ":");
        labeladicionar.setPreferredSize(new Dimension(284, 30));
        labeladicionar.setFont(new Font("Cantarell", Font.PLAIN, 14));
        labeladicionar.setBounds(0, 0, 284, 30);
        labeladicionar.setHorizontalAlignment(javax.swing.JLabel.LEFT);
        labeladicionar.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        jpaneldireitacima.add(labeladicionar);

        comboboxdireitacima = new javax.swing.JComboBox<>();
        comboboxdireitacima.setEditable(true);
        comboboxdireitacima.setEnabled(true);
        this.updateComboBox(comboboxopcoes);

        

        comboboxdireitacima.setPreferredSize(new Dimension(254, 26));
        comboboxdireitacima.setBounds(0, 35, 244, 26);
        BasicComboPopup popupcarateristicas = (BasicComboPopup) comboboxdireitacima.getAccessibleContext().getAccessibleChild(0);
        popupcarateristicas.getList().setSelectionBackground(Color.DARK_GRAY);
        popupcarateristicas.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        comboboxdireitacima.setFocusable(true);
        comboboxdireitacima.setBackground(new Color(213, 213, 213));
        jpaneldireitacima.add(comboboxdireitacima);

        btdireitacimamais = new Components.PersonalButton();
        btdireitacimamais.setPreferredSize(new Dimension(30, 26));
        btdireitacimamais.setBounds(250, 35, 30, 26);
        btdireitacimamais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ImageIcon ico = null;
        try {
            BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/plus16x24negro.png"));
            ico = new ImageIcon(im);
        } catch (IOException ex) {
            Logger.getLogger(WMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ico != null) {
            btdireitacimamais.setIcon(ico);
        } else {
            btdireitacimamais.setText("+");
        }
        btdireitacimamais.addActionListener((ActionEvent e) -> {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(380, 80));
            javax.swing.JLabel ldescricao = new javax.swing.JLabel(lingua.translate("Descrição") + ": ");
            ldescricao.setPreferredSize(new Dimension(100, 32));
            ldescricao.setBounds(0, 0, 100, 32);
            panel.add(ldescricao);

            Components.PersonalTextField tdescricao = new Components.PersonalTextField();
            tdescricao.setPreferredSize(new Dimension(240, 32));
            tdescricao.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
            tdescricao.setBounds(100, 0, 240, 32);
            panel.add(tdescricao);

            javax.swing.JLabel lmedida = new javax.swing.JLabel(lingua.translate("Medida") + ": ");
            lmedida.setPreferredSize(new Dimension(100, 32));
            lmedida.setBounds(0, 0, 100, 32);
            panel.add(lmedida);

            Components.PersonalTextField tmedida = new Components.PersonalTextField();
            tmedida.setPreferredSize(new Dimension(240, 32));
            tmedida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.black, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK)), BorderFactory.createEmptyBorder(0, 10, 0, 10)));
            tmedida.setBounds(100, 0, 240, 32);
            panel.add(tmedida);

            String[] btstexto = {lingua.translate("Adicionar"), lingua.translate("Voltar")};
            tdescricao.addPlaceHolder("Descrição da caraterística", null);
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
            tmedida.setLostCenterComponent(pop.getLeftButton());
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
                        updateComboBox(comboboxopcoes);
                        for (int i = 0; i < comboboxdireitacima.getItemCount(); i++) {
                            String aux = comboboxdireitacima.getItemAt(i);
                            System.out.println(aux);
                            if (aux.equals(desc)) {
                                comboboxdireitacima.setSelectedIndex(i);
                                break;
                            }
                        }

                    }
                }
            }
        });
        if (comboboxopcoes.getSelectedIndex() != 2) {
            jpaneldireitacima.add(btdireitacimamais);
        }
        comboboxopcoes.addActionListener((ActionEvent e) -> {
            if (comboboxopcoes.getSelectedIndex() != 2) {
                if (!jpaneldireitacima.getComponent(1).equals(btdireitacimamais)) {
                    jpaneldireitacima.add(btdireitacimamais);
                }
            } else {
                jpaneldireitacima.remove(btdireitacimamais);
            }
        });

        JPanel jpaneldireitabaixo = new javax.swing.JPanel();

        jpaneldireitabaixo.setBackground(Color.PINK);
        JPanel jpanelbaixo = new javax.swing.JPanel();
        jpanelbaixo.setBackground(Color.ORANGE);

        //jpanel2 = jpanelesquerda
        //jpanel4 = jpaneldireitacima azul
        //jpanel7 = jpaneldireitabaixo rosa
        //jpanel5 = jpaneltituloesquerda
        //jpanel6 = jpaneltitulodireita verde
        //jpanel8 = jpanelbaixo laranja
        jpanelesquerda.setPreferredSize(new java.awt.Dimension(355, 300));

        jpaneltituloesquerda.setPreferredSize(new java.awt.Dimension(315, 40));

        jpaneldireitabaixo.setPreferredSize(new java.awt.Dimension(315, 147));
        jpanelbaixo.setPreferredSize(new java.awt.Dimension(631, 70));

        javax.swing.GroupLayout playout = new javax.swing.GroupLayout(painelgeral);
        painelgeral.setLayout(playout);
        playout.setHorizontalGroup(
                playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(playout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jpanelbaixo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(playout.createSequentialGroup()
                                        .addGroup(playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jpaneltituloesquerda, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                                                .addComponent(jpanelesquerda, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(playout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jpaneldireitabaixo, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                                .addComponent(jpaneldireitacima, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                                .addComponent(jpaneltitulodireita, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))))
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
                                        .addComponent(jpaneldireitacima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jpaneldireitabaixo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jpanelesquerda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpanelbaixo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(63, Short.MAX_VALUE))
        );

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                prefs.put("material", mat.getCodeOfMaterial());
                prefs.putInt("combobox", comboboxopcoes.getSelectedIndex());
            }

            @Override
            public void windowClosed(WindowEvent e) {
                prefs.put("material", mat.getCodeOfMaterial());
                prefs.putInt("combobox", comboboxopcoes.getSelectedIndex());
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

        });

        /*javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );*/
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
            panelscroll.setPreferredSize(new Dimension(334, 300));
            panelscroll.setBounds(0, 0, 334, 300);
            panelscroll.setViewportView(tabela);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            tabela.setRowHeight(30);
            tabela.setModel(model);
            tabela.setPreferredSize(new Dimension((int) panelscroll.getPreferredSize().getWidth(), (int) (panelscroll.getPreferredSize().getHeight() - 40)));
            tabela.setBounds(panelscroll.getBounds());
            DataBase.DataBase db = new DataBase.DataBase(url);
            java.util.List<Keys.Feature> lfeat;
            java.util.List<Keys.Software> lsoft;
            java.util.List<Keys.Subject> ldis;
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            String auxiliar;
            switch (valor) {
                case 1:
                    lsoft = db.getSoftwareListByMaterial(mat);
                    for (Keys.Software soft : lsoft) {
                        auxiliar = soft.getName() + " (" + lingua.translate("Versão") + ": " + soft.getVersion() + ")";
                        modelo.addRow(new String[]{auxiliar});
                    }
                    break;
                case 2:
                    ldis = db.getSubjectsByMaterial(mat);
                    for (Keys.Subject dis : ldis) {
                        auxiliar = dis.getName();
                        modelo.addRow(new String[]{auxiliar});
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
                    }
                    break;
            }
            db.close();
            panelscroll.setBorder(null);
            tabela.setBorder(BorderFactory.createLineBorder(new Color(1, 1, 1), 1));
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
                    this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, painelgeral.getBackground()), BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(1, 1, 1))));
                    return this;
                }

            };

            headerRenderer.setBackground(new Color(80, 80, 80));
            headerRenderer.setForeground(Color.WHITE);
            headerRenderer.setPreferredSize(new Dimension(100, 30));
            headerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            tabela.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
            tabela.setEnabled(ativa);
        }
        return panelscroll;
    }

    private void updateComboBox(javax.swing.JComboBox<String> comboboxopcoes) {
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            int val = comboboxopcoes.getSelectedIndex();
            switch (val) {
                case 1:
                    software = db.getSoftwareList();
                    valores = new String[software.size() ];
                    int j = 0;
                    while (j < software.size()) {
                        if (!software.get(j).getVersion().equals("")) {
                            valores[j] = software.get(j).getName() + " (" + software.get(j).getVersion() + ")";
                        } else {
                            valores[j] = software.get(j).getName();
                        }
                        j++;
                    }
                    comboboxdireitacima.setModel(new javax.swing.DefaultComboBoxModel<>(valores));
                    break;
                case 2:
                    disciplinas = db.getAllSubjects();
                    valores = new String[disciplinas.size()];
                    int l = 0;
                    while (l < disciplinas.size()) {
                        valores[l] = disciplinas.get(l).getName();
                        l++;
                    }
                    comboboxdireitacima.setModel(new javax.swing.DefaultComboBoxModel<>(valores));
                    break;
                default:
                    features = db.getFeaturesByTypeOfMaterial(mat.getTypeOfMaterial());
                    valores = new String[features.size()];
                    int i = 0;
                    while (i < features.size()) {
                        valores[i] = features.get(i).getDescription();
                        i++;
                    }
                    comboboxdireitacima.setModel(new javax.swing.DefaultComboBoxModel<>(valores));
                    break;
            }
        }
    }

}
