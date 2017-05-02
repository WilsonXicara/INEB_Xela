/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

/**
 *
 * @author pc
 */
public class HistorialEstudiante extends javax.swing.JDialog {

    /**
     * Creates new form HistorialEstudiante
     */
    public HistorialEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_filtro_busqueda = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        etiqueta_buscar_por = new javax.swing.JLabel();
        filtro_busqueda = new javax.swing.JComboBox<>();
        campo_busqueda = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        ver_historial = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_encontrados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_encontrados1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel_filtro_busqueda.setBackground(new java.awt.Color(153, 153, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ESPECIFICACIÓN DE BÚSQUEDA");

        etiqueta_buscar_por.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etiqueta_buscar_por.setText("Buscar por:");
        etiqueta_buscar_por.setEnabled(false);

        filtro_busqueda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filtro_busqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código Personal", "Apellidos", "Nombres" }));
        filtro_busqueda.setEnabled(false);
        filtro_busqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtro_busquedaItemStateChanged(evt);
            }
        });

        campo_busqueda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        campo_busqueda.setEnabled(false);

        buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar.setText("Nueva Búsqueda");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        ver_historial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ver_historial.setText("Ver Historial");
        ver_historial.setEnabled(false);
        ver_historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_historialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_filtro_busquedaLayout = new javax.swing.GroupLayout(panel_filtro_busqueda);
        panel_filtro_busqueda.setLayout(panel_filtro_busquedaLayout);
        panel_filtro_busquedaLayout.setHorizontalGroup(
            panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtro_busquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_filtro_busquedaLayout.createSequentialGroup()
                        .addComponent(etiqueta_buscar_por)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtro_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ver_historial))
                    .addGroup(panel_filtro_busquedaLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel4)))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        panel_filtro_busquedaLayout.setVerticalGroup(
            panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtro_busquedaLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_filtro_busquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiqueta_buscar_por)
                    .addComponent(filtro_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar)
                    .addComponent(ver_historial))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        tabla_encontrados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_encontrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Código Personal", "CUI", "Nombres", "Apellidos", "Dirección", "Municipio", "Fecha Nacimiento", "Sexo", "Etnia", "Capacidad Diferente", "Tipo Capacidad", "Encargado", "Relación"
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
        tabla_encontrados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_encontrados.setEnabled(false);
        tabla_encontrados.setRowHeight(25);
        tabla_encontrados.getTableHeader().setResizingAllowed(false);
        tabla_encontrados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla_encontrados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        tabla_encontrados1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabla_encontrados1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Ciclo Esoclar", "Grado", "Sección", "Aula", "Nota Final"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_encontrados1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla_encontrados1.setEnabled(false);
        tabla_encontrados1.setRowHeight(25);
        tabla_encontrados1.getTableHeader().setResizingAllowed(false);
        tabla_encontrados1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabla_encontrados1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Historial de");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 205, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_filtro_busqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_filtro_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filtro_busquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtro_busquedaItemStateChanged
        campo_busqueda.setEnabled(!(filtro_busqueda.getSelectedIndex() == 0));
    }//GEN-LAST:event_filtro_busquedaItemStateChanged

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        
    }//GEN-LAST:event_buscarActionPerformed

    private void ver_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_historialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ver_historialActionPerformed
    
    /**
     * Método que define el ancho de las columnas de ambas tablas, en base a valores definidos previamente por pruebas.
     */
    private void definir_ancho_columnas() {
        // Definición del ancho de las columnas para la Tabla Encontrados (valores definidos en base a pruebas)
        tabla_encontrados.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla_encontrados.getColumnModel().getColumn(1).setPreferredWidth(110);
        tabla_encontrados.getColumnModel().getColumn(2).setPreferredWidth(135);
        tabla_encontrados.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabla_encontrados.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabla_encontrados.getColumnModel().getColumn(5).setPreferredWidth(175);
        tabla_encontrados.getColumnModel().getColumn(6).setPreferredWidth(180);
        tabla_encontrados.getColumnModel().getColumn(7).setPreferredWidth(120);
        tabla_encontrados.getColumnModel().getColumn(8).setPreferredWidth(80);
        tabla_encontrados.getColumnModel().getColumn(9).setPreferredWidth(80);
        tabla_encontrados.getColumnModel().getColumn(10).setPreferredWidth(125);
        tabla_encontrados.getColumnModel().getColumn(11).setPreferredWidth(230);
        tabla_encontrados.getColumnModel().getColumn(12).setPreferredWidth(330);
        tabla_encontrados.getColumnModel().getColumn(13).setPreferredWidth(115);
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
            java.util.logging.Logger.getLogger(HistorialEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistorialEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistorialEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistorialEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HistorialEstudiante dialog = new HistorialEstudiante(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton buscar;
    private javax.swing.JTextField campo_busqueda;
    private javax.swing.JLabel etiqueta_buscar_por;
    private javax.swing.JComboBox<String> filtro_busqueda;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panel_filtro_busqueda;
    private javax.swing.JTable tabla_encontrados;
    private javax.swing.JTable tabla_encontrados1;
    private javax.swing.JButton ver_historial;
    // End of variables declaration//GEN-END:variables
}