package test;

import org.junit.jupiter.api.Test;

import modele.GenerateurPDF;
import modele.GenerateurQR;
import modele.GestionProfil;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.util.Properties;

public class ModeleTest {

    @Test
    public void testGenerateurQR() throws Exception {
        GenerateurQR qr = new GenerateurQR();
        String nomFichier = "test_qrcode.png";
        
        qr.creerQRCode("https://github.com", nomFichier);
        
        File fichier = new File(nomFichier);
        assertTrue(fichier.exists());
        
        fichier.delete();
    }

    @Test
    public void testGenerateurPDF() throws Exception {
        GenerateurQR qr = new GenerateurQR();
        GenerateurPDF pdf = new GenerateurPDF();
        
        String imgQR = "test_qrcode_pdf.png";
        String docPDF = "test_resultat.pdf";
        
        qr.creerQRCode("https://github.com", imgQR);
        pdf.creerPDF("https://github.com", imgQR, docPDF);
        
        File fichierPDF = new File(docPDF);
        assertTrue(fichierPDF.exists());
        
        new File(imgQR).delete();
        fichierPDF.delete();
    }

    @Test
    public void testGenerateurPDFAvance() throws Exception {
        GenerateurQR qr = new GenerateurQR();
        GenerateurPDF pdf = new GenerateurPDF();
        
        String imgQR = "test_qrcode_pdf_avance.png";
        String docPDF = "test_resultat_avance.pdf";
        
        qr.creerQRCode("https://github.com", imgQR);
        pdf.creerPDFAvance("https://github.com", imgQR, docPDF, "Courier", "Rouge", "");
        
        File fichierPDF = new File(docPDF);
        assertTrue(fichierPDF.exists());
        
        new File(imgQR).delete();
        fichierPDF.delete();
    }

    @Test
    public void testGestionProfil() {
        GestionProfil profil = new GestionProfil();
        
        profil.sauvegarderProfil("Courier", "Rouge", "chemin/test/image.png");
        Properties props = profil.chargerProfil();
        
        assertEquals("Courier", props.getProperty("police"));
        assertEquals("Rouge", props.getProperty("couleur"));
        assertEquals("chemin/test/image.png", props.getProperty("cheminImage"));
        
        new File("profil.properties").delete();
    }
}