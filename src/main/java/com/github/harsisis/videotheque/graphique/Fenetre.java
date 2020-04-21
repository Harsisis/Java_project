package com.github.harsisis.videotheque.graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame{
    private JPanel panel = new JPanel();
    private JPanel title = new JPanel();
    private JPanel workPlace = new JPanel();
    private JButton addClient = new JButton("Ajouter un client");
    private JLabel labelApp = new JLabel("Vidéothèque");
    private JLabel labelClient = new JLabel("client");


    public Fenetre(){
        this.setTitle("Application");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addClient.addActionListener(new addClientListener());

        Font montserrat = new Font("Montserrat", Font.BOLD, 18);
        labelApp.setFont(montserrat);

        workPlace.add(labelClient);

        title.setBackground(Color.LIGHT_GRAY);
        title.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        title.add(labelApp);

        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.add(title, BorderLayout.NORTH);
        panel.add(addClient, BorderLayout.SOUTH);
        panel.add(workPlace, BorderLayout.CENTER);

        this.setContentPane(panel);
        //this.setContentPane(new Panel());
        this.setVisible(true);
        go();
    }

    private void go() {

    }
    class addClientListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            labelClient.setText("ici le client");
        }
    }

}
