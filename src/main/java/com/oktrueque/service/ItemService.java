package com.oktrueque.service;

import com.oktrueque.model.Item;
import com.oktrueque.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 21-May-17.
 */
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getItems(){
        List<Item> items = new ArrayList<Item>();
        itemRepository.findAll().forEach(items :: add);
        return items;
    }

    public void addItem(Item item){
        itemRepository.save(item);
    }

    public void deleteItemAlone(Long id) { itemRepository.delete(id);    }

    public List<Item> getItemsByCategory(int id_category) {
        return itemRepository.findByCategory_Id(id_category);
    }

    public List<Item> getItemsByStatus(int status){

        return itemRepository.findByStatus(status);
    }
}