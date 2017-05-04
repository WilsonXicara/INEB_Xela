/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_notas_y_reporte;
import Modulo_notas_y_reporte.ModuloCurso;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nasc_
 */
public class Ventanareporte extends javax.swing.JFrame {

    /**
     * Creates new form Ventanareporte
     */
    DefaultTableModel model;
    Connection conexion;
    Statement sent;
    ModuloCurso cn = new ModuloCurso();
    String id_estudiante;
    String ciclo;
    JFrame va;
    
    public Ventanareporte() {
        /*initComponents();
        conexion = cn.Conectar();
        año();
        limpiar();
        deshabilitar();
        cargar();
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent we) {
                
            }

            @Override
            public void windowClosing(WindowEvent we) {
                cerrar();
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
        });*/
    }
    public Ventanareporte(Connection conec, JFrame ventana)
    {
        initComponents();
        this.setLocationRelativeTo(null);
        conexion = conec;
        va = ventana;
        año();
        limpiar();
        deshabilitar();
        cargar();
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent we) {
                
            }

            @Override
            public void windowClosing(WindowEvent we) {
                cerrar();
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
    public void cerrar()
    {
        if((fecha.getText() != "")&&(descrip.getText() != ""))
        {
            String[] opciones = new String[]{"SI", "NO"};
            int opcion = JOptionPane.showOptionDialog(this,"Desea guardar los cambios realizados", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            //System.out.println(opcion);
            if(opcion == JOptionPane.YES_OPTION)
            {
               try {
                    String sql = "INSERT INTO Reporte (Fecha, Descripcion, Estudiante_Id)" + "VALUES(?,?,?)";
                    PreparedStatement ps = conexion.prepareCall(sql);
                    ps.setString(1, fecha.getText());
                    ps.setString(2, descrip.getText());
                    ps.setString(3, id_estudiante);

                    int n = ps.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "Datos Guardados Correctamente");
                    }
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
                }
                limpiar();
                cargar();
                deshabilitar();
            }
        }
        va.setEnabled(true);
        this.dispose();
    }
    void limpiar() {
        fecha.setText("");
        descrip.setText("");   
    }
    void deshabilitar() {
        fecha.setEditable(false);
        descrip.setEditable(false); 
        Guardar.setEnabled(false);
    }

    void habilitar() {
        fecha.setEditable(true);
        descrip.setEditable(true);
        Guardar.setEnabled(true);
    }
    
    
    
    void año()
    {
        try
        {
            conexion = cn.Conectar();
            String sql = "SELECT MAX(ID) FROM cicloescolar;";
            sent = conexion.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            rs.next();
            ciclo = rs.getString(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    void cargar()
    {
        try {
            conexion = cn.Conectar();
            String [] titulos={"a", "Apellidos", "Nombre"};
            String [] fila = new String [3];
        
            String sql = "SELECT Estudiante.ID, Estudiante.Apellidos, Estudiante.Nombres FROM Estudiante INNER JOIN AsignacionEst ON Estudiante.ID = AsignacionEst.Estudiante_ID WHERE AsignacionEst.CicloEscolar_ID = " + 5 +  " ORDER BY Estudiante.Apellidos ASC;";
        
            model = new DefaultTableModel(null, titulos);
            sent = conexion.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            
            while (rs.next()) {
                fila[0] = rs.getString("Estudiante.ID");
                fila[1] = rs.getString("Estudiante.Apellidos");
                fila[2] = rs.getString("Estudiante.Nombres");

                model.addRow(fila);
            }
            Estudiantes.setModel(model);
            Estudiantes.getColumnModel().getColumn(0).setMaxWidth(0);
            Estudiantes.getColumnModel().getColumn(0).setMinWidth(0);
            Estudiantes.getColumnModel().getColumn(0).setPreferredWidth(0);
            Estudiantes.getColumnModel().getColumn(0).setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        descrip = new javax.swing.JTextField();
        Guardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Estudiantes = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Creación de reporte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.black)); // NOI18N

        jLabel1.setText("Fecha ");

        jLabel2.setText("Descripción");

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        Estudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Estudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EstudiantesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Estudiantes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(descrip))
                        .addGap(18, 18, 18)
                        .addComponent(Guardar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(descrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Guardar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO Reporte (Fecha, Descripcion, Estudiante_Id)" + "VALUES(?,?,?)";
            PreparedStatement ps = conexion.prepareCall(sql);
            ps.setString(1, fecha.getText());
            ps.setString(2, descrip.getText());
            ps.setString(3, id_estudiante);
            
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Datos Guardados Correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        limpiar();
        cargar();
        deshabilitar();
    }//GEN-LAST:event_GuardarActionPerformed

    private void EstudiantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EstudiantesMouseClicked
        // TODO add your handling code here:
        
        if(evt.getButton() == 1)
        {
            int fila = Estudiantes.getSelectedRow();
            try {
                habilitar();
                id_estudiante = (String)Estudiantes.getValueAt(fila, 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_EstudiantesMouseClicked

    
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
            java.util.logging.Logger.getLogger(Ventanareporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventanareporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventanareporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventanareporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventanareporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Estudiantes;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField descrip;
    private javax.swing.JTextField fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
