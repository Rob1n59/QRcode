package controleur;

import java.awt.Color;
import javax.swing.SwingUtilities;
import modele.GenerateurQR;
import modele.GenerateurPDF;
import vue.FrmGenerateur;

public class Controle {
    
    private FrmGenerateur frmGenerateur;
    private GenerateurQR generateurQR;
    private GenerateurPDF generateurPDF;

    public Controle() {
        generateurQR = new GenerateurQR();
        generateurPDF = new GenerateurPDF();
        frmGenerateur = new FrmGenerateur(this);
        frmGenerateur.setVisible(true);
    }

    public void demandeGeneration(String data) {
        frmGenerateur.setStatus("Génération en cours...", Color.ORANGE);
        try {
            String imagePath = "qrcode.png";
            String pdfPath = "resultat.pdf";
            
            generateurQR.creerQRCode(data, imagePath);
            generateurPDF.creerPDF(data, imagePath, pdfPath);
            
            frmGenerateur.setStatus("PDF généré avec succès !", new Color(0, 150, 0));
        } catch (Exception e) {
            frmGenerateur.setStatus("Erreur lors de la génération.", Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Controle());
    }
}