package mi.gui;

import mi.domain.KerdesValasz;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class OktatasSzerkesztesNezet extends JPanel implements ActionListener, KeyListener {

    public OktatasSzerkesztesTableModel tableModel;
    private JTable tabla;
    private JScrollPane scrollPane;
    private JTextField bemenet;
    private JButton kuldesGomb;
    private JPanel bemenetiSor;

    private List<KerdesValasz> kerdesekValaszok;

    public OktatasSzerkesztesNezet(List<KerdesValasz> kerdesekValaszok){
        this.kerdesekValaszok = kerdesekValaszok;

        setLayout(new BorderLayout());

        tableModel = new OktatasSzerkesztesTableModel(kerdesekValaszok);
        tabla = new JTable(tableModel);
        tabla.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                repaint();
            }
        });

        tabla.getColumnModel().getColumn(0).setMaxWidth(60);
        tabla.getColumnModel().getColumn(2).setMaxWidth(100);
        tabla.getColumnModel().getColumn(3).setMaxWidth(100);

        tabla.addMouseListener(new OktatasSzerkesztesButtonAdapter(tabla));
        tabla.setDefaultRenderer(String.class, new OktatasSzerkesztesTableRenderer());
        TableCellRenderer buttonRenderer = new OktatasSzerkesztesButtonRenderer();

        tabla.getColumn("Törlés").setCellRenderer(buttonRenderer);
        tabla.getColumn("Hozzáadás").setCellRenderer(buttonRenderer);

        scrollPane = new JScrollPane(tabla);


        bemenet = new JTextField();
        bemenet.addKeyListener(this);

        kuldesGomb = new JButton("Új kérdés");
        kuldesGomb.addActionListener(this);

        bemenetiSor = new JPanel();
        bemenetiSor.setLayout(new BorderLayout());
        bemenetiSor.add(bemenet, BorderLayout.CENTER);
        bemenetiSor.add(kuldesGomb, BorderLayout.LINE_END);

        add(scrollPane, BorderLayout.CENTER);
        add(bemenetiSor, BorderLayout.PAGE_END);
    }


    private void ujKerdesHozzaadasa() {
        tableModel.addSor("Kérdés", bemenet.getText());
        tableModel.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == kuldesGomb) {
            ujKerdesHozzaadasa();
        }
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

    public List<KerdesValasz> getKerdesekValaszok(){
        kerdesekValaszok = tableModel.getKerdesValaszok();
        return kerdesekValaszok;
    }
}
