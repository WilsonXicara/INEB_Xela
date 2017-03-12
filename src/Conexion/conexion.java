/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.*;


/**
 *
 * @author USUARIO
 */
public class conexion {
    Connection conect = null;
    
    public Connection Conectar(){
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ejemplo","root","sergio2710");
        } catch (Exception e) {
            System.out.println("Fallo");
        }
        
        return conect;
    }
    
}
