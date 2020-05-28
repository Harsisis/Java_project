package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Commande;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

public class CreateHome extends JFrame {
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel workPlacePnl = new JPanel();// panel with a list of all customers, orders and products registered
    private JPanel listPnl = new JPanel();// panel where customers, products or orders list are
    private JPanel dataPnl = new JPanel();//panel at the right with listPnl and indicationPnl
    private JPanel indicationPnl = new JPanel();//panel with a label which change when you are clicking on the "afficher la liste des ..." from menu

    private JLabel indicationLbl = new JLabel(); // Label where i write all

    private JButton addClientBtn = new JButton("Ajouter un client");
    private JButton addOrderBtn = new JButton("Ajouter une commande");
    private JButton addProductBtn = new JButton("Ajouter un produit");
    private JButton addQtyProductBtn = new JButton("Ajouter du stock");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu li = new JMenu("Liste");
    private JMenuItem listUser = new JMenuItem("Liste des clients");
    private JMenuItem listCommand = new JMenuItem("Liste des commandes");
    private JMenuItem listProduct = new JMenuItem("Liste des produits");
    private JMenuItem listEmpty = new JMenuItem("Vider le panneau");
    private JMenuItem quit = new JMenuItem("Quitter");
    private JMenu help = new JMenu("Aide");


    public CreateHome() {

        // set window settings --------------------------------------------------------------------
        setTitle("Application");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addClientBtn.addActionListener(e -> new CreateClient());//add procedure to addClientBtn
        addOrderBtn.addActionListener(e -> new CreateOrder());
        addProductBtn.addActionListener(e -> new CreateProduct());
        addQtyProductBtn.addActionListener(e -> new CreateStock());

        //items menu ------------------------------------------------------------------------------
        //create a menu with 2 items, Liste to pull down a menu with three buttons that display list of customers, order and product
        // and the second one Aide to pull down another menu with the exit button
        menuBar.add(li);//add Li item to menu
        menuBar.add(help);

        help.add(quit);

        li.add(listUser);
        li.add(listCommand);
        li.add(listProduct);
        li.add(listEmpty);

        listUser.addActionListener(e -> {
            listPnl.removeAll();
            ArrayList<Client> clientList = new ArrayList<>(Videotheque.getInstance().getListClient());
            System.out.println(clientList);
            JList list = new JList(clientList.toArray());
            listPnl.add(list);
            indicationLbl.setText("Liste des Clients :");
            revalidate();
            listPnl.repaint();
            indicationPnl.repaint();
        });
        listCommand.addActionListener(e -> {
            listPnl.removeAll();
            ArrayList<String> liCommande = new ArrayList<>();
            for(Commande commande : Videotheque.getInstance().getListCommande()){
                liCommande.add(commande.getCommandeId() +
                        " | " +
                        commande.getClient().getNom() +
                        " " +
                        commande.getClient().getPrenom());
            }
            JList list = new JList(liCommande.toArray());
            listPnl.add(list);
            indicationLbl.setText("Liste des Commandes :");
            revalidate();
            listPnl.repaint();
            indicationPnl.repaint();
        });
        listProduct.addActionListener(e -> {
            listPnl.removeAll();
            ArrayList<String> produit = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : Videotheque.getInstance().getListStockProduit().entrySet()) {
                produit.add((entry.getKey() + " | " + entry.getValue()));
            }
            JList list = new JList(produit.toArray());
            listPnl.add(list);
            indicationLbl.setText("Liste des Produits :");
            revalidate();
            listPnl.repaint();
            indicationPnl.repaint();
        });

        listEmpty.addActionListener(e -> {
            listPnl.removeAll();
            indicationLbl.setText("");
            revalidate();
            listPnl.repaint();
            indicationPnl.repaint();
        });

        quit.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        setJMenuBar(menuBar);

        //data panel -----------------------------------------------------------------------------
        dataPnl.add(listPnl, BorderLayout.SOUTH);
        dataPnl.add(indicationLbl, BorderLayout.NORTH);

        //indication panel ------------------------------------------------------------------------
        indicationPnl.add(indicationLbl);
        indicationLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        indicationLbl.setAlignmentY(Component.CENTER_ALIGNMENT);
        indicationPnl.setPreferredSize(new Dimension(700,100));
        indicationPnl.setBorder(BorderFactory.createLineBorder(Color.black));

        //panel list ------------------------------------------------------------------------------
        listPnl.setBackground(Color.white);
        listPnl.setPreferredSize(new Dimension(700, 500));

        //panel buttons (workplace)----------------------------------------------------------------
        //buttons to create customers, orders and products, they are stocked in a gridLayout
        addClientBtn.setBackground(Color.white);
        addClientBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addClientBtn.setPreferredSize(new Dimension(140, 30));
        addOrderBtn.setBackground(Color.white);
        addOrderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addOrderBtn.setPreferredSize(new Dimension(140, 30));
        addProductBtn.setBackground(Color.white);
        addProductBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductBtn.setPreferredSize(new Dimension(140, 20));
        addQtyProductBtn.setBackground(Color.white);
        addQtyProductBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addQtyProductBtn.setPreferredSize(new Dimension(140, 20));
        //panel
        workPlacePnl.setBackground(Color.darkGray);
        workPlacePnl.setPreferredSize(new Dimension(200, 600));
        //workPlacePnl.setLayout(new BoxLayout(workPlacePnl, BoxLayout.PAGE_AXIS));
        workPlacePnl.setLayout(new GridLayout(8, 1, 0, 5));
        workPlacePnl.add(addClientBtn);
        workPlacePnl.add(addOrderBtn);
        workPlacePnl.add(addProductBtn);
        workPlacePnl.add(addQtyProductBtn);

        //panel display----------------------------------------------------------------------------
        // I can display only one panel then all the other panels are stocked in displayPnl
        displayPnl.setBackground(Color.WHITE);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(workPlacePnl, BorderLayout.WEST);
        displayPnl.add(dataPnl, BorderLayout.EAST);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }
}