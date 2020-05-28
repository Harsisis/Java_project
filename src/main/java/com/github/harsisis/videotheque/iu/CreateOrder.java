package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Commande;
import com.github.harsisis.videotheque.domaine.Emprunt;
import com.github.harsisis.videotheque.domaine.Videotheque;
import com.github.harsisis.videotheque.util.ValidatorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CreateOrder extends JFrame {

    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel managePnl = new JPanel();// panel with client list, add button, confirm button and cancel button
    private JPanel workplacePnl = new JPanel(); // panel to display all the products and total
    private JPanel titlePnl = new JPanel();//panel to display the title
    private JPanel selectPnl = new JPanel();//panel with list to select a customer
    private JPanel selectProductPnl = new JPanel();//panel with a combo box of all the products
    private JPanel selectTimePnl = new JPanel();//panel to select the rental period
    private JPanel addDelPnl = new JPanel();// panel with add and delete button for product
    private JPanel selectLoaningPnl = new JPanel();// panel with add and delete button for product
    private JPanel confirmMinusPnl = new JPanel();// panel with add and delete button for product
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
    private JPanel confirmPlusPnl = new JPanel();// panel with button to cancel or confirm add product
    private JPanel listProdPnl = new JPanel();//list with all the product includes in the order
    private JPanel totalPnl = new JPanel();// display total price

    private JLabel titleLbl = new JLabel("Enregistrer une nouvelle commande :");
    private JLabel productAddLbl = new JLabel("Ajouter ou Supprimer un produit :");
    private JLabel choicePrdLbl = new JLabel("Choisir un produit :");
    private JLabel choiceClLbl = new JLabel("Choisir un client :");
    private JLabel durationLbl = new JLabel("Saisir une durée (en jour) :");
    private JLabel amountLbl = new JLabel("Total :");

    private JButton plusProductBtn = new JButton("+");
    private JButton minusProductBtn = new JButton("-");
    private JButton confirmOrderBtn = new JButton("Valider");
    private JButton cancelOrderBtn = new JButton("Annuler");
    private JButton confirmProductBtn = new JButton("Ajouter");
    private JButton cancelProductBtn = new JButton("Annuler");
    private JButton confirmDelBtn = new JButton("Supprimer");
    private JButton cancelDelBtn = new JButton("Annuler");

    private JTextField durationJtf = new JTextField();

    private JOptionPane jop3 = new JOptionPane();

    public CreateOrder() {
        // set window settings --------------------------------------------------------------------
        setTitle("Ajout d'une Commande");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        managePnl.setPreferredSize(new Dimension(400,600));
        managePnl.setBackground(Color.darkGray);
        managePnl.setLayout(new GridLayout(7, 1, 10, 10));

        JComboBox<Client> liClientJcbx = new JComboBox<>();

        for (Client client : Videotheque.getInstance().getListClient()) {
            liClientJcbx.addItem(client);
        }

        JComboBox<String> liProductJcbx = new JComboBox<>();

        for (String produit : Videotheque.getInstance().getListStockProduit().keySet()) {
            liProductJcbx.addItem(produit);
        }

        JComboBox<String> liLoaningJcbx = new JComboBox<>();

//        for (int i = 0; i < Commande.getListEmprunt().size(); i++){
//            liLoaningJcbx.addItem((String) Commande.getListEmprunt().get(i));
//        }

        Commande commande = new Commande();

        //buttons on the main page
        cancelOrderBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        confirmOrderBtn.addActionListener(e -> {
            if (true) {
                Videotheque.getInstance().ajoutCommande((Client) liClientJcbx.getSelectedItem(), commande.getListEmprunt());
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            } else
                jop3.showMessageDialog(null, "Veuillez rentrer un Nom et Prénom valide", "Erreur", JOptionPane.ERROR_MESSAGE);

            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        //buttons on the adding loaning page
        plusProductBtn.addActionListener(e -> addParameter(liProductJcbx));

        cancelProductBtn.addActionListener(e -> mainPage(liClientJcbx));

        confirmProductBtn.addActionListener(e -> {
            if (ValidatorUtil.isValidInteger(durationJtf.getText())) {
               commande.ajoutEmprunt(liProductJcbx.getSelectedItem().toString(), Integer.parseInt(durationJtf.getText()));

                //liste produit panel-----------------------------------------------------------------------
                durationJtf.setText("");
                mainPage(liClientJcbx);
            }
            else {
                jop3.showMessageDialog(null, "Veuillez saisir une durée valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        //buttons on the delete loaning page
        minusProductBtn.addActionListener(e -> removeParameter());

        cancelDelBtn.addActionListener(e -> mainPage(liClientJcbx));

        //display panel-----------------------------------------------------------------------------
        mainPage(liClientJcbx);
        displayPnl.setBackground(Color.white);
        displayPnl.setOpaque(true);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(managePnl, BorderLayout.WEST);
        displayPnl.add(workplacePnl, BorderLayout.EAST);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }

    public void mainPage(JComboBox liClientJcbx){
        //manage panel left side of the model-------------------------------------------------------
        managePnl.removeAll();
        managePnl.add(titlePnl);
        managePnl.add(selectPnl);
        managePnl.add(addDelPnl);
        managePnl.add(confirmPnl);

        //title panel-------------------------------------------------------------------------------
        titlePnl.add(titleLbl);
        titlePnl.setBackground(Color.darkGray);
        titleLbl.setForeground(Color.white);

        //select panel------------------------------------------------------------------------------
        selectPnl.setLayout(new GridLayout(2, 1));
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
        listProdPnl.setPreferredSize(new Dimension(800,500));
        listProdPnl.setBackground(Color.white);

        //total panel-------------------------------------------------------------------------------
        totalPnl.setPreferredSize(new Dimension(750,100));
        totalPnl.setBackground(Color.darkGray);
        totalPnl.setLayout(new GridLayout(1, 4));
        totalPnl.add(amountLbl);
        amountLbl.setForeground(Color.white);
        //totalPnl.add();
    }

    public void addParameter(JComboBox liProductJcbx){


        //select product panel----------------------------------------------------------------------
        selectProductPnl.setLayout(new GridLayout(2,1));
        selectProductPnl.setBackground(Color.white);
        selectProductPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        selectProductPnl.add(choicePrdLbl);
        selectProductPnl.add(liProductJcbx);
        liProductJcbx.setBackground(Color.white);

        //select time panel----------------------------------------------------------------------
        selectTimePnl.setLayout(new GridLayout(2,1));
        selectTimePnl.setBackground(Color.white);
        selectTimePnl.setBorder(BorderFactory.createLineBorder(Color.black));
        selectTimePnl.add(durationLbl);
        selectTimePnl.add(durationJtf);
        durationJtf.setBackground(Color.white);

        //confirm add product panel----------------------------------------------------------------
        confirmPlusPnl.setBackground(Color.white);
        confirmPlusPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        confirmPlusPnl.add(confirmProductBtn);
        confirmPlusPnl.add(cancelProductBtn);
        confirmProductBtn.setBackground(Color.white);
        cancelProductBtn.setBackground(Color.white);

        //manage panel ----------------------------------------------------------------------------
        managePnl.removeAll();
        managePnl.add(titlePnl);
        managePnl.add(selectProductPnl);
        managePnl.add(selectTimePnl);
        managePnl.add(confirmPlusPnl);

        revalidate();
        managePnl.repaint();
    }

    public void removeParameter(){
        managePnl.removeAll();

        managePnl.add(titlePnl);
        managePnl.add(selectLoaningPnl);
        managePnl.add(confirmMinusPnl);

        //select loaning panel-----------------------------------------------------------------------

        selectLoaningPnl.setBackground(Color.white);
        selectLoaningPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        //selectLoaningPnl.add(liLoaningJcbx);

        //confirm minus panel------------------------------------------------------------------------

        confirmMinusPnl.setBackground(Color.white);
        confirmMinusPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        confirmMinusPnl.add(confirmDelBtn);
        confirmMinusPnl.add(cancelDelBtn);
        confirmDelBtn.setBackground(Color.white);
        cancelDelBtn.setBackground(Color.white);

        revalidate();
        managePnl.repaint();
    }
}
