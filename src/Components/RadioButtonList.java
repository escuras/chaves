/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.ListCellRenderer;

/**
 *
 * @author toze
 */
public class RadioButtonList extends JRadioButton implements ListCellRenderer {
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setBorder(BorderFactory.createEmptyBorder(0,10, 0, 10));
        setComponentOrientation(list.getComponentOrientation());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setSelected(isSelected);
        setEnabled(list.isEnabled());
        setText(value == null ? "" : value.toString());  
        return this;
    }
}
