package com.github.harsisis;

import java.util.UUID;

public class Client {

    private final UUID id;
    private String nom;
    private String prenom;
    private double reduction;
    private boolean typeClient;

    public Client(String nom, String prenom, boolean typeClient) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.prenom = prenom;
        this.typeClient = typeClient;
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

    public double GetReduction() {
        if (this.typeClient)
            this.reduction = 0.1;
        else
            this.reduction = 0;
        return this.reduction;
    }

    @Override
    public String toString() {
        return "\nInformations client n° " + GetId() + " :\nnom : " + GetNom() + "\nprénom : " + GetPrenom() + "\nréduction : " + GetReduction();
    }
}
