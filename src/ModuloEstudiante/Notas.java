/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 * Clase utilizada para visualizar y modificar las Notas, de todos los cursos, de un Estudiante específico.
 * @author Wilson Xicará
 */
public class Notas extends javax.swing.JFrame {
    private Connection conexion;
    private ResultSet consultaNotas = null;
    private boolean notasEditadas;
    private int estudianteId;
    private DefaultTableModel tablaModel;
    /**
     * Creates new form Notas
     */
    public Notas() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.consultaNotas = null;
        this.notasEditadas = false;
        tablaModel = new DefaultTableModel();
        // Obtención de los encabezados de las columnas de la tabla
        tablaModel.addColumn("Curso");
        tablaModel.addColumn("Nota 1");
        tablaModel.addColumn("Nota 2");
        tablaModel.addColumn("Nota 3");
        tablaModel.addColumn("Nota 4");
        tablaModel.addColumn("Nota Recuperacion");
        tablaModel.addColumn("Nota Final");
    }
    /**
     * Inicializa la ventana con las notas de todos los cursos de un estudiante en específico
     * @param conexion objeto que permite la conexión y comunicación con la Base de Datos.
     * @param estudiante nodo de la consulta (tabla) que tiene la información del Estudiante.
     */
    public Notas(Connection conexion, ResultSet estudiante) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.conexion = conexion;
        this.consultaNotas = null;
        this.notasEditadas = false;
        
        tablaModel = new DefaultTableModel();
        // Obtención de los encabezados de las columnas de la tabla
        tablaModel.addColumn("Curso");
        tablaModel.addColumn("Nota 1");
        tablaModel.addColumn("Nota 2");
        tablaModel.addColumn("Nota 3");
        tablaModel.addColumn("Nota 4");
        tablaModel.addColumn("Nota Recuperacion");
        tablaModel.addColumn("Nota Final");
        
        try {
            // Inicio la carga de las notas de 'estudiante.getString("Id")'
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            consultaNotas = sentencia.executeQuery("SELECT Estudiante.Nombres, Estudiante.Apellidos, Curso.Nombre Curso, Notas.* FROM Estudiante "
                    + "INNER JOIN Notas ON Estudiante.Id = Notas.Estudiante_Id "
                    + "INNER JOIN Curso ON Notas.Curso_Id = Curso.Id "
                    + "WHERE Estudiante.Nombres = '"+estudiante.getString("Nombres")+"' AND Estudiante.Apellidos = '"+estudiante.getString("Apellidos")+"'");
            
            consultaNotas.next();
            this.estudianteId = consultaNotas.getInt("Estudiante_Id");
            this.setTitle("Notas del(la) estudiante "+consultaNotas.getString("Nombres")+" "+consultaNotas.getString("Apellidos"));
            nombre_estudiante.setText("Estudiante: "+consultaNotas.getString("Nombres")+" "+consultaNotas.getString("Apellidos"));
            
            // Extracción de todas las notas
            do {
                String[] registro = new String[7];
                registro[0] = consultaNotas.getString("Curso");
                registro[1] = consultaNotas.getString("Nota1");
                registro[2] = consultaNotas.getString("Nota2");
                registro[3] = consultaNotas.getString("Nota3");
                registro[4] = consultaNotas.getString("Nota4");
                registro[5] = consultaNotas.getString("NotaRecuperacion");
                registro[6] = consultaNotas.getString("NotaFinal");
                tablaModel.addRow(registro);
            } while (consultaNotas.next());
            tabla_notas.setModel(tablaModel);
            tabla_notas.getColumnModel().getColumn(0).setPreferredWidth(150);   // Curso
            tabla_notas.getColumnModel().getColumn(1).setPreferredWidth(50);    // Nota1
            tabla_notas.getColumnModel().getColumn(2).setPreferredWidth(50);    // Nota2
            tabla_notas.getColumnModel().getColumn(3).setPreferredWidth(50);    // Nota3
            tabla_notas.getColumnModel().getColumn(4).setPreferredWidth(50);    // Nota4
            tabla_notas.getColumnModel().getColumn(5).setPreferredWidth(100);   // NotaRecuperacion
            tabla_notas.getColumnModel().getColumn(6).setPreferredWidth(100);   // NotaFinal
            tabla_notas.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_notas = new javax.swing.JTable();
        nombre_estudiante = new javax.swing.JLabel();
        guardar_cambios = new javax.swing.JButton();
        editar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla_notas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_notas);

        nombre_estudiante.setText("Estudiante:");

        guardar_cambios.setText("Guardar cambios");
        guardar_cambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_cambiosActionPerformed(evt);
            }
        });

        editar.setText("Editar notas");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nombre_estudiante)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardar_cambios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(nombre_estudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar_cambios)
                    .addComponent(editar))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardar_cambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_cambiosActionPerformed
        // El guardado de las notas modificadas implica la actualización de todos los registros de 'consultaNotas'
        if (notasEditadas == true) {
            try {
                consultaNotas.first();  // Regreso al inicio de la lista en la consulta
                // Inicio la obtención de todos los campos (estén o no modificados, igual se sobreescribirán)
                int cantidadCursos = tabla_notas.getRowCount();
                for(int i=0; i<cantidadCursos; i++) {
                    String actualizarNota = "UPDATE Notas SET ";
                    actualizarNota+= "Nota1 = "+Integer.parseInt((String)tabla_notas.getValueAt(i, 1))+", ";    // Obtención de la nueva Nota1
                    actualizarNota+= "Nota2 = "+Integer.parseInt((String)tabla_notas.getValueAt(i, 2))+", ";    // Obtención de la nueva Nota2
                    actualizarNota+= "Nota3 = "+Integer.parseInt((String)tabla_notas.getValueAt(i, 3))+", ";    // Obtención de la nueva Nota3
                    actualizarNota+= "Nota4 = "+Integer.parseInt((String)tabla_notas.getValueAt(i, 4))+", ";    // Obtención de la nueva Nota4
                    actualizarNota+= "NotaRecuperacion = "+Integer.parseInt((String)tabla_notas.getValueAt(i, 5))+", ";    // Obtención de la nueva NotaRecuperacion
                    actualizarNota+= "NotaFinal = "+Integer.parseInt((String)tabla_notas.getValueAt(i, 6))+" ";    // Obtención de la nueva NotaFinal
                    actualizarNota+= "WHERE Estudiante_Id = "+estudianteId+" AND Curso_Id = "+consultaNotas.getInt("Curso_Id")+"";
                    PreparedStatement insercion = conexion.prepareStatement(actualizarNota);
                    insercion.executeUpdate();
                    consultaNotas.next();   // Avanzo al i-ésimo registro de la consulta
                }
            } catch (SQLException ex) {
                Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
            }
            tabla_notas.setEnabled(false);
            notasEditadas = false;
        }
    }//GEN-LAST:event_guardar_cambiosActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        tabla_notas.setEnabled(true);
        editar.setEnabled(false);
        notasEditadas = true;
    }//GEN-LAST:event_editarActionPerformed

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
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editar;
    private javax.swing.JButton guardar_cambios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombre_estudiante;
    private javax.swing.JTable tabla_notas;
    // End of variables declaration//GEN-END:variables
}
