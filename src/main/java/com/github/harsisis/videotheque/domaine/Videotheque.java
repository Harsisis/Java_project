package com.github.harsisis.videotheque.domaine;

import java.util.*;

public class Videotheque {

    private static final Videotheque INSTANCE = new Videotheque();

    // this should stay private because only one instance can existe
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
                listStockProduit.put(produitId, quantity + listStockProduit.get(produitId));
            }
    }

    public static Videotheque getInstance() {
        return INSTANCE;
    }
}
