public class Test {
    public static void main(String[] args) {
        CD hello = new CD (1, "Hello", 3, 2000);
        System.out.println(hello);

        DVD lion = new DVD (2,"Lion King 2019", 4, "Jon Favreau");
        System.out.println(lion);

        Dictionnaire wembley = new Dictionnaire (3,"Good Wembley DICtionnary",2,"Anglais");
        System.out.println(wembley);

        Livre mockingbird = new Livre (4,"To kill a mocking bird",8, "Harper Lee");
        System.out.println(mockingbird);
    }
}
