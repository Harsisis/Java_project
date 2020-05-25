package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Livre;
import com.github.harsisis.videotheque.domaine.Produit;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Map;

public class CreateStock extends JFrame{
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workPlacePnl = new JPanel(); // panel with blank to enter customer's data
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm

    private JLabel titleLbl = new JLabel("Ajouter du Stock :");
    private JLabel ProductLbl = new JLabel("Produit :");
    private JLabel QuantityLbl = new JLabel("Quantité :");

    private static JTextField saisiQuantityJtf = new JTextField();

    private JButton cancelStockBtn = new JButton("Annuler");
    private JButton confirmStockBtn = new JButton("Valider");

    private JOptionPane jop3 = new JOptionPane();

    public CreateStock(){    // set window settings --------------------------------------------------------------------
        setTitle("Ajout d'un Stock");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JComboBox cBox = new JComboBox();
            cBox.setPreferredSize(new Dimension(400, 15));

            Map<Produit, Integer> listStockProduit = Videotheque.getInstance().getListStockProduit();

            for (Produit produit : listStockProduit.keySet()) {
                if (produit.getClassName().equals("Livre")) {
                    cBox.addItem(produit.getCategorieProduit() + " | " + produit.getClassName() + " | " + Livre.getCategorieLivre() + " | " + produit.getTitre());
                }
                else cBox.addItem(produit.getCategorieProduit() + " | " + produit.getClassName() + " | " + produit.getTitre());

                // cBox.addItem(produit);
            }

        // add to Action Listener-------------------------------------------------------------------
        // by default set the text field to blank and add some procedure
        //confirmStockButton verify if textFields are not blank and if is the case it create a Stock else it display error message
        saisiQuantityJtf.setText("");
        cancelStockBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        confirmStockBtn.addActionListener(e -> {
            if (Videotheque.getInstance().isValidInteger(saisiQuantityJtf.getText()))
                Videotheque.getInstance().ajoutStockProduit((Produit) cBox.getSelectedItem(),Integer.parseInt(saisiQuantityJtf.getText()));
            else
                jop3.showMessageDialog(null, "Quantité invalide, veuillez saisir un entier", "Erreur", JOptionPane.ERROR_MESSAGE);
        });

        // title panel--------------------------------------------------------------------------------
        titleLbl.setForeground(Color.white);
        titlePnl.add(titleLbl);
        titlePnl.setBackground(Color.darkGray);
        titlePnl.setPreferredSize(new Dimension(500,30));

        // workPlace panel----------------------------------------------------------------------------
        saisiQuantityJtf.setPreferredSize(new Dimension(150, 30));

        workPlacePnl.setLayout(new GridLayout(4,1,0,5));
        workPlacePnl.add(ProductLbl);
        workPlacePnl.add(cBox);
        workPlacePnl.add(QuantityLbl);
        workPlacePnl.add(saisiQuantityJtf);


        // confirm panel------------------------------------------------------------------------------
        confirmPnl.add(confirmStockBtn);
        confirmPnl.add(cancelStockBtn);
        confirmPnl.setPreferredSize(new Dimension(500,200));
        confirmStockBtn.setBackground(Color.white);
        cancelStockBtn.setBackground(Color.white);

        // display panel------------------------------------------------------------------------------
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(workPlacePnl, BorderLayout.CENTER);
        displayPnl.add(confirmPnl, BorderLayout.SOUTH);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }
}
