package com.github.harsisis.videotheque.domaine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Videotheque {

    private static final Videotheque INSTANCE = new Videotheque();

    private Videotheque() {
    }

    public static final double REDUC_FIDELE = 0.1;
    private Map<Produit, Integer> listStockProduit;

    private HashSet<Client> listClient = new HashSet<Client>();
    private HashSet<Commande> listCommande = new HashSet<Commande>();

    public boolean ajoutClient (String nom, String prenom, boolean fidele) {
        Client client = new Client(nom, prenom, fidele);
        return listClient.add(client);
    }

    public boolean ajoutCommande (Client client, ArrayList<Emprunt> empruntList) {
        Commande commande = new Commande(client, empruntList);
        return listCommande.add(commande);
    }

    //public void

    public void ajoutStockProduit (Produit produit, int quantity) {
        if (quantity > 0) {
            if (produitExist(produit)) {
                listStockProduit.put(produit, quantity + listStockProduit.get(produit));
            }
            else {
                listStockProduit.put(produit, quantity);
            }
        }
    }

    public boolean produitExist (Produit produit) {
        if (listStockProduit.containsKey(produit)) {
            return true;
        }
        else return false;
    }

    public void supCommande (int numeroCommande) {
        listCommande.remove(numeroCommande);
    }

    public static Videotheque getInstance() {
        return INSTANCE;
    }
}
