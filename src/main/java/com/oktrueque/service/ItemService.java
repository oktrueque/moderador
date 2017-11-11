package com.oktrueque.service;

import com.oktrueque.model.Item;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    void addItem(Item item);

    void deleteItemAlone(Long id);

    List<Item> getItemsByCategory(int id_category);

    List<Item> getItemsByStatus(int status);
    
    Item getItemById(Long id);

    Item saveItem(Item item);

    void approveItem(Long id);

    List<Item> getItemsByUserUsername(String username, Pageable pageable);

    List<Item> findItemsByStatus(Integer status);

    void deleteItemsByUserId(Long id);
}
