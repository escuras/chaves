/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JToolBar;

/**
 *
 * @author toze
 */
public class ToolBar extends JToolBar{
    
    private static final  long serialVersionUID = 1L; 


    @Override
    public void paintAll(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, this.getWidth(),this.getHeight());
        super.paintAll(g); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paintComponent(Graphics g) {
        
        g.setColor(Color.red);
        g.fillRect(0, 0, this.getWidth(),this.getHeight());
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }

    
}
