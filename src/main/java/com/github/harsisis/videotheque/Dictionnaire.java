package com.github.harsisis.videotheque;

public class Dictionnaire extends Produit{

    private String langue;

    public Dictionnaire(String titre, double tarifJournalier, String langue) {
        super(titre, tarifJournalier, CategorieProduit.DOCUMENT);
        this.langue = langue;
    }

    public String getLangue() {
        return langue;
    }
}
