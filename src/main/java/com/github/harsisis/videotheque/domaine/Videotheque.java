package com.github.harsisis.videotheque.domaine;

import java.util.ArrayList;
import java.util.HashSet;

public class Videotheque {

    public static final double REDUC_FIDELE = 0.1;

    HashSet<Client> listClient = new HashSet<>();
    HashSet<Commande> listCommande = new HashSet<>();

    public boolean ajoutClient (String nom, String prenom, boolean fidele) {
        Client client = new Client(nom, prenom, fidele);
        return listClient.add(client);
    }

    public boolean ajoutCommande (Client client, ArrayList<Emprunt> empruntList) {
        Commande commande = new Commande(client, empruntList);
        return listCommande.add(commande);
    }
}
