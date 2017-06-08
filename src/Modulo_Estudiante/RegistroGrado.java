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
public class RegistroGrado {
    private int ID;
    private String nombre, seccion;
    private ArrayList<String> listaCursos;

    public RegistroGrado() {
        this.ID = 0;
        this.nombre = this.seccion = "";
        this.listaCursos = new ArrayList<>();
    }
    public RegistroGrado(int id, String nombre, String seccion) {
        this.ID = id;
        this.nombre = nombre;
        this.seccion = seccion;
        this.listaCursos = new ArrayList<>();
    }

    public int getID() { return ID; }
    public String getNombre() { return nombre; }
    public String getSeccion() { return seccion; }
    public ArrayList<String> getListaCursos() { return listaCursos; }

    public void setID(int ID) { this.ID = ID; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setSeccion(String seccion) { this.seccion = seccion; }
    public void addCurso(String curso) { this.listaCursos.add(curso); }
    public void addCursos(ArrayList<String> listaCursos) { this.listaCursos = listaCursos; }

    public String getGradoSeccion() { return nombre+" "+seccion; }
}
