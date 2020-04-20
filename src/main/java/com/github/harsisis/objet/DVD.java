package com.github.harsisis.objet;

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