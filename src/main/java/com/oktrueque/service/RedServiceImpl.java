package com.oktrueque.service;

import com.oktrueque.model.*;
import com.oktrueque.repository.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Facundo on 9/19/2017.
 */
public class RedServiceImpl implements RedService{

    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final UserTagRepository userTagRepository;
    private final ItemRepository itemRepository;
    private final ItemTagRepository itemTagRepository;


    public RedServiceImpl(UserRepository userRepository, TagRepository tagRepository,
                          UserTagRepository userTagRepository, ItemRepository itemRepository,
                          ItemTagRepository itemTagRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.userTagRepository = userTagRepository;
        this.itemRepository = itemRepository;
        this.itemTagRepository = itemTagRepository;
    }

    @Override
    public Set<User> getAllUsersWithTags() {
        Set<User> usersWithTags = new HashSet<>();
        userRepository.findAll().forEach(t -> {
            if(!t.getItems().isEmpty() && !t.getTags().isEmpty()){
                t.getItems().forEach(i -> {
                    if(!i.getTags().isEmpty()){
                        usersWithTags.add(t);
                    }
                });
            }
        });
        return usersWithTags;
    }

    @Override
    public Set<User> getUsersByPreferences(Long id) { 
        Set<User> usersWithItemsByPreferences = new HashSet<>();
        User user = userRepository.findOne(id);
        List<Long> idTags = new ArrayList<>();
        user.getTags().forEach(t->{
            idTags.add(t.getId());
        });
        List<ItemTag> itemsTags = (idTags.size()!= 1) ?
                itemTagRepository.findAllByIdTagIdIn(idTags) : itemTagRepository.findByIdTagId(idTags.get(0));
        List<Long> idItems = new ArrayList<>();
        itemsTags.forEach(t->{
            idItems.add(t.getId().getItemId());
        });
        List<Item> items = (idItems.size()!= 1) ?
                itemRepository.findAllByIdIn(idItems) : itemRepository.findById(idItems.get(0));
        items.forEach(t ->{
            if(t.getUser().getId() != id){
                usersWithItemsByPreferences.add(t.getUser());
            }
        });
        return  usersWithItemsByPreferences;
    }

    @Override
    public Set<User> getUsersByPreferencesOfTwoUsers(Long id, Long idUserInitial) {
        Set<User> candidatesUsers = new HashSet<>();
        User userInitial = userRepository.findOne(idUserInitial);
        this.getUsersByPreferences(id).forEach(user-> {
            user.getTags().forEach(tag->{
                userInitial.getItems().forEach(item->{
                    item.getTags().forEach(tagItem ->{
                        if(tag.getId().equals(tagItem.getId()) && !user.getId().equals(idUserInitial)){
                            candidatesUsers.add(user);
                        }
                    });
                });
            });
        });
        return candidatesUsers.stream().distinct().collect(Collectors.toSet());
    }

    @Override
    public List<Item> getItemsToUser(Long id, Long idUserSelected) {
        List<Tag> tags = userRepository.findOne(idUserSelected).getTags();
        return itemRepository.findAllByUser_IdAndTagsIn(id,tags).stream().distinct().collect(Collectors.toList());
    }


}
