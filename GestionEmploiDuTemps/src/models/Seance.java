package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Seance {

    private StringProperty horaire;
    private StringProperty enseignant;

    // Constructeur
    public Seance(String horaire, String enseignant) {
        this.horaire = new SimpleStringProperty(horaire);
        this.enseignant = new SimpleStringProperty(enseignant);
    }

    // Getters et setters pour les propriétés
    public String getHoraire() {
        return horaire.get();
    }

    public StringProperty horaireProperty() {
        return horaire;
    }

    public String getEnseignant() {
        return enseignant.get();
    }

    public StringProperty enseignantProperty() {
        return enseignant;
    }
}
