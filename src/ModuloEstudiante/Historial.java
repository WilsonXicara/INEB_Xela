/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.write.WriteException;

/**
 *
 * @author Wilson Xicará
 */
public class Historial extends javax.swing.JDialog {
    private Connection conexion;
    private ArrayList<RegCicloAsignado> listaCiclos;
    private int idUltimoCiclo;
    private DefaultTableModel modelCiclos, modelCursosNotas;
    /**
     * Creates new form Historial
     */
    public Historial(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public Historial(java.awt.Frame parent, boolean modal, Connection conexion, RegistroEstudiante estudiante) {
        super(parent, modal);
        initComponents();
        this.conexion = conexion;
        String descripcion = "Historial de"+("F".equals(estudiante.getSexo())?" la":"l")+" estudiante "+estudiante.getNombres()+" "+estudiante.getApellidos();
        this.setTitle(descripcion);
        etiqueta_nombre_estudiante.setText(descripcion);
        etiqueta_asignaciones.setText("Ciclos Escolares a los que se ha Asignado:");
        
        modelCiclos = (DefaultTableModel)tabla_ciclos_escolares.getModel();
        modelCursosNotas = (DefaultTableModel)tabla_cursos_notas.getModel();
        
        // Obtengo todos los Ciclos Escolares y los Grados a los que se ha Asignado el Estudiante
        try {
            this.listaCiclos = new ArrayList<>();
            Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet cUltimoCiclo = sentencia.executeQuery("SELECT MAX(Id) FROM CicloEscolar");
            cUltimoCiclo.next();
            idUltimoCiclo = cUltimoCiclo.getInt(1);
            ResultSet cCiclos = sentencia.executeQuery("SELECT CicloEscolar.Id idCiclo, CicloEscolar.Anio, Grado.Id idGrado, Grado.Nombre, Grado.Seccion FROM AsignacionEST "
                    + "INNER JOIN CicloEscolar ON AsignacionEST.CicloEscolar_Id = CicloEscolar.Id "
                    + "INNER JOIN Grado ON AsignacionEST.Grado_Id = Grado.Id "
                    + "WHERE Estudiante_Id = "+estudiante.getID());
            int cont = 0;
            while(cCiclos.next()) {
                cont++;
                RegCicloAsignado nuevo = new RegCicloAsignado(cCiclos.getInt("idCiclo"), cCiclos.getString("Anio"), cCiclos.getInt("idGrado"), cCiclos.getString("Nombre"), cCiclos.getString("Seccion"));
                listaCiclos.add(nuevo); // Agrego el Ciclo cargado desde la Base de Datos
                // Inserto los datos del Ciclo cargado en la tabla
                modelCiclos.addRow(nuevo.getDatosParaTabla(cont));
            }
            // Ahora obtengo todas las Notas de todos los Cursos de todos los Grados del Estudiante
            int cantidad = listaCiclos.size();
            for(cont=0; cont<cantidad; cont++) {
                ArrayList<RegCursoNota> listaNotas = new ArrayList<>();
                ResultSet cCursoNotas = sentencia.executeQuery("SELECT Curso.Nombre Curso, Notas.Nota1, Notas.Nota2, Notas.Nota3, Notas.Nota4, Notas.NotaRecuperacion, Notas.NotaFinal FROM AsignacionEST "
                        + "INNER JOIN Notas ON AsignacionEst.Id = Notas.AsignacionEST_Id "
                        + "INNER JOIN Curso ON Notas.Curso_Id = Curso.Id "
                        + "WHERE AsignacionEST.Estudiante_Id = "+estudiante.getID()+" AND AsignacionEst.CicloEscolar_Id = "+listaCiclos.get(cont).getIdCiclo());
                while (cCursoNotas.next())
                    listaNotas.add(new RegCursoNota(cCursoNotas.getString("Curso"), cCursoNotas.getFloat("Nota1"), cCursoNotas.getFloat("Nota2"), cCursoNotas.getFloat("Nota3"), cCursoNotas.getFloat("Nota4"), cCursoNotas.getFloat("NotaRecuperacion"), cCursoNotas.getFloat("NotaFinal")));
                listaCiclos.get(cont).addNotas(listaNotas);
            }   // Hasta aquí se garantiza la extracción de todas la Notas
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al extraer los datos.\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Cierro la ventana en caso de ocurrir un error
//            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
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
        etiqueta_nombre_estudiante = new javax.swing.JLabel();
        panel_ciclos_del_estudiante = new javax.swing.JPanel();
        etiqueta_asignaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_ciclos_escolares = new javax.swing.JTable();
        panel_notas_del_estudiante = new javax.swing.JPanel();
        etiqueta_cursos_notas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_cursos_notas = new javax.swing.JTable();
        panel_botones_estudiante1 = new javax.swing.JPanel();
        exportar_datos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel_info_estudiante.setBackground(new java.awt.Color(153, 153, 255));

        etiqueta_nombre_estudiante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        etiqueta_nombre_estudiante.setText("Historial del Estudiante:");

        javax.swing.GroupLayout panel_info_estudianteLayout = new javax.swing.GroupLayout(panel_info_estudiante);
        panel_info_estudiante.setLayout(panel_info_estudianteLayout);
        panel_info_estudianteLayout.setHorizontalGroup(
            panel_info_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_info_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta_nombre_estudiante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_info_estudianteLayout.setVerticalGroup(
            panel_info_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_info_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta_nombre_estudiante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_ciclos_del_estudiante.setBackground(new java.awt.Color(153, 153, 255));

        etiqueta_asignaciones.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        etiqueta_asignaciones.setText("ASIGNACIONES QUE HA TENIDO:");

        tabla_ciclos_escolares.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_ciclos_escolares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Ciclo Escolar", "Grado", "Seccion", "Vigente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_ciclos_escolares.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_ciclos_escolares.setRowHeight(25);
        tabla_ciclos_escolares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_ciclos_escolaresMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_ciclos_escolares);

        javax.swing.GroupLayout panel_ciclos_del_estudianteLayout = new javax.swing.GroupLayout(panel_ciclos_del_estudiante);
        panel_ciclos_del_estudiante.setLayout(panel_ciclos_del_estudianteLayout);
        panel_ciclos_del_estudianteLayout.setHorizontalGroup(
            panel_ciclos_del_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ciclos_del_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ciclos_del_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ciclos_del_estudianteLayout.createSequentialGroup()
                        .addComponent(etiqueta_asignaciones)
                        .addGap(0, 631, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panel_ciclos_del_estudianteLayout.setVerticalGroup(
            panel_ciclos_del_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ciclos_del_estudianteLayout.createSequentialGroup()
                .addComponent(etiqueta_asignaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_notas_del_estudiante.setBackground(new java.awt.Color(153, 153, 255));

        etiqueta_cursos_notas.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        etiqueta_cursos_notas.setText("CURSOS Y NOTAS:");

        tabla_cursos_notas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_cursos_notas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Curso", "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Nota de Recuperación", "Nota Final", "Resultado"
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
        tabla_cursos_notas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_cursos_notas.setRowHeight(25);
        jScrollPane2.setViewportView(tabla_cursos_notas);

        javax.swing.GroupLayout panel_notas_del_estudianteLayout = new javax.swing.GroupLayout(panel_notas_del_estudiante);
        panel_notas_del_estudiante.setLayout(panel_notas_del_estudianteLayout);
        panel_notas_del_estudianteLayout.setHorizontalGroup(
            panel_notas_del_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_notas_del_estudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_notas_del_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_notas_del_estudianteLayout.createSequentialGroup()
                        .addComponent(etiqueta_cursos_notas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_notas_del_estudianteLayout.setVerticalGroup(
            panel_notas_del_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_notas_del_estudianteLayout.createSequentialGroup()
                .addComponent(etiqueta_cursos_notas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_botones_estudiante1.setBackground(new java.awt.Color(51, 51, 255));

        exportar_datos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exportar_datos.setText("Exportar cursos y notas");
        exportar_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportar_datosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_botones_estudiante1Layout = new javax.swing.GroupLayout(panel_botones_estudiante1);
        panel_botones_estudiante1.setLayout(panel_botones_estudiante1Layout);
        panel_botones_estudiante1Layout.setHorizontalGroup(
            panel_botones_estudiante1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_botones_estudiante1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exportar_datos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_botones_estudiante1Layout.setVerticalGroup(
            panel_botones_estudiante1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_botones_estudiante1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportar_datos)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_info_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_ciclos_del_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_notas_del_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_botones_estudiante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_info_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_ciclos_del_estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_notas_del_estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_botones_estudiante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Acción que extrae
     * @param evt 
     */
    private void tabla_ciclos_escolaresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ciclos_escolaresMousePressed
        etiqueta_cursos_notas.setText("CURSOS Y NOTAS DEL CICLO "+(String)tabla_ciclos_escolares.getValueAt(tabla_ciclos_escolares.getSelectedRow(), 1)+":");
        // Inicio la carga de las Notas del Ciclo Escolar y Grado seleccionados
        int indexCiclo = tabla_ciclos_escolares.getSelectedRow(), cantidad;
        ArrayList<RegCursoNota> notas = listaCiclos.get(indexCiclo).getNotas();
        cantidad = notas.size();
        
        modelCursosNotas.setRowCount(0);
        for (int i=0; i<cantidad; i++)
            modelCursosNotas.addRow(notas.get(i).getDatosParaTabla(i+1));
    }//GEN-LAST:event_tabla_ciclos_escolaresMousePressed

    private void exportar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportar_datosActionPerformed
         if (tabla_cursos_notas.getRowCount()==0) {
            JOptionPane.showMessageDialog (null, "No hay datos en la tabla para exportar.","BCO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de excel (*.xls)","xls");
        chooser.setFileFilter(filtro);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        int opcion = chooser.showSaveDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String nombreArchivo = chooser.getSelectedFile().toString(); 
            try {
                 ExportarAExcel.exportar_tabla(new File(nombreArchivo+".xls"), tabla_cursos_notas, "Notas del Ciclo "+(String)tabla_ciclos_escolares.getValueAt(tabla_ciclos_escolares.getSelectedRow(), 1), true);
                 JOptionPane.showMessageDialog(this, "Archivo '"+chooser.getSelectedFile().getAbsolutePath()+"' creado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
             } catch (IOException | WriteException ex) {
                 JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar crear el archivo.\n\nDescripción:\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
             }
            
        }
    }//GEN-LAST:event_exportar_datosActionPerformed

    /**ÚTIL!!!
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Ciclos Escolares (valores definidos en base a pruebas)
        tabla_ciclos_escolares.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_ciclos_escolares.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla_ciclos_escolares.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabla_ciclos_escolares.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla_ciclos_escolares.getColumnModel().getColumn(4).setPreferredWidth(65);
        // Definición del ancho de las columnas para la Tabla Cursos y Notas (valores definidos en base a pruebas)
        tabla_cursos_notas.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_cursos_notas.getColumnModel().getColumn(1).setPreferredWidth(190);
        tabla_cursos_notas.getColumnModel().getColumn(2).setPreferredWidth(65);
        tabla_cursos_notas.getColumnModel().getColumn(3).setPreferredWidth(65);
        tabla_cursos_notas.getColumnModel().getColumn(4).setPreferredWidth(65);
        tabla_cursos_notas.getColumnModel().getColumn(5).setPreferredWidth(65);
        tabla_cursos_notas.getColumnModel().getColumn(6).setPreferredWidth(140);
        tabla_cursos_notas.getColumnModel().getColumn(7).setPreferredWidth(75);
        tabla_cursos_notas.getColumnModel().getColumn(8).setPreferredWidth(115);
    }
    
    private class RegCicloAsignado {
        private int idCiclo, idGrado;
        private String anio, grado, seccion;
        private ArrayList<RegCursoNota> listaNotas;
        
        public RegCicloAsignado() {
            idCiclo = idGrado = 0;
            anio = grado = seccion = "";
            listaNotas = new ArrayList<>();
        }
        public RegCicloAsignado(int idCiclo, String anio, int idGrado, String grado, String seccion) {
            this.idCiclo = idCiclo;
            this.idGrado = idGrado;
            this.anio = anio;
            this.grado = grado;
            this.seccion = seccion;
            this.listaNotas = new ArrayList<>();
        }

        public int getIdCiclo() { return idCiclo; }
        public String getAnio() { return anio; }
        public int getIdGrado() { return idGrado; }
        public String getGrado() { return grado; }
        public String getSeccion() { return seccion; }
        public RegCursoNota getNota(int indexNota) { return listaNotas.get(indexNota); }
        public ArrayList<RegCursoNota> getNotas() { return listaNotas; }

        public void setIdCiclo(int idCiclo) { this.idCiclo = idCiclo; }
        public void setAnio(String anio) { this.anio = anio; }
        public void setIdGrado(int idGrado) { this.idGrado = idGrado; }
        public void setGrado(String grado) { this.grado = grado; }
        public void setSeccion(String seccion) { this.seccion = seccion; }
        public void addNota(RegCursoNota nota) { this.listaNotas.add(nota); }
        public void addNotas(ArrayList<RegCursoNota> listaNotas) { this.listaNotas = listaNotas; }
        
        public String[] getDatosParaTabla(int num) {
            return new String[]{""+num, anio, grado, seccion, (idCiclo==idUltimoCiclo)?"SI":"NO"};
        }
    }
    private class RegCursoNota {
        private String curso;
        private float nota1, nota2, nota3, nota4, notaRecuperacion, notaFinal;
        
        public RegCursoNota() {
            curso = "";
            nota1 = nota2 = nota3 = nota4 = notaFinal = 0;
            notaRecuperacion = -1;
        }
        public RegCursoNota(String curso, float nota1, float nota2, float nota3, float nota4, float notaRecuperacion, float notaFinal) {
            this.curso = curso;
            this.nota1 = nota1;
            this.nota2 = nota2;
            this.nota3 = nota3;
            this.nota4 = nota4;
            this.notaRecuperacion = notaRecuperacion;
            this.notaFinal = notaFinal;
        }

        public String getCurso() { return curso; }
        public float getNota1() { return nota1; }
        public float getNota2() { return nota2; }
        public float getNota3() { return nota3; }
        public float getNota4() { return nota4; }
        public float getNotaRecuperacion() { return notaRecuperacion; }
        public float getNotaFinal() { return notaFinal; }

        public void setCurso(String curso) { this.curso = curso; }
        public void setNota1(float nota1) { this.nota1 = nota1; }
        public void setNota2(float nota2) { this.nota2 = nota2; }
        public void setNota3(float nota3) { this.nota3 = nota3; }
        public void setNota4(float nota4) { this.nota4 = nota4; }
        public void setNotaRecuperacion(float notaRecuperacion) { this.notaRecuperacion = notaRecuperacion; }
        public void setNotaFinal(float notaFinal) { this.notaFinal = notaFinal; }
        
        public String[] getDatosParaTabla(int num) {
            return new String[]{""+num, curso, ""+nota1, ""+nota2, ""+nota3, ""+nota4, (notaRecuperacion!=-1)?""+notaRecuperacion:"", ""+notaFinal, (notaFinal!=0)?(notaFinal>=65?"Promovido":"No Promovido"):""};
        }
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
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Historial dialog = new Historial(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel etiqueta_asignaciones;
    private javax.swing.JLabel etiqueta_cursos_notas;
    private javax.swing.JLabel etiqueta_nombre_estudiante;
    private javax.swing.JButton exportar_datos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel_botones_estudiante1;
    private javax.swing.JPanel panel_ciclos_del_estudiante;
    private javax.swing.JPanel panel_info_estudiante;
    private javax.swing.JPanel panel_notas_del_estudiante;
    private javax.swing.JTable tabla_ciclos_escolares;
    private javax.swing.JTable tabla_cursos_notas;
    // End of variables declaration//GEN-END:variables
}
