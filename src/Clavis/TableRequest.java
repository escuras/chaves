/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author toze
 */
public class TableRequest {

    public static final Color DEFAULT_BACKGROUND_COLOR = UIManager.getColor("Table.background");
    public static final Color DEFAULT_FOREGROUND_COLOR = Color.black;
    public static final Color DEFAULT_SELECT_COLOR = Color.DARK_GRAY;
    public static final Color DEFAULT_PANEL_COLOR = new Color(245, 245, 220);
    public static final Color DEFAULT_PANEL_FOREGROUND_COLOR = new Color(0, 0, 0);
    public static final Color DEFAULT_SELECT_FOREGROUND_COLOR = Color.WHITE;
    public static final int DEFAULT_AFTER_TIME = 0;
    public static final int DEFAULT_BEFORE_TIME = 60;
    public static final int DEFAULT_LATE_INTERVAL = 30;

    private final Components.RowTable tabela;
    private final Langs.Locale lingua;
    private int selecionado;
    private RequestList requisicoes;
    private Color panelColor;
    private Color panelForegroundColor;
    private Color backColor;
    private Color foreColor;
    private Color selectColor;
    private Color foregroundSelectColor;
    private final javax.swing.JPanel spanel;
    private Clavis.PanelDetails panel;
    private Color[] cores;
    private int antes_hora;
    private int depois_hora;
    private boolean ficouvazia;
    private boolean vazia;
    private boolean procura;
    private final boolean devolucoes;
    private final int tipomaterial;
    private Timer time;
    private final javax.swing.JButton btconfirma;
    private final javax.swing.JButton btaltera;
    private final Color systemColor;
    private final String url;
    private int atrasolegal;

    public TableRequest(RequestList requisicao, javax.swing.JPanel spanel, Langs.Locale lingua, boolean devolucoes, javax.swing.JButton bt1, javax.swing.JButton bt2, String url) {
        this.lingua = lingua;
        btconfirma = bt1;
        btaltera = bt2;
        this.systemColor = KeyQuest.getSystemColor();
        this.url = url;
        this.selecionado = -1;
        this.devolucoes = devolucoes;
        this.spanel = spanel;
        this.requisicoes = requisicao;
        procura = false;
        vazia = false;
        ficouvazia = false;
        this.tipomaterial = requisicao.getTypeOfMaterial().getMaterialTypeID();
        antes_hora = TableRequest.DEFAULT_BEFORE_TIME * 60;
        depois_hora = -TableRequest.DEFAULT_AFTER_TIME * 60;
        cores = new Color[]{Color.GREEN.darker(), new Color(255, 149, 0), Color.BLACK};
        backColor = TableRequest.DEFAULT_BACKGROUND_COLOR;
        foreColor = TableRequest.DEFAULT_FOREGROUND_COLOR;
        selectColor = TableRequest.DEFAULT_SELECT_COLOR;
        panelColor = TableRequest.DEFAULT_PANEL_COLOR;
        panelForegroundColor = TableRequest.DEFAULT_PANEL_FOREGROUND_COLOR;
        panel = new PanelDetails(lingua, panelColor, panelForegroundColor, requisicao.getTypeOfMaterial().getTypeOfMaterialImage(), requisicao.getTypeOfMaterial().getTypeOfMaterialName());
        tabela = new Components.RowTable(new javax.swing.JTable().getModel());
        this.atrasolegal = -TableRequest.DEFAULT_LATE_INTERVAL * 60;
    }

    public void create() {
        tabela.removeAll();
        spanel.removeAll();
        panel.setInterval(2);
        panel.create();

        this.addPanel(spanel, panel.alternativePanel());
        if (!requisicoes.getRequests().isEmpty()) {
            ficouvazia = false;
            vazia = false;
            if (!devolucoes) {
                tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Utilizador"), lingua.translate(requisicoes.getTypeOfMaterial().getTypeOfMaterialName()), lingua.translate("Hora"), lingua.translate("Data")}));
            } else {
                tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Utilizador"), lingua.translate(requisicoes.getTypeOfMaterial().getTypeOfMaterialName()), lingua.translate("Hora"), lingua.translate("Data")}));
            }
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            if (!devolucoes) {
                for (Keys.Request req : requisicoes.getRequests()) {
                    Object[] ob = {"  " + req.getPerson().getName(), req.getMaterial().getDescription(), req.getTimeBegin().toString(0), req.getBeginDate().toStringWithMonthWord(lingua)};
                    modelo.addRow(ob);
                }
            } else {
                for (Keys.Request req : requisicoes.getRequests()) {
                    Object[] ob = {"   " + req.getPerson().getName(), req.getMaterial().getDescription(), req.getTimeEnd().toString(0), req.getEndDate().toStringWithMonthWord(lingua)};
                    modelo.addRow(ob);
                }
            }
        } else {
            vazia = true;
            tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Situação")}));
            Object[] ob;
            if (!devolucoes) {
                if (!requisicoes.isConnected()) {
                    ob = new Object[]{lingua.translate("Problema_de_rede_ou_ligação_base_de_dados")};
                } else if ((requisicoes.getRequests().isEmpty())) {
                    if (ficouvazia) {
                        if (requisicoes.getView() == RequestList.VIEW_DAY) {
                            ob = new Object[]{lingua.translate("Não_existem_mais_registos_para_hoje.")};
                        } else {
                            ob = new Object[]{lingua.translate("Não_existem_registos.")};
                        }
                    } else if (requisicoes.getView() == RequestList.VIEW_DAY) {
                        ob = new Object[]{lingua.translate("Não_existem_registos_para_hoje.")};
                    } else {
                        ob = new Object[]{lingua.translate("Não_existem_registos.")};
                    }
                } else {
                    ob = new Object[]{lingua.translate("Não_existem_registos.")};
                }
            } else if (!requisicoes.isConnected()) {
                ob = new Object[]{lingua.translate("Problema_de_rede_ou_ligação_base_de_dados")};
            } else if ((requisicoes.getRequests().isEmpty())) {
                if (ficouvazia) {
                    if (requisicoes.getView() == RequestList.VIEW_DAY) {
                        ob = new Object[]{lingua.translate("Não_existem_mais_devolucoes_para_hoje.")};
                    } else {
                        ob = new Object[]{lingua.translate("Não_existem_registos.")};
                    }
                } else if (requisicoes.getView() == RequestList.VIEW_DAY) {
                    ob = new Object[]{lingua.translate("Não_existem_registos_para_hoje.")};
                } else {
                    ob = new Object[]{lingua.translate("Não_existem_registos.")};
                }
            } else {
                ob = new Object[]{lingua.translate("Não_existem_registos.")};
            }
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            modelo.addRow(ob);
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalTextPosition(SwingConstants.CENTER);
        renderer.setVerticalTextPosition(SwingConstants.CENTER);
        renderer.setPreferredSize(new Dimension(40, 40));
        tabela.setRowHeight(35);

        if (tabela.getColumnCount() > 1) {
            
                tabela.getColumnModel().getColumn(0).setPreferredWidth(280);
                tabela.getColumnModel().getColumn(1).setMinWidth(150);
                tabela.getColumnModel().getColumn(2).setMinWidth(80);
                tabela.getColumnModel().getColumn(3).setMinWidth(150);
        }
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setFont(new Font("Cantarell", Font.PLAIN, 14));
                this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, new Color(0, 0, 0)));

                return this;
            }

        };
        headerRenderer.setBackground(new Color(165, 165, 165));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        DefaultTableCellRenderer headerRenderer2 = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setFont(new Font("Cantarell", Font.PLAIN, 14));
                this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0)),BorderFactory.createEmptyBorder(0, 20, 0, 0)));
                return this;
            }

        };
        headerRenderer2.setBackground(new Color(165, 165, 165));
        headerRenderer2.setForeground(Color.WHITE);
        if (tabela.getColumnCount() > 1) {
            headerRenderer2.setHorizontalAlignment(javax.swing.JLabel.LEFT);
        } else {
            headerRenderer2.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        }

        for (int i = 0; i < tabela.getModel().getColumnCount(); i++) {
            if (i == 0) {
                tabela.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer2);
            } else {
                tabela.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }
        }

        JTableHeader th = tabela.getTableHeader();
        th.setPreferredSize(new Dimension(200, 42));
        th.setEnabled(false);

        // remoção do contorno da célula
        Border border = BorderFactory.createEmptyBorder(5, 5, 0, 0);

        UIManager.put("Table.focusCellHighlightBorder", border);

        tabela.setBackground(backColor);

        tabela.alternateColorRows();

        tabela.setForeground(foreColor);

        tabela.setSelectionBackground(selectColor);
        if (!devolucoes) {
            tabela.setToolTipText(lingua.translate("Lista_de_requisições"));
        } else {
            tabela.setToolTipText(lingua.translate("Lista_de_devolucoes"));
        }

        tabela.setFillsViewportHeight(
                true);
        // Alinhamento do texto na tabela
        DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();

        if (tabela.getColumnCount() > 1) {
            int i = 0;
            while (i < tabela.getColumnCount()) {
                switch (i % 4) {
                    case 0:
                        renderer2.setHorizontalAlignment(javax.swing.JLabel.LEFT);
                        break;
                    case 1:
                        renderer2.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                        break;
                    case 2:
                        renderer2.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                        break;
                    case 3:
                        renderer2.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                        break;
                    default:
                        renderer2.setHorizontalAlignment(javax.swing.JLabel.CENTER);
                        break;
                }
                tabela.getColumnModel().getColumn(i).setCellRenderer(renderer2);
                renderer2 = new DefaultTableCellRenderer();
                i++;
            }
        } else {
            renderer2.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            tabela.getColumnModel().getColumn(0).setCellRenderer(renderer2);
        }
        // seleção e atualização do painel
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            String[] titulos = {lingua.translate("Copiar nome de utilizador"), lingua.translate("Copiar descrição de recurso"), lingua.translate("Copiar hora de requisição"), lingua.translate("Marcar como requisitado"), lingua.translate("Alterar requisição"), lingua.translate("Ver recurso")};
            String[] titulos2 = {lingua.translate("Copiar nome de utilizador"), lingua.translate("Copiar descrição de recurso"), lingua.translate("Copiar hora de devolução"), lingua.translate("Marcar como entregue"), lingua.translate("Ver requisicão"), lingua.translate("Ver recurso")};
            ActionListener[] act = new ActionListener[titulos.length];
            Components.PopUpMenu popup = new Components.PopUpMenu();

            @Override
            public void mousePressed(MouseEvent e) {
                if (!devolucoes) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        int r = tabela.rowAtPoint(e.getPoint());
                        if (r >= 0 && r < tabela.getRowCount()) {
                            selecionado = r;
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (requisicoes.getRequests().size() > 0) {
                    if (!devolucoes) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            int r = tabela.rowAtPoint(e.getPoint());
                            if (r >= 0 && r < tabela.getRowCount()) {
                                tabela.setRowSelectionInterval(r, r);
                                tabela.getSelectionModel().setSelectionInterval(r, r);
                            } else {
                                return;
                            }
                            act[0] = (ActionEvent e1) -> {
                                Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                                StringSelection nome = new StringSelection(getSelectedRequest().getPerson().getName());
                                c.setContents(nome, nome);
                            };
                            act[1] = (ActionEvent e1) -> {
                                Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                                StringSelection nome = new StringSelection(getSelectedRequest().getMaterial().getDescription());
                                c.setContents(nome, nome);
                            };
                            act[2] = (ActionEvent e1) -> {
                                Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                                StringSelection nome = new StringSelection(getSelectedRequest().getTimeBegin().toString());
                                c.setContents(nome, nome);
                            };
                            act[3] = (ActionEvent e1) -> {
                                btconfirma.doClick();
                            };
                            act[4] = (ActionEvent e1) -> {
                                btaltera.doClick();
                            };
                            act[5] = (ActionEvent e1) -> {
                                if (tipomaterial == 1) {
                                    DataBase.DataBase db = new DataBase.DataBase(url);
                                    Keys.Classroom cla = db.getClassroom(getSelectedRequest().getMaterial());
                                    db.close();
                                    Clavis.ActionButton bt = new Clavis.ActionButton(cla, lingua, url, null);
                                    bt.create();
                                    bt.open();
                                } else {
                                    Clavis.ActionButton bt = new Clavis.ActionButton(getSelectedRequest().getMaterial(), lingua, url, null);
                                    bt.create();
                                    bt.open();
                                }
                            };
                            Keys.Request req = requisicoes.getSelectedRequest(selecionado);
                            TimeDate.Time tempo = new TimeDate.Time();
                            TimeDate.Date data = new TimeDate.Date();
                            int valfinal;
                            int val = tempo.compareTime(req.getTimeBegin());

                            if ((req.getBeginDate().getDay() == data.getDay()) && (req.getBeginDate().getMonth() == data.getMonth()) && (req.getBeginDate().getYear() == data.getYear())) {
                                if (new TimeDate.Date().getDayYear() < req.getEndDate().getDayYear()) {
                                    valfinal = tempo.compareTime(req.getTimeEnd()) + ((req.getEndDate().getDayYear() - new TimeDate.Date().getDayYear()) * 86400);
                                } else {
                                    valfinal = tempo.compareTime(req.getTimeEnd());
                                }
                                if ((val < antes_hora) && (valfinal >= depois_hora) && (!req.getMaterial().isLoaned())) {
                                    btconfirma.setEnabled(true);
                                    btaltera.setEnabled(true);
                                } else {
                                    btconfirma.setEnabled(false);
                                    btaltera.setEnabled(true);
                                }
                            } else {
                                btconfirma.setEnabled(false);
                                btaltera.setEnabled(true);
                            }

                            popup = new Components.PopUpMenu(titulos, act, btconfirma, btaltera, false);
                            popup.create();
                            popup.show(e.getComponent(), e.getX(), e.getY());
                        }
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        int r = tabela.rowAtPoint(e.getPoint());
                        if (r >= 0 && r < tabela.getRowCount()) {
                            tabela.setRowSelectionInterval(r, r);
                            tabela.getSelectionModel().setSelectionInterval(r, r);
                        } else {
                            return;
                        }
                        act[0] = (ActionEvent e1) -> {
                            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                            StringSelection nome = new StringSelection(getSelectedRequest().getPerson().getName());
                            c.setContents(nome, nome);
                        };
                        act[1] = (ActionEvent e1) -> {
                            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                            StringSelection nome = new StringSelection(getSelectedRequest().getMaterial().getDescription());
                            c.setContents(nome, nome);
                        };
                        act[2] = (ActionEvent e1) -> {
                            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                            StringSelection nome = new StringSelection(getSelectedRequest().getTimeEnd().toString());
                            c.setContents(nome, nome);
                        };
                        act[3] = (ActionEvent e1) -> {
                            btconfirma.doClick();
                        };
                        act[4] = (ActionEvent e1) -> {
                            btaltera.doClick();
                        };
                        act[5] = (ActionEvent e1) -> {
                            if (tipomaterial == 1) {
                                DataBase.DataBase db = new DataBase.DataBase(url);
                                Keys.Classroom cla = db.getClassroom(getSelectedRequest().getMaterial());
                                db.close();
                                Clavis.ActionButton bt = new Clavis.ActionButton(cla, lingua, url, null);
                                bt.create();
                                bt.open();
                            } else {
                                Clavis.ActionButton bt = new Clavis.ActionButton(getSelectedRequest().getMaterial(), lingua, url, null);
                                bt.create();
                                bt.open();
                            }
                        };
                        Keys.Request req = requisicoes.getSelectedRequest(selecionado);
                        TimeDate.Time tempo = new TimeDate.Time();
                        TimeDate.Date data = new TimeDate.Date();
                        popup = new Components.PopUpMenu(titulos2, act, btconfirma, btaltera, true);
                        popup.create();
                        popup.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }

        });
        tabela.getSelectionModel()
                .addListSelectionListener((ListSelectionEvent e) -> {
                    TableRequest.this.selecionado = tabela.getSelectedRow();
                    if (tabela.getSelectedRow() >= 0) {
                        if (tabela.getColumnCount() > 1) {
                            if (!e.getValueIsAdjusting()) {
                                int select = TableRequest.this.selecionado;
                                if (select >= 0) {
                                    Object[] baux = requisicoes.getRequests().toArray();
                                    Keys.Request req = (Keys.Request) baux[select];
                                    String[] results;
                                    String[] titulos;
                                    String atividade;
                                    if ((req.getActivity().equals("") || (req.getActivity().equals("sem")))) {
                                        atividade = lingua.translate("Não existe descrição");
                                    } else {
                                        atividade = req.getActivity();
                                    }
                                    if (req.getBeginDate().isBigger(req.getEndDate()) == 0) {
                                        if (!devolucoes) {
                                            titulos = new String[]{"Recurso", "Utilizador", "Data", "Atividade", "Disciplina", "Horário", "Dia da semana"};
                                            results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toString(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeBegin().toString(0) + " - " + req.getTimeEnd().toString(0), req.getWeekDay().perDayName()};
                                        } else {
                                            titulos = new String[]{"Recurso", "Utilizador", "Data", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana"};
                                            results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toString(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), req.getWeekDay().perDayName()};
                                        }
                                    } else if (!devolucoes) {
                                        titulos = new String[]{"Recurso", "Utilizador", "Data de levantamento", "Data de devolução", "Atividade", "Disciplina", "Hora de levantamento", "Dia da semana (levantamento)"};
                                        results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toStringWithMonthWord(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeBegin().toString(0), req.getWeekDay().perDayName()};
                                    } else {
                                        try {
                                            titulos = new String[]{"Recurso", "Utilizador", "Data de devolução", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana (devolução)"};
                                            results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), new TimeDate.WeekDay(req.getEndDate()).perDayName()};
                                        } catch (ParseException ex) {
                                            titulos = new String[]{"Recurso", "Utilizador", "Data de devolução", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana (devolução)"};
                                            results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), ""};
                                        }
                                    }
                                    panel = new PanelDetails(panelColor, panelForegroundColor, titulos, results, lingua, requisicoes.getTypeOfMaterial().getTypeOfMaterialImage(), this.getSelectedRequest().getMaterial().getMaterialImage(), requisicoes.getTypeOfMaterial().getTypeOfMaterialName(), spanel);
                                    panel.setInterval(2);
                                    panel.create();
                                    spanel.setPreferredSize(panel.getPreferredSize());
                                    this.addPanel(spanel, panel);
                                } else {
                                    panel = new PanelDetails(lingua, panelColor, panelForegroundColor, requisicoes.getTypeOfMaterial().getTypeOfMaterialImage(), requisicoes.getTypeOfMaterial().getTypeOfMaterialName());
                                    panel.setInterval(2);
                                    panel.create();
                                    spanel.setPreferredSize(panel.alternativePanel().getPreferredSize());
                                    this.addPanel(spanel, panel.alternativePanel());
                                }
                            }
                        } else {
                            panel = new PanelDetails(lingua, panelColor, panelForegroundColor, requisicoes.getTypeOfMaterial().getTypeOfMaterialImage(), requisicoes.getTypeOfMaterial().getTypeOfMaterialName());
                            panel.setInterval(2);
                            panel.create();
                            spanel.setPreferredSize(panel.alternativePanel().getPreferredSize());
                            this.addPanel(spanel, panel.alternativePanel());
                        }
                    } else {
                        panel = new PanelDetails(lingua, panelColor, panelForegroundColor, requisicoes.getTypeOfMaterial().getTypeOfMaterialImage(), requisicoes.getTypeOfMaterial().getTypeOfMaterialName());
                        panel.setInterval(2);
                        panel.create();
                        spanel.setPreferredSize(panel.alternativePanel().getPreferredSize());
                        this.addPanel(spanel, panel.alternativePanel());
                    }
                }
                );

        tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
        tabela.setShowGrid(false);
        tabela.setShowHorizontalLines(true);
        tabela.setFocusable(true);
        tabela.setGridColor(Color.WHITE);

        tabela.getInputMap(JComponent.WHEN_FOCUSED)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        tabela.getActionMap()
                .put("Enter", new AbstractAction() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void actionPerformed(ActionEvent ae
                    ) {
                        if (tabela.getSelectedRowCount() == 0) {
                            tabela.setRowSelectionInterval(0, 0);
                        }
                    }
                }
                );
        tabela.getActionMap()
                .put("cancel", new AbstractAction() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void actionPerformed(ActionEvent ae
                    ) {
                        tabela.clearSelection();
                    }
                }
                );

        spanel.addComponentListener(
                new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e
            ) {
                if ((!isListEmpty()) && (tabela.getSelectedRow() >= 0)) {
                    Object[] baux = requisicoes.getRequests().toArray();
                    Keys.Request req = (Keys.Request) baux[TableRequest.this.selecionado];
                    String[] results;
                    String[] titulos;
                    String atividade;
                    if ((req.getActivity().equals("") || (req.getActivity().equals("sem")))) {
                        atividade = lingua.translate("Não existe descrição");
                    } else {
                        atividade = req.getActivity();
                    }
                    if (req.getBeginDate().isBigger(req.getEndDate()) == 0) {
                        if (!devolucoes) {
                            titulos = new String[]{"Recurso", "Utilizador", "Data", "Atividade", "Disciplina", "Horário", "Dia da semana"};
                            results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toString(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeBegin().toString(0) + " - " + req.getTimeEnd().toString(0), req.getWeekDay().perDayName()};
                        } else {
                            titulos = new String[]{"Recurso", "Utilizador", "Data", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana"};
                            results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toString(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), req.getWeekDay().perDayName()};
                        }
                    } else if (!devolucoes) {
                        titulos = new String[]{"Recurso", "Utilizador", "Data de levantamento", "Data de devolução", "Atividade", "Disciplina", "Hora de levantamento", "Dia da semana (levantamento)"};
                        results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toStringWithMonthWord(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeBegin().toString(0), req.getWeekDay().perDayName()};
                    } else {
                        try {
                            titulos = new String[]{"Recurso", "Utilizador", "Data de devolução", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana (devolução)"};
                            results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), new TimeDate.WeekDay(req.getEndDate()).perDayName()};
                        } catch (ParseException ex) {
                            titulos = new String[]{"Recurso", "Utilizador", "Data de devolução", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana (devolução)"};
                            results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), ""};
                        }
                    }
                    panel = new PanelDetails(panelColor, panelForegroundColor, titulos, results, lingua, requisicoes.getTypeOfMaterial().getTypeOfMaterialImage(), getSelectedRequest().getMaterial().getMaterialImage(), requisicoes.getTypeOfMaterial().getTypeOfMaterialName(), spanel);
                    panel.setInterval(2);
                    panel.create();
                    spanel.removeAll();
                    javax.swing.GroupLayout jPanelInformaBaixoLayout = new javax.swing.GroupLayout(spanel);
                    spanel.setLayout(jPanelInformaBaixoLayout);
                    jPanelInformaBaixoLayout.setHorizontalGroup(
                            jPanelInformaBaixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addGroup(jPanelInformaBaixoLayout.createSequentialGroup()
                                    .addContainerGap(117, Short.MAX_VALUE))
                    );
                    jPanelInformaBaixoLayout.setVerticalGroup(
                            jPanelInformaBaixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInformaBaixoLayout.createSequentialGroup()
                                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    );
                }
            }

            @Override
            public void componentMoved(ComponentEvent e
            ) {
            }

            @Override
            public void componentShown(ComponentEvent e
            ) {
            }

            @Override
            public void componentHidden(ComponentEvent e
            ) {
            }
        }
        );

        /* Método para sort
         List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
         sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
         TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela.getModel());
         tabela.setRowSorter(sorter);
         sorter.setSortKeys(sortKeys);
         */
    }

    private void addPanel(javax.swing.JPanel panel, javax.swing.JPanel panel2) {
        panel.removeAll();
        javax.swing.GroupLayout jPanelInformaBaixoLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanelInformaBaixoLayout);
        jPanelInformaBaixoLayout.setHorizontalGroup(
                jPanelInformaBaixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addGroup(jPanelInformaBaixoLayout.createSequentialGroup()
                        .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanelInformaBaixoLayout.setVerticalGroup(
                jPanelInformaBaixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInformaBaixoLayout.createSequentialGroup()
                        .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
        );
    }

    public void clean() {
        tabela.removeAll();
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        int tam = modelo.getRowCount();
        for (int i = 0; i < tam; i++) {
            modelo.removeRow(0);
        }
        tabela.repaint();
    }

    public void serchBy(int opcao, String person) {
        this.clean();
        requisicoes.searchBy(opcao, person);
        this.procura = true;
        if (requisicoes.getRequests().isEmpty()) {
            tabela.removeBorderColor(0);
        }
        this.create();

    }

    public void serchByTime(Boolean bool, TimeDate.Time time) {
        this.clean();
        if (bool) {
            requisicoes.searchByTime(bool, time, true, false);
        } else {
            requisicoes.searchByTime(bool, time, false, false);
        }
        if (requisicoes.getRequests().isEmpty()) {
            tabela.removeBorderColor(0);
        }
        this.procura = true;
        this.create();
    }

    public void destroySearch() {
        this.clean();
        requisicoes.reMake();
        this.procura = false;
        this.create();

    }

    public void addListenenerSelectionRequisitions(javax.swing.JButton bt2, javax.swing.JButton bt3) {
        this.addKeyListenerRequisitions(bt2, bt3);
        tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (selecionado >= 0) {
                if (requisicoes.getRequests().size() > (selecionado)) {
                    Keys.Request req = requisicoes.getSelectedRequest(selecionado);
                    boolean estadomaterial;
                    if (DataBase.DataBase.testConnection(url)) {
                        DataBase.DataBase db = new DataBase.DataBase(url);
                        estadomaterial = db.getStateOfMaterial(req);
                        db.close();
                    } else {
                        estadomaterial = req.getMaterial().isLoaned();
                    }
                    TimeDate.Time tempo = new TimeDate.Time();
                    TimeDate.Date data = new TimeDate.Date();
                    int valfinal;
                    int val = tempo.compareTime(req.getTimeBegin());
                    if ((!devolucoes) && (selecionado >= 0)) {
                        if ((req.getBeginDate().getDay() == data.getDay()) && (req.getBeginDate().getMonth() == data.getMonth()) && (req.getBeginDate().getYear() == data.getYear())) {
                            if (new TimeDate.Date().getDayYear() < req.getEndDate().getDayYear()) {
                                valfinal = tempo.compareTime(req.getTimeEnd()) + ((req.getEndDate().getDayYear() - new TimeDate.Date().getDayYear()) * 86400);
                            } else {
                                valfinal = tempo.compareTime(req.getTimeEnd());
                            }
                            if ((val < antes_hora) && (valfinal >= depois_hora) && (!estadomaterial)) {
                                bt2.setEnabled(true);
                                bt3.setEnabled(true);
                            } else {
                                bt2.setEnabled(false);
                                bt3.setEnabled(true);
                            }
                        } else {
                            bt2.setEnabled(false);
                            bt3.setEnabled(true);
                        }
                    } else {
                        bt2.setEnabled(true);
                        bt3.setEnabled(true);
                    }
                } else {
                    bt2.setEnabled(false);
                    bt3.setEnabled(false);
                }
            } else {
                bt2.setEnabled(false);
                bt3.setEnabled(false);
            }

        });
    }

    private void addKeyListenerRequisitions(javax.swing.JButton bt2, javax.swing.JButton bt3) {
        tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tabela.clearSelection();
                    bt2.setEnabled(false);
                    bt3.setEnabled(false);
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (selecionado >= 0) {
                    if (requisicoes.getRequests().size() > (selecionado)) {
                        Keys.Request req = requisicoes.getSelectedRequest(selecionado);
                        boolean estadomaterial;
                        if (DataBase.DataBase.testConnection(url)) {
                            DataBase.DataBase db = new DataBase.DataBase(url);
                            estadomaterial = db.getStateOfMaterial(req);
                            db.close();
                        } else {
                            estadomaterial = req.getMaterial().isLoaned();
                        }
                        TimeDate.Time tempo = new TimeDate.Time();
                        TimeDate.Date data = new TimeDate.Date();
                        int valfinal;
                        int val = tempo.compareTime(req.getTimeBegin());
                        if (!devolucoes) {
                            if ((req.getBeginDate().getDay() == data.getDay()) && (req.getBeginDate().getMonth() == data.getMonth()) && (req.getBeginDate().getYear() == data.getYear())) {
                                if (new TimeDate.Date().getDayYear() < req.getEndDate().getDayYear()) {
                                    valfinal = tempo.compareTime(req.getTimeEnd()) + ((req.getEndDate().getDayYear() - new TimeDate.Date().getDayYear()) * 86400);
                                } else {
                                    valfinal = tempo.compareTime(req.getTimeEnd());
                                }
                                if ((val < antes_hora) && (valfinal >= depois_hora) && (!estadomaterial)) {
                                    bt2.setEnabled(true);
                                    bt3.setEnabled(true);
                                } else {
                                    bt2.setEnabled(false);
                                    bt3.setEnabled(true);
                                }
                            } else {
                                bt2.setEnabled(false);
                                bt3.setEnabled(true);
                            }
                        } else {
                            bt2.setEnabled(true);
                            bt3.setEnabled(true);
                        }
                    } else {
                        bt2.setEnabled(false);
                        bt3.setEnabled(false);
                    }
                } else {
                    bt2.setEnabled(false);
                    bt3.setEnabled(false);
                }
            }
        });
    }

    public void addTimerColors() {
        //Timer
        time = new Timer(100, new ActionListener() {
            TimeDate.Time tempo;
            TimeDate.Date data;
            DefaultTableModel modelo;

            @Override
            public void actionPerformed(ActionEvent e) {
                tempo = new TimeDate.Time();
                data = new TimeDate.Date();
                modelo = (DefaultTableModel) tabela.getModel();
                if (!devolucoes) {
                    if ((modelo.getRowCount() > 0) && (!requisicoes.getRequests().isEmpty())) {
                        int i = 0;
                        int valfinal;
                        int val;
                        int estado;
                        DataBase.DataBase db = new DataBase.DataBase(url);
                        for (Keys.Request req : requisicoes.getRequests()) {
                            estado = db.getStateOfMaterial(req.getMaterial());
                            val = tempo.compareTime(req.getTimeBegin());
                            if ((req.getBeginDate().getDay() == data.getDay()) && (req.getBeginDate().getMonth() == data.getMonth()) && (req.getBeginDate().getYear() == data.getYear())) {
                                if (new TimeDate.Date().getDayYear() < req.getEndDate().getDayYear()) {
                                    tempo = new TimeDate.Time();
                                    valfinal = tempo.compareTime(req.getTimeEnd()) + ((req.getEndDate().getDayYear() - new TimeDate.Date().getDayYear()) * 86400);
                                } else {
                                    valfinal = tempo.compareTime(req.getTimeEnd());
                                }
                                if ((val < antes_hora) && (valfinal >= depois_hora) && (estado == 0)) {
                                    tabela.setBorderColor(i, cores[0]);
                                } else if ((val < antes_hora) && (valfinal >= depois_hora) && (estado == 1)) {
                                    tabela.setBorderColor(i, cores[2]);
                                } else if (valfinal < 0) {
                                    tabela.setBorderColor(i, cores[1]);
                                } else {
                                    tabela.removeBorderColor(i);
                                }
                            } else {
                                tabela.removeBorderColor(i);
                            }
                            tabela.repaint();
                            i++;
                        }
                        db.close();
                    }
                } else if ((modelo.getRowCount() > 0) && (!requisicoes.getRequests().isEmpty())) {
                    int i = 0;
                    int valfinal;
                    for (Keys.Request req : requisicoes.getRequests()) {
                        if ((req.getEndDate().getDay() == data.getDay()) && (req.getEndDate().getMonth() == data.getMonth()) && (req.getEndDate().getYear() == data.getYear())) {
                            valfinal = tempo.compareTime(req.getTimeEnd());
                            if (valfinal >= depois_hora + atrasolegal) {
                                tabela.setBorderColor(i, cores[0]);
                            } else if (valfinal < 0) {
                                tabela.setBorderColor(i, cores[1]);
                            } else {
                                tabela.removeBorderColor(i);
                            }
                        } else {
                            if (data.isBigger(req.getEndDate()) < 0) {
                                tabela.setBorderColor(i, cores[1]);
                            } else {
                                tabela.removeBorderColor(i);
                            }
                        }
                        tabela.repaint();
                        i++;
                    }
                }
            }
        });
    }

    public void stopTimerColors() {
        time.stop();
    }

    public void startTimerColors() {
        time.start();
    }

    public javax.swing.JTable getTable() {
        return this.tabela;

    }

    public Keys.Request getSelectedRequest() {
        Object[] baux;
        if (selecionado >= 0) {
            baux = requisicoes.getRequests().toArray();
            if (baux.length > 0) {
                return this.requisicoes.getSelectedRequest(selecionado);
            }
        }
        return null;
    }

    public boolean removeSelectedRequest() {
        if (this.getSelectedRequest() != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            Keys.Request req = this.getSelectedRequest();
            modelo.removeRow(selecionado);
            requisicoes.removeRequest(req);
            if (modelo.getRowCount() == 0) {
                ficouvazia = true;
                tabela.removeBorderColor(0);
                tabela.repaint();
            }
            this.create();
            return true;
        }
        return false;
    }

    /**
     * @return the selecionado
     */
    public int getSelectedRows() {
        return selecionado;
    }

    /**
     * @return the lista
     */
    public RequestList getRequestList() {
        return requisicoes;
    }

    public void setRquestList(RequestList requisicoes) {
        this.requisicoes = requisicoes;

    }

    /**
     * @return the color
     */
    public Color getBackGroundColor() {
        return backColor;
    }

    /**
     * @param color the color to set
     */
    public void setBackGroundColor(Color color) {
        this.backColor = color;
        tabela.setBackground(color);
        tabela.alternateColorRows();
        tabela.repaint();
    }

    /**
     * @return the forecolor
     */
    public Color getForegroundcolor() {
        return foreColor;
    }

    /**
     * @param forecolor the forecolor to set
     */
    public void setForegroundcolor(Color forecolor) {
        this.foreColor = forecolor;
        tabela.setForeground(forecolor);
        tabela.repaint();
    }

    /**
     * @return the selectColor
     */
    public Color getSelectColor() {
        return selectColor;
    }

    /**
     * @param selectColor the selectColor to set
     */
    public void setSelectColor(Color selectColor) {
        this.selectColor = selectColor;
        tabela.setSelectionBackground(selectColor);
        tabela.repaint();

    }

    /**
     * @return the panelColor
     */
    public Color getPanelColor() {
        return panelColor;
    }

    /**
     * @param panelColor the panelColor to set
     * @param panelForegroundColor
     */
    public void setPanelColor(Color panelColor, Color panelForegroundColor) {
        this.panelColor = panelColor;
        this.panelForegroundColor = panelForegroundColor;
        if (tabela.getColumnCount() > 1) {
            int select = TableRequest.this.selecionado;
            if (select >= 0) {
                Object[] baux = requisicoes.getRequests().toArray();
                Keys.Request req = (Keys.Request) baux[select];
                String[] results;
                String[] titulos;
                String atividade;
                if ((req.getActivity().equals("") || (req.getActivity().equals("sem")))) {
                    atividade = lingua.translate("Não existe descrição");
                } else {
                    atividade = req.getActivity();
                }
                if (req.getBeginDate().isBigger(req.getEndDate()) == 0) {
                    if (!devolucoes) {
                        titulos = new String[]{"Recurso", "Utilizador", "Data", "Atividade", "Disciplina", "Horário", "Dia da semana"};
                        results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toString(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeBegin().toString(0) + " - " + req.getTimeEnd().toString(0), req.getWeekDay().perDayName()};
                    } else {
                        titulos = new String[]{"Recurso", "Utilizador", "Data", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana"};
                        results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toString(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), req.getWeekDay().perDayName()};
                    }
                } else if (!devolucoes) {
                    titulos = new String[]{"Recurso", "Utilizador", "Data de levantamento", "Data de devolução", "Atividade", "Disciplina", "Hora de levantamento", "Dia da semana (levantamento)"};
                    results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getBeginDate().toStringWithMonthWord(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeBegin().toString(0), req.getWeekDay().perDayName()};
                } else {
                    try {
                        titulos = new String[]{"Recurso", "Utilizador", "Data de devolução", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana (devolução)"};
                        results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), new TimeDate.WeekDay(req.getEndDate()).perDayName()};
                    } catch (ParseException ex) {
                        titulos = new String[]{"Recurso", "Utilizador", "Data de devolução", "Atividade", "Disciplina", "Hora de devolução", "Dia da semana (devolução)"};
                        results = new String[]{lingua.translate(req.getMaterial().getTypeOfMaterialName()) + ": " + lingua.translate(req.getMaterial().getDescription()), req.getPerson().getName(), req.getEndDate().toStringWithMonthWord(), lingua.translate(atividade), lingua.translate(req.getSubject().getName()), req.getTimeEnd().toString(0), ""};
                    }
                }
                panel = new PanelDetails(panelColor, panelForegroundColor, titulos, results, lingua, requisicoes.getTypeOfMaterial().getTypeOfMaterialImage(), this.getSelectedRequest().getMaterial().getMaterialImage(), requisicoes.getTypeOfMaterial().getTypeOfMaterialName(), spanel);
                panel.setInterval(2);
                panel.create();
                spanel.setPreferredSize(panel.getPreferredSize());
                this.addPanel(spanel, panel);
            } else {
                panel = new PanelDetails(lingua, panelColor, panelForegroundColor, requisicoes.getTypeOfMaterial().getTypeOfMaterialImage(), requisicoes.getTypeOfMaterial().getTypeOfMaterialName());
                panel.setInterval(2);
                panel.create();
                spanel.setPreferredSize(panel.alternativePanel().getPreferredSize());
                this.addPanel(spanel, panel.alternativePanel());
            }
        } else {
            panel = new PanelDetails(lingua, panelColor, panelForegroundColor, requisicoes.getTypeOfMaterial().getTypeOfMaterialImage(), requisicoes.getTypeOfMaterial().getTypeOfMaterialName());
            panel.setInterval(2);
            panel.create();
            spanel.setPreferredSize(panel.alternativePanel().getPreferredSize());
            this.addPanel(spanel, panel.alternativePanel());
        }
    }

    /**
     * @return the spanel
     */
    public javax.swing.JPanel getSpanel() {
        return spanel;
    }

    /**
     * @return the cores
     */
    public Color[] getRowColors() {
        return cores;
    }

    /**
     * @param cores the cores to set
     */
    public void setRowColors(Color[] cores) {
        this.cores = cores;
    }

    /**
     * @return the antes_hora
     */
    public int getBeforeHour() {
        return antes_hora;
    }

    /**
     * @param antes_hora the antes_hora to set
     */
    public void setBeforeHour(int antes_hora) {
        this.antes_hora = antes_hora * 60;
    }

    /**
     * @return the depois_hora
     */
    public int getAfterHour() {
        return depois_hora;
    }

    /**
     * @param depois_hora the depois_hora to set
     */
    public void setAfterHour(int depois_hora) {
        this.depois_hora = -depois_hora * 60;
    }

    /**
     * @return the procura
     */
    public boolean isResultOfASearch() {
        return procura;
    }

    /**
     * @param procura the procura to set
     */
    public void setResultOfASearch(boolean procura) {
        this.procura = procura;
    }

    /**
     * @return the vazia
     */
    public boolean isListEmpty() {
        return vazia;
    }

    /**
     * @return the foregroundSelectColor
     */
    public Color getForegroundSelectColor() {
        return foregroundSelectColor;
    }

    /**
     * @param foregroundSelectColor the foregroundSelectColor to set
     */
    public void setForegroundSelectColor(Color foregroundSelectColor) {
        this.foregroundSelectColor = foregroundSelectColor;
        tabela.setSelectionForeground(foregroundSelectColor);
        tabela.repaint();
    }

    /**
     * @return the atrasolegal
     */
    public int getPermittedDelay() {
        return atrasolegal;
    }

    /**
     * @param atrasolegal the atrasolegal to set
     */
    public void setPermittedDelay(int atrasolegal) {
        this.atrasolegal = -atrasolegal;
    }

}
