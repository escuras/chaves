/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JMenuBar;

/**
 *
 * @author toze
 */
public class Menu extends JMenuBar {
    private static final long serialVersionUID =1111;
    Color bgcolor;
    
    public Menu(){
        this.bgcolor = Color.WHITE;
        
    }

    public void setColor(Color color) {
        this.bgcolor=color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint paint = new GradientPaint(0,0,bgcolor,this.getWidth()/2,0,Color.BLACK,false);
        g2d.setColor(bgcolor);
        g2d.setPaint(paint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
    }
    
}
