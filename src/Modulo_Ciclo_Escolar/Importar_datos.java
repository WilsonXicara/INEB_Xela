/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_Ciclo_Escolar;

import Tipos_tablas.No_editable;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class Importar_datos extends javax.swing.JDialog {
     boolean copiar;
     String ciclo_actual;
     ArrayList<String> Id_grado;
     ArrayList<String> Id_curso;
     Connection base;
     ArrayList<String> Id_ciclo;
    
    int posicion =0;
     
     
    /**
     * Creates new form Crear_Ciclo_Escolar_3
     */
    public Importar_datos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }
    public Importar_datos(java.awt.Frame parent, boolean modal,Connection base,String ciclo_actual) {
        super(parent, modal);
        initComponents();
        this.base = base;
        this.ciclo_actual = ciclo_actual;
        Actualizar_actuales();
        Id_ciclo = new ArrayList<String>();
        try {
            Statement a = base.createStatement();
            ResultSet consulta = a.executeQuery("SELECT cicloescolar.Id, cicloescolar.Anio FROM cicloescolar WHERE cicloescolar.Id !="+this.ciclo_actual+";");
            while(consulta.next()){
                String Id = consulta.getString(1);
                String nombre = consulta.getString(2);
                Id_ciclo.add(Id);
                this.ciclo.addItem(nombre);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Importar_datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.setLocationRelativeTo(null);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Grados = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Grados_actuales = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Cursos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Cursos_actuales = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        ciclo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Importar Datos");

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Copiar Todo");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });
        jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton1StateChanged(evt);
            }
        });
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jRadioButton1MousePressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Grados"));

        jLabel4.setText("Actuales");

        Grados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Copiar", "Nombre", "Seccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Grados.setEnabled(false);
        jScrollPane1.setViewportView(Grados);

        Grados_actuales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nombre", "Seccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(Grados_actuales);
        if (Grados_actuales.getColumnModel().getColumnCount() > 0) {
            Grados_actuales.getColumnModel().getColumn(0).setResizable(false);
            Grados_actuales.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(99, 99, 99))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cursos"));

        jScrollPane2.setEnabled(false);

        Cursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Copiar", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Cursos);

        jLabel2.setText("Cursos");

        Cursos_actuales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
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
        jScrollPane4.setViewportView(Cursos_actuales);
        if (Cursos_actuales.getColumnModel().getColumnCount() > 0) {
            Cursos_actuales.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ciclo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cicloItemStateChanged(evt);
            }
        });

        jLabel3.setText("Ciclo Escolar:");

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ciclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
       if(evt.getStateChange() == ItemEvent.SELECTED){
           Grados.setEnabled(false);
           Cursos.setEnabled(false);
           copiar = true;
       } 
       else if (evt.getStateChange() == ItemEvent.DESELECTED){
           Grados.setEnabled(true);
           Cursos.setEnabled(true);
           copiar = false;
       }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton1StateChanged
       
    }//GEN-LAST:event_jRadioButton1StateChanged

    private void jRadioButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MousePressed
       
    }//GEN-LAST:event_jRadioButton1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Si el boton esta activo copio todos los elementos
        if(copiar == true){
            //Creo la relacion de los grados con el ciclo nuevo
            for (int i = 0; i < Id_grado.size(); i++) {
                try {
                    String instruccion = "INSERT INTO asignacioncat(asignacioncat.CicloEscolar_Id,asignacioncat.Grado_Id) VALUES("+ciclo_actual+","+Id_grado.get(i)+");";
                    PreparedStatement  pst = base.prepareStatement(instruccion);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Importar_datos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Creo la relacion de los curso con el ciclo nuevo
            for (int i = 0; i < Id_curso.size(); i++) {
                try {
                    String instruccion = "INSERT INTO asignacioncat(asignacioncat.CicloEscolar_Id,asignacioncat.Curso_Id) VALUES("+ciclo_actual+","+Id_curso.get(i)+");";
                    PreparedStatement  pst = base.prepareStatement(instruccion);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Importar_datos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }
        //Si no copio solo los elementos seleccinados
        else{
            DefaultTableModel tabla_grados = (DefaultTableModel)Grados.getModel();
            DefaultTableModel tabla_cursos = (DefaultTableModel)Cursos.getModel();
            for (int i = 0; i < Id_grado.size(); i++) {
                try {
                    boolean marcado = (boolean) tabla_grados.getValueAt(i, 0);
                    if(marcado== true){
                    String instruccion = "INSERT INTO asignacioncat(asignacioncat.CicloEscolar_Id,asignacioncat.Grado_Id) VALUES("+ciclo_actual+","+Id_grado.get(i)+");";
                    PreparedStatement  pst = base.prepareStatement(instruccion);
                    pst.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Importar_datos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Creo la relacion de los curso con el ciclo nuevo
            for (int i = 0; i < Id_curso.size(); i++) {
                try {
                    boolean marcado = (boolean) tabla_cursos.getValueAt(i, 0);
                    if(marcado == true){
                    String instruccion = "INSERT INTO asignacioncat(asignacioncat.CicloEscolar_Id,asignacioncat.Curso_Id) VALUES("+ciclo_actual+","+Id_curso.get(i)+");";
                    PreparedStatement  pst = base.prepareStatement(instruccion);
                    pst.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Importar_datos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }
        Actualizar_actuales();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cicloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cicloItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED){
            posicion = ciclo.getSelectedIndex();
            Actualizar_copias();
        }
    }//GEN-LAST:event_cicloItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Importar_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Importar_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Importar_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Importar_datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Importar_datos dialog = new Importar_datos(new javax.swing.JFrame(), true);
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
    
    public void Actualizar_copias(){
         try {
             //Grados.getColumnModel().getColumn(0).setPreferredWidth(20);
             //Cursos.getColumnModel().getColumn(0).setPreferredWidth(20);
             DefaultTableModel tabla_grados = (DefaultTableModel)Grados.getModel();
             DefaultTableModel tabla_cursos = (DefaultTableModel)Cursos.getModel();
             
             copiar= true;
             this.base = base;
             
             Id_grado = new ArrayList<String>();
             Id_curso = new ArrayList<String>();
             //Tomo todos los grados del ciclo a copiar
             Statement a = base.createStatement();
             ResultSet consulta = a.executeQuery("SELECT grado.Id, grado.Nombre, grado.Seccion FROM asignacioncat INNER JOIN grado ON asignacioncat.Grado_Id = grado.Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id WHERE asignacioncat.CicloEscolar_Id ="+Id_ciclo.get(posicion)+" AND asignacioncat.Curso_Id is null AND asignacioncat.Catedratico_Id is null;");
             while(consulta.next()){
                 String Id = consulta.getString(1);
                 Object[] fila = new Object[3];
                 fila[0] = false;
                 fila[1] = consulta.getString(2);
                 fila[2] = consulta.getString(3);
                 tabla_grados.addRow(fila);
                 Id_grado.add(Id);
             }
             Grados.setModel(tabla_grados);
             //Tomo todos los cursos del ciclo a copiar
             a = base.createStatement();
             consulta = a.executeQuery("SELECT curso.Id,curso.Nombre FROM asignacioncat INNER JOIN curso ON asignacioncat.Curso_Id = curso.Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id WHERE asignacioncat.CicloEscolar_Id ="+Id_ciclo.get(posicion)+" AND asignacioncat.Grado_Id is null AND asignacioncat.Catedratico_Id is null;");
             while(consulta.next()){
                 String Id = consulta.getString(1);
                 Object[] fila = new Object[2];
                 fila[0] = false;
                 fila[1] = consulta.getString(2);
                 tabla_cursos.addRow(fila);
                 Id_curso.add(Id);
             }
             Cursos.setModel(tabla_cursos);
         } catch (SQLException ex) {
             Logger.getLogger(Importar_datos.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void Actualizar_actuales(){
        Statement a;
        try {
            a = base.createStatement();
            ResultSet consulta_Grados = a.executeQuery("SELECT grado.Nombre,grado.Seccion, grado.Id FROM AsignacionCAT INNER JOIN grado ON AsignacionCAT.Grado_Id = grado.Id WHERE AsignacionCAT.CicloEscolar_Id="+ciclo_actual+" AND AsignacionCAT.Curso_Id is null ANd AsignacionCAT.Catedratico_Id is null;");
            a = base.createStatement();
            ResultSet consulta_Curso = a.executeQuery("SELECT curso.Nombre, curso.Id FROM asignacioncat INNER JOIN curso ON asignacioncat.Curso_Id = curso.Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id WHERE asignacioncat.CicloEscolar_Id = "+ciclo_actual+" AND asignacioncat.Grado_Id is null AND asignacioncat.Catedratico_Id is null;");
            if(consulta_Grados.next()){
                Tabla_Grados(consulta_Grados);
            }
            else{
                No_editable tabla = new No_editable();
                tabla.addColumn("No");
                tabla.addColumn("Nombre");
                tabla.addColumn("Sección");
                Grados_actuales.setModel(tabla);
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
                Cursos_actuales.setModel(tabla);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Ciclo_Escolar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Tabla_Grados(ResultSet consulta) throws SQLException{
        No_editable tabla = new No_editable();
        tabla.addColumn("No");
        tabla.addColumn("Nombre");
        tabla.addColumn("Sección");
        int cont = 1;
        String[] fila = new String[3];
        fila[0] = Integer.toString(cont);
        fila[1] = consulta.getString(1);
        fila[2] = consulta.getString(2);
        
        tabla.addRow(fila);
        while(consulta.next()){
            cont++;
            fila[0] = Integer.toString(cont);
            fila[1] = consulta.getString(1);
            fila[2] = consulta.getString(2);
            tabla.addRow(fila);
            
        }
        tabla.isCellEditable(0, 0);
        Grados_actuales.setModel(tabla);
    }
    
    public void Tabla_cursos(ResultSet consulta) throws SQLException{
        No_editable tabla = new No_editable();
        String[] fila = new String[2];
        tabla.addColumn("No");
        tabla.addColumn("Nombre");
        int cont = 1;
        
        fila[0] = Integer.toString(cont);
        fila[1] = consulta.getString(1);
        
        tabla.addRow(fila);
        while (consulta.next()){
            cont++;
            fila[0] = Integer.toString(cont);
            fila[1] = consulta.getString(1);
            tabla.addRow(fila);
        }
    
        Cursos_actuales.setModel(tabla);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Cursos;
    private javax.swing.JTable Cursos_actuales;
    private javax.swing.JTable Grados;
    private javax.swing.JTable Grados_actuales;
    private javax.swing.JComboBox<String> ciclo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
