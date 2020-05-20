package com.github.harsisis.videotheque.domaine;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Videotheque {

    private static final Videotheque INSTANCE = new Videotheque();

    // this should stay private because only one instance can exist
    // use Videotheque.getInstance()
    private Videotheque() {
    }

    public static final double REDUC_FIDELE = 0.1;

    // not sure about this
    private Map<Produit, Integer> listStockProduit = new HashMap<>();
    private HashSet<Client> listClient = new HashSet<>();
    private HashSet<Commande> listCommande = new HashSet<>();

    public HashSet<Client> getListClient() {
        return listClient;
    }

    public HashSet<Commande> getListCommande() {
        return listCommande;
    }

    public Map<Produit, Integer> getListStockProduit() {
        return listStockProduit;
    }

    public List<Produit> listProduitEnStock() {
        List<Produit> resultat = new ArrayList<>();
        for (Map.Entry<Produit, Integer> entry : listStockProduit.entrySet()
             ) {
            if (entry.getValue() > 0) {
                resultat.add(entry.getKey());
            }
        }
        return resultat;
    }

    public JComboBox createComboBox(final Map<Produit, Integer> listStockProduit){
        final JComboBox cBox = new JComboBox();
        for (Produit product : listStockProduit.keySet())
            cBox.addItem(product);
        return cBox;
    }

    public boolean ajoutClient (String nom, String prenom, boolean fidele) {
        Client client = new Client(nom, prenom, fidele);
        return listClient.add(client);
    }

    public boolean ajoutCommande (Client client, ArrayList<Emprunt> empruntList) {
        Commande commande = new Commande(client, empruntList);
        return listCommande.add(commande);
    }

    public void ajoutStockProduit (Produit produitId, int quantity) {
            if (listStockProduit.containsKey(produitId)) {
                listStockProduit.put(produitId, listStockProduit.get(produitId) + quantity);
            }
            else listStockProduit.put(produitId, listStockProduit.get(produitId));
    }

    public void creeCD (String titre, double tarifJournalier, int anneeSortie) {
        ajoutStockProduit(new CD(titre, tarifJournalier, anneeSortie),1);
    }

    public void creeDictionnaire (String titre, double tarifJournalier, String langue) {
        ajoutStockProduit(new Dictionnaire(titre, tarifJournalier, langue),1);
    }

    public void creeDVD (String titre, double tarifJournalier, String realisateur) {
        ajoutStockProduit(new DVD(titre, tarifJournalier, realisateur),1);
    }

    public void creeLivre(String titre, double tarifJournalier, String auteur, CategorieLivre categorieLivre) {
        ajoutStockProduit(new Livre(titre, tarifJournalier, auteur, categorieLivre),1);
    }

    public void retirerStockProduit (Produit produitId, int quantity) {
        listStockProduit.put(produitId, listStockProduit.get(produitId) - quantity);
    }
    public static Videotheque getInstance() {
        return INSTANCE;
    }
}
