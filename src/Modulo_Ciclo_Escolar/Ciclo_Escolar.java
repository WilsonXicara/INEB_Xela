/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_Ciclo_Escolar;

import Tipos_tablas.No_editable;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class Ciclo_Escolar extends javax.swing.JDialog {

    Connection base;
    int cambio = 0;
    int cambio2 = 0;
    //Contienen informacion del ciclo actual
    ArrayList<String>  ID ;
    //Contienen informacion de los grados asociados al ciclo dentro de la base
    ArrayList<String> Id_grado,Nombre_grado;
    
    //Contienen informacion de los cursos asociados al ciclo dentro de la base
    ArrayList<String> Id_curso,Nombre_curso;
    
    //Contiene la informacion de los grados que se deasean agregar a la base
    ArrayList<String> nombre_grados_agregados;
    ArrayList<String> grados_agregados;
    ArrayList<String> seccion_agregados;
    //Contiene la informacion de los grados que se desean eliminar de la base
    ArrayList<String> Id_grados_borrados;
    ArrayList<String> nombre_grados_borrados ;
    
    
    //Contiene la informacion de los cursos que se desean agregar a la base
     ArrayList<String> cursos_agregados;
    //Contiene la informacion de los cursos que se desas eliminar a la base
      ArrayList<String> id_cursos_borrados;
      ArrayList<String> nombre_cursos_borrados;
    
    String año;
    int posicion,pos_cursos,pos_grados;
    
    
    /**
     * Creates new form Ciclo_Escolar
     */
    public Ciclo_Escolar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public Ciclo_Escolar(java.awt.Frame parent, boolean modal,Connection base) throws SQLException {
        super(parent, modal);
        initComponents();
        cursos_agregados = new ArrayList<String>();
        grados_agregados = new ArrayList<String>();
        seccion_agregados = new ArrayList<>();
        id_cursos_borrados = new ArrayList<String>();
        nombre_cursos_borrados = new ArrayList<String>();
        Id_grados_borrados = new ArrayList<String>();
        nombre_grados_borrados = new ArrayList<>();
        nombre_grados_agregados = new ArrayList<>();
       
        
         this.base = base;
         posicion =0;
         pos_cursos = -1;
         pos_grados = -1;
        Calendar fecha = new GregorianCalendar();
        año = Integer.toString(fecha.get(Calendar.YEAR));
        Cargar_Datos();
        
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent we) {
           }

            @Override
            public void windowClosing(WindowEvent we) {
                int tamaño_cursos = cursos_agregados.size();
        int tamaño_grados = grados_agregados.size();
        int tamaño_cursos_borrados = id_cursos_borrados.size();
        int tamaño_grados_borrados = nombre_grados_borrados.size();
        if(tamaño_cursos > 0 || tamaño_grados > 0 || tamaño_cursos_borrados > 0 || tamaño_grados_borrados > 0){
            String[] opciones = new String[2];
            opciones[0] = "SI";
            opciones[1] = "NO";
            //Pregunto si desea guardar los cursos agregados
            int eleccion = JOptionPane.showOptionDialog(null, "Desea guardar los cambios realizados", "Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if(eleccion == JOptionPane.YES_OPTION) {
                Guardar_cambios_grados();
                Guardar_cambios_cursos();
                
                cursos_agregados = new ArrayList<String>();
                id_cursos_borrados = new ArrayList<String>();
                nombre_cursos_borrados = new ArrayList<String>();
                
                Id_grados_borrados = new ArrayList<>();
                nombre_grados_borrados = new ArrayList<>();
                
                grados_agregados = new ArrayList<>();
                seccion_agregados = new ArrayList<>();
                nombre_grados_agregados = new ArrayList<>();
                
                Cambio_ciclo();                
            }
           
        }
           }

            @Override
            public void windowClosed(WindowEvent we) {
           }

            @Override
            public void windowIconified(WindowEvent we) {
           }

            @Override
            public void windowDeiconified(WindowEvent we) {
            }

            @Override
            public void windowActivated(WindowEvent we) {
           }

            @Override
            public void windowDeactivated(WindowEvent we) {
           }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        ciclo = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Cursos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        Tx_Nombre_curso = new javax.swing.JTextField();
        agregar_curso = new javax.swing.JButton();
        eliminar_curso = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Tx_Nombre_grado = new javax.swing.JTextField();
        Tx_seccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Grados = new javax.swing.JTable();
        agregar_grado = new javax.swing.JButton();
        eliminar_grado = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Cursos_asignados = new javax.swing.JTable();
        asignaciones = new javax.swing.JButton();
        guardar_cambios = new javax.swing.JButton();
        exportar_datos = new javax.swing.JButton();
        ciclo_listo = new javax.swing.JRadioButton();
        cerrado = new javax.swing.JRadioButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        Menu_crear = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ciclo Escolar");

        jLabel1.setText("Ciclo Escolar");

        ciclo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cicloItemStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cursos"));

        Cursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Cursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CursosMouseClicked(evt);
            }
        });
        Cursos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CursosKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(Cursos);

        jLabel5.setText("Nombre: ");

        agregar_curso.setText("Agregar");
        agregar_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_cursoActionPerformed(evt);
            }
        });

        eliminar_curso.setText("Eliminar");
        eliminar_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_cursoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tx_Nombre_curso, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(agregar_curso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eliminar_curso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tx_Nombre_curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar_curso)
                    .addComponent(eliminar_curso))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Grados"));

        jLabel2.setText("Sección");

        jLabel3.setText("Nombre");

        Grados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Grados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GradosMouseClicked(evt);
            }
        });
        Grados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GradosKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(Grados);

        agregar_grado.setText("Agregar");
        agregar_grado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_gradoActionPerformed(evt);
            }
        });

        eliminar_grado.setText("Eliminar");
        eliminar_grado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar_gradoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Tx_Nombre_grado, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(agregar_grado)
                                    .addGap(18, 18, 18)
                                    .addComponent(eliminar_grado))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Tx_seccion, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Tx_Nombre_grado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Tx_seccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar_grado)
                    .addComponent(eliminar_grado))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cursos Asignados"));

        jScrollPane1.setViewportView(Cursos_asignados);

        asignaciones.setText("Asignaciones");
        asignaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(asignaciones)
                .addGap(105, 105, 105))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(asignaciones)
                .addContainerGap())
        );

        guardar_cambios.setText("Guardar Cambios");
        guardar_cambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_cambiosActionPerformed(evt);
            }
        });

        exportar_datos.setText("Exportar Datos");
        exportar_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportar_datosActionPerformed(evt);
            }
        });

        ciclo_listo.setText("Ciclo Listo");
        ciclo_listo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ciclo_listoItemStateChanged(evt);
            }
        });
        ciclo_listo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciclo_listoActionPerformed(evt);
            }
        });

        cerrado.setText("Cerrar Ciclo");
        cerrado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cerradoItemStateChanged(evt);
            }
        });

        Menu_crear.setText("Crear");

        jMenuItem1.setText("Ciclo Escolar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Menu_crear.add(jMenuItem1);

        jMenuBar2.add(Menu_crear);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(ciclo_listo)
                .addGap(3, 3, 3)
                .addComponent(cerrado)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(guardar_cambios)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exportar_datos)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciclo_listo)
                    .addComponent(cerrado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar_cambios)
                    .addComponent(exportar_datos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GradosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GradosKeyPressed
        evt.consume();
    }//GEN-LAST:event_GradosKeyPressed

    private void cicloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cicloItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
        int tamaño_cursos = cursos_agregados.size();
        int tamaño_grados = grados_agregados.size();
        int tamaño_cursos_borrados = id_cursos_borrados.size();
        int tamaño_grados_borrados = nombre_grados_borrados.size();
       
        if(tamaño_cursos > 0 || tamaño_grados > 0 || tamaño_cursos_borrados > 0 || tamaño_grados_borrados > 0){
            String[] opciones = new String[2];
            opciones[0] = "SI";
            opciones[1] = "NO";
            //Pregunto si desea guardar los cursos agregados
            int eleccion = JOptionPane.showOptionDialog(null, "Desea guardar los cambios realizados", "Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if(eleccion == JOptionPane.YES_OPTION) {
                Guardar_cambios_grados();
                Guardar_cambios_cursos();
                posicion = ciclo.getSelectedIndex();
                cursos_agregados = new ArrayList<String>();
                id_cursos_borrados = new ArrayList<String>();
                nombre_cursos_borrados = new ArrayList<String>();
                Id_grados_borrados = new ArrayList<>();
                nombre_grados_borrados = new ArrayList<>();
                
                grados_agregados = new ArrayList<>();
                seccion_agregados = new ArrayList<>();
                nombre_grados_agregados = new ArrayList<>();
                
                Cambio_ciclo();
            }
            else{
                posicion = ciclo.getSelectedIndex();
                cursos_agregados = new ArrayList<String>();
                id_cursos_borrados = new ArrayList<String>();
                nombre_cursos_borrados = new ArrayList<String>();
                Id_grados_borrados = new ArrayList<>();
                nombre_grados_borrados = new ArrayList<>();
                
                grados_agregados = new ArrayList<>();
                seccion_agregados = new ArrayList<>();
                nombre_grados_agregados = new ArrayList<>();
                Cambio_ciclo();
            }
        }else{
             posicion = ciclo.getSelectedIndex();
            Cambio_ciclo();
        }
        }
    }//GEN-LAST:event_cicloItemStateChanged

    private void CursosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CursosKeyTyped
        evt.consume();
    }//GEN-LAST:event_CursosKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            new Crear_Ciclo_Escolar_1(new Frame(),true, base).setVisible(true);
            this.setVisible(true);
            Cargar_Datos();
        } catch (SQLException ex) {
            Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void GradosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GradosMouseClicked
        try {
            int grado = Grados.getSelectedRow();
            pos_grados = grado;
            System.out.println(grado);
            String aux  = Grados.getValueAt(pos_grados, 1) +" "+Grados.getValueAt(pos_grados, 2);
            if(Nombre_grado.contains(aux)){
               int a = Nombre_grado.indexOf(aux);
            Tabla_cursos_asignados(Id_grado.get(a));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_GradosMouseClicked

    private void agregar_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_cursoActionPerformed
        int filas = Cursos.getRowCount() + 1;
        String[] curso = new String[2];
        curso[0] = Integer.toString(filas);
        curso[1] = Tx_Nombre_curso.getText().trim();
        if (curso[1].length() > 0) {
            if(nombre_cursos_borrados.contains(curso[1])){
                if(!Nombre_curso.contains(curso[1])){
                    DefaultTableModel aux = (DefaultTableModel) Cursos.getModel();
                    aux.addRow(curso);
                    cursos_agregados.add(curso[1]);
                }else{
                    DefaultTableModel aux = (DefaultTableModel) Cursos.getModel();
                    aux.addRow(curso); 
                }
                int aux = nombre_cursos_borrados.indexOf(curso[1]);
                id_cursos_borrados.remove(aux);
                nombre_cursos_borrados.remove(curso[1]);
            }
            else if(!Nombre_curso.contains(curso[1])){
                if(!cursos_agregados.contains(curso[1])){
                DefaultTableModel aux = (DefaultTableModel) Cursos.getModel();
                aux.addRow(curso);
                cursos_agregados.add(curso[1]);
                        
                }else{
                    JOptionPane.showMessageDialog(this, "El curso ya ha sido agregado", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                }
            }else{
                JOptionPane.showMessageDialog(this, "El curso ya ha sido agregado", "ERROR", JOptionPane.ERROR_MESSAGE, null);
            }
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Debe escribir algo en la caja Nombre", "ERROR", JOptionPane.ERROR_MESSAGE, null);
        }
         Tx_Nombre_curso.setText("");
    }//GEN-LAST:event_agregar_cursoActionPerformed

    private void agregar_gradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_gradoActionPerformed
        String Nombre = Tx_Nombre_grado.getText().trim();
        String Seccion = Tx_seccion.getText().trim();
        int filas = Grados.getRowCount()+1;
        String[] fila = new String[3];
        fila[0] = Integer.toString(filas);
        fila[1] = Nombre;
        fila[2] = Seccion;
        if(Nombre.length()>0 && Seccion.length()>0){
            
            if(nombre_grados_borrados.contains(Nombre+" "+Seccion)){
                DefaultTableModel aux = (DefaultTableModel) Grados.getModel();
                if(!Nombre_grado.contains(Nombre+" "+Seccion)){
                    aux.addRow(fila);
                    nombre_grados_agregados.add(Nombre+" "+Seccion);
                    grados_agregados.add(Nombre);
                    seccion_agregados.add(Seccion);
                }else{
                    aux.addRow(fila);
                }
                int pos = nombre_grados_borrados.indexOf(Nombre+" "+Seccion);
                nombre_grados_borrados.remove(pos);
                Id_grados_borrados.remove(pos);
                
            }else if(!Nombre_grado.contains(Nombre+" "+Seccion)){
                if(!nombre_grados_agregados.contains(Nombre+" "+Seccion)){
                    DefaultTableModel aux = (DefaultTableModel) Grados.getModel();
                    aux.addRow(fila);
                    nombre_grados_agregados.add(Nombre+" "+Seccion);
                    grados_agregados.add(Nombre);
                    seccion_agregados.add(Seccion);
                }else{
                    JOptionPane.showMessageDialog(this, "El grado ya a sido agregado", "ERROR", JOptionPane.ERROR_MESSAGE, null);
                }
            }else{
                 JOptionPane.showMessageDialog(this, "El grado ya a sido agregado", "ERROR", JOptionPane.ERROR_MESSAGE, null);
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Debe llenar todas las casillas", "ERROR", JOptionPane.ERROR_MESSAGE, null);
        }
        Tx_Nombre_grado.setText("");
        Tx_seccion.setText("");
    }//GEN-LAST:event_agregar_gradoActionPerformed

    private void guardar_cambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_cambiosActionPerformed
        int tamaño_cursos = cursos_agregados.size();
        int tamaño_grados = grados_agregados.size();
        int tamaño_cursos_borrados = id_cursos_borrados.size();
        int tamaño_grados_borrados = nombre_grados_borrados.size();
        if(tamaño_cursos > 0 || tamaño_grados > 0 || tamaño_cursos_borrados > 0 || tamaño_grados_borrados > 0){
            String[] opciones = new String[2];
            opciones[0] = "SI";
            opciones[1] = "NO";
            //Pregunto si desea guardar los cursos agregados
            int eleccion = JOptionPane.showOptionDialog(null, "Desea guardar los cambios realizados", "Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if(eleccion == JOptionPane.YES_OPTION) {
                Guardar_cambios_grados();
                Guardar_cambios_cursos();
                
                cursos_agregados = new ArrayList<String>();
                id_cursos_borrados = new ArrayList<String>();
                nombre_cursos_borrados = new ArrayList<String>();
                
                Id_grados_borrados = new ArrayList<>();
                nombre_grados_borrados = new ArrayList<>();
                
                grados_agregados = new ArrayList<>();
                seccion_agregados = new ArrayList<>();
                nombre_grados_agregados = new ArrayList<>();
                
                Cambio_ciclo();                
            }
           
        }
    }//GEN-LAST:event_guardar_cambiosActionPerformed

    private void eliminar_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_cursoActionPerformed
        if(pos_cursos != -1){
            String curso = (String) Cursos.getValueAt(pos_cursos, 1);
            if(Nombre_curso.contains(curso)){
                String Id = Id_curso.get(Nombre_curso.indexOf(curso));
                id_cursos_borrados.add(Id);
                nombre_cursos_borrados.add(curso);
            }
            else{
                cursos_agregados.remove(curso);
                
            }
            DefaultTableModel aux = (DefaultTableModel) Cursos.getModel();
            aux.removeRow(pos_cursos);
            pos_cursos = -1;
        }else{
             JOptionPane.showMessageDialog(this, "Debe selecionar una fila", "ERROR", JOptionPane.ERROR_MESSAGE, null);
        }
        
    }//GEN-LAST:event_eliminar_cursoActionPerformed

    private void CursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CursosMouseClicked
        pos_cursos = Cursos.getSelectedRow();
        System.out.println(pos_cursos);
    }//GEN-LAST:event_CursosMouseClicked

    private void eliminar_gradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar_gradoActionPerformed
        if(pos_grados != -1){
            String Grado = (String) Grados.getValueAt(pos_grados, 1);
            String seccion = (String) Grados.getValueAt(pos_grados, 2);
            if(Nombre_grado.contains(Grado+" "+seccion)){
                String Id  = Id_grado.get(Nombre_grado.indexOf(Grado+" "+seccion));
                Id_grados_borrados.add(Id);
                nombre_grados_borrados.add(Grado+" "+seccion);
            }else{
                int aux = nombre_grados_agregados.indexOf(Grado+" "+seccion);
                nombre_grados_agregados.remove(aux);
                grados_agregados.remove(aux);
                seccion_agregados.remove(aux);
            }
            DefaultTableModel aux = (DefaultTableModel) Grados.getModel();
            aux.removeRow(pos_grados);
            pos_grados = -1;
        }else{
            JOptionPane.showMessageDialog(this, "Debe selecionar una fila", "ERROR", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_eliminar_gradoActionPerformed

    private void exportar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportar_datosActionPerformed
        int tamaño_cursos = cursos_agregados.size();
        int tamaño_grados = grados_agregados.size();
        int tamaño_cursos_borrados = id_cursos_borrados.size();
        int tamaño_grados_borrados = nombre_grados_borrados.size();
       
        if(tamaño_cursos > 0 || tamaño_grados > 0 || tamaño_cursos_borrados > 0 || tamaño_grados_borrados > 0){
            String[] opciones = new String[2];
            opciones[0] = "SI";
            opciones[1] = "NO";
            //Pregunto si desea guardar los cursos agregados
            int eleccion = JOptionPane.showOptionDialog(null, "Desea guardar los cambios realizados", "Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if(eleccion == JOptionPane.YES_OPTION) {
                Guardar_cambios_grados();
                Guardar_cambios_cursos();
                
                cursos_agregados = new ArrayList<String>();
                id_cursos_borrados = new ArrayList<String>();
                nombre_cursos_borrados = new ArrayList<String>();
                
                Id_grados_borrados = new ArrayList<>();
                nombre_grados_borrados = new ArrayList<>();
                
                grados_agregados = new ArrayList<>();
                seccion_agregados = new ArrayList<>();
                nombre_grados_agregados = new ArrayList<>();
                
                new Importar_datos(new Frame(), true, base, ID.get(posicion)).show();
                Cambio_ciclo();
            }
            else{
                
                cursos_agregados = new ArrayList<String>();
                id_cursos_borrados = new ArrayList<String>();
                nombre_cursos_borrados = new ArrayList<String>();
                
                Id_grados_borrados = new ArrayList<>();
                nombre_grados_borrados = new ArrayList<>();
                
                grados_agregados = new ArrayList<>();
                seccion_agregados = new ArrayList<>();
                nombre_grados_agregados = new ArrayList<>();
                new Importar_datos(new Frame(), true, base, ID.get(posicion)).show();
                Cambio_ciclo();
            }
        }else{
            new Importar_datos(new Frame(), true, base, ID.get(posicion)).show();
            Cambio_ciclo();
        }
        
        
    }//GEN-LAST:event_exportar_datosActionPerformed

    private void asignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignacionesActionPerformed
         int tamaño_cursos = cursos_agregados.size();
        int tamaño_grados = grados_agregados.size();
        int tamaño_cursos_borrados = id_cursos_borrados.size();
        int tamaño_grados_borrados = nombre_grados_borrados.size();
       
        if(tamaño_cursos > 0 || tamaño_grados > 0 || tamaño_cursos_borrados > 0 || tamaño_grados_borrados > 0){
            String[] opciones = new String[2];
            opciones[0] = "SI";
            opciones[1] = "NO";
            //Pregunto si desea guardar los cursos agregados
            int eleccion = JOptionPane.showOptionDialog(null, "Desea guardar los cambios realizados", "Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if(eleccion == JOptionPane.YES_OPTION) {
                Guardar_cambios_grados();
                Guardar_cambios_cursos();
                
                cursos_agregados = new ArrayList<String>();
                id_cursos_borrados = new ArrayList<String>();
                nombre_cursos_borrados = new ArrayList<String>();
                
                Id_grados_borrados = new ArrayList<>();
                nombre_grados_borrados = new ArrayList<>();
                
                grados_agregados = new ArrayList<>();
                seccion_agregados = new ArrayList<>();
                nombre_grados_agregados = new ArrayList<>();
                
                try {
                    new Asignar_curso_a_grado(new Frame(), true, base, ID.get(posicion)).show();
                } catch (SQLException ex) {
                    Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
                }
                Cambio_ciclo();
            }
            else{
                
                cursos_agregados = new ArrayList<String>();
                id_cursos_borrados = new ArrayList<String>();
                nombre_cursos_borrados = new ArrayList<String>();
                
                Id_grados_borrados = new ArrayList<>();
                nombre_grados_borrados = new ArrayList<>();
                
                grados_agregados = new ArrayList<>();
                seccion_agregados = new ArrayList<>();
                nombre_grados_agregados = new ArrayList<>();
                try {
                    new Asignar_curso_a_grado(new Frame(), true, base, ID.get(posicion)).show();
                } catch (SQLException ex) {
                    Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
                }
                Cambio_ciclo();
            }
        }else{
            try {
                    new Asignar_curso_a_grado(new Frame(), true, base, ID.get(posicion)).show();
                } catch (SQLException ex) {
                    Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
                }
            Cambio_ciclo();
        }
    }//GEN-LAST:event_asignacionesActionPerformed

    private void ciclo_listoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciclo_listoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ciclo_listoActionPerformed

    private void ciclo_listoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ciclo_listoItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED && cambio == 0){
            String[] opciones = new String[2];
            opciones[0] = "Continuar";
            opciones[1] = "Cancelar";
            
            //Pregunto si desea copiar los cursos y grados de un ciclo anterior
            int eleccion = JOptionPane.showOptionDialog(null, "Al marcar como listo ya no podra hacer cambios al ciclo \n no podra agregar o eliminar cursos y grados.\n Ya no podra realizar asignaciones", "Advertencia si ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
            if(eleccion == JOptionPane.YES_OPTION){
                String instruccion_grado = "UPDATE CicloEscolar SET cicloescolar.Listo = 1 WHERE cicloescolar.Id ="+ID.get(posicion)+";";
                try {
                    PreparedStatement pst = base.prepareStatement(instruccion_grado);
                    pst.executeUpdate();
                    cambio = 1;
                    agregar_curso.setEnabled(false);
                    eliminar_curso.setEnabled(false);
                    agregar_grado.setEnabled(false);
                    eliminar_grado.setEnabled(false);
                    asignaciones.setEnabled(false);
                    guardar_cambios.setEnabled(false);
                    exportar_datos.setEnabled(false);
                    ciclo_listo.setEnabled(false);
                    Tx_Nombre_curso.setEnabled(false);
                    Tx_Nombre_grado.setEnabled(false);
                    Tx_seccion.setEnabled(false);
                    ciclo_listo.setSelected(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_ciclo_listoItemStateChanged

    private void cerradoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cerradoItemStateChanged
       if(evt.getStateChange() == ItemEvent.SELECTED && cambio2 == 0){
            String[] opciones = new String[2];
            opciones[0] = "Continuar";
            opciones[1] = "Cancelar";
            
            //Pregunto si desea copiar los cursos y grados de un ciclo anterior
            int eleccion = JOptionPane.showOptionDialog(null, "Al marcar como cerrad ya no podra hacer cambios a ciclo \n no podra asignar notas a los cursos.\n Ya no podra realizar cambios a los grados.", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
            if(eleccion == JOptionPane.YES_OPTION){
                String instruccion_grado = "UPDATE CicloEscolar SET cicloescolar.Cerrado = 1 WHERE cicloescolar.Id ="+ID.get(posicion)+";";
                try {
                    PreparedStatement pst = base.prepareStatement(instruccion_grado);
                    pst.executeUpdate();
                    cambio = 1;
                    agregar_curso.setEnabled(false);
                    eliminar_curso.setEnabled(false);
                    agregar_grado.setEnabled(false);
                    eliminar_grado.setEnabled(false);
                    asignaciones.setEnabled(false);
                    guardar_cambios.setEnabled(false);
                    exportar_datos.setEnabled(false);
                    ciclo_listo.setEnabled(false);
                    Tx_Nombre_curso.setEnabled(false);
                    Tx_Nombre_grado.setEnabled(false);
                    Tx_seccion.setEnabled(false);
                    cerrado.setSelected(true);
                    cerrado.setEnabled(false);
                } catch (SQLException ex) {
                    Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_cerradoItemStateChanged

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
            java.util.logging.Logger.getLogger(Ciclo_Escolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ciclo_Escolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ciclo_Escolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ciclo_Escolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ciclo_Escolar dialog = new Ciclo_Escolar(new javax.swing.JFrame(), true);
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
    
       public void Cargar_Datos() throws SQLException{
           ciclo.removeAllItems();
        boolean encontrado = false;
        ID = new ArrayList<String>();
        Statement a = base.createStatement();
        ResultSet consulta = a.executeQuery("SELECT anio,Id FROM CicloEscolar ORDER BY anio");
        while(consulta.next()){
            String nuevo = consulta.getString(1);
            ID.add(consulta.getString(2));
            if(nuevo.equals(año)) encontrado = true;
            ciclo.addItem(nuevo);
        }
        if(encontrado == true){
            ciclo.setSelectedItem(año);
        }
    }
    public void Tabla_Grados(ResultSet consulta) throws SQLException{
        No_editable tabla = new No_editable();
        Id_grado= new ArrayList<String>();
        Nombre_grado = new ArrayList<String>();
        int cont = 1;
        tabla.addColumn("No");
        tabla.addColumn("Nombre");
        tabla.addColumn("Sección");
        String[] fila = new String[3];
        fila[0] = Integer.toString(cont);
        fila[1] = consulta.getString(1);
        fila[2] = consulta.getString(2);
        Id_grado.add(consulta.getString(3));
        Nombre_grado.add(fila[1]+" "+fila[2]);
        tabla.addRow(fila);
        while(consulta.next()){
            cont++;
            fila[0] = Integer.toString(cont);
            fila[1] = consulta.getString(1);
            fila[2] = consulta.getString(2);
            tabla.addRow(fila);
            Id_grado.add(consulta.getString(3));
            Nombre_grado.add(fila[1]+" "+fila[2]);
        }
        tabla.isCellEditable(0, 0);
        Grados.setModel(tabla);
    }
    
    public void Tabla_cursos(ResultSet consulta) throws SQLException{
        Id_curso = new ArrayList<String>();
        Nombre_curso = new ArrayList<String>();
        No_editable tabla = new No_editable();
        String[] fila = new String[2];
        int cont = 1;
        tabla.addColumn("No");
        tabla.addColumn("Nombre");
        fila[0] = Integer.toString(cont);
        fila[1] = consulta.getString(1);
        Nombre_curso.add(fila[1]);
        Id_curso.add(consulta.getString(2));
        tabla.addRow(fila);
        while (consulta.next()){
            cont++;
            fila[0] = Integer.toString(cont);
            fila[1] = consulta.getString(1);
            Nombre_curso.add(fila[1]);
            Id_curso.add(consulta.getString(2));
        tabla.addRow(fila);
        }
    
       Cursos.setModel(tabla);
    }
    
    public void Tabla_cursos_asignados(String Id) throws SQLException{
        Statement a = base.createStatement();
        ResultSet consulta_Curso_asignado = a.executeQuery("SELECT curso.Nombre, catedratico.Nombres,catedratico.Apellidos FROM asignacioncat INNER JOIN curso ON asignacioncat.Curso_Id = curso.Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id INNER JOIN catedratico ON asignacioncat.Catedratico_Id = catedratico.Id WHERE asignacioncat.CicloEscolar_Id = "+ID.get(posicion)+" AND asignacioncat.Grado_Id = "+Id+";");
        No_editable tabla = new No_editable();
        tabla.addColumn("Nombre");
        tabla.addColumn("Catedratico Asignado");
        
        //Se ingresa las demas filas
        while (consulta_Curso_asignado.next()){
            String[] fila = new String[2];
            fila[0] = consulta_Curso_asignado.getString(1);
            //Se verifica si el curso ya tiene un catedratico asignado
            fila[1] = consulta_Curso_asignado.getString(2) +" "+consulta_Curso_asignado.getString(3);
        
            tabla.addRow(fila);
        }
    
       Cursos_asignados.setModel(tabla);
    }
    
    public void Guardar_cambios_grados(){
        int tamaño_grados = grados_agregados.size();
        int tamaño_grados_borrados = nombre_grados_borrados.size();
                     
        //Se crea la relacion de los nuevos grados
        Statement Primer_paso;
        for (int i = 0; i < tamaño_grados; i++) {
            String Nombre = grados_agregados.get(i);
            String Seccion = seccion_agregados.get(i);
           try {
               // confirmar si ya existe un grado con ese nombre
               Primer_paso = base.createStatement();
               ResultSet consulta_1 = Primer_paso.executeQuery("SELECT* FROM grado WHERE grado.Nombre = '"+Nombre+"' AND grado.Seccion = '"+Seccion+"';");
               //Si ya existe solo se crea una relacion del grado con el ciclo
               if(consulta_1.next()){
                   String instruccion_asignacion = "INSERT INTO asignacioncat(asignacioncat.CicloEscolar_Id,asignacioncat.Grado_Id) VALUES ("+ID.get(posicion)+","+consulta_1.getString(1)+");";
                   PreparedStatement pst = base.prepareStatement(instruccion_asignacion);
                   pst.executeUpdate();

               }   
               //Si no existe se crea el registro en grado y luego la relacion del grado con el ciclo
               else{
                   //Se crea el grado y obtengo el Id
                   String instruccion_grado = "INSERT INTO grado(Nombre,Seccion) VALUES('"+Nombre+"','"+Seccion+"');";
                   PreparedStatement pst = base.prepareStatement(instruccion_grado);
                   pst.executeUpdate();
                   Statement aux = base.createStatement();
                   ResultSet Id = aux.executeQuery("SELECT Id FROM grado WHERE grado.Nombre = '"+Nombre+"' AND grado.Seccion = '"+Seccion+"';");
                   Id.next();

                   //Creo la relacion del grado con ciclo
                   String instruccion_asignacion = "INSERT INTO asignacioncat(asignacioncat.CicloEscolar_Id,asignacioncat.Grado_Id) VALUES ("+ID.get(posicion)+","+Id.getString(1)+");";
                   PreparedStatement psta = base.prepareStatement(instruccion_asignacion);
                   psta.executeUpdate();
               }
               } catch (SQLException ex) {
               Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        for (int i = 0; i < tamaño_grados_borrados; i++) {
            String id = Id_grados_borrados.get(i);
            
           try {  
                   //borro la relacion que tiene el grado con el ciclo
                   String instruccion_grado = "DELETE FROM asignacioncat WHERE asignacioncat.CicloEscolar_Id = "+ID.get(posicion) +" AND asignacioncat.Grado_Id = "+id+";";
                   PreparedStatement pst = base.prepareStatement(instruccion_grado);
                   pst.executeUpdate();
                   
               
               } catch (SQLException ex) {
               Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
           }
        }                
    }
    
    public void Guardar_cambios_cursos(){
       int tamaño_cursos = cursos_agregados.size();
       int tamaño_cursos_borrados = id_cursos_borrados.size();
        for (int i = 0; i < tamaño_cursos; i++) {
            String Nombre = cursos_agregados.get(i);
                Statement Primer_paso;
                try {
                    // confirmar si ya existe un curso con ese nombre
                    Primer_paso = base.createStatement();
                    ResultSet consulta_1 = Primer_paso.executeQuery("SELECT* FROM curso WHERE curso.Nombre = '"+Nombre+"';");
                    //Si ya existe solo se crea una relacion del curso con el ciclo
                    if(consulta_1.next()){
                        String instruccion_asignacion = "INSERT INTO asignacioncat(asignacioncat.CicloEscolar_Id,asignacioncat.Curso_Id) VALUES ("+ID.get(posicion)+","+consulta_1.getString(1)+");";
                        PreparedStatement pst = base.prepareStatement(instruccion_asignacion);
                        pst.executeUpdate();

                    }   
                    //Si no existe se crea el registro en curso y luego la relacion del curso con el ciclo
                    else{
                        //Se crea el curso y obtengo el Id
                        String instruccion_curso = "INSERT INTO curso(Nombre) VALUES('"+Nombre+"');";
                        PreparedStatement pst = base.prepareStatement(instruccion_curso);
                        pst.executeUpdate();
                        Statement aux = base.createStatement();
                        ResultSet Id = aux.executeQuery("SELECT Id FROM curso WHERE curso.Nombre = '"+Nombre+"';");
                        Id.next();

                        //Creo la relacion del curso con ciclo
                        String instruccion_asignacion = "INSERT INTO asignacioncat(asignacioncat.CicloEscolar_Id,asignacioncat.Curso_Id) VALUES ("+ID.get(posicion)+","+Id.getString(1)+");";
                        PreparedStatement psta = base.prepareStatement(instruccion_asignacion);
                        psta.executeUpdate();
                    }
                    
                    } catch (SQLException ex) {
                    Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
                }
           
        }
        
        for (int i = 0; i < tamaño_cursos_borrados; i++) {
            try {
                    String Id = id_cursos_borrados.get(i);
                    //Se crea el curso y obtengo el Id
                        String instruccion_curso = "DELETE FROM asignacioncat WHERE asignacioncat.CicloEscolar_Id = "+ID.get(posicion)+" AND asignacioncat.Curso_Id = "+Id+";";
                        PreparedStatement pst = base.prepareStatement(instruccion_curso);
                        pst.executeUpdate();                    
                    } catch (SQLException ex) {
                    Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }
    public void Cambio_ciclo(){
        Id_curso = new ArrayList<String>();
        Nombre_curso = new ArrayList<String>();
        Id_grado = new ArrayList<String>();
        Nombre_grado = new ArrayList<String>();
        if(posicion != -1){
        String Id = ID.get(posicion);
        Statement a;
        try {
            Statement b = base.createStatement();
            ResultSet consultab = b.executeQuery("SELECT Listo, Cerrado FROM cicloescolar WHERE cicloescolar.Id = '"+Id+"';");
            if(consultab.next()){
                String listo = consultab.getString(1);
                String cerrado = consultab.getString(2);
                if(listo.equals("1")){
                    cambio = 1;
                    agregar_curso.setEnabled(false);
                    eliminar_curso.setEnabled(false);
                    agregar_grado.setEnabled(false);
                    eliminar_grado.setEnabled(false);
                    asignaciones.setEnabled(false);
                    guardar_cambios.setEnabled(false);
                    exportar_datos.setEnabled(false);
                    ciclo_listo.setEnabled(false);
                    Tx_Nombre_curso.setEnabled(false);
                    Tx_Nombre_grado.setEnabled(false);
                    Tx_seccion.setEnabled(false);
                    ciclo_listo.setSelected(true);
                    if(cerrado.equals("1")){
                        this.cerrado.setEnabled(false);
                        cambio2 = 1;
                    }else{
                        this.cerrado.setEnabled(true);
                        cambio2 = 0;
                    }
                }
                else{
                    cambio = 0;
                    agregar_curso.setEnabled(true);
                    eliminar_curso.setEnabled(true);
                    agregar_grado.setEnabled(true);
                    eliminar_grado.setEnabled(true);
                    asignaciones.setEnabled(true);
                    guardar_cambios.setEnabled(true);
                    exportar_datos.setEnabled(true);
                    ciclo_listo.setEnabled(true);
                    Tx_Nombre_curso.setEnabled(true);
                    Tx_Nombre_grado.setEnabled(true);
                    Tx_seccion.setEnabled(true);
                    ciclo_listo.setSelected(false);
                    this.cerrado.setVisible(false);
                }
                
            }
            
            a = base.createStatement();
            ResultSet consulta_Grados = a.executeQuery("SELECT grado.Nombre,grado.Seccion, grado.Id FROM AsignacionCAT INNER JOIN grado ON AsignacionCAT.Grado_Id = grado.Id WHERE AsignacionCAT.CicloEscolar_Id="+Id+" AND AsignacionCAT.Curso_Id is null ANd AsignacionCAT.Catedratico_Id is null;");
            a = base.createStatement();
            ResultSet consulta_Curso = a.executeQuery("SELECT curso.Nombre, curso.Id FROM asignacioncat INNER JOIN curso ON asignacioncat.Curso_Id = curso.Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id WHERE asignacioncat.CicloEscolar_Id = "+Id+" AND asignacioncat.Grado_Id is null AND asignacioncat.Catedratico_Id is null;");
            if(consulta_Grados.next()){
                Tabla_Grados(consulta_Grados);
            }
            else{
                No_editable tabla = new No_editable();
                tabla.addColumn("No");
                tabla.addColumn("Nombre");
                tabla.addColumn("Sección");
                Grados.setModel(tabla);
            }
            if(consulta_Curso.next()){
                Tabla_cursos(consulta_Curso);
            }
            else{
                No_editable tabla = new No_editable();
                tabla.addColumn("No");
                tabla.addColumn("Nombre");
               // tabla.addColumn("Catedratico Asignado");
               // tabla.addColumn("Grado Asignado");
                Cursos.setModel(tabla);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Cursos;
    private javax.swing.JTable Cursos_asignados;
    private javax.swing.JTable Grados;
    private javax.swing.JMenu Menu_crear;
    private javax.swing.JTextField Tx_Nombre_curso;
    private javax.swing.JTextField Tx_Nombre_grado;
    private javax.swing.JTextField Tx_seccion;
    private javax.swing.JButton agregar_curso;
    private javax.swing.JButton agregar_grado;
    private javax.swing.JButton asignaciones;
    private javax.swing.JRadioButton cerrado;
    private javax.swing.JComboBox<String> ciclo;
    private javax.swing.JRadioButton ciclo_listo;
    private javax.swing.JButton eliminar_curso;
    private javax.swing.JButton eliminar_grado;
    private javax.swing.JButton exportar_datos;
    private javax.swing.JButton guardar_cambios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
