package com.oktrueque.controller;

import com.oktrueque.model.User;
import com.oktrueque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Felipe on 7/5/2017.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public String getUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public String addUser(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:/users";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public String updateUser(@ModelAttribute User user){
        userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
