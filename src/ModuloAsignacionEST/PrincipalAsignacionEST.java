/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloAsignacionEST;

import ModuloEstudiante.RegistroCiclo;
import ModuloEstudiante.RegistroGrado;
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
 * Clase que permite crear VARIAS ASIGNACIONES O REASIGNACIONES PARA VARIOS ESTUDIANTES.
 * Las ASIGNACIONES consisten en buscar a todos los estudiantes que no tienen ninguna asignación (cuando un estudiante ha sido
 * agregado recientemente a la Base de Datos) y los muestra en la Tabla para poder crearles una Asignación por primera vez.
 * Las REASIGNACIONES consisten en seleccionar un Ciclo Escolar anterior y cargar los datos de todos los estudiantes que tienen
 * una Asignación en dicho ciclo. Esto es útil cuando un estudiante aumenta de Grado.
 * @author Wilson Xicará
 */
public class PrincipalAsignacionEST extends javax.swing.JDialog {
    private Connection conexion;
    private DefaultTableModel modelEstudiantes;
    private ArrayList<RegistroAsignacionEST> listaEstudiantes;
    private ArrayList<RegistroCiclo> listaCiclos;
    private boolean paraReasignacion, ciclosCargados, hacerVisible;
    
    /**
     * Creates new form AsignacionEST
     * @param parent
     * @param modal
     */
    public PrincipalAsignacionEST(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**
     * Constructor que inicializa la ventana para ASIGNACIONES o REASIGNACIONES. Si es para Asignaciones, muestra a todos los
     * estudiantes a los que se asignará por primera vez (cuando son inscritos recientemente). Si es para Reasignaciones,
     * muestra los estudiantes asignados a un Ciclo escolar previo a los cuales se les crará su reasignación.
     * @param parent
     * @param modal
     * @param conexion
     * @param paraReasignacion booleano que indica si la ventana se llama para Asignaciones o Reasignaciones.
     */
    public PrincipalAsignacionEST(java.awt.Frame parent, boolean modal, Connection conexion, boolean paraReasignacion) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        modelEstudiantes = (DefaultTableModel)tabla_estudiantes.getModel();
        this.listaEstudiantes = new ArrayList<>();
        this.listaCiclos = new ArrayList<>();
        this.paraReasignacion = paraReasignacion;
        this.ciclosCargados = false;  // Indicador de que todos los datos ya han sido obtenidos de la Base de Datos
        this.hacerVisible = true;
        this.setTitle(((paraReasignacion)?"Rea":"A")+"signación de Estudiantes");
        this.crear_asignacion.setText("Crear "+((paraReasignacion)?"Rea":"A")+"signación");
        
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cCicloEscolar;
            if (paraReasignacion) { // Se seleccionará un ciclo escolar anterior del cual se basará la reasignación
                // Realizo una consulta a la Tabla CicloEscolar de la BD y los agrego al JComboBox correspondiente
                cCicloEscolar = sentencia.executeQuery("SELECT Id, Anio, Listo, Cerrado FROM CicloEscolar");
                // Cargo al ArrayList los Ciclos Escolares encontrados en la Base de Datos
                while(cCicloEscolar.next()) {
                    listaCiclos.add(new RegistroCiclo(cCicloEscolar.getInt("Id"), cCicloEscolar.getString("Anio"), cCicloEscolar.getBoolean("Listo"), cCicloEscolar.getBoolean("Cerrado")));
                    ciclo_escolar.addItem(cCicloEscolar.getString("Anio")); // Agrego el Ciclo Escolar al JComboBox
                } ciclosCargados = true;// Hasta aquí se garantiza la carga de todos los Grados y Ciclos Escolares de la Base de Datos
                
                // En caso de no existir por lo menos un ciclo escolar no se mostrará la ventana
                if (listaCiclos.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se ha creado ningún Ciclo Escolar para Reasignar Estudiantes.\n\nConsulte con el Administrador para más información.", "Sin datos", JOptionPane.INFORMATION_MESSAGE);
                    hacerVisible = false;
                } else {
                    ciclo_escolar.setSelectedIndex(-1); // Esta opción es para generar una llamada al itemStateChange en caso de sólo encontrar un ciclo
                    ciclo_escolar.setSelectedIndex(ciclo_escolar.getItemCount() - 1);   // Selecciono por defecto el último Ciclo Esoclar
                    buscar_para_reasignacion.setEnabled(true);  // Habilito el botón para realizar búsquedas
                }
            } else {    // Se mostrarán los estudiantes que no tienen ninguna asignación
                // Primero verifico que exista por lo menos un Ciclo Escolar donde se pueda Asignar a los estudiantes
                cCicloEscolar = sentencia.executeQuery("SELECT COUNT(Id) FROM CicloEscolar");
                cCicloEscolar.next();
                if (cCicloEscolar.getInt(1) == 0) {
                    JOptionPane.showMessageDialog(this, "No se ha creado ningún Ciclo Escolar para Asignar Estudiantes.\n\nConsulte con el Administrador para más información.", "Sin datos", JOptionPane.INFORMATION_MESSAGE);
                    hacerVisible = false;
                } else {
                    cargar_estudiantes_no_asignados();  // Cargo los datos de todos los Estudiantes que no tienen ninguna Asingación
                }
            }
            definir_ancho_columnas();
            this.setLocationRelativeTo(null);   // Para centrar esta ventana sobre la pantalla.
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar obtener datos.\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            hacerVisible = false;   // Si ocurre un erro, no se mostrará la ventana
//            Logger.getLogger(PrincipalAsignacionEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargar_estudiantes_no_asignados() throws SQLException {
        // Obtengo la consulta de todos los Estudiantes que aún no tienen una Asignación
        Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet cAsignaciones = sentencia.executeQuery("SELECT Estudiante.Id idEST, Estudiante.CodigoPersonal, Estudiante.CUI, Estudiante.Nombres, Estudiante.Apellidos, Estudiante.Sexo, AsignacionEST.Id idASIG FROM Estudiante "
                + "LEFT JOIN AsignacionEST ON Estudiante.Id = AsignacionEST.Estudiante_Id "
                + "WHERE AsignacionEst.Id IS NULL");
        int contador = 0;
        // Obtención de datos, agregación al ArrayList y a la Tabla correspondiente
        while (cAsignaciones.next()) {
            contador++;
            // Obtengo los datos de la consulta y los agrego al ArrayList correspondiente
            RegistroAsignacionEST nuevo = new RegistroAsignacionEST();
            nuevo.setID(cAsignaciones.getInt("idEST"));
            nuevo.setCodigoPersonal(cAsignaciones.getString("CodigoPersonal"));
            nuevo.setCUI(cAsignaciones.getString("CUI"));
            nuevo.setNombres(cAsignaciones.getString("Nombres"));
            nuevo.setApellidos(cAsignaciones.getString("Apellidos"));
            nuevo.setSexo(cAsignaciones.getString("Sexo"));
            // Por defecto, el estudiante tiene asignación anterior y nueva como false

            listaEstudiantes.add(nuevo);    // Agregación del nuevo registro al ArrayList
            modelEstudiantes.addRow(nuevo.getDatosParaTabla(contador)); // Inserto los datos del nuevo registro en la Tabla
        }   // Hasta aquí se garantiza la extracción de todos los registros de estudiantes que aún no tienen una asignación
        cAsignaciones.close();
        hacerVisible = contador > 0;
        if (!hacerVisible) {    // Si no hay estudiantes por asignar, se cerrará automáticamente el JDialog
            JOptionPane.showMessageDialog(this, "Aviso.\n\nNo hay estudiantes para Asignar", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            panel_estudiantes_encontrados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hay "+contador+" estudiante"+((contador==1)?"":"s")+" para Asignar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14)));
            crear_asignacion.setEnabled(hacerVisible);  // Habilito el botón en Asignaciones nuevas si hay como mínimo un estudiante
        }
    }
    private void cargar_estudiantes_para_reasignacion() {
        modelEstudiantes.setRowCount(0);    // Borro los registros de la Búsqueda Anterior
        // Obtengo la consulta de todos los Estudiantes que tienen una Asignación en el Cilo y Grado seleccionados
        int cicloSelec = ciclo_escolar.getSelectedIndex(), gradoSelec = (grado.getSelectedIndex() == (grado.getItemCount()-1))?-1:grado.getSelectedIndex();
        try {
            String instruccion = "SELECT Estudiante.Id idEST, Estudiante.CodigoPersonal, Estudiante.CUI, Estudiante.Nombres, Estudiante.Apellidos, Estudiante.Sexo, AsignacionEST.Id idASIG, AsignacionEST.CicloEscolar_Id, CicloEscolar.Anio, AsignacionEST.Grado_Id, Grado.Nombre Grado, Grado.Seccion FROM Estudiante "
                    + "INNER JOIN AsignacionEST ON Estudiante.Id = AsignacionEST.Estudiante_Id "
                    + "INNER JOIN Grado ON AsignacionEST.Grado_Id = Grado.Id "
                    + "INNER JOIN CicloEscolar ON AsignacionEST.CicloEscolar_Id = CicloEscolar.Id "
                    + "WHERE AsignacionEst.CicloEscolar_Id = "+listaCiclos.get(cicloSelec).getID()+"";
            if (gradoSelec != -1)  // Si se quiere mostrar los alumnos de un Ciclo y Grado específico
                instruccion+= " AND Grado.Id = "+listaCiclos.get(cicloSelec).getGrado(gradoSelec).getID()+"";
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cAsignaciones = sentencia.executeQuery(instruccion);
            int contador = 0;
            // Obtención de datos, agregación al ArrayList e impresión en la Tabla correspondiente
            while (cAsignaciones.next()) {
                contador++;
                // Obtengo los datos de la consulta y los agrego al ArrayList correspondiente
                RegistroAsignacionEST nuevo = new RegistroAsignacionEST();
                nuevo.setID(cAsignaciones.getInt("idEST"));
                nuevo.setCodigoPersonal(cAsignaciones.getString("CodigoPersonal"));
                nuevo.setCUI(cAsignaciones.getString("CUI"));
                nuevo.setNombres(cAsignaciones.getString("Nombres"));
                nuevo.setApellidos(cAsignaciones.getString("Apellidos"));
                nuevo.setSexo(cAsignaciones.getString("Sexo"));
                nuevo.setAsignacionAnterior(true);  // Por defecto, su asignación nueva es false
                nuevo.setCicloEscolarId(cAsignaciones.getInt("CicloEscolar_Id"));
                nuevo.setAnio(cAsignaciones.getString("Anio"));
                nuevo.setGradoId(cAsignaciones.getInt("Grado_Id"));
                nuevo.setGrado(cAsignaciones.getString("Grado"));
                nuevo.setSeccion(cAsignaciones.getString("Seccion"));
                
                listaEstudiantes.add(nuevo);    // Agregación del nuevo registro al ArrayList
                modelEstudiantes.addRow(nuevo.getDatosParaTabla(contador)); // Inserto los datos del nuevo registro en la Tabla
            }   // Hasta aquí se garantiza la extracción de todos los registros de estudiantes que aún no tienen una asignación
            cAsignaciones.close();
            panel_estudiantes_encontrados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hay "+contador+" estudiante"+((contador==1)?"":"s")+" para Reasignar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14)));
            crear_asignacion.setEnabled(contador > 0);  // Habilito el botón para Crear las Reasignaciones si hay como minimo un registro
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar extraer datos.\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(PrincipalAsignacionEST.class.getName()).log(Level.SEVERE, null, ex);
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

        panel_reasignacion = new javax.swing.JPanel();
        etiqueta_seleccion_ciclo_escolar = new javax.swing.JLabel();
        ciclo_escolar = new javax.swing.JComboBox<>();
        buscar_para_reasignacion = new javax.swing.JButton();
        etiqueta_ciclo_escolar = new javax.swing.JLabel();
        etiqueta_grado = new javax.swing.JLabel();
        grado = new javax.swing.JComboBox<>();
        panel_estudiantes_encontrados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_estudiantes = new javax.swing.JTable();
        etiqueta_aviso = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        crear_asignacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asignación de Estudiantes");
        setLocation(new java.awt.Point(100, 10));

        panel_reasignacion.setBackground(new java.awt.Color(153, 153, 255));
        panel_reasignacion.setDoubleBuffered(false);

        etiqueta_seleccion_ciclo_escolar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiqueta_seleccion_ciclo_escolar.setText("Seleccione el Ciclo Escolar del que desea reasignar:");
        etiqueta_seleccion_ciclo_escolar.setEnabled(false);

        ciclo_escolar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ciclo_escolar.setEnabled(false);
        ciclo_escolar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ciclo_escolarItemStateChanged(evt);
            }
        });

        buscar_para_reasignacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar_para_reasignacion.setText("Nueva Búsqueda");
        buscar_para_reasignacion.setEnabled(false);
        buscar_para_reasignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_para_reasignacionActionPerformed(evt);
            }
        });

        etiqueta_ciclo_escolar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiqueta_ciclo_escolar.setText("Ciclo escolar:");
        etiqueta_ciclo_escolar.setEnabled(false);

        etiqueta_grado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiqueta_grado.setText("Grado:");
        etiqueta_grado.setEnabled(false);

        grado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        grado.setEnabled(false);

        javax.swing.GroupLayout panel_reasignacionLayout = new javax.swing.GroupLayout(panel_reasignacion);
        panel_reasignacion.setLayout(panel_reasignacionLayout);
        panel_reasignacionLayout.setHorizontalGroup(
            panel_reasignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_reasignacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_reasignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta_seleccion_ciclo_escolar)
                    .addGroup(panel_reasignacionLayout.createSequentialGroup()
                        .addComponent(etiqueta_ciclo_escolar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ciclo_escolar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiqueta_grado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscar_para_reasignacion)))
                .addContainerGap(430, Short.MAX_VALUE))
        );
        panel_reasignacionLayout.setVerticalGroup(
            panel_reasignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_reasignacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta_seleccion_ciclo_escolar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_reasignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiqueta_ciclo_escolar)
                    .addComponent(etiqueta_grado)
                    .addComponent(ciclo_escolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar_para_reasignacion))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panel_estudiantes_encontrados.setBackground(new java.awt.Color(153, 153, 255));
        panel_estudiantes_encontrados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hay n de m estudiantes no asignados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tabla_estudiantes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_estudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Código Personal", "CUI", "Nombres", "Apellidos", "Asignación Nueva", "Asignación Anterior", "Ciclo Escolar", "Grado", "Sección"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_estudiantes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_estudiantes.setRowHeight(25);
        tabla_estudiantes.getTableHeader().setResizingAllowed(false);
        tabla_estudiantes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla_estudiantes);

        etiqueta_aviso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout panel_estudiantes_encontradosLayout = new javax.swing.GroupLayout(panel_estudiantes_encontrados);
        panel_estudiantes_encontrados.setLayout(panel_estudiantes_encontradosLayout);
        panel_estudiantes_encontradosLayout.setHorizontalGroup(
            panel_estudiantes_encontradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etiqueta_aviso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        panel_estudiantes_encontradosLayout.setVerticalGroup(
            panel_estudiantes_encontradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_estudiantes_encontradosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiqueta_aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        crear_asignacion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        crear_asignacion.setText("Crear Asignación");
        crear_asignacion.setEnabled(false);
        crear_asignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_asignacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(crear_asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(crear_asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_estudiantes_encontrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_reasignacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_reasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_estudiantes_encontrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crear_asignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_asignacionActionPerformed
        int[] rango = tabla_estudiantes.getSelectedRows();
        if (rango.length == 0)
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un registro", "Información", JOptionPane.INFORMATION_MESSAGE);
        else if (rango.length > 1)
            JOptionPane.showMessageDialog(this, "Seleccione sólo un registro a la vez", "Información", JOptionPane.INFORMATION_MESSAGE);
        else {
            RegistroAsignacionEST registro = listaEstudiantes.get(rango[0]);
            if (!registro.isAsignacionNueva()) {
                // Llamo a la Ventana encargada de realizar la Asignación
                AsignarEstudiante nuevaAsignacion = new AsignarEstudiante(new javax.swing.JFrame(), true, conexion, listaCiclos, registro, paraReasignacion);
                nuevaAsignacion.setVisible(nuevaAsignacion.getHacerVisible());
                tabla_estudiantes.setValueAt((registro.isAsignacionNueva()) ? "SI" : "NO", rango[0], 5);   // Actualizo el indicador de Asignación de la Tabla
            } else
                JOptionPane.showMessageDialog(this, ""+registro.getNombres()+" "+registro.getApellidos()+" ya tiene Asignación", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_crear_asignacionActionPerformed
    /**
     * Acción que permite realizar una búsqueda de Estudiantes que han sido asignados a un Ciclo Escolar, siempre y cuando
     * ya haya sido cerrado (finalizado). El evento encargado de actulizar el Ciclo Escolar (en el JComboBox ciclo_escolar),
     * y los Grados, habilita este botón siempre y cuando el Ciclo ya fue cerrado
     * @param evt 
     */
    private void buscar_para_reasignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_para_reasignacionActionPerformed
        if ("Nueva Búsqueda".equals(buscar_para_reasignacion.getText())) {
            // Se habilita el filtro para realizar la búsqueda
            buscar_para_reasignacion.setText("Mostrar Estudiantes");
            setEnabled_campos_resultado(false);
        } else {    // Se deshabilita los campos de filtro para realizar la búsqueda. Se realiza la búsqueda
            buscar_para_reasignacion.setText("Nueva Búsqueda");
            int indexCiclo = ciclo_escolar.getSelectedIndex();
            if (indexCiclo != -1) {
                cargar_estudiantes_para_reasignacion();
                // Bloqueo los componentes utilizados para cargar la reasignación
                setEnabled_campos_resultado(true);  // Se realizó una búsqueda. Habilito la tabla para poder seleccionar registros
            }
        }
    }//GEN-LAST:event_buscar_para_reasignacionActionPerformed
    private void setEnabled_campos_resultado(boolean valorEnabled) {
        etiqueta_seleccion_ciclo_escolar.setEnabled(!valorEnabled);
        etiqueta_ciclo_escolar.setEnabled(!valorEnabled);
        etiqueta_grado.setEnabled(!valorEnabled);
        ciclo_escolar.setEnabled(!valorEnabled);
        grado.setEnabled(!valorEnabled);
        tabla_estudiantes.setEnabled(valorEnabled);
        crear_asignacion.setEnabled(valorEnabled);
    }
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
            // Evalúo si se puede Reasignar estudiantes del Ciclo seleccionado (si ya está cerrado)
            etiqueta_aviso.setText(listaCiclos.get(indexCiclo).isCicloCerrado() ?
                    "" : "No se puede Reasignar estudiante del ciclo seleccionado pues aún no ha sido Cerrado.");
            buscar_para_reasignacion.setEnabled(listaCiclos.get(indexCiclo).isCicloCerrado());
        }
    }//GEN-LAST:event_ciclo_escolarItemStateChanged
    /**
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Estudiantes (valores definidos en base a pruebas)
        tabla_estudiantes.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_estudiantes.getColumnModel().getColumn(1).setPreferredWidth(110);
        tabla_estudiantes.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabla_estudiantes.getColumnModel().getColumn(3).setPreferredWidth(115);
        tabla_estudiantes.getColumnModel().getColumn(4).setPreferredWidth(140);
        tabla_estudiantes.getColumnModel().getColumn(5).setPreferredWidth(125);
        tabla_estudiantes.getColumnModel().getColumn(6).setPreferredWidth(120);
        tabla_estudiantes.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla_estudiantes.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla_estudiantes.getColumnModel().getColumn(9).setPreferredWidth(60);
    }
    public boolean getHacerVisible() { return hacerVisible; }
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
            java.util.logging.Logger.getLogger(PrincipalAsignacionEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrincipalAsignacionEST dialog = new PrincipalAsignacionEST(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton buscar_para_reasignacion;
    private javax.swing.JComboBox<String> ciclo_escolar;
    private javax.swing.JButton crear_asignacion;
    private javax.swing.JLabel etiqueta_aviso;
    private javax.swing.JLabel etiqueta_ciclo_escolar;
    private javax.swing.JLabel etiqueta_grado;
    private javax.swing.JLabel etiqueta_seleccion_ciclo_escolar;
    private javax.swing.JComboBox<String> grado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_estudiantes_encontrados;
    private javax.swing.JPanel panel_reasignacion;
    private javax.swing.JTable tabla_estudiantes;
    // End of variables declaration//GEN-END:variables
}
