/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import Tipos_tablas.No_editable;
import Ventanas_Modulo_Ciclo_Escolar.Crear_Grados;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author USUARIO
 */
public class Ciclo_Escolar extends javax.swing.JFrame {
    Connection base;
    ArrayList<String>  ID ;
    String año;
    /**
     * Creates new form Ciclo_Escolar
     */
    public Ciclo_Escolar() {
        initComponents();
    }
    
    public Ciclo_Escolar(Connection conexion) throws SQLException {
        initComponents();
        base = conexion;
        Calendar fecha = new GregorianCalendar();
        año = Integer.toString(fecha.get(Calendar.YEAR));
        Cargar_Datos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        ciclo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Cursos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Grados = new javax.swing.JTable();
        jMenuBar2 = new javax.swing.JMenuBar();
        Menu_crear = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ciclo Escolar");

        ciclo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cicloItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Grados");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Cursos");

        Cursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Cursos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CursosKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(Cursos);

        Grados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Grados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GradosKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(Grados);

        Menu_crear.setText("Crear");

        jMenuItem1.setText("Ciclo Escolar");
        Menu_crear.add(jMenuItem1);

        jMenuItem2.setText("Grado");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Menu_crear.add(jMenuItem2);

        jMenuItem3.setText("Curso");
        Menu_crear.add(jMenuItem3);

        jMenuBar2.add(Menu_crear);

        jMenu1.setText("Modificar");

        jMenuItem5.setText("Ciclo Escolar");
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Grado");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("Curso");
        jMenu1.add(jMenuItem7);

        jMenuBar2.add(jMenu1);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(124, 124, 124))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cicloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cicloItemStateChanged
        int posicion = ciclo.getSelectedIndex();
        String Id = ID.get(posicion);
        Statement a;
        try {
            a = base.createStatement();
            ResultSet consulta_Grados = a.executeQuery("SELECT grado.Nombre,grado.Seccion FROM componentes INNER JOIN grado ON componentes.Grado_Id = grado.Id WHERE componentes.CicloEscolar_Id="+Id+";");
            a = base.createStatement();
            ResultSet consulta_Curso = a.executeQuery("SELECT curso.Nombre, curso.`Catedrático_Id` FROM curso INNER JOIN componentes ON curso.Id = componentes.Curso_Id    WHERE componentes.CicloEscolar_Id="+Id+";");
            if(consulta_Grados.next()== true){
              Tabla_Grados(consulta_Grados);
            }
            if(consulta_Curso.next()== true){
                Tabla_cursos(consulta_Curso);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_cicloItemStateChanged

    private void CursosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CursosKeyTyped
        evt.consume();
    }//GEN-LAST:event_CursosKeyTyped

    private void GradosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GradosKeyPressed
        evt.consume();
    }//GEN-LAST:event_GradosKeyPressed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.setVisible(false);
        Crear_Grados a = new Crear_Grados(new Frame(),true,base);
        a.setVisible(true);
        this.setVisible(true);
        System.out.println("hola");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(Ciclo_Escolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ciclo_Escolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ciclo_Escolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ciclo_Escolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ciclo_Escolar().setVisible(true);
            }
        });
    }

    public void Cargar_Datos() throws SQLException{
        boolean encontrado = false;
        ID = new ArrayList<String>();
        Statement a = base.createStatement();
        ResultSet consulta = a.executeQuery("SELECT anio,Id FROM CicloEscolar ORDER BY anio");
        while(consulta.next()){
            String nuevo = consulta.getString(1);
            ID.add(consulta.getString(2));
            if(nuevo.equals(año)) encontrado = true;
            ciclo.addItem(nuevo);
        }
        if(encontrado == true){
            ciclo.setSelectedItem(año);
        }
    }
    public void Tabla_Grados(ResultSet consulta) throws SQLException{
        No_editable tabla = new No_editable();
        tabla.addColumn("Nombre");
        tabla.addColumn("Sección");
        String[] fila = new String[2];
        fila[0] = consulta.getString(1);
        fila[1] = consulta.getString(2);
        tabla.addRow(fila);
        while(consulta.next()){
            fila[0] = consulta.getString(1);
            fila[1] = consulta.getString(2);
            tabla.addRow(fila);
        }
        tabla.isCellEditable(0, 0);
        Grados.setModel(tabla);
    }
    
    public void Tabla_cursos(ResultSet consulta) throws SQLException{
        Statement a = base.createStatement();
        No_editable tabla = new No_editable();
        String[] fila = new String[2];
        String catedratico;
        // Se ingresa la primera fila ya que consulta si contiene algo
        tabla.addColumn("Nombre");
        tabla.addColumn("Catedratico Asignado");
        ResultSet con_catedratico = a.executeQuery("SELECT catedratico.Nombres,catedratico.Apellidos FROM curso INNER JOIN catedratico ON curso.`Catedrático_Id` = catedratico.Id WHERE curso.Id ="+consulta.getString(2)+";");
        fila[0] = consulta.getString(1);
        //Se verifica si el curso ya tiene un catedratico asignado
        if(con_catedratico.next()){
            catedratico = con_catedratico.getString(1)+" "+con_catedratico.getString(2);
        }
        else{
            catedratico = "Catedratico no asignado";
        }
        fila[1] = catedratico;
        tabla.addRow(fila);
        
        //Se ingresa las demas filas
        while (consulta.next()){
            a = base.createStatement();
            con_catedratico = a.executeQuery("SELECT catedratico.Nombres,catedratico.Apellidos FROM curso INNER JOIN catedratico ON curso.`Catedrático_Id` = catedratico.Id WHERE curso.Id ="+consulta.getString(2)+";");
            fila[0] = consulta.getString(1);
            //Se verifica si el curso ya tiene un catedratico asignado
            if(con_catedratico.next()){
                catedratico = con_catedratico.getString(1)+" "+con_catedratico.getString(2);
             }
            else{
                catedratico = "Catedratico no asignado";
            }
            fila[1] = catedratico;
            tabla.addRow(fila);    
        }
    
       Cursos.setModel(tabla);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Cursos;
    private javax.swing.JTable Grados;
    private javax.swing.JMenu Menu_crear;
    private javax.swing.JComboBox<String> ciclo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
