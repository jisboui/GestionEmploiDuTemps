package view;

import controller.SeanceController;
import model.Seance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class RequeteInterface extends JFrame {
    private SeanceController seanceController = new SeanceController();

    public RequeteInterface() {
        setTitle("Requêtes - Gestion des emplois du temps");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panels
        JPanel panelTop = new JPanel(new GridLayout(3, 1)); // Trois lignes pour les recherches et suppression
        JPanel panelBottom = new JPanel(new BorderLayout());

        // Title
        JLabel title = new JLabel("Requêtes", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panelTop.add(title);

        // ** Recherche par classe et matière **
        JPanel panelRecherche = new JPanel(new FlowLayout());
        JLabel lblClasse = new JLabel("Classe:");
        JTextField txtClasse = new JTextField(10);
        JLabel lblMatiere = new JLabel("Matière:");
        JTextField txtMatiere = new JTextField(10);
        JButton btnChercherSeances = new JButton("CHERCHER");

        panelRecherche.add(lblClasse);
        panelRecherche.add(txtClasse);
        panelRecherche.add(lblMatiere);
        panelRecherche.add(txtMatiere);
        panelRecherche.add(btnChercherSeances);
        panelTop.add(panelRecherche);

        // ** Suppression d'une séance **
        JPanel panelSuppression = new JPanel(new FlowLayout());
        JLabel lblSeanceID = new JLabel("ID Séance:");
        JTextField txtSeanceID = new JTextField(10);
        JButton btnSupprimerSeance = new JButton("SUPPRIMER");

        panelSuppression.add(lblSeanceID);
        panelSuppression.add(txtSeanceID);
        panelSuppression.add(btnSupprimerSeance);
        panelTop.add(panelSuppression);

        // ** Tableau pour afficher les résultats **
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Classe", "Matière", "Horaire"}, 0);
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panelBottom.add(scrollPane, BorderLayout.CENTER);

        // Ajouter les panels à la fenêtre
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.CENTER);

        // ** Actions **

        // Action pour chercher les séances par classe et matière
        btnChercherSeances.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String classe = txtClasse.getText();
                    String matiere = txtMatiere.getText();

                    // Rechercher les séances
                    List<Seance> seances = seanceController.chercherSeancesParClasseEtMatiere(classe, matiere);

                    // Effacer les anciennes données
                    tableModel.setRowCount(0);

                    // Ajouter les nouvelles données
                    for (Seance seance : seances) {
                        tableModel.addRow(new Object[]{seance.getId(), seance.getClasse(), seance.getMatiere(), seance.getHoraire()});
                    }

                    if (seances.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Aucune séance trouvée !");
                    }
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
                    int seanceID = Integer.parseInt(txtSeanceID.getText());

                    // Suppression de la séance via le contrôleur
                    boolean success = seanceController.supprimerSeance(seanceID);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Séance supprimée avec succès !");
                        txtSeanceID.setText(""); // Réinitialiser le champ
                        tableModel.setRowCount(0); // Rafraîchir la table
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur : ID non trouvé !");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un ID valide !");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                }
            }
        });
    }
}
