package com.github.harsisis.videotheque.domaine;

public class Dictionnaire extends Produit {

    private String langue;

    public Dictionnaire(String titre, double tarifJournalier, String langue) {
        super(titre, "Dictionnaire", tarifJournalier, CategorieProduit.DOCUMENT);
        this.langue = langue;
    }

    public String getLangue() {
        return langue;
    }
}
