package com.github.harsisis.videotheque;

public class CD extends Produit {

    private int anneeSortie;

    public CD(String titre, double tarifJournalier, int anneeSortie) {
        super(titre, tarifJournalier,"Support numérique");
        this.anneeSortie = anneeSortie;
    }

    public int getAnnee_sortie() {
        return anneeSortie;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAnnée sortie : " + getAnnee_sortie();
    }

}