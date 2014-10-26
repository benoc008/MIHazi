package mi.gui;

import mi.logic.KerdesValasz;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OktatasSzerkesztesTableModel extends AbstractTableModel implements TableModel {

    private class Elem {
        String tipus;
        String sor;

        Elem(String tipus, String sor) {
            this.tipus = tipus;
            this.sor = sor;
        }
    }

    private List<KerdesValasz> lista;
    private List<Elem> sorok;
    String[] oszlopNevek = {"Típius", "Sor", "Törlés", "Hozzáadás"};

    public OktatasSzerkesztesTableModel(List<KerdesValasz> lista) {
        this.lista = lista;
        sorok = new ArrayList<>();
        for (KerdesValasz kv : lista) {
            sorok.add(new Elem("Kérdés", kv.getKerdes()));
            for (String s : kv.getValaszok()) {
                sorok.add(new Elem("Válasz", s));
            }
        }
    }

    @Override
    public int getRowCount() {
        return sorok.size();
    }

    @Override
    public int getColumnCount() {
        return oszlopNevek.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return oszlopNevek[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
                return String.class;
            case 2:
                return JButton.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return sorok.get(rowIndex).tipus;
            case 1: return sorok.get(rowIndex).sor;
            case 2:
                    final JButton torlesGomb = new JButton(oszlopNevek[columnIndex]);
                    torlesGomb.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            int i = rowIndex;
                            if(sorok.get(rowIndex).tipus.equals("Válasz")){
                                sorok.remove(rowIndex);
                            } else {
                                while(sorok.get(rowIndex).tipus.equals("Válasz")){
                                    sorok.remove(rowIndex);
                                    i++;
                                }
                            }
                            fireTableRowsDeleted(rowIndex, i);
                        }
                    });
                    return torlesGomb;
            case 3:
                    if(sorok.get(rowIndex).tipus.equals("Válasz")) {
                        JButton button = new JButton();
                        return button;
                    }
                    final JButton hozzaadasGomb = new JButton(oszlopNevek[columnIndex]);
                    hozzaadasGomb.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(hozzaadasGomb), "Button clicked for row " + rowIndex);
                        }
                    });
                    return hozzaadasGomb;
            default: return null;
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        sorok.get(rowIndex).sor = (String) aValue;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
