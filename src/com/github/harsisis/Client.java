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
        this.reduction = GetReduction();
    }

    public UUID GetId (){
        return id;
    }

    String GetNom (){
        return nom;
    }

    String GetPrenom (){
        return prenom;
    }

    public abstract double GetReduction();

    @Override
    public String toString() {
        return "\nInformations client n° " + GetId() + " :\nnom : " + GetNom() + "\nprénom : " + GetPrenom() + "\nréduction : " + GetReduction();
    }
}
