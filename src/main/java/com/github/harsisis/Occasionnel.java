package com.github.harsisis;

import com.github.harsisis.Client;

public class Occasionnel extends Client {

    private double reduction = 0;

    public Occasionnel(int id_client, String nom, String prenom) {
        super(id_client, nom, prenom);
    }

    @Override
    public double GetReduction() {
        return reduction;
    }


}
