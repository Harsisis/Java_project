package com.github.harsisis.videotheque.domaine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Commande {

    private UUID commandeId;
    private LocalDate debutDate;
    private Client client;
    ArrayList<Emprunt> listEmprunt = new ArrayList<>();

    public Commande(Client client, ArrayList<Emprunt> empruntList) {
        this.commandeId = UUID.randomUUID();
        this.client = client;
        this.debutDate = LocalDate.now();
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


    public boolean ajoutEmprunt (Produit produit, int dureeLocation) {
        Emprunt emprunt = new Emprunt(produit, dureeLocation);
        return listEmprunt.add(emprunt);
    }

    // peut etre autre methode plus simple a comprendre
    public double calculPrix() {

        //expression ternaire
        double coefPrix = client.isFidele() ? 1 - Videotheque.REDUC_FIDELE : 1;

        // solution plus basique
        double total = 0;
        for (Emprunt emprunt: listEmprunt) {
            total += emprunt.getProduit().getTarifJournalier() * emprunt.getDureeLocation() * coefPrix;
        }

        return total;

        // solution plus moderne
        /*
        return listEmprunt.stream()
                .map(emprunt -> emprunt.getProduit().getTarifJournalier() * emprunt.getDureeLocation() * coefPrix)
                .reduce(0.0D, (a,b) -> a+b);
        */
    }

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
