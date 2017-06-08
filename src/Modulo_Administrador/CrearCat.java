/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilson Xicará y SERGIO MALDONADO
 */
public class CrearCat extends javax.swing.JFrame {
    private Connection conexion;
    private ResultSet datosCat = null;
    private DefaultTableModel modelo;
    private JFrame Ventanita;
    private int VarId = 0;
    private ArrayList<Integer> listaIDCatedraticos;
    /**
     * Creates new form CrearCat
     */
    
    public CrearCat() {
        initComponents();
    }
    public CrearCat(Connection conec,JFrame ventana){
        initComponents();
        Ventanita = ventana;
        this.setLocationRelativeTo(null);
        conexion = conec;
        modelo = (DefaultTableModel) Datos.getModel();
        listaIDCatedraticos = new ArrayList<>();
        cargar_catedraticos_sin_usuario(); //revisar la funcion
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Datos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        regresar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Dpi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nombre_usuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        contraseña = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        confirmacion = new javax.swing.JPasswordField();
        crear_usuario = new javax.swing.JButton();
        elegir_catedratico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Creacion de Usuarios Catedraticos");

        Datos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nombres", "Apellidos", "DPI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Datos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(Datos);
        if (Datos.getColumnModel().getColumnCount() > 0) {
            Datos.getColumnModel().getColumn(0).setPreferredWidth(40);
            Datos.getColumnModel().getColumn(1).setPreferredWidth(130);
            Datos.getColumnModel().getColumn(2).setPreferredWidth(130);
            Datos.getColumnModel().getColumn(3).setPreferredWidth(115);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Catedráticos Disponibles (sin cuenta de usuario):");

        regresar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cuenta de usuario:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Nombre completo:");

        Nombre.setEditable(false);
        Nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("DPI:");

        Dpi.setEditable(false);
        Dpi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Usuario:");

        nombre_usuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Contraseña:");

        contraseña.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Confirmar Contraseña:");

        confirmacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        crear_usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        crear_usuario.setText("Crear Usuario");
        crear_usuario.setEnabled(false);
        crear_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_usuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dpi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(crear_usuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Dpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(crear_usuario)
                .addContainerGap())
        );

        elegir_catedratico.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        elegir_catedratico.setText("Elegir");
        elegir_catedratico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elegir_catedraticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(elegir_catedratico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(regresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(elegir_catedratico))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void elegir_catedraticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elegir_catedraticoActionPerformed
        int rango[] = Datos.getSelectedRows();
        if (rango.length == 1) {
            String nomb, ape, dpi;
            nomb = Datos.getValueAt(Datos.getSelectedRow(), 1).toString();
            ape = Datos.getValueAt(Datos.getSelectedRow(), 2).toString();
            dpi = Datos.getValueAt(Datos.getSelectedRow(), 3).toString();
            Nombre.setText(nomb + " " + ape);
            Dpi.setText(dpi);
            VarId = listaIDCatedraticos.get(rango[0]);
            crear_usuario.setEnabled(true);
//            VarId = Integer.parseInt(Datos.getValueAt(Datos.getSelectedRow(), 0).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione "+(rango.length==0?"al menos":"sólo")+"un registro.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_elegir_catedraticoActionPerformed

    private void crear_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_usuarioActionPerformed
        // Condiciones para validar que la cuenta de usuario no tenga campos vacíos
        if (nombre_usuario.getText().length() == 0)
            JOptionPane.showMessageDialog(this, "El nombre de usuario no puede ser nulo", "Error en datos", JOptionPane.ERROR_MESSAGE);
        else if (!comparar_contraseñas(contraseña.getPassword(), confirmacion.getPassword()))
            JOptionPane.showMessageDialog(this, "La contrseña y su confirmación no coinciden", "Error en datos", JOptionPane.ERROR_MESSAGE);
        else {
            // Llamo a la función dentro de la Base de Datos que crea el Usuario. Retorna 1 si el usuario es creado o
            // -1 si el nombre de usuario ya existe.
            try {
                Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                ResultSet cUsuarios = sentencia.executeQuery("SELECT nuevoUsuario('"+nombre_usuario.getText()+"', '"+String.valueOf(contraseña.getPassword())+"', 3, "+VarId+", 0)");
                cUsuarios.next();
                if (cUsuarios.getInt(1) == 1) {
                    JOptionPane.showMessageDialog(this, "Se ha creado el usuario '"+nombre_usuario.getText()+"' exitosamente.", "Usuario creado", JOptionPane.INFORMATION_MESSAGE);
                    //borrar campos
                    Nombre.setText("");
                    Dpi.setText("");
                    nombre_usuario.setText("");
                    contraseña.setText("");
                    confirmacion.setText("");
                    cargar_catedraticos_sin_usuario();
                    crear_usuario.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "El usuario "+nombre_usuario.getText()+" ya existe.", "Datos repetidos", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CrearCat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_crear_usuarioActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Ventanita.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        // TODO add your handling code here:
        Ventanita.setEnabled(true);
        this.dispose();
        
    }//GEN-LAST:event_regresarActionPerformed

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed
    public void cargar_catedraticos_sin_usuario(){
        modelo.setRowCount(0);
        int cont = 1;
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            datosCat = sentencia.executeQuery("SELECT C.Id,C.Nombres,C.Apellidos,C.DPI FROM catedratico C LEFT JOIN usuarios U ON C.Id = U.Catedratico_Id WHERE U.Id is null;");
            while(datosCat.next()){
                modelo.addRow(new Object[]{cont,datosCat.getString(2),datosCat.getString(3),datosCat.getString(4)});
                listaIDCatedraticos.add(datosCat.getInt(1));
                cont++;
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    private boolean comparar_contraseñas(char[] contraseña, char[] confirmacion) {
        if (contraseña.length != confirmacion.length)
            return false;
        int cont = contraseña.length;
        for(int i=0; i<cont; i++) {
            if (contraseña[i] != confirmacion[i]) {
                return false;
            }
        }
        return true;
    }
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
            java.util.logging.Logger.getLogger(CrearCat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearCat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearCat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearCat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearCat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Datos;
    private javax.swing.JTextField Dpi;
    private javax.swing.JTextField Nombre;
    private javax.swing.JPasswordField confirmacion;
    private javax.swing.JPasswordField contraseña;
    private javax.swing.JButton crear_usuario;
    private javax.swing.JButton elegir_catedratico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre_usuario;
    private javax.swing.JButton regresar;
    // End of variables declaration//GEN-END:variables
}
