package mi.gui;

import mi.logic.KerdesValasz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class OktatasSzerkesztesNezet extends JPanel implements ActionListener, KeyListener {

    private JTable tabla;

    private List<KerdesValasz> kerdesekValaszok;

    public OktatasSzerkesztesNezet(List<KerdesValasz> kerdesekValaszok){
        this.kerdesekValaszok = kerdesekValaszok;

        tabla = new JTable(new OktatasSzerkesztesTableModel(kerdesekValaszok));
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
