package com.github.harsisis.videotheque;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.DVD;
import com.github.harsisis.videotheque.domaine.Videotheque;
import com.github.harsisis.videotheque.iu.WindowHome;

//Convention nommage
//  CSTE : ABC_DEF
//  Class : Abcd
//  Label : abcLbl
//  Button : abcBtn
//  Panel : abcPnl
//  Check Box : abcdeCbx
//  Text Field : abcdeJtf
//  Autre : abcDef
// nom de classe de iu : pas de verbe

public class Main {
    public static void main(String[] args) {
        //create window
        WindowHome video = new WindowHome();
        DVD dvd = new DVD("Leo", 5, "death");
        Videotheque.getInstance().ajoutClient("NOM", "Prénom", false);
        Videotheque.getInstance().ajoutProduit(dvd.getProduitId(),dvd);
        Videotheque.getInstance().ajoutStockProduit(dvd.getProduitId(),4);
    }
}
