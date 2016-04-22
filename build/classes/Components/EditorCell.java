/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author toze
 */
public class EditorCell implements TableCellEditor {

    JComponent component = new JTextField();

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            ((JTextField) component).selectAll();
            ((JTextField) component).setText((String) value);
            ((JTextField) component).setHorizontalAlignment(SwingConstants.CENTER);
            ((JTextField) component).setBackground(table.getBackground());
            ((JTextField) component).setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        }
        return component;
    }

    @Override
    public Object getCellEditorValue() {
        return ((JTextField) component).getText();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
    }

}