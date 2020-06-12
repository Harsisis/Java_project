package com.github.harsisis.videotheque.domaine;

import org.junit.Test;

import java.util.ArrayList;

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
    public void given_produit_when_getProduit_returns_produit_then_both_produit_equals(){
        //GIVEN
        Videotheque videotheque = Videotheque.getInstance();
        Produit livre = new Livre("Harry Potter and the Chamber of Secrets", 5, "JK. Rowling", CategorieLivre.ROMAN);

        //WHEN
        videotheque.ajoutProduit(livre.getProduitId(),livre);
        Produit produit = videotheque.getProduit(livre.getProduitId());

        //THEN
        assertEquals(livre, produit);
    }

    @Test
    public void given_validInputParams_when_ajoutProduit_then_true() {
        //GIVEN
        Videotheque videotheque = Videotheque.getInstance();
        DVD dvd = new DVD("Lion King", 2, "Jon Favreau");

        //WHEN
        videotheque.ajoutProduit(dvd.getProduitId(), dvd);

        //THEN
        assertTrue(!videotheque.getListProduit().isEmpty());
    }

    @Test
    public void given_validInputParams_when_ajoutCommand_then_true() {
        //GIVEN
        Videotheque videotheque = Videotheque.getInstance();
        Client client = new Client("Homo", "Sapien", false);
        CD cd = new CD("Your Name", 6, 2016);
        Emprunt emprunt = new Emprunt(cd.getProduitId(),1);
        ArrayList<Emprunt> emprunts = new ArrayList<Emprunt>();
        emprunts.add(emprunt);

        //WHEN
        videotheque.ajoutCommande(client,emprunts);

        //THEN
        assertTrue(!videotheque.getListCommande().isEmpty());
    }

    @Test
    public void given_produitAndQuantity_when_ajoutStockProduit_then_listStockProduitShouldNotBeEmpty() {
        //GIVEN
        Produit p1 = new CD("titre", 10D, 1983);
        int quantity = 10;
        Videotheque videotheque = Videotheque.getInstance();

        //WHEN
        videotheque.ajoutStockProduit(p1.getProduitId(), quantity);

        //THEN
        assertFalse(videotheque.getListStockProduit().isEmpty());
    }

    @Test
    public void given_command_when_supprimeCommande_then_command_should_not_exist() {
        //GIVEN
        Videotheque videotheque = Videotheque.getInstance();
        Client client = new Client("Will", "Smith", true);
        Dictionnaire dictionnaire = new Dictionnaire("Wembley", 2, Langue.FR);
        Emprunt emprunt = new Emprunt(dictionnaire.getProduitId(),1);
        ArrayList<Emprunt> emprunts = new ArrayList<Emprunt>();
        Commande commande = videotheque.ajoutCommande(client,emprunts);
        emprunts.add(emprunt);

        //WHEN
        videotheque.supprimeCommande(commande);

        //THEN
        assertFalse(videotheque.getListCommande().contains(commande));
    }
}