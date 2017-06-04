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
public class CrearEncargado extends javax.swing.JDialog {
    private Connection conexion;
    private DefaultTableModel modelEncontrados, modelAgregados;
    private RegistroEncargado seleccionado;
    private ArrayList<RegistroEncargado> listaRecientes, listaEncontrados;
    private int contadorIDEncargadoEnBD;
    /**
     * Creates new form CrearEncargado
     */
    public CrearEncargado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public CrearEncargado(java.awt.Frame parent, boolean modal, Connection conexion, ArrayList<RegistroEncargado> listaEncargados) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        this.listaRecientes = listaEncargados;
        this.listaEncontrados = new ArrayList<>();
        this.seleccionado = null;
        
        modelEncontrados = (DefaultTableModel)tabla_encontrados.getModel();
        modelAgregados = (DefaultTableModel)tabla_encargados_agregados.getModel();
        
        // Obtengo lo que será el inicio de los correlativos ID's de todos los registros Encargado que se crearán
        try {
            Statement sentencia = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cEncargado = sentencia.executeQuery("SELECT MAX(Id) Nuevos FROM Encargado");
            cEncargado.next();
            this.contadorIDEncargadoEnBD = cEncargado.getInt("Nuevos") + 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar obtener información de la Base de Datos\n"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Si ocurre un error en este punto, cierro la ventana
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cargar_municipios();    // Obtengo el listado de municipios
        encargado_fechaNacimiento.getJCalendar().setWeekOfYearVisible(false);  // Para no mostrar el número de semana en el Calendario
        definir_ancho_columnas();   // Se define el ahcho de columnas en base a valores obtenidos previamente por pruebas
        
        // Ahora agrego a la Tabla de Recientes los que han sido agregados o seleccionados con anterioridad
        int cont = listaRecientes.size();
        for(int i=0; i<cont; i++)
            modelAgregados.addRow(listaRecientes.get(i).getDatosParaTabla(i+1));
        
        this.setLocationRelativeTo(null);   // Para centrar esta ventana sobre la pantalla.
    }
    /**APROBADO!!!
     * Método que obtiene el listado de los Municipios almacenados en la Base de Datos para insertarlos en el JComboBox
     * que corresponde. Sólo se obtiene los nombres más no los Id's ya que son correlativos entre ambos.
     */
    private void cargar_municipios() {
        encargado_municipio.removeAllItems();
        try {
            Statement sentencia = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cMunicipios = sentencia.executeQuery("SELECT Nombre FROM Municipio");
            while (cMunicipios.next())
                encargado_municipio.addItem(cMunicipios.getString("Nombre"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar obtener los Municipios\n"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Si ocurre un error en este punto, cierro la ventana
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Encontrados (valores definidos en base a pruebas)
        tabla_encontrados.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_encontrados.getColumnModel().getColumn(1).setPreferredWidth(115);
        tabla_encontrados.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabla_encontrados.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabla_encontrados.getColumnModel().getColumn(4).setPreferredWidth(215);
        tabla_encontrados.getColumnModel().getColumn(5).setPreferredWidth(125);
        tabla_encontrados.getColumnModel().getColumn(6).setPreferredWidth(115);
        tabla_encontrados.getColumnModel().getColumn(7).setPreferredWidth(87);
        tabla_encontrados.getColumnModel().getColumn(8).setPreferredWidth(95);
        tabla_encontrados.getColumnModel().getColumn(9).setPreferredWidth(130);
        // Definición del ancho de las columnas para la Tabla Agregados (valores definidos en base a pruebas)
        tabla_encargados_agregados.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_encargados_agregados.getColumnModel().getColumn(1).setPreferredWidth(115);
        tabla_encargados_agregados.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabla_encargados_agregados.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabla_encargados_agregados.getColumnModel().getColumn(4).setPreferredWidth(215);
        tabla_encargados_agregados.getColumnModel().getColumn(5).setPreferredWidth(125);
        tabla_encargados_agregados.getColumnModel().getColumn(6).setPreferredWidth(115);
        tabla_encargados_agregados.getColumnModel().getColumn(7).setPreferredWidth(87);
        tabla_encargados_agregados.getColumnModel().getColumn(8).setPreferredWidth(95);
        tabla_encargados_agregados.getColumnModel().getColumn(9).setPreferredWidth(130);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_datos_encargado = new javax.swing.JPanel();
        panel_botones_encargado = new javax.swing.JPanel();
        crear_registro_encargado = new javax.swing.JButton();
        panel_datos_primarios_encargado = new javax.swing.JPanel();
        encargado_dpi = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        encargado_nombres = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        encargado_apellidos = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        buscar_dpi_encargado = new javax.swing.JButton();
        panel_datos_secundarios_encargado = new javax.swing.JPanel();
        encargado_direccion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        encargado_municipio = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        encargado_fechaNacimiento = new com.toedter.calendar.JDateChooser();
        encargado_telefono = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        encargado_celular = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        etiqueta_encargado_trabajo = new javax.swing.JLabel();
        encargado_trabajo = new javax.swing.JTextField();
        panel_registros_encontrados = new javax.swing.JPanel();
        scroll_tabla_encontrados = new javax.swing.JScrollPane();
        tabla_encontrados = new javax.swing.JTable();
        agregar_registro_encontrado = new javax.swing.JButton();
        panel_registros_recientes = new javax.swing.JPanel();
        scroll_tabla_recientes = new javax.swing.JScrollPane();
        tabla_encargados_agregados = new javax.swing.JTable();
        seleccionar_registro_encargado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel_datos_encargado.setBackground(new java.awt.Color(153, 153, 255));
        panel_datos_encargado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del Encargado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        panel_botones_encargado.setBackground(new java.awt.Color(51, 51, 255));

        crear_registro_encargado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        crear_registro_encargado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        crear_registro_encargado.setText("Crear Registro");
        crear_registro_encargado.setEnabled(false);
        crear_registro_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_registro_encargadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_botones_encargadoLayout = new javax.swing.GroupLayout(panel_botones_encargado);
        panel_botones_encargado.setLayout(panel_botones_encargadoLayout);
        panel_botones_encargadoLayout.setHorizontalGroup(
            panel_botones_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botones_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crear_registro_encargado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_botones_encargadoLayout.setVerticalGroup(
            panel_botones_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_botones_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crear_registro_encargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        encargado_dpi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        encargado_dpi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                encargado_dpiKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel21.setText("DPI:");

        encargado_nombres.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel23.setText("Nombres:");

        encargado_apellidos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel25.setText("Apellidos:");

        buscar_dpi_encargado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar_dpi_encargado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        buscar_dpi_encargado.setText("Buscar coincidencias");
        buscar_dpi_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_dpi_encargadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_datos_primarios_encargadoLayout = new javax.swing.GroupLayout(panel_datos_primarios_encargado);
        panel_datos_primarios_encargado.setLayout(panel_datos_primarios_encargadoLayout);
        panel_datos_primarios_encargadoLayout.setHorizontalGroup(
            panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_primarios_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel25)
                        .addComponent(jLabel23))
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_primarios_encargadoLayout.createSequentialGroup()
                        .addComponent(encargado_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar_dpi_encargado))
                    .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encargado_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        panel_datos_primarios_encargadoLayout.setVerticalGroup(
            panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_primarios_encargadoLayout.createSequentialGroup()
                .addGroup(panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_dpi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_primarios_encargadoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_primarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(encargado_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_primarios_encargadoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buscar_dpi_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );

        encargado_direccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        encargado_direccion.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("Dirección:");

        encargado_municipio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        encargado_municipio.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setText("Municipio:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel24.setText("Fecha de Nacimiento:");

        encargado_fechaNacimiento.setDateFormatString("yyyy-MM-dd");
        encargado_fechaNacimiento.setEnabled(false);
        encargado_fechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        encargado_telefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        encargado_telefono.setEnabled(false);
        encargado_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                encargado_telefonoKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel22.setText("Teléfono:");

        encargado_celular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        encargado_celular.setEnabled(false);
        encargado_celular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                encargado_celularKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel15.setText("Celular:");

        etiqueta_encargado_trabajo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        etiqueta_encargado_trabajo.setText("Trabajo u oficio:");

        encargado_trabajo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        encargado_trabajo.setEnabled(false);

        javax.swing.GroupLayout panel_datos_secundarios_encargadoLayout = new javax.swing.GroupLayout(panel_datos_secundarios_encargado);
        panel_datos_secundarios_encargado.setLayout(panel_datos_secundarios_encargadoLayout);
        panel_datos_secundarios_encargadoLayout.setHorizontalGroup(
            panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_secundarios_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiqueta_encargado_trabajo)
                    .addComponent(jLabel24)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(jLabel22)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encargado_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_datos_secundarios_encargadoLayout.setVerticalGroup(
            panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_secundarios_encargadoLayout.createSequentialGroup()
                .addGroup(panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(encargado_fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_secundarios_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encargado_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta_encargado_trabajo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_datos_encargadoLayout = new javax.swing.GroupLayout(panel_datos_encargado);
        panel_datos_encargado.setLayout(panel_datos_encargadoLayout);
        panel_datos_encargadoLayout.setHorizontalGroup(
            panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel_datos_secundarios_encargado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_datos_primarios_encargado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_botones_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_datos_encargadoLayout.setVerticalGroup(
            panel_datos_encargadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_encargadoLayout.createSequentialGroup()
                .addComponent(panel_datos_primarios_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_datos_secundarios_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_botones_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
        );

        panel_registros_encontrados.setBackground(new java.awt.Color(153, 153, 255));
        panel_registros_encontrados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros que coinciden con la búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tabla_encontrados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_encontrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "DPI", "Nombres", "Apellidos", "Dirección", "Municipio", "Fecha Nacimiento", "Teléfono", "Celular", "Trabajo u oficio"
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
        tabla_encontrados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_encontrados.setRowHeight(25);
        tabla_encontrados.getTableHeader().setResizingAllowed(false);
        tabla_encontrados.getTableHeader().setReorderingAllowed(false);
        scroll_tabla_encontrados.setViewportView(tabla_encontrados);

        agregar_registro_encontrado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregar_registro_encontrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bajar.png"))); // NOI18N
        agregar_registro_encontrado.setText("Agregar a la lista");
        agregar_registro_encontrado.setEnabled(false);
        agregar_registro_encontrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_registro_encontradoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_registros_encontradosLayout = new javax.swing.GroupLayout(panel_registros_encontrados);
        panel_registros_encontrados.setLayout(panel_registros_encontradosLayout);
        panel_registros_encontradosLayout.setHorizontalGroup(
            panel_registros_encontradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_tabla_encontrados, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
            .addGroup(panel_registros_encontradosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(agregar_registro_encontrado))
        );
        panel_registros_encontradosLayout.setVerticalGroup(
            panel_registros_encontradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_registros_encontradosLayout.createSequentialGroup()
                .addComponent(scroll_tabla_encontrados, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agregar_registro_encontrado))
        );

        panel_registros_recientes.setBackground(new java.awt.Color(153, 153, 255));
        panel_registros_recientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros agregados recientemente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tabla_encargados_agregados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_encargados_agregados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "DPI", "Nombres", "Apellidos", "Dirección", "Municipio", "Fecha Nacimiento", "Teléfono", "Celular", "Trabajo u oficio"
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
        tabla_encargados_agregados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_encargados_agregados.setRowHeight(25);
        tabla_encargados_agregados.getTableHeader().setResizingAllowed(false);
        tabla_encargados_agregados.getTableHeader().setReorderingAllowed(false);
        tabla_encargados_agregados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_encargados_agregadosMousePressed(evt);
            }
        });
        scroll_tabla_recientes.setViewportView(tabla_encargados_agregados);

        seleccionar_registro_encargado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        seleccionar_registro_encargado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ok.png"))); // NOI18N
        seleccionar_registro_encargado.setText("Seleccionar registro");
        seleccionar_registro_encargado.setEnabled(false);
        seleccionar_registro_encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionar_registro_encargadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_registros_recientesLayout = new javax.swing.GroupLayout(panel_registros_recientes);
        panel_registros_recientes.setLayout(panel_registros_recientesLayout);
        panel_registros_recientesLayout.setHorizontalGroup(
            panel_registros_recientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_registros_recientesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(seleccionar_registro_encargado))
            .addComponent(scroll_tabla_recientes, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panel_registros_recientesLayout.setVerticalGroup(
            panel_registros_recientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_registros_recientesLayout.createSequentialGroup()
                .addComponent(scroll_tabla_recientes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seleccionar_registro_encargado))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_datos_encargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_registros_encontrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_registros_recientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_registros_encontrados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_registros_recientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panel_datos_encargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción que busca coincidencias de los campos DPI, Nombres o Apellidos del nuevo encargado en la Base de Datos.
     * Muestra los registros que coincinan con al menos un campo en la Tabla de Encontrados (desde donde se pueden agregar
     * a la lista de datos recientes). Previo a ello, evalúa que los datos que se buscarán sean correctos.
     * @param evt 
     */
    private void buscar_dpi_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_dpi_encargadoActionPerformed
        try {
            validar_datos_encargado(true);
            String dpi = encargado_dpi.getText(), nombres = encargado_nombres.getText(), apellidos = encargado_apellidos.getText();
            int cantidad = listaRecientes.size(), index;
            boolean encontradoEnTabla = false;
            // Busco en la tabla de agregados recientemente un registro similar (mismo DPI, Nombres y Apellidos)
            for(index=0; index<cantidad; index++) {
                RegistroEncargado iterador = listaRecientes.get(index);
                if (iterador.getDPI().equals(dpi) && iterador.getNombres().equals(nombres) && iterador.getApellidos().equals(apellidos)) {
                    encontradoEnTabla = true;
                    break;  // Finalizo el ciclo
                }
            }   // 'index' tiene la posición del registro similar
            if (encontradoEnTabla) {    // El registro ya está en la tabla de recientes
                JOptionPane.showMessageDialog(this, "El registro se encuentra en la fila "+(index+1)+" de\nla Tabla Agregados Recientemente", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                tabla_encontrados.setRowSelectionInterval(index, index);
            } else {    // Los campos del registro se buscarán en la Base de Datos
                listaEncontrados.clear();
                modelEncontrados.setRowCount(0);
                // Realizo una consulta hacia la Base de Datos en busca de registros que coincidan
                Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                ResultSet cEncontrados = sentencia.executeQuery("SELECT * FROM Encargado WHERE DPI = '"+dpi+"' OR Nombres = '"+nombres+"' OR Apellidos = '"+apellidos+"'");
                int contador = 0;
                while (cEncontrados.next()) {   // Todos los que encuentra, los agrego a la listaEncontrados
                    contador++;
                    RegistroEncargado encontrado = new RegistroEncargado();
                    encontrado.setID(cEncontrados.getInt("Id"));
                    encontrado.setNombres(cEncontrados.getString("Nombres"));
                    encontrado.setApellidos(cEncontrados.getString("Apellidos"));
                    encontrado.setDireccion(cEncontrados.getString("Direccion"));
                    encontrado.setDPI(cEncontrados.getString("DPI"));
                    encontrado.setFechaNacimiento(cEncontrados.getString("FechaNacimiento"));
                    encontrado.setTrabajo(cEncontrados.getString("Trabajo"));
                    encontrado.setMunicipioId(cEncontrados.getInt("Municipio_Id"));
                    encontrado.setMunicipio(encargado_municipio.getItemAt(encontrado.getMunicipioId() - 1));
                    // Ahora agrego el registro a la listaEncontrados y después a la tabla encontrados
                    listaEncontrados.add(encontrado);
                }   // Hasta aquí se garantiza la extracción de todos los registros que coinciden con al menos un campo de la búsqueda
                // Obtengo los Teléfonos de todos los registros que coincidieron (su implementación en el while cierra la consulta cEncontrados)
                int contEncontrados = listaEncontrados.size();
                for(int i=0; i<contEncontrados; i++) {
                    // Obtengo el teléfono y celular asociado al encargado
                    ResultSet cTelefonos = sentencia.executeQuery("SELECT Telefono FROM Telefono WHERE Encargado_Id = "+listaEncontrados.get(i).getID()+"");
                    if (cTelefonos.next()) listaEncontrados.get(i).setTelefono(cTelefonos.getString("Telefono"));
                    if (cTelefonos.next()) listaEncontrados.get(i).setCelular(cTelefonos.getString("Telefono"));
                    modelEncontrados.addRow(listaEncontrados.get(i).getDatosParaTabla(i+1));
                }
                
                // Se habilitan los campos para completar la información del encargado. En caso de existir, dichos campos
                // se deshabilitaran al agregar el registro a la tabla de agregados recientemente.
                setEnabled_campos_secundarios_encargado(true);  // Habilito los campos para completar la información
                // Mensaje en caso de encontrar o no registros que coincidan
                JOptionPane.showMessageDialog(this,
                        (contador>0)?"Se encontr"+((contador==1)?"ó un registro":"aron "+contador+" registros")+" que posiblemente coincidan con su búsqueda.":"No se encontró algún registro similiar.\n\nPuede crear uno nuevo.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                agregar_registro_encontrado.setEnabled(contador > 0);   // Habilito el botón de agregar el registro encontrado si hay por lo menos un registro
            }
        } catch (ExcepcionDatosIncorrectos ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en datos", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEncargado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar obtener los registros que coinciden\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEncargado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buscar_dpi_encargadoActionPerformed

    private void crear_registro_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_registro_encargadoActionPerformed
        // Antes de crear el registro, evalúo que realmente no exista en la Base de Datos. La manera de comprobarlo es haciendo
        // una consulta en busca del DPI: si existe una coincidencia se concluye que el registro ya existe (omitiendo los campos
        // de nombres y apellidos).
        boolean encargadoYaExiste = false;
        try {
            validar_datos_encargado(false);
            String dpi = encargado_dpi.getText();
            Statement sentencia = this.conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cEncargado = sentencia.executeQuery("SELECT DPI FROM Encargado WHERE DPI = '"+dpi+"'");
            encargadoYaExiste = cEncargado.next();
            if (!encargadoYaExiste) {
                int opcion = JOptionPane.showOptionDialog(this,
                    "Está seguro que desea crear el nuevo registro?\n\nNo podrá revertir esta acción.",
                    "Seleccione una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (opcion == JOptionPane.YES_OPTION) {
                    // Creo un RegistroEncargado con los datos del nuevo encargado
                    RegistroEncargado nuevo = new RegistroEncargado();
                    nuevo.setID(contadorIDEncargadoEnBD);
                    contadorIDEncargadoEnBD++;  // Aumento el contador de ID's asignados a los nuevos encargados
                    nuevo.setNombres(encargado_nombres.getText());
                    nuevo.setApellidos(encargado_apellidos.getText());
                    nuevo.setDireccion(encargado_direccion.getText());
                    nuevo.setDPI(dpi);
                    Calendar fecha = encargado_fechaNacimiento.getCalendar();
                    nuevo.setFechaNacimiento(""+fecha.get(Calendar.YEAR)+"-"+(fecha.get(Calendar.MONTH)+1)+"-"+fecha.get(Calendar.DAY_OF_MONTH));
                    nuevo.setTrabajo(encargado_trabajo.getText());
                    nuevo.setMunicipioId(encargado_municipio.getSelectedIndex() + 1);
                    nuevo.setMunicipio((String)encargado_municipio.getSelectedItem());
                    nuevo.setTelefono(encargado_telefono.getText());
                    nuevo.setCelular(encargado_celular.getText());
                    // Hasta aquí ya se creó el RegistroEncargado con los datos del nuevo Encargado
                    
                    listaRecientes.add(nuevo);  // Agrego el nuevo registro al ArrayList y a la Tabla
                    modelAgregados.addRow(nuevo.getDatosParaTabla(tabla_encargados_agregados.getRowCount() + 1));
                    
                    // Creación del nuevo registro en la Base de Datos
                    String nuevoEncargado = "INSERT INTO Encargado(Nombres, Apellidos, Direccion, DPI, FechaNacimiento, Trabajo, Municipio_Id) VALUES (";
                    nuevoEncargado+= "'"+nuevo.getNombres()+"',";
                    nuevoEncargado+= "'"+nuevo.getApellidos()+"',";
                    nuevoEncargado+= "'"+nuevo.getDireccion()+"',";
                    nuevoEncargado+= "'"+nuevo.getDPI()+"',";
                    nuevoEncargado+= "'"+nuevo.getFechaNacimiento()+"',";
                    nuevoEncargado+= "'"+nuevo.getTrabajo()+"',";
                    nuevoEncargado+= ""+nuevo.getMunicipioId()+")";
                    conexion.prepareStatement(nuevoEncargado).executeUpdate();  // Inserción del nuevo registro Encargado
                    // De momento se crea nuevos teléfonos para cada nuevo Encargado
                    if (nuevo.getTelefono().length() != 0)
                        conexion.prepareStatement("INSERT INTO Telefono(Telefono, Encargado_Id) VALUES('"+nuevo.getTelefono()+"',"+nuevo.getID()+")").executeUpdate();
                    if (nuevo.getCelular().length() != 0)
                        conexion.prepareStatement("INSERT INTO Telefono(Telefono, Encargado_Id) VALUES('"+nuevo.getCelular()+"',"+nuevo.getID()+")").executeUpdate();
                    // HASTA AQUÍ SE GARANTIZA LA CREACIÓN DEL NUEVO REGISTRO
                    
                    limpiar_campos_encargado();     // Limpio los campos de entrada
                    setEnabled_campos_secundarios_encargado(false); // Deshabilito los campos de datos secundarios (para obligar a realizar búsquedas)
                }
            } else
                JOptionPane.showMessageDialog(this, "El registro que intenta crear ya\nexiste en la Base de Datos.\n\nNo se podrá crear el nuevo registro.", "Datos duplicados", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcionDatosIncorrectos ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Conexión fallida con la Base de Datos.\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CrearEncargado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_crear_registro_encargadoActionPerformed
    /**
     * Acción que permitirá agregar un RegistroEncargado que coincide con al menos uno de los campos de la búsqueda a la
     * listaRecientes. Previo a ello, pide confirmación de si el registro buscado está entre un de los que se agregarán a la lista
     * (ya que da la opción de cargar varios registros).
     * @param evt 
     */
    private void agregar_registro_encontradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_registro_encontradoActionPerformed
        int[] rango = tabla_encontrados.getSelectedRows();
        if (rango.length > 0) {
            int opcion = JOptionPane.showOptionDialog(this,
                    "El registro que busca "+((rango.length>1)?"está entre los "+(rango.length)+" registros":"es el registro")+" que desea agregar?"
                            + "\n\nTome en cuenta de que si está pero selecciona NO,\nno se podrá crear el nuevo registro más adelnate.",
                    "Seleccione una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (opcion != JOptionPane.CLOSED_OPTION) {  // Al cerrar el OptionDialog, no se realiza la acción
                // Agrego el(los) registro(s) seleccionado(s) a la tabla de Recientes, siempre que los registros no estén agregados
                int contAnterior = listaRecientes.size();
                for(int selected=0; selected<rango.length; selected++) {
                    boolean yaExiste = false;
                    for(int itRecientes=0; itRecientes<contAnterior; itRecientes++) {
                        if (listaRecientes.get(itRecientes).getID() == listaEncontrados.get(rango[selected]).getID()) {
                            yaExiste = true;
                            itRecientes = contAnterior;
                        }
                    }
                    if (!yaExiste) {    // Si aún no existe, lo agrego
                        RegistroEncargado iterador = listaEncontrados.remove(rango[selected]);
                        listaRecientes.add(iterador);
                        modelAgregados.addRow(iterador.getDatosParaTabla(tabla_encargados_agregados.getRowCount() + 1));
                        // Debido a que elimino el registro de listaEncontrados, debo actualizar los índices seleccionados en rango
                        for(int j=selected+1; j<rango.length; j++)
                            rango[j]--;
                    }
                }   // Hasta aquí se garantiza la extracción de los registros seleccioandos
                // Ahora actualizo la información de la tabla Encontrados
                modelEncontrados.setRowCount(0);
                rango[0] = listaEncontrados.size(); // Reutilizo el array para almacenar la cantidad de items en listaEncontrados
                for(int i=0; i<rango[0]; i++)
                    modelEncontrados.addRow(listaEncontrados.get(i).getDatosParaTabla(i+1));
                // Ahora evaluo si el usuario confirma que el registro está entre uno de los que seleccionó
                if (opcion == JOptionPane.YES_OPTION) { // Aquí limpio los campos y deshabilitos los secundarios
                    limpiar_campos_encargado();
                    setEnabled_campos_secundarios_encargado(false);
                }   // Si selecciona NO, se espera completar los datos para crear el nuevo registro
            }
        } else
            JOptionPane.showMessageDialog(this, "Seleccione al menos un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_agregar_registro_encontradoActionPerformed
    /**
     * Acción que permite seleccionar un RegistroEncargado para asignarselo al Estudiante que se creará.
     * @param evt 
     */
    private void seleccionar_registro_encargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionar_registro_encargadoActionPerformed
        int[] rango = tabla_encargados_agregados.getSelectedRows();
        if (rango.length == 1) {
            seleccionado = listaRecientes.get(rango[0]);    // Guardo el registro seleccionado
            this.dispose(); // Cierro la ventana.
        } else
            JOptionPane.showMessageDialog(this, "Seleccione "+((rango.length==0)?"al menos":"sólo")+" un registro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_seleccionar_registro_encargadoActionPerformed

    private void tabla_encargados_agregadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_encargados_agregadosMousePressed
        seleccionar_registro_encargado.setEnabled(true);
    }//GEN-LAST:event_tabla_encargados_agregadosMousePressed
    /**
     * Eventos para controlar que en los campos DPI, Teléfono y Celular se ingresen sólo dígitos, hasta cierta cantidad.
     */
    private void encargado_dpiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encargado_dpiKeyTyped
        // Se acepta la tecla si es un dígito y si hay menos de 13 dígitos en el DPI
        if (!Pattern.compile("\\d").matcher(String.valueOf(evt.getKeyChar())).matches() || encargado_dpi.getText().length() == 13)
            evt.consume();
    }//GEN-LAST:event_encargado_dpiKeyTyped
    private void encargado_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encargado_telefonoKeyTyped
        // Se acepta la tecla si es un dígito y si hay menos de 8 dígitos en el Número de Teléfono
        if (!Pattern.compile("\\d").matcher(String.valueOf(evt.getKeyChar())).matches() || encargado_telefono.getText().length() == 8)
            evt.consume();
    }//GEN-LAST:event_encargado_telefonoKeyTyped
    private void encargado_celularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encargado_celularKeyTyped
        // Se acepta la tecla si es un dígito y si hay menos de 8 dígitos en el Número de Celular
        if (!Pattern.compile("\\d").matcher(String.valueOf(evt.getKeyChar())).matches() || encargado_celular.getText().length() == 8)
            evt.consume();
    }//GEN-LAST:event_encargado_celularKeyTyped
    
    /**APROBADO!!!
     * Función que valida que los datos del nuevo Encargado sean correctos. Respecto al DPI, Teléfono y Celular, hay eventos
     * para cada caso en el que se permite sólo ingresar dígitos; lo único a evaluar es que no estén vacíos.
     * @param paraBuscar booleano que indica si la validación se hace a la hora de buscar coincidencias en la Base de Datos.
     * Si los datos son para buscar coincidencias, no se necesitan valores en los campos secundarios (pues estarían bloqueados).
     * @throws ExcepcionDatosIncorrectos 
     */
    private void validar_datos_encargado(boolean paraBuscar) throws ExcepcionDatosIncorrectos {
        if (encargado_dpi.getText().length() != 13)
            throw new ExcepcionDatosIncorrectos("El DPI debe tener 13 dígitos.");
        if (encargado_nombres.getText().length() == 0 || encargado_apellidos.getText().length() == 0)
            throw new ExcepcionDatosIncorrectos("Los Nombres y Apellidos no pueden ser nulos.");
        if (!paraBuscar) {
            if (encargado_direccion.getText().length() == 0)
                throw new ExcepcionDatosIncorrectos("No se ha especificado la Dirección.");
            if (encargado_municipio.getSelectedIndex() == -1)
                throw new ExcepcionDatosIncorrectos("No se ha especificado el Municipio.");
            if (encargado_fechaNacimiento.getDate() == null)
                throw new ExcepcionDatosIncorrectos("No se ha especificado la Fecha de nacimiento.");
            if (encargado_telefono.getText().length()!=0 && encargado_telefono.getText().length()!=8)
                throw new ExcepcionDatosIncorrectos("El Número de Teléfono debe tener 8 dígitos.");
            if (encargado_celular.getText().length()!=0 && encargado_celular.getText().length()!=8)
                throw new ExcepcionDatosIncorrectos("El Número de Celular debe tener 8 dígitos.");
            // El Trabajo no es campo obligatorio
        }
    }
    private void setEnabled_campos_secundarios_encargado(boolean valorEnabled) {
        encargado_direccion.setEnabled(valorEnabled);
        encargado_municipio.setEnabled(valorEnabled);
        encargado_fechaNacimiento.setEnabled(valorEnabled);
        encargado_telefono.setEnabled(valorEnabled);
        encargado_celular.setEnabled(valorEnabled);
        encargado_trabajo.setEnabled(valorEnabled);
        crear_registro_encargado.setEnabled(valorEnabled);
    }
    private void limpiar_campos_encargado() {
        encargado_dpi.setText("");
        encargado_nombres.setText("");
        encargado_apellidos.setText("");
        encargado_direccion.setText("");
        encargado_municipio.setSelectedIndex(-1);
        encargado_fechaNacimiento.setDate(null);
        encargado_telefono.setText("");
        encargado_celular.setText("");
        encargado_trabajo.setText("");
    }
    public RegistroEncargado getEncargado() { return seleccionado; }
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
            java.util.logging.Logger.getLogger(CrearEncargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearEncargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearEncargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearEncargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CrearEncargado dialog = new CrearEncargado(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton agregar_registro_encontrado;
    private javax.swing.JButton buscar_dpi_encargado;
    private javax.swing.JButton crear_registro_encargado;
    private javax.swing.JTextField encargado_apellidos;
    private javax.swing.JTextField encargado_celular;
    private javax.swing.JTextField encargado_direccion;
    private javax.swing.JTextField encargado_dpi;
    private com.toedter.calendar.JDateChooser encargado_fechaNacimiento;
    private javax.swing.JComboBox<String> encargado_municipio;
    private javax.swing.JTextField encargado_nombres;
    private javax.swing.JTextField encargado_telefono;
    private javax.swing.JTextField encargado_trabajo;
    private javax.swing.JLabel etiqueta_encargado_trabajo;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel panel_botones_encargado;
    private javax.swing.JPanel panel_datos_encargado;
    private javax.swing.JPanel panel_datos_primarios_encargado;
    private javax.swing.JPanel panel_datos_secundarios_encargado;
    private javax.swing.JPanel panel_registros_encontrados;
    private javax.swing.JPanel panel_registros_recientes;
    private javax.swing.JScrollPane scroll_tabla_encontrados;
    private javax.swing.JScrollPane scroll_tabla_recientes;
    private javax.swing.JButton seleccionar_registro_encargado;
    private javax.swing.JTable tabla_encargados_agregados;
    private javax.swing.JTable tabla_encontrados;
    // End of variables declaration//GEN-END:variables
}
