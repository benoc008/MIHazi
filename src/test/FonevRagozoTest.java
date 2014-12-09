package test;

import mi.domain.Szo;
import mi.domain.enumok.FonevRag;
import mi.logic.FonevRagozo;
import mi.logic.InputProcessor;
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

    @Test
    public void targyragTest(){
        Szo szo = new Szo();
        szo.addSzofaj("főnév");
        szo.setSzo("bőr");
        String eredmeny = FonevRagozo.ragoz(szo, FonevRag.TARGY);
        assert eredmeny.equals("bőrt");

        szo.setSzo("könyv");
        eredmeny = FonevRagozo.ragoz(szo, FonevRag.TOBBES_SZAM, FonevRag.TARGY);
        assert eredmeny.equals("könyveket");
    }

    @Test
    public void valaszTest(){
        InputProcessor inputProcessor = new InputProcessor();
        String valasz = inputProcessor.add("csak rúgom a bőrt");

        assert valasz.endsWith("?");
    }

    @Test
    public void kerdesTest(){
        InputProcessor inputProcessor = new InputProcessor();
        String valasz = inputProcessor.add("én is szeretem a könyveket.");

        assert valasz.endsWith("?");
    }
}
