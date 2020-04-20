package com.github.harsisis.videotheque;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public void afficheTitre (Graphics a){
        Font Montserrat = new Font("Courrier", Font.BOLD, 18);
        a.setFont(Montserrat);
        a.setColor(Color.BLUE);
        a.drawString("Vidéothèque :", 10, 20);
    }
}
