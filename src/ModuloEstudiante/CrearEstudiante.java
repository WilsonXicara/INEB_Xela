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
    private Statement sentencia = null;
    private String mensaje_error_estudiante, mensaje_error_encargado;
    private int estudiante_Id, encargado_Id;
    private boolean estudianteCreado = false;
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
     * @param parent
     * @param modal
     * @param conexion objeto que permite la conexión y comunicación con la Base de Datos
     */
    public CrearEstudiante(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
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
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet consulta = sentencia.executeQuery("SELECT Id, CodigoPersonal, Nombres, Apellidos FROM Estudiante"
                    + " WHERE CodigoPersonal = '"+estudiante_codigo_personal.getText()+"'");
            encontrado = consulta.next();
            // Si la consulta regresa por lo menos un registro, consulta.next() es true, de lo contrario es false (no existe).
            if (encontrado == true) {
                mensaje_error_estudiante+= "Ya existe un estudiante con el mismo Código Personal:"
                        + "\n\tCodigo Personal: "+consulta.getString("CodigoPersonal")
                        + "\n\tNombres:         "+consulta.getString("Nombres")
                        + "\n\tApellidos:       "+consulta.getString("Apellidos");
            } else {    // Si el nuevo estudiante no existe, obtengo lo que será su Id
                consulta = sentencia.executeQuery("SELECT COUNT(*) Cantidad FROM Estudiante");
                estudiante_Id = consulta.getInt("Cantidad") + 1;    // Obtengo lo que será el Id del nuevo estudiante
            }
            sentencia.close();
            consulta.close();
        } catch (SQLException e) {
            System.out.println("Error en estudianteYaExiste: "+e.getMessage());
        }
        return encontrado;
    }
    /**
     * Función que evalua si el encargado del nuevo estudiante ya existe en la Base de Datos. Para ello,
     * busca una coincidencia de 'DPI' del encargado nuevo en la tabla Encargado (ya que este campo es único para cada encargado).
     * @return 'true' si el DPI (y por lo tanto el Encargado) ya existe; 'false' en caso contrario.
     */
    private boolean encargadoYaExiste() {
        boolean encontrado = false;
        // Hago una consulta a la tabla Encargado en busca del campo encargado_dpi.getText() en la columna DPI
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet consulta = sentencia.executeQuery("SELECT Id, Nombre, DPI FROM Encargado"
                    + " WHERE DPI = '"+encargado_dpi.getText()+"'");
            // Si la consulta regresa por lo menos un registro, consulta.next() es true, de lo contrario es false (no existe).
            encontrado = consulta.next();
            if (encontrado == true) {
                mensaje_error_encargado+= "Ya existe un encargado con el mismo DPI:"
                        + "\n\tNombre: "+consulta.getString("Nombre")
                        + "\n\tDPI:    "+consulta.getString("DPI")
                        + "\nSe utilizará los datos anteriores.";
                // Obtengo su Id
                encargado_Id = consulta.getInt("Id");
            }
        } catch (SQLException e) {
            System.out.println("Error en encargadoYaExiste: "+e.getMessage());
        }
        return encontrado;
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
        estudiante_fechaNacimiento_dia = new javax.swing.JComboBox<>();
        estudiante_fechaNacimiento_mes = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        estudiante_fechaNacimiento_año = new javax.swing.JComboBox<>();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear nuevo estudiante");

        jLabel11.setText("Municipio:");

        jLabel17.setText("Municipio:");

        estudiante_municipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Almolonga", "Cabrican", "Cajolá", "Cantel", "Coatepeque", "Colomba Costa Cuca", "Concepción Chiquirichapa", "El Palmar", "Flores Costa Cuca", "Génova Costa Cuca", "Huitán", "La Esperanza", "Olintepeque", "Palestina de los Altos", "Quetzaltenango", "Salcajá", "San Carlos Sija", "San Francisco La Unión", "San Juan Ostuncalco", "San Martín Sacatepéquez", "San Mateo", "San Miguel Sigüilá", "Sibilia", "Zunil" }));

        encargado_municipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Almolonga", "Cabrican", "Cajolá", "Cantel", "Coatepeque", "Colomba Costa Cuca", "Concepción Chiquirichapa", "El Palmar", "Flores Costa Cuca", "Génova Costa Cuca", "Huitán", "La Esperanza", "Olintepeque", "Palestina de los Altos", "Quetzaltenango", "Salcajá", "San Carlos Sija", "San Francisco La Unión", "San Juan Ostuncalco", "San Martín Sacatepéquez", "San Mateo", "San Miguel Sigüilá", "Sibilia", "Zunil" }));

        estudiante_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));

        estudiante_capacidad_diferente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));

        crear_estudiante.setText("Crear");
        crear_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_estudianteActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        estudiante_fechaNacimiento_dia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia" }));

        estudiante_fechaNacimiento_mes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        estudiante_fechaNacimiento_mes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estudiante_fechaNacimiento_mesItemStateChanged(evt);
            }
        });

        jLabel12.setText("INFORMACIÓN DEL ESTUDIANTE:");

        estudiante_fechaNacimiento_año.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Año", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        jLabel13.setText("Dirección:");

        jLabel4.setText("Apellidos:");

        jLabel14.setText("Relación con el estudiante:");

        jLabel5.setText("Dirección:");

        jLabel15.setText("Celular:");

        jLabel6.setText("Fecha de Nacimiento:");

        jLabel16.setText("Trabajo u oficio:");

        jLabel7.setText("Sexo:");

        jLabel20.setText("INFORMACIÓN DEL ENCARGADO:");

        jLabel8.setText("Comunidad étnica:");

        jLabel21.setText("DPI:");

        jLabel9.setText("Capacidad Diferente:");

        jLabel22.setText("Teléfono de casa:");

        jLabel10.setText("Tipo capacidad:");

        jLabel23.setText("Nombre Completo:");

        jLabel24.setText("Fecha de Nacimiento:");

        jLabel1.setText("Codigo Personal:");

        jLabel2.setText("CUI:");

        jLabel3.setText("Nombres:");

        crear_asignacion.setText("Asignar Estudiante");
        crear_asignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_asignacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(277, 277, 277)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(crear_asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(crear_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(366, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(estudiante_cui, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(estudiante_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(estudiante_apellidos, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(estudiante_nombres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(estudiante_capacidad_diferente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(16, 16, 16)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel7))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(estudiante_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(83, 83, 83)
                            .addComponent(jLabel12)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel17)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel24)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(encargado_nombre_completo)
                                .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(encargado_direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(83, 83, 83)
                            .addComponent(jLabel20))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel22)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(encargado_trabajo)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(encargado_relacion_con_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(encargado_telefono_casa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                            .addComponent(encargado_celular, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addGap(0, 78, Short.MAX_VALUE)))))
                    .addContainerGap(91, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(estudiante_fechaNacimiento_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(estudiante_fechaNacimiento_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(estudiante_fechaNacimiento_año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(estudiante_cui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(estudiante_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(estudiante_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(estudiante_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(encargado_relacion_con_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(encargado_telefono_casa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crear_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crear_asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_fechaNacimiento_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estudiante_fechaNacimiento_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estudiante_fechaNacimiento_año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
            mensaje_error_estudiante = mensaje_error_encargado = "";
            sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cEncargadoId = null;

            // Primero evalúo si el estudiante ya existe
            if (estudianteYaExiste() == false) {
                // Evaluo si el encargado ya existe
                if (encargadoYaExiste() == false) {
                    String nuevoEncargado = "INSERT INTO Encargado(Nombre, Direccion, DPI, FechaNacimiento, Relacion, Trabajo, Municipio_Id) VALUES (";
                    // Datos del encargado
                    nuevoEncargado+= "'"+encargado_nombre_completo.getText()+"',";
                    nuevoEncargado+= "'"+encargado_direccion.getText()+"',";
                    nuevoEncargado+= "'"+encargado_dpi.getText()+"',";
                    nuevoEncargado+= "'"+encargado_fechaNacimiento.getText()+"',";
                    nuevoEncargado+= "'"+encargado_relacion_con_estudiante.getText()+"',";
                    nuevoEncargado+= "'"+encargado_trabajo.getText()+"',";
                    nuevoEncargado+= ""+Integer.toString(encargado_municipio.getSelectedIndex()+1)+")";
                    // En caso de no actualizar la tabla automáticamente, obtengo el Id del nuevo encagado que es igual
                    // a COUNT(*). Esta consulta siempre devolverá un registro
                    cEncargadoId = sentencia.executeQuery("SELECT COUNT(*) Cantidad FROM Encargado");
                    cEncargadoId.next();
                    encargado_Id = cEncargadoId.getInt("Cantidad") + 1; // El nuevo encargado tendrá Id COUNT(*) + 1

                    insercion = conexion.prepareStatement(nuevoEncargado);
                    insercion.executeUpdate();
                    cEncargadoId.close();
                } else {
                    // Si el encargado ya existe, ya obtuve su Id
                    JOptionPane.showMessageDialog(this, mensaje_error_encargado, "Información", JOptionPane.INFORMATION_MESSAGE, null);
                }

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
                if (estudiante_capacidad_diferente.getSelectedIndex() == 0) {
                    nuevoEstudiante+= ""+false+",";
                    nuevoEstudiante+= "NULL,";
                } else {
                    nuevoEstudiante+= ""+true+",";
                    nuevoEstudiante+= "'"+estudiante_tipo_capacidad.getText()+"',";
                }
                nuevoEstudiante+= ""+Integer.toString(estudiante_municipio.getSelectedIndex()+1)+",";
                nuevoEstudiante+= ""+encargado_Id+")";
                insercion = conexion.prepareStatement(nuevoEstudiante);
                insercion.executeUpdate();

                sentencia.close();
                crear_asignacion.setEnabled(true);
                estudianteCreado = true;
            } else {
                JOptionPane.showMessageDialog(this, mensaje_error_estudiante, "Error", JOptionPane.ERROR_MESSAGE, null);
                crear_asignacion.setEnabled(false);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_crear_estudianteActionPerformed

    private void estudiante_fechaNacimiento_mesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estudiante_fechaNacimiento_mesItemStateChanged
        int itemMes = estudiante_fechaNacimiento_mes.getSelectedIndex();
        int itemDia = estudiante_fechaNacimiento_dia.getSelectedIndex();
        if (itemMes > 0) {
            estudiante_fechaNacimiento_dia.removeAllItems();
            estudiante_fechaNacimiento_dia.addItem("Dia");
            if ((itemMes == 1) || (itemMes == 3) || (itemMes == 5) || (itemMes == 7) || (itemMes == 8) || (itemMes == 10) || itemMes == 12)
            for (int i=1; i<32; i++)
            estudiante_fechaNacimiento_dia.addItem(Integer.toString(i));
            else if ((itemMes == 4) || (itemMes == 6) || (itemMes == 9) || (itemMes == 11))
            for (int i=1; i<31; i++)
            estudiante_fechaNacimiento_dia.addItem(Integer.toString(i));
            else
            for (int i=1; i<29; i++)
            estudiante_fechaNacimiento_dia.addItem(Integer.toString(i));
        }
        if (itemDia <= itemMes)
        estudiante_fechaNacimiento_dia.setSelectedIndex(itemDia);
        else
        estudiante_fechaNacimiento_dia.setSelectedIndex(estudiante_fechaNacimiento_dia.getItemCount()-1);
    }//GEN-LAST:event_estudiante_fechaNacimiento_mesItemStateChanged

    private void crear_asignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_asignacionActionPerformed
        /* Esta opción se habilita sí y solo si el estudiante aún no existía y ya fué creado. Tengo su Id en 'estudiante_Id'.
           Desde aquí, no es necesario comprobar si la Asignación ya existe (pues el estudiante acaba de ser creado, y por lo
           tanto no tiene asignación).*/
        // Le paso el Id del estudiante recién creado, para que pueda realizarse su asignación
        AsignarEstudiante nueva_ventana = new AsignarEstudiante(new javax.swing.JFrame(), true, conexion, estudiante_Id);
        nueva_ventana.setVisible(true);
    }//GEN-LAST:event_crear_asignacionActionPerformed

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
    private javax.swing.JComboBox<String> estudiante_fechaNacimiento_año;
    private javax.swing.JComboBox<String> estudiante_fechaNacimiento_dia;
    private javax.swing.JComboBox<String> estudiante_fechaNacimiento_mes;
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
