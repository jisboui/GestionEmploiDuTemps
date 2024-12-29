package controllers;

import utils.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherController {

    @FXML
    private TextField tfMatricule, tfNom, tfContact;
    
    @FXML
    private Button btnEnregistrer, btnChercher, btnModifier, btnSupprimer;
    
    // Enregistrer un enseignant
    @FXML
    private void enregistrerEnseignant() {
        String matricule = tfMatricule.getText();
        String nom = tfNom.getText();
        String contact = tfContact.getText();

        String query = "INSERT INTO enseignants (matricule, nom, contact) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, matricule);
            statement.setString(2, nom);
            statement.setString(3, contact);
            
            statement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Enseignant enregistré avec succès!");
            alert.showAndWait();
            
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de l'enregistrement.");
            alert.showAndWait();
        }
    }

    // Chercher un enseignant par nom
    @FXML
    private void chercherEnseignant() {
        String nom = tfNom.getText();
        String query = "SELECT * FROM enseignants WHERE nom LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, "%" + nom + "%");
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                tfMatricule.setText(resultSet.getString("matricule"));
                tfContact.setText(resultSet.getString("contact"));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Enseignant non trouvé.");
                alert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la recherche.");
            alert.showAndWait();
        }
    }

    // Modifier un enseignant
    @FXML
    private void modifierEnseignant() {
        String matricule = tfMatricule.getText();
        String nom = tfNom.getText();
        String contact = tfContact.getText();
        
        String query = "UPDATE enseignants SET nom = ?, contact = ? WHERE matricule = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, nom);
            statement.setString(2, contact);
            statement.setString(3, matricule);
            
            statement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Enseignant modifié avec succès!");
            alert.showAndWait();
            
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la modification.");
            alert.showAndWait();
        }
    }

    // Supprimer un enseignant
    @FXML
    private void supprimerEnseignant() {
        String matricule = tfMatricule.getText();
        
        String query = "DELETE FROM enseignants WHERE matricule = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, matricule);
            statement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Enseignant supprimé avec succès!");
            alert.showAndWait();
            
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la suppression.");
            alert.showAndWait();
        }
    }
}
