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
 * Clase que permite crear UNA ASIGNACIÓN O UNA REASIGNACIÓN PARA UN ESTUDIANTE. Para ello, se evalua si la Asignación que
 * se quiere crear ya existe (que exista un registro de Asignación correspondiente al Ciclo Escolar en curso).
 * @author Wilson Xicará
 */
public class AsignarEstudiante extends javax.swing.JDialog {
    private Connection conexion;
    private DefaultTableModel modelEstudiante, modelCursos;
    private boolean paraReasignacion, ciclosCargados;
    private RegistroAsignacionEST estudiante;
    private ArrayList<RegistroCiclo> listaCiclos;
    /**
     * Creates new form AsignacionEST
     */
    public AsignarEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**
     * 
     * @param parent
     * @param modal
     * @param conexion
     * @param estudiante
     * @param paraReasignacion 
     */
    public AsignarEstudiante(java.awt.Frame parent, boolean modal, Connection conexion, RegistroAsignacionEST estudiante, boolean paraReasignacion) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        this.paraReasignacion = paraReasignacion;
        this.estudiante = estudiante;
        this.ciclosCargados = false;
        this.listaCiclos = new ArrayList<>();
        
        modelEstudiante = (DefaultTableModel)tabla_info_estudiante.getModel();
        modelCursos = (DefaultTableModel)tabla_info_cursos.getModel();
        // Inserto el Título de la Ventana, que tendrá el nombre del(la) estudiante
        this.setTitle(((paraReasignacion)?"Rea":"A")+"signación "+((estudiante.getSexo().equals("F"))?"de la":"del")+" estudiante "+estudiante.getNombres()+" "+estudiante.getApellidos());
        // Muestro la información del Estudiante
        etiqueta_informacion_estudiante.setText("Información "+((estudiante.getSexo().equals("F"))?"de la":"del")+" estudiante:");
        boolean asignacionAnterior = estudiante.isAsignacionAnterior();
        modelEstudiante.addRow(new String[]{
            estudiante.getCodigoPersonal(),
            estudiante.getCUI(),
            estudiante.getNombres()+" "+estudiante.getApellidos(),
            (asignacionAnterior)?"SI":"No",
            (asignacionAnterior)?estudiante.getAnio():"",
            (asignacionAnterior)?estudiante.getGrado()+" "+estudiante.getSeccion():"",
            (asignacionAnterior)?estudiante.getAula():""
        });
        // Muestro los Ciclos Escolares a los cuales se podría Asignar o Reasignar
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cCicloEscolar = sentencia.executeQuery("SELECT Anio FROM CicloEscolar");
            while(cCicloEscolar.next())
                listaCiclos.add(new RegistroCiclo(cCicloEscolar.getString("Anio")));
            
            // Ahora obtengo los grados asociados a cada Ciclo Escolar cargado desde la Base de Datos
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
            ciclo_escolar.setSelectedIndex(-1); // Esta parte es para generar un cambio de item, en caso de solo tener 1
            ciclo_escolar.setSelectedIndex(ciclo_escolar.getItemCount() - 1);  // Selecciona el último item
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al extraer los Ciclos Escolares:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(AsignarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
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

        panel_info_estudiante = new javax.swing.JPanel();
        etiqueta_informacion_estudiante = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_info_estudiante = new javax.swing.JTable();
        panel_info_asignacion = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        aula = new javax.swing.JTextField();
        ciclo_escolar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        grado = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_info_cursos = new javax.swing.JTable();
        crear_asignacion = new javax.swing.JButton();
        etiqueta_info_asignacion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Asignación de Estudiantes");

        panel_info_estudiante.setBackground(new java.awt.Color(153, 153, 255));

        etiqueta_informacion_estudiante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiqueta_informacion_estudiante.setText("Información Estudiante");

        tabla_info_estudiante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_info_estudiante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Personal", "CUI", "Nombre Completo", "Asignación Anterior", "Ciclo Escolar", "Grado", "Aula"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_info_estudiante.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_info_estudiante.setRowHeight(25);
        tabla_info_estudiante.getTableHeader().setResizingAllowed(false);
        tabla_info_estudiante.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla_info_estudiante);

        javax.swing.GroupLayout panel_info_estudianteLayout = new javax.swing.GroupLayout(panel_info_estudiante);
        panel_info_estudiante.setLayout(panel_info_estudianteLayout);
        panel_info_estudianteLayout.setHorizontalGroup(
            panel_info_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_info_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_info_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_info_estudianteLayout.createSequentialGroup()
                        .addComponent(etiqueta_informacion_estudiante)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_info_estudianteLayout.setVerticalGroup(
            panel_info_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_info_estudianteLayout.createSequentialGroup()
                .addComponent(etiqueta_informacion_estudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_info_asignacion.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Grado:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Aula:");

        aula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ciclo_escolar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ciclo_escolar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ciclo_escolarItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ciclo escolar:");

        grado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        grado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gradoItemStateChanged(evt);
            }
        });

        tabla_info_cursos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_info_cursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_info_cursos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_info_cursos.setRowHeight(25);
        tabla_info_cursos.getTableHeader().setResizingAllowed(false);
        tabla_info_cursos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabla_info_cursos);

        crear_asignacion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        crear_asignacion.setText("Crear Asignación");
        crear_asignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_asignacionActionPerformed(evt);
            }
        });

        etiqueta_info_asignacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        etiqueta_info_asignacion.setText("Información Asignación/Reasignación");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Cursos:");

        javax.swing.GroupLayout panel_info_asignacionLayout = new javax.swing.GroupLayout(panel_info_asignacion);
        panel_info_asignacion.setLayout(panel_info_asignacionLayout);
        panel_info_asignacionLayout.setHorizontalGroup(
            panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_info_asignacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta_info_asignacion)
                    .addGroup(panel_info_asignacionLayout.createSequentialGroup()
                        .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_info_asignacionLayout.createSequentialGroup()
                                .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ciclo_escolar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(grado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(aula, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel_info_asignacionLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(crear_asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addGroup(panel_info_asignacionLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panel_info_asignacionLayout.setVerticalGroup(
            panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_info_asignacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta_info_asignacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_info_asignacionLayout.createSequentialGroup()
                        .addGroup(panel_info_asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ciclo_escolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(crear_asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_info_asignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_info_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_info_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_info_asignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ciclo_escolarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ciclo_escolarItemStateChanged
        // Cada vez que se selecciona un Ciclo Escolar, se actualizan los valores del JComboBox 'grado'
        if (ciclosCargados) {
            int index = ciclo_escolar.getSelectedIndex();
            if (index != -1) {
                grado.removeAllItems(); // Borro los registros del JComboBox
                
                ArrayList<RegistroGrado> grados = listaCiclos.get(index).getGrados();   // Obtengo los Grados asignados al Ciclo Escolar seleccionado
                int cantidad = grados.size();
                for(int cont=0; cont<cantidad; cont++)
                    grado.addItem(grados.get(cont).getGradoSeccion());  // Agrego el Grado
            }
        }
    }//GEN-LAST:event_ciclo_escolarItemStateChanged

    private void crear_asignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_asignacionActionPerformed
        /* Para la asignación, los datos que se necesitan y de donde se obtienen son:
           - El Ciclo Escolar se obtiene de 'ciclo_escolar.getSelectedIndex()+1' (es correlativo con la Base de Datos).
           - El Grado se obtiene de 'listaGrados.get(grado.getSelectedIndex).getID'
           Los ID's se obtienen como se mencionó. */
        
        int cicloEscolarAnterior = (paraReasignacion) ? Integer.parseInt(estudiante.getAnio()) : 0;
        int cicloEscolarNuevo = Integer.parseInt((String)ciclo_escolar.getSelectedItem());
        // Evalúo que la nueva Reasignación (de ser el caso) realmente no exista
        boolean yaExiste = false;
        
        if (paraReasignacion) { 
            try {
                Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                ResultSet cAsignacion = sentencia.executeQuery("SELECT Id, CicloEscolar_Id, Estudiante_Id FROM AsignacionEST "
                        + "WHERE CicloEscolar_Id = "+(ciclo_escolar.getSelectedIndex()+1)+" AND Estudiante_Id = "+estudiante.getID()+"");
                yaExiste = cAsignacion.next();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al intentar comprobar la existencia de la Reasignación:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(AsignarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (cicloEscolarAnterior == cicloEscolarNuevo || yaExiste) {    // Evalúo que el estudiante no se reasigne en el ciclo escolar al que ya fue asignado
            JOptionPane.showMessageDialog(this,
                    ((estudiante.getSexo().equals("F"))?"La":"El")+" estudiante ya fue asignad"+((estudiante.getSexo().equals("F"))?"a":"o")+" en el Ciclo "+((yaExiste)?(String)ciclo_escolar.getSelectedItem():cicloEscolarAnterior),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (paraReasignacion && cicloEscolarNuevo < cicloEscolarAnterior) {  // Evalúo que el estudiante no se reasigne a un ciclo anterior al que tiene
            JOptionPane.showMessageDialog(this,
                    ((estudiante.getSexo().equals("F"))?"La":"El")+" estudiante no puede ser asignad"+((estudiante.getSexo().equals("F"))?"a":"o")+" al Ciclo "+cicloEscolarNuevo
                            + "\npues es anterior al Ciclo "+cicloEscolarAnterior+" al que ya fue asignad"+((estudiante.getSexo().equals("F"))?"a":"o."),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else if (aula.getText().length() == 0)
            JOptionPane.showMessageDialog(this, "No se le ha asignado un aula "+((estudiante.getSexo().equals("F"))?"a la":"al")+" estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
        else {  // Se realizará la Asignación/Reasignación
            int opcion = JOptionPane.showOptionDialog(this,
                    "Se creará la "+((paraReasignacion)?"Rea":"A")+"signación de "+estudiante.getNombres()+" "+estudiante.getApellidos()+":"
                            + "\nCiclo:  "+(String)ciclo_escolar.getSelectedItem()+""
                            + "\nGrado:  "+(String)grado.getSelectedItem()+""
                            + "\nAula:   "+aula.getText()+""
                            + "\n\nEstá seguro que desea continuar?",
                    "Aviso", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (opcion == JOptionPane.YES_OPTION) {
                int indexCiclo = ciclo_escolar.getSelectedIndex(), indexGrado = grado.getSelectedIndex();
                try {
                    String nuevaAsignacion = "INSERT INTO AsignacionEst(CicloEscolar_Id, Grado_Id, Estudiante_Id, Aula) VALUES("
                            +(indexCiclo+1)+","+listaCiclos.get(indexCiclo).getGrado(indexGrado).getID()+","+estudiante.getID()+",'"+ aula.getText()+"')";
                    conexion.prepareStatement(nuevaAsignacion).executeUpdate(); // Inserto y actulizo
                    estudiante.setAsignacionNueva(true);    // Actualizo el registro del estudiante para indicar que ya fue reasignado
                    crear_asignacion.setEnabled(false);     // Bloqueo el botón para evitar que se vuelva a asignar
                    ciclo_escolar.setEnabled(false);    // Bloqueo el JComboBox para evitar accesos innecesarios a la Base de Datos
                    grado.setEnabled(false);    // Bloqueo el JComboBox para evitar accesos innecesarios a la Base de Datos
                    aula.setEnabled(false);     // Bloqueo el campo donde se ingresa el Aula
                    JOptionPane.showMessageDialog(this, ((paraReasignacion)?"Rea":"A")+"signación creada exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al intentar crear la Asignación:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(AsignarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
        }
    }//GEN-LAST:event_crear_asignacionActionPerformed
    /**
     * Para evitar hacer una consulta a la Base de Datos cada vez que se cambia el item de este JComboBox, se cargarán los
     * datos a una estructura desde la cual se extraerá en caso de ya existir. Con ello, sólo se hará una petición a la BD
     * sólo cuando los grados no han sido cargados; de ya haber sido cargados, los obtiene del ArrayList que los contiene
     * @param evt 
     */
    private void gradoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gradoItemStateChanged
        int indexGrado = grado.getSelectedIndex();
        if (ciclosCargados && indexGrado != -1) {
            int indexCiclo = ciclo_escolar.getSelectedIndex();
            // Obtengo el listado de Cursos del Ciclo Escolar y Grado seleccionados
            ArrayList<String> listaCursos = listaCiclos.get(indexCiclo).getGrado(indexGrado).getListaCursos();
            if (listaCursos.isEmpty()) {    // Si no hay cursos, consulto la BD para obtener dichos cursos
                try {
                    // Obtengo los cursos asignados al Ciclo Escolar y Grado correspondientes
                    Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    ResultSet cCursos = sentencia.executeQuery("SELECT CicloEscolar_Id, Grado_Id, Curso_Id, Curso.Nombre Curso FROM AsignacionCAT "
                            + "INNER JOIN Curso ON AsignacionCAT.Curso_Id = Curso.Id "
                            + "WHERE AsignacionCAT.CicloEscolar_Id = "+(indexCiclo+1)+" AND Grado_Id = "+listaCiclos.get(indexCiclo).getGrado(indexGrado).getID());
                    int contCursos = 1;
                    while (cCursos.next()) {
                        listaCursos.add(cCursos.getString("Curso"));
                        modelCursos.addRow(new String[]{""+contCursos++, cCursos.getString("Curso")});
                    }
                    // Agrego los Cursos del Grado y Ciclo Escolar seleccionados
                    listaCiclos.get(indexCiclo).getGrado(indexGrado).addCursos(listaCursos);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al mostrar los Cursos:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(AsignarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   // Hasta aquí, listaCursos contiene los Cursos asociados al Grado y Ciclo Escolar seleccionados
            modelCursos.setRowCount(0);
            int cantidad = listaCursos.size();
            for(int i=0; i<cantidad; i++)
                modelCursos.addRow(new String[]{""+(i+1), listaCursos.get(i)});
        }
    }//GEN-LAST:event_gradoItemStateChanged
    /**
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Información Estudiante (valores definidos en base a pruebas)
        tabla_info_estudiante.getColumnModel().getColumn(0).setPreferredWidth(110);
        tabla_info_estudiante.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabla_info_estudiante.getColumnModel().getColumn(2).setPreferredWidth(195);
        tabla_info_estudiante.getColumnModel().getColumn(3).setPreferredWidth(120);
        tabla_info_estudiante.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabla_info_estudiante.getColumnModel().getColumn(5).setPreferredWidth(85);
        tabla_info_estudiante.getColumnModel().getColumn(6).setPreferredWidth(85);
        
        // Definición del ancho de las columnas para la Tabla Información Cursos (valores definidos en base a pruebas)
        tabla_info_cursos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla_info_cursos.getColumnModel().getColumn(1).setPreferredWidth(250);
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
            java.util.logging.Logger.getLogger(AsignarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AsignarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AsignarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AsignarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AsignarEstudiante dialog = new AsignarEstudiante(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField aula;
    private javax.swing.JComboBox<String> ciclo_escolar;
    private javax.swing.JButton crear_asignacion;
    private javax.swing.JLabel etiqueta_info_asignacion;
    private javax.swing.JLabel etiqueta_informacion_estudiante;
    private javax.swing.JComboBox<String> grado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel_info_asignacion;
    private javax.swing.JPanel panel_info_estudiante;
    private javax.swing.JTable tabla_info_cursos;
    private javax.swing.JTable tabla_info_estudiante;
    // End of variables declaration//GEN-END:variables
}
