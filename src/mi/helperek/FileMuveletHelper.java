package mi.helperek;

import mi.domain.KerdesValasz;
import mi.domain.Szo;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileMuveletHelper {

    public static File log;

    public static void logol(String s) {
        System.out.println(s);
//        try {
//            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(log, true)));
//            out.println(s);
//            out.close();
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Hiba a logolasnal!");
//        }
    }

    public static List<Szo> szokincsetOlvasCSVbol(String file) {
        List<Szo> szokincs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                szokincs.add(Szo.createFromCsv(line));
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return szokincs;
    }

    public static List<KerdesValasz> kerdeseketBeolvas(String file) {
        List<KerdesValasz> eloreDefinialtKerdesekValaszok = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String kerdes = null;
            List<String> valaszok = new ArrayList<>();
            while (line != null) {
                if (line.startsWith("K:")) {
                    if (kerdes != null) {
                        eloreDefinialtKerdesekValaszok.add(new KerdesValasz(kerdes, valaszok));
                    }
                    kerdes = line.substring(3);
                    valaszok = new ArrayList<>();
                } else if (line.startsWith("V:")) {
                    valaszok.add(line.substring(3));
                }
                line = br.readLine();
            }
            eloreDefinialtKerdesekValaszok.add(new KerdesValasz(kerdes, valaszok));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eloreDefinialtKerdesekValaszok;
    }

}
