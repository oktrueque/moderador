package com.oktrueque.repository;

import com.oktrueque.model.Trueque;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Envy on 8/7/2017.
 */
public interface TruequeRepository extends CrudRepository<Trueque, Long> {

    Trueque findTruequeById(long id);

    List<Trueque> findByStatus(int status);

    Integer countAllByStatus(int status);

}
