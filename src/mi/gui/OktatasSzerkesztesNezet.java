package mi.gui;

import mi.logic.KerdesValasz;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class OktatasSzerkesztesNezet extends JPanel implements ActionListener, KeyListener {

    private JTable tabla;
    private JScrollPane scrollPane;

    private List<KerdesValasz> kerdesekValaszok;

    public OktatasSzerkesztesNezet(List<KerdesValasz> kerdesekValaszok){
        this.kerdesekValaszok = kerdesekValaszok;

        setLayout(new BorderLayout());

        tabla = new JTable(new OktatasSzerkesztesTableModel(kerdesekValaszok));

        tabla.getColumnModel().getColumn(0).setMaxWidth(60);
        tabla.getColumnModel().getColumn(2).setMaxWidth(100);
        tabla.getColumnModel().getColumn(3).setMaxWidth(100);

        tabla.addMouseListener(new OktatasSzerkesztesButtonAdapter(tabla));
        tabla.setDefaultRenderer(String.class, new OktatasSzerkesztesTableRenderer());
        TableCellRenderer buttonRenderer = new OktatasSzerkesztesButtonRenderer();

        tabla.getColumn("Törlés").setCellRenderer(buttonRenderer);
        tabla.getColumn("Hozzáadás").setCellRenderer(buttonRenderer);

        scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
