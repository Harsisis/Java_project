package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WindowHome extends JFrame {
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

    private JOptionPane jop3 = new JOptionPane();

    public WindowHome() {

        // set window settings --------------------------------------------------------------------
        setTitle("Application");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addClientBtn.addActionListener(e -> new WindowClient());//add procedure to addClientBtn
        addOrderBtn.addActionListener(e -> new WindowOrder());
        addProductBtn.addActionListener(e -> new WindowProduct());
        addQtyProductBtn.addActionListener(e -> new WindowStock());


        //items menu ------------------------------------------------------------------------------
        //create a menu with tables
        menuBar.add(listUser);
        menuBar.add(listCommand);
        menuBar.add(listProduct);
        menuBar.add(listEmpty);
        menuBar.add(quit);

        //Table definition ---------------------------------------------------------------------------
        DefaultTableModel modelClient = new DefaultTableModel();
        JTable tableClient = new JTable(modelClient);
        tableClient.setAutoCreateRowSorter(true);

        DefaultTableModel modelCommande = new DefaultTableModel();
        JTable tableCommande = new JTable(modelCommande);

        DefaultTableModel modelProduit = new DefaultTableModel();
        JTable tableProduit = new JTable(modelProduit);
        tableProduit.setAutoCreateRowSorter(true);

        ListSelectionModel cellSelectionModel;
        tableCommande.setCellSelectionEnabled(true);
        cellSelectionModel = tableCommande.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        defineClientTable(modelClient, tableClient);

        defineCommandeTable(modelCommande, tableCommande);

        defineProduitTable(modelProduit, tableProduit);

        listUser.addActionListener(e -> {
            createClientTable(modelClient, tableClient);
        });

        listCommand.addActionListener(e -> {
            createCommandeTable(modelCommande, tableCommande);
        });

        listProduct.addActionListener(e -> {
            createProduitTable(modelProduit, tableProduit);
        });

        listEmpty.addActionListener(e -> {
            listPnl.removeAll();
            indicationLbl.setText("");
            revalidate();
            listPnl.repaint();
        });

        quit.addActionListener(e -> {
            int reply = jop3.showConfirmDialog(null, "Êtes vous sûr de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        });

        setJMenuBar(menuBar);

        //panel list ------------------------------------------------------------------------------
        listPnl.setBackground(Color.white);
        listPnl.setPreferredSize(new Dimension(750, 600));

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

    private void defineClientTable(DefaultTableModel modelClient, JTable tableClient) {
        modelClient.addColumn("ID client");
        modelClient.addColumn("Nom");
        modelClient.addColumn("Prénom");
        modelClient.addColumn("Fidèle");

        TableColumn column = null;
        for (int i = 0; i <= 3; i++) {
            column = tableClient.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(400);//column ID
            } else if (i == 3) {
                column.setPreferredWidth(100);//column fidele
            } else {
                column.setPreferredWidth(150);//columns nom and prenom
            }
        }
    }

    private void createClientTable(DefaultTableModel modelClient, JTable tableClient) {
        listPnl.removeAll();
        modelClient.setRowCount(0);//clear the table
        for (Client client : Videotheque.getInstance().getListClient()) {
            modelClient.addRow(new Object[]{client.getClientId(), client.getNom(), client.getPrenom(), client.isFidele()});
        }
        scrollPane = new JScrollPane(tableClient);
        scrollPane.setPreferredSize(new Dimension(720, 500));
        tableClient.setFillsViewportHeight(true);
        listPnl.add(scrollPane);
        indicationLbl.setText("Liste des Clients :");
        revalidate();
        listPnl.repaint();
    }

    private void defineCommandeTable(DefaultTableModel modelCommande, JTable tableCommande) {
        modelCommande.addColumn("ID commande");
        modelCommande.addColumn("Client");
        modelCommande.addColumn("Date de début de la commande");

        TableColumn column = null;
        for (int i = 0; i <= 2; i++) {
            column = tableCommande.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(300);//column ID
            } else if (i == 1) {
                column.setPreferredWidth(500);//column Client
            } else {
                column.setPreferredWidth(100);
            }
        }
    }

    private void createCommandeTable(DefaultTableModel modelCommande, JTable tableCommande) {
        listPnl.removeAll();
        modelCommande.setRowCount(0);
        for (Commande commande : Videotheque.getInstance().getListCommande()) {
            modelCommande.addRow(new Object[]{commande.getCommandeId(), commande.getClient(), commande.getDebutDate()});
        }
        scrollPane = new JScrollPane(tableCommande);
        scrollPane.setPreferredSize(new Dimension(720, 500));
        tableCommande.setFillsViewportHeight(true);
        listPnl.add(scrollPane);
        indicationLbl.setText("Liste des Commandes :");
        revalidate();
        listPnl.repaint();
    }

    private void defineProduitTable(DefaultTableModel modelProduit, JTable tableProduit) {
        modelProduit.addColumn("Produit");
        modelProduit.addColumn("Quantité");

        tableProduit.getColumnModel().getColumn(0).setPreferredWidth(650);
        tableProduit.getColumnModel().getColumn(1).setPreferredWidth(150);
    }

    private void createProduitTable(DefaultTableModel modelProduit, JTable tableProduit) {
        listPnl.removeAll();
        modelProduit.setRowCount(0);
        for (String produit : Videotheque.getInstance().getListStockProduit().keySet()) {
            Produit prod = Videotheque.getInstance().getProduit(produit);
            modelProduit.addRow(new Object[]{prod.getProduitNom(prod), Videotheque.getInstance().getListStockProduit().get(produit)});
        }

        scrollPane = new JScrollPane(tableProduit);
        scrollPane.setPreferredSize(new Dimension(720, 500));
        tableProduit.setFillsViewportHeight(true);
        listPnl.add(scrollPane);
        indicationLbl.setText("Liste des Produits :");
        revalidate();
        listPnl.repaint();
    }
}