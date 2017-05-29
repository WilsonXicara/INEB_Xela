/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import java.util.Date;

/**
 * Clase que modela un REGISTRO PARA UN ENCARGADO. Dicho registro es usado para almacenar temporalmente los datos de los
 * Encargados que se van a guardar en la Base de Datos.
 * @author Wilson Xicará
 */
public class RegistroEncargado {
    private int ID, municipioId;
    private String DPI, nombres, apellidos, direccion, municipio, fechaNacimiento;
    private String telefono, celular, trabajo;

    public RegistroEncargado() {
        ID = municipioId = 0;
        DPI = nombres = apellidos = direccion = municipio = fechaNacimiento = telefono = celular = trabajo = "";
    }

    public int getID() { return ID; }
    public int getMunicipioId() { return municipioId; }
    public String getDPI() { return DPI; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDireccion() { return direccion; }
    public String getMunicipio() { return municipio; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public Date getDateNacimiento() {
        String[] fecha = fechaNacimiento.split("-");
        return new Date(Integer.parseInt(fecha[0])-1900, Integer.parseInt(fecha[1])-1, Integer.parseInt(fecha[2]));
    }
    public String getTelefono() { return telefono; }
    public String getCelular() { return celular; }
    public String getTrabajo() { return trabajo; }

    public void setID(int ID) { this.ID = ID; }
    public void setMunicipioId(int municipioId) { this.municipioId = municipioId; }
    public void setDPI(String DPI) { this.DPI = DPI; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCelular(String celular) { this.celular = celular; }
    public void setTrabajo(String trabajo) { this.trabajo = trabajo; }

    /**
     * Devuelve un array con los datos para ser insertados en una fila de la Tabla de Encargados. Estos datos son:
     * "No.",
     * "DPI",
     * "Nombres",
     * "Apellidos"
     * "Dirección",
     * "Municipio",
     * "Fecha Nacimiento",
     * "Relación con Estudiante",
     * "Teléfono",
     * "Celular",
     * "Trabajo u oficio"
     * @return los datos del encargado para ser insertados en una fila de la Tabla de Encargados
     */
    public String[] getDatosParaTabla(int num) {
        return new String[]{
            ""+num,
            DPI,
            nombres,
            apellidos,
            direccion,
            municipio,
            fechaNacimiento,
            telefono,
            celular,
            trabajo
        };
    }
}
