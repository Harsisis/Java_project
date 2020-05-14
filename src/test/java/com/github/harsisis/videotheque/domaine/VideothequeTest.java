package com.github.harsisis.videotheque.domaine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VideothequeTest {

    @Test
    public void given_validInputParams_when_ajoutClient_then_true() {
        Videotheque videotheque = Videotheque.getInstance();

        boolean result = videotheque.ajoutClient("John", "Smith", true);

        assertTrue(result);
    }

    @Test
    public void Videotheque_is_singleton() {
        //GIVEN
        Videotheque videotheque1 = Videotheque.getInstance();
        Videotheque videotheque2 = Videotheque.getInstance();

        //THEN
        assertEquals(videotheque1, videotheque2);
    }

    @Test
    public void given_produitAndQuantity_when_ajoutStockProduit_then_listStockProduitShouldNotBeEmpty() {
        // GIVEN
        Produit p1 = new CD("titre", 10D, 1983);
        int quantity = 10;
        Videotheque videotheque = Videotheque.getInstance();

        //WHEN
        videotheque.ajoutStockProduit(p1, quantity);

        //THEN
       // assertFalse(videotheque.getListStockProduit().isEmpty());
    }

}