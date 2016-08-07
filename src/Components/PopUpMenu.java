/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import com.sun.glass.events.KeyEvent;
import java.awt.Event;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

/**
 *
 * @author toze
 */
public class PopUpMenu extends JPopupMenu {

    JMenuItem[] item;

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
    
    public PopUpMenu(String[] titulos, int inicio,int fim, Langs.Locale lang) {
        int i = 0;
        item = new JMenuItem[fim-inicio];
        if ((titulos.length < fim)||(fim < inicio)) {
            fim = titulos.length;
        }
        String [] auxiliar;
        while (inicio < fim) {
            auxiliar = titulos[inicio].split(";;;");
            item[i] = new JMenuItem("<html> <b>"+lang.translate(auxiliar[0])+": </b>"+ lang.translate("Desde as").toLowerCase()+" "+auxiliar[1]+" "+lang.translate("até às")+" "+auxiliar[2]+".</html>");
            inicio++;
            i++;
        }
        setFocusable(false);
    }

    public PopUpMenu(String[] titulos, ActionListener[] act, javax.swing.JButton bt1, javax.swing.JButton bt2) {
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
            item[i].addActionListener(act[i]);
            i++;
        }
    }

    public void create() {
        int i = 0;
        System.out.println(item.length);
        while (i < item.length) {
            this.add(item[i]);
            i++;
        }
    }
}
