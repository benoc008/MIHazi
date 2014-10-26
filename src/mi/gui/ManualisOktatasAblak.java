package mi.gui;

import mi.logic.KerdesValasz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.awt.Window;
import java.io.*;
import mi.logic.KerdesValasz;

import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

public class ManualisOktatasAblak extends JFrame implements Runnable, ActionListener, KeyListener {

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

    List<KerdesValasz> kerdesekValaszok = new ArrayList<>();

    public ManualisOktatasAblak(){
        List<String> valaszok1 = new ArrayList<>();
        valaszok1.add("semmi");
        valaszok1.add("semmu kul");
        valaszok1.add("lofasz habbal");
        kerdesekValaszok.add(new KerdesValasz("mizu?", valaszok1));

        List<String> valaszok2 = new ArrayList<>();
        valaszok2.add("igen");
        valaszok2.add("nem");
        kerdesekValaszok.add(new KerdesValasz("szoktal te szopni?", valaszok2));

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
        }else if (e.getSource()== mentesMenuPont){
            try {
                mentesfileba();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e.getSource()==betoltesMenuPont){
            try {
                betoltesfilebol();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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

    public  void mentesfileba()throws IOException{
        file =new JFileChooser();
        int returnVal = file.showDialog(ManualisOktatasAblak.this, "Mentés");
        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file2 = file.getSelectedFile();
            File file4= file.getCurrentDirectory();
            File file3 = new File(file4.getCanonicalPath(),file2.getName());
            FileWriter fis = new FileWriter(file3);
            KerdesValasz kv = new KerdesValasz();
            for(int i=0 ;i<kerdesekValaszok.size();i++) {
                kv = kerdesekValaszok.get(i);
                fis.write("K:" + " " + kv.getKerdes()+"\n");
                for (int j=0;j<kerdesekValaszok.get(i).getValaszok().size();j++)
                    fis.write(kv.getValaszKiir(j)+"\n");
            }
            fis.close();
        } else {
        }
        ;
     }

    public void betoltesfilebol() throws  IOException{
        file = new JFileChooser();
        int returnVal = file.showDialog(ManualisOktatasAblak.this, "Betöltés");
        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file2 = file.getSelectedFile();
            File file3 = file.getCurrentDirectory();
            File file4=new File(file3.getCanonicalPath(),file2.getName());
            FileReader isr = new FileReader(file4);
            BufferedReader br = new BufferedReader(isr);
            int i=0;
            int j=1;
            try {
                while (true) {
                    String line = br.readLine();
                    KerdesValasz kv = new KerdesValasz();
                    if(line.matches("K:.*")) {
                        i=i+1;
                        if(i==2){
                            kerdesekValaszok.add(new KerdesValasz(kv.getKerdes(), kv.getValaszok()));
                            i=1;
                        }
                        kv.setKerdes(line.substring(3));
                    }
                    else if (line.matches("V:.*")) {
                            kv.setValasz(line.substring(3));

                    }

                }

            } catch(IOException e) {
                System.out.println(e);
            }
            br.close();
        }
    }
}
