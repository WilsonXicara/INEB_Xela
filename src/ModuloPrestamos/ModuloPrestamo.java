/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPrestamos;

import ModuloAdministrador.ModuloPrincipalAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SERGIO
 */
public class ModuloPrestamo extends javax.swing.JFrame {

    /**
     * Creates new form ModuloPrestamo
     */
    Connection conexcion;
    DefaultTableModel modelo;
    ResultSet Packs = null;
    JFrame Ventanita;
    int vId = 0;
    public ModuloPrestamo() {
        initComponents();
    }
    
    public ModuloPrestamo(Connection conec,JFrame ventana){
        initComponents();
        modelo = (DefaultTableModel) Paquetes.getModel();
        conexcion = conec;
        Ventanita = ventana;
        //revisar eso y la funcion si sirven
        DatosPaquetes();
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
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NoFactura = new javax.swing.JTextField();
        CodigoPaquete = new javax.swing.JTextField();
        FechaPago = new javax.swing.JTextField();
        Monto = new javax.swing.JTextField();
        CodigoEstudiante = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Paquetes = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Prestamo de Libros");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 20, 1130, 22);
        getContentPane().add(jLabel9);
        jLabel9.setBounds(1220, 1022, 0, 0);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Prestamo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(550, 340, 115, 25);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(690, 340, 115, 25);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Listado de Prestamos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(470, 590, 202, 34);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Listado Paquetes");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(250, 590, 202, 34);
        getContentPane().add(jLabel13);
        jLabel13.setBounds(1208, 1022, 0, 0);
        getContentPane().add(jLabel11);
        jLabel11.setBounds(1220, 1029, 0, 0);
        getContentPane().add(jLabel14);
        jLabel14.setBounds(1199, 616, 0, 0);
        getContentPane().add(jLabel15);
        jLabel15.setBounds(1206, 227, 0, 0);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Regresar Menú Principal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(60, 590, 177, 34);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Prestamo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("No.Factura");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Codigo del Paquete");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Fecha de Pago");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Monto (Q)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Codigo Estudiante");

        NoFactura.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        CodigoPaquete.setEditable(false);
        CodigoPaquete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        FechaPago.setEditable(false);
        FechaPago.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Monto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        CodigoEstudiante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(92, 92, 92)
                        .addComponent(NoFactura))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel3))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FechaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                            .addComponent(CodigoPaquete)
                            .addComponent(Monto)
                            .addComponent(CodigoEstudiante))))
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(NoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(CodigoPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(FechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(Monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(CodigoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(525, 81, 570, 247);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(1213, 981, 0, 0);

        Paquetes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Paquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Paquete", "Codigo Paquete", "Descripcion"
            }
        ));
        jScrollPane1.setViewportView(Paquetes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 120, 452, 378);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Seleccionar Paquete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(520, 470, 155, 25);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Paquetes Disponibles");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 90, 127, 17);

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\SERGIO MALDONADO\\Documents\\GitHub\\INEB_Xela\\src\\Imagenes\\Libros.png")); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(790, 370, 309, 274);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        new ListadoPaquetes(conexcion).setVisible(true);                    //Despliega la ventana ListadoPaquetes
        this.setEnabled(true);
        //Cerrar o minimizar ventana ModuloPrestamos
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        new ListadoPrestamos(conexcion).setVisible(true);                   //Despliega la ventana ListadoPrestamos
        this.setEnabled(true);
        //Cerrar o minimizar ventana ModuloPrestamos
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        NoFactura.setText("");
        CodigoPaquete.setText("");
        CodigoEstudiante.setText("");
        Monto.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String NoFac, CodPack, CodEst, NomEst, ApeEst, Grad,Desc, Instruccion = "";
        float Efectivo;
        ResultSet resultado = null,resultado2 = null, packetes=null;
        if(vId == 0){
            JOptionPane.showMessageDialog(null, "¡No ha seleccionado ningun paquete!");
        }
        else if((NoFactura.getText().equals(""))||(Monto.getText().equals(""))||(CodigoEstudiante.getText().equals(""))){
            JOptionPane.showMessageDialog(null, "¡Hay Campos Vacios!");
        }
        else{
            NoFac = NoFactura.getText();
            CodPack = CodigoPaquete.getText();
            CodEst = CodigoEstudiante.getText();
            Efectivo = Float.parseFloat(Monto.getText());
            //Condicion que si el CodigoEstudiante no existe en la base arrojar un mensaje
            //Condicion si el CodigoPaquete no existe
            try {
                Statement sentencia = conexcion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                resultado = sentencia.executeQuery("SELECT * FROM prestamo WHERE CodigoBoleta = '" + NoFac + "';");
                Statement sentencia2 = conexcion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                resultado2 = sentencia2.executeQuery("SELECT * FROM estudiante WHERE CodigoPersonal = " + "'" + CodEst + "';");
                //Condicion que si el CodigoEstudiante no existe en la base arrojar un mensaje
                //Condicion si el No Factura ya existe
                if((false == resultado.next())||(resultado2.next() == false)){
                    JOptionPane.showMessageDialog(null, "Hay Datos incorrectos");
                }
                else{
                    //Concatenamos la instrucción para insertar a la tabla prestamo.
                    //System.out.println(resultado.next());
                    //System.out.println(resultado2.next());
                    Instruccion = "INSERT INTO prestamo(PaqueteLibro_ID,Estudiante_ID,CodigoBoleta,FechaPago,Monto) VALUES (" + resultado.getString(1) + "," + resultado2.getString(1) +  "," + "'" + NoFac + "'" + ",NOW()," + Efectivo + ");"; //Insercion a la Tabla Prestamos
                     //Insertamos en la base
                    try {
                        PreparedStatement  pst = conexcion.prepareStatement(Instruccion);
                        int a = pst.executeUpdate();
                        pst.close();
                        if (a>0){
                            int filas = Paquetes.getRowCount();
                            for (int i = 0;filas>i; i++) {
                                modelo.removeRow(0);
                            }
                            System.out.println("Guardado");
                            JOptionPane.showMessageDialog(null, "¡Se ha compleado el Prestamo del libro " + CodPack + " Exitosamente!");
                            DatosPaquetes(); //revisar la funcion
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ModuloPrestamo.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Error en SQLException: "+ex.getMessage());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
           
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Ventanita.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String vCodigo = "",vDescrip;
        if(Paquetes.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
        }
        else{
            vCodigo = Paquetes.getValueAt(Paquetes.getSelectedRow(), 1).toString();
            CodigoPaquete.setText(vCodigo);
            vId = Integer.parseInt(Paquetes.getValueAt(Paquetes.getSelectedRow(), 0).toString());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        Ventanita.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosed
    public void DatosPaquetes(){
        try {
            Statement sentencia = conexcion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //revisar la consulta
            Packs = sentencia.executeQuery("SELECT L.Id,L.Codigo,L.Descripcion FROM prestamo P RIGHT JOIN paquetelibro L ON P.PaqueteLibro_Id = L.Id WHERE P.PaqueteLibro_Id is null;");
            if(Packs.next() == false){
                //No hay paquetes disponibles
            }
            else{
                Packs.previous();
                while(Packs.next() != false){
                    modelo.addRow(new Object[]{Packs.getString(1),Packs.getString(2),Packs.getString(3)});
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(ModuloPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloPrestamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CodigoEstudiante;
    private javax.swing.JTextField CodigoPaquete;
    private javax.swing.JTextField FechaPago;
    private javax.swing.JTextField Monto;
    private javax.swing.JTextField NoFactura;
    private javax.swing.JTable Paquetes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
