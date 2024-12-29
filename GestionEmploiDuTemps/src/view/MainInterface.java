package view;

import controller.EnseignantController;
import controller.SeanceController;
import model.Enseignant;
import model.Seance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainInterface extends JFrame {
    private EnseignantController enseignantController = new EnseignantController();
    private SeanceController seanceController = new SeanceController();

    public MainInterface() {
        setTitle("Gestion des emplois du temps - ISI/UVT");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panels
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel(new GridLayout(1, 2));

        // Top Panel: Title
        JLabel title = new JLabel("Gestion des emplois du temps");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panelTop.add(title);

        // Bottom Panels: Formulaires
        JPanel panelEnseignants = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel panelSeances = new JPanel(new GridLayout(5, 2, 10, 10));

        // ** Formulaire Enseignants **
        JLabel lblNomEnseignant = new JLabel("Nom de l'enseignant:");
        JTextField txtNomEnseignant = new JTextField();
        JLabel lblContactEnseignant = new JLabel("Contact de l'enseignant:");
        JTextField txtContactEnseignant = new JTextField();
        JLabel lblIdEnseignant = new JLabel("ID de l'enseignant (pour modifier/supprimer):");
        JTextField txtIdEnseignant = new JTextField();
        JButton btnChercherEnseignant = new JButton("CHERCHER");
        JButton btnEnregistrerEnseignant = new JButton("ENREGISTRER");
        JButton btnModifierEnseignant = new JButton("MODIFIER");
        JButton btnSupprimerEnseignant = new JButton("SUPPRIMER");

        panelEnseignants.setBorder(BorderFactory.createTitledBorder("Enseignants"));
        panelEnseignants.add(lblNomEnseignant);
        panelEnseignants.add(txtNomEnseignant);
        panelEnseignants.add(lblContactEnseignant);
        panelEnseignants.add(txtContactEnseignant);
        panelEnseignants.add(lblIdEnseignant);
        panelEnseignants.add(txtIdEnseignant);
        panelEnseignants.add(btnChercherEnseignant);
        panelEnseignants.add(btnEnregistrerEnseignant);
        panelEnseignants.add(btnModifierEnseignant);
        panelEnseignants.add(btnSupprimerEnseignant);

        // ** Formulaire Séances **
        JLabel lblClasse = new JLabel("Classe:");
        JTextField txtClasse = new JTextField();
        JLabel lblMatiere = new JLabel("Matière:");
        JTextField txtMatiere = new JTextField();
        JLabel lblHoraire = new JLabel("Horaire:");
        JTextField txtHoraire = new JTextField();
        JLabel lblIdSeance = new JLabel("ID de la séance (pour supprimer):");
        JTextField txtIdSeance = new JTextField();
        JButton btnEnregistrerSeance = new JButton("ENREGISTRER");
        JButton btnSupprimerSeance = new JButton("SUPPRIMER");
        JButton btnRequetes = new JButton("REQUETES");

        panelSeances.setBorder(BorderFactory.createTitledBorder("Séances de cours"));
        panelSeances.add(lblClasse);
        panelSeances.add(txtClasse);
        panelSeances.add(lblMatiere);
        panelSeances.add(txtMatiere);
        panelSeances.add(lblHoraire);
        panelSeances.add(txtHoraire);
        panelSeances.add(lblIdSeance);
        panelSeances.add(txtIdSeance);
        panelSeances.add(btnEnregistrerSeance);
        panelSeances.add(btnSupprimerSeance);
        panelSeances.add(btnRequetes);

        // Ajouter les panels au Frame
        panelBottom.add(panelEnseignants);
        panelBottom.add(panelSeances);

        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.CENTER);

        // ** Action Listeners **

        // Action pour enregistrer un enseignant
        btnEnregistrerEnseignant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nom = txtNomEnseignant.getText();
                    String contact = txtContactEnseignant.getText();
                    Enseignant enseignant = new Enseignant(0, nom, contact);
                    enseignantController.ajouterEnseignant(enseignant);
                    JOptionPane.showMessageDialog(null, "Enseignant enregistré avec succès !");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour chercher un enseignant par nom
        btnChercherEnseignant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nom = txtNomEnseignant.getText();
                    Enseignant enseignant = enseignantController.chercherEnseignantParNom(nom);
                    if (enseignant != null) {
                        txtIdEnseignant.setText(String.valueOf(enseignant.getId()));
                        txtContactEnseignant.setText(enseignant.getContact());
                        JOptionPane.showMessageDialog(null, "Enseignant trouvé !");
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun enseignant trouvé !");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour modifier un enseignant
        btnModifierEnseignant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtIdEnseignant.getText());
                    String nom = txtNomEnseignant.getText();
                    String contact = txtContactEnseignant.getText();
                    Enseignant enseignant = new Enseignant(id, nom, contact);
                    enseignantController.modifierEnseignant(enseignant);
                    JOptionPane.showMessageDialog(null, "Enseignant modifié avec succès !");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour supprimer un enseignant
        btnSupprimerEnseignant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtIdEnseignant.getText());
                    enseignantController.supprimerEnseignant(id);
                    JOptionPane.showMessageDialog(null, "Enseignant supprimé avec succès !");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour enregistrer une séance
        btnEnregistrerSeance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String classe = txtClasse.getText();
                    String matiere = txtMatiere.getText();
                    String horaire = txtHoraire.getText();
                    Seance seance = new Seance(0, classe, matiere, horaire);
                    seanceController.ajouterSeance(seance);
                    JOptionPane.showMessageDialog(null, "Séance enregistrée avec succès !");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour supprimer une séance
        btnSupprimerSeance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtIdSeance.getText());
                    seanceController.supprimerSeance(id);
                    JOptionPane.showMessageDialog(null, "Séance supprimée avec succès !");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });

        // Action pour accéder à l'interface des requêtes
        btnRequetes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequeteInterface requeteInterface = new RequeteInterface();
                requeteInterface.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainInterface mainInterface = new MainInterface();
            mainInterface.setVisible(true);
        });
    }
}
