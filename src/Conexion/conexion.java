/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author USUARIO
 */
public class conexion {
    Connection conect = null;
    
    public Connection Conectar(JFrame ventana){
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost/Usuarios","root","hola");
        } catch (Exception e) {
             String[] opciones = new String[1];
                      opciones[0] = "Aceptar";
                     int choice = JOptionPane.showOptionDialog(null, "Error", "No se ha podido conectar al servidor", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                      if(choice == JOptionPane.YES_OPTION){
                          ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
        }
        
        return conect;
    }
    
}
