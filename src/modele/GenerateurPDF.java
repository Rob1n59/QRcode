package modele;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class GenerateurPDF {

    public void creerPDF(String texte, String cheminImageQR, String nomFichierPDF) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(nomFichierPDF));
        document.open();
        
        document.add(new Paragraph("Donnee saisie : " + texte));
        document.add(new Paragraph(" "));
        
        Image imageQR = Image.getInstance(cheminImageQR);
        imageQR.setAlignment(Image.ALIGN_CENTER);
        document.add(imageQR);
        
        document.close();
    }
}