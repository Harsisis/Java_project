package com.github.harsisis.videotheque.domaine;

public class Dictionnaire extends Produit {

    private Langue langue;

    public Dictionnaire(String titre, double tarifJournalier, Langue langue) {
        super(titre, "Dictionnaire", tarifJournalier, CategorieProduit.DOCUMENT);
        this.langue = langue;
    }

    public Langue getLangue() {
        return langue;
    }
}
