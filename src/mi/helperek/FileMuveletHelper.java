package mi.helperek;

import javax.swing.*;
import java.io.*;

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
}
