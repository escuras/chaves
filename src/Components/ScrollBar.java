/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author toze
 */
public class ScrollBar extends BasicScrollBarUI {
 
    
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        c.setBackground(Color.orange);
        c.setSize(trackBounds.width, trackBounds.height+15);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
      
    }
}
