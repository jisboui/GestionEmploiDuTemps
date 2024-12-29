package controller;

import model.Enseignant;
import model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnseignantController {

    public void ajouterEnseignant(Enseignant enseignant) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO enseignant (nom, contact) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, enseignant.getNom());
        stmt.setString(2, enseignant.getContact());
        stmt.executeUpdate();
    }

    public void modifierEnseignant(Enseignant enseignant) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE enseignant SET nom = ?, contact = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, enseignant.getNom());
        stmt.setString(2, enseignant.getContact());
        stmt.setInt(3, enseignant.getId());
        stmt.executeUpdate();
    }

    public void supprimerEnseignant(int id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "DELETE FROM enseignant WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Enseignant chercherEnseignantParNom(String nom) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM enseignant WHERE nom = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, nom);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Enseignant(rs.getInt("id"), rs.getString("nom"), rs.getString("contact"));
        }
        return null;
    }
}
