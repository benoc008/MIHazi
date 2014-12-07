package mi.domain;

import java.io.Serializable;

public class Tudas implements Serializable{
    String kerdoszo;
    Szo kerdezettSzo;
    Mondat valasz;


    public Tudas() {

    }

    public Tudas(String kerdoszo, Szo kerdezettSzo, Mondat valasz) {
        this.kerdoszo = kerdoszo;
        this.kerdezettSzo = kerdezettSzo;
        this.valasz = valasz;
    }

    public String getKerdoszo() {
        return kerdoszo;
    }

    public Szo getKerdezettSzo() {
        return kerdezettSzo;
    }

    public Mondat getValasz() {
        return valasz;
    }

    public void setKerdoszo(String kerdoszo) {
        this.kerdoszo = kerdoszo;
    }

    public void setKerdezettSzo(Szo kerdezettSzo) {
        this.kerdezettSzo = kerdezettSzo;
    }

    public void setValasz(Mondat valasz) {
        this.valasz = valasz;
    }
}
