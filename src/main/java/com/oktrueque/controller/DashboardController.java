package com.oktrueque.controller;

import com.oktrueque.model.Dashboard;
import com.oktrueque.model.Report;
import com.oktrueque.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(method = RequestMethod.GET, value="/")
    public String getDashboard(Model model){
        Report itemsPorMesReport = reportService.getItemsCreatedByMonth();
        Report truequesConcretadosVsIniciadosReport = reportService.getTruequesConcretadosVsIniciados();

        List<Report> reportes = new ArrayList<>();
        reportes.add(itemsPorMesReport);
        reportes.add(truequesConcretadosVsIniciadosReport);

        Dashboard dashboard = new Dashboard();
        dashboard.setName("Moderator Dashboard");
        dashboard.setReportes(reportes);

        model.addAttribute("dashboard",dashboard);
        model.addAttribute("itemsPorMesReport",itemsPorMesReport);
        model.addAttribute("truequesConcretadosVsIniciadosReport",truequesConcretadosVsIniciadosReport);
        return "home";
    }
}
