package com.github.harsisis;

public abstract class Produit {
    private int id_produit;
    private String titre;
    private double tarif_journalier;
    private String categorie;

    public Produit(int id_produit, String titre, double tarif_journalier, String categorie) {
        this.id_produit = id_produit;
        this.titre = titre;
        this.tarif_journalier = tarif_journalier;
        this.categorie = categorie;
    }

    public int getId_produit() {
        return id_produit;
    }

    public String getCategorie() {
        return categorie;
    }

    abstract String getTitre(String titre);

    abstract double getTarif_journalier(double tarif_journalier);

    @Override
    public String toString() {
        return "\nNuméro de produit : " + id_produit + "\nTitre : " + titre + "\nTarif journalier : " + tarif_journalier + "€" + "\nCatégorie : " + categorie;
    }
}