package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RequestController {

    public ComboBox<String> classSelector;

    public void initialize() {
        // Populate the class selector
        classSelector.getItems().addAll("6ème", "5ème", "4ème", "3ème", "2nde", "1ère", "Terminale");
    }

    public void showCourseSessions(ActionEvent event) {
        String selectedClass = classSelector.getValue();
        if (selectedClass != null) {
            System.out.println("Displaying course sessions for class: " + selectedClass);
        } else {
            System.out.println("Please select a class.");
        }
    }

    public void goBackToMain(ActionEvent event) {
        try {
            // Load the MainView
            Parent mainView = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
            Stage stage = (Stage) classSelector.getScene().getWindow();
            stage.setScene(new Scene(mainView));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
