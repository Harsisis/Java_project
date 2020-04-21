package com.github.harsisis.videotheque.domaine;

import java.time.LocalDate;
import java.util.UUID;

public class Commande {

    private String commandeID;
    private LocalDate debutDate;
    private int dureeLocation;
    private Client clientId;
    private Produit produitId;

    public Commande(Client clientId, Produit produitId, int dureeLocation) {
        this.commandeID = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.produitId = produitId;
        this.debutDate = LocalDate.now();
        this.dureeLocation = dureeLocation;
    }

    public String getCommandeID() {
        return commandeID;
    }

    public LocalDate getDebutDate() {
        return debutDate;
    }

    public int getDureeLocation() {
        return dureeLocation;
    }

    public Client getClientId() {
        return clientId;
    }

    public Produit getProduitId() {
        return produitId;
    }

    public LocalDate getFinLocation () {
        return this.debutDate.plusDays(this.dureeLocation);
    }
}
