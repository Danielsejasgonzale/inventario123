
package com.mycompany.empresa;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class movimiento_salida_producto extends menu {
    private JPopupMenu popupMenu;
    private JMenuItem buscarItem, actualizarItem, seleccionarItem;

    public movimiento_salida_producto() {
        initComponents();
        createPopupMenu();
        addPopupToTable();
        cargarDatosProductos();
        inicializarTablaProductoSeleccionado();
   
        // Configura la tabla para que las celdas no sean editables
        productolista.setDefaultEditor(Object.class, null);
        productoseleccionado.setDefaultEditor(Object.class, null);
        fecha.setEnabled(false);
        codigomovimiento.setEnabled(false);
    // Centrar la ventana en la pantalla
        centerWindow(this);
    }
    
    private static void centerWindow(JFrame window) {
    window.pack();  // Ajusta el tamaño de la ventana al contenido (opcional)
    window.setLocationRelativeTo(null);  // Centra la ventana en la pantalla
}
    private void createPopupMenu() {
        buscarItem = new JMenuItem("Buscar");
        actualizarItem = new JMenuItem("Actualizar");
        seleccionarItem = new JMenuItem("Seleccionar");

        popupMenu = new JPopupMenu(); // Inicia el menú emergente
        popupMenu.add(seleccionarItem); 
        popupMenu.add(buscarItem);
        popupMenu.add(actualizarItem);
        

        actualizarItem.addActionListener(e -> cargarDatosProductos());
        buscarItem.addActionListener(e -> buscarProductoConDialogo());
        seleccionarItem.addActionListener(e -> seleccionarProducto());
        
        
    }
    private void addPopupToTable() {
        productolista.addMouseListener(new MouseAdapter() {
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
                int row = productolista.rowAtPoint(e.getPoint());
                if (row >= 0 && row < productolista.getRowCount()) {
                    productolista.setRowSelectionInterval(row, row);
                } else {
                    productolista.clearSelection();
                }
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
    
    
    
    private void cargarDatosProductos() {
    DefaultTableModel model = new DefaultTableModel();

    // Agregar las columnas requeridas
    model.addColumn("MovimientoID");
    model.addColumn("Nombre del Producto");
    model.addColumn("Categoría");
    model.addColumn("Proveedor");
    model.addColumn("Fecha de Registro");
    model.addColumn("Cantidad Disponible");

    boolean hayDatos = false; // Variable para verificar si hay datos

    // Consulta SQL ajustada para excluir las cantidades cero
    String query = "SELECT m.MovimientoID, " +
                   "p.Nombre AS ProductoNombre, " +
                   "c.Nombre AS CategoriaNombre, " +
                   "pr.Nombre AS ProveedorNombre, " +
                   "m.Fecha AS FechaRegistro, " +
                   "SUM(m.Cantidad) AS CantidadDisponible " +
                   "FROM Movimientos m " +
                   "JOIN Productos p ON m.ProductoID = p.ProductoID " +
                   "JOIN Categorias c ON p.CategoríaID = c.CategoríaID " +
                   "JOIN Proveedores pr ON p.ProveedorID = pr.ProveedorID " +
                   "WHERE m.TipoMovimiento = 'Entrada' " +
                   "GROUP BY m.MovimientoID, p.Nombre, c.Nombre, pr.Nombre, m.Fecha " +
                   "HAVING SUM(m.Cantidad) > 0 " +  // Filtrar las filas con cantidad mayor a 0
                   "ORDER BY FechaRegistro DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            hayDatos = true; // Si hay al menos una fila, cambiamos la variable a true
            Object[] row = new Object[6];
            row[0] = rs.getInt("MovimientoID"); // Mostrar MovimientoID
            row[1] = rs.getString("ProductoNombre");
            row[2] = rs.getString("CategoriaNombre");
            row[3] = rs.getString("ProveedorNombre");
            row[4] = rs.getDate("FechaRegistro"); // Usamos Date para FechaRegistro
            row[5] = rs.getInt("CantidadDisponible"); // Cantidad Disponible calculada
            model.addRow(row);
        }

        // Si no hay datos, agregamos una fila con el mensaje "Sin registros"
        if (!hayDatos) {
            model.addRow(new Object[] { "Sin registros", "", "", "", "", "" });
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    productolista.setModel(model);

    // Añadir el TableRowSorter para habilitar la ordenación
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    productolista.setRowSorter(sorter);  // Asignar el sorter a la tabla
}
    private void buscarProductoConDialogo() {
    // Mostrar un cuadro de diálogo para que el usuario ingrese el criterio de búsqueda
    String criterioBusqueda = JOptionPane.showInputDialog(this, "Ingrese el nombre del producto a buscar:", "Buscar Producto", JOptionPane.QUESTION_MESSAGE);

    // Verificar si el usuario ingresó un valor o canceló el cuadro de diálogo
    if (criterioBusqueda == null || criterioBusqueda.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un nombre para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return; // Salir si no se ingresó un valor válido
    }

    DefaultTableModel model = new DefaultTableModel();

    // Agregar las columnas requeridas
    model.addColumn("MovimientoID");
    model.addColumn("Nombre del Producto");
    model.addColumn("Categoría");
    model.addColumn("Proveedor");
    model.addColumn("Fecha de Registro");
    model.addColumn("Cantidad Disponible");

    boolean hayDatos = false; // Variable para verificar si hay datos

    // Consulta SQL ajustada para filtrar por el criterio de búsqueda
    String query = "SELECT m.MovimientoID, " +
                   "p.Nombre AS ProductoNombre, " +
                   "c.Nombre AS CategoriaNombre, " +
                   "pr.Nombre AS ProveedorNombre, " +
                   "m.Fecha AS FechaRegistro, " +
                   "SUM(m.Cantidad) AS CantidadDisponible " +
                   "FROM Movimientos m " +
                   "JOIN Productos p ON m.ProductoID = p.ProductoID " +
                   "JOIN Categorias c ON p.CategoríaID = c.CategoríaID " +
                   "JOIN Proveedores pr ON p.ProveedorID = pr.ProveedorID " +
                   "WHERE m.TipoMovimiento = 'Entrada' " +
                   "AND p.Nombre LIKE ? " + // Filtrar por el nombre del producto
                   "GROUP BY m.MovimientoID, p.Nombre, c.Nombre, pr.Nombre, m.Fecha " +
                   "HAVING SUM(m.Cantidad) > 0 " + // Filtrar las filas con cantidad mayor a 0
                   "ORDER BY FechaRegistro DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(query)) {

        // Configurar el parámetro de búsqueda
        String busqueda = "%" + criterioBusqueda + "%"; // Usar LIKE con comodines para búsqueda parcial
        pst.setString(1, busqueda);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            hayDatos = true; // Si hay al menos una fila, cambiamos la variable a true
            Object[] row = new Object[6];
            row[0] = rs.getInt("MovimientoID"); // Mostrar MovimientoID
            row[1] = rs.getString("ProductoNombre");
            row[2] = rs.getString("CategoriaNombre");
            row[3] = rs.getString("ProveedorNombre");
            row[4] = rs.getDate("FechaRegistro"); // Usamos Date para FechaRegistro
            row[5] = rs.getInt("CantidadDisponible"); // Cantidad Disponible calculada
            model.addRow(row);
        }

        // Si no hay datos, agregamos una fila con el mensaje "Sin registros"
        if (!hayDatos) {
            model.addRow(new Object[] { "Sin registros", "", "", "", "", "" });
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    productolista.setModel(model);

    // Añadir el TableRowSorter para habilitar la ordenación
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    productolista.setRowSorter(sorter);  // Asignar el sorter a la tabla
}

    private void llenarFechaYHora() {
        // Obtiene la fecha y hora actual
        LocalDateTime fechaActual = LocalDateTime.now();

        // Define el formato que necesitas, por ejemplo: YYYY-MM-DD HH:mm:ss
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Formatea la fecha y hora actual según el patrón
        String fechaFormateada = fechaActual.format(formatoFecha);

        // Coloca la fecha formateada en el JTextField
        fecha.setText(fechaFormateada);
    }
    private void limpiarCampos() {
    codigomovimiento.setText("");
    fecha.setText("");
    cantidad.setText("");
    descripcion.setText("");

    // Limpiar la tabla productoseleccionado
    DefaultTableModel modelSeleccionado = (DefaultTableModel) productoseleccionado.getModel();
    modelSeleccionado.setRowCount(0);
}
    private void inicializarTablaProductoSeleccionado() {
    DefaultTableModel modelSeleccionado = new DefaultTableModel();

    // Crear las mismas columnas que en la tabla productolista
    modelSeleccionado.addColumn("MovimientoID");
    modelSeleccionado.addColumn("ProductoID");
    modelSeleccionado.addColumn("Nombre del Producto");
    modelSeleccionado.addColumn("Categoría");
    modelSeleccionado.addColumn("Proveedor");
    modelSeleccionado.addColumn("Fecha de Registro");
    modelSeleccionado.addColumn("Cantidad Disponible");

    // Establecer el modelo en la tabla productoseleccionado sin filas (solo columnas)
    productoseleccionado.setModel(modelSeleccionado);

    // Añadir el TableRowSorter para habilitar la ordenación en productoseleccionado (opcional)
    TableRowSorter<DefaultTableModel> sorterSeleccionado = new TableRowSorter<>(modelSeleccionado);
    productoseleccionado.setRowSorter(sorterSeleccionado);  // Asignar el sorter a la tabla productoseleccionado
}
    private void actualizarTablaSeleccionado(int movimientoID, int productoID, String nombreProducto, String categoriaNombre, String proveedorNombre, Date fechaRegistro, int cantidadDisponible) {
    // Obtener el modelo de la tabla productoseleccionado
    DefaultTableModel modelSeleccionado = (DefaultTableModel) productoseleccionado.getModel();

    boolean movimientoExiste = false;

    // Buscar si el MovimientoID ya está en la tabla productoseleccionado
    for (int i = 0; i < modelSeleccionado.getRowCount(); i++) {
        int idMovimientoEnTabla = (int) modelSeleccionado.getValueAt(i, 0);
        if (idMovimientoEnTabla == movimientoID) {
            // Si el movimiento ya está en la tabla, actualizamos los datos
            modelSeleccionado.setValueAt(productoID, i, 1);
            modelSeleccionado.setValueAt(nombreProducto, i, 2);
            modelSeleccionado.setValueAt(categoriaNombre, i, 3);
            modelSeleccionado.setValueAt(proveedorNombre, i, 4);
            modelSeleccionado.setValueAt(fechaRegistro, i, 5);
            modelSeleccionado.setValueAt(cantidadDisponible, i, 6);
            movimientoExiste = true;
            break;
        }
    }

    // Si el movimiento no está en la tabla, agregar una nueva fila
    if (!movimientoExiste) {
        modelSeleccionado.addRow(new Object[]{movimientoID, productoID, nombreProducto, categoriaNombre, proveedorNombre, fechaRegistro, cantidadDisponible});
    }
}
    
    private void seleccionarProducto() {
    // Obtener la fila seleccionada de la tabla productolista
    int filaSeleccionada = productolista.getSelectedRow();

    if (filaSeleccionada != -1) { // Verificar que se haya seleccionado una fila
        try {
            inicializarTablaProductoSeleccionado();
            codigomovimiento.setText("");
            // Obtener el modelo de la tabla productolista
            DefaultTableModel model = (DefaultTableModel) productolista.getModel();

            // Obtener los valores de la fila seleccionada
            int movimientoID = (int) model.getValueAt(filaSeleccionada, 0); // MovimientoID
            String nombreProducto = (String) model.getValueAt(filaSeleccionada, 1);
            String categoriaNombre = (String) model.getValueAt(filaSeleccionada, 2);
            String proveedorNombre = (String) model.getValueAt(filaSeleccionada, 3);
            Date fechaRegistro = (Date) model.getValueAt(filaSeleccionada, 4);
            int cantidadDisponible = (int) model.getValueAt(filaSeleccionada, 5);

            // Obtener el ProductoID basado en MovimientoID
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT p.ProductoID " +
                         "FROM Movimientos m " +
                         "JOIN Productos p ON m.ProductoID = p.ProductoID " +
                         "WHERE m.MovimientoID = ?";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, movimientoID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Obtener el ProductoID
                int productoID = rs.getInt("ProductoID");

                // Asignar el ProductoID al TextField llamado codigoproducto
                codigomovimiento.setText(String.valueOf(movimientoID));
                llenarFechaYHora();

                // Actualizar o agregar a la tabla productoseleccionado
                actualizarTablaSeleccionado(movimientoID, productoID, nombreProducto, categoriaNombre, proveedorNombre, fechaRegistro, cantidadDisponible);

            } else {
                JOptionPane.showMessageDialog(this, "Error: Movimiento no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            pst.close();
            connection.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al seleccionar el movimiento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
}
    
    private void Movimientosalida() {
    // Obtener los valores de los campos de texto
    String fechaStr = fecha.getText();
    String cantidadStr = cantidad.getText();
    String descripcionStr = descripcion.getText();
    String movimientoIDStr = codigomovimiento.getText(); // Obtener MovimientoID
    String tipoMovimiento = "Salida"; // Tipo de movimiento es Salida

    // Validar MovimientoID
    int movimientoID;
    try {
        movimientoID = Integer.parseInt(movimientoIDStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID de movimiento no válido. Debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar Fecha y Hora
    Timestamp fecha;
    try {
        // Define el formato esperado
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(fechaStr, dateTimeFormatter);
        fecha = Timestamp.valueOf(localDateTime); // Convertir a Timestamp
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Fecha y hora no válidas. Deben estar en el formato YYYY-MM-DD HH:MM:SS.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar Cantidad
    int cantidad;
    try {
        cantidad = Integer.parseInt(cantidadStr);
        if (cantidad <= 0) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Cantidad no válida. Debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Consultar la cantidad actual y el ProductoID en la base de datos
    int cantidadActual = 0;
    int productoID = 0;
    String sqlConsulta = "SELECT Cantidad, ProductoID FROM Movimientos WHERE MovimientoID = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstConsulta = conn.prepareStatement(sqlConsulta)) {

        pstConsulta.setInt(1, movimientoID);
        ResultSet rsConsulta = pstConsulta.executeQuery();

        if (rsConsulta.next()) {
            cantidadActual = rsConsulta.getInt("Cantidad");
            productoID = rsConsulta.getInt("ProductoID");
        } else {
            JOptionPane.showMessageDialog(this, "ID de movimiento no encontrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        rsConsulta.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al consultar la cantidad actual y ProductoID: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar si la cantidad actual es suficiente para la salida
    if (cantidad > cantidadActual) {
        JOptionPane.showMessageDialog(this, "Cantidad solicitada excede la cantidad disponible. Por favor, actualice la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Actualizar el registro existente con la nueva cantidad
    String sqlActualizar = "UPDATE Movimientos SET Cantidad = ? WHERE MovimientoID = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstActualizar = conn.prepareStatement(sqlActualizar)) {

        pstActualizar.setInt(1, cantidadActual - cantidad); // Actualizar la cantidad restante
        pstActualizar.setInt(2, movimientoID);

        int filasAfectadas = pstActualizar.executeUpdate();
        if (filasAfectadas == 0) {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el movimiento existente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar el movimiento existente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Insertar el nuevo movimiento de salida en la base de datos
    String sqlInsertar = "INSERT INTO Movimientos (ProductoID, Fecha, Cantidad, TipoMovimiento, Descripción) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstInsertar = conn.prepareStatement(sqlInsertar)) {

        pstInsertar.setInt(1, productoID); // Insertar el ProductoID obtenido
        pstInsertar.setTimestamp(2, fecha); // Usar Timestamp para fecha y hora
        pstInsertar.setInt(3, cantidad); // Cantidad restada en el nuevo registro
        pstInsertar.setString(4, tipoMovimiento);
        pstInsertar.setString(5, descripcionStr);

        int filasAfectadas = pstInsertar.executeUpdate();
        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(this, "Movimiento registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarDatosProductos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar el movimiento.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al insertar el movimiento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}



    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        productolista = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        codigomovimiento = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        productoseleccionado = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        descripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jScrollPane1.setViewportView(productolista);

        jLabel7.setText("Seleccionar producto");

        codigomovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigomovimientoActionPerformed(evt);
            }
        });

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        jLabel1.setText("Registro de Salida de producto");

        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });

        jLabel3.setText("Producto seleccionado");

        jLabel4.setText("Codigo Entrada");

        jLabel5.setText("Cantidad");

        jLabel6.setText("Fecha registro");

        productoseleccionado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(productoseleccionado);

        jButton1.setText("Registrar Salida");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Descripción");

        jCheckBox2.setText("Sin nota");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(codigomovimiento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(cantidad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(fecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(descripcion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCheckBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codigomovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(fecha)
                                .addGap(38, 38, 38))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 158, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2)
                    .addContainerGap()))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigomovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(108, 108, 108)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                    .addContainerGap(236, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(47, 47, 47)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(315, 315, 315)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codigomovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigomovimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigomovimientoActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed

    }//GEN-LAST:event_fechaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Movimientosalida();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // Verifica si el CheckBox está seleccionado
        if (jCheckBox2.isSelected()) {
            // Si está seleccionado, coloca un texto en el JTextField
            descripcion.setText("Sin descripción");
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

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
            java.util.logging.Logger.getLogger(movimiento_salida_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(movimiento_salida_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(movimiento_salida_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(movimiento_salida_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new movimiento_salida_producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField codigomovimiento;
    private javax.swing.JTextField descripcion;
    private javax.swing.JTextField fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable productolista;
    private javax.swing.JTable productoseleccionado;
    // End of variables declaration//GEN-END:variables
}
