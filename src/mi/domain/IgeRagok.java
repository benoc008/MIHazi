package mi.domain;

import mi.domain.enumok.Ido;
import mi.domain.enumok.IgeragozasiRendszer;
import mi.domain.enumok.Mod;
import mi.domain.enumok.SzamSzemely;

public class IgeRagok {
    private SzamSzemely szamSzemely;
    private Mod mod;
    private Ido ido;
    private IgeragozasiRendszer igeragozasiRendszer;

    public IgeRagok(SzamSzemely szamSzemely, Mod mod, Ido ido, IgeragozasiRendszer igeragozasiRendszer) {
        this.szamSzemely = szamSzemely;
        this.mod = mod;
        this.ido = ido;
        this.igeragozasiRendszer = igeragozasiRendszer;
    }

    public SzamSzemely getSzamSzemely() {
        return szamSzemely;
    }

    public void setSzamSzemely(SzamSzemely szamSzemely) {
        this.szamSzemely = szamSzemely;
    }

    public Mod getMod() {
        return mod;
    }

    public void setMod(Mod mod) {
        this.mod = mod;
    }

    public Ido getIdo() {
        return ido;
    }

    public void setIdo(Ido ido) {
        this.ido = ido;
    }

    public IgeragozasiRendszer getIgeragozasiRendszer() {
        return igeragozasiRendszer;
    }

    public void setIgeragozasiRendszer(IgeragozasiRendszer igeragozasiRendszer) {
        this.igeragozasiRendszer = igeragozasiRendszer;
    }
}
