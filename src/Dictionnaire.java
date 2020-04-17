public class Dictionnaire extends Document{
    private String langue;

    public Dictionnaire(int id_produit, String titre, double tarif_journalier, String type_produit) {
        super(id_produit, titre, tarif_journalier, type_produit);
    }
}