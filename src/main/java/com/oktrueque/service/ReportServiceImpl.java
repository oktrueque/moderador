package com.oktrueque.service;

import com.oktrueque.model.Report;
import com.oktrueque.repository.ItemRepository;
import com.oktrueque.repository.TruequeRepository;
import com.oktrueque.repository.UserRepository;

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

    public Report getReportDataByReportName(String name){

        return new Report(name);
    };

}
