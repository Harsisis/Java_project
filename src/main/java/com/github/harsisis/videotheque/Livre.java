package com.github.harsisis.videotheque;

public class Livre extends Produit {

    private String auteur;
    private CategorieLivre categorie;

    public Livre(String titre, double tarifJournalier, String auteur, CategorieLivre categorie) {
        super(titre, tarifJournalier,CategorieProduit.DOCUMENT);
        this.auteur = auteur;
        this.categorie = categorie;
    }

    public String getAuteur() {
        return auteur;
    }
}