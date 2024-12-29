package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/emploidutemps_db"; // Modifiez avec vos informations
    private static final String USER = "root"; // Votre utilisateur MySQL
    private static final String PASSWORD = ""; // Votre mot de passe MySQL
    
    public static Connection getConnection() throws SQLException {
        try {
            // Chargement du driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connexion à la base de données
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur de connexion à la base de données.");
        }
    }
}
