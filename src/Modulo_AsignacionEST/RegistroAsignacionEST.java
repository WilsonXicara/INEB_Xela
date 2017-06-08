/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_AsignacionEST;

/**
 *
 * @author Wilson Xicará
 */
public class RegistroAsignacionEST {
    private int ID, cicloEscolarId, gradoId;
    private String codigoPersonal, CUI, nombres, apellidos, sexo;
    private String anio, grado, seccion;
    private boolean asignacionNueva, asignacionAnterior;

    public RegistroAsignacionEST() {
        this.ID = this.cicloEscolarId = this.gradoId = 0;
        this.codigoPersonal = this.CUI = this.nombres = this.apellidos = this.sexo = "";
        this.anio = this.grado = this.seccion = "";
        this.asignacionNueva = this.asignacionAnterior = false;
    }

    public int getID() { return ID; }
    public int getCicloEscolarId() { return cicloEscolarId; }
    public int getGradoId() { return gradoId; }
    public String getCodigoPersonal() { return codigoPersonal; }
    public String getCUI() { return CUI; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getSexo() { return sexo; }
    public String getAnio() { return anio; }
    public String getGrado() { return grado; }
    public String getSeccion() { return seccion; }
    public boolean isAsignacionNueva() { return asignacionNueva; }
    public boolean isAsignacionAnterior() { return asignacionAnterior; }

    public void setID(int ID) { this.ID = ID; }
    public void setCicloEscolarId(int cicloEscolarId) { this.cicloEscolarId = cicloEscolarId; }
    public void setGradoId(int gradoId) { this.gradoId = gradoId; }
    public void setCodigoPersonal(String codigoPersonal) { this.codigoPersonal = codigoPersonal; }
    public void setCUI(String CUI) { this.CUI = CUI; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setAnio(String anio) { this.anio = anio; }
    public void setGrado(String grado) { this.grado = grado; }
    public void setSeccion(String seccion) { this.seccion = seccion; }
    public void setAsignacionNueva(boolean asignacionNueva) { this.asignacionNueva = asignacionNueva; }
    public void setAsignacionAnterior(boolean asignacionAnterior) { this.asignacionAnterior = asignacionAnterior; }

    public String[] getDatosParaTabla(int num) {
        return new String[]{
            ""+num,
            codigoPersonal,
            CUI,
            nombres,
            apellidos,
            (asignacionNueva) ? "SI" : "NO",
            (asignacionAnterior) ? "SI" : "NO",
            anio,
            grado,
            seccion
        };
    }
}
