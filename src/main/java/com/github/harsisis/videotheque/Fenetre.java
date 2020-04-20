package com.github.harsisis.videotheque;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{
    public Fenetre(){
        this.setTitle("Application");
        this.setSize(1200, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        //this.setContentPane(panel);
        this.setContentPane(new Panel());

        this.setVisible(true);
    }
}
