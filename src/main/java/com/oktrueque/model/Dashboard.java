package com.oktrueque.model;

import java.util.List;

/**
 * Created by tomas on 11/11/2017.
 */
public class Dashboard {

    private String name;
    private List<Report> reportes;

    public Dashboard() {
    }

    public Dashboard(String name, List<Report> reportes) {
        this.name = name;
        this.reportes = reportes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Report> getReportes() {
        return reportes;
    }

    public void setReportes(List<Report> reportes) {
        this.reportes = reportes;
    }
}
