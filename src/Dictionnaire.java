public class Dictionnaire extends Document {

    private String langue;

    public Dictionnaire(int id_produit, String titre, double tarif_journalier, String langue) {
        super(id_produit, titre, tarif_journalier,"CD");
        this.langue = langue;
    }

    public String getLangue() {
        return langue;
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
        return super.toString() + "\nLangue : " + getLangue();
    }

}