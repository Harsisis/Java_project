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
            result = produit.getCategorieProduit().getLibelle() + " | " + produit.getClassName() + " | " + Livre.getCategorieLivre().getLibelle() + " | " + produit.getTitre();
        } else {
            result = produit.getCategorieProduit().getLibelle() + " | " + produit.getClassName() + " | " + produit.getTitre();
        }
        return result;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public String getClassName() {
        String on;
        on = this.getClass().getSimpleName();
        return on;
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

    @Override
    public String toString() {
        String result = "";
        if (this instanceof Livre) {
            result = this.getCategorieProduit().getLibelle() + " | " + this.getClassName() + " | " + Livre.getCategorieLivre().getLibelle() + " | " + this.getTitre();
        } else {
            result = this.getCategorieProduit().getLibelle() + " | " + this.getClassName() + " | " + this.getTitre();
        }

        return result;
    }
}