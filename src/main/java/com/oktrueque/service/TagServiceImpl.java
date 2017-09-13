package com.oktrueque.service;

import com.oktrueque.model.Tag;
import com.oktrueque.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolas on 19/07/17.
 */
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAll(){
        return (List<Tag>) tagRepository.findAll();
    }

    @Override
    public List<Tag> findTagsByIds(List<Long> tagsId) {
        return tagRepository.findAllByIdIn(tagsId);
    }

    @Override
    public void saveTags(List<Tag> tags){tagRepository.save(tags);}

    @Override
    public List<Tag> getAllTags(){
        List<Tag> tags = new ArrayList<>();
        tagRepository.findAll()
                .forEach(tags::add);
        return tags;

    }
    @Override
    public void addTag(Tag Category){
        tagRepository.save(Category);
    }

    @Override
    public Tag getTag(Long id){
        return tagRepository.findOne(id);
    }
    @Override
    public void updateTag(Tag tag){
        tagRepository.save(tag);
    }
    @Override
    public void deleteTag(Long id){
        tagRepository.delete(id);
    }
}
