package mi.gui;

import mi.domain.KerdesValasz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class OktatasFeltoltesNezet extends JPanel implements ActionListener, KeyListener{

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField bemenet;
    private JButton kuldesGomb;
    private JPanel bemenetiSor;

    private List<KerdesValasz> kerdesekValaszok;

    public OktatasFeltoltesNezet(List<KerdesValasz> kerdesekValaszok){

        this.kerdesekValaszok = kerdesekValaszok;

        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);

        bemenet = new JTextField();
        bemenet.addKeyListener(this);

        kuldesGomb = new JButton("Küldés");
        kuldesGomb.addActionListener(this);

        bemenetiSor = new JPanel();
        bemenetiSor.setLayout(new BorderLayout());
        bemenetiSor.add(bemenet, BorderLayout.CENTER);
        bemenetiSor.add(kuldesGomb, BorderLayout.LINE_END);

        add(textArea, BorderLayout.CENTER);
        add(bemenetiSor, BorderLayout.PAGE_END);
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
