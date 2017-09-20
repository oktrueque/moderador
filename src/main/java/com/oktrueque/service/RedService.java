package com.oktrueque.service;

import com.oktrueque.model.User;
import com.oktrueque.model.UserTag;

import java.util.List;
import java.util.Set;

/**
 * Created by Facundo on 9/19/2017.
 */
public interface RedService {
    Set<User> getAllUsersWithTags();

    List<User> getUsersByPreferences(Long id);
}
