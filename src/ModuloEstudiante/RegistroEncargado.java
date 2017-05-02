/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

/**
 * Clase que modela un REGISTRO PARA UN ENCARGADO. Dicho registro es usado para almacenar temporalmente los datos de los
 * Encargados que se van a guardar en la Base de Datos.
 * @author Wilson Xicará
 */
public class RegistroEncargado {
    private int num, ID;
    private boolean estaEnBD;
    private int municipioId;
    private String DPI, nombre, direccion, municipio, fechaNacimiento;
    private String telefono, celular, trabajo;

    public RegistroEncargado() {
        num = ID = municipioId = 0;
        estaEnBD = false;
        DPI = nombre = direccion = municipio = fechaNacimiento = telefono = celular = trabajo = "";
    }

    public int getNum() { return num; }
    public int getID() { return ID; }
    public boolean getEstaEnBD() { return estaEnBD; }
    public int getMunicipioId() { return municipioId; }
    public String getDPI() { return DPI; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getMunicipio() { return municipio; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getTelefono() { return telefono; }
    public String getCelular() { return celular; }
    public String getTrabajo() { return trabajo; }

    public void setNum(int num) { this.num = num; }
    public void setID(int ID) { this.ID = ID; }
    public void setEstaEnBD(boolean estaEnBD) { this.estaEnBD = estaEnBD; }
    public void setMunicipioId(int municipioId) { this.municipioId = municipioId; }
    public void setDPI(String DPI) { this.DPI = DPI; }
    public void setNombre(String nombre) { this.nombre = nombre; }
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
     * "Nombre Completo",
     * "Dirección",
     * "Municipio",
     * "Fecha Nacimiento",
     * "Relación con Estudiante",
     * "Teléfono",
     * "Celular",
     * "Trabajo u oficio"
     * @return los datos del encargado para ser insertados en una fila de la Tabla de Encargados
     */
    public String[] getDatosParaTabla() {
        return new String[]{
            ""+num,
            DPI,
            nombre,
            direccion,
            municipio,
            fechaNacimiento,
            telefono,
            celular,
            trabajo
        };
    }
}
