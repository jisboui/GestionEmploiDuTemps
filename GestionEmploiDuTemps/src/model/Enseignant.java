package model;

public class Enseignant {
    private int id;
    private String nom;
    private String contact;

    // Constructeurs
    public Enseignant() {}

    public Enseignant(int id, String nom, String contact) {
        this.id = id;
        this.nom = nom;
        this.contact = contact;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
