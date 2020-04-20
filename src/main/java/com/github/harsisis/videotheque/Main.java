package com.github.harsisis.videotheque;

//import com.github.harsisis.Dictionnaire;
//import com.github.harsisis.Livre;


public class Main {
    public static void main(String[] args) {
        CD hello = new CD ("Hello", 3, 2000);
        System.out.println(hello);

        Client dm = new Client("DUPONT", "Martin", true);
        System.out.println(dm);

/*
        DVD lion = new DVD (2,"Lion King 2019", 4, "Jon Favreau");
        System.out.println(lion);

        Dictionnaire wembley = new Dictionnaire (3,"Good Wembley DICtionnary",2,"Anglais");
        System.out.println(wembley);

        Livre mockingbird = new Livre (4,"To kill a mocking bird",8, "Harper Lee");
        System.out.println(mockingbird);

        Fidele martin =new Fidele(1, "Martin", "Lucie");
        System.out.println(martin);

        Occasionnel dupont =new Occasionnel(2, "Dupont", "Jean");
        System.out.println(dupont);*/
    }
}
