
package com.mycompany.empresa;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private final Color backgroundColor;
    private final Color foregroundColor;

    public CustomTableCellRenderer(Color backgroundColor, Color foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (value != null && value.toString().startsWith("Se aplicaron filtros de búsqueda")) {
            cell.setBackground(backgroundColor);
            cell.setForeground(foregroundColor);
        } else {
            cell.setBackground(Color.WHITE);
            cell.setForeground(Color.BLACK);
        }
        
        return cell;
    }
}

