package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Videotheque;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

public class CreateClient extends JFrame {
    private static JTextField saisiNomJtf = new JTextField();
    private static JTextField saisiPrenomJtf = new JTextField();
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workPlacePnl = new JPanel(); // panel with blank to enter customer's data
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
    private JLabel titleLbl = new JLabel("Créer un nouveau client :");
    private JLabel nomLbl = new JLabel("Nom :");
    private JLabel prenomLbl = new JLabel("Prénom :");
    private JLabel fideleLbl = new JLabel("Fidèle :");
    private JCheckBox fideleCbx = new JCheckBox();

    private JButton cancelClientBtn = new JButton("Annuler");
    private JButton confirmClientBtn = new JButton("Valider");


    private JOptionPane jop3 = new JOptionPane();


    public CreateClient() {
        // set window settings --------------------------------------------------------------------
        setTitle("Ajout d'un Client");
        setSize(280, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // add to Action Listener-------------------------------------------------------------------
        // by default set the text field to blank and add some procedure
        //confirmClientButton verify if textFields are not blank and if is the case it create a client else it display error message
        saisiNomJtf.setText("");
        saisiPrenomJtf.setText("");
        cancelClientBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        confirmClientBtn.addActionListener(e -> {
            if (!saisiNomJtf.getText().equals("") && !saisiPrenomJtf.getText().equals("")) {
                System.out.println("Nom: " + saisiNomJtf.getText() + "\nPrénom: " + saisiPrenomJtf.getText() + "\nFidèle: " + fideleCbx.isSelected());
                Videotheque.getInstance().ajoutClient(saisiNomJtf.getText(), saisiPrenomJtf.getText(), fideleCbx.isSelected());
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            } else
                jop3.showMessageDialog(null, "Veuillez rentrer un Nom et Prénom valide", "Erreur", JOptionPane.ERROR_MESSAGE);

        });

        // title panel--------------------------------------------------------------------------------
        titleLbl.setForeground(Color.white);
        titlePnl.add(titleLbl);
        titlePnl.setBackground(Color.darkGray);
        titlePnl.setPreferredSize(new Dimension(280, 30));

        // workPlace panel----------------------------------------------------------------------------
        saisiPrenomJtf.setPreferredSize(new Dimension(150, 30));
        saisiNomJtf.setPreferredSize(new Dimension(150, 30));

        workPlacePnl.setLayout(new GridLayout(8, 1, 0, 5));
        workPlacePnl.add(nomLbl);
        workPlacePnl.add(saisiNomJtf);
        workPlacePnl.add(prenomLbl);
        workPlacePnl.add(saisiPrenomJtf);
        workPlacePnl.add(fideleLbl);
        workPlacePnl.add(fideleCbx);

        // confirm panel------------------------------------------------------------------------------
        confirmPnl.add(confirmClientBtn);
        confirmPnl.add(cancelClientBtn);
        confirmClientBtn.setBackground(Color.white);
        cancelClientBtn.setBackground(Color.white);

        // display panel------------------------------------------------------------------------------
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(workPlacePnl, BorderLayout.CENTER);
        displayPnl.add(confirmPnl, BorderLayout.SOUTH);

        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }
}
