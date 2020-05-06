package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClient extends JFrame {
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workplacePnl = new JPanel(); // panel with blank to enter customer's data
    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm

    private JLabel titleLbl = new JLabel("Créer un nouveau client :");
    private JLabel nomLbl = new JLabel("Nom :");
    private JLabel prenomLbl = new JLabel("Prénom :");
    private JLabel fideleLbl = new JLabel("Fidèle :");

    private static JTextField saisiNomJtf = new JTextField("saisir Nom");
    private static JTextField saisiPrenomJtf = new JTextField("saisir Prénom");

    private JCheckBox fideleCbx = new JCheckBox();

    private JButton cancelClientBtn = new JButton("Annuler");
    private JButton confirmClientBtn = new JButton("Valider");


    public CreateClient(){
        // windows settings
        this.setTitle("Ajout d'un Client");
        this.setSize(600, 200);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // add to Action Listener
        cancelClientBtn.addActionListener(new cancelClientBtnListener());
        confirmClientBtn.addActionListener(new confirmClientBtnListener());
        fideleCbx.addActionListener(new StateListener());

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
        saisiPrenomJtf.setForeground(Color.lightGray);
        saisiNomJtf.setForeground(Color.lightGray);

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

        this.setContentPane(displayPnl);
        //this.setContentPane(new Panel());
        this.setVisible(true);
    }


    public static class cancelClientBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            //I want to close this windows but keep the main open
        }
    }

    public static class confirmClientBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("nom : " + saisiNomJtf.getText() + "\nprénom : " + saisiPrenomJtf.getText());
            Client client = new Client(saisiNomJtf.getText(), saisiPrenomJtf.getText(), ((JCheckBox)actionEvent.getSource()).isSelected());// doesn't work for now
        }
    }

    public static class StateListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("etat case : " + ((JCheckBox)actionEvent.getSource()).isSelected());
        }
    }
}
