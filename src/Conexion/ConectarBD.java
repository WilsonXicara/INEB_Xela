/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class ConectarBD extends javax.swing.JDialog {
    Connection conexion;
    public static final String SEPARADOR = System.getProperty("file.separator");
    public static final String CARPETA_PRINCIPAL =  System.getProperty("user.home")+ SEPARADOR +"BD";
    public static final String archivo = CARPETA_PRINCIPAL+SEPARADOR+"datos";
    private final String nombreBD = "sbd_inebxela", usuarioBD = "inebxela", contraseñaBD = "inebxela_quetgo";
    private boolean hacerVisible;
    /**
     * Creates new form ConectarBD
     */
    public ConectarBD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.hacerVisible = true;
        
        this.setLocationRelativeTo(null);   // Para centrar esta ventana sobre la pantalla.
        intentar_conexion();
    }
    private void intentar_conexion() {
        File carpetaPrincipal = new File(CARPETA_PRINCIPAL);
        // Si la carpeta principal no existe se crea para guardar los datos de la base de datos
        if (!carpetaPrincipal.exists()) {
            carpetaPrincipal.mkdir();
            etiqueta_titulo.setText("No se encontró el archivo principal");
        } else {    // Si la carpeta existe, obtengo la dirección IP del servidor e intento crear la conexión
            try {
                System.out.println("Buscará el archivo");
                FileReader ruta = new FileReader(archivo);
                BufferedReader leer = new BufferedReader(ruta);
                String ipServidor = leer.readLine();
                System.out.println("ipServidor = "+ipServidor);
                leer.close();
                conectar(ipServidor);
                etiqueta_titulo.setText("Conexión establecida");
                ingresar_ip_servidor.setEnabled(false);
                buscar_ip_servidor.setEnabled(false);
                campo_direccion_ip1.setEnabled(false);
                campo_direccion_ip2.setEnabled(false);
                campo_direccion_ip3.setEnabled(false);
                campo_direccion_ip4.setEnabled(false);
                conectar_con_base_datos.setEnabled(false);
                // Si se logra establecer la conexión, se puede cerrar el JDialog
                this.setVisible(false);
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error al intentar obtener la Dirección IP del servidor\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al intentar obtener la Dirección IP del servidor\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "No se puede conectar con la Base de Datos\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel2 = new javax.swing.JPanel();
        etiqueta_titulo = new javax.swing.JLabel();
        ingresar_ip_servidor = new javax.swing.JRadioButton();
        buscar_ip_servidor = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        campo_direccion_ip3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        campo_direccion_ip4 = new javax.swing.JTextField();
        campo_direccion_ip1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campo_direccion_ip2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        conectar_con_base_datos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conexión con el Servidor");

        jPanel2.setBackground(new java.awt.Color(0, 204, 153));

        etiqueta_titulo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiqueta_titulo.setText("Conexión fallida. Intente lo siguiente:");

        ingresar_ip_servidor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ingresar_ip_servidor.setSelected(true);
        ingresar_ip_servidor.setText("Ingresar IP del servidor");
        ingresar_ip_servidor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ingresar_ip_servidorItemStateChanged(evt);
            }
        });

        buscar_ip_servidor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buscar_ip_servidor.setText("Buscar el Servidor");
        buscar_ip_servidor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buscar_ip_servidorItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Dirección IP:");

        campo_direccion_ip3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText(".");

        campo_direccion_ip4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        campo_direccion_ip1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText(".");

        campo_direccion_ip2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText(".");

        conectar_con_base_datos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        conectar_con_base_datos.setText("Conectar");
        conectar_con_base_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectar_con_base_datosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campo_direccion_ip1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campo_direccion_ip2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campo_direccion_ip3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campo_direccion_ip4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(etiqueta_titulo)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ingresar_ip_servidor)
                                .addGap(29, 29, 29)
                                .addComponent(buscar_ip_servidor))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(conectar_con_base_datos)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(etiqueta_titulo)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingresar_ip_servidor)
                    .addComponent(buscar_ip_servidor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campo_direccion_ip1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(campo_direccion_ip2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(campo_direccion_ip3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(campo_direccion_ip4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(conectar_con_base_datos)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresar_ip_servidorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ingresar_ip_servidorItemStateChanged
        buscar_ip_servidor.setSelected(!ingresar_ip_servidor.isSelected());
        boolean selected = ingresar_ip_servidor.isSelected();
        campo_direccion_ip1.setEnabled(selected);
        campo_direccion_ip2.setEnabled(selected);
        campo_direccion_ip3.setEnabled(selected);
        campo_direccion_ip4.setEnabled(selected);
    }//GEN-LAST:event_ingresar_ip_servidorItemStateChanged

    private void buscar_ip_servidorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buscar_ip_servidorItemStateChanged
        ingresar_ip_servidor.setSelected(!buscar_ip_servidor.isSelected());
    }//GEN-LAST:event_buscar_ip_servidorItemStateChanged

    private void conectar_con_base_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectar_con_base_datosActionPerformed
        // Selecciono la opción de Búsqueda del Servidor: Desde una IP ingresada o desde buscarlo por toda la red
        boolean buscarIPServidor = ingresar_ip_servidor.isSelected();
        if (buscarIPServidor) { // Se intentará la conexión desde la IP fija
            try {
                validar_direccion_ip();
                String ipServidor = campo_direccion_ip1.getText()+"."+campo_direccion_ip2.getText()+"."+campo_direccion_ip3.getText()+"."+campo_direccion_ip4.getText();
                conectar(ipServidor);
                JOptionPane.showMessageDialog(this, "Conexión Establecida", "Información", JOptionPane.INFORMATION_MESSAGE);
                // Si todo está bien, ya se ha creado la conexión y puedo guardar la Dirección IP del servidor
                FileWriter ruta = new FileWriter(archivo);
                BufferedWriter escritor = new BufferedWriter(ruta);
                escritor.write(ipServidor+"\n");
                escritor.close();
                
                this.dispose(); // Cierro el JDialog
                
            } catch (ExcepcionDatoIncorrecto ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "No se puede conectar con la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al intentar guardar la Dirección IP del Servidor", "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {    // Se intentará la conexión por prueba y error
            ArrayList<String> ipsAccesibles = obtener_IPs_accesibles();
            int cantidad = ipsAccesibles.size();
            System.out.println("IPs Accesibles = "+ipsAccesibles);
            int contador = 0;
            boolean repetir = true;
            while (repetir == true) {
                try {
                    if ((contador+1) == cantidad)
                        repetir = false;
                    System.out.print("\nEstablecer conexión con: "+ipsAccesibles.get(contador));
                    conectar(ipsAccesibles.get(contador));
                    JOptionPane.showMessageDialog(this, "Conexión Establecida", "Información", JOptionPane.INFORMATION_MESSAGE);
                    
                    FileWriter ruta = new FileWriter(archivo);
                    BufferedWriter escritor = new BufferedWriter(ruta);
                    escritor.write(ipsAccesibles.get(contador)+"\n");
                    escritor.close();
                    
                    // Si se logra la conexión, cierro el ciclo
                    repetir = false;
                    this.dispose();
                    System.out.print(" -> Servidor");
                } catch (ClassNotFoundException | SQLException ex) {
                    contador++;
                    System.out.print(" -> No se pudo conectar");
//                    Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al intentar guardar la dirección del servidor", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_conectar_con_base_datosActionPerformed
    private ArrayList<String> obtener_IPs_accesibles() {
        ArrayList<String> ipsAccesibles = new ArrayList<>();
        int tiempoEspera = 1000, contadorSinAccesibles = 0;
        String miIP = "";
        String[] ipRed = new String[]{"", "", ""};
        for (int contador=0; contador<255; contador++) {
            try {
                if ("".equals(miIP)) {
                    miIP = InetAddress.getLocalHost().getHostAddress();
                    int cont = 0;
                    for(int index=0; index<3; index++) {
                        String caracter = ""+miIP.charAt(cont);
                        while (!".".equals(caracter)) {
                            ipRed[index]+= caracter;
                            cont++;
                            caracter = ""+miIP.charAt(cont);
                        }
                        cont++;
                    }
                }
                String ipPrueba = ipRed[0]+"."+ipRed[1]+"."+ipRed[2]+"."+contador;
                if (InetAddress.getByName(ipPrueba).isReachable(tiempoEspera)){
                    System.out.println("La Dirección IP "+ipPrueba+" es accesible");
                    ipsAccesibles.add(ipPrueba);
                    contadorSinAccesibles = 0;
                } else {
                    contadorSinAccesibles++;
                }
                if (contadorSinAccesibles == 5)
                    contador = 255;
            } catch (UnknownHostException ex) {
//                Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
//                Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ipsAccesibles;
    }
    private void validar_direccion_ip() throws ExcepcionDatoIncorrecto {
        // Compruebo que los campos no estén vacios
        if ("".equals(campo_direccion_ip1.getText()) || "".equals(campo_direccion_ip2.getText()) || "".equals(campo_direccion_ip3.getText()) || "".equals(campo_direccion_ip4.getText()))
            throw new ExcepcionDatoIncorrecto("Compruebe que la Dirección IP no tenga campos nulos");
        // Compruebo que los valores de la IP sean sólo números
        int[] valor = new int[4];
        try {
            valor[0] = Integer.parseInt(campo_direccion_ip1.getText());
            valor[1] = Integer.parseInt(campo_direccion_ip2.getText());
            valor[2] = Integer.parseInt(campo_direccion_ip3.getText());
            valor[3] = Integer.parseInt(campo_direccion_ip4.getText());
        } catch(NumberFormatException ex) {
             throw new ExcepcionDatoIncorrecto("Compruebe que la Dirección IP contenga sólo dígitos");
        }
        // Compruebo que los valores de la IP estén entre 0 y 255 (incluido ambos)
        if ((valor[0] < 0 || valor[0] > 255) || (valor[1] < 0 || valor[1] > 255) || (valor[2] < 0 || valor[2] > 255) || (valor[3] < 0 || valor[3] > 255))
            throw new ExcepcionDatoIncorrecto("Compruebe que los valores de la Dirección IP estén entre 0 y 255");
        // Si llega hasta acá, la Dirección IP es correcta
    }
    private void conectar(String ipServidor) throws ClassNotFoundException, SQLException  {
        Class.forName("org.gjt.mm.mysql.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://"+ipServidor+"/"+nombreBD,usuarioBD,contraseñaBD);
        hacerVisible = false;
    }
    public Connection getConexion() {
        // Verifico si la conexión no se pudo realizar, y se creó la carpeta
        File carpetaPrincipal = new File(CARPETA_PRINCIPAL);
        if (carpetaPrincipal.exists()) {
            carpetaPrincipal.delete();
        }
        return this.conexion;
    }
    public boolean getHacerVisible() { return this.hacerVisible; }
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
            java.util.logging.Logger.getLogger(ConectarBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConectarBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConectarBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConectarBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConectarBD dialog = new ConectarBD(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    private class ExcepcionDatoIncorrecto extends Exception {

        public ExcepcionDatoIncorrecto(String message) {
            super(message);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton buscar_ip_servidor;
    private javax.swing.JTextField campo_direccion_ip1;
    private javax.swing.JTextField campo_direccion_ip2;
    private javax.swing.JTextField campo_direccion_ip3;
    private javax.swing.JTextField campo_direccion_ip4;
    private javax.swing.JButton conectar_con_base_datos;
    private javax.swing.JLabel etiqueta_titulo;
    private javax.swing.JRadioButton ingresar_ip_servidor;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
