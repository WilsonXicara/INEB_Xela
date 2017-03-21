/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilson Xicará
 */
public class InformacionEstudiante extends javax.swing.JDialog {
    private Connection conexion;
    private ResultSet consultaEstudiante, consultaCicloEscolar;
    private DefaultTableModel tablaModel;
    private int cicloEscolar_Id = 0;
    /**
     * Creates new form InformacionEstudiante
     */
    public InformacionEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public InformacionEstudiante(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        this.consultaEstudiante = null;
        cargarCicloEscolar();
        inicializarModel();
    }
    private void cargarCicloEscolar() {
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            // Obtengo el listado de los ciclos escolares y los inserto en el JComboBox correspondiente
            consultaCicloEscolar = sentencia.executeQuery("SELECT CicloEscolar.Id, CicloEscolar.Anio FROM CicloEscolar");
            ciclo_escolar.removeAllItems();
            while (consultaCicloEscolar.next()) {
                ciclo_escolar.addItem(consultaCicloEscolar.getString("Anio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
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

        ver_notas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_encontrados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        filtro_busqueda = new javax.swing.JComboBox<>();
        campo_busqueda = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        editar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ciclo_escolar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Información de los Estudiantes");

        ver_notas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ver_notas.setText("Ver Notas");
        ver_notas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_notasActionPerformed(evt);
            }
        });

        tabla_encontrados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_encontrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_encontrados);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por:");

        filtro_busqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        filtro_busqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin especificar", "Apellidos", "Código Personal", "CUI" }));
        filtro_busqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtro_busquedaItemStateChanged(evt);
            }
        });

        campo_busqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Encontrado:");

        editar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editar.setText("Editar Información");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Ciclo Escolar:");

        ciclo_escolar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ciclo_escolar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ciclo_escolarItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 913, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ciclo_escolar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filtro_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editar)
                            .addComponent(ver_notas))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(filtro_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(ciclo_escolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ver_notas)
                        .addGap(173, 173, 173))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ver_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_notasActionPerformed
        int registroSeleccionado = tabla_encontrados.getSelectedRow();
        if (registroSeleccionado != -1) {
            try {
                consultaEstudiante.first();
                for(int i=0; i<registroSeleccionado; i++)
                consultaEstudiante.next();
                Notas notas = new Notas(new JFrame(), true, conexion, consultaEstudiante);
                notas.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(InformacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ver_notasActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        inicializarModel();
        /* Obtención del tipo de filtro para utilizar en la búsqueda */
        int filtro = 0; // Sin especificar
        if ("Apellidos".equals(filtro_busqueda.getSelectedItem().toString()) == true) filtro = 1;   // Búsqueda por Apellidos
        else if ("Código Personal".equals(filtro_busqueda.getSelectedItem().toString()) == true) filtro = 2;    // Búsqueda por Código Personal
        else if ("CUI".equals(filtro_busqueda.getSelectedItem().toString()) == true) filtro = 3;    // Búsqueda por CUI
        else filtro = 0;    // Sin Especificar
        String campo = campo_busqueda.getText();
        extraerDatos(filtro, campo);
        tabla_encontrados.setModel(tablaModel);
        tabla_encontrados.setVisible(true);

        tabla_encontrados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla_encontrados.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_encontrados.getColumnModel().getColumn(1).setPreferredWidth(110);
        tabla_encontrados.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabla_encontrados.getColumnModel().getColumn(3).setPreferredWidth(200);
        tabla_encontrados.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla_encontrados.getColumnModel().getColumn(5).setPreferredWidth(120);
        tabla_encontrados.getColumnModel().getColumn(6).setPreferredWidth(40);
        tabla_encontrados.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla_encontrados.getColumnModel().getColumn(8).setPreferredWidth(130);
        tabla_encontrados.getColumnModel().getColumn(9).setPreferredWidth(300);
        tabla_encontrados.getColumnModel().getColumn(10).setPreferredWidth(200);
    }//GEN-LAST:event_buscarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        try {
            int registroSelccionado = tabla_encontrados.getSelectedRow();
            consultaEstudiante.first();
            if (registroSelccionado != -1) {
                // Obtengo el regitro seleccionado (desde la consulta)
                for(int i=0; i<registroSelccionado; i++)
                    consultaEstudiante.next();
                // Hago una consulta para obtener la información del Encargado
                Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                ResultSet consultaEncargado = sentencia.executeQuery("SELECT Encargado.*, Municipio.Nombre Municipio FROM Encargado "
                    + "INNER JOIN Municipio ON Encargado.Municipio_Id = Municipio.Id "
                    + " WHERE Encargado.Nombre = '"+consultaEstudiante.getString("Encargado")+"'");
                consultaEncargado.next();
                // Creación de la Ventana para Editar los datos del Estudiante
                EditarEstudiante editarEstudiante = new EditarEstudiante(new JFrame(), true, conexion, consultaEstudiante, consultaEncargado);
                editarEstudiante.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InformacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editarActionPerformed

    private void ciclo_escolarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ciclo_escolarItemStateChanged
        // Obtengo el Id del Ciclo Escolar seleccionado
        int itemSeleccionado = ciclo_escolar.getSelectedIndex();
        if (itemSeleccionado > -1) {
            try {
                consultaCicloEscolar.first();   // Regreso al primer registro de la consulta
                for(int i=1; i<=itemSeleccionado; i++) consultaCicloEscolar.next();
                cicloEscolar_Id = consultaCicloEscolar.getInt("Id");    // Obtengo el Id del Ciclo Escolar Seleccionado
            } catch (SQLException ex) {
                Logger.getLogger(InformacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ciclo_escolarItemStateChanged

    private void filtro_busquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtro_busquedaItemStateChanged
        if (filtro_busqueda.getSelectedIndex() == 0)
            campo_busqueda.setEnabled(false);
        else
            campo_busqueda.setEnabled(true);
    }//GEN-LAST:event_filtro_busquedaItemStateChanged
    /**
     * Método que inicializa el Model para la 'tabla_encontrados'. Inserta el nombre de los encabezados de las columnas.
     */
    private void inicializarModel() {
        tablaModel = new DefaultTableModel();
        // Definición de las columnas de 'tablaModel'
        tablaModel.addColumn("Id");
        tablaModel.addColumn("Código Personal");
        tablaModel.addColumn("CUI");
        tablaModel.addColumn("Nombre");
        tablaModel.addColumn("Dirección");
        tablaModel.addColumn("Fecha Nacimiento");
        tablaModel.addColumn("Sexo");
        tablaModel.addColumn("Etnia");
        tablaModel.addColumn("Capacidad Diferente");
        tablaModel.addColumn("Tipo Capacidad");
        tablaModel.addColumn("Encargado");
    }
    private void extraerDatos(int filtro, String campoBusqueda) {
        /* La búsqueda debe realizarse por Ciclo Escolar */
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            switch (filtro) {
                // 'filtro == 1': Búsqueda por Apellidos
                case 1:
                    consultaEstudiante = sentencia.executeQuery("SELECT AsignacionEST.CicloEscolar_Id, CicloEscolar.Anio, Estudiante.*, Municipio.Nombre Municipio, Encargado.Nombre Encargado FROM CicloEscolar "
                            + "INNER JOIN AsignacionEST ON CicloEscolar.Id = AsignacionEST.CicloEscolar_Id "
                            + "INNER JOIN Estudiante ON AsignacionEST.Estudiante_Id = Estudiante.Id "
                            + "INNER JOIN Municipio ON Estudiante.Municipio_Id = Municipio.Id "
                            + "INNER JOIN Encargado ON Estudiante.Encargado_Id = Encargado.Id "
                            + "WHERE Estudiante.Apellidos = '"+campoBusqueda+"' AND CicloEscolar_Id = "+cicloEscolar_Id+"");
                break;
                // 'filtro == 2': Búsqueda por Código Personal
                case 2:
                    consultaEstudiante = sentencia.executeQuery("SELECT AsignacionEST.CicloEscolar_Id, CicloEscolar.Anio, Estudiante.*, Municipio.Nombre Municipio, Encargado.Nombre Encargado FROM CicloEscolar "
                            + "INNER JOIN AsignacionEST ON CicloEscolar.Id = AsignacionEST.CicloEscolar_Id "
                            + "INNER JOIN Estudiante ON AsignacionEST.Estudiante_Id = Estudiante.Id "
                            + "INNER JOIN Municipio ON Estudiante.Municipio_Id = Municipio.Id "
                            + "INNER JOIN Encargado ON Estudiante.Encargado_Id = Encargado.Id "
                            + "WHERE Estudiante.CodigoPersonal = '"+campoBusqueda+"' AND CicloEscolar_Id = "+cicloEscolar_Id+"");
                break;
                // 'filtro == 3': Búsqueda por CUI
                case 3:
                    consultaEstudiante = sentencia.executeQuery("SELECT AsignacionEST.CicloEscolar_Id, CicloEscolar.Anio, Estudiante.*, Municipio.Nombre Municipio, Encargado.Nombre Encargado FROM CicloEscolar "
                            + "INNER JOIN AsignacionEST ON CicloEscolar.Id = AsignacionEST.CicloEscolar_Id "
                            + "INNER JOIN Estudiante ON AsignacionEST.Estudiante_Id = Estudiante.Id "
                            + "INNER JOIN Municipio ON Estudiante.Municipio_Id = Municipio.Id "
                            + "INNER JOIN Encargado ON Estudiante.Encargado_Id = Encargado.Id "
                            + "WHERE Estudiante.CUI = '"+campoBusqueda+"' AND CicloEscolar_Id = "+cicloEscolar_Id+"");
                break;
                // 'fitro == 0': Sin especificar (devuelve todo el contenido de la tabla de la BD)
                default:
                    consultaEstudiante = sentencia.executeQuery("SELECT AsignacionEST.CicloEscolar_Id, CicloEscolar.Anio, Estudiante.*, Municipio.Nombre Municipio, Encargado.Nombre Encargado FROM CicloEscolar "
                            + "INNER JOIN AsignacionEST ON CicloEscolar.Id = AsignacionEST.CicloEscolar_Id "
                            + "INNER JOIN Estudiante ON AsignacionEST.Estudiante_Id = Estudiante.Id "
                            + "INNER JOIN Municipio ON Estudiante.Municipio_Id = Municipio.Id "
                            + "INNER JOIN Encargado ON Estudiante.Encargado_Id = Encargado.Id "
                            + "WHERE CicloEscolar_Id = "+cicloEscolar_Id+"");
                    
                break;
            }   // Hasta aquí se han cargado los datos
            
            /** Obtención de los Metadatos
            ResultSetMetaData columnas = consulta.getMetaData();
            int cantidadColumnas = columnas.getColumnCount();
            for(int i=1; i<=cantidadColumnas; i++)
                tabla.addColumn(columnas.getColumnLabel(i));**/
            // Obtención e inserción de las filas en 'tabla'
            while (consultaEstudiante.next()) {
                String[] registro = new String[11];
                registro[0] = consultaEstudiante.getString("Id");
                registro[1] = consultaEstudiante.getString("CodigoPersonal");
                registro[2] = consultaEstudiante.getString("CUI");
                registro[3] = consultaEstudiante.getString("Nombres")+" "+ consultaEstudiante.getString("Apellidos");
                registro[4] = consultaEstudiante.getString("Direccion") + ", " + consultaEstudiante.getString("Municipio");
                registro[5] = consultaEstudiante.getString("FechaNacimiento");
                registro[6] = consultaEstudiante.getString("Sexo");
                registro[7] = consultaEstudiante.getString("Etnia");
                if (consultaEstudiante.getBoolean("CapacidadDiferente")) {
                    registro[8] = "Si";
                    registro[9] = consultaEstudiante.getString("TipoCapacidad");
                } else {
                    registro[8] = "No";
                    registro[9] = "<No existente>";
                }
                registro[10] = consultaEstudiante.getString("Encargado");
                tablaModel.addRow(registro); // Inserción del i-ésimo registro en la tabla
            }
//            consultaEstudiante.close();   // No cierro la consulta pues me servirá para modificar algún registro
        } catch (SQLException ex) {
            Logger.getLogger(InformacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(InformacionEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InformacionEstudiante dialog = new InformacionEstudiante(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JTextField campo_busqueda;
    private javax.swing.JComboBox<String> ciclo_escolar;
    private javax.swing.JButton editar;
    private javax.swing.JComboBox<String> filtro_busqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_encontrados;
    private javax.swing.JButton ver_notas;
    // End of variables declaration//GEN-END:variables
}
