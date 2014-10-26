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
    private JMenuItem feltoltesNezetMenuPont;
    private JMenuItem szerkesztesNezetMenuPont;

    private JMenu fileMenu;
    private JMenuItem betoltesMenuPont;
    private JMenuItem mentesMenuPont;
    private JMenuItem mentesMaskentMenuPont;


    private OktatasFeltoltesNezet feltoltesNezet;
    private OktatasSzerkesztesNezet szerkesztesNezet;

    public ManualisOktatasAblak(){
        setLayout(new BorderLayout());

        //TODO file, sugo, akarmmi...

        menuBar = new JMenuBar();

        fileMenu = new JMenu("Fájl");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        betoltesMenuPont = new JMenuItem("Betöltés", KeyEvent.VK_O);
        betoltesMenuPont.addActionListener(this);
        fileMenu.add(betoltesMenuPont);
        mentesMenuPont = new JMenuItem("Mentés", KeyEvent.VK_S);
        mentesMenuPont.addActionListener(this);
        fileMenu.add(mentesMenuPont);
        mentesMaskentMenuPont = new JMenuItem("Mentés másként", KeyEvent.VK_A);
        mentesMaskentMenuPont.addActionListener(this);
        fileMenu.add(mentesMaskentMenuPont);
        menuBar.add(fileMenu);

        nezetMenu = new JMenu("Nézet");
        nezetMenu.setMnemonic(KeyEvent.VK_N);
        feltoltesNezetMenuPont = new JMenuItem("Feltöltés nézet", KeyEvent.VK_F);
        feltoltesNezetMenuPont.addActionListener(this);
        nezetMenu.add(feltoltesNezetMenuPont);
        szerkesztesNezetMenuPont = new JMenuItem("Szerkesztés nézet", KeyEvent.VK_E);
        szerkesztesNezetMenuPont.addActionListener(this);
        nezetMenu.add(szerkesztesNezetMenuPont);
        menuBar.add(nezetMenu);

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
        if (e.getSource() == feltoltesNezetMenuPont) {
            add(feltoltesNezet, BorderLayout.CENTER);
        } else if (e.getSource() == szerkesztesNezetMenuPont) {
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
