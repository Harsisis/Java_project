package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Commande;
import com.github.harsisis.videotheque.domaine.Emprunt;
import com.github.harsisis.videotheque.domaine.Videotheque;
import com.github.harsisis.videotheque.util.ValidatorUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class WindowModify extends JFrame {

    private JPanel displayPnl = new JPanel();
    private JPanel managePnl = new JPanel();
    private JPanel listProdPnl = new JPanel();
    private JPanel addDelPnl = new JPanel();
    private JPanel backPnl = new JPanel();
    private JPanel titlePnl = new JPanel();
    private JPanel selectProductPnl = new JPanel();
    private JPanel selectTimePnl = new JPanel();
    private JPanel confirmPlusPnl = new JPanel();
    private JPanel selectLoaningPnl = new JPanel();// panel with add and delete button for product
    private JPanel confirmMinusPnl = new JPanel();

    private JButton plusProductBtn = new JButton("+");
    private JButton minusProductBtn = new JButton("-");
    private JButton backProductBtn = new JButton("Retour");
    private JButton confirmProductBtn = new JButton("Valider");
    private JButton cancelProductBtn = new JButton("Retour");
    private JButton confirmDelBtn = new JButton("Supprimer");
    private JButton cancelDelBtn = new JButton("Retour");

    private JLabel titleLbl = new JLabel("Modification d'une commande");
    private JLabel choicePrdLbl = new JLabel("Choisir un produit :");
    private JLabel durationLbl = new JLabel("Saisir une durée (en jour) :");
    private JLabel choiceEmpLbl = new JLabel("Choisir un emprunt :");

    private JTextField durationJtf = new JTextField();

    private JScrollPane scrollPane;

    DefaultTableModel modelEmprunt = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    JTable tableEmprunt = new JTable(modelEmprunt);

    public WindowModify(Commande commande) {
        defWindow();

        Videotheque.getInstance().getListCommande().remove(commande);
        JComboBox<String> liProductJcbx = new JComboBox<>();
        for (String produit : Videotheque.getInstance().getListProduit().keySet()) {
            liProductJcbx.addItem(produit);
        }
        liProductJcbx.setRenderer(ComboBoxRenderer.createListRendererProduit());
        JComboBox<String> liEmpruntJcbx = new JComboBox<>();
        liEmpruntJcbx.setRenderer(ComboBoxRenderer.createListRendererEmprunt(commande));
        ArrayList<Emprunt> emprunts = new ArrayList<>(commande.getListEmprunt());

        defineEmpruntTable(modelEmprunt, tableEmprunt);
        backProductBtn.addActionListener(e -> {
            Videotheque.getInstance().ajoutCommande(commande.getClient(), emprunts);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        plusProductBtn.addActionListener(e -> addparameter(liProductJcbx));

        minusProductBtn.addActionListener(e -> minusProductBtnAction(liEmpruntJcbx, emprunts));

        cancelDelBtn.addActionListener(e -> mainPage(modelEmprunt, tableEmprunt, emprunts));

        cancelProductBtn.addActionListener(e -> mainPage(modelEmprunt, tableEmprunt, emprunts));

        confirmDelBtn.addActionListener(e -> confirmDelBtnAction(commande, liEmpruntJcbx, emprunts));

        confirmProductBtn.addActionListener(e -> confirmProductBtnAction(liProductJcbx, emprunts));

        mainPage(modelEmprunt, tableEmprunt, emprunts);


        displayPnl.setOpaque(true);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(managePnl, BorderLayout.WEST);
        displayPnl.add(listProdPnl, BorderLayout.EAST);

        setContentPane(displayPnl);
        setVisible(true);
    }

    private void confirmProductBtnAction(JComboBox<String> liProductJcbx, ArrayList<Emprunt> emprunts) {
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
            JOptionPane.showMessageDialog(null, "Valeur invalide ou quantité insuffisante", "Attention", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void confirmDelBtnAction(Commande commande, JComboBox<String> liEmpruntJcbx, ArrayList<Emprunt> emprunts) {
        emprunts.remove(trouverEmprunt((String) liEmpruntJcbx.getSelectedItem(),commande));
        Videotheque.getInstance().ajoutStockProduit((String) liEmpruntJcbx.getSelectedItem(), 1);
        JOptionPane.showMessageDialog(null, "Le produit a bien été supprimé de la commande", "Attention", JOptionPane.WARNING_MESSAGE);
        createEmpruntTable(modelEmprunt, tableEmprunt, emprunts);
        revalidate();
        listProdPnl.repaint();
    }

    private void minusProductBtnAction(JComboBox<String> liEmpruntJcbx, ArrayList<Emprunt> emprunts) {
        liEmpruntJcbx.removeAllItems();
        for (Emprunt emp : emprunts) {
            liEmpruntJcbx.addItem(emp.getEmpruntId());
        }
        removeParameter(liEmpruntJcbx);
    }

    private void defWindow() {
        setTitle("Modification d'une Commande");
        setSize(1200, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addDelPnl.add(plusProductBtn);
        addDelPnl.add(minusProductBtn);
        plusProductBtn.setBackground(Color.white);
        minusProductBtn.setBackground(Color.white);

        backPnl.add(backProductBtn);
        backProductBtn.setBackground(Color.white);

        managePnl.setPreferredSize(new Dimension(400, 500));
        listProdPnl.setPreferredSize(new Dimension(800, 500));
    }

    public Emprunt trouverEmprunt(String id, Commande commande) {
        for (Emprunt emprunt : commande.getListEmprunt()) {
            if (emprunt.getEmpruntId().equals(id))
                return emprunt;
        }
        return null;
    }
    private void removeParameter(JComboBox<String> liEmpruntJcbx) {
        managePnl.removeAll();

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

    private void addparameter(JComboBox<String> liProductJcbx) {
        managePnl.removeAll();

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
        managePnl.add(selectProductPnl);
        managePnl.add(selectTimePnl);
        managePnl.add(confirmPlusPnl);

        revalidate();
        managePnl.repaint();
        listProdPnl.repaint();
    }

    private void mainPage(DefaultTableModel modelEmprunt, JTable tableEmprunt, ArrayList<Emprunt> emprunts) {
        listProdPnl.setBackground(Color.white);

        titlePnl.add(titleLbl);
        titlePnl.setBackground(Color.darkGray);
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLbl.setAlignmentY(Component.CENTER_ALIGNMENT);
        titleLbl.setForeground(Color.white);

        addDelPnl.setPreferredSize(new Dimension(200, 500));
        addDelPnl.setBackground(Color.white);
        addDelPnl.setBorder(BorderFactory.createLineBorder(Color.black));

        backPnl.setPreferredSize(new Dimension(200, 500));
        backPnl.setBackground(Color.white);
        backPnl.setBorder(BorderFactory.createLineBorder(Color.black));

        managePnl.setBackground(Color.darkGray);
        managePnl.setLayout(new GridLayout(7, 1, 10, 10));

        managePnl.removeAll();
        listProdPnl.removeAll();
        createEmpruntTable(modelEmprunt, tableEmprunt,emprunts);
        managePnl.add(titlePnl);
        managePnl.add(addDelPnl);
        managePnl.add(backPnl);

        revalidate();
        managePnl.repaint();
        listProdPnl.repaint();
    }

    private void defineEmpruntTable(DefaultTableModel modelEmprunt, JTable tableEmprunt) {
        modelEmprunt.addColumn("ID emprunt");
        modelEmprunt.addColumn("Produit");
        modelEmprunt.addColumn("Durée");

        tableEmprunt.getColumnModel().getColumn(0).setPreferredWidth(300);
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

    private boolean produitEnStock(String produitId) {
        return Videotheque.getInstance().getListStockProduit().get(produitId) > 0;
    }

}
