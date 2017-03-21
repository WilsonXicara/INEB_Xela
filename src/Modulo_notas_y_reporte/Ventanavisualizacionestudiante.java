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
        //cargar();
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
    
    void cargar()
    {
        try {
            String j = ciclo.getSelectedItem().toString();
            conexion = cn.Conectar();
            String [] titulos={"Curso ID", "Nombre", "Estudiante Id", "Nombres", "Apellidos", "Codigo Personal"};
            String [] fila = new String [6];
        
            String sql = "SELECT curso.Id, curso.Nombre, estudiante.Id, estudiante.Nombres, estudiante.Apellidos, estudiante.CodigoPersonal FROM curso INNER JOIN asignacioncat ON curso.Id = asignacioncat.Curso_Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id INNER JOIN notas ON curso.Id = notas.Curso_Id INNER JOIN estudiante ON notas.Estudiante_Id = estudiante.Id WHERE curso.Id = cicloescolar.Anio = '%" + j + "%'";
        
            model = new DefaultTableModel(null, titulos);
            sent = conexion.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            
            while (rs.next()) {
                fila[0] = rs.getString("Curso.Id");
                fila[1] = rs.getString("Curso.Nombre");
                fila[2] = rs.getString("Estudiante.Id");
                fila[3] = rs.getString("Estudiante.Nombres");
                fila[4] = rs.getString("Estudiante.Apellidos");
                fila[5] = rs.getString("Estudiante.CodigoPersonal");
                
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
        ciclo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        Volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estudiantes por curso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        Tablaestudiante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Tablaestudiante);

        ciclo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cicloItemStateChanged(evt);
            }
        });

        jLabel1.setText("Año");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
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
        //cargar();
        if(ciclo.getSelectedIndex()!=-1){
        posicion = ciclo.getSelectedIndex();
        String Id = ID.get(posicion);
        Statement a;
        try {
            //a = conexion.createStatement();
            //ResultSet consulta_Grados = a.executeQuery("SELECT curso.Id, curso.Nombre, estudiante.Id, estudiante.Nombres, estudiante.Apellidos FROM curso INNER JOIN asignacioncat ON curso.Id = asignacioncat.Curso_Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id INNER JOIN notas ON curso.Id = notas.Curso_Id INNER JOIN asignacionest ON asignacionest.Id = notas.AsignacionEST_Id INNER JOIN estudiante ON asignacionest.Estudiante_Id = estudiante.Id WHERE asignacionest.CicloEscolar_Id = "+ Id + ";");
            conexion = cn.Conectar();
            String [] titulos={"Curso ID", "Nombre", "Estudiante Id", "Nombres", "Apellidos"};
            String [] fila = new String [5];
            
            System.out.println(Id);
        
            String sql = "SELECT curso.Id, curso.Nombre, estudiante.Id, estudiante.Nombres, estudiante.Apellidos FROM curso INNER JOIN asignacioncat ON curso.Id = asignacioncat.Curso_Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id INNER JOIN notas ON curso.Id = notas.Curso_Id INNER JOIN asignacionest ON asignacionest.Id = notas.AsignacionEST_Id INNER JOIN estudiante ON asignacionest.Estudiante_Id = estudiante.Id WHERE asignacionest.CicloEscolar_Id = " + Id + ";";
        
            model = new DefaultTableModel(null, titulos);
            sent = conexion.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            
            while (rs.next()) {
                fila[0] = rs.getString("Curso.Id");
                fila[1] = rs.getString("Curso.Nombre");
                fila[2] = rs.getString("Estudiante.Id");
                fila[3] = rs.getString("Estudiante.Nombres");
                fila[4] = rs.getString("Estudiante.Apellidos");
                
                model.addRow(fila);
            }
            Tablaestudiante.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }//GEN-LAST:event_cicloItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Pantalla a = null;
        try {
            a = new Pantalla();
        } catch (SQLException ex) {
            Logger.getLogger(Ventanavisualizacionestudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_formWindowClosing

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
    private javax.swing.JTable Tablaestudiante;
    private javax.swing.JButton Volver;
    private javax.swing.JComboBox ciclo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
