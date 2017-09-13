package com.oktrueque.service;

import com.oktrueque.model.Trueque;
import com.oktrueque.model.UserTrueque;
import com.oktrueque.repository.TruequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oktrueque.repository.UserTruequeRepository;

import java.util.List;

/**
 * Created by Envy on 8/7/2017.
 */
@Service
public class TruequeService {

    private TruequeRepository truequeRepository;
    private UserTruequeRepository userTruequeRepository;

    @Autowired
    public void setTruequeRepository(TruequeRepository truequeRepository, UserTruequeRepository userTruequeRepository){
        this.truequeRepository = truequeRepository;
        this.userTruequeRepository = userTruequeRepository;
    }

    public List<Trueque> getAllTrueques(){
        return (List<Trueque>) this.truequeRepository.findAll();
    }

    public List<UserTrueque> getUserTruequeById_UserId(long id){
        return userTruequeRepository.getUserTruequeById_UserId(id);
    }

    public Trueque getTruequeById (long id){
        return truequeRepository.findTruequeById(id);
    }

}
