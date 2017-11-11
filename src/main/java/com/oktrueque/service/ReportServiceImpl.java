package com.oktrueque.service;

import com.oktrueque.model.Dataset;
import com.oktrueque.model.Report;
import com.oktrueque.repository.ItemRepository;
import com.oktrueque.repository.TruequeRepository;
import com.oktrueque.repository.UserRepository;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        Calendar cal = Calendar.getInstance();
//        cal.set(actualYear,actualMonth,1);
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(actualYear,actualMonth,cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
//        itemRepository.countItemByCreationDateBetween(cal.getTime(),cal2.getTime());
        DateTime dateTime = new DateTime();
        Integer actualMonth = dateTime.getMonthOfYear();
        Integer actualYear = dateTime.getYear();
        Report report = new Report();
        Dataset firstDataset = new Dataset();
        ArrayList<Integer> cuatroMesesHaciaAtras = new ArrayList<>();
        ArrayList<Integer> itemsPorMes = new ArrayList<>();
        for (Integer x=0;x<5;x++){
            cuatroMesesHaciaAtras.add(dateTime.getMonthOfYear()-x);
            int aux = itemRepository.countByMonth(actualMonth-x);
            itemsPorMes.add(aux);
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
        report.setReportTitle("Items por mes");

        return report;
    }

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

}
