/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_Estudiante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase utilizada para visualizar y modificar las Notas, de todos los cursos, de un Estudiante específico.
 * @author Wilson Xicará
 */
public class Notas extends javax.swing.JDialog {
    private Connection conexion;
    private RegistroEstudiante estudiante;
    private int idCicloEscolar, indexNotaEditada;
    private DefaultTableModel modelNotas;
    private ArrayList<RegistroNota> listaNotas;
    /**
     * Creates new form Notas
     * @param parent
     * @param modal
     */
    public Notas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /**APROBADO!!!
     * Constructor que inicializa la ventana donde se podrá visualizar las notas del Estudiante correspondiente al Ciclo Escolar
     * especificacos como parámetros. No se pasa el ID del grado como parámetro pues un estudiante tiene sólo una AsignacionEST
     * por cada Ciclo Escolar.
     * @param parent
     * @param modal
     * @param conexion
     * @param estudiante
     * @param cicloEscolarId 
     */
    public Notas(java.awt.Frame parent, boolean modal, Connection conexion, RegistroEstudiante estudiante, int cicloEscolarId) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        this.estudiante = estudiante;
        this.idCicloEscolar = cicloEscolarId;
        this.modelNotas = (DefaultTableModel) tabla_notas.getModel();
        listaNotas = new ArrayList<>();
        this.setTitle("Notas de"+((estudiante.getSexo().equals("F"))?" la":"l")+" estudiante "+estudiante.getNombres()+" "+estudiante.getApellidos());
        
        /* Inicio la carga de las notas asociadas al Estudiante y al Ciclo Escolar pasados como parámetros */
        try {
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cNotas = sentencia.executeQuery("SELECT AsignacionEst.Id IdAsignacion, Notas.Id IdNota, Notas.Nota1, Notas.Nota2, Notas.Nota3, Notas.Nota4, Notas.NotaRecuperacion, Notas.NotaFinal, Curso.Id IdCurso, Curso.Nombre Curso FROM AsignacionEST "
                    + "INNER JOIN Notas ON AsignacionEst.Id = Notas.AsignacionEST_Id "
                    + "INNER JOIN Curso ON Notas.Curso_Id = Curso.Id "
                    + "WHERE AsignacionEST.Estudiante_Id = "+estudiante.getID()+" AND AsignacionEst.CicloEscolar_Id = "+cicloEscolarId+"");
            // Inicio la extracción de las Notas encontradas
            int contador = 0;
            while(cNotas.next()) {
                contador++;
                RegistroNota nuevo = new RegistroNota();
                nuevo.setIdNota(cNotas.getInt("IdNota"));
                nuevo.setIdCurso(cNotas.getInt("IdCurso"));
                nuevo.setNota1(cNotas.getFloat("Nota1"));
                nuevo.setNota2(cNotas.getFloat("Nota2"));
                nuevo.setNota3(cNotas.getFloat("Nota3"));
                nuevo.setNota4(cNotas.getFloat("Nota4"));
                nuevo.setNotaRecuperacion(cNotas.getFloat("NotaRecuperacion"));
                nuevo.setNotaFinal(cNotas.getFloat("NotaFinal"));
                nuevo.setCurso(cNotas.getString("Curso"));
                
                // Agregación de la nota extraida al ArrayList y a la Tabla de Notas
                listaNotas.add(nuevo);
                modelNotas.addRow(nuevo.getDatosParaTabla(contador));
            }   // Hasta aquí se garantiza la extracción y muestra de todas las Notas
            // Obtengo información del Ciclo Escolar y del Grado del Estudiante que se muestra en la ventana
            ResultSet cInfo = sentencia.executeQuery("SELECT AsignacionEST.CicloEscolar_Id idCiclo, CicloEscolar.Anio, AsignacionEST.Grado_Id idGrado, Grado.Nombre, Grado.Seccion FROM AsignacionEST "
                    + "INNER JOIN CicloEscolar ON CicloEscolar_Id = CicloEscolar.Id "
                    + "INNER JOIN Grado ON Grado_Id = Grado.Id "
                    + "WHERE AsignacionEST.Estudiante_Id = "+estudiante.getID()+" AND AsignacionEst.CicloEscolar_Id = "+cicloEscolarId+"");
            if (cInfo.next())
                this.etiqueta_nombre_estudiante.setText("Estudiante: "+estudiante.getNombres()+" "+estudiante.getApellidos()
                        +" ("+cInfo.getString("Nombre")+" "+cInfo.getString("Seccion")+", "+cInfo.getString("Anio")+").");
            // Las notas pueden editarse sí y sólo si el ciclo escolar no ha sido cerrado (está vigente)
            ResultSet cCicloVigente = sentencia.executeQuery("SELECT Cerrado FROM CicloEscolar WHERE Id = "+idCicloEscolar);
            cCicloVigente.next();
            editar_notas.setEnabled(!listaNotas.isEmpty() && cCicloVigente.getInt(1) == 0);
            if (idCicloEscolar !=cCicloVigente.getInt(1))
                etiqueta_notas.setText("Notas: (Las notas no pueden ser editadas pues corresponden a un Ciclo Escolar anterior)");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al intentar extraer datos.\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
        }
        definir_ancho_columnas();
        
        this.setLocationRelativeTo(null);   // Para centrar esta ventana sobre la pantalla.
        ver_ancho_columnas_de_tabla.setVisible(false);  // Botón que sirve para obtener el ancho actual de las columnas en los JTable
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_notas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_notas = new javax.swing.JTable();
        etiqueta_notas = new javax.swing.JLabel();
        panel_nombre_estudiante = new javax.swing.JPanel();
        etiqueta_nombre_estudiante = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nota1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nota2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nota3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nota4 = new javax.swing.JTextField();
        nota_recuperacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nota_final = new javax.swing.JTextField();
        editar_notas = new javax.swing.JButton();
        check_nota_recuperacion = new javax.swing.JCheckBox();
        guardar_cambios = new javax.swing.JButton();
        ver_ancho_columnas_de_tabla = new javax.swing.JButton();
        calcular_nota_final = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel_notas.setBackground(new java.awt.Color(153, 153, 255));

        tabla_notas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_notas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Curso", "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Nota Recuperación", "Nota Final"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_notas.setRowHeight(25);
        tabla_notas.getTableHeader().setResizingAllowed(false);
        tabla_notas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla_notas);

        etiqueta_notas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        etiqueta_notas.setText("Notas:");

        javax.swing.GroupLayout panel_notasLayout = new javax.swing.GroupLayout(panel_notas);
        panel_notas.setLayout(panel_notasLayout);
        panel_notasLayout.setHorizontalGroup(
            panel_notasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_notasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_notasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addGroup(panel_notasLayout.createSequentialGroup()
                        .addComponent(etiqueta_notas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_notasLayout.setVerticalGroup(
            panel_notasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_notasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta_notas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_nombre_estudiante.setBackground(new java.awt.Color(153, 153, 255));

        etiqueta_nombre_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        etiqueta_nombre_estudiante.setText("Estudiante:");

        javax.swing.GroupLayout panel_nombre_estudianteLayout = new javax.swing.GroupLayout(panel_nombre_estudiante);
        panel_nombre_estudiante.setLayout(panel_nombre_estudianteLayout);
        panel_nombre_estudianteLayout.setHorizontalGroup(
            panel_nombre_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_nombre_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta_nombre_estudiante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_nombre_estudianteLayout.setVerticalGroup(
            panel_nombre_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_nombre_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta_nombre_estudiante)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        nota1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nota1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nota1.setEnabled(false);
        nota1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nota1FocusLost(evt);
            }
        });
        nota1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nota1KeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nota 1:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nota 2:");

        nota2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nota2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nota2.setEnabled(false);
        nota2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nota2FocusLost(evt);
            }
        });
        nota2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nota2KeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nota 3:");

        nota3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nota3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nota3.setEnabled(false);
        nota3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nota3FocusLost(evt);
            }
        });
        nota3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nota3KeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nota 4:");

        nota4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nota4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nota4.setEnabled(false);
        nota4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nota4FocusLost(evt);
            }
        });
        nota4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nota4KeyTyped(evt);
            }
        });

        nota_recuperacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nota_recuperacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nota_recuperacion.setEnabled(false);
        nota_recuperacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nota_recuperacionFocusLost(evt);
            }
        });
        nota_recuperacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nota_recuperacionKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("NOTA FINAL:");

        nota_final.setEditable(false);
        nota_final.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nota_final.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nota_final.setEnabled(false);

        editar_notas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editar_notas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit.png"))); // NOI18N
        editar_notas.setText("Editar notas");
        editar_notas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_notasActionPerformed(evt);
            }
        });

        check_nota_recuperacion.setBackground(new java.awt.Color(153, 153, 255));
        check_nota_recuperacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        check_nota_recuperacion.setText("Nota Recuperación:");
        check_nota_recuperacion.setEnabled(false);
        check_nota_recuperacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                check_nota_recuperacionItemStateChanged(evt);
            }
        });

        guardar_cambios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guardar_cambios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        guardar_cambios.setText("Guardar cambios y salir");
        guardar_cambios.setEnabled(false);
        guardar_cambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_cambiosActionPerformed(evt);
            }
        });

        ver_ancho_columnas_de_tabla.setText("Longitud");
        ver_ancho_columnas_de_tabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_ancho_columnas_de_tablaActionPerformed(evt);
            }
        });

        calcular_nota_final.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/adelante.PNG"))); // NOI18N
        calcular_nota_final.setEnabled(false);
        calcular_nota_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcular_nota_finalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nota1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nota2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nota3, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nota4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(nota_recuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check_nota_recuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addComponent(calcular_nota_final, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(nota_final, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(editar_notas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardar_cambios)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ver_ancho_columnas_de_tabla)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editar_notas)
                    .addComponent(guardar_cambios))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check_nota_recuperacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota_recuperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nota_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calcular_nota_final))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ver_ancho_columnas_de_tabla))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_notas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_nombre_estudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_nombre_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_notas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**APROBADO!!!
     * Acción que permite habilitar la edición de la Nota seleccionada de la Tabla de Notas. Para ello, carga las notas del
     * registro en los JTextFiel correspondiente para modificar dicho valor. Todas las modificaciones se hacen temporalmente
     * en el ArrayList<RegistroNota> y al final dichos cambios son escritos en la Base de Datos
     * @param evt 
     */
    private void editar_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_notasActionPerformed
        int rango[] = tabla_notas.getSelectedRows();
        if ("Editar notas".equals(editar_notas.getText())) {    // Se habilitará la edición de las notas
            if (rango.length == 0)
                JOptionPane.showMessageDialog(this, "No ha seleccionado una Nota para editar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            else if (rango.length > 1)
                JOptionPane.showMessageDialog(this, "Por favor, seleccione sólo una Nota", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            else {  // Se modificará la Nota seleccionada
                indexNotaEditada = rango[0];   // Guardo el índice de la Nota modificada
                RegistroNota iterador = listaNotas.get(rango[0]);
                // Cargo las notas a los campos correspondientes
                nota1.setText(iterador.getNota1()==-1 ? "0.0" : Float.toString(iterador.getNota1()));
                nota2.setText(iterador.getNota2()==-1 ? "0.0" : Float.toString(iterador.getNota2()));
                nota3.setText(iterador.getNota3()==-1 ? "0.0" : Float.toString(iterador.getNota3()));
                nota4.setText(iterador.getNota4()==-1 ? "0.0" : Float.toString(iterador.getNota4()));
                check_nota_recuperacion.setSelected(iterador.getNotaRecuperacion()!=-1);
                nota_recuperacion.setText((iterador.getNotaRecuperacion()!=-1) ? Float.toString(iterador.getNotaRecuperacion()) : "");
                nota_final.setText(iterador.getNotaFinal()==-1 ? "0.0" : Float.toString(iterador.getNotaFinal()));
                setEnable_campos_notas(true);   // Habilito los campos para poder modificarlos
                
                editar_notas.setText("Aceptar");    // Cambio el texto para indicar el guardado de los datos
                editar_notas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ok.png")));
                guardar_cambios.setEnabled(false);  // Inhabilito el botón de guardado
            }
        } else {    // Se guadarán los cambios de la nota editada
            try {
                validar_notas();    // Valido que las notas sean correctas
                RegistroNota editado = listaNotas.get(indexNotaEditada);
                editado.setNota1(Float.parseFloat(nota1.getText()));
                editado.setNota2(Float.parseFloat(nota2.getText()));
                editado.setNota3(Float.parseFloat(nota3.getText()));
                editado.setNota4(Float.parseFloat(nota4.getText()));
                editado.setNotaRecuperacion((check_nota_recuperacion.isSelected()) ? Float.parseFloat(nota_recuperacion.getText()) : -1);
                // Cálculo de la Nota Final:
                editado.setNotaFinal((check_nota_recuperacion.isSelected()) ?
                        editado.getNotaRecuperacion() : // Es la Nota de Recuperación
                        editado.getPromedioNotas());   // Es el promedio de las Notas 1-4
                editado.setEditado(true);   // Marco el registro como Editado
                // Actualizo las notas en la tabla
                tabla_notas.setValueAt(""+editado.getNota1(), indexNotaEditada, 2);
                tabla_notas.setValueAt(""+editado.getNota2(), indexNotaEditada, 3);
                tabla_notas.setValueAt(""+editado.getNota3(), indexNotaEditada, 4);
                tabla_notas.setValueAt(""+editado.getNota4(), indexNotaEditada, 5);
                tabla_notas.setValueAt((editado.getNotaRecuperacion()!=-1 ? ""+editado.getNotaRecuperacion() : ""), indexNotaEditada, 6);
//                tabla_notas.setValueAt(((check_nota_recuperacion.isSelected()) ? ""+editado.getNotaRecuperacion() : ""), indexNotaEditada, 6);
                tabla_notas.setValueAt(""+editado.getNotaFinal(), indexNotaEditada, 7);
                
                limpiar_campos_notas();         // Limpio los campos de notas
                setEnable_campos_notas(false);  // Inhabilito los campos de notas
                
                editar_notas.setText("Editar notas");   // Cambio el texto del botón
                editar_notas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit.png")));
                guardar_cambios.setEnabled(true);   // Habilito el botón para poder guardar los cambios
            } catch (ExcepcionDatosIncorrectos ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editar_notasActionPerformed

    private void check_nota_recuperacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_check_nota_recuperacionItemStateChanged
        nota_recuperacion.setEnabled(check_nota_recuperacion.isSelected());
    }//GEN-LAST:event_check_nota_recuperacionItemStateChanged
    /**APROBADO!!!
     * Acción que permite guardar todos los cambios de las notas en la Base de Datos. Para ello, evalua la propiedad
     * RegistroNota.isEditado() y actualiza el registro de la BD si dicha propiedad es true.
     * @param evt 
     */
    private void guardar_cambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_cambiosActionPerformed
        // Inicio la actualización de todas las notas que fueron editadas
        int tamaño = listaNotas.size();
        int cont = 0;   // Contador de cuántas notas fueron modificadas
        boolean errorEnNotas = false;
        for(int i=0; i<tamaño; i++) {
            RegistroNota iterador = listaNotas.get(i);
            if (iterador.isEditado()) {
                try {
                    // Esta nota fue editada, lo actualizo en la BD
                    String actualizar = "UPDATE Notas SET ";
                    actualizar+= "Nota1 = "+iterador.getNota1()+", ";
                    actualizar+= "Nota2 = "+iterador.getNota2()+", ";
                    actualizar+= "Nota3 = "+iterador.getNota3()+", ";
                    actualizar+= "Nota4 = "+iterador.getNota4()+", ";
                    actualizar+= "NotaRecuperacion = "+iterador.getNotaRecuperacion()+", ";
                    actualizar+= "NotaFinal = "+iterador.getNotaFinal()+" ";
                    actualizar+= "WHERE Id = "+iterador.getIdNota()+"";
                    conexion.prepareStatement(actualizar).executeUpdate();  // Actualización del registro Estudiante en la Base de Datos
                    
                    // Habilito la opción de volver a editar la nota (en caso de no salir al guardar todo)
                    iterador.setEditado(false);
                    cont++; // Aumento el contador de notas guardadas con éxito
                } catch (SQLException ex) {
                    errorEnNotas = true;
                    JOptionPane.showMessageDialog(this, "Error al intentar actualizar la Nota No. "+(i+1)+"\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Notas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        guardar_cambios.setEnabled(false);  // Inhabilito este botón hasta que se vuelva a modificar otra nota
        if (cont != 0 && !errorEnNotas)
            JOptionPane.showMessageDialog(this, "Se han guardado los cambios de "+cont+" Nota"+((cont==1)?"":"s"), "Información", JOptionPane.INFORMATION_MESSAGE);
        if (!errorEnNotas)
            this.dispose(); // En caso de no haber errores, cierro la ventana
    }//GEN-LAST:event_guardar_cambiosActionPerformed

    private void ver_ancho_columnas_de_tablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_ancho_columnas_de_tablaActionPerformed
        System.out.print("\nLongitudes de Estudiante en Clase <Notas>: ");
        for(int i=0; i<tabla_notas.getColumnCount(); i++)
        System.out.print(tabla_notas.getColumnModel().getColumn(i).getWidth()+",");
    }//GEN-LAST:event_ver_ancho_columnas_de_tablaActionPerformed
    /**APROBADO!!!
     * Acción que calcula la Nota Final como promedio de las Notas 1-4, o como la nota de recuperación.
     * @param evt 
     */
    private void calcular_nota_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcular_nota_finalActionPerformed
        if (check_nota_recuperacion.isSelected())
            nota_final.setText(nota_recuperacion.getText());
        else {
            if (nota1.getText().length()==0 || nota2.getText().length()==0 || nota3.getText().length()==0 || nota4.getText().length()==0)
                JOptionPane.showMessageDialog(this, "No se puede calcular la Nota Final sin las 4 notas anteriores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            else
                nota_final.setText(""+(Float.parseFloat(nota1.getText()) + Float.parseFloat(nota2.getText()) + Float.parseFloat(nota3.getText()) + Float.parseFloat(nota4.getText()))/4);
        }
    }//GEN-LAST:event_calcular_nota_finalActionPerformed
    /**
     * Eventos que controlan que las Notas que se ingresen sean dígitos solamente, y que estén en el rango de [0.0,100.0].
     * Dichos eventos se lanzan antes de insertar la tecla.
     */
    private void nota1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nota1KeyTyped
        char teclaPulsada = evt.getKeyChar();
        if (teclaPulsada == '.') {  // Cuando se presiona el punto (para una cantidad decimal)
            if (nota1.getText().contains("."))  // Se rechaza la tecla si la cantidad ya contiene un punto decimal
                evt.consume();
            else if (nota1.getText().length() == 0) // Se concatena un 0 al inicio si la caja está vacía
                nota1.setText("0");
        } else if (!Pattern.compile("\\d").matcher(String.valueOf(teclaPulsada)).matches())
            evt.consume();  // No se acepta la tecla si no es un dígito
        else {  // Si es un dígito
            if (nota1.getText().length()!=0 && !nota1.getText().contains(".") && Integer.parseInt(nota1.getText())==0 && teclaPulsada=='0')
                evt.consume();  // Se rechaza la tecla en caso de querer escribir varios ceros seguidos
            else if (Float.parseFloat(nota1.getText()+String.valueOf(teclaPulsada)) > 100.0)
                evt.consume();  // Se rechaza la tecla si hace que la nota sea mayor a 100.0
        }
    }//GEN-LAST:event_nota1KeyTyped
    private void nota2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nota2KeyTyped
        char teclaPulsada = evt.getKeyChar();
        if (teclaPulsada == '.') {  // Cuando se presiona el punto (para una cantidad decimal)
            if (nota2.getText().contains("."))  // Se rechaza la tecla si la cantidad ya contiene un punto decimal
                evt.consume();
            else if (nota2.getText().length() == 0) // Se concatena un 0 al inicio si la caja está vacía
                nota2.setText("0");
        } else if (!Pattern.compile("\\d").matcher(String.valueOf(teclaPulsada)).matches())
            evt.consume();  // No se acepta la tecla si no es un dígito
        else {  // Si es un dígito
            if (nota2.getText().length()!=0 && !nota2.getText().contains(".") && Integer.parseInt(nota2.getText())==0 && teclaPulsada=='0')
                evt.consume();  // Se rechaza la tecla en caso de querer escribir varios ceros seguidos
            else if (Float.parseFloat(nota2.getText()+String.valueOf(teclaPulsada)) > 100.0)
                evt.consume();  // Se rechaza la tecla si hace que la nota sea mayor a 100.0
        }
    }//GEN-LAST:event_nota2KeyTyped
    private void nota3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nota3KeyTyped
        char teclaPulsada = evt.getKeyChar();
        if (teclaPulsada == '.') {  // Cuando se presiona el punto (para una cantidad decimal)
            if (nota3.getText().contains("."))  // Se rechaza la tecla si la cantidad ya contiene un punto decimal
                evt.consume();
            else if (nota3.getText().length() == 0) // Se concatena un 0 al inicio si la caja está vacía
                nota3.setText("0");
        } else if (!Pattern.compile("\\d").matcher(String.valueOf(teclaPulsada)).matches())
            evt.consume();  // No se acepta la tecla si no es un dígito
        else {  // Si es un dígito
            if (nota3.getText().length()!=0 && !nota3.getText().contains(".") && Integer.parseInt(nota3.getText())==0 && teclaPulsada=='0')
                evt.consume();  // Se rechaza la tecla en caso de querer escribir varios ceros seguidos
            else if (Float.parseFloat(nota3.getText()+String.valueOf(teclaPulsada)) > 100.0)
                evt.consume();  // Se rechaza la tecla si hace que la nota sea mayor a 100.0
        }
    }//GEN-LAST:event_nota3KeyTyped
    private void nota4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nota4KeyTyped
        char teclaPulsada = evt.getKeyChar();
        if (teclaPulsada == '.') {  // Cuando se presiona el punto (para una cantidad decimal)
            if (nota4.getText().contains("."))  // Se rechaza la tecla si la cantidad ya contiene un punto decimal
                evt.consume();
            else if (nota4.getText().length() == 0) // Se concatena un 0 al inicio si la caja está vacía
                nota4.setText("0");
        } else if (!Pattern.compile("\\d").matcher(String.valueOf(teclaPulsada)).matches())
            evt.consume();  // No se acepta la tecla si no es un dígito
        else {  // Si es un dígito
            if (nota4.getText().length()!=0 && !nota4.getText().contains(".") && Integer.parseInt(nota4.getText())==0 && teclaPulsada=='0')
                evt.consume();  // Se rechaza la tecla en caso de querer escribir varios ceros seguidos
            else if (Float.parseFloat(nota4.getText()+String.valueOf(teclaPulsada)) > 100.0)
                evt.consume();  // Se rechaza la tecla si hace que la nota sea mayor a 100.0
        }
    }//GEN-LAST:event_nota4KeyTyped
    private void nota_recuperacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nota_recuperacionKeyTyped
        char teclaPulsada = evt.getKeyChar();
        if (teclaPulsada == '.') {  // Cuando se presiona el punto (para una cantidad decimal)
            if (nota_recuperacion.getText().contains("."))  // Se rechaza la tecla si la cantidad ya contiene un punto decimal
                evt.consume();
            else if (nota_recuperacion.getText().length() == 0) // Se concatena un 0 al inicio si la caja está vacía
                nota_recuperacion.setText("0");
        } else if (!Pattern.compile("\\d").matcher(String.valueOf(teclaPulsada)).matches())
            evt.consume();  // No se acepta la tecla si no es un dígito
        else {  // Si es un dígito
            if (nota_recuperacion.getText().length()!=0 && !nota_recuperacion.getText().contains(".") && Integer.parseInt(nota_recuperacion.getText())==0 && teclaPulsada=='0')
                evt.consume();  // Se rechaza la tecla en caso de querer escribir varios ceros seguidos
            else if (Float.parseFloat(nota_recuperacion.getText()+String.valueOf(teclaPulsada)) > 100.0)
                evt.consume();  // Se rechaza la tecla si hace que la nota sea mayor a 100.0
        }
    }//GEN-LAST:event_nota_recuperacionKeyTyped
    /**
     * Eventos que controlan cuando alguna de las cajas de notas pierde el focus. Si al ocurrir esto, el texto tiene la
     * el formato n. (donde n es un entero entre [0,100]) entonces se concatena '0' al final.
     */
    private void nota1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nota1FocusLost
        if (nota1.getText().length()==0 || (nota1.getText().length()!=0 && Float.parseFloat(nota1.getText())==0.0))
            nota1.setText("0.0");
        else if (nota1.getText().indexOf(".")==(nota1.getText().length()-1))
            nota1.setText(nota1.getText()+"0");
    }//GEN-LAST:event_nota1FocusLost
    private void nota2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nota2FocusLost
        if (nota2.getText().length()==0 || (nota2.getText().length()!=0 && Float.parseFloat(nota2.getText())==0.0))
            nota2.setText("0.0");
        else if (nota2.getText().indexOf(".")==(nota2.getText().length()-1))
            nota2.setText(nota2.getText()+"0");
    }//GEN-LAST:event_nota2FocusLost
    private void nota3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nota3FocusLost
        if (nota3.getText().length()==0 || (nota3.getText().length()!=0 && Float.parseFloat(nota3.getText())==0.0))
            nota3.setText("0.0");
        else if (nota3.getText().indexOf(".")==(nota3.getText().length()-1))
            nota3.setText(nota3.getText()+"0");
    }//GEN-LAST:event_nota3FocusLost
    private void nota4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nota4FocusLost
        if (nota4.getText().length()==0 || (nota4.getText().length()!=0 && Float.parseFloat(nota4.getText())==0.0))
            nota4.setText("0.0");
        else if (nota4.getText().indexOf(".")==(nota4.getText().length()-1))
            nota4.setText(nota4.getText()+"0");
    }//GEN-LAST:event_nota4FocusLost
    private void nota_recuperacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nota_recuperacionFocusLost
        if (nota_recuperacion.getText().indexOf(".")==(nota_recuperacion.getText().length()-1))
            nota_recuperacion.setText(nota_recuperacion.getText()+"0");
    }//GEN-LAST:event_nota_recuperacionFocusLost
    /**APROBADO!!!
     * Este método dispara una excepción en caso de existir una nota incorrecta. Debido a que en los eventos de las cajas
     * de texto donde se ingresan las notas se verifica que su formato sea correcto, lo único por evaluar es que no sean nulos.
     * Dicha validación aplica a las 4 notas, y a la nota de recuperación (si check_nota_recuperacion está seleccionado)
     * @throws ExcepcionDatosIncorrectos 
     */
    private void validar_notas() throws ExcepcionDatosIncorrectos {
        float nota;
        
        /* Verificación de que las notas correspondan a valores (no caracteres) */
        try { Float.parseFloat(nota1.getText());
        } catch(NumberFormatException ex) { throw new ExcepcionDatosIncorrectos("La Nota 1 no puede ser texto"); }
        try { Float.parseFloat(nota2.getText());
        } catch(NumberFormatException ex) { throw new ExcepcionDatosIncorrectos("La Nota 2 no puede ser texto"); }
        try { Float.parseFloat(nota3.getText());
        } catch(NumberFormatException ex) { throw new ExcepcionDatosIncorrectos("La Nota 3 no puede ser texto"); }
        try { Float.parseFloat(nota4.getText());
        } catch(NumberFormatException ex) { throw new ExcepcionDatosIncorrectos("La Nota 4 no puede ser texto"); }
        if (check_nota_recuperacion.isSelected()) {
            try { Float.parseFloat(nota_recuperacion.getText());
            } catch(NumberFormatException ex) { throw new ExcepcionDatosIncorrectos("La Nota de Recuperación no puede ser texto"); }
        }
        /* Verificación de que las notas estén en los rangos correctos */
        nota = Float.parseFloat(nota1.getText());
        if (nota < 0)   throw new ExcepcionDatosIncorrectos("La Nota 1 no puede ser menor a 0");
        if (nota > 100) throw new ExcepcionDatosIncorrectos("La Nota 1 no puede ser mayor a 100");
        nota = Float.parseFloat(nota2.getText());
        if (nota < 0)   throw new ExcepcionDatosIncorrectos("La Nota 2 no puede ser menor a 0");
        if (nota > 100) throw new ExcepcionDatosIncorrectos("La Nota 2 no puede ser mayor a 100");
        nota = Float.parseFloat(nota3.getText());
        if (nota < 0)   throw new ExcepcionDatosIncorrectos("La Nota 3 no puede ser menor a 0");
        if (nota > 100) throw new ExcepcionDatosIncorrectos("La Nota 3 no puede ser mayor a 100");
        nota = Float.parseFloat(nota4.getText());
        if (nota < 0)   throw new ExcepcionDatosIncorrectos("La Nota 4 no puede ser menor a 0");
        if (nota > 100) throw new ExcepcionDatosIncorrectos("La Nota 4 no puede ser mayor a 100");
        if (check_nota_recuperacion.isSelected()) {
            nota = Float.parseFloat(nota_recuperacion.getText());
            if (nota < 0)   throw new ExcepcionDatosIncorrectos("La Nota de Recuperación no puede ser menor a 0");
            if (nota > 100) throw new ExcepcionDatosIncorrectos("La Nota de Recuperación no puede ser mayor a 100");
        }
    }
    /**ÚTIL!!!
     * 
     * @param valorEnable 
     */
    private void setEnable_campos_notas(boolean valorEnable) {
        nota1.setEnabled(valorEnable);
        nota2.setEnabled(valorEnable);
        nota3.setEnabled(valorEnable);
        nota4.setEnabled(valorEnable);
        check_nota_recuperacion.setEnabled(valorEnable);
        nota_final.setEnabled(valorEnable);
        calcular_nota_final.setEnabled(valorEnable);
    }
    /**ÚTIL!!!
     * 
     */
    private void limpiar_campos_notas() {
        nota1.setText("");
        nota2.setText("");
        nota3.setText("");
        nota4.setText("");
        nota_recuperacion.setText("");
        check_nota_recuperacion.setSelected(false);
        nota_final.setText("");
    }
    /**ÚTIL!!!
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Notas (valores definidos en base a pruebas)
        tabla_notas.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_notas.getColumnModel().getColumn(1).setPreferredWidth(210);
        tabla_notas.getColumnModel().getColumn(2).setPreferredWidth(65);
        tabla_notas.getColumnModel().getColumn(3).setPreferredWidth(65);
        tabla_notas.getColumnModel().getColumn(4).setPreferredWidth(65);
        tabla_notas.getColumnModel().getColumn(5).setPreferredWidth(65);
        tabla_notas.getColumnModel().getColumn(6).setPreferredWidth(120);
        tabla_notas.getColumnModel().getColumn(7).setPreferredWidth(120);
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
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Notas dialog = new Notas(new javax.swing.JFrame(), true);
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
    
    private class RegistroNota {
        private int idNota, idCurso;
        private float nota1, nota2, nota3, nota4, notaRecuperacion, notaFinal;
        private String curso;
        private boolean editado;
        
        public RegistroNota() {
            idNota = idCurso = 0;
            nota1 = nota2 = nota3 = nota4 = notaFinal = 0;
            notaRecuperacion = -1;  // Indicador de que no se tiene Nota de Recuperación
            curso = "";
            editado = false;
        }

        public int getIdNota() { return idNota; }
        public int getIdCurso() { return idCurso; }
        public float getNota1() { return nota1; }
        public float getNota2() { return nota2; }
        public float getNota3() { return nota3; }
        public float getNota4() { return nota4; }
        public float getNotaRecuperacion() { return notaRecuperacion; }
        public float getNotaFinal() { return notaFinal; }
        public String getCurso() { return curso; }
        public boolean isEditado() { return editado; }
        public float getPromedioNotas() { return (nota1+nota2+nota3+nota4)/4; }

        public void setIdNota(int idNota) { this.idNota = idNota; }
        public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
        public void setNota1(float nota1) { this.nota1 = nota1; }
        public void setNota2(float nota2) { this.nota2 = nota2; }
        public void setNota3(float nota3) { this.nota3 = nota3; }
        public void setNota4(float nota4) { this.nota4 = nota4; }
        public void setNotaRecuperacion(float notaRecuperacion) { this.notaRecuperacion = notaRecuperacion; }
        public void setNotaFinal(float notaFinal) { this.notaFinal = notaFinal; }
        public void setCurso(String curso) { this.curso = curso; }
        public void setEditado(boolean editado) { this.editado = editado; }
        
        public String[] getDatosParaTabla(int num) {
            return new String[] {
                ""+num,
                curso,
                (nota1 == -1) ? "0.0": ""+nota1,
                (nota2 == -1) ? "0.0": ""+nota2,
                (nota3 == -1) ? "0.0": ""+nota3,
                (nota4 == -1) ? "0.0": ""+nota4,
                (notaRecuperacion == -1) ? "" : ""+notaRecuperacion,
                (notaFinal == -1) ? "0.0": ""+notaFinal
            };
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcular_nota_final;
    private javax.swing.JCheckBox check_nota_recuperacion;
    private javax.swing.JButton editar_notas;
    private javax.swing.JLabel etiqueta_nombre_estudiante;
    private javax.swing.JLabel etiqueta_notas;
    private javax.swing.JButton guardar_cambios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nota1;
    private javax.swing.JTextField nota2;
    private javax.swing.JTextField nota3;
    private javax.swing.JTextField nota4;
    private javax.swing.JTextField nota_final;
    private javax.swing.JTextField nota_recuperacion;
    private javax.swing.JPanel panel_nombre_estudiante;
    private javax.swing.JPanel panel_notas;
    private javax.swing.JTable tabla_notas;
    private javax.swing.JButton ver_ancho_columnas_de_tabla;
    // End of variables declaration//GEN-END:variables
}
