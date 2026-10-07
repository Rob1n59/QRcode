package controleur;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.util.Properties;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import modele.GenerateurQR;
import modele.GenerateurPDF;
import modele.GestionProfil;
import vue.FrmGenerateur;

public class Controle {
    
    private FrmGenerateur frmGenerateur;
    private GenerateurQR generateurQR;
    private GenerateurPDF generateurPDF;
    private GestionProfil gestionProfil;

    public Controle() {
        generateurQR = new GenerateurQR();
        generateurPDF = new GenerateurPDF();
        gestionProfil = new GestionProfil();
        
        frmGenerateur = new FrmGenerateur(this);
        chargerProfilUtilisateur();
        frmGenerateur.setVisible(true);
    }

    private void chargerProfilUtilisateur() {
        Properties props = gestionProfil.chargerProfil();
        String police = props.getProperty("police");
        String couleur = props.getProperty("couleur");
        String chemin = props.getProperty("cheminImage");
        frmGenerateur.setValeursProfil(police, couleur, chemin);
    }

    public void demandeGeneration(String data) {
    }

    public void demandeGenerationAvancee(String data, String police, String couleur, String cheminImageAjoutee) {
        frmGenerateur.setStatus("Génération en cours...", Color.ORANGE);
        frmGenerateur.setProgress(10, "Sauvegarde du profil...");
        
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            protected Void doInBackground() throws Exception {
                gestionProfil.sauvegarderProfil(police, couleur, cheminImageAjoutee);
                setProgress(30);
                Thread.sleep(300); 
                
                String imagePath = "qrcode.png";
                String pdfPath = "resultat.pdf";
                
                setProgress(50);
                generateurQR.creerQRCode(data, imagePath);
                Thread.sleep(300);
                
                setProgress(80);
                generateurPDF.creerPDFAvance(data, imagePath, pdfPath, police, couleur, cheminImageAjoutee);
                
                setProgress(100);
                return null;
            }

            protected void process(java.util.List<Integer> chunks) {
                int derniereValeur = chunks.get(chunks.size() - 1);
                frmGenerateur.setProgress(derniereValeur, derniereValeur + "%");
            }

            protected void done() {
                try {
                    get(); 
                    frmGenerateur.afficherQRCode("qrcode.png");
                    frmGenerateur.setStatus("PDF généré avec succès !", new Color(0, 150, 0));
                    frmGenerateur.setProgress(100, "Terminé");
                    
                    File pdfFile = new File("resultat.pdf");
                    if (Desktop.isDesktopSupported() && pdfFile.exists()) {
                        Desktop.getDesktop().open(pdfFile);
                    }
                } catch (Exception e) {
                    frmGenerateur.setStatus("Erreur lors de la génération : " + e.getMessage(), Color.RED);
                    frmGenerateur.setProgress(0, "Erreur");
                }
            }
        };
        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Controle());
    }
}