package controller;

import model.Seance;
import model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceController {

    public void ajouterSeance(Seance seance) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO seance (classe, matiere, horaire) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, seance.getClasse());
        stmt.setString(2, seance.getMatiere());
        stmt.setString(3, seance.getHoraire());
        stmt.executeUpdate();
    }

    public boolean supprimerSeance(int id) throws SQLException {
        String query = "DELETE FROM seance WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Renvoie vrai si une ligne a été supprimée
        }}

    public List<Seance> chercherSeancesParClasseEtMatiere(String classe, String matiere) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM seance WHERE classe = ? AND matiere = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, classe);
        stmt.setString(2, matiere);
        ResultSet rs = stmt.executeQuery();

        List<Seance> seances = new ArrayList<>();
        while (rs.next()) {
            seances.add(new Seance(rs.getInt("id"), rs.getString("classe"), rs.getString("matiere"), rs.getString("horaire")));
        }
        return seances;
    }
}
