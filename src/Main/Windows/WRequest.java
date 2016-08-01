/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Windows;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author toze
 */
public class WRequest extends javax.swing.JDialog {
    
    public static final long serialVersionUID = 1L;
    public static final Color DEFAULT_PANEL_COLOR = Color.WHITE;
    private javax.swing.JDialog dialogopai;
    private Color panelcor;
    private final JPanel painelgeral;
    
    public WRequest(){
        super();
        panelcor = DEFAULT_PANEL_COLOR;
        this.dialogopai = null;
        painelgeral = new JPanel();
    }
    
    
    public WRequest(javax.swing.JDialog dialogo,Color cor){
        super(dialogo);
        this.dialogopai = dialogo;
        panelcor = cor;
        painelgeral = new JPanel();
    }
    
    public void create(){
        this.setPreferredSize(new Dimension(700,500));
        this.setResizable(false);
        this.setMinimumSize(new Dimension(700,500));
        this.setMaximumSize(new Dimension(700,500));
        this.setBackground(Color.WHITE);
        painelgeral.setPreferredSize(new Dimension(700,500));
        painelgeral.setBounds(0, 0, 700, 500);
        javax.swing.border.Border border11 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(panelcor, 6), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        javax.swing.border.Border border22 = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1), border11);
        painelgeral.setBorder(border22);
        this.add(painelgeral);
        
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
