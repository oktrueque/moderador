package com.oktrueque.service;

import com.oktrueque.model.Dataset;
import com.oktrueque.model.Report;
import com.oktrueque.model.Trueque;
import com.oktrueque.repository.ItemRepository;
import com.oktrueque.repository.TruequeRepository;
import com.oktrueque.repository.UserRepository;

import org.joda.time.DateTime;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Tomas on 07-Oct-17.
 */
public class ReportServiceImpl implements  ReportService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final TruequeRepository truequeRepository;

    public ReportServiceImpl(ItemRepository itemRepository, UserRepository userRepository, TruequeRepository truequeRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.truequeRepository = truequeRepository;
    }

    @Override
    public Report getItemsCreatedByMonth(){
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD"); //mySQL Date column.
//        cal.set(actualYear,actualMonth,1);
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(actualYear,actualMonth,cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
//        itemRepository.countItemByCreationDateBetween(cal.getTime(),cal2.getTime());
        Calendar cal = Calendar.getInstance();
        DateTime dateTime = new DateTime();
        Integer actualMonth = dateTime.getMonthOfYear();
        Integer actualYear = dateTime.getYear();
        Report report = new Report();
        Dataset firstDataset = new Dataset();
        ArrayList<Integer> cuatroMesesHaciaAtras = new ArrayList<>();
        ArrayList<Integer> itemsPorMes = new ArrayList<>();
        /*TODO Crear metodo en el itemRepository countByYear y recorrer esa lista para buscar por mes */
        for (Integer x=7;x>0;x--){ //INFO: X-1 = Cantidad de meses hacia atras que se vera en el reporte.
            if(dateTime.getMonthOfYear()-x>0) {
                cuatroMesesHaciaAtras.add(dateTime.getMonthOfYear() - x);
                int aux = itemRepository.countByMonthAndYear(actualMonth - x, actualYear);
                itemsPorMes.add(aux);
            }
        }
        firstDataset.setData(itemsPorMes);
        firstDataset.setLabel("Items");
        ArrayList<String> meses = new ArrayList<>();
        for (Integer mes:cuatroMesesHaciaAtras) {
            meses.add(getMonthForInt(mes-1));
        }
        report.setLabels(meses);
        report.setFirstDataset(firstDataset);
        report.setReportName("itemsPorMes");
        report.setReportType("bar");
        report.setReportTitle("Items creados por mes, del año actual.");

        return report;
    }


    private String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

    @Override
    public Report getTruequesConcretadosVsIniciados(){

        List<Trueque> concretados = truequeRepository.findByStatus(3);
        List<Trueque> iniciados = truequeRepository.findByStatus(1);

        Report report = new Report("TruequesIniciadosVsConcretados","line","Trueques Iniciados vs Concretados");
        Dataset firstDataset = new Dataset();
        firstDataset.setLabel("Concretados");
        Dataset secondDataset = new Dataset();
        secondDataset.setLabel("Iniciados");

        Calendar calendar = Calendar.getInstance();
        DateTime dateTime = new DateTime();
        Integer actualMonth = dateTime.getMonthOfYear();
        Integer actualYear = dateTime.getYear();
        DateFormatSymbols dfs = new DateFormatSymbols();


        ArrayList<String> mesesList = new ArrayList<>();

        for(int x=11;x>-1;x--){
            mesesList.add(getMonthForInt(x));
        }
        report.setLabels(mesesList);

        int mes = -1;
        for (Trueque Tc:concretados){
            calendar.setTime(Tc.getEndingDate());
            mes = calendar.get(Calendar.MONTH);
            firstDataset = setDatasetDataForAYear(mes,firstDataset);
        }

        for (Trueque Ti:iniciados){
            calendar.setTime(Ti.getEndingDate());
            mes = calendar.get(Calendar.MONTH);
            secondDataset = setDatasetDataForAYear(mes,secondDataset);
        }

        return report;
    }

    private Dataset setDatasetDataForAYear(int mes, Dataset dataset){
        switch (mes){
            case 0:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 1:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 2:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 3:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 4:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 5:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 6:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 7:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 8:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 9:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 10:dataset.getData().add(mes,dataset.getData().get(mes)+1);
            case 11:dataset.getData().add(mes,dataset.getData().get(mes)+1);
        }
        return dataset;
    }

}
