package com.oktrueque.service;

import com.oktrueque.model.Dataset;
import com.oktrueque.model.Report;
import com.oktrueque.repository.ItemRepository;
import com.oktrueque.repository.TruequeRepository;
import com.oktrueque.repository.UserRepository;
import org.joda.time.DateTime;

import java.text.DateFormat;
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
    public Report getItemsCreatedByMonth(int actualMonth, int actualYear){
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD"); //mySQL Date column.
//        Calendar cal = Calendar.getInstance();
//        cal.set(actualYear,actualMonth,1);
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(actualYear,actualMonth,cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
////        itemRepository.countItemByCreationDateBetween(cal.getTime(),cal2.getTime());
//
//        DateTime dateTime = new DateTime();
//        Report report = new Report();
//        Dataset firstDataset = new Dataset();
//        ArrayList<Integer> cuatroMesesHaciaAtras = new ArrayList<>();
//        ArrayList<Integer> itemsPorMes = new ArrayList<>();
//        for (int x=0;x<5;x++){
//            cuatroMesesHaciaAtras.add(dateTime.getMonthOfYear()-x);
//            itemsPorMes.add(itemRepository.countByMonth(actualMonth-x));
//        }
//        firstDataset.setData(itemsPorMes);
//        report.setFirstDataset(firstDataset);

         return null;
    }

}
