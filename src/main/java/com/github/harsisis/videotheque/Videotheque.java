package com.github.harsisis.videotheque;

import java.util.HashSet;

public class Videotheque {

    private static double reducFidele;

    HashSet<Client> listClient = new HashSet<Client>();

    public boolean ajoutClient (String nom, String prenom, boolean fidele) {
        Client client = new Client(nom, prenom, fidele);
        return listClient.add(client);
    }
}
