package vue;

import javax.swing.*;
import java.awt.*;
import controleur.Controle;

public class FrmGenerateur extends JFrame {
    
    private Controle controle;
    private JTextField txtData;
    private JButton btnGenerer;
    private JLabel lblStatus;

    public FrmGenerateur(Controle controle) {
        this.controle = controle;
        
        setTitle("Générateur QR Code PDF");
        setSize(450, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSaisie = new JPanel();
        panelSaisie.add(new JLabel("Donnée (texte, lien) :"));
        txtData = new JTextField(20);
        panelSaisie.add(txtData);

        btnGenerer = new JButton("Générer le PDF");
        
        lblStatus = new JLabel("En attente de saisie...");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setForeground(Color.BLUE);

        add(panelSaisie, BorderLayout.NORTH);
        add(btnGenerer, BorderLayout.CENTER);
        add(lblStatus, BorderLayout.SOUTH);

        btnGenerer.addActionListener(e -> {
            String data = txtData.getText();
            if (!data.trim().isEmpty()) {
                controle.demandeGeneration(data);
            } else {
                lblStatus.setText("Erreur : le champ est vide.");
                lblStatus.setForeground(Color.RED);
            }
        });
    }

    public void setStatus(String message, Color color) {
        lblStatus.setText(message);
        lblStatus.setForeground(color);
    }
}