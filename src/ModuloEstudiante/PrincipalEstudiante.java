/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import ModuloAsignacionEST.PrincipalAsignacionEST;
import java.sql.Connection;

/**
 *
 * @author pc
 */
public class PrincipalEstudiante extends javax.swing.JDialog {
    private Connection conexion;
    /**
     * Creates new form PrincipalEstudiante
     */
    public PrincipalEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public PrincipalEstudiante(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
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
        asignar_estudiantes = new javax.swing.JButton();
        reasignar_estudiantes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        crear_estudiante.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        crear_estudiante.setText("Crear Estudiante");
        crear_estudiante.setToolTipText("Permite crear un nuevo registro para un nuevo estudiante.\nTome en cuenta de que el programa le permitirá crear un nuevo estudiante siempre y cuando no tenga un Código Personal repetido con otro ya inscrito (el Código Personal es un atributo único para cada estudiante).");
        crear_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_estudianteActionPerformed(evt);
            }
        });

        ver_estudiante.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ver_estudiante.setText("Ver información de Estudiante");
        ver_estudiante.setToolTipText("Permite visualizar la información general de cada estudiante, así como poder editar dicha información y las notas asociadas al estudiante, en el Ciclo escolar vigente.");
        ver_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_estudianteActionPerformed(evt);
            }
        });

        asignar_estudiantes.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        asignar_estudiantes.setText("Asignar Nuevos Estudiantes");
        asignar_estudiantes.setToolTipText("Permite crear un nuevo registro para asignar un Estudiante a un Ciclo Escolar y a un Grado.\nEsta función es útil en caso de que a la hora de crear un nuevo estudiante no se crea la Asignación correspondiete (sólo mostrará el listado de los Estudiantes que aún no han sido asignados).");
        asignar_estudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignar_estudiantesActionPerformed(evt);
            }
        });

        reasignar_estudiantes.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        reasignar_estudiantes.setText("Reasignar Estudiantes");
        reasignar_estudiantes.setToolTipText("Permite crear un nuevo registro para asignar un Estudiante a un Ciclo Escolar y a un Grado.\nEsta función es útil en caso de que a la hora de crear un nuevo estudiante no se crea la Asignación correspondiete (sólo mostrará el listado de los Estudiantes que aún no han sido asignados).");
        reasignar_estudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reasignar_estudiantesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crear_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ver_estudiante))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(asignar_estudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reasignar_estudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crear_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ver_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asignar_estudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reasignar_estudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crear_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_estudianteActionPerformed
        this.setVisible(false);
        CrearEstudiante nueva_ventana = new CrearEstudiante(new javax.swing.JFrame(), true, conexion);
        nueva_ventana.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_crear_estudianteActionPerformed

    private void ver_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_estudianteActionPerformed
        //        this.setVisible(false);
        InformacionEstudiante nueva_ventana = new InformacionEstudiante(new javax.swing.JFrame(), true, conexion);
        nueva_ventana.setVisible(true);
        //        this.setVisible(true);
    }//GEN-LAST:event_ver_estudianteActionPerformed

    private void asignar_estudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignar_estudiantesActionPerformed
        this.setVisible(false);
        PrincipalAsignacionEST nueva_ventana = new PrincipalAsignacionEST(new javax.swing.JFrame(), true, conexion, false);
        nueva_ventana.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_asignar_estudiantesActionPerformed

    private void reasignar_estudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reasignar_estudiantesActionPerformed
        this.setVisible(false);
        PrincipalAsignacionEST nueva_ventana = new PrincipalAsignacionEST(new javax.swing.JFrame(), true, conexion, true);
        nueva_ventana.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_reasignar_estudiantesActionPerformed

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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrincipalEstudiante dialog = new PrincipalEstudiante(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton asignar_estudiantes;
    private javax.swing.JButton crear_estudiante;
    private javax.swing.JButton reasignar_estudiantes;
    private javax.swing.JButton ver_estudiante;
    // End of variables declaration//GEN-END:variables
}
