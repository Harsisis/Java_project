public class Livre extends Support_numerique {

    private String auteur;

    public Livre(int id_produit, String titre, double tarif_journalier, String auteur) {
        super(id_produit, titre, tarif_journalier,"CD");
        this.auteur = auteur;
    }

    public String getAuteur() {
        return auteur;
    }

    @Override
    int getId_produit(int id_produit) {
        return id_produit;
    }

    @Override
    String getTitre(String titre) {
        return titre;
    }

    @Override
    double getTarif_journalier(double tarif_journalier) {
        return tarif_journalier;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAuteur : " + getAuteur();
    }

}