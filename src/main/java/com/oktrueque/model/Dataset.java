package com.oktrueque.model;

import java.util.ArrayList;

/**
 * Created by Tomas on 11-Nov-17.
 */
public class Dataset {

    private ArrayList<Integer> data;
    private String label;

    public Dataset() {
    }

    public Dataset(ArrayList<Integer> data, String label) {
        this.data = data;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }
}
