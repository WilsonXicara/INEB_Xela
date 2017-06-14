/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_Estudiante;

import java.util.Date;

/**
 * Clase que modela un REGISTRO PARA UN ESTUDIANTE. Dicho registro es usado para almacenar temporalmente los datos de los
 * Estudiantes que se van a guardar en la Base de Datos.
 * @author Wilson Xicará
 */
public class RegistroEstudiante {
    private int ID, municipioId, encargadoId, encargadoNum;
    private String codigoPersonal, CUI, nombres, apellidos, direccion, municipio, fechaNacimiento, sexo, etnia, tipoCapacidad;
    private String nombreEncargado, relacionEncargado;
    private boolean capacidadDiferente, guardadoEnBD;

    public RegistroEstudiante() {
        ID = municipioId = encargadoId = encargadoNum = 0;
        capacidadDiferente = guardadoEnBD = false;
        codigoPersonal = CUI = nombres = apellidos = direccion = municipio = fechaNacimiento = sexo = etnia = tipoCapacidad = nombreEncargado = relacionEncargado = "";
    }

    public int getID() { return ID; }
    public int getMunicipioId() { return municipioId; }
    public int getEncargadoId() { return encargadoId; }
    public int getEncargadoNum() { return encargadoNum; }
    public String getCodigoPersonal() { return codigoPersonal; }
    public String getCUI() { return CUI; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDireccion() { return direccion; }
    public String getMunicipio() { return municipio; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public Date getDateNacimiento() {
        String[] fecha = fechaNacimiento.split("-");
        return new Date(Integer.parseInt(fecha[0])-1900, Integer.parseInt(fecha[1])-1, Integer.parseInt(fecha[2]));
    }
    public String getSexo() { return sexo; }
    public String getEtnia() { return etnia; }
    public String getTipoCapacidad() { return tipoCapacidad; }
    public String getNombreEncargado() { return nombreEncargado; }
    public String getRelacionEncargado() { return relacionEncargado; }
    public boolean isCapacidadDiferente() { return capacidadDiferente; }
    public boolean isGuardadoEnBD() { return guardadoEnBD; }

    public void setID(int ID) { this.ID = ID; }
    public void setMunicipioId(int municipioId) { this.municipioId = municipioId; }
    public void setEncargadoId(int encargadoId) { this.encargadoId = encargadoId; }
    public void setEncargadoNum(int encargadoNum) { this.encargadoNum = encargadoNum; }
    public void setCodigoPersonal(String codigoPersonal) { this.codigoPersonal = codigoPersonal; }
    public void setCUI(String CUI) { this.CUI = CUI; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setEtnia(String etnia) { this.etnia = etnia; }
    public void setTipoCapacidad(String tipoCapacidad) { this.tipoCapacidad = tipoCapacidad; }
    public void setNombreEncargado(String nombreEncargado) { this.nombreEncargado = nombreEncargado; }
    public void setRelacionEncargado(String relacionEncargado) { this.relacionEncargado = relacionEncargado; }
    public void setCapacidadDiferente(boolean capacidadDiferente) { this.capacidadDiferente = capacidadDiferente; }
    public void setGuardadoEnBD(boolean guardadoEnBD) { this.guardadoEnBD = guardadoEnBD; }
    /**
     * Devuelve un array con los datos para ser insertados en una fila de la Tabla de Estudiantes. Estos datos son:
     *  "No.",
     *  "Código Personal",
     *  "CUI",
     *  "Nombres",
     *  "Apellidos",
     *  "Dirección",
     *  "Municipio",
     *  "Fecha Nacimiento",
     *  "Sexo",
     *  "Comunidad Étnica",
     *  "Capacidad Diferente",
     *  "Tipo Capacidad"
     *  "Nombre del Encargado"
     * @return los datos del encargado para ser insertados en una fila de la Tabla de Encargados
     */
    public String[] getDatosParaTabla(int num) {
        return new String[]{
            ""+num,
            codigoPersonal,
            CUI,
            nombres,
            apellidos,
            direccion,
            municipio,
            fechaNacimiento,
            sexo.equals("F") ? "Femenino" : "Masculino",
            etnia,
            (capacidadDiferente) ? "Si" : "No",
            tipoCapacidad,
            nombreEncargado,
            relacionEncargado
        };
    }
}
