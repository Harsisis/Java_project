package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.*;
import com.github.harsisis.videotheque.util.ValidatorUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private JPanel modifyPnl = new JPanel();

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
    private JButton cancelOrderBtn = new JButton("Retour");
    private JButton confirmProductBtn = new JButton("Ajouter");
    private JButton cancelProductBtn = new JButton("Retour");
    private JButton confirmDelBtn = new JButton("Supprimer");
    private JButton cancelDelBtn = new JButton("Annuler");
    private JButton modifyCommandeBtn = new JButton("Modifier la commande");
    private JButton supCommandeBtn = new JButton("Supprimer la commande");

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
        managePnl.setPreferredSize(new Dimension(400, 600));
        managePnl.setBackground(Color.darkGray);
        managePnl.setLayout(new GridLayout(7, 1, 10, 10));
        confirmOrderBtn.setEnabled(false);
        modifyCommandeBtn.setEnabled(false);
        supCommandeBtn.setEnabled(false);

        JComboBox<Client> liClientJcbx = new JComboBox<>();
        for (Client client : Videotheque.getInstance().getListClient()) {
            liClientJcbx.addItem(client);
        }

        if(!Videotheque.getInstance().getListClient().isEmpty() && !Videotheque.getInstance().getListProduit().isEmpty()){
            confirmOrderBtn.setEnabled(true);
        }

        JComboBox<String> liProductJcbx = new JComboBox<>();
        for (String produit : Videotheque.getInstance().getListProduit().keySet()) {
            liProductJcbx.addItem(produit);
        }
        liProductJcbx.setRenderer(ComboBoxRenderer.createListRendererProduit());
        JComboBox<String> liEmpruntJcbx = new JComboBox<>();
        liEmpruntJcbx.setRenderer(ComboBoxRenderer.createListRendererProduit());
        ArrayList<Emprunt> emprunts = new ArrayList<>();

        DefaultTableModel modelCommande = new DefaultTableModel();
        JTable tableCommande = new JTable(modelCommande);
        defineCommandeTable(modelCommande, tableCommande);
        tableCommande.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel;
        cellSelectionModel = tableCommande.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel modelEmprunt = new DefaultTableModel();
        JTable tableEmprunt = new JTable(modelEmprunt);
        defineEmpruntTable(modelEmprunt, tableEmprunt);

        //buttons on the main page
        cancelOrderBtn.addActionListener(e -> {
            if(!emprunts.isEmpty()) {
                int reply = jop3.showConfirmDialog(null, "Êtes vous sûr de vouloir quitter ? Vous avez un emprunt non sauvegardé !", "Quitter", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
            }
            else {
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        });

        confirmOrderBtn.addActionListener(e -> {
            Videotheque.getInstance().ajoutCommande((Client) liClientJcbx.getSelectedItem(), emprunts);
            JOptionPane.showMessageDialog(this, "La commande a bien été ajouté à la liste des commandes",  "Succès", JOptionPane.INFORMATION_MESSAGE);
            createCommandeTable(modelCommande, tableCommande);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        cellSelectionModel.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                double total = getTotal(tableCommande);
                amountLbl.setText("Total : " + total + " €");
                amountLbl.setVisible(true);
                Commande commande = trouverCommande((String) tableCommande.getValueAt(tableCommande.getSelectedRow(),0));

                modifyCommandeBtn.addActionListener(actionEvent -> new WindowModify(commande));

                supCommandeBtn.addActionListener(actionEvent -> {
                    Videotheque.getInstance().supprimeCommande(commande);
                    JOptionPane.showMessageDialog(this, "La commande a bien été supprimé de la liste des commandes", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                });
                modifyCommandeBtn.setEnabled(true);
                supCommandeBtn.setEnabled(true);
                plusProductBtn.setEnabled(false);
                minusProductBtn.setEnabled(false);
                confirmOrderBtn.setEnabled(false);
            }
        });

        //buttons on the add loan page
        plusProductBtn.addActionListener(e -> {
            addParameter(liProductJcbx);
            createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
        });

        cancelProductBtn.addActionListener(e -> mainPage(liClientJcbx, modelCommande, tableCommande));

        confirmProductBtn.addActionListener(e -> {
            if (ValidatorUtil.isValidInteger(durationJtf.getText()) && produitEnStock((String) liProductJcbx.getSelectedItem())) {
                emprunts.add(new Emprunt((String) liProductJcbx.getSelectedItem(), Integer.parseInt(durationJtf.getText())));
                listProdPnl.repaint();
                createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);

                //liste produit panel-----------------------------------------------------------------------
                JOptionPane.showMessageDialog(this, "Le produit " +
                        Videotheque.getInstance().getProduit((String) liProductJcbx.getSelectedItem()).getProduitNom(Videotheque.getInstance().getProduit((String) liProductJcbx.getSelectedItem())) +
                        " a bien été ajouté à la liste des emprunts.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                durationJtf.setText("");
            } else {
                jop3.showMessageDialog(null, "Valeur invalide ou quantité insuffisante", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        });
        //buttons on the delete loaning page
        minusProductBtn.addActionListener(e -> {
            liEmpruntJcbx.removeAllItems();
            for (Emprunt emp : emprunts) {
                liEmpruntJcbx.addItem(emp.getProduitId());
            }
            removeParameter(liEmpruntJcbx);
            createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
        });
        cancelDelBtn.addActionListener(e -> {
            mainPage(liClientJcbx, modelCommande, tableCommande);
            revalidate();
            managePnl.repaint();
            listProdPnl.repaint();
        });

        confirmDelBtn.addActionListener(e -> {
            emprunts.remove(liEmpruntJcbx.getSelectedIndex());
            jop3.showMessageDialog(null, "Le produit a bien été supprimé de la commande", "Attention", JOptionPane.WARNING_MESSAGE);
            createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
            minusProductBtn.setEnabled(true);
            listProdPnl.repaint();
        });

        //display panel-----------------------------------------------------------------------------
        mainPage(liClientJcbx, modelCommande, tableCommande);
        displayPnl.setBackground(Color.white);
        displayPnl.setOpaque(true);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(managePnl, BorderLayout.WEST);
        displayPnl.add(workplacePnl, BorderLayout.EAST);

        amountLbl.setBorder(new EmptyBorder(0, 10, 0, 20));
        listProdPnl.setPreferredSize(new Dimension(800, 500));
        totalPnl.setPreferredSize(new Dimension(800, 100));
        totalPnl.add(amountLbl, BorderLayout.EAST);
        amountLbl.setPreferredSize(new Dimension(800, 0));
        amountLbl.setVisible(false);
        modifyCommandeBtn.setBackground(Color.white);
        modifyCommandeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyCommandeBtn.setPreferredSize(new Dimension(200, 30));
        modifyPnl.add(modifyCommandeBtn);
        modifyPnl.add(supCommandeBtn);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }


    double getTotal(JTable tableCommande) {
        Client client = (Client) tableCommande.getModel().getValueAt(tableCommande.getSelectedRow(), 1);
        String commandeId = (String) tableCommande.getModel().getValueAt(tableCommande.getSelectedRow(), 0);
        Commande commande = trouverCommande(commandeId);
        double coefPrix = client.isFidele() ? 1 - Videotheque.REDUC_FIDELE : 1;
        double total = 0;
        for (Emprunt emprunt : commande.getListEmprunt()) {
            double tarif = Videotheque.getInstance().getProduit(emprunt.getProduitId()).getTarifJournalier();
            int dureeLocation = emprunt.getDureeLocation();
            total += tarif * dureeLocation * coefPrix;
        }
        return total;
    }

    private boolean produitEnStock(String produitId) {
        return Videotheque.getInstance().getListStockProduit().get(produitId) > 0;
    }

    public Commande trouverCommande(String id) {
        for (Commande commande : Videotheque.getInstance().getListCommande()) {
            if (commande.getCommandeId().equals(id))
                return commande;
        }
        return null;
    }

    public void mainPage(JComboBox liClientJcbx, DefaultTableModel modelCommande, JTable tableCommande) {

        createCommandeTable(modelCommande, tableCommande);

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
        totalPnl.add(amountLbl, BorderLayout.EAST);
        amountLbl.setForeground(Color.white);

        //modify panel ----------------------------------------------------------------------------
        modifyPnl.setBackground(Color.white);
        modifyPnl.setBorder(BorderFactory.createLineBorder(Color.black));

        //manage panel left side of the model-------------------------------------------------------
        managePnl.removeAll();
        managePnl.add(titlePnl);
        managePnl.add(selectPnl);
        managePnl.add(addDelPnl);
        managePnl.add(confirmPnl);
        managePnl.add(modifyPnl);

        revalidate();
        managePnl.repaint();
        listProdPnl.repaint();
    }

    public void addParameter(JComboBox liProductJcbx) {

        listProdPnl.removeAll();
        amountLbl.setVisible(false);

        //select product panel----------------------------------------------------------------------
        selectProductPnl.setLayout(new GridLayout(2, 1));
        selectProductPnl.setBackground(Color.white);
        selectProductPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        selectProductPnl.add(choicePrdLbl);
        selectProductPnl.add(liProductJcbx);
        liProductJcbx.setBackground(Color.white);

        //select time panel----------------------------------------------------------------------
        selectTimePnl.setLayout(new GridLayout(2, 1));
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
        managePnl.repaint();
        listProdPnl.repaint();
    }

    public void removeParameter(JComboBox liEmpruntJcbx) {
        managePnl.removeAll();
        listProdPnl.removeAll();
        amountLbl.setVisible(false);

        managePnl.add(titlePnl);
        managePnl.add(selectLoaningPnl);
        managePnl.add(confirmMinusPnl);

        //select loaning panel-----------------------------------------------------------------------

        selectLoaningPnl.setBackground(Color.white);
        selectLoaningPnl.setLayout(new GridLayout(2, 1, 5, 0));
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
        managePnl.repaint();
        listProdPnl.repaint();
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
        listProdPnl.removeAll();
        modelCommande.setRowCount(0);
        for (Commande commande : Videotheque.getInstance().getListCommande()) {
            modelCommande.addRow(new Object[]{commande.getCommandeId(), commande.getClient(), commande.getDebutDate()});
        }
        scrollPane = new JScrollPane(tableCommande);
        scrollPane.setPreferredSize(new Dimension(750, 450));
        tableCommande.setFillsViewportHeight(true);
        listProdPnl.add(scrollPane);
        revalidate();
        listProdPnl.repaint();
    }

    private void defineEmpruntTable(DefaultTableModel modelEmprunt, JTable tableEmprunt) {
        modelEmprunt.addColumn("ID emprunt");
        modelEmprunt.addColumn("Produit");
        modelEmprunt.addColumn("Durée");

        tableEmprunt.getColumnModel().getColumn(0).setPreferredWidth(295);
        tableEmprunt.getColumnModel().getColumn(1).setPreferredWidth(400);
        tableEmprunt.getColumnModel().getColumn(2).setPreferredWidth(50);
    }

    private void createEmpruntTable(DefaultTableModel modelEmprunt, JTable tableEmprunt, ArrayList<Emprunt> emprunts) {
        listProdPnl.removeAll();
        modelEmprunt.setRowCount(0);
        for (Emprunt emp : emprunts) {
            modelEmprunt.addRow(new Object[]{emp.getEmpruntId(),
                    Videotheque.getInstance().getProduit(emp.getProduitId()).getProduitNom(Videotheque.getInstance().getProduit(emp.getProduitId())),
                    emp.getDureeLocation()});
        }
        scrollPane = new JScrollPane(tableEmprunt);
        scrollPane.setPreferredSize(new Dimension(750, 450));
        tableEmprunt.setFillsViewportHeight(true);
        listProdPnl.add(scrollPane);
        listProdPnl.setVisible(true);
        revalidate();
        listProdPnl.repaint();
    }

}