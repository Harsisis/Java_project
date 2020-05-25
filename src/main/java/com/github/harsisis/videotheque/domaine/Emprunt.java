package com.github.harsisis.videotheque.domaine;

import java.time.LocalDate;
import java.util.UUID;

public class Emprunt {

    private UUID empruntId;
    private Produit produit;
    private int dureeLocation;

    public Emprunt(Produit produit, int dureeLocation) {
        this.empruntId = UUID.randomUUID();
        this.produit = produit;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getDureeLocation() {
        return dureeLocation;
    }

    public LocalDate getFinLocation(LocalDate debutDate) {
        return debutDate.plusDays(this.dureeLocation);
    }
}
