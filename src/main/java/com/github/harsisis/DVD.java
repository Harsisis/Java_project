package com.github.harsisis;

public class DVD extends Support_numerique {

    private String realisateur;

    public DVD(int id_produit, String titre, double tarif_journalier, String realisateur) {
        super(id_produit, titre, tarif_journalier,"CD");
        this.realisateur = realisateur;
    }

    public String getRealisateur() {
        return realisateur;
    }

    @Override
    String getTitre(String titre) {
        return titre;
    }

    @Override
    double getTarif_journalier(double tarif_journalier) {
        return tarif_journalier;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRéalisateur : " + getRealisateur();
    }

}