package com.github.harsisis.videotheque;

import java.util.UUID;

public class Client {

    private final UUID id;
    private String nom;
    private String prenom;
    private double reduction;
    private boolean fidele;

    public Client(String nom, String prenom, boolean fidele) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.prenom = prenom;
        this.fidele = fidele;
        this.reduction = getReduction();
    }

    public String getId() {
        return id.toString();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public double getReduction() {
        if (this.fidele)
            this.reduction = 0.1;
        else
            this.reduction = 0;
        return this.reduction;
    }

    public boolean isFidele() {
        return fidele;
    }

    @Override
    public String toString() {
        return "\nInformations client n° " + getId() +
                "\nNom : " + getNom() +
                "\nPrénom : " + getPrenom() +
                "\nRéduction : " + getReduction();
    }
}
