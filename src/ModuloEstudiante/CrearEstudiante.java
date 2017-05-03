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
import java.util.Date;
import java.util.GregorianCalendar;
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
    private int contadorIdEstudiantesEnBD, contadorIdEncargadosEnBD;
    private int idEncargadoEnBD;
    private int indexRegistroEncargadoEditado, indexRegistroEstudianteEditado;
    private DefaultTableModel modelEstudiantes, modelEncargados;
    private ArrayList<RegistroEstudiante> listaEstudiantes;
    private ArrayList<RegistroEncargado> listaEncargados;
    /**
     * Creates new form CrearEstudiante2
     */
    public CrearEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**APROBADO!!!
     * Inicializa la ventana con los campos requeridos para crear un nuevo Estudiante y agregarlo a la BD, así como la
     * información de su Encargado.
     * @param parent componente padre del nuevo JDialog a mostrar
     * @param modal modo de apertura. Si es 'true', no se permitirá trabajar sobre la ventana padre mientras este JDialog está abierto.
     * @param conexion objeto que permite la conexión y comunicación con la Base de Datos
     */
    public CrearEstudiante(java.awt.Frame parent, boolean modal, Connection conexion) {
        super(parent, modal);
        initComponents();
        modelEstudiantes = (DefaultTableModel) tabla_estudiantes.getModel();
        modelEncargados = (DefaultTableModel) tabla_encargados.getModel();
        this.conexion = conexion;   // Inicializo la conexión.
        // Obtengo los correlativos ID's de Estudiantes y Encargados que se creen temporalmente en las tablas
        try {
            Statement sentencia = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            // Obtengo el correlativo actual de los Id's de estudiantes en la BD (los nuevos serán mayores al actual mayor).
            ResultSet cEstudiante = sentencia.executeQuery("SELECT MAX(Id) Cantidad FROM Estudiante");
            cEstudiante.next();
            // Obtengo lo que será el inicio del correlativo de los Id's de los nuevos estudiantes
            contadorIdEstudiantesEnBD = cEstudiante.getInt("Cantidad");
            
            ResultSet cEncargado = sentencia.executeQuery("SELECT MAX(Id) Cantidad FROM Encargado");
            cEncargado.next();
            // Obtengo el correlativo actual de los Id's de encargados en la BD (los nuevos serán mayores al actual mayor).
            contadorIdEncargadosEnBD = cEncargado.getInt("Cantidad");
            cEstudiante.close();
            cEncargado.close();
            sentencia.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar conectarse a la Base de Datos.\n"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargar_municipios();/**
        DefaultTableModel model = new DefaultTableModel();
        JComboBox comboBox = new JComboBox(new String[]{"ITEM 1","ITEM 2", "ITEM 3"});
        DefaultCellEditor defaultCellEditor=new DefaultCellEditor(comboBox);
        tabla_estudiantes.getColumnModel().getColumn(2).setCellEditor(defaultCellEditor);
        **/
        this.setLocationRelativeTo(null);   // Para centrar esta ventana sobre la pantalla.
        listaEncargados = new ArrayList<>();
        listaEstudiantes = new ArrayList<>();
        
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
        encargado_fechaNacimiento.getJCalendar().setWeekOfYearVisible(false);
        definir_ancho_columnas();   // Se define el ahcho de columnas en base a valores obtenidos previamente por pruebas
    }
    /**APROBADO!!!
     * Método que obtiene el listado de los Municipios almacenados en la Base de Datos para insertarlos en los JComboBox
     * que corresponden. Sólo se obtiene los nombres más no los Id's ya que son correlativos entre ambos.
     */
    private void cargar_municipios() {
        estudiante_municipio.removeAllItems();
        encargado_municipio.removeAllItems();
        try {
            Statement consulta = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet municipios = consulta.executeQuery("SELECT Nombre FROM Municipio");
            while (municipios.next()) {
                estudiante_municipio.addItem(municipios.getString("Nombre"));
                encargado_municipio.addItem(municipios.getString("Nombre"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar obtener los Municipios\n"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**APROBADO!!!
     * Función que evalua si el nuevo estudiante ya existe en la Base de Datos. Para ello, busca una coincidencia de
     * 'CodigoPersonal' de nuevo en la Tabla Estudiante (que contiene los datos temporales de los nuevos estudiantes), y
     * también lo busca (de ser necesario) en la Base de Datos (ya que este campo es único para cada estudiante). En ambos
     * casos, si encuentra una coincidencia retorna 'true' para no crear un nuevo registro.
     * @return 'true' si 'CodigoPersonal' se repite (en la Tabla o en la BD) o si tiene formato incorrecto; 'false' en caso contrario.
     */
    private boolean estudianteYaExiste() {
        boolean encontrado = false;
        String codigoPersonal = estudiante_codigo_personal.getText();
        if (Pattern.compile("\\D\\d{3}\\D{3}").matcher(codigoPersonal).matches()) {
            // Hago una búsqueda en la Tabla de Estudiantes en busca del Código Personal
            int tamaño = listaEstudiantes.size();
            for(int i=0; i<tamaño; i++) {
                if (codigoPersonal.equals(listaEstudiantes.get(i).getCodigoPersonal())) {
                    encontrado = true;
                    tabla_estudiantes.setRowSelectionInterval(i, i);
                    JOptionPane.showMessageDialog(this, "Ya existe un registro con el mismo Código Personal en la fila "+(i+1)+" de la Tabla de Estudiantes", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    i = tamaño;
                }
            }
            if (!encontrado) {  // Si no lo encuentra en la Tabla de registros temporales, lo busco en la Base de Datos
                // Hago una consulta a la tabla Estudiante de la BD en busca de alguna coincidencia del CodigoPersonal
                try {
                    Statement consulta = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    ResultSet cEstudiante = consulta.executeQuery("SELECT CodigoPersonal, Nombres, Apellidos FROM Estudiante "
                            + "WHERE CodigoPersonal = '"+codigoPersonal+"'");
                    encontrado = cEstudiante.next();
                    // Si la consulta regresa por lo menos un registro, consulta.next() es true, de lo contrario es false (no existe).
                    if (encontrado) {
                        JOptionPane.showMessageDialog(this, "Ya existe un estudiante con el mismo Código Personal:"
                                + "\n\tCodigo Personal: "+cEstudiante.getString("CodigoPersonal")
                                + "\n\tNombres:         "+cEstudiante.getString("Nombres")
                                + "\n\tApellidos:       "+cEstudiante.getString("Apellidos"), "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al consultar la tabla Estudiante\n"+e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "El Código Personal es incorrecto."
                    + "\n\nEl Código personal consiste de\n1 caracter, 3 dígitos y 3 caracteres (sin espacios).\n\nNo puede ser nulo.", "Error", JOptionPane.ERROR_MESSAGE);
            encontrado = true;
        }
        return encontrado;
    }
    /**APROBADO!!!
     * Función que guarda todos los registros temporales de los ArrayList en las tablas correspondientes de la Base de Datos.
     * Para ello, marca cada registro de los ArrayList como guardado (para no eliminarlos de los ArrayList), dando la
     * posibilidad de seleccionar el guardaado varias varias veces y sin eliminar los registros de los ArrayList y de las
     * tablas. Los registros guardados conservan sus datos de BD (Id y demás valores).
     * @return 'true' si no ocurrió algún error al intentar guardar; 'false' si hay registros incorrectos o se tienen problemas
     * de conexión con la Base de Datos.
     */
    private boolean guardar_registros() {
        /* Una manera de verificar que los RegistroEstudiante estén correctos es mediate su atributo 'encargadoId':
           Si dicho valor es cero, implica que el encargado al que estaba relacionado fue eliminado y no se actualizó
           el registro Estudiante para asignarle un nuevo Encargado. */
        int cantidad, cont, incorrectos = 0;
        for(cont=0, cantidad=listaEstudiantes.size(); cont<cantidad; cont++)
            if (listaEstudiantes.get(cont).getEncargadoId() == 0) incorrectos++;
        if (incorrectos != 0) {
            JOptionPane.showMessageDialog(this,
                    "Hay "+incorrectos+" registro"+((incorrectos==1)?"":"s")+" de Estudiantes a los que"
                            + "\nno se les ha asignado un encargado."
                            + "\nPor favor, verifique dichos datos", "Error al guardar", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            // Inicio el guardado de todos los registros temporales no guardados de Encargados
            int contEstudiantes = 0, contEncargados = 0;
            RegistroEncargado iteradorEnc;
            for(cont=0, cantidad=listaEncargados.size(); cont<cantidad; cont++) {
                iteradorEnc = listaEncargados.get(cont);
                if (!iteradorEnc.getEstaEnBD()) {
                    contEncargados++;
                    // Creación de un nuevo registro para el Encargado
                    String nuevoEncargado = "INSERT INTO Encargado(Nombre, Direccion, DPI, FechaNacimiento, Trabajo, Municipio_Id) VALUES (";
                    // Datos del encargado
                    nuevoEncargado+= "'"+iteradorEnc.getNombre()+"',";
                    nuevoEncargado+= "'"+iteradorEnc.getDireccion()+"',";
                    nuevoEncargado+= "'"+iteradorEnc.getDPI()+"',";
                    nuevoEncargado+= "'"+iteradorEnc.getFechaNacimiento()+"',";
                    nuevoEncargado+= "'"+iteradorEnc.getTrabajo()+"',";
                    nuevoEncargado+= ""+(iteradorEnc.getMunicipioId()+1)+")";
                    conexion.prepareStatement(nuevoEncargado).executeUpdate();  // Inserción del nuevo registro Encargado
                    // De momento se crea nuevos teléfonos para cada nuevo Encargado
                    if (!"".equals(iteradorEnc.getTelefono()))
                        conexion.prepareStatement("INSERT INTO Telefono(Telefono, Encargado_Id) VALUES('"+iteradorEnc.getTelefono()+"', "+iteradorEnc.getID()+")").executeUpdate();
                    if (!"".equals(iteradorEnc.getCelular()))
                        conexion.prepareStatement("INSERT INTO Telefono(Telefono, Encargado_Id) VALUES('"+iteradorEnc.getCelular()+"', "+iteradorEnc.getID()+")").executeUpdate();
                    
                    // Ahora actualizo el indicador en iteradorEnc de que este registro ya fue guardado en la Base de Datos
                    iteradorEnc.setEstaEnBD(true);
                }
            }   // Hasta aquí, se garantiza el guardado de todos los registros Encargado en la Base de Datos
            
            // Inicio el guardado de todos los registros temporales no guardados de Estudiantes
            RegistroEstudiante iteradorEst;
            for(cont=0, cantidad=listaEstudiantes.size(); cont<cantidad; cont++) {
                iteradorEst = listaEstudiantes.get(cont);
                if (!iteradorEst.isGuardadoEnBD()) {
                    contEstudiantes++;
                    // Creación de un nuevo registro para el Encargado
                    String nuevoEstudiante = "INSERT INTO Estudiante(CodigoPersonal, CUI, Nombres, Apellidos, FechaNacimiento, Direccion, Sexo, Etnia, CapacidadDiferente, TipoCapacidad, Municipio_Id, Encargado_Id, RelacionEncargado) VALUES (";
                    // Datos del estudiante
                    nuevoEstudiante+= "'"+iteradorEst.getCodigoPersonal()+"',";
                    nuevoEstudiante+= "'"+iteradorEst.getCUI()+"',";
                    nuevoEstudiante+= "'"+iteradorEst.getNombres()+"',";
                    nuevoEstudiante+= "'"+iteradorEst.getApellidos()+"',";
                    nuevoEstudiante+= "'"+iteradorEst.getFechaNacimiento()+"',";
                    nuevoEstudiante+= "'"+iteradorEst.getDireccion()+"',";
                    nuevoEstudiante+= "'"+iteradorEst.getSexo()+"',";
                    nuevoEstudiante+= "'"+iteradorEst.getEtnia()+"',";
                    nuevoEstudiante+= ""+iteradorEst.isCapacidadDiferente()+",";
                    nuevoEstudiante+= (iteradorEst.isCapacidadDiferente()) ? "'"+iteradorEst.getTipoCapacidad()+"'," : "NULL,";
                    nuevoEstudiante+= ""+(iteradorEst.getMunicipioId()+1)+",";
                    nuevoEstudiante+= ""+iteradorEst.getEncargadoId()+",";
                    nuevoEstudiante+= "'"+iteradorEst.getRelacionEncargado()+"')";
                    System.out.println("INSERT = "+nuevoEstudiante);
                    conexion.prepareStatement(nuevoEstudiante).executeUpdate();  // Inserción del nuevo registro Encargado
                    
                    // Ahora actualizo el indicador en iteradorEst de que este registro ya fue guardado en la Base de Datos
                    iteradorEst.setGuardadoEnBD(true);
                }
            }   // Hasta aquí, se garantiza el guardado de todos los registros Estudiante en la Base de Datos
            if (contEstudiantes != 0 || contEncargados != 0)
                JOptionPane.showMessageDialog(this, "Guardado con éxito."
                        + "\nSe ha guardado el registro de:"
                        + ((contEstudiantes!=0)?"\n"+contEstudiantes+" Estudiante"+((contEstudiantes==1)?"":"s"):"")
                        + ((contEncargados!=0)?"\n"+contEncargados+" Encargado"+((contEncargados==1)?"":"s"):""), "Información", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Problema al guardar un registro\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    /**APROBADO!!!
     * Función que se lanza previo a cerrar el JDialog en caso de haber registros que aún no han sido guardados. Para ello,
     * lanza un JOptionPane en el que se pide una acción y se toma la medida dependiendo del resultado.
     * Se sabe que un registro aún no ha sido guardado si su atributo correspondiente es false, esto es:
     * RegistroEncargado.estaEnBD = false, y , RegistroEstudiante.guardadoEnBD = false
     * A la hora de guardar un registro en la BD, se marcan estos valores como true y no se eliminan de los ArrayList
     */
    private void antes_de_cerrar() {
        int contEstudiantes = 0, contEncargados = 0, cantidad, i;
        for(i=0, cantidad=listaEncargados.size(); i<cantidad; i++) {
            if (!listaEncargados.get(i).getEstaEnBD())
                contEncargados++;
        }
        for(i=0, cantidad=listaEstudiantes.size(); i<cantidad; i++) {
            if (!listaEstudiantes.get(i).isGuardadoEnBD())
                contEstudiantes++;
        }
        if (contEncargados != 0 || contEstudiantes != 0) {
            String[] opciones = new String[]{"Guardar Todo", "Salir sin guardar", "Cancelar"};
            int opcion = JOptionPane.showOptionDialog(this,
                    "Aún hay cambios no guardados."
                    + "\nDesea guardarlos antes de salir?"
                    + "\n\nTome en cuenta que si elige Salir sin guardar"
                    + "\nperderá todos los registros que lleva hasta"
                    + "\nel momento.", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch (opcion) {
                case JOptionPane.YES_OPTION:    // Se guardarán los datos
                    if (contEstudiantes == 0) {
                        JOptionPane.showMessageDialog(this,
                                "No hay registros de estudiantes a guardar."
                                + "\n\nTome en cuenta los registro de encargados que "
                                + "\nse guardarán ahora la próxima vez.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    this.setDefaultCloseOperation((guardar_registros()) ? javax.swing.JDialog.DISPOSE_ON_CLOSE : javax.swing.JDialog.DO_NOTHING_ON_CLOSE);
                break;
                case JOptionPane.NO_OPTION:     // No se guardarán los datos
                    this.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
                break;
                default:        // Se canceló la opción
                    this.setDefaultCloseOperation(javax.swing.JDialog.DO_NOTHING_ON_CLOSE);
                break;
            }
        }
    }
    /**ÚTIL!!!
     * 
     * @param valorEnable 
     */
    private void setEnable_campos_encargado(boolean valorEnable) {
        encargado_nombre_completo.setEnabled(valorEnable);
        encargado_direccion.setEnabled(valorEnable);
        encargado_municipio.setEnabled(valorEnable);
        encargado_fechaNacimiento.setEnabled(valorEnable);
        encargado_telefono_casa.setEnabled(valorEnable);
        encargado_celular.setEnabled(valorEnable);
        encargado_trabajo.setEnabled(valorEnable);
    }
    /**ÚTIL!!!
     * 
     * @param valorEnable 
     */
    private void setEnable_campos_estudiante(boolean valorEnable) {
        estudiante_cui.setEnabled(valorEnable);
        estudiante_nombres.setEnabled(valorEnable);
        estudiante_apellidos.setEnabled(valorEnable);
        estudiante_nombre_encargado.setEnabled(valorEnable);
        estudiante_relacion_encargado.setEnabled(valorEnable);
        estudiante_direccion.setEnabled(valorEnable);
        estudiante_municipio.setEnabled(valorEnable);
        estudiante_fechaNacimiento.setEnabled(valorEnable);
        estudiante_sexo.setEnabled(valorEnable);
        estudiante_etnia.setEnabled(valorEnable);
        estudiante_capacidad_diferente.setEnabled(valorEnable);
    }
    /**ÚTIL!!!
     * 
     * @param valorEditable 
     */
    private void setEditable_campos_encargado(boolean valorEditable) {
        encargado_dpi.setEditable(!valorEditable);
        encargado_nombre_completo.setEditable(valorEditable);
        encargado_direccion.setEditable(valorEditable);
        encargado_fechaNacimiento.setEnabled(valorEditable);
        encargado_telefono_casa.setEditable(valorEditable);
        encargado_celular.setEditable(valorEditable);
        encargado_trabajo.setEditable(valorEditable);
    }
    /**ÚTIL, PERO NO UTILIZADO!!!
     * 
     * @param valorEditable 
     */
    private void setEditable_campos_estudiante(boolean valorEditable) {
        estudiante_codigo_personal.setEditable(!valorEditable);
        estudiante_cui.setEditable(valorEditable);
        estudiante_nombres.setEditable(valorEditable);
    }
    /**ÚTIL!!!
     * 
     */
    private void limpiar_campos_encargado(boolean limpiarDPI) {
        if (limpiarDPI) encargado_dpi.setText("");
        encargado_nombre_completo.setText("");
        encargado_direccion.setText("");
        encargado_municipio.setSelectedIndex(0);
        encargado_fechaNacimiento.setDate(null);    // Para limpiar la fecha
        encargado_telefono_casa.setText("");
        encargado_celular.setText("");
        encargado_trabajo.setText("");
        // Siempre que los campos se limpien, no se pueden agregar los 'datos vacíos'
        agregar_fila_encargado.setEnabled(false);
    }
    /**ÚTIL!!!
     * 
     */
    private void limpiar_campos_estudiante() {
       estudiante_codigo_personal.setText("");
       estudiante_cui.setText("");
       estudiante_nombres.setText("");
       estudiante_apellidos.setText("");
       estudiante_nombre_encargado.setSelectedIndex(-1);
       estudiante_relacion_encargado.setText("");
       estudiante_direccion.setText("");
       estudiante_fechaNacimiento.setDate(null);
       estudiante_sexo.setSelectedIndex(0);
       estudiante_etnia.setText("");
       estudiante_capacidad_diferente.setSelectedIndex(0);
       estudiante_tipo_capacidad.setText("");
    }
    /**ÚTIL!!!
     * 
     * @param encargado 
     */
    private void cargar_encargado_en_campos(RegistroEncargado encargado) {
        encargado_dpi.setText(encargado.getDPI());
        encargado_nombre_completo.setText(encargado.getNombre());
        encargado_direccion.setText(encargado.getDireccion());
        encargado_municipio.setSelectedIndex(encargado.getMunicipioId());
        encargado_fechaNacimiento.setDate(encargado.getDateNacimiento());
        encargado_telefono_casa.setText(encargado.getTelefono());
        encargado_celular.setText(encargado.getCelular());
        encargado_trabajo.setText(encargado.getTrabajo());
    }
    /**ÚTIL!!!
     * 
     * @param estudiante 
     */
    private void cargar_estudiante_en_campos(RegistroEstudiante estudiante) {
        estudiante_codigo_personal.setText(estudiante.getCodigoPersonal());
        estudiante_cui.setText(estudiante.getCUI());
        estudiante_nombres.setText(estudiante.getNombres());
        estudiante_apellidos.setText(estudiante.getApellidos());
        estudiante_relacion_encargado.setText(estudiante.getRelacionEncargado());
        estudiante_direccion.setText(estudiante.getDireccion());
        estudiante_fechaNacimiento.setDate(estudiante.getDateNacimiento());
        estudiante_sexo.setSelectedIndex(("F".equals(estudiante.getSexo())) ? 0 : 1);
        estudiante_etnia.setText(estudiante.getEtnia());
        estudiante_capacidad_diferente.setSelectedIndex((estudiante.isCapacidadDiferente()) ? 1 : 0);
        estudiante_tipo_capacidad.setText((estudiante.isCapacidadDiferente()) ? estudiante.getTipoCapacidad() : "");
        // Carga de los datos de su Enargado
        estudiante_nombre_encargado.setSelectedItem(estudiante.getNombreEncargado());
    }
    /**APROBADO!!!
     * Método que verifica que los datos para un nuevo registro Encargado sean los correctos. Si hay algún dato nulo o incorrecto
     * lanza una 'ExcepcionDatosIncorrectos '.
     * @throws ExcepcionDatosIncorrectos 
     */
    private void validar_datos_encargado() throws ExcepcionDatosIncorrectos {
        // Verificación de la validez del DPI
        if (!Pattern.compile("[0-9]{13}").matcher(encargado_dpi.getText()).matches())
            throw new ExcepcionDatosIncorrectos("El texto para el DPI es incorrecto");
        if ("".equals(encargado_nombre_completo.getText()))
            throw new ExcepcionDatosIncorrectos("El nombre no puede ser nulo");
        if ("".equals(encargado_direccion.getText()))
            throw new ExcepcionDatosIncorrectos("No se ha especificado la Dirección");
        if (encargado_fechaNacimiento.getDate() == null)
            throw new ExcepcionDatosIncorrectos("Especifique la Fecha de nacimiento");
        if (encargado_telefono_casa.getText().length() != 0 && !Pattern.compile("[0-9]{8}").matcher(encargado_telefono_casa.getText()).matches())
            throw new ExcepcionDatosIncorrectos("Verifique que el Telefono sea de 8 dígitos (sin espacios ni letras)");
        if (encargado_celular.getText().length() != 0 && !Pattern.compile("[0-9]{8}").matcher(encargado_celular.getText()).matches())
            throw new ExcepcionDatosIncorrectos("Verifique que el Celular sea de 8 dígitos (sin espacios ni letras)");
    }
    /**APROBADO!!!
     * Método que verifica que los datos para un nuevo registro Estudiante sean los correctos. Si hay algún dato nulo o
     * incorrecto lanza una 'ExcepcionDatosIncorrectos '.
     * @throws ExcepcionDatosIncorrectos 
     */
    private void validar_datos_estudiante() throws ExcepcionDatosIncorrectos {
        // Verificación de la validez de los datos
        if (estudiante_nombre_encargado.getSelectedIndex() == -1)
            throw new ExcepcionDatosIncorrectos("No se ha seleccionado el Encargado para el Estudiante.");
        // El Código Personal se valida en la función estudianteYaExiste()
        if (!Pattern.compile("\\d{13}").matcher(estudiante_cui.getText()).matches())
            throw new ExcepcionDatosIncorrectos("El Código Único de Identificación tiene un formato incorrecto\nVerifique que sea un código de 13 dígitos (sin letras ni espacios)");
        if ("".equals(estudiante_nombres.getText()) || "".equals(estudiante_apellidos.getText()))
            throw new ExcepcionDatosIncorrectos("Los nombres o los apellidos no pueden ser nulos");
        if ("".equals(estudiante_relacion_encargado.getText()))
            throw new ExcepcionDatosIncorrectos("Especifique la relación del encargado con el estudiante");
        if ("".equals(estudiante_direccion.getText()))
            throw new ExcepcionDatosIncorrectos("No se ha especificado la dirección de estudiante");
        if (estudiante_fechaNacimiento.getDate() == null)
            throw new ExcepcionDatosIncorrectos("No se ha especificado la fecha de nacimiento del estudiante");
        if ("".equals(estudiante_etnia.getText()))
            throw new ExcepcionDatosIncorrectos("No se ha especificado la comunidad étnica del estudiante");
        if (estudiante_capacidad_diferente.getSelectedIndex() == 1 && "".equals(estudiante_tipo_capacidad.getText()))
            throw new ExcepcionDatosIncorrectos("No se ha especificado el tipo de capacidad diferente del estudiante");
        
    }
    /**
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Estudiantes (valores definidos en base a pruebas)
        tabla_estudiantes.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_estudiantes.getColumnModel().getColumn(1).setPreferredWidth(110);
        tabla_estudiantes.getColumnModel().getColumn(2).setPreferredWidth(135);
        tabla_estudiantes.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabla_estudiantes.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabla_estudiantes.getColumnModel().getColumn(5).setPreferredWidth(175);
        tabla_estudiantes.getColumnModel().getColumn(6).setPreferredWidth(180);
        tabla_estudiantes.getColumnModel().getColumn(7).setPreferredWidth(120);
        tabla_estudiantes.getColumnModel().getColumn(8).setPreferredWidth(80);
        tabla_estudiantes.getColumnModel().getColumn(9).setPreferredWidth(80);
        tabla_estudiantes.getColumnModel().getColumn(10).setPreferredWidth(125);
        tabla_estudiantes.getColumnModel().getColumn(11).setPreferredWidth(230);
        tabla_estudiantes.getColumnModel().getColumn(12).setPreferredWidth(330);
        tabla_estudiantes.getColumnModel().getColumn(13).setPreferredWidth(115);
        
        // Definición del ancho de las columnas para la Tabla Encargados (valores definidos en base a pruebas)
        tabla_encargados.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_encargados.getColumnModel().getColumn(1).setPreferredWidth(115);
        tabla_encargados.getColumnModel().getColumn(2).setPreferredWidth(190);
        tabla_encargados.getColumnModel().getColumn(3).setPreferredWidth(215);
        tabla_encargados.getColumnModel().getColumn(4).setPreferredWidth(125);
        tabla_encargados.getColumnModel().getColumn(5).setPreferredWidth(115);
        tabla_encargados.getColumnModel().getColumn(6).setPreferredWidth(87);
        tabla_encargados.getColumnModel().getColumn(7).setPreferredWidth(95);
        tabla_encargados.getColumnModel().getColumn(8).setPreferredWidth(130);
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
        jLabel12 = new javax.swing.JLabel();
        estudiante_codigo_personal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        estudiante_cui = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        estudiante_nombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        buscar_codigoPersonal_estudiante = new javax.swing.JButton();
        estudiante_apellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        estudiante_nombre_encargado = new javax.swing.JComboBox<>();
        estudiante_direccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        estudiante_municipio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        estudiante_sexo = new javax.swing.JComboBox<>();
        estudiante_etnia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        estudiante_capacidad_diferente = new javax.swing.JComboBox<>();
        estudiante_tipo_capacidad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panel_botones_estudiante = new javax.swing.JPanel();
        agregar_fila_estudiante = new javax.swing.JButton();
        editar_fila_estudiante = new javax.swing.JButton();
        eliminar_fila_estudiante = new javax.swing.JButton();
        estudiante_relacion_encargado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        estudiante_fechaNacimiento = new com.toedter.calendar.JDateChooser();
        panel_registros_estudiante = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_estudiantes = new javax.swing.JTable();
        panel_datos_encargado = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        encargado_dpi = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        encargado_nombre_completo = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        encargado_direccion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        encargado_municipio = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        encargado_telefono_casa = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        buscar_dpi_encargado = new javax.swing.JButton();
        encargado_celular = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        encargado_trabajo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        panel_botones_encargado = new javax.swing.JPanel();
        agregar_fila_encargado = new javax.swing.JButton();
        editar_fila_encargado = new javax.swing.JButton();
        eliminar_fila_encargado = new javax.swing.JButton();
        encargado_fechaNacimiento = new com.toedter.calendar.JDateChooser();
        panel_registros_encargado = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_encargados = new javax.swing.JTable();
        panel_boton_guardar = new javax.swing.JPanel();
        guardar_todos_los_registros = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear nuevo estudiante");

        panel_datos_estudiante.setBackground(new java.awt.Color(153, 153, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("INFORMACIÓN DEL ESTUDIANTE:");

        estudiante_codigo_personal.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Codigo Personal:");

        estudiante_cui.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_cui.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("CUI:");

        estudiante_nombres.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_nombres.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Nombres:");

        buscar_codigoPersonal_estudiante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar_codigoPersonal_estudiante.setText("Buscar...");
        buscar_codigoPersonal_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_codigoPersonal_estudianteActionPerformed(evt);
            }
        });

        estudiante_apellidos.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_apellidos.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Apellidos:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setText("Encargado:");

        estudiante_nombre_encargado.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_nombre_encargado.setEnabled(false);

        estudiante_direccion.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_direccion.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Dirección:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Municipio:");

        estudiante_municipio.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_municipio.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Sexo:");

        estudiante_sexo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));
        estudiante_sexo.setEnabled(false);

        estudiante_etnia.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_etnia.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Comunidad étnica:");

        estudiante_capacidad_diferente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_capacidad_diferente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));
        estudiante_capacidad_diferente.setEnabled(false);
        estudiante_capacidad_diferente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estudiante_capacidad_diferenteItemStateChanged(evt);
            }
        });

        estudiante_tipo_capacidad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_tipo_capacidad.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Capacidad Diferente:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Tipo capacidad:");

        panel_botones_estudiante.setBackground(new java.awt.Color(51, 51, 255));

        agregar_fila_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregar_fila_estudiante.setText("Agregar");
        agregar_fila_estudiante.setEnabled(false);
        agregar_fila_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_fila_estudianteActionPerformed(evt);
            }
        });

        editar_fila_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editar_fila_estudiante.setText("Editar");
        editar_fila_estudiante.setEnabled(false);
        editar_fila_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_fila_estudianteActionPerformed(evt);
            }
        });

        eliminar_fila_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar_fila_estudiante.setText("Quitar");
        eliminar_fila_estudiante.setEnabled(false);
        eliminar_fila_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_fila_estudianteActionPerformed(evt);
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
                .addComponent(editar_fila_estudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminar_fila_estudiante)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panel_botones_estudianteLayout.setVerticalGroup(
            panel_botones_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_botones_estudianteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_botones_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar_fila_estudiante)
                    .addComponent(editar_fila_estudiante)
                    .addComponent(agregar_fila_estudiante))
                .addContainerGap())
        );

        estudiante_relacion_encargado.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        estudiante_relacion_encargado.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setText("Relación con estudiante:");

        estudiante_fechaNacimiento.setDateFormatString("yyyy-MM-dd");
        estudiante_fechaNacimiento.setEnabled(false);
        estudiante_fechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout panel_datos_estudianteLayout = new javax.swing.GroupLayout(panel_datos_estudiante);
        panel_datos_estudiante.setLayout(panel_datos_estudianteLayout);
        panel_datos_estudianteLayout.setHorizontalGroup(
            panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jLabel12))
                    .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                        .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)))
                            .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_estudianteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estudiante_capacidad_diferente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(panel_botones_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                        .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buscar_codigoPersonal_estudiante))
                                    .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                        .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(estudiante_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(estudiante_cui, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                        .addComponent(estudiante_nombre_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(estudiante_relacion_encargado))
                                    .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                        .addComponent(estudiante_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                                        .addComponent(estudiante_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(estudiante_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panel_datos_estudianteLayout.setVerticalGroup(
            panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_codigo_personal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(buscar_codigoPersonal_estudiante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_cui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(estudiante_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_relacion_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(estudiante_nombre_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estudiante_direccion)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(estudiante_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(estudiante_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(estudiante_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_estudianteLayout.createSequentialGroup()
                        .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estudiante_etnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estudiante_capacidad_diferente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estudiante_tipo_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addContainerGap())
                    .addComponent(panel_botones_estudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panel_registros_estudiante.setBackground(new java.awt.Color(153, 153, 255));

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
        jScrollPane1.setViewportView(tabla_estudiantes);

        javax.swing.GroupLayout panel_registros_estudianteLayout = new javax.swing.GroupLayout(panel_registros_estudiante);
        panel_registros_estudiante.setLayout(panel_registros_estudianteLayout);
        panel_registros_estudianteLayout.setHorizontalGroup(
            panel_registros_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registros_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        panel_registros_estudianteLayout.setVerticalGroup(
            panel_registros_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registros_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_datos_encargado.setBackground(new java.awt.Color(153, 153, 255));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("INFORMACIÓN DEL ENCARGADO:");

        encargado_dpi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("DPI:");

        encargado_nombre_completo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        encargado_nombre_completo.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Nombre completo:");

        encargado_direccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        encargado_direccion.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Dirección:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Municipio:");

        encargado_municipio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        encargado_municipio.setEnabled(false);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Fecha de Nacimiento:");

        encargado_telefono_casa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        encargado_telefono_casa.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Teléfono:");

        buscar_dpi_encargado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar_dpi_encargado.setText("Buscar...");
        buscar_dpi_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_dpi_encargadoActionPerformed(evt);
            }
        });

        encargado_celular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        encargado_celular.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Celular:");

        encargado_trabajo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        encargado_trabajo.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Trabajo u oficio:");

        panel_botones_encargado.setBackground(new java.awt.Color(51, 51, 255));

        agregar_fila_encargado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregar_fila_encargado.setText("Agregar");
        agregar_fila_encargado.setEnabled(false);
        agregar_fila_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_fila_encargadoActionPerformed(evt);
            }
        });

        editar_fila_encargado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editar_fila_encargado.setText("Editar");
        editar_fila_encargado.setEnabled(false);
        editar_fila_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_fila_encargadoActionPerformed(evt);
            }
        });

        eliminar_fila_encargado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar_fila_encargado.setText("Quitar");
        eliminar_fila_encargado.setEnabled(false);
        eliminar_fila_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_fila_encargadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_botones_encargadoLayout = new javax.swing.GroupLayout(panel_botones_encargado);
        panel_botones_encargado.setLayout(panel_botones_encargadoLayout);
        panel_botones_encargadoLayout.setHorizontalGroup(
            panel_botones_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botones_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(agregar_fila_encargado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editar_fila_encargado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminar_fila_encargado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_botones_encargadoLayout.setVerticalGroup(
            panel_botones_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_botones_encargadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_botones_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar_fila_encargado)
                    .addComponent(editar_fila_encargado)
                    .addComponent(agregar_fila_encargado))
                .addContainerGap())
        );

        encargado_fechaNacimiento.setDateFormatString("yyyy-MM-dd");
        encargado_fechaNacimiento.setEnabled(false);
        encargado_fechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout panel_datos_encargadoLayout = new javax.swing.GroupLayout(panel_datos_encargado);
        panel_datos_encargado.setLayout(panel_datos_encargadoLayout);
        panel_datos_encargadoLayout.setHorizontalGroup(
            panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                        .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                                        .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buscar_dpi_encargado))
                                    .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                                        .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(encargado_telefono_casa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addComponent(jLabel20)))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_encargadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel_botones_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel_datos_encargadoLayout.setVerticalGroup(
            panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(buscar_dpi_encargado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_nombre_completo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_telefono_casa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(panel_botones_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_registros_encargado.setBackground(new java.awt.Color(153, 153, 255));

        tabla_encargados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_encargados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "DPI", "Nombre Completo", "Dirección", "Municipio", "Fecha Nacimiento", "Teléfono", "Celular", "Trabajo u oficio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_encargados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_encargados.setRowHeight(25);
        jScrollPane2.setViewportView(tabla_encargados);

        javax.swing.GroupLayout panel_registros_encargadoLayout = new javax.swing.GroupLayout(panel_registros_encargado);
        panel_registros_encargado.setLayout(panel_registros_encargadoLayout);
        panel_registros_encargadoLayout.setHorizontalGroup(
            panel_registros_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registros_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panel_registros_encargadoLayout.setVerticalGroup(
            panel_registros_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registros_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_boton_guardar.setBackground(new java.awt.Color(153, 153, 255));

        guardar_todos_los_registros.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        guardar_todos_los_registros.setText("Guardar todos los registros");
        guardar_todos_los_registros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_todos_los_registrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_boton_guardarLayout = new javax.swing.GroupLayout(panel_boton_guardar);
        panel_boton_guardar.setLayout(panel_boton_guardarLayout);
        panel_boton_guardarLayout.setHorizontalGroup(
            panel_boton_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_boton_guardarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guardar_todos_los_registros)
                .addGap(504, 504, 504))
        );
        panel_boton_guardarLayout.setVerticalGroup(
            panel_boton_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_boton_guardarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardar_todos_los_registros)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_boton_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel_registros_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_datos_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_datos_encargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_registros_encargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_datos_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_datos_encargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_registros_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_registros_encargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_boton_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void estudiante_capacidad_diferenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estudiante_capacidad_diferenteItemStateChanged
        estudiante_tipo_capacidad.setEnabled(estudiante_capacidad_diferente.getSelectedIndex() != 0);
    }//GEN-LAST:event_estudiante_capacidad_diferenteItemStateChanged

    private void agregar_fila_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_fila_estudianteActionPerformed
        // Se agregan los datos que se extraen de los campos a las celdas de la Tabla Estudiante
        /* Realizo nuevamente la comprobación, en caso de que el Código Personal haya sido modificado después de comprobar
        que aún no existe. */
        if (!estudianteYaExiste()) {
            try {
                validar_datos_estudiante();
                RegistroEstudiante nuevoEst = new RegistroEstudiante();
                contadorIdEstudiantesEnBD++;
                System.out.println("El nuevo estudiante tendrá "+contadorIdEstudiantesEnBD+" como ID");
                nuevoEst.setNum(tabla_estudiantes.getRowCount() + 1);
                nuevoEst.setID(contadorIdEstudiantesEnBD);
                nuevoEst.setCodigoPersonal(estudiante_codigo_personal.getText());
                nuevoEst.setCUI(estudiante_cui.getText());
                nuevoEst.setNombres(estudiante_nombres.getText());
                nuevoEst.setApellidos(estudiante_apellidos.getText());
                nuevoEst.setDireccion(estudiante_direccion.getText());
                nuevoEst.setMunicipioId(estudiante_municipio.getSelectedIndex());
                nuevoEst.setMunicipio((String)estudiante_municipio.getSelectedItem());
                Calendar fecha = estudiante_fechaNacimiento.getCalendar();
                nuevoEst.setFechaNacimiento(""+fecha.get(Calendar.YEAR)+"-"+(fecha.get(Calendar.MONTH)+1)+"-"+fecha.get(Calendar.DAY_OF_MONTH));
                nuevoEst.setSexo((estudiante_sexo.getSelectedIndex() == 0) ? "F" : "M");
                nuevoEst.setEtnia(estudiante_etnia.getText());
                nuevoEst.setCapacidadDiferente((estudiante_capacidad_diferente.getSelectedIndex() == 1));
                nuevoEst.setTipoCapacidad((nuevoEst.isCapacidadDiferente()) ? estudiante_tipo_capacidad.getText() : "");
                // Inserción de los datos de su Encargado
                int indexEncargado = estudiante_nombre_encargado.getSelectedIndex();
                nuevoEst.setNombreEncargado(estudiante_nombre_encargado.getItemAt(indexEncargado));
                nuevoEst.setRelacionEncargado(estudiante_relacion_encargado.getText());
                nuevoEst.setEncargadoId(listaEncargados.get(indexEncargado).getID());
                nuevoEst.setEncargadoNum(indexEncargado);

                listaEstudiantes.add(nuevoEst); // Agrego el nuevo registro al ArrayList listaEstudiantes
                modelEstudiantes.addRow(nuevoEst.getDatosParaTabla());  // Agrego el nuevo registro a la Tabla de Encargados
                limpiar_campos_estudiante();
                setEnable_campos_estudiante(false);
                agregar_fila_estudiante.setEnabled(false);  // Se podrá agregar un nuevo registro después de comprobar que no sea repetido
                editar_fila_estudiante.setEnabled(true);    // Se pueden Editar registros siempre que por lo menos exista uno
                eliminar_fila_estudiante.setEnabled(true);  // Se pueden Eliminar registros siempre que por lo menos exista uno
            } catch (ExcepcionDatosIncorrectos ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_agregar_fila_estudianteActionPerformed

    private void editar_fila_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_fila_estudianteActionPerformed
        if ("Editar".equals(editar_fila_estudiante.getText())) { // Se habilitará la edición del registro
            int[] rango = tabla_estudiantes.getSelectedRows();
            if (rango.length == 0)
                JOptionPane.showMessageDialog(this, "No se ha seleccionado un registro", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            else if (rango.length > 1)
                JOptionPane.showMessageDialog(this, "No se pueden editar varios registro a la vez", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            else {
                RegistroEstudiante editar = listaEstudiantes.get(rango[0]);
                editar_fila_estudiante.setText("Guardar");
                agregar_fila_estudiante.setEnabled(false);  // Bloqueo el botón de Agregar
                eliminar_fila_estudiante.setEnabled(false); // Bloqueo el botón de Eliminar
                indexRegistroEstudianteEditado = rango[0];  // Guardo el index que se va a editar
                cargar_estudiante_en_campos(editar);    // Cargo los datos del registro en los campos para ser editados
                setEnable_campos_estudiante(true);
                estudiante_codigo_personal.setEditable(false);  // El Código Personal no puede ser editado
            }
        } else {    // Cambio el texto del botón para indicar que se guardarán los cambios del registro editado
            editar_fila_estudiante.setText("Editar");  // Cambio el texto del botón para indicar que se guardarán los cambios
            // Guardo todos los nuevos datos del registro
            RegistroEstudiante editado = listaEstudiantes.get(indexRegistroEstudianteEditado);
            // El Código Personal sigue siendo el mismo
            editado.setCUI(estudiante_cui.getText());
            editado.setNombres(estudiante_nombres.getText());
            editado.setApellidos(estudiante_apellidos.getText());
            editado.setDireccion(estudiante_direccion.getText());
            editado.setMunicipioId(estudiante_municipio.getSelectedIndex());
            Calendar fecha = estudiante_fechaNacimiento.getCalendar();
            editado.setFechaNacimiento(""+fecha.get(Calendar.YEAR)+"-"+(fecha.get(Calendar.MONTH)+1)+"-"+fecha.get(Calendar.DAY_OF_MONTH));
            editado.setSexo((estudiante_sexo.getSelectedIndex() == 0) ? "F" : "M");
            editado.setEtnia(estudiante_etnia.getText());
            editado.setCapacidadDiferente((estudiante_capacidad_diferente.getSelectedIndex() == 1));
            editado.setTipoCapacidad((editado.isCapacidadDiferente()) ? estudiante_tipo_capacidad.getText() : "");
            // Inserción de los datos de su Encargado
            int indexEncargado = estudiante_nombre_encargado.getSelectedIndex();
            editado.setNombreEncargado(estudiante_nombre_encargado.getItemAt(indexEncargado));
            editado.setRelacionEncargado(estudiante_relacion_encargado.getText());
            editado.setEncargadoId(listaEncargados.get(indexEncargado).getID());
            editado.setEncargadoNum(indexEncargado);
            // Ahora inserto los nuevos datos en la Tabla de Estudiantes
            String[] nuevosDatos = listaEstudiantes.get(indexRegistroEstudianteEditado).getDatosParaTabla();
            for(int i=0; i<nuevosDatos.length; i++)
                modelEstudiantes.setValueAt(nuevosDatos[i], indexRegistroEstudianteEditado, i);

            agregar_fila_estudiante.setEnabled(true);   // Habilito el botón de Agregar
            eliminar_fila_estudiante.setEnabled(true);  // Habilito el botón de Eliminar
            estudiante_codigo_personal.setEditable(true);
            limpiar_campos_estudiante();    // Borro el contenido de los campos
            setEnable_campos_estudiante(false);
        }
    }//GEN-LAST:event_editar_fila_estudianteActionPerformed

    private void eliminar_fila_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_fila_estudianteActionPerformed
        int rango[] = tabla_estudiantes.getSelectedRows();
        if (rango.length == 0)
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else if (rango.length > 1)
            JOptionPane.showMessageDialog(this, "Por favor seleccione un solo registro de la Tabla", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else {
            // Eliminación del registro del ArrayList listaEstudiantes
            listaEstudiantes.remove(rango[0]);
            // Eliminación del registro de la Tabla de Estudiantes
            modelEstudiantes.removeRow(rango[0]);
            // Actualización del No. y del ID de los registros siguientes:
            int contFilas = listaEstudiantes.size();
            for(int i=rango[0]; i<contFilas; i++) {
                listaEstudiantes.get(i).setID(listaEstudiantes.get(i).getID() - 1);
                listaEstudiantes.get(i).setNum(listaEstudiantes.get(i).getNum() - 1);
                tabla_estudiantes.setValueAt(listaEstudiantes.get(i).getNum(), i, 0);
            }
            contadorIdEstudiantesEnBD--;
        }
    }//GEN-LAST:event_eliminar_fila_estudianteActionPerformed

    private void buscar_codigoPersonal_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_codigoPersonal_estudianteActionPerformed
        /* Evaluo si el Codigo Personal del nuevo registro ya existe en la Tabla de Estudiantes (datos temporales) o en la
        Tabla Estudiante (de la Base de Datos). Si ya existe una coincidencia, la muestro y no se debe crear el nuevo registro. */
        // Habilito o bloqueo el botón, dependiendo del caso
        boolean yaExiste = estudianteYaExiste();
        agregar_fila_estudiante.setEnabled(!yaExiste);
        setEnable_campos_estudiante(!yaExiste);
    }//GEN-LAST:event_buscar_codigoPersonal_estudianteActionPerformed

    private void buscar_dpi_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_dpi_encargadoActionPerformed
        String DPIBuscado = encargado_dpi.getText();
        // Evaluo la validez del texto DPI mediante una Expresión Regular
        if (Pattern.compile("[0-9]{13}").matcher(DPIBuscado).matches()) {
            /* Búsqueda del DPI en la Tabla de Encargados (datos temporales). El orden de los datos de esta tabla es
            correlativo a los del ArrayList<RegistroEncargado> encargados. */
            int cantidad = listaEncargados.size();
            RegistroEncargado iterador;
            boolean encontradoEnTabla = false;
            for(int i=0; i<cantidad; i++) {
                iterador = listaEncargados.get(i);
                // Si lo encuentra en la tabla, lanza el mensaje haciendo referencia de su posición
                encontradoEnTabla = DPIBuscado.equals(iterador.getDPI());
                if (encontradoEnTabla) {
                    // Selecciono la fila que contiene el dato a evaluar
                    tabla_encargados.setRowSelectionInterval(i, i);
                    JOptionPane.showMessageDialog(this, "El registro se encuentra en la fila "+(i+1)+" de la tabla.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    i = cantidad;
                }
            }
            // Si no lo encuentra en la Tabla de Encargados, entonces se buscará en la Base de Datos
            boolean encontradoEnBD = false;
            if (!encontradoEnTabla) {
                try {   // Hago una consulta a la tabla Encargado en busca del campo encargado_dpi.getText() en la columna DPI
                    Statement consulta = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    ResultSet cEncargado = consulta.executeQuery("SELECT * FROM Encargado WHERE DPI = '"+DPIBuscado+"'");
                    // Si la consulta regresa por lo menos un registro, consulta.next() es true, de lo contrario es false (no existe).
                    encontradoEnBD = cEncargado.next();
                    if (encontradoEnBD) {
                        // Obtengo los datos del Encargado encontrado en la Base de Datos y los cargo en los campos
                        idEncargadoEnBD = cEncargado.getInt("Id");    // Obtengo su Id que me servirá a la hora de agregarlo a la tabla
                        encargado_nombre_completo.setText(cEncargado.getString("Nombre"));
                        encargado_direccion.setText(cEncargado.getString("Direccion"));
                        encargado_municipio.setSelectedIndex(cEncargado.getInt("Municipio_Id")-1);
                        String[] fecha = cEncargado.getString("FechaNacimiento").split("-");
                        encargado_fechaNacimiento.setDate(new Date(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));
                        encargado_trabajo.setText(cEncargado.getString("Trabajo"));
                        ResultSet cTelefono = consulta.executeQuery("SELECT Telefono FROM Telefono WHERE Encargado_Id = "+idEncargadoEnBD+"");
                        if (cTelefono.next()) encargado_telefono_casa.setText(cTelefono.getString("Telefono"));
                        if (cTelefono.next()) encargado_celular.setText(cTelefono.getString("Telefono"));
                        // Hasta aquí ya se han cargado los datos desde la Base de Datos
                        encontradoEnBD = true;
                        setEnable_campos_encargado(false);  // Deshabilitar los campos de para editar datos
                        agregar_fila_encargado.setEnabled(true);    // Habilito el botón para poder Agregar el registro
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error en:\n"+e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            // Si no se encuentra en la Tabla de Encargados ni en la Base de Datos, se debe crear un nuevo Registro
            if (!encontradoEnBD && !encontradoEnTabla) {
                // CREAR EL NUEVO REGISTRO
                setEnable_campos_encargado(true);   // Habilita los campos para poder ingresar datos
//                limpiar_campos_encargado(false);
//                encargado_dpi.setEditable(true);    // En caso de que quiere ingresar otro DPI
                idEncargadoEnBD = 0;    // Para indicar que el nuevo registro aún no está en la BD
                agregar_fila_encargado.setEnabled(true);    // Habilito el botón para poder Agregar el registro
            }
        } else
            JOptionPane.showMessageDialog(this, "El DPI no es válido.\nPor favor, verifique que no tenga espacios y que sea de 13 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_buscar_dpi_encargadoActionPerformed

    private void agregar_fila_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_fila_encargadoActionPerformed
        /* La manera de determinar si el registro que se quiere agregar ya está en la Base de Datos es mediante el
        'idEncargadoEnBd'. Si es diferente de cero, entonces dicho registro ya está en la BD; de lo contrario, el registro
        aún no existe en la BD.
        Cada vez que se agrega un encargado, se debe agregar el nombre al JComboBox estudiante_nombre_encargado*/
        try {
            validar_datos_encargado();
            RegistroEncargado nuevoEnc = new RegistroEncargado();
            if (idEncargadoEnBD != 0) {
                nuevoEnc.setEstaEnBD(true);
                nuevoEnc.setID(idEncargadoEnBD);
            } else {
                nuevoEnc.setEstaEnBD(false);
                contadorIdEncargadosEnBD++;
                System.out.println("El nuevo encargado tendrá "+contadorIdEncargadosEnBD+" como ID");
                nuevoEnc.setID(contadorIdEncargadosEnBD);
            }
            nuevoEnc.setNum(tabla_encargados.getRowCount() + 1);
            nuevoEnc.setDPI(encargado_dpi.getText());
            nuevoEnc.setNombre(encargado_nombre_completo.getText());
            nuevoEnc.setDireccion(encargado_direccion.getText());
            nuevoEnc.setMunicipioId(encargado_municipio.getSelectedIndex());
            nuevoEnc.setMunicipio((String)encargado_municipio.getSelectedItem());
            Calendar fecha = encargado_fechaNacimiento.getCalendar();
            nuevoEnc.setFechaNacimiento(""+fecha.get(Calendar.YEAR)+"-"+(fecha.get(Calendar.MONTH)+1)+"-"+fecha.get(Calendar.DAY_OF_MONTH));
            nuevoEnc.setTelefono(encargado_telefono_casa.getText());
            nuevoEnc.setCelular(encargado_celular.getText());
            nuevoEnc.setTrabajo(encargado_trabajo.getText());
            listaEncargados.add(nuevoEnc);   // Agrego el nuevo registro al ArrayList encargados
            modelEncargados.addRow(nuevoEnc.getDatosParaTabla());   // Agrego el nuevo registro a la Tabla de Encargados
            estudiante_nombre_encargado.addItem(nuevoEnc.getNombre());  // Agrego el Nombre del nuevo Encargado
            limpiar_campos_encargado(true); // Borro los datos de los campos
            setEnable_campos_encargado(false);    // Para iniciar de nuevo
            agregar_fila_encargado.setEnabled(false);    // Inhabilito el botón para evitar intentar caer en la excepción
            editar_fila_encargado.setEnabled(true);     // Se pueden Editar registros siempre que por lo menos exista uno
            eliminar_fila_encargado.setEnabled(true);   // Se pueden Eliminar registros siempre que por lo menos exista uno
        } catch (ExcepcionDatosIncorrectos ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_agregar_fila_encargadoActionPerformed

    private void editar_fila_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_fila_encargadoActionPerformed
        if ("Editar".equals(editar_fila_encargado.getText())) { // Se habilitará la edición del registro
            int[] rango = tabla_encargados.getSelectedRows();
            if (rango.length == 0)
                JOptionPane.showMessageDialog(this, "No se ha seleccionado un registro", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            else if (rango.length > 1)
                JOptionPane.showMessageDialog(this, "No se pueden editar varios registro a la vez", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            else {
                // Verifico si el registro aún no está en la Base de Datos
                RegistroEncargado editar = listaEncargados.get(rango[0]);
                if (!editar.getEstaEnBD()) {   // Si no está en la BD, puede ser editado
                    System.out.println("Se editará un registro de Encargado");
                    editar_fila_encargado.setText("Guardar");  // Cambio el texto del botón para indicar que se está editando un dato
                    agregar_fila_encargado.setEnabled(false);   // Bloqueo el botón de Agregar
                    eliminar_fila_encargado.setEnabled(false);  // Bloqueo el botón de Eliminar
                    indexRegistroEncargadoEditado = rango[0]; // Guardo el index que se va a editar
                    cargar_encargado_en_campos(editar); // Cargo los datos del registro en los campos para ser editados
                    setEnable_campos_encargado(true);
                    encargado_dpi.setEditable(false);
                    // El DPI no puede ser modificado pues se pueden generar conflictos con datos de la Base de Datos
                } else
                    JOptionPane.showMessageDialog(this, "No se puede editar este registro.\nSólo se pueden editar registros temporales", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {    // Cambio el texto del botón para indicar que se guardarán los cambios del dato editado
            editar_fila_encargado.setText("Editar");  // Cambio el texto del botón para indicar que se guardarán los cambios
            // Guardo todos los nuevos datos del registro
            RegistroEncargado editado = listaEncargados.get(indexRegistroEncargadoEditado);
            // El DPI sigue siento el mismo
            editado.setNombre(encargado_nombre_completo.getText());
            editado.setDireccion(encargado_direccion.getText());
            editado.setMunicipioId(encargado_municipio.getSelectedIndex());
            Calendar fecha = encargado_fechaNacimiento.getCalendar();
            editado.setFechaNacimiento(""+fecha.get(Calendar.YEAR)+"-"+(fecha.get(Calendar.MONTH)+1)+"-"+fecha.get(Calendar.DAY_OF_MONTH));
            editado.setTelefono(encargado_telefono_casa.getText());
            editado.setCelular(encargado_celular.getText());
            editado.setTrabajo(encargado_trabajo.getText());
            // Actualizo el nombre del Encargado en la parte del Estudiante
            estudiante_nombre_encargado.removeItemAt(indexRegistroEncargadoEditado);
            estudiante_nombre_encargado.insertItemAt(editado.getNombre(), indexRegistroEncargadoEditado);
            // Ahora inserto los nuevos datos en la Tabla de Encargados
            String[] nuevosDatos = listaEncargados.get(indexRegistroEncargadoEditado).getDatosParaTabla();
            for(int i=0; i<nuevosDatos.length; i++)
                modelEncargados.setValueAt(nuevosDatos[i], indexRegistroEncargadoEditado, i);
            agregar_fila_encargado.setEnabled(true);   // Habilito el botón de Agregar
            eliminar_fila_encargado.setEnabled(true);  // Habilito el botón de Eliminar
            setEnable_campos_encargado(false);
            encargado_dpi.setEditable(true);
            limpiar_campos_encargado(true);     // Borro el contenido de los campos
        }
    }//GEN-LAST:event_editar_fila_encargadoActionPerformed

    private void eliminar_fila_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_fila_encargadoActionPerformed
        int[] rango = tabla_encargados.getSelectedRows();
        if (rango.length == 0)
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else if (rango.length > 1)
            JOptionPane.showMessageDialog(this, "Por favor seleccione un solo registro de la Tabla", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else {
            int idEliminado = listaEncargados.get(rango[0]).getID();
            // Eliminación del registro del ArrayList listaEncargados
            listaEncargados.remove(rango[0]);
            // Eliminación del registro de la Tabla de Encargados
            modelEncargados.removeRow(rango[0]);
            // Eliminación del registro del JComboBox estudiante_nombre_encargado
            estudiante_nombre_encargado.removeItemAt(rango[0]);
            // Actualización del No. y del ID de los registros siguientes:
            int contFilas = listaEncargados.size();
            for(int i=rango[0]; i<contFilas; i++) {
                if (!listaEncargados.get(i).getEstaEnBD())
                    listaEncargados.get(i).setID(listaEncargados.get(i).getID() - 1);
                listaEncargados.get(i).setNum(listaEncargados.get(i).getNum() - 1);
                tabla_encargados.setValueAt(listaEncargados.get(i).getNum(), i, 0);
            }
            contadorIdEncargadosEnBD--;
            // Actualización de los registros Estudiantes afectados por esta eliminación
            for(int i=0; i<listaEstudiantes.size(); i++) {
                RegistroEstudiante iterador = listaEstudiantes.get(i);
                if (iterador.getEncargadoId() == idEliminado) {
                    iterador.setEncargadoId(0); // Indico que no tiene Encargado
                    iterador.setEncargadoNum(-1);
                    iterador.setNombreEncargado("");
                    iterador.setEncargadoEnBD(false);
                    // Ahora modifico estos valores en la Tabla de Estudiantes
                    tabla_estudiantes.setValueAt("", i, 12);    // Eliminación del Nombre del Encargado
                    tabla_estudiantes.setValueAt("", i, 13);    // Eliminación de la Relación del Encargado
                }
            }
            JOptionPane.showMessageDialog(this, "Se eliminó el registro "+(rango[0]+1)+" de la Tabla."
                + "\nTenga en cuenta de que el registro eliminado"
                + "\npudo estar relacionado con otros registros de"
                + "\nla Tabla de Estudiantes, por lo que dichas"
                + "\nrelaciones han quedado vacías."
                + "\n\nPor favor, corriga esta falta de valores", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_eliminar_fila_encargadoActionPerformed

    private void guardar_todos_los_registrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_todos_los_registrosActionPerformed
        // El método retorna false en caso de que ocurra un error de conexión con la Base de Datos, o si hay registros
        // incorrectos. Para el segundo caso, se carga el primer RegistroEstudiante incorrecto para ser editado
        if (!guardar_registros()) {
            // Busco hasta el primer RegistroEstudiante incorrecto (si no hay, no hace nada)
            int cont, cantidad;
            for(cont=0, cantidad=listaEstudiantes.size(); cont<cantidad; cont++) {
                if (listaEstudiantes.get(cont).getEncargadoId() == 0) { // Este registro es incorrecto
                    editar_fila_estudiante.setText("Guardar");
                    agregar_fila_estudiante.setEnabled(false);  // Bloqueo el botón de Agregar
                    eliminar_fila_estudiante.setEnabled(false); // Bloqueo el botón de Eliminar
                    indexRegistroEstudianteEditado = cont;   // Guardo el index que se va a editar
                    cargar_estudiante_en_campos(listaEstudiantes.get(cont));    // Cargo los datos del registro en los campos para ser editados
                    setEnable_campos_estudiante(true);
                    estudiante_codigo_personal.setEditable(false);  // El Código Personal no puede ser editado
                    cont = cantidad;    // Finalizo el ciclo
                }
            }
        }
    }//GEN-LAST:event_guardar_todos_los_registrosActionPerformed

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton agregar_fila_encargado;
    private javax.swing.JButton agregar_fila_estudiante;
    private javax.swing.JButton buscar_codigoPersonal_estudiante;
    private javax.swing.JButton buscar_dpi_encargado;
    private javax.swing.JButton editar_fila_encargado;
    private javax.swing.JButton editar_fila_estudiante;
    private javax.swing.JButton eliminar_fila_encargado;
    private javax.swing.JButton eliminar_fila_estudiante;
    private javax.swing.JTextField encargado_celular;
    private javax.swing.JTextField encargado_direccion;
    private javax.swing.JTextField encargado_dpi;
    private com.toedter.calendar.JDateChooser encargado_fechaNacimiento;
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
    private com.toedter.calendar.JDateChooser estudiante_fechaNacimiento;
    private javax.swing.JComboBox<String> estudiante_municipio;
    private javax.swing.JComboBox<String> estudiante_nombre_encargado;
    private javax.swing.JTextField estudiante_nombres;
    private javax.swing.JTextField estudiante_relacion_encargado;
    private javax.swing.JComboBox<String> estudiante_sexo;
    private javax.swing.JTextField estudiante_tipo_capacidad;
    private javax.swing.JButton guardar_todos_los_registros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel_boton_guardar;
    private javax.swing.JPanel panel_botones_encargado;
    private javax.swing.JPanel panel_botones_estudiante;
    private javax.swing.JPanel panel_datos_encargado;
    private javax.swing.JPanel panel_datos_estudiante;
    private javax.swing.JPanel panel_registros_encargado;
    private javax.swing.JPanel panel_registros_estudiante;
    private javax.swing.JTable tabla_encargados;
    private javax.swing.JTable tabla_estudiantes;
    // End of variables declaration//GEN-END:variables
}
