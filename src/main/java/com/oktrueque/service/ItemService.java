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

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
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
    public Item getItemById(Long id){ return itemRepository.findOne(id);}

    public Item setItem(Item item){
        return itemRepository.save(item);
    }

    public void approveItem(Long id) {
        Item item = itemRepository.findOne(id);
        item.setStatus(1);
        itemRepository.save(item);
    }

    public List<Item> findItemsByStatus(Integer status) {
        return itemRepository.findByStatus(status);
    }
}
