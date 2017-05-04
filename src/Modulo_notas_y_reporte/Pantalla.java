/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_notas_y_reporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Catedratico.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
//import modulo.prestamo.libro.MóduloCurso;

/**
 *
 * @author nasc_
 */
public class Pantalla extends javax.swing.JFrame {

    DefaultTableModel model;
    Connection conexion;
    Statement sent;
    String año;
    ArrayList<String> ID, ID1, ID_ESTUDIANTE;
    int posicion;
    int Id_curso;
    int Id_cat;
    int Id_cicloescolar;
    String cicloesc, nf;
    ResultSet est, b;
    JFrame va;
    float Notafinal, n1,n2,n3,n4;
    /**
     * Creates new form Pantalla
     */
    ModuloCurso cn = new ModuloCurso();

    
    //es este
    public Pantalla() throws SQLException {
        /*initComponents();
        this.setLocationRelativeTo(null);
        conexion = cn.Conectar();
        //validar(tipo);
        limpiar();
        deshabilitar();
        Id_cicloescolar = 5;
        Id_curso = 13;
        //cargar("");
        Calendar fecha = new GregorianCalendar();
        año = Integer.toString(fecha.get(Calendar.YEAR));
        posicion = 0;
        llenar();
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent we) {
                cerrar();
            }

            @Override
            public void windowClosed(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //Cargar_Datos_Estudiante();*/
    }
    public Pantalla(Connection conex, int cat, int cur, int ciclo, /*ResultSet a,*/ JFrame v) throws SQLException {
        initComponents();
        conexion = conex;
        limpiar();
        deshabilitar();
        //cargar("");
        Calendar fecha = new GregorianCalendar();
        año = Integer.toString(fecha.get(Calendar.YEAR));
        posicion = 0;
        Id_cicloescolar = ciclo;
        Id_curso = cur;
        Id_cat = cat;
        //b = a;
        va = v;
        llenar();
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent we) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent we) {
                cerrar();
            }

            @Override
            public void windowClosed(WindowEvent we) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified(WindowEvent we) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent we) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    /*public void Cargar_Datos_Estudiante() throws SQLException
    {
        //EstudianteCB.removeAllItems();
        try {
            Statement consulta=(Statement) conexion.createStatement();
            ResultSet est = consulta.executeQuery ("SELECT Estudiante.Nombres, Estudiante.Id FROM Estudiante INNER JOIN Asignacionest ON Estudiante.Id = Asignacionest.Estudiante_Id INNER JOIN Cicloescolar ON Asignacionest.Cicloescolar_Id = Cicloescolar.Id INNER JOIN AsignacionCAT ON Cicloescolar.Id = AsignacionCAT.Cicloescolar_Id INNER JOIN Curso ON AsignacionCAT.Curso_Id = Curso.Id WHERE Asignacionest.Cicloescolar_ID = " + "5" + "AND Curso.Id = " + Id_curso + " GROUP BY Estudiante.Id ORDER BY Apellidos;");
            //Statement sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            //est = sentencia.executeQuery("SELECT Estudiante.Apellidos, Estudiante.Nombres, Estudiante.Id FROM Estudiante INNER JOIN Asignacionest ON Estudiante.Id = Asignacionest.Estudiante_Id INNER JOIN Cicloescolar ON Asignacionest.Cicloescolar_Id = Cicloescolar.Id INNER JOIN AsignacionCAT ON Cicloescolar.Id = AsignacionCAT.Cicloescolar_Id INNER JOIN Curso ON AsignacionCAT.Curso_Id = Curso.Id WHERE Asignacionest.Cicloescolar_ID = " + cicloesc + "AND Curso.Id = " + Id_curso + " GROUP BY Estudiante.Id ORDER BY Apellidos;");
            while(est.next())
            {
                EstudianteCB.addItem(est.getString(1));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error sql no se pueden leer datos");
        }
        
    }*/
    public void cerrar()
    {
        if((Nota1.getText() != "0")||(Nota2.getText() != "0")||(Nota3.getText() != "0")||(Nota4.getText() != "0")||(NotaR.getText() != "0"))
        {
            String[] opciones = new String[]{"SI", "NO"};
            int opcion = JOptionPane.showOptionDialog(this,"Desea guardar los cambios realizados", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            //System.out.println(opcion);
            if(opcion == JOptionPane.YES_OPTION)
            {
                try {
                    String sql = "UPDATE Notas SET Nota1 =?, Nota2 =?, Nota3 =?, Nota4 =?, NotaFinal =?, NotaRecuperacion =? WHERE Notas.Id =?";
                    int fila = modificacion.getSelectedRow();
                    String dao = (String)modificacion.getValueAt(fila, 0);
                    PreparedStatement ps = conexion.prepareStatement(sql);
                    String a = Integer.toString(Id_curso);
                    n1 = Integer.parseInt(Nota1.getText());
                    n2 = Integer.parseInt(Nota2.getText());
                    n3 = Integer.parseInt(Nota3.getText());
                    n4 = Integer.parseInt(Nota4.getText());
                    Notafinal = ((n1+n2+n3+n4)/4);
                    nf = Float.toString(Notafinal);
                    if(((n1>=0)&&(n1<=100))&&((n2>=0)&&(n2<=100))&&((n3>=0)&&(n3<=100))&&((n4>=0)&&(n4<=100)))
                    {
                        ps.setString(1, Nota1.getText());
                        ps.setString(2, Nota2.getText());
                        ps.setString(3, Nota3.getText());
                        ps.setString(4, Nota4.getText());
                        ps.setString(5, nf);
                        ps.setString(6, NotaR.getText());
                        ps.setString(7, dao);

                        int n = ps.executeUpdate();
                        if(n>0)
                        {
                            limpiar();
                            llenar();
                            //prueba();
                            JOptionPane.showMessageDialog(null, "Datos modificados");
                        } 
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Una de las notas no tiene un valor entre '0' y '100'");
                    }

                    //llenar();
                    //limpiar();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
                }
                
            }
        }
        va.setEnabled(true);
        this.dispose();
    }
    
    void validar(int tip)
    {
        if(tip == 1)
        {
            Modificar.setEnabled(false);
        }
        else
        {
            Modificar.setEnabled(true);
        }
    }

    void limpiar() {
        //CursoID.setText("");
        //EstudianteID.setText("");
        Nota1.setText("0");
        Nota2.setText("0");
        Nota3.setText("0");
        Nota4.setText("0");
        NotaR.setText("0");
    }

    void deshabilitar() {
        //CursoID.setEditable(false);
        //EstudianteID.setEditable(false);
        Nota1.setEditable(false);
        Nota2.setEditable(false);
        Nota3.setEditable(false);
        Nota4.setEditable(false);
        NotaR.setEditable(false);
        Modificar.setEnabled(false);
    }

    void habilitar() {
        //CursoID.setEditable(true);
        //EstudianteID.setEditable(true);
        Nota1.setEditable(true);
        Nota2.setEditable(true);
        Nota3.setEditable(true);
        Nota4.setEditable(true);
        NotaR.setEditable(true);
        Modificar.setEnabled(true);
        //CursoID.requestFocus();
    }

    
    void llenar() {
        try {
            //String j = ciclo.getSelectedItem().toString();
            //conexion = cn.Conectar();
            String[] titulos = {"a","Codigo Personal", "Apellidos", "Nombre", "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Nota Final", "Nota Recuperacion"};
            String sql = "SELECT Notas.ID, Estudiante.CodigoPersonal, Estudiante.Apellidos, Estudiante.Nombres, notas.Nota1, notas.Nota2, notas.Nota3, notas.Nota4, notas.NotaFinal, notas.NotaRecuperacion FROM notas INNER JOIN estudiante ON notas.Estudiante_Id = estudiante.Id INNER JOIN asignacionest ON asignacionest.Estudiante_Id = estudiante.Id INNER JOIN cicloescolar ON asignacionest.CicloEscolar_Id = cicloescolar.Id WHERE asignacionest.CicloEscolar_Id = " + Id_cicloescolar + " AND notas.Curso_Id = "+ Id_curso +" ORDER BY Estudiante.Apellidos ASC;";
            model = new DefaultTableModel(null, titulos);
            sent = conexion.createStatement();
            ResultSet rs = sent.executeQuery(sql);

            String[] fila = new String[10];

            while (rs.next()) {
                fila[0] = rs.getString("Notas.ID");
                fila[1] = rs.getString("Estudiante.CodigoPersonal");
                fila[2] = rs.getString("Estudiante.Apellidos");
                fila[3] = rs.getString("Estudiante.Nombres");
                fila[4] = rs.getString("Notas.Nota1");
                fila[5] = rs.getString("Notas.Nota2");
                fila[6] = rs.getString("Notas.Nota3");
                fila[7] = rs.getString("Notas.Nota4");
                fila[8] = rs.getString("Notas.NotaFinal");
                fila[9] = rs.getString("Notas.NotaRecuperacion");

                model.addRow(fila);
            }
            modificacion.setModel(model);
            modificacion.getColumnModel().getColumn(0).setMaxWidth(0);
            modificacion.getColumnModel().getColumn(0).setMinWidth(0);
            modificacion.getColumnModel().getColumn(0).setPreferredWidth(0);
            modificacion.getColumnModel().getColumn(0).setResizable(false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //funcion que obtiene el año del pc
    
    /*void cargar(String valor)
    {
        try {
            //String j = ciclo.getSelectedItem().toString();
            //conexion = cn.Conectar();
            String [] titulos={"ID (código)", "Nombre", "Código catedrático"};
            String [] fila = new String [3];
            String sql = "SELECT curso.Id, curso.Nombre, asignacioncat.Catedratico_Id FROM curso INNER JOIN asignacioncat ON curso.Id = asignacioncat.Curso_Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id WHERE cicloescolar.anio = '"  + "' AND Curso.Nombre LIKE '%" + valor + "%';";
        
            model = new DefaultTableModel(null, titulos);
            sent = conexion.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            
            while (rs.next()) {
                fila[0] = rs.getString("Curso.Id");
                fila[1] = rs.getString("Curso.Nombre");
                fila[2] = rs.getString("AsignacionCAT.Catedratico_Id");

                model.addRow(fila);
            }
            Cursos.setModel(model);
            
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }*/
    
    /*void prueba()
    {
        if(ciclo.getSelectedIndex()!=-1){
        posicion = ciclo.getSelectedIndex();
        String Id = ID.get(posicion);
        //cicloesc = Id;
            try {
                //String j = ciclo.getSelectedItem().toString();
                //conexion = cn.Conectar();
                String [] titulos={"Catedratico", "Curso", "Código catedrático"};
                String [] fila = new String [3];
                String sql = "SELECT curso.Id, curso.Nombre, asignacioncat.Catedratico_Id FROM curso INNER JOIN asignacioncat ON curso.Id = asignacioncat.Curso_Id INNER JOIN cicloescolar ON asignacioncat.CicloEscolar_Id = cicloescolar.Id WHERE asignacioncat.CicloEscolar_Id = " + Id + ";"; // AND Curso.Id = " + Id_curso + "

                model = new DefaultTableModel(null, titulos);
                sent = conexion.createStatement();
                ResultSet rs = sent.executeQuery(sql);

                while (rs.next()) {
                    fila[0] = rs.getString("Curso.Id");
                    fila[1] = rs.getString("Curso.Nombre");
                    fila[2] = rs.getString("AsignacionCAT.Catedratico_Id");

                    model.addRow(fila);
                }
                
                Cursos.setModel(model);

            } catch (Exception e) {
                e.printStackTrace();
            }  
        }
    }*/

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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        modificacion = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Nota4 = new javax.swing.JTextField();
        Nota3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Nota2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Nota1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        NotaR = new javax.swing.JTextField();
        Modificar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Codigo = new javax.swing.JTextField();
        Ape = new javax.swing.JTextField();
        Nom = new javax.swing.JTextField();

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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estudiantes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.black)); // NOI18N

        modificacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        modificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(modificacion);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso de notas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.black));

        jLabel4.setText("Nota 4");

        Nota4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Nota4KeyTyped(evt);
            }
        });

        Nota3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Nota3KeyTyped(evt);
            }
        });

        jLabel3.setText("Nota 3");

        Nota2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Nota2KeyTyped(evt);
            }
        });

        jLabel2.setText("Nota 2");

        jLabel1.setText("Nota 1");

        Nota1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Nota1KeyTyped(evt);
            }
        });

        jLabel6.setText("Nota recuperación");

        NotaR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NotaRKeyTyped(evt);
            }
        });

        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nota2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nota3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Nota1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(NotaR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Nota4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Modificar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Nota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(NotaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Nota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Nota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Nota4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.black));

        jLabel5.setText("Nombres");

        jLabel7.setText("Apellidos");

        jLabel8.setText("Código Personal");

        Codigo.setEditable(false);
        Codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoActionPerformed(evt);
            }
        });

        Ape.setEditable(false);
        Ape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApeActionPerformed(evt);
            }
        });

        Nom.setEditable(false);
        Nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel7))
                    .addComponent(Ape, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Nom, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel5)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Ape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modificacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificacionMouseClicked
        // TODO add your handling code here:
        if(evt.getButton() == 1)
        {
            int fila = modificacion.getSelectedRow();
            try {
                habilitar();
                String sql = "SELECT *FROM Notas WHERE Notas.Id=" + modificacion.getValueAt(fila, 0);
                sent = conexion.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next();
                //CursoID.setText(rs.getString("Curso_Id"));
                //EstudianteID.setText(rs.getString("Estudiante_Id"));
                Nota1.setText(rs.getString("Nota1"));
                Nota2.setText(rs.getString("Nota2"));
                Nota3.setText(rs.getString("Nota3"));
                Nota4.setText(rs.getString("Nota4"));
                NotaR.setText(rs.getString("NotaRecuperacion"));
                Codigo.setText((String)modificacion.getValueAt(fila, 1));
                Ape.setText((String)modificacion.getValueAt(fila, 2));
                Nom.setText((String)modificacion.getValueAt(fila, 3));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_modificacionMouseClicked

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "UPDATE Notas SET Nota1 =?, Nota2 =?, Nota3 =?, Nota4 =?, NotaFinal =?, NotaRecuperacion =? WHERE Notas.Id =?";
            int fila = modificacion.getSelectedRow();
            String dao = (String)modificacion.getValueAt(fila, 0);
            PreparedStatement ps = conexion.prepareStatement(sql);
            String a = Integer.toString(Id_curso);
            n1 = Integer.parseInt(Nota1.getText());
            n2 = Integer.parseInt(Nota2.getText());
            n3 = Integer.parseInt(Nota3.getText());
            n4 = Integer.parseInt(Nota4.getText());
            Notafinal = ((n1+n2+n3+n4)/4);
            nf = Float.toString(Notafinal);
            if(((n1>=0)&&(n1<=100))&&((n2>=0)&&(n2<=100))&&((n3>=0)&&(n3<=100))&&((n4>=0)&&(n4<=100)))
            {
                ps.setString(1, Nota1.getText());
                ps.setString(2, Nota2.getText());
                ps.setString(3, Nota3.getText());
                ps.setString(4, Nota4.getText());
                ps.setString(5, nf);
                ps.setString(6, NotaR.getText());
                ps.setString(7, dao);

                int n = ps.executeUpdate();
                if(n>0)
                {
                    limpiar();
                    llenar();
                    //prueba();
                    deshabilitar();
                    
                    JOptionPane.showMessageDialog(null, "Datos modificados");
                } 
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Una de las notas no tiene un valor entre '0' y '100'");
            }
            
            //llenar();
            //limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
           
        //ModuloPrincipalCatedratico a;
            //a = new ModuloPrincipalCatedratico(conexion, b);
        
    }//GEN-LAST:event_formWindowClosing

    private void CodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoActionPerformed

    private void ApeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApeActionPerformed

    private void NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomActionPerformed

    private void Nota1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nota1KeyTyped
        // TODO add your handling code here:
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
    }//GEN-LAST:event_Nota1KeyTyped

    private void Nota2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nota2KeyTyped
        // TODO add your handling code here:
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
    }//GEN-LAST:event_Nota2KeyTyped

    private void Nota3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nota3KeyTyped
        // TODO add your handling code here:
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
    }//GEN-LAST:event_Nota3KeyTyped

    private void Nota4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nota4KeyTyped
        // TODO add your handling code here:
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
    }//GEN-LAST:event_Nota4KeyTyped

    private void NotaRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NotaRKeyTyped
        // TODO add your handling code here:
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
    }//GEN-LAST:event_NotaRKeyTyped

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
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Pantalla().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Ape;
    private javax.swing.JTextField Codigo;
    private javax.swing.JButton Modificar;
    private javax.swing.JTextField Nom;
    private javax.swing.JTextField Nota1;
    private javax.swing.JTextField Nota2;
    private javax.swing.JTextField Nota3;
    private javax.swing.JTextField Nota4;
    private javax.swing.JTextField NotaR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable modificacion;
    // End of variables declaration//GEN-END:variables
}
