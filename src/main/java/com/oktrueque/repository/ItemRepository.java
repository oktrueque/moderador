package com.oktrueque.repository;

import com.oktrueque.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Tomas on 21-May-17.
 */
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByCategory_Id(int id);
    List<Item> findByStatus(int status);
}
