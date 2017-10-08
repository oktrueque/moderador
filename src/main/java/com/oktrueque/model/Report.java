package com.oktrueque.model;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

/**
 * Created by Tomas on 07-Oct-17.
 */
public class Report {

    private ArrayList<Integer> ejeXint;
    private ArrayList<String> ejeXstring;
    private ArrayList<Integer> ejeYint;
    private ArrayList<String> ejeYstring;
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

    public ArrayList<String> getEjeXstring() {
        return ejeXstring;
    }

    public void setEjeXstring(ArrayList<String> ejeXstring) {
        this.ejeXstring = ejeXstring;
    }

    public ArrayList<String> getEjeYstring() {
        return ejeYstring;
    }

    public void setEjeYstring(ArrayList<String> ejeYstring) {
        this.ejeYstring = ejeYstring;
    }
}
