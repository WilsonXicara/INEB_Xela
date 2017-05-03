/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPrestamos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author SERGIO
 */
public class ListadoPaquetes extends javax.swing.JFrame {

    /**
     * Creates new form ListadoPaquetes
     */
    DefaultTableModel modelo;
    Connection conexcion;
    ResultSet Packs = null;
    public ListadoPaquetes() {
        initComponents();
    }
    public ListadoPaquetes(Connection conec){
        initComponents();
        String prestado = "";
        conexcion = conec;
        modelo = (DefaultTableModel) Listado.getModel();
        try {
            
            //Se agregaran todos los valores al principio
            Statement sentencia = conexcion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            Packs = sentencia.executeQuery("SELECT P.Codigo,L.Codigo,L.Nombre,L.Estado, E.PaqueteLibro_Id FROM paquetelibro P INNER JOIN libro L ON P.Id = L.PaqueteLibro_Codigo LEFT JOIN prestamo E ON E.PaqueteLibro_Id = P.Id;");
            if(Packs.next() == false){
                //No hay paquetes
                modelo.addRow(new Object[]{"No existen paquetes creados"});
            }
            else{
                Packs.previous();
                while(Packs.next() != false){
                    System.out.println("El pack "+Packs.getString(5));
                    if(Packs.getString(5) == null){
                            prestado = "No Prestado";
                        }
                    else{
                            prestado = "Prestado";
                    }
                    modelo.addRow(new Object[]{Packs.getString(1),Packs.getString(2),Packs.getString(3),Packs.getString(4),prestado});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListadoPaquetes.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CodigoPaquete = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Listado = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Listados de Paquetes de Libros");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Filtrar por:");

        CodigoPaquete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Listado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Listado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Paquete", "Codigo Libro", "Nombre", "Estado", "Prestado"
            }
        ));
        jScrollPane1.setViewportView(Listado);

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1.setText("Todo");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton2.setText("Codigo de Paquete");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CodigoPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(CodigoPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        //Se agregará todo
        //modelo.addRow(rowData);
        ResultSet Packs3 = null;
        String prestado = "";
        int filas = Listado.getRowCount();
        for (int i = 0;filas>i; i++) {
            modelo.removeRow(0);
        }
        try {
            
            //Se agregaran todos los valores al principio
            if(Packs.next() == false){
                //No hay paquetes
                modelo.addRow(new Object[]{"No existen paquetes creados"});
            }
            else{
                Packs.previous();
                while(Packs.next() != false){
                    System.out.println("El pack "+Packs.getString(5));
                    if(Packs.getString(5) == null){
                            prestado = "No Prestado";
                        }
                        else{
                            prestado = "Prestado";
                        }
                    modelo.addRow(new Object[]{Packs.getString(1),Packs.getString(2),Packs.getString(3),Packs.getString(4),prestado});
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListadoPaquetes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        String Cod, prestado = "";
        int filas = Listado.getRowCount();
        ResultSet Packs2 = null;
        if(CodigoPaquete.getText().equals("")){
            JOptionPane.showMessageDialog(null, "¡El campo está vacio!");
        }
        else{
            Cod = CodigoPaquete.getText();
            Statement sentencia;
            try {
                sentencia = conexcion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                //revisar la consulta
                Packs2 = sentencia.executeQuery("SELECT P.Codigo,L.Codigo,L.Nombre,L.Estado, E.PaqueteLibro_Id FROM paquetelibro P INNER JOIN libro L ON P.Id = L.PaqueteLibro_Codigo LEFT JOIN prestamo E ON E.PaqueteLibro_Id = P.Id WHERE P.Codigo = '" + Cod + "';");
                if(Packs2.next() == false){
                //Todos los Cat tienen usuario
                JOptionPane.showMessageDialog(null, "¡El paquete no existe!");
                }
                else{
                    //eliminar las filas de las tablas
                    for (int i = 0;filas>i; i++) {
                        modelo.removeRow(0);
                    }
                    Packs2.previous();
                    while(Packs2.next() != false){
                        //revisar la comparacion
                        System.out.println("El pack "+Packs2.getString(5));
                        if(Packs2.getString(5) == null){
                            prestado = "No Prestado";
                        }
                        else{
                            prestado = "Prestado";
                        }
                        modelo.addRow(new Object[]{Packs2.getString(1),Packs2.getString(2),Packs2.getString(3),Packs2.getString(4),prestado});
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListadoPaquetes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Se mostrará todo lo que sea con ese codigo
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(ListadoPaquetes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoPaquetes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoPaquetes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoPaquetes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListadoPaquetes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CodigoPaquete;
    private javax.swing.JTable Listado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
