package com.oktrueque.service;

import com.oktrueque.model.Item;
import com.oktrueque.repository.ItemRepository;
import com.oktrueque.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public void addItem(Item item){
        itemRepository.save(item);
    }

    @Override
    public void deleteItemAlone(Long id) {
        itemRepository.delete(id);
    }

    @Override
    public List<Item> getItemsByCategory(int id_category) {
        return itemRepository.findByCategory_Id(id_category);
    }

    @Override
    public List<Item> getItemsByStatus(int status){

        return itemRepository.findByStatus(status);
    }

    @Override
    public Item getItemById(Long id){ return itemRepository.findOne(id);}

    @Override
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public void approveItem(Long id) {
        Item item = itemRepository.findOne(id);
        item.setStatus(Constants.ITEM_STATUS_ACTIVE);
        itemRepository.save(item);
    }

    @Override
    public List<Item> getItemsByUserUsername(String username, Pageable pageable) {
        return itemRepository.findByUser_Username(username, pageable);
    }

    @Override
    public List<Item> findItemsByStatus(Integer status) {
        return itemRepository.findByStatus(status);
    }

    @Override
    public void deleteItemsByUserId(Long id) {
        itemRepository.deleteAllByUserId(id);
    }
}
