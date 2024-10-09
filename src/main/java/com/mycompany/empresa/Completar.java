
package com.mycompany.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Completar extends javax.swing.JFrame {

    public Completar() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        respuesta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        contrasenia = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        pregunta = new javax.swing.JTextField();
        confincontrasenia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        respuestas = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        usuario1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        contrasenia1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        pregunta1 = new javax.swing.JTextField();
        confincontrasenia1 = new javax.swing.JTextField();

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        jLabel3.setText("Confirma contraseña");

        jLabel4.setText("Pregunta de seguridad");

        jLabel5.setText("Respuesta de seguridad");

        jLabel6.setText("Completado de datos ");

        jButton1.setText("Guardar");

        jButton2.setText("Cancelar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setText("Usuario");

        jLabel8.setText("Contraseña");

        jLabel9.setText("Confirma contraseña");

        jLabel10.setText("Pregunta de seguridad");

        jLabel11.setText("Respuesta de seguridad");

        jLabel12.setText("Completado de datos ");

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

        confincontrasenia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confincontrasenia1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pregunta1)
                                    .addComponent(usuario1)
                                    .addComponent(contrasenia1)
                                    .addComponent(respuestas)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)))
                                .addGap(18, 18, 18)
                                .addComponent(confincontrasenia1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(9, 9, 9)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contrasenia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confincontrasenia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pregunta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(respuestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private boolean verificarUsuarioSinContrasena() {
    String nombreUsuario = usuario1.getText().trim(); // Obtener el nombre del usuario del JTextField
    if (nombreUsuario.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "SELECT contrasena, rol FROM usuarios WHERE nombre_usuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String contrasena = resultSet.getString("contrasena");
                    String rol = resultSet.getString("rol");

                    // Verifica si la contraseña es nula o vacía
                    if ((contrasena == null || contrasena.trim().isEmpty())) {
                        return true; // El usuario puede completar los datos
                    }
                }
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al verificar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return false; // El usuario no existe, o no cumple con las condiciones
}
    private void completarDatosFaltantes() {
    String nombreUsuario = usuario1.getText().trim(); // Obtener el nombre del usuario del JTextField
    if (nombreUsuario.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener los valores ingresados por el usuario
    String nuevaContrasena = contrasenia1.getText().trim();
    String confirmarContrasena = confincontrasenia1.getText().trim();
    String preguntaSeguridad = pregunta1.getText().trim();
    String respuestaSeguridad = respuestas.getText().trim();

    // Validar que ningún campo esté vacío
    if (nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty() || preguntaSeguridad.isEmpty() || respuestaSeguridad.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar que la contraseña y la confirmación coincidan
    if (!nuevaContrasena.equals(confirmarContrasena)) {
        JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Hashear la nueva contraseña
    String contrasenaHasheada = PasswordHasher.hashPassword(nuevaContrasena);

    // Actualizar los datos en la base de datos
    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "UPDATE usuarios SET contrasena = ?, pregunta_seguridad = ?, respuesta_seguridad = ? WHERE nombre_usuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, contrasenaHasheada);
            statement.setString(2, preguntaSeguridad);
            statement.setString(3, respuestaSeguridad);
            statement.setString(4, nombreUsuario);

            // Ejecutar la actualización
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Datos actualizados exitosamente.");
                // Limpiar los campos después de actualizar los datos
                limpiarCampos();
                Acceso complete = new Acceso(); // Asegúrate de tener un JFrame llamado Acceso
                complete.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudieron actualizar los datos. Verifique el nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    if (verificarUsuarioSinContrasena()) {
        completarDatosFaltantes();
    } else {
        JOptionPane.showMessageDialog(this, "El usuario no es válido o ya tiene una contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Acceso complete = new Acceso(); // Asegúrate de tener un JFrame llamado Panel
        complete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void confincontrasenia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confincontrasenia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confincontrasenia1ActionPerformed
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
                new Completar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField confincontrasenia;
    private javax.swing.JTextField confincontrasenia1;
    private javax.swing.JTextField contrasenia;
    private javax.swing.JTextField contrasenia1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField pregunta;
    private javax.swing.JTextField pregunta1;
    private javax.swing.JTextField respuesta;
    private javax.swing.JTextField respuestas;
    private javax.swing.JTextField usuario;
    private javax.swing.JTextField usuario1;
    // End of variables declaration//GEN-END:variables
}
