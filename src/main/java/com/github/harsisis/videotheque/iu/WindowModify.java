package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Commande;
import com.github.harsisis.videotheque.domaine.Emprunt;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class WindowModify extends JFrame {

    private JPanel displayPnl = new JPanel();
    private JPanel managePnl = new JPanel();
    private JPanel listProdPnl = new JPanel();
    private JPanel addDelPnl = new JPanel();
    private JPanel backPnl = new JPanel();

    private JButton plusProductBtn = new JButton("+");
    private JButton minusProductBtn = new JButton("-");
    private JButton backProductBtn = new JButton("Retour");

    private JScrollPane scrollPane;

    DefaultTableModel modelEmprunt = new DefaultTableModel();
    JTable tableEmprunt = new JTable(modelEmprunt);

    public WindowModify(Commande commande) {
        setTitle("Modification d'une Commande");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        addDelPnl.add(plusProductBtn);
        addDelPnl.add(minusProductBtn);
        plusProductBtn.setBackground(Color.white);
        minusProductBtn.setBackground(Color.white);

        backPnl.add(backProductBtn);
        backProductBtn.setBackground(Color.white);

        mainPage(commande);
        defineEmpruntTable(modelEmprunt, tableEmprunt);

        displayPnl.add(managePnl, BorderLayout.WEST);
        displayPnl.add(listProdPnl, BorderLayout.EAST);

        setContentPane(displayPnl);
        setVisible(true);
    }

    private void mainPage(Commande commande) {
        listProdPnl.removeAll();
        managePnl.removeAll();

        listProdPnl.setPreferredSize(new Dimension(200, 500));
        listProdPnl.setBackground(Color.white);

        addDelPnl.setPreferredSize(new Dimension(200, 500));
        addDelPnl.setBackground(Color.white);
        addDelPnl.setBorder(BorderFactory.createLineBorder(Color.black));

        backPnl.setPreferredSize(new Dimension(200, 500));
        backPnl.setBackground(Color.white);
        backPnl.setBorder(BorderFactory.createLineBorder(Color.black));

        managePnl.setPreferredSize(new Dimension(600, 500));
        managePnl.setBackground(Color.darkGray);
        managePnl.setLayout(new GridLayout(7, 1, 10, 10));
        managePnl.add(addDelPnl);
        managePnl.add(backPnl);

        createEmpruntTable(modelEmprunt, tableEmprunt, commande.getListEmprunt());
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
