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
    private Map<UUID, Integer> listStockProduit = new Map<UUID, Integer>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object o) {
            return false;
        }

        @Override
        public boolean containsValue(Object o) {
            return false;
        }

        @Override
        public Integer get(Object o) {
            return null;
        }

        @Override
        public Integer put(UUID uuid, Integer integer) {
            return null;
        }

        @Override
        public Integer remove(Object o) {
            return null;
        }

        @Override
        public void putAll(Map<? extends UUID, ? extends Integer> map) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<UUID> keySet() {
            return null;
        }

        @Override
        public Collection<Integer> values() {
            return null;
        }

        @Override
        public Set<Entry<UUID, Integer>> entrySet() {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    };

    private HashSet<Client> listClient = new HashSet<Client>();
    private HashSet<Commande> listCommande = new HashSet<Commande>();

    public HashSet<Client> getListClient() {
        return listClient;
    }

    public HashSet<Commande> getListCommande() {
        return listCommande;
    }

    public boolean ajoutClient (String nom, String prenom, boolean fidele) {
        Client client = new Client(nom, prenom, fidele);
        return listClient.add(client);
    }

    public boolean ajoutCommande (Client client, ArrayList<Emprunt> empruntList) {
        Commande commande = new Commande(client, empruntList);
        return listCommande.add(commande);
    }

    public void ajoutStockProduit (UUID produitId, int quantity) {
            if (listStockProduit.containsKey(produitId)) {
                listStockProduit.put(produitId, quantity + listStockProduit.get(produitId));
            }
    }

    public static Videotheque getInstance() {
        return INSTANCE;
    }
}
