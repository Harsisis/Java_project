package com.github.harsisis.videotheque.domaine;

public class Livre extends Produit {

    private CategorieLivre categorieLivre;
    private String auteur;

    public Livre(String titre, double tarifJournalier, String auteur, CategorieLivre categorieLivre) {
        super(titre, tarifJournalier, CategorieProduit.DOCUMENT);
        this.auteur = auteur;
        this.categorieLivre = categorieLivre;
    }

    public CategorieLivre getCategorieLivre() {
        return categorieLivre;
    }

    public String getAuteur() {
        return auteur;
    }
}