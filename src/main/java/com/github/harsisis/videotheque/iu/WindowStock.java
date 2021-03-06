package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Videotheque;
import com.github.harsisis.videotheque.util.ValidatorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class WindowStock extends JFrame {
    private static JTextField saisiQuantityJtf = new JTextField();
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workPlacePnl = new JPanel(); // panel with blank to enter customer's data
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
    private JLabel titleLbl = new JLabel("Ajouter du Stock :");
    private JLabel ProductLbl = new JLabel("Produit :");
    private JLabel QuantityLbl = new JLabel("Quantité :");
    private JButton cancelStockBtn = new JButton("Annuler");
    private JButton confirmStockBtn = new JButton("Valider");

    public WindowStock() {    // set window settings --------------------------------------------------------------------
        if(!Videotheque.getInstance().getListProduit().isEmpty()) {
            setTitle("Ajout d'un Stock");
            setSize(500, 300);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            JComboBox<String> cBox = new JComboBox<>();
            cBox.setPreferredSize(new Dimension(400, 15));
            for (String produit : Videotheque.getInstance().getListStockProduit().keySet()) {
                cBox.addItem(produit);
            }
            cBox.setRenderer(ComboBoxRenderer.createListRendererProduit());
            defWindow(cBox);

            // set visible------------------------------------------------------------------------------
            setContentPane(displayPnl);
            setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Impossible, aucun produit n'est enregistré", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void defWindow(JComboBox<String> cBox) {
        // add to Action Listener-------------------------------------------------------------------
        // by default set the text field to blank and add some procedure
        //confirmStockButton verify if textFields are not blank and if is the case it create a Stock else it display error message
        saisiQuantityJtf.setText("");
        cancelStockBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        confirmStockBtn.addActionListener(e -> processInput(cBox));

        // title panel--------------------------------------------------------------------------------
        titleLbl.setForeground(Color.white);
        titlePnl.add(titleLbl);
        titlePnl.setBackground(Color.darkGray);
        titlePnl.setPreferredSize(new Dimension(500, 30));

        // workPlace panel----------------------------------------------------------------------------
        saisiQuantityJtf.setPreferredSize(new Dimension(150, 30));

        workPlacePnl.setLayout(new GridLayout(4, 1, 0, 5));
        workPlacePnl.add(ProductLbl);
        workPlacePnl.add(cBox);

        cBox.setBackground(Color.white);
        workPlacePnl.add(QuantityLbl);
        workPlacePnl.add(saisiQuantityJtf);


        // confirm panel------------------------------------------------------------------------------
        confirmPnl.add(confirmStockBtn);
        confirmPnl.add(cancelStockBtn);
        confirmPnl.setPreferredSize(new Dimension(500, 200));
        confirmStockBtn.setBackground(Color.white);
        cancelStockBtn.setBackground(Color.white);

        // display panel------------------------------------------------------------------------------
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(workPlacePnl, BorderLayout.CENTER);
        displayPnl.add(confirmPnl, BorderLayout.SOUTH);
    }

    private void processInput(JComboBox<String> cBox) {
        if (ValidatorUtil.isValidInteger(saisiQuantityJtf.getText()) && Integer.parseInt(saisiQuantityJtf.getText()) >= 0) {
            Videotheque.getInstance().ajoutStockProduit((String) cBox.getSelectedItem(), Integer.parseInt(saisiQuantityJtf.getText()));
            if (Integer.parseInt(saisiQuantityJtf.getText()) == 1) {
                JOptionPane.showMessageDialog(this, saisiQuantityJtf.getText() +
                        " unité a bien été ajouté pour ce produit", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, saisiQuantityJtf.getText() +
                        " unités ont bien été ajoutés pour ce produit", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else {
            JOptionPane.showMessageDialog(this, "Quantité invalide, veuillez saisir un entier positif", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
}
