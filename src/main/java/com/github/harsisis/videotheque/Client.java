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

    public boolean isFidele() {
        return fidele;
    }

}
