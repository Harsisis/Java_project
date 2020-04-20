package com.github.harsisis.videotheque;

import java.util.UUID;

public abstract class Produit {
    private final UUID id;
    private String titre;
    private double tarifJournalier;
    private CategorieProduit categorie;

    public Produit(String titre, double tarifJournalier, CategorieProduit categorie) {
        this.id = UUID.randomUUID();
        this.titre = titre;
        this.tarifJournalier = tarifJournalier;
        this.categorie = categorie;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitre() {
        return titre;
    }

    public double getTarifJournalier() {
        return tarifJournalier;
    }

    public CategorieProduit getCategorie() {
        return categorie;
    }

}