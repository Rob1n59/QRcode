package controleur;

import java.awt.Color;

import javax.swing.SwingUtilities;

import vue.FrmGenerateur;

public class Controle {
    
    private FrmGenerateur frmGenerateur;

    public Controle() {
        frmGenerateur = new FrmGenerateur(this);
        frmGenerateur.setVisible(true);
    }

    public void demandeGeneration(String data) {
        frmGenerateur.setStatus("Génération en cours...", Color.ORANGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Controle());
    }
}