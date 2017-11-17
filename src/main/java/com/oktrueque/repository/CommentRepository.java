package com.oktrueque.repository;

import com.oktrueque.model.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Facundo on 11/15/2017.
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
