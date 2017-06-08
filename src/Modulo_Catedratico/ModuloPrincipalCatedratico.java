/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_Catedratico;

import Modulo_InicioSesion.CambiarContraseña;
import Modulo_NotasReporte.Pantalla;
import java.awt.Frame;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author oem y Wilson Xicará
 */
public class ModuloPrincipalCatedratico extends javax.swing.JFrame {
    private JFrame ventanaPadre;
    /**
     * Creates new form ModuloPrincipalCatedratico
     */
    Connection conexion;
    ResultSet respuesta;
    Statement stmt;
    
    String titulos [] = {"Nombre del curso",
                         "Grado",
                         "Seccion"};
    String fila[]= new String [3];
    DefaultTableModel modelo;
    int idcat, idcurso, idciclo;
    String Materia = "", A;
    ArrayList<String> Años;
    
    public ModuloPrincipalCatedratico() {
        initComponents();
    }
    
    public ModuloPrincipalCatedratico(Connection conex, ResultSet sentencia, JFrame ventanaPadre) throws SQLException{
        initComponents();
        this.setLocationRelativeTo(null);
        conexion = conex;
        respuesta = sentencia;
        this.ventanaPadre = ventanaPadre;
        ResultSet rs2=null;
        Calendar fecha = new GregorianCalendar();
        A = Integer.toString(fecha.get(Calendar.YEAR));
        LlenarComboBoxx();
        Llenartabla();
        LlenarDatos();
    }
    public void LlenarDatos() throws SQLException{
        String consulta = "SELECT catedratico.nombres, catedratico.apellidos FROM catedratico WHERE catedratico.id = "+ respuesta.getInt(5);
        ResultSet r = stmt.executeQuery(consulta);
        while(r.next()){
                Campo_Nombre.setText(r.getString("nombres"));
                Campo_Apellidos.setText(r.getString("apellidos"));
            }
        
    }
    public void LlenarComboBoxx() throws SQLException{
        boolean existe = false;
        Años = new ArrayList<String>();
        Statement stmt3 = conexion.createStatement();
        ResultSet consulta = stmt3.executeQuery("SELECT Id, anio FROM CicloEscolar ORDER BY anio");
        while(consulta.next()){
            String pivote = consulta.getString(2);
            Años.add(consulta.getString(1));
            if(pivote.equals(A)) existe = true;
            Campo_Año.addItem(pivote);
        }
        if(existe = true){
            Campo_Año.setSelectedItem(A);
        }
        
    }
    
    public void Llenartabla(){
        try{
           // int a = respuesta.getInt(5);
            Campo_Usuario.setText(respuesta.getString("NombreUsuario"));
            //Campo_Apellidos.setText(String.valueOf(respuesta.getInt(5)));
            idcat = respuesta.getInt(5);
            stmt = conexion.createStatement();
            String Opcion = Campo_Año.getSelectedItem().toString();
            
            ResultSet rs = stmt.executeQuery("SELECT curso.Nombre, grado.Nombre, grado.Seccion FROM asignacioncat "
                    + "INNER JOIN catedratico ON asignacioncat.Catedratico_Id = catedratico.Id " 
                    + "INNER JOIN curso ON asignacioncat.Curso_Id = curso.Id "
                    + "INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id "
                    + "INNER JOIN grado ON asignacioncat.Grado_Id = grado.Id "
                    + "WHERE catedratico.id = "+idcat+" AND cicloescolar.anio = '"+ Opcion+ "'");
            modelo = new DefaultTableModel (null,titulos);
            //ResultSet rs = stmt.executeQuery("select* from curso");
            while(rs.next()){
                fila[0] = rs.getString("curso.Nombre");
                fila[1] = rs.getString("grado.Nombre");
                fila[2] = rs.getString("grado.Seccion");
                //fila[1] = rs.getString("catedratico.Nombres");
                modelo.addRow(fila);
            }
            Tabla.setModel(modelo);
            TableColumn ci = Tabla.getColumn("Nombre del curso");
            ci.setMaxWidth(500);
            TableColumn ci2 = Tabla.getColumn("Grado");
            ci2.setMaxWidth(100);
            TableColumn ci3 = Tabla.getColumn("Seccion");
            ci3.setMaxWidth(100);
            
        }catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalCatedratico.class.getName()).log(Level.SEVERE, null, ex);
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

        Campo_Usuario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Campo_Año = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Campo_Nombre = new javax.swing.JTextField();
        Campo_Apellidos = new javax.swing.JTextField();
        cerrar_sesion = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Campo_Usuario.setEditable(false);
        Campo_Usuario.setBackground(new java.awt.Color(204, 204, 204));

        Tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tabla.getTableHeader().setReorderingAllowed(false);
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        jLabel1.setText("Bienvenido:");

        Campo_Año.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Campo_AñoItemStateChanged(evt);
            }
        });

        jLabel2.setText("Ciclo escolar:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del catedrático", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombres:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Apellidos:");

        Campo_Nombre.setEditable(false);
        Campo_Nombre.setBackground(new java.awt.Color(204, 204, 204));
        Campo_Nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        Campo_Apellidos.setEditable(false);
        Campo_Apellidos.setBackground(new java.awt.Color(204, 204, 204));
        Campo_Apellidos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Campo_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Campo_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cerrar_sesion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cerrar_sesion.setText("Cerrar Sesión");
        cerrar_sesion.setToolTipText("");
        cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar_sesionActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Grados y cursos que tiene asignado (Dar doble clic para editar las notas de un curso):");

        jMenu1.setText("Opciones");

        jMenuItem1.setText("Cambiar contraseña");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 170, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Campo_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Campo_Año, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(cerrar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Campo_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(Campo_Año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cerrar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        
    }//GEN-LAST:event_TablaMouseClicked

    private void Campo_AñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Campo_AñoItemStateChanged
        // TODO add your handling code here:
        if(Campo_Año.getSelectedIndex() != 1){
            int posicion = Campo_Año.getSelectedIndex();
            String p = Años.get(posicion);
            try{
           // int a = respuesta.getInt(5);
            Campo_Usuario.setText(respuesta.getString("NombreUsuario"));
            //Campo_Apellidos.setText(String.valueOf(respuesta.getInt(5)));
            idcat = respuesta.getInt(5);
            stmt = conexion.createStatement();
            String Opcion = Campo_Año.getSelectedItem().toString();
            
            ResultSet rs = stmt.executeQuery("SELECT curso.Nombre, grado.Nombre, grado.Seccion FROM asignacioncat "
                    + "INNER JOIN catedratico ON asignacioncat.Catedratico_Id = catedratico.Id " 
                    + "INNER JOIN curso ON asignacioncat.Curso_Id = curso.Id "
                    + "INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id "
                    + "INNER JOIN grado ON asignacioncat.Grado_Id = grado.Id "
                    + "WHERE catedratico.id = "+idcat+" AND cicloescolar.anio = '"+ Opcion+ "'");
            modelo = new DefaultTableModel (null,titulos);
            //ResultSet rs = stmt.executeQuery("select* from curso");
            while(rs.next()){
                fila[0] = rs.getString("curso.Nombre");
                fila[1] = rs.getString("grado.Nombre");
                fila[2] = rs.getString("grado.Seccion");
                //fila[1] = rs.getString("catedratico.Nombres");
                modelo.addRow(fila);
            }
            Tabla.setModel(modelo);
            TableColumn ci = Tabla.getColumn("Nombre del curso");
            ci.setMaxWidth(500);
            TableColumn ci2 = Tabla.getColumn("Grado");
            ci2.setMaxWidth(100);
            TableColumn ci3 = Tabla.getColumn("Seccion");
            ci3.setMaxWidth(100);
            
            
        }catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalCatedratico.class.getName()).log(Level.SEVERE, null, ex);
        }
            //String 
        }
    }//GEN-LAST:event_Campo_AñoItemStateChanged

    private void TablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMousePressed
         try{
                if(evt.getClickCount() > 1){
            int fila = Tabla.getSelectedRow();
            
            Statement stmt2 = conexion.createStatement();
            String sql = "SELECT * FROM curso WHERE curso.Nombre = '"+Tabla.getValueAt(fila, 0).toString()+"'";
            ResultSet respuesta4 = stmt2.executeQuery(sql);
            int id=0;
            //System.out.println(id);
            while (respuesta4.next()){
                 id = respuesta4.getInt(1);
                 System.out.println(id); 
                 idcurso = id;
             }
            String sql2 = "SELECT cicloescolar.Id FROM cicloescolar WHERE cicloescolar.Anio = '"+Campo_Año.getSelectedItem().toString()+"'";
            ResultSet respuesta5 = stmt2.executeQuery(sql2);
            int id2=0;
            //System.out.println(id);
            while (respuesta5.next()){
                 id2 = respuesta5.getInt(1);
                 //System.out.println(id); 
                 idciclo = id2;
             }
            
            System.out.println(idcat);
            Pantalla s = new Pantalla(conexion,idcat, idcurso, idciclo, this); // Llama a la del Andrés
            s.setVisible(true);
            this.setEnabled(false);
                }
        }catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalCatedratico.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_TablaMousePressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new CambiarContraseña(this, true, conexion, respuesta).setVisible(true);
            
            //this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrar_sesionActionPerformed
        ventanaPadre.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrar_sesionActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ventanaPadre.setVisible(true);
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
            java.util.logging.Logger.getLogger(ModuloPrincipalCatedratico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloPrincipalCatedratico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloPrincipalCatedratico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloPrincipalCatedratico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloPrincipalCatedratico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Campo_Apellidos;
    private javax.swing.JComboBox<String> Campo_Año;
    private javax.swing.JTextField Campo_Nombre;
    private javax.swing.JTextField Campo_Usuario;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton cerrar_sesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
