/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_notas_y_reporte;
import Modulo_notas_y_reporte.ModuloCurso;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nasc_
 */
public class Ventanavisualizacionestudiante extends javax.swing.JFrame {
    
    DefaultTableModel model;
    Connection conexion;
    Statement sent;
    String año;
    ArrayList<String> ID;
    int posicion;
    /**
     * Creates new form Ventanavisualizacionestudiante
     */
    ModuloCurso cn = new ModuloCurso();
    public Ventanavisualizacionestudiante() throws SQLException {
        initComponents();
        conexion = cn.Conectar();
        cargar("");
        Calendar fecha = new GregorianCalendar();
        año = Integer.toString(fecha.get(Calendar.YEAR));
        Cargar_Datos();
        posicion = 0;
    }
    
    public void Cargar_Datos() throws SQLException
    {
        boolean encontrado = false;
        ID = new ArrayList<String>();
        Statement a = conexion.createStatement();
        ResultSet consulta = a.executeQuery("SELECT anio,Id FROM CicloEscolar ORDER BY anio");
        while(consulta.next())
        {
            String nuevo = consulta.getString(1);
            ID.add(consulta.getString(2));
            if(nuevo.equals(año)) encontrado = true;
            ciclo.addItem(nuevo);
        }
        if(encontrado == true)
        {
            ciclo.setSelectedItem(año);
        }
    }
    
    void cargar(String valor)
    {
        try {
            String j = ciclo.getSelectedItem().toString();
            conexion = cn.Conectar();
            String [] titulos={"Curso ID", "Nombre", "Estudiante Id", "Nombres", "Apellidos", "Codigo Personal"};
            String [] fila = new String [6];
        
            String sql = "SELECT curso.Id, curso.Nombre, estudiante.Id, estudiante.Nombres, estudiante.Apellidos, estudiante.CodigoPersonal FROM curso INNER JOIN asignacioncat ON curso.Id = asignacioncat.Curso_Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id INNER JOIN notas ON curso.Id = notas.Curso_Id INNER JOIN estudiante ON notas.Estudiante_Id = estudiante.Id WHERE curso.Id = '%" + valor + "%' AND cicloescolar.Anio = '%" + j + "%'";
        
            model = new DefaultTableModel(null, titulos);
            sent = conexion.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            
            while (rs.next()) {
                fila[0] = rs.getString("Curso_Id");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("Estudiante_Id");
                fila[3] = rs.getString("Nombres");
                fila[4] = rs.getString("Apellidos");
                fila[5] = rs.getString("CodigoPersonal");
                
                model.addRow(fila);
            }
            Tablaestudiante.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tablaestudiante = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Buscacurso = new javax.swing.JTextField();
        ciclo = new javax.swing.JComboBox();
        Volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estudiantes por curso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        Tablaestudiante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Tablaestudiante);

        jLabel1.setText("Búsqueda por curso");

        Buscacurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BuscacursoKeyReleased(evt);
            }
        });

        ciclo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cicloItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Buscacurso, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Buscacurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Volver.setText("Volver");
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Volver)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Volver)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscacursoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscacursoKeyReleased
        // TODO add your handling code here:
        cargar(Buscacurso.getText());
    }//GEN-LAST:event_BuscacursoKeyReleased

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
        // TODO add your handling code here:
        Pantalla a = null;
        try {
            a = new Pantalla();
        } catch (SQLException ex) {
            Logger.getLogger(Ventanavisualizacionestudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.setVisible(true);
        //this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_VolverActionPerformed

    private void cicloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cicloItemStateChanged
        // TODO add your handling code here:
        cargar(Buscacurso.getText());
    }//GEN-LAST:event_cicloItemStateChanged

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
            java.util.logging.Logger.getLogger(Ventanavisualizacionestudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventanavisualizacionestudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventanavisualizacionestudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventanavisualizacionestudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Ventanavisualizacionestudiante().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Ventanavisualizacionestudiante.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Buscacurso;
    private javax.swing.JTable Tablaestudiante;
    private javax.swing.JButton Volver;
    private javax.swing.JComboBox ciclo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
