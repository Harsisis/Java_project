package com.github.harsisis;

import com.github.harsisis.Client;

public class Fidele extends Client {

    private double reduction = 0.1;

    public Fidele(int id_client, String nom, String prenom) {
        super(id_client, nom, prenom);
    }

    @Override
    public double GetReduction() {
        return 0;
    }

}
