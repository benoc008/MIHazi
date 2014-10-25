package mi;

import mi.gui.Ablak;
import mi.helperek.FileMuveletHelper;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
//tzdhtf fthf
        FileMuveletHelper.log = new File("log" + new Date().getTime() + ".txt");
        try {
            FileMuveletHelper.log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ablak ablak = new Ablak();
        Thread t = new Thread(ablak);
        t.start();

    }
}
