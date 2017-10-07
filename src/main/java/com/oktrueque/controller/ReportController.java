package com.oktrueque.controller;

import com.oktrueque.model.Report;
import com.oktrueque.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomas on 07-Oct-17.
 */
@Controller
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(method = RequestMethod.GET, value="/reportes/{reportName}")
    public ResponseEntity<Report> getReportData(@PathVariable String reportName){

        Report report = reportService.getReportDataByReportName(reportName);

        return new ResponseEntity(report, HttpStatus.CREATED);
    }
}
