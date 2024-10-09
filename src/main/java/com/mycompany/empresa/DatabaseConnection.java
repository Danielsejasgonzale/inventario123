package com.mycompany.empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseConnection {

    private static final String LOCAL_URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String USER_LOCAL = "root"; // Cambia esto por tu usuario local
    private static final String PASSWORD_LOCAL = ""; // Cambia esto por tu contraseña local

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(LOCAL_URL, USER_LOCAL, PASSWORD_LOCAL);
            createTablesIfNotExists(connection); // Crear tablas si no existen
        } catch (SQLException e) {
            showAlert("No es posible establecer la conexión con la base de datos.");
            logError(e);
        }

        return connection;
    }

    private static void createTablesIfNotExists(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        // Crear tabla Categorias
        String createCategoriasTable = "CREATE TABLE IF NOT EXISTS Categorias ("
                + "CategoríaID INT PRIMARY KEY AUTO_INCREMENT,"
                + "Nombre VARCHAR(100) NOT NULL)";
        
        // Crear tabla Proveedores
        String createProveedoresTable = "CREATE TABLE IF NOT EXISTS Proveedores ("
                + "ProveedorID INT PRIMARY KEY AUTO_INCREMENT,"
                + "Nombre VARCHAR(100) NOT NULL,"
                + "Teléfono VARCHAR(20),"
                + "Dirección VARCHAR(255))";
        
        // Crear tabla Productos
        String createProductosTable = "CREATE TABLE IF NOT EXISTS Productos ("
                + "ProductoID INT PRIMARY KEY AUTO_INCREMENT,"
                + "Nombre VARCHAR(100) NOT NULL,"
                + "Descripción TEXT,"
                + "CategoríaID INT,"
                + "ProveedorID INT,"
                + "PrecioUnitario DECIMAL(10, 2),"
                + "FOREIGN KEY (CategoríaID) REFERENCES Categorias(CategoríaID),"
                + "FOREIGN KEY (ProveedorID) REFERENCES Proveedores(ProveedorID))";
        
        // Crear tabla Movimientos
        String createMovimientosTable = "CREATE TABLE IF NOT EXISTS Movimientos ("
                + "MovimientoID INT PRIMARY KEY AUTO_INCREMENT,"
                + "ProductoID INT,"
                + "Fecha DATE,"
                + "Cantidad INT,"
                + "CantidadInicial INT,"
                + "TipoMovimiento ENUM('Entrada', 'Salida'),"
                + "Descripción TEXT,"
                + "FOREIGN KEY (ProductoID) REFERENCES Productos(ProductoID))";
        
        // Crear tabla usuarios
        String createUsuariosTable = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "nombre_usuario VARCHAR(50) NOT NULL UNIQUE,"
                + "contrasena VARCHAR(255),"
                + "rol VARCHAR(10) NOT NULL CHECK (rol IN ('admin', 'usuario')),"
                + "pregunta_seguridad VARCHAR(255),"
                + "respuesta_seguridad VARCHAR(255))";

        // Ejecutar las consultas para crear las tablas
        statement.executeUpdate(createCategoriasTable);
        statement.executeUpdate(createProveedoresTable);
        statement.executeUpdate(createProductosTable);
        statement.executeUpdate(createMovimientosTable);
        statement.executeUpdate(createUsuariosTable);

        statement.close();
    }

    private static void showAlert(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void logError(Exception e) {
        e.printStackTrace();
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            closeConnection(connection);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logError(e);
            }
        }
    }
}
