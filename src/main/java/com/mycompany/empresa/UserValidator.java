package com.mycompany.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserValidator {

    // Método para comprobar si existe al menos un usuario con rol de admin
    public static boolean hasAdminUser() {
        String query = "SELECT COUNT(*) AS total FROM usuarios WHERE rol = 'admin'";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("total");
                return count > 0;  // Si hay al menos un admin, devolver true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Si no hay admin o hay un error, devolver false
    }
}
