package mi.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManualisOktatasAblak extends JFrame implements Runnable, ActionListener, KeyListener {

    private JMenuBar menuBar;
    private JMenu nezetMenu;
    private JMenuItem feltoltesNezetMenuElem;
    private JMenuItem szerkesztesNezetMenuElem;

    private OktatasFeltoltesNezet feltoltesNezet;
    private OktatasSzerkesztesNezet szerkesztesNezet;

    public ManualisOktatasAblak(){
        setLayout(new BorderLayout());

        //TODO file, sugo, akarmmi...

        menuBar = new JMenuBar();
        nezetMenu = new JMenu("Nézet");
        nezetMenu.setMnemonic(KeyEvent.VK_N);
        feltoltesNezetMenuElem = new JMenuItem("Feltöltés nézet", KeyEvent.VK_F);
        feltoltesNezetMenuElem.addActionListener(this);
        nezetMenu.add(feltoltesNezetMenuElem);
        szerkesztesNezetMenuElem = new JMenuItem("Szerkesztés nézet", KeyEvent.VK_E);
        szerkesztesNezetMenuElem.addActionListener(this);
        nezetMenu.add(szerkesztesNezetMenuElem);

        add(menuBar, BorderLayout.PAGE_START);

        feltoltesNezet = new OktatasFeltoltesNezet();
        szerkesztesNezet = new OktatasSzerkesztesNezet();

        add(feltoltesNezet, BorderLayout.CENTER);
    }

    @Override
    public void run() {
        createView();
    }

    private void createView() {
        JFrame frame = new ManualisOktatasAblak();
        frame.setSize(640, 480);
        frame.setTitle("ChatBot - Manuális oktatás");

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    // x kor bezárja
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == feltoltesNezetMenuElem ) {
            add(feltoltesNezet, BorderLayout.CENTER);
        } else if (e.getSource() == szerkesztesNezetMenuElem) {
            add(szerkesztesNezet, BorderLayout.CENTER);
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
}
