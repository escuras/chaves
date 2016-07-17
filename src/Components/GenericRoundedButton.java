/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author toze
 */
public class GenericRoundedButton extends javax.swing.JButton {
    
    private static final long serialVersionUID = 1L;
    
    public GenericRoundedButton(String text) {
        super(text);
    }

    public GenericRoundedButton() {
        super();
    }

    @Override
    public void paint(Graphics g) {
        // Set background same as parent. 
        setBackground(getParent().getBackground());

        // I don't need this -- calls to above methods will 
        // invoke repaint as needed. // 
        super.paint(g);
        // Take advantage of Graphics2D to position string 

        Graphics2D g2d = (Graphics2D) g;
        //g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 45, 45);
        //g2d.setColor(Color.BLACK);
        //g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 90, 90);
          g2d.setColor(Color.WHITE);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 90, 90));
        g2d.setComposite(java.awt.AlphaComposite.SrcAtop);
        FontRenderContext frc = new FontRenderContext(null, false, false);
        Rectangle2D r = getFont().getStringBounds(getText(), frc);
        float xMargin = (float) (getWidth() - r.getWidth()) / 2;
        float yMargin = (float) (getHeight() - getFont().getSize()) / 2; // Draw the text in the center 
        g2d.drawString(getText(), xMargin, (float) getFont().getSize() + yMargin);
    }
}
