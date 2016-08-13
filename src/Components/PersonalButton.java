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
public class PersonalButton extends javax.swing.JToggleButton {
    
    private Color hoverBackgroundColor;
        private Color pressedBackgroundColor;

        public PersonalButton() {
            super();
            super.setContentAreaFilled(false);
            super.setOpaque(false);
        }

        public PersonalButton(String text) {
            super(text);
            super.setContentAreaFilled(false);
            super.setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(new Color(1,1,1,75));
                //g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
                
            } else if (getModel().isSelected()) {
                g.setColor(new Color(1,1,1,0));
            } else if (!getModel().isEnabled()) {
                g.setColor(new Color(254,254,254,0));    
                    
            } else {
                //g.setColor(new Color(1,1,1,0));
            }
            //g.fillRect(0, 0, getWidth(), getHeight());
            g.fillRoundRect(8, 2, this.getWidth()-16, this.getHeight()-4, 10, 10);
            g.setColor(new Color(1,254,1));
            g.fillRoundRect(18, this.getHeight()-5, this.getWidth()-37, this.getHeight()-22, 2, 2);
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {
        }

        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }

        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }

        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }

        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }
}
