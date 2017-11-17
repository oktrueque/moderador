package com.oktrueque.service;

import com.oktrueque.model.Comment;
import com.oktrueque.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Facundo on 11/15/2017.
 */
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach(comments::add);
        return comments;
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }
}
