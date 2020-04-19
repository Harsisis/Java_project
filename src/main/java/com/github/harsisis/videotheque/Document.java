package com.github.harsisis.videotheque;

public class Document extends Produit {
    private String selection;

    public Document(String titre, double tarif_journalier, String selection) {
        super(titre, tarif_journalier, "Document");
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType de produit : " + getSelection();
    }
}