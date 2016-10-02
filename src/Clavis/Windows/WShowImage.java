/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clavis.Windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author toze
 */
public class WShowImage extends javax.swing.JDialog{
    
    private BufferedImage imagem;
    private Dimension dimensao;
    
    public WShowImage(BufferedImage imagem, int largura, int altura){
        this.imagem = imagem;
        this.dimensao = new Dimension(largura,altura);
    }
    
    public void create(){
        this.setPreferredSize(dimensao);
        this.setSize(dimensao);
        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setPreferredSize(dimensao);
        panel.setSize(dimensao);
        javax.swing.JLabel label = new javax.swing.JLabel();
        label.setPreferredSize(new Dimension((int)dimensao.getWidth()-10,(int)dimensao.getHeight()-42));
        label.setSize(new Dimension((int)dimensao.getWidth()-10,(int)dimensao.getHeight()-42));
        ImageIcon im = new ImageIcon(imagem);
        label.setIcon(im);
        panel.add(label);
        this.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
    }
    
    public void appear(){
        Dimension m = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)(m.getWidth()/ 2 - this.getWidth() /2);
        int y = (int)(m.getHeight()/ 2 - this.getHeight() /2);
        this.setLocation(x, y);
        this.setVisible(true);
    }
    
    
    
}
