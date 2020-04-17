public class Support_numerique extends Produit {
    private String selection;

    public Support_numerique(int id_produit, String titre, double tarif_journalier, String selection) {
        super(id_produit, titre, tarif_journalier, "Support numérique");
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
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
        return super.toString() + "\nType de produit : " + getSelection();
    }
}