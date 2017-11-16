package com.oktrueque.service;

import com.oktrueque.model.Comment;

import java.util.List;

/**
 * Created by Facundo on 11/15/2017.
 */
public interface CommentService {
    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    void deleteComment(Long id);
}
