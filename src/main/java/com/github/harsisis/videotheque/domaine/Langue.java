package com.github.harsisis.videotheque.domaine;

public enum Langue {
    FR("Français"),
    EN("Anglais"),
    IT("Italien"),
    DE("Allemand"),
    ES("Espagnol");

    private final String libelle;

    Langue(String libelle) {
        this.libelle = libelle;
    }

    public String getLangue() {
        return libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
