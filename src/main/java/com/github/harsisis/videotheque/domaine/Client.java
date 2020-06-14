package com.github.harsisis.videotheque.domaine;

import java.util.Objects;
import java.util.UUID;

public class Client {

    private final UUID clientId;
    private String nom;
    private String prenom;
    private boolean fidele;

    public Client(String nom, String prenom, boolean fidele) {
        this.clientId = UUID.randomUUID();
        this.nom = nom;
        this.prenom = prenom;
        this.fidele = fidele;
    }

    public String getClientId() {
        return clientId.toString();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId.equals(client.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }

    @Override
    public String toString() {
        return nom + " | " + prenom;
    }
}
