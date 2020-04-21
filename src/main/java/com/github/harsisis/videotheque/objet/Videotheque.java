package com.github.harsisis.videotheque.objet;

import java.util.HashSet;

public class Videotheque {

    private static final double REDUC_FIDELE = 0.1;

    HashSet<Client> listClient = new HashSet<>();

    public boolean ajoutClient (String nom, String prenom, boolean fidele) {
        Client client = new Client(nom, prenom, fidele);
        return listClient.add(client);
    }
}
