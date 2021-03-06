/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;
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

    public static final long serialVersionUID = 1L;
    public static final Color DEFAULT_PLACEHOLDER_COLOR = new Color(205, 205, 205);
    public String DEFAULT_TEXT_PLACEHOLDER = "Introduza o valor ...";
    private Color cor;
    private String placeholder;
    private boolean condicao;
    private javax.swing.JComponent componentedesaida;
    private String textocondicional;
    private java.awt.event.FocusListener focus;
    private java.awt.event.KeyAdapter keyevent;
    Color corauxiliar;
    Color background;

    public PersonalTextField() {
        super();
        cor = DEFAULT_PLACEHOLDER_COLOR;
        condicao = false;
        this.placeholder = DEFAULT_TEXT_PLACEHOLDER;
        background = super.getBackground();
    }

    public PersonalTextField(String texto) {
        super();
        cor = DEFAULT_PLACEHOLDER_COLOR;
        condicao = false;
        this.placeholder = texto;
        background = super.getBackground();
    }

    public void addPlaceHolder(String texto, javax.swing.JComponent bt) {
        corauxiliar = getForeground();
        setForeground(cor);
        this.componentedesaida = bt;
        setPlaceHolderText(texto);
        super.setText(texto);
        
        this.setSelectionColor(new Color(50, 50, 50));
        focus = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getText();
                if ((isEditable())&&(textocondicional.equals(placeholder))) {
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
    public void setText(String text) {
        requestFocus();
        setForeground(corauxiliar);
        super.setText(text);
    }

    public void clearText() {
        super.setText("");
        this.showPLaceHolder();
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
    
    @Override
    public void setEditable(boolean cond){
        if (cond) {
            super.setBackground(background);
        } else {
            super.setBackground(new Color(235,235,235));
        }
        super.setEditable(cond);
    }

    private void putText(String texto) {
        super.setText(texto);
        setCaretPosition(0);
    }

    public void startPlaceHolder() {
        condicao = false;
        focus = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getText();
                if ((isEditable())&&(textocondicional.equals(placeholder))) {
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

    public void showWithCondition(String only, boolean cond) {
        this.stopPlaceHolder();
        setForeground(cor);
        super.setText(only);
        condicao = false;
        focus = new FocusListener() {
            String passa;
            @Override
            public void focusGained(FocusEvent e) {
                getText();
                System.out.println("olol");
                System.out.println(getText());
                System.out.println(textocondicional);
                if (cond) {
                    passa = only;
                } else {
                    passa = placeholder;
                }
                System.out.println(passa);
                if (textocondicional.equals(passa)) {
                    setForeground(corauxiliar);
                    setText("");
                }
                condicao = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                getText();
                System.out.println("perdeu");
                if (textocondicional.equals("")) {
                    setForeground(cor);
                    putText(passa);
                    condicao = false;
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
        componentedesaida.requestFocus();
    }

    public void restartPlaceHolder() {
        this.stopPlaceHolder();
        this.startPlaceHolder();
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

    public void showPLaceHolder(javax.swing.JComponent j) {
        setForeground(cor);
        super.setText(placeholder);
        j.requestFocus();
        condicao = false;
    }

    public boolean isPlaceHolderSet() {
        return !condicao;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 4, 4);
        super.paintComponent(g);
    }

}
