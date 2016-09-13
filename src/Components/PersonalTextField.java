/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
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
    public static final Color DEFAULT_PLACEHOLDER_COLOR = new Color(205, 205, 205);
    private Color cor;
    private String placeholder;
    private boolean condicao;
    private javax.swing.JComponent componentedesaida;
    private String textocondicional;
    private java.awt.event.FocusListener focus;
    private java.awt.event.KeyAdapter keyevent;
    Color corauxiliar;

    public PersonalTextField() {
        super();
        cor = DEFAULT_PLACEHOLDER_COLOR;
        condicao = false;
    }

    public PersonalTextField(String texto) {
        super();
        cor = DEFAULT_PLACEHOLDER_COLOR;
        condicao = false;
    }

    public void addPlaceHolder(String texto, javax.swing.JComponent bt) {
        corauxiliar = getForeground();
        setForeground(cor);
        this.componentedesaida = bt;
        setPlaceHolderText(texto);
        super.setText(texto);

        focus = new FocusListener() {
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
                    showPLaceHolder();
                } else {
                    condicao = true;
                }
            }
        };
        this.addFocusListener(focus);
        keyevent = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (componentedesaida != null) {
                        componentedesaida.requestFocus();
                    }
                }
            }
        };
        this.addKeyListener(keyevent);
    }
    
    
    @Override
    public void setText(String text){
        setForeground(corauxiliar);
        super.setText(text);
    }

    public void stopPlaceHolder() {
        if (focus != null) {
            this.removeFocusListener(focus);
        }
        if (keyevent != null) {
            this.removeKeyListener(keyevent);
        }
        condicao = true;
    }

    public void startPlaceHolder() {
        if (focus != null) {
            this.addFocusListener(focus);
        }
        if (keyevent != null) {
            this.addKeyListener(keyevent);
        }
    }

    public Color getPlaceHolderColor() {
        return cor;
    }

    public void setPlaceHolderColor(Color cor) {
        this.cor = cor;
    }

    public String getPlaceHolderText() {
        return placeholder;
    }

    public void setPlaceHolderText(String texto) {
        this.placeholder = texto;
    }

    public void setLostCenterComponent(javax.swing.JComponent componente) {
        this.componentedesaida = componente;
    }

    public javax.swing.JComponent getLostCenterComponent() {
        return this.componentedesaida;
    }
    
   
    @Override
    public String getText() {
        textocondicional = super.getText();
        if (condicao) {
            return super.getText();
        } else {
            return "";
        }
    }

    public void showPLaceHolder() {
        setForeground(cor);
        super.setText(placeholder);
        condicao = false;
    }

    public boolean isPlaceHolderSet() {
        return !condicao;
    }

}
