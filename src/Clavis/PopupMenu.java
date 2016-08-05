/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPopupMenu;

/**
 *
 * @author toze
 */
public class PopupMenu extends JPopupMenu{
    
    @Override
    public void paintAll(Graphics g)  {
        
        g.setColor(Color.red);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
