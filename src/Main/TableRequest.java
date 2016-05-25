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
import java.util.EventObject;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.plaf.ComponentUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author toze
 */
public class TableRequest {

    public static final Color DEFAULT_BACKGROUND_COLOR = UIManager.getColor("Table.background");
    public static final Color DEFAULT_FOREGROUND_COLOR = Color.black;
    public static final Color DEFAULT_SELECT_COLOR = Color.DARK_GRAY;
    public static final Color DEFAULT_PANEL_COLOR = Color.WHITE;
    public static final float DEFAULT_DIVIDER_LOCATION = 0.7f;
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
    private final JSplitPane spanel;
    private Main.PanelDetails panel;
    private final Main.RequestList requisicao;
    private float panelDividerLocation;
    private Color[] cores;
    private int antes_hora;
    private int depois_hora;

    public TableRequest(RequestList requisicao, javax.swing.JSplitPane spanel, Langs.Locale lingua) {
        this.requisicao = requisicao;
        this.lingua = lingua;
        this.selecionado = -1;
        this.spanel = spanel;
        this.lista = requisicao;
        antes_hora = TableRequest.DEFAULT_BEFORE_TIME * 60;
        depois_hora = TableRequest.DEFAULT_AFTER_TIME * 60;
        cores = new Color[]{Color.GREEN.darker(), Color.RED};
        backColor = TableRequest.DEFAULT_BACKGROUND_COLOR;
        foreColor = TableRequest.DEFAULT_FOREGROUND_COLOR;
        selectColor = TableRequest.DEFAULT_SELECT_COLOR;
        panelColor = TableRequest.DEFAULT_PANEL_COLOR;
        panelDividerLocation = TableRequest.DEFAULT_DIVIDER_LOCATION;
        panel = new PanelDetails(lingua, panelColor, requisicao.getTypeOfMaterial().getTypeOfMaterialName());
        tabela = new Components.RowTable(new javax.swing.JTable().getModel());

    }

    public void create() {
        tabela.removeAll();
        panel.setInterval(2);
        panel.create();
        spanel.setLeftComponent(panel.alternativePanel());
        spanel.setDividerLocation(panelDividerLocation);
        if (!requisicao.getRequests().isEmpty()) {
            tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Nome"), lingua.translate(requisicao.getTypeOfMaterial().getTypeOfMaterialName()), lingua.translate("Horário"), lingua.translate("Data")}));
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            for (Clavis.Request req : requisicao.getRequests()) {
                Object[] ob = {req.getPerson().getName(), req.getMaterial().getDescription(), req.getTimeBegin().toString(0) + " - " + req.getTimeEnd().toString(0), req.getBeginDate()};
                modelo.addRow(ob);
            }
        } else {
            tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Situação")}));
            Object[] ob = {};
            System.out.println(requisicao.isConnected());
            if (!requisicao.isConnected()) {
                ob = new Object[]{lingua.translate("Problema_de_rede_ou_ligação_base_de_dados")};
            } else if ((requisicao.getRequests().isEmpty())){
                ob = new Object[]{lingua.translate("Não_existem_registos_para_hoje.")};
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
        tabela.setToolTipText(lingua.translate("Lista_de_requisições"));
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
                        renderer2.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
                        break;
                    case 2:
                        renderer2.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
                        break;
                    case 3:
                        renderer2.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
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
        tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            TableRequest.this.selecionado = tabela.getSelectedRow();
            if (tabela.getColumnCount() > 1) {
                if (!e.getValueIsAdjusting()) {
                    String titulo = "Detalhes";
                    String[] titulos = {"Utilizador", "Recurso", "Data"};
                    int select = TableRequest.this.selecionado;
                    if (select >= 0) {
                        Object[] baux = lista.getRequests().toArray();
                        Clavis.Request req = (Clavis.Request) baux[select];
                        String[] results = {req.getPerson().getName(), req.getMaterial().getTypeOfMaterialName() + " " + req.getMaterial().getDescription(), req.getBeginDate().toStringWithMonthWord()};
                        panel = new PanelDetails(panelColor, titulo, titulos, results, lingua, requisicao.getTypeOfMaterial().getTypeOfMaterialName());
                        panel.setInterval(2);
                        panel.create();
                        spanel.setLeftComponent(panel);
                        spanel.setDividerLocation(panelDividerLocation);
                    } else {
                        panel = new PanelDetails(lingua, panelColor, requisicao.getTypeOfMaterial().getTypeOfMaterialName());
                        panel.setInterval(2);
                        panel.create();
                        spanel.setLeftComponent(panel.alternativePanel());
                        spanel.setDividerLocation(panelDividerLocation);
                    }
                }
            } else {
                panel = new PanelDetails(lingua, panelColor, requisicao.getTypeOfMaterial().getTypeOfMaterialName());
                panel.setInterval(2);
                panel.create();
                spanel.setLeftComponent(panel.alternativePanel());
                spanel.setDividerLocation(panelDividerLocation);
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

        /*tabela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tabela.getSelectedRow() <= tabela.getRowCount()) {
                        tabela.setRowSelectionInterval(selecionado + 1, selecionado + 1);
                        ButtonEditor editor = (ButtonEditor) tabela.getColumn(lingua.translate("Detalhes")).getCellEditor();
                        editor.setActive(false);
                        editor.setSelect(-1);
                        editor.button.doClick();
                    }
                }
            }
        });*/
 /* Método para sort
         List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
         sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
         TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela.getModel());
         tabela.setRowSorter(sorter);
         sorter.setSortKeys(sortKeys);
         */
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

    public void addTimerColors() {
        //Timer
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        Timer time = new Timer(5000, new ActionListener() {
            TimeDate.Time tempo;
            TimeDate.Date data;
            @Override
            public void actionPerformed(ActionEvent e) {
                tempo = new TimeDate.Time();
                data = new TimeDate.Date();
                if (modelo.getRowCount() > 0) {
                    int i = 0;
                    for (Clavis.Request req : requisicao.getRequests()) {
                        int val = tempo.compareTime(req.getTimeBegin());
                        int valfinal;
                        if ((req.getBeginDate().getDay() == data.getDay()) && (req.getBeginDate().getMonth() == data.getMonth()) && (req.getBeginDate().getYear() == data.getYear())) {
                            if (new TimeDate.Date().getDayYear() < req.getEndDate().getDayYear()) {
                                valfinal = new TimeDate.Time(0, 0, 0).compareTime(req.getTimeEnd()) + tempo.compareTime(new TimeDate.Time(23, 59, 59)) + (84600 * (new TimeDate.Date().getDayYear() - req.getEndDate().getDayYear()) - 1);
                            } else {
                                valfinal = tempo.compareTime(req.getTimeEnd());
                            }
                            if ((val < antes_hora) && (valfinal >= depois_hora)) {
                                //tabela.setBorderColor(i, cores[0]);
                                tabela.setRowColor(i, cores[0]);

                            } else if (valfinal < 0) {
                                tabela.setRowColor(i, cores[1]);
                                //tabela.setBorderColor(i, cores[1]);
                            } else {
                                //tabela.removeBorderColor(i);

                            }
                        }
                        tabela.repaint();
                        i++;
                    }
                }
            }
        });
        time.start();
    }

    public javax.swing.JTable getTable() {
        return this.tabela;

    }

    public Clavis.Request getSelectedRequest() {
        return this.lista.getSelectedRequest(selecionado);
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
        if (tabela.getColumnCount() > 1) {
            String titulo = "Detalhes";
            String[] titulos = {"Utilizador", "Recurso", "Data"};
            int select = TableRequest.this.selecionado;
            if (select >= 0) {
                Object[] baux = lista.getRequests().toArray();
                Clavis.Request req = (Clavis.Request) baux[select];
                String[] results = {req.getPerson().getName(), req.getMaterial().getTypeOfMaterialName() + " " + req.getMaterial().getDescription(), req.getBeginDate().toStringWithMonthWord()};
                panel = new PanelDetails(panelColor, titulo, titulos, results, lingua, requisicao.getTypeOfMaterial().getTypeOfMaterialName());
                panel.setInterval(2);
                panel.create();
                spanel.setLeftComponent(panel);
                spanel.setDividerLocation(panelDividerLocation);
            } else {
                panel = new PanelDetails(lingua, panelColor, requisicao.getTypeOfMaterial().getTypeOfMaterialName());
                panel.setInterval(2);
                panel.create();
                spanel.setLeftComponent(panel.alternativePanel());
                spanel.setDividerLocation(panelDividerLocation);
            }
        }
    }

    /**
     * @return the spanel
     */
    public JSplitPane getSpanel() {
        return spanel;
    }

    /**
     * @return the panelDividerLocation
     */
    public float getPanelDividerLocation() {
        return panelDividerLocation;
    }

    /**
     * @param panelDividerLocation the panelDividerLocation to set
     */
    public void setPanelDividerLocation(float panelDividerLocation) {
        this.panelDividerLocation = panelDividerLocation;

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

}
