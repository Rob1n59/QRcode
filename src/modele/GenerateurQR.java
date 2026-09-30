package modele;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class GenerateurQR {

    public String creerQRCode(String data, String nomFichier) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 250, 250);
        Path path = FileSystems.getDefault().getPath(nomFichier);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return nomFichier;
    }
}