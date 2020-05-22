package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;

public class CreateProduct extends JFrame {

    private JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private JPanel titlePnl = new JPanel();// panel with title label
    private JPanel workPlacePnl = new JPanel(); // panel with blank to enter customer's data

    private JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
    private JPanel typeProductPnl = new JPanel();
    private JPanel commonPointsPnl = new JPanel();
    private JPanel placeHolderPnl = new JPanel();
    private JPanel buttonGrpLivrePnl = new JPanel();
    private JPanel auteurLivrePnl = new JPanel();

    private JLabel titleLbl = new JLabel("Créer un nouveau produit : ");
    private JLabel titleProductLbl = new JLabel("Titre : ");
    private JLabel priceProductLbl = new JLabel("Tarif Journalier : ");
    private JLabel autorNameLbl = new JLabel("Nom de l'auteur : ");
    private JLabel realisatorNameLbl = new JLabel("Nom du réalisateur : ");
    private JLabel yearReleaseLbl = new JLabel("Année de sortie : ");
    private JLabel languageLbl = new JLabel("Langue : ");

    private static JTextField saisiTitleJtf = new JTextField();
    private static JTextField saisiPriceJtf = new JTextField();

    private static JTextField saisiYearJtf = new JTextField();
    private static JTextField saisiAutorJtf = new JTextField();

    String[] nameCat = new String[] {"CD", "DVD", "Dictionnaire", "Livre"};

    String[] nameLang = new String[] {"Français", "Anglais", "Espagnol", "Italien", "Allemand"};

    private JButton cancelProductBtn = new JButton("Annuler");
    private JButton confirmProductBtn = new JButton("Valider");

    private ButtonGroup bookTypeBg = new ButtonGroup();

    private JRadioButton romanBtn = new JRadioButton("Roman");
    private JRadioButton bdBtn = new JRadioButton("BD");
    private JRadioButton manuel_scolaireBtn = new JRadioButton("Manuel scolaire");

    private JOptionPane jop3 = new JOptionPane();

    public CreateProduct(){
        // set window settings --------------------------------------------------------------------
        setTitle("Ajout d'un produit");
        setSize(600,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        saisiTitleJtf.setText("");
        saisiPriceJtf.setText("");
        JComboBox<String> selectCatJcbx = new JComboBox<>(nameCat);
        JComboBox<String> selectLangJcbx = new JComboBox<>(nameLang);

        cancelProductBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        confirmProductBtn.addActionListener(e -> {
            if (!saisiTitleJtf.getText().equals("") && !saisiPriceJtf.getText().equals("")) {
                switch (selectCatJcbx.getSelectedIndex()) {
                    case 0:
                        if (!saisiYearJtf.getText().equals("") && saisiYearJtf.getText().length()==4)
                            Videotheque.getInstance().ajoutStockProduit(new CD(saisiTitleJtf.getText(),Double.parseDouble(saisiPriceJtf.getText()),Integer.parseInt(saisiYearJtf.getText())), 1);
                        else
                            jop3.showMessageDialog(null, "Veuillez saisir une année correcte (quatre entiers)", "Attention", JOptionPane.WARNING_MESSAGE);
                        break;
                    case 1:
                        if (!saisiAutorJtf.getText().equals(""))
                            Videotheque.getInstance().ajoutStockProduit(new DVD(saisiTitleJtf.getText(),Double.parseDouble(saisiPriceJtf.getText()),saisiAutorJtf.getText()), 1);
                        break;
                    case 2:
                        if (!String.valueOf(selectLangJcbx.getSelectedIndex()).equals(""))
                            Videotheque.getInstance().ajoutStockProduit(new Dictionnaire(saisiTitleJtf.getText(),Double.parseDouble(saisiPriceJtf.getText()),String.valueOf(selectLangJcbx.getSelectedIndex())), 1);
                        break;
                    case 3:
                        CategorieLivre typeLivre = CategorieLivre.ROMAN;
                        if (!saisiAutorJtf.getText().equals("")) {
                            if (romanBtn.isSelected())
                                typeLivre = CategorieLivre.ROMAN;
                            if (bdBtn.isSelected())
                                typeLivre = CategorieLivre.BD;
                            if (manuel_scolaireBtn.isSelected())
                                typeLivre = CategorieLivre.MANUEL;
                            Videotheque.getInstance().ajoutStockProduit(new Livre(saisiTitleJtf.getText(), Double.parseDouble(saisiPriceJtf.getText()), saisiAutorJtf.getText(), typeLivre), 1);
                        }
                        break;
                }
                System.out.println("Titre : " + saisiTitleJtf.getText() + "\nPrix : " + saisiPriceJtf.getText());
                saisiYearJtf.setText("");
                saisiTitleJtf.setText("");
                saisiPriceJtf.setText("");
                saisiAutorJtf.setText("");
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
            else
                jop3.showMessageDialog(null, "Champs incorrect(s) ou non valide, veuillez vérifier la saisie", "Erreur", JOptionPane.ERROR_MESSAGE);

        });

        selectCatJcbx.addActionListener(e -> {
            if (selectCatJcbx.getSelectedIndex() == 0){// CD
                placeHolderPnl.removeAll();
                saisiYearJtf.setText("");
                saisiTitleJtf.setText("");
                saisiPriceJtf.setText("");
                //insert textField --> saisiYear
                placeHolderPnl.add(yearReleaseLbl, BorderLayout.EAST);
                placeHolderPnl.add(saisiYearJtf, BorderLayout.WEST);

            }
            else if (selectCatJcbx.getSelectedIndex() == 1){// DVD
                placeHolderPnl.removeAll();
                saisiAutorJtf.setText("");
                saisiTitleJtf.setText("");
                saisiPriceJtf.setText("");
                //insert text field --> saisiAutor
                placeHolderPnl.add(realisatorNameLbl, BorderLayout.EAST);
                placeHolderPnl.add(saisiAutorJtf, BorderLayout.WEST);

            }
            else if (selectCatJcbx.getSelectedIndex() == 2){// Dictionnaire
                placeHolderPnl.removeAll();
                saisiTitleJtf.setText("");
                saisiPriceJtf.setText("");
                //insert combo box with language
                placeHolderPnl.add(languageLbl, BorderLayout.EAST);
                placeHolderPnl.add(selectLangJcbx, BorderLayout.WEST);

            }
            else {// livre
                placeHolderPnl.removeAll();
                saisiAutorJtf.setText("");
                saisiTitleJtf.setText("");
                saisiPriceJtf.setText("");
                //insert type Buttons
                placeHolderPnl.add(buttonGrpLivrePnl, BorderLayout.NORTH);
                placeHolderPnl.add(auteurLivrePnl, BorderLayout.SOUTH);

                buttonGrpLivrePnl.add(romanBtn);
                buttonGrpLivrePnl.add(bdBtn);
                buttonGrpLivrePnl.add(manuel_scolaireBtn);

                auteurLivrePnl.add(autorNameLbl, BorderLayout.WEST);
                auteurLivrePnl.add(saisiAutorJtf, BorderLayout.EAST);

            }
            revalidate();
            placeHolderPnl.repaint();
        });

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
        placeHolderPnl.add(yearReleaseLbl, BorderLayout.EAST);
        placeHolderPnl.add(saisiYearJtf, BorderLayout.WEST);
        saisiAutorJtf.setPreferredSize(new Dimension(100,20));
        saisiYearJtf.setPreferredSize(new Dimension(50,20));

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
