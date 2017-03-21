package Catedratico;


import ModuloAdministrador.ModuloPrincipalAdmin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oem
 */
public class Datos_Catedraticos extends javax.swing.JFrame {

    Connection con = null;
    Statement stmt = null;
    String titulos[] = {"ID","Nombres","Apellidos","Direccion"/*,"Telefono"*/,"DPI","Sexo","Etnia"/*,"Municipio"*/};
    String fila[] = new String [8];
    DefaultTableModel modelo;

    public Datos_Catedraticos(Connection a){
        initComponents();
        con = a;
        try {
            
           /* String url = "jdbc:mysql://localhost:3306/sbd_inebxela";
            String usuario = "root";
            String contraseña = "6148";  
            
               Class.forName("com.mysql.jdbc.Driver").newInstance();// Crea la conexión
               con = DriverManager.getConnection(url,usuario,contraseña);//Obtiene la conexión
               if (con!= null)//Verifica si existe conexión
                   System.out.println("Se ha establecido una conexion a la base de datos"+"\n"+url);
               */
               stmt = con.createStatement(); 
               ResultSet rs = stmt.executeQuery("select* from catedratico");//...
               
               modelo = new DefaultTableModel(null,titulos);
            System.out.println("SAAAAAAAAAAAAAAAAAAAAA");
               while(rs.next()) {
                   
                   fila[0] = rs.getString("ID");
                   fila[1] = rs.getString("Nombres");
                   fila[2] = rs.getString("Apellidos");
                   fila[3] = rs.getString("Direccion");
                   //fila[4] = rs.getString("Telefono");
                   fila[4] = rs.getString("DPI");
                   fila[5] = rs.getString("Sexo");
                   fila[6] = rs.getString("Etnia");
                   
                   modelo.addRow(fila);     
               }
               Tabla.setModel(modelo);
                TableColumn ci = Tabla.getColumn("ID");
                ci.setMaxWidth(25);
                TableColumn cn = Tabla.getColumn("Nombres");
                cn.setMaxWidth(145);
                TableColumn cd = Tabla.getColumn("Apellidos");
                cd.setMaxWidth(140);
                TableColumn ct = Tabla.getColumn("Direccion");
                ct.setMaxWidth(240);
              //  TableColumn cnick = Tabla.getColumn("Telefono");
              //  cnick.setMaxWidth(100);
                TableColumn cp = Tabla.getColumn("DPI");
                cp.setMaxWidth(85);
                TableColumn ctipo = Tabla.getColumn("Sexo");
                ctipo.setMaxWidth(50);
                TableColumn ca = Tabla.getColumn("Etnia");
                ca.setMaxWidth(75);
        
    } catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipalAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Datos_Catedraticos() {
        initComponents();
        this.setTitle("Datos de catedráticos");
        this.setLocation(335,220);
        this.setResizable(false);// No permite que el usuario edite datos
    //    ImageIcon icono = new ImageIcon("aqui pon la ruta de la imagen que quieres poner"); // Cambia el logo de la esquina ;)
    //    this.setIconImage(icono.getImage());
    
        
        try {
            
           /* String url = "jdbc:mysql://localhost:3306/sbd_inebxela";
            String usuario = "root";
            String contraseña = "6148";  
            
               Class.forName("com.mysql.jdbc.Driver").newInstance();// Crea la conexión
               con = DriverManager.getConnection(url,usuario,contraseña);//Obtiene la conexión
               if (con!= null)//Verifica si existe conexión
                   System.out.println("Se ha establecido una conexion a la base de datos"+"\n"+url);
               */
               stmt = con.createStatement(); 
               ResultSet rs = stmt.executeQuery("select* from catedratico");//...
               
               modelo = new DefaultTableModel(null,titulos);
            System.out.println("SAAAAAAAAAAAAAAAAAAAAA");
               while(rs.next()) {
                   
                   fila[0] = rs.getString("ID");
                   fila[1] = rs.getString("Nombres");
                   fila[2] = rs.getString("Apellidos");
                   fila[3] = rs.getString("Direccion");
                   //fila[4] = rs.getString("Telefono");
                   fila[4] = rs.getString("DPI");
                   fila[5] = rs.getString("Sexo");
                   fila[6] = rs.getString("Etnia");
                   
                   modelo.addRow(fila);     
               }
               Tabla.setModel(modelo);
                TableColumn ci = Tabla.getColumn("ID");
                ci.setMaxWidth(25);
                TableColumn cn = Tabla.getColumn("Nombres");
                cn.setMaxWidth(145);
                TableColumn cd = Tabla.getColumn("Apellidos");
                cd.setMaxWidth(140);
                TableColumn ct = Tabla.getColumn("Direccion");
                ct.setMaxWidth(240);
              //  TableColumn cnick = Tabla.getColumn("Telefono");
              //  cnick.setMaxWidth(100);
                TableColumn cp = Tabla.getColumn("DPI");
                cp.setMaxWidth(85);
                TableColumn ctipo = Tabla.getColumn("Sexo");
                ctipo.setMaxWidth(50);
                TableColumn ca = Tabla.getColumn("Etnia");
                ca.setMaxWidth(75);
               
        }
        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
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
        Tabla = new javax.swing.JTable();
        Menu = new javax.swing.JMenuBar();
        Menu_Archivo = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 870, 330));

        Menu_Archivo.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Registro catedrático");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Menu_Archivo.add(jMenuItem1);

        Menu.add(Menu_Archivo);

        setJMenuBar(Menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        Catedraticos Ventana = new Catedraticos();
        Ventana.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Datos_Catedraticos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Datos_Catedraticos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Datos_Catedraticos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Datos_Catedraticos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Datos_Catedraticos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu Menu_Archivo;
    private javax.swing.JTable Tabla;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
