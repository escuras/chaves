/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import Clavis.ButtonListRequest;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Savepoint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author toze
 */
public class WChangeRequest extends javax.swing.JFrame {

    /**
     * Creates new form WChangeRequest
     */
    private static final long serialVersionUID = 123L;
    public static final Color COLOR_PANEL = new Color(245, 250, 250);
    private Color corfundo;
    private Color corborda;
    private String url;
    private Langs.Locale lingua;
    private Keys.Request selecionada;
    private BufferedImage imagem;
    private Components.PersonalCombo jComboBoxMaterial;
    private java.util.List<Keys.Material> mlista;
    private TimeDate.Date data1;
    private TimeDate.Date data2;
    private TimeDate.Time tempo1;
    private TimeDate.Time tempo2;
    private Keys.Material mselecionado;
    private Color colorpanel;

    public WChangeRequest(Color corfundo, Color corborda, String url, Langs.Locale lingua, Keys.Request selecionada) {
        this.corfundo = corfundo;
        this.corborda = corborda;
        this.lingua = lingua;
        this.selecionada = selecionada;
        mlista = null;
        this.url = url;
        this.colorpanel = COLOR_PANEL;
    }

    public void create() {
        initComponents();
        makePanel();
        this.setTitle(lingua.translate("Substituir requisição"));
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        if (selecionada.getMaterial().getMaterialImage().equals("sem")) {
            try {
                File file = new File(new File("").getAbsolutePath()
                        + System.getProperty("file.separator")
                        + "Resources" + System.getProperty("file.separator")
                        + "Images" + System.getProperty("file.separator")
                        + selecionada.getMaterial().getTypeOfMaterialImage() + ".png");
                if (file.isFile()) {
                    imagem = ImageIO.read(file);
                    ImageIcon ic = new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(imagem, 95, 90));
                    jLabelImagem.setIcon(ic);
                }
            } catch (IOException ex) {
                Logger.getLogger(ButtonListRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            imagem = FileIOAux.ImageAux.transformFromBase64IntoImage(selecionada.getMaterial().getMaterialImage());
            if (imagem != null) {
                ImageIcon ic = new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(imagem, 95, 90));
                jLabelImagem.setIcon(ic);
            }
        }
        this.makeFileRequest();
        this.addBehaviorToLabelImage();
        this.jSpinnerDataEntrega.setValue(this.toSystemDate(selecionada.getEndDate().toString()));
        this.jSpinnerDataLevantamento.setValue(this.toSystemDate(selecionada.getBeginDate().toString()));
        this.jSpinnerHoraEntrega.setValue(this.toSystemTime(selecionada.getTimeEnd().toString()));
        this.jSpinnerHoraLevantamento.setValue(this.toSystemTime(selecionada.getTimeBegin().toString()));
        this.makeValidDate();
        this.makeComboBoxMaterial(selecionada.getBeginDate(), selecionada.getEndDate(), selecionada.getTimeBegin(), selecionada.getTimeEnd());
        jLabelRequisicaoNova.requestFocus();
        if (mlista != null) {
            for (int i = 0; i < mlista.size(); i++) {
                if (mlista.get(i).compareTo(selecionada.getMaterial()) == 0) {
                    jComboBoxMaterial.setSelectedIndex(i + 1);
                    break;
                }
            }
        }
    }

    private Date toSystemDate(String value) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyyy");
        Date date = null;
        try {
            date = dt.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(WChangeRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    private Date toSystemTime(String value) {
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = dt.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(WChangeRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public void appear() {
        Dimension m = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (m.getWidth() / 2 - this.getWidth() / 2);
        int y = (int) (m.getHeight() / 2 - this.getHeight() / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    private void makePanel() {
        Border b = BorderFactory.createLineBorder(corborda, 4);
        jPanelInicial.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createLineBorder(Color.BLACK)));
        jPanelInicial.setBackground(corfundo);
    }

    private void makeComboBoxMaterial(TimeDate.Date dat1, TimeDate.Date dat2, TimeDate.Time tim1, TimeDate.Time tim2) {
        if (DataBase.DataBase.testConnection(url)) {
            DataBase.DataBase db = new DataBase.DataBase(url);
            mlista = new java.util.ArrayList<>(db.getFreeMaterialsBetweenDates(selecionada.getMaterial().getMaterialTypeID(), dat1, dat2, tim1, tim2));
            if (db.isTheOnlyRequest(selecionada, dat1, dat2, tim1, tim2)) {
                boolean t = true;
                for (int i = 0; i < mlista.size(); i++) {
                    if (mlista.get(i).compareTo(selecionada.getMaterial()) == 0) {
                        t = false;
                        break;
                    }
                }
                if (t) {
                    mlista.add(selecionada.getMaterial());
                }
            }
            db.close();
            Collections.sort(mlista);
            DefaultComboBoxModel<Object> modelo = (DefaultComboBoxModel<Object>) jComboBoxMaterial.getModel();
            modelo.removeAllElements();
            modelo.addElement("");
            for (int i = 0; i < mlista.size(); i++) {
                if (mlista.get(i).getMaterialTypeID() == 1) {
                    mlista.set(i, new Keys.Material(mlista.get(i)) {
                        @Override
                        public String toString() {
                            return lingua.translate(this.getTypeOfMaterialName()) + " " + this.getDescription();
                        }
                    });
                }
                modelo.addElement(mlista.get(i).toString());
            }
            jComboBoxMaterial.setSelectedIndex(0);
        }
    }

    private boolean checkHolidays() {
        TimeDate.Date dinicio = this.getDate(jSpinnerDataLevantamento);
        TimeDate.Date dfim = this.getDate(jSpinnerDataEntrega);
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

    private void makeFileRequest() {
        jLabelUtilizador2.setText(Clavis.Windows.WRequest.treatLongStrings(selecionada.getPerson().getName(), 100, jLabelRecurso2.getFont()));
        jLabelRecurso2.setText(lingua.translate(selecionada.getMaterial().getTypeOfMaterialName()) + " " + selecionada.getMaterial().getDescription());
        jLabelInicioData2.setText(selecionada.getBeginDate().toStringWithMonthWord(lingua));
        jLabelInicioHora2.setText(selecionada.getTimeBegin().toString(0));
        jLabelFimData2.setText(selecionada.getEndDate().toStringWithMonthWord(lingua));
        jLabelFimHora2.setText(selecionada.getTimeEnd().toString(0));
        String[] satividade = selecionada.getActivity().split(":::");
        if (satividade.length > 1) {
            Components.PopUpMenu pop = new Components.PopUpMenu(satividade, lingua);
            pop.create();
            jLabelAtividade2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pop.show(e.getComponent(), e.getX(), e.getY());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    pop.setVisible(false);
                }
            });
        }
        jLabelAtividade2.setText(lingua.translate(satividade[0]));
        if (!selecionada.isMultiDisciplinar()) {
            jLabelDisciplina2.setText(lingua.translate(selecionada.getSubject().toString()));
        } else {
            java.util.Iterator<Keys.Subject> iter = selecionada.getSubjects().iterator();
            String[] sdisciplinas = new String[selecionada.getSubjects().size()];
            int i = 0;
            Keys.Subject s;
            while (iter.hasNext()) {
                s = iter.next();
                sdisciplinas[i] = s.getName() + " (" + s.getCode() + ")";
                i++;
            }
            Components.PopUpMenu pop = new Components.PopUpMenu(sdisciplinas);
            pop.create();
            jLabelDisciplina2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    pop.show(jLabelDisciplina2, e.getX(), e.getY());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    pop.setVisible(false);
                }
            });
            jLabelDisciplina2.setText(lingua.translate("Múltiplas disciplinas"));
        }

    }

    private void addBehaviorToLabelImage() {
        jLabelImagem.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Clavis.Windows.WShowImage ws = new Clavis.Windows.WShowImage(FileIOAux.ImageAux.resize(imagem, 700, 500), 700, 500);
                ws.create();
                ws.appear();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabelImagem.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(FileIOAux.ImageAux.makeWhiter(imagem, 80), 95, 90)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (imagem != null) {
                    jLabelImagem.setIcon(new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(imagem, 95, 90)));
                }
            }
        });
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
        jPanelDados = new javax.swing.JPanel();
        jLabelUtilizador = new javax.swing.JLabel();
        jLabelRecurso = new javax.swing.JLabel();
        jLabelUtilizador2 = new javax.swing.JLabel();
        jLabelInicio = new javax.swing.JLabel();
        jLabelInicioData = new javax.swing.JLabel();
        jLabelInicioHora = new javax.swing.JLabel();
        jLabelInicioData2 = new javax.swing.JLabel();
        jLabelInicioHora2 = new javax.swing.JLabel();
        jLabelFim = new javax.swing.JLabel();
        jLabelFimData = new javax.swing.JLabel();
        jLabelFimHora = new javax.swing.JLabel();
        jLabelFimData2 = new javax.swing.JLabel();
        jLabelFimHora2 = new javax.swing.JLabel();
        jLabelRecurso2 = new javax.swing.JLabel();
        jLabelAtividade = new javax.swing.JLabel();
        jLabelAtividade2 = new javax.swing.JLabel();
        jLabelDisciplina2 = new javax.swing.JLabel();
        jLabelDisciplina = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanelAlteracao = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSpinnerHoraLevantamento = new javax.swing.JSpinner();
        jSpinnerDataLevantamento = new javax.swing.JSpinner();
        jXLabelLevantamento = new org.jdesktop.swingx.JXLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelNovasDatas = new javax.swing.JLabel();
        jComboBoxMaterial = new Components.PersonalCombo(jLabelUtilizador);
        jComboBoxM = jComboBoxMaterial.getComboBox();
        jPanel6 = new javax.swing.JPanel();
        jSpinnerHoraEntrega = new javax.swing.JSpinner();
        jSpinnerDataEntrega = new javax.swing.JSpinner();
        jLabelEntrega2 = new org.jdesktop.swingx.JXLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelMaterial = new javax.swing.JLabel();
        jLabelRequisicaoVelha = new javax.swing.JLabel();
        jLabelImagem = new javax.swing.JLabel();
        jLabelRequisicaoNova = new javax.swing.JLabel();
        jButtonSair = new javax.swing.JButton();
        jButtonConfirmarAlteracao = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 600));

        jPanelInicial.setBackground(new java.awt.Color(254, 254, 254));
        jPanelInicial.setPreferredSize(new java.awt.Dimension(600, 400));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setCornerSize(6);
        dropShadowBorder1.setShadowSize(3);
        dropShadowBorder1.setShowLeftShadow(true);
        jPanel1.setBorder(dropShadowBorder1);

        jPanelDados.setBackground(new java.awt.Color(245, 250, 250));
        jPanelDados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelUtilizador.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N

        jLabelRecurso.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabelRecurso.setMaximumSize(new java.awt.Dimension(114, 90));
        jLabelRecurso.setMinimumSize(new java.awt.Dimension(114, 90));
        jLabelRecurso.setPreferredSize(new java.awt.Dimension(114, 90));

        jLabelUtilizador2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setCornerSize(6);
        dropShadowBorder2.setShadowSize(2);
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jLabelUtilizador2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder2, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelUtilizador2.setOpaque(true);

        jLabelInicio.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N

        jLabelInicioData.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N

        jLabelInicioHora.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N

        jLabelInicioData2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setCornerSize(6);
        dropShadowBorder3.setShadowSize(2);
        dropShadowBorder3.setShowLeftShadow(true);
        dropShadowBorder3.setShowTopShadow(true);
        jLabelInicioData2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder3, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelInicioData2.setOpaque(true);

        jLabelInicioHora2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder4 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder4.setCornerSize(6);
        dropShadowBorder4.setShadowSize(2);
        dropShadowBorder4.setShowLeftShadow(true);
        dropShadowBorder4.setShowTopShadow(true);
        jLabelInicioHora2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder4, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelInicioHora2.setOpaque(true);

        jLabelFim.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N

        jLabelFimData.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N

        jLabelFimHora.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N

        jLabelFimData2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder5 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder5.setCornerSize(6);
        dropShadowBorder5.setShadowSize(2);
        dropShadowBorder5.setShowLeftShadow(true);
        dropShadowBorder5.setShowTopShadow(true);
        jLabelFimData2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder5, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelFimData2.setOpaque(true);

        jLabelFimHora2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder6 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder6.setCornerSize(6);
        dropShadowBorder6.setShadowSize(2);
        dropShadowBorder6.setShowLeftShadow(true);
        dropShadowBorder6.setShowTopShadow(true);
        jLabelFimHora2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder6, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelFimHora2.setOpaque(true);

        jLabelRecurso2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder7 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder7.setCornerSize(6);
        dropShadowBorder7.setShadowSize(2);
        dropShadowBorder7.setShowLeftShadow(true);
        dropShadowBorder7.setShowTopShadow(true);
        jLabelRecurso2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder7, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelRecurso2.setOpaque(true);

        jLabelAtividade.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N

        jLabelAtividade2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder8 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder8.setCornerSize(6);
        dropShadowBorder8.setShadowSize(2);
        dropShadowBorder8.setShowLeftShadow(true);
        dropShadowBorder8.setShowTopShadow(true);
        jLabelAtividade2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder8, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelAtividade2.setOpaque(true);

        jLabelDisciplina2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder9 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder9.setCornerSize(6);
        dropShadowBorder9.setShadowSize(2);
        dropShadowBorder9.setShowLeftShadow(true);
        dropShadowBorder9.setShowTopShadow(true);
        jLabelDisciplina2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder9, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelDisciplina2.setOpaque(true);

        jLabelDisciplina.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(jLabelAtividade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAtividade2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDisciplina2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabelInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelRecurso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelInicioData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelInicioHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelFimData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addComponent(jLabelFimHora, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelFimHora2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                        .addComponent(jLabelFimData2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabelInicioHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelInicioData2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabelUtilizador2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRecurso2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)))
                .addGap(16, 16, 16))
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUtilizador2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRecurso2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInicioData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInicioData2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelFimData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInicioHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelInicioHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabelFimData2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFimHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFimHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAtividade2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDisciplina2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jLabelUtilizador.setText(lingua.translate("Utilizador")+":");
        jLabelRecurso.setText(lingua.translate("Recurso")+":");
        jLabelInicio.setText(lingua.translate("Início")+":");
        jLabelInicioData.setText(lingua.translate("Data")+":");
        jLabelInicioHora.setText(lingua.translate("Hora")+":");
        jLabelFim.setText(lingua.translate("Fim")+":");
        jLabelFimData.setText(lingua.translate("Data")+":");
        jLabelFimHora.setText(lingua.translate("Hora")+":");
        jLabelAtividade.setText(lingua.translate("Atividade")+":");
        jLabelDisciplina.setText(lingua.translate("Disciplina")+":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder10 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder10.setCornerSize(6);
        dropShadowBorder10.setShadowSize(3);
        dropShadowBorder10.setShowLeftShadow(true);
        jPanel2.setBorder(dropShadowBorder10);

        jPanelAlteracao.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder11 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder11.setCornerSize(6);
        dropShadowBorder11.setShadowSize(2);
        dropShadowBorder11.setShowLeftShadow(true);
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder11, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))), null));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabelLevantamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerDataLevantamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinnerHoraLevantamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabelLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerDataLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
        jLabel1.setText(lingua.translate("Data")+":");
        jLabel2.setText(lingua.translate("Hora")+":");

        jLabelNovasDatas.setMaximumSize(new java.awt.Dimension(444444, 26));
        jLabelNovasDatas.setMinimumSize(new java.awt.Dimension(104, 26));
        jLabelNovasDatas.setPreferredSize(new java.awt.Dimension(104, 26));

        jComboBoxMaterial.setHelpText(lingua.translate("Escolha de recurso")+" ...");
        jComboBoxM.setMinimumSize(new java.awt.Dimension(35, 28));
        jComboBoxM.setPreferredSize(new java.awt.Dimension(35, 28));

        jPanel6.setBackground(new java.awt.Color(250, 250, 250));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder12 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder12.setCornerSize(6);
        dropShadowBorder12.setShadowSize(2);
        dropShadowBorder12.setShowLeftShadow(true);
        jPanel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder12, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jSpinnerHoraEntrega.setMaximumSize(new java.awt.Dimension(148, 30));
        jSpinnerHoraEntrega.setMinimumSize(new java.awt.Dimension(148, 30));
        jSpinnerHoraEntrega.setPreferredSize(new java.awt.Dimension(148, 30));

        jSpinnerDataEntrega.setPreferredSize(new java.awt.Dimension(148, 30));

        jLabelEntrega2.setBackground(new java.awt.Color(240, 238, 238));
        jLabelEntrega2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelEntrega2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEntrega2.setMaximumSize(new java.awt.Dimension(260, 30));
        jLabelEntrega2.setMinimumSize(new java.awt.Dimension(269, 30));
        jLabelEntrega2.setOpaque(true);
        jLabelEntrega2.setPreferredSize(new java.awt.Dimension(269, 30));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelEntrega2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerDataEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinnerHoraEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEntrega2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinnerHoraEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSpinnerHoraEntrega.setModel(new SpinnerDateModel());
        jSpinnerHoraEntrega.setEditor(new JSpinner.DateEditor(jSpinnerHoraEntrega, "HH:mm"));
        javax.swing.JFormattedTextField ffjSpinnerHoraEntrega2 = (javax.swing.JFormattedTextField)((javax.swing.JSpinner.DateEditor)jSpinnerHoraEntrega.getEditor()).getComponent(0);
        ffjSpinnerHoraEntrega2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jSpinnerDataEntrega.setModel(new SpinnerDateModel());
        jSpinnerDataEntrega.setEditor(new JSpinner.DateEditor(jSpinnerDataEntrega, "dd/MM/yyyy"));
        javax.swing.JFormattedTextField ffSpinner4 = (javax.swing.JFormattedTextField)((javax.swing.JSpinner.DateEditor)jSpinnerDataEntrega.getEditor()).getComponent(0);
        ffSpinner4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabelEntrega2.setText(lingua.translate("Fim da requisição"));
        jLabel7.setText(lingua.translate("Data")+":");
        jLabel8.setText(lingua.translate("Hora")+":");

        jLabelMaterial.setMaximumSize(new java.awt.Dimension(444444, 26));
        jLabelMaterial.setMinimumSize(new java.awt.Dimension(104, 26));
        jLabelMaterial.setPreferredSize(new java.awt.Dimension(104, 26));

        javax.swing.GroupLayout jPanelAlteracaoLayout = new javax.swing.GroupLayout(jPanelAlteracao);
        jPanelAlteracao.setLayout(jPanelAlteracaoLayout);
        jPanelAlteracaoLayout.setHorizontalGroup(
            jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlteracaoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelNovasDatas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addComponent(jLabelMaterial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelAlteracaoLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlteracaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxM, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanelAlteracaoLayout.setVerticalGroup(
            jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlteracaoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNovasDatas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadow = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadow.setCornerSize(6);
        dropShadow.setShadowSize(3);
        dropShadow.setShowLeftShadow(true);
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadow, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jLabelNovasDatas.setText(lingua.translate("Novas datas")+": ");
        jComboBoxMaterial.create();
        jComboBoxMaterial.setHorizontalTextPosition(javax.swing.JLabel.CENTER);
        jLabelMaterial.setText(lingua.translate("Recurso")+": ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanelAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAlteracao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabelRequisicaoVelha.setBackground(new java.awt.Color(245, 245, 245));
        jLabelRequisicaoVelha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRequisicaoVelha.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 3, this.corfundo), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jLabelRequisicaoVelha.setOpaque(true);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder13 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder13.setCornerSize(6);
        dropShadowBorder13.setShadowSize(2);
        dropShadowBorder13.setShowLeftShadow(true);
        dropShadowBorder13.setShowTopShadow(true);
        jLabelImagem.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder13, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jLabelImagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelImagem.setOpaque(true);

        jLabelRequisicaoNova.setBackground(new java.awt.Color(245, 245, 245));
        jLabelRequisicaoNova.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRequisicaoNova.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 3, this.corfundo), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jLabelRequisicaoNova.setOpaque(true);

        jButtonSair.setBackground(new java.awt.Color(1, 1, 1));
        jButtonSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSair.setFocusPainted(false);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jButtonConfirmarAlteracao.setBackground(new java.awt.Color(51, 102, 203));
        jButtonConfirmarAlteracao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonConfirmarAlteracao.setEnabled(false);
        jButtonConfirmarAlteracao.setFocusPainted(false);
        jButtonConfirmarAlteracao.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonConfirmarAlteracao.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonConfirmarAlteracao.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonConfirmarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarAlteracaoActionPerformed(evt);
            }
        });

        jButtonEditar.setBackground(new java.awt.Color(51, 102, 203));
        jButtonEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEditar.setFocusPainted(false);
        jButtonEditar.setMaximumSize(new java.awt.Dimension(90, 40));
        jButtonEditar.setMinimumSize(new java.awt.Dimension(90, 40));
        jButtonEditar.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInicialLayout = new javax.swing.GroupLayout(jPanelInicial);
        jPanelInicial.setLayout(jPanelInicialLayout);
        jPanelInicialLayout.setHorizontalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelInicialLayout.createSequentialGroup()
                            .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelRequisicaoVelha, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonConfirmarAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelRequisicaoNova, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanelInicialLayout.setVerticalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelRequisicaoVelha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addComponent(jLabelRequisicaoNova, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConfirmarAlteracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabelRequisicaoVelha.setText(lingua.translate("Dados da requisição"));
        jLabelImagem.setToolTipText(lingua.translate("Clique para ver a imagem maior")+".");
        jLabelRequisicaoNova.setText(lingua.translate("Dados para alteração"));
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
        jButtonConfirmarAlteracao.setToolTipText(lingua.translate("Comfirme os novos valores")+".");
        try {
            if (Clavis.KeyQuest.class.getResource("Images/ok.png") != null) {
                BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
                ImageIcon imo = new ImageIcon(im);
                jButtonConfirmarAlteracao.setIcon(imo);
            } else {
                jButtonConfirmarAlteracao.setText(lingua.translate("Confirmar"));
            }
        } catch(IOException e) {}
        jButtonEditar.setToolTipText(lingua.translate("Registe os novos valores")+".");
        try {
            if (Clavis.KeyQuest.class.getResource("Images/substituir.png") != null) {
                BufferedImage im = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/substituir.png"));
                ImageIcon imo = new ImageIcon(im);
                jButtonEditar.setIcon(imo);
            } else {
                jButtonEditar.setText(lingua.translate("Editar"));
            }
        } catch(IOException e) {}

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        TimeDate.Date dat1 = getDate(jSpinnerDataLevantamento);
        TimeDate.Date dat2 = getDate(jSpinnerDataEntrega);
        TimeDate.Time tim1 = getTime(jSpinnerHoraLevantamento);
        TimeDate.Time tim2 = getTime(jSpinnerHoraEntrega);
        if (jComboBoxM.getSelectedIndex() > 0) {
            if (!checkForTheSameRequest()) {
                if (!this.checkHolidays()) {
                    jButtonConfirmarAlteracao.setEnabled(true);
                    int val = jComboBoxMaterial.getSelectedIndex() - 1;
                    mselecionado = mlista.get(val);
                    if (mselecionado.getMaterialImage().equals("sem")) {
                        try {
                            File file = new File(new File("").getAbsolutePath()
                                    + System.getProperty("file.separator")
                                    + "Resources" + System.getProperty("file.separator")
                                    + "Images" + System.getProperty("file.separator")
                                    + selecionada.getMaterial().getTypeOfMaterialImage() + ".png");
                            if (file.isFile()) {
                                imagem = ImageIO.read(file);
                                ImageIcon ic = new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(imagem, 95, 90));
                                jLabelImagem.setIcon(ic);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ButtonListRequest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        imagem = FileIOAux.ImageAux.transformFromBase64IntoImage(mselecionado.getMaterialImage());
                        if (imagem != null) {
                            ImageIcon ic = new javax.swing.ImageIcon(FileIOAux.ImageAux.resize(imagem, 95, 90));
                            jLabelImagem.setIcon(ic);
                        }
                    }
                    jLabelRecurso2.setText(mselecionado.toString());
                    jLabelInicioData2.setText(dat1.toString());
                    jLabelFimData2.setText(dat2.toString());
                    jLabelInicioHora2.setText(tim1.toString(0));
                    jLabelFimHora2.setText(tim2.toString(0));
                    jPanelDados.setBackground(new Color(255, 250, 250));
                    data1 = dat1;
                    data2 = dat2;
                    tempo1 = tim1;
                    tempo2 = tim2;
                } else {
                    Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Aviso"), 400, 200, lingua.translate("As datas escolhidas colidem com feriados") + ".", new String[]{"Voltar"});
                    mensagem.showMessage();
                }
            } else {
                Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Aviso"), 400, 200, lingua.translate("A requisição tem os mesmos valores da original") + ".", new String[]{"Voltar"});
                mensagem.showMessage();
            }
        } else {
            Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Aviso"), 400, 200, lingua.translate("Escolha um recurso") + ".", new String[]{"Voltar"});
            mensagem.showMessage();
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonConfirmarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarAlteracaoActionPerformed
        if (mselecionado != null) {
            if ((data1.isBigger(data2) == 0) && (tempo1.compareTime(tempo2) == 0)) {
                tempo2 = tempo2.addSeconds(60 * 10);
                if ((tempo2.getHour() == 0) && (tempo1.getHour() == 23)) {
                    data2 = data2.dateAfter(1);
                }
            }
            if (DataBase.DataBase.testConnection(url)) {
                DataBase.DataBase db = new DataBase.DataBase(url);
                db.setAutoCommit(false);
                int tnovo = 0;
                int toriginal = 0;

                Savepoint p = db.createSavepoint("ssss");

                java.util.Set<Keys.Request> lista = db.getUnionRequests(selecionada);
                if (lista.size() > 0) {
                    int intervalo = data1.isBigger(data2);
                    if (intervalo >= 1) {
                        tnovo += tempo1.compareTime(new TimeDate.Time(23, 59, 59));
                        tnovo += new TimeDate.Time(0, 0, 0).compareTime(tempo2);
                        int i = 1;
                        while (i < intervalo) {
                            tnovo += 86400;
                            i++;
                        }
                    } else {
                        tnovo = tempo1.compareTime(tempo2);
                    }
                    int intervalooriginal = selecionada.getBeginDate().isBigger(selecionada.getEndDate());
                    if (intervalooriginal >= 1) {
                        toriginal += tempo1.compareTime(new TimeDate.Time(23, 59, 59));
                        toriginal += new TimeDate.Time(0, 0, 0).compareTime(tempo2);
                        int i = 1;
                        while (i < intervalo) {
                            toriginal += 86400;
                            i++;
                        }
                    } else {
                        toriginal = selecionada.getTimeBegin().compareTime(selecionada.getTimeEnd());
                    }
                    Keys.Request auxiliar = db.getRequestByID(selecionada.getId());
                    int vprimeiro = auxiliar.getTimeBegin().compareTime(auxiliar.getTimeEnd());
                    vprimeiro = tnovo * vprimeiro / toriginal;
                    TimeDate.Time tauxiliar = tempo1.addSeconds(vprimeiro);
                    tauxiliar.setSeconds(0);
                    auxiliar.setTimeBegin(tempo1);
                    auxiliar.setTimeEnd(tauxiliar);
                    auxiliar.setBeginDate(data1);
                    auxiliar.setEndDate(data2);
                    auxiliar.setMaterial(mselecionado);
                    if (db.setRequestSubstitute(auxiliar) != 1) {
                        db.roolback(p);
                        Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Aviso"), 400, 200, lingua.translate("Houve um problema na alteração dos dados") + ".", new String[]{"Voltar"});
                        mensagem.showMessage();
                        return;
                    }
                    int id = db.getRequestID(auxiliar.getPerson().getId(), auxiliar.getMaterial().getId(), auxiliar.getBeginDate(), auxiliar.getEndDate(), auxiliar.getTimeBegin(), auxiliar.getTimeEnd());
                    int vsegundos;
                    for (Keys.Request l : lista) {
                        int gol = l.getTimeBegin().compareTime(l.getTimeEnd());
                        vsegundos = (tnovo * gol) / toriginal;
                        l.setTimeBegin(tauxiliar);
                        l.setUnionRequest(id);
                        tauxiliar = tauxiliar.addSeconds(vsegundos);
                        l.setTimeEnd(tauxiliar);
                        l.setBeginDate(data1);
                        l.setEndDate(data2);
                        l.setMaterial(mselecionado);
                        if (db.setRequestSubstitute(l) != 1) {
                            db.roolback(p);
                            Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Aviso"), 400, 200, lingua.translate("Houve um problema na alteração dos dados") + ".", new String[]{"Voltar"});
                            mensagem.showMessage();
                            return;
                        }
                    }
                } else {
                    selecionada.setMaterial(mselecionado);
                    selecionada.setBeginDate(data1);
                    selecionada.setEndDate(data2);
                    selecionada.setTimeBegin(tempo1);
                    selecionada.setTimeEnd(tempo2);
                    selecionada.setSubstitute(selecionada.getId());
                    if (db.setRequestSubstitute(selecionada) != 1) {
                        Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.AVISO, corborda, lingua.translate("Aviso"), 400, 200, lingua.translate("Houve um problema na alteração dos dados") + ".", new String[]{"Voltar"});
                        mensagem.showMessage();
                        return;
                    }
                }
                db.commit();
                db.close();
                jPanelDados.setBackground(colorpanel);
                jButtonConfirmarAlteracao.setEnabled(false);
                Clavis.KeyQuest.refreshTables();
                Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.INFORMACAO, corborda, lingua.translate("Nota"), 400, 200, lingua.translate("A requisição foi alterada") + ".", new String[]{"Voltar"});
                mensagem.showMessage();
            }
        }
    }//GEN-LAST:event_jButtonConfirmarAlteracaoActionPerformed

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

    private boolean checkForTheSameRequest() {
        TimeDate.Date dat1 = getDate(jSpinnerDataLevantamento);
        TimeDate.Date dat2 = getDate(jSpinnerDataEntrega);
        TimeDate.Time tim1 = getTime(jSpinnerHoraLevantamento);
        TimeDate.Time tim2 = getTime(jSpinnerHoraEntrega);
        if ((mlista != null) && (mlista.size() > 0) && jComboBoxM.getSelectedIndex() > 0) {
            int val = jComboBoxMaterial.getSelectedIndex() - 1;
            Keys.Material m = mlista.get(val);
            return (dat1.isBigger(selecionada.getBeginDate()) == 0) && (dat2.isBigger(selecionada.getEndDate()) == 0) && (tim1.compareTime(selecionada.getTimeBegin()) == 0) && (tim2.compareTime(selecionada.getTimeEnd()) == 0) && (m.compareTo(selecionada.getMaterial()) == 0);
        }
        return false;
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
        });
        javax.swing.JFormattedTextField tx = ((JSpinner.DefaultEditor) jSpinnerHoraLevantamento.getEditor()).getTextField();
        tx.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                TimeDate.Date dat1 = getDate(jSpinnerDataLevantamento);
                TimeDate.Date dat2 = getDate(jSpinnerDataEntrega);
                TimeDate.Time tim1 = getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = getTime(jSpinnerHoraEntrega);
                Keys.Material m = null;
                if (jComboBoxMaterial.getSelectedIndex() > 0) {
                    m = mlista.get(jComboBoxMaterial.getSelectedIndex() - 1);
                }
                makeComboBoxMaterial(dat1, dat2, tim1, tim2);
                if (m != null) {
                    for (int i = 0; i < mlista.size(); i++) {
                        if (mlista.get(i).compareTo(m) == 0) {
                            jComboBoxMaterial.setSelectedIndex(i + 1);
                            break;
                        }
                    }
                }
                Object child = jComboBoxM.getAccessibleContext().getAccessibleChild(0);
                BasicComboPopup popup = (BasicComboPopup) child;
                popup.repaint();
                javax.swing.JList<?> list = popup.getList();
                list.repaint();
            }

        });
        jSpinnerHoraEntrega.addChangeListener((ChangeEvent e) -> {
            TimeDate.Date date1 = this.getDate(jSpinnerDataLevantamento);
            TimeDate.Date date2 = this.getDate(jSpinnerDataEntrega);
            int v = jComboBoxMaterial.getSelectedIndex();
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
        });
        tx.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jLabelRequisicaoNova.requestFocus();
                }
            }

        });
        javax.swing.JFormattedTextField tx2 = ((JSpinner.DefaultEditor) jSpinnerHoraEntrega.getEditor()).getTextField();
        tx2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                TimeDate.Date dat1 = getDate(jSpinnerDataLevantamento);
                TimeDate.Date dat2 = getDate(jSpinnerDataEntrega);
                TimeDate.Time tim1 = getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = getTime(jSpinnerHoraEntrega);
                Keys.Material m = null;
                if (jComboBoxMaterial.getSelectedIndex() > 0) {
                    m = mlista.get(jComboBoxMaterial.getSelectedIndex() - 1);
                }
                makeComboBoxMaterial(dat1, dat2, tim1, tim2);
                if (m != null) {
                    for (int i = 0; i < mlista.size(); i++) {
                        if (mlista.get(i).compareTo(m) == 0) {
                            jComboBoxMaterial.setSelectedIndex(i + 1);
                            break;
                        }
                    }
                }
                Object child = jComboBoxM.getAccessibleContext().getAccessibleChild(0);
                BasicComboPopup popup = (BasicComboPopup) child;
                popup.repaint();
                javax.swing.JList<?> list = popup.getList();
                list.repaint();
            }

        });
        tx2.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jLabelRequisicaoNova.requestFocus();
                }
            }

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
        });
        javax.swing.JFormattedTextField tx3 = ((JSpinner.DefaultEditor) jSpinnerDataLevantamento.getEditor()).getTextField();
        tx3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                TimeDate.Date dat1 = getDate(jSpinnerDataLevantamento);
                TimeDate.Date dat2 = getDate(jSpinnerDataEntrega);
                TimeDate.Time tim1 = getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = getTime(jSpinnerHoraEntrega);
                Keys.Material m = null;
                if (jComboBoxMaterial.getSelectedIndex() > 0) {
                    m = mlista.get(jComboBoxMaterial.getSelectedIndex() - 1);
                }
                makeComboBoxMaterial(dat1, dat2, tim1, tim2);
                if (m != null) {
                    for (int i = 0; i < mlista.size(); i++) {
                        if (mlista.get(i).compareTo(m) == 0) {
                            jComboBoxMaterial.setSelectedIndex(i + 1);
                            break;
                        }
                    }
                }
                Object child = jComboBoxM.getAccessibleContext().getAccessibleChild(0);
                BasicComboPopup popup = (BasicComboPopup) child;
                popup.repaint();
                javax.swing.JList<?> list = popup.getList();
                list.repaint();
            }

        });
        tx3.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jLabelRequisicaoNova.requestFocus();
                }
            }

        });
        jSpinnerDataEntrega.addChangeListener((ChangeEvent e) -> {
            TimeDate.Date date1 = this.getDate(jSpinnerDataLevantamento);
            TimeDate.Date date2 = this.getDate(jSpinnerDataEntrega);
            TimeDate.Date datahoje = new TimeDate.Date();
            if (date2.isBigger(date1) > 0) {
                jSpinnerDataLevantamento.setValue((Date) jSpinnerDataEntrega.getValue());
            } else if (date1.isBigger(date2) == 0) {
                if ((jSpinnerHoraLevantamento.getValue() != null) && (jSpinnerHoraEntrega.getValue() != null)) {
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
                }
            } else if (datahoje.isBigger(date1) == 0) {
                TimeDate.Time tim = this.getTime(jSpinnerHoraLevantamento);
                TimeDate.Time timatual = new TimeDate.Time();
                if (timatual.compareTime(tim) < 0) {
                    jSpinnerHoraLevantamento.setValue(new Date());
                    jSpinnerHoraLevantamento.repaint();
                }
            }
        });
        javax.swing.JFormattedTextField tx4 = ((JSpinner.DefaultEditor) jSpinnerDataEntrega.getEditor()).getTextField();
        tx4.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                TimeDate.Date dat1 = getDate(jSpinnerDataLevantamento);
                TimeDate.Date dat2 = getDate(jSpinnerDataEntrega);
                TimeDate.Time tim1 = getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = getTime(jSpinnerHoraEntrega);
                Keys.Material m = null;
                if (jComboBoxMaterial.getSelectedIndex() > 0) {
                    m = mlista.get(jComboBoxMaterial.getSelectedIndex() - 1);
                }
                makeComboBoxMaterial(dat1, dat2, tim1, tim2);
                if (m != null) {
                    for (int i = 0; i < mlista.size(); i++) {
                        if (mlista.get(i).compareTo(m) == 0) {
                            jComboBoxMaterial.setSelectedIndex(i + 1);
                            break;
                        }
                    }
                }
                Object child = jComboBoxM.getAccessibleContext().getAccessibleChild(0);
                BasicComboPopup popup = (BasicComboPopup) child;
                popup.repaint();
                javax.swing.JList<?> list = popup.getList();
                list.repaint();
            }

        });
        tx4.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jLabelRequisicaoNova.requestFocus();
                }
            }

        });
        jPanel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                TimeDate.Date dat1 = getDate(jSpinnerDataLevantamento);
                TimeDate.Date dat2 = getDate(jSpinnerDataEntrega);
                TimeDate.Time tim1 = getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = getTime(jSpinnerHoraEntrega);
                Keys.Material m = null;
                if (jComboBoxMaterial.getSelectedIndex() > 0) {
                    m = mlista.get(jComboBoxMaterial.getSelectedIndex() - 1);
                }
                makeComboBoxMaterial(dat1, dat2, tim1, tim2);
                if (m != null) {
                    for (int i = 0; i < mlista.size(); i++) {
                        if (mlista.get(i).compareTo(m) == 0) {
                            jComboBoxMaterial.setSelectedIndex(i + 1);
                            break;
                        }
                    }
                }
                Object child = jComboBoxM.getAccessibleContext().getAccessibleChild(0);
                BasicComboPopup popup = (BasicComboPopup) child;
                popup.repaint();
                javax.swing.JList<?> list = popup.getList();
                list.repaint();
            }
        });

        jPanel6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                TimeDate.Date dat1 = getDate(jSpinnerDataLevantamento);
                TimeDate.Date dat2 = getDate(jSpinnerDataEntrega);
                TimeDate.Time tim1 = getTime(jSpinnerHoraLevantamento);
                TimeDate.Time tim2 = getTime(jSpinnerHoraEntrega);
                Keys.Material m = null;
                if (jComboBoxMaterial.getSelectedIndex() > 0) {
                    m = mlista.get(jComboBoxMaterial.getSelectedIndex() - 1);
                }
                makeComboBoxMaterial(dat1, dat2, tim1, tim2);
                if (m != null) {
                    for (int i = 0; i < mlista.size(); i++) {
                        if (mlista.get(i).compareTo(m) == 0) {
                            jComboBoxMaterial.setSelectedIndex(i + 1);
                            break;
                        }
                    }
                }
                Object child = jComboBoxM.getAccessibleContext().getAccessibleChild(0);
                BasicComboPopup popup = (BasicComboPopup) child;
                popup.repaint();
                javax.swing.JList<?> list = popup.getList();
                list.repaint();
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmarAlteracao;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonSair;
    /*
    private javax.swing.JComboBox<String> jComboBoxM;
    */
    private javax.swing.JComboBox<Object> jComboBoxM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelAtividade;
    private javax.swing.JLabel jLabelAtividade2;
    private javax.swing.JLabel jLabelDisciplina;
    private javax.swing.JLabel jLabelDisciplina2;
    private org.jdesktop.swingx.JXLabel jLabelEntrega2;
    private javax.swing.JLabel jLabelFim;
    private javax.swing.JLabel jLabelFimData;
    private javax.swing.JLabel jLabelFimData2;
    private javax.swing.JLabel jLabelFimHora;
    private javax.swing.JLabel jLabelFimHora2;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelInicio;
    private javax.swing.JLabel jLabelInicioData;
    private javax.swing.JLabel jLabelInicioData2;
    private javax.swing.JLabel jLabelInicioHora;
    private javax.swing.JLabel jLabelInicioHora2;
    private javax.swing.JLabel jLabelMaterial;
    private javax.swing.JLabel jLabelNovasDatas;
    private javax.swing.JLabel jLabelRecurso;
    private javax.swing.JLabel jLabelRecurso2;
    private javax.swing.JLabel jLabelRequisicaoNova;
    private javax.swing.JLabel jLabelRequisicaoVelha;
    private javax.swing.JLabel jLabelUtilizador;
    private javax.swing.JLabel jLabelUtilizador2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelAlteracao;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelInicial;
    private javax.swing.JSpinner jSpinnerDataEntrega;
    private javax.swing.JSpinner jSpinnerDataLevantamento;
    private javax.swing.JSpinner jSpinnerHoraEntrega;
    private javax.swing.JSpinner jSpinnerHoraLevantamento;
    private org.jdesktop.swingx.JXLabel jXLabelLevantamento;
    // End of variables declaration//GEN-END:variables
}
