package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Commande;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CreateHome extends JFrame{
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel workPlacePnl = new JPanel();// panel with a list of all customers registered
    private JPanel listPnl = new JPanel();// panel where customers, products or orders list are

    private JLabel listLbl = new JLabel(); // Label where i write all

    private JButton addClientBtn = new JButton("Ajouter un client");
    private JButton addOrderBtn = new JButton("Ajouter une commande");
    private JButton addProductBtn = new JButton("Ajouter un produit");
    private JButton addQtyProductBtn = new JButton("Ajouter du stock");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu li = new JMenu("Liste");
    private JMenuItem listUser = new JMenuItem("liste des clients");
    private JMenuItem listCommand = new JMenuItem("liste des commandes");
    private JMenuItem listProduct = new JMenuItem("liste des produits");
    private JMenuItem listEmpty = new JMenuItem("vider le panneau");
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
            List<Client> clientList = new ArrayList<Client>(Videotheque.getInstance().getListClient());
            System.out.println(clientList);
            JList list = new JList(new Vector<Client>(clientList));
            listPnl.add(list);
            revalidate();
            listPnl.repaint();
        });
        listCommand.addActionListener(e -> {
            listPnl.removeAll();
            List<Commande> commandeList = new ArrayList<Commande>(Videotheque.getInstance().getListCommande());
            System.out.println(commandeList);
            JList list = new JList(new Vector<Commande>(commandeList));
            listPnl.add(list);
            revalidate();
            listPnl.repaint();
        });
        listProduct.addActionListener(e -> {
            listPnl.removeAll();
            listPnl.add(listLbl);
            listLbl.setText("");
            StringBuilder liProduct = new StringBuilder();
            liProduct.append("<html>");
            for (String produit : Videotheque.getInstance().getListStockProduit().keySet()) {// display quantity stock
                liProduct.append(listLbl.getText()).append("<br>").append(produit);
            }

            liProduct.append("</html>");
            listLbl.setText(liProduct.toString());
            revalidate();
            listPnl.repaint();
        });

        listEmpty.addActionListener(e -> {
            listPnl.removeAll();
            revalidate();
            listPnl.repaint();
        });

        quit.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        setJMenuBar(menuBar);


        //panel list ------------------------------------------------------------------------------
        listPnl.setBackground(Color.white);
        listPnl.setPreferredSize(new Dimension(760,600));

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
        workPlacePnl.setPreferredSize(new Dimension(180, 600));
        //workPlacePnl.setLayout(new BoxLayout(workPlacePnl, BoxLayout.PAGE_AXIS));
        workPlacePnl.setLayout(new GridLayout(8,1,0,5));
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