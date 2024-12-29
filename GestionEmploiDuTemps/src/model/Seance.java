package model;

public class Seance {
    private int id;
    private String classe;
    private String matiere;
    private String horaire;

    // Constructeurs
    public Seance() {}

    public Seance(int id, String classe, String matiere, String horaire) {
        this.id = id;
        this.classe = classe;
        this.matiere = matiere;
        this.horaire = horaire;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }

    public String getMatiere() { return matiere; }
    public void setMatiere(String matiere) { this.matiere = matiere; }

    public String getHoraire() { return horaire; }
    public void setHoraire(String horaire) { this.horaire = horaire; }
}
