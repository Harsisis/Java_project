package com.github.harsisis.videotheque.iu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame{
    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workPlaceLbl = new JPanel();// panel with a list of all customers registered

    private JButton addClientBtn = new JButton("Ajouter un client");
    private JLabel AppLbl = new JLabel("Vidéothèque");
    private JLabel ClientLbl = new JLabel("client");
// Jtextfield, Jcheckbox

    public Fenetre(){

        this.setTitle("Application");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addClientBtn.addActionListener(new addClientBtnListener());

        Font arial = new Font("arial", Font.BOLD, 18);
        AppLbl.setFont(arial);

        workPlaceLbl.add(ClientLbl);

        titlePnl.setBackground(Color.LIGHT_GRAY);
        titlePnl.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        titlePnl.add(AppLbl);

        displayPnl.setBackground(Color.WHITE);
        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(addClientBtn, BorderLayout.SOUTH);
        displayPnl.add(workPlaceLbl, BorderLayout.CENTER);

        this.setContentPane(displayPnl);
        //this.setContentPane(new Panel());
        this.setVisible(true);
    }

    //what is this ?
    class addClientBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            CreateClient video = new CreateClient();
        }
    }
}