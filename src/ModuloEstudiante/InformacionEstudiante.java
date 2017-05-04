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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase permite buscar información de uno o varios estudiantes (mostrando los que coincidan con la búsqueda en la tabla).
 * Además, da la opción de Editar la Información de un estudiante, así como visualizar su asignación (y todo lo relacionado).
 * @author Wilson Xicará
 */
public class InformacionEstudiante extends javax.swing.JDialog {
    private Connection conexion;
    private DefaultTableModel modelEncontrados;
    private ArrayList<RegistroCiclo> listaCiclos;
    private boolean ciclosCargados;
    private ArrayList<RegistroEstudiante> listaEncontrados;
    /**
     * Creates new form InformacionEstudiante
     */
    public InformacionEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**
     * 
     * @param parent
     * @param modal
     * @param conexion 
     */
    public InformacionEstudiante(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        modelEncontrados = (DefaultTableModel)tabla_encontrados.getModel();
        listaCiclos = new ArrayList<>();
        ciclosCargados = false;  // Indicador de que todos los datos ya han sido obtenidos de la Base de Datos
        listaEncontrados = new ArrayList<>();
        
        /* Realizo una consulta a la Tabla CicloEscolar de la Base de Datos y los agrego al ArrayList correspondiente, para
           luego agregarlo al JComboBox. El Id de cada ciclo es correlativo a su posición en el ArrayList. */
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cCicloEscolar = sentencia.executeQuery("SELECT Anio FROM CicloEscolar");
            // Cargo al ArrayList y al JComboBox los Ciclos Escolares encontrados en la Base de Datos
            while(cCicloEscolar.next()) {
                listaCiclos.add(new RegistroCiclo(cCicloEscolar.getString("Anio")));    // Agrego un Ciclo Escolar encontrado
                ciclo_escolar.addItem(cCicloEscolar.getString("Anio"));
            } ciclosCargados = true;// Hasta aquí se garantiza la carga de todos los Grados y Ciclos Escolares de la Base de Datos
            
            ciclo_escolar.setSelectedIndex(-1); // Esta opción es para generar una llamada al itemStateChange en caso de sólo encontrar un ciclo
            ciclo_escolar.setSelectedIndex(ciclo_escolar.getItemCount() - 1);   // Selecciono por defecto el último Ciclo Esoclar
            
            /*/ Ahora obtengo los grados asociados a cada Ciclo Escolar cargado desde la Base de Datos
            int cantidad = listaCiclos.size();
            for(int cont=0; cont<cantidad; cont++) {
                // El ID de cada ciclo es correlativo a su posición en el ArrayList
                ResultSet cGrados = sentencia.executeQuery("SELECT AsignacionCAT.CicloEscolar_Id idCiclo, AsignacionCAT.Grado_Id idGrado, Grado.Nombre, Grado.Seccion, COUNT(AsignacionCAT.Grado_Id) grados FROM AsignacionCAT "
                        + "INNER JOIN Grado ON AsignacionCAT.Grado_Id = Grado.Id "
                        + "WHERE AsignacionCAT.CicloEscolar_Id = "+(cont+1)+" "
                        + "GROUP BY AsignacionCAT.Grado_Id");
                // Cargo al ArrayList todos los Grados del Ciclo Escolar seleccionado
                while (cGrados.next())
                    listaCiclos.get(cont).addGrado(new RegistroGrado(cGrados.getInt("idGrado"), cGrados.getString("Nombre"), cGrados.getString("Seccion")));
                
                // Ahora cargo el nombre de todos los Ciclos Escolares encontrados al JComboBox 'ciclo_escolar'
                ciclo_escolar.addItem(listaCiclos.get(cont).getAnio());
            } ciclosCargados = true;// Hasta aquí se garantiza la carga de todos los Grados y Ciclos Escolares de la Base de Datos
            ciclo_escolar.setSelectedIndex(ciclo_escolar.getItemCount() - 1);   // Selecciono por defecto el último Ciclo Esoclar
        */} catch (SQLException ex) {
            Logger.getLogger(InformacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al intentar obtener el registros de la Base de Datos:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        definir_ancho_columnas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_filtro_busqueda = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ciclo_escolar = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        filtro_busqueda = new javax.swing.JComboBox<>();
        campo_busqueda = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        grado = new javax.swing.JComboBox<>();
        panel_encontrados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_encontrados = new javax.swing.JTable();
        etiqueta_encontrados = new javax.swing.JLabel();
        panel_botones = new javax.swing.JPanel();
        editar = new javax.swing.JButton();
        ver_notas = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Información de los Estudiantes");

        panel_filtro_busqueda.setBackground(new java.awt.Color(153, 153, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ESPECIFICACIÓN DE BÚSQUEDA");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Ciclo Escolar:");

        ciclo_escolar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ciclo_escolar.setEnabled(false);
        ciclo_escolar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ciclo_escolarItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Buscar por:");

        filtro_busqueda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filtro_busqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin especificar", "Código Personal", "Apellidos", "Nombres" }));
        filtro_busqueda.setEnabled(false);
        filtro_busqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtro_busquedaItemStateChanged(evt);
            }
        });

        campo_busqueda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        campo_busqueda.setEnabled(false);

        buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar.setText("Nueva Búsqueda");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Grado:");

        grado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        grado.setEnabled(false);

        javax.swing.GroupLayout panel_filtro_busquedaLayout = new javax.swing.GroupLayout(panel_filtro_busqueda);
        panel_filtro_busqueda.setLayout(panel_filtro_busquedaLayout);
        panel_filtro_busquedaLayout.setHorizontalGroup(
            panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtro_busquedaLayout.createSequentialGroup()
                .addGroup(panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_filtro_busquedaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ciclo_escolar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grado, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtro_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar))
                    .addGroup(panel_filtro_busquedaLayout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(jLabel4)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        panel_filtro_busquedaLayout.setVerticalGroup(
            panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtro_busquedaLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(filtro_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buscar))
                    .addGroup(panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(grado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(ciclo_escolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        panel_encontrados.setBackground(new java.awt.Color(153, 153, 255));

        tabla_encontrados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_encontrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Código Personal", "CUI", "Nombres", "Apellidos", "Dirección", "Municipio", "Fecha Nacimiento", "Sexo", "Etnia", "Capacidad Diferente", "Tipo Capacidad", "Encargado", "Relación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_encontrados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_encontrados.setEnabled(false);
        tabla_encontrados.setRowHeight(25);
        tabla_encontrados.getTableHeader().setResizingAllowed(false);
        tabla_encontrados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla_encontrados);

        etiqueta_encontrados.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiqueta_encontrados.setText("ENCONTRADO(S):");

        javax.swing.GroupLayout panel_encontradosLayout = new javax.swing.GroupLayout(panel_encontrados);
        panel_encontrados.setLayout(panel_encontradosLayout);
        panel_encontradosLayout.setHorizontalGroup(
            panel_encontradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encontradosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_encontradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                    .addGroup(panel_encontradosLayout.createSequentialGroup()
                        .addComponent(etiqueta_encontrados)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_encontradosLayout.setVerticalGroup(
            panel_encontradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_encontradosLayout.createSequentialGroup()
                .addComponent(etiqueta_encontrados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_botones.setBackground(new java.awt.Color(153, 153, 255));

        editar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editar.setText("Editar Información");
        editar.setEnabled(false);
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        ver_notas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ver_notas.setText("Ver Notas");
        ver_notas.setEnabled(false);
        ver_notas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_notasActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("ACCIONES:");

        javax.swing.GroupLayout panel_botonesLayout = new javax.swing.GroupLayout(panel_botones);
        panel_botones.setLayout(panel_botonesLayout);
        panel_botonesLayout.setHorizontalGroup(
            panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botonesLayout.createSequentialGroup()
                .addGroup(panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_botonesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editar)
                            .addComponent(ver_notas)))
                    .addGroup(panel_botonesLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_botonesLayout.setVerticalGroup(
            panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botonesLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addComponent(editar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ver_notas)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_encontrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panel_filtro_busqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_filtro_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_encontrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(panel_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción que permite ver las notas asociadas al Estudiante seleccionado, en el Ciclo Escolar Seleccionado. Para ello, se
     * pasa como parámetros el RegistroEstudiante seleccionado y el ID del ciclo escolar.
     * @param evt 
     */
    private void ver_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_notasActionPerformed
        int[] rango = tabla_encontrados.getSelectedRows();
        if (rango.length == 0)
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else if (rango.length > 1)
            JOptionPane.showMessageDialog(this, "Por favor selecciona sólo un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else {
            // Instanciación de la ventana encargada de la Visualización de las Notas asociadas al Estudiante y al Ciclo Escolar
            this.setVisible(false);
            Notas ventanaNotas = new Notas(new javax.swing.JFrame(), true, conexion, listaEncontrados.get(rango[0]), ciclo_escolar.getSelectedIndex()+1);
            ventanaNotas.setVisible(true);
            this.setVisible(true);
        }
    }//GEN-LAST:event_ver_notasActionPerformed
    /**APROBADO!!!
     * Acción que permite realizar una búsqueda de los registros que coincidan con los valores del campo y los filtros de
     * búsqueda. En la busqueda, carga los registros en un ArrayList<RegistroEstudiante> listaEncontrados
     * Cada vez que se realiza una búsqueda, se eliminan los registros de la búsqueda anterior.
     * @param evt 
     */
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        if (buscar.getText().equals("Nueva Búsqueda")) {    // Se habilitan los campos para filtrar la búsqueda
            buscar.setText("Buscar");   // Indicador de que se filtrará la búsqueda y puede realizarse la misma
            setEnabled_campos_resultado(false);
        } else {    // Ya se han seleccionado el filtro de búsqueda
            buscar.setText("Nueva Búsqueda");   // Indicador de que se habilitará el filtro para una nueva búsqueda
            setEnabled_campos_resultado(true);
            /* Obtención del tipo de filtro para utilizar en la búsqueda */
            int filtro = filtro_busqueda.getSelectedIndex();
            int cicloSelec = ciclo_escolar.getSelectedIndex();  // Obtengo el index cel ciclo seleccionado
            // Obtengo el Id del Grado seleccionado (si la búsqueda es para todos los grados, este valor será cero)
            int gradoId = (grado.getSelectedIndex() == listaCiclos.get(cicloSelec).getGrados().size()) ?
                    0 : listaCiclos.get(cicloSelec).getGrado(grado.getSelectedIndex()).getID();
            String campoBusqueda = campo_busqueda.getText();

            modelEncontrados.setRowCount(0);    // Eliminación de todos los registros de la tabla_encontrados
            listaEncontrados.clear();   // Eliminación de todos los registros de listaEstudiantes
            
            extraer_y_mostrar_registros(cicloSelec+1, gradoId, filtro, campoBusqueda);
        }
    }//GEN-LAST:event_buscarActionPerformed
    /**APROBADO!!!
     * Acción que permite editar los datos de un RegistroEstudiante encontrado en la búsqueda. Al llamar a la ventana
     * EditarEstudiante le pasa como parámetro un RegistroEstudiante en el cual se basará los datos a editar.
     * @param evt 
     */
    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        int indexRegistro = tabla_encontrados.getSelectedRow();
        if (indexRegistro != -1) {
            // Instanciación de la ventana encargada de la Edición de los Datos
            EditarEstudiante ventanaEditar = new EditarEstudiante(new javax.swing.JFrame(), true, conexion, listaEncontrados.get(indexRegistro));
            ventanaEditar.setVisible(true);
        } else
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_editarActionPerformed
    /**APROBADO!!!
     * Acción que habilita o deshabilita 'campo_busqueda' dependiendo del filtro de búsqueda a utilizar. Los filtros indican
     * los campos en los que se basará la búsqueda. Dichos campos son:
     *  "Sin especificar"
     *  "Código Personal"
     *  "Apellidos"
     *  "Nombres"
     * @param evt 
     */
    private void filtro_busquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtro_busquedaItemStateChanged
        campo_busqueda.setEnabled(!(filtro_busqueda.getSelectedIndex() == 0));
        if (filtro_busqueda.getSelectedIndex()==0)
            campo_busqueda.setText("");
    }//GEN-LAST:event_filtro_busquedaItemStateChanged
    /**
     * Acción que permite mostrar en el JComboBox 'grado' los grados asignados al ciclo escolar seleccionado. Para evita hacer
     * varias peticiones a la Base de Datos, los grados se cargan temporalmente en una estructura interna (con la información
     * necesaria para identificarlas).
     * @param evt 
     */
    private void ciclo_escolarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ciclo_escolarItemStateChanged
        // Cada vez que se selecciona un nuevo ciclo escolar se deben actualizar 'grado' con los grados del ciclo seleccionado
        int indexCiclo = ciclo_escolar.getSelectedIndex();
        if (ciclosCargados && indexCiclo != -1) {
            grado.removeAllItems();
            ArrayList<RegistroGrado> listaGrados = listaCiclos.get(indexCiclo).getGrados();
            int cantidad = listaGrados.size();
            if (cantidad == 0) {    // Si el ArrayList está vacío, aún no se han cargado los grados de dicho ciclo
                // Ahora obtengo los Grados asociados a cada Ciclo Escolar seleccionado en el JComboBox correspondiente
                try {
                    Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    ResultSet cGrados = sentencia.executeQuery("SELECT AsignacionCAT.CicloEscolar_Id idCiclo, AsignacionCAT.Grado_Id idGrado, Grado.Nombre, Grado.Seccion, COUNT(AsignacionCAT.Grado_Id) grados FROM AsignacionCAT "
                            + "INNER JOIN Grado ON AsignacionCAT.Grado_Id = Grado.Id "
                            + "WHERE AsignacionCAT.CicloEscolar_Id = "+(indexCiclo+1)+" "
                            + "GROUP BY AsignacionCAT.Grado_Id");
                    // Cargo al ArrayList todos los Grados del Ciclo Escolar seleccionado. El ID de cada ciclo es correlativo a su posición en el ArrayList
                    while (cGrados.next())
                        listaGrados.add(new RegistroGrado(cGrados.getInt("idGrado"), cGrados.getString("Nombre"), cGrados.getString("Seccion")));
                    // Hasta aquí se garantiza la carga de todos los grados
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al intentar obtener los grados:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(PrincipalAsignacionEST.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // Ahora cargo los Grados encontrados al JComboBox
            cantidad = listaGrados.size();
            for(int i=0; i<cantidad; i++)
                grado.addItem(listaGrados.get(i).getGradoSeccion());
            grado.addItem("Todos los grados");  // Última opción que permitirá buscar a los estudiantes de todos los grados
            grado.setSelectedIndex((cantidad == 0) ? -1 : 0);
        }
    }//GEN-LAST:event_ciclo_escolarItemStateChanged
    /**APROBADO!!!
     * Método que busca los registros que coinciden con las especificaciones de la búsqueda en la Base de Datos. Al finalizar
     * la búsqueda, agrega los datos al ArrayList listaEstudiatnes y a la tabla_encontrados.
     * @param cicloEscolarId
     * @param gradoId
     * @param filtro
     * @param campoBusqueda 
     */
    private void extraer_y_mostrar_registros(int cicloEscolarId, int gradoId, int filtro, String campoBusqueda) {
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String instruccion = "SELECT AsignacionEST.CicloEscolar_Id, AsignacionEST.Grado_Id, Estudiante.*, Municipio.Nombre MunicipioEst, Encargado.Nombre NombreEncargado, Encargado.DPI FROM CicloEscolar "
                    + "INNER JOIN AsignacionEST ON CicloEscolar.Id = AsignacionEST.CicloEscolar_Id "
                    + "INNER JOIN Estudiante ON AsignacionEST.Estudiante_Id = Estudiante.Id "
                    + "INNER JOIN Municipio ON Estudiante.Municipio_Id = Municipio.Id "
                    + "INNER JOIN Encargado ON Estudiante.Encargado_Id = Encargado.Id "
                    + "WHERE CicloEscolar_Id = "+cicloEscolarId;
            if (gradoId != 0)   // No existe un grado con Id = 0, por lo que dicho valor es utilizado como marca
                instruccion+= " AND Grado_Id = "+gradoId;
            /* Realizo la búsqueda según el filtro, el Ciclo Escolar y el Grado especificados */
            switch (filtro) {
                case 1: // Búsqueda por Código Personal
                    instruccion+= " AND CodigoPersonal = '"+campoBusqueda + "'";
                break;
                case 2: // Búsqueda por Apellidos
                    instruccion+= " AND Apellidos = '"+campoBusqueda + "'";
                break;
                
                case 3: // Búsqueda por Nombres
                    instruccion+= " AND Nombres = '"+campoBusqueda + "'";
                break;
                default:    // Sin especificar (devuelve todos los datos del CicloEscolar especificado)
                break;
            }
            ResultSet encontrados = sentencia.executeQuery(instruccion);
            // Hasta aquí, 'encontrados' contiene los resultados de la búsqueda según las especificaciones
            
            /* Obtención de los registros de 'encontrados' y agregación al ArrayList listaEstudiantes */
            int contNum = 0;
            while(encontrados.next()) {
                contNum++;
                RegistroEstudiante nuevo = new RegistroEstudiante();
                nuevo.setNum(contNum);
                nuevo.setID(encontrados.getInt("Id"));
                nuevo.setCodigoPersonal(encontrados.getString("CodigoPersonal"));
                nuevo.setCUI(encontrados.getString("CUI"));
                nuevo.setNombres(encontrados.getString("Nombres"));
                nuevo.setApellidos(encontrados.getString("Apellidos"));
                nuevo.setFechaNacimiento(encontrados.getString("FechaNacimiento"));
                nuevo.setDireccion(encontrados.getString("Direccion"));
                nuevo.setSexo(encontrados.getString("Sexo"));
                nuevo.setEtnia(encontrados.getString("Etnia"));
                nuevo.setCapacidadDiferente(encontrados.getBoolean("CapacidadDiferente"));
                nuevo.setTipoCapacidad((nuevo.isCapacidadDiferente()) ? encontrados.getString("TipoCapacidad") : "");
                nuevo.setMunicipioId(encontrados.getInt("Municipio_Id") - 1);   // Para hacerlo corresponder con el ArrayList
                nuevo.setMunicipio(encontrados.getString("MunicipioEst"));
                nuevo.setEncargadoId(encontrados.getInt("Encargado_Id"));
                nuevo.setNombreEncargado(encontrados.getString("NombreEncargado")+" ("+encontrados.getString("DPI")+")");   // Concateno el DPI al nombre del Encargado
                nuevo.setRelacionEncargado(encontrados.getString("RelacionEncargado"));
                nuevo.setGuardadoEnBD(true);    // Indicador de que es un registro de la BD
                nuevo.setEncargadoEnBD(true);
                // Hasta aquí, se garantiza la obtención de los datos del estudiante que concuerda con la búsqueda.
                listaEncontrados.add(nuevo);
                
                // Ahora, cargo los datos a la tabla_encontrados
                modelEncontrados.addRow(nuevo.getDatosParaTabla());
            }   // Hasta aquí se garantiza la búsqueda y muestra de datos según las especificaciones
            
            /** Obtención de los Metadatos
            ResultSetMetaData columnas = consulta.getMetaData();
            int cantidadColumnas = columnas.getColumnCount();
            for(int i=1; i<=cantidadColumnas; i++)
                tabla.addColumn(columnas.getColumnLabel(i));**/
            String mensaje = "Se encontró "+contNum+" registro"+((contNum!=1)?"s":"")+" en "+((gradoId==0)?"todos los grados":"el grado "+(String)grado.getSelectedItem())+" del ciclo escolar "+ciclo_escolar.getItemAt(cicloEscolarId-1)+((filtro!=0)?" (Búsqueda por: "+(String)filtro_busqueda.getSelectedItem()+" = '"+campoBusqueda+"')":"");
            etiqueta_encontrados.setText(mensaje);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar extraer los datos especificados:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(InformacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Encontrados (valores definidos en base a pruebas)
        tabla_encontrados.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_encontrados.getColumnModel().getColumn(1).setPreferredWidth(110);
        tabla_encontrados.getColumnModel().getColumn(2).setPreferredWidth(135);
        tabla_encontrados.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabla_encontrados.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabla_encontrados.getColumnModel().getColumn(5).setPreferredWidth(175);
        tabla_encontrados.getColumnModel().getColumn(6).setPreferredWidth(180);
        tabla_encontrados.getColumnModel().getColumn(7).setPreferredWidth(120);
        tabla_encontrados.getColumnModel().getColumn(8).setPreferredWidth(80);
        tabla_encontrados.getColumnModel().getColumn(9).setPreferredWidth(80);
        tabla_encontrados.getColumnModel().getColumn(10).setPreferredWidth(125);
        tabla_encontrados.getColumnModel().getColumn(11).setPreferredWidth(230);
        tabla_encontrados.getColumnModel().getColumn(12).setPreferredWidth(330);
        tabla_encontrados.getColumnModel().getColumn(13).setPreferredWidth(115);
    }
    /**
     * Método que permite habilitar e inhabilitar campos dentro de la ventana. Esta opción es útil a la hora de hacer una
     * 'Búsqueda' o de seleccionar una 'Nueva Búsqueda'. Al realizar una búsqueda y obtener resultados es necesario que la
     * parte de filtro de búsqueda esté deshabilitado para evitar confusión.
     * @param valorEnabled valor que se le asigna al .setEnabled() de los componentes adecuados.
     */
    private void setEnabled_campos_resultado(boolean valorEnabled) {
        ciclo_escolar.setEnabled(!valorEnabled);
        grado.setEnabled(!valorEnabled);
        filtro_busqueda.setEnabled(!valorEnabled);
        if (!valorEnabled) filtro_busqueda.setSelectedIndex(0);
        
        tabla_encontrados.setEnabled(valorEnabled);
        editar.setEnabled(valorEnabled);
        ver_notas.setEnabled(valorEnabled);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
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
    private javax.swing.JLabel etiqueta_encontrados;
    private javax.swing.JComboBox<String> filtro_busqueda;
    private javax.swing.JComboBox<String> grado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel panel_encontrados;
    private javax.swing.JPanel panel_filtro_busqueda;
    private javax.swing.JTable tabla_encontrados;
    private javax.swing.JButton ver_notas;
    // End of variables declaration//GEN-END:variables
}
