package com.oktrueque.service;

import com.oktrueque.model.User;

import java.util.List;

/**
 * Created by Felipe on 7/5/2017.
 */
public interface UserService {

    List<User> getUsers();

    List<User> getUserById(Long id);

}
