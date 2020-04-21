package com.github.harsisis.videotheque.iu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame{
    private JPanel displayPnl = new JPanel();
    private JPanel titlePnl = new JPanel();
    private JPanel workPlaceLbl = new JPanel();
    private JButton addClientBtn = new JButton("Ajouter un client");
    private JLabel appLbl = new JLabel("Vidéothèque");
    private JLabel clientLbl = new JLabel("client");
// Jtextfield, Jcheckbox

    public Fenetre(){
        this.setTitle("Application");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addClientBtn.addActionListener(new addClientBtnListener());

        Font montserrat = new Font("Montserrat", Font.BOLD, 18);
        appLbl.setFont(montserrat);

        workPlaceLbl.add(clientLbl);

        titlePnl.setBackground(Color.LIGHT_GRAY);
        titlePnl.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        titlePnl.add(appLbl);

        displayPnl.setBackground(Color.WHITE);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(addClientBtn, BorderLayout.SOUTH);
        displayPnl.add(workPlaceLbl, BorderLayout.CENTER);

        this.setContentPane(displayPnl);
        //this.setContentPane(new Panel());
        this.setVisible(true);
        go();
    }

    private void go() {

    }
    class addClientBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            clientLbl.setText("ici le client");
        }
    }

}
