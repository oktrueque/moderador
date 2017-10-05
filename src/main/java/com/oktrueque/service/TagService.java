package com.oktrueque.service;

import com.oktrueque.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTagsByIds(List<Long> tagsId);

    List<Tag> findAll();

    void saveTags(List<Tag> tags);

    List<Tag> getAllTags();

    void addTag(Tag Category);

    Tag getTag(Long id);

    void updateTag(Tag tag);

    void deleteTag(Long id);
}
