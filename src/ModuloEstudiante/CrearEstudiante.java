/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import ModuloAsignacionEST.AsignarEstudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase en el que se ingresan los datos de un nuevo estudiante. Se evalúa si el nuevo estudiante que se quiere agregar
 * a la BD (de existir, no lo insertará) para evitar repetir datos.
 * @author Wilson Xicará
 */
public class CrearEstudiante extends javax.swing.JDialog {
    private Connection conexion;
    private PreparedStatement insercion;
    private Statement sentencia;
    private String mensaje_error_estudiante;
    private int nuevoEstudiante_Id, encargado_Id;
    private boolean encargadoYaExiste = false;
    /**
     * Creates new form NewJDialog
     * @param parent
     * @param modal
     */
    public CrearEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**
     * Inicializa la ventana con los campos requeridos para crear un nuevo Estudiante y agregarlo a la BD, así como la
     * información de su Encargado.
     * @param parent componente padre del nuevo JDialog a mostrar
     * @param modal modo de apertura. Si es 'true', no se permitirá trabajar sobre la ventana padre mientras este JDialog está abierto.
     * @param conexion objeto que permite la conexión y comunicación con la Base de Datos
     */
    public CrearEstudiante(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;   // Inicializo la conexión.
        try {
            this.sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarMunicipios();
    }
    private void cargarMunicipios() {
        // Realiza una consulta para obtenenr el nombre de los municipios (no obtengo el Id del municipio por ser correlativo al orden que tiene).
        estudiante_municipio.removeAllItems();
        encargado_municipio.removeAllItems();
        try {
            ResultSet municipios = sentencia.executeQuery("SELECT Nombre FROM Municipio");
            while (municipios.next()) {
                estudiante_municipio.addItem(municipios.getString("Nombre"));
                encargado_municipio.addItem(municipios.getString("Nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Función que evalua si el nuevo estudiante ya existe en la Base de Datos. Para ello, busca una coincidencia de
     * 'CodigoPersonal' de nuevo en la tabla Estudiante (ya que este campo es único para cada estudiante).
     * @return 'true' si el CodigoPersonal se repite; 'false' en caso contrario.
     */
    private boolean estudianteYaExiste() {
        boolean encontrado = false;
        // Hago una consulta a la tabla Estudiante en busca del campo estudiante_codigo_personal.getText() en la columna CodigoPersonal
        try {
            ResultSet consulta = sentencia.executeQuery("SELECT Id, CodigoPersonal, Nombres, Apellidos FROM Estudiante "
                    + "WHERE CodigoPersonal = '"+estudiante_codigo_personal.getText()+"'");
            encontrado = consulta.next();
            // Si la consulta regresa por lo menos un registro, consulta.next() es true, de lo contrario es false (no existe).
            if (encontrado == true) {
                mensaje_error_estudiante+= "Ya existe un estudiante con el mismo Código Personal:"
                        + "\n\tCodigo Personal: "+consulta.getString("CodigoPersonal")
                        + "\n\tNombres:         "+consulta.getString("Nombres")
                        + "\n\tApellidos:       "+consulta.getString("Apellidos");
            } else {    // Si el nuevo estudiante no existe, obtengo lo que será su Id
                consulta = sentencia.executeQuery("SELECT COUNT(*) Cantidad FROM Estudiante");
                nuevoEstudiante_Id = consulta.getInt("Cantidad") + 1;    // Obtengo lo que será el Id del nuevo estudiante
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en:\n"+e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        return encontrado;
    }
    /**
     * Función que evalua si el encargado del nuevo estudiante ya existe en la Base de Datos. Para ello, busca una coincidencia
     * de 'DPI' del encargado nuevo en la tabla Encargado (ya que este campo es único para cada encargado). Si existe, se
     * cargan dichos datos en los campos correspondientes y ya no se trata de crear un nuevo registro Encargado.
     * @return 'true' si el DPI (y por lo tanto el Encargado) ya existe; 'false' en caso contrario.
     */
    private void encargadoYaExiste() {
        // Hago una consulta a la tabla Encargado en busca del campo encargado_dpi.getText() en la columna DPI
        try {
            ResultSet consulta = sentencia.executeQuery("SELECT * FROM Encargado WHERE DPI = "+encargado_dpi.getText()+"");
            // Si la consulta regresa por lo menos un registro, consulta.next() es true, de lo contrario es false (no existe).
            encargadoYaExiste = consulta.next();
            if (encargadoYaExiste == true) {
                // Obtengo su Id
                encargado_Id = consulta.getInt("Id");
                // Inicio la carga de los datos
                encargado_nombre_completo.setText(consulta.getString("Nombre"));
                encargado_direccion.setText(consulta.getString("Direccion"));
                encargado_fechaNacimiento.setText(consulta.getString("FechaNacimiento"));
                encargado_municipio.setSelectedIndex(consulta.getInt("Municipio_Id")-1);
                encargado_relacion_con_estudiante.setText(consulta.getString("Relacion"));
                // Obtengo los números de teléfono asociados al Encargado
                ResultSet telefonos = sentencia.executeQuery("SELECT Telefono FROM Telefono WHERE Encargado_Id = "+encargado_Id+"");
                if (telefonos.next()) encargado_telefono_casa.setText(telefonos.getString("Telefono"));
                if (telefonos.next()) encargado_celular.setText(telefonos.getString("Telefono"));
                encargado_trabajo.setText(consulta.getString("Trabajo"));
            } else {
                // Se creará un nuevo registro Encargado (habilito los campos necesarios)
                encargado_nombre_completo.setEnabled(true);
                encargado_direccion.setEnabled(true);
                encargado_fechaNacimiento.setEnabled(true);
                encargado_municipio.setEnabled(true);
                encargado_relacion_con_estudiante.setEnabled(true);
                encargado_telefono_casa.setEnabled(true);
                encargado_celular.setEnabled(true);
                encargado_trabajo.setEnabled(true);
                consulta = sentencia.executeQuery("SELECT COUNT(*) Cantidad FROM Encargado");
                encargado_Id = consulta.getInt("Cantidad") + 1; // Obtengo lo que será el Id del nuevo Encargado
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en:\n"+e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
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

        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        estudiante_municipio = new javax.swing.JComboBox<>();
        encargado_municipio = new javax.swing.JComboBox<>();
        estudiante_sexo = new javax.swing.JComboBox<>();
        encargado_relacion_con_estudiante = new javax.swing.JTextField();
        estudiante_etnia = new javax.swing.JTextField();
        encargado_telefono_casa = new javax.swing.JTextField();
        estudiante_capacidad_diferente = new javax.swing.JComboBox<>();
        encargado_celular = new javax.swing.JTextField();
        estudiante_tipo_capacidad = new javax.swing.JTextField();
        encargado_trabajo = new javax.swing.JTextField();
        crear_estudiante = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        estudiante_fechaNacimiento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        estudiante_codigo_personal = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        estudiante_cui = new javax.swing.JTextField();
        estudiante_nombres = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        encargado_dpi = new javax.swing.JTextField();
        encargado_nombre_completo = new javax.swing.JTextField();
        estudiante_apellidos = new javax.swing.JTextField();
        encargado_fechaNacimiento = new javax.swing.JTextField();
        estudiante_direccion = new javax.swing.JTextField();
        encargado_direccion = new javax.swing.JTextField();
        crear_asignacion = new javax.swing.JButton();
        borrar_todo = new javax.swing.JButton();
        buscar_encargado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear nuevo estudiante");
        setPreferredSize(new java.awt.Dimension(925, 550));
        setSize(new java.awt.Dimension(925, 550));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Municipio:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Municipio:");

        estudiante_municipio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        encargado_municipio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        encargado_municipio.setEnabled(false);

        estudiante_sexo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        estudiante_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));

        encargado_relacion_con_estudiante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        encargado_relacion_con_estudiante.setEnabled(false);

        estudiante_etnia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        encargado_telefono_casa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        encargado_telefono_casa.setEnabled(false);

        estudiante_capacidad_diferente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        estudiante_capacidad_diferente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));
        estudiante_capacidad_diferente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estudiante_capacidad_diferenteItemStateChanged(evt);
            }
        });

        encargado_celular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        encargado_celular.setEnabled(false);

        estudiante_tipo_capacidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        estudiante_tipo_capacidad.setEnabled(false);

        encargado_trabajo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        encargado_trabajo.setEnabled(false);

        crear_estudiante.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        crear_estudiante.setText("Crear Estudiante");
        crear_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_estudianteActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("INFORMACIÓN DEL ESTUDIANTE:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Dirección:");

        estudiante_fechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Apellidos:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Relación con el estudiante:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Dirección:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Celular:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Trabajo u oficio:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Sexo:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("INFORMACIÓN DEL ENCARGADO:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Comunidad étnica:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("DPI:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Capacidad Diferente:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Teléfono de casa:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Tipo capacidad:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Nombre completo:");

        estudiante_codigo_personal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Fecha de Nacimiento:");

        estudiante_cui.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        estudiante_nombres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Codigo Personal:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("CUI:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombres:");

        encargado_dpi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        encargado_nombre_completo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        encargado_nombre_completo.setEnabled(false);

        estudiante_apellidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        encargado_fechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        encargado_fechaNacimiento.setEnabled(false);

        estudiante_direccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        encargado_direccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        encargado_direccion.setEnabled(false);

        crear_asignacion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        crear_asignacion.setText("Asignar Estudiante");
        crear_asignacion.setEnabled(false);
        crear_asignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_asignacionActionPerformed(evt);
            }
        });

        borrar_todo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        borrar_todo.setText("Iniciar nuevo");
        borrar_todo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrar_todoActionPerformed(evt);
            }
        });

        buscar_encargado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buscar_encargado.setText("Buscar");
        buscar_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_encargadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estudiante_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estudiante_capacidad_diferente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estudiante_cui, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(estudiante_apellidos, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(estudiante_nombres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(estudiante_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(51, 51, 51)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(118, 118, 118)
                                                    .addComponent(jLabel15))
                                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(encargado_relacion_con_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(encargado_telefono_casa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(60, 60, 60))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel21))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(buscar_encargado))
                                            .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(125, 125, 125)))
                                .addGap(0, 92, Short.MAX_VALUE))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(crear_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crear_asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrar_todo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel20)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buscar_encargado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(encargado_relacion_con_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(encargado_telefono_casa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estudiante_cui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estudiante_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estudiante_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estudiante_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(estudiante_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(estudiante_capacidad_diferente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crear_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(crear_asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrar_todo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crear_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_estudianteActionPerformed
        /* Creación de un nuevo Estudiante.
        La creación de un nuevo registro Estudiante consiste en lo siguiente:
        - Todo estudiante tiene un CodigoPersonal único, así que previo a la inserción se buscará si dicho codigo ya existe.
        - Se alertará si el nuevo estudiante tiene un nombre repetido (dando opción de decisión al respecto).
        - Se buscará si su encargado ya existe en la BD; de no existir se creará el nuevo encargado.
        - Todo encargado tendrá un DPI único (lo que ayudará en la decisión de crea un nuevo registro o no).
        - Para el encargado, se buscará si su número telefónico existe en la tabla Telefono; de no existir se creará.*/
        try {
            mensaje_error_estudiante = "";

            // Primero evalúo si el estudiante ya existe
            if (estudianteYaExiste() == false) {
                /* Evaluo si el encargado ya existe */
                if (encargadoYaExiste == false) {
                    // Creación de un nuevo registro para el Encargado
                    String nuevoEncargado = "INSERT INTO Encargado(Nombre, Direccion, DPI, FechaNacimiento, Relacion, Trabajo, Municipio_Id) VALUES (";
                    // Datos del encargado
                    nuevoEncargado+= "'"+encargado_nombre_completo.getText()+"',";
                    nuevoEncargado+= "'"+encargado_direccion.getText()+"',";
                    nuevoEncargado+= "'"+encargado_dpi.getText()+"',";
                    nuevoEncargado+= "'"+encargado_fechaNacimiento.getText()+"',";
                    nuevoEncargado+= "'"+encargado_relacion_con_estudiante.getText()+"',";
                    // De momento se crea nuevos teléfonos para cada nuevo Encargado
                    if (encargado_telefono_casa.getText().length() != 0) {
                        conexion.prepareStatement("INSERT INTO Telefono(Telefono, Encargado_Id) "
                                + "VALUES('"+encargado_telefono_casa.getText()+"', "+encargado_Id+")").executeUpdate();
                    }
                    if (encargado_celular.getText().length() != 0) {
                        conexion.prepareStatement("INSERT INTO Telefono(Telefono, Encargado_Id) "
                                + "VALUES('"+encargado_celular.getText()+"', "+encargado_Id+")").executeUpdate();
                    }
                    nuevoEncargado+= "'"+encargado_trabajo.getText()+"',";
                    nuevoEncargado+= ""+Integer.toString(encargado_municipio.getSelectedIndex()+1)+")";
                    // En encargadoYaExiste() obtuve el Id del nuevo encargado
                    conexion.prepareStatement(nuevoEncargado).executeUpdate();  // Inserción del nuevo registro
                }
                /* Creación de un nuevo retistro para el Nuevo Estudiante */
                String nuevoEstudiante = "INSERT INTO Estudiante(CodigoPersonal, CUI, Nombres, Apellidos, FechaNacimiento, Direccion,"
                        + "Sexo, Etnia, CapacidadDiferente, TipoCapacidad, Municipio_Id, Encargado_Id) VALUES (";
                // Datos del estudiante
                nuevoEstudiante+= "'"+estudiante_codigo_personal.getText()+"',";
                nuevoEstudiante+= "'"+estudiante_cui.getText()+"',";
                nuevoEstudiante+= "'"+estudiante_nombres.getText()+"',";
                nuevoEstudiante+= "'"+estudiante_apellidos.getText()+"',";
                nuevoEstudiante+= "'"+estudiante_fechaNacimiento.getText()+"',";
                nuevoEstudiante+= "'"+estudiante_direccion.getText()+"',";
                if (estudiante_sexo.getSelectedIndex() == 0) nuevoEstudiante+= "'F',";
                else nuevoEstudiante+= "'M',";
                nuevoEstudiante+= "'"+estudiante_etnia.getText()+"',";
                nuevoEstudiante+= (estudiante_capacidad_diferente.getSelectedIndex() == 0) ? ""+false+",NULL," : ""+true+",'"+estudiante_tipo_capacidad.getText()+"',";
                nuevoEstudiante+= ""+Integer.toString(estudiante_municipio.getSelectedIndex()+1)+",";
                nuevoEstudiante+= ""+encargado_Id+")";
                conexion.prepareStatement(nuevoEstudiante).execute();
                crear_asignacion.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, mensaje_error_estudiante, "Error!", JOptionPane.ERROR_MESSAGE, null);
                crear_asignacion.setEnabled(false);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_crear_estudianteActionPerformed

    private void crear_asignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_asignacionActionPerformed
        /* Esta opción se habilita sí y solo si el estudiante aún no existía y ya fué creado. Tengo su Id en 'estudiante_Id'.
           Desde aquí, no es necesario comprobar si la Asignación ya existe (pues el estudiante acaba de ser creado, y por lo
           tanto no tiene asignación).*/
        // Le paso el Id del estudiante recién creado, para que pueda realizarse su asignación
        this.setVisible(false);
        AsignarEstudiante nueva_ventana = new AsignarEstudiante(new javax.swing.JFrame(), true, conexion, nuevoEstudiante_Id);
        nueva_ventana.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_crear_asignacionActionPerformed

    private void borrar_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrar_todoActionPerformed
        // Borrado de los datos de Estudiante
        estudiante_codigo_personal.setText("");
        estudiante_cui.setText("");
        estudiante_nombres.setText("");
        estudiante_apellidos.setText("");
        estudiante_direccion.setText("");
        estudiante_fechaNacimiento.setText("");
        estudiante_etnia.setText("");
        estudiante_capacidad_diferente.setSelectedIndex(0);
        estudiante_tipo_capacidad.setText("");
        // Borrado de los datos de Encargado
        encargado_dpi.setText("");
        encargado_nombre_completo.setText("");
        encargado_direccion.setText("");
        encargado_fechaNacimiento.setText("");
        encargado_relacion_con_estudiante.setText("");
        encargado_telefono_casa.setText("");
        encargado_celular.setText("");
        encargado_trabajo.setText("");
        // Deshabilitación de los campos de Encargado
        encargado_nombre_completo.setEnabled(true);
        encargado_direccion.setEnabled(true);
        encargado_fechaNacimiento.setEnabled(true);
        encargado_municipio.setEnabled(true);
        encargado_relacion_con_estudiante.setEnabled(true);
        encargado_telefono_casa.setEnabled(true);
        encargado_celular.setEnabled(true);
        encargado_trabajo.setEnabled(true);
        // Deshabilitación del botón Crear Asignación
        crear_asignacion.setEnabled(false);
    }//GEN-LAST:event_borrar_todoActionPerformed

    private void estudiante_capacidad_diferenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estudiante_capacidad_diferenteItemStateChanged
        estudiante_tipo_capacidad.setEnabled(estudiante_capacidad_diferente.getSelectedIndex() != 0);
    }//GEN-LAST:event_estudiante_capacidad_diferenteItemStateChanged

    private void buscar_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_encargadoActionPerformed
        // Evalúo si existe un registro con DPI = 'encargado_dpi.getText()'.
        encargadoYaExiste();
    }//GEN-LAST:event_buscar_encargadoActionPerformed

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
            java.util.logging.Logger.getLogger(CrearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
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
    private javax.swing.JButton borrar_todo;
    private javax.swing.JButton buscar_encargado;
    private javax.swing.JButton crear_asignacion;
    private javax.swing.JButton crear_estudiante;
    private javax.swing.JTextField encargado_celular;
    private javax.swing.JTextField encargado_direccion;
    private javax.swing.JTextField encargado_dpi;
    private javax.swing.JTextField encargado_fechaNacimiento;
    private javax.swing.JComboBox<String> encargado_municipio;
    private javax.swing.JTextField encargado_nombre_completo;
    private javax.swing.JTextField encargado_relacion_con_estudiante;
    private javax.swing.JTextField encargado_telefono_casa;
    private javax.swing.JTextField encargado_trabajo;
    private javax.swing.JTextField estudiante_apellidos;
    private javax.swing.JComboBox<String> estudiante_capacidad_diferente;
    private javax.swing.JTextField estudiante_codigo_personal;
    private javax.swing.JTextField estudiante_cui;
    private javax.swing.JTextField estudiante_direccion;
    private javax.swing.JTextField estudiante_etnia;
    private javax.swing.JTextField estudiante_fechaNacimiento;
    private javax.swing.JComboBox<String> estudiante_municipio;
    private javax.swing.JTextField estudiante_nombres;
    private javax.swing.JComboBox<String> estudiante_sexo;
    private javax.swing.JTextField estudiante_tipo_capacidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
