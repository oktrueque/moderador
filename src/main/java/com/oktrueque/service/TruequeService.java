package com.oktrueque.service;

import com.oktrueque.model.Item;
import com.oktrueque.model.Trueque;
import com.oktrueque.model.UserTrueque;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by Facundo on 9/23/2017.
 */
public interface TruequeService {

    List<Trueque> getAllTrueques();

    List<UserTrueque> getUserTruequeById_UserId(long id);

    Trueque getTruequeById (long id);

    @Transactional
    void saveTrueque(Map<Integer, List<Item>> participants, Long user1, Long user2, Long user3);
}
