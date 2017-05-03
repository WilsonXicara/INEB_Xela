/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import java.util.ArrayList;

/**
 *
 * @author Wilson Xicará
 */
public class RegistroCiclo {
    private String anio;
    private ArrayList<RegistroGrado> grados;

    public RegistroCiclo() {
        this.anio = "";
        grados = new ArrayList<>();
    }
    public RegistroCiclo(String anio) {
        this.anio = anio;
        grados = new ArrayList<>();
    }

    public String getAnio() { return anio; }
    public RegistroGrado getGrado(int index) { return grados.get(index); }
    public ArrayList<RegistroGrado> getGrados() { return grados; }

    public void setAnio(String anio) { this.anio = anio; }
    public void addGrado(RegistroGrado grado) { this.grados.add(grado); }
    public void addGrados(ArrayList<RegistroGrado> grados) { this.grados = grados; }
}
