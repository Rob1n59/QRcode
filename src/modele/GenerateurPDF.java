package modele;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.File;

public class GenerateurPDF {

    public void creerPDF(String texte, String cheminImageQR, String nomFichierPDF) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(nomFichierPDF));
        document.open();
        
        Image imageQR = Image.getInstance(cheminImageQR);
        imageQR.setAlignment(Image.ALIGN_CENTER);
        document.add(imageQR);
        
        document.close();
    }

    public void creerPDFAvance(String texte, String cheminImageQR, String nomFichierPDF, String policeNom, String couleurNom, String cheminImageAjoutee) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(nomFichierPDF));
        document.open();
        
        BaseColor couleur = BaseColor.BLACK;
        if ("Bleu".equals(couleurNom)) couleur = BaseColor.BLUE;
        else if ("Rouge".equals(couleurNom)) couleur = BaseColor.RED;
        else if ("Vert".equals(couleurNom)) couleur = BaseColor.GREEN;
        
        String fontName = FontFactory.HELVETICA;
        if ("Times Roman".equals(policeNom)) fontName = FontFactory.TIMES_ROMAN;
        else if ("Courier".equals(policeNom)) fontName = FontFactory.COURIER;
        
        Font font = FontFactory.getFont(fontName, 18, Font.BOLD, couleur);
        
        Paragraph titre = new Paragraph("QRcode : " + texte, font);
        titre.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titre);
        document.add(new Paragraph(" "));
        
        Image imageQR = Image.getInstance(cheminImageQR);
        imageQR.setAlignment(Image.ALIGN_CENTER);
        document.add(imageQR);
        
        if (cheminImageAjoutee != null && !cheminImageAjoutee.isEmpty()) {
            File imgFile = new File(cheminImageAjoutee);
            if (imgFile.exists()) {
                document.add(new Paragraph(" "));
                Image imageUser = Image.getInstance(cheminImageAjoutee);
                imageUser.setAlignment(Image.ALIGN_CENTER);
                imageUser.scaleToFit(250, 250);
                document.add(imageUser);
            }
        }
        
        document.close();
    }
}