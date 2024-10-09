
package com.mycompany.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

public class primerusuario extends javax.swing.JFrame {

    public primerusuario() {
        initComponents();
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

        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        contrasenia = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        pregunta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        confincontrasenia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        respuestas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel11.setText("Respuesta de seguridad");

        jLabel12.setText("Registro primer usuario");

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setText("Usuario");

        jLabel8.setText("Contraseña");

        jLabel9.setText("Confirma contraseña");

        jLabel10.setText("Pregunta de seguridad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pregunta)
                            .addComponent(usuario)
                            .addComponent(contrasenia)
                            .addComponent(respuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(confincontrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confincontrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(respuestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    // Obtener los valores ingresados por el usuario
String nombreUsuario = usuario.getText().trim();
String contrasena = contrasenia.getText().trim();
String confirmarContrasena = confincontrasenia.getText().trim();
String preguntaSeguridad = pregunta.getText().trim();
String respuestaSeguridad = respuestas.getText().trim();

// Validar que ningún campo esté vacío
if (nombreUsuario.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty() || preguntaSeguridad.isEmpty() || respuestaSeguridad.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    return;
}

// Verificar que la contraseña y la confirmación coincidan
if (!contrasena.equals(confirmarContrasena)) {
    javax.swing.JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    return;
}

// Hashear la contraseña
String contrasenaHasheada = PasswordHasher.hashPassword(contrasena);

try (Connection conn = DatabaseConnection.getConnection()) {
    // Verificar si el usuario ya existe
    String checkSql = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
    try (PreparedStatement checkStatement = conn.prepareStatement(checkSql)) {
        checkStatement.setString(1, nombreUsuario);
        try (ResultSet checkResultSet = checkStatement.executeQuery()) {
            if (checkResultSet.next() && checkResultSet.getInt(1) > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "El nombre de usuario ya está registrado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    // Insertar el nuevo usuario con rol "admin"
    String insertSql = "INSERT INTO usuarios (nombre_usuario, contrasena, pregunta_seguridad, respuesta_seguridad, rol) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement insertStatement = conn.prepareStatement(insertSql)) {
        insertStatement.setString(1, nombreUsuario);
        insertStatement.setString(2, contrasenaHasheada);
        insertStatement.setString(3, preguntaSeguridad);
        insertStatement.setString(4, respuestaSeguridad);
        insertStatement.setString(5, "admin"); // Establecer el rol como "admin"

        // Ejecutar la inserción
        int rowsInserted = insertStatement.executeUpdate();
        if (rowsInserted > 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            // Limpiar los campos después del registro
            limpiarCampos();
            Acceso complete = new Acceso(); // Asegúrate de tener un JFrame llamado Panel
            complete.setVisible(true);
            this.dispose();
        }
    }
} catch (SQLException e) {
    javax.swing.JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed
    // Método para limpiar los campos después de un registro exitoso
private void limpiarCampos() {
    usuario.setText("");
    contrasenia.setText("");
    confincontrasenia.setText("");
    pregunta.setText("");
    respuestas.setText("");
}
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new primerusuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField confincontrasenia;
    private javax.swing.JTextField contrasenia;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField pregunta;
    private javax.swing.JTextField respuestas;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
