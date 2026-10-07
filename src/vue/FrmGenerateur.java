package vue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import controleur.Controle;

public class FrmGenerateur extends JFrame {
    
    private Controle controle;
    private JTextField txtData;
    private JComboBox<String> comboPolice;
    private JComboBox<String> comboCouleur;
    private JButton btnImage;
    private JLabel lblCheminImage;
    private JButton btnGenerer;
    private JLabel lblStatus;
    private JLabel lblImageQR;
    private JProgressBar progressBar;
    private String imageChoisieChemin = "";

    public FrmGenerateur(Controle controle) {
        this.controle = controle;
        
        setTitle("Générateur QR Code PDF - Personnalisable");
        setSize(500, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelHaut = new JPanel(new GridLayout(4, 2, 5, 5));
        panelHaut.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelHaut.add(new JLabel("Donnée (texte, lien) :"));
        txtData = new JTextField();
        panelHaut.add(txtData);

        panelHaut.add(new JLabel("Police du PDF :"));
        String[] polices = {"Helvetica", "Times Roman", "Courier"};
        comboPolice = new JComboBox<>(polices);
        panelHaut.add(comboPolice);

        panelHaut.add(new JLabel("Couleur du texte :"));
        String[] couleurs = {"Noir", "Bleu", "Rouge", "Vert"};
        comboCouleur = new JComboBox<>(couleurs);
        panelHaut.add(comboCouleur);

        btnImage = new JButton("Sélectionner une image...");
        lblCheminImage = new JLabel("Aucune image");
        panelHaut.add(btnImage);
        panelHaut.add(lblCheminImage);

        add(panelHaut, BorderLayout.NORTH);

        lblImageQR = new JLabel();
        lblImageQR.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblImageQR, BorderLayout.CENTER);
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setString("Prêt");
        
        JPanel panelBas = new JPanel(new BorderLayout(5, 5));
        btnGenerer = new JButton("Générer le PDF personnalisé");
        lblStatus = new JLabel("En attente de saisie...");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setForeground(Color.BLUE);
        
        panelBas.add(btnGenerer, BorderLayout.NORTH);
        panelBas.add(progressBar, BorderLayout.CENTER);
        panelBas.add(lblStatus, BorderLayout.SOUTH);
        panelBas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelBas, BorderLayout.SOUTH);

        btnImage.addActionListener(e -> choisirImage());

        btnGenerer.addActionListener(e -> {
            String data = txtData.getText();
            String police = (String) comboPolice.getSelectedItem();
            String couleur = (String) comboCouleur.getSelectedItem();
            
            if (!data.trim().isEmpty()) {
                controle.demandeGenerationAvancee(data, police, couleur, imageChoisieChemin);
            } else {
                lblStatus.setText("Erreur : le champ est vide.");
                lblStatus.setForeground(Color.RED);
            }
        });
    }

    private void choisirImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images (JPG, PNG)", "jpg", "jpeg", "png"));
        int resultat = fileChooser.showOpenDialog(this);
        if (resultat == JFileChooser.APPROVE_OPTION) {
            File fichierSelectionne = fileChooser.getSelectedFile();
            imageChoisieChemin = fichierSelectionne.getAbsolutePath();
            lblCheminImage.setText(fichierSelectionne.getName());
        } else {
            imageChoisieChemin = "";
            lblCheminImage.setText("Aucune image");
        }
    }

    public void setStatus(String message, Color color) {
        lblStatus.setText(message);
        lblStatus.setForeground(color);
    }
    
    public void afficherQRCode(String cheminImage) {
        ImageIcon icon = new ImageIcon(cheminImage);
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblImageQR.setIcon(new ImageIcon(img));
        revalidate();
        repaint();
    }

    public void setValeursProfil(String police, String couleur, String cheminImage) {
        if (police != null) comboPolice.setSelectedItem(police);
        if (couleur != null) comboCouleur.setSelectedItem(couleur);
        if (cheminImage != null && !cheminImage.trim().isEmpty()) {
            File imgFile = new File(cheminImage);
            if (imgFile.exists()) {
                this.imageChoisieChemin = cheminImage;
                this.lblCheminImage.setText(imgFile.getName());
            }
        }
    }

    public void setProgress(int value, String text) {
        progressBar.setValue(value);
        progressBar.setString(text);
    }
}