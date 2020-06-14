package com.github.harsisis.videotheque.domaine;

public class CD extends Produit {

    private int anneeSortie;

    public CD(String titre, double tarifJournalier, int anneeSortie) {
        super(titre, "CD", tarifJournalier, CategorieProduit.SUPPORT_NUMERIQUE);
        this.anneeSortie = anneeSortie;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }
}