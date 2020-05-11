package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workPlacePnl = new JPanel();// panel with a list of all customers registered

    private JButton addClientBtn = new JButton("Ajouter un client");
    private JButton addOrderBtn = new JButton("Ajouter une Commande");
    private JLabel AppLbl = new JLabel("Vidéothèque");
    private JLabel ClientLbl = new JLabel("client");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu Add = new JMenu("Ajouter");
    private JMenu AddUser = new JMenu("ajouter un client");
    private JMenu AddCommand = new JMenu("ajouter une commande");
    private JMenu AddProduct = new JMenu("ajouter un produit");
    private JMenu Li = new JMenu("Liste");
    private JMenu listUser = new JMenu("liste des clients");
    private JMenu listCommand = new JMenu("liste des commandes");
    private JMenu listProduct = new JMenu("liste des produits");
    private JMenu Help = new JMenu("Aide");
    private JRadioButtonMenuItem Quit = new JRadioButtonMenuItem("Quitter");


    public Videotheque application;

// Jtextfield, Jcheckbox

    public Fenetre() {

        setTitle("Application");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application = new Videotheque();

        addClientBtn.addActionListener(e -> new CreateClient(application));
        addOrderBtn.addActionListener(e -> new CreateOrder(application));

        Font arial = new Font("arial", Font.BOLD, 18);
        AppLbl.setFont(arial);

        //panel Workplace
        workPlacePnl.add(ClientLbl);

        //panel title
        titlePnl.setBackground(Color.LIGHT_GRAY);
        titlePnl.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        titlePnl.add(AppLbl);

        //items menu
        menuBar.add(Add);
        menuBar.add(Li);
        menuBar.add(Help);
        menuBar.add(Quit);

        Add.add(AddUser);
        Add.add(AddCommand);
        Add.add(AddProduct);

        Li.add(listUser);
        Li.add(listCommand);
        Li.add(listProduct);

        Quit.addActionListener(e -> this.dispose());

        this.setJMenuBar(menuBar);


        //panel buttons


        //panel display
        displayPnl.setBackground(Color.WHITE);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(addClientBtn, BorderLayout.SOUTH);
        displayPnl.add(workPlacePnl, BorderLayout.CENTER);

        // set visible
        setContentPane(displayPnl);
        //this.setContentPane(new Panel());
        setVisible(true);
    }
}