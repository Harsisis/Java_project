package com.github.harsisis.videotheque.domaine;

public enum CategorieProduit {
    DOCUMENT("Document"),
    SUPPORT_NUMERIQUE("Support Numérique");

    private final String libelle;

    CategorieProduit(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
