package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    public TextField teacherNameField;
    public TextField teacherContactField;

    public void registerTeacher(ActionEvent event) {
        String name = teacherNameField.getText();
        String contact = teacherContactField.getText();

        if (!name.isEmpty() && !contact.isEmpty()) {
            // Call database method to register teacher
            System.out.println("Teacher registered: " + name + ", " + contact);
        } else {
            System.out.println("Please fill in all fields.");
        }
    }

    public void openRequestView(ActionEvent event) {
        try {
            // Load the RequestView
            Parent requestView = FXMLLoader.load(getClass().getResource("/views/RequestView.fxml"));
            Stage stage = (Stage) teacherNameField.getScene().getWindow();
            stage.setScene(new Scene(requestView));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
