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
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author toze
 */
public class WRequest extends javax.swing.JFrame {

    private static final java.awt.Color BACKGROUND_COLOR = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color BORDER_COLOR = new java.awt.Color(115, 115, 115);

    private final java.awt.Color corborda;
    private final java.awt.Color corfundo;
    private Langs.Locale lingua;
    private final Keys.Material mat;
    private String url;
    private javax.swing.JSpinner.NumberEditor spineditor;
    private java.util.List<Keys.TypeOfMaterial> tlista;
    private static java.util.Set<Keys.Material> mlista;
    boolean entrou;
    boolean bpesquisa;
    java.util.List<Keys.Person> pessoas;
    static Keys.Person pessoaescolhida;
    Components.PersonalLabel pl;
    boolean mexeu;
    int tipomaterialselecionado;
    static Keys.TypeOfMaterial tiposelecionado;
    Components.PersonalCombo jComboBoxTipoMaterial;
    static Components.PersonalCombo jComboBoxMaterial;
    Components.PersonalCombo jComboBoxNomeUtilizador;
    private java.util.List<Keys.ClassStudents> reqturmas;
    private java.util.List<Keys.Subject> reqdisciplinas;
    private String reqatividade;

    /**
     * Creates new form WRequest
     */
    public WRequest() {
        super();
        corborda = BORDER_COLOR;
        corfundo = BACKGROUND_COLOR;
        lingua = Langs.Locale.getLocale_pt_PT();
        mat = null;
        url = "";
        tlista = new java.util.ArrayList<>();
        mlista = new java.util.TreeSet<>();
        spineditor = null;
        mexeu = false;
        tipomaterialselecionado = 0;
        jComboBoxTipoMaterial = new Components.PersonalCombo(jLabelRecurso);
        jComboBoxMaterial = new Components.PersonalCombo(jLabelRecurso);
        jComboBoxNomeUtilizador = new Components.PersonalCombo(jLabelRecurso);
        reqturmas = new java.util.ArrayList<>();
        reqdisciplinas = new java.util.ArrayList<>();
        reqatividade = "";
        bpesquisa = false;
    }

    public WRequest(java.awt.Color corborda, java.awt.Color corfundo, String url, Langs.Locale lingua) {
        super();
        this.corborda = corborda;
        this.corfundo = corfundo;
        this.lingua = lingua;
        mat = null;
        this.url = url;
        tlista = new java.util.ArrayList<>();
        mlista = new java.util.TreeSet<>();
        spineditor = null;
        mexeu = false;
        tipomaterialselecionado = 0;
        jComboBoxTipoMaterial = new Components.PersonalCombo(jLabelRecurso);
        jComboBoxMaterial = new Components.PersonalCombo(jLabelRecurso);
        jComboBoxNomeUtilizador = new Components.PersonalCombo(jLabelRecurso);
        reqturmas = new java.util.ArrayList<>();
        reqdisciplinas = new java.util.ArrayList<>();
        reqatividade = "";
        bpesquisa = false;
    }

    public WRequest(java.awt.Color corborda, java.awt.Color corfundo, String url, Langs.Locale lingua, Keys.Material mat) {
        super();
        this.corborda = corborda;
        this.corfundo = corfundo;
        this.lingua = lingua;
        this.mat = mat;
        this.url = url;
        tlista = new java.util.ArrayList<>();
        mlista = new java.util.TreeSet<>();
        spineditor = null;
        mexeu = false;
        tipomaterialselecionado = 0;
        jComboBoxTipoMaterial = new Components.PersonalCombo(jLabelRecurso);
        jComboBoxMaterial = new Components.PersonalCombo(jLabelRecurso);
        jComboBoxNomeUtilizador = new Components.PersonalCombo(jLabelRecurso);
        reqturmas = new java.util.ArrayList<>();
        reqdisciplinas = new java.util.ArrayList<>();
        reqatividade = "";
        bpesquisa = false;
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
        jLabelRecurso = new javax.swing.JLabel();
        jComboBoxTipoM = jComboBoxTipoMaterial.getComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxM = jComboBoxMaterial.getComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSpinnerQuantidade = new javax.swing.JSpinner();
        jButtonPesquisa = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelPessoa = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxNomeU = jComboBoxNomeUtilizador.getComboBox();
        jLabel11 = new javax.swing.JLabel();
        personalTextFieldCodigoUtilizador = new Components.PersonalTextField();
        jLabel12 = new javax.swing.JLabel();
        personalTextFieldEmailUtilizador = new Components.PersonalTextField();
        jButtonMaisUtilizador = new javax.swing.JButton();
        jButtonAlgoMais = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner(new SpinnerDateModel());
        jLabel6 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSpinner2 = new javax.swing.JSpinner(new SpinnerDateModel());
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelConteudo = new javax.swing.JPanel();
        jButtonSair = new javax.swing.JButton();
        jButtonRequisitar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 650));
        setMinimumSize(new java.awt.Dimension(900, 650));
        setPreferredSize(new java.awt.Dimension(900, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 650));

        jPanelInicial.setBackground(new java.awt.Color(254, 254, 254));
        jPanelInicial.setMinimumSize(new java.awt.Dimension(900, 500));
        jPanelInicial.setName(""); // NOI18N
        jPanelInicial.setPreferredSize(new java.awt.Dimension(900, 500));
        jPanelInicial.setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(246, 246, 246));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setCornerSize(6);
        dropShadowBorder1.setShadowSize(3);
        dropShadowBorder1.setShowLeftShadow(true);
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder1, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel1.setMinimumSize(new java.awt.Dimension(424, 250));

        jLabelRecurso.setBackground(new java.awt.Color(250, 250, 250));
        jLabelRecurso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRecurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelRecurso.setMinimumSize(new java.awt.Dimension(47, 30));
        jLabelRecurso.setName(""); // NOI18N
        jLabelRecurso.setOpaque(true);
        jLabelRecurso.setPreferredSize(new java.awt.Dimension(47, 30));

        jComboBoxTipoM.setMinimumSize(new java.awt.Dimension(35, 28));
        jComboBoxTipoM.setPreferredSize(new java.awt.Dimension(35, 28));

        jLabel2.setMaximumSize(new java.awt.Dimension(4444444, 26));
        jLabel2.setMinimumSize(new java.awt.Dimension(104, 26));
        jLabel2.setPreferredSize(new java.awt.Dimension(104, 26));

        jComboBoxMaterial.setHelpText(lingua.translate("Escolha de recurso")+" ...");
        jComboBoxM.setMinimumSize(new java.awt.Dimension(35, 28));
        jComboBoxM.setPreferredSize(new java.awt.Dimension(35, 28));
        jComboBoxM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMActionPerformed(evt);
            }
        });

        jLabel3.setMaximumSize(new java.awt.Dimension(444444, 26));
        jLabel3.setMinimumSize(new java.awt.Dimension(104, 26));
        jLabel3.setPreferredSize(new java.awt.Dimension(104, 26));

        jLabel5.setToolTipText("");
        jLabel5.setMaximumSize(new java.awt.Dimension(3333337, 26));
        jLabel5.setMinimumSize(new java.awt.Dimension(104, 26));
        jLabel5.setPreferredSize(new java.awt.Dimension(104, 26));

        jSpinnerQuantidade.setMinimumSize(new java.awt.Dimension(32, 26));
        jSpinnerQuantidade.setPreferredSize(new java.awt.Dimension(32, 24));

        jButtonPesquisa.setBackground(new java.awt.Color(51, 102, 153));
        jButtonPesquisa.setFocusPainted(false);
        jButtonPesquisa.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonPesquisa.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonPesquisa.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisaActionPerformed(evt);
            }
        });

        jButtonAtualizar.setBackground(new java.awt.Color(51, 102, 153));
        jButtonAtualizar.setBorder(null);
        jButtonAtualizar.setFocusPainted(false);
        jButtonAtualizar.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonAtualizar.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonAtualizar.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jComboBoxM, 0, 239, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinnerQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxTipoM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabelRecurso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxTipoM, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSpinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxM, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelRecurso.setText(lingua.translate("Recurso"));
        jComboBoxTipoMaterial.setBackgroundColor(new Color(213,213,213));
        jComboBoxTipoMaterial.setHelpText(lingua.translate("Tipo de material")+" ...");
        jComboBoxTipoMaterial.create();
        jLabel2.setText(lingua.translate("Tipo de material")+": ");
        jComboBoxMaterial.create();
        jLabel3.setText(lingua.translate("Recurso específico")+": ");
        jLabel5.setText(lingua.translate("Quantidade")+": ");
        try {
            if (Clavis.KeyQuest.class.getResource("Images/lupa.png") != null) {
                BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lupa.png"));
                ImageIcon ic = new ImageIcon(im);
                jButtonPesquisa.setIcon(ic);
            } else {
                jButtonPesquisa.setText(lingua.translate("Pesquisa"));
            }
        } catch(IOException e){}

        jButtonPesquisa.setToolTipText(lingua.translate("Filtrar através de pesquisa"));
        jButtonPesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        try {
            if (Clavis.KeyQuest.class.getResource("Images/refresh.png") != null) {
                BufferedImage imAtualizar = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/refresh.png"));
                ImageIcon icAtualizar = new ImageIcon(imAtualizar);
                jButtonAtualizar.setIcon(icAtualizar);
            } else {
                jButtonAtualizar.setText(lingua.translate("Atualizar"));
            }
        } catch(IOException e){}

        jButtonAtualizar.setToolTipText(lingua.translate("Atualizar recursos disponíveis"));
        jButtonAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBackground(new java.awt.Color(246, 246, 246));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setCornerSize(6);
        dropShadowBorder2.setShadowSize(3);
        dropShadowBorder2.setShowLeftShadow(true);
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder2, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel2.setMinimumSize(new java.awt.Dimension(424, 250));
        jPanel2.setPreferredSize(new java.awt.Dimension(424, 250));

        jLabelPessoa.setBackground(new java.awt.Color(250, 250, 250));
        jLabelPessoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPessoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelPessoa.setMinimumSize(new java.awt.Dimension(47, 30));
        jLabelPessoa.setName(""); // NOI18N
        jLabelPessoa.setOpaque(true);
        jLabelPessoa.setPreferredSize(new java.awt.Dimension(47, 30));

        jLabel8.setToolTipText("");
        jLabel8.setMaximumSize(new java.awt.Dimension(374444, 26));
        jLabel8.setMinimumSize(new java.awt.Dimension(37, 26));
        jLabel8.setPreferredSize(new java.awt.Dimension(147, 26));

        jComboBoxNomeU.setMinimumSize(new java.awt.Dimension(223, 28));
        jComboBoxNomeU.setPreferredSize(new java.awt.Dimension(223, 28));
        jComboBoxNomeU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNomeUActionPerformed(evt);
            }
        });

        jLabel11.setMaximumSize(new java.awt.Dimension(433332, 30));
        jLabel11.setMinimumSize(new java.awt.Dimension(129, 30));
        jLabel11.setPreferredSize(new java.awt.Dimension(129, 30));

        personalTextFieldCodigoUtilizador.setBorder(null);
        personalTextFieldCodigoUtilizador.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        personalTextFieldCodigoUtilizador.setMinimumSize(new java.awt.Dimension(10, 28));
        personalTextFieldCodigoUtilizador.setPreferredSize(new java.awt.Dimension(107, 28));

        jLabel12.setMaximumSize(new java.awt.Dimension(444444, 30));
        jLabel12.setMinimumSize(new java.awt.Dimension(129, 30));
        jLabel12.setPreferredSize(new java.awt.Dimension(129, 30));

        personalTextFieldEmailUtilizador.setBorder(null);
        personalTextFieldEmailUtilizador.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        personalTextFieldEmailUtilizador.setMinimumSize(new java.awt.Dimension(10, 28));
        personalTextFieldEmailUtilizador.setPreferredSize(new java.awt.Dimension(107, 28));
        personalTextFieldEmailUtilizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personalTextFieldEmailUtilizadorActionPerformed(evt);
            }
        });

        jButtonMaisUtilizador.setBackground(new java.awt.Color(51, 102, 153));
        jButtonMaisUtilizador.setFocusPainted(false);
        jButtonMaisUtilizador.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonMaisUtilizador.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonMaisUtilizador.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonMaisUtilizador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMaisUtilizadorActionPerformed(evt);
            }
        });

        jButtonAlgoMais.setBackground(new java.awt.Color(51, 102, 153));
        jButtonAlgoMais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAlgoMais.setEnabled(false);
        jButtonAlgoMais.setFocusPainted(false);
        jButtonAlgoMais.setMaximumSize(new java.awt.Dimension(532222, 2222));
        jButtonAlgoMais.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonAlgoMais.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonAlgoMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlgoMaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonMaisUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAlgoMais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelPessoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(personalTextFieldEmailUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(personalTextFieldCodigoUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jComboBoxNomeU, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxNomeU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(personalTextFieldEmailUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(personalTextFieldCodigoUtilizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonMaisUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlgoMais, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelPessoa.setText(lingua.translate("Utilizador"));
        jLabel8.setText(lingua.translate("Nome")+": ");
        jComboBoxNomeUtilizador.setBackgroundColor(new Color(213,213,213));
        jComboBoxNomeUtilizador.setHelpText(lingua.translate("Nome de utilizador")+" ...");
        jComboBoxNomeUtilizador.create();
        jLabel11.setText(lingua.translate("Email")+": ");
        personalTextFieldCodigoUtilizador.addPlaceHolder(lingua.translate("Código de identificação")+" ...", jLabelRecurso);
        Border f = BorderFactory.createCompoundBorder(new org.jdesktop.swingx.border.DropShadowBorder(Color.BLACK, 3, 0.5f, 6, false, false, true, true), BorderFactory.createLineBorder(Color.BLACK));
        personalTextFieldCodigoUtilizador.setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        jLabel12.setText(lingua.translate("Identificação")+": ");
        personalTextFieldEmailUtilizador.addPlaceHolder(lingua.translate("Correio eletrónico")+" ...", jLabelRecurso);

        personalTextFieldEmailUtilizador.setBorder(BorderFactory.createCompoundBorder(f, BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        try {
            if (Clavis.KeyQuest.class.getResource("Images/plus24x24.png") != null) {
                BufferedImage im2 = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/plus24x24.png"));
                ImageIcon ic2 = new ImageIcon(im2);
                jButtonMaisUtilizador.setIcon(ic2);
            } else {
                jButtonMaisUtilizador.setText(lingua.translate("Adicionar"));
            }
        } catch(IOException e){}

        jButtonMaisUtilizador.setToolTipText(lingua.translate("Adicionar / editar utilizadores"));
        jButtonMaisUtilizador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(100, 100, 100));
        jLabel7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Hora");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel7.setMaximumSize(new java.awt.Dimension(3343446, 96));
        jLabel7.setMinimumSize(new java.awt.Dimension(36, 26));
        jLabel7.setOpaque(true);
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 20));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setCornerSize(6);
        dropShadowBorder3.setShadowSize(3);
        dropShadowBorder3.setShowLeftShadow(true);
        jSpinner1.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder3, javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0))));
        jSpinner1.setMinimumSize(new java.awt.Dimension(50, 30));
        jSpinner1.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel6.setBackground(new java.awt.Color(100, 100, 100));
        jLabel6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(254, 254, 254));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Data");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel6.setMaximumSize(new java.awt.Dimension(3343446, 96));
        jLabel6.setMinimumSize(new java.awt.Dimension(36, 26));
        jLabel6.setOpaque(true);
        jLabel6.setPreferredSize(new java.awt.Dimension(149, 20));

        jXDatePicker1.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder4 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder4.setCornerSize(6);
        dropShadowBorder4.setShadowSize(3);
        dropShadowBorder4.setShowLeftShadow(true);
        jXDatePicker1.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder4, javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0))));
        jXDatePicker1.setMaximumSize(new java.awt.Dimension(111, 30));
        jXDatePicker1.setMinimumSize(new java.awt.Dimension(111, 30));
        jXDatePicker1.setPreferredSize(new java.awt.Dimension(111, 30));
        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(250, 250, 250));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setMaximumSize(new java.awt.Dimension(131333, 300));
        jLabel1.setMinimumSize(new java.awt.Dimension(131, 30));
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(131, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(53, 53, 53))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jLabel1.setText(lingua.translate("Início"));
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) jSpinner1.getEditor();
        spinnerEditor.getTextField().setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSpinner1.setModel(new SpinnerDateModel());
        jSpinner1.setEditor(new JSpinner.DateEditor(jSpinner1, "HH:mm"));
        javax.swing.JFormattedTextField ff = (javax.swing.JFormattedTextField)((javax.swing.JSpinner.DateEditor)jSpinner1.getEditor()).getComponent(0);
        ff.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabel1.setText(lingua.translate("Início"));
        javax.swing.JPanel pan2 = new javax.swing.JPanel(null);
        pan2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20), BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)));
        String compoe = lingua.translate("Hoje é dia")+": "+new TimeDate.Date().toString();
        javax.swing.JLabel fil2 = new javax.swing.JLabel(compoe);
        fil2.setBounds(0, 0, 300, 26);
        fil2.setHorizontalAlignment(SwingConstants.CENTER);
        pan2.setPreferredSize(new Dimension(300,30));
        pan2.setBounds(0, 0, 300, 30);
        pan2.add(fil2);
        jXDatePicker1.setLinkPanel(pan2);

        jXDatePicker1.setLocale(lingua.systemlocale);
        jXDatePicker1.getEditor().setSelectionColor(Color.DARK_GRAY);
        jXDatePicker1.getEditor().setBorder(BorderFactory.createEmptyBorder());
        jXDatePicker1.getEditor().setHorizontalAlignment(SwingConstants.CENTER);
        javax.swing.JButton bbt2 = (javax.swing.JButton) jXDatePicker1.getComponent(1);
        bbt2.setBackground(Color.WHITE);

        jXDatePicker1.setFormats("dd/MM/yyyy");
        jXDatePicker1.setDate(new java.util.Date());
        jLabel1.setText(lingua.translate("Início da requisição"));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder5 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder5.setCornerSize(6);
        dropShadowBorder5.setShadowSize(3);
        dropShadowBorder5.setShowLeftShadow(true);
        jSpinner2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder5, javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0))));
        jSpinner2.setMinimumSize(new java.awt.Dimension(50, 30));
        jSpinner2.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel9.setBackground(new java.awt.Color(100, 100, 100));
        jLabel9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Hora");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel9.setMaximumSize(new java.awt.Dimension(3343446, 96));
        jLabel9.setMinimumSize(new java.awt.Dimension(36, 26));
        jLabel9.setOpaque(true);
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel4.setBackground(new java.awt.Color(250, 250, 250));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel4.setMaximumSize(new java.awt.Dimension(131333, 300));
        jLabel4.setMinimumSize(new java.awt.Dimension(131, 30));
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(131, 30));

        jLabel10.setBackground(new java.awt.Color(100, 100, 100));
        jLabel10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(254, 254, 254));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Data");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel10.setMaximumSize(new java.awt.Dimension(3343446, 96));
        jLabel10.setMinimumSize(new java.awt.Dimension(36, 26));
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(149, 20));

        jXDatePicker2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder6 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder6.setCornerSize(6);
        dropShadowBorder6.setShadowSize(3);
        dropShadowBorder6.setShowLeftShadow(true);
        jXDatePicker2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder6, javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(0, 0, 0))));
        jXDatePicker2.setMaximumSize(new java.awt.Dimension(111, 30));
        jXDatePicker2.setMinimumSize(new java.awt.Dimension(111, 30));
        jXDatePicker2.setPreferredSize(new java.awt.Dimension(111, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        JSpinner.DefaultEditor spinnerEditor2 = (JSpinner.DefaultEditor) jSpinner2.getEditor();
        spinnerEditor2.getTextField().setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSpinner2.setModel(new SpinnerDateModel());
        jSpinner2.setEditor(new JSpinner.DateEditor(jSpinner2, "HH:mm"));
        ff = (javax.swing.JFormattedTextField)((javax.swing.JSpinner.DateEditor)jSpinner2.getEditor()).getComponent(0);
        ff.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabel1.setText(lingua.translate("Início"));
        jLabel4.setText(lingua.translate("Fim da requisição"));
        jLabel1.setText(lingua.translate("Início"));
        javax.swing.JPanel pan3 = new javax.swing.JPanel(null);
        pan3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20), BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)));
        String compoe2 = lingua.translate("Hoje é dia")+": "+new TimeDate.Date().toString();
        javax.swing.JLabel fil3 = new javax.swing.JLabel(compoe2);
        fil3.setBounds(0, 0, 300, 26);
        fil3.setHorizontalAlignment(SwingConstants.CENTER);
        pan3.setPreferredSize(new Dimension(300,30));
        pan3.setBounds(0, 0, 300, 30);
        pan3.add(fil3);
        jXDatePicker2.setLinkPanel(pan3);

        jXDatePicker2.setLocale(lingua.systemlocale);
        jXDatePicker2.getEditor().setSelectionColor(Color.DARK_GRAY);
        jXDatePicker2.getEditor().setBorder(BorderFactory.createEmptyBorder());
        jXDatePicker2.getEditor().setHorizontalAlignment(SwingConstants.CENTER);
        javax.swing.JButton bbt3 = (javax.swing.JButton) jXDatePicker2.getComponent(1);
        bbt3.setBackground(Color.WHITE);

        jXDatePicker2.setFormats("dd/MM/yyyy");
        jXDatePicker2.setDate(new java.util.Date());

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder7 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder7.setCornerSize(6);
        dropShadowBorder7.setShadowSize(3);
        dropShadowBorder7.setShowLeftShadow(true);
        dropShadowBorder7.setShowTopShadow(true);
        jScrollPane1.setBorder(dropShadowBorder7);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(621, 218));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(621, 218));

        jPanelConteudo.setBackground(new java.awt.Color(50, 50, 50));
        jPanelConteudo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 254, 254)));
        jPanelConteudo.setMinimumSize(new java.awt.Dimension(612, 208));
        jPanelConteudo.setName(""); // NOI18N
        jPanelConteudo.setPreferredSize(new java.awt.Dimension(612, 208));

        javax.swing.GroupLayout jPanelConteudoLayout = new javax.swing.GroupLayout(jPanelConteudo);
        jPanelConteudo.setLayout(jPanelConteudoLayout);
        jPanelConteudoLayout.setHorizontalGroup(
            jPanelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelConteudoLayout.setVerticalGroup(
            jPanelConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanelConteudo);

        jButtonSair.setBackground(new java.awt.Color(1, 1, 1));
        jButtonSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSair.setFocusPainted(false);
        jButtonSair.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonSair.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonSair.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jButtonRequisitar.setBackground(new java.awt.Color(57, 147, 2));
        jButtonRequisitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRequisitar.setEnabled(false);
        jButtonRequisitar.setFocusPainted(false);
        jButtonRequisitar.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonRequisitar.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonRequisitar.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonRequisitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRequisitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInicialLayout = new javax.swing.GroupLayout(jPanelInicial);
        jPanelInicial.setLayout(jPanelInicialLayout);
        jPanelInicialLayout.setHorizontalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelInicialLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRequisitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanelInicialLayout.setVerticalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRequisitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
        );

        try {
            if (Clavis.KeyQuest.class.getResource("Images/exit26x24.png") != null) {
                BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/exit26x24.png"));
                ImageIcon imo = new ImageIcon(im);
                jButtonSair.setIcon(imo);
            } else {
                jButtonSair.setText(lingua.translate("Sair"));
            }
        } catch(IOException e) {}
        jButtonSair.setToolTipText(lingua.translate("Sair"));
        try {
            if (Clavis.KeyQuest.class.getResource("Images/save.png") != null) {
                BufferedImage im3 = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/save.png"));
                ImageIcon ic3 = new ImageIcon(im3);
                jButtonRequisitar.setIcon(ic3);
            } else {
                jButtonRequisitar.setText(lingua.translate("Requisitar"));
            }
        } catch(IOException e){}

        jButtonRequisitar.setToolTipText(lingua.translate("Efetuar requisição"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );

        jPanelInicial.setBackground(corfundo);
        jPanelInicial.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(corborda, 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMActionPerformed

    private void jButtonPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisaActionPerformed
        if (bpesquisa) {
            int val = jComboBoxMaterial.getSelectedIndex() - 1;
            Keys.Material ajuda = null;
            if (val >= 0) {
                int i = 0;
                Keys.Material m = null;
                for (Keys.Material mo : mlista) {
                    if (i == val) {
                        m = mo;
                        break;
                    }
                    i++;
                }
                this.updateComboMaterialBox(null);
                if (m != null) {
                    int g = 0;
                    for (Keys.Material mo : mlista) {
                        if (m.compareTo(mo) == 0) {
                            jComboBoxMaterial.setSelectedIndex(g + 1);
                            ajuda = mo;
                            break;
                        }
                        g++;
                    }
                }
            } else {
                this.updateComboMaterialBox(null);
            }
            jButtonPesquisa.setBorder(null);

            if (pl != null) {
                if (mlista.size() < (int) jSpinnerQuantidade.getValue()) {
                    jSpinnerQuantidade.setValue(mlista.size());
                }
                if (val < 0) {
                    int valor = (int) jSpinnerQuantidade.getValue();
                    pl.set(valor);
                    pl.setNewList(mlista);
                    pl.go(true, null);
                } else if (ajuda != null) {
                    pl.go(false, ajuda);
                }
                bpesquisa = false;
                try {
                    if (Clavis.KeyQuest.class.getResource("Images/lupa.png") != null) {
                        BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lupa.png"));
                        ImageIcon ic = new ImageIcon(im);
                        jButtonPesquisa.setIcon(ic);
                    } else {
                        jButtonPesquisa.setText(lingua.translate("Pesquisa"));
                    }
                } catch (IOException e) {
                }
                jButtonPesquisa.setBackground(new Color(51, 102, 153));
                jButtonPesquisa.setToolTipText(lingua.translate("Filtrar através de pesquisa"));

            }
        } else if (tiposelecionado != null) {
            Clavis.Windows.WSearch ws = new Clavis.Windows.WSearch(corfundo, corborda, url, lingua, this, tiposelecionado, mlista);
            ws.create();
            ws.appear();
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButtonPesquisaActionPerformed

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    private void jComboBoxNomeUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNomeUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxNomeUActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        TimeDate.Date datatual = new TimeDate.Date();
        TimeDate.Date dat1 = new TimeDate.Date(this.jXDatePicker1.getDate());
        TimeDate.Date dat2 = new TimeDate.Date(this.jXDatePicker2.getDate());
        if (datatual.isBigger(dat1) < 0) {
            this.jXDatePicker1.setDate(new Date());
        }
        if (datatual.isBigger(dat2) < 0) {
            this.jXDatePicker2.setDate(new Date());
        }
        int val = jComboBoxMaterial.getSelectedIndex() - 1;
        boolean ficou_o_mesmo = false;
        Keys.Material m = null;
        if (val >= 0) {
            int i = 0;
            for (Keys.Material mo : mlista) {
                if (i == val) {
                    m = mo;
                    break;
                }
                i++;
            }
        }
        this.updateComboMaterialBox(null);
        if (m != null) {
            int g = 0;
            for (Keys.Material mo : mlista) {
                if (m.compareTo(mo) == 0) {
                    jComboBoxMaterial.setSelectedIndex(g + 1);
                    ficou_o_mesmo = true;
                    break;
                }
                g++;
            }
        }
        bpesquisa = false;
        try {
            if (Clavis.KeyQuest.class.getResource("Images/lupa.png") != null) {
                BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lupa.png"));
                ImageIcon ic = new ImageIcon(im);
                jButtonPesquisa.setIcon(ic);
            } else {
                jButtonPesquisa.setText(lingua.translate("Pesquisa"));
            }
        } catch (IOException e) {
        }
        jButtonPesquisa.setBorder(null);
        jButtonPesquisa.setBackground(new Color(51, 102, 153));
        jButtonPesquisa.setToolTipText(lingua.translate("Filtrar através de pesquisa"));
        if (mexeu) {
            jButtonAtualizar.setBorder(null);
            if (!ficou_o_mesmo) {
                if (pl != null) {
                    pl.go(true, null);
                }
            }
            mexeu = false;
        }

    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jButtonRequisitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRequisitarActionPerformed
        java.util.List<Keys.Material> mats = pl.getSelectedOnes();
        String atividade = reqatividade;
        java.util.List<Keys.ClassStudents> turmas = reqturmas;
        java.util.List<Keys.Subject> disciplinas = reqdisciplinas;
        TimeDate.Date data1 = new TimeDate.Date(jXDatePicker1.getDate());
        TimeDate.Date data2 = new TimeDate.Date(jXDatePicker2.getDate());
        TimeDate.Time tempo1 = this.getTime(jSpinner1);
        TimeDate.Time tempo2 = this.getTime(jSpinner2);
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            db.setAutoCommit(false);
            java.sql.Savepoint ponto = db.createSavepoint("voltar");
            int i = 0;
            int resultado = -1;
            boolean emprestado = false;
            while (i < mats.size()) {
                if (!WRequest.isMaterialInLateState(mats.get(i), url)) {
                    resultado = db.insertRequest(mats.get(i), pessoaescolhida, atividade, turmas, disciplinas, data1, data2, tempo1, tempo2);
                    if (resultado < 1) {
                        break;
                    }
                } else {
                    String aviso = "<html><div style='text-align:center'>" + lingua.translate("O recurso")+" \"" 
                            + lingua.translate(mats.get(i).getTypeOfMaterialName())+" "+mats.get(i).getDescription()+"\" "
                            + lingua.translate("está emprestado com atraso") + ".<br/>"
                            + lingua.translate("Mesmo assim, pretende fazer a requisição") + "?</div></html>";
                    Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.INFORMACAO, corborda, lingua.translate("Nota"), 400, 200, aviso, new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
                    int val = mensagem.showMessage();
                    if (val == 1) {
                        resultado = db.insertRequest(mats.get(i), pessoaescolhida, atividade, turmas, disciplinas, data1, data2, tempo1, tempo2);
                        if (resultado < 1) {
                            break;
                        }
                    }
                }
                i++;
            }
            if (i < mats.size()) {
                String envia = lingua.translate("Houve um problema com o registo das requisições") + ".";
                Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Nota"), 400, 200, envia, new String[]{lingua.translate("Voltar")});
                mensagem.showMessage();
                db.roolback(ponto);
            } else {
                Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.INFORMACAO, corborda, lingua.translate("Nota"), 400, 200, lingua.translate("A Requisição foi registada com sucesso") + ".", new String[]{lingua.translate("Voltar")});
                mensagem.showMessage();
                db.commit();
                Clavis.KeyQuest.refreshTables();
                jComboBoxTipoMaterial.setSelectedIndex(0);
                jComboBoxMaterial.setSelectedIndex(0);
                jSpinnerQuantidade.setValue(0);
                jComboBoxNomeUtilizador.setSelectedIndex(0);
                jButtonAlgoMais.setBorder(null);
                jButtonPesquisa.setBorder(null);
            }
            db.setAutoCommit(true);
            db.close();
        }
    }//GEN-LAST:event_jButtonRequisitarActionPerformed

    private void jButtonAlgoMaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlgoMaisActionPerformed
        WOthersPanel panel = new WOthersPanel(this, url, lingua, reqatividade, reqturmas, reqdisciplinas);
        Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.ACAO, Clavis.KeyQuest.getSystemColor(), lingua.translate("Adicionar outra informação"), 600, 400, panel.createPanelMoreInfo(500, 350), "", new String[]{"Confirmar", "Voltar"});
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
                jButtonAlgoMais.setBorder(null);
            }
        } else {
            reqatividade = "";
            reqturmas = new java.util.ArrayList<>();
            reqdisciplinas = new java.util.ArrayList<>();
            jButtonAlgoMais.setBorder(null);
        }
    }//GEN-LAST:event_jButtonAlgoMaisActionPerformed

    private void jButtonMaisUtilizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMaisUtilizadorActionPerformed
        Clavis.Windows.WUsers wu = new Clavis.Windows.WUsers(corfundo, corborda, url, lingua, this);
        wu.create();
        wu.setLocation(this.getX(), this.getY());
        wu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonMaisUtilizadorActionPerformed

    private void personalTextFieldEmailUtilizadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personalTextFieldEmailUtilizadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_personalTextFieldEmailUtilizadorActionPerformed

    private void init() {
        this.setTitle(lingua.translate("Registo de requisição"));
        this.setBackground(corfundo);
    }

    private void close() {
        this.setVisible(false);
        this.dispose();
    }

    public void appear() {
        this.setVisible(true);
    }

    public void create() {
        initComponents();
        init();
        jLabelRecurso.requestFocus();
        jComboBoxMaterial.setComponentFocus(jLabelRecurso);
        jComboBoxTipoMaterial.setComponentFocus(jLabelRecurso);
        jComboBoxNomeUtilizador.setComponentFocus(jLabelRecurso);
        spineditor = (javax.swing.JSpinner.NumberEditor) jSpinnerQuantidade.getEditor();
        spineditor.getFormat().setGroupingUsed(false);
        spineditor.getModel().setMinimum(0);
        spineditor.getModel().setValue(0);
        spineditor.getModel().setStepSize(1);
        spineditor.getTextField().setSelectionColor(Color.DARK_GRAY);
        javax.swing.JTextField tx = (javax.swing.JTextField) jComboBoxTipoMaterial.getComboBox().getEditor().getEditorComponent();
        tx.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        javax.swing.JTextField txm = (javax.swing.JTextField) jComboBoxMaterial.getComboBox().getEditor().getEditorComponent();
        txm.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        javax.swing.JTextField txmutilizador = (javax.swing.JTextField) jComboBoxNomeUtilizador.getComboBox().getEditor().getEditorComponent();
        txmutilizador.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        jComboBoxTipoMaterial.setHorizontalTextPosition((int) javax.swing.JLabel.LEFT);
        jComboBoxMaterial.setHorizontalTextPosition((int) javax.swing.JLabel.LEFT);
        jComboBoxNomeUtilizador.setHorizontalTextPosition((int) javax.swing.JLabel.LEFT);
        javax.swing.JSpinner.DefaultEditor editor = (javax.swing.JSpinner.DefaultEditor) jSpinnerQuantidade.getEditor();
        javax.swing.JTextField txx = editor.getTextField();
        txx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if ((int) jSpinnerQuantidade.getValue() > 1) {
                    jComboBoxMaterial.setSelectedIndex(0);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if ((int) jSpinnerQuantidade.getValue() > 1) {
                    jComboBoxMaterial.setSelectedIndex(0);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if ((int) jSpinnerQuantidade.getValue() > 1) {
                    jComboBoxMaterial.setSelectedIndex(0);
                }
            }
        });

        pl = new Components.PersonalLabel(jPanelConteudo, 120, 150, mlista, url);
        jSpinnerQuantidade.addChangeListener((ChangeEvent e) -> {
            if ((int) jSpinnerQuantidade.getValue() >= 0) {
                if (jComboBoxTipoMaterial.getSelectedIndex() > 0) {
                    int val = (int) jSpinnerQuantidade.getValue();
                    if (val == 0) {
                        jComboBoxMaterial.setSelectedIndex(0);
                    }
                    jPanelConteudo.removeAll();
                    pl.set(val);
                    pl.setNewList(mlista);
                    pl.go(false, null);
                } else {
                    jPanelConteudo.removeAll();
                    jPanelConteudo.revalidate();
                    jPanelConteudo.repaint();
                }
            }
            this.changeStateButtons();
        });
        entrou = false;
        jComboBoxTipoMaterial.addActionListener((ActionEvent e) -> {
            if ((jComboBoxTipoMaterial.getSelectedIndex() > 0) && (jComboBoxTipoMaterial.getSelectedIndex() != tipomaterialselecionado)) {
                tipomaterialselecionado = jComboBoxTipoMaterial.getSelectedIndex();
                tiposelecionado = tlista.get(tipomaterialselecionado - 1);
                updateComboMaterialBox(null);
                int val = (int) jSpinnerQuantidade.getValue();
                pl.set(val);
                pl.setNewList(mlista);
                pl.go(true, null);
            } else if (jComboBoxTipoMaterial.getSelectedIndex() == 0) {
                jComboBoxMaterial.removeAllItems();
                jComboBoxMaterial.setSelectedIndex(0);
                spineditor.getModel().setMaximum(1);
                jSpinnerQuantidade.setValue(0);
                tipomaterialselecionado = 0;
                tiposelecionado = null;
                bpesquisa = false;
                jButtonPesquisa.setBorder(null);
                try {
                    if (Clavis.KeyQuest.class.getResource("Images/lupa.png") != null) {
                        BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/lupa.png"));
                        ImageIcon ic = new ImageIcon(im);
                        jButtonPesquisa.setIcon(ic);
                    } else {
                        jButtonPesquisa.setText(lingua.translate("Pesquisa"));
                    }
                } catch (IOException ex) {
                }
                jButtonPesquisa.setBackground(new Color(51, 102, 153));
                jButtonPesquisa.setToolTipText(lingua.translate("Filtrar através de pesquisa"));
                jButtonAtualizar.setBorder(null);
                mexeu = false;
            }
            this.changeStateButtons();
        });
        jComboBoxMaterial.addActionListener((ActionEvent e) -> {
            if (jComboBoxMaterial.getSelectedIndex() > 0) {
                jSpinnerQuantidade.setValue(1);
                pl.go(false, (Keys.Material) jComboBoxMaterial.getSelectedItem());
            }
            this.changeStateButtons();
        });

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
            this.changeStateButtons();
        });
        if (DataBase.DataBase.testConnection(url)) {
            DefaultComboBoxModel<Keys.TypeOfMaterial> modelo = (DefaultComboBoxModel) jComboBoxTipoMaterial.getModel();
            DataBase.DataBase db = new DataBase.DataBase(url);
            tlista = db.getTypesOfMaterial();
            for (int h = 0; h < tlista.size(); h++) {
                if (tlista.get(h).getMaterialTypeID() == 1) {
                    Keys.TypeOfMaterial m = tlista.get(0);
                    tlista.set(0, tlista.get(h));
                    tlista.set(h, m);
                }
            }
            if (tlista.size() > 0) {
                int i = 0;
                for (Keys.TypeOfMaterial m : tlista) {
                    m = new Keys.TypeOfMaterial(m) {
                        @Override
                        public String toString() {
                            return lingua.translateWithPlural(getTypeOfMaterialName());
                        }
                    };
                    modelo.addElement(m);
                    if (mat != null) {
                        if (m.getMaterialTypeID() == mat.getMaterialTypeID()) {
                            jComboBoxTipoMaterial.setSelectedIndex(i + 1);
                        }
                    }
                    i++;
                }
                if (jComboBoxTipoMaterial.getSelectedIndex() < 1) {
                    jComboBoxTipoMaterial.setSelectedIndex(0);
                }
            }
            java.util.List<Keys.Person> p = db.getPersons();
            pessoas = p;
            int var;
            for (int i = 0; i < p.size() - 1; i++) {
                var = 2;
                for (int j = i + 1; j < p.size(); j++) {
                    p.set(i, new Keys.Person(p.get(i)) {
                        @Override
                        public String toString() {
                            return treatLongStrings(getName(), 80, jComboBoxNomeUtilizador.getComboBox().getEditor().getEditorComponent().getFont());
                        }
                    });
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
            jComboBoxNomeUtilizador.setSelectedIndex(0);
            personalTextFieldCodigoUtilizador.addActionListener((ActionEvent e) -> {
                boolean encontrou = false;
                for (Keys.Person pp : p) {
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
                this.changeStateButtons();
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
            personalTextFieldEmailUtilizador.addActionListener((ActionEvent e) -> {
                boolean encontrou = false;
                for (Keys.Person pp : p) {
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
                this.changeStateButtons();
            });

            jSpinner1.addChangeListener((ChangeEvent e) -> {
                TimeDate.Date date1 = new TimeDate.Date(jXDatePicker1.getDate());
                TimeDate.Date date2 = new TimeDate.Date(jXDatePicker2.getDate());
                TimeDate.Date datahoje = new TimeDate.Date();
                if (date1.isBigger(date2) == 0) {
                    TimeDate.Time tim = this.getTime(jSpinner1);
                    TimeDate.Time tim2 = this.getTime(jSpinner2);
                    if (date1.isBigger(datahoje) == 0) {
                        TimeDate.Time timatual = new TimeDate.Time();
                        if (timatual.compareTime(tim) < 0) {
                            jSpinner1.setValue(new Date());
                        }
                    }
                    if (tim.compareTime(tim2) < 0) {
                        jSpinner2.setValue(jSpinner1.getValue());
                    }
                } else if (datahoje.isBigger(date1) == 0) {
                    TimeDate.Time tim = this.getTime(jSpinner1);
                    TimeDate.Time timatual = new TimeDate.Time();
                    if (timatual.compareTime(tim) < 0) {
                        jSpinner1.setValue(new Date());
                    }
                }
                if (jComboBoxTipoMaterial.getSelectedIndex() > 0) {
                    jButtonAtualizar.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
                    mexeu = true;
                }
            });

            jSpinner2.addChangeListener((ChangeEvent e) -> {
                TimeDate.Date date1 = new TimeDate.Date(jXDatePicker1.getDate());
                TimeDate.Date date2 = new TimeDate.Date(jXDatePicker2.getDate());
                TimeDate.Date datahoje = new TimeDate.Date();
                if (date1.isBigger(date2) == 0) {
                    TimeDate.Time tim = this.getTime(jSpinner1);
                    TimeDate.Time tim2 = this.getTime(jSpinner2);
                    if (date1.isBigger(datahoje) == 0) {
                        TimeDate.Time timatual = new TimeDate.Time();
                        if (timatual.compareTime(tim2) < 0) {
                            jSpinner2.setValue(new Date());
                        }
                    }
                    if (tim2.compareTime(tim) > 0) {
                        jSpinner1.setValue(jSpinner2.getValue());
                    }
                } else if (datahoje.isBigger(date1) == 0) {
                    TimeDate.Time tim = this.getTime(jSpinner1);
                    TimeDate.Time timatual = new TimeDate.Time();
                    if (timatual.compareTime(tim) < 0) {
                        jSpinner1.setValue(new Date());
                    }
                }
                if (jComboBoxTipoMaterial.getSelectedIndex() > 0) {
                    jButtonAtualizar.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
                    mexeu = true;
                }
            });

            jXDatePicker1.addActionListener((ActionEvent e) -> {
                TimeDate.Date date1 = new TimeDate.Date(jXDatePicker1.getDate());
                TimeDate.Date date2 = new TimeDate.Date(jXDatePicker2.getDate());
                TimeDate.Date datahoje = new TimeDate.Date();
                if (date1.isBigger(datahoje) > 0) {
                    jXDatePicker1.setDate(new Date());
                } else if (date1.isBigger(date2) < 0) {
                    jXDatePicker2.setDate(jXDatePicker1.getDate());
                } else if (date1.isBigger(date2) == 0) {
                    TimeDate.Time tim = this.getTime(jSpinner1);
                    TimeDate.Time tim2 = this.getTime(jSpinner2);
                    if (date1.isBigger(datahoje) == 0) {
                        TimeDate.Time timatual = new TimeDate.Time();
                        if (timatual.compareTime(tim) > 0) {
                            jSpinner1.setValue(new Date());
                        }
                    }
                    if (tim.compareTime(tim2) < 0) {
                        jSpinner2.setValue(jSpinner1.getValue());
                    }
                } else if (datahoje.isBigger(date1) == 0) {
                    TimeDate.Time tim = this.getTime(jSpinner1);
                    TimeDate.Time timatual = new TimeDate.Time();
                    if (timatual.compareTime(tim) < 0) {
                        jSpinner1.setValue(new Date());
                    }
                }
                if (jComboBoxTipoMaterial.getSelectedIndex() > 0) {
                    jButtonAtualizar.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
                    mexeu = true;
                }
            });

            jXDatePicker2.addActionListener((ActionEvent e) -> {
                TimeDate.Date date1 = new TimeDate.Date(jXDatePicker1.getDate());
                TimeDate.Date date2 = new TimeDate.Date(jXDatePicker2.getDate());
                TimeDate.Date datahoje = new TimeDate.Date();
                if (date2.isBigger(date1) > 0) {
                    jXDatePicker1.setDate(jXDatePicker2.getDate());
                } else if (date1.isBigger(date2) == 0) {
                    TimeDate.Time tim = this.getTime(jSpinner1);
                    TimeDate.Time tim2 = this.getTime(jSpinner2);
                    if (date1.isBigger(datahoje) == 0) {
                        TimeDate.Time timatual = new TimeDate.Time();
                        if (timatual.compareTime(tim) < 0) {
                            jSpinner1.setValue(new Date());
                        }
                    }
                    if (tim.compareTime(tim2) < 0) {
                        jSpinner2.setValue(jSpinner1.getValue());
                    }
                } else if (datahoje.isBigger(date1) == 0) {
                    TimeDate.Time tim = this.getTime(jSpinner1);
                    TimeDate.Time timatual = new TimeDate.Time();
                    if (timatual.compareTime(tim) < 0) {
                        jSpinner1.setValue(new Date());
                        jSpinner1.repaint();
                    }
                }
                if (jComboBoxTipoMaterial.getSelectedIndex() > 0) {
                    jButtonAtualizar.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
                    mexeu = true;
                }
            });
            db.close();
        }
        jButtonSair.addActionListener((ActionEvent e) -> {
            this.close();
        });
        jComboBoxTipoMaterial.getComboBox().setLightWeightPopupEnabled(true);
        jComboBoxMaterial.getComboBox().setLightWeightPopupEnabled(true);
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapterImpl());
        pack();
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

    public void updateSelectPerson(java.util.List<Keys.Person> lista, Keys.Person pessoa) {
        pessoas = lista;
        int pos = -1;
        jComboBoxNomeUtilizador.removeAllItems();
        for (int i = 1; i < pessoas.size() + 1; i++) {
            jComboBoxNomeUtilizador.addItem(pessoas.get(i - 1));
            if (pessoas.get(i - 1).compareTo(pessoa) == 0) {
                pos = i;
            }
        }
        jComboBoxNomeUtilizador.setSelectedIndex(pos);
        jComboBoxNomeUtilizador.setSelectedIndex(pos);
        if (pos > 0) {
            if ((pessoa.getEmail().equals("")) || (pessoa.getEmail().equals("sem"))) {
                personalTextFieldEmailUtilizador.showWithCondition(lingua.translate("Não existe registo de email") + "!", jComboBoxNomeUtilizador.getSelectedIndex() > 0);
            } else {
                personalTextFieldEmailUtilizador.setText(pessoa.getEmail());
            }
            if ((pessoa.getIdentification().equals("")) || (pessoa.getIdentification().equals("sem"))) {
                personalTextFieldCodigoUtilizador.showWithCondition(lingua.translate("Não existe Identificação") + "!", jComboBoxNomeUtilizador.getSelectedIndex() > 0);
            } else {
                personalTextFieldCodigoUtilizador.setText(pessoa.getIdentification());
            }
        }
    }

    public static boolean checkHolidays(TimeDate.Date dinicio, TimeDate.Date dfim) {
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

    public static boolean isMaterialInLateState(Keys.Material material, String url) {
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

    public static final void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            WRequest wr = new WRequest();
            wr.create();
            wr.setVisible(true);
        });
    }

    public static String treatLongStrings(String l, int tamanho, Font font) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        int dimensao = (int) (font.getStringBounds(l, frc).getWidth());
        if (dimensao > tamanho) {
            String[] texto = l.split(" ");
            l = l.replace(texto[0], "");
            l = l.replace(texto[texto.length - 1], "");
            int i = 0;
            while (i < texto.length) {
                if (texto[i].length() > 3) {
                    l = l.replace(texto[i], texto[i].charAt(0) + ".");
                } else {
                    l = l.replace(texto[i], "");
                }
                dimensao = (int) (font.getStringBounds(l, frc).getWidth());
                if (dimensao < tamanho) {
                    break;
                }
                i++;
            }
            return texto[0] + l + texto[texto.length - 1];
        }
        return l;
    }

    public static Keys.Person getSelectedPerson() {
        return pessoaescolhida;
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

    private synchronized void updateComboMaterialBox(java.util.Set<Keys.Material> lista) {
        if (lista == null) {
            if (jComboBoxTipoMaterial.getSelectedIndex() > 0) {
                jComboBoxMaterial.removeAllItems();
                DataBase.DataBase db = new DataBase.DataBase(url);
                java.util.Date datad = jXDatePicker1.getDate();
                Calendar f = Calendar.getInstance();
                f.setTime(datad);
                TimeDate.Date dat1 = new TimeDate.Date(jXDatePicker1.getDate());
                TimeDate.Date dat2 = new TimeDate.Date(jXDatePicker2.getDate());
                TimeDate.Time tim1 = getTime(jSpinner1);
                TimeDate.Time tim2 = getTime(jSpinner2);
                if (dat1.isBigger(dat2) >= 0) {
                    mlista = db.getFreeMaterialsBetweenDates(tlista.get(jComboBoxTipoMaterial.getSelectedIndex() - 1).getMaterialTypeID(), dat1, dat2, tim1, tim2);
                    DefaultComboBoxModel<Keys.Material> modelo = (DefaultComboBoxModel) jComboBoxMaterial.getModel();
                    int i = 0;
                    int valor = 0;
                    for (Keys.Material n : mlista) {
                        if (n.getMaterialTypeID() == 1) {
                            n = new Keys.Material(n) {
                                @Override
                                public String toString() {
                                    return this.getTypeOfMaterialName() + " " + this.getDescription();
                                }
                            };
                        }
                        modelo.addElement(n);
                        if ((mat != null) && (n.getId() == mat.getId()) && (!entrou)) {
                            valor = i;
                            entrou = true;
                        }
                        i++;
                    }
                    jComboBoxMaterial.setSelectedIndex(valor);
                    spineditor.getModel().setMaximum(jComboBoxMaterial.getItemCount());
                    if (mlista.size() < (int) jSpinnerQuantidade.getValue()) {
                        jSpinnerQuantidade.setValue(mlista.size());
                    }
                }
            }
        } else {
            mlista = lista;
            int valorsel = jComboBoxMaterial.getSelectedIndex();
            jComboBoxMaterial.removeAllItems();
            DefaultComboBoxModel<Keys.Material> modelo = (DefaultComboBoxModel) jComboBoxMaterial.getModel();
            for (Keys.Material n : mlista) {
                n = new Keys.Material(n) {
                    @Override
                    public String toString() {
                        return this.getTypeOfMaterialName() + " " + this.getDescription();
                    }
                };
                modelo.addElement(n);
            }

            spineditor.getModel().setMaximum(jComboBoxMaterial.getItemCount());
            if (mlista.size() < (int) jSpinnerQuantidade.getValue()) {
                jSpinnerQuantidade.setValue(mlista.size());
            }
            int val = (int) jSpinnerQuantidade.getValue();
            pl.set(val);
            pl.setNewList(mlista);
            pl.go(true, null);
            if (valorsel >= 0) {
                Keys.Material m = pl.getSelectedOnes().get(0);
                int i = 0;
                for (Keys.Material b : mlista) {
                    if (b.compareTo(m) == 0) {
                        jComboBoxMaterial.setSelectedIndex(i + 1);
                        break;
                    }
                    i++;
                }
            } else {
                jComboBoxMaterial.setSelectedIndex(0);
            }
            if (jComboBoxTipoMaterial.getSelectedIndex() > 0) {
                jButtonPesquisa.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
                jButtonPesquisa.setBackground(new Color(253, 67, 67));
                try {
                    if (Clavis.KeyQuest.class.getResource("Images/limpar.png") != null) {
                        BufferedImage im3 = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/limpar.png"));
                        ImageIcon ic3 = new ImageIcon(im3);
                        jButtonPesquisa.setIcon(ic3);
                    } else {
                        jButtonPesquisa.setText(lingua.translate("Limpar"));
                    }
                } catch (IOException e) {
                }
                jButtonPesquisa.setToolTipText(lingua.translate("Limpar seleção por pesquisa"));
                bpesquisa = true;
                mexeu = true;
            }
        }
    }

    private void changeStateButtons() {
        if ((pessoaescolhida != null) && (jComboBoxTipoMaterial.getSelectedIndex() > 0)) {
            jButtonAlgoMais.setEnabled(true);
            TimeDate.Date data1 = new TimeDate.Date(jXDatePicker1.getDate());
            TimeDate.Date data2 = new TimeDate.Date(jXDatePicker2.getDate());
            if (((int) jSpinnerQuantidade.getValue()) > 0) {
                if (!WRequest.checkHolidays(data1, data2)){
                    jButtonRequisitar.setEnabled(true);
                } else {
                    jButtonRequisitar.setEnabled(false);
                }
            } else {
                jButtonRequisitar.setEnabled(false);
            }
            jButtonPesquisa.setEnabled(true);
        } else if (jComboBoxTipoMaterial.getSelectedIndex() > 0) {
            jButtonAlgoMais.setEnabled(true);
            jButtonPesquisa.setEnabled(true);
            jButtonRequisitar.setEnabled(false);
        } else {
            jButtonPesquisa.setEnabled(false);
            jButtonAlgoMais.setEnabled(false);
            jButtonRequisitar.setEnabled(false);
        }
    }

    public static int getSizeOfString(String l, Font font) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return (int) (font.getStringBounds(l, frc).getWidth());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlgoMais;
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonMaisUtilizador;
    private javax.swing.JButton jButtonPesquisa;
    private javax.swing.JButton jButtonRequisitar;
    private javax.swing.JButton jButtonSair;
    /*
    private javax.swing.JComboBox<String> jComboBoxM;
    */
    private javax.swing.JComboBox<Object> jComboBoxM;
    /*
    private javax.swing.JComboBox<String> jComboBoxNomeU;
    */
    private javax.swing.JComboBox<Object> jComboBoxNomeU;
    /*
    private javax.swing.JComboBox<String> jComboBoxTipoM;
    */
    private javax.swing.JComboBox<Object> jComboBoxTipoM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelPessoa;
    private static javax.swing.JLabel jLabelRecurso;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelConteudo;
    private javax.swing.JPanel jPanelInicial;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinnerQuantidade;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private Components.PersonalTextField personalTextFieldCodigoUtilizador;
    private Components.PersonalTextField personalTextFieldEmailUtilizador;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the tlista
     */
    public java.util.List<Keys.TypeOfMaterial> getTypeMaterialList() {
        return tlista;
    }

    /**
     * @param tlista the tlista to set
     */
    public void setTypeMaterialList(java.util.List<Keys.TypeOfMaterial> tlista) {
        this.tlista = tlista;
    }

    private class WindowAdapterImpl extends WindowAdapter {

        public WindowAdapterImpl() {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            close();
        }
    }

    public java.util.Set<Keys.Material> getMaterialsList() {
        return mlista;
    }

    public void setMaterialsList(java.util.Set<Keys.Material> lista) {
        int val = jComboBoxMaterial.getSelectedIndex() - 1;
        if (val >= 0) {
            int i = 0;
            Keys.Material m = null;
            for (Keys.Material mo : mlista) {
                if (i == val) {
                    m = mo;
                    break;
                }
                i++;
            }
            this.updateComboMaterialBox(lista);
            if (m != null) {
                int g = 0;
                for (Keys.Material mo : mlista) {
                    if (m.compareTo(mo) == 0) {
                        jComboBoxMaterial.setSelectedIndex(g + 1);
                        break;
                    }
                    g++;
                }
            }
        } else {
            this.updateComboMaterialBox(lista);
        }
    }

    public java.util.List<Keys.Person> getPersonsList() {
        return pessoas;
    }

    public static void updateComboMaterialOnSpecialRequest(Keys.Material m) {
        int i = 0;
        for (Keys.Material mo : mlista) {
            if (mo.compareTo(m) == 0) {
                jComboBoxMaterial.setSelectedIndex(i + 1);
                jLabelRecurso.requestFocus();
                break;
            }
            i++;
        }
    }
}
