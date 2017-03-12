/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.*;


/**
 * En esta clase se establece la conexión entre la Base de Datos y el programa en Java. Para ello, se debe utilizar atributos
 * de la cuenta de usuario (registrado en MariaDB) para conectarse a través de ese usuario.
 * @author Wilson Xicará
 */
public class ConexionConMariaDB {
    private Connection conexion;
    
    /**
     * Inicia una nueva conexión entre MariaDB y la aplicación Java sobre el usuario especificado en 'nombreUsuario' (que está
     * en los registros de usuarios de MariaDB). Si la conexión es exitosa, se inicializa 'conexion' como el objeto que
     * permitirá la conexión; de lo contrario, 'conexion' queda nulo.
     * 
     * ¡NOTA!
     * Para poder realizar la conexión entre MariaDB y Java se necesita hacer lo siguiente:
     * -> Añadir como librería (Add Library...) el archivo 'C:\Program Files\NetBeans 8.1\ide\modules\ext\mysql-connector-java-5.1.23-bin.jar'
     * 
     * @param nombreBD nombre que tiene la base de datos en MariaDB
     * @param nombreUsuario nombre de usuario que tiene permiso de acceder a la Base de Datos 'nombreDB'
     * @param contraseñaUsuario  contraseña de 'nombreUsuario'
     */
    public ConexionConMariaDB() {
        conexion = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/"+ "ejemplo", "root", "sergio2710");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Fallo. Error en: "+e.getMessage());
        }
    }
    public Connection getConexion() { return conexion; }
}
