package com.oktrueque.model;

import java.util.List;

/**
 * Created by tomas on 11/11/2017.
 */
public class Dashboard {

    private String dashboardName;
    private List<Report> reportes;

    public Dashboard() {
    }

    public Dashboard(String dashboardName, List<Report> reportes) {
        this.dashboardName = dashboardName;
        this.reportes = reportes;
    }

    public String getDashboardName() {
        return dashboardName;
    }

    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    public List<Report> getReportes() {
        return reportes;
    }

    public void setReportes(List<Report> reportes) {
        this.reportes = reportes;
    }
}
