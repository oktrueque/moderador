package com.oktrueque.repository;
import com.oktrueque.model.Item;
import com.oktrueque.model.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Tomas on 21-May-17.
 */
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByCategory_Id(int id);
    List<Item> findByStatus(int status);
    List<Item> findByUser_Username(String username, Pageable pageable);
    List<Item> findAllByIdInAndStatus(List<Long> ids, Integer idStatus);
    List<Item> findByIdAndStatus(Long id,Integer idStatus);
    List<Item> findTop3ByUser_IdAndTagsInAndStatus(Long id, List<Tag> tags,Integer idStatus);
    void deleteAllByUserId(Long userId);
    @Query(nativeQuery = true, value= "select count(*) from items i where month(creation_date) = ?1 and year(creation_date)=?2")
            Integer countByMonthAndYear(int month, int year);
    Integer countAllByCategory_Id(int catId);
    List<Item> findAll();
    Integer countAllByStatus(int status);


}
