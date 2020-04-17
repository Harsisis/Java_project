public class Occasionnel extends Client{

    private double reduction = 0;

    public Occasionnel(int id_client, String nom, String prenom) {
        super(id_client, nom, prenom);
    }

    @Override
    double GetReduction() {
        return reduction;
    }

}
