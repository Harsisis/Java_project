package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CreateOrder extends JFrame{

    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workplacePnl = new JPanel(); // panel with blank to enter customer's data
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm

    private JLabel titleLbl = new JLabel("Enregistrer une nouvelle commande :");
    private JLabel productLbl = new JLabel("Liste produit(s) :");
    private JLabel amountLbl = new JLabel("Prix :");

    /*-------------
    private JCheckBox codeCbx = new JCheckBox();
    private static JTextField saisiCodeJtf = new JTextField();
   ---------------- if we want to manage promotional code */

    public CreateOrder(Videotheque app){

        setTitle("Ajout d'une Commande");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
