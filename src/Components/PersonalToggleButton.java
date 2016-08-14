/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author toze
 */
public class PersonalToggleButton extends javax.swing.JToggleButton {

    private static final long serialVersionUID = 1L;
    
    public PersonalToggleButton() {
        super();
        super.setContentAreaFilled(false);
        super.setOpaque(false);
    }

    public PersonalToggleButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        super.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isEnabled()) {
            if (getModel().isPressed()) {
                this.drawRectangle(g, 25);
                g.setColor(new Color(1, 1, 1, 75));
                g.fillRect(this.getWidth() / 2 - 14, 3, 26, this.getHeight() - 4);
                g.setColor(new Color(1, 1, 1, 125));
                g.drawRect(this.getWidth() / 2 - 14, 3, 26, this.getHeight() - 4);
            } else if (getModel().isRollover()) {
                this.drawRectangle(g, 125);
                g.setColor(new Color(1, 1, 1, 25));

                g.fillRect(getWidth() / 2 - 14, 3, 26, getHeight() - 4);
                g.setColor(new Color(1, 1, 1, 45));
                g.drawRect(getWidth() / 2 - 14, 3, 26, getHeight() - 4);
            } else {
                this.drawRectangle(g, 254);
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
