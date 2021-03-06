package com.github.harsisis.videotheque.domaine;

import java.util.*;

public class Videotheque {

    public static final double REDUC_FIDELE = 0.1;
    private static final Videotheque INSTANCE = new Videotheque();
    private Map<String, Integer> listStockProduit = new HashMap<>();
    private Map<String, Produit> listProduit = new HashMap<>();
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

    public Map<String, Produit> getListProduit() {
        return listProduit;
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

    public Commande ajoutCommande(Client client, ArrayList<Emprunt> empruntList) {
        Commande commande = new Commande(client, empruntList);
        listCommande.add(commande);
        return commande;
    }

    public boolean supprimeCommande(Commande commande) {
        return listCommande.remove(commande);
    }

    public void ajoutStockProduit(String produitId, int quantity) {
        if (listStockProduit.containsKey(produitId)) {
            listStockProduit.put(produitId, listStockProduit.get(produitId) + quantity);
        } else listStockProduit.put(produitId, quantity);
    }

    public void ajoutProduit(String produitId, Produit produit) {
        listProduit.put(produitId, produit);
    }

    public Produit getProduit(String produitId) {
        return listProduit.get(produitId);
    }

    public void retirerStockProduit(String produitId) {
        listStockProduit.put(produitId, listStockProduit.get(produitId) - 1);
    }
}
