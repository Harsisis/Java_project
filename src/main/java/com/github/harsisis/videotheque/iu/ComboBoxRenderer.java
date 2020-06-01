package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Produit;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;

public class ComboBoxRenderer {

    static ListCellRenderer<? super String> createListRenderer() {
        return new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                Produit produit = Videotheque.getInstance().getProduit((String) value);
                this.setText(String.format(produit.getProduitNom(produit)));
                return this;
            }
        };
    }

}
