package test;

import junit.framework.Assert;
import mi.domain.Szo;
import mi.domain.enumok.FonevRag;
import mi.logic.FonevRagozo;
import org.junit.Test;

public class FonevRagozoTest {

    @Test
    public void tobbesSzamTargyRagTest(){
        Szo szo = new Szo();
        szo.addSzofaj("főnév");
        szo.setSzo("kecske");
        String eredmeny = FonevRagozo.ragoz(szo, FonevRag.TOBBES_SZAM, FonevRag.TARGY);

        assert eredmeny.equals("kecskéket");
    }
}
