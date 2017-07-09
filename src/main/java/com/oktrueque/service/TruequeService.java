package com.oktrueque.service;

import com.oktrueque.model.Trueque;
import com.oktrueque.repository.TruequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Envy on 8/7/2017.
 */
@Service
public class TruequeService {

    private TruequeRepository truequeRepository;

    @Autowired
    public void setTruequeRepository(TruequeRepository truequeRepository){
        this.truequeRepository = truequeRepository;
    }

    public List<Trueque> getAllTrueques(){
        return (List<Trueque>) this.truequeRepository.findAll();
    }
}
