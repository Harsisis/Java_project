public class CD extends Support_numerique {

    private int annee_sortie;

    public CD(int id_produit, String titre, double tarif_journalier, int annee_sortie) {
        super(id_produit, titre, tarif_journalier,"CD");
        this.annee_sortie = annee_sortie;
    }

    public int getAnnee_sortie() {
        return annee_sortie;
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
        return super.toString() + "\nAnnée sortie : " + getAnnee_sortie();
    }

}