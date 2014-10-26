package mi.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class OktatasSzerkesztesTableRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(((String)value).equals("Kérdés")){
            renderer.setBackground(Color.PINK);
        } else if (((String)value).equals("Válasz")){
            renderer.setBackground(Color.GREEN);
        }
        return renderer;
    }
}
