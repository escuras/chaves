/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
    public static final Color DEFAULT_SELECT_FOREGROUND_COLOR = Color.WHITE;
    public static final int DEFAULT_AFTER_TIME = 0;
    public static final int DEFAULT_BEFORE_TIME = 10;

    private final Components.RowTable tabela;
    private final Langs.Locale lingua;
    private int selecionado;
    private RequestList lista;
    private Color panelColor;
    private Color backColor;
    private Color foreColor;
    private Color selectColor;
    private Color foregroundSelectColor;
    private final javax.swing.JPanel spanel;
    private Main.PanelDetails panel;
    private Color[] cores;
    private int antes_hora;
    private int depois_hora;
    private boolean ficouvazia;
    private boolean vazia;
    private boolean procura;
    private final boolean devolucoes;
    private final int tipomaterial;
    private Timer time;

    public TableRequest(RequestList requisicao, javax.swing.JPanel spanel, Langs.Locale lingua, boolean devolucoes) {
        this.lingua = lingua;
        this.selecionado = -1;
        this.devolucoes = devolucoes;
        this.spanel = spanel;
        this.lista = requisicao;
        procura = false;
        vazia = false;
        ficouvazia = false;
        this.tipomaterial = requisicao.getTypeOfMaterial().getMaterialTypeID();
        antes_hora = TableRequest.DEFAULT_BEFORE_TIME * 60;
        depois_hora = TableRequest.DEFAULT_AFTER_TIME * 60;
        cores = new Color[]{Color.GREEN.darker(), Color.RED};
        backColor = TableRequest.DEFAULT_BACKGROUND_COLOR;
        foreColor = TableRequest.DEFAULT_FOREGROUND_COLOR;
        selectColor = TableRequest.DEFAULT_SELECT_COLOR;
        panelColor = TableRequest.DEFAULT_PANEL_COLOR;
        panel = new PanelDetails(lingua, panelColor, requisicao.getTypeOfMaterial().getTypeOfMaterialImage(), requisicao.getTypeOfMaterial().getTypeOfMaterialName());
        tabela = new Components.RowTable(new javax.swing.JTable().getModel());
    }

    public void create() {
        tabela.removeAll();
        spanel.removeAll();
        panel.setInterval(2);
        panel.create();
        this.addPanel(spanel, panel.alternativePanel());
        if (!lista.getRequests().isEmpty()) {
            ficouvazia = false;
            vazia = false;
            if (tipomaterial == 1) {
                if (!devolucoes) {
                    tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Utilizador"), lingua.translate("Salas"), lingua.translate("Horário"), lingua.translate("Data")}));
                } else {
                    tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Utilizador"), lingua.translate("Salas"), lingua.translate("Hora_de_entrega"), lingua.translate("Data_de_entrega")}));
                }
            } else if (!devolucoes) {
                tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Utilizador"), lingua.translate(lista.getTypeOfMaterial().getTypeOfMaterialName()), lingua.translate("Inicio"), lingua.translate("Fim")}));
            } else {
                tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Utilizador"), lingua.translate(lista.getTypeOfMaterial().getTypeOfMaterialName()), lingua.translate("Hora_de_entrega"), lingua.translate("Data_de_entrega")}));
            }
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            if (tipomaterial == 1) {
                if (!devolucoes) {
                    for (Clavis.Request req :lista.getRequests()) {
                        Object[] ob = {req.getPerson().getName(), req.getMaterial().getDescription(), req.getTimeBegin().toString(0) + " - " + req.getTimeEnd().toString(0), req.getBeginDate()};
                        modelo.addRow(ob);
                    }
                } else {
                    for (Clavis.Request req : lista.getRequests()) {
                        Object[] ob = {req.getPerson().getName(), req.getMaterial().getDescription(), req.getTimeEnd().toString(0), req.getEndDate()};
                        modelo.addRow(ob);
                    }
                }
            } else if (!devolucoes) {
                for (Clavis.Request req : lista.getRequests()) {
                    Object[] ob = {req.getPerson().getName(), req.getMaterial().getDescription(), req.getTimeBegin().toString(0) + " - " + req.getBeginDate(), req.getTimeEnd().toString(0) + " - " + req.getEndDate()};
                    modelo.addRow(ob);
                }
            } else {
                for (Clavis.Request req : lista.getRequests()) {
                    Object[] ob = {req.getPerson().getName(), req.getMaterial().getDescription(), req.getTimeEnd().toString(0), req.getEndDate()};
                    modelo.addRow(ob);
                }
            }
        } else {
            vazia = true;
            tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Situação")}));
            Object[] ob = {};
            if (!devolucoes) {
                if (!lista.isConnected()) {
                    ob = new Object[]{lingua.translate("Problema_de_rede_ou_ligação_base_de_dados")};
                } else if ((lista.getRequests().isEmpty())) {
                    if (ficouvazia) {
                        if (lista.getView() == RequestList.VIEW_DAY) {
                            ob = new Object[]{lingua.translate("Não_existem_mais_registos_para_hoje.")};
                        } else {
                            ob = new Object[]{lingua.translate("Não_existem_registos.")};
                        }
                    } else if (lista.getView() == RequestList.VIEW_DAY) {
                        ob = new Object[]{lingua.translate("Não_existem_registos_para_hoje.")};
                    } else {
                        ob = new Object[]{lingua.translate("Não_existem_registos.")};
                    }
                } else {
                    ob = new Object[]{lingua.translate("Não_existem_registos.")};
                }
            } else if (!lista.isConnected()) {
                ob = new Object[]{lingua.translate("Problema_de_rede_ou_ligação_base_de_dados")};
            } else if ((lista.getRequests().isEmpty())) {
                if (ficouvazia) {
                    ob = new Object[]{lingua.translate("Não_existem_mais_devolucoes_para_hoje.")};
                } else {
                    ob = new Object[]{lingua.translate("Não_ha_registos_neste_dia.")};
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
        tabela.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 45));
        if (tabela.getColumnCount() > 1) {
            tabela.getColumnModel().getColumn(0).setPreferredWidth(350);
            tabela.getColumnModel().getColumn(1).setMinWidth(100);
            tabela.getColumnModel().getColumn(2).setMinWidth(110);
            tabela.getColumnModel().getColumn(3).setMinWidth(100);
        }
        DefaultTableCellRenderer rend = ((DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer());
        rend.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        JTableHeader th = tabela.getTableHeader();
        th.setFont(new Font("Cantarell", Font.BOLD, 13));

        // remoção do contorno da célula
        Border border = BorderFactory.createEmptyBorder(0, 5, 0, 5);
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
        tabela.setFillsViewportHeight(true);
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
        /*
        BasicSplitPaneUI ui = (BasicSplitPaneUI) spanel.getUI();
        BasicSplitPaneDivider divisor = ui.getDivider();
        divisor.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (spanel.getDividerLocation() < 10) {
                    int i = spanel.getLastDividerLocation();
                    tabela.clearSelection();
                    spanel.setDividerLocation(i);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        divisor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        divisor.setBackground(Color.CYAN);*/
 /*spanel.setUI(new BasicSplitPaneUI() {
            public BasicSplitPaneDivider createDefaultDivider() {
            return new BasicSplitPaneDivider(this) {
                public void setBorder(Border b) {
                }

                @Override
                    public void paint(java.awt.Graphics g) {
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, getSize().width, getSize().height);
                    g.setColor(Color.WHITE);
                    g.drawLine(5, getSize().height/2, getSize().width/2, getSize().height/2);
                        super.paint(g);
                    }
            };
            }
        });
        spanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));*/
        // seleção e atualização do painel
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            TableRequest.this.selecionado = tabela.getSelectedRow();
            if (tabela.getSelectedRow() >= 0) {
                if (tabela.getColumnCount() > 1) {
                    if (!e.getValueIsAdjusting()) {
                        String[] titulos = {"Utilizador", "Recurso", "Data", "Atividade_letiva", "Tempo_inicial", "Tempo_final", "Dia_da_semana"};
                        int select = TableRequest.this.selecionado;
                        if (select >= 0) {
                            Object[] baux = lista.getRequests().toArray();
                            Clavis.Request req = (Clavis.Request) baux[select];
                            String[] results = {req.getPerson().getName(), req.getMaterial().getTypeOfMaterialName() + " " + req.getMaterial().getDescription(), req.getBeginDate().toStringWithMonthWord(), req.getSubject().getName(), req.getTimeBegin().toString(), req.getTimeEnd().toString(), req.getWeekDay().perDayName()};
                            spanel.setPreferredSize(new Dimension(180, 550));
                            panel = new PanelDetails(panelColor, titulos, results, lingua, lista.getTypeOfMaterial().getTypeOfMaterialImage(), lista.getTypeOfMaterial().getTypeOfMaterialName(), spanel);
                            panel.setInterval(2);
                            panel.create();
                            this.addPanel(spanel, panel);
                        } else {
                            spanel.setPreferredSize(new Dimension(180, 210));
                            panel = new PanelDetails(lingua, panelColor, lista.getTypeOfMaterial().getTypeOfMaterialName(), lista.getTypeOfMaterial().getTypeOfMaterialName());
                            panel.setInterval(2);
                            panel.create();
                            this.addPanel(spanel, panel.alternativePanel());
                        }
                    }
                } else {
                    spanel.setPreferredSize(new Dimension(180, 210));
                    panel = new PanelDetails(lingua, panelColor, lista.getTypeOfMaterial().getTypeOfMaterialImage(), lista.getTypeOfMaterial().getTypeOfMaterialName());
                    panel.setInterval(2);
                    panel.create();
                    this.addPanel(spanel, panel.alternativePanel());
                }
            } else {
                spanel.setPreferredSize(new Dimension(180, 210));
                panel = new PanelDetails(lingua, panelColor, lista.getTypeOfMaterial().getTypeOfMaterialImage(), lista.getTypeOfMaterial().getTypeOfMaterialName());
                panel.setInterval(2);
                panel.create();
                this.addPanel(spanel, panel.alternativePanel());
            }
        });

        tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());
        tabela.setShowGrid(false);
        tabela.setShowHorizontalLines(true);
        tabela.setFocusable(true);
        tabela.setGridColor(Color.WHITE);
        tabela.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        tabela.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tabela.getSelectedRowCount() == 0) {
                    tabela.setRowSelectionInterval(0, 0);
                }
            }
        });
        tabela.getActionMap().put("cancel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tabela.clearSelection();
            }
        });

        spanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                if ((!isListEmpty()) && (tabela.getSelectedRow() >= 0)) {
                    String[] titulos = {"Utilizador", "Recurso", "Data", "Atividade_letiva", "Tempo_inicial", "Tempo_final", "Dia_da_semana"};
                    Object[] baux = lista.getRequests().toArray();
                    Clavis.Request req = (Clavis.Request) baux[TableRequest.this.selecionado];
                    String[] results = {req.getPerson().getName(), req.getMaterial().getTypeOfMaterialName() + " " + req.getMaterial().getDescription(), req.getBeginDate().toStringWithMonthWord(), req.getSubject().getName(), req.getTimeBegin().toString(), req.getTimeEnd().toString(), req.getWeekDay().perDayName()};
                    panel = new PanelDetails(panelColor, titulos, results, lingua, lista.getTypeOfMaterial().getTypeOfMaterialImage(), lista.getTypeOfMaterial().getTypeOfMaterialName(), spanel);
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
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });

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
    
    public void serchBy(int opcao, String person){
        this.clean();
        lista.searchBy(opcao,person);
        this.procura = true;
        this.create();
    }
    
    public void serchByTime(Boolean bool, TimeDate.Time time){
        this.clean();
        lista.searchByTime(bool,time);
        this.procura = true;
        this.create();
    }
    
    public void destroySearch(){
        this.clean();
        lista.reMake();
        this.procura = false;
        this.create();
    }

    public void addListenenerSelectionRequisitions(javax.swing.JButton bt2, javax.swing.JButton bt3) {
        this.addKeyListenerRequisitions(bt2, bt3);
        tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (selecionado >= 0) {
                if (lista.getRequests().size() > (selecionado)) {
                    Clavis.Request req = lista.getSelectedRequest(selecionado);
                    TimeDate.Time tempo = new TimeDate.Time();
                    TimeDate.Date data = new TimeDate.Date();
                    int valfinal = 0;
                    int val = tempo.compareTime(req.getTimeBegin());
                    boolean sit = false;
                    if (!devolucoes) {
                        if ((req.getBeginDate().getDay() == data.getDay()) && (req.getBeginDate().getMonth() == data.getMonth()) && (req.getBeginDate().getYear() == data.getYear())) {
                            if (new TimeDate.Date().getDayYear() < req.getEndDate().getDayYear()) {
                                valfinal = new TimeDate.Time(0, 0, 0).compareTime(req.getTimeEnd()) + tempo.compareTime(new TimeDate.Time(23, 59, 59)) + (84600 * (new TimeDate.Date().getDayYear() - req.getEndDate().getDayYear()) - 1);
                            } else {
                                valfinal = tempo.compareTime(req.getTimeEnd());
                            }
                            if ((val < antes_hora) && (valfinal >= depois_hora)) {
                                sit = true;
                                bt2.setEnabled(true);
                                bt3.setEnabled(true);
                            } else if (valfinal < 0) {
                                bt2.setEnabled(false);
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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tabela.clearSelection();
                    bt2.setEnabled(false);
                    bt3.setEnabled(false);
                }
            }
        });
    }

    public void addTimerColors() {
        //Timer
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        time = new Timer(1000, new ActionListener() {
            TimeDate.Time tempo;
            TimeDate.Date data;

            @Override
            public void actionPerformed(ActionEvent e) {
                tempo = new TimeDate.Time();
                data = new TimeDate.Date();
                if (modelo.getRowCount() > 0) {
                    int i = 0;
                    for (Clavis.Request req :lista.getRequests()) {
                        int val = tempo.compareTime(req.getTimeBegin());
                        int valfinal;
                        if ((req.getBeginDate().getDay() == data.getDay()) && (req.getBeginDate().getMonth() == data.getMonth()) && (req.getBeginDate().getYear() == data.getYear())) {
                            if (new TimeDate.Date().getDayYear() < req.getEndDate().getDayYear()) {
                                valfinal = new TimeDate.Time(0, 0, 0).compareTime(req.getTimeEnd()) + tempo.compareTime(new TimeDate.Time(23, 59, 59)) + (84600 * (new TimeDate.Date().getDayYear() - req.getEndDate().getDayYear()) - 1);
                            } else {
                                valfinal = tempo.compareTime(req.getTimeEnd());
                            }
                            if ((val < antes_hora) && (valfinal >= depois_hora)) {
                                tabela.setBorderColor(i, cores[0]);
                                //tabela.setRowColor(i, cores[0]);

                            } else if (valfinal < 0) {
                                //tabela.setRowColor(i, cores[1]);
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

    public Clavis.Request getSelectedRequest() {
        Object[] baux;
        if (selecionado >= 0) {
            baux = lista.getRequests().toArray();
            if (baux.length > 0) {
                return this.lista.getSelectedRequest(selecionado);
            }
        }
        return null;
    }

    public boolean removeSelectedRequest() {
        if (this.getSelectedRequest() != null) {
            lista.removeRequest(this.getSelectedRequest());
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            modelo.removeRow(selecionado);
            if (modelo.getRowCount() == 0) {
                ficouvazia = true;
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
    public RequestList getList() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setList(RequestList lista) {
        this.lista = lista;

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
     */
    public void setPanelColor(Color panelColor) {
        this.panelColor = panelColor;
        //if (tipomaterial == 1) {
        if (tabela.getColumnCount() > 1) {
            String[] titulos = {"Utilizador", "Recurso", "Data", "Atividade_letiva", "Tempo_inicial", "Tempo_final", "Dia_da_semana"};
            int select = TableRequest.this.selecionado;
            if (select >= 0) {
                spanel.setPreferredSize(new Dimension(180, 550));
                Object[] baux = lista.getRequests().toArray();
                Clavis.Request req = (Clavis.Request) baux[select];
                String[] results = {req.getPerson().getName(), req.getMaterial().getTypeOfMaterialName() + " " + req.getMaterial().getDescription(), req.getBeginDate().toStringWithMonthWord(), req.getSubject().getName(), req.getTimeBegin().toString(), req.getTimeEnd().toString(), req.getWeekDay().perDayName()};
                panel = new PanelDetails(panelColor, titulos, results, lingua, lista.getTypeOfMaterial().getTypeOfMaterialImage(), lista.getTypeOfMaterial().getTypeOfMaterialName(), spanel);
                panel.setInterval(2);
                panel.create();
                this.addPanel(spanel, panel);
            } else {
                spanel.setPreferredSize(new Dimension(180, 210));
                panel = new PanelDetails(lingua, panelColor, lista.getTypeOfMaterial().getTypeOfMaterialImage(), lista.getTypeOfMaterial().getTypeOfMaterialName());
                panel.setInterval(2);
                panel.create();
                this.addPanel(spanel, panel.alternativePanel());
            }
        } else {
            spanel.setPreferredSize(new Dimension(180, 210));
            panel = new PanelDetails(lingua, panelColor, lista.getTypeOfMaterial().getTypeOfMaterialImage(), lista.getTypeOfMaterial().getTypeOfMaterialName());
            panel.setInterval(2);
            panel.create();
            this.addPanel(spanel, panel.alternativePanel());
        }

        //} else {
        //}
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
        this.depois_hora = depois_hora * 60;
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

}
