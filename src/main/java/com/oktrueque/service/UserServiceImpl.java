package com.oktrueque.service;

import com.oktrueque.model.User;
import com.oktrueque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe on 7/5/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users :: add);
        return users;
    }

    public List<User> getUserById(Long id) {
        List<User> user = userRepository.findUserById(id);
        return user;
    }




}
