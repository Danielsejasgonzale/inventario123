package com.mycompany.empresa;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createTables() {
        
        
        String createUsuariosSQL = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
                contrasena VARCHAR(255),
                rol VARCHAR(10) NOT NULL CHECK (rol IN ('admin', 'usuario')),
                pregunta_seguridad VARCHAR(255),
                respuesta_seguridad VARCHAR(255)
            );
        """;

        String createCategoriasSQL = """
            CREATE TABLE IF NOT EXISTS Categorias (
                CategoríaID INT PRIMARY KEY AUTO_INCREMENT,
                Nombre VARCHAR(100) NOT NULL
            );
        """;

        String createProveedoresSQL = """
            CREATE TABLE IF NOT EXISTS Proveedores (
                ProveedorID INT PRIMARY KEY AUTO_INCREMENT,
                Nombre VARCHAR(100) NOT NULL,
                Teléfono VARCHAR(20),
                Dirección VARCHAR(255)
            );
        """;

        String createProductosSQL = """
            CREATE TABLE IF NOT EXISTS Productos (
                ProductoID INT PRIMARY KEY AUTO_INCREMENT,
                Nombre VARCHAR(100) NOT NULL,
                Descripción TEXT,
                CategoríaID INT,
                ProveedorID INT,
                PrecioUnitario DECIMAL(10, 2),
                FOREIGN KEY (CategoríaID) REFERENCES Categorias(CategoríaID),
                FOREIGN KEY (ProveedorID) REFERENCES Proveedores(ProveedorID)
            );
        """;

        String createMovimientosSQL = """
            CREATE TABLE IF NOT EXISTS Movimientos (
                MovimientoID INT PRIMARY KEY AUTO_INCREMENT,
                ProductoID INT,
                Fecha DATE,
                Cantidad INT,
                CantidadInicial INT,
                TipoMovimiento ENUM('Entrada', 'Salida'),
                Descripción TEXT,
                FOREIGN KEY (ProductoID) REFERENCES Productos(ProductoID)
            );
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            // Crear tabla usuarios
            statement.execute(createUsuariosSQL);
            // Crear tabla Categorias
            statement.execute(createCategoriasSQL);
            // Crear tabla Proveedores
            statement.execute(createProveedoresSQL);
            // Crear tabla Productos
            statement.execute(createProductosSQL);
            // Crear tabla Movimientos
            statement.execute(createMovimientosSQL);

            System.out.println("Tablas creadas o ya existen.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

