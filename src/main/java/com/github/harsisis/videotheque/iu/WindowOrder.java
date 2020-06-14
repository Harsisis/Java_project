package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Commande;
import com.github.harsisis.videotheque.domaine.Emprunt;
import com.github.harsisis.videotheque.domaine.Videotheque;
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
    private JPanel selectClientPnl = new JPanel();//panel with list to select a customer
    private JPanel selectProductPnl = new JPanel();//panel with a combo box of all the products
    private JPanel selectTimePnl = new JPanel();//panel to select the rental period
    private JPanel addDelPnl = new JPanel();// panel with add and delete button for product
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
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
    private JButton cancelBtn = new JButton("Retour");
    private JButton confirmProductBtn = new JButton("Ajouter");
    private JButton cancelProductBtn = new JButton("Retour");
    private JButton confirmDelBtn = new JButton("Supprimer");
    private JButton modifyCommandeBtn = new JButton("Voir la commande");
    private JButton supCommandeBtn = new JButton("Supprimer la commande");
    private JButton newCommandeBtn = new JButton("Nouvelle commande");

    private JTextField durationJtf = new JTextField();

    private JScrollPane scrollPane;

    private JOptionPane jop3 = new JOptionPane();

    public WindowOrder() {
        if (!Videotheque.getInstance().getListProduit().isEmpty() && !Videotheque.getInstance().getListClient().isEmpty()) {
            // set window settings --------------------------------------------------------------------
            defWindow();

            JComboBox<Client> liClientJcbx = getClientJComboBox();

            JComboBox<String> liProductJcbx = getProductJComboBox();

            ArrayList<Emprunt> emprunts = new ArrayList<>();

            JComboBox<String> liEmpruntJcbx = new JComboBox<>();

            DefaultTableModel modelEmprunt = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };

            JTable tableEmprunt = getEmpruntJTable(liEmpruntJcbx, modelEmprunt);

            DefaultTableModel modelCommande = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };
            JTable tableCommande = getCommandeJTable(modelCommande);
            ListSelectionModel cellSelectionModel = getCommandeListSelectionModel(tableCommande);

            //buttons on the main page
            cancelBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

            cellSelectionModel.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    double total = getTotal(tableCommande);
                    amountLbl.setText("Total : " + total + " €");
                    amountLbl.setVisible(true);
                    Commande commande = trouverCommande((String) tableCommande.getValueAt(tableCommande.getSelectedRow(), 0));

                    modifyCommandeBtn.addActionListener(actionEvent -> new WindowModify(commande));

                    supCommandeBtn.addActionListener(actionEvent -> supCommandeBtnAction(commande, modelCommande, tableCommande));

                    modifyCommandeBtn.setEnabled(true);
                    supCommandeBtn.setEnabled(true);
                }
            });

            newCommandeBtn.addActionListener(e -> newCommandeBtnAction(liClientJcbx, modelEmprunt, tableEmprunt, emprunts));

            cancelOrderBtn.addActionListener(e -> cancelOrderBtnAction(emprunts, modelCommande, tableCommande));

            confirmOrderBtn.addActionListener(e -> confirmOrderBtnAction(liClientJcbx, emprunts, modelCommande, tableCommande));

            //buttons on the add loan page
            cancelProductBtn.addActionListener(e -> commandePage(liClientJcbx));

            plusProductBtn.addActionListener(e -> plusProductBtnAction(liProductJcbx, modelEmprunt, tableEmprunt, emprunts));


            confirmProductBtn.addActionListener(e -> confirmProductBtnAction(liProductJcbx, modelEmprunt, tableEmprunt, emprunts));

            //buttons on the delete loaning page
            minusProductBtn.addActionListener(e -> minusProductBtnAction(liEmpruntJcbx, modelEmprunt, tableEmprunt, emprunts));

            confirmDelBtn.addActionListener(e -> confirmDelBtnAction(liEmpruntJcbx, modelEmprunt, tableEmprunt, emprunts));

            createCommandeTable(modelCommande, tableCommande);

            //display panel-----------------------------------------------------------------------------
            mainPage(modelCommande, tableCommande);
            // set visible------------------------------------------------------------------------------
            setContentPane(displayPnl);
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Impossible, aucun produit ni aucun client n'est enregistré", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void defWindow() {
        setTitle("Ajout d'une Commande");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        managePnl.setPreferredSize(new Dimension(400, 600));
        managePnl.setBackground(Color.darkGray);
        managePnl.setLayout(new GridLayout(7, 1, 10, 10));
        modifyCommandeBtn.setEnabled(false);
        supCommandeBtn.setEnabled(false);
    }

    private void confirmDelBtnAction(JComboBox<String> liEmpruntJcbx, DefaultTableModel modelEmprunt, JTable tableEmprunt, ArrayList<Emprunt> emprunts) {
        emprunts.remove(liEmpruntJcbx.getSelectedIndex());
        Videotheque.getInstance().ajoutStockProduit((String) liEmpruntJcbx.getSelectedItem(), 1);
        jop3.showMessageDialog(null, "Le produit a bien été supprimé de la commande", "Attention", JOptionPane.WARNING_MESSAGE);
        createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
        minusProductBtn.setEnabled(true);
        listProdPnl.repaint();
    }

    private void minusProductBtnAction(JComboBox<String> liEmpruntJcbx, DefaultTableModel modelEmprunt, JTable tableEmprunt, ArrayList<Emprunt> emprunts) {
        liEmpruntJcbx.removeAllItems();
        for (Emprunt emp : emprunts) {
            liEmpruntJcbx.addItem(emp.getProduitId());
        }
        deleteProductPage(liEmpruntJcbx);
        createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
    }

    private void confirmProductBtnAction(JComboBox<String> liProductJcbx, DefaultTableModel modelEmprunt, JTable tableEmprunt, ArrayList<Emprunt> emprunts) {
        if (ValidatorUtil.isValidInteger(durationJtf.getText()) && produitEnStock((String) liProductJcbx.getSelectedItem())) {
            emprunts.add(new Emprunt((String) liProductJcbx.getSelectedItem(), Integer.parseInt(durationJtf.getText())));
            Videotheque.getInstance().retirerStockProduit((String) liProductJcbx.getSelectedItem());
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
    }

    private void plusProductBtnAction(JComboBox<String> liProductJcbx, DefaultTableModel modelEmprunt, JTable tableEmprunt, ArrayList<Emprunt> emprunts) {
        addProductPage(liProductJcbx);
        createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
    }

    private void confirmOrderBtnAction(JComboBox<Client> liClientJcbx, ArrayList<Emprunt> emprunts, DefaultTableModel modelCommande, JTable tableCommande) {
        Videotheque.getInstance().ajoutCommande((Client) liClientJcbx.getSelectedItem(), emprunts);
        JOptionPane.showMessageDialog(this, "La commande a bien été ajouté à la liste des commandes", "Succès", JOptionPane.INFORMATION_MESSAGE);
        mainPage(modelCommande, tableCommande);
    }

    private void cancelOrderBtnAction(ArrayList<Emprunt> emprunts, DefaultTableModel modelCommande, JTable tableCommande) {
        if (!emprunts.isEmpty()) {
            int reply = jop3.showConfirmDialog(null, "Êtes vous sûr de vouloir quitter ? Vous avez un emprunt non sauvegardé !", "Quitter", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                mainPage(modelCommande, tableCommande);
            }
        } else {
            mainPage(modelCommande, tableCommande);
        }
    }

    private void newCommandeBtnAction(JComboBox<Client> liClientJcbx, DefaultTableModel modelEmprunt, JTable tableEmprunt, ArrayList<Emprunt> emprunts) {
        emprunts.clear();
        commandePage(liClientJcbx);
        createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
    }

    private void supCommandeBtnAction(Commande commande, DefaultTableModel modelCommande, JTable tableCommande) {
        Videotheque.getInstance().supprimeCommande(commande);
        for (Emprunt emprunt : commande.getListEmprunt()) {
            Videotheque.getInstance().ajoutStockProduit(emprunt.getProduitId(), 1);
        }
        createCommandeTable(modelCommande, tableCommande);
        JOptionPane.showMessageDialog(this, "La commande a bien été supprimé de la liste des commandes", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    private ListSelectionModel getCommandeListSelectionModel(JTable tableCommande) {
        ListSelectionModel cellSelectionModel;
        cellSelectionModel = tableCommande.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return cellSelectionModel;
    }

    private JTable getCommandeJTable(DefaultTableModel modelCommande) {
        JTable tableCommande = new JTable(modelCommande);
        defineCommandeTable(modelCommande, tableCommande);
        tableCommande.setCellSelectionEnabled(true);
        return tableCommande;
    }

    private JComboBox<String> getProductJComboBox() {
        JComboBox<String> liProductJcbx = new JComboBox<>();
        for (String produit : Videotheque.getInstance().getListProduit().keySet()) {
            liProductJcbx.addItem(produit);
        }
        liProductJcbx.setRenderer(ComboBoxRenderer.createListRendererProduit());
        return liProductJcbx;
    }

    private JTable getEmpruntJTable(JComboBox<String> liEmpruntJcbx, DefaultTableModel modelEmprunt) {
        JTable tableEmprunt = new JTable(modelEmprunt);
        defineEmpruntTable(modelEmprunt, tableEmprunt);
        liEmpruntJcbx.setRenderer(ComboBoxRenderer.createListRendererProduit());
        return tableEmprunt;
    }

    private JComboBox<Client> getClientJComboBox() {
        JComboBox<Client> liClientJcbx = new JComboBox<>();
        for (Client client : Videotheque.getInstance().getListClient()) {
            liClientJcbx.addItem(client);
        }
        return liClientJcbx;
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

    public void deleteProductPage(JComboBox liEmpruntJcbx) {

        amountLbl.setVisible(false);

        //select loaning panel-----------------------------------------------------------------------
        defSelectProductPnl();
        selectProductPnl.add(choiceEmpLbl);
        selectProductPnl.add(liEmpruntJcbx);
        liEmpruntJcbx.setBackground(Color.white);

        //confirm minus panel------------------------------------------------------------------------
        confirmPnl.removeAll();
        confirmPnl.add(confirmDelBtn);
        confirmPnl.add(cancelProductBtn);
        confirmOrderBtn.setBackground(Color.white);
        cancelOrderBtn.setBackground(Color.white);

        //manage panel left side of the model-------------------------------------------------------
        managePnl.removeAll();
        listProdPnl.removeAll();

        managePnl.add(titlePnl);
        managePnl.add(selectProductPnl);
        managePnl.add(confirmPnl);

        refreshPage();
    }

    public void addProductPage(JComboBox liProductJcbx) {
        //select product panel----------------------------------------------------------------------
        defSelectProductPnl();
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

        //confirm panel-----------------------------------------------------------------------------
        confirmPnl.removeAll();
        confirmPnl.add(confirmProductBtn);
        confirmPnl.add(cancelProductBtn);
        confirmOrderBtn.setBackground(Color.white);
        cancelOrderBtn.setBackground(Color.white);

        //manage panel ----------------------------------------------------------------------------
        managePnl.removeAll();
        listProdPnl.removeAll();

        managePnl.add(titlePnl);
        managePnl.add(selectProductPnl);
        managePnl.add(selectTimePnl);
        managePnl.add(confirmPnl);

        refreshPage();
    }

    private void refreshPage() {
        revalidate();
        managePnl.repaint();
        listProdPnl.repaint();
    }

    private void defSelectProductPnl() {
        selectProductPnl.removeAll();
        selectProductPnl.setLayout(new GridLayout(2, 1));
        selectProductPnl.setBackground(Color.white);
        selectProductPnl.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void commandePage(JComboBox liClientJcbx) {
        amountLbl.setVisible(false);

        //select panel------------------------------------------------------------------------------
        selectClientPnl.setLayout(new GridLayout(2, 1));
        selectClientPnl.setBackground(Color.white);
        selectClientPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        selectClientPnl.add(choiceClLbl);
        selectClientPnl.add(liClientJcbx);
        liClientJcbx.setBackground(Color.white);

        //addDel panel------------------------------------------------------------------------------
        addDelPnl.add(productAddLbl);
        addDelPnl.add(plusProductBtn);
        addDelPnl.add(minusProductBtn);
        addDelPnl.setBackground(Color.white);
        addDelPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        plusProductBtn.setBackground(Color.white);
        minusProductBtn.setBackground(Color.white);

        //confirm panel-----------------------------------------------------------------------------
        confirmPnl.removeAll();
        confirmPnl.add(confirmOrderBtn);
        confirmPnl.add(cancelOrderBtn);
        confirmOrderBtn.setBackground(Color.white);
        cancelOrderBtn.setBackground(Color.white);

        //manage panel left side of the model-------------------------------------------------------
        managePnl.removeAll();
        managePnl.add(titlePnl);
        managePnl.add(selectClientPnl);
        managePnl.add(addDelPnl);
        managePnl.add(confirmPnl);

        refreshPage();
    }


    public void mainPage(DefaultTableModel modelCommande, JTable tableCommande) {

        //title panel-------------------------------------------------------------------------------
        titlePnl.add(titleLbl);
        titlePnl.setBackground(Color.darkGray);
        titleLbl.setForeground(Color.white);

        //confirm panel-----------------------------------------------------------------------------
        confirmPnl.removeAll();
        confirmPnl.add(cancelBtn);
        confirmPnl.add(newCommandeBtn);
        confirmPnl.setBackground(Color.white);
        confirmPnl.setBorder(BorderFactory.createLineBorder(Color.black));
        newCommandeBtn.setBackground(Color.white);
        cancelBtn.setBackground(Color.white);

        //workplace panel---------------------------------------------------------------------------
        workplacePnl.setBackground(Color.white);
        workplacePnl.setLayout(new BorderLayout());
        workplacePnl.add(listProdPnl, BorderLayout.NORTH);
        workplacePnl.add(totalPnl, BorderLayout.SOUTH);

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

        //display panel ---------------------------------------------------------------------------
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
        modifyCommandeBtn.setPreferredSize(new Dimension(180, 30));

        supCommandeBtn.setBackground(Color.white);
        supCommandeBtn.setPreferredSize(new Dimension(180, 30));

        modifyPnl.add(modifyCommandeBtn);
        modifyPnl.add(supCommandeBtn);

        //manage panel left side of the model-------------------------------------------------------
        managePnl.removeAll();
        createCommandeTable(modelCommande, tableCommande);
        managePnl.add(titlePnl);
        managePnl.add(confirmPnl);
        managePnl.add(modifyPnl);

        refreshPage();
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
        try{
            modelCommande.setRowCount(0);
            ArrayList<Commande> list = new ArrayList<>(Videotheque.getInstance().getListCommande());
            Object rowData[] = new Object[3];
            for(int i = 0; i < Videotheque.getInstance().getListCommande().size(); i++) {
                rowData[0] = list.get(i).getCommandeId();
                rowData[1] = list.get(i).getClient();
                rowData[2] = list.get(i).getDebutDate();
                modelCommande.addRow(rowData);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
        }
//        for (Commande commande : Videotheque.getInstance().getListCommande()) {
//            modelCommande.addRow(new Object[]{commande.getCommandeId(), commande.getClient(), commande.getDebutDate()});
//        }
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