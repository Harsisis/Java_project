package com.github.harsisis.videotheque;

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

    public UUID getProduitId() {
        return this.produitId;
    }

    public String getTitre() {
        return titre;
    }

    public double getTarifJournalier() {
        return tarifJournalier;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

}