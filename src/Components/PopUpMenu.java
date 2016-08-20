/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import com.sun.glass.events.KeyEvent;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

/**
 *
 * @author toze
 */
public class PopUpMenu extends JPopupMenu {

    private static final long serialVersionUID = 1L;

    JMenuItem[] item;

    public PopUpMenu() {
        item = new JMenuItem[0];
    }

    public PopUpMenu(JMenuItem[] item) {
        this.item = item;
    }

    public PopUpMenu(String[] titulos, ActionListener[] act) {
        int i = 0;
        item = new JMenuItem[titulos.length];
        if (titulos.length == act.length) {
            while (i < titulos.length) {
                item[i] = new JMenuItem(titulos[i]);
                item[i].addActionListener(act[i]);
                i++;
            }
        }
    }

    public PopUpMenu(String[] titulos, ActionListener[] act, String colar, String copiar) {
        int i = 0;
        item = new JMenuItem[titulos.length];
        while (i < act.length) {
            item[i] = new JMenuItem(titulos[i]);
            item[i].addActionListener(act[i]);
            if (i == 0) {
                if (colar.equals("")) {
                    item[i].setEnabled(false);
                } else {
                    item[i].setEnabled(true);
                }
                item[i].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK));
            }
            if (i == 1) {
                if (copiar.equals("")) {
                    item[i].setEnabled(false);
                } else {
                    item[i].setEnabled(true);
                }
                item[i].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
            }
            i++;
        }
        setFocusable(false);
    }

    public PopUpMenu(String[] titulos, Langs.Locale lang) {
        int inicio = 1;
        int fim = titulos.length;
        String[] auxiliar;
        item = new JMenuItem[fim - 1];
        while (inicio < fim) {
            auxiliar = titulos[inicio].split(";;;");
            item[inicio - 1] = new JMenuItem("<html> <div style='text-align:center;'><b>" + lang.translate(auxiliar[0]) + ": </b>" + lang.translate("Desde as").toLowerCase() + " " + auxiliar[1] + " " + lang.translate("até às") + " " + auxiliar[2] + ".</div></html>");
            inicio++;
        }
    }

    public PopUpMenu(String[] titulos, ActionListener[] act, javax.swing.JButton bt1, javax.swing.JButton bt2, boolean cond) {
        int i = 0;
        item = new JMenuItem[titulos.length];
        while (i < act.length) {
            item[i] = new JMenuItem(titulos[i], (int) titulos[i].charAt(0));
            if (i == 0) {
                item[i].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
            }
            if (i > 2) {
                item[i].setAccelerator(KeyStroke.getKeyStroke(titulos[i].charAt(0), Event.ALT_MASK));
            }
            if (!cond) {
                if (i == 3) {
                    if (!bt1.isEnabled()) {
                        item[i].setEnabled(false);
                    } else {
                        item[i].setEnabled(true);
                    }
                }
                if (i == 4) {
                    if (!bt2.isEnabled()) {
                        item[i].setEnabled(false);
                    } else {
                        item[i].setEnabled(true);
                    }
                }
            } else {
                bt1.setEnabled(true);
                bt2.setEnabled(true);
            }
            item[i].addActionListener(act[i]);
            i++;
        }
    }

    public void create() {
        int i = 0;
        while (i < item.length) {
            this.add(item[i]);
            i++;
        }
    }

    public static java.awt.event.MouseListener simpleCopyPaste(Langs.Locale lingua, javax.swing.JComponent fil) {
        JMenuItem[] itens = new JMenuItem[2];
        String[] titulos = {lingua.translate("Copiar"), lingua.translate("Colar")};
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();

        itens[1] = new JMenuItem(titulos[0]);
        itens[0] = new JMenuItem(titulos[1]);

        ActionListener list = (ActionEvent ex) -> {
            if (fil instanceof javax.swing.JTextField) {
                StringSelection nome = new StringSelection(((javax.swing.JTextField) fil).getSelectedText());
                c.setContents(nome, nome);
            } else if (fil instanceof javax.swing.JComboBox<?>) {
                javax.swing.JComboBox<?> tor = ((javax.swing.JComboBox) fil);
                javax.swing.JTextField texto = (javax.swing.JTextField) tor.getEditor().getEditorComponent();
                StringSelection nome = new StringSelection(texto.getSelectedText());
                c.setContents(nome, nome);
            }
        };

        ActionListener list2 = (ActionEvent ex) -> {
            Transferable t = c.getContents(null);
            if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    Object ol = t.getTransferData(DataFlavor.stringFlavor);
                    String colarl = (String) t.getTransferData(DataFlavor.stringFlavor);
                    javax.swing.JTextField texto = new javax.swing.JTextField();
                    if (fil instanceof javax.swing.JTextField) {
                        texto = ((javax.swing.JTextField) fil);
                    } else if (fil instanceof javax.swing.JComboBox<?>) {
                        javax.swing.JComboBox<?> tor = ((javax.swing.JComboBox) fil);
                        texto = (javax.swing.JTextField) tor.getEditor().getEditorComponent();
                    }
                    int pos = texto.getCaretPosition();
                    int tamanho = texto.getText().length();
                    if (texto.getSelectedText() == null) {
                        String inicio = texto.getText().substring(0, pos);
                        String fim = texto.getText().substring(pos, tamanho);
                        colarl = inicio + colarl + fim;
                        texto.setText(colarl);
                    } else {
                        pos = texto.getSelectionStart();
                        int posseguinte = texto.getSelectionEnd();
                        String inicio = texto.getText().substring(0, pos);
                        String fim = texto.getText().substring(posseguinte, tamanho);
                        colarl = inicio + colarl + fim;
                        texto.setText(colarl);
                    }
                } catch (UnsupportedFlavorException | IOException eo) {
                }
            }
        };
        itens[1].addActionListener(list);
        itens[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        itens[0].addActionListener(list2);
        itens[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        PopUpMenu menu = new PopUpMenu(itens);
        menu.create();
        java.awt.event.MouseListener mouse = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (fil instanceof javax.swing.JTextField) {
                        if (((javax.swing.JTextField) fil).getSelectedText() == null) {
                            itens[1].setEnabled(false);
                        } else {
                            itens[1].setEnabled(true);
                        }
                    } else if (fil instanceof javax.swing.JComboBox<?>) {
                        javax.swing.JComboBox<?> tor = ((javax.swing.JComboBox) fil);
                        javax.swing.JTextField texto = (javax.swing.JTextField) tor.getEditor().getEditorComponent();
                        if (((javax.swing.JTextField) fil).getSelectedText() == null) {
                            itens[1].setEnabled(false);
                        } else {
                            itens[1].setEnabled(true);
                        }
                    }
                    Transferable t = c.getContents(null);
                    if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        try {
                            Object ol = t.getTransferData(DataFlavor.stringFlavor);
                            String colarl = (String) t.getTransferData(DataFlavor.stringFlavor);
                            System.out.println("colarl: " + colarl);
                            if ((colarl == null) || (colarl.equals(""))) {
                                itens[0].setEnabled(false);
                            } else {
                                itens[0].setEnabled(true);
                            }
                        } catch (UnsupportedFlavorException | IOException eo) {
                        }
                    }
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

        };
        return mouse;
    }

    public JMenuItem[] getitens() {
        return item;
    }
}
