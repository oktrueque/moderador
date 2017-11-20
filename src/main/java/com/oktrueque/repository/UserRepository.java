package com.oktrueque.repository;


import com.oktrueque.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
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

    Integer countAllByRegisterDateBetween(Date date1, Date date2);

    @Query(nativeQuery = true,
            value="SELECT u.*" +
            "  FROM users u" +
            "  JOIN users_trueques on u.id = users_trueques.id_user" +
            "  JOIN trueques t on users_trueques.id_trueque = t.id" +
            "  WHERE" +
            "    t.ending_date IS NOT NULL" +
            "    AND t.status = ?1" +
            "    AND DATEDIFF(now(),t.ending_date) BETWEEN ?2 AND ?3")
    List<User> usersWithPendingTrueques(int activeStatus, int limiteInferior, int limiteSuperior);

}
