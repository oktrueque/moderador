package com.oktrueque.controller;

import com.oktrueque.model.User;
import com.oktrueque.service.StorageService;
import com.oktrueque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    private UserService userService;
    private StorageService storageService;

    @Autowired
    public UserController(UserService userService, StorageService storageService){
        this.userService = userService;
        this.storageService = storageService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public String getUsers(@RequestParam(required = false) Integer status, Model model){
        if(status == null) status = 0;
        model.addAttribute("users", userService.findUsersByStatus(status));
        return "users";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public String addUser(@ModelAttribute User user, @RequestParam("image") MultipartFile image){
        String imageUrl = "";
        try {
            imageUrl = storageService.store(image, user);
//            model.addAttribute("message", "You successfully uploaded " + image.getOriginalimagename() + "!");
//            images.add(image.getOriginalimagename());
        } catch (Exception e) {
            //model.addAttribute("message", "FAIL to upload " + image.getOriginalimagename() + "!");
        }
        user.setPhoto1(imageUrl);
        User userResponse = userService.addUser(user);
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

    @RequestMapping("/login")
    public String loginUser() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
