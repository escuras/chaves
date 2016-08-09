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
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
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
    
    public WMaterial(){
        super();
        panelcor = DEFAULT_PANEL_COLOR;
        this.dialogopai = null;
        painelgeral = new JPanel();
        lingua = new Langs.Locale();
        mat = new Keys.Material();
    }
    
    
    public WMaterial(javax.swing.JDialog dialogo, Keys.Material mat, Color cor, Langs.Locale lingua, String url){
        super(dialogo);
        this.dialogopai = dialogo;
        panelcor = cor;
        painelgeral = new JPanel();
        this.lingua = lingua;
        this.url = url;
        this.mat = mat;
    }
    
    public void create(){
        this.setPreferredSize(new Dimension(700,500));
        this.setResizable(false);
        this.setMinimumSize(new Dimension(700,500));
        this.setMaximumSize(new Dimension(700,500));
        this.setBackground(new Color(254,254,254));
        painelgeral.setPreferredSize(new Dimension(7000,500));
        painelgeral.setBounds(0, 0, 700, 500);
        javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(panelcor, 6), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1), border11);
        painelgeral.setBorder(border22);
        this.add(painelgeral);
        
        JPanel jpanelesquerda = new javax.swing.JPanel();
        jpanelesquerda.setBackground(painelgeral.getBackground());
        org.jdesktop.swingx.JXTable tabela = new org.jdesktop.swingx.JXTable();
        javax.swing.JScrollPane panelscroll = new javax.swing.JScrollPane();
        tabela.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{lingua.translate("Utilizador")}));
        tabela.setPreferredSize(new Dimension(320,250));
        tabela.setBounds(0, 0, 320, 300);
        panelscroll.setPreferredSize(new Dimension(320,290));
        panelscroll.setBounds(0, 0, 320, 300);
        panelscroll.setViewportView(tabela);
        jpanelesquerda.add(panelscroll);
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.addRow(new String[]{"isto"});
        modelo.addRow(new String[]{"aquilo"});
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(javax.swing.JLabel.LEFT);
        tabela.setRowHeight(30);
        DataBase.DataBase db = new DataBase.DataBase(url);
        java.util.List<Keys.Feature> lista = db.getFeaturesByMaterial(mat);
        String auxiliar;
        for (Keys.Feature fea : lista) {
            auxiliar = fea.getDescription();
            if (fea.getNumber() != 0) {
                auxiliar += auxiliar + " "+fea.getNumber()+" ("+fea.getUnityMeasure()+")";
            }
            modelo.addRow(new String[]{auxiliar});
        }
        panelscroll.setBorder(null);
        tabela.setBorder(BorderFactory.createLineBorder(new Color(1,1,1), 1));
        tabela.getColumnModel().getColumn(0).setCellRenderer(renderer);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() { 
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setFont(new Font("Cantarell", Font.PLAIN, 14));
                this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, painelgeral.getBackground()), BorderFactory.createMatteBorder(1, 1, 0, 1,new Color(1,1,1))));
                return this;
            }

        };
        headerRenderer.setBackground(new Color(80, 80, 80));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setPreferredSize(new Dimension(100, 30));
        headerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        tabela.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
        tabela.setEnabled(false);
        
        
        JPanel jpaneldireitacima = new javax.swing.JPanel();
        jpaneldireitacima.setBackground(Color.blue);
        
        
        
        
        JPanel jpaneltituloesquerda = new javax.swing.JPanel();
        jpaneltituloesquerda.setBackground(painelgeral.getBackground());
        javax.swing.JComboBox comboboxopcoes = new javax.swing.JComboBox(new String[]{lingua.translate("Caraterísticas de")+" "+lingua.translate(mat.getTypeOfMaterialName()).toLowerCase(), lingua.translate("Software em computadores"), lingua.translate("Disciplinas relacionadas")});
        ((javax.swing.JLabel)comboboxopcoes.getRenderer()).setHorizontalAlignment(javax.swing.JLabel.CENTER);
        BasicComboPopup popupVista = (BasicComboPopup) comboboxopcoes.getAccessibleContext().getAccessibleChild(0);
        popupVista.getList().setSelectionBackground(Color.DARK_GRAY);
        popupVista.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        comboboxopcoes.setPreferredSize(new Dimension(310,26));
        comboboxopcoes.setFocusable(false);
        comboboxopcoes.setBackground(new Color(213,213,213));
        comboboxopcoes.setBounds(0, 0, 320, 40);
        jpaneltituloesquerda.add(comboboxopcoes);
        
        
        JPanel jpaneltitulodireita = new javax.swing.JPanel();
        jpaneltitulodireita.setBackground(Color.GREEN);
        JPanel jpaneldireitabaixo = new javax.swing.JPanel();
        jpaneldireitabaixo.setBackground(Color.PINK);
        JPanel jpanelbaixo = new javax.swing.JPanel();
        jpanelbaixo.setBackground(Color.ORANGE);
       

        //jpanel2 = jpanelesquerda
        //jpanel4 = jpaneldireitacima
        //jpanel7 = jpaneldireitabaixo
        //jpanel5 = jpaneltituloesquerda
        //jpanel6 = jpaneltitulodireita
        //jpanel8 = jpanelbaixo
        
        jpanelesquerda.setPreferredSize(new java.awt.Dimension(315, 300));
        jpaneldireitacima.setPreferredSize(new java.awt.Dimension(315, 147));
        jpaneltituloesquerda.setPreferredSize(new java.awt.Dimension(315, 40));
        jpaneltitulodireita.setPreferredSize(new java.awt.Dimension(315, 40));
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
               .addGap(34,34,34)
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
    
    public void appear(){
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
    
}
