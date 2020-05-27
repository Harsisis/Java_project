package com.github.harsisis.videotheque.domaine;

public class Livre extends Produit {

    private static CategorieLivre categorieLivre;
    private String auteur;

    public Livre(String titre, double tarifJournalier, String auteur, CategorieLivre categorieLivre) {
        super(titre, tarifJournalier, CategorieProduit.DOCUMENT);
        this.auteur = auteur;
        this.categorieLivre = categorieLivre;
    }

    public static CategorieLivre getCategorieLivre() {
        return categorieLivre;
    }

    public String getAuteur() {
        return auteur;
    }
}