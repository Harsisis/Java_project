package com.github.harsisis.videotheque;

import java.util.UUID;

public abstract class Produit {
    private final UUID id;
    private String titre;
    private double tarifJournalier;
    private String categorie;

    public Produit(String titre, double tarifJournalier, String categorie) {
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

    public String getCategorie() {
        return categorie;
    }

    @Override
    public String toString() {
        return "\nNuméro de produit : " + getId() +
                "\nTitre : " + getTitre() +
                "\nTarif journalier : " + getTarifJournalier() + "€" +
                "\nCatégorie : " + getCategorie();
    }
}