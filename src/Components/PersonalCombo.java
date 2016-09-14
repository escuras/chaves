/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author toze
 */
public class PersonalCombo {

    public static final java.awt.Color FOREGROUND_SELECT_COLOR = new java.awt.Color(0, 0, 0);
    public static final java.awt.Color BACKGROUND_SELECT_COLOR = java.awt.Color.DARK_GRAY;
    public static final java.awt.Color BACKGROUND_COLOR = new java.awt.Color(213, 213, 213);
    public static final int LEFT = javax.swing.JLabel.LEFT;
    public static final int RIGHT = javax.swing.JLabel.RIGHT;
    public static final int CENTER = javax.swing.JLabel.CENTER;
    public static final String HELP_TEXT = "Escolha uma das opções ...";
    private javax.swing.JComponent perdefocus;
    private java.awt.Color selectForegroundColor;
    private java.awt.Color selectBackgroundColor;
    private java.awt.Color backgroundColor;
    private java.awt.Dimension dimensao;
    private String textodeajuda;
    private int posicaotexto;
    private javax.swing.JComboBox<Object> combo;

   
    public PersonalCombo(javax.swing.JComponent perdefocus) {
        combo = new javax.swing.JComboBox<>();
        this.selectForegroundColor = FOREGROUND_SELECT_COLOR;
        this.selectBackgroundColor = BACKGROUND_SELECT_COLOR;
        this.backgroundColor = BACKGROUND_COLOR;
        textodeajuda = HELP_TEXT;
        combo.setMaximumRowCount(20);
        dimensao = new Dimension(170, 28);
        this.perdefocus = perdefocus;
    }

    public void addcopyPaste(Langs.Locale lingua) {
        javax.swing.JTextField fil = (javax.swing.JTextField) combo.getEditor().getEditorComponent();
        fil.addMouseListener(Components.PopUpMenu.simpleCopyPaste(lingua, fil, false));
    }

    public void create() {
        combo.setEditable(true);
        combo.setEnabled(true);
        combo.setAutoscrolls(true);
        combo.addItem("");
        javax.swing.JTextField fil = (javax.swing.JTextField) combo.getEditor().getEditorComponent();
        if (combo.getSelectedIndex() == 0) {
            fil.setForeground(new Color(205, 205, 205));
        } else {
            fil.setForeground(selectForegroundColor);
        }
        combo.setForeground(selectForegroundColor);
        fil.setSelectionColor(this.selectBackgroundColor);
        combo.setPreferredSize(dimensao);
        BasicComboPopup popcar = (BasicComboPopup) combo.getAccessibleContext().getAccessibleChild(0);
        popcar.getList().setSelectionBackground(this.selectBackgroundColor);
        popcar.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        combo.setFocusable(true);
        combo.setBackground(this.getBackgroundColor());
        combo.setSelectedIndex(0);
        combo.getEditor().getEditorComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) {
                    if (combo.getSelectedIndex() == 0) {
                        javax.swing.JTextField fil = (javax.swing.JTextField) combo.getEditor().getEditorComponent();
                        fil.setText("");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) {
                    if (combo.getSelectedIndex() == 0) {
                        javax.swing.JTextField fil = (javax.swing.JTextField) combo.getEditor().getEditorComponent();
                        fil.setCaretColor(java.awt.Color.BLACK);
                        fil.setForeground(java.awt.Color.BLACK);
                        combo.setSelectedIndex(-1);
                    }
                }
            }
        });
        Object child = combo.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup = (BasicComboPopup) child;
        javax.swing.JList<?> list = popup.getList();
        list.setCellRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel label = new javax.swing.JLabel();
                label.setOpaque(true);
                label.setBackground(new java.awt.Color(255, 255, 255));
                label.setPreferredSize(new Dimension((int) dimensao.getWidth(), 20));
                label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                label.setHorizontalAlignment(posicaotexto);
                if (index != 0) {
                    label.setText(value.toString());
                } else {
                    label.setForeground(new java.awt.Color(205, 205, 205));
                    label.setText("");
                }

                if (isSelected) {
                    label.setBackground(java.awt.Color.DARK_GRAY);
                    label.setForeground(java.awt.Color.WHITE);
                }
                return label;
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (combo.getSelectedIndex() == 0) {
                    combo.getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
                    fil.setText(textodeajuda);
                    if (perdefocus != null) {
                        perdefocus.requestFocus();
                    }
                } else {
                    combo.getEditor().getEditorComponent().setForeground(selectForegroundColor);
                }
            }
        });
        combo.getEditor().getEditorComponent().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (combo.getSelectedIndex() < 1) {
                    fil.setForeground(new java.awt.Color(1, 1, 1));
                    fil.setCaretColor(new Color(1, 1, 1));
                    fil.setText("");
                    combo.setSelectedIndex(-1);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (combo.getSelectedIndex() < 1) {
                    fil.setForeground(new java.awt.Color(205, 205, 205));
                    combo.setSelectedIndex(0);
                    fil.setText(textodeajuda);
                }
            }
        });

        combo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            int aux;
            boolean bauxiliar = false;
            int conta = 0;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (perdefocus != null) {
                        perdefocus.requestFocus();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (combo.getSelectedIndex() == 0) {
                        fil.setCaretColor(new Color(1, 1, 1));
                        fil.setCaretPosition(0);
                        fil.setText("");
                    }
                } else if ((combo.getSelectedIndex() == 0) && (bauxiliar)) {
                    fil.setCaretPosition(0);
                    fil.setForeground(selectForegroundColor);
                    fil.setCaretColor(new Color(1, 1, 1));
                    setSelectedIndex(-1);
                    fil.setText("");
                    bauxiliar = false;
                } else if (!combo.isPopupVisible()) {
                    combo.setPopupVisible(true);
                } else if ((combo.getSelectedIndex() == 1) && (e.getKeyCode() == KeyEvent.VK_UP)) {
                    combo.getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
                    fil.setCaretColor(new Color(255, 255, 255));

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int val = e.getKeyCode();
                if ((val == KeyEvent.VK_DOWN) || (val == KeyEvent.VK_UP)) {
                    if (combo.getSelectedIndex() == 0) {
                        combo.getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
                        fil.setText(textodeajuda);
                        fil.setCaretColor(new Color(255, 255, 255));
                        bauxiliar = true;
                    } else {
                        combo.getEditor().getEditorComponent().setForeground(new java.awt.Color(1, 1, 1));
                        fil.setCaretColor(selectForegroundColor);
                    }
                    if ((val == KeyEvent.VK_DOWN) && (combo.getSelectedIndex() == -1)) {
                        if (combo.getModel().getSize() > 1) {
                            combo.setSelectedIndex(1);
                        }
                    } else if (((e.getKeyCode() == KeyEvent.VK_UP) && (combo.getSelectedIndex() == -1))) {
                        if (combo.getModel().getSize() > 1) {
                            combo.setSelectedIndex(1);
                        }
                    }
                } else if ((combo.getSelectedIndex() == 0) && (val == KeyEvent.VK_ENTER)) {
                    if (perdefocus != null ) {
                        perdefocus.requestFocusInWindow();
                    }
                } else if ((combo.isPopupVisible()) && (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (fil.getText().equals("")) && (combo.getSelectedIndex() > 0)) {
                    combo.getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
                    fil.setCaretColor(new Color(255, 255, 255));
                    bauxiliar = true;
                    combo.setSelectedIndex(0);
                    fil.setText(textodeajuda);
                } else if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (combo.getSelectedIndex() == 0)) {
                    fil.setCaretColor(new Color(1, 1, 1));
                }
                if ((e.getKeyCode() != KeyEvent.VK_LEFT) && (e.getKeyCode() != KeyEvent.VK_RIGHT)) {
                    if (combo.getSelectedIndex() != 0) {
                        String va = fil.getText();
                        aux = combo.getSelectedIndex();
                        DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) combo.getModel();
                        for (int i = 1; i <= modelo.getSize(); i++) {
                            if (modelo.getElementAt(i) != null) {
                                String mov = va;
                                String mov2 = modelo.getElementAt(i).toString();
                                mov = mov.replaceAll("[áàãäâ]", "a");
                                mov = mov.replaceAll("[íìîĩï]", "i");
                                mov = mov.replaceAll("[éèêẽë]", "e");
                                mov = mov.replaceAll("[úùũüû]", "u");
                                mov = mov.replaceAll("[óòôõö]", "o");
                                mov = mov.replaceAll("[ñ]", "n");
                                mov2 = mov2.replaceAll("[áàãäâ]", "a");
                                mov2 = mov2.replaceAll("[íìîĩï]", "i");
                                mov2 = mov2.replaceAll("[éèêẽë]", "e");
                                mov2 = mov2.replaceAll("[úùũüû]", "u");
                                mov2 = mov2.replaceAll("[óòôõö]", "o");
                                mov2 = mov2.replaceAll("[ñ]", "n");
                                if ((mov2.toLowerCase().matches("(.*)" + mov.toLowerCase().replaceAll("[\\[\\]\\(\\)\\/\\{\\}\"\\#$%&=\\\\]+", "") + "(.*)")) && (!mov.equals(""))) {
                                    aux = i;
                                    break;
                                }
                            }
                        }
                        combo.setSelectedIndex(aux);
                        if (val == KeyEvent.VK_ENTER) {
                            if (combo.getSelectedIndex() > 0) {
                                va = combo.getSelectedItem().toString();
                            }
                        }
                        if (fil.getCaretPosition() == fil.getText().length()) {
                            fil.setText(va);
                        }
                    }
                }
            }
        }
        );
    }

    /**
     * @return the selectForegroundColor
     */
    public java.awt.Color getSelectForegroundColor() {
        return selectForegroundColor;
    }

    public void setSelectedIndex(int val) {
        combo.setSelectedIndex(val);
        if (val == 0) {
            combo.getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
            ((javax.swing.JTextField) combo.getEditor().getEditorComponent()).setCaretColor(new Color(255, 255, 255));
            ((javax.swing.JTextField) combo.getEditor().getEditorComponent()).setText(textodeajuda);
        } else if (val > 0) {
            combo.getEditor().getEditorComponent().setForeground(this.selectForegroundColor);
            ((javax.swing.JTextField) combo.getEditor().getEditorComponent()).setCaretColor(this.selectForegroundColor);
        }
    }
    
    public void setEnabled(boolean cond){
        if (cond) {
            combo.setEnabled(true);
            this.setSelectedIndex(0);
        } else {
            combo.setEnabled(false);
            this.setSelectedIndex(-1);
        }
    }

    public int getItemCount() {
        return (combo.getItemCount() - 1);
    }

    public void setSelectedItem(Object val) {
        int situacao = -1;
        if (val != null) {
            for (int i = 0; i < combo.getModel().getSize(); i++) {
                Object element = combo.getModel().getElementAt(i);
                if (val.equals(element)) {
                    situacao = i;
                    break;
                }
            }
        }
        if (situacao == 0) {
            combo.getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
            ((javax.swing.JTextField) combo.getEditor().getEditorComponent()).setCaretColor(new Color(255, 255, 255));
            ((javax.swing.JTextField) combo.getEditor().getEditorComponent()).setText(textodeajuda);
        } else if (situacao > 0) {
            combo.getEditor().getEditorComponent().setForeground(this.selectForegroundColor);
            ((javax.swing.JTextField) combo.getEditor().getEditorComponent()).setCaretColor(this.selectForegroundColor);
        }
        combo.setSelectedItem(val);
    }

    public void removeAllItems() {
        combo.removeAllItems();
        combo.addItem("");
        combo.setSelectedIndex(0);
        combo.getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
        ((javax.swing.JTextField) combo.getEditor().getEditorComponent()).setCaretColor(new Color(255, 255, 255));
        ((javax.swing.JTextField) combo.getEditor().getEditorComponent()).setText(textodeajuda);
    }

    /**
     * @param selectForegroundColor the selectForegroundColor to set
     */
    public void setSelectForegroundColor(java.awt.Color selectForegroundColor) {
        this.selectForegroundColor = selectForegroundColor;
    }

    /**
     * @return the selectBackgroundColor
     */
    public java.awt.Color getSelectBackgroundColor() {
        return selectBackgroundColor;
    }

    /**
     * @param selectBackgroundColor the selectBackgroundColor to set
     */
    public void setSelectBackgroundColor(java.awt.Color selectBackgroundColor) {
        this.selectBackgroundColor = selectBackgroundColor;
    }

    /**
     * @return the textodeajuda
     */
    public String getHelpText() {
        return textodeajuda;
    }

    /**
     * @param textodeajuda the textodeajuda to set
     */
    public void setHelpText(String textodeajuda) {
        this.textodeajuda = textodeajuda;
    }

    /**
     * @return the backgroundColor
     */
    public java.awt.Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(java.awt.Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @return the posicaotexto
     */
    public int getHorizontalTextPosition() {
        return posicaotexto;
    }

    /**
     * @param posicaotexto the posicaotexto to set
     */
    public void setHorizontalTextPosition(int posicaotexto) {
        this.posicaotexto = posicaotexto;
        javax.swing.JTextField fil = (javax.swing.JTextField) combo.getEditor().getEditorComponent();
        fil.setHorizontalAlignment(posicaotexto);
        ((javax.swing.JLabel) combo.getRenderer()).setHorizontalAlignment(posicaotexto);
    }

    public javax.swing.JComboBox<Object> getComboBox() {
        return this.combo;
    }

    public void setPreferredSize(Dimension dim) {
        this.dimensao = dim;
        combo.setPreferredSize(dim);
    }

    public void setMinimumSize(Dimension dim) {
        combo.setMinimumSize(dim);
    }

    public void setMaximumSize(Dimension dim) {
        combo.setMaximumSize(dim);
    }

    public void addItem(Object i) {
        combo.addItem(i);
    }

    public void removeItem(Object i) {
        combo.removeItem(i);
    }

    public void removeItemAt(int i) {
        combo.removeItemAt(i);
    }

    public void addActionListener(ActionListener l) {
        combo.addActionListener(l);
    }
    
    public int getSelectedIndex(){
        return combo.getSelectedIndex();
    }
    
    public ComboBoxModel<Object> getModel(){
        return combo.getModel();
    } 
    
    public Object getSelectedItem(){
        return combo.getSelectedItem();
    }
    
    public void setComponentFocus(javax.swing.JComponent compo){
        perdefocus = compo;
    }
    
    public javax.swing.JComponent getComponentFocus(){
        return perdefocus;
    }
    
     public static void setHorizontalTextPosition(int posicaotexto, javax.swing.JComboBox pos) {
        javax.swing.JTextField fil = (javax.swing.JTextField) pos.getEditor().getEditorComponent();
        fil.setHorizontalAlignment(posicaotexto);
        ((javax.swing.JLabel) pos.getRenderer()).setHorizontalAlignment(posicaotexto);
    }

}
