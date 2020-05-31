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
    }

    public String getEmpruntId() {
        return empruntId.toString();
    }

    public String getProduitId() {
        return produitId;
    }

//    public String getEmpruntStr(){ //voir erreur dans windowOrder -> "AWT-EventQueue-0" java.lang.NullPointerException
//        return produitId.getTitre() +
//                " | " +
//                String.valueOf(dureeLocation);
//    }

    public int getDureeLocation() {
        return dureeLocation;
    }

    public LocalDate getFinLocation(LocalDate debutDate) {
        return debutDate.plusDays(this.dureeLocation);
    }
}
