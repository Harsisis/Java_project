package com.github.harsisis.videotheque.iu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.*;

public class CreateProduct extends JFrame {

    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workPlacePnl = new JPanel(); // panel with blank to enter customer's data

    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
    private JPanel typeProductPnl = new JPanel();
    private JPanel commonPointsPnl = new JPanel();
    private JPanel placeHolderPnl = new JPanel();

    private JLabel titleLbl = new JLabel("Créer un nouveau produit :");
    private JLabel titleProductLbl = new JLabel("Titre :");
    private JLabel priceProductLbl = new JLabel("Tarif Journalier:");

    private static JTextField saisiTitleJtf = new JTextField();
    private static JTextField saisiPriceJtf = new JTextField();

    String[] nameCat = new String[] {"CD", "DVD", "Dictionnaire", "Livre"};
    JComboBox<String> selectCatJcbx = new JComboBox<>(nameCat);

    private JButton cancelProductBtn = new JButton("Annuler");
    private JButton confirmProductBtn = new JButton("Valider");

    private ButtonGroup bookTypeBg = new ButtonGroup();

    private JRadioButton romanBtn = new JRadioButton("roman");
    private JRadioButton bdBtn = new JRadioButton("BD");
    private JRadioButton manuel_scolaireBtn = new JRadioButton("manuel scolaire");

    private JOptionPane jop3 = new JOptionPane();

    public CreateProduct(){
        setTitle("Ajout d'un produit");
        setSize(600,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        saisiTitleJtf.setText("");
        saisiPriceJtf.setText("");

        cancelProductBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        confirmProductBtn.addActionListener(e -> {
            if (!saisiPriceJtf.getText().equals("") && !saisiTitleJtf.getText().equals("")) {
                System.out.println("Titre: " + saisiTitleJtf.getText() + "\nTarif: " + saisiPriceJtf.getText());
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else
                jop3.showMessageDialog(null, "Champs incorrect(s) ou non valide", "Erreur", JOptionPane.ERROR_MESSAGE);

            /*for(Client client : app.getClient()){
                System.out.println(client);
            }*/
        });

        selectCatJcbx.addActionListener(e -> {
            if (selectCatJcbx.getSelectedIndex() == 0){// CD
                placeHolderPnl.removeAll();
                //insert date picker
            }
            else if (selectCatJcbx.getSelectedIndex() == 1){// DVD
                placeHolderPnl.removeAll();
                //insert text field
            }
            else if (selectCatJcbx.getSelectedIndex() == 2){// Dictionnaire
                placeHolderPnl.removeAll();
                //insert combo box with language

            }
            else {// livre
                placeHolderPnl.removeAll();
                typeProductPnl.add(romanBtn);
                typeProductPnl.add(bdBtn);
                typeProductPnl.add(manuel_scolaireBtn);
                //insert text field

            }
        });

        // font settings-----------------------------------------------------------------------------
        Font arial = new Font("arial", Font.BOLD, 16);
        Font arialSmall = new Font("arial", Font.BOLD, 12);
        titleLbl.setFont(arial);
        saisiTitleJtf.setFont(arialSmall);
        saisiPriceJtf.setFont(arialSmall);
        titleProductLbl.setFont(arialSmall);
        priceProductLbl.setFont(arialSmall);

        bookTypeBg.add(romanBtn);
        bookTypeBg.add(bdBtn);
        bookTypeBg.add(manuel_scolaireBtn);

        // title panel--------------------------------------------------------------------------------
        titlePnl.add(titleLbl);
        titleLbl.setForeground(Color.white);
        titlePnl.setBackground(Color.darkGray);
        titlePnl.setPreferredSize(new Dimension(600,50));

        // workPlace panel----------------------------------------------------------------------------
        workPlacePnl.setLayout(new GridLayout(2,2,10,10));
        workPlacePnl.add(typeProductPnl);
        workPlacePnl.add(commonPointsPnl);
        workPlacePnl.add(placeHolderPnl);
        workPlacePnl.setBorder(BorderFactory.createTitledBorder("espace de travail"));
        workPlacePnl.setPreferredSize(new Dimension(590,250));

        // place holder panel-------------------------------------------------------------------------
        placeHolderPnl.setBorder(BorderFactory.createTitledBorder("caractéristiques spéciales"));


        // common points panel-------------------------------------------------------------------------
        commonPointsPnl.setBorder(BorderFactory.createTitledBorder("caractéristiques"));
        commonPointsPnl.setLayout(new GridLayout(2,2));
        commonPointsPnl.add(titleProductLbl);
        commonPointsPnl.add(saisiTitleJtf);
        commonPointsPnl.add(priceProductLbl);
        commonPointsPnl.add(saisiPriceJtf);

        // type product panel-------------------------------------------------------------------------
        typeProductPnl.setBorder(BorderFactory.createTitledBorder("type de produit"));
        typeProductPnl.setLayout(new GridLayout(2,1));
        typeProductPnl.add(selectCatJcbx);
        selectCatJcbx.setBackground(Color.white);
        selectCatJcbx.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectCatJcbx.setAlignmentY(Component.CENTER_ALIGNMENT);



        // confirm panel------------------------------------------------------------------------------
        confirmPnl.add(confirmProductBtn);
        confirmPnl.add(cancelProductBtn);
        confirmProductBtn.setBackground(Color.white);
        cancelProductBtn.setBackground(Color.white);
        confirmPnl.setPreferredSize(new Dimension(600,50));

        // display panel------------------------------------------------------------------------------
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(workPlacePnl, BorderLayout.CENTER);
        displayPnl.add(confirmPnl, BorderLayout.SOUTH);


        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }
}
