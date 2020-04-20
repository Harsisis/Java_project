package com.github.harsisis.videotheque;

public class Livre extends Produit {

    private String auteur;
    private CategorieLivre categorieLivre;

    public Livre(String titre, double tarifJournalier, String auteur, CategorieLivre categorieLivre) {
        super(titre, tarifJournalier,CategorieProduit.DOCUMENT);
        this.auteur = auteur;
        this.categorieLivre = categorieLivre;
    }

    public String getAuteur() {
        return auteur;
    }

    public CategorieLivre getCategorieLivre() {
        return categorieLivre;
    }
}