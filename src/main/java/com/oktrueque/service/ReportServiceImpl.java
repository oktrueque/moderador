package com.oktrueque.service;

import com.oktrueque.model.*;
import com.oktrueque.repository.*;

import com.oktrueque.utils.Constants;
import org.joda.time.DateTime;

import java.text.DateFormatSymbols;
import java.util.*;

/**
 * Created by Tomas on 07-Oct-17.
 */
public class ReportServiceImpl implements  ReportService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final TruequeRepository truequeRepository;
    private final CategoryRepository categoryRepository;
    private final ComplaintRepository complaintRepository;
    private final ComplaintTypeRepository complaintTypeRepository;

    public ReportServiceImpl(ItemRepository itemRepository, UserRepository userRepository, TruequeRepository truequeRepository, CategoryRepository categoryRepository, ComplaintRepository complaintRepository, ComplaintTypeRepository complaintTypeRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.truequeRepository = truequeRepository;
        this.categoryRepository = categoryRepository;
        this.complaintRepository = complaintRepository;
        this.complaintTypeRepository = complaintTypeRepository;
    }

    private Dataset setDatasetDataForMonths(ArrayList<Integer> meses){
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<Integer> aux = new ArrayList<>();
        for(int x=0;x<12;x++){
            data.add(x,0);
            aux.add(x,0);
        }
        for(Integer nroMes:meses) {
            switch (nroMes) { // .set(Index,Element)
                case 0:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 1:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 2:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 3:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 4:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 5:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 6:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 7:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 8:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 9:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 10:
                    data.set(nroMes, aux.get(nroMes) + 1);
                case 11:
                    data.set(nroMes, aux.get(nroMes) + 1);
            }
            aux.set(nroMes,data.get(nroMes));
        }
        Dataset dataset = new Dataset();
        dataset.setData(data);
        return dataset;
    }

    @Override
    public Report getItemsCreatedByMonth(){
        DateTime dateTime = new DateTime();
        Integer actualMonth = dateTime.getMonthOfYear();
        Integer actualYear = dateTime.getYear();
        Report report = new Report();
        Dataset firstDataset = new Dataset();
        ArrayList<Integer> cuatroMesesHaciaAtras = new ArrayList<>();
        ArrayList<Integer> itemsPorMes = new ArrayList<>();
        /*TODO Crear metodo en el itemRepository countByYear y recorrer esa lista para buscar por mes */
        for (Integer x=12;x>-1;x--){ //INFO: X-1 = Cantidad de meses hacia atras que se vera en el reporte.
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
        Report report = new Report("TruequesIniciadosVsConcretados","bar","Trueques Iniciados vs Concretados");
        Dataset firstDataset = new Dataset();
        firstDataset.setLabel("Concretados");
        Dataset secondDataset = new Dataset();
        secondDataset.setLabel("Iniciados");
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> mesesList = new ArrayList<>();
        for(int x=0;x<12;x++){
            mesesList.add(getMonthForInt(x));
        }
        report.setLabels(mesesList);
        if(concretados.size()==0){
            ArrayList<Integer> aux = new ArrayList<>();
            aux.add(0);
            firstDataset.setData(aux);
        } else if(iniciados.size()==0){
            ArrayList<Integer> aux = new ArrayList<>();
            aux.add(0);
            secondDataset.setData(aux);
        }
        int mes = -1;
        ArrayList<Integer> meses1 = new ArrayList<>();
        for (Trueque Tc:concretados){
                calendar.setTime(Tc.getAcceptanceDate());
                mes = calendar.get(Calendar.MONTH);
                meses1.add(mes);
        }
        firstDataset = setDatasetDataForMonths(meses1);

        ArrayList<Integer> meses2 = new ArrayList<>();
        for (Trueque Ti:iniciados){
                calendar.setTime(Ti.getAcceptanceDate());
                mes = calendar.get(Calendar.MONTH);
                meses2.add(mes);
        }
        secondDataset = setDatasetDataForMonths(meses2);
        report.setFirstDataset(firstDataset);
        report.setSecondDataset(secondDataset);
        return report;
    }

    @Override
    public Report itemPorCategoria(){
        Report report = new Report("itemsPorCategoriaReport","bar","Items por categoria");
        Dataset dataset = new Dataset();
        List<Item> items = itemRepository.findByStatus(1);
        List<Category> categorias = categoryRepository.findAll();
        ArrayList<String> catNames = new ArrayList<>();
        ArrayList<Integer> data = new ArrayList<>();
        if(categorias.size()==0){
            ArrayList<Integer> aux = new ArrayList<>();
            aux.add(0);
            dataset.setData(aux);
        }
        for(Category cat:categorias){
            catNames.add(cat.getName());
            int cantItemsPorCat = itemRepository.countAllByCategory_Id(cat.getId());
            data.add(cantItemsPorCat);
        }
        report.setLabels(catNames);
        dataset.setData(data);
        dataset.setLabel("Items");
        report.setFirstDataset(dataset);
        return report;
    }
    @Override
    public Report denunciasPorMes(){
        Report report = new Report("denunciasPorMesReport","bar","Denuncias por mes");
        Dataset dataset = new Dataset();
        List<Complaint> denuncias = complaintRepository.findAll();
        Calendar calendar = Calendar.getInstance();
        ArrayList<String> mesesList = new ArrayList<>();
        for(int x=0;x<12;x++){
            mesesList.add(getMonthForInt(x));
        }
        report.setLabels(mesesList);
        int mes = -1;
        if(denuncias.size()==0){
            ArrayList<Integer> aux = new ArrayList<>();
            aux.add(0);
            dataset.setData(aux);
        }
        ArrayList<Integer> meses = new ArrayList<>();
        for (Complaint Co:denuncias){
            calendar.setTime(Co.getDate());
            mes = calendar.get(Calendar.MONTH);
            meses.add(mes);
        }
        dataset = setDatasetDataForMonths(meses);
        dataset.setLabel("Denuncias");
        report.setFirstDataset(dataset);
        return report;
    }

    @Override
    public Report itemsPorEstado(){
        Report report = new Report("itemsPorEstado","doughnut","Items por estado");
        Dataset dataset = new Dataset();
        Integer registrados = itemRepository.countAllByStatus(Constants.ITEM_STATUS_PENDING);
        Integer activos = itemRepository.countAllByStatus(Constants.ITEM_STATUS_ACTIVE);
        Integer baneados = itemRepository.countAllByStatus(Constants.ITEM_STATUS_BANNED);
        Integer eliminados = itemRepository.countAllByStatus(Constants.ITEM_STATUS_EXCHANGED);
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        data.add(registrados);
        labels.add(Constants.ITEM_STATUS_NAME_PENDING);
        data.add(activos);
        labels.add(Constants.ITEM_STATUS_NAME_ACTIVE);
        data.add(baneados);
        labels.add(Constants.ITEM_STATUS_NAME_BANNED);
        data.add(eliminados);
        labels.add(Constants.ITEM_STATUS_NAME_DELETED);
        dataset.setData(data);
        dataset.setLabel("Items");
        report.setFirstDataset(dataset);
        report.setLabels(labels);
        return report;
    }

    @Override
    public Report usuariosPorEstado(){
        Report report = new Report("usuariosPorEstado","doughnut","Usuarios por estado");
        Dataset dataset = new Dataset();
        Integer registrados = userRepository.countAllByStatus(Constants.USER_STATUS_REGISTERED);
        Integer activos = userRepository.countAllByStatus(Constants.USER_STATUS_ACTIVE);
        Integer eliminados = userRepository.countAllByStatus(Constants.USER_STATUS_DELETED);
        Integer baneados = userRepository.countAllByStatus(Constants.USER_STATUS_BANNED);
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        data.add(registrados);labels.add(Constants.USER_STATUS_NAME_REGISTERED);
        data.add(activos);labels.add(Constants.USER_STATUS_NAME_ACTIVE);
        data.add(eliminados);labels.add(Constants.USER_STATUS_NAME_DELETED);
        data.add(baneados);labels.add(Constants.USER_STATUS_NAME_BANNED);
        report.setLabels(labels);
        dataset.setData(data);
        report.setFirstDataset(dataset);
        return report;
    }

    @Override
    public Report truequesPorEstado(){
        Report report = new Report("truequesPorEstado","doughnut","Trueques por estado");
        Dataset dataset = new Dataset();
        Integer propuestos = truequeRepository.countAllByStatus(Constants.TRUEQUE_STATUS_PENDING);
        Integer activos = truequeRepository.countAllByStatus(Constants.TRUEQUE_STATUS_ACTIVE);
        Integer rechazados = truequeRepository.countAllByStatus(Constants.TRUEQUE_STATUS_REJECTED);
        Integer confirmados = truequeRepository.countAllByStatus(Constants.TRUEQUE_STATUS_CONFIRMED);
        Integer cancelados = truequeRepository.countAllByStatus(Constants.TRUEQUE_STATUS_CANCELED);
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        data.add(propuestos);labels.add(Constants.TRUEQUE_STATUS_NAME_PENDING);
        data.add(activos);labels.add(Constants.TRUEQUE_STATUS_NAME_ACTIVE);
        data.add(rechazados);labels.add(Constants.TRUEQUE_STATUS_NAME_REJECTED);
        data.add(confirmados);labels.add(Constants.TRUEQUE_STATUS_NAME_CONFIRMED);
        data.add(cancelados);labels.add(Constants.TRUEQUE_STATUS_NAME_CANCELED);
        report.setLabels(labels);
        dataset.setData(data);
        report.setFirstDataset(dataset);
        return report;
    }

    @Override
    public Report usuariosPorScore(){
        Report report = new Report("usuariosPorScore","doughnut","Usuarios por Score");
        Dataset dataset = new Dataset();
        Integer malos = userRepository.countAllByScore(1);
        Integer regulares = userRepository.countAllByScore(2);
        Integer buenos = userRepository.countAllByScore(3);
        Integer muyBuenos = userRepository.countAllByScore(4);
        Integer excelentes = userRepository.countAllByScore(5);
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        data.add(malos);labels.add("Malos");
        data.add(regulares);labels.add("Regulares");
        data.add(buenos);labels.add("Buenos");
        data.add(muyBuenos);labels.add("Muy buenos");
        data.add(excelentes);labels.add("Excelentes");
        report.setLabels(labels);
        dataset.setData(data);
        report.setFirstDataset(dataset);
        return report;
    }

    @Override
    public Report denunciasPorTipos(){
        Report report = new Report("denunciasPorTipos","doughnut","Denuncias por tipos");
        Dataset dataset = new Dataset();
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        List<ComplaintType> tipos = complaintTypeRepository.findAll();
        for(ComplaintType CT:tipos){
            Integer aux = complaintRepository.countAllByComplaintType_Id(CT.getId());
            data.add(aux);
            labels.add(CT.getName());
        }
        report.setLabels(labels);
        dataset.setData(data);
        report.setFirstDataset(dataset);
        return report;
    }

    @Override
    public Report nuevosUsuarios(){
        Report report = new Report("nuevosUsuarios","","Nuevos usuarios");
        Dataset firstDataset = new Dataset();
        DateTime dateTime = new DateTime();
        Integer actualMonth = dateTime.getMonthOfYear();
        Integer actualYear = dateTime.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(actualYear,actualMonth-1,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date primerDiaDelMes = calendar.getTime();
        calendar.set(actualYear,actualMonth-1,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date ultimoDiaDelMes = calendar.getTime();
        Integer nuevosUsuarios = userRepository.countAllByRegisterDateBetween(primerDiaDelMes,ultimoDiaDelMes);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(nuevosUsuarios);
        firstDataset.setData(data);
        firstDataset.setLabel("Nuevos usuarios");
        report.setFirstDataset(firstDataset);
        return report;
    }

    @Override
    public Report cantidadTotalUsuarios(){
        Report report = new Report("cantTotalUsuarios","","Usuarios");
        Dataset dataset = new Dataset();
        Integer cantidad = userRepository.countAllByStatus(Constants.USER_STATUS_ACTIVE);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(cantidad);
        dataset.setData(data);
        report.setFirstDataset(dataset);
        return report;
    }

    @Override
    public Report denunciasSinModerar(){
        Report report = new Report("denunciasSinModerar","","Denuncias: "+Constants.COMPLAINT_STATUS_NAME_PENDING);
        Dataset dataset = new Dataset();
        ArrayList<Integer> data = new ArrayList<>();
        Integer denuncias = complaintRepository.countAllByStatus(Constants.COMPLAINT_STATUS_PROCESSING);
        data.add(denuncias);
        dataset.setData(data);
        report.setFirstDataset(dataset);
        return  report;
    }

    @Override
    public Report itemsSinModerar(){
        Report report = new Report("itemsSinModerar","","ITEMS: "+Constants.ITEM_STATUS_NAME_PENDING);
        Dataset dataset = new Dataset();
        ArrayList<Integer> data = new ArrayList<>();
        Integer items = itemRepository.countAllByStatus(Constants.ITEM_STATUS_PENDING);
        data.add(items);
        dataset.setData(data);
        report.setFirstDataset(dataset);
        return report;
    }



}
