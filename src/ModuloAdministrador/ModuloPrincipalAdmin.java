/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloAdministrador;
import Modulo_incio_sesion.CambiarContraseña;
import ModuloPrestamos.ModuloPrestamo;
import Catedratico.Mostrar_Datos;
import ModuloAsignacionEST.PrincipalAsignacionEST;
import ModuloEstudiante.CrearEstudiante;
import ModuloEstudiante.InformacionEstudiante;
import ModuloPrestamos.ModuloPaquetes;
import Modulo_Ciclo_Escolar.Ciclo_Escolar;
import Modulo_Ciclo_Escolar.Crear_Ciclo_Escolar_1;
import Modulo_notas_y_reporte.Ventanareporte;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author SERGIO MALDONADO
 */
public class ModuloPrincipalAdmin extends javax.swing.JFrame {
    private JFrame ventanaPadre;
    private Connection conexcion;
    private ResultSet User;
    
    /**
     * Creates new form ModuloPrincipalAdmin
     */
    public ModuloPrincipalAdmin() {
        initComponents();
    }
    
    public ModuloPrincipalAdmin(Connection conec, ResultSet admin, JFrame ventanaPadre){
        initComponents();
        this.ventanaPadre = ventanaPadre;
        this.setLocationRelativeTo(null);
        conexcion = conec;
        User = admin;
        ResultSet resultado = null, resultado2 = null;
        try {
            //Desplegar informacion
            this.setTitle("Sesión Iniciada: "+User.getString("NombreUsuario"));
            Usuario.setText(User.getString("NombreUsuario"));
            Nombre.setText(User.getString("Nombres")+ " " + User.getString("Apellidos"));
            Direccion.setText(User.getString("Direccion"));
            DPI.setText(User.getString("Dpi"));
            Sexo.setText(User.getString("Sexo"));
            //Tipo.setText(User.getString("Tipo"));
            Statement sentencia = conexcion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery("SELECT * FROM Municipio WHERE Id = " + User.getString(13));
            resultado.next();
            Municipio.setText(resultado.getString("Nombre"));
            //Statement sentencia2 = conexcion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado2 = sentencia.executeQuery("SELECT * FROM Telefono WHERE Administrador_Id = " + User.getString(6));
            if(resultado2.next()==true){
                Telefono.setText(resultado2.getString("Telefono"));
            }
            else{
                Telefono.setText("No Disponible");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ModuloPrincipalAdmin(Connection conexion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Usuario = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        Direccion = new javax.swing.JTextField();
        DPI = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Sexo = new javax.swing.JTextField();
        Municipio = new javax.swing.JTextField();
        Telefono = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_crear = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        item_nuevo_estudiante = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        Reporte = new javax.swing.JMenuItem();
        menu_ver = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        item_info_estudiantes = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        menu_asignaciones = new javax.swing.JMenu();
        item_asignaciones = new javax.swing.JMenuItem();
        item_reasignaciones = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sesión iniciada: Administrador");
        setPreferredSize(new java.awt.Dimension(900, 590));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menú Administrador");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 13, 855, 22);
        getContentPane().add(jLabel11);
        jLabel11.setBounds(1024, 637, 0, 0);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Cerrar Sesión");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(333, 401, 175, 38);
        getContentPane().add(jLabel12);
        jLabel12.setBounds(1024, 537, 0, 0);
        getContentPane().add(jLabel13);
        jLabel13.setBounds(1003, 537, 0, 0);
        getContentPane().add(jLabel14);
        jLabel14.setBounds(619, 544, 0, 0);
        getContentPane().add(jLabel15);
        jLabel15.setBounds(612, 537, 0, 0);
        getContentPane().add(jLabel16);
        jLabel16.setBounds(1010, 527, 0, 0);
        getContentPane().add(jLabel17);
        jLabel17.setBounds(1017, 527, 0, 0);
        getContentPane().add(jLabel18);
        jLabel18.setBounds(262, 637, 256, 0);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información Administrdor"));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Usuario");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nombre Completo");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Dirección");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("DPI");

        Usuario.setEditable(false);
        Usuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Nombre.setEditable(false);
        Nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Direccion.setEditable(false);
        Direccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        DPI.setEditable(false);
        DPI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Sexo");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Municipio");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Telefono");

        Sexo.setEditable(false);
        Sexo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Municipio.setEditable(false);
        Municipio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Telefono.setEditable(false);
        Telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(91, 91, 91)
                        .addComponent(Usuario))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31)
                        .addComponent(Nombre))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(81, 81, 81)
                        .addComponent(Direccion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(DPI, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(38, 38, 38)
                        .addComponent(Municipio))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(DPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(30, 70, 853, 189);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 539, 0, 0);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Cambiar Contraseña");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(330, 340, 175, 37);
        getContentPane().add(jLabel9);
        jLabel9.setBounds(599, 268, 0, 260);

        menu_crear.setText("Crear");
        menu_crear.setPreferredSize(new java.awt.Dimension(40, 19));

        jMenuItem1.setText("Usuarios");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu_crear.add(jMenuItem1);

        jMenuItem2.setText("Catedrático");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_crear.add(jMenuItem2);

        item_nuevo_estudiante.setText("Nuevo Estudiante");
        item_nuevo_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_nuevo_estudianteActionPerformed(evt);
            }
        });
        menu_crear.add(item_nuevo_estudiante);

        jMenuItem3.setText("Ciclo Escolar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu_crear.add(jMenuItem3);

        jMenuItem7.setText("Prestamos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menu_crear.add(jMenuItem7);

        jMenuItem6.setText("Paquete Libro");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menu_crear.add(jMenuItem6);

        Reporte.setText("Reporte");
        Reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteActionPerformed(evt);
            }
        });
        menu_crear.add(Reporte);

        jMenuBar1.add(menu_crear);

        menu_ver.setText("Ver");
        menu_ver.setPreferredSize(new java.awt.Dimension(40, 19));

        jMenuItem4.setText("Catedráticos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menu_ver.add(jMenuItem4);

        item_info_estudiantes.setText("Información de Estudiantes");
        item_info_estudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_info_estudiantesActionPerformed(evt);
            }
        });
        menu_ver.add(item_info_estudiantes);

        jMenuItem9.setText("Ciclo Escolar");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menu_ver.add(jMenuItem9);

        jMenuItem8.setText("Usuarios");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menu_ver.add(jMenuItem8);

        jMenuBar1.add(menu_ver);

        menu_asignaciones.setText("Asignaciones");

        item_asignaciones.setText("Asignar Estudiantes");
        item_asignaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_asignacionesActionPerformed(evt);
            }
        });
        menu_asignaciones.add(item_asignaciones);

        item_reasignaciones.setText("Reasignar Estudiantes");
        item_reasignaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_reasignacionesActionPerformed(evt);
            }
        });
        menu_asignaciones.add(item_reasignaciones);

        jMenuBar1.add(menu_asignaciones);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            // TODO add your handling code here:
            if(User.getString("Tipo").equals("1")){
                //Si se puede llamar a la ventana
                this.setEnabled(false);
                new Usuarios(conexcion,this).setVisible(true);
                //this.dispose();
                //this.setEnabled(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "¡No tiene permisos para realizar esta acción!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void item_info_estudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_info_estudiantesActionPerformed
        InformacionEstudiante nueva_ventana = new InformacionEstudiante(new javax.swing.JFrame(), true, conexcion);
        nueva_ventana.setVisible(true);
    }//GEN-LAST:event_item_info_estudiantesActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
                this.setEnabled(false);
                new ModuloPrestamo(conexcion,this).setVisible(true);
                //this.setEnabled(true);
                //this.setVisible(false);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        new Mostrar_Datos(conexcion,this).setVisible(true);
        //this.setVisible(false);
        //this.setEnabled(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ventanaPadre.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        Mostrar_Datos ven = new Mostrar_Datos(conexcion,this);
        ven.setVisible(true);
        //this.setEnabled(true);
       // new Catedraticos(conexcion, this).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        try {
            // TODO add your handling code here:
            String tipo = User.getString("Tipo");
            if(tipo.equals("1")){
                this.setEnabled(false);
                new Ciclo_Escolar(new Frame(),true, conexcion).setVisible(true);
                this.setEnabled(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "¡No Posee Permisos para realizar esta operacion!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void item_nuevo_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_nuevo_estudianteActionPerformed
        CrearEstudiante nueva_ventana = new CrearEstudiante(new javax.swing.JFrame(), true, conexcion);
        nueva_ventana.setVisible(true);
    }//GEN-LAST:event_item_nuevo_estudianteActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //this.setVisible(false);
        Crear_Ciclo_Escolar_1 a;
        try {
            if(User.getString("Tipo").equals("1")){
                this.setEnabled(false);
                a = new Crear_Ciclo_Escolar_1(new Frame(),true, conexcion);
                a.setVisible(true);
                this.setEnabled(true);
                //this.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "¡No Posee Permisos para realizar esta operacion!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        new ModuloPaquetes(conexcion,this).setVisible(true);
        //this.setEnabled(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ventanaPadre.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new CambiarContraseña(this, true, conexcion, User).setVisible(true);
//        this.setEnabled(false);
//        new CambiarContra(conexcion,User,this).setVisible(true);
        //this.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void item_asignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_asignacionesActionPerformed
        PrincipalAsignacionEST asignaciones = new PrincipalAsignacionEST(new javax.swing.JFrame(), true, conexcion, false);
        asignaciones.setVisible(asignaciones.getHacerVisible());
    }//GEN-LAST:event_item_asignacionesActionPerformed

    private void item_reasignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_reasignacionesActionPerformed
        PrincipalAsignacionEST reasignaciones = new PrincipalAsignacionEST(new javax.swing.JFrame(), true, conexcion, true);
        reasignaciones.setVisible(reasignaciones.getHacerVisible());
    }//GEN-LAST:event_item_reasignacionesActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        try {
            // TODO add your handling code here:
            
            if(User.getString("Tipo").equals("1")){
                this.setEnabled(false);
                new ListadoUsuarios(conexcion,this).setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "¡No Posee Permisos para realizar esta operacion!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void ReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        Ventanareporte ventana = new Ventanareporte(conexcion,this);
        ventana.setVisible(true);
    }//GEN-LAST:event_ReporteActionPerformed


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
            java.util.logging.Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloPrincipalAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DPI;
    private javax.swing.JTextField Direccion;
    private javax.swing.JTextField Municipio;
    private javax.swing.JTextField Nombre;
    private javax.swing.JMenuItem Reporte;
    private javax.swing.JTextField Sexo;
    private javax.swing.JTextField Telefono;
    private javax.swing.JTextField Usuario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem item_asignaciones;
    private javax.swing.JMenuItem item_info_estudiantes;
    private javax.swing.JMenuItem item_nuevo_estudiante;
    private javax.swing.JMenuItem item_reasignaciones;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menu_asignaciones;
    private javax.swing.JMenu menu_crear;
    private javax.swing.JMenu menu_ver;
    // End of variables declaration//GEN-END:variables
}
