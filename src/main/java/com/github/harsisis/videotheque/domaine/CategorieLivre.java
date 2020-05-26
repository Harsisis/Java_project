package com.github.harsisis.videotheque.domaine;

public enum CategorieLivre {
    BD("Bande dessinée"),
    MANUEL("Manuel"),
    ROMAN("Roman");

    private final String libelle;

    CategorieLivre(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
