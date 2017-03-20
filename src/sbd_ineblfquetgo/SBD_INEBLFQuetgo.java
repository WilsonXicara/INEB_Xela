/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbd_ineblfquetgo;

import ModuloEstudiante.PrincipalEstudiante;
import Conexion.conexion;

/**
 *
 * @author Wilson Xicará
 */
public class SBD_INEBLFQuetgo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrincipalEstudiante principal = new PrincipalEstudiante(new conexion().Conectar());
        principal.setVisible(true);
    }
    
}
