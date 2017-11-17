package com.oktrueque.repository;


import com.oktrueque.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUserById(Long id);
    User findByEmail(String email);
    List<User> findByStatus(Integer status);

    @Query("SELECT COUNT(e) FROM User e WHERE e.email=?1 OR e.username=?2")
    Long checkIfUserExists(String email, String username);

    User findByUsername(String username);

    List<User> findAllByIdIn(List<Long> ids);

    Integer countAllByStatus(int status);

    Integer countAllByScore(int score);

}
