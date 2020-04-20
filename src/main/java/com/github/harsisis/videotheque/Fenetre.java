package com.github.harsisis.videotheque;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{
    private JPanel panel = new JPanel();
    private JPanel title = new JPanel();
    private JButton ajouterClient = new JButton("Ajouter un client");
    private JLabel labelApp = new JLabel("Vidéothèque");


    public Fenetre(){
        this.setTitle("Application");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title.setBackground(Color.LIGHT_GRAY);
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        title.add(labelApp);
        panel.add(title, BorderLayout.NORTH);
        panel.add(ajouterClient, BorderLayout.SOUTH);

        this.setContentPane(panel);
        //this.setContentPane(new Panel());
        this.setVisible(true);
    }
}
