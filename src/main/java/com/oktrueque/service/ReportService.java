package com.oktrueque.service;

import com.oktrueque.model.Report;

/**
 * Created by Tomas on 07-Oct-17.
 */
public interface ReportService {

    Report getItemsCreatedByMonth();

    Report getTruequesConcretadosVsIniciados();

    Report itemPorCategoria();

    Report denunciasPorMes();

    Report itemsPorEstado();

    Report usuariosPorEstado();

    Report truequesPorEstado();

    Report usuariosPorScore();

    Report denunciasPorTipos();

    Report nuevosUsuarios();

    Report cantidadTotalUsuarios();

    Report denunciasSinModerar();

    Report itemsSinModerar();

    Report usuariosActivosPorMes();

}
