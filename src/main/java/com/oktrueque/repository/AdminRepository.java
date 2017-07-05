package com.oktrueque.repository;

import com.oktrueque.model.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Envy on 5/7/2017.
 */
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByUsername(String username);
}
