
package com.mycompany.empresa;


public class menu extends javax.swing.JFrame {

    public menu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        producto = new javax.swing.JMenuItem();
        proveedor = new javax.swing.JMenuItem();
        categoria = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        entradaproductomovimiento = new javax.swing.JMenuItem();
        salidaproductomovimiento = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        entradaproductoreporte = new javax.swing.JMenuItem();
        salidaproductoreporte = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Menu");

        jMenu4.setText("Registro");

        producto.setText("Producto");
        producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoActionPerformed(evt);
            }
        });
        jMenu4.add(producto);

        proveedor.setText("Proveedor");
        proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorActionPerformed(evt);
            }
        });
        jMenu4.add(proveedor);

        categoria.setText("Categoria");
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });
        jMenu4.add(categoria);

        jMenu1.add(jMenu4);
        jMenu1.add(jSeparator3);

        jMenu5.setText("Movimientos");

        entradaproductomovimiento.setText("Entrada de productos");
        entradaproductomovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaproductomovimientoActionPerformed(evt);
            }
        });
        jMenu5.add(entradaproductomovimiento);

        salidaproductomovimiento.setText("Salida de productos");
        salidaproductomovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidaproductomovimientoActionPerformed(evt);
            }
        });
        jMenu5.add(salidaproductomovimiento);

        jMenu1.add(jMenu5);

        jMenu6.setText("Reportes");

        entradaproductoreporte.setText("Entrada de producto");
        entradaproductoreporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaproductoreporteActionPerformed(evt);
            }
        });
        jMenu6.add(entradaproductoreporte);

        salidaproductoreporte.setText("Salida de productos");
        salidaproductoreporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidaproductoreporteActionPerformed(evt);
            }
        });
        jMenu6.add(salidaproductoreporte);

        jMenu1.add(jMenu6);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void entradaproductomovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaproductomovimientoActionPerformed
        movimiento_entrada_producto entradaFrame = new movimiento_entrada_producto();
        entradaFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_entradaproductomovimientoActionPerformed

    private void entradaproductoreporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaproductoreporteActionPerformed
        reportes_entrada_producto reporteEntradaFrame = new reportes_entrada_producto();
        reporteEntradaFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_entradaproductoreporteActionPerformed

    private void productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoActionPerformed
        registro_producto productoFrame = new registro_producto();
        productoFrame.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_productoActionPerformed

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        registro_categoria categoriaFrame = new registro_categoria();
        categoriaFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_categoriaActionPerformed

    private void proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorActionPerformed
        registro_proveedor proveedorFrame = new registro_proveedor();
        proveedorFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_proveedorActionPerformed

    private void salidaproductomovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaproductomovimientoActionPerformed
        movimiento_salida_producto salidaFrame = new movimiento_salida_producto();
        salidaFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_salidaproductomovimientoActionPerformed

    private void salidaproductoreporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaproductoreporteActionPerformed
        reportes_salida_producto reporteSalidaFrame = new reportes_salida_producto();
        reporteSalidaFrame.setVisible(true);
        this.setVisible(false);    
    }//GEN-LAST:event_salidaproductoreporteActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem categoria;
    private javax.swing.JMenuItem entradaproductomovimiento;
    private javax.swing.JMenuItem entradaproductoreporte;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem producto;
    private javax.swing.JMenuItem proveedor;
    private javax.swing.JMenuItem salidaproductomovimiento;
    private javax.swing.JMenuItem salidaproductoreporte;
    // End of variables declaration//GEN-END:variables
}
