package com.oktrueque.model;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

/**
 * Created by Tomas on 07-Oct-17.
 */
public class Report {

    private String reportName;
    private String reportType; //bar, doughnut, line.
    private ArrayList<String> labels;
    private String reportTitle;
    private Dataset firstDataset;
    private Dataset secondDataset;

    public Report() {
    }

    public Report(String reportName, String reportType, ArrayList<String> labels, String reportTitle, Dataset firstDataset, Dataset secondDataset) {
        this.reportName = reportName;
        this.reportType = reportType;
        this.labels = labels;
        this.reportTitle = reportTitle;
        this.firstDataset = firstDataset;
        this.secondDataset = secondDataset;
    }

    public Report(String reportName, String reportType, String reportTitle) {
        this.reportName = reportName;
        this.reportType = reportType;
        this.reportTitle = reportTitle;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public Dataset getFirstDataset() {
        return firstDataset;
    }

    public void setFirstDataset(Dataset firstDataset) {
        this.firstDataset = firstDataset;
    }

    public Dataset getSecondDataset() {
        return secondDataset;
    }

    public void setSecondDataset(Dataset secondDataset) {
        this.secondDataset = secondDataset;
    }
}
