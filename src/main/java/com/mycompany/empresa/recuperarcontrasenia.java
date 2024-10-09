
package com.mycompany.empresa;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;

public class recuperarcontrasenia extends javax.swing.JFrame {

    public recuperarcontrasenia() {
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
        usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        respuestas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        contrasenia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        conficontrasenia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        recuperar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario");

        jLabel2.setText("Respuesta pregunta seguridad");

        jLabel3.setText("Cambio contraseña");

        jLabel4.setText("Confirmar contraseña");

        jLabel5.setText("Cambiar contraseña");

        recuperar.setText("Recuperar");
        recuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recuperarActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar");
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
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(recuperar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(respuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(contrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(conficontrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(respuestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conficontrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(recuperar))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void actualizarContrasena(String nombreUsuario, String contrasenaHasheada) {
    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE nombre_usuario = ?";
        try (java.sql.PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, contrasenaHasheada);
            statement.setString(2, nombreUsuario);

            // Ejecutar la actualización
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Contraseña actualizada exitosamente.");
                // Limpiar los campos después de la actualización
                limpiarCampos();
                // Mostrar el formulario de acceso
                Acceso accesoForm = new Acceso();
                accesoForm.setVisible(true);
                this.dispose(); // Cerrar el formulario de inicio de sesión
            }
        }
    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar la contraseña: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void recuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recuperarActionPerformed
    String nombreUsuario = usuario.getText().trim();
    String respuestaIngresada = respuestas.getText().trim();
    String nuevaContrasena = contrasenia.getText().trim();
    String confirmarContrasena = conficontrasenia.getText().trim();

    // Verificar que los campos no estén vacíos
    if (nombreUsuario.isEmpty() || respuestaIngresada.isEmpty() || nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar que la contraseña y la confirmación coincidan
    if (!nuevaContrasena.equals(confirmarContrasena)) {
        javax.swing.JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar la respuesta de seguridad y actualizar la contraseña
    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "SELECT respuesta_seguridad FROM usuarios WHERE nombre_usuario = ?";
        try (java.sql.PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nombreUsuario);
            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String respuestaCorrecta = resultSet.getString("respuesta_seguridad");

                    // Verificar si la respuesta ingresada coincide con la respuesta almacenada
                    if (respuestaCorrecta.equalsIgnoreCase(respuestaIngresada)) {
                        // Hashear la nueva contraseña
                        String contrasenaHasheada = PasswordHasher.hashPassword(nuevaContrasena);

                        // Actualizar la contraseña en la base de datos
                        actualizarContrasena(nombreUsuario, contrasenaHasheada);
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "La respuesta de seguridad no es correcta.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "El usuario no existe.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error al recuperar la contraseña: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_recuperarActionPerformed

    
    private void limpiarCampos() {
    usuario.setText("");
    respuestas.setText("");
    contrasenia.setText("");
    conficontrasenia.setText("");
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Acceso complete = new Acceso(); // Asegúrate de tener un JFrame llamado Panel
        complete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new recuperarcontrasenia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField conficontrasenia;
    private javax.swing.JTextField contrasenia;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton recuperar;
    private javax.swing.JTextField respuestas;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
