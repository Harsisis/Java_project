package com.github.harsisis.videotheque;

public class CD extends Produit {

    private int anneeSortie;

    public CD(String titre, double tarifJournalier, int anneeSortie) {
        super(titre, tarifJournalier,CategorieProduit.SUPPORT_NUMERIQUE);
        this.anneeSortie = anneeSortie;
    }

    public int getAnnee_sortie() {
        return anneeSortie;
    }

}