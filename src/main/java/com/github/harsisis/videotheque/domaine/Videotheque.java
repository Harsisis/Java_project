package com.github.harsisis.videotheque.domaine;

import java.util.*;
import java.util.List;

public class Videotheque {

    private static final Videotheque INSTANCE = new Videotheque();

    // this should stay private because only one instance can exist
    // use Videotheque.getInstance()
    private Videotheque() {
    }

    public static final double REDUC_FIDELE = 0.1;

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
        for (Map.Entry<Produit, Integer> entry : listStockProduit.entrySet()) {
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

    public void ajoutStockProduit (Produit produit, int quantity) {
            if (listStockProduit.containsKey(produit)) {
                listStockProduit.put(produit, listStockProduit.get(produit) + quantity);
            }
            else listStockProduit.put(produit, listStockProduit.get(produit));
    }

    public void retirerStockProduit (Produit produitId, int quantity) {
        listStockProduit.put(produitId, listStockProduit.get(produitId) - quantity);
    }

    public static Videotheque getInstance() {
        return INSTANCE;
    }

    public String getProduitNom(Map<Produit, Integer> listStockProduit){
        String result = "";
        for (Produit produit : listStockProduit.keySet()) {
            if (produit.getClassName().equals("Livre")) {
                result = produit.getCategorieProduit() + " | " + produit.getClassName() + " | " + Livre.getCategorieLivre() + " | " + produit.getTitre();
            }
            else result = produit.getCategorieProduit() + " | " + produit.getClassName() + " | " + produit.getTitre();
        }
        return result;
    }

    public static boolean isValidDouble(String str) {
        boolean isValid = false;
        try {
            Double newInput = Double.parseDouble(str);
            Double i = newInput.doubleValue();
            isValid = true;
        } finally {
            return isValid;
        }
    }

    public static boolean isValidInteger(String str) {
        boolean isValid = false;
        try {
            Integer newInput = Integer.valueOf(str);
            Integer i = newInput.intValue();
            isValid = true;
        } finally {
            return isValid;
        }
    }
}
