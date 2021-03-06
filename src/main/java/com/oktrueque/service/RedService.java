package com.oktrueque.service;

import com.oktrueque.model.User;
import com.oktrueque.model.Item;
import java.util.List;
import java.util.Set;

/**
 * Created by Facundo on 9/19/2017.
 */
public interface RedService {
    Set<User> getAllUsersWithTags();

    Set<User> getUsersByPreferences(Long id);

    Set<User> getUsersByPreferencesOfTwoUsers(Long id, Long idUserInitial);

    List<Item> getItemsToUser(Long id, Long idUserSelected);
}
