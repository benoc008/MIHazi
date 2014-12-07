package mi.gui;

import mi.domain.Tudas;
import mi.logic.InputProcessor;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.List;


public class Ablak extends JFrame implements Runnable, ActionListener, KeyListener {

    private JTextArea chatAblak;
    private JScrollPane scrollPane;
    private JPanel bemenetiSor;
    private JTextField bemenetiMezo;
    private JButton kuldesGomb;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem mentesMenupont;
    private JMenuItem betoltesMenupont;
    private JMenuItem ujrakezdesMenupont;
    private JMenu eszkozokMenu;
    private JMenuItem manualisOktatasMenupont;


    private InputProcessor inputProcessor;

    public Ablak(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor; //feldolgozza a bemenetet

        setLayout(new BorderLayout());      // felosztja az ablakot felsö oldaso job ball középső sávra

        chatAblak = new JTextArea();
        chatAblak.setEditable(false);       // ne legyen szerkeszthetö a középsö ablak
        scrollPane = new JScrollPane(chatAblak);

        bemenetiMezo = new JTextField();    // szövegg ide
        bemenetiMezo.addKeyListener(this);
        kuldesGomb = new JButton("Kuldes");
        kuldesGomb.addActionListener(this);     //eseméyn figyelő feldolgozza a beérkezö eseményeket

        bemenetiSor = new JPanel();
        bemenetiSor.setLayout(new BorderLayout());

        bemenetiSor.add(bemenetiMezo, BorderLayout.CENTER);
        bemenetiSor.add(kuldesGomb, BorderLayout.LINE_END);

        add(scrollPane, BorderLayout.CENTER);
        add(bemenetiSor, BorderLayout.PAGE_END);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        mentesMenupont = new JMenuItem("Mentés", KeyEvent.VK_S);
        mentesMenupont.addActionListener(this);
        fileMenu.add(mentesMenupont);
        betoltesMenupont = new JMenuItem("Betöltés", KeyEvent.VK_S);
        betoltesMenupont.addActionListener(this);
        fileMenu.add(betoltesMenupont);
        ujrakezdesMenupont = new JMenuItem("Új beszélgetés", KeyEvent.VK_N);
        ujrakezdesMenupont.addActionListener(this);
        fileMenu.add(ujrakezdesMenupont);
        menuBar.add(fileMenu);

        eszkozokMenu = new JMenu("Eszkozok");
        eszkozokMenu.setMnemonic(KeyEvent.VK_E);
        manualisOktatasMenupont = new JMenuItem("Manuális oktatás", KeyEvent.VK_O);
        manualisOktatasMenupont.addActionListener(this);
        eszkozokMenu.add(manualisOktatasMenupont);
        menuBar.add(eszkozokMenu);

        add(menuBar, BorderLayout.PAGE_START);
    }

    private void createView() {
        // Create and set up the window.
        JFrame frame = new Ablak(inputProcessor);
        frame.setSize(640, 480);
        frame.setTitle("ChatBot");
        // Display the window.
        //frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    // x kor bezárja
    }

    public void run() {
        createView();
    }

    public void kuldes() {
        String valasz = inputProcessor.add(bemenetiMezo.getText() + "\n");
        chatAblak.append(bemenetiMezo.getText() + "\n");
        chatAblak.append(valasz + "\n");
        bemenetiMezo.setText("");
    }

    public void manualisOktatasAblakMegnyitas(){
        ManualisOktatasAblak manualisOktatasAblak = new ManualisOktatasAblak(inputProcessor);
        Thread t = new Thread(manualisOktatasAblak);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == kuldesGomb) {
            kuldes();
        } else if (e.getSource() == manualisOktatasMenupont) {
            manualisOktatasAblakMegnyitas();
        } else if(e.getSource() == mentesMenupont){
            tudastarMentese();
        } else if(e.getSource() == betoltesMenupont){
            tudastarBeolvasas();
        }
    }

    private void tudastarMentese() {
        JFileChooser file = new JFileChooser();
        int returnVal = file.showDialog(Ablak.this, "Mentés");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.getSelectedFile()))) {
                oos.writeObject(inputProcessor.getTudastar());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "A fajl megnyitasa nem sikerul.");
            }
        }
    }

    private void tudastarBeolvasas(){
        JFileChooser file = new JFileChooser();
        int returnVal = file.showDialog(Ablak.this, "Betöltés");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getSelectedFile()))) {
                inputProcessor.addTudastar((List<Tudas>)ois.readObject());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "A fajl megnyitasa nem sikerul.");
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
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


