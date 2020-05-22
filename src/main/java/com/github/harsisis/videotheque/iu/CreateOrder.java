package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class CreateOrder extends JFrame{

    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel managePnl = new JPanel();// panel with client list, add button, confirm button and cancel button
    private JPanel workplacePnl = new JPanel(); // panel to display all the products and total
    private JPanel titlePnl = new JPanel();//panel to display the title
    private JPanel selectPnl = new JPanel();//panel with list to select a customer
    private JPanel addDelPnl = new JPanel();// panel with add and delete button for product
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
    private JPanel listProdPnl = new JPanel();//list with all the product includes in the order
    private JPanel totalPnl = new JPanel();// display total price

    private JLabel titleLbl = new JLabel("Enregistrer une nouvelle commande :");
    private JLabel productAddLbl = new JLabel("Ajouter ou Supprimer un produit :");
    private JLabel choiceClLbl = new JLabel("Choisir un client :");
    private JLabel amountLbl = new JLabel("Total :");

    private JButton plusProductBtn = new JButton("+");
    private JButton minusProductBtn = new JButton("-");
    private JButton confirmOrderBtn = new JButton("Valider");
    private JButton cancelOrderBtn = new JButton("Annuler");


    public CreateOrder(){
        // set window settings --------------------------------------------------------------------
        setTitle("Ajout d'une Commande");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JComboBox<Client> liClientJcbx = new JComboBox<>();

        for (Client client : Videotheque.getInstance().getListClient())
            liClientJcbx.addItem(client);

        //manage panel left side of the model-------------------------------------------------------
        managePnl.setBackground(Color.darkGray);
        managePnl.setLayout(new GridLayout(8, 1, 10, 10));
        managePnl.add(titlePnl);
        managePnl.add(selectPnl);
        managePnl.add(addDelPnl);
        managePnl.add(confirmPnl);

        //title panel-------------------------------------------------------------------------------
        titlePnl.add(titleLbl);
        titlePnl.setBackground(Color.darkGray);
        titleLbl.setForeground(Color.white);


        cancelOrderBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        //confirmOrderBtn.addActionListener();


        //select panel------------------------------------------------------------------------------
        selectPnl.setLayout(new GridLayout(2,1));
        selectPnl.setBackground(Color.white);
        selectPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        selectPnl.add(choiceClLbl);
        selectPnl.add(liClientJcbx);
        liClientJcbx.setBackground(Color.white);

        //confirm panel-----------------------------------------------------------------------------
        confirmPnl.add(confirmOrderBtn);
        confirmPnl.add(cancelOrderBtn);
        confirmPnl.setBackground(Color.white);
        confirmPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        confirmOrderBtn.setBackground(Color.white);
        cancelOrderBtn.setBackground(Color.white);

        //workplace panel---------------------------------------------------------------------------
        workplacePnl.setBackground(Color.white);
        workplacePnl.setLayout(new BorderLayout());
        workplacePnl.add(listProdPnl, BorderLayout.NORTH);
        workplacePnl.add(totalPnl, BorderLayout.SOUTH);

        //addDel panel------------------------------------------------------------------------------
        addDelPnl.add(productAddLbl);
        addDelPnl.add(plusProductBtn);
        addDelPnl.add(minusProductBtn);
        addDelPnl.setBackground(Color.white);
        addDelPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        plusProductBtn.setBackground(Color.white);
        minusProductBtn.setBackground(Color.white);

        //liste produit panel-----------------------------------------------------------------------

        //total panel-------------------------------------------------------------------------------
        totalPnl.setBackground(Color.darkGray);
        totalPnl.setLayout(new GridLayout(1,4));
        totalPnl.add(amountLbl);
        //totalPnl.add();

        //display panel-----------------------------------------------------------------------------
        displayPnl.setBackground(Color.white);
        displayPnl.setOpaque(true);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(managePnl, BorderLayout.WEST);
        displayPnl.add(workplacePnl, BorderLayout.EAST);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }
}
