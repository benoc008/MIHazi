package mi.gui;

import mi.domain.Sor;
import mi.domain.KerdesValasz;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OktatasSzerkesztesTableModel extends AbstractTableModel implements TableModel {

    private List<Sor> sorok;
    String[] oszlopNevek = {"Típius", "Sor", "Törlés", "Hozzáadás"};

    public OktatasSzerkesztesTableModel(List<KerdesValasz> lista) {
        setSorokFromKerdesValaszLista(lista);
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
                                sorok.remove(rowIndex);
                                while(rowIndex < sorok.size() && sorok.get(rowIndex).tipus.equals("Válasz")){
                                    sorok.remove(rowIndex);
                                    i++;
                                }
                            }
                            fireTableDataChanged();
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
                            sorok.add(rowIndex + 1, new Sor("Válasz", ""));
                            fireTableDataChanged();
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

    public void addSor(String tipus, String sor) {
        sorok.add(new Sor(tipus, sor));
    }

    public List<KerdesValasz> getKerdesValaszok(){
        return KerdesValasz.createListFromSorok(sorok);
    }

    public void setSorokFromKerdesValaszLista(List<KerdesValasz> lista) {
        sorok = new ArrayList<>();
        for (KerdesValasz kv : lista) {
            sorok.add(new Sor("Kérdés", kv.getKerdes()));
            for (String s : kv.getValaszok()) {
                sorok.add(new Sor("Válasz", s));
            }
        }
        fireTableDataChanged();
    }
}
