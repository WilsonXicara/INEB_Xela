/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_incio_sesion;

import Modulo_Ciclo_Escolar.Ciclo_Escolar;
import Conexion.Conec_BD;
import Conexion.conexion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    Connection conexion;
    Ciclo_Escolar ciclo;
    public static final String SEPARADOR = System.getProperty("file.separator");
    public static final String CARPETA_PRINCIPAL =  System.getProperty("user.home")+ SEPARADOR +"BD";
    public static final String archivo = CARPETA_PRINCIPAL+SEPARADOR+"datos";
    
    
    public Principal() throws IOException {
        initComponents();
        
        File carpetaPrincipal = new File(CARPETA_PRINCIPAL);
        // Si la carpeta principal no existe se crea para guardar los datos de la base de datos
        if (!carpetaPrincipal.exists()) {
            System.out.println("No se encontró la carpeta principal: "+CARPETA_PRINCIPAL);
            carpetaPrincipal.mkdir();
            Conec_BD a = new Conec_BD();
            String nombre = a.getNombre();
            String usuario= a.getUsuario();
            String contraseña= a.getContraseña();
            FileWriter ruta = new FileWriter(archivo);
            BufferedWriter escritor = new BufferedWriter(ruta);
            escritor.write(nombre+"\n");
            escritor.write(usuario+"\n");
            escritor.write(contraseña+"\n");
            escritor.close();
            int respuesta = conectar(nombre, usuario, contraseña);
            if(respuesta == 0){
                  JOptionPane.showMessageDialog(this, "Se ha logrado conectar a la base de datos", "Correcto", JOptionPane.INFORMATION_MESSAGE, null);
                  ruta.close();
            }
            else{
                  JOptionPane.showMessageDialog(this, "La base de datos no existe", "Error", JOptionPane.ERROR_MESSAGE, null);
                  carpetaPrincipal = new File(CARPETA_PRINCIPAL);
                  File s = new File(archivo);
                  s.delete();
                  carpetaPrincipal.delete();
                  System.exit(0);
            }
        }
        else{
            FileReader ruta = new FileReader(archivo);
            BufferedReader leer = new BufferedReader(ruta);
            String nombre = leer.readLine();
            String usuario = leer.readLine();
            String contraseña = leer.readLine();
            leer.close();
            int respuesta = conectar(nombre, usuario, contraseña);
            if (respuesta == 1){
                 JOptionPane.showMessageDialog(this, "No se ha logrado conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE, null);
                   ruta.close();
                  System.exit(0);
            }
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

        saludo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        contraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        saludo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        saludo.setText("BIENVENIDO");

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        jButton1.setText("Entrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButton1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(usuario))
                                .addComponent(saludo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jButton1)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saludo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Statement a  = conexion.createStatement();
            String contraseña_entrada = this.contraseña.getText();
            String usuario_entrada = this.usuario.getText();
            String contraseña;
            String tipo;
            ResultSet b = a.executeQuery("SELECT* FROM usuarios WHERE NombreUsuario = '"+ usuario_entrada+"'");
            if(b.next()){
            contraseña = b.getString(3);
            tipo = b.getString(4);
            if(contraseña_entrada.equals(contraseña)){
                if(tipo.equals("3")){
                    
                }
                else{
                    
                }
                
            }
            else {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE, null);
                this.contraseña.setText("");
                this.usuario.setText("");
            }
            }
            else{
                  JOptionPane.showMessageDialog(this, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE, null);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyTyped
        char code = evt.getKeyChar(); 
        if(code == '\n'){
              try {
            Statement a  = conexion.createStatement();
            String contraseña_entrada = this.contraseña.getText();
            String usuario_entrada = this.usuario.getText();
            String contraseña;
            String tipo;
            ResultSet b = a.executeQuery("SELECT* FROM usuarios WHERE NombreUsuario = '"+ usuario_entrada+"'");
            if(b.next()){
            contraseña = b.getString(3);
            tipo = b.getString(4);
            if(contraseña_entrada.equals(contraseña)){
                ciclo = new Ciclo_Escolar(this,true,conexion);
                ciclo.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE, null);
                this.contraseña.setText("");
                this.usuario.setText("");
            }
            }
            else{
                  JOptionPane.showMessageDialog(this, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE, null);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButton1KeyTyped

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private int conectar(String nombre, String usuario,String contraseña){
        int respuesta =0;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/"+nombre,usuario,contraseña);
        } catch (Exception e) {  
            respuesta=1;
                
        }
        
        return respuesta;
    } 
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField contraseña;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel saludo;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
