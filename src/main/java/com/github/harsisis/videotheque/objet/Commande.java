package com.github.harsisis.videotheque.objet;

import java.time.LocalDate;
import java.util.UUID;

public class Commande {

    private String commandeID;
    private LocalDate debutDate;
    private int finDate;
    private Client clientId;
    private Produit produitId;

    public Commande(Client clientId, Produit produitId, LocalDate debutDate, int finDate) {
        this.commandeID = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.produitId = produitId;
        this.debutDate = debutDate;
        this.finDate += finDate;
    }
}
