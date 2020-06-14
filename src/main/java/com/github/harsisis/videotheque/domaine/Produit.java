package com.github.harsisis.videotheque.domaine;

import java.util.Objects;
import java.util.UUID;

public abstract class Produit {
    private final UUID produitId;
    private String type;
    private String titre;
    private double tarifJournalier;
    private CategorieProduit categorieProduit;

    public Produit(String titre, String type, double tarifJournalier, CategorieProduit categorieProduit) {
        this.produitId = UUID.randomUUID();
        this.type = type;
        this.titre = titre;
        this.tarifJournalier = tarifJournalier;
        this.categorieProduit = categorieProduit;
    }

    public String getType() {
        return type;
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
            result = produit.getCategorieProduit().getLibelle() + " | " + "Livre" + " | " + produit.getTitre() + " | " + ((Livre) produit).getCategorieLivre().getLibelle();
        } else if (produit instanceof Dictionnaire) {
            result = produit.getCategorieProduit().getLibelle() + " | " + "Dictionnaire" + " | " + produit.getTitre() + " | " + ((Dictionnaire) produit).getLangue();
        } else if (produit instanceof DVD) {
            result = produit.getCategorieProduit().getLibelle() + " | " + "DVD" + " | " + produit.getTitre() + " | " + ((DVD) produit).getRealisateur();
        } else if (produit instanceof CD) {
            result = produit.getCategorieProduit().getLibelle() + " | " + "CD" + " | " + produit.getTitre() + " | " + ((CD) produit).getAnneeSortie();
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