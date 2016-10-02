/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import Clavis.ButtonListRequest;
import static Clavis.KeyQuest.systemColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author toze
 */
public class WSeeRequest extends javax.swing.JFrame {

    /**
     * Creates new form WChangeRequest
     */
    private Color corfundo;
    private Color corborda;
    private String url;
    private Langs.Locale lingua;
    private Keys.Request selecionada;
    private BufferedImage imagem;

    public WSeeRequest(Color corfundo, Color corborda, String url, Langs.Locale lingua, Keys.Request selecionada) {
        this.corfundo = corfundo;
        this.corborda = corborda;
        this.lingua = lingua;
        this.selecionada = selecionada;
        this.url = url;
    }

    public void create() {
        initComponents();
        makePanel();
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        if (selecionada != null) {
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
        }
        this.makeFileRequest();
        this.addBehaviorToLabelImage();

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

    private void makeFileRequest() {
        if (selecionada != null) {
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
            if (satividade[0].equals("")) {
                jLabelAtividade2.setText("Não existe");
            } else {
                jLabelAtividade2.setText(satividade[0]);
            }
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
            jLabelMomentoData2.setText(selecionada.getLiftDate().toStringWithMonthWord(lingua));
            jLabelMomentoHora2.setText(selecionada.getLiftTime().toString(0));
            boolean atrasado = false;
            String tempodeatraso = "";
            TimeDate.Date dat = new TimeDate.Date();
            TimeDate.Time tm = new TimeDate.Time();
            int atraso = selecionada.getEndDate().isBigger(dat);
            int atrasohoras = selecionada.getTimeEnd().compareTime(tm);
            String hora;
            String minutos;
            if (atraso > 0) {
                atrasado = true;
                if (atrasohoras > 0) {
                    double t = atrasohoras / 60;
                    double decimal = 0;
                    if (t > 59) {
                        t = t / 60;
                        decimal = (int) t;
                        decimal = t - decimal;
                        decimal = decimal * 60;
                        t = (int) t;
                    }
                    hora = "" + (int) t;
                    minutos = "" + Math.round(decimal);
                    tempodeatraso = "\n" + lingua.translate("Em atraso") + ":\n\n" + atraso + " " + lingua.translate("dias")
                            + ", " + hora + " " + lingua.translate("horas") + ", " + minutos + " " + lingua.translate("minutos") + ".";
                } else if (atrasohoras < 0) {
                    int seg = selecionada.getTimeEnd().compareTime(new TimeDate.Time(23, 59, 59));
                    seg += new TimeDate.Time(0, 0, 0).compareTime(tm);
                    double t = seg / 60;
                    double decimal = 0;
                    if (t > 59) {
                        t = t / 60;
                        decimal = (int) t;
                        decimal = t - decimal;
                        decimal = decimal * 60;
                        t = (int) t;
                    }
                    hora = "" + (int) t;
                    minutos = "" + Math.round(decimal);
                    tempodeatraso = "\n" + lingua.translate("Em atraso") + ":\n\n" + (atraso - 1) + " " + lingua.translate("dias")
                            + ", " + hora + " " + lingua.translate("horas") + ", " + minutos + " " + lingua.translate("minutos") + ".";
                } else {
                    tempodeatraso = "\n" + lingua.translate("Em atraso") + ":\n\n" + (atraso - 1) + " " + lingua.translate("dias");
                }
            } else if (atraso == 0) {
                if (atrasohoras > 0) {
                    double t = atrasohoras / 60;
                    double decimal = 0;
                    if (t > 59) {
                        t = t / 60;
                        decimal = (int) t;
                        decimal = t - decimal;
                        decimal = decimal * 60;
                        t = (int) t;
                    }
                    hora = "" + (int) t;
                    minutos = "" + Math.round(decimal);
                    tempodeatraso = "\n" + lingua.translate("Em atraso") + ":\n\n"
                            + hora + " " + lingua.translate("horas") + ", " + minutos + " " + lingua.translate("minutos") + ".";
                } else if (atrasohoras <= 0) {
                    atrasohoras = -atrasohoras;
                    double t = atrasohoras / 60;
                    double decimal = 0;
                    if (t > 59) {
                        t = t / 60;
                        decimal = (int) t;
                        decimal = t - decimal;
                        decimal = decimal * 60;
                        t = (int) t;
                    }
                    hora = "" + (int) t;
                    minutos = "" + Math.round(decimal);
                    tempodeatraso = "\n" + lingua.translate("Estado ativo") + ", "
                            + lingua.translate("faltam") + ":\n\n " + hora + " " + lingua.translate("horas") + ", " + minutos + " " + lingua.translate("minutos") + ".";
                }
            } else {
                atraso = -atraso;
                if (atrasohoras > 0) {
                    int seg = tm.compareTime(new TimeDate.Time(23, 59, 59));
                    seg += new TimeDate.Time(0, 0, 0).compareTime(selecionada.getTimeEnd());
                    double t = seg / 60;
                    double decimal = 0;
                    if (t > 59) {
                        t = t / 60;
                        decimal = (int) t;
                        decimal = t - decimal;
                        decimal = decimal * 60;
                        t = (int) t;
                    }
                    hora = "" + (int) t;
                    minutos = "" + Math.round(decimal);
                    tempodeatraso = "\n" + lingua.translate("Estado ativo") + ", " + lingua.translate("faltam") + ": \n\n"
                            + (atraso - 1) + " " + lingua.translate("dias") + ", " + hora + " " + lingua.translate("horas") + ", " + minutos + " " + lingua.translate("minutos") + ".";
                } else if (atrasohoras <= 0) {
                    atrasohoras = -atrasohoras;
                    double t = atrasohoras / 60;
                    double decimal = 0;
                    if (t > 59) {
                        t = t / 60;
                        decimal = (int) t;
                        decimal = t - decimal;
                        decimal = decimal * 60;
                        t = (int) t;
                    }
                    hora = "" + (int) t;
                    minutos = "" + Math.round(decimal);
                    tempodeatraso = "\n" + lingua.translate("Estado ativo") + ", " + lingua.translate("faltam") + ": \n\n"
                            + atraso + " " + lingua.translate("dias") + ", " + hora + " " + lingua.translate("horas") + ", " + minutos + " " + lingua.translate("minutos") + ".";
                }
            }
            jTextPaneEstado.setText(tempodeatraso);
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

    public static void main(String[] args) {
        Clavis.Windows.WSeeRequest ws = new Clavis.Windows.WSeeRequest(new Color(255, 255, 255), new Color(2, 2, 2), "", Langs.Locale.getLocale_pt_PT(), null);
        ws.create();
        ws.appear();
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
        jLabelMomentoData = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelMomentoHora = new javax.swing.JLabel();
        jLabelMomentoHora2 = new javax.swing.JLabel();
        jLabelMomentoEntrega = new javax.swing.JLabel();
        jLabelMomentoData2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneEstado = new javax.swing.JTextPane();
        jButtonConfirmaDevolucao = new javax.swing.JButton();
        jLabelRequisicaoVelha = new javax.swing.JLabel();
        jLabelImagem = new javax.swing.JLabel();
        jLabelOutrosDados = new javax.swing.JLabel();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
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
        jLabelUtilizador.setText("Utilizador:");

        jLabelRecurso.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabelRecurso.setText("Recurso:");
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
        jLabelInicio.setText("Início:");

        jLabelInicioData.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabelInicioData.setText("Data");

        jLabelInicioHora.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabelInicioHora.setText("Hora");

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
        jLabelFim.setText("Fim:");

        jLabelFimData.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabelFimData.setText("Data");

        jLabelFimHora.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabelFimHora.setText("Hora");

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
        jLabelAtividade.setText("Atividade:");

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
        jLabelDisciplina.setText("Disciplina:");

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelUtilizador, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelUtilizador2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelRecurso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelFimData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelInicioHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelInicioData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelFimHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelFimHora2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(jLabelFimData2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelInicioHora2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelInicioData2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelRecurso2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(jLabelAtividade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 50, Short.MAX_VALUE)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAtividade2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDisciplina2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
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
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelInicioData2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelInicioData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelFimData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInicioHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelInicioHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabelFimData2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFimHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFimHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAtividade2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDisciplina2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelUtilizador.setText(lingua.translate("Utilizador")+":");
        jLabelRecurso.setText(lingua.translate("Recurso")+":");
        jLabelInicio.setText(lingua.translate("Início")+":");
        jLabelInicioData.setText(lingua.translate("Data"));
        jLabelInicioHora.setText(lingua.translate("Hora"));
        jLabelFim.setText(lingua.translate("Fim")+":");
        jLabelFimData.setText(lingua.translate("Data"));
        jLabelFimHora.setText(lingua.translate("Hora"));
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
            .addComponent(jPanelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder10 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder10.setCornerSize(6);
        dropShadowBorder10.setShadowSize(3);
        dropShadowBorder10.setShowLeftShadow(true);
        jPanel2.setBorder(dropShadowBorder10);

        jPanelAlteracao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelMomentoData.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabelMomentoData.setText("Data");

        jLabelEstado.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabelEstado.setText("Estado: ");

        jLabelMomentoHora.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        jLabelMomentoHora.setText("Hora");

        jLabelMomentoHora2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder11 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder11.setCornerSize(6);
        dropShadowBorder11.setShadowSize(2);
        dropShadowBorder11.setShowLeftShadow(true);
        dropShadowBorder11.setShowTopShadow(true);
        jLabelMomentoHora2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder11, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelMomentoHora2.setOpaque(true);

        jLabelMomentoEntrega.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        jLabelMomentoEntrega.setText("Momento de entrega:");

        jLabelMomentoData2.setBackground(new java.awt.Color(254, 254, 254));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder12 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder12.setCornerSize(6);
        dropShadowBorder12.setShadowSize(2);
        dropShadowBorder12.setShowLeftShadow(true);
        dropShadowBorder12.setShowTopShadow(true);
        jLabelMomentoData2.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder12, javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10))));
        jLabelMomentoData2.setOpaque(true);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder13 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder13.setCornerSize(6);
        dropShadowBorder13.setShadowSize(2);
        dropShadowBorder13.setShowLeftShadow(true);
        dropShadowBorder13.setShowTopShadow(true);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder13, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jTextPaneEstado.setBackground(new java.awt.Color(254, 254, 254));
        jScrollPane1.setViewportView(jTextPaneEstado);
        StyledDocument doc = jTextPaneEstado.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        jButtonConfirmaDevolucao.setBackground(new java.awt.Color(57, 147, 2));
        jButtonConfirmaDevolucao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonConfirmaDevolucao.setFocusPainted(false);
        jButtonConfirmaDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmaDevolucaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAlteracaoLayout = new javax.swing.GroupLayout(jPanelAlteracao);
        jPanelAlteracao.setLayout(jPanelAlteracaoLayout);
        jPanelAlteracaoLayout.setHorizontalGroup(
            jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlteracaoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlteracaoLayout.createSequentialGroup()
                        .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlteracaoLayout.createSequentialGroup()
                                .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelMomentoHora, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                    .addComponent(jLabelMomentoData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMomentoData2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelMomentoHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelMomentoEntrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlteracaoLayout.createSequentialGroup()
                        .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonConfirmaDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );
        jPanelAlteracaoLayout.setVerticalGroup(
            jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlteracaoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabelMomentoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMomentoData2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMomentoData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanelAlteracaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMomentoHora2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMomentoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButtonConfirmaDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabelInicioData.setText(lingua.translate("Data"));
        jLabelEstado.setText(lingua.translate("Estado")+":");
        jLabelInicioHora.setText(lingua.translate("Hora"));
        jLabelMomentoEntrega.setText(lingua.translate("Momento de entrega")+":");
        try {
            if (Clavis.KeyQuest.class.getResource("Images/ok.png") != null) {
                BufferedImage imagebtdevok = ImageIO.read(Clavis.KeyQuest.class.getResourceAsStream("Images/ok.png"));
                ImageIcon iconbtdevok = new javax.swing.ImageIcon(imagebtdevok);
                jButtonConfirmaDevolucao.setIcon(iconbtdevok);
            } else {
                jButtonConfirmaDevolucao.setText(lingua.translate("Confirmar"));
            }
        } catch (IOException ex) {

        }
        jButtonConfirmaDevolucao.setToolTipText(lingua.translate("Confirmar devolucao"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAlteracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAlteracao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabelRequisicaoVelha.setBackground(new java.awt.Color(245, 245, 245));
        jLabelRequisicaoVelha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRequisicaoVelha.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 3, this.corfundo), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jLabelRequisicaoVelha.setOpaque(true);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder14 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder14.setCornerSize(6);
        dropShadowBorder14.setShadowSize(2);
        dropShadowBorder14.setShowLeftShadow(true);
        dropShadowBorder14.setShowTopShadow(true);
        jLabelImagem.setBorder(javax.swing.BorderFactory.createCompoundBorder(dropShadowBorder14, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jLabelImagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelImagem.setOpaque(true);

        jLabelOutrosDados.setBackground(new java.awt.Color(245, 245, 245));
        jLabelOutrosDados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOutrosDados.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 3, this.corfundo), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jLabelOutrosDados.setOpaque(true);

        jButtonSair.setBackground(new java.awt.Color(1, 1, 1));
        jButtonSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSair.setFocusPainted(false);
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInicialLayout = new javax.swing.GroupLayout(jPanelInicial);
        jPanelInicial.setLayout(jPanelInicialLayout);
        jPanelInicialLayout.setHorizontalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInicialLayout.createSequentialGroup()
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelInicialLayout.createSequentialGroup()
                                .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelRequisicaoVelha, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelOutrosDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanelInicialLayout.setVerticalGroup(
            jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInicialLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelOutrosDados, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRequisicaoVelha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabelRequisicaoVelha.setText(lingua.translate("Dados da requisição"));
        jLabelOutrosDados.setText(lingua.translate("Outros dados"));
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

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonConfirmaDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmaDevolucaoActionPerformed
        Components.MessagePane mensagem = new Components.MessagePane(this, Components.MessagePane.ACAO, corborda, lingua.translate("Confirmar devolução"), 400, 200, lingua.translate("Pretende confirmar a devolução") + ". ", new String[]{lingua.translate("Confirmar"), lingua.translate("Voltar")});
        int val = mensagem.showMessage();
        System.out.println(val);
        System.out.println(selecionada.getId());
        if (val == 1) {
            if (DataBase.DataBase.testConnection(url)) {
                DataBase.DataBase db = new DataBase.DataBase(url);
                db.changeRequestTerminateState(selecionada);
                db.close();
                Clavis.KeyQuest.refreshDevolutionTable(selecionada);
                jButtonConfirmaDevolucao.setEnabled(false);
            }
        }
        
    }//GEN-LAST:event_jButtonConfirmaDevolucaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmaDevolucao;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabelAtividade;
    private javax.swing.JLabel jLabelAtividade2;
    private javax.swing.JLabel jLabelDisciplina;
    private javax.swing.JLabel jLabelDisciplina2;
    private javax.swing.JLabel jLabelEstado;
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
    private javax.swing.JLabel jLabelMomentoData;
    private javax.swing.JLabel jLabelMomentoData2;
    private javax.swing.JLabel jLabelMomentoEntrega;
    private javax.swing.JLabel jLabelMomentoHora;
    private javax.swing.JLabel jLabelMomentoHora2;
    private javax.swing.JLabel jLabelOutrosDados;
    private javax.swing.JLabel jLabelRecurso;
    private javax.swing.JLabel jLabelRecurso2;
    private javax.swing.JLabel jLabelRequisicaoVelha;
    private javax.swing.JLabel jLabelUtilizador;
    private javax.swing.JLabel jLabelUtilizador2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAlteracao;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelInicial;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPaneEstado;
    // End of variables declaration//GEN-END:variables
}
