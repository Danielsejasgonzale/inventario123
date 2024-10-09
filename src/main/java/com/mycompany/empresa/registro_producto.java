
package com.mycompany.empresa;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class registro_producto extends menu {
    private List<String> data;
    private List<String> data2;
    private JPopupMenu popupMenu;
    private JMenuItem insertarItem, editarItem, eliminarItem, buscarItem, actualizarItem;
    

    


    public registro_producto() {
        initComponents();
        data = new ArrayList<>();
        data2 = new ArrayList<>();
        loadComboBoxData();
        loadProveedorComboBoxData();
        cargarDatosProductos();
        createPopupMenu();
        addPopupToTable();
        deshabilitar();
 // Centrar la ventana en la pantalla
        centerWindow(this);
    }
    
    private static void centerWindow(JFrame window) {
    window.pack();  // Ajusta el tamaño de la ventana al contenido (opcional)
    window.setLocationRelativeTo(null);  // Centra la ventana en la pantalla
}

    
    private void deshabilitar(){
        // Inabhilita
        jDesktopPane1.setEnabled(false);
        Nombre.setEnabled(false);
        Descripcion.setEnabled(false); 
        precio.setEnabled(false); 
        proveedor.setEnabled(false);
        jTextField1.setEnabled(false); 
        jTextField2.setEnabled(false); 
        categoria.setEnabled(false); 
        guardar.setEnabled(false); 
        cancelar.setEnabled(false);
        editar.setEnabled(false);
        Nombre.setText("");
        Descripcion.setText("");
        precio.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        
    };
    
    private void habilitar(){
        jDesktopPane1.setEnabled(true);
        Nombre.setEnabled(true);
        Descripcion.setEnabled(true); 
        precio.setEnabled(true); 
        proveedor.setEnabled(true);
        jTextField1.setEnabled(true); 
        jTextField2.setEnabled(true); 
        categoria.setEnabled(true);  
        cancelar.setEnabled(true);
    };
 
    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        insertarItem = new JMenuItem("Insertar");
        editarItem = new JMenuItem("Editar");
        eliminarItem = new JMenuItem("Eliminar");
        buscarItem = new JMenuItem("Buscar");
        actualizarItem = new JMenuItem("Actualizar");

        popupMenu.add(insertarItem);
        popupMenu.add(editarItem);
        popupMenu.add(eliminarItem);
        popupMenu.add(buscarItem);
        popupMenu.add(actualizarItem);


        buscarItem.addActionListener(e -> buscarProducto());
        actualizarItem.addActionListener(e -> cargarDatosProductos());
        insertarItem.addActionListener(e -> habilitarPaneYBotonguardar());
        editarItem.addActionListener(e -> cargarDatosSeleccionados());
        eliminarItem.addActionListener(e -> eliminarProducto());
        
    }

    private void addPopupToTable() {
        producto.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            private void showPopup(MouseEvent e) {
                int row = producto.rowAtPoint(e.getPoint());
                if (row >= 0 && row < producto.getRowCount()) {
                    producto.setRowSelectionInterval(row, row);
                } else {
                    producto.clearSelection();
                }
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        producto = new javax.swing.JTable();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        proveedor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        categoria = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Descripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        editar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(producto);

        proveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Proveedor");

        jButton2.setText("Filtrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Categoría");

        categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Descripción producto");

        jLabel4.setText("Nombre producto");

        jLabel5.setText("Precio unitario");

        jDesktopPane1.setLayer(proveedor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextField2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(categoria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(Descripcion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(Nombre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(precio, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Descripcion)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1)))
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))))
                    .addComponent(Nombre)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        editar.setText("Aplicar Cambios");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        jButton3.setText("Descargar informe");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDesktopPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(129, 129, 129))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void cargarDatosProductos() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Nombre del Producto");
    model.addColumn("Descripción");
    model.addColumn("Categoría");
    model.addColumn("Proveedor");
    model.addColumn("Precio Unitario");

    boolean hayDatos = false; // Variable para verificar si hay datos

    String query = "SELECT p.Nombre AS ProductoNombre, p.Descripción, c.Nombre AS CategoriaNombre, pr.Nombre AS ProveedorNombre, p.PrecioUnitario " +
                   "FROM Productos p " +
                   "JOIN Categorias c ON p.CategoríaID = c.CategoríaID " +
                   "JOIN Proveedores pr ON p.ProveedorID = pr.ProveedorID";

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            hayDatos = true; // Si hay al menos una fila, cambiamos la variable a true
            Object[] row = new Object[5];
            row[0] = rs.getString("ProductoNombre");
            row[1] = rs.getString("Descripción");
            row[2] = rs.getString("CategoriaNombre");
            row[3] = rs.getString("ProveedorNombre");
            row[4] = rs.getBigDecimal("PrecioUnitario");
            model.addRow(row);
        }

        // Si no hay datos, agregamos una fila con el mensaje "Sin registros"
        if (!hayDatos) {
            model.addRow(new Object[] { "Sin registros", "", "", "", "" });
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    producto.setModel(model);

    // Añadir el TableRowSorter para habilitar la ordenación
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    producto.setRowSorter(sorter);  // Asignar el sorter a la tabla
}
    
    
    private void buscarProducto() {
    // Crear un cuadro de diálogo para ingresar el nombre del producto a buscar
    String nombreBusqueda = JOptionPane.showInputDialog(this, "Ingrese el nombre del producto a buscar:");

    DefaultTableModel model = (DefaultTableModel) producto.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de mostrar los resultados

    if (nombreBusqueda != null && !nombreBusqueda.trim().isEmpty()) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT p.Nombre AS ProductoNombre, p.Descripción, c.Nombre AS CategoriaNombre, pr.Nombre AS ProveedorNombre, p.PrecioUnitario " +
                         "FROM Productos p " +
                         "JOIN Categorias c ON p.CategoríaID = c.CategoríaID " +
                         "JOIN Proveedores pr ON p.ProveedorID = pr.ProveedorID " +
                         "WHERE p.Nombre LIKE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, "%" + nombreBusqueda.trim() + "%");
            ResultSet rs = pst.executeQuery();

            boolean hayDatos = false; // Variable para verificar si se encontraron resultados

            // Rellenar la tabla con los resultados de la búsqueda
            while (rs.next()) {
                hayDatos = true; // Si se encuentra al menos un registro, cambiamos la variable a true
                String nombreProducto = rs.getString("ProductoNombre");
                String descripcion = rs.getString("Descripción");
                String categoriaNombre = rs.getString("CategoriaNombre");
                String proveedorNombre = rs.getString("ProveedorNombre");
                BigDecimal precioUnitario = rs.getBigDecimal("PrecioUnitario");
                model.addRow(new Object[]{nombreProducto, descripcion, categoriaNombre, proveedorNombre, precioUnitario});
            }

            // Si no se encontraron datos, agregar una fila con el mensaje "Sin registros"
            if (!hayDatos) {
                model.addRow(new Object[]{"Sin registros", "", "", "", ""});
            }

            // Agregar una fila al final con el mensaje de filtros aplicados
            model.addRow(new Object[]{"", "", "", "", "Se aplicaron filtros de búsqueda: " + nombreBusqueda.trim()});

            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Mostrar mensaje de error si el nombre de búsqueda está vacío
        JOptionPane.showMessageDialog(this, "El nombre de búsqueda no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Aplicar el renderer personalizado a la tabla
    producto.setDefaultRenderer(Object.class, new CustomTableCellRenderer(Color.RED, Color.BLACK));
}

    
    private void habilitarPaneYBotonguardar() {
        habilitar();
        guardar.setEnabled(true);
        editar.setEnabled(false);
        
  
}
    private void cargarDatosSeleccionados() {
    int selectedRow = producto.getSelectedRow();
    if (selectedRow >= 0) {
        // Obtener los datos de la fila seleccionada
        String nombreProducto = (String) producto.getValueAt(selectedRow, 0);
        String descripcion = (String) producto.getValueAt(selectedRow, 1);
        String categoriaNombre = (String) producto.getValueAt(selectedRow, 2);
        String proveedorNombre = (String) producto.getValueAt(selectedRow, 3);
        BigDecimal precioUnitario = (BigDecimal) producto.getValueAt(selectedRow, 4);

        // Rellenar los JTextField y JComboBox con los datos seleccionados
        Nombre.setText(nombreProducto);
        Descripcion.setText(descripcion);
        precio.setText(precioUnitario.toString());

        // Establecer el JComboBox para categoría
        categoria.setSelectedItem(categoriaNombre);

        // Establecer el JComboBox para proveedor
        proveedor.setSelectedItem(proveedorNombre);
        
        editar.setEnabled(true);
        guardar.setEnabled(false);
        habilitar();
        
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para editar.", "Seleccionar Fila", JOptionPane.WARNING_MESSAGE);
    }
}
    private void eliminarProducto() {
    int selectedRow = producto.getSelectedRow();
    if (selectedRow >= 0) {
        // Obtener los datos de la fila seleccionada
        String nombreProducto = (String) producto.getValueAt(selectedRow, 0);
        String categoriaNombre = (String) producto.getValueAt(selectedRow, 2);
        String proveedorNombre = (String) producto.getValueAt(selectedRow, 3);

        // Confirmar la eliminación
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar el producto \"" + nombreProducto + "\"?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int categoriaID = -1;
            int proveedorID = -1;

            try (Connection connection = DatabaseConnection.getConnection()) {
                // Obtener ID de Categoría
                String categoriaSql = "SELECT CategoríaID FROM Categorias WHERE Nombre = ?";
                try (PreparedStatement categoriaStmt = connection.prepareStatement(categoriaSql)) {
                    categoriaStmt.setString(1, categoriaNombre);
                    ResultSet categoriaRs = categoriaStmt.executeQuery();
                    if (categoriaRs.next()) {
                        categoriaID = categoriaRs.getInt("CategoríaID");
                    } else {
                        JOptionPane.showMessageDialog(this, "Categoría no encontrada.", "Error en la categoría", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Obtener ID de Proveedor
                String proveedorSql = "SELECT ProveedorID FROM Proveedores WHERE Nombre = ?";
                try (PreparedStatement proveedorStmt = connection.prepareStatement(proveedorSql)) {
                    proveedorStmt.setString(1, proveedorNombre);
                    ResultSet proveedorRs = proveedorStmt.executeQuery();
                    if (proveedorRs.next()) {
                        proveedorID = proveedorRs.getInt("ProveedorID");
                    } else {
                        JOptionPane.showMessageDialog(this, "Proveedor no encontrado.", "Error en el proveedor", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Eliminar el producto de la base de datos
                String deleteSql = "DELETE FROM Productos WHERE Nombre = ? AND CategoríaID = ? AND ProveedorID = ?";
                try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
                    deleteStmt.setString(1, nombreProducto);
                    deleteStmt.setInt(2, categoriaID);
                    deleteStmt.setInt(3, proveedorID);
                    int rowsAffected = deleteStmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        cargarDatosProductos(); // Recargar los datos de la tabla
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el producto. Puede que no exista.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto: " + e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para eliminar.", "Seleccionar Producto", JOptionPane.WARNING_MESSAGE);
    }
}



    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
    
    }//GEN-LAST:event_categoriaActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String filterText = jTextField1.getText().trim().toLowerCase();  // Obtén el texto del campo de texto y lo pasas a minúsculas
        List<String> filteredData = new ArrayList<>();

        if (filterText.isEmpty()) {
            // Si no hay texto en el campo de texto, mostrar todos los elementos
            filteredData = new ArrayList<>(data);
        } else {
            // Filtrar los datos que coinciden con el texto ingresado
            for (String item : data) {
                if (item.toLowerCase().contains(filterText)) {
                    filteredData.add(item);
                }
            }
        }

        // Ordenar los datos filtrados
        Collections.sort(filteredData);

        // Actualizar el modelo del JComboBox con los datos filtrados y ordenados
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(filteredData.toArray(new String[0]));
        categoria.setModel(model);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String filterText = jTextField2.getText().trim().toLowerCase();  // Obtén el texto del campo de texto jTextField2 y lo pasas a minúsculas
    List<String> filteredData = new ArrayList<>();

    if (filterText.isEmpty()) {
        // Si no hay texto en el campo de texto, mostrar todos los elementos
        filteredData = new ArrayList<>(data2);
    } else {
        // Filtrar los datos que coinciden con el texto ingresado
        for (String item : data2) {
            if (item.toLowerCase().contains(filterText)) {
                filteredData.add(item);
            }
        }
    }

    // Ordenar los datos filtrados
    Collections.sort(filteredData);

    // Actualizar el modelo del JComboBox con los datos filtrados y ordenados
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(filteredData.toArray(new String[0]));
    proveedor.setModel(model);  // Actualiza el JComboBox 'proveedor' con los datos filtrados

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        deshabilitar();    
    }//GEN-LAST:event_cancelarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
    // Obtener los datos del formulario
    String nombreProducto = Nombre.getText().trim();
    String descripcion = Descripcion.getText().trim();
    String precioTexto = precio.getText().trim();

    // Obtener los nombres de categoría y proveedor desde los combobox
    String nombreCategoria = (String) categoria.getSelectedItem();
    String nombreProveedor = (String) proveedor.getSelectedItem();

    // Validar nombre del producto
    if (nombreProducto.isEmpty() || nombreProducto.length() > 30) {
        JOptionPane.showMessageDialog(this, "El nombre del producto no puede estar vacío ni tener más de 30 caracteres.", "Error en el nombre", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar descripción
    if (descripcion.length() > 200) {
        JOptionPane.showMessageDialog(this, "La descripción no puede tener más de 200 caracteres.", "Error en la descripción", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar precio
    BigDecimal precioUnitario;
    try {
        precioUnitario = new BigDecimal(precioTexto);
        if (precioUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número positivo.", "Error en el precio", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error en el precio", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int categoriaID = -1;
    int proveedorID = -1;

    // Obtener ID de Categoría
    try (Connection connection = DatabaseConnection.getConnection()) {
        String categoriaSql = "SELECT CategoríaID FROM Categorias WHERE Nombre = ?";
        try (PreparedStatement categoriaStmt = connection.prepareStatement(categoriaSql)) {
            categoriaStmt.setString(1, nombreCategoria);
            ResultSet categoriaRs = categoriaStmt.executeQuery();
            if (categoriaRs.next()) {
                categoriaID = categoriaRs.getInt("CategoríaID");
            } else {
                JOptionPane.showMessageDialog(this, "Categoría no encontrada o sin categorias registrada, registra una nueva he intenta de nuevo", "Error en la categoría", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Obtener ID de Proveedor
        String proveedorSql = "SELECT ProveedorID FROM Proveedores WHERE Nombre = ?";
        try (PreparedStatement proveedorStmt = connection.prepareStatement(proveedorSql)) {
            proveedorStmt.setString(1, nombreProveedor);
            ResultSet proveedorRs = proveedorStmt.executeQuery();
            if (proveedorRs.next()) {
                proveedorID = proveedorRs.getInt("ProveedorID");
            } else {
                JOptionPane.showMessageDialog(this, "Proveedor no encontrado o sin registros, registra uno nuevo o intenta nuevamente", "Error en el proveedor", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Validar existencia del producto con el mismo nombre, categoría y proveedor
        String checkSql = "SELECT COUNT(*) FROM Productos WHERE Nombre = ? AND CategoríaID = ? AND ProveedorID = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setString(1, nombreProducto);
            checkStmt.setInt(2, categoriaID);
            checkStmt.setInt(3, proveedorID);
            ResultSet checkRs = checkStmt.executeQuery();
            checkRs.next();
            int count = checkRs.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Ya existe un producto con este nombre para el proveedor y categoría seleccionados.", "Error de duplicado", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Insertar el nuevo producto
        String insertSql = "INSERT INTO Productos (Nombre, Descripción, CategoríaID, ProveedorID, PrecioUnitario) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            insertStmt.setString(1, nombreProducto);
            insertStmt.setString(2, descripcion);
            insertStmt.setInt(3, categoriaID);
            insertStmt.setInt(4, proveedorID);
            insertStmt.setBigDecimal(5, precioUnitario);
            insertStmt.executeUpdate();
        }

        // Mensaje de éxito
        JOptionPane.showMessageDialog(this, "Producto insertado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        deshabilitar();
        cargarDatosProductos();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al insertar el producto: " + e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_guardarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        
    int selectedRow = producto.getSelectedRow();
    if (selectedRow >= 0) {
        // Obtener el ID del producto de la fila seleccionada
        String nombreProductoOriginal = (String) producto.getValueAt(selectedRow, 0);
        
        // Obtener los datos del formulario
        String nombreProducto = Nombre.getText().trim();
        String descripcion = Descripcion.getText().trim();
        String precioTexto = precio.getText().trim();
        String nombreCategoria = (String) categoria.getSelectedItem();
        String nombreProveedor = (String) proveedor.getSelectedItem();

        // Validar los datos del formulario
        if (nombreProducto.isEmpty() || nombreProducto.length() > 30) {
            JOptionPane.showMessageDialog(this, "El nombre del producto no puede estar vacío ni tener más de 30 caracteres.", "Error en el nombre", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.length() > 200) {
            JOptionPane.showMessageDialog(this, "La descripción no puede tener más de 200 caracteres.", "Error en la descripción", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BigDecimal precioUnitario;
        try {
            precioUnitario = new BigDecimal(precioTexto);
            if (precioUnitario.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número positivo.", "Error en el precio", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error en el precio", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int categoriaID;
        int proveedorID;

        // Obtener ID de Categoría
        try (Connection connection = DatabaseConnection.getConnection()) {
            String categoriaSql = "SELECT CategoríaID FROM Categorias WHERE Nombre = ?";
            try (PreparedStatement categoriaStmt = connection.prepareStatement(categoriaSql)) {
                categoriaStmt.setString(1, nombreCategoria);
                ResultSet categoriaRs = categoriaStmt.executeQuery();
                if (categoriaRs.next()) {
                    categoriaID = categoriaRs.getInt("CategoríaID");
                } else {
                    JOptionPane.showMessageDialog(this, "Categoría no encontrada.", "Error en la categoría", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Obtener ID de Proveedor
            String proveedorSql = "SELECT ProveedorID FROM Proveedores WHERE Nombre = ?";
            try (PreparedStatement proveedorStmt = connection.prepareStatement(proveedorSql)) {
                proveedorStmt.setString(1, nombreProveedor);
                ResultSet proveedorRs = proveedorStmt.executeQuery();
                if (proveedorRs.next()) {
                    proveedorID = proveedorRs.getInt("ProveedorID");
                } else {
                    JOptionPane.showMessageDialog(this, "Proveedor no encontrado.", "Error en el proveedor", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Actualizar el producto en la base de datos
            String updateSql = "UPDATE Productos SET Nombre = ?, Descripción = ?, CategoríaID = ?, ProveedorID = ?, PrecioUnitario = ? WHERE Nombre = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setString(1, nombreProducto);
                updateStmt.setString(2, descripcion);
                updateStmt.setInt(3, categoriaID);
                updateStmt.setInt(4, proveedorID);
                updateStmt.setBigDecimal(5, precioUnitario);
                updateStmt.setString(6, nombreProductoOriginal);
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    deshabilitar();
                    cargarDatosProductos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el producto.", "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto: " + e.getMessage(), "Error en la base de datos", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto para editar.", "Seleccionar Producto", JOptionPane.WARNING_MESSAGE);
    }


    }//GEN-LAST:event_editarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    GeneradorPDF generador = new GeneradorPDF();
    generador.exportarTabla(producto, "Reporte de productos registrados");

    }//GEN-LAST:event_jButton3ActionPerformed
    // Método para cargar los datos en el JComboBox
    private void loadComboBoxData() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT Nombre FROM Categorias";
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                List<String> names = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("Nombre");
                    names.add(name);
                    data.add(name);
                }
                // Ordenar alfabéticamente
                Collections.sort(names);
                
                // Cargar los datos ordenados en el JComboBox
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(names.toArray(new String[0]));
                categoria.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadProveedorComboBoxData() {
    try (Connection connection = DatabaseConnection.getConnection()) {
        String query = "SELECT Nombre FROM Proveedores";  // Consulta para obtener los nombres de proveedores
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            List<String> names = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("Nombre");  // Obtén el nombre de la columna 'Nombre'
                names.add(name);
                data2.add(name);  // Si necesitas almacenar los nombres en otra lista 'data'
            }
            // Ordenar alfabéticamente los nombres de proveedores
            Collections.sort(names);
            
            // Cargar los datos ordenados en el JComboBox 'proveedor'
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(names.toArray(new String[0]));
            proveedor.setModel(model);  // Cargar el JComboBox con los nombres de proveedores
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
         
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro_producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Descripcion;
    private javax.swing.JTextField Nombre;
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JButton editar;
    private javax.swing.JButton guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField precio;
    private javax.swing.JTable producto;
    private javax.swing.JComboBox<String> proveedor;
    // End of variables declaration//GEN-END:variables
}
