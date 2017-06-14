/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_Estudiante;

import java.util.ArrayList;

/**
 *
 * @author Wilson Xicará
 */
public class RegistroCiclo {
    private int ID;
    private String anio;
    private boolean cicloListo, cicloCerrado;
    private ArrayList<RegistroGrado> grados;

    public RegistroCiclo() {
        ID = 0;
        anio = "";
        cicloListo = cicloCerrado = false;
        grados = new ArrayList<>();
    }
    public RegistroCiclo(int ID, String anio) {
        this.ID = ID;
        this.anio = anio;
        this.cicloListo = false;
        this.cicloCerrado = false;
        grados = new ArrayList<>();
    }
    public RegistroCiclo(int ID, String anio, boolean cicloListo, boolean cicloCerrado) {
        this.ID = ID;
        this.anio = anio;
        this.cicloListo = cicloListo;
        this.cicloCerrado = cicloCerrado;
        grados = new ArrayList<>();
    }

    public int getID() { return ID; }
    public String getAnio() { return anio; }
    public boolean isCicloListo() { return cicloListo; }
    public boolean isCicloCerrado() { return cicloCerrado; }
    public RegistroGrado getGrado(int index) { return grados.get(index); }
    public ArrayList<RegistroGrado> getGrados() { return grados; }

    public void setID(int ID) { this.ID = ID; }
    public void setAnio(String anio) { this.anio = anio; }
    public void setCicloListo(boolean cicloListo) { this.cicloListo = cicloListo; }
    public void setCicloCerrado(boolean cicloCerrado) { this.cicloCerrado = cicloCerrado; }
    public void addGrado(RegistroGrado grado) { this.grados.add(grado); }
    public void addGrados(ArrayList<RegistroGrado> grados) { this.grados = grados; }
}
