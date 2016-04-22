/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author toze
 */
public class HeaderRenderer extends DefaultTableCellRenderer {

    /**
     * Constructs a <code>DefaultTableHeaderCellRenderer</code>.
     * <P>
     * The horizontal alignment and text position are set as appropriate to a
     * table header cell, and the opaque property is set to false.
     */
    public HeaderRenderer() {
        
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(LEFT);
        setVerticalAlignment(BOTTOM);
        setOpaque(false);
        JTableHeader tableHeader = table.getTableHeader();
        if (tableHeader != null) {
            setForeground(tableHeader.getForeground());
        }
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setText(value.toString());
        setBounds(0, 0, 250, 55);
        setPreferredSize(new Dimension(250, 35));
        if (column == 0) {
            setHorizontalAlignment(javax.swing.JLabel.CENTER);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));
        }
        else setHorizontalAlignment(javax.swing.JLabel.CENTER);
        this.setVerticalAlignment(javax.swing.JLabel.CENTER);
        setBackground(table.getBackground().darker());
        setOpaque(true);
        //setBorder(BorderFactory.createLineBorder(Color.black, 0));
        return this;
    }

    
}
