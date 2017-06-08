/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_Catedratico;

import Modulo_Administrador.ModuloPrincipalAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author oem y Wilson Xicará
 */
public class Mostrar_Datos extends javax.swing.JFrame {

    
    private Connection conexion;
    Statement stmt;
    ResultSet Regresa;
    JFrame Ventanita;
    String titulos[] = { "ID",
                        "Nombres",
                        "Apellidos",
                        "Direccion"/*,"Telefono"*/,
                        "DPI",
                        "Sexo",
                        "Etnia",
                       // "Municipio_Id",
                        "Nombre",
                        "Telefono"/*,"Municipio"*/};
    String fila[] = new String [9];
    DefaultTableModel modelo;
    public Mostrar_Datos() {
        initComponents();
        //-------------------------------------------------
    }
    
    
    public Mostrar_Datos(Connection conex,JFrame ventana){
        initComponents();
        this.setLocationRelativeTo(null);
        this.conexion  = conex;
        Campo_Id.setVisible(false);
        Llenartabla("");
        llenarcombobox();
        //Regresa = Devolver;
        Ventanita = ventana;
    }
/**
 * Función que llena la tabla con todos los datos de los docentes
 */
    public void llenarcombobox(){
        Campo_Municipio.removeAllItems();
        try {
            Statement Sentencia = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet municipios = Sentencia.executeQuery("SELECT Nombre FROM Municipio");
            while (municipios.next()) {
                Campo_Municipio.addItem(municipios.getString("Nombre"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar obtener los Municipios\n"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Mostrar_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    public void Llenartabla(String Condicion){
        String consulta = "";
        if(Condicion.equals("")){
                 consulta = "SELECT catedratico.*, municipio.Nombre, telefono.Telefono "
                    + "FROM catedratico LEFT OUTER JOIN municipio ON catedratico.Municipio_Id = municipio.Id" +
                     " LEFT OUTER JOIN telefono ON catedratico.Id = telefono.Catedratico_Id";
            }
            else{
                 consulta = "SELECT catedratico.*, municipio.Nombre, telefono.Telefono "
                    + "FROM catedratico LEFT OUTER JOIN municipio ON catedratico.Municipio_Id = municipio.Id" +
                     " LEFT OUTER JOIN telefono ON catedratico.Id = telefono.Catedratico_Id WHERE catedratico.apellidos = '"+Condicion+"'";
            }
            
        try{
            stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            modelo = new DefaultTableModel(null,titulos);
            String sql = "";
            int contador = 0;
            while(rs.next()) {
                contador++;
                modelo.addRow(new String[]{
                    ""+contador,
                    rs.getString("Nombres"),
                    rs.getString("Apellidos"),
                    rs.getString("Direccion"),
                    rs.getString("DPI"),
                    rs.getString("Sexo"),
                    rs.getString("Etnia"),
                    rs.getString("Nombre"),
                    rs.getString("Telefono")
                });
               }
            
            Tabla_Datos.setModel(modelo);
                TableColumn ci = Tabla_Datos.getColumn("ID");
                ci.setMaxWidth(42);
                TableColumn cn = Tabla_Datos.getColumn("Nombres");
                cn.setMaxWidth(400);
                TableColumn cd = Tabla_Datos.getColumn("Apellidos");
                cd.setMaxWidth(400);
                TableColumn ct = Tabla_Datos.getColumn("Direccion");
                ct.setMaxWidth(240);
              //  TableColumn cnick = Tabla.getColumn("Telefono");
              //  cnick.setMaxWidth(100);
                TableColumn cp = Tabla_Datos.getColumn("DPI");
                cp.setMaxWidth(85);
                TableColumn ctipo = Tabla_Datos.getColumn("Sexo");
                ctipo.setMaxWidth(50);
                TableColumn ca = Tabla_Datos.getColumn("Etnia");
                ca.setMaxWidth(85);
               // TableColumn cp2 = Tabla_Datos.getColumn("Municipio_Id");
               // cp2.setMaxWidth(85);
                TableColumn ctipo2 = Tabla_Datos.getColumn("Nombre");
                ctipo2.setMaxWidth(350);
                TableColumn ca2 = Tabla_Datos.getColumn("Telefono");
                ca2.setMaxWidth(75);
                

        }catch (SQLException ex) {
            Logger.getLogger(Mostrar_Datos.class.getName()).log(Level.SEVERE, null, ex);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Datos = new javax.swing.JTable();
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
        crear_catedrático = new javax.swing.JButton();
        modificar_catedratico = new javax.swing.JButton();
        Campo_Id = new javax.swing.JTextField();
        boton_regresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Campo_busqueda = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jMenu1.setText("Modificar");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenu1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabla_Datos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        Tabla_Datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tabla_Datos.setComponentPopupMenu(jPopupMenu1);
        Tabla_Datos.setFocusable(false);
        Tabla_Datos.getTableHeader().setReorderingAllowed(false);
        Tabla_Datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Tabla_DatosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_Datos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 660, 190));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Módulo catedrático");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 13, 680, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del cátedratico:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

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

        Campo_Telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Campo_TelefonoActionPerformed(evt);
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

        Campo_DPI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Campo_DPIKeyTyped(evt);
            }
        });

        Campo_Sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));

        Campo8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo8.setText("Municipio:");

        Campo_Municipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Almolonga", "Cabricán", "Cajolá", "Cantel", "Coatepeque", "Colomba Costa Cuca", "Concepción Chiquirichapa", "El Palmar", "Flores Costa Cuca", "Génova", "Huitán", "La Esperanza", "Olintepeque", "Palestina de Los Altos", "Quetzaltenango", "Salcajá", "San Juan Ostuncalco", "San Carlos Sija", "San Francisco La Unión", "San Martín Sacatepéquez", "San Mateo", "San Miguel Sigüilá", "Sibilia", "Zunil" }));

        Campo4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Campo4.setText("DPI:");

        crear_catedrático.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        crear_catedrático.setText("Crear");
        crear_catedrático.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_catedráticoActionPerformed(evt);
            }
        });

        modificar_catedratico.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        modificar_catedratico.setText("Modificar");
        modificar_catedratico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificar_catedraticoActionPerformed(evt);
            }
        });

        Campo_Id.setEditable(false);
        Campo_Id.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Campo1)
                    .addComponent(Campo)
                    .addComponent(Campo2)
                    .addComponent(Campo8)
                    .addComponent(Campo3)
                    .addComponent(Campo4)
                    .addComponent(Campo5)
                    .addComponent(Campo6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_DPI, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Campo_Etnia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crear_catedrático, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificar_catedratico, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(134, 134, 134))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Campo_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(crear_catedrático, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modificar_catedratico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Campo_Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo1))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Campo_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Campo_Municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Campo_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Campo_DPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Campo_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Campo_Etnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(Campo_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 46, 660, 300));

        boton_regresar.setText("Regresar");
        boton_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_regresarActionPerformed(evt);
            }
        });
        getContentPane().add(boton_regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 14, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscador:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Mostrar todos los datos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("Buscar por apellidos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Campo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(Campo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 356, 660, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Campo_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_TelefonoKeyTyped
        if (!Pattern.compile("\\d").matcher(String.valueOf(evt.getKeyChar())).matches() || Campo_Telefono.getText().length() == 8)
            evt.consume();
    }//GEN-LAST:event_Campo_TelefonoKeyTyped

    private void Campo_ApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_ApellidosKeyTyped
      //  char letra = evt.getKeyChar();
      //  if((letra<'a'||letra>'z')&&(letra<'A'||letra>'z')&&(letra<' ' || letra>' ')) evt.consume();
    }//GEN-LAST:event_Campo_ApellidosKeyTyped

    private void Campo_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_NombreKeyTyped
       // char letra = evt.getKeyChar();
       // if((letra<'a'||letra>'z')&&(letra<'A'||letra>'z')&&(letra<' ' || letra>' ')) evt.consume();
    }//GEN-LAST:event_Campo_NombreKeyTyped

    private void Campo_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo_NombreActionPerformed

    }//GEN-LAST:event_Campo_NombreActionPerformed

    private void crear_catedráticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_catedráticoActionPerformed
        // Boton que crea un nuevo registro para la tabla cátedratico.
        String cadena2,cadena3,cadena4,cadena5,cadena6,cadena7,cadena8,cadena9;
        cadena2 = Campo_Nombre.getText();
        cadena3 = Campo_Apellidos.getText();
        cadena4 = Campo_Direccion.getText();
        cadena5 = Campo_Telefono.getText();
        cadena6 = Campo_DPI.getText();
        cadena7 = (Campo_Sexo.getSelectedIndex() == 0) ? "M" : "F";
        cadena8 = Campo_Etnia.getText();
        cadena9 = Integer.toString(Campo_Municipio.getSelectedIndex()+1);
        try {
            if(Campo_Nombre.getText().equals("") || Campo_Apellidos.getText().equals("") || Campo_Direccion.getText().equals("") || Campo_DPI.getText().equals("") ||
               Campo_Etnia.getText().equals("") || Campo_Telefono.getText().equals("") ){
                JOptionPane.showMessageDialog(null,"Error: Uno de los campos se encuentran vacíos.");
            }else if (Campo_Telefono.getText().length() <= 7){
                JOptionPane.showMessageDialog(null,"Error: El campo telefono no posee 8 caracteres.");
            }else if (Campo_DPI.getText().length() <= 12){
                JOptionPane.showMessageDialog(null,"Error: El campo DPI no posee 13 caracteres.");
            }
            else{
            PreparedStatement pst = conexion.prepareStatement("INSERT INTO catedratico (Nombres,Apellidos,Direccion,DPI, Sexo, Etnia,Municipio_Id)"
                    + "VALUES('"+cadena2+"','"+cadena3+"','"+cadena4+"','"+cadena6+"','"+cadena7+"','"+cadena8+"','"+cadena9+ "')");
            
            pst.executeUpdate();
            
            ResultSet rs = null;
            String sql = "SELECT MAX(catedratico.Id) FROM catedratico";
            Statement stmt2 = conexion.createStatement();
                  rs = stmt2.executeQuery(sql);
                  int id2 =0;
                    while(rs.next()){
                    //System.out.println(rs.getInt(1));
                    id2 = rs.getInt(1);
                    }
                 stmt2.executeUpdate("INSERT INTO telefono (Telefono,Catedratico_Id) VALUES('"+cadena5+"','"+id2+"')");
                  
              //    System.out.println("Los valores han sido agregados a la base de datos ");
              Llenartabla("");
            JOptionPane.showMessageDialog(null,"Se ha creado un nuevo registro.");
            
            this.Campo_Id.setText("");
            this.Campo_Nombre.setText("");
            this.Campo_Apellidos.setText("");
            this.Campo_Direccion.setText("");
            this.Campo_Telefono.setText("");
            this.Campo_DPI.setText("");  
            this.Campo_Etnia.setText("");
            }
        } catch (Exception e) {
            //Logger.getLogger(Mostrar_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_crear_catedráticoActionPerformed

    private void boton_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_regresarActionPerformed
        // Botón para volver a la ventana anterior
            Ventanita.setEnabled(true);
            this.dispose();
    }//GEN-LAST:event_boton_regresarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Llenartabla(Campo_busqueda.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Llenartabla("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void modificar_catedraticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificar_catedraticoActionPerformed
        try {
            // TODO add your handling code here:
            PreparedStatement pst = conexion.prepareStatement("UPDATE catedratico SET Nombres = '"+Campo_Nombre.getText()+"', Apellidos = '"+Campo_Apellidos.getText()+
                    "', Apellidos = '"+Campo_Apellidos.getText()+"', Direccion = '"+Campo_Direccion.getText()+"' ,DPI = '"+Campo_DPI.getText()+"',"
                    + " Sexo = '"+Campo_Sexo.getSelectedItem()+"', Etnia = '"+Campo_Etnia.getText()+"',Municipio_id = '"+(Campo_Municipio.getSelectedIndex()+1)+"'WHERE Id = "+Campo_Id.getText());
            pst.executeUpdate();
            PreparedStatement pst2 = conexion.prepareStatement("UPDATE telefono SET telefono.Telefono = '"+Campo_Telefono.getText()+"' WHERE telefono.Catedratico_Id = "+Campo_Id.getText());
            pst2.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se ha modificado un nuevo registro.");
            
            Llenartabla("");
        } catch (SQLException ex) {
            Logger.getLogger(Mostrar_Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modificar_catedraticoActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        System.out.println("Aqui entro.");
        int fila = Tabla_Datos.getSelectedRow();
        if(fila >= 0){
            Campo_Id.setText(Tabla_Datos.getValueAt(fila, 0).toString());
            Campo_Nombre.setText(Tabla_Datos.getValueAt(fila, 1).toString());
            Campo_Apellidos.setText(Tabla_Datos.getValueAt(fila, 2).toString());
            Campo_Direccion.setText(Tabla_Datos.getValueAt(fila, 3).toString());
            Campo_DPI.setText(Tabla_Datos.getValueAt(fila, 4).toString());
          //  Campo_Sexo.setText(Tabla_Datos.getValueAt(fila, 5).toString());
            Campo_Etnia.setText(Tabla_Datos.getValueAt(fila, 6).toString());
            //Campo_Municipio.setText(Tabla_Datos.getValueAt(fila, 7).toString());
            Campo_Telefono.setText(Tabla_Datos.getValueAt(fila, 8).toString());
        }else{
            JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void Tabla_DatosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_DatosMousePressed
        // TODO add your handling code here:
        if(evt.getClickCount() > 1){
            int fila = Tabla_Datos.getSelectedRow();
            if(fila >= 0){
            Campo_Id.setText(Tabla_Datos.getValueAt(fila, 0).toString());
            Campo_Nombre.setText(Tabla_Datos.getValueAt(fila, 1).toString());
            Campo_Apellidos.setText(Tabla_Datos.getValueAt(fila, 2).toString());
            Campo_Direccion.setText(Tabla_Datos.getValueAt(fila, 3).toString());
            Campo_DPI.setText(Tabla_Datos.getValueAt(fila, 4).toString());
          //  Campo_Sexo.setText(Tabla_Datos.getValueAt(fila, 5).toString());
            Campo_Etnia.setText(Tabla_Datos.getValueAt(fila, 6).toString());
            //Campo_Municipio.setText(Tabla_Datos.getValueAt(fila, 7).toString());
            Campo_Telefono.setText(Tabla_Datos.getValueAt(fila, 8).toString());
            }else{
                JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna fila.");
            }
        }
    }//GEN-LAST:event_Tabla_DatosMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
            //ModuloPrincipalAdmin s = new ModuloPrincipalAdmin(conexion, Regresa); // Llama a la del Andrés
       
            Ventanita.setEnabled(true);
            this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void Campo_TelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo_TelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo_TelefonoActionPerformed

    private void Campo_DPIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_DPIKeyTyped
        // TODO add your handling code here:
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
        
        int limite  = 13;
        if (Campo_DPI.getText().length()== limite){
          evt.consume();
        }
    }//GEN-LAST:event_Campo_DPIKeyTyped

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
            java.util.logging.Logger.getLogger(Mostrar_Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mostrar_Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mostrar_Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mostrar_Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mostrar_Datos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JTextField Campo_Id;
    private javax.swing.JComboBox<String> Campo_Municipio;
    private javax.swing.JTextField Campo_Nombre;
    private javax.swing.JComboBox<String> Campo_Sexo;
    private javax.swing.JTextField Campo_Telefono;
    private javax.swing.JTextField Campo_busqueda;
    private javax.swing.JTable Tabla_Datos;
    private javax.swing.JButton boton_regresar;
    private javax.swing.JButton crear_catedrático;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificar_catedratico;
    // End of variables declaration//GEN-END:variables
}
