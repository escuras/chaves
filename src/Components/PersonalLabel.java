/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

/**
 *
 * @author toze
 */
public class PersonalLabel extends javax.swing.JLabel {
    
    private int painelaltura;
    private int altura;
    private int painellargura;
    private int largura;
    private int x; 
    private int y;
    
    public PersonalLabel(){
       super();
    }
    
    public PersonalLabel(javax.swing.JPanel panel){
        super();
        this.painellargura = panel.getWidth();
        this.painelaltura = panel.getHeight();
        x = 0;
        y = 0;
        altura = 100;
        largura = 80;
    }
    
    public void create(){
        this.setBounds(x, y, largura, altura);
    }
    
    public void randomXY(){
        x = new java.util.Random().nextInt(painellargura - largura - 10) + 10;
        System.out.println(x);
        y = new java.util.Random().nextInt((painelaltura/2) - 10) + 10;
        System.out.println(y);
    }
  
 
}
