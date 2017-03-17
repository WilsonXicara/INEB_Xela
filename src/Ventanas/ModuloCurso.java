/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author nasc_
 */
public class ModuloCurso {

    Connection conect = null;
    
    public Connection Conectar(){
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost/sbd_inebxela","root","chuchumix03");
            //JOptionPane.showMessageDialog(null, "Conecto");
        } catch (Exception e) {
            System.out.println("Fallo");
        }
        
        return conect;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
