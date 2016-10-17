/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import Clavis.ButtonListRequest;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author toze
 */
public class WListMaterial extends javax.swing.JFrame {

    private static final long serialVersionUID = 123L;
    private Color corborda;
    private Color corfundo;
    private static String url;
    private static Langs.Locale lingua;
    private Keys.TypeOfMaterial tiposelecionado;
    public static ButtonListRequest btTodos;
    public static ButtonListRequest btLivres;
    public static ButtonListRequest btOcupados;
    public static ButtonListRequest btPesquisa;
    private javax.swing.JScrollPane panTodos;
    private javax.swing.JScrollPane panLivres;
    private javax.swing.JScrollPane panOcupados;
    private javax.swing.JScrollPane panPesquisa;
    private int tabselecionada;
    private boolean pesquisa;
    private int ndias;

    public WListMaterial(Color corfundo, Color corborda, String url, Langs.Locale lingua, Keys.TypeOfMaterial tipo, int ndias) {
        this.corborda = corborda;
        this.corfundo = corfundo;
        WListMaterial.url = url;
        WListMaterial.lingua = lingua;
        tiposelecionado = tipo;
        tabselecionada = -1;
        pesquisa = false;
        switch (ndias) {
            case 0:
                this.ndias = 0;
                break;
            case 1:
                this.ndias = 1;
                break;
            case 2:
                this.ndias = 7;
                break;
            case 3:
                this.ndias = 14;
                break;
            default:
                this.ndias = 0;
                break;
        }
    }

    private void makePanel() {
        Border b = BorderFactory.createLineBorder(corborda, 4);
        jPanelInicial.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createLineBorder(Color.BLACK)));
        jPanelInicial.setBackground(corfundo);
        jLabelPesquisa.requestFocus();
    }

    private void createBehaviors() {
        textPesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String val = textPesquisa.getText();
                if ((!val.equals("")) && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                    makeSearchTab(val);
                } else if ((val.equals("")) && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
                    if (pesquisa) {
                        makeNormalTabs();
                    }
                } else if ((val.equals("")) && (e.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    btTodos.clear();
                    btOcupados.clear();
                    btLivres.clear();
                    if (btPesquisa != null) {
                        btPesquisa.clear();
                    }
                }
            }
        });
        jTabbedPaneMaterialBotoes.addChangeListener((ChangeEvent e) -> {
            if (jTabbedPaneMaterialBotoes.getTabCount() > 1) {
                if (tabselecionada != jTabbedPaneMaterialBotoes.getSelectedIndex()) {
                    tabselecionada = jTabbedPaneMaterialBotoes.getSelectedIndex();
                    btTodos.clear();
                    btOcupados.clear();
                    btLivres.clear();
                }
            } else if (btPesquisa != null) {
                btPesquisa.clear();
            }
        });

    }

    public void appear() {
        java.awt.Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Point po = new java.awt.Point((int) dim.getWidth() / 2 - this.getWidth() / 2, (int) dim.getHeight() / 2 - this.getHeight() / 2);
        this.setLocation(po);
        this.setVisible(true);
    }

    private void makeNormalTabs() {
        jTabbedPaneMaterialBotoes.removeAll();
        btTodos = new ButtonListRequest(url, this, tiposelecionado, lingua, jTabbedPaneMaterialBotoes, 2, "");
        btTodos.addComponentsToBehavior(jLabelNome, jLabelCodigo);
        btTodos.addList(jListRequisicoes, jScrollPane2, ndias);
        panTodos = btTodos.getScrollPane();
        jTabbedPaneMaterialBotoes.add(lingua.translate("Todos"), panTodos);
        btLivres = new ButtonListRequest(url, this, tiposelecionado, lingua, jTabbedPaneMaterialBotoes, 0, "");
        btLivres.addComponentsToBehavior(jLabelNome, jLabelCodigo);
        btLivres.addList(jListRequisicoes, jScrollPane2, ndias);
        panLivres = btLivres.getScrollPane();
        jTabbedPaneMaterialBotoes.add(lingua.translate("Livres"), panLivres);
        btOcupados = new ButtonListRequest(url, this, tiposelecionado, lingua, jTabbedPaneMaterialBotoes, 1, "");
        btOcupados.addComponentsToBehavior(jLabelNome, jLabelCodigo);
        btOcupados.addList(jListRequisicoes, jScrollPane2, ndias);
        panOcupados = btOcupados.getScrollPane();
        jTabbedPaneMaterialBotoes.add(lingua.translate("Ocupados"), panOcupados);
        jButtonLimpar.setEnabled(false);
        pesquisa = false;
    }

    private void makeSearchTab(String termo) {
        jTabbedPaneMaterialBotoes.removeAll();
        btTodos.clear();
        btOcupados.clear();
        btLivres.clear();
        btPesquisa = new ButtonListRequest(url, this, tiposelecionado, lingua, jTabbedPaneMaterialBotoes, 3, termo);
        btPesquisa.addComponentsToBehavior(jLabelNome, jLabelCodigo);
        btPesquisa.addList(jListRequisicoes, jScrollPane2, ndias);
        panPesquisa = btPesquisa.getScrollPane();
        jTabbedPaneMaterialBotoes.add(lingua.translate("Resultado da pesquisa"), panPesquisa);
        jButtonLimpar.setEnabled(true);
        pesquisa = true;
    }

    public void create() {
        initComponents();
        this.setTitle(lingua.translate("Listagem de recursos"));
        makeComboBox();
        makePanel();
        makeNormalTabs();
        createBehaviors();
        jTabbedPaneMaterialBotoes.addChangeListener((ChangeEvent e) -> {
            panTodos.repaint();
            panLivres.repaint();
            panOcupados.repaint();
        });
    }

    public static javax.swing.JTabbedPane getTable() {
        return jTabbedPaneMaterialBotoes;
    }

    public void makeComboBox() {
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            java.util.List<Keys.TypeOfMaterial> tipos = db.getTypesOfMaterial();
            DefaultComboBoxModel<Keys.TypeOfMaterial> modelo = new DefaultComboBoxModel<>();
            int andando = 0;
            int sel = 0;
            for (Keys.TypeOfMaterial t : tipos) {
                t = new Keys.TypeOfMaterial(t) {
                    @Override
                    public String toString(){
                        return lingua.translate(this.getTypeOfMaterialName());
                    }
                };
                modelo.addElement(t);
                if ((t.getMaterialTypeID() == tiposelecionado.getMaterialTypeID()) && (t.getTypeOfMaterialName().equals(tiposelecionado.getTypeOfMaterialName()))) {
                    sel = andando;
                }
                andando++;
            }
            jComboBoxTipo.setModel(modelo);
            jComboBoxTipo.setSelectedIndex(sel);
        }
        jComboBoxTipo.addActionListener((ActionEvent e) -> {
            tiposelecionado = (Keys.TypeOfMaterial) jComboBoxTipo.getSelectedItem();
            this.makeNormalTabs();
        });
    }

    /**
     * This met
     *
     * /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInicial = new javax.swing.JPanel();
        jScrollPaneMaterialBotoes = new javax.swing.JScrollPane();
        jTabbedPaneMaterialBotoes = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        textPesquisa = new Components.PersonalTextField();
        jButtonPesquisa = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelCodigo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListRequisicoes = new javax.swing.JList<String>();
        jPanel3 = new javax.swing.JPanel();
        jLabelPesquisa = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2222222, 2147483647));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);

        jPanelInicial.setBackground(new java.awt.Color(254, 254, 254));
        jPanelInicial.setMinimumSize(new java.awt.Dimension(800, 500));
        jPanelInicial.setPreferredSize(new java.awt.Dimension(800, 500));

        jScrollPaneMaterialBotoes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneMaterialBotoes.setOpaque(false);
        jScrollPaneMaterialBotoes.setPreferredSize(new java.awt.Dimension(540, 528));

        jTabbedPaneMaterialBotoes.setBackground(new java.awt.Color(254, 254, 254));
        jTabbedPaneMaterialBotoes.setOpaque(true);
        jTabbedPaneMaterialBotoes.setPreferredSize(new java.awt.Dimension(534, 380));
        jScrollPaneMaterialBotoes.setViewportView(jTabbedPaneMaterialBotoes);
        UIManager.put("TabbedPane.background", Color.WHITE);
        UIManager.put("TabbedPane.selected", Color.red);

        jTabbedPaneMaterialBotoes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTabbedPaneMaterialBotoes.setAutoscrolls(true);

        jTabbedPaneMaterialBotoes.setFocusable(false);

        jTabbedPaneMaterialBotoes.setOpaque(true);

        jTabbedPaneMaterialBotoes.setRequestFocusEnabled(false);

        jScrollPaneMaterialBotoes.setViewportView(jTabbedPaneMaterialBotoes);

        jLabel1.setBackground(new java.awt.Color(240, 240, 240));
        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setMaximumSize(new java.awt.Dimension(592, 30));
        jLabel1.setMinimumSize(new java.awt.Dimension(592, 30));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(592, 30));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setCornerSize(6);
        dropShadowBorder1.setShadowSize(2);
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        textPesquisa.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder1, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));

        jButtonPesquisa.setBackground(new java.awt.Color(51, 102, 203));
        jButtonPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPesquisa.setFocusPainted(false);
        jButtonPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisaActionPerformed(evt);
            }
        });

        jButtonSair.setBackground(new java.awt.Color(1, 1, 1));
        jButtonSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSair.setFocusPainted(false);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jButtonLimpar.setBackground(new java.awt.Color(253, 67, 67));
        jButtonLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLimpar.setEnabled(false);
        jButtonLimpar.setFocusPainted(false);
        jButtonLimpar.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonLimpar.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonLimpar.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jPanel1.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabelNome.setBackground(new java.awt.Color(240, 240, 240));
        jLabelNome.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jLabelNome.setMaximumSize(new java.awt.Dimension(181, 30));
        jLabelNome.setMinimumSize(new java.awt.Dimension(181, 30));
        jLabelNome.setOpaque(true);
        jLabelNome.setPreferredSize(new java.awt.Dimension(181, 30));

        jLabel5.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabelCodigo.setBackground(new java.awt.Color(240, 240, 240));
        jLabelCodigo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jLabelCodigo.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(240, 240, 240));
        jLabel2.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jLabel3.setText(lingua.translate("Nome")+": ");
        jLabel5.setText(lingua.translate("Código")+": ");
        jLabel2.setText(lingua.translate("Recurso"));

        jPanel2.setOpaque(false);

        jLabel4.setBackground(new java.awt.Color(240, 240, 240));
        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setBorder(null);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jListRequisicoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListRequisicoes);
        jListRequisicoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jListRequisicoes.setCellRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 2L;

            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = (javax.swing.JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(120, 45));
                if (isSelected) {
                    label.setBackground(jListRequisicoes.getBackground());
                    label.setForeground(Color.WHITE);
                }
                label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                return label;
            }
        });
        jListRequisicoes.setPreferredSize(new Dimension(220, 130));
        jListRequisicoes.setBackground(new Color(250, 250, 250));

        jScrollPane2.setBorder(BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, true, true, true), BorderFactory.createLineBorder(Color.BLACK)));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
        );

        jLabel4.setText(lingua.translate("Lista de requisições")+":");

        jPanel3.setOpaque(false);

        jLabelPesquisa.setBackground(new java.awt.Color(240, 240, 240));
        jLabelPesquisa.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabelPesquisa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPesquisa.setBorder(null);
        jLabelPesquisa.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        jLabelPesquisa.setText(lingua.translate("Pesquisa")+":");

        jComboBoxTipo.setFocusable(false);

        javax.swing.GroupLayout jPanelInicialLayout = new javax.swing.GroupLayout(jPanelInicial);
        jPanelInicial.setLayout(jPanelInicialLayout);
        jPanelInicialLayout.setHorizontalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPaneMaterialBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicialLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textPesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16))))
        );
        jPanelInicialLayout.setVerticalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInicialLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPaneMaterialBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(textPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonPesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        jLabel1.setText(lingua.translate("Lista de recursos"));
        textPesquisa.addPlaceHolder(lingua.translate("Termo a pesquisar")+"...", jLabelPesquisa);
        jLabelPesquisa.requestFocus();
        try {
            if (Clavis.KeyQuest.class.getResource("Images/lupa.png") != null) {
                BufferedImage impesquisa = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lupa.png"));
                ImageIcon icpesquisa = new ImageIcon(impesquisa);
                jButtonPesquisa.setIcon(icpesquisa);
            } else {
                jButtonPesquisa.setText(lingua.translate("Pesquisa"));
            }
        } catch(IOException e){}

        jButtonPesquisa.setToolTipText(lingua.translate("Pesquisa de recurso"));
        try {
            if (Clavis.KeyQuest.class.getResource("Images/exit26x24.png") != null) {
                BufferedImage imsair = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/exit26x24.png"));
                ImageIcon imosair = new ImageIcon(imsair);
                jButtonSair.setIcon(imosair);
            } else {
                jButtonSair.setText(lingua.translate("Sair"));
            }
        } catch(IOException e) {}
        jButtonSair.setToolTipText(lingua.translate("Sair"));
        try {
            if (Clavis.KeyQuest.class.getResource("Images/limpar.png") != null) {
                BufferedImage bflimpar = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/limpar.png"));
                ImageIcon imlimpar = new ImageIcon(bflimpar);
                jButtonLimpar.setIcon(imlimpar);
            } else {
                jButtonLimpar.setText(lingua.translate("Limpar"));
            }

        } catch (IOException e) {}
        jButtonLimpar.setToolTipText(lingua.translate("Limpar a pesquisa"));
        ((javax.swing.JLabel)jComboBoxTipo.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        BasicComboPopup popuptipo = (BasicComboPopup) jComboBoxTipo.getAccessibleContext().getAccessibleChild(0);
        popuptipo.getList().setSelectionBackground(Color.DARK_GRAY);
        popuptipo.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        if (DataBase.DataBase.testConnection(url)) {
            this.makeNormalTabs();
            textPesquisa.clearText();
            btTodos.clear();
            btOcupados.clear();
            btLivres.clear();
            if (btPesquisa != null) {
                btPesquisa.clear();
            }
        }
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisaActionPerformed
        String termo = textPesquisa.getText();
        if (!termo.equals("")) {
            if (DataBase.DataBase.testConnection(url)) {
                this.makeSearchTab(termo);
            }
        }
    }//GEN-LAST:event_jButtonPesquisaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonPesquisa;
    private javax.swing.JButton jButtonSair;
    /*
    private javax.swing.JComboBox<String> jComboBoxTipo;
    */
    private javax.swing.JComboBox<Keys.TypeOfMaterial> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private static javax.swing.JLabel jLabelCodigo;
    private static javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelPesquisa;
    /*
    private static org.jdesktop.swingx.JXList<String> jListRequisicoes;
    */
    private javax.swing.JList<String> jListRequisicoes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelInicial;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneMaterialBotoes;
    private static javax.swing.JTabbedPane jTabbedPaneMaterialBotoes;
    private Components.PersonalTextField textPesquisa;
    // End of variables declaration//GEN-END:variables
}
