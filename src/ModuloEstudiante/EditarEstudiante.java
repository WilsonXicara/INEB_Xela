/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Similar a la clase CrearEstudiante. Esta clase se utiliza para modificar los datos de un Estudiante, así como la de su
 * Encargado. El único dato que no se puede modificar de un Estudiante es su Código Personal (campo único para cada Estudiante).
 * También puede modificarse los datos del Encargado (incluso cambiar el Encargado del Estudiante).
 * Al editar un estudiante, no se puede cambiar su Código Personal. Todo lo demás puede ser editado.
 * Se puede cambiar de encargado, incluso crear un nuevo registro Encargado.
 * @author Wilson Xicará
 */
public class EditarEstudiante extends javax.swing.JDialog {
    private Connection conexion;
    private PreparedStatement insercion;
    private RegistroEstudiante estudiante;
    private RegistroEncargado regEncargado, nuevoEncargado;
    private int estudiante_Id, encargado_Id;
    /**
     * Creates new form EditarEstudiante
     */
    public EditarEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**APROBADO!!!
     * Constructor para inicializar una nueva ventana para editar los datos de un Estudiante y/o su Encargado.
     * @param parent
     * @param modal
     * @param conexion objeto que permite la conexión y comunicación con la Base de Datos.
     * @param estudiante objeto que contiene toda la información del RegistroEstudiante que se va a editar.
     */
    public EditarEstudiante(java.awt.Frame parent, boolean modal, Connection conexion, RegistroEstudiante estudiante) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        this.estudiante = estudiante;
        this.setTitle("Editar información de"+((estudiante.getSexo().equals("F"))?" la":"l")+" Estudiante: "+estudiante.getNombres()+" "+estudiante.getApellidos());
        
        // Extraigo el listado de Municipios almacenados en la Base de Datos y los inserto en los JComboBox correspondiente
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cMunicipios = sentencia.executeQuery("SELECT Nombre FROM Municipio");
            while(cMunicipios.next()) {
                estudiante_municipio.addItem(cMunicipios.getString("Nombre"));
                encargado_municipio.addItem(cMunicipios.getString("Nombre"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar obtener los Municipios desde la Base de Datos:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(EditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* En 'estudiante' tengo una referencia del DPI de su encargado, ya que en InformacionEstudiante.extraer_y_mostrar_registros()
           almaceno la información del Encargado como nuevo.setNombreEncargado('_nombre' '(_DPI)')*/
        String DPIEncargado = estudiante.getNombreEncargado().substring(estudiante.getNombreEncargado().length()-14, estudiante.getNombreEncargado().length()-1);
        System.out.println("DPI Encargado = "+DPIEncargado);
        regEncargado = extraer_encargado(DPIEncargado);
        
        cargar_encargado_en_campos(false);
        cargar_estudiante_en_campos();
    }
    /**APROBADO!!!
     * Método que extrae de la Base de Datos correspondiente al Encargado del Estudiante que se va a editar, y se guarda en
     * RegistroEncargado.
     * @param DPIEncargado 
     * @return el objeto con los datos del Encargado que se extrajo de la Base de Datos.
     */
    private RegistroEncargado extraer_encargado(String DPIEncargado) {
        RegistroEncargado encargado = new RegistroEncargado();
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cEncargado = sentencia.executeQuery("SELECT * FROM Encargado WHERE DPI = '"+DPIEncargado+"'");
            if (cEncargado.next()) {    // La consulta devuelve un registro
                // Inicio la Extracción de los datos del Encargado
                encargado.setID(cEncargado.getInt("Id"));
                encargado.setNombre(cEncargado.getString("Nombre"));
                encargado.setDireccion(cEncargado.getString("Direccion"));
                encargado.setDPI(DPIEncargado);
                encargado.setFechaNacimiento(cEncargado.getString("FechaNacimiento"));
                encargado.setMunicipioId(cEncargado.getInt("Municipio_Id") - 1);
                encargado.setTrabajo(cEncargado.getString("Trabajo"));
                // Extraigo el Teléfono y el celular del Encargado
                ResultSet cTelefono = sentencia.executeQuery("SELECT Telefono FROM Telefono WHERE Encargado_Id = "+DPIEncargado+"");
                if (cTelefono.next()) encargado.setTelefono(cTelefono.getString("Telefono"));
                if (cTelefono.next()) encargado.setCelular(cTelefono.getString("Telefono"));
                encargado.setEstaEnBD(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se ha encontrado un registro\ncon DPI = '"+DPIEncargado+"'"
                                + "\nSi desea, ingrese los datos para un nuevo registro"
                                + "\ny seleccione GuardarEncargado", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al obtener registro del Encargado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return encargado;
    }
    /**APROBADO!!!
     * Método que carga los datos de RegistroEstudiante y RegistroEncargado a los campos que corresponden.
     */
    private void cargar_estudiante_en_campos() {
        /* Inicio de la carga de los Datos del Estudiante a los campos correspondientes */
        estudiante_codigo_personal.setText(estudiante.getCodigoPersonal());
        estudiante_cui.setText(estudiante.getCUI());
        estudiante_nombres.setText(estudiante.getNombres());
        estudiante_apellidos.setText(estudiante.getApellidos());
        estudiante_relacion_encargado.setText(estudiante.getRelacionEncargado());
        estudiante_direccion.setText(estudiante.getDireccion());
        estudiante_municipio.setSelectedIndex(estudiante.getMunicipioId());
        estudiante_fechaNacimiento.setText(estudiante.getFechaNacimiento());
        estudiante_sexo.setSelectedIndex(("F".equals(estudiante.getSexo())) ? 0: 1);
        estudiante_etnia.setText(estudiante.getEtnia());
        estudiante_capacidad_diferente.setSelectedIndex(!(estudiante.isCapacidadDiferente()) ? 0 : 1);
        estudiante_tipo_capacidad.setText((estudiante.isCapacidadDiferente()) ? estudiante.getTipoCapacidad() : "");
        // Hasta aquí se garantiza la carga de los datos a los campos que corresponden
    }
    /**APROBADO!!!
     * Método que carga los datos de RegistroEstudiante y RegistroEncargado a los campos que corresponden.
     */
    private void cargar_encargado_en_campos(boolean camposEditables) {
        /* Inicio la carga de los Datos del Encargado relacionado al Estudiante, a los campos correspondientes */
        encargado_dpi.setText(regEncargado.getDPI());
        encargado_nombre_completo.setText(regEncargado.getNombre());
        encargado_direccion.setText(regEncargado.getDireccion());
        encargado_municipio.setSelectedIndex(regEncargado.getMunicipioId());
        encargado_fechaNacimiento.setText(regEncargado.getFechaNacimiento());
        encargado_telefono_casa.setText(regEncargado.getTelefono());
        encargado_celular.setText(regEncargado.getCelular());
        encargado_trabajo.setText(regEncargado.getTrabajo());
        setEditable_campos_encargado(camposEditables);
        
        // Hasta aquí se garantiza la carga de los datos a los campos que corresponden
    }
    /**ÚTIL!!!
     * 
     * @param valorEditable 
     */
    private void setEditable_campos_encargado(boolean valorEditable) {
        encargado_dpi.setEditable(valorEditable);
        encargado_nombre_completo.setEditable(valorEditable);
        encargado_direccion.setEditable(valorEditable);
        encargado_municipio.setEnabled(valorEditable);
        encargado_fechaNacimiento.setEditable(valorEditable);
        estudiante_relacion_encargado.setEditable(valorEditable);
        encargado_telefono_casa.setEditable(valorEditable);
        encargado_celular.setEditable(valorEditable);
        encargado_trabajo.setEditable(valorEditable);
    }
    /**ÚTIL!!!
     * 
     */
    private void limpiar_campos_encargado() {
        encargado_dpi.setText("");
        encargado_nombre_completo.setText("");
        encargado_direccion.setText("");
        encargado_municipio.setSelectedIndex(-1);
        encargado_fechaNacimiento.setText("");
        estudiante_relacion_encargado.setText("");
        encargado_telefono_casa.setText("");
        encargado_celular.setText("");
        encargado_trabajo.setText("");
    }
    /**APROBADO!!!
     * Método que verifica que los datos para un nuevo registro Encargado sean los correctos. Si hay algún dato nulo o incorrecto
     * lanza una 'ExcepcionDatosIncorrectos '.
     * @throws ExcepcionDatosIncorrectos 
     */
    private void validar_datos_encargado(boolean validarExistencia) throws ExcepcionDatosIncorrectos {
        // Verificación de la validez del DPI
        if (!Pattern.compile("[0-9]{13}").matcher(encargado_dpi.getText()).matches())
            throw new ExcepcionDatosIncorrectos("El texto para el DPI es incorrecto");
        // Compruebo que el DPI no exista en la Base de Datos
        if (validarExistencia) {
            try {
                Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                ResultSet cEncargado = sentencia.executeQuery("SELECT * FROM Encargado WHERE DPI = '"+encargado_dpi.getText()+"'");
                if (cEncargado.next())
                    throw new ExcepcionDatosIncorrectos("El DPI ya existe en la Base de Datos");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al intentar validar datos.\n"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(EditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ("".equals(encargado_nombre_completo.getText()))
            throw new ExcepcionDatosIncorrectos("El nombre no puede ser nulo");
        if ("".equals(encargado_direccion.getText()))
            throw new ExcepcionDatosIncorrectos("No se ha especificado la Dirección");
        if ("".equals(encargado_fechaNacimiento.getText()))
            throw new ExcepcionDatosIncorrectos("Especifique la Fecha de nacimiento");
        if ("".equals(estudiante_relacion_encargado.getText()))
            throw new ExcepcionDatosIncorrectos("Especifique la relación con el estudiante");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_estudiante = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        estudiante_nombres = new javax.swing.JTextField();
        estudiante_apellidos = new javax.swing.JTextField();
        estudiante_direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        estudiante_tipo_capacidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        estudiante_codigo_personal = new javax.swing.JTextField();
        estudiante_cui = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        estudiante_fechaNacimiento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        estudiante_municipio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        estudiante_sexo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        estudiante_etnia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        estudiante_capacidad_diferente = new javax.swing.JComboBox<>();
        panel_encargado = new javax.swing.JPanel();
        encargado_celular = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        encargado_dpi = new javax.swing.JTextField();
        encargado_nombre_completo = new javax.swing.JTextField();
        encargado_fechaNacimiento = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        encargado_direccion = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        encargado_trabajo = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        encargado_municipio = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        estudiante_relacion_encargado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        encargado_telefono_casa = new javax.swing.JTextField();
        buscar_dpi_encargado = new javax.swing.JButton();
        guardar_encargado = new javax.swing.JButton();
        panel_botones = new javax.swing.JPanel();
        cambiar_encargado = new javax.swing.JButton();
        editar_encargado = new javax.swing.JButton();
        guardar_cambios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 500));

        panel_estudiante.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Codigo Personal:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("CUI:");

        estudiante_nombres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        estudiante_apellidos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        estudiante_direccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Capacidad Diferente:");

        estudiante_tipo_capacidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Tipo capacidad:");

        estudiante_codigo_personal.setEditable(false);
        estudiante_codigo_personal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        estudiante_cui.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("INFORMACIÓN DEL ESTUDIANTE:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nombres:");

        estudiante_fechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Apellidos:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Municipio:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Dirección:");

        estudiante_municipio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento:");

        estudiante_sexo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        estudiante_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Sexo:");

        estudiante_etnia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Comunidad étnica:");

        estudiante_capacidad_diferente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        estudiante_capacidad_diferente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));

        javax.swing.GroupLayout panel_estudianteLayout = new javax.swing.GroupLayout(panel_estudiante);
        panel_estudiante.setLayout(panel_estudianteLayout);
        panel_estudianteLayout.setHorizontalGroup(
            panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_estudianteLayout.createSequentialGroup()
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_estudianteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(estudiante_cui, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_estudianteLayout.createSequentialGroup()
                                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estudiante_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(estudiante_apellidos, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(estudiante_nombres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estudiante_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panel_estudianteLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(estudiante_capacidad_diferente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_estudianteLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_estudianteLayout.setVerticalGroup(
            panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_estudianteLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_cui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_capacidad_diferente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_encargado.setBackground(new java.awt.Color(153, 153, 255));

        encargado_celular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Fecha de Nacimiento:");

        encargado_dpi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        encargado_nombre_completo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        encargado_fechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("DPI:");

        encargado_direccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Teléfono de casa:");

        encargado_trabajo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Nombre Completo:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Dirección:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Relación con el estudiante:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Municipio:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Celular:");

        encargado_municipio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Trabajo u oficio:");

        estudiante_relacion_encargado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("INFORMACIÓN DEL ENCARGADO:");

        encargado_telefono_casa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        buscar_dpi_encargado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar_dpi_encargado.setText("Buscar...");
        buscar_dpi_encargado.setEnabled(false);
        buscar_dpi_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_dpi_encargadoActionPerformed(evt);
            }
        });

        guardar_encargado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guardar_encargado.setText("Guardar Encargado");
        guardar_encargado.setEnabled(false);
        guardar_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_encargadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_encargadoLayout = new javax.swing.GroupLayout(panel_encargado);
        panel_encargado.setLayout(panel_encargadoLayout);
        panel_encargadoLayout.setHorizontalGroup(
            panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23)
                    .addComponent(jLabel21)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel22)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_encargadoLayout.createSequentialGroup()
                        .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_encargadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(229, 229, 229))
                    .addGroup(panel_encargadoLayout.createSequentialGroup()
                        .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_encargadoLayout.createSequentialGroup()
                                .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))
                            .addGroup(panel_encargadoLayout.createSequentialGroup()
                                .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscar_dpi_encargado))
                            .addGroup(panel_encargadoLayout.createSequentialGroup()
                                .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(guardar_encargado))
                            .addComponent(estudiante_relacion_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(encargado_telefono_casa, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panel_encargadoLayout.setVerticalGroup(
            panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_encargadoLayout.createSequentialGroup()
                .addComponent(jLabel20)
                .addGap(4, 4, 4)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar_dpi_encargado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_relacion_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_telefono_casa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(guardar_encargado))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        panel_botones.setBackground(new java.awt.Color(153, 153, 255));

        cambiar_encargado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cambiar_encargado.setText("Cambiar de Encargado");
        cambiar_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiar_encargadoActionPerformed(evt);
            }
        });

        editar_encargado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editar_encargado.setText("Editar datos del Encargado");
        editar_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_encargadoActionPerformed(evt);
            }
        });

        guardar_cambios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guardar_cambios.setText("Guardar y Salir");
        guardar_cambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_cambiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_botonesLayout = new javax.swing.GroupLayout(panel_botones);
        panel_botones.setLayout(panel_botonesLayout);
        panel_botonesLayout.setHorizontalGroup(
            panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botonesLayout.createSequentialGroup()
                .addGap(396, 396, 396)
                .addGroup(panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_botonesLayout.createSequentialGroup()
                        .addComponent(cambiar_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editar_encargado))
                    .addGroup(panel_botonesLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(guardar_cambios, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(377, Short.MAX_VALUE))
        );
        panel_botonesLayout.setVerticalGroup(
            panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cambiar_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(guardar_cambios, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 572, Short.MAX_VALUE))
            .addComponent(panel_botones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**APROBADO!!!
     * Acción que guarda los cambios realizados en el RegistroEstudiante, siempre que el encargado (y sus posibles cambios) ya
     * estén el la Base de Datos. Al guardar los cambios correctamente inhabilita todos los botones para que no se tenga otra
     * opción que cerrar la ventana.
     * @param evt 
     */
    private void guardar_cambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_cambiosActionPerformed
        /* Modificación de los datos de un Estudiante.
        La modificación de un registro Estudiante consiste en lo siguiente:
        - Todo estudiante tiene un CodigoPersonal único, por lo que este dato no puede modificarse.
        - Si no hay un encargado en campos, no se puede guardar los cambios. */
        if (regEncargado.getID() != 0) {    // Hay un encargado asignado
            try {
                // El nuevo encargado ya ha sido escrito en la Base de Datos. Sólo queda actualizar los datos del Estudiante
                String actualizarEstudiante = "UPDATE Estudiante SET ";
                actualizarEstudiante+= "CUI = '"+estudiante_cui.getText()+"', ";
                actualizarEstudiante+= "Nombres = '"+estudiante_nombres.getText()+"', ";
                actualizarEstudiante+= "Apellidos = '"+estudiante_apellidos.getText()+"', ";
                actualizarEstudiante+= "FechaNacimiento = '"+estudiante_fechaNacimiento.getText()+"', ";
                actualizarEstudiante+= "Direccion = '"+estudiante_direccion.getText()+"', ";
                actualizarEstudiante+= "Sexo = '"+((estudiante_sexo.getSelectedIndex()==0) ? "F" : "M")+"', ";
                actualizarEstudiante+= "Etnia = '"+estudiante_etnia.getText()+"', ";
                actualizarEstudiante+= "CapacidadDiferente = "+(estudiante_capacidad_diferente.getSelectedIndex()==1)+", ";
                actualizarEstudiante+= "TipoCapacidad = "+((estudiante_capacidad_diferente.getSelectedIndex()==1) ? "'"+estudiante_tipo_capacidad.getText()+"', " : "NULL, ");
                actualizarEstudiante+= "Municipio_Id = "+(estudiante_municipio.getSelectedIndex()+1)+", ";
                actualizarEstudiante+= "Encargado_Id = "+regEncargado.getID()+", ";
                actualizarEstudiante+= "RelacionEncargado = '"+estudiante_relacion_encargado.getText()+"' ";
                actualizarEstudiante+= "WHERE Id = "+estudiante.getID()+"";
                System.out.println("Actualizar = "+actualizarEstudiante);
                // Actualización del registro Estudiante en la Base de Datos
                conexion.prepareStatement(actualizarEstudiante).executeUpdate();
                
                guardar_cambios.setEnabled(false);
                cambiar_encargado.setEnabled(false);
                editar_encargado.setEnabled(false);
                buscar_dpi_encargado.setEnabled(false);
                guardar_encargado.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Los nuevos datos se guardaron exitosamente.", "Cambio exitoso.", JOptionPane.INFORMATION_MESSAGE);
                
                // Hasta aquí, se garantiza la actulaización del registro Estudiante
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al intentar actualizar los datos:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(EditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
            JOptionPane.showMessageDialog(this, "No se tiene asignado un encargado.\nPor favor, corriga dicho error.", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_guardar_cambiosActionPerformed
    /**APROBADO!!!
     * Acción que permite modificar los datos de regEncargado (debido a cambiar el Encargado relacionado con el Estudiante en
     * campos). Lanza un mensaje y pide confirmación.
     * @param evt 
     */
    private void cambiar_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiar_encargadoActionPerformed
        String[] opciones = new String[]{"Si", "No", "Cancelar"};
        int opcion = JOptionPane.showOptionDialog(this,
                "Si cambia el Encargado relacionado con el"
                + "\nestudiante se perderá la referencia al"
                + "\nencargado actual."
                + "\nAl estudiante se le asignará el último"
                + "\nencargado mostrado."
                + "\nDesea cambiar de Encargado?"
                + "\n\nTome en cuenta que puede volver a encontrar"
                + "\ndicha referencia después de una Búsqueda del DPI.", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        // Si se quiere cambiar de encargado
        if (opcion == JOptionPane.YES_OPTION) {
            limpiar_campos_encargado();
            setEditable_campos_encargado(true);
            regEncargado.setID(0);     // Indicador de que el Encargado se cambiará (se espera la selección de otro)
            buscar_dpi_encargado.setEnabled(true);
        }
    }//GEN-LAST:event_cambiar_encargadoActionPerformed
    /**APROBADO!!!
     * Acción que habilita la edición de los datos del Encargado mostrados actualmente, siempre que ya exista en la Base de Datos.
     * @param evt 
     */
    private void editar_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_encargadoActionPerformed
        if (regEncargado.getID() != 0) {
            if ("Editar datos del Encargado".equals(editar_encargado.getText())) {  // Se habilitará la Edición de datos del Encargado mostrado
                editar_encargado.setText("Guardar Cambios");
                setEditable_campos_encargado(true);
                encargado_dpi.setEditable(false);   // El DPI no puede cambiarse
                buscar_dpi_encargado.setEnabled(false);     // Bloque los otros botones para no generar errores
                guardar_encargado.setEnabled(false);
                cambiar_encargado.setEnabled(false);
                guardar_cambios.setEnabled(false);
            } else {    // Se guardarán los datos actualizados del Encargado
                try {
                    validar_datos_encargado(false);  // 'false' indica que no se verifica si el DPI ya existe
                    
                    /* Actualizo los nuevos datos en regEncargado */
                    // El DPI sigue siendo el mismo
                    regEncargado.setNombre(encargado_nombre_completo.getText());
                    regEncargado.setDireccion(encargado_direccion.getText());
                    regEncargado.setMunicipioId(encargado_municipio.getSelectedIndex());
                    regEncargado.setFechaNacimiento(encargado_fechaNacimiento.getText());
                    estudiante.setRelacionEncargado(estudiante_relacion_encargado.getText());
                    regEncargado.setTelefono(encargado_telefono_casa.getText());
                    regEncargado.setCelular(encargado_celular.getText());
                    regEncargado.setTrabajo(encargado_trabajo.getText());
                    
                    /* Ahora actualizo dichos datos en la Base de Datos */
                    String actualizarEncargado = "UPDATE Encargado SET ";
                    actualizarEncargado+= "Nombre = '"+regEncargado.getNombre()+"', ";
                    actualizarEncargado+= "Direccion = '"+regEncargado.getDireccion()+"', ";
                    actualizarEncargado+= "Municipio_Id = "+(regEncargado.getMunicipioId()+1)+", ";
                    actualizarEncargado+= "FechaNacimiento = '"+regEncargado.getFechaNacimiento()+"', ";
                    actualizarEncargado+= "Trabajo = '"+regEncargado.getTrabajo()+"', ";
                    actualizarEncargado+= "WHERE Id = "+regEncargado.getID()+"";
                    System.out.println("ActualizarEncargado = "+actualizarEncargado);
                    conexion.prepareStatement(actualizarEncargado).executeUpdate();
                    
                    // Para actualizar los teléfonos, debo hacer una consulta a la tabla de Telefono
                    Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    ResultSet cTelefono = sentencia.executeQuery("SELECT Id, Telefono FROM Telefono WHERE Encargado_Id = "+regEncargado.getID()+"");
                    int idTelefono = 0, idCelular = 0;
                    if (cTelefono.next()) idTelefono = cTelefono.getInt("Id");
                    if (cTelefono.next()) idCelular = cTelefono.getInt("Id");
                    String instruccion = "";
                    if (Pattern.compile("\\d{8}").matcher(encargado_telefono_casa.getText()).matches()) {
                        if (idTelefono == 0) {  // Actulamente no tiene teléfono, lo creo
                            instruccion = "INSERT INTO Telefono(Telefono, Encargado_Id) VALUES('"+regEncargado.getTelefono()+"', "+regEncargado.getID()+")";
                            System.out.println("Crear Telefono = "+instruccion);
                        } else {    // Tiene teléfono, lo actualizo
                            instruccion = "UPDATE Telefono SET Telefono = '"+regEncargado.getTelefono()+"' WHERE Id = "+idTelefono+"";
                            System.out.println("Actualizar Telefono = "+instruccion);
                        }
                        conexion.prepareStatement(instruccion).executeUpdate();
                    }
                    if (Pattern.compile("\\d{8}").matcher(encargado_celular.getText()).matches()) {
                        if (idCelular == 0) {  // Actulamente no tiene celular, lo creo
                            instruccion = "INSERT INTO Telefono(Telefono, Encargado_Id) VALUES('"+regEncargado.getCelular()+"', "+regEncargado.getID()+")";
                            System.out.println("Crear Celular = "+instruccion);
                        } else {    // Tiene celular, lo actualizo
                            instruccion = "UPDATE Telefono SET Telefono = '"+regEncargado.getCelular()+"' WHERE Id = "+idCelular+"";
                            System.out.println("Actualizar Celular = "+instruccion);
                        }
                        conexion.prepareStatement(instruccion).executeUpdate();
                    }
                    
                    // Hasta aquí se garantiza la actualización de los datos del Encargado mostrado
                    setEditable_campos_encargado(false);
                    buscar_dpi_encargado.setEnabled(true);     // Habilito los botones bloqueados anteriormente
                    guardar_encargado.setEnabled(true);
                    cambiar_encargado.setEnabled(true);
                    guardar_cambios.setEnabled(true);
                    
                    editar_encargado.setText("Editar datos del Encargado"); // Cambio el nombre del botón para indicar que ya se guardaron los cambios
                } catch (ExcepcionDatosIncorrectos | SQLException ex) {
                    Logger.getLogger(EditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Error al intentar actualizar el registro del Encargado\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_editar_encargadoActionPerformed
    /**APROBADO!!!
     * Acción que verifica si el nuevo DPI ingresado pertenece a un registro existente. Si el registro ya existe en la BD
     * carga los datos en los campos para ser vistos; de lo contrario habilita la opción de crear un nuevo RegistroEncargado
     * @param evt 
     */
    private void buscar_dpi_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_dpi_encargadoActionPerformed
        String DPIBuscado = encargado_dpi.getText();
        // Evaluo la validez del texto DPI mediante una Expresión Regular
        if (Pattern.compile("[0-9]{13}").matcher(DPIBuscado).matches()) {
            /* Búsqueda del DPI en la tabla Encargado de la Base de Datos. */
            regEncargado = extraer_encargado(DPIBuscado);
            // Si encontrado.getId() != 0 entonces se encontró un registro en la Base de Datos
            if (regEncargado.getID() != 0) {
                cargar_encargado_en_campos(false);
            } else {    // No se encontró un registro que concuerde. Se debe crear el registro
                setEditable_campos_encargado(true);
                guardar_encargado.setEnabled(true);
            }
        } else
            JOptionPane.showMessageDialog(this, "El DPI no es válido.\nPor favor, verifique que no tenga espacios y que sea de 13 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_buscar_dpi_encargadoActionPerformed
    /**APROBADO!!!
     * Acción que permite crear un nuevo registro Encargado en la Base de Datos, con los valores existentes en los campos
     * de Encargado
     * @param evt 
     */
    private void guardar_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_encargadoActionPerformed
        /* La manera de determinar si el registro que se quiere agregar ya está en la Base de Datos es mediante el atributo
        'getID'. Si es diferente de cero, entonces dicho registro ya está en la BD; de lo contrario, el registro
        aún no existe en la BD. */
        try {
            validar_datos_encargado(true);
            // Obtengo lo que será el Id del Encargado que se agregará a la Base de Datos
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cEncargado = sentencia.executeQuery("SELECT MAX(Id) Maximo FROM Encargado");
            cEncargado.next();
            // Actualizo los datos del nuevo encargado en 'regEncargado'
            regEncargado.setID(cEncargado.getInt("Maximo") + 1);
            regEncargado.setDPI(encargado_dpi.getText());
            regEncargado.setNombre(encargado_nombre_completo.getText());
            regEncargado.setDireccion(encargado_direccion.getText());
            regEncargado.setMunicipioId(encargado_municipio.getSelectedIndex());
            regEncargado.setFechaNacimiento(encargado_fechaNacimiento.getText());
            regEncargado.setTelefono(encargado_telefono_casa.getText());
            regEncargado.setCelular(encargado_celular.getText());
            regEncargado.setTrabajo(encargado_trabajo.getText());
            
            // Ahora escribo los datos del nuevo encargado en la Base de Datos:
            String nuevoEncargado = "INSERT INTO Encargado(Nombre, Direccion, DPI, FechaNacimiento, Trabajo, Municipio_Id) VALUES (";
            // Datos del encargado
            nuevoEncargado+= "'"+regEncargado.getNombre()+"',";
            nuevoEncargado+= "'"+regEncargado.getDireccion()+"',";
            nuevoEncargado+= "'"+regEncargado.getDPI()+"',";
            nuevoEncargado+= "'"+regEncargado.getFechaNacimiento()+"',";
            nuevoEncargado+= "'"+regEncargado.getTrabajo()+"',";
            nuevoEncargado+= ""+(regEncargado.getMunicipioId()+1)+")";
            conexion.prepareStatement(nuevoEncargado).executeUpdate();  // Inserción del nuevo registro Encargado
            // De momento se crea nuevos teléfonos para cada nuevo Encargado
            if (!"".equals(regEncargado.getTelefono()))
                conexion.prepareStatement("INSERT INTO Telefono(Telefono, Encargado_Id) VALUES('"+regEncargado.getTelefono()+"', "+regEncargado.getID()+")").executeUpdate();
            if (!"".equals(regEncargado.getCelular()))
                conexion.prepareStatement("INSERT INTO Telefono(Telefono, Encargado_Id) VALUES('"+regEncargado.getCelular()+"', "+regEncargado.getID()+")").executeUpdate();
            // Hasta aquí se garantiza haber guardado todos los datos
            
        } catch (ExcepcionDatosIncorrectos | SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(EditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardar_encargadoActionPerformed

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
            java.util.logging.Logger.getLogger(EditarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarEstudiante dialog = new EditarEstudiante(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton buscar_dpi_encargado;
    private javax.swing.JButton cambiar_encargado;
    private javax.swing.JButton editar_encargado;
    private javax.swing.JTextField encargado_celular;
    private javax.swing.JTextField encargado_direccion;
    private javax.swing.JTextField encargado_dpi;
    private javax.swing.JTextField encargado_fechaNacimiento;
    private javax.swing.JComboBox<String> encargado_municipio;
    private javax.swing.JTextField encargado_nombre_completo;
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
    private javax.swing.JTextField estudiante_relacion_encargado;
    private javax.swing.JComboBox<String> estudiante_sexo;
    private javax.swing.JTextField estudiante_tipo_capacidad;
    private javax.swing.JButton guardar_cambios;
    private javax.swing.JButton guardar_encargado;
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
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel panel_encargado;
    private javax.swing.JPanel panel_estudiante;
    // End of variables declaration//GEN-END:variables
}