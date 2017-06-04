/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilson Xicará
 */
public class CrearEstudiante extends javax.swing.JDialog {
    private Connection conexion;
    private int contadorIdEstudiantesEnBD, indexEstudianteEditado;
    private DefaultTableModel modelEstudiantes;
    private ArrayList<RegistroEstudiante> listaEstudiantes;
    private ArrayList<RegistroEncargado> listaEncargados;
    private RegistroEncargado encargadoAsignado;
    
    /**
     * Creates new form CrearEstudiante2
     */
    public CrearEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**APROBADO!!!
     * Inicializa la ventana con los campos requeridos para crear un nuevo Estudiante y agregarlo a la BD. Dicho proceso
     * también conlleva la creación (de ser necesario) y asignación de un Encargado al Estudiante.
     * @param parent componente padre del nuevo JDialog a mostrar
     * @param modal modo de apertura. Si es 'true', no se permitirá trabajar sobre la ventana padre mientras este JDialog está abierto.
     * @param conexion objeto que permite la conexión y comunicación con la Base de Datos
     */
    public CrearEstudiante(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        modelEstudiantes = (DefaultTableModel) tabla_estudiantes.getModel();
        this.conexion = conexion;   // Inicializo la conexión.
        
        // Obtengo los correlativos ID's de Estudiantes y Encargados que se creen temporalmente en las tablas
        try {
            Statement sentencia = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            // Obtengo el correlativo actual de los Id's de estudiantes en la BD (los nuevos serán mayores al actual mayor).
            ResultSet cEstudiante = sentencia.executeQuery("SELECT MAX(Id) Cantidad FROM Estudiante");
            cEstudiante.next();
            // Obtengo lo que será el inicio del correlativo de los Id's de los nuevos estudiantes
            contadorIdEstudiantesEnBD = cEstudiante.getInt("Cantidad");
            cEstudiante.close();
            sentencia.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar conectarse a la Base de Datos.\n"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Si ocurre un error en esta parte, cierro la ventana
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargar_municipios();    // Obtengo el listado de municipios almacenados en la Base de Datos
        /**
        DefaultTableModel model = new DefaultTableModel();
        JComboBox comboBox = new JComboBox(new String[]{"ITEM 1","ITEM 2", "ITEM 3"});
        DefaultCellEditor defaultCellEditor=new DefaultCellEditor(comboBox);
        tabla_estudiantes.getColumnModel().getColumn(2).setCellEditor(defaultCellEditor);
        **/
        this.setLocationRelativeTo(null);   // Para centrar esta ventana sobre la pantalla.
        listaEstudiantes = new ArrayList<>();
        listaEncargados = new ArrayList<>();
        
        // Evento para controlar cuando la ventana se quiere cerrar, y no se han guardado los datos temporales
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent we) {}
            @Override
            public void windowClosing(WindowEvent we) { antes_de_cerrar(); }
            @Override
            public void windowClosed(WindowEvent we) {}
            @Override
            public void windowIconified(WindowEvent we) {}
            @Override
            public void windowDeiconified(WindowEvent we) {}
            @Override
            public void windowActivated(WindowEvent we) {}
            @Override
            public void windowDeactivated(WindowEvent we) {}
        });
        estudiante_fechaNacimiento.getJCalendar().setWeekOfYearVisible(false);  // Para no mostrar el número de semana en el Calendario
        definir_ancho_columnas();   // Se define el ahcho de columnas en base a valores obtenidos previamente por pruebas
        
        guardar_fila_editada.setVisible(false);     // Oculto estos botones (se mostrarán en la edición de datos)
        cancelar_edicion.setVisible(false);
    }
    /**APROBADO!!!
     * Método que obtiene el listado de los Municipios almacenados en la Base de Datos para insertarlos en el JComboBox
     * que corresponde. Sólo se obtiene los nombres más no los Id's ya que son correlativos entre ambos.
     */
    private void cargar_municipios() {
        estudiante_municipio.removeAllItems();
        try {
            Statement sentencia = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cMunicipios = sentencia.executeQuery("SELECT Nombre FROM Municipio");
            while (cMunicipios.next())
                estudiante_municipio.addItem(cMunicipios.getString("Nombre"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar obtener los Municipios\n"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Si ocurre un error en este punto, cierro la ventana
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
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

        panel_datos_estudiante = new javax.swing.JPanel();
        panel_botones_estudiante = new javax.swing.JPanel();
        agregar_fila_estudiante = new javax.swing.JButton();
        cancelar_edicion = new javax.swing.JButton();
        guardar_fila_editada = new javax.swing.JButton();
        panel_datos_principales_estudiante = new javax.swing.JPanel();
        estudiante_codigo_personal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        estudiante_cui = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        estudiante_nombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        estudiante_apellidos = new javax.swing.JTextField();
        buscar_codigoPersonal_estudiante = new javax.swing.JButton();
        panel_datos_secundarios_estudiante = new javax.swing.JPanel();
        estudiante_direccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        estudiante_municipio = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        estudiante_fechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        estudiante_etnia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        estudiante_tipo_capacidad = new javax.swing.JTextField();
        estudiante_sexo_masculino = new javax.swing.JRadioButton();
        estudiante_sexo_femenino = new javax.swing.JRadioButton();
        estudiante_capacidad_diferente = new javax.swing.JRadioButton();
        panel_datos_encargado = new javax.swing.JPanel();
        estudiante_asignar_encargado = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        encargado_nombre_completo = new javax.swing.JTextField();
        encargado_relacion_con_estudiante = new javax.swing.JTextField();
        panel_lista_estudiantes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_estudiantes = new javax.swing.JTable();
        panel_botones_estudiante1 = new javax.swing.JPanel();
        editar_fila_estudiante = new javax.swing.JButton();
        eliminar_fila_estudiante = new javax.swing.JButton();
        panel_boton_guardar_todo = new javax.swing.JPanel();
        guardar_todos_los_registros = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear nuevo estudiante");

        panel_datos_estudiante.setBackground(new java.awt.Color(153, 153, 255));
        panel_datos_estudiante.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del nuevo Estudiante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        panel_botones_estudiante.setBackground(new java.awt.Color(51, 51, 255));

        agregar_fila_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregar_fila_estudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar.png"))); // NOI18N
        agregar_fila_estudiante.setText("Agregar");
        agregar_fila_estudiante.setEnabled(false);
        agregar_fila_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_fila_estudianteActionPerformed(evt);
            }
        });

        cancelar_edicion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelar_edicion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        cancelar_edicion.setText("Cancelar");
        cancelar_edicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar_edicionActionPerformed(evt);
            }
        });

        guardar_fila_editada.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        guardar_fila_editada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ok.png"))); // NOI18N
        guardar_fila_editada.setText("Guardar");
        guardar_fila_editada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_fila_editadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_botones_estudianteLayout = new javax.swing.GroupLayout(panel_botones_estudiante);
        panel_botones_estudiante.setLayout(panel_botones_estudianteLayout);
        panel_botones_estudianteLayout.setHorizontalGroup(
            panel_botones_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botones_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(agregar_fila_estudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardar_fila_editada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelar_edicion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_botones_estudianteLayout.setVerticalGroup(
            panel_botones_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_botones_estudianteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_botones_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(guardar_fila_editada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agregar_fila_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelar_edicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        estudiante_codigo_personal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_codigo_personal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                estudiante_codigo_personalKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Codigo Personal:");

        estudiante_cui.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_cui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                estudiante_cuiKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("CUI:");

        estudiante_nombres.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Nombres:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Apellidos:");

        estudiante_apellidos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        buscar_codigoPersonal_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar_codigoPersonal_estudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        buscar_codigoPersonal_estudiante.setText("Buscar coincidencias");
        buscar_codigoPersonal_estudiante.setIconTextGap(5);
        buscar_codigoPersonal_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_codigoPersonal_estudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_datos_principales_estudianteLayout = new javax.swing.GroupLayout(panel_datos_principales_estudiante);
        panel_datos_principales_estudiante.setLayout(panel_datos_principales_estudianteLayout);
        panel_datos_principales_estudianteLayout.setHorizontalGroup(
            panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_principales_estudianteLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_principales_estudianteLayout.createSequentialGroup()
                        .addComponent(estudiante_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(buscar_codigoPersonal_estudiante))
                    .addGroup(panel_datos_principales_estudianteLayout.createSequentialGroup()
                        .addGroup(panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estudiante_cui, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estudiante_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_datos_principales_estudianteLayout.setVerticalGroup(
            panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_principales_estudianteLayout.createSequentialGroup()
                .addGroup(panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_cui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_principales_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(0, 22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_principales_estudianteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buscar_codigoPersonal_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        estudiante_direccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_direccion.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Dirección:");

        estudiante_municipio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_municipio.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Municipio:");

        estudiante_fechaNacimiento.setDateFormatString("yyyy-MM-dd");
        estudiante_fechaNacimiento.setEnabled(false);
        estudiante_fechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Sexo:");

        estudiante_etnia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_etnia.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Comunidad étnica:");

        estudiante_tipo_capacidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_tipo_capacidad.setText("Sin especificar");
        estudiante_tipo_capacidad.setEnabled(false);

        estudiante_sexo_masculino.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_sexo_masculino.setSelected(true);
        estudiante_sexo_masculino.setText("Masculino");
        estudiante_sexo_masculino.setEnabled(false);
        estudiante_sexo_masculino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estudiante_sexo_masculinoItemStateChanged(evt);
            }
        });

        estudiante_sexo_femenino.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_sexo_femenino.setText("Femenino");
        estudiante_sexo_femenino.setEnabled(false);
        estudiante_sexo_femenino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estudiante_sexo_femeninoItemStateChanged(evt);
            }
        });

        estudiante_capacidad_diferente.setText("Capacidad diferente");
        estudiante_capacidad_diferente.setEnabled(false);
        estudiante_capacidad_diferente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estudiante_capacidad_diferenteItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panel_datos_secundarios_estudianteLayout = new javax.swing.GroupLayout(panel_datos_secundarios_estudiante);
        panel_datos_secundarios_estudiante.setLayout(panel_datos_secundarios_estudianteLayout);
        panel_datos_secundarios_estudianteLayout.setHorizontalGroup(
            panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_secundarios_estudianteLayout.createSequentialGroup()
                .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_datos_secundarios_estudianteLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel11)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_secundarios_estudianteLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(estudiante_capacidad_diferente)))
                    .addGroup(panel_datos_secundarios_estudianteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_datos_secundarios_estudianteLayout.createSequentialGroup()
                        .addComponent(estudiante_sexo_masculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(estudiante_sexo_femenino))
                    .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estudiante_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_datos_secundarios_estudianteLayout.setVerticalGroup(
            panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_secundarios_estudianteLayout.createSequentialGroup()
                .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_direccion)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_sexo_masculino)
                    .addComponent(estudiante_sexo_femenino)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estudiante_capacidad_diferente))
                .addContainerGap())
        );

        panel_datos_encargado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del Encargado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        estudiante_asignar_encargado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        estudiante_asignar_encargado.setText("Asignar encargado");
        estudiante_asignar_encargado.setEnabled(false);
        estudiante_asignar_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estudiante_asignar_encargadoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Nombre Completo:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Relación con el estudiante:");

        encargado_nombre_completo.setEditable(false);
        encargado_nombre_completo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        encargado_nombre_completo.setEnabled(false);

        encargado_relacion_con_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        encargado_relacion_con_estudiante.setEnabled(false);

        javax.swing.GroupLayout panel_datos_encargadoLayout = new javax.swing.GroupLayout(panel_datos_encargado);
        panel_datos_encargado.setLayout(panel_datos_encargadoLayout);
        panel_datos_encargadoLayout.setHorizontalGroup(
            panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estudiante_asignar_encargado)
                    .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                        .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(encargado_relacion_con_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_datos_encargadoLayout.setVerticalGroup(
            panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                .addComponent(estudiante_asignar_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(encargado_relacion_con_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_datos_estudianteLayout = new javax.swing.GroupLayout(panel_datos_estudiante);
        panel_datos_estudiante.setLayout(panel_datos_estudianteLayout);
        panel_datos_estudianteLayout.setHorizontalGroup(
            panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_datos_secundarios_estudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_datos_principales_estudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_datos_encargado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_estudianteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel_botones_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_datos_estudianteLayout.setVerticalGroup(
            panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                .addComponent(panel_datos_principales_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_datos_secundarios_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_datos_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_botones_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_lista_estudiantes.setBackground(new java.awt.Color(153, 153, 255));
        panel_lista_estudiantes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estudiantes agregados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tabla_estudiantes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_estudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Código Personal", "CUI", "Nombres", "Apellidos", "Dirección", "Municipio", "Fecha Nacimiento", "Sexo", "Comunidad Étnica", "Capacidad Diferente", "Tipo Capacidad", "Encargado", "Relacion"
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
        tabla_estudiantes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_estudiantes.setRowHeight(25);
        tabla_estudiantes.getTableHeader().setResizingAllowed(false);
        tabla_estudiantes.getTableHeader().setReorderingAllowed(false);
        tabla_estudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_estudiantesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_estudiantes);

        panel_botones_estudiante1.setBackground(new java.awt.Color(51, 51, 255));

        editar_fila_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editar_fila_estudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit.png"))); // NOI18N
        editar_fila_estudiante.setText("Editar");
        editar_fila_estudiante.setEnabled(false);
        editar_fila_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_fila_estudianteActionPerformed(evt);
            }
        });

        eliminar_fila_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar_fila_estudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        eliminar_fila_estudiante.setText("Quitar");
        eliminar_fila_estudiante.setEnabled(false);
        eliminar_fila_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_fila_estudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_botones_estudiante1Layout = new javax.swing.GroupLayout(panel_botones_estudiante1);
        panel_botones_estudiante1.setLayout(panel_botones_estudiante1Layout);
        panel_botones_estudiante1Layout.setHorizontalGroup(
            panel_botones_estudiante1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botones_estudiante1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editar_fila_estudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminar_fila_estudiante)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panel_botones_estudiante1Layout.setVerticalGroup(
            panel_botones_estudiante1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_botones_estudiante1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_botones_estudiante1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar_fila_estudiante)
                    .addComponent(editar_fila_estudiante))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_lista_estudiantesLayout = new javax.swing.GroupLayout(panel_lista_estudiantes);
        panel_lista_estudiantes.setLayout(panel_lista_estudiantesLayout);
        panel_lista_estudiantesLayout.setHorizontalGroup(
            panel_lista_estudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_lista_estudiantesLayout.createSequentialGroup()
                .addGap(0, 492, Short.MAX_VALUE)
                .addComponent(panel_botones_estudiante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
        );
        panel_lista_estudiantesLayout.setVerticalGroup(
            panel_lista_estudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_lista_estudiantesLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_botones_estudiante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_boton_guardar_todo.setBackground(new java.awt.Color(153, 153, 255));

        guardar_todos_los_registros.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        guardar_todos_los_registros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        guardar_todos_los_registros.setText("Guardar todos los registros");
        guardar_todos_los_registros.setEnabled(false);
        guardar_todos_los_registros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_todos_los_registrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_boton_guardar_todoLayout = new javax.swing.GroupLayout(panel_boton_guardar_todo);
        panel_boton_guardar_todo.setLayout(panel_boton_guardar_todoLayout);
        panel_boton_guardar_todoLayout.setHorizontalGroup(
            panel_boton_guardar_todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_boton_guardar_todoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guardar_todos_los_registros)
                .addGap(504, 504, 504))
        );
        panel_boton_guardar_todoLayout.setVerticalGroup(
            panel_boton_guardar_todoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_boton_guardar_todoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardar_todos_los_registros)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_datos_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_lista_estudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panel_boton_guardar_todo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_datos_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_lista_estudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_boton_guardar_todo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**APROBADO!!!
     * Acción que permite guardar todos los registros temporales, mostrados en la tabla, en la Base de Datos. Para ello, si
     * un registro está en la tabla es porque no se repite en la Base de Datos ni en la Tabla.
     * @param evt 
     */
    private void guardar_todos_los_registrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_todos_los_registrosActionPerformed
        // El método retorna false en caso de que ocurra un error de conexión con la Base de Datos, o si hay registros
        // incorrectos. Para el segundo caso, se carga el primer RegistroEstudiante incorrecto para ser editado
        int opcion = JOptionPane.showOptionDialog(this,
                "Si guarda ahora todos los registro, ya no podrá editarlos más adelante.\n\nDesea continuar?",
                "Guardar Todo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (opcion == JOptionPane.YES_OPTION) {
            boolean resultado = guardar_todos_los_registros();
            // Si los registros se guardan correctamente, deshabilito este botón
            guardar_todos_los_registros.setEnabled(!resultado);
        }
    }//GEN-LAST:event_guardar_todos_los_registrosActionPerformed

    private void estudiante_capacidad_diferenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estudiante_capacidad_diferenteItemStateChanged
        estudiante_tipo_capacidad.setText((estudiante_capacidad_diferente.isSelected())?"":"Sin especificar");
        estudiante_tipo_capacidad.setEnabled(estudiante_capacidad_diferente.isSelected());
    }//GEN-LAST:event_estudiante_capacidad_diferenteItemStateChanged

    private void estudiante_sexo_masculinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estudiante_sexo_masculinoItemStateChanged
        estudiante_sexo_femenino.setSelected(!estudiante_sexo_masculino.isSelected());
    }//GEN-LAST:event_estudiante_sexo_masculinoItemStateChanged

    private void estudiante_sexo_femeninoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estudiante_sexo_femeninoItemStateChanged
        estudiante_sexo_masculino.setSelected(!estudiante_sexo_femenino.isSelected());
    }//GEN-LAST:event_estudiante_sexo_femeninoItemStateChanged
    /**APROBADO!!!
     * Esta acción permitirá buscar en la Base de Datos alguna coincidencia de los campos Código Personal, CUI, Nombres
     * o Apellidos del Estudiante que se quiere agregar. Se mostrarán todos loa que coincidan con al menos uno de los campos
     * mencionados.
     * @param evt 
     */
    private void buscar_codigoPersonal_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_codigoPersonal_estudianteActionPerformed
        // Verifico que los datos a buscar sean correctos
        try {
            validar_datos_estudiante(true);
            // Si los datos son correctos:
            String codigoPersonal = estudiante_codigo_personal.getText();
            // Inicialización de la tabla que contendrá los registros encontrados
            javax.swing.JTable tabla_encontrados=  new javax.swing.JTable();
            tabla_encontrados.setFont(new java.awt.Font("Tahoma", 0, 14));
            tabla_encontrados.setModel(new DefaultTableModel(
                new Object [][] {},
                new String [] {"No.", "Código Personal", "CUI", "Nombres", "Apellidos", "Dirección", "Municipio", "Fecha Nacimiento", "Sexo", "Comunidad Étnica", "Capacidad Diferente", "Tipo Capacidad"}) {
                    Class[] types = new Class [] { java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class };
                    boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false, false, false, false, false };
                    @Override
                    public Class getColumnClass(int columnIndex) { return types [columnIndex]; }
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
            });
            tabla_encontrados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tabla_encontrados.setRowHeight(25);
            tabla_encontrados.getTableHeader().setResizingAllowed(false);
            tabla_encontrados.getTableHeader().setReorderingAllowed(false);
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
            javax.swing.JScrollPane miScroll = new javax.swing.JScrollPane();
            miScroll.setViewportView(tabla_encontrados);
            DefaultTableModel modelEncontrados = (DefaultTableModel)tabla_encontrados.getModel();

            // Realizo la consulta para obtener todos los registros que coincidan con al menos un campo
            String instruccion = "SELECT * FROM Estudiante "
                    + "WHERE CodigoPersonal = '"+codigoPersonal+"' OR CUI = '"+estudiante_cui.getText()+"' OR Nombres = '"+estudiante_nombres.getText()+"' OR Apellidos = '"+estudiante_apellidos.getText()+"'";
            try {
                Statement sentencia = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                ResultSet cEstudiante = sentencia.executeQuery(instruccion);
                int contador = 0;
                while (cEstudiante.next()) {    // Obtengo todos los registros de la consulta
                    contador++;
                    modelEncontrados.addRow(new String[] {""+contador,
                        cEstudiante.getString("CodigoPersonal"),
                        cEstudiante.getString("CUI"),
                        cEstudiante.getString("Nombres"),
                        cEstudiante.getString("Apellidos"),
                        cEstudiante.getString("Direccion"),
                        estudiante_municipio.getItemAt(cEstudiante.getInt("Municipio_Id") - 1),
                        cEstudiante.getString("FechaNacimiento"),
                        ("F".equals(cEstudiante.getString("Sexo"))?"Femenino":"Masculino"),
                        cEstudiante.getString("Etnia"),
                        (cEstudiante.getBoolean("CapacidadDiferente"))?"SI":"NO",
                        (cEstudiante.getBoolean("CapacidadDiferente"))?cEstudiante.getString("TipoCapacidad"):""
                    });
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al consultar la tabla de Estudiante\n"+e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, e);
            }

            // Ahora muestro la tabla con los registros encontrados en un cuadro de diálogo, para pedir confirmación
            if (tabla_encontrados.getRowCount() > 0) {
                String[] opciones = new String[]{"El estudiante que busco ya existe", "El estudiante que busco NO existe"};
                int opcion = JOptionPane.showOptionDialog(this, miScroll, "Registros que posiblemente coincidan", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                // Si ya existe un registro con el mismo Código Personal, cambio la desición del usuario
                int contEncontrados = modelEncontrados.getRowCount();
                for(int i=0; i<contEncontrados; i++) {
                    if (codigoPersonal.equals((String)tabla_encontrados.getValueAt(i, 1))) {
                        opcion = JOptionPane.YES_OPTION;
                        JOptionPane.showMessageDialog(this, "El registro "+(i+1)+" de la tabla tiene el mismo Código Personal que el nuevo.\n\nNo se podrá crear el nuevo", "Datos repetidos", JOptionPane.ERROR_MESSAGE);
                        setEnabled_estudiante_campos_secundarios(false);
                        break;
                    }
                }
                if (opcion == JOptionPane.NO_OPTION) {  // Si el usuario confirma que no existe el registro, habilito los campos para completar la información
                    setEnabled_estudiante_campos_secundarios(true);
                } else {    // Si el usuario confirma que existe el registro o cierra sin confirmar, no se podrá crear un nuevo registro
                    setEnabled_estudiante_campos_secundarios(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró algún registro similar", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                setEnabled_estudiante_campos_secundarios(true);
            }
        } catch (ExcepcionDatosIncorrectos ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscar_codigoPersonal_estudianteActionPerformed
    /**APROBADO!!!
     * Acción que permitirá agregar un nuevo registro temporalmente a la tabla de estudiantes. Para ello, se asume que ya se
     * ha evaluado si existen coincidencias y ya se ha confirmado al respecto. Previo a la inserción, evalúa en la tabla de
     * estudiantes si el registro aún no existe (pues la búsqueda anterior se realiza en la Base de Datos).
     * @param evt 
     */
    private void agregar_fila_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_fila_estudianteActionPerformed
        // Previo a la inserción, evalúo que los datos sean correctos y de que no estén en la tabla de estudiantes
        try {
            validar_datos_estudiante(false);
            // Realizo una búsqueda en la tabla de estudiantes en busca de algún registro igual
            int contFilas = tabla_estudiantes.getRowCount(), cont;
            boolean encontrado = false;
            for(cont=0; cont<contFilas; cont++) {
                if (estudiante_codigo_personal.getText().equals((String)tabla_estudiantes.getValueAt(cont, 1)) &&
                        estudiante_cui.getText().equals((String)tabla_estudiantes.getValueAt(cont, 2)) &&
                        estudiante_nombres.getText().equals((String)tabla_estudiantes.getValueAt(cont, 3)) &&
                        estudiante_apellidos.getText().equals((String)tabla_estudiantes.getValueAt(cont, 4))) {
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                JOptionPane.showMessageDialog(this, "El nuevo registro coincide con el\nregistro de la fila "+(cont+1)+"de la tabla\\n\\nNo se puede guardar el nuevo registro", "Datos repetidos", JOptionPane.ERROR_MESSAGE);
                tabla_estudiantes.setRowSelectionInterval(cont, cont);
            } else {    // Se creará el nuevo registro
                // Se inicializa con indicador false (aún no está guardado en la Base de Datos. No es necesario conocer su ID)
                RegistroEstudiante nuevoEst = new RegistroEstudiante();
                nuevoEst.setCodigoPersonal(estudiante_codigo_personal.getText());
                nuevoEst.setCUI(estudiante_cui.getText());
                nuevoEst.setNombres(estudiante_nombres.getText());
                nuevoEst.setApellidos(estudiante_apellidos.getText());
                nuevoEst.setDireccion(estudiante_direccion.getText());
                nuevoEst.setMunicipioId(estudiante_municipio.getSelectedIndex()+1);
                nuevoEst.setMunicipio((String)estudiante_municipio.getSelectedItem());
                Calendar fechaNac = estudiante_fechaNacimiento.getCalendar();
                nuevoEst.setFechaNacimiento(""+fechaNac.get(Calendar.YEAR)+"-"+(fechaNac.get(Calendar.MONTH)+1)+"-"+fechaNac.get(Calendar.DAY_OF_MONTH));
                nuevoEst.setSexo((estudiante_sexo_femenino.isSelected()) ? "F" : "M");
                nuevoEst.setEtnia(estudiante_etnia.getText());
                nuevoEst.setCapacidadDiferente(estudiante_capacidad_diferente.isSelected());
                nuevoEst.setTipoCapacidad((nuevoEst.isCapacidadDiferente()) ? estudiante_tipo_capacidad.getText() : "");
                // Inserción de los datos de su Encargado
                nuevoEst.setEncargadoId(encargadoAsignado.getID());
                nuevoEst.setNombreEncargado(encargadoAsignado.getNombres()+" "+encargadoAsignado.getApellidos());
                nuevoEst.setRelacionEncargado(encargado_relacion_con_estudiante.getText());
                
                // Agrego el nuevo registro al ArrayList y a la Tabla
                listaEstudiantes.add(nuevoEst);
                modelEstudiantes.addRow(nuevoEst.getDatosParaTabla(tabla_estudiantes.getRowCount() + 1));
                
                agregar_fila_estudiante.setEnabled(false);
                limpiar_campos_estudiante();
                setEnabled_estudiante_campos_secundarios(false);
                
                // En caso de que el botón 'Guardar todos los registros' esté deshabilitado
                if (!guardar_todos_los_registros.isEnabled())
                    guardar_todos_los_registros.setEnabled(true);
            }
        } catch (ExcepcionDatosIncorrectos ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en datos", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_agregar_fila_estudianteActionPerformed
    /**APROBADO!!!
     * Acción que permite asignar un Encargado al Estudiante que se creará. Para ello, muestra la ventana de
     * Encargados en el que se puede buscar un registro Encargado en la Base de Datos o se crea un nuevo registro para
     * asignárselo al nuevo estudiante.
     * @param evt 
     */
    private void estudiante_asignar_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estudiante_asignar_encargadoActionPerformed
        CrearEncargado crear = new CrearEncargado(new java.awt.Frame(), true, conexion, listaEncargados);
        crear.setVisible(true);
        encargadoAsignado = crear.getEncargado();
        if (encargadoAsignado != null) {
            encargado_nombre_completo.setText(encargadoAsignado.getNombres()+" "+encargadoAsignado.getApellidos());
            agregar_fila_estudiante.setEnabled(true);
        } else {
            agregar_fila_estudiante.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No se seleccionó un encargado", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_estudiante_asignar_encargadoActionPerformed

    private void tabla_estudiantesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_estudiantesMousePressed
        editar_fila_estudiante.setEnabled(true);
        eliminar_fila_estudiante.setEnabled(true);
    }//GEN-LAST:event_tabla_estudiantesMousePressed
    /**APROBADO!!!
     * Acción que permitirá editar los datos de un RegistroEstudiante, siempre que estos aún no estén guardados en la Base
     * de Datos. De un registro, se podrá editar todo excepto el Código Personal y el CUI; en caso de querer cambiar dichos
     * valores, se debe eliminar el registro y crear uno nuevo
     * @param evt 
     */
    private void editar_fila_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_fila_estudianteActionPerformed
        int[] rango = tabla_estudiantes.getSelectedRows();
        if (rango.length == 1) {
            if (listaEstudiantes.get(rango[0]).isGuardadoEnBD())  // Verifico que el registro aún no haya sido escrito en la Base de Datos
                JOptionPane.showMessageDialog(this, "El registro ya fué guardado en la Base de Datos.\n\nNo se puede editar.", "Registro no editable", JOptionPane.INFORMATION_MESSAGE);
            else {
                // Habilito los botones útiles y deshabilito otros
                editar_fila_estudiante.setEnabled(false);
                eliminar_fila_estudiante.setEnabled(false);
                guardar_fila_editada.setVisible(true);
                cancelar_edicion.setVisible(true);
                agregar_fila_estudiante.setVisible(false);
                cargar_registro_en_campos(listaEstudiantes.get(rango[0]));  // Cargo los datos del estudiante que se editarán
                setEnabled_estudiante_campos_secundarios(true);     // Habilito los campos secundarios para la edición
                estudiante_codigo_personal.setEditable(false);  // Hago no editable los campos Código Personal y CUI
                estudiante_cui.setEditable(false);
                indexEstudianteEditado = rango[0];  // Guardo el index del registro que se va a editar
            }
        } else
            JOptionPane.showMessageDialog(this, "Seleccione "+((rango.length==0)?"al menos":"sólo")+" un registro", "Aviso", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editar_fila_estudianteActionPerformed
    /**APROBADO!!!
     * Acción que elimina un RegistroEstudiante seleccionado.
     * @param evt 
     */
    private void eliminar_fila_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_fila_estudianteActionPerformed
        int[] rango = tabla_estudiantes.getSelectedRows();
        if (rango.length == 1) {
            int opcion = JOptionPane.showOptionDialog(this, "Está seguro de eliminar el registro?\n\nNo podrá revertir la acción.", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (opcion == JOptionPane.YES_OPTION) {
                // Actualizo el No. de los registros que preceden al que se eliminará
                int cantidad = listaEstudiantes.size();
                for(int i=rango[0]+1; i<cantidad; i++)
                    tabla_estudiantes.setValueAt(i - 1, i, 0);
                
                listaEstudiantes.remove(rango[0]);  // Elimino el registro del ArrayList
                modelEstudiantes.removeRow(rango[0]);   // Elimino el registro de la Tabla de Estudiantes
                
                // En caso de ya no haber filas por guardar, deshabilito el botón de Guardar todo, Editar y Quitar
                if (tabla_estudiantes.getRowCount() == 0) {
                    guardar_todos_los_registros.setEnabled(false);
                    editar_fila_estudiante.setEnabled(false);
                    eliminar_fila_estudiante.setEnabled(false);
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "Seleccione "+((rango.length==0)?"por lo menos":"sólo")+" un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_eliminar_fila_estudianteActionPerformed
    /**APROBADO!!!
     * Acción que permite guardar los cambios de la edición de un RegistroEstudiante.
     * @param evt 
     */
    private void guardar_fila_editadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_fila_editadaActionPerformed
        try {
            validar_datos_estudiante(false);    // Valido si los datos son correctos
            // El Código Personal y el CUI no cambiaron, pero puede que los Nombres y Apellidos se repitan
            int contFilas = listaEstudiantes.size(), indexRepetido, i;
            boolean repetido = false;
            // Verifico que no se repita los Nombres y Apellidos
            for(indexRepetido=i=0; i<contFilas; i++,indexRepetido++) {
                if (indexRepetido!=indexEstudianteEditado &&
                        estudiante_nombres.getText().equals(listaEstudiantes.get(i).getNombres()) && estudiante_apellidos.getText().equals(listaEstudiantes.get(i).getApellidos())) {
                    i = contFilas;
                    repetido = true;
                }
            }
            if (repetido)
                JOptionPane.showMessageDialog(this, "No se puede guardar los cambios.\n\nLos nuevos datos se repiten con los del registro "+(indexRepetido+1)+" de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            else {  // Se guardarán los cambios
                RegistroEstudiante editado = listaEstudiantes.get(indexEstudianteEditado);
                editado.setNombres(estudiante_nombres.getText());
                editado.setApellidos(estudiante_apellidos.getText());
                editado.setDireccion(estudiante_direccion.getText());
                editado.setMunicipioId(estudiante_municipio.getSelectedIndex() + 1);
                editado.setMunicipio((String)estudiante_municipio.getSelectedItem());
                Calendar fecha = estudiante_fechaNacimiento.getCalendar();
                editado.setFechaNacimiento(""+fecha.get(Calendar.YEAR)+"-"+(fecha.get(Calendar.MONTH)+1)+"-"+fecha.get(Calendar.DAY_OF_MONTH));
                editado.setSexo(estudiante_sexo_masculino.isSelected() ? "M" : "F");
                editado.setEtnia(estudiante_etnia.getText());
                editado.setCapacidadDiferente(estudiante_capacidad_diferente.isSelected());
                editado.setTipoCapacidad(editado.isCapacidadDiferente() ? estudiante_tipo_capacidad.getText() : "");
                if (encargadoAsignado != null) {    // Se le asignó otro encargado
                    editado.setEncargadoId(encargadoAsignado.getID());
                    editado.setNombreEncargado(encargado_nombre_completo.getText());
                }
                editado.setRelacionEncargado(encargado_relacion_con_estudiante.getText());
                // Hasta aquí, ya se ha modificado los datos del registro
                
                limpiar_campos_estudiante();    // Borro los datos de los campos
                setEnabled_estudiante_campos_secundarios(false);    // Deshabilito los campos secundarios
                estudiante_codigo_personal.setEditable(true);  // Hago editable los campos Código Personal y CUI
                estudiante_cui.setEditable(true);
                guardar_fila_editada.setVisible(false);     // Deshabilito los botones usados para la edición
                cancelar_edicion.setVisible(false);
                editar_fila_estudiante.setEnabled(true);
                eliminar_fila_estudiante.setEnabled(true);
                agregar_fila_estudiante.setVisible(true);
                
                JOptionPane.showMessageDialog(this, "Registro editado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
                
                // Ahora actualizo los datos en la tabla
                String[] datosEditados = listaEstudiantes.get(indexEstudianteEditado).getDatosParaTabla(indexEstudianteEditado+1);
                for(i=3; i<datosEditados.length; i++)
                    tabla_estudiantes.setValueAt(datosEditados[i], indexEstudianteEditado, i);
            }
        } catch (ExcepcionDatosIncorrectos ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardar_fila_editadaActionPerformed
    /**APR0BADO!!!
     * Acción que permite cancelar la edición de los datos del RegistroEstudiante seleccionado.
     * @param evt 
     */
    private void cancelar_edicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar_edicionActionPerformed
        limpiar_campos_estudiante();    // Limpio los datos cargados a los campos
        setEnabled_estudiante_campos_secundarios(false);    // Inhabilito los campos secundarios
        estudiante_codigo_personal.setEditable(true);  // Hago editable los campos Código Personal y CUI
        estudiante_cui.setEditable(true);
        guardar_fila_editada.setVisible(false); // Oculto los botones utilizados para la edición
        cancelar_edicion.setVisible(false);
        editar_fila_estudiante.setEnabled(true);
        eliminar_fila_estudiante.setEnabled(true);
        agregar_fila_estudiante.setVisible(true);
    }//GEN-LAST:event_cancelar_edicionActionPerformed
    /**
     * Eventos para controlar el ingreso de datos en CUI, Código Personal. Ambos tienen un formato específico.
     */
    private void estudiante_codigo_personalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_estudiante_codigo_personalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_estudiante_codigo_personalKeyTyped

    private void estudiante_cuiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_estudiante_cuiKeyTyped
        // Se acepta la tecla si es un dígito y si hay menos de 13 dígitos en el CUI
        if (!Pattern.compile("\\d").matcher(String.valueOf(evt.getKeyChar())).matches() || estudiante_cui.getText().length() == 13)
            evt.consume();
    }//GEN-LAST:event_estudiante_cuiKeyTyped
    /**APROBADO!!!
     * Método que carga los datos de un RegistroEstudiante a los campos correspondientes. Útil en la edición de un registro.
     * @param estudiante registro del que se extraerán los datos para cargarlos a los campos correspondientes.
     */
    private void cargar_registro_en_campos(RegistroEstudiante estudiante) {
        estudiante_codigo_personal.setText(estudiante.getCodigoPersonal());
        estudiante_cui.setText(estudiante.getCUI());
        estudiante_nombres.setText(estudiante.getNombres());
        estudiante_apellidos.setText(estudiante.getApellidos());
        estudiante_direccion.setText(estudiante.getDireccion());
        estudiante_municipio.setSelectedIndex(estudiante.getMunicipioId() - 1);
        estudiante_fechaNacimiento.setDate(estudiante.getDateNacimiento());
        estudiante_sexo_masculino.setSelected("M".equals(estudiante.getSexo()));
        estudiante_etnia.setText(estudiante.getEtnia());
        estudiante_capacidad_diferente.setSelected(estudiante.isCapacidadDiferente());
        estudiante_tipo_capacidad.setText(estudiante.isCapacidadDiferente() ? estudiante.getTipoCapacidad() : "");
        encargado_nombre_completo.setText(estudiante.getNombreEncargado());
        encargado_relacion_con_estudiante.setText(estudiante.getRelacionEncargado());
    }
    /**APROBADO!!!
     * Función que valida que los datos del nuevo Estudiante sean correctos. Para el Código Personal y el CUI, hay eventos
     * que controlan que los formatos sean correctos; lo único por evaluar es que estén completos.
     * @param paraBuscar booleano que indica si la validación se hace a la hora de buscar coincidencias en la Base de Datos.
     * Si los datos son para buscar coincidencias, no se necesitan valores en los campos secundarios (pues estarían bloqueados).
     * @throws ExcepcionDatosIncorrectos 
     */
    private void validar_datos_estudiante(boolean paraBuscar) throws ExcepcionDatosIncorrectos {
        if (!Pattern.compile("\\D\\d{3}\\D{3}").matcher(estudiante_codigo_personal.getText()).matches())
            throw new ExcepcionDatosIncorrectos("El Código Personal es incorrecto.\n\nEl formato correcto es:\nUn caracter, Tres dígitos y Tres caracteres (sin espacios)");
        if (estudiante_cui.getText().length() != 13)
            throw new ExcepcionDatosIncorrectos("El Código Único de Identificación debe tener 13 dígitos.");
        if (estudiante_nombres.getText().length()==0 || estudiante_apellidos.getText().length()==0)
            throw new ExcepcionDatosIncorrectos("Los nombres o los apellidos no pueden ser nulos");
        if (!paraBuscar) {
            if (estudiante_direccion.getText().length() == 0)
                throw new ExcepcionDatosIncorrectos("No se ha especificado la dirección de estudiante");
            if (estudiante_municipio.getSelectedIndex() == -1)
                throw new ExcepcionDatosIncorrectos("No se ha especificado el municipio del estudiante.");
            if (estudiante_fechaNacimiento.getDate() == null)
                throw new ExcepcionDatosIncorrectos("No se ha especificado la fecha de nacimiento del estudiante");
            if (estudiante_etnia.getText().length() == 0)
                throw new ExcepcionDatosIncorrectos("No se ha especificado la comunidad étnica del estudiante");
            if (estudiante_capacidad_diferente.isSelected() && estudiante_tipo_capacidad.getText().length() == 0)
                throw new ExcepcionDatosIncorrectos("No se ha especificado el tipo de capacidad diferente del estudiante");
            // Un encargado ya ha sido asignado si se muestra su nombre completo (ya que al crearlo, no puede ser nulo)
            if (encargado_nombre_completo.getText().length() == 0)
                throw new ExcepcionDatosIncorrectos("No se ha asignado un Encargado al Estudiante");
            if (encargado_relacion_con_estudiante.getText().length() == 0)
                throw new ExcepcionDatosIncorrectos("No se ha especificado la relación entre el Estudiante y su Encargado");
        }
    }
    /**APROBADO!!!
     * Método que habilita o inhabilita los campos en los que se ingresará la información secundaria del nuevo Estudiante. La
     * información secundaria es toda aquella información de la que no se busca coincidencias en la Base de Datos.
     * @param valorEnabled valor que se le pasará a la propiedad setEnabled() de los campos correspondientes.
     */
    private void setEnabled_estudiante_campos_secundarios(boolean valorEnabled) {
        estudiante_direccion.setEnabled(valorEnabled);
        estudiante_municipio.setEnabled(valorEnabled);
        estudiante_fechaNacimiento.setEnabled(valorEnabled);
        estudiante_sexo_masculino.setEnabled(valorEnabled);
        estudiante_sexo_femenino.setEnabled(valorEnabled);
        estudiante_etnia.setEnabled(valorEnabled);
        estudiante_capacidad_diferente.setEnabled(valorEnabled);
        estudiante_asignar_encargado.setEnabled(valorEnabled);
        encargado_nombre_completo.setEnabled(valorEnabled);
        encargado_relacion_con_estudiante.setEnabled(valorEnabled);
    }
    /**APROBADO!!!
     * Método que inserta texto vacío en los campos de entrada de datos de un nuevo estudiante (también hace null al encargado buscado anteriormente).
     */
    private void limpiar_campos_estudiante() {
        estudiante_codigo_personal.setText("");
        estudiante_cui.setText("");
        estudiante_nombres.setText("");
        estudiante_apellidos.setText("");
        estudiante_direccion.setText("");
        estudiante_municipio.setSelectedIndex(-1);
        estudiante_fechaNacimiento.setDate(null);
        estudiante_etnia.setText("");
        estudiante_capacidad_diferente.setSelected(false);
        encargadoAsignado = null;
        encargado_nombre_completo.setText("");
        encargado_relacion_con_estudiante.setText("");
    }
    /**APROBADO!!!
     * Función que escribe en la Base de Datos todos los RegistroEstudiante que aún no han sido guardados. Se sabe que un
     * registro aún no ha sido guardado si su atributo correspondiente es false, esto es si
     * RegistroEstudiante::guardadoEnBD = false
     * A la hora de guardar un registro en la BD, se marcan dicho valores como true (pero no se eliminan de los ArrayList).
     * @return 'true' si se guardan todos los registros no guardados con éxito; 'false' si ocurre un error al intentar guardar
     * uno de los registros.
     */
    private boolean guardar_todos_los_registros() {
        boolean guardadoSinProblemas = true;
        int total = listaEstudiantes.size(), guardados = 0;
        String indexNoGuardados = "";
        for(int cont=0; cont<total; cont++) {
            RegistroEstudiante iterador = listaEstudiantes.get(cont);
            if (!iterador.isGuardadoEnBD()) {   // Busco los registros que aún no han sido guardados
                String insert = "INSERT INTO Estudiante(CodigoPersonal, CUI, Nombres, Apellidos, FechaNacimiento, Direccion, Sexo, Etnia, CapacidadDiferente, TipoCapacidad, Municipio_Id, Encargado_Id, RelacionEncargado) VALUES(";
                insert+= "'"+iterador.getCodigoPersonal()+"', ";
                insert+= "'"+iterador.getCUI()+"', ";
                insert+= "'"+iterador.getNombres()+"', ";
                insert+= "'"+iterador.getApellidos()+"', ";
                insert+= "'"+iterador.getFechaNacimiento()+"', ";
                insert+= "'"+iterador.getDireccion()+"', ";
                insert+= "'"+iterador.getSexo()+"', ";
                insert+= "'"+iterador.getEtnia()+"', ";
                insert+= iterador.isCapacidadDiferente()+", ";
                insert+= (iterador.isCapacidadDiferente() ? "'"+iterador.getTipoCapacidad()+"', " : "NULL, ");
                insert+= iterador.getMunicipioId()+", ";
                insert+= iterador.getEncargadoId()+", ";
                insert+= "'"+iterador.getRelacionEncargado()+"')";
                try {
                    System.out.println("insert = "+insert);
                    conexion.prepareStatement(insert).executeUpdate();  // Inserción en la Base de Datos del RegistroEstudiante
                    iterador.setGuardadoEnBD(true); // Si no hay errores, prosigo a cambiar el indicador de 'iterador'
                    guardados++;
                    // HASTA AQUÍ SE GARANTIZA LA CREACIÓN DEL NUEVO ESTUDIANTE.
                } catch (SQLException ex) {
                    indexNoGuardados+= ""+(cont+1)+", ";
                    JOptionPane.showMessageDialog(this, "No se puede guardar el registro "+(cont+1)+":\n\n"+ex.getMessage(), "Error", HEIGHT);
                    guardadoSinProblemas = false;
//                    Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!guardadoSinProblemas) { // Por lo menos un registro no se guardó con éxito.
            indexNoGuardados = indexNoGuardados.substring(0, indexNoGuardados.length()-2);
            JOptionPane.showMessageDialog(this, "Los siguientes registros de la tabla no se guardaron:\n\n"+indexNoGuardados+"\n\nVerifique dichos registros.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else
            JOptionPane.showMessageDialog(this, "Se ha"+((guardados!=1)?"n":"")+" guardado "+guardados+" registro"+((guardados!=1)?"s":"")+" exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        return guardadoSinProblemas;
    }
    /**APROBADO!!!
     * Función que se lanza previo a cerrar el JDialog en caso de haber registros que aún no han sido guardados. Para ello,
     * lanza un JOptionPane en el que se pide una acción y se toma la medida dependiendo del resultado.
     * Si ocurre un error al intentar guardar un registro (el método correspondiente retorna false) se pide acción al usuario
     * de ignorarlos o corregirlos.
     */
    private void antes_de_cerrar() {
        int contEstudiantes = 0, cantidad, i;
        for(i=0, cantidad=listaEstudiantes.size(); i<cantidad; i++) // Cuento los RegistroEstudiante's que no han sido guardados
            if (!listaEstudiantes.get(i).isGuardadoEnBD())
                contEstudiantes++;
        
        if (contEstudiantes != 0) {
            String[] opciones = new String[]{"Guardar Todo", "Salir sin guardar", "Cancelar"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "Aún hay "+(contEstudiantes)+" registro"+((contEstudiantes==1)?" no guardado.":"s no guardados.")
                    + "\nDesea guardarlos antes de salir?"
                    + "\n\nTome en cuenta que si elige Salir sin guardar"
                    + "\nperderá todos los registros no guardados.",
                    "Aviso", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (opcion == JOptionPane.YES_OPTION) {
                boolean guardadoSinProlemas = guardar_todos_los_registros();
                if (!guardadoSinProlemas) {
                    int opcion2 = JOptionPane.showOptionDialog(this,
                            "Uno de los registros no pudo guardarse.\n\nDesea ignoralos y continuar?\n\nSi los ignora, no podrá recuperalos",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (opcion2 == JOptionPane.YES_OPTION)
                        guardadoSinProlemas = true;
                }
                this.setDefaultCloseOperation(guardadoSinProlemas ? javax.swing.JDialog.DISPOSE_ON_CLOSE : javax.swing.JDialog.DO_NOTHING_ON_CLOSE);
            } else
                this.setDefaultCloseOperation((opcion == JOptionPane.NO_OPTION) ? javax.swing.JDialog.DISPOSE_ON_CLOSE : javax.swing.JDialog.DO_NOTHING_ON_CLOSE);
            // 'javax.swing.JDialog.DISPOSE_ON_CLOSE'       cierra la ventana
            // 'javax.swing.JDialog.DO_NOTHING_ON_CLOSE'    no cierra la ventana
        }
    }
    /**APROBADO!!!
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Estudiantes (valores definidos en base a pruebas)
        tabla_estudiantes.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_estudiantes.getColumnModel().getColumn(1).setPreferredWidth(110);
        tabla_estudiantes.getColumnModel().getColumn(2).setPreferredWidth(135);
        tabla_estudiantes.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabla_estudiantes.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabla_estudiantes.getColumnModel().getColumn(5).setPreferredWidth(225);
        tabla_estudiantes.getColumnModel().getColumn(6).setPreferredWidth(180);
        tabla_estudiantes.getColumnModel().getColumn(7).setPreferredWidth(120);
        tabla_estudiantes.getColumnModel().getColumn(8).setPreferredWidth(80);
        tabla_estudiantes.getColumnModel().getColumn(9).setPreferredWidth(80);
        tabla_estudiantes.getColumnModel().getColumn(10).setPreferredWidth(125);
        tabla_estudiantes.getColumnModel().getColumn(11).setPreferredWidth(230);
        tabla_estudiantes.getColumnModel().getColumn(12).setPreferredWidth(250);
        tabla_estudiantes.getColumnModel().getColumn(13).setPreferredWidth(115);
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
            java.util.logging.Logger.getLogger(CrearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CrearEstudiante dialog = new CrearEstudiante(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton agregar_fila_estudiante;
    private javax.swing.JButton buscar_codigoPersonal_estudiante;
    private javax.swing.JButton cancelar_edicion;
    private javax.swing.JButton editar_fila_estudiante;
    private javax.swing.JButton eliminar_fila_estudiante;
    private javax.swing.JTextField encargado_nombre_completo;
    private javax.swing.JTextField encargado_relacion_con_estudiante;
    private javax.swing.JTextField estudiante_apellidos;
    private javax.swing.JButton estudiante_asignar_encargado;
    private javax.swing.JRadioButton estudiante_capacidad_diferente;
    private javax.swing.JTextField estudiante_codigo_personal;
    private javax.swing.JTextField estudiante_cui;
    private javax.swing.JTextField estudiante_direccion;
    private javax.swing.JTextField estudiante_etnia;
    private com.toedter.calendar.JDateChooser estudiante_fechaNacimiento;
    private javax.swing.JComboBox<String> estudiante_municipio;
    private javax.swing.JTextField estudiante_nombres;
    private javax.swing.JRadioButton estudiante_sexo_femenino;
    private javax.swing.JRadioButton estudiante_sexo_masculino;
    private javax.swing.JTextField estudiante_tipo_capacidad;
    private javax.swing.JButton guardar_fila_editada;
    private javax.swing.JButton guardar_todos_los_registros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_boton_guardar_todo;
    private javax.swing.JPanel panel_botones_estudiante;
    private javax.swing.JPanel panel_botones_estudiante1;
    private javax.swing.JPanel panel_datos_encargado;
    private javax.swing.JPanel panel_datos_estudiante;
    private javax.swing.JPanel panel_datos_principales_estudiante;
    private javax.swing.JPanel panel_datos_secundarios_estudiante;
    private javax.swing.JPanel panel_lista_estudiantes;
    private javax.swing.JTable tabla_estudiantes;
    // End of variables declaration//GEN-END:variables
}
