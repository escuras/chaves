/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;

/**
 *
 * @author toze
 */
public class Nova extends javax.swing.JFrame{
    
    public Nova(){
        super();
    }
    
    public void create(){
        this.setMinimumSize(new java.awt.Dimension(400,300));
        this.setPreferredSize(new java.awt.Dimension(400,300));
        javax.swing.JPanel pn = new javax.swing.JPanel();
        pn.setPreferredSize(new java.awt.Dimension(400,300));
        pn.setMinimumSize(new java.awt.Dimension(400,300));
        javax.swing.JLabel label = new javax.swing.JLabel();
        label.setPreferredSize(new java.awt.Dimension(90,90));
        label.setBackground(Color.red);
        label.setOpaque(true);
        MouseHandler mh = new MouseHandler();
        label.addMouseMotionListener(mh);
        pn.add(label);
        this.add(pn);
        this.pack();
        this.setVisible(true);
        
    }
    public static final void main( String[] args){
      
    }
}

 class MouseHandler extends MouseAdapter {

        private boolean active = false;

        @Override
        public void mousePressed(MouseEvent e) {

            JLabel label = (JLabel) e.getComponent();
            Point point = e.getPoint();

            active = getIconCell(label).contains(point);
            if (active) {
                label.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            } else {
                label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            active = false;
            JLabel label = (JLabel) e.getComponent();
            label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (active) {
                JLabel label = (JLabel) e.getComponent();
                Point point = e.getPoint();

                int verticalAlign = label.getVerticalAlignment();
                int horizontalAlign = label.getHorizontalAlignment();

                if (isWithInColumn(label, point, 0)) {
                    horizontalAlign = JLabel.LEFT;
                } else if (isWithInColumn(label, point, 1)) {
                    horizontalAlign = JLabel.CENTER;
                } else if (isWithInColumn(label, point, 2)) {
                    horizontalAlign = JLabel.RIGHT;
                }

                if (isWithInRow(label, point, 0)) {
                    verticalAlign = JLabel.TOP;
                } else if (isWithInRow(label, point, 1)) {
                    verticalAlign = JLabel.CENTER;
                } else if (isWithInRow(label, point, 2)) {
                    verticalAlign = JLabel.BOTTOM;
                }

                label.setVerticalAlignment(verticalAlign);
                label.setHorizontalAlignment(horizontalAlign);

                label.invalidate();
                label.repaint();

            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        protected boolean isWithInColumn(JLabel label, Point p, int gridx) {
            int cellWidth = label.getWidth() / 3;
            int cellHeight = label.getHeight();

            Rectangle bounds = new Rectangle(gridx * cellWidth, 0, cellWidth, cellHeight);

            return bounds.contains(p);
        }

        protected boolean isWithInRow(JLabel label, Point p, int gridY) {
            int cellWidth = label.getWidth();
            int cellHeight = label.getHeight() / 3;

            Rectangle bounds = new Rectangle(0, cellHeight * gridY, cellWidth, cellHeight);

            return bounds.contains(p);
        }

        private Rectangle getIconCell(JLabel label) {

            Rectangle bounds = new Rectangle();

            int cellWidth = label.getWidth() / 3;
            int cellHeight = label.getHeight() / 3;

            bounds.width = cellWidth;
            bounds.height = cellHeight;

            if (label.getHorizontalAlignment() == JLabel.LEFT) {
                bounds.x = 0;
            } else if (label.getHorizontalAlignment() == JLabel.CENTER) {
                bounds.x = cellWidth;
            } else if (label.getHorizontalAlignment() == JLabel.RIGHT) {
                bounds.x = cellWidth * 2;
            } else {
                bounds.x = 0;
                bounds.width = 0;
            }
            //if (label.getHorizontalAlignment() == JLabel.TOP) {
            //    bounds.y = 0;
            //} else if (label.getHorizontalAlignment() == JLabel.CENTER) {
            //    bounds.y = cellHeight;
            //} else if (label.getHorizontalAlignment() == JLabel.BOTTOM) {
            //    bounds.y = cellHeight * 2;
            //} else {
            //    bounds.y = 0;
            //    bounds.height = 0;
            //}
            if (label.getVerticalAlignment() == JLabel.TOP) {
                bounds.y = 0;
            } else if (label.getVerticalAlignment() == JLabel.CENTER) {
                bounds.y = cellHeight;
            } else if (label.getVerticalAlignment() == JLabel.BOTTOM) {
                bounds.y = cellHeight * 2;
            } else {
                bounds.y = 0;
                bounds.height = 0;
            }

            return bounds;

        }

    }
