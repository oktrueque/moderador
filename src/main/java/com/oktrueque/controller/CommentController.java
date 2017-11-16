package com.oktrueque.controller;

import com.oktrueque.model.Comment;
import com.oktrueque.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Facundo on 11/15/2017.
 */
@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comments")
    public String getAllComments(Model model){
        model.addAttribute("comments" , commentService.getAllComments());
         return "comments" ;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comments/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id){
        Comment comment = commentService.getCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
