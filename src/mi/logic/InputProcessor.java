package mi.logic;

import java.util.StringTokenizer;

import static mi.helperek.FileMuveletHelper.logol;

public class InputProcessor {


    public InputProcessor() {

    }

    public void add(String s) {
        logol(s);
        feldolgoz(s);
    }

    private void feldolgoz(String s) {
        StringTokenizer stringTokenizer = new StringTokenizer(s, ".!?", true);
        while (stringTokenizer.hasMoreTokens()) {
            String mondat = stringTokenizer.nextToken();
            String irasjel = "";
            if(stringTokenizer.hasMoreTokens()) {
                irasjel = stringTokenizer.nextToken();
            }
            Mondat m = new Mondat(mondat, irasjel);
            ertelmez(m);
        }
    }

    private void ertelmez(Mondat mondat){

    }
}
