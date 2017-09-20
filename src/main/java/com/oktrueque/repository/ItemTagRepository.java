package com.oktrueque.repository;

import com.oktrueque.model.ItemTag;
import com.oktrueque.model.ItemTagId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemTagRepository extends CrudRepository<ItemTag, ItemTagId> {

    List<ItemTag> findAllByIdTagIdIn(List<Long> tagId);
    List<ItemTag> findByIdTagId(Long tagId);
}
