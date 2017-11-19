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
        Report itemsPorCategoriaReport = reportService.itemPorCategoria();
        Report denunciasPorMesReport = reportService.denunciasPorMes();
        Report itemsPorEstadoReport = reportService.itemsPorEstado();
        Report usuariosPorEstadoReport = reportService.usuariosPorEstado();
        Report truequesPorEstadoReport = reportService.truequesPorEstado();
        Report usuariosPorScoreReport = reportService.usuariosPorScore();
        Report denunciasPorTiposReport = reportService.denunciasPorTipos();
        Report nuevosUsuariosReport = reportService.nuevosUsuarios();
        Report cantidadTotalUsuariosReport = reportService.cantidadTotalUsuarios();
        Report denunciasSinModerarReport = reportService.denunciasSinModerar();
        Report itemsSinModerarReport = reportService.itemsSinModerar();

//        List<Report> reportes = new ArrayList<>();
//        reportes.add(itemsPorMesReport);
//        reportes.add(truequesConcretadosVsIniciadosReport);
//        reportes.add(itemsPorCategoriaReport);
//        reportes.add(denunciasPorMesReport);
//        reportes.add(itemsPorEstado);

        Dashboard dashboard = new Dashboard();
        dashboard.setName("Moderator Dashboard");
//        dashboard.setReportes(reportes);

        model.addAttribute("dashboard",dashboard);
        model.addAttribute("itemsPorMesReport",itemsPorMesReport);
        model.addAttribute("truequesConcretadosVsIniciadosReport",truequesConcretadosVsIniciadosReport);
        model.addAttribute("itemsPorCategoriaReport",itemsPorCategoriaReport);
        model.addAttribute("denunciasPorMesReport",denunciasPorMesReport);
        model.addAttribute("itemsPorEstadoReport",itemsPorEstadoReport);
        model.addAttribute("usuariosPorEstadoReport",usuariosPorEstadoReport);
        model.addAttribute("truequesPorEstadoReport",truequesPorEstadoReport);
        model.addAttribute("usuariosPorScoreReport",usuariosPorScoreReport);
        model.addAttribute("denunciasPorTiposReport",denunciasPorTiposReport);
        model.addAttribute("nuevosUsuariosReport",nuevosUsuariosReport);
        model.addAttribute("cantidadTotalUsuariosReport",cantidadTotalUsuariosReport);
        model.addAttribute("denunciasSinModerarReport",denunciasSinModerarReport);
        model.addAttribute("itemsSinModerarReport",itemsSinModerarReport);
        return "home";
    }
}
