package com.oktrueque.repository;

import com.oktrueque.model.Complaint;
import com.oktrueque.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ComplaintRepository extends CrudRepository<Complaint, Long> {

        List<Complaint> findAllByUserTargetId(Long userTargetId);

        List<Complaint> findAll();

    }
