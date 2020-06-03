package com.github.harsisis.videotheque.domaine;

import java.time.LocalDate;
import java.util.UUID;

public class Emprunt {

    private UUID empruntId;
    private String produitId;
    private int dureeLocation;

    public Emprunt(String produitId, int dureeLocation) {
        this.empruntId = UUID.randomUUID();
        this.produitId = produitId;
        this.dureeLocation = dureeLocation;
    }

    public String getEmpruntId() {
        return empruntId.toString();
    }

    public String getProduitId() {
        return produitId;
    }

    public int getDureeLocation() {
        return dureeLocation;
    }

    public LocalDate getFinLocation(LocalDate debutDate) {
        return debutDate.plusDays(this.dureeLocation);
    }
}
