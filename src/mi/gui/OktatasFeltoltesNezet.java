package mi.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OktatasFeltoltesNezet extends JPanel implements ActionListener, KeyListener{

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField bemenet;
    private JButton kuldesGomb;
    private JPanel bemenetiSor;

    public OktatasFeltoltesNezet(){

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
