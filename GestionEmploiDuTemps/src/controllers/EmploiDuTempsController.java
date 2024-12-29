package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Seance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmploiDuTempsController {

    @FXML
    private ComboBox<String> cbClasse, cbMatiere;
    @FXML
    private TableView<Seance> tableSeances;
    @FXML
    private TableColumn<Seance, String> colHoraire, colEnseignant;
    
    private ObservableList<Seance> seanceList;

    @FXML
    private void initialize() {
        // Initialisation des ComboBox avec des données dynamiques
        cbClasse.getItems().addAll("Classe 1", "Classe 2");
        cbMatiere.getItems().addAll("Matière 1", "Matière 2");

        // Initialisation de la TableView
        seanceList = FXCollections.observableArrayList();
        tableSeances.setItems(seanceList);
        
        // Définir les colonnes de la TableView
        colHoraire.setCellValueFactory(cellData -> cellData.getValue().horaireProperty());
        colEnseignant.setCellValueFactory(cellData -> cellData.getValue().enseignantProperty());
    }

    // Chercher les séances d'une matière dans une classe
    @FXML
    private void chercherSeances() {
        String classe = cbClasse.getValue();
        String matiere = cbMatiere.getValue();
        
        // Charger les séances de la base de données en fonction de la classe et de la matière
        // Cette méthode doit être implémentée pour récupérer les données correspondantes
    }
}
