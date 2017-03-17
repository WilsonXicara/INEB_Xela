/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import ModuloAsignacionEST.AsignarEstudiante;
import java.sql.Connection;

/**
 *
 * @author Wilson Xicará
 */
public class PrincipalEstudiante extends javax.swing.JFrame {
    private Connection conexion;
    /**
     * Creates new form PrincipalEstudiante
     */
    public PrincipalEstudiante() {
        initComponents();
    }
    public PrincipalEstudiante(Connection conexion) {
        initComponents();
        this.conexion = conexion;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crear_estudiante = new javax.swing.JButton();
        ver_estudiante = new javax.swing.JButton();
        asignar_estudiante = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Módulo Estudiante");

        crear_estudiante.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        crear_estudiante.setText("Crear Estudiante");
        crear_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_estudianteActionPerformed(evt);
            }
        });

        ver_estudiante.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ver_estudiante.setText("Ver información de Estudiante");
        ver_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_estudianteActionPerformed(evt);
            }
        });

        asignar_estudiante.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        asignar_estudiante.setText("Asignar Estudiante");
        asignar_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignar_estudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crear_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ver_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(asignar_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crear_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ver_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(asignar_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crear_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_estudianteActionPerformed
        CrearEstudiante nueva_ventana = new CrearEstudiante(conexion);
        nueva_ventana.setVisible(true);
    }//GEN-LAST:event_crear_estudianteActionPerformed

    private void ver_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_estudianteActionPerformed
        InformacionEstudiante nueva_ventana = new InformacionEstudiante(conexion);
        nueva_ventana.setVisible(true);
    }//GEN-LAST:event_ver_estudianteActionPerformed

    private void asignar_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignar_estudianteActionPerformed
        AsignarEstudiante nueva_ventana = new AsignarEstudiante(conexion);
        nueva_ventana.setVisible(true);
    }//GEN-LAST:event_asignar_estudianteActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalEstudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton asignar_estudiante;
    private javax.swing.JButton crear_estudiante;
    private javax.swing.JButton ver_estudiante;
    // End of variables declaration//GEN-END:variables
}
