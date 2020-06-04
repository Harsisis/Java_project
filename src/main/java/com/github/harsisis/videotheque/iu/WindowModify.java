package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Commande;
import com.github.harsisis.videotheque.domaine.Emprunt;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class WindowModify extends JFrame {

    private JPanel managePnl = new JPanel();
    private JPanel listProdPnl = new JPanel();
    private JScrollPane scrollPane;
    DefaultTableModel modelEmprunt = new DefaultTableModel();
    JTable tableEmprunt = new JTable(modelEmprunt);

    public WindowModify(Commande commande) {
        setTitle("Ajout d'une Commande");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        managePnl.setPreferredSize(new Dimension(400, 600));
        managePnl.setBackground(Color.darkGray);
        managePnl.setLayout(new GridLayout(7, 1, 10, 10));
        defineEmpruntTable(modelEmprunt, tableEmprunt);
        setVisible(true);
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
