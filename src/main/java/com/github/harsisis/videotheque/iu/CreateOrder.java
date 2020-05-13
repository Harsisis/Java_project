package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.*;

public class CreateOrder extends JFrame{

    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel managePnl = new JPanel();// panel with client list, add button, confirm button and cancel button
    private JPanel workplacePnl = new JPanel(); // panel to display all the products and total
    private JPanel titlePnl = new JPanel();//panel to display the title
    private JPanel selectPnl = new JPanel();//panel with list to select a customer and add button
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
    private JPanel liProdPnl = new JPanel();//list with all the product includes in the order
    private JPanel totalPnl = new JPanel();// display total price

    private JLabel titleLbl = new JLabel("Enregistrer une nouvelle commande :");
    private JLabel productAddLbl = new JLabel("Ajouter un produit :");
    private JLabel choiceClLbl = new JLabel("Choisir un client :");
    private JLabel amountLbl = new JLabel("Total :");

    private JButton plusProductBtn = new JButton("+");
    private JButton confirmOrderBtn = new JButton("Valider");
    private JButton cancelOrderBtn = new JButton("Annuler");

    /*-------------
    private JCheckBox codeCbx = new JCheckBox();
    private static JTextField saisiCodeJtf = new JTextField();
   ---------------- if we want to manage promotional code */

    public CreateOrder(){
        // set window settings --------------------------------------------------------------------
        setTitle("Ajout d'une Commande");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //manage panel left side of the model-------------------------------------------------------
        managePnl.setPreferredSize(new Dimension(200,600));
        managePnl.setLayout(new BorderLayout());
        managePnl.add(titlePnl, BorderLayout.NORTH);
        managePnl.add(selectPnl, BorderLayout.CENTER);
        managePnl.add(confirmPnl, BorderLayout.SOUTH);

        //title panel-------------------------------------------------------------------------------
        titleLbl.setForeground(Color.white);
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePnl.add(titleLbl);
        titlePnl.setPreferredSize(new Dimension());
        titlePnl.setBackground(Color.darkGray);


        //display panel-----------------------------------------------------------------------------
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(managePnl, BorderLayout.WEST);
        displayPnl.add(workplacePnl, BorderLayout.EAST);

        //select panel------------------------------------------------------------------------------
        selectPnl.setLayout(new GridLayout(8,1,0,5));
        selectPnl.add(choiceClLbl);
        //selectPnl.add(); display dropdown list (https://www.roseindia.net/struts/struts2/struts2uitags/select-tag.shtml) (https://stackoverrun.com/fr/q/633764)
        selectPnl.add(productAddLbl);
        selectPnl.add(plusProductBtn);
        plusProductBtn.setBackground(Color.white);

        //confirm panel-----------------------------------------------------------------------------
        confirmPnl.add(confirmOrderBtn);
        confirmPnl.add(cancelOrderBtn);
        confirmOrderBtn.setBackground(Color.white);
        cancelOrderBtn.setBackground(Color.white);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }
}
