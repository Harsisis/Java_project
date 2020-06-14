package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ComboBoxRenderer {

    static ListCellRenderer<? super String> createListRendererProduit() {
        return new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                Produit produit = Videotheque.getInstance().getProduit((String) value);
                this.setText(produit.getProduitNom(produit));
                return this;
            }
        };
    }

    static ListCellRenderer<? super String> createListRendererEmprunt(Commande commande) {
        return new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                ArrayList<Emprunt> emprunts = commande.getListEmprunt();
                for (Emprunt emp : emprunts) {
                    this.setText(Videotheque.getInstance().getProduit(emp.getProduitId()).getProduitNom(Videotheque.getInstance().getProduit(emp.getProduitId())));
                }
                return this;
            }
        };
    }

}
