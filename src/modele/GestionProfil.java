package modele;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class GestionProfil {
    
    private static final String FICHIER_PROFIL = "profil.properties";
    private Properties properties;

    public GestionProfil() {
        properties = new Properties();
    }

    public void sauvegarderProfil(String police, String couleur, String cheminImage) {
        try {
            properties.setProperty("police", police);
            properties.setProperty("couleur", couleur);
            properties.setProperty("cheminImage", cheminImage != null ? cheminImage : "");
            properties.store(new FileOutputStream(FICHIER_PROFIL), null);
        } catch (Exception e) {
        }
    }

    public Properties chargerProfil() {
        try {
            properties.load(new FileInputStream(FICHIER_PROFIL));
        } catch (Exception e) {
        }
        return properties;
    }
}