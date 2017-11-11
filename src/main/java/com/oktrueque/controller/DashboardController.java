package com.oktrueque.controller;

import com.oktrueque.model.Dashboard;
import com.oktrueque.model.Report;
import com.oktrueque.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 07-Oct-17.
 */
@Controller
public class DashboardController {

    private ReportService reportService;

    public DashboardController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(method = RequestMethod.GET, value="/dashboard")
    public String getDashboard(Model model){
        Dashboard dashboard = new Dashboard();
        dashboard.setDashboardName("Moderator Dashboard");
        Report itemsPorMesReport = reportService.getItemsCreatedByMonth();
        List<Report> reportes = new ArrayList<>();
        reportes.add(itemsPorMesReport);
        dashboard.setReportes(reportes);
        model.addAttribute("dashboard",dashboard);
        model.addAttribute("itemsPorMesReport",itemsPorMesReport);
        return "home";
    }
}
