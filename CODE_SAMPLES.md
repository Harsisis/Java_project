## Code samples 

Here you can find some pieces of code that you might need to integrate in your project.

### This is an example of how to create a Client from the ui :

Instead of using an inner class to manage each action listener (this is the old Java 1.3 approach), 
it is preferable to use a lambda expression (Java 8+ approach)

Also, this.dispose() will close the window when the user clicks on the "Annuler" btn

```
public class CreateClient extends JFrame {
    ...

    private JFormattedTextField saisiNomJtf = new JFormattedTextField("");
    private JFormattedTextField saisiPrenomJtf = new JFormattedTextField("");

    private JCheckBox fideleCbx = new JCheckBox();

    private JButton cancelClientBtn = new JButton("Annuler");
    private JButton confirmClientBtn = new JButton("Valider");


    public CreateClient() {
        // windows settings
        this.setTitle("Ajout d'un Client");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add to Action Listener
        cancelClientBtn.addActionListener(e -> this.dispose());
        confirmClientBtn.addActionListener(e -> {
                    System.out.println("nom : " + saisiNomJtf.getText() + "\nprénom : " + saisiPrenomJtf.getText() + "\nfidele ? " + fideleCbx.isSelected());
                    Client client = new Client(saisiNomJtf.getText(), saisiPrenomJtf.getText(), fideleCbx.isSelected());
                }
        );

     ...

    }

}

```

### Videotheque Singleton

This class should be a Singleton : it means that we must guarantee that there will be only ONE instance of this object
when the App is running. There are several ways to create singletons, many are complicated and NOT thread safe, this one is easy to 
implement AND thread safe :

```
public class Videotheque {

    private static final Videotheque INSTANCE = new Videotheque();

    ...

    private Videotheque() {
        // empty hidden constructor
    }
    ...

    // Runtime initialization
    // By default ThreadSafe
    public static Videotheque getInstance() {
        return INSTANCE;
    }

    ...

}
```

Then you can retrieve your singleton from anywhere in the App just by using this :
```
Videotheque.getInstance();
```
