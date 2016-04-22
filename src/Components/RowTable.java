/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author toze
 */
public class RowTable extends JTable {

    private final Map<Integer, Color> rowColor;
    private final Map<Integer, Border> borderColor;

    public RowTable(TableModel model) {
        super(model);
        rowColor = new HashMap<>();
        borderColor = new HashMap<>();
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        Border border = borderColor.get(row);
        JComponent jz = (JComponent) c;
        jz.setBorder(border == null ? BorderFactory.createEmptyBorder(0, 10, 0, 10) : border);
        if (!isRowSelected(row)) {
            Color color = rowColor.get(row);
            c.setBackground(color == null ? getBackground() : color);
        }
        return c;
    }

    public void setRowColor(int row, Color color) {
        rowColor.put(row, color);
    }
    
    public void setBorderColor(int row, Color color) {
        Border g = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        Border f = BorderFactory.createMatteBorder(0, 0, 5, 0, color);
        borderColor.put(row, BorderFactory.createCompoundBorder(f, g));
    }
    
    public void removeBorderColor(int row) {
        Border g = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        borderColor.put(row, g);
    }
    
    public void alternateColorRows(){
        for (int i=0; i< this.getRowCount(); i++) {
            int red = this.getBackground().getRed();
            if ((red +50)<= 255) red += 50; 
            else red = 255;
            int blue = this.getBackground().getBlue();
            if ((blue +50)<= 255) blue += 50;
            else blue = 255;
            int green = this.getBackground().getGreen();
            if ((green +50)<= 255) green += 50;
            else green = 255;
            if (i%2 == 0) rowColor.put(i, new Color(red,green,blue));
            else rowColor.put(i, this.getBackground());
        }
    }
}

