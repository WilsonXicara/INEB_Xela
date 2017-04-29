package Catedratico;

/*Eliminar-------------------------------------*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    Connection con, conexion;
    Statement stmt;
    String titulos[] = {"ID","Nombres","Apellidos","Direccion"/*,"Telefono"*/,"DPI","Sexo","Etnia"/*,"Municipio"*/};
    String fila[] = new String [8];
    DefaultTableModel modelo;
    
    public Datos_Catedraticos(Connection v){
        initComponents();
        conexion = v;
          try {       
        //if (con!= null)//Verifica si existe conexión
                   System.out.println("Se ha establecido una conexion a la base de datos");
               
               stmt = conexion.createStatement(); 
               ResultSet rs = stmt.executeQuery("select* from catedratico");//...
               
               modelo = new DefaultTableModel(null,titulos);
           // System.out.println("SAAAAAAAAAAAAAAAAAAAAA");
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
        catch (SQLException ex) {
            Logger.getLogger(Datos_Catedraticos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Datos_Catedraticos() {
        initComponents();
        this.setTitle("Datos de catedráticos");
        this.setLocation(335,220);
        this.setResizable(false);// No permite que el usuario edite datos
    //    ImageIcon icono = new ImageIcon("aqui pon la ruta de la imagen que quieres poner"); // Cambia el logo de la esquina ;)
    //    this.setIconImage(icono.getImage());
    
        /*
        try {
            
            String url = "jdbc:mysql://localhost:3306/sbd_inebxela";
            String usuario = "root";
            String contraseña = "6148";  
            
               Class.forName("com.mysql.jdbc.Driver").newInstance();// Crea la conexión
               con = DriverManager.getConnection(url,usuario,contraseña);//Obtiene la conexión
               
               
        }
        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error al extraer los datos de la tabla");
        }
*/

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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Campo = new javax.swing.JLabel();
        Campo1 = new javax.swing.JLabel();
        Campo2 = new javax.swing.JLabel();
        Campo3 = new javax.swing.JLabel();
        Campo_Nombre = new javax.swing.JTextField();
        Campo_Apellidos = new javax.swing.JTextField();
        Campo_Direccion = new javax.swing.JTextField();
        Campo_Telefono = new javax.swing.JTextField();
        Campo5 = new javax.swing.JLabel();
        Campo6 = new javax.swing.JLabel();
        Campo_DPI = new javax.swing.JTextField();
        Campo_Sexo = new javax.swing.JComboBox<>();
        Campo8 = new javax.swing.JLabel();
        Campo_Municipio = new javax.swing.JComboBox<>();
        Campo_Etnia = new javax.swing.JTextField();
        Campo4 = new javax.swing.JLabel();
        Boton_Crear_Cat = new javax.swing.JButton();
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 770, 100));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Módulo cátedratico");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del cátedratico"));

        Campo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo.setText("Nombres:");

        Campo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo1.setText("Apellidos:");

        Campo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo2.setText("Dirección:");

        Campo3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo3.setText("Teléfono:");

        Campo_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Campo_NombreActionPerformed(evt);
            }
        });
        Campo_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Campo_NombreKeyTyped(evt);
            }
        });

        Campo_Apellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Campo_ApellidosKeyTyped(evt);
            }
        });

        Campo_Telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Campo_TelefonoKeyTyped(evt);
            }
        });

        Campo5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo5.setText("Sexo:");

        Campo6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo6.setText("Etnia:");

        Campo_Sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F", "M" }));

        Campo8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo8.setText("Municipio:");

        Campo_Municipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Almolonga", "Cabricán", "Cajolá", "Cantel", "Coatepeque", "Colomba Costa Cuca", "Concepción Chiquirichapa", "El Palmar", "Flores Costa Cuca", "Génova", "Huitán", "La Esperanza", "Olintepeque", "Palestina de Los Altos", "Quetzaltenango", "Salcajá", "San Juan Ostuncalco", "San Carlos Sija", "San Francisco La Unión", "San Martín Sacatepéquez", "San Mateo", "San Miguel Sigüilá", "Sibilia", "Zunil" }));

        Campo4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo4.setText("DPI:");

        Boton_Crear_Cat.setText("Crear");
        Boton_Crear_Cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_Crear_CatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Campo)
                        .addGap(14, 14, 14)
                        .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Campo1)
                        .addGap(14, 14, 14)
                        .addComponent(Campo_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Campo2)
                        .addGap(13, 13, 13)
                        .addComponent(Campo_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Campo3)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Campo_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Boton_Crear_Cat, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Campo4)
                        .addGap(15, 15, 15)
                        .addComponent(Campo_DPI, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Campo5)
                        .addGap(16, 16, 16)
                        .addComponent(Campo_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(Campo6))
                            .addComponent(Campo8))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo_Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo_Etnia, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo)
                            .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo1)
                            .addComponent(Campo_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo4)
                            .addComponent(Campo_DPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo5)
                            .addComponent(Campo_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo6)
                            .addComponent(Campo_Etnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Campo8)
                            .addComponent(Campo_Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(Boton_Crear_Cat, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

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
        Catedraticos Ventana = new Catedraticos(con, this);
        Ventana.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void Campo_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo_NombreActionPerformed

    }//GEN-LAST:event_Campo_NombreActionPerformed

    private void Campo_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_NombreKeyTyped
        char letra = evt.getKeyChar();
        if((letra<'a'||letra>'z')&&(letra<'A'||letra>'z')&&(letra<' ' || letra>' ')) evt.consume();
    }//GEN-LAST:event_Campo_NombreKeyTyped

    private void Campo_ApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_ApellidosKeyTyped
        char letra = evt.getKeyChar();
        if((letra<'a'||letra>'z')&&(letra<'A'||letra>'z')&&(letra<' ' || letra>' ')) evt.consume();
    }//GEN-LAST:event_Campo_ApellidosKeyTyped

    private void Campo_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_TelefonoKeyTyped
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
    }//GEN-LAST:event_Campo_TelefonoKeyTyped

    private void Boton_Crear_CatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_Crear_CatActionPerformed
        // Boton que crea un nuevo registro para la tabla cátedratico.
        String cadena2,cadena3,cadena4,cadena5,cadena6,cadena7,cadena8,cadena9;
        cadena2 = Campo_Nombre.getText();
        cadena3 = Campo_Apellidos.getText();
        cadena4 = Campo_Direccion.getText();
        cadena5 = Campo_Telefono.getText();
        cadena6 = Campo_DPI.getText();
        cadena7 = Campo_Sexo.getSelectedItem().toString();
        cadena8 = Campo_Etnia.getText().toString();
        cadena9 = Integer.toString(Campo_Municipio.getSelectedIndex()+1);
        try {
            PreparedStatement pst = conexion.prepareStatement("INSERT INTO catedratico (Nombres,Apellidos,Direccion,DPI, Sexo, Etnia,Municipio_Id"
                + "VALUES('"+cadena2+"','"+cadena3+"','"+cadena4+"','"+cadena6+"','"+cadena7+"','"+cadena8+"','"+cadena9+ "')");
            pst.executeUpdate();
        } catch (Exception e) {
            //Logger.getLogger(Mostrar_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Boton_Crear_CatActionPerformed

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
    private javax.swing.JButton Boton_Crear_Cat;
    private javax.swing.JLabel Campo;
    private javax.swing.JLabel Campo1;
    private javax.swing.JLabel Campo2;
    private javax.swing.JLabel Campo3;
    private javax.swing.JLabel Campo4;
    private javax.swing.JLabel Campo5;
    private javax.swing.JLabel Campo6;
    private javax.swing.JLabel Campo8;
    private javax.swing.JTextField Campo_Apellidos;
    private javax.swing.JTextField Campo_DPI;
    private javax.swing.JTextField Campo_Direccion;
    private javax.swing.JTextField Campo_Etnia;
    private javax.swing.JComboBox<String> Campo_Municipio;
    private javax.swing.JTextField Campo_Nombre;
    private javax.swing.JComboBox<String> Campo_Sexo;
    private javax.swing.JTextField Campo_Telefono;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu Menu_Archivo;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
