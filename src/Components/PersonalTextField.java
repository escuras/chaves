/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author toze
 */
public class PersonalTextField extends JTextField {

    private static final long serialVersionUID = 1L;

    private Color cor;
    private String placeholder;
    private boolean condicao;
    private javax.swing.JComponent componentedesaida;
    private String textocondicional;

    public PersonalTextField() {
        super();
        cor = new Color(145, 145, 145);
        condicao = false;
    }

    public PersonalTextField(String texto) {
        super(texto);
        cor = new Color(145, 145, 145);
        condicao = false;
    }

    public void addPlaceHolder(String texto, javax.swing.JComponent bt) {
        Color corauxiliar = getForeground();
        setForeground(cor);
        this.componentedesaida = bt;
        setText(texto);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getText();
                if (textocondicional.equals(texto)) {
                    setForeground(corauxiliar);
                    setText("");
                }
                condicao = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                getText();
                if (textocondicional.equals("")) {
                    setForeground(cor);
                    setText(texto);
                    condicao = false;
                }   else {
                    condicao = true;
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (componentedesaida != null) componentedesaida.requestFocus();
                }
            }
        });
    }

    public Color getPlaceHolderColor() {
        return cor;
    }

    public void setPlaceHolderColor(Color cor) {
        this.cor = cor;
    }
    
    public String getPlaceHolderText(){
        return placeholder;
    }
    
    public void setPlaceHolderText(String texto){
        this.placeholder = texto;
    }
    
    public void setLostCenterComponent(javax.swing.JComponent componente){
        this.componentedesaida = componente;
    }
    
    public javax.swing.JComponent getLostCenterComponent(){
        return this.componentedesaida;
    }
   
    @Override
    public String getText(){
        textocondicional = super.getText();
        if (condicao) return super.getText();
        else return "";
    }
 
}
