/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

/**
 *
 * @author toze
 */
public class Color extends java.awt.Color {

    private javax.swing.JPanel panelbandeira;
    private java.awt.Color velha;

    public Color(int r, int g, int b) {
        super(r, g, b);
      
        panelbandeira = null;
    }

    public Color(int r, int g, int b, int a) {
        super(r, g, b, a);
       
        panelbandeira = null;
    }

    public Color(Color cor) {
        super(cor.getRed(), cor.getGreen(), cor.getBlue(), cor.getAlpha());
 
        panelbandeira = null;
    }

 
    

}
