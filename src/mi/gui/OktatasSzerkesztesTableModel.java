package mi.gui;

import mi.logic.KerdesValasz;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class OktatasSzerkesztesTableModel implements TableModel {

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
    String[] oszlopNevek = {"Típius", "Sor", "Törlés"};

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
                return String.class;
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return sorok.get(rowIndex).tipus;
        } else if (columnIndex == 1) {
            return sorok.get(rowIndex).sor;
        }
        return "Torles";
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
