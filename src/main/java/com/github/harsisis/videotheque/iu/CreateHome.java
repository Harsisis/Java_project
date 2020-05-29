package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Commande;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

public class CreateHome extends JFrame {
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel workPlacePnl = new JPanel();// panel with a list of all customers, orders and products registered
    private JPanel listPnl = new JPanel();// panel where customers, products or orders list are

    private JLabel indicationLbl = new JLabel(""); // Label where i write all

    private JButton addClientBtn = new JButton("Ajouter un client");
    private JButton addOrderBtn = new JButton("Ajouter une commande");
    private JButton addProductBtn = new JButton("Ajouter un produit");
    private JButton addQtyProductBtn = new JButton("Ajouter du stock");

    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem listUser = new JMenuItem("Liste des clients");
    private JMenuItem listCommand = new JMenuItem("Liste des commandes");
    private JMenuItem listProduct = new JMenuItem("Liste des produits");
    private JMenuItem listEmpty = new JMenuItem("Vider le panneau");
    private JMenuItem quit = new JMenuItem("Quitter");

    private JScrollPane scrollPane;


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
        menuBar.add(listUser);
        menuBar.add(listCommand);
        menuBar.add(listProduct);
        menuBar.add(listEmpty);
        menuBar.add(quit);


        //Table definition ---------------------------------------------------------------------------
        DefaultTableModel modelUser = new DefaultTableModel();
        JTable tableUser = new JTable(modelUser);

        modelUser.addColumn("ID utilisateur");
        modelUser.addColumn("Nom");
        modelUser.addColumn("Prénom");
        modelUser.addColumn("Fidèle");

        TableColumn column = null;
        for(int i = 0; i<=3; i++){
            column = tableUser.getColumnModel().getColumn(i);
            if (i == 0){
                column.setPreferredWidth(400);//column ID
            }
            else if(i == 3){
                column.setPreferredWidth(100);//column fidele
            }
            else {
                column.setPreferredWidth(150);
            }
        }

        listUser.addActionListener(e -> {
            listPnl.removeAll();
            //ArrayList<Client> clientList = new ArrayList<>(Videotheque.getInstance().getListClient());
            //System.out.println(clientList);

            for (Client client : Videotheque.getInstance().getListClient()) {
                modelUser.addRow(new Object[]{client.getClientId(), client.getNom(), client.getPrenom(), client.isFidele()});
            }
            scrollPane = new JScrollPane(tableUser);
            tableUser.setFillsViewportHeight(true);

            listPnl.add(scrollPane);
            indicationLbl.setText("Liste des Clients :");
            revalidate();
            listPnl.repaint();
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
        });

        listEmpty.addActionListener(e -> {
            listPnl.removeAll();
            indicationLbl.setText("");
            revalidate();
            listPnl.repaint();
        });

        quit.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        setJMenuBar(menuBar);

        //panel list or scroll pane ----------------------------------------------------------------
        listPnl.setBackground(Color.white);
        listPnl.setPreferredSize(new Dimension(600, 600));

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

        indicationLbl.setForeground(Color.white);
        //panel
        workPlacePnl.setBackground(Color.darkGray);
        workPlacePnl.setPreferredSize(new Dimension(200, 600));
        workPlacePnl.setLayout(new GridLayout(8, 1, 0, 5));
        workPlacePnl.add(indicationLbl);
        workPlacePnl.add(addClientBtn);
        workPlacePnl.add(addOrderBtn);
        workPlacePnl.add(addProductBtn);
        workPlacePnl.add(addQtyProductBtn);

        //panel display----------------------------------------------------------------------------
        // I can display only one panel then all the other panels are stocked in displayPnl
        displayPnl.setBackground(Color.WHITE);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(workPlacePnl, BorderLayout.WEST);
        displayPnl.add(listPnl, BorderLayout.EAST);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }
}