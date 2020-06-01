package com.github.harsisis.videotheque.domaine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Commande {

    private UUID commandeId;
    private LocalDate debutDate;
    private Client client;

    private ArrayList<Emprunt> listEmprunt = new ArrayList<>();

    public Commande(Client client, ArrayList<Emprunt> listEmprunt) {
        this.commandeId = UUID.randomUUID();
        this.client = client;
        this.debutDate = LocalDate.now();
        this.listEmprunt = listEmprunt;
    }

    public Commande() {
    }

    public String getCommandeId() {
        return commandeId.toString();
    }

    public LocalDate getDebutDate() {
        return debutDate;
    }

    public Client getClient() {
        return client;
    }

    public ArrayList<Emprunt> getListEmprunt() {
        return listEmprunt;
    }

    // peut etre autre methode plus simple a comprendre

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Commande commande = (Commande) o;
        return commandeId.equals(commande.commandeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandeId);
    }
}
