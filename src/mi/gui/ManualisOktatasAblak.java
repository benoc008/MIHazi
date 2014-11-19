package mi.gui;

import mi.logic.InputProcessor;
import mi.domain.KerdesValasz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManualisOktatasAblak extends JFrame implements Runnable, ActionListener, KeyListener {

    private  String ALAP_FILE = "kerdesek.txt";

    private JMenuBar menuBar;
    private JMenu nezetMenu;
    private JMenuItem feltoltesNezetMenuPont;
    private JMenuItem szerkesztesNezetMenuPont;

    private JMenu fileMenu;
    private JMenuItem betoltesMenuPont;
    private JMenuItem mentesMenuPont;
    private JMenuItem mentesMaskentMenuPont;

    private JFileChooser file;

    private OktatasFeltoltesNezet feltoltesNezet;
    private OktatasSzerkesztesNezet szerkesztesNezet;

    private InputProcessor inputProcessor;

    List<KerdesValasz> kerdesekValaszok = new ArrayList<>();

    public ManualisOktatasAblak(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
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

        feltoltesNezet = new OktatasFeltoltesNezet(kerdesekValaszok);
        szerkesztesNezet = new OktatasSzerkesztesNezet(kerdesekValaszok);

        kerdesekValaszokBetoltes(new File(ALAP_FILE));

        //add(feltoltesNezet, BorderLayout.CENTER);
        add(szerkesztesNezet, BorderLayout.CENTER);
    }

    @Override
    public void run() {
        createView();
    }

    private void createView() {
        JFrame frame = new ManualisOktatasAblak(inputProcessor);
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
        } else if (e.getSource() == mentesMenuPont) {
            mentesFajlba();
        } else if (e.getSource() == betoltesMenuPont) {
            betoltesFajlbol();
        }
        else if (e.getSource()==mentesMaskentMenuPont){
            mentesMaskentFajlba();
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

    public void mentesMaskentFajlba() {
        kerdesekValaszok = szerkesztesNezet.getKerdesekValaszok();

        file = new JFileChooser();
        int returnVal = file.showDialog(ManualisOktatasAblak.this, "Mentés");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            kerdesValaszokMentes(file.getSelectedFile());
         }
    }

    public void mentesFajlba(){
        kerdesekValaszok = szerkesztesNezet.getKerdesekValaszok();
        File file=new File(ALAP_FILE);
        kerdesValaszokMentes(file);
        inputProcessor.kerdeseketBeolvas();
    }

    private void kerdesValaszokMentes(File file){
        try(
            FileWriter fis = new FileWriter(file)) {
            ALAP_FILE=file.getCanonicalPath();
            for (KerdesValasz kv : kerdesekValaszok) {
                fis.write("K:" + " " + kv.getKerdes() + "\n");
                for (int j = 0; j < kv.getValaszok().size(); j++) {
                    fis.write(kv.getValaszKiir(j) + "\n");
                }
            }
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, "A fajl megnyitasa nem sikerul.");
        }
    }

    public void betoltesFajlbol() {

        file = new JFileChooser();
        int returnVal = file.showDialog(ManualisOktatasAblak.this, "Betöltés");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            kerdesekValaszokBetoltes(file.getSelectedFile());
        }
    }

    private void kerdesekValaszokBetoltes(File file) {
        kerdesekValaszok = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            ALAP_FILE=file.getCanonicalPath();
            String line = br.readLine();
            while (line != null) {
                KerdesValasz kv = new KerdesValasz();
                if (line.matches("K:.*")) {
                    kv.setKerdes(line.substring(3));
                    line = br.readLine();
                    while (line != null && line.matches("V:.*")){
                        kv.addValasz(line.substring(3));
                        line = br.readLine();
                    }
                }
                kerdesekValaszok.add(kv);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        szerkesztesNezet.tableModel.setSorokFromKerdesValaszLista(kerdesekValaszok);
    }
}
