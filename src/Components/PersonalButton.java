/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;

/**
 *
 * @author toze
 */
public class PersonalButton extends javax.swing.JButton {
    
    private static final long serialVersionUID = 1L;
    
    public PersonalButton() {
        super();
        super.setContentAreaFilled(false);
        super.setOpaque(false);
        super.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 2));
    }

    public PersonalButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        super.setOpaque(false);
        super.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isEnabled()) {
            if (getModel().isPressed()) {
                g.setColor(new Color(1, 1, 1, 75));
                g.fillRect(this.getWidth() / 2 - 14, 3, 26, this.getHeight() - 4);
                g.setColor(new Color(1, 1, 1, 125));
                g.drawRect(this.getWidth() / 2 - 14, 3, 26, this.getHeight() - 4);
            } else if (getModel().isRollover()) {
                g.setColor(new Color(1, 1, 1, 25));
                g.fillRect(getWidth() / 2 - 14, 3, 26, getHeight() - 4);
                g.setColor(new Color(1, 1, 1, 45));
                g.drawRect(getWidth() / 2 - 14, 3, 26, getHeight() - 4);
            } 
        }
        super.paintComponent(g);
    }

    private void drawRectangle(Graphics g, int alpha) {
        if (this.getModel().isSelected()) {
            g.setColor(new Color(1, 254, 1, alpha));
            g.fillRect(this.getWidth() / 2 - 8, this.getHeight() - 4, 15, 2);
            g.setColor(new Color(1, 1, 1, alpha));
            g.drawRect(this.getWidth() / 2 - 9, this.getHeight() - 5, 16, 3);
        } else {
            g.setColor(new Color(145, 145, 145, alpha));
            g.fillRect(this.getWidth() / 2 - 8, this.getHeight() - 4, 15, 2);
            g.setColor(new Color(1, 1, 1, alpha));
            g.drawRect(this.getWidth() / 2 - 9, this.getHeight() - 5, 16, 3);
        }
    }
}
