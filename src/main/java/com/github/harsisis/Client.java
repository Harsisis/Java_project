package com.github.harsisis;

import java.util.UUID;

public abstract class Client {

    private final UUID id;
    private String nom;
    private String prenom;
    private double reduction;
    private boolean typeClient;

    public Client(int id, String nom, String prenom) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.prenom = prenom;
        this.reduction = getReduction();
    }

    public UUID getId (){
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public abstract double getReduction();

    @Override
    public String toString() {
        return "\nInformations client n° " + getId() + " :\nnom : " + getNom() + "\nprénom : " + getPrenom() + "\nréduction : " + getReduction();
    }
}
