package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class CreateHome extends JFrame{
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel workPlacePnl = new JPanel();// panel with a list of all customers registered
    private JPanel listPnl = new JPanel();// panel where customers, products or orders list are

    private JButton addClientBtn = new JButton("Ajouter un client");
    private JButton addOrderBtn = new JButton("Ajouter une commande");
    private JButton addProductBtn = new JButton("Ajouter un produit");
    private JButton addQtyProductBtn = new JButton("Ajouter du stock");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu Li = new JMenu("Liste");
    private JRadioButtonMenuItem listUser = new JRadioButtonMenuItem("liste des clients");
    private JRadioButtonMenuItem listCommand = new JRadioButtonMenuItem("liste des commandes");
    private JRadioButtonMenuItem listProduct = new JRadioButtonMenuItem("liste des produits");
    private JMenu Help = new JMenu("Aide");
    private JRadioButtonMenuItem Quit = new JRadioButtonMenuItem("Quitter");
    public Videotheque videothq = Videotheque.getInstance();


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
        menuBar.add(Li);//add Li item to menu
        menuBar.add(Help);

        Help.add(Quit);

        Li.add(listUser);
        Li.add(listCommand);
        Li.add(listProduct);

        //don't delete, it should be define when we are able to display list from videotheque
        //listUser.addActionListener(e -> );
        //listCommand.addActionListener(e -> );
        //listProduct.addActionListener(e -> );

        Quit.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        setJMenuBar(menuBar);


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

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }
}