package mi.logic;

import mi.domain.Mondat;
import mi.domain.Tudas;
import mi.domain.enumok.MondatFajta;

import java.util.List;

import static mi.domain.enumok.MondatFajta.KERDO;
import static mi.helperek.FileMuveletHelper.logol;

public class InputProcessor {

    private Elemzo elemzo;
    private String valasz;

    public InputProcessor() {
        elemzo = new Elemzo();
    }

    public String add(String s) {
        s = s.replaceAll("\\n", "");
        elemzo.addHistory(s);
        logol("--------------------");
        logol("Input: " + s);
        feldolgoz(s);
        elemzo.addHistory(valasz);
        logol("Valasz: " + valasz);
        return valasz;
    }

    private void feldolgoz(String s) {
        valasz = "";
        List<Mondat> mondatok = elemzo.mondatokraBont(s);
        for (Mondat mondat : mondatok) {
            String valaszMondatra = elemzo.eloreDefinialtAdatokAlapjanEllenoriz(mondat);
            if (!valaszMondatra.equals("")) {
                valasz += valaszMondatra;
                continue;
            }
            elemzo.ertelmez(mondat);
            tanul(mondat);
            valaszol(mondat);
        }

        if (valasz.equals("")) {
            valasz = s;
        }
    }

    private void tanul(Mondat mondat) {
        Tudas tudas = new Tudas();
        if (elemzo.getHistory().size() < 2) {
            return;
        }
        String elozoMondatok = elemzo.getHistory().get(elemzo.getHistory().size() - 2);
        for (Mondat elozoMondat : elemzo.mondatokraBont(elozoMondatok)) {
            if (elozoMondat.getFajta().equals(KERDO)) {
                elemzo.ertelmez(elozoMondat);
                //TODO lehet a Mondatban kellene tarolni a kerdoszot.
                String kerdoszo = ValaszGeneralo.kerdoszotKeres(elozoMondat.getMondat());
                if (!kerdoszo.equals("")) {
                    tudas.setKerdoszo(kerdoszo);
                    for (String s : KerdesGeneralo.ALLITMANYRA_KERDEZ) {
                        if (s.startsWith(kerdoszo)) {
                            if (elozoMondat.getAllitmany() != null) {
                                tudas.setKerdezettSzo(elozoMondat.getAllitmany());
                            } else {
                                throw new RuntimeException("hianyzo allitmany");
                            }
                        }
                    }
                    for (String s : KerdesGeneralo.TARGYRA_KERDEZ) {
                        if (s.startsWith(kerdoszo)) {
                            if (elozoMondat.getTargy() != null) {
                                tudas.setKerdezettSzo(elozoMondat.getTargy());
                            } else {
                                throw new RuntimeException("hianyzo targy");
                            }
                        }
                    }
                }
            }
            if (tudas.getKerdoszo() != null) {
                tudas.setValasz(mondat);
                elemzo.addTudas(tudas);
                logol(">>> tudastarba a kovetkezo kerdeshez: " + tudas.getKerdoszo() + ", " + tudas.getKerdezettSzo() +
                        ", valasz felveve:" + tudas.getValasz().getMondat());
            }
        }
    }


    private void valaszol(Mondat mondat) {

        if (mondat.getFajta().equals(MondatFajta.KIJELENTO)) {
            try {
                valasz = new KerdesGeneralo().general(mondat);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mondat.getFajta().equals(KERDO)) {
            valasz = new ValaszGeneralo(elemzo.getTudastar()).general(mondat);

        }
    }

    public Elemzo getElemzo() {
        return elemzo;
    }
}
