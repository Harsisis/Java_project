package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.*;
import com.github.harsisis.videotheque.util.ValidatorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WindowOrder extends JFrame {

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
    private JLabel choiceEmpLbl = new JLabel("Choisir un emprunt :");
    private JLabel choiceClLbl = new JLabel("Choisir un client :");
    private JLabel durationLbl = new JLabel("Saisir une durée (en jour) :");
    private JLabel amountLbl = new JLabel("Total :");

    private JButton plusProductBtn = new JButton("+");
    private JButton minusProductBtn = new JButton("-");
    private JButton confirmOrderBtn = new JButton("Valider");
    private JButton cancelOrderBtn = new JButton("Annuler");
    private JButton confirmProductBtn = new JButton("Ajouter");
    private JButton cancelProductBtn = new JButton("Retour");
    private JButton confirmDelBtn = new JButton("Supprimer");
    private JButton cancelDelBtn = new JButton("Annuler");

    private JTextField durationJtf = new JTextField();

    private JScrollPane scrollPane;

    private JOptionPane jop3 = new JOptionPane();

    public WindowOrder() {
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
        DefaultTableModel modelCommande = new DefaultTableModel();

        JTable tableCommande = new JTable(modelCommande);

        ArrayList<Emprunt> emprunts = new ArrayList<>();

        JComboBox<String> liEmpruntJcbx = new JComboBox<>();

        DefaultTableModel modelEmprunt = new DefaultTableModel();

        JTable tableEmprunt = new JTable(modelEmprunt);

        defineEmpruntTable(modelEmprunt);
        defineCommandeTable(modelCommande, tableCommande);

        tableCommande.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int lsm = ListSelectionModel.SINGLE_SELECTION;
                double total = 0;
                boolean isAdjusting = e.getValueIsAdjusting();

                //on est trop con, la liste emprunts est vide est on la rempli pas, forcément que ca n'affiche rien

                if(!isAdjusting){

                    Client client = (Client) tableCommande.getValueAt(tableCommande.getSelectedRow(),1);
                    double coefPrix = client.isFidele() ? 1 - Videotheque.REDUC_FIDELE : 1;


                    for (Emprunt emprunt : emprunts) {
                        System.out.println("tota1l"+trouverProdId(emprunt.getProduitId(), (Iterable<Produit>) Videotheque.getInstance().getListStockProduit().keySet().iterator()).getTarifJournalier());
                        System.out.println("total2"+emprunt.getDureeLocation());
                        System.out.println("total3"+coefPrix);
                        total += trouverProdId(emprunt.getProduitId(), (Iterable<Produit>) Videotheque.getInstance().getListStockProduit().keySet().iterator()).getTarifJournalier() * emprunt.getDureeLocation() * coefPrix;
                    }
                    System.out.println("total"+total);
                    amountLbl.setText("Total : " + total);
                    amountLbl.setVisible(true);
                }
            }
        });

        //buttons on the main page
        cancelOrderBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        confirmOrderBtn.addActionListener(e -> {
            Videotheque.getInstance().ajoutCommande((Client) liClientJcbx.getSelectedItem(), emprunts);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        //buttons on the adding loaning page
        plusProductBtn.addActionListener(e -> {
            addParameter(liProductJcbx);
            //createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
        });

        cancelProductBtn.addActionListener(e -> mainPage(liClientJcbx, modelCommande, tableCommande, modelEmprunt));

        confirmProductBtn.addActionListener(e -> {
            if (ValidatorUtil.isValidInteger(durationJtf.getText())) {//vérifier si il y a du stock
                    emprunts.add(new Emprunt((String) liProductJcbx.getSelectedItem(), Integer.parseInt(durationJtf.getText())));
               //createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);

                //liste produit panel-----------------------------------------------------------------------
                JOptionPane.showMessageDialog(this, "Le produit " +
                        liProductJcbx.getSelectedItem().toString() +
                        " a bien été ajouté à la liste des emprunts.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                durationJtf.setText("");

                for(Emprunt emp : emprunts) {
                    //modelEmprunt.addRow(emp.getEmpruntId(), emp.getProduit(), emp.getFinLocation(now()));
                }
            }
            else {
                jop3.showMessageDialog(null, "Veuillez saisir une durée valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        //buttons on the delete loaning page
        minusProductBtn.addActionListener(e -> {
            for (Emprunt emp : emprunts) {
                    liEmpruntJcbx.addItem(emp.getProduitId());
            }
            emprunts.remove(liEmpruntJcbx.getSelectedObjects());
            //for (Emprunt emp : emprunts) {
            //    liEmpruntJcbx.addItem(emp.getEmpruntStr());       ne pas oublier de mettre le type de la comboBox en String
            //                                                      "AWT-EventQueue-0" java.lang.NullPointerException
            //	                                                    at com.github.harsisis.videotheque.domaine.Emprunt.getEmpruntStr(Emprunt.java:26)
            //	                                                    at com.github.harsisis.videotheque.iu.WindowOrder.removeParameter(WindowOrder.java:286)
            //	                                                    at com.github.harsisis.videotheque.iu.WindowOrder.lambda$new$5(WindowOrder.java:130)
            //}
            removeParameter(liEmpruntJcbx);
        });

        cancelDelBtn.addActionListener(e -> {
            mainPage(liClientJcbx, modelCommande, tableCommande, modelEmprunt);
            createCommandeTable(modelCommande, tableCommande);
        });

        //display panel-----------------------------------------------------------------------------
        mainPage(liClientJcbx, modelCommande, tableCommande, modelEmprunt);
        displayPnl.setBackground(Color.white);
        displayPnl.setOpaque(true);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(managePnl, BorderLayout.WEST);
        displayPnl.add(workplacePnl, BorderLayout.EAST);

        listProdPnl.setPreferredSize(new Dimension(800,500));
        totalPnl.setPreferredSize(new Dimension(800,100));
        totalPnl.add(amountLbl, BorderLayout.EAST);
        amountLbl.setPreferredSize(new Dimension(800,0));
        amountLbl.setVisible(false);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }

    public static Produit trouverProdId(String id, Iterable<Produit> liste) {
        for(Produit prod : liste)
            if (prod.getProduitId().equals(id))
                return prod;
        return null;
    }

    private void defineEmpruntTable(DefaultTableModel modelEmprunt) {
        modelEmprunt.addColumn("ID emprunt");
        modelEmprunt.addColumn("Produit");
        modelEmprunt.addColumn("Durée");
    }

    private void createEmpruntTable(DefaultTableModel modelEmprunt, JTable tableEmprunt, ArrayList<Emprunt> emprunts) {
        listProdPnl.removeAll();
        for (Emprunt emp : emprunts) {
            modelEmprunt.addRow(new Object[]{emp.getEmpruntId(), emp.getProduitId().toString(), emp.getDureeLocation()});
        }
        scrollPane = new JScrollPane(tableEmprunt);
        scrollPane.setPreferredSize(new Dimension(750,450));
        tableEmprunt.setFillsViewportHeight(true);
        listProdPnl.add(scrollPane);
        revalidate();
        listProdPnl.repaint();
    }

    public void mainPage(JComboBox liClientJcbx, DefaultTableModel modelCommande, JTable tableCommande, DefaultTableModel modelEmprunt){

        createCommandeTable(modelCommande, tableCommande);
        //defineEmpruntTable(modelEmprunt);

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
        listProdPnl.setBackground(Color.white);

        //total panel-------------------------------------------------------------------------------
        totalPnl.setLayout(new BorderLayout());
        totalPnl.setBackground(Color.DARK_GRAY);
        amountLbl.setForeground(Color.white);

        //manage panel left side of the model-------------------------------------------------------
        managePnl.removeAll();
        managePnl.add(titlePnl);
        managePnl.add(selectPnl);
        managePnl.add(addDelPnl);
        managePnl.add(confirmPnl);

        revalidate();
        managePnl.repaint();
        listProdPnl.repaint();
    }

    public void addParameter(JComboBox liProductJcbx){

        listProdPnl.removeAll();

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
        managePnl.add(selectPnl);
        managePnl.add(selectProductPnl);
        managePnl.add(selectTimePnl);
        managePnl.add(confirmPlusPnl);

        revalidate();
        listProdPnl.repaint();
        managePnl.repaint();

    }

    public void removeParameter(JComboBox liEmpruntJcbx){
        managePnl.removeAll();
        listProdPnl.removeAll();

        managePnl.add(titlePnl);
        managePnl.add(selectLoaningPnl);
        managePnl.add(confirmMinusPnl);

        //select loaning panel-----------------------------------------------------------------------

        selectLoaningPnl.setBackground(Color.white);
        selectLoaningPnl.setLayout(new GridLayout(2,1, 5,0));
        selectLoaningPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        selectLoaningPnl.add(choiceEmpLbl);
        selectLoaningPnl.add(liEmpruntJcbx);
        liEmpruntJcbx.setBackground(Color.white);

        //confirm minus panel------------------------------------------------------------------------

        confirmMinusPnl.setBackground(Color.white);
        confirmMinusPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        confirmMinusPnl.add(confirmDelBtn);
        confirmMinusPnl.add(cancelDelBtn);
        confirmDelBtn.setBackground(Color.white);
        cancelDelBtn.setBackground(Color.white);

        revalidate();
        listProdPnl.repaint();
        managePnl.repaint();

    }

    private void defineCommandeTable(DefaultTableModel modelCommande, JTable tableCommande) {
        modelCommande.addColumn("ID commande");
        modelCommande.addColumn("Client");
        modelCommande.addColumn("Date de début de la commande");

        TableColumn column = null;
        for(int i = 0; i<=2; i++){
            column = tableCommande.getColumnModel().getColumn(i);
            if (i == 0){
                column.setPreferredWidth(300);//column ID
            }
            else if(i == 1){
                column.setPreferredWidth(500);//column Client
            }
            else {
                column.setPreferredWidth(100);
            }
        }
    }
    private void createCommandeTable(DefaultTableModel modelCommande, JTable tableCommande) {
        listProdPnl.removeAll();
        modelCommande.setRowCount(0);//clear the table
        for (Commande commande : Videotheque.getInstance().getListCommande()) {
            modelCommande.addRow(new Object[]{commande.getCommandeId(), commande.getClient(), commande.getDebutDate()});
        }
        scrollPane = new JScrollPane(tableCommande);
        scrollPane.setPreferredSize(new Dimension(750,450));
        tableCommande.setFillsViewportHeight(true);
        listProdPnl.add(scrollPane);
        revalidate();
        listProdPnl.repaint();
    }

}
