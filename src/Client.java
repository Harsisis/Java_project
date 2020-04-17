public abstract class Client {

    private int id_client;
    private String nom;
    private String prenom;
    private double reduction;

    public Client(int id_client, String nom, String prenom) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.reduction = GetReduction();
    }

    int GetId_client (){
        return id_client;
    }

    String GetNom (){
        return nom;
    }

    String GetPrenom (){
        return prenom;
    }

    abstract double GetReduction ();

    @Override
    public String toString() {
        return "\nInformations client n° " + id_client + " :\nnom : " + nom + "\nprénom : " + prenom + "\nréduction : " + GetReduction();
    }
}
