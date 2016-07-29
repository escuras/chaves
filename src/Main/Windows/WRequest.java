/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Windows;

import java.awt.Dimension;

/**
 *
 * @author toze
 */
public class WRequest extends javax.swing.JDialog {
    
    public static final long serialVersionUID = 1L;
    private javax.swing.JDialog dialogopai;
    
    public WRequest(){
        super();
    }
    
    public WRequest(javax.swing.JDialog dialogo){
        super(dialogo);
        this.dialogopai = dialogo;
    }
    
    public void create(){
        this.setPreferredSize(new Dimension(700,500));
        this.setResizable(false);
        this.setMinimumSize(new Dimension(700,500));
        this.setMaximumSize(new Dimension(700,500));
    }
    
    public void appear(){
        this.setVisible(true);
        this.setLocationRelativeTo(dialogopai);
    }
    
}
