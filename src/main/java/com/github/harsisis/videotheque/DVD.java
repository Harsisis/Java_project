package com.github.harsisis.videotheque;

public class DVD extends Produit {

    private String realisateur;

    public DVD(String titre, double tarifJournalier, String realisateur) {
        super(titre, tarifJournalier, CategorieProduit.SUPPORT_NUMERIQUE);
        this.realisateur = realisateur;
    }

    public String getRealisateur() {
        return realisateur;
    }
}