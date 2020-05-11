package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;
import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.*;

public class CreateClient extends JFrame {
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workplacePnl = new JPanel(); // panel with blank to enter customer's data
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm

    private JLabel titleLbl = new JLabel("Créer un nouveau client :");
    private JLabel nomLbl = new JLabel("Nom :");
    private JLabel prenomLbl = new JLabel("Prénom :");
    private JLabel fideleLbl = new JLabel("Fidèle :");

    private static JTextField saisiNomJtf = new JTextField();
    private static JTextField saisiPrenomJtf = new JTextField();

    private JCheckBox fideleCbx = new JCheckBox();

    private JButton cancelClientBtn = new JButton("Annuler");
    private JButton confirmClientBtn = new JButton("Valider");


    private JOptionPane jop3 = new JOptionPane();


    public CreateClient(Videotheque app){
        // windows settings
        setTitle("Ajout d'un Client");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        saisiNomJtf.setText("");
        saisiPrenomJtf.setText("");
        // add to Action Listener
        cancelClientBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        confirmClientBtn.addActionListener(e -> {
            if (!saisiNomJtf.getText().equals("") && !saisiPrenomJtf.getText().equals("")) {
                System.out.println("nom: " + saisiNomJtf.getText() + "\nprénom: " + saisiPrenomJtf.getText() + "\nfidèle: " + fideleCbx.isSelected());
                app.ajoutClient(saisiNomJtf.getText(), saisiPrenomJtf.getText(), fideleCbx.isSelected());
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else
                jop3.showMessageDialog(null, "Message d'erreur", "Erreur", JOptionPane.ERROR_MESSAGE);

            /*for(Client client : app.getClient()){
                System.out.println(client);
            }*/
        });

        // font settings
        Font arial = new Font("arial", Font.BOLD, 16);
        Font arialSmall = new Font("arial", Font.BOLD, 12);
        titleLbl.setFont(arial);
        saisiNomJtf.setFont(arialSmall);
        saisiPrenomJtf.setFont(arialSmall);
        nomLbl.setFont(arialSmall);
        prenomLbl.setFont(arialSmall);

        // title panel
        titlePnl.add(titleLbl);
        titlePnl.setBackground(Color.lightGray);

        // workplace panel
        saisiPrenomJtf.setPreferredSize(new Dimension(150, 30));
        saisiNomJtf.setPreferredSize(new Dimension(150, 30));

        workplacePnl.add(nomLbl);
        workplacePnl.add(saisiNomJtf);
        workplacePnl.add(prenomLbl);
        workplacePnl.add(saisiPrenomJtf);
        workplacePnl.add(fideleLbl);
        workplacePnl.add(fideleCbx);

        // confirm panel
        confirmPnl.add(confirmClientBtn);
        confirmPnl.add(cancelClientBtn);
        confirmPnl.setBackground(Color.lightGray);

        // display panel
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(workplacePnl, BorderLayout.CENTER);
        displayPnl.add(confirmPnl, BorderLayout.SOUTH);

        setContentPane(displayPnl);
        //this.setContentPane(new Panel());
        setVisible(true);
    }
}
