
package com.mycompany.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class reportes_salida_producto extends menu {

    public reportes_salida_producto() {
        initComponents();
        cargarDatosProductos();
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
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        productolista = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fechainicial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fechafinal = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setText("Actualizar tabla");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        productolista.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(productolista);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Reporte de salidas");

        jButton2.setText("Aplicar Filtros");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Inicial (Año-mes-dia)");

        jLabel3.setText("Fecha Final (Año-mes-dia)");

        jCheckBox1.setText("Aplicar fecha actual para fecha final");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fechainicial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fechafinal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCheckBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechainicial))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechafinal)))
                .addContainerGap())
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fechainicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fechafinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jButton1.setText("Generar reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(255, 255, 255))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jDesktopPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void cargarDatosProductos() {
    cargarDatosProductos(null, null); // Llama al método con parámetros nulos para cargar todos los datos
}
    
    private void cargarDatosProductos(java.util.Date fechaInicial, java.util.Date fechaFinal) {
    DefaultTableModel model = new DefaultTableModel();
    // Agregar las columnas requeridas (sin Cantidad Inicial, renombrar Cantidad Disponible)
    model.addColumn("Nombre del Producto");
    model.addColumn("Categoría");
    model.addColumn("Proveedor");
    model.addColumn("Fecha de Registro");
    model.addColumn("Cantidad Salida"); // Renombrado

    boolean hayDatos = false; // Variable para verificar si hay datos

    // Convertir fechas a formato SQL
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fechaInicialStr = fechaInicial != null ? sdf.format(fechaInicial) : "1970-01-01";
    String fechaFinalStr = fechaFinal != null ? sdf.format(fechaFinal) : "9999-12-31";

    // Consulta SQL ajustada para excluir CantidadInicial y renombrar CantidadDisponible
    String query = "SELECT p.Nombre AS ProductoNombre, " +
                   "c.Nombre AS CategoriaNombre, " +
                   "pr.Nombre AS ProveedorNombre, " +
                   "m.Fecha AS FechaRegistro, " +
                   "SUM(m.Cantidad) AS CantidadSalida " + // Renombrado
                   "FROM Movimientos m " +
                   "JOIN Productos p ON m.ProductoID = p.ProductoID " +
                   "JOIN Categorias c ON p.CategoríaID = c.CategoríaID " +
                   "JOIN Proveedores pr ON p.ProveedorID = pr.ProveedorID " +
                   "WHERE m.TipoMovimiento = 'Salida' " +
                   "AND m.Fecha BETWEEN ? AND ? " +  // Filtro de fechas
                   "GROUP BY p.Nombre, c.Nombre, pr.Nombre, m.Fecha " + // Excluir CantidadInicial del GROUP BY
                   "ORDER BY FechaRegistro DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, fechaInicialStr);
        pstmt.setString(2, fechaFinalStr);

        try (ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                hayDatos = true; // Si hay al menos una fila, cambiamos la variable a true
                Object[] row = new Object[5]; // Ajustar el tamaño del array a 5
                row[0] = rs.getString("ProductoNombre");
                row[1] = rs.getString("CategoriaNombre");
                row[2] = rs.getString("ProveedorNombre");
                row[3] = rs.getDate("FechaRegistro"); // Usamos Date para FechaRegistro
                row[4] = rs.getInt("CantidadSalida"); // Cantidad Salida calculada
                model.addRow(row);
            }

            // Si no hay datos, agregamos una fila con el mensaje "Sin registros"
            if (!hayDatos) {
                model.addRow(new Object[] { "Sin registros", "", "", "", "" });
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Añadir una fila indicando si hay o no filtros aplicados
    if (fechaInicial == null && fechaFinal == null) {
        // Si no se aplicaron filtros de fecha
        model.addRow(new Object[] { "Sin filtros de búsqueda", "", "", "", "" });
    } else {
        // Si se aplicaron filtros de fecha
        String mensajeFiltro = "Se aplicaron filtros de fecha inicial (" + (fechaInicial != null ? fechaInicialStr : "Sin filtro") + 
                               ") a fecha final (" + (fechaFinal != null ? fechaFinalStr : "Sin filtro") + ")";
        model.addRow(new Object[] { mensajeFiltro, "", "", "", "" });
    }

    productolista.setModel(model);

    // Añadir el TableRowSorter para habilitar la ordenación
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    productolista.setRowSorter(sorter);  // Asignar el sorter a la tabla
}

    
    private void aplicarFiltro() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false); // Configurar el formato a no permisivo

    try {
        // Intentar parsear las fechas
        java.util.Date fechaInicial = fechainicial.getText().isEmpty() ? null : sdf.parse(fechainicial.getText());
        java.util.Date fechaFinal = fechafinal.getText().isEmpty() ? null : sdf.parse(fechafinal.getText());

        // Verificar si las fechas son válidas y si la fechaFinal es después de fechaInicial
        if (fechaInicial != null && fechaFinal != null && fechaFinal.before(fechaInicial)) {
            JOptionPane.showMessageDialog(null, "La fecha final debe ser posterior a la fecha inicial.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Cargar los datos con las fechas válidas
            cargarDatosProductos(fechaInicial, fechaFinal);
        }
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Por favor ingrese una fecha en el formato yyyy-MM-dd.\nEjemplo: 2024-09-15", "Formato de Fecha Incorrecto", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    private void configurarFiltros() {
    fechainicial.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) { aplicarFiltro(); }
        @Override
        public void removeUpdate(DocumentEvent e) { aplicarFiltro(); }
        @Override
        public void changedUpdate(DocumentEvent e) { aplicarFiltro(); }
    });

    fechafinal.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) { aplicarFiltro(); }
        @Override
        public void removeUpdate(DocumentEvent e) { aplicarFiltro(); }
        @Override
        public void changedUpdate(DocumentEvent e) { aplicarFiltro(); }
    });
}

    
    private void llenarFechaYHora() {
        // Obtiene la fecha y hora actual
        LocalDateTime fechaActual = LocalDateTime.now();

        // Define el formato que necesitas, por ejemplo: YYYY-MM-DD HH:mm:ss
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Formatea la fecha y hora actual según el patrón
        String fechaFormateada = fechaActual.format(formatoFecha);

        // Coloca la fecha formateada en el JTextField
        fechafinal.setText(fechaFormateada);
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cargarDatosProductos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        aplicarFiltro();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // Verifica si el JCheckBox está seleccionado
        if (jCheckBox1.isSelected()) {
            llenarFechaYHora();
        } else {
            fechafinal.setText("");
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GeneradorPDF generador = new GeneradorPDF();
        generador.exportarTabla(productolista, "Reporte de salidas de productos registrados");

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reportes_salida_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportes_salida_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportes_salida_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportes_salida_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportes_salida_producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fechafinal;
    private javax.swing.JTextField fechainicial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable productolista;
    // End of variables declaration//GEN-END:variables
}
