/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 *
 * @author toze
 */
public class PersonalComboBox extends javax.swing.JComboBox {

    public static final java.awt.Color FOREGROUND_SELECT_COLOR = new java.awt.Color(0, 0, 0);
    public static final java.awt.Color BACKGROUND_SELECT_COLOR = java.awt.Color.DARK_GRAY;
    public static final java.awt.Color BACKGROUND_COLOR = new java.awt.Color(213, 213, 213);
    public static final int LEFT = javax.swing.JLabel.LEFT;
    public static final int RIGHT = javax.swing.JLabel.RIGHT;
    public static final int CENTER = javax.swing.JLabel.CENTER;
    public static final String HELP_TEXT = "Escolha uma das opções ...";
    private final javax.swing.JComponent perdefocus;
    private java.awt.Color selectForegroundColor;
    private java.awt.Color selectBackgroundColor;
    private java.awt.Color backgroundColor;
    private java.awt.Dimension dimensao;
    private String textodeajuda;
    private int posicaotexto;

    public PersonalComboBox(javax.swing.JComponent perdefocus) {
        super();
        this.selectForegroundColor = FOREGROUND_SELECT_COLOR;
        this.selectBackgroundColor = BACKGROUND_SELECT_COLOR;
        this.backgroundColor = BACKGROUND_COLOR;
        textodeajuda = HELP_TEXT;
        dimensao = new Dimension(170, 30);
        this.perdefocus = perdefocus;
    }

    public void addcopyPaste(Langs.Locale lingua) {
        javax.swing.JTextField fil = (javax.swing.JTextField) this.getEditor().getEditorComponent();
        fil.addMouseListener(Components.PopUpMenu.simpleCopyPaste(lingua, fil, false));
    }

    public void create() {
        this.setEditable(true);
        this.setEnabled(true);
        this.setAutoscrolls(true);
        
        DefaultComboBoxModel modelo = (DefaultComboBoxModel) this.getModel();
        modelo.addElement("");
        javax.swing.JTextField fil = (javax.swing.JTextField) this.getEditor().getEditorComponent();
        if (this.getSelectedIndex() == 0) {
            fil.setForeground(new Color(205, 205, 205));
        } else {
            fil.setForeground(selectForegroundColor);
        }
        this.setForeground(selectForegroundColor);
        fil.setSelectionColor(this.selectBackgroundColor);
        this.setPreferredSize(dimensao);
        BasicComboPopup popcar = (BasicComboPopup) this.getAccessibleContext().getAccessibleChild(0);
        popcar.getList().setSelectionBackground(this.selectBackgroundColor);
        popcar.getList().setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));
        this.setFocusable(true);
        this.setBackground(this.getBackgroundColor());
        this.setSelectedIndex(0);
        this.getEditor().getEditorComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) {
                    if (getSelectedIndex() == 0) {
                        javax.swing.JTextField fil = (javax.swing.JTextField) getEditor().getEditorComponent();
                        fil.setText("");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) {
                    if (getSelectedIndex() == 0) {
                        javax.swing.JTextField fil = (javax.swing.JTextField) getEditor().getEditorComponent();
                        fil.setCaretColor(java.awt.Color.BLACK);
                        fil.setForeground(java.awt.Color.BLACK);
                        setSelectedIndex(-1);
                    }
                }
            }
        });
        Object child = getAccessibleContext().getAccessibleChild(0);
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
                if (getSelectedIndex() == 0) {
                    getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
                    fil.setText(textodeajuda);
                    perdefocus.requestFocus();
                } else {
                    getEditor().getEditorComponent().setForeground(selectForegroundColor);
                }
            }
        });
        getEditor().getEditorComponent().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getSelectedIndex() < 1) {
                    fil.setForeground(new java.awt.Color(1, 1, 1));
                    fil.setCaretColor(new Color(1, 1, 1));
                    fil.setText("");
                    setSelectedIndex(-1);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getSelectedIndex() < 1) {
                    fil.setForeground(new java.awt.Color(205, 205, 205));
                    setSelectedIndex(0);
                    fil.setText(textodeajuda);
                }
            }
        });

        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            int aux;
            boolean bauxiliar = false;
            int conta = 0;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    perdefocus.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (getSelectedIndex() == 0) {
                        fil.setCaretColor(new Color(1, 1, 1));
                        fil.setCaretPosition(0);
                        fil.setText("");
                    }
                } else if ((getSelectedIndex() == 0) && (bauxiliar) && (e.getKeyCode() != KeyEvent.VK_ENTER)) {
                    fil.setCaretPosition(0);
                    fil.setForeground(selectForegroundColor);
                    fil.setCaretColor(new Color(1, 1, 1));
                    setSelectedIndex(-1);
                    fil.setText("");
                    bauxiliar = false;
                } else if (!isPopupVisible()) {
                    setPopupVisible(true);
                } else if ((getSelectedIndex() == 1) && (e.getKeyCode() == KeyEvent.VK_UP)) {
                    getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
                    fil.setCaretColor(new Color(255, 255, 255));

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int val = e.getKeyCode();
                if ((val == KeyEvent.VK_DOWN) || (val == KeyEvent.VK_UP)) {
                    if (getSelectedIndex() == 0) {
                        getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
                        fil.setCaretColor(new Color(255, 255, 255));
                        bauxiliar = true;
                    } else {
                        getEditor().getEditorComponent().setForeground(new java.awt.Color(1, 1, 1));
                        fil.setCaretColor(selectForegroundColor);
                    }
                    if ((val == KeyEvent.VK_DOWN) && (getSelectedIndex() == -1)) {
                        setSelectedIndex(1);
                    } else if (((e.getKeyCode() == KeyEvent.VK_UP) && (getSelectedIndex() == -1))) {
                        setSelectedIndex(1);
                    } 
                } else if ((getSelectedIndex() == 0) && (val == KeyEvent.VK_ENTER)) {
                    perdefocus.requestFocusInWindow();
                } else if ((isPopupVisible()) && (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (fil.getText().equals("")) && (getSelectedIndex() > 0)) {
                    getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
                    fil.setCaretColor(new Color(255, 255, 255));
                    bauxiliar = true;
                    setSelectedIndex(0);
                    fil.setText(textodeajuda);
                } else if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (getSelectedIndex() == 0)) {
                    fil.setCaretColor(new Color(1, 1, 1));
                }
                if ((e.getKeyCode() != KeyEvent.VK_LEFT) && (e.getKeyCode() != KeyEvent.VK_RIGHT)) {
                    if (getSelectedIndex() != 0) {
                        String va = fil.getText();
                        aux = getSelectedIndex();
                        DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) getModel();
                        for (int i = 1; i <= modelo.getSize(); i++) {
                            if (modelo.getElementAt(i) != null) {
                                if ((modelo.getElementAt(i).toString().toLowerCase().matches("(.*)" + va.toLowerCase().replaceAll("[^a-zA-Z0-9 .,;:-_]+", "") + "(.*)")) && (!va.equals(""))) {
                                    aux = i;
                                    break;
                                }
                            }
                        }
                        setSelectedIndex(aux);
                        if (val == KeyEvent.VK_ENTER) {
                            if (getSelectedIndex() > 0) {
                                va = getSelectedItem().toString();
                            }
                        }
                        fil.setText(va);
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

    @Override
    public void setSelectedIndex(int val) {
        if (val == 0) {
            getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
            ((javax.swing.JTextField) getEditor().getEditorComponent()).setCaretColor(new Color(255, 255, 255));
            ((javax.swing.JTextField) getEditor().getEditorComponent()).setText(textodeajuda);
        } else if (val > 0) {
            getEditor().getEditorComponent().setForeground(this.selectForegroundColor);
            ((javax.swing.JTextField) getEditor().getEditorComponent()).setCaretColor(this.selectForegroundColor);
        }
        super.setSelectedIndex(val);

    }

    @Override
    public void setSelectedItem(Object val) {
        int situacao = -1;
        if ((val != null) && (!this.isEditable)) {
            for (int i = 0; i < dataModel.getSize(); i++) {
                Object element = dataModel.getElementAt(i);
                if (val.equals(element)) {
                    situacao = i;
                    break;
                }
            }
        }
        if (situacao == 0) {
            getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
            ((javax.swing.JTextField) getEditor().getEditorComponent()).setCaretColor(new Color(255, 255, 255));
            ((javax.swing.JTextField) getEditor().getEditorComponent()).setText(textodeajuda);
        } else if (situacao > 0) {
            getEditor().getEditorComponent().setForeground(this.selectForegroundColor);
            ((javax.swing.JTextField) getEditor().getEditorComponent()).setCaretColor(this.selectForegroundColor);
        }
        super.setSelectedItem(val);
    }

    @Override
    public void removeAllItems() {
        super.removeAllItems();
        DefaultComboBoxModel modelo = (DefaultComboBoxModel) this.getModel();
        modelo.addElement("");
        super.setSelectedIndex(0);
        getEditor().getEditorComponent().setForeground(new java.awt.Color(205, 205, 205));
        ((javax.swing.JTextField) getEditor().getEditorComponent()).setCaretColor(new Color(255, 255, 255));
        ((javax.swing.JTextField) getEditor().getEditorComponent()).setText(textodeajuda);
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
        javax.swing.JTextField fil = (javax.swing.JTextField) this.getEditor().getEditorComponent();
        fil.setHorizontalAlignment(posicaotexto);
        ((javax.swing.JLabel) getRenderer()).setHorizontalAlignment(posicaotexto);
    }

}
