package com.oktrueque.repository;

import com.oktrueque.model.ComplaintType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Fabrizio SPOSETTI on 31/08/2017.
 */
public interface ComplaintTypeRepository extends CrudRepository<ComplaintType, Integer> {

    Integer countAllById(Long id);
    List<ComplaintType> findAll();

}
