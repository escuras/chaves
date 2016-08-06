/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 *
 * @author toze
 */
public class PopUpMenu extends JPopupMenu {

    JMenuItem[] item;

    public PopUpMenu(String[] titulos, ActionListener[] act) {
        int i = 0;
        item = new JMenuItem[titulos.length];
        System.out.println(item.length);
        while (i < act.length) {
            item[i] = new JMenuItem(titulos[i]);
            item[i].addActionListener(act[i]);
            i++;
        }
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
            if( i == 3) {
                if (!bt1.isEnabled()) item[i].setEnabled(false);
                else item[i].setEnabled(true);
            } 
            if( i == 4) {
                if (!bt2.isEnabled()) item[i].setEnabled(false);
                else item[i].setEnabled(true);
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
}
