public abstract class Produit {
    private int id_produit;
    private String titre;
    private double tarif_journalier;
    private String type_produit;

    public Produit(int id_produit, String titre, double tarif_journalier, String type_produit) {
        this.id_produit = id_produit;
        this.titre = titre;
        this.tarif_journalier = tarif_journalier;
        this.type_produit = type_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getTarif_journalier() {
        return tarif_journalier;
    }

    public void setTarif_journalier(double tarif_journalier) {
        this.tarif_journalier = tarif_journalier;
    }

    public String getType_produit() {
        return type_produit;
    }

    public void setType_produit(String type_produit) {
        this.type_produit = type_produit;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", titre='" + titre + '\'' +
                ", tarif_journalier=" + tarif_journalier +
                ", type_produit='" + type_produit + '\'' +
                '}';
    }
}