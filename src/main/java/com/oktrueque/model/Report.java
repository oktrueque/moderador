package com.oktrueque.model;

import java.util.ArrayList;

/**
 * Created by Tomas on 07-Oct-17.
 */
public class Report {

    private ArrayList<Integer> ejeXint;
    private ArrayList<Integer> ejeYint;
    private String nombre;

    public Report(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getEjeXint() {
        return ejeXint;
    }

    public void setEjeXint(ArrayList<Integer> ejeXint) {
        this.ejeXint = ejeXint;
    }

    public ArrayList<Integer> getEjeYint() {
        return ejeYint;
    }

    public void setEjeYint(ArrayList<Integer> ejeYint) {
        this.ejeYint = ejeYint;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
