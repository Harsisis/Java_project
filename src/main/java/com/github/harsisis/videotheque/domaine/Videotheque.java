package com.github.harsisis.videotheque.domaine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Videotheque {

    public static final double REDUC_FIDELE = 0.1;
    private static final Videotheque INSTANCE = new Videotheque();
    private Map<String, Integer> listStockProduit = new HashMap<>();
    private HashSet<Client> listClient = new HashSet<>();
    private HashSet<Commande> listCommande = new HashSet<>();
    // this should stay private because only one instance can exist
    // use Videotheque.getInstance()
    private Videotheque() {
    }

    public static Videotheque getInstance() {
        return INSTANCE;
    }

    public HashSet<Client> getListClient() {
        return listClient;
    }

    public HashSet<Commande> getListCommande() {
        return listCommande;
    }

    public Map<String, Integer> getListStockProduit() {
        return listStockProduit;
    }

    public List<String> listProduitDispo() {
        List<String> resultat = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : listStockProduit.entrySet()) {
            if (entry.getValue() > 0) {
                resultat.add(entry.getKey());
            }
        }
        return resultat;
    }

    public boolean ajoutClient(String nom, String prenom, boolean fidele) {
        Client client = new Client(nom, prenom, fidele);
        return listClient.add(client);
    }

    public boolean ajoutCommande(Client client, ArrayList<Emprunt> empruntList) {
        Commande commande = new Commande(client, empruntList);
        return listCommande.add(commande);
    }

    public void ajoutStockProduit(String produitId, int quantity) {
        if (listStockProduit.containsKey(produitId)) {
            listStockProduit.put(produitId, listStockProduit.get(produitId) + quantity);
        } else listStockProduit.put(produitId, quantity);
    }

    public void retirerStockProduit(String produitId, int quantity) {
        listStockProduit.put(produitId, listStockProduit.get(produitId) - quantity);
    }
}
