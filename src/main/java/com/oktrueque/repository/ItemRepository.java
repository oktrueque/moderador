package com.oktrueque.repository;

import com.oktrueque.model.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Tomas on 21-May-17.
 */
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByCategory_Id(int id);
    List<Item> findByStatus(int status);
    List<Item> findByUser_Username(String username, Pageable pageable);
    List<Item> findAllByIdIn(List<Long> ids);
    List<Item> findById(Long id);

}
