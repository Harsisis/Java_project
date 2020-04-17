public class Fidele extends Client{

    private double reduction = 0.1;

    public Fidele(int id_client, String nom, String prenom) {
        super(id_client, nom, prenom);
    }

    @Override
    double GetReduction() {
        return reduction;
    }
}
