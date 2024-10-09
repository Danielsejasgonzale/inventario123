package com.mycompany.empresa;

import javax.swing.*;
import java.awt.*;

public class Empresa {
    public static void main(String[] args) {
        // Aplicar estilos globales usando UIManager
        setGlobalStyles();

        // Iniciar la interfaz principal
        Acceso a = new Acceso();
        a.setVisible(true);
        
    }
    

    private static void setGlobalStyles() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Estilo para JTable y su encabezado
            UIManager.put("Table.background", Color.WHITE);  // Fondo blanco para las filas
            UIManager.put("Table.foreground", Color.BLACK);  // Texto en negro
            UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 14));  // Fuente más grande
            UIManager.put("Table.gridColor", Color.WHITE);  // Sin grillas visibles
            UIManager.put("Table.selectionBackground", new Color(204, 229, 255));  // Selección en azul claro
            UIManager.put("Table.selectionForeground", Color.BLACK);  // Texto en negro al seleccionar
            UIManager.put("TableHeader.background", new Color(0, 102, 204));  // Encabezado en azul oscuro
            UIManager.put("TableHeader.foreground", Color.BLACK);  // Texto en negro en el encabezado
            UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 14));  // Fuente más grande y negrita
            UIManager.put("Table.showGrid", false);  // Sin grillas visibles
            UIManager.put("Table.intercellSpacing", new Dimension(10, 10));  // Aumentar espacio intercelular
            UIManager.put("Table.rowHeight", 40);  // Ajusta la altura de la fila

            // Estilo para el fondo de las filas alternas
            UIManager.put("Table.alternateRowColor", new Color(240, 248, 255));  // Azul claro para filas alternas
            UIManager.put("Table.cellSelectionBackground", new Color(204, 229, 255));  // Fondo de celda seleccionado en azul claro

            // Estilo para el fondo y borde del JScrollPane
            UIManager.put("ScrollPane.background", Color.WHITE);  // Fondo blanco
            UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());  // Sin borde

            // Estilo para los botones
            UIManager.put("Button.border", BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLUE, 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
            UIManager.put("Button.focus", BorderFactory.createEmptyBorder());
            UIManager.put("Button.background", Color.WHITE);
            UIManager.put("Button.foreground", Color.BLACK);
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 12));
            UIManager.put("Button.border", BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLUE, 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
