package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher {
    private StringProperty matricule;
    private StringProperty nom;
    private StringProperty contact;

    public Teacher(String matricule, String nom, String contact) {
        this.matricule = new SimpleStringProperty(matricule);
        this.nom = new SimpleStringProperty(nom);
        this.contact = new SimpleStringProperty(contact);
    }

    public String getMatricule() {
        return matricule.get();
    }

    public StringProperty matriculeProperty() {
        return matricule;
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public String getContact() {
        return contact.get();
    }

    public StringProperty contactProperty() {
        return contact;
    }
}
