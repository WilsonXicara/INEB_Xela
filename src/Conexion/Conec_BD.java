/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author USUARIO
 */
public class Conec_BD {
    JPanel panel1;
    JLabel etiquetas[];
    JTextField cajas[];
    JPasswordField con;
    JFrame a;
    JDialog ventana;
    JButton aceptar;
    String nombre,usuario,contraseña,Ip;
    
    public Conec_BD(){
        a = new JFrame("Datos Base de Datos");
        ventana = new JDialog(a);
        ventana.setLayout(null);
        ventana.setModal(true);
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                nombre = cajas[0].getText();
                usuario = cajas[1].getText();
                contraseña = con.getText();
                Ip = cajas[2].getText();
                if(nombre.equals("")==false && usuario.equals("")==false && contraseña.equals("")==false){
                    ventana.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(ventana, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE, null);
                }
                
            }
        });
        crear_ventana();
        panel1.setBounds(10, 10, 400 , 120);
        aceptar.setBounds(150,130,150,40);
        ventana.setLocation(new Point(300, 300));
        ventana.setSize(450, 250);
        ventana.add(panel1);
        ventana.add(aceptar);
        ventana.setResizable(false);
        ventana.setVisible(true);
        
        
    }
    
    public void crear_ventana(){
        panel1 = new JPanel(new GridLayout(4, 2));
        etiquetas = new JLabel[4];
        cajas = new JTextField[3];
        etiquetas[0] = new JLabel("Nombre de la Base de Datos: ");
        etiquetas[1] = new JLabel("Nombre del usuario: ");
        etiquetas[2] = new JLabel("Direccion IP: ");
        etiquetas[3] = new JLabel("Contraseña: ");
        cajas[0] = new JTextField();
        cajas[1] = new JTextField();
        cajas[2] = new JTextField();
        con = new JPasswordField();
        panel1.add(etiquetas[0]);
        panel1.add(cajas[0]);
        panel1.add(etiquetas[1]);
        panel1.add(cajas[1]);
        panel1.add(etiquetas[2]);
        panel1.add(cajas[2]);
        panel1.add(etiquetas[3]);
        panel1.add(con);
        
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getIp() {
        return Ip;
    }
    
    
}
