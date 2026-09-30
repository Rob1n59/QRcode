package test;


import org.junit.jupiter.api.Test;

import modele.GenerateurPDF;
import modele.GenerateurQR;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;

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
}