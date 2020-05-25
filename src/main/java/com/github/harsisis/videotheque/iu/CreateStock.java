package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Produit;
import com.github.harsisis.videotheque.domaine.Videotheque;
import com.github.harsisis.videotheque.util.ValidatorUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

public class CreateStock extends JFrame {
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

    public CreateStock() {    // set window settings --------------------------------------------------------------------
        setTitle("Ajout d'un Stock");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JComboBox<Produit> cBox = new JComboBox<>();
        cBox.setPreferredSize(new Dimension(400, 15));


        for (Produit produit : Videotheque.getInstance().getListStockProduit().keySet()) {
            cBox.addItem(produit);
        }

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

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }

    private void processInput(JComboBox<Produit> cBox) {
        if (ValidatorUtil.isValidInteger(saisiQuantityJtf.getText())) {
            Videotheque.getInstance().ajoutStockProduit((Produit) cBox.getSelectedItem(), Integer.parseInt(saisiQuantityJtf.getText()));
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else {
            JOptionPane.showMessageDialog(this, "Quantité invalide, veuillez saisir un entier", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }
}
