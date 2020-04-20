package com.github.harsisis.videotheque;

//import com.github.harsisis.Dictionnaire;
//import com.github.harsisis.Livre;
import javax.swing.*;
import java.awt.*;

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
