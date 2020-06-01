package com.github.harsisis.videotheque.iu;

import com.github.harsisis.videotheque.domaine.*;
import com.github.harsisis.videotheque.iu.exception.ValidationException;
import com.github.harsisis.videotheque.util.CapitalizeUtil;
import com.github.harsisis.videotheque.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;

public class WindowProduct extends JFrame {

    private static final JTextField saisiTitleJtf = new JTextField();
    private static final JTextField saisiPriceJtf = new JTextField();
    private static final JTextField saisiYearJtf = new JTextField();
    private static final JTextField saisiAutorJtf = new JTextField();

    private final JPanel displayPnl = new JPanel();// display all the panels, buttons...
    private final JPanel titlePnl = new JPanel();// panel with title label
    private final JPanel workPlacePnl = new JPanel(); // panel with blank to enter customer's data
    private final JPanel confirmPnl = new JPanel();// panel with button to cancel or confirm
    private final JPanel typeProductPnl = new JPanel();
    private final JPanel commonPointsPnl = new JPanel();
    private final JPanel placeHolderPnl = new JPanel();
    private final JPanel buttonGrpLivrePnl = new JPanel();
    private final JPanel auteurLivrePnl = new JPanel();

    private final JLabel titleLbl = new JLabel("Créer un nouveau produit : ");
    private final JLabel titleProductLbl = new JLabel("Titre : ");
    private final JLabel priceProductLbl = new JLabel("Tarif Journalier : ");
    private final JLabel autorNameLbl = new JLabel("Nom de l'auteur : ");
    private final JLabel realisatorNameLbl = new JLabel("Nom du réalisateur : ");
    private final JLabel yearReleaseLbl = new JLabel("Année de sortie : ");
    private final JLabel languageLbl = new JLabel("Langue : ");

    private final JButton cancelProductBtn = new JButton("Annuler");
    private final JButton confirmProductBtn = new JButton("Valider");
    private final ButtonGroup bookTypeBg = new ButtonGroup();

    private final JRadioButton romanBtn = new JRadioButton("Roman");
    private final JRadioButton bdBtn = new JRadioButton("BD");
    private final JRadioButton manuelScolaireBtn = new JRadioButton("Manuel scolaire");

    private final JOptionPane jop3 = new JOptionPane();

    String[] nameCat = new String[]{"CD", "DVD", "Dictionnaire", "Livre"};
    private final JComboBox<String> selectCatJcbx = new JComboBox<>(nameCat);
    String[] nameLang = new String[]{"Français", "Anglais", "Espagnol", "Italien", "Allemand"};
    private final JComboBox<String> selectLangJcbx = new JComboBox<>(nameLang);

    public WindowProduct() {
        saisiYearJtf.setDocument(new JTextFieldLimit(4));
        // set window settings --------------------------------------------------------------------
        setTitle("Ajout d'un produit");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        saisiTitleJtf.setText("");
        saisiPriceJtf.setText("");
        saisiYearJtf.setText("");

        // cancel button event
        cancelProductBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        // confirm button event
        confirmProductBtn.addActionListener(e -> processInput());

        selectCatJcbx.addActionListener(e -> effacePanel());

        construitPanel(selectCatJcbx);

        bookTypeBg.add(romanBtn);
        bookTypeBg.add(bdBtn);
        bookTypeBg.add(manuelScolaireBtn);
    }

    private void construitPanel(JComboBox<String> selectCatJcbx) {
        // title panel--------------------------------------------------------------------------------
        titlePnl.add(titleLbl);
        titleLbl.setForeground(Color.white);
        titlePnl.setBackground(Color.darkGray);
        titlePnl.setPreferredSize(new Dimension(600, 50));

        // workPlace panel----------------------------------------------------------------------------
        workPlacePnl.setLayout(new GridLayout(2, 2, 10, 10));
        workPlacePnl.add(typeProductPnl);
        workPlacePnl.add(commonPointsPnl);
        workPlacePnl.add(placeHolderPnl);
        workPlacePnl.setBorder(BorderFactory.createTitledBorder("Espace de travail"));
        workPlacePnl.setPreferredSize(new Dimension(590, 250));

        // place holder panel-------------------------------------------------------------------------
        placeHolderPnl.setBorder(BorderFactory.createTitledBorder("Caractéristiques spéciales"));
        placeHolderPnl.add(yearReleaseLbl, BorderLayout.EAST);
        placeHolderPnl.add(saisiYearJtf, BorderLayout.WEST);
        saisiAutorJtf.setPreferredSize(new Dimension(100, 20));
        saisiYearJtf.setPreferredSize(new Dimension(50, 20));

        // common points panel-------------------------------------------------------------------------
        commonPointsPnl.setBorder(BorderFactory.createTitledBorder("Caractéristiques"));
        commonPointsPnl.setLayout(new GridLayout(2, 2));
        commonPointsPnl.add(titleProductLbl);
        commonPointsPnl.add(saisiTitleJtf);
        commonPointsPnl.add(priceProductLbl);
        commonPointsPnl.add(saisiPriceJtf);

        // type product panel-------------------------------------------------------------------------
        typeProductPnl.setBorder(BorderFactory.createTitledBorder("Type de produit"));
        typeProductPnl.setLayout(new GridLayout(2, 1));
        typeProductPnl.add(selectCatJcbx);
        selectCatJcbx.setBackground(Color.white);
        selectCatJcbx.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectCatJcbx.setAlignmentY(Component.CENTER_ALIGNMENT);


        // confirm panel------------------------------------------------------------------------------
        confirmPnl.add(confirmProductBtn);
        confirmPnl.add(cancelProductBtn);
        confirmProductBtn.setBackground(Color.white);
        cancelProductBtn.setBackground(Color.white);
        confirmPnl.setPreferredSize(new Dimension(600, 50));

        // display panel------------------------------------------------------------------------------
        displayPnl.add(titlePnl, BorderLayout.NORTH);
        displayPnl.add(workPlacePnl, BorderLayout.CENTER);
        displayPnl.add(confirmPnl, BorderLayout.SOUTH);


        // set visible------------------------------------------------------------------------------
        setContentPane(displayPnl);
        setVisible(true);
    }

    void processInput() {
        try {
            Produit nouveauProduit = buildProduit();
            Videotheque.getInstance().ajoutStockProduit(nouveauProduit.getProduitId(), 0);
            Videotheque.getInstance().ajoutProduit(nouveauProduit.getProduitId(), nouveauProduit);
            System.out.println("Titre : " + saisiTitleJtf.getText() + "\nPrix : " + saisiPriceJtf.getText());
            JOptionPane.showMessageDialog(this, "Le produit " +
                    CapitalizeUtil.getCapitalize(saisiTitleJtf.getText()) +
                    " a bien été crée.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } catch (ValidationException ve) {
            JOptionPane.showMessageDialog(this, ve.getMessage(), ve.getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }

    private Produit buildProduit() throws ValidationException {
        if (StringUtils.isEmpty(saisiTitleJtf.getText())
                || StringUtils.isEmpty(saisiPriceJtf.getText())
                || !ValidatorUtil.isValidDouble(saisiPriceJtf.getText())) {
            throw new ValidationException("Champs incorrect(s) ou non valide, veuillez vérifier la saisie", "Attention");
        }

        // check the condition doesn't work even if it's a double
        switch (selectCatJcbx.getSelectedIndex()) {
            case 0:
                return buildCD();
            case 1:
                return buildDVD();
            case 2:
                return buildDictionnaire();
            case 3:
                return buildLivre();
            default:
                throw new ValidationException("Valeur Impossible: " + selectCatJcbx.getSelectedIndex(), "Attention");
        }

    }

    private Produit buildLivre() throws ValidationException {
        CategorieLivre typeLivre;
        if (!StringUtils.isEmpty(saisiAutorJtf.getText())) {
            if (romanBtn.isSelected()) {
                typeLivre = CategorieLivre.ROMAN;
            } else if (bdBtn.isSelected()) {
                typeLivre = CategorieLivre.BD;
            } else if (manuelScolaireBtn.isSelected()) {
                typeLivre = CategorieLivre.MANUEL;
            } else {
                throw new ValidationException("Veuillez selectionner une catégorie valide", "Attention");
            }
            return new Livre(saisiTitleJtf.getText(), Double.parseDouble(saisiPriceJtf.getText()), saisiAutorJtf.getText(), typeLivre);
        } else {
            throw new ValidationException("Veuillez saisir un auteur", "Attention");
        }
    }

    private Produit buildDictionnaire() throws ValidationException {
        if (!StringUtils.isEmpty(saisiTitleJtf.getText())) {
            return new Dictionnaire(saisiTitleJtf.getText(), Double.parseDouble(saisiPriceJtf.getText()), String.valueOf(selectLangJcbx.getSelectedIndex()));
        } else {
            throw new ValidationException("Veuillez saisir un titre", "Attention");
        }
    }

    private Produit buildDVD() throws ValidationException {
        if (!StringUtils.isEmpty(saisiAutorJtf.getText())) {
            return new DVD(saisiTitleJtf.getText(), Double.parseDouble(saisiPriceJtf.getText()), saisiAutorJtf.getText());
        } else {
            throw new ValidationException("Veuillez saisir un auteur", "Attention");
        }
    }

    private Produit buildCD() throws ValidationException {
        if (!saisiYearJtf.getText().equals("") && saisiYearJtf.getText().length() == 4 && ValidatorUtil.isValidInteger(saisiYearJtf.getText())) {
            return new CD(saisiTitleJtf.getText(), Double.parseDouble(saisiPriceJtf.getText()), Integer.parseInt(saisiYearJtf.getText()));
        } else {
            throw new ValidationException("Veuillez saisir une année correcte (quatre entiers)", "Attention");
        }
    }

    private void effacePanel() {
        if (selectCatJcbx.getSelectedIndex() == 0) {// CD
            placeHolderPnl.removeAll();
            saisiYearJtf.setText("");
            saisiTitleJtf.setText("");
            saisiPriceJtf.setText("");
            //insert textField --> saisiYear
            placeHolderPnl.add(yearReleaseLbl, BorderLayout.EAST);
            placeHolderPnl.add(saisiYearJtf, BorderLayout.WEST);

        } else if (selectCatJcbx.getSelectedIndex() == 1) {// DVD
            placeHolderPnl.removeAll();
            saisiAutorJtf.setText("");
            saisiTitleJtf.setText("");
            saisiPriceJtf.setText("");
            //insert text field --> saisiAutor
            placeHolderPnl.add(realisatorNameLbl, BorderLayout.EAST);
            placeHolderPnl.add(saisiAutorJtf, BorderLayout.WEST);

        } else if (selectCatJcbx.getSelectedIndex() == 2) {// Dictionnaire
            placeHolderPnl.removeAll();
            saisiTitleJtf.setText("");
            saisiPriceJtf.setText("");
            //insert combo box with language
            placeHolderPnl.add(languageLbl, BorderLayout.EAST);
            placeHolderPnl.add(selectLangJcbx, BorderLayout.WEST);

        } else {// livre
            placeHolderPnl.removeAll();
            saisiAutorJtf.setText("");
            saisiTitleJtf.setText("");
            saisiPriceJtf.setText("");
            //insert type Buttons
            placeHolderPnl.add(buttonGrpLivrePnl, BorderLayout.NORTH);
            placeHolderPnl.add(auteurLivrePnl, BorderLayout.SOUTH);

            buttonGrpLivrePnl.add(romanBtn);
            buttonGrpLivrePnl.add(bdBtn);
            buttonGrpLivrePnl.add(manuelScolaireBtn);

            auteurLivrePnl.add(autorNameLbl, BorderLayout.WEST);
            auteurLivrePnl.add(saisiAutorJtf, BorderLayout.EAST);

        }
        revalidate();
        placeHolderPnl.repaint();
    }
    class JTextFieldLimit extends PlainDocument {
        private int limit;
        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }
        JTextFieldLimit(int limit, boolean upper) {
            super();
            this.limit = limit;
        }
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
