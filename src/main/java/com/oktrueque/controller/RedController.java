package com.oktrueque.controller;

import com.oktrueque.model.User;
import com.oktrueque.service.RedService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Facundo on 9/19/2017.
 */
@Controller
public class RedController {

    @Autowired
    private RedService redService;

    @RequestMapping(method = RequestMethod.GET, value = "/propuesta")
    public String getUserWithTagsAndItemsWithTags(Model model){
        model.addAttribute("usersWithTags", redService.getAllUsersWithTags());
        return "propuesta";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/propuesta/inicio")
    public ResponseEntity<List<User>> getUser(@RequestBody User user){
        List<User> users = redService.getUsersByPreferences(user.getId());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
