package mi.gui;

import mi.logic.InputProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Ablak extends JFrame implements Runnable, ActionListener, KeyListener {

    private JTextArea chatAblak;
    private JPanel bemenetiSor;
    private JTextField bemenetiMezo;
    private JButton kuldesGomb;

    private InputProcessor inputProcessor;

    public Ablak() {
        inputProcessor = new InputProcessor(); //feldolgozza a bemenetet

        setLayout(new BorderLayout());      // felosztja az ablakot felsö oldaso job ball középső sávra

        chatAblak = new JTextArea();
        chatAblak.setEditable(false);       // ne legyen szerkeszthetö a középsö ablak

        bemenetiMezo = new JTextField();    // szövegg ide
        bemenetiMezo.addKeyListener(this);
        kuldesGomb = new JButton("Kuldes");
        kuldesGomb.addActionListener(this);     //eseméyn figyelő feldolgozza a beérkezö eseményeket

        bemenetiSor = new JPanel();
        bemenetiSor.setLayout(new BorderLayout());

        bemenetiSor.add(bemenetiMezo, BorderLayout.CENTER);
        bemenetiSor.add(kuldesGomb, BorderLayout.LINE_END);

        add(chatAblak, BorderLayout.CENTER);
        add(bemenetiSor, BorderLayout.PAGE_END);
    }

    private void createMenu() {
        // Create and set up the window.
        JFrame frame = new Ablak();
        frame.setSize(640, 480);
        frame.setTitle("ChatBot");
        // Display the window.
        //frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    // x kor bezárja
    }

    public void run() {
        createMenu();
    }

    public void kuldes() {
        inputProcessor.add(bemenetiMezo.getText() + "\n");
        chatAblak.append(bemenetiMezo.getText() + "\n");
        bemenetiMezo.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == kuldesGomb) {
            kuldes();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ENTER){
            kuldes();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
