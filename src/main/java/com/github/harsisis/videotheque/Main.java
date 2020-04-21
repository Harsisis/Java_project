package com.github.harsisis.videotheque;

import com.github.harsisis.videotheque.graphique.Fenetre;
import com.github.harsisis.videotheque.objet.CategorieLivre;
import com.github.harsisis.videotheque.objet.Client;
import com.github.harsisis.videotheque.objet.Livre;

public class Main {
    public static void main(String[] args) {
        Livre l01 = new Livre("Garfield", 2.5, "Martin", CategorieLivre.BD);

        Client cl01 = new Client("Maurice", "Pat", false);
        //System.out.println(cl01.getReduction());

        Client cl02 = new Client("Maurice", "Pat", true);
        //System.out.println(cl02.getReduction());

        //created window
        Fenetre video = new Fenetre();

    }
}
