
package com.mycompany.empresa;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class registro_proveedor extends menu {
    private JPopupMenu popupMenu;
    private JMenuItem insertarItem, editarItem, eliminarItem, buscarItem, actualizarItem;


    public registro_proveedor() {
        initComponents();
        createPopupMenu();
        addPopupToTable();
        cargarDatos();
        cancelarInsercion();
        configurarValidacionYGuardado();
    // Centrar la ventana en la pantalla
        centerWindow(this);
    }
    
    private static void centerWindow(JFrame window) {
    window.pack();  // Ajusta el tamaño de la ventana al contenido (opcional)
    window.setLocationRelativeTo(null);  // Centra la ventana en la pantalla
}
    
    
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

        insertarItem.addActionListener(e -> habilitarFilaInsercion());
        editarItem.addActionListener(e -> editarProveedor());
        eliminarItem.addActionListener(e -> eliminarProveedor());
        buscarItem.addActionListener(e -> buscarProveedor());
        actualizarItem.addActionListener(e -> cargarDatos());
    }
    
    private void addPopupToTable() {
        proveedor.addMouseListener(new MouseAdapter() {
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
                int row = proveedor.rowAtPoint(e.getPoint());
                if (row >= 0 && row < proveedor.getRowCount()) {
                    proveedor.setRowSelectionInterval(row, row);
                } else {
                    proveedor.clearSelection();
                }
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
    private void cargarDatos() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ProveedorID");
    model.addColumn("Nombre");
    model.addColumn("Teléfono");
    model.addColumn("Dirección");

    boolean hayDatos = false; // Variable para verificar si hay datos

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM Proveedores")) {

        while (rs.next()) {
            hayDatos = true; // Si hay al menos una fila, cambiamos la variable a true
            Object[] row = new Object[4];
            row[0] = rs.getInt("ProveedorID");
            row[1] = rs.getString("Nombre");
            row[2] = rs.getString("Teléfono");
            row[3] = rs.getString("Dirección");
            model.addRow(row);
        }

        // Si no hay datos, agregamos una fila con el mensaje "Sin registros"
        if (!hayDatos) {
            model.addRow(new Object[] { "Sin registros", "", "", "" });
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    proveedor.setModel(model);

    // Añadir el TableRowSorter para habilitar la ordenación
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    proveedor.setRowSorter(sorter);  // Asignar el sorter a la tabla
}
    private boolean isInsertInProgress = false;  // Bandera para saber si hay un registro en progreso
    
    private void habilitarFilaInsercion() {
    DefaultTableModel model = (DefaultTableModel) proveedor.getModel();
    
    // Generar un ProveedorID automáticamente (por ejemplo, 1)
    // Nota: Si ya tienes un método para generar el ID, utilízalo aquí
    int proveedorID = 1;  // Puedes cambiar esto por un valor dinámico si lo deseas

    // Agregar una fila con ProveedorID y campos vacíos
    model.insertRow(0, new Object[]{proveedorID, "", "", ""});  // ProveedorID, Nombre, Teléfono, Dirección
    
    isInsertInProgress = true;  // Activar la bandera de inserción

    // Habilitar edición en la primera fila, pero NO en la primera columna (ProveedorID)
    proveedor.changeSelection(0, 1, false, false);  // Seleccionar la primera celda editable (Nombre)
    proveedor.editCellAt(0, 1);  // Permitir edición en la primera celda editable (Nombre)
    proveedor.getEditorComponent().requestFocusInWindow();  // Enfocar el editor
}
    private void cancelarInsercion() {
        if (isInsertInProgress) {
            // Preguntar si el usuario quiere cancelar el proceso de inserción
            int option = JOptionPane.showConfirmDialog(
                proveedor, 
                "¿Está seguro de que desea cancelar la inserción?", 
                "Confirmar Cancelación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE
            );

            if (option == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) proveedor.getModel();
                // Eliminar la fila de inserción (fila 0)
                model.removeRow(0);
                isInsertInProgress = false;  // Desactivar la bandera de inserción
            }
        }
    }
    
    private void configurarValidacionYGuardado() {
    proveedor.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int row = proveedor.getSelectedRow();
                if (row == 0 && isInsertInProgress) {
                    // Actualizar los valores de la tabla antes de la validación
                    proveedor.editCellAt(row, 1); // Forzar actualización de las celdas
                    proveedor.editCellAt(row, 2);
                    proveedor.editCellAt(row, 3);

                    DefaultTableModel model = (DefaultTableModel) proveedor.getModel();

                    // Obtener los valores de la fila
                    String nombre = (String) model.getValueAt(row, 1);
                    String telefono = (String) model.getValueAt(row, 2);
                    String direccion = (String) model.getValueAt(row, 3);

                    // Validar campos
                    StringBuilder mensajeError = new StringBuilder();
                    if (nombre == null || nombre.trim().isEmpty()) {
                        mensajeError.append("El campo 'Nombre' es obligatorio.\n");
                    } else if (nombre.length() > 100) {
                        mensajeError.append("El campo 'Nombre' no puede exceder los 100 caracteres.\n");
                    }

                    if (telefono == null || telefono.trim().isEmpty()) {
                        mensajeError.append("El campo 'Teléfono' es obligatorio.\n");
                    } else if (telefono.length() > 20) {
                        mensajeError.append("El campo 'Teléfono' no puede exceder los 20 caracteres.\n");
                    }

                    if (direccion == null || direccion.trim().isEmpty()) {
                        mensajeError.append("El campo 'Dirección' es obligatorio.\n");
                    } else if (direccion.length() > 255) {
                        mensajeError.append("El campo 'Dirección' no puede exceder los 255 caracteres.\n");
                    }

                    // Si hay errores, mostrarlos
                    if (mensajeError.length() > 0) {
                        JOptionPane.showMessageDialog(proveedor, mensajeError.toString(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Validación duplicada
                    try (Connection connection = DatabaseConnection.getConnection()) {
                        String checkSQL = "SELECT COUNT(*) FROM Proveedores WHERE Nombre = ?";
                        PreparedStatement checkPst = connection.prepareStatement(checkSQL);
                        checkPst.setString(1, nombre);
                        ResultSet rs = checkPst.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) {
                            JOptionPane.showMessageDialog(proveedor, "Ya existe un proveedor con el nombre: " + nombre, "Error de Duplicado", JOptionPane.ERROR_MESSAGE);
                            rs.close();
                            checkPst.close();
                            return;
                        }

                        rs.close();
                        checkPst.close();

                        // Insertar nuevo proveedor
                        String insertSQL = "INSERT INTO Proveedores (Nombre, Teléfono, Dirección) VALUES (?, ?, ?)";
                        PreparedStatement insertPst = connection.prepareStatement(insertSQL);
                        insertPst.setString(1, nombre);
                        insertPst.setString(2, telefono);
                        insertPst.setString(3, direccion);
                        insertPst.executeUpdate();
                        insertPst.close();

                        JOptionPane.showMessageDialog(proveedor, "Proveedor insertado correctamente.");
                        isInsertInProgress = false;
                        cargarDatos();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(proveedor, "Error al insertar el proveedor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    });

    // Agregar FocusListener para asegurar la actualización del valor de la celda antes de la validación
    proveedor.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            proveedor.editCellAt(proveedor.getSelectedRow(), proveedor.getSelectedColumn());
        }
    });
}

    private void editarProveedor() {
    int selectedRow = proveedor.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un proveedor para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener el ID y el nombre del proveedor seleccionado
    int proveedorID = (int) proveedor.getValueAt(selectedRow, 0);
    String nombreActual = (String) proveedor.getValueAt(selectedRow, 1);
    String telefonoActual = (String) proveedor.getValueAt(selectedRow, 2);
    String direccionActual = (String) proveedor.getValueAt(selectedRow, 3);

    // Crear un cuadro de diálogo para ingresar nuevos datos
    JDialog editDialog = new JDialog(this, "Editar Proveedor", true);
    editDialog.setSize(300, 250);
    editDialog.setLayout(new BoxLayout(editDialog.getContentPane(), BoxLayout.Y_AXIS));

    JTextField nombreField = new JTextField(nombreActual);
    JTextField telefonoField = new JTextField(telefonoActual);
    JTextField direccionField = new JTextField(direccionActual);

    // Añadir campos al cuadro de diálogo
    editDialog.add(new JLabel("Nombre:"));
    editDialog.add(nombreField);
    editDialog.add(new JLabel("Teléfono:"));
    editDialog.add(telefonoField);
    editDialog.add(new JLabel("Dirección:"));
    editDialog.add(direccionField);

    JButton actualizarButton = new JButton("Actualizar");
    editDialog.add(actualizarButton);

    // Acción al hacer clic en "Actualizar"
    actualizarButton.addActionListener(e -> {
        String nuevoNombre = nombreField.getText().trim();
        String nuevoTelefono = telefonoField.getText().trim();
        String nuevaDireccion = direccionField.getText().trim();

        // Validar entradas
        if (nuevoNombre.isEmpty() || nuevoTelefono.isEmpty() || nuevaDireccion.isEmpty()) {
            JOptionPane.showMessageDialog(editDialog, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nuevoNombre.length() > 100 || nuevoTelefono.length() > 20 || nuevaDireccion.length() > 255) {
            JOptionPane.showMessageDialog(editDialog, "Uno de los campos excede el número máximo de caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Comprobar si el nuevo nombre ya existe
        try (Connection connection = DatabaseConnection.getConnection()) {
            String checkSQL = "SELECT COUNT(*) FROM Proveedores WHERE Nombre = ? AND ProveedorID != ?";
            PreparedStatement checkPst = connection.prepareStatement(checkSQL);
            checkPst.setString(1, nuevoNombre);
            checkPst.setInt(2, proveedorID);
            ResultSet rs = checkPst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(editDialog, "Ya existe un proveedor con ese nombre.", "Error de Duplicado", JOptionPane.ERROR_MESSAGE);
                rs.close();
                checkPst.close();
                return;
            }

            rs.close();
            checkPst.close();

            // Actualizar el proveedor
            String updateSQL = "UPDATE Proveedores SET Nombre = ?, Teléfono = ?, Dirección = ? WHERE ProveedorID = ?";
            PreparedStatement updatePst = connection.prepareStatement(updateSQL);
            updatePst.setString(1, nuevoNombre);
            updatePst.setString(2, nuevoTelefono);
            updatePst.setString(3, nuevaDireccion);
            updatePst.setInt(4, proveedorID);
            updatePst.executeUpdate();

            updatePst.close();

            // Actualizar la tabla
            cargarDatos();

            JOptionPane.showMessageDialog(editDialog, "Proveedor actualizado correctamente.");
            editDialog.dispose();  // Cerrar el cuadro de diálogo después de actualizar
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el proveedor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    editDialog.setVisible(true);
}
    private void eliminarProveedor() {
    int selectedRow = proveedor.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un proveedor para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener el ID del proveedor seleccionado
    int proveedorID = (int) proveedor.getValueAt(selectedRow, 0);
    int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar este proveedor?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM Proveedores WHERE ProveedorID = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, proveedorID);
            pst.executeUpdate();

            pst.close();
            connection.close();

            // Actualizar la tabla
            cargarDatos();

            JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    private void buscarProveedor() {
    // Crear un cuadro de diálogo para ingresar el nombre del proveedor a buscar
    String nombreBusqueda = JOptionPane.showInputDialog(this, "Ingrese el nombre del proveedor a buscar:");

    DefaultTableModel model = (DefaultTableModel) proveedor.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de mostrar los resultados

    if (nombreBusqueda != null && !nombreBusqueda.trim().isEmpty()) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Proveedores WHERE Nombre LIKE ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, "%" + nombreBusqueda.trim() + "%");
            ResultSet rs = pst.executeQuery();

            boolean hayDatos = false; // Variable para verificar si se encontraron resultados

            // Rellenar la tabla con los resultados de la búsqueda
            while (rs.next()) {
                hayDatos = true; // Si se encuentra al menos un registro, cambiamos la variable a true
                int proveedorID = rs.getInt("ProveedorID");
                String nombre = rs.getString("Nombre");
                String telefono = rs.getString("Teléfono");
                String direccion = rs.getString("Dirección");
                model.addRow(new Object[]{proveedorID, nombre, telefono, direccion});
            }

            // Si no se encontraron datos, agregar una fila con el mensaje "Sin registros"
            if (!hayDatos) {
                model.addRow(new Object[]{"Sin registros", "", "", ""});
            }

            // Agregar una fila al final con el mensaje de filtros aplicados
            model.addRow(new Object[]{"", "", "", "Se aplicaron filtros de búsqueda: " + nombreBusqueda.trim()});

            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar proveedores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Mostrar mensaje de error si el nombre de búsqueda está vacío
        JOptionPane.showMessageDialog(this, "El nombre de búsqueda no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Aplicar el renderer personalizado a la tabla
    proveedor.setDefaultRenderer(Object.class, new CustomTableCellRenderer(Color.RED, Color.BLACK));
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        proveedor = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        proveedor.setModel(new javax.swing.table.DefaultTableModel(
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
        proveedor.setRowHeight(40);
        jScrollPane1.setViewportView(proveedor);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton1.setText("Descargar informe");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    GeneradorPDF generador = new GeneradorPDF();
    generador.exportarTabla(proveedor, "Reporte de proveedores registrados");


    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registro_proveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTable proveedor;
    // End of variables declaration//GEN-END:variables
}
