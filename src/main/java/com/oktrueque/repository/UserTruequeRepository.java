package com.oktrueque.repository;

import com.oktrueque.model.UserTrueque;
import com.oktrueque.model.UserTruequeId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserTruequeRepository extends PagingAndSortingRepository<UserTrueque,UserTruequeId> {

    List<UserTrueque> getUserTruequeById_UserId(long id);
    List<UserTrueque> findByIdTruequeIdOrderByOrder(Long id);

}
