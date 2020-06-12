package com.github.harsisis.videotheque.domaine;

import java.util.Objects;
import java.util.UUID;

public abstract class Produit {
    private final UUID produitId;
    private String titre;
    private double tarifJournalier;
    private CategorieProduit categorieProduit;

    public Produit(String titre, double tarifJournalier, CategorieProduit categorieProduit) {
        this.produitId = UUID.randomUUID();
        this.titre = titre;
        this.tarifJournalier = tarifJournalier;
        this.categorieProduit = categorieProduit;
    }

    public String getProduitId() {
        return this.produitId.toString();
    }

    public String getTitre() {
        return titre;
    }

    public double getTarifJournalier() {
        return tarifJournalier;
    }

    public String getProduitNom(Produit produit) {
        String result = "";
        if (produit instanceof Livre) {
            result = produit.getCategorieProduit().getLibelle() + " | " + "Livre" + " | " + ((Livre) produit).getCategorieLivre().getLibelle() + " | " + produit.getTitre();
        } else if (produit instanceof Dictionnaire) {
            result = produit.getCategorieProduit().getLibelle() + " | " + "Dictionnaire" + " | " + ((Dictionnaire) produit).getLangue() + " | " + produit.getTitre();
        } else if (produit instanceof DVD) {
            result = produit.getCategorieProduit().getLibelle() + " | " + "DVD" + " | " + ((DVD) produit).getRealisateur() + " | " + produit.getTitre();
        } else if (produit instanceof CD) {
            result = produit.getCategorieProduit().getLibelle() + " | " + "CD" + " | " + ((CD) produit).getAnneeSortie() + " | " + produit.getTitre();
        }
        return result;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return produitId.equals(produit.produitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produitId);
    }

}