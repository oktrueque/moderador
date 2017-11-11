package com.oktrueque.repository;

import com.oktrueque.model.Item;
import com.oktrueque.model.Tag;
import org.joda.time.DateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
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
    List<Item> findAllByUser_IdAndTagsIn(Long id, List<Tag> tags);
    Integer countItemByStatus(int status);
//    Integer countItemByCreationDateBetween(Date dateStart,Date dateEnd);
 //   Integer countItemByCreationDate_Month(int month);
    void deleteAllByUserId(Long userId);

    @Query(nativeQuery = true, value= "select * from items i where month(creation_date) = ?1")
            int countByMonth(int month);

}
