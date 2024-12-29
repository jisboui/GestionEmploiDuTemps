package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SeanceCoursController {

    @FXML
    private TextField tfMatiere, tfClasse, tfHoraire;
    @FXML
    private ComboBox<String> cbEnseignant;
    @FXML
    private Button btnEnregistrerSeance;

    // Enregistrer une séance de cours
    @FXML
    private void enregistrerSeance() {
        String matiere = tfMatiere.getText();
        String classe = tfClasse.getText();
        String horaire = tfHoraire.getText();
        String enseignant = cbEnseignant.getValue();
        
        // Implémentez la logique pour enregistrer dans la base de données
    }

    // Initialiser la ComboBox avec les enseignants
    @FXML
    private void initialize() {
        cbEnseignant.getItems().addAll("Enseignant 1", "Enseignant 2", "Enseignant 3"); // À remplacer par une liste d'enseignants dynamiques depuis la base
    }
}
