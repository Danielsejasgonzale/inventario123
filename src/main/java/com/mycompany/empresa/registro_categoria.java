
package com.mycompany.empresa;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class registro_categoria extends menu {
    private JPopupMenu popupMenu;
    private JMenuItem editMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem updateTableMenuItem;
    private int selectedRow; 

    public registro_categoria() {
        initComponents();
        mostrarDatosEnTabla(); // Añadir esta línea para cargar los datos
        initPopupMenu(); // Inicializa el menú contextual
        addTableMouseListener(); // Añade el mouse listener para el menú contextual
      // Centrar la ventana en la pantalla
        centerWindow(this);
    }
    
    private static void centerWindow(JFrame window) {
    window.pack();  // Ajusta el tamaño de la ventana al contenido (opcional)
    window.setLocationRelativeTo(null);  // Centra la ventana en la pantalla
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        categoria = new javax.swing.JTable();
        caracteristicaspdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        categoria.setModel(new javax.swing.table.DefaultTableModel(
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
        categoria.setRowHeight(40);
        jScrollPane2.setViewportView(categoria);

        caracteristicaspdf.setText("Descargar Informe");
        caracteristicaspdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caracteristicaspdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(caracteristicaspdf, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(caracteristicaspdf, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private JDialog loadingDialog; 

    
    
    private void initPopupMenu() {
    popupMenu = new JPopupMenu();

    editMenuItem = new JMenuItem("Editar");
    editMenuItem.setBackground(Color.WHITE);
    editMenuItem.setForeground(Color.BLACK);
    editMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            editarCategoria();
        }
    });
    
    popupMenu.add(editMenuItem);

    deleteMenuItem = new JMenuItem("Eliminar");
    deleteMenuItem.setBackground(Color.WHITE);
    deleteMenuItem.setForeground(Color.BLACK);
    deleteMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            eliminarCategoria();
        }
    });
    popupMenu.add(deleteMenuItem);

    JMenuItem searchMenuItem = new JMenuItem("Buscar");
    searchMenuItem.setBackground(Color.WHITE);
    searchMenuItem.setForeground(Color.BLACK);
    searchMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchCategoria();
        }
    });
    popupMenu.add(searchMenuItem);

    updateTableMenuItem = new JMenuItem("Actualizar Tabla");
    updateTableMenuItem.setBackground(Color.WHITE);
    updateTableMenuItem.setForeground(Color.BLACK);
    updateTableMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarDatosEnTabla();
        }
    });
    popupMenu.add(updateTableMenuItem);
    
    // Opción Insertar
    JMenuItem insertMenuItem = new JMenuItem("Insertar");
    insertMenuItem.setBackground(Color.WHITE);
    insertMenuItem.setForeground(Color.BLACK);
    insertMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showInsertDialog();
        }
    });
    popupMenu.add(insertMenuItem);  // Agregar la opción Insertar

    
}
    private void addTableMouseListener() {
        categoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) { // Verifica si se hizo clic con el botón derecho
                    int row = categoria.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < categoria.getRowCount()) {
                        categoria.setRowSelectionInterval(row, row);
                        selectedRow = row;
                        popupMenu.show(categoria, e.getX(), e.getY());
                    }
                }
            }
        });
    }
    private void editarCategoria() {
    int categoriaID = (int) categoria.getValueAt(selectedRow, 0);
    String nombreActual = (String) categoria.getValueAt(selectedRow, 1);

    // Mostrar un cuadro de diálogo para ingresar el nuevo nombre
    String nuevoNombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre:", nombreActual);

    if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Comprobar si el nuevo nombre ya existe en la tabla
            String checkSql = "SELECT COUNT(*) FROM Categorias WHERE Nombre = ? AND CategoríaID != ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setString(1, nuevoNombre);
            checkStmt.setInt(2, categoriaID);  // Excluir el registro que estamos editando
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Si ya existe un registro con ese nombre, mostramos un mensaje de error
                JOptionPane.showMessageDialog(this, "El nombre de la categoría ya existe. Por favor, elija un nombre diferente.");
            } else {
                // Si no existe, procedemos con la actualización
                String sql = "UPDATE Categorias SET Nombre = ? WHERE CategoríaID = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, nuevoNombre);
                pst.setInt(2, categoriaID);
                pst.executeUpdate();
                pst.close();

                // Actualizar la tabla  
                mostrarDatosEnTabla();
                JOptionPane.showMessageDialog(this, "Categoría actualizada correctamente.");
            }

            rs.close();
            checkStmt.close();
            connection.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la categoría: " + e.getMessage());
        }
    }
}
    private void eliminarCategoria() {
    int categoriaID = (int) categoria.getValueAt(selectedRow, 0);
    int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar esta categoría?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Verificar si la categoría está en uso en la tabla Productos
            String checkSql = "SELECT COUNT(*) FROM Productos WHERE CategoríaID = ?";
            PreparedStatement checkPst = connection.prepareStatement(checkSql);
            checkPst.setInt(1, categoriaID);
            ResultSet rs = checkPst.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar la categoría porque está siendo utilizada en la tabla Productos.");
                rs.close();
                checkPst.close();
                connection.close();
                return; // Salir del método sin realizar la eliminación
            }

            // Cerrar los recursos de la verificación
            rs.close();
            checkPst.close();

            // Proceder con la eliminación
            String sql = "DELETE FROM Categorias WHERE CategoríaID = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, categoriaID);
            pst.executeUpdate();

            pst.close();
            connection.close();

            // Actualizar la tabla
            mostrarDatosEnTabla();
            JOptionPane.showMessageDialog(this, "Categoría eliminada correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la categoría: " + e.getMessage());
        }
    }
}

    
    private void searchCategoria() {
    // Crear un cuadro de diálogo para ingresar el nombre de la categoría a buscar
    String nombreBusqueda = JOptionPane.showInputDialog(this, "Ingrese el nombre de la categoría a buscar:");

    DefaultTableModel model = (DefaultTableModel) categoria.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de mostrar los resultados

    if (nombreBusqueda != null && !nombreBusqueda.trim().isEmpty()) {
        // Crear la consulta SQL
        String sql = "SELECT * FROM Categorias WHERE Nombre LIKE ?";
        nombreBusqueda = "%" + nombreBusqueda.trim() + "%";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombreBusqueda);
            ResultSet rs = pst.executeQuery();

            boolean hayDatos = false; // Variable para verificar si se encontraron resultados

            while (rs.next()) {
                hayDatos = true; // Si se encuentra al menos un registro, cambiamos la variable a true
                int id = rs.getInt("CategoríaID");
                String nombre = rs.getString("Nombre");
                model.addRow(new Object[]{id, nombre});
            }

            // Si no se encontraron datos, agregar una fila con el mensaje "Sin registros"
            if (!hayDatos) {
                model.addRow(new Object[]{"Sin registros", ""});
            }

            // Agregar una fila al final con el mensaje de filtros aplicados
            model.addRow(new Object[]{"", "Se aplicaron filtros de búsqueda: " + nombreBusqueda});

            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Mostrar mensaje de error si el nombre de búsqueda está vacío
        JOptionPane.showMessageDialog(this, "El nombre de búsqueda no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Aplicar el renderer personalizado a la tabla
    categoria.setDefaultRenderer(Object.class, new CustomTableCellRenderer(Color.RED, Color.BLACK));
}

    
    
    
    private void mostrarDatosEnTabla() {
    try {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Categorias";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        // Crear el modelo de la tabla con las columnas especificadas
        DefaultTableModel model = new DefaultTableModel(new String[]{"CategoríaID", "Nombre"}, 0);

        // Verificar si el ResultSet tiene registros
        if (!rs.isBeforeFirst()) {  // No hay registros en el ResultSet
            model.addRow(new Object[]{"Sin registros", ""});
        } else {
            // Llenar la tabla con los registros de la base de datos
            while (rs.next()) {
                int id = rs.getInt("CategoríaID");
                String nombre = rs.getString("Nombre");
                model.addRow(new Object[]{id, nombre});
            }
        }

        // Establecer el modelo de la tabla
        categoria.setModel(model);

        // Crear un TableRowSorter para permitir la clasificación de las columnas
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        categoria.setRowSorter(sorter);

        // Definir el comparador para la columna CategoríaID
        sorter.setComparator(0, (o1, o2) -> {
            if (o1 instanceof Integer && o2 instanceof Integer) {
                return Integer.compare((Integer) o1, (Integer) o2);
            }
            return 0;
        });

        // Cerrar recursos
        rs.close();
        pst.close();
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage());
    }
}

    
    
    private void showInsertDialog() {
    JDialog insertDialog = new JDialog(this, "Insertar Categoría", true);
    insertDialog.setLayout(new GridLayout(3, 2, 10, 10));
    insertDialog.setSize(400, 200);
    insertDialog.setLocationRelativeTo(this);

    // Etiquetas y campos de texto
    JLabel idLabel = new JLabel("CategoríaID (Máx. 15 números):");
    JTextField idField = new JTextField();
    idField.setDocument(new JTextFieldLimit(15));  // Limitar el número de caracteres a 15
    
    JLabel nameLabel = new JLabel("Nombre (Máx. 12 letras):");
    JTextField nameField = new JTextField();
    nameField.setDocument(new JTextFieldLimit(12));  // Limitar el número de caracteres a 12

    // Botón Insertar
    JButton insertButton = new JButton("Insertar");
    insertButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String categoriaIDStr = idField.getText().trim();
            String nombre = nameField.getText().trim();

            // Validar que CategoriaID solo tenga números
            if (!categoriaIDStr.matches("\\d{1,15}")) {
                JOptionPane.showMessageDialog(insertDialog, "El CategoríaID debe ser numérico y de máximo 15 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (nombre.isEmpty() || !nombre.matches("[a-zA-Z ]{1,12}")) {
                JOptionPane.showMessageDialog(insertDialog, "El Nombre debe contener solo letras y hasta 12 caracteres.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertir CategoriaID a entero
            int categoriaID = Integer.parseInt(categoriaIDStr);

            // Intentar insertar en la base de datos
            insertarCategoria(categoriaID, nombre, insertDialog);
        }
    });

    // Añadir componentes al cuadro de diálogo
    insertDialog.add(idLabel);
    insertDialog.add(idField);
    insertDialog.add(nameLabel);
    insertDialog.add(nameField);
    insertDialog.add(new JLabel());
    insertDialog.add(insertButton);

    insertDialog.setVisible(true);
}
    private void insertarCategoria(int categoriaID, String nombre, JDialog insertDialog) {
    try {
        Connection connection = DatabaseConnection.getConnection();

        // Comprobar si la categoría ya existe
        String checkSQL = "SELECT COUNT(*) FROM Categorias WHERE CategoríaID = ? OR Nombre = ?";
        PreparedStatement checkPst = connection.prepareStatement(checkSQL);
        checkPst.setInt(1, categoriaID);
        checkPst.setString(2, nombre);
        ResultSet rs = checkPst.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            JOptionPane.showMessageDialog(insertDialog, "Ya existe una categoría con ese ID o Nombre.", "Error de Duplicado", JOptionPane.ERROR_MESSAGE);
            rs.close();
            checkPst.close();
            return;
        }

        rs.close();
        checkPst.close();

        // Insertar nueva categoría si no existe
        String insertSQL = "INSERT INTO Categorias (CategoríaID, Nombre) VALUES (?, ?)";
        PreparedStatement insertPst = connection.prepareStatement(insertSQL);
        insertPst.setInt(1, categoriaID);
        insertPst.setString(2, nombre);
        insertPst.executeUpdate();

        insertPst.close();
        connection.close();

        // Actualizar la tabla
        mostrarDatosEnTabla();

        JOptionPane.showMessageDialog(insertDialog, "Categoría insertada correctamente.");
        insertDialog.dispose();  // Cerrar el cuadro de diálogo después de insertar
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al insertar la categoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    class JTextFieldLimit extends javax.swing.text.PlainDocument {
    private int limit;

    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
 
    private void caracteristicaspdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caracteristicaspdfActionPerformed
    GeneradorPDF generador = new GeneradorPDF();
    generador.exportarTabla(categoria, "Reporte de categorias registradas");


    }//GEN-LAST:event_caracteristicaspdfActionPerformed

    



    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                registro_categoria frame = new registro_categoria();
                frame.setVisible(true);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton caracteristicaspdf;
    public javax.swing.JTable categoria;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
