package com.github.harsisis.videotheque.domaine;

public class DVD extends Produit {

    private String realisateur;

    public DVD(String titre, double tarifJournalier, String realisateur) {
        super(titre, "DVD", tarifJournalier, CategorieProduit.SUPPORT_NUMERIQUE);
        this.realisateur = realisateur;
    }

    public String getRealisateur() {
        return realisateur;
    }
}